package com.itacasa.cloud.service.crawler2.repository;

import com.itacasa.cloud.service.crawler2.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Category Repository
 *
 * @author will
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, String>, JpaSpecificationExecutor
{
    /**
     * 根据名称查询
     *
     * @param name 名称
     * @return
     */
    Category findByName(String name);


}
