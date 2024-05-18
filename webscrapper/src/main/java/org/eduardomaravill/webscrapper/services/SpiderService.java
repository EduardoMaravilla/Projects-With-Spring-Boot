package org.eduardomaravill.webscrapper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpiderService {

    @Autowired
    private IWebScrapperService webScrapperService;

    public void startSpider() {
        String initialPath = "https://arc.dev";
        scrapeAndSavePath(initialPath);
    }

    synchronized public void scrapeAndSavePath(String initialPath) {
        List<String> paths = webScrapperService.getAllPaths(initialPath);
        paths.forEach(path -> {webScrapperService.scrapeAndSave(path);
            scrapeAndSavePath(path);
        });
    }
}
