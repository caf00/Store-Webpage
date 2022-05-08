package com.alejandro.storewebpage.app.usecase;

import com.alejandro.storewebpage.app.usecase.dto.WebPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindWebPageByTitleUseCase {
    Page<WebPageDto> execute(Pageable pageable, String title);
}
