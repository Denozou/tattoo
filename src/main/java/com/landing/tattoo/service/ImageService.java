package com.landing.tattoo.service;

import com.landing.tattoo.model.Image;
import com.landing.tattoo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
    public List<Image> getCarouselImages() {
        return imageRepository.findByInCarouselTrue();
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public Image deleteImage(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isPresent()) {
            imageRepository.delete(image.get());
            return image.get();
        }
        return null;
    }
    public Optional<Image> toggleCarousel(Long id) {
        Optional<Image> imageOpt = imageRepository.findById(id);
        if (imageOpt.isPresent()) {
            Image image = imageOpt.get();

            if (!image.isInCarousel()) {
                long count = imageRepository.findByInCarouselTrue().size();
                if (count >= 8) {
                    return Optional.empty();
                }
            }
            image.setInCarousel(!image.isInCarousel()); //true -> false, false -> true
            return Optional.of(imageRepository.save(image));
        }
        return Optional.empty();
    }

}
