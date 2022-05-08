package com.alejandro.storewebpage.app.usecase;

import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllCategoriesUseCase {
    Page<CategoryDto> execute(Pageable pageable);
}
