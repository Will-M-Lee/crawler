package com.itacasa.cloud.service.crawler2.repository;

import com.itacasa.cloud.service.crawler2.domain.PicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PicInfo Repository
 *
 * @author will
 */
@Repository
public interface PicInfoRepository extends JpaRepository<PicInfo, String>, JpaSpecificationExecutor
{
    /**
     * 根据分类查询
     *
     * @param category 分类
     * @return 数量
     */
    Integer countAllByCategory(String category);

    /**
     * 查询未下载图片信息
     *
     * @return 图片信息集
     */
    List<PicInfo> findAllByDownload(Boolean download);
}
