package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;
import com.alejandro.storewebpage.app.usecase.dto.WebPageDto;
import com.alejandro.storewebpage.app.usecase.FindWebPagesByCategoryUseCase;
import com.alejandro.storewebpage.app.domain.entity.Category;
import com.alejandro.storewebpage.app.domain.repository.CategoryJpaRepository;
import com.alejandro.storewebpage.app.domain.repository.WebPageJpaRepository;
import com.alejandro.storewebpage.app.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindWebPagesByCategoryUseCaseImpl implements FindWebPagesByCategoryUseCase {

    private final CategoryJpaRepository categoryJpaRepository;
    private final WebPageJpaRepository webPageJpaRepository;

    @Autowired
    public FindWebPagesByCategoryUseCaseImpl(CategoryJpaRepository categoryJpaRepository,
                                             WebPageJpaRepository webPageJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
        this.webPageJpaRepository = webPageJpaRepository;
    }

    @Override
    public Page<WebPageDto> execute(Pageable pageable, Integer categoryId) {
        Category category = categoryJpaRepository
                .findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        return webPageJpaRepository.findByCategory(pageable, category)
                .map(wp -> WebPageDto.createDto(
                        wp.getId(),
                        wp.getTitle(),
                        wp.getUrl(),
                        wp.getDescription(),
                        CategoryDto.createDto(
                                wp.getCategoryId(),
                                wp.getCategoryName()
                        )
                ));
    }
}
