package com.alejandro.storewebpage.web;

import com.alejandro.storewebpage.app.usecase.dto.CategoryDto;
import com.alejandro.storewebpage.app.usecase.*;
import com.alejandro.storewebpage.app.usecase.dto.WebPageDto;
import com.alejandro.storewebpage.web.request.CreateWebPageRequest;
import com.alejandro.storewebpage.web.request.UpdateWebPageRequest;
import com.alejandro.storewebpage.web.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/webpage")
public class WebPageController {

    private final FindAllWebPagesUseCase findAllWebPagesUseCase;
    private final FindWebPageByIdUseCase findWebPageByIdUseCase;
    private final CreateWebPageUseCase createWebPageUseCase;
    private final DeleteWebPageUseCase deleteWebPageUseCase;
    private final UpdateWebPageUseCase updateWebPageUseCase;
    private final FindWebPagesByCategoryUseCase findWebPagesByCategoryUseCase;
    private final FindWebPageByTitleUseCase findWebPageByTitleUseCase;

    @Autowired
    public WebPageController(FindAllWebPagesUseCase findAllWebPagesUseCase,
                             FindWebPageByIdUseCase findWebPageByIdUseCase,
                             CreateWebPageUseCase createWebPageUseCase,
                             DeleteWebPageUseCase deleteWebPageUseCase,
                             UpdateWebPageUseCase updateWebPageUseCase,
                             FindWebPagesByCategoryUseCase findWebPagesByCategoryUseCase,
                             FindWebPageByTitleUseCase findWebPageByTitleUseCase) {
        this.findAllWebPagesUseCase = findAllWebPagesUseCase;
        this.findWebPageByIdUseCase = findWebPageByIdUseCase;
        this.createWebPageUseCase = createWebPageUseCase;
        this.deleteWebPageUseCase = deleteWebPageUseCase;
        this.updateWebPageUseCase = updateWebPageUseCase;
        this.findWebPagesByCategoryUseCase = findWebPagesByCategoryUseCase;
        this.findWebPageByTitleUseCase = findWebPageByTitleUseCase;
    }

    @GetMapping
    public ResponseEntity<ListResponse<WebPageResponse>> findAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "id") String sort
    ) {
        Page<WebPageDto> pagesDtoPage = findAllWebPagesUseCase
                .execute(PageRequest.of(page, size, Sort.by(sort).descending()));
        List<WebPageResponse> webPagesResponse = pagesDtoPage
                .getContent()
                .stream()
                .map(w->WebPageResponse.createResponse(
                        w.getId(),
                        w.getTitle(),
                        w.getUrl(),
                        w.getDescription(),
                        CategoryResponse.createResponse(
                                w.getCategoryId(),
                                w.getCategoryName()
                        )

                ))
                .collect(Collectors.toList());
        ListResponse<WebPageResponse> listResponse = ListResponse.createResponse(
                webPagesResponse,
                pagesDtoPage.getNumber(),
                pagesDtoPage.getTotalPages(),
                pagesDtoPage.hasNext(),
                pagesDtoPage.hasPrevious()
        );
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<ListResponse<WebPageResponse>> findByTitle(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "id") String sort,
            @PathVariable String title) {
        Page<WebPageDto> pagesDtoPage = findWebPageByTitleUseCase
                .execute(PageRequest.of(page, size, Sort.by(sort).descending()), title);
        List<WebPageResponse> webPagesResponse = pagesDtoPage
                .getContent()
                .stream()
                .map(w->WebPageResponse.createResponse(
                        w.getId(),
                        w.getTitle(),
                        w.getUrl(),
                        w.getDescription(),
                        CategoryResponse.createResponse(
                                w.getCategoryId(),
                                w.getCategoryName()
                        )

                ))
                .collect(Collectors.toList());
        ListResponse<WebPageResponse> listResponse = ListResponse.createResponse(
                webPagesResponse,
                pagesDtoPage.getNumber(),
                pagesDtoPage.getTotalPages(),
                pagesDtoPage.hasNext(),
                pagesDtoPage.hasPrevious()
        );
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ListResponse<WebPageResponse>> findByCategory(@PathVariable(name = "id") Integer categoryId) {
        Page<WebPageDto> pagesDtoPage = findWebPagesByCategoryUseCase.execute(Pageable.ofSize(50), categoryId);
        List<WebPageResponse> webPagesResponse = pagesDtoPage
                .getContent()
                .stream()
                .map(w->WebPageResponse.createResponse(
                        w.getId(),
                        w.getTitle(),
                        w.getUrl(),
                        w.getDescription(),
                        CategoryResponse.createResponse(
                                w.getCategoryId(),
                                w.getCategoryName()
                        )
                ))
                .collect(Collectors.toList());
        ListResponse<WebPageResponse> listResponse = ListResponse.createResponse(
                webPagesResponse,
                pagesDtoPage.getNumber(),
                pagesDtoPage.getTotalPages(),
                pagesDtoPage.hasNext(),
                pagesDtoPage.hasPrevious()
        );
        return ResponseEntity.ok(listResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SingleResponse<WebPageResponse>> findById(@PathVariable(name = "id") Integer webpageId) {
        WebPageDto webPageDto = findWebPageByIdUseCase.execute(webpageId);
        SingleResponse<WebPageResponse> singleResponse = SingleResponse.createResponse(
                WebPageResponse.createResponse(
                        webPageDto.getId(),
                        webPageDto.getTitle(),
                        webPageDto.getUrl(),
                        webPageDto.getDescription(),
                        CategoryResponse.createResponse(
                                webPageDto.getCategoryId(),
                                webPageDto.getCategoryName()
                        )
                )
        );
        return ResponseEntity.ok(singleResponse);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody CreateWebPageRequest createWebPageRequest) {
        WebPageDto webPageDto = WebPageDto.createDto(
                createWebPageRequest.getTitle(),
                createWebPageRequest.getUrl(),
                createWebPageRequest.getDescription(),
                CategoryDto.createDto(createWebPageRequest.getCategoryId())
        );
        createWebPageUseCase.execute(webPageDto);
        return ResponseEntity.ok(MessageResponse.createResponse("Webpage created successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable(name = "id") Integer webpageIb) {
        deleteWebPageUseCase.execute(webpageIb);
        return ResponseEntity.ok(MessageResponse.createResponse("Webpage deleted successfully"));
    }

    @PutMapping
    public ResponseEntity<MessageResponse> update(@Valid @RequestBody UpdateWebPageRequest updateWebPageRequest) {
        updateWebPageUseCase.execute(WebPageDto.createDto(
                        updateWebPageRequest.getId(),
                        updateWebPageRequest.getTitle(),
                        updateWebPageRequest.getUrl(),
                        updateWebPageRequest.getDescription(),
                        CategoryDto.createDto(updateWebPageRequest.getCategoryId()))
        );
        return ResponseEntity.ok(MessageResponse.createResponse("Webpage updated successfully"));
    }
}
