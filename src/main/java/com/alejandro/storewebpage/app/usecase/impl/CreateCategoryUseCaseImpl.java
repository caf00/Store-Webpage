package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.domain.entity.Category;
import com.alejandro.storewebpage.app.domain.repository.CategoryJpaRepository;
import com.alejandro.storewebpage.app.usecase.CreateCategoryUseCase;
import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;
import com.alejandro.storewebpage.app.exception.ResourceAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    private final CategoryJpaRepository categoryJpaRepository;

    @Autowired
    public CreateCategoryUseCaseImpl(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public void execute(CategoryDto categoryDto) {
        validate(categoryDto);
        categoryJpaRepository.save(Category.newCategory(categoryDto.getName()));
    }

    private void validate(CategoryDto categoryDto) {
        Long existName = categoryJpaRepository.countByName(categoryDto.getName());
        if(existName>0) {
            throw new ResourceAlreadyExistException("Category name already exist");
        }
    }
}
