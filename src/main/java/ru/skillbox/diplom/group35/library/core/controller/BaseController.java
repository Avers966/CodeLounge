package ru.skillbox.diplom.group35.library.core.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseDto;
import ru.skillbox.diplom.group35.library.core.dto.base.BaseSearchDto;

import java.util.UUID;

public interface BaseController<Dto extends BaseDto, SearchDto extends BaseSearchDto> {

    @GetMapping(value = "/{uuid}")
    ResponseEntity<Dto> getById(@PathVariable UUID uuid);

    @GetMapping
    ResponseEntity<Page<Dto>> getAll(SearchDto searchDto, Pageable page);

    @PostMapping
    ResponseEntity<Dto> create(@RequestBody Dto dto);

    @PutMapping
    ResponseEntity<Dto> update(@RequestBody Dto dto);

    @DeleteMapping(value = "/{uuid}")
    void deleteById(@PathVariable UUID uuid);
}
