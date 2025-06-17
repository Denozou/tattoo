package com.landing.tattoo.repository;
import com.landing.tattoo.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository {
    List<Image> findByInCarouselTrue();

}
