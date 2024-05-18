package org.eduardomaravill.webscrapper.services;

import org.eduardomaravill.webscrapper.models.WebPage;

import java.io.IOException;
import java.util.List;

public interface IWebScrapperService {

    void scrapeAndSave(String url);
    List<WebPage> findByText(String text);

    List<String> getAllPaths(String initialPath);

    boolean existsByUrl(String url);
}
