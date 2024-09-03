package com.springApp.controller;

import com.springApp.model.News;
import com.springApp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        News news = newsService.findById(id);
        if (news == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"Новость с id " + id + " не найдена.\"}");
        }
        return ResponseEntity.ok(news);
    }

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.findAll();
        return ResponseEntity.ok(newsList);
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news) {
        News createdNews = newsService.save(news);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNews);
    }

    @PutMapping
    public ResponseEntity<?> updateNews(@RequestBody News news) {
        if (newsService.existsById(news.getId())) {
            News updatedNews = newsService.save(news);
            return ResponseEntity.ok(updatedNews);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"message\": \"Новость с id " + news.getId() + " не найдена.\"}");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id) {
        if (newsService.existsById(id)) {
            newsService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"message\": \"Новость с id " + id + " не найдена.\"}");
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<News>> getNewsByCategoryId(@PathVariable Long id) {
        List<News> newsList = newsService.findByCategoryId(id);
        return ResponseEntity.ok(newsList);
    }
}
