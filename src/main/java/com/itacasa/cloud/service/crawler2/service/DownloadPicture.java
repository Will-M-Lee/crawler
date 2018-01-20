package com.itacasa.cloud.service.crawler2.service;

import com.itacasa.cloud.service.crawler2.utils.DownloadPic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 下载图片
 *
 * @author will
 */
@Slf4j
@Service
public class DownloadPicture
{
    public void download(String url, String category)
    {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        try
        {
            DownloadPic.download(url, category, fileName);
        }
        catch (IOException e)
        {
            log.error("download picture err:" + e.getMessage());
        }
    }
}
