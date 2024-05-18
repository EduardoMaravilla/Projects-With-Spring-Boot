package org.eduardomaravill.webscrapper.jobs;

import org.eduardomaravill.webscrapper.services.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WebScrapperJob {

    @Autowired
    private SpiderService spiderService;

    @Scheduled(cron = "0 0 4 * * *")
    public void executeJob(){
        spiderService.startSpider();
    }
}
