package com.alejandro.storewebpage.app.domain.repository;

import com.alejandro.storewebpage.app.domain.entity.Category;
import com.alejandro.storewebpage.app.domain.entity.WebPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebPageJpaRepository extends PagingAndSortingRepository<WebPage, Integer> {
    Page<WebPage> findAll(Pageable pageable);
    Page<WebPage> findByCategory(Pageable pageable, Category category);
    Page<WebPage> findByTitleIgnoreCaseContaining(Pageable pageable, String title);
    Long countByUrl(String url);
    Long countByTitle(String title);
}
