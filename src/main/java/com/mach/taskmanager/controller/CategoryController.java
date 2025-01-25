package com.mach.taskmanager.controller;

import com.mach.taskmanager.domain.categories.Categories;
import com.mach.taskmanager.domain.categories.CategoryCreationData;
import com.mach.taskmanager.domain.categories.CategoryDataDetails;
import com.mach.taskmanager.domain.categories.CategoryListData;
import com.mach.taskmanager.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity addCategory(@RequestBody @Valid CategoryCreationData data, UriComponentsBuilder uriBuilder) {

        var category = new Categories(data);
        var uri = uriBuilder.path("/category/{id}").buildAndExpand(category.getId()).toUri();

        repository.save(category);
        return ResponseEntity.created(uri).body(new CategoryDataDetails(category));
    }

    @GetMapping
    public ResponseEntity<Page<CategoryListData>> medicoList(@PageableDefault(sort = {"name"}) Pageable paginable) {
        var page = repository.findAll(paginable).map(CategoryListData::new);
        return ResponseEntity.ok(page);
    }
}
