package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.domain.repository.CategoryJpaRepository;
import com.alejandro.storewebpage.app.usecase.FindAllCategoriesUseCase;
import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllCategoriesUseCaseImpl implements FindAllCategoriesUseCase {

    private final CategoryJpaRepository categoryJpaRepository;

    @Autowired
    public FindAllCategoriesUseCaseImpl(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public Page<CategoryDto> execute(Pageable pageable) {
        return categoryJpaRepository.findAll(pageable)
                .map( c -> CategoryDto.createDto(
                            c.getId(),
                            c.getName()
                        )
                );
    }
}
