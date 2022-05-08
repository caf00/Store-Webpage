package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.domain.repository.WebPageJpaRepository;
import com.alejandro.storewebpage.app.usecase.FindAllWebPagesUseCase;
import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;
import com.alejandro.storewebpage.app.usecase.dto.WebPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class FindAllWebPagesUseCaseImpl implements FindAllWebPagesUseCase {

    private final WebPageJpaRepository webPageJpaRepository;

    @Autowired
    public FindAllWebPagesUseCaseImpl(WebPageJpaRepository webPageJpaRepository) {
        this.webPageJpaRepository = webPageJpaRepository;
    }

    @Override
    public Page<WebPageDto> execute(Pageable pageable) {
        return webPageJpaRepository.findAll(pageable)
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
