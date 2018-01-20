package com.itacasa.cloud.service.crawler2.repository;

import com.itacasa.cloud.service.crawler2.domain.Category;
import com.itacasa.cloud.service.crawler2.domain.PicInfo;
import com.itacasa.cloud.service.crawler2.service.CrawlerService;
import com.itacasa.cloud.service.crawler2.utils.DownloadPic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author will
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PicInfoTest
{
    @Autowired
    private CrawlerService crawlerService;

    @Autowired
    private PicInfoRepository picInfoRepository;

    // 测试分类创建
    @Test
    public void test()
    {
        Category category = new Category("1", 2);
        category.create();

    }

    // 测试图片下载
    @Test
    public void downloadTest() throws IOException
    {
        String url      = "https://st.hzcdn.com/simgs/d651ce1509f8979c_9-6335/traditional-kitchen.jpg";
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        DownloadPic.download(
                url,
                "kitchen",
                UUID.randomUUID().toString() + fileName);
    }

    // 测试拿取数据
    @Test
    public void testFetch()
    {
        List<PicInfo> infos = picInfoRepository.findAllByDownload(false);
        System.err.println(infos);
        return;
    }

}