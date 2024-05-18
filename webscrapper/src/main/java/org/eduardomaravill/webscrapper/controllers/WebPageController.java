package org.eduardomaravill.webscrapper.controllers;


import org.eduardomaravill.webscrapper.models.WebPage;
import org.eduardomaravill.webscrapper.services.IWebScrapperService;
import org.eduardomaravill.webscrapper.services.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class WebPageController {

    @Autowired
    IWebScrapperService webScrapperService;

    @Autowired
    SpiderService spiderService;

    @GetMapping("/search")
    public List<WebPage> search(@RequestParam(value = "query", required = false) String query) {
        if (query != null) {
            return new ArrayList<>(webScrapperService.findByText(query));
        }
        return new ArrayList<>();
    }

    @GetMapping("/webscrapper")
    public void scrapeAndSave(@RequestParam(value = "url", required = false) String url) throws IOException {
        webScrapperService.scrapeAndSave(url);
    }

    @GetMapping("/start")
    public void scrapeAndSave(){
        spiderService.startSpider();
    }

}
