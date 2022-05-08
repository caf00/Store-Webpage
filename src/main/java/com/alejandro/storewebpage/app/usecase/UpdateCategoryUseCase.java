package com.alejandro.storewebpage.app.usecase;

import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;

public interface UpdateCategoryUseCase {
    void execute(CategoryDto categoryDto);
}
