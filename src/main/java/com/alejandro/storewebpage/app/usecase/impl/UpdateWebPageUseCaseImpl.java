package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.usecase.UpdateWebPageUseCase;
import com.alejandro.storewebpage.app.usecase.dto.WebPageDto;
import com.alejandro.storewebpage.app.domain.entity.Category;
import com.alejandro.storewebpage.app.domain.entity.WebPage;
import com.alejandro.storewebpage.app.domain.repository.CategoryJpaRepository;
import com.alejandro.storewebpage.app.domain.repository.WebPageJpaRepository;
import com.alejandro.storewebpage.app.exception.InvalidArgumentException;
import com.alejandro.storewebpage.app.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UpdateWebPageUseCaseImpl implements UpdateWebPageUseCase {

    private final WebPageJpaRepository webPageJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;

    @Autowired
    public UpdateWebPageUseCaseImpl(WebPageJpaRepository webPageJpaRepository,
                                    CategoryJpaRepository categoryJpaRepository) {
        this.webPageJpaRepository = webPageJpaRepository;
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public void execute(WebPageDto webPageDto) {
        verifyCategoryId(webPageDto.getCategoryId());
        WebPage webPage = webPageJpaRepository
                .findById(webPageDto.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Webpage not found."));
        verifyAttributes(webPage, webPageDto);
        Category category = obtainCategory(webPage, webPageDto);
        webPage.updateFrom(WebPage.newWebPage(
                webPageDto.getId(),
                webPageDto.getTitle(),
                webPageDto.getUrl(),
                webPageDto.getDescription(),
                category));
        webPageJpaRepository.save(webPage);
    }

    private void verifyAttributes(WebPage webPage, WebPageDto webPageDto) {
        if(!webPage.getTitle().equals(webPageDto.getTitle())) {
            Long existTitle = webPageJpaRepository.countByTitle(webPageDto.getTitle());
            if(existTitle>0) {
                throw new InvalidArgumentException("Webpage title already exist");
            }
        }
        if(!webPage.getUrl().equals(webPageDto.getUrl())) {
            Long existUrl = webPageJpaRepository.countByUrl(webPageDto.getUrl());
            if(existUrl>0) {
                throw new InvalidArgumentException("Webpage url already exist");
            }
        }
    }

    private void verifyCategoryId(Integer categoryId) {
        if(categoryId==null) {
            throw new InvalidArgumentException("Category must not be null");
        }
    }

    private Category obtainCategory(WebPage webPage, WebPageDto webPageDto) {
        if(!Objects.equals(webPage.getCategoryId(), webPageDto.getCategoryId())) {
            return categoryJpaRepository
                    .findById(webPageDto.getCategoryId())
                    .orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        }
        return webPage.getCategory();
    }
}
