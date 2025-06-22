package com.landing.tattoo.repository;
import com.landing.tattoo.model.ContentBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentBlockRepository extends JpaRepository<ContentBlock, Long>  {
    Optional<ContentBlock> findByKey(String key);

}
