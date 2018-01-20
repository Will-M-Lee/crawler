package com.itacasa.cloud.service.crawler2.handle;

import com.google.common.collect.Maps;
import com.itacasa.cloud.service.crawler2.utils.HtmlHeaders;
import com.itacasa.cloud.service.crawler2.utils.HttpClientUtils;
import com.itacasa.cloud.service.crawler2.utils.SubUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author will
 */
@Component
public class SpiderCategory
{
    @Autowired
    private SpiderCategoryData spiderCategoryData;

    public void spiderCategory()
    {
        // 元素
        Map<String, String> params = Maps.newHashMap();

        // 请求头
        Map<String, String> headers = HtmlHeaders.getHomeHtmlHeaders();

        // 图片主页
        String url = "https://www.houzz.it/photos";

        // 获取html
        String html = HttpClientUtils.sendGet(url, headers, params);

        // 类型转换
        Document document = Jsoup.parse(html);

        // 获取分类链接
        Elements elements = document
                .select("div[id=hz-br-filter-group-room]")
                .select("div[class=hz-linklist-container]")
                .select("ul[class=hz-br-navigation-filter hz-br-navigation-filter--1 hz-linklist ]")
                .select("li[class=hz-link__item text-s  hz-link__item--depth-1]");
        for (Element element : elements)
        {
            String categoryHtml = element
                    .select("a")
                    .attr("href");
            String rgex     = "photos/(.*?)$";
            String category = SubUtil.sub(categoryHtml, rgex);


            spiderCategoryData.spider(category);
        }

    }
}
