package com.itacasa.cloud.service.crawler2.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载图片
 *
 * @author will
 */
public class DownloadPic
{
    public static void download(String urlString, String folder, String filename) throws IOException
    {
        // 构造URL
        URL url = new URL(urlString);

        // 打开连接
        URLConnection con = url.openConnection();

        // 输入流
        InputStream is = con.getInputStream();

        // 1M 的数据缓冲
        byte[] bs = new byte[1024 * 1024];

        // 读取到的数据长度
        int len;

        // 输出的文件流
        File file = new File(File.separator + "Users" + File.separator + "liweimingweiming" + File.separator
                + "spider" + File.separator + "pic/" + folder + File.separator);
        if (!file.exists())
        {
            file.mkdirs();
        }

        OutputStream os = new FileOutputStream(file + File.separator + filename);

        // 开始读取
        while ((len = is.read(bs)) != -1)
        {
            os.write(bs, 0, len);
        }

        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
}
