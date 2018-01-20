package com.itacasa.cloud.service.crawler2.domain;

import com.itacasa.cloud.service.crawler2.common.BaseEntity;
import com.itacasa.cloud.service.crawler2.repository.CategoryRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;

/**
 * @author will
 */
@Getter
@Entity
@Table(name = "tbl_category")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Category extends BaseEntity
{
    // id
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    // 名称
    private String name;

    // 数量
    private Integer quantity;

    // 创建时间
    @CreatedDate
    private Date createDatetime;

    // 更新时间
    @LastModifiedDate
    private Date updateDatetime;

    public Category(String name, Integer quantity)
    {
        this.name = name;
        this.quantity = quantity;
    }

    @Transactional
    public Category create()
    {
        CategoryRepository repository = getBean(CategoryRepository.class);
        return repository.save(this);
    }

    @Transactional
    public void update(String name, Integer quantity)
    {
        this.name = name;
        this.quantity = quantity;

        CategoryRepository repository = getBean(CategoryRepository.class);
        repository.save(this);
    }

}
