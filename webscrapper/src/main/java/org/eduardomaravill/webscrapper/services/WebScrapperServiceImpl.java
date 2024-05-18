package org.eduardomaravill.webscrapper.services;

import org.eduardomaravill.webscrapper.models.WebPage;
import org.eduardomaravill.webscrapper.repository.WebPageRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WebScrapperServiceImpl implements IWebScrapperService {
    private static final String URL_REGEX = "^(https?|ftp)://[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(:\\d+)?(/[\\w\\-\\\\./?%&=]*)?$";
    private static final Pattern pattern = Pattern.compile(URL_REGEX);
    @Autowired
    WebPageRepository webPageRepository;

    @Override
    public void scrapeAndSave(String url) {
        if (existsByUrl(url)) {
            return;
        }
        Document document;
        String title;
        String content;
        String picture;
        try {
            document = Jsoup.connect(url).get();
            title = document.title();
            content = document.select("meta[name=description]").attr("content");
            picture = document.select("meta[property=og:image]").attr("content");
        } catch (Exception e) {
            return;
        }
        WebPage webPage = new WebPage();
        webPage.setUrl(url);
        webPage.setTitle(title);
        webPage.setContent(content);
        webPage.setPicture(picture);
        webPage.setDomain(getDomainFromUrl(url));
        webPageRepository.save(webPage);
    }

    @Override
    public List<WebPage> findByText(String text) {
        return webPageRepository.findByText(text);
    }

    @Override
    synchronized public List<String> getAllPaths(String url) {
        System.out.println(url);
        if (!isValidUrl(url)) {
            return new ArrayList<>();
        }
        Set<String> paths = new HashSet<>();
        Document document;
        Elements links;
        try {
            document = Jsoup.connect(url).get();
            links = document.select("a[href]");
        } catch (IOException e) {
            return new ArrayList<>();
        }
        links.forEach(link -> {
            String path = link.attr("href");
            if (path.startsWith("/")) {
                path = "https://" + getDomainFromUrl(url) + path;
            }
            if (!existsByUrl(url)) {
                paths.add(path);
            }
        });
        return new ArrayList<>(paths);
    }

    @Override
    public boolean existsByUrl(String url) {
        return webPageRepository.existsByUrl(url);
    }

    private String getDomainFromUrl(String url) {
        String domain = url.replaceAll("^(https?://)?(www\\.)?", "");
        int index = domain.indexOf('/');
        if (index != -1) {
            domain = domain.substring(0, index);
        }
        return domain;
    }

    public static boolean isValidUrl(String url) {
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }
}
