package com.alejandro.storewebpage.app.usecase.impl;

import com.alejandro.storewebpage.app.usecase.DeleteWebPageUseCase;
import com.alejandro.storewebpage.app.domain.entity.WebPage;
import com.alejandro.storewebpage.app.domain.repository.WebPageJpaRepository;
import com.alejandro.storewebpage.app.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteWebPageUseCaseImpl implements DeleteWebPageUseCase {

    private final WebPageJpaRepository webPageJpaRepository;

    @Autowired
    public DeleteWebPageUseCaseImpl(WebPageJpaRepository webPageJpaRepository) {
        this.webPageJpaRepository = webPageJpaRepository;
    }

    @Override
    public void execute(Integer webpageId) {
        WebPage webPage = webPageJpaRepository.findById(webpageId)
                .orElseThrow(()-> new ResourceNotFoundException("Webpage not found"));
        webPageJpaRepository.delete(webPage);
    }
}
