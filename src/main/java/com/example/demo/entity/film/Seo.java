package com.example.demo.entity.film;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "seo")
public class Seo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seo_id")
    private Long seoId;
    @URL
    private String url;
    private String title;
    private String keywords;
    @Column(columnDefinition = "text", length = 2000)
    private String description;

    public Seo( String url, String title, String keywords, String description) {
        this.url = url;
        this.title = title;
        this.keywords = keywords;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Seo{" +
                "seoID=" + seoId +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", keywords='" + keywords + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seo)) return false;
        Seo seo = (Seo) o;
        return Objects.equals(url, seo.url) &&
                Objects.equals(title, seo.title) &&
                Objects.equals(keywords, seo.keywords) &&
                Objects.equals(description, seo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, title, keywords, description);
    }
}
