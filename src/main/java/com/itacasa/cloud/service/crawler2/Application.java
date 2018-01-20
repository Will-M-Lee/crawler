package com.itacasa.cloud.service.crawler2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author will
 */
@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
public class Application
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

}
