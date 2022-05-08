package com.alejandro.storewebpage.app.domain.repository;

import com.alejandro.storewebpage.app.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaRepository extends PagingAndSortingRepository<Category, Integer> {
    Page<Category> findAll(Pageable pageable);
    Long countByName(String name);
}
