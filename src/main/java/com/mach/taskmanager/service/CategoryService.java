package com.mach.taskmanager.service;

import com.mach.taskmanager.domain.categories.Categories;
import com.mach.taskmanager.domain.categories.CategoryCreationData;
import com.mach.taskmanager.domain.categories.CategoryDataDetails;
import com.mach.taskmanager.domain.categories.CategoryListData;
import com.mach.taskmanager.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<CategoryDataDetails> addCategory(CategoryCreationData data, UriComponentsBuilder uriBuilder) {
        var category = new Categories(data);
        var uri = uriBuilder.path("/category/{id}").buildAndExpand(category.getId()).toUri();

        repository.save(category);
        return ResponseEntity.created(uri).body(new CategoryDataDetails(category));
    }

    public Page<CategoryListData> listCategories(Pageable pageable) {
        return repository.findAll(pageable).map(CategoryListData::new);
    }
}
