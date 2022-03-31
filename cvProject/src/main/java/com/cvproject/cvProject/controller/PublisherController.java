package com.cvproject.cvProject.controller;

import com.cvproject.cvProject.model.Publisher;
import com.cvproject.cvProject.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/publisher")
public class PublisherController {
    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/getPublishers")
    public void getAllPublishers() {
        List<Publisher> allPublishers = publisherService.getAllPublishers();
        allPublishers.stream()
                .sorted(Comparator.comparing(Publisher::getName))
                .collect(Collectors.toList());

    }

    @PostMapping("/create")
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherService.createPublisher(publisher);
    }

    @GetMapping("/publisher/{id}")
    public Publisher getPublisherById(@PathVariable("id")Long id) {
        return publisherService.getPublisherById(id);
    }

    @PostMapping("/update")
    public Publisher updatePublisher(@RequestBody Publisher publisher) {
        return publisherService.updatePublisher(publisher);
    }

    @DeleteMapping(path = "{publisherid}")
    public void deletePublisher(@PathVariable("publisherid") Long id) {
        publisherService.deletePublisher(id);
    }
}
