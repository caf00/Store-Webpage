package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;
import com.alejandro.storewebpage.app.usecase.dto.WebPageDto;
import com.alejandro.storewebpage.app.usecase.FindWebPageByTitleUseCase;
import com.alejandro.storewebpage.app.domain.repository.WebPageJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindWebPageByTitleUseCaseImpl implements FindWebPageByTitleUseCase {

    private final WebPageJpaRepository webPageJpaRepository;

    @Autowired
    public FindWebPageByTitleUseCaseImpl(WebPageJpaRepository webPageJpaRepository) {
        this.webPageJpaRepository = webPageJpaRepository;
    }

    @Override
    public Page<WebPageDto> execute(Pageable pageable, String title) {
        return webPageJpaRepository.findByTitleIgnoreCaseContaining(pageable, title)
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
