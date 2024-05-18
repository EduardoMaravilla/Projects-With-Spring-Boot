package org.eduardomaravill.webscrapper.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "webpages")
@NoArgsConstructor
@AllArgsConstructor
public class WebPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domain;
    @Column(unique = true)
    private String url;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String picture;
    private Integer ranking;
}
