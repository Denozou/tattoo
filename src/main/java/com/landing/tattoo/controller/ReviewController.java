package com.landing.tattoo.controller;

import com.landing.tattoo.model.Review;
import com.landing.tattoo.service.FileStorageService;
import com.landing.tattoo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {
    private final ReviewService reviewService;
    private final FileStorageService fileStorageService;

    @Autowired
    public ReviewController(ReviewService reviewService, FileStorageService fileStorageService) {
        this.reviewService = reviewService;
        this.fileStorageService = fileStorageService;
    }
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }
    @PostMapping
    public ResponseEntity<Review> addReview(
            @RequestParam("name") String name,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam("comment") String comment,
            @RequestParam(value = "photo", required = false) MultipartFile photo) {

        Review review = new Review();
        review.setName(name);
        review.setCountry(country);
        review.setComment(comment);

        if (photo != null && !photo.isEmpty()) {
            try {
                String fileName = fileStorageService.storeFile(photo);
                review.setPhotoUrl(fileName);
            } catch (IOException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        Review savedReview = reviewService.saveReview(review);
        return ResponseEntity.ok(savedReview);
    }
}
