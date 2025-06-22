package com.landing.tattoo.service;
import com.landing.tattoo.model.ContentBlock;
import com.landing.tattoo.repository.ContentBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentBlockService {
    private final ContentBlockRepository contentBlockRepository;

    @Autowired
    public ContentBlockService(ContentBlockRepository contentBlockRepository) {
        this.contentBlockRepository = contentBlockRepository;
    }

    public List<ContentBlock> getAllContentBlocks() {
        return contentBlockRepository.findAll();
    }

    public Optional<ContentBlock> getContentBlockByKey(String key) {
        return contentBlockRepository.findByKey(key);
    }

    public ContentBlock updateContentBlock(String key, ContentBlock updatedBlock) {
        Optional<ContentBlock> existingBlockOpt = contentBlockRepository.findByKey(key);

        if (existingBlockOpt.isPresent()) {
            ContentBlock existingBlock = existingBlockOpt.get();
            existingBlock.setContentEn(updatedBlock.getContentEn());
            existingBlock.setContentUa(updatedBlock.getContentUa());
            return contentBlockRepository.save(existingBlock);
        } else {
            updatedBlock.setKey(key);
            return contentBlockRepository.save(updatedBlock);
        }
    }
}
