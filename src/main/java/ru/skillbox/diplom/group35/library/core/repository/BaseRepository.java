package ru.skillbox.diplom.group35.library.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<M, ID> extends JpaRepository<M, ID> {
}
