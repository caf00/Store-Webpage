package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.domain.entity.Category;
import com.alejandro.storewebpage.app.domain.entity.WebPage;
import com.alejandro.storewebpage.app.domain.repository.CategoryJpaRepository;
import com.alejandro.storewebpage.app.domain.repository.WebPageJpaRepository;
import com.alejandro.storewebpage.app.exception.InvalidArgumentException;
import com.alejandro.storewebpage.app.exception.ResourceAlreadyExistException;
import com.alejandro.storewebpage.app.exception.ResourceNotFoundException;
import com.alejandro.storewebpage.app.usecase.CreateWebPageUseCase;
import com.alejandro.storewebpage.app.usecase.dto.WebPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateWebPageUseCaseImpl implements CreateWebPageUseCase {

    private final WebPageJpaRepository webPageJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;

    @Autowired
    public CreateWebPageUseCaseImpl(WebPageJpaRepository webPageJpaRepository,
                                    CategoryJpaRepository categoryJpaRepository) {
        this.webPageJpaRepository = webPageJpaRepository;
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public void execute(WebPageDto webPageDto) {
        validate(webPageDto);
        Category category = categoryJpaRepository
                .findById(webPageDto.getCategory().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));
        WebPage webPage = WebPage.newWebPage(
                webPageDto.getTitle(),
                webPageDto.getUrl(),
                webPageDto.getDescription(),
                category
        );
        webPageJpaRepository.save(webPage);
    }

    private void validate(WebPageDto webPageDto) {
        if(webPageDto.getCategory().getId()==null) {
            throw new InvalidArgumentException("Category id must not be null");
        }
        Long existTitle = webPageJpaRepository.countByTitle(webPageDto.getTitle());
        if(existTitle>0) {
            throw new ResourceAlreadyExistException("Webpage title already exist");
        }
        Long existUrl = webPageJpaRepository.countByUrl(webPageDto.getUrl());
        if(existUrl>0) {
            throw new ResourceAlreadyExistException("Webpage url already exist");
        }
    }
}
