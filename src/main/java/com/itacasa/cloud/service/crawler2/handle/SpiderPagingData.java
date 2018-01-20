package com.itacasa.cloud.service.crawler2.handle;

import com.itacasa.cloud.service.crawler2.domain.PicInfo;
import com.itacasa.cloud.service.crawler2.repository.PicInfoRepository;
import com.itacasa.cloud.service.crawler2.utils.HtmlHeaders;
import com.itacasa.cloud.service.crawler2.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author will
 */
@Slf4j
@Component
public class SpiderPagingData
{
    @Autowired
    private ParsePagingHtml parsePagingHtml;

    @Autowired
    private PicInfoRepository picInfoRepository;

    public void spiderData(String url, Map<String, String> params, String category)
    {
        Map<String, String> headers = HtmlHeaders.getPageHtmlHeaders();

        String html = HttpClientUtils.sendGet(url, headers, params);
        if (!StringUtils.isBlank(html))
        {
            // 使用线程池提交任务
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            try
            {
//                executorService.submit(() ->
//                        {
                List<PicInfo> picInfos = parsePagingHtml.parsePageHtml(html, category);
                picInfoRepository.save(picInfos);
//                        }
//                );
            }
            catch (Exception e)
            {
                log.error("storage Error:" + e.getMessage());
            }
        }
    }
}
