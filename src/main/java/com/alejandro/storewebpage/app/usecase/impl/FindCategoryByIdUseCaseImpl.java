package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.domain.entity.Category;
import com.alejandro.storewebpage.app.domain.repository.CategoryJpaRepository;
import com.alejandro.storewebpage.app.exception.ResourceNotFoundException;
import com.alejandro.storewebpage.app.usecase.FindCategoryByIdUseCase;
import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FindCategoryByIdUseCaseImpl implements FindCategoryByIdUseCase {

    private final CategoryJpaRepository categoryJpaRepository;

    @Autowired
    public FindCategoryByIdUseCaseImpl(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public CategoryDto execute(Integer categoryId) {
        Category category = categoryJpaRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        return CategoryDto.createDto(category.getId(), category.getName());
    }
}
