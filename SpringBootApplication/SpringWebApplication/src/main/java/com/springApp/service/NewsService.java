package com.springApp.service;

import com.springApp.model.News;
import com.springApp.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News findById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public News save(News news) {
        return newsRepository.save(news);
    }

    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return newsRepository.existsById(id);
    }

    public List<News> findByCategoryId(Long categoryId) {
        return newsRepository.findByCategoryId(categoryId);
    }
}
