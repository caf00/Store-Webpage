package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.usecase.DeleteCategoryUseCase;
import com.alejandro.storewebpage.app.domain.entity.Category;
import com.alejandro.storewebpage.app.domain.repository.CategoryJpaRepository;
import com.alejandro.storewebpage.app.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategoryUseCaseImpl implements DeleteCategoryUseCase {

    private final CategoryJpaRepository categoryJpaRepository;

    @Autowired
    public DeleteCategoryUseCaseImpl(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public void execute(Integer categoryId) {
        Category category = categoryJpaRepository
                        .findById(categoryId)
                        .orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        categoryJpaRepository.delete(category);
    }
}
