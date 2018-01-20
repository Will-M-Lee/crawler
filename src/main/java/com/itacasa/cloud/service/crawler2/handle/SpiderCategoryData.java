package com.itacasa.cloud.service.crawler2.handle;

import com.google.common.collect.Maps;
import com.itacasa.cloud.service.crawler2.domain.Category;
import com.itacasa.cloud.service.crawler2.repository.CategoryRepository;
import com.itacasa.cloud.service.crawler2.repository.PicInfoRepository;
import com.itacasa.cloud.service.crawler2.utils.HtmlHeaders;
import com.itacasa.cloud.service.crawler2.utils.HttpClientUtils;
import com.itacasa.cloud.service.crawler2.utils.SubUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author will
 */
@Slf4j
@Component
public class SpiderCategoryData
{
    @Autowired
    private SpiderPagingData spiderPagingData;

    @Autowired
    private CategoryRepository categoryRepository;

    // 抓取分类分页
    public void spider(String category)
    {
        Map<String, String> params = Maps.newHashMap();

        Map<String, String> headers = HtmlHeaders.getPageHtmlHeaders();
        // 分类首页链接
        String url = "https://www.houzz.com/photos/" + category + "/p/0";

        // 计算获取的次数
        Integer count = 0;

        // 分类总数
        Integer amount = null;

        // 抓取分类数量
        Boolean tag = true;

        while (tag)
        {
            // 抓取分类数量
            String html = HttpClientUtils.sendGet(url, headers, params);
            String rgex = "\"totalCount\":(.*?),";
            String num  = SubUtil.sub(html, rgex);
            if (!num.isEmpty())
            {
                amount = Integer.valueOf(num);
                tag = false;
            }
            count++;
            if (count > 5)
            {
                tag = false;
            }

        }
        if (count > 5)
        {
            return;
        }

        Integer actual = amount;

        //读取数据库存储数量
        Integer originalQuantity;

        Category category1 = categoryRepository.findByName(category);


        if (category1 == null)
        {
            Category _category = new Category(category, amount);
            _category.create();
        }
        else
        {
            originalQuantity = category1.getQuantity();
            actual = amount - originalQuantity;
        }

        category1.update(category, amount);

        // 使用线程池提交任务
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        for (int i = 0; i <= actual; i += 8)
        {
            // 拼接url
            String _url = "https://www.houzz.com/photos/" + category + "/p/" + i;
            try
            {
//                executorService.submit(() ->
                spiderPagingData.spiderData(
                        _url,
                        params,
                        category);
//                        ));

            }
            catch (Exception e)
            {
                log.error("spiderData Error:" + e.getMessage());
            }

        }
    }
}
