package com.alejandro.storewebpage.app.usecase;

import com.alejandro.storewebpage.app.usecase.dto.WebPageDto;

public interface FindWebPageByIdUseCase {
    WebPageDto execute(Integer id);
}
