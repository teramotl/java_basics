package com.springApp.controller;

import com.springApp.dto.NewsResponseDTO;
import com.springApp.model.Category;
import com.springApp.model.News;
import com.springApp.service.CategoryService;
import com.springApp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        News news = newsService.findById(id);
        if (news == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"Новость с id " + id + " не найдена.\"}");
        }
        NewsResponseDTO response = new NewsResponseDTO(
                news.getId(),
                news.getTitle(),
                news.getText(),
                news.getDate(),
                news.getCategory() != null ? news.getCategory().getTitle() : null
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NewsResponseDTO>> getAllNews() {
        List<News> newsList = newsService.findAll();
        List<NewsResponseDTO> responseList = newsList.stream()
                .map(news -> new NewsResponseDTO(
                        news.getId(),
                        news.getTitle(),
                        news.getText(),
                        news.getDate(),
                        news.getCategory() != null ? news.getCategory().getTitle() : null
                ))
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @PostMapping
    public ResponseEntity<?> createNews(@RequestBody Map<String, Object> newsData) {
        String title = (String) newsData.get("title");
        String text = (String) newsData.get("text");
        String categoryName = (String) newsData.get("category");

        Category category = categoryService.findByName(categoryName);

        if (category == null) {
            category = new Category();
            category.setTitle(categoryName);
            category = categoryService.save(category);
        }

        News news = new News();
        news.setTitle(title);
        news.setText(text);
        news.setDate(LocalDateTime.now()); // Set the current date and time
        news.setCategory(category);

        News createdNews = newsService.save(news);
        NewsResponseDTO response = new NewsResponseDTO(
                createdNews.getId(),
                createdNews.getTitle(),
                createdNews.getText(),
                createdNews.getDate(),
                createdNews.getCategory() != null ? createdNews.getCategory().getTitle() : null
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateNews(@RequestBody News news) {
        if (newsService.existsById(news.getId())) {
            News updatedNews = newsService.save(news);
            NewsResponseDTO response = new NewsResponseDTO(
                    updatedNews.getId(),
                    updatedNews.getTitle(),
                    updatedNews.getText(),
                    updatedNews.getDate(),
                    updatedNews.getCategory() != null ? updatedNews.getCategory().getTitle() : null
            );
            return ResponseEntity.ok(response);
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
    public ResponseEntity<List<NewsResponseDTO>> getNewsByCategoryId(@PathVariable Long id) {
        List<News> newsList = newsService.findByCategoryId(id);
        List<NewsResponseDTO> responseList = newsList.stream()
                .map(news -> new NewsResponseDTO(
                        news.getId(),
                        news.getTitle(),
                        news.getText(),
                        news.getDate(),
                        news.getCategory() != null ? news.getCategory().getTitle() : null
                ))
                .toList();
        return ResponseEntity.ok(responseList);
    }
}
