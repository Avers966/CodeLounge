package ru.skillbox.diplom.group35.library.core.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public class BaseRepositoryImpl<Entity extends BaseEntity>
                    extends SimpleJpaRepository<Entity, UUID>
                    implements BaseRepository<Entity> {

    EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<Entity, UUID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void deleteById(@NonNull UUID uuid) {
        super.findById(uuid).ifPresent(x -> {
            x.setIsDeleted(true);
            super.save(x);
        });
    }

    @Transactional
    @Override
    public void delete(@NonNull Entity entity) {
        entity.setIsDeleted(true);
        super.save(entity);
    }

    @Transactional
    public void deleteAll(@NonNull Iterable<? extends Entity> entities) {
        entities.forEach(entity -> entity.setIsDeleted(true));
        super.saveAll(entities);
    }

    @Transactional
    @Override
    public void hardDelete(Entity entity) {
        super.delete(entity);
    }

    @Transactional
    @Override
    public void hardDeleteById(UUID uuid) {
        super.findById(uuid).ifPresent(super::delete);
    }

    @Transactional
    @NonNull
    public Entity getById(@NonNull UUID uuid) {
        return super.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }
}
