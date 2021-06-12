package com.example.demo.service.impl;

import com.example.demo.entity.film.Seo;
import com.example.demo.entity.user.News;
import com.example.demo.repository.NewsRepository;
import com.example.demo.service.interfaces.NewsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NewsServiceImpl extends ImageServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public Optional<News> findWithImagesById(Long newsId) {
        return newsRepository.findWithImagesById(newsId);
    }

    @Override
    public void updateNews(Long newsId,
                           String newsName,
                           String newsDescription,
                           String newsVideoLink,
                           Boolean newsStatus,
                           LocalDate newsPublishDate,
                           MultipartFile newsMainImage,
                           MultipartFile[] newsImages,
                           Seo seo) {
        findWithImagesById(newsId).ifPresent(news -> {
            news.setName(newsName);
            news.setDescription(newsDescription);
            news.setVideoLink(newsVideoLink);
            news.setActive(newsStatus);
            news.setPublishDate(newsPublishDate);
            checkNewsImage(news, newsMainImage, newsImages);

            if (!news.getSeo().equals(seo)){
                news.setSeo(seo);
            }

            newsRepository.saveAndFlush(news);
        });
    }

    @Override
    public void createNews(String newsName,
                           String newsDescription,
                           String newsVideoLink,
                           Boolean newsStatus,
                           LocalDate newsPublishDate,
                           MultipartFile newsMainImage,
                           MultipartFile[] newsImages,
                           Seo seo) {
        News news = new News();
        news.setName(newsName);
        news.setDescription(newsDescription);
        news.setVideoLink(newsVideoLink);
        news.setActive(newsStatus);
        news.setPublishDate(newsPublishDate);
        news.setSeo(seo);
        checkNewsImage(news, newsMainImage, newsImages);
        newsRepository.save(news);
    }

    @Override
    public void deleteById(Long newsId) {
        News news = new News();

        deleteImage(news.getMainImage());
        deleteImageSet(news.getNewsImages());
        newsRepository.deleteById(newsId);
    }

    void checkNewsImage(News news, MultipartFile mainImage, MultipartFile[] images) {
        if (!(mainImage == null) && !mainImage.isEmpty()){
            deleteImage(news.getMainImage());
            news.setMainImage(saveImage(mainImage));
        }
        if (!(images == null) && !(images.length == 0)) {
            if(!(images[0] == null) && !(images[0].isEmpty())) {
                deleteImageSet(news.getNewsImages());
                news.setNewsImages(saveImageArray(images));
            }
        }
    }
}
