package com.landing.tattoo.controller;
import com.landing.tattoo.model.ContentBlock;
import com.landing.tattoo.service.ContentBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin(origins = "*")
public class ContentBlockController {
    private final ContentBlockService contentBlockService;

    @Autowired
    public ContentBlockController(ContentBlockService contentBlockService) {
        this.contentBlockService = contentBlockService;
    }

    @GetMapping
    public ResponseEntity<List<ContentBlock>> getAllContentBlocks() {
        return ResponseEntity.ok(contentBlockService.getAllContentBlocks());
    }

    @GetMapping("/{key}")
    public ResponseEntity<ContentBlock> getContentBlockByKey(@PathVariable String key) {
        Optional<ContentBlock> contentBlock = contentBlockService.getContentBlockByKey(key);
        return contentBlock.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{key}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ContentBlock> updateContentBlock(
            @PathVariable String key,
            @RequestBody ContentBlock contentBlock) {
        ContentBlock updatedBlock = contentBlockService.updateContentBlock(key, contentBlock);
        return ResponseEntity.ok(updatedBlock);
    }
}
