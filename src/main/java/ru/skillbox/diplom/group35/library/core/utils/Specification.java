package ru.skillbox.diplom.group35.library.core.utils;

import lombok.experimental.UtilityClass;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseSearchDto;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity_;

import javax.persistence.metamodel.SingularAttribute;
import java.util.function.Supplier;

@UtilityClass
public class Specification {
    public final org.springframework.data.jpa.domain.Specification EMPTY_SPECIFICATION = (root, query, criteriaBuilder) -> null;

    public <T, V> org.springframework.data.jpa.domain.Specification<T> equal(SingularAttribute<T, V> field, V value, boolean isSkipNullValues) {
        return nullValueCheck(value, isSkipNullValues, () -> ((root, query, builder) -> {
            query.distinct(true);
            return builder.equal(root.get(field), value);
        }));
    }

    public <T, V> org.springframework.data.jpa.domain.Specification<T> nullValueCheck(V value, boolean isSkipNullValues, Supplier<org.springframework.data.jpa.domain.Specification<T>> specificationSupplier) {
        return value == null && isSkipNullValues ? EMPTY_SPECIFICATION : (org.springframework.data.jpa.domain.Specification) specificationSupplier.get();
    }

    public org.springframework.data.jpa.domain.Specification getBaseSpecification(BaseSearchDto searchDto) {
        return equal(BaseEntity_.id, searchDto.getId(), true)
                .and(equal(BaseEntity_.isDeleted, searchDto.getIsDeleted(), true));
    }
}
