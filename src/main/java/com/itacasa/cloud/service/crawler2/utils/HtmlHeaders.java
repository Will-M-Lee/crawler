package com.itacasa.cloud.service.crawler2.utils;


import java.util.HashMap;
import java.util.Map;

/**
 * 请求头信息
 *
 * @author will
 */
public class HtmlHeaders
{

    // 获取主页请求头
    public static Map<String, String> getHomeHtmlHeaders()
    {
        // 请求头
        Map<String, String> headers = new HashMap<>();
        headers.put(SysConstant.Header.ACCEPT, "text/html,application/xhtml+xm…plication/xml;q=0.9,*/*;q=0.8");
        headers.put(SysConstant.Header.ACCEPT_ENCODING, "gzip, deflate, br");
        headers.put(SysConstant.Header.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        headers.put(SysConstant.Header.CACHE_CONTROL, "max-age=0");
        headers.put(SysConstant.Header.CONNECTION, "keep-alive");
        headers.put(SysConstant.Header.HOST, "www.houzz.com");
        headers.put(SysConstant.Header.UPGRADE_INSECURE_REQUESTS, "1");
        return headers;
    }

    // 获取图片请求头
    public static Map<String, String> getPicHtmlHeaders()
    {
        Map<String, String> headers = new HashMap<>();
        headers.put(SysConstant.Header.ACCEPT, "*/*");
        headers.put(SysConstant.Header.ACCEPT_ENCODING, "gzip, deflate, br");
        headers.put(SysConstant.Header.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        headers.put(SysConstant.Header.CONNECTION, "keep-alive");
        headers.put(SysConstant.Header.HOST, "st.hzcdn.com");
        headers.put(SysConstant.Header.USER_AGENT, SysConstant.user_agent[(int) (SysConstant.user_agent.length * Math.random())]);
        return headers;
    }

    // 获取页面请求头
    public static Map<String, String> getPageHtmlHeaders()
    {
        Map<String, String> headers = new HashMap<>();
        headers.put(SysConstant.Header.ACCEPT, "*/*");
        headers.put(SysConstant.Header.ACCEPT_ENCODING, "gzip, deflate, br");
        headers.put(SysConstant.Header.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        headers.put(SysConstant.Header.CONNECTION, "keep-alive");
        headers.put(SysConstant.Header.HOST, "www.houzz.com");
        headers.put(SysConstant.Header.UPGRADE_INSECURE_REQUESTS, "1");
        headers.put(SysConstant.Header.USER_AGENT, SysConstant.user_agent[(int) (SysConstant.user_agent.length * Math.random())]);

        return headers;
    }

}
