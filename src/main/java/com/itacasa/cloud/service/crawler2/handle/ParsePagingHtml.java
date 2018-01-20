package com.itacasa.cloud.service.crawler2.handle;

import com.google.common.collect.Lists;
import com.itacasa.cloud.service.crawler2.domain.PicInfo;
import com.itacasa.cloud.service.crawler2.utils.HtmlHeaders;
import com.itacasa.cloud.service.crawler2.utils.HttpClientUtils;
import com.itacasa.cloud.service.crawler2.utils.SubUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 根据分页查询图片详情
 *
 * @author will
 */
@Slf4j
@Component
public class ParsePagingHtml
{
    public List<PicInfo> parsePageHtml(String html, String category)
    {
        // 图片集
        List<PicInfo> picInfos = Lists.newArrayList();

        /*
         * 获取dom并解析
         */
        Document document = Jsoup.parse(html);
        Elements elements = document
                .select("div[class=hz-br-container__resultset]")
                .select("div[class=hz-card clearfix]")
                .select("div[class= hz-br-container hz-spf-animation-container  hz-br-container__photos ]")
                .select("div[class=hz-space-card hz-space-card-xl hz-track-me]");
        int index = 0;
        for (Element element : elements)
        {
            // 获取跳转页
            String picHtml = element
                    .select("div[class=hz-space-card-xl__left hz-space-card__image-container hz-space-card-xl__image-container]")
                    .select("a")
                    .attr("href");

//            String _picHtml="https://www.houzz.com/photo/95537966-bathrooms-transitional-bathroom-atlanta";
            Map<String, String> headers = HtmlHeaders.getPicHtmlHeaders();

            // 获取跳转页详情
            String _documentString = HttpClientUtils.sendGet(picHtml, null, null);

            Document _document = Jsoup.parse(_documentString);

//            Document _document = (Document) DocumentHelper.parseText(_documentString);

            // 获取图片标签
            Elements picElements = _document
                    .select("div[id=photo]")
                    .select("section[class=hz-carded space]")
                    .select("div[class=row]")
                    .select("div[class=col-xs-12 space-image pinchable]")
                    .select("img");

            String pic = picElements
                    .attr("data-imgsrc");

            String rgex = "\"name\": \"(.*?)\"";
            String name = SubUtil.sub(_documentString, rgex);

            PicInfo picInfo = new PicInfo(category, name, pic, picHtml);
            picInfos.add(picInfo);
            log.info("成功爬取【" + pic + "】的基本信息 ");
            if (index++ >= 7)
            {
                break;
            }
        }
        return picInfos;
    }
}
