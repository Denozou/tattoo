package com.landing.tattoo.controller;
import com.landing.tattoo.model.Image;
import com.landing.tattoo.service.FileStorageService;
import com.landing.tattoo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*")
public class ImageController {

    private final ImageService imageService;
    private final FileStorageService fileStorageService;

    @Autowired
    public ImageController(ImageService imageService, FileStorageService fileStorageService) {
        this.imageService = imageService;
        this.fileStorageService = fileStorageService;
    }
    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        return ResponseEntity.ok(imageService.getAllImages());
    }

    @GetMapping("/carousel")
    public ResponseEntity<List<Image>> getCarouselImages() {
        return ResponseEntity.ok(imageService.getCarouselImages());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Image> addImage(@RequestParam("image") MultipartFile file) {
        try {
            String fileName = fileStorageService.storeFile(file);
            String fullUrl = "/files/" + fileName;

            Image image = new Image();
            image.setUrl(fullUrl);
            image.setInCarousel(false);

            Image savedImage = imageService.saveImage(image);
            return ResponseEntity.ok(savedImage);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/carousel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Image> toggleCarousel(@PathVariable Long id) {
        Optional<Image> updatedImage = imageService.toggleCarousel(id);
        return updatedImage.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
