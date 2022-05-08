package com.alejandro.storewebpage.app.usecase;

import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;

public interface FindCategoryByIdUseCase {
    CategoryDto execute(Integer id);
}
