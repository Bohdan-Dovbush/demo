package com.example.demo.controller.admin;

import com.example.demo.entity.film.Seo;
import com.example.demo.entity.user.News;
import com.example.demo.service.interfaces.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("admin/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String news(Model model) {
        model.addAttribute("newsList", newsService.findAll());
        return "admin/news/adminNews";
    }

    @GetMapping("/create")
    public String createNews() {
        return "admin/news/createNews";
    }

    @GetMapping(value = "/edit", params = {"newsId"})
    public String editNews(Model model, @RequestParam Long newsId) {
        Optional<News> optionalNews = newsService.findWithImagesById(newsId);
        if (optionalNews.isPresent()) {
            model.addAttribute("news", optionalNews.get());
            return "admin/news/editNews";
        }
        return "redirect:/admin/news";
    }

    @GetMapping(value = "/delete", params = {"newsId"})
    public String deleteNews(@RequestParam Long newsId) {
        newsService.deleteById(newsId);
        return "redirect:/admin/news";
    }

    @PostMapping("/update")
    public String updateNews(@RequestParam Long newsId,
                             @RequestParam String newsName,
                             @RequestParam String newsDescription,
                             @RequestParam String newsVideoLink,
                             @RequestParam Boolean newsStatus,
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newsPublishDate,
                             @RequestParam(required = false) MultipartFile newsMainImage,
                             @RequestParam(required = false) MultipartFile[] newsImages,
                             @RequestParam String seoURL,
                             @RequestParam String seoTitle,
                             @RequestParam String seoKeyWords,
                             @RequestParam String seoDescription) {
        newsService.updateNews(newsId, newsName, newsDescription, newsVideoLink, newsStatus, newsPublishDate,
                newsMainImage, newsImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/news";
    }

    @PostMapping("/save")
    public String saveNews(@RequestParam String newsName,
                           @RequestParam String newsDescription,
                           @RequestParam String newsVideoLink,
                           @RequestParam Boolean newsStatus,
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate newsPublishDate,
                           @RequestParam(required = false) MultipartFile newsMainImage,
                           @RequestParam(required = false) MultipartFile[] newsImages,
                           @RequestParam String seoURL,
                           @RequestParam String seoTitle,
                           @RequestParam String seoKeyWords,
                           @RequestParam String seoDescription) {

        newsService.createNews(newsName, newsDescription, newsVideoLink, newsStatus, newsPublishDate,
                newsMainImage, newsImages, new Seo(seoURL, seoTitle, seoKeyWords, seoDescription));
        return "redirect:/admin/news";
    }
}
