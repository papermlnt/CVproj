package com.cvproject.cvProject.service;

import com.cvproject.cvProject.repository.PublisherRepository;
import com.cvproject.cvProject.exception.PublisherNotFoundException;
import com.cvproject.cvProject.exception.UserNotFoundException;
import com.cvproject.cvProject.model.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublishers() {
        List<Publisher> publisherList = publisherRepository.findAll();
        return publisherList.stream()
                .sorted(Comparator.comparing(Publisher::getName))
                .collect(Collectors.toList());
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(()-> new PublisherNotFoundException("Not found"));

    }

    @Transactional(readOnly = true)
    public Publisher updatePublisher(Publisher id) {
        Publisher publisher = publisherRepository.findById(id.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        publisher.setId(id.getId());
        publisher.setBooks(id.getBooks());
        publisher.setDescription(id.getDescription());
        publisher.setName(id.getName());
        return publisherRepository.save(publisher);
    }

    @Transactional(readOnly = true)
    public void deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                    .orElseThrow(() -> new PublisherNotFoundException("Publisher not found"));
        publisherRepository.deleteById(publisher.getId());
    }

    @Transactional(readOnly = true)
    public Publisher createPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }
}
