package com.learnings.adityasamant.controller;

import com.learnings.adityasamant.model.Content;
import com.learnings.adityasamant.repository.ContentJdbcTemplateRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content/jdbc")
@CrossOrigin
public class ContentJdbcController {


    private final ContentJdbcTemplateRepository repository;

    public ContentJdbcController(ContentJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.getAllContent();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return repository.getContent(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content) {
        repository.createContent(content.title(), content.desc(), content.status().name(), content.contentType().name(), content.url());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id) {
        if (repository.getContent(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
        }
        repository.updateContent(content.id(), content.title(), content.desc(), content.status().name(), content.contentType().name(), content.url());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        repository.deleteContent(id);
    }
}
