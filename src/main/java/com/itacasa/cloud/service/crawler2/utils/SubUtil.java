package com.itacasa.cloud.service.crawler2.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配
 *
 * @author will
 */
public class SubUtil
{
    /**
     * 正则匹配
     *
     * @param soap 原字符串
     * @param rgex 正则表达式
     * @return 目标
     */
    public static String sub(String soap, String rgex)
    {
        // 匹配的模式
        Pattern pattern = Pattern.compile(rgex);
        Matcher result  = pattern.matcher(soap);
        while (result.find())
        {
            return result.group(1);
        }
        return "";
    }


}