package com.itacasa.cloud.service.crawler2.Scheduled;

import com.itacasa.cloud.service.crawler2.domain.PicInfo;
import com.itacasa.cloud.service.crawler2.repository.PicInfoRepository;
import com.itacasa.cloud.service.crawler2.service.CrawlerService;
import com.itacasa.cloud.service.crawler2.utils.DownloadPic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 定时任务
 * 多线程结束后才会触发Delay操作设置
 *
 * @author will
 */
@Component
public class ScheduledTask
{
    @Autowired
    private CrawlerService crawlerService;

    @Autowired
    private PicInfoRepository picInfoRepository;

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 2)
    public void spider() throws InterruptedException
    {
        crawlerService.start();
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 2)
    public void downloadPic() throws IOException
    {
        List<PicInfo> picInfo = picInfoRepository.findAllByDownload(false);
        for (PicInfo _picInfo : picInfo)
        {
            String fileName = _picInfo.getImgUrl().substring(_picInfo.getImgUrl().lastIndexOf("/") + 1);

            DownloadPic.download(_picInfo.getImgUrl(), _picInfo.getCategory(), UUID.randomUUID().toString() + fileName);

            _picInfo.updateDownloadStatus(true);
        }
    }

//    @Scheduled(fixedDelay = 1000 * 100000)
//    public void test() throws InterruptedException
//    {
//        System.err.println("scheduled" + new Date());
//        // 使用线程池提交任务
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        for(int i=0;i<100;i++)
//        {
//        executorService.submit(() ->
//                {
//                    try
//                    {
//
//                            output();
//
//
//                    }
//                    catch (InterruptedException e)
//                    {
//                        System.err.println("output() error:" + e.getMessage());
//                    }
//                }
//        );
//        }
//    }
//
//    public void output() throws InterruptedException
//    {
//        System.err.println("scheduled:" +Thread.currentThread().getName()+":"+ new Date());
//        Thread.sleep((long)( 1000 * 10*Math.random()));
//        return;
//    }

}
