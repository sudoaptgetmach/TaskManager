package com.mach.taskmanager.controller;

import com.mach.taskmanager.domain.categories.CategoryCreationData;
import com.mach.taskmanager.domain.categories.CategoryDataDetails;
import com.mach.taskmanager.domain.categories.CategoryListData;
import com.mach.taskmanager.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping("/category")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<CategoryDataDetails> addCategory(@RequestBody @Valid CategoryCreationData data, UriComponentsBuilder uriBuilder) {
        return service.addCategory(data, uriBuilder);
    }

    @GetMapping("/categories")
    public ResponseEntity<Page<CategoryListData>> categoryList(@PageableDefault(sort = {"name"}) Pageable paginable) {
        return ResponseEntity.ok(service.listCategories(paginable));
    }
}
