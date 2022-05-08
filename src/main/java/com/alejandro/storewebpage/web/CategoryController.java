package com.alejandro.storewebpage.web;

import com.alejandro.storewebpage.app.usecase.*;
import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;
import com.alejandro.storewebpage.web.request.CreateCategoryRequest;
import com.alejandro.storewebpage.web.request.UpdateCategoryRequest;
import com.alejandro.storewebpage.web.response.CategoryResponse;
import com.alejandro.storewebpage.web.response.ListResponse;
import com.alejandro.storewebpage.web.response.MessageResponse;
import com.alejandro.storewebpage.web.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final FindAllCategoriesUseCase findAllCategoriesUseCase;
    private final CreateCategoryUseCase createCategoryUseCase;
    private final FindCategoryByIdUseCase findCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    @Autowired
    public CategoryController(FindAllCategoriesUseCase findAllCategoriesUseCase,
                              CreateCategoryUseCase createCategoryUseCase,
                              FindCategoryByIdUseCase findCategoryByIdUseCase,
                              UpdateCategoryUseCase updateCategoryUseCase,
                              DeleteCategoryUseCase deleteCategoryUseCase) {
        this.findAllCategoriesUseCase = findAllCategoriesUseCase;
        this.createCategoryUseCase = createCategoryUseCase;
        this.findCategoryByIdUseCase = findCategoryByIdUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }

    @GetMapping
    public ResponseEntity<ListResponse<CategoryResponse>> findAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "id") String sort
    ) {
        Page<CategoryDto> categoryDtoPage = findAllCategoriesUseCase.execute(PageRequest.of(page, size, Sort.by(sort).descending()));
        List<CategoryResponse> categoriesResponse = categoryDtoPage
                .getContent()
                .stream()
                .map(c->CategoryResponse.createResponse(
                        c.getId(),
                        c.getName()
                ))
                .collect(Collectors.toList());
        ListResponse<CategoryResponse> listResponse = ListResponse.createResponse(
                categoriesResponse,
                categoryDtoPage.getNumber(),
                categoryDtoPage.getTotalPages(),
                categoryDtoPage.hasNext(),
                categoryDtoPage.hasPrevious()
        );
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleResponse<CategoryResponse>> findById(@PathVariable Integer id) {
        CategoryDto categoryDto = findCategoryByIdUseCase.execute(id);
        SingleResponse<CategoryResponse> singleResponse = SingleResponse.createResponse(
                CategoryResponse.createResponse(
                        categoryDto.getId(),
                        categoryDto.getName()
                )
        );
        return ResponseEntity.ok(singleResponse);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        CategoryDto categoryDto = CategoryDto.createDto(createCategoryRequest.getName());
        createCategoryUseCase.execute(categoryDto);
        return ResponseEntity.ok(MessageResponse.createResponse("Category created successfully"));
    }

    @PutMapping
    public ResponseEntity<MessageResponse> update(@Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        CategoryDto categoryDto = CategoryDto.createDto(
                        updateCategoryRequest.getId(),
                        updateCategoryRequest.getName());
        updateCategoryUseCase.execute(categoryDto);
        return ResponseEntity.ok(MessageResponse.createResponse("Category updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable(name = "id") Integer categoryId) {
        deleteCategoryUseCase.execute(categoryId);
        return ResponseEntity.ok(MessageResponse.createResponse("Category deleted successfully"));
    }
}
