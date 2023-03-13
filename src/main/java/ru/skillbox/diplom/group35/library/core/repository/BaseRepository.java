package ru.skillbox.diplom.group35.library.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<Entity extends BaseEntity> extends JpaRepository<Entity, UUID> {
    void delete(@NonNull Entity entity);
    void deleteById(@NonNull UUID uuid);

    void deleteAll(@NonNull Iterable<? extends Entity> entities);
    void hardDelete(Entity entity);
    void hardDeleteById(UUID uuid);
    @NonNull
    @Override
    Optional<Entity> findById(@NonNull UUID uuid);
}
