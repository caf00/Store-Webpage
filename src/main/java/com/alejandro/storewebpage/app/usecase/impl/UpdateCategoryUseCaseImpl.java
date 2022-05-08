package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.domain.entity.Category;
import com.alejandro.storewebpage.app.domain.repository.CategoryJpaRepository;
import com.alejandro.storewebpage.app.exception.ResourceAlreadyExistException;
import com.alejandro.storewebpage.app.exception.ResourceNotFoundException;
import com.alejandro.storewebpage.app.usecase.UpdateCategoryUseCase;
import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCategoryUseCaseImpl implements UpdateCategoryUseCase {

    private final CategoryJpaRepository categoryJpaRepository;

    @Autowired
    public UpdateCategoryUseCaseImpl(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;;
    }

    @Override
    public void execute(CategoryDto categoryDto) {
        Long existCategoryName = categoryJpaRepository.countByName(categoryDto.getName());
        if(existCategoryName>0) {
            throw new ResourceAlreadyExistException("Category name already exist");
        }
        Category category = categoryJpaRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.updateFrom(categoryDto.getName());
        categoryJpaRepository.save(category);
    }
}
