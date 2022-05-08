package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.domain.entity.WebPage;
import com.alejandro.storewebpage.app.domain.repository.WebPageJpaRepository;
import com.alejandro.storewebpage.app.exception.ResourceNotFoundException;
import com.alejandro.storewebpage.app.usecase.FindWebPageByIdUseCase;
import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;
import com.alejandro.storewebpage.app.usecase.dto.WebPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindWebPageByIdUseCaseImpl implements FindWebPageByIdUseCase {

    private final WebPageJpaRepository webPageJpaRepository;

    @Autowired
    public FindWebPageByIdUseCaseImpl(WebPageJpaRepository webPageJpaRepository) {
        this.webPageJpaRepository = webPageJpaRepository;
    }

    @Override
    public WebPageDto execute(Integer webPageId) {
        WebPage webPage = webPageJpaRepository.findById(webPageId)
                .orElseThrow(() -> new ResourceNotFoundException("Web page not found"));
        return WebPageDto.createDto(
                webPage.getId(),
                webPage.getTitle(),
                webPage.getUrl(),
                webPage.getDescription(),
                CategoryDto.createDto(
                        webPage.getCategoryId(),
                        webPage.getCategoryName()
                )
        );
    }
}
