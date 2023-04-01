package ru.skillbox.diplom.group35.library.core.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseSearchDto;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity_;

import java.util.List;
import java.time.ZonedDateTime;
import java.util.function.Supplier;
import javax.persistence.metamodel.SingularAttribute;

@UtilityClass
public class SpecificationUtil {
    public final Specification EMPTY_SPECIFICATION = (root, query, criteriaBuilder) -> null;

    public <T, V> Specification<T> equal(SingularAttribute<T, V> field, V value, boolean isSkipNullValues) {
        return nullValueCheck(value, isSkipNullValues, () -> ((root, query, builder) -> {
            query.distinct(true);
            return builder.equal(root.get(field), value);
        }));
    }

    public <T> Specification<T> likeLowerCase(SingularAttribute<T, String> field, String value,
                                              boolean isSkipNullValues) {
        return nullValueCheck(value, isSkipNullValues, () -> ((root, query, builder) -> {
            query.distinct(true);
            return builder.like(
                    builder.lower(root.get(field)), "%" + value.toLowerCase() + "%");
        }));
    }

    public <T> Specification<T> between(SingularAttribute<T, ZonedDateTime> field,
            ZonedDateTime valueFrom, ZonedDateTime valueTo, boolean isSkipNullValues) {

        return nullValueCheck(valueFrom, valueTo, isSkipNullValues, () ->
                ((root, query, builder) -> {
                    if (valueFrom == null) {
                        return builder.lessThanOrEqualTo(root.get(field), valueTo);
                    } else if (valueTo == null) {
                        return builder.greaterThanOrEqualTo(root.get(field), valueFrom);
                    } else {
                        return builder.between(root.get(field), valueFrom, valueTo);
                    }
                }));
    }

    public <T, V> Specification<T> in(SingularAttribute<T, V> field, List<V> value, boolean isSkipNullValues) {
        return nullValueCheck(value, isSkipNullValues, () -> ((root, query, builder) -> {
                query.distinct(true);
                return root.get(field).in(value);
        }));
    }

    public <T, V> Specification<T> notIn(SingularAttribute<T, V> field, List<V> value, boolean isSkipNullValues) {
        return nullValueCheck(value, isSkipNullValues, () -> ((root, query, builder) -> {
            query.distinct(true);
            return builder.not(root.get(field).in(value));
        }));
    }

    public <T, V> Specification<T> nullValueCheck(V value, boolean isSkipNullValues,
            Supplier<Specification<T>> specificationSupplier) {
        return value == null && isSkipNullValues ? EMPTY_SPECIFICATION : specificationSupplier.get();
    }

    public <T, V> Specification<T> nullValueCheck(V value1, V value2, boolean isSkipNullValues,
            Supplier<Specification<T>> specificationSupplier) {
        return value1 == null && value2 == null && isSkipNullValues ?
                EMPTY_SPECIFICATION : specificationSupplier.get();
    }

    public Specification getBaseSpecification(BaseSearchDto searchDto) {
        return equal(BaseEntity_.id, searchDto.getId(), true)
                .and(equal(BaseEntity_.isDeleted, searchDto.getIsDeleted(), true));
    }
}
