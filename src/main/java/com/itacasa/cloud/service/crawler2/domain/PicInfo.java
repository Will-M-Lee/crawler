package com.itacasa.cloud.service.crawler2.domain;

import com.itacasa.cloud.service.crawler2.common.BaseEntity;
import com.itacasa.cloud.service.crawler2.repository.PicInfoRepository;
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
@Table(name = "tbl_pic")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PicInfo extends BaseEntity
{

    // id
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    // 分类
    private String category;

    // 设计师
    private String designer;

    // 图片地址
    private String imgUrl;

    // 原地址
    private String originUrl;

    // 是否已下载
    private Boolean download;

    // 创建时间
    @CreatedDate
    private Date createDatetime;

    // 更新时间
    @LastModifiedDate
    private Date updateDatetime;

    public PicInfo(String category, String designer, String imgUrl, String originUrl)
    {
        this.category = category;
        this.designer = designer;
        this.imgUrl = imgUrl;
        this.originUrl = originUrl;
        this.download = false;
    }

    @Transactional
    public PicInfo create()
    {
        PicInfoRepository repository = getBean(PicInfoRepository.class);
        return repository.save(this);
    }

    @Transactional
    public void updateDownloadStatus(boolean download)
    {
        this.download = download;

        PicInfoRepository repository = getBean(PicInfoRepository.class);
        repository.save(this);
    }

}
