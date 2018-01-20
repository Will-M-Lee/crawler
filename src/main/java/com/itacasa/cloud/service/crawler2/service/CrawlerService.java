package com.itacasa.cloud.service.crawler2.service;

import com.itacasa.cloud.service.crawler2.handle.SpiderCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 爬取服务
 *
 * @author will
 */
@Service
public class CrawlerService
{
    @Autowired
    private SpiderCategory spiderCategory;

    public void start()
    {
        spiderCategory.spiderCategory();
    }
}
