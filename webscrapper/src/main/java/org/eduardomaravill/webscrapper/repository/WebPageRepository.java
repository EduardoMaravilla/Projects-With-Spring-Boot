package org.eduardomaravill.webscrapper.repository;

import org.eduardomaravill.webscrapper.models.WebPage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WebPageRepository extends CrudRepository<WebPage,Long> {
    @Query("SELECT w FROM WebPage w WHERE w.domain LIKE %:text% OR w.url LIKE %:text% OR w.title LIKE %:text% OR w.content LIKE %:text% ORDER BY w.ranking DESC")
    List<WebPage> findByText(@Param("text") String text);

    WebPage findByUrl(String url);
    boolean existsByUrl(String url);
}
