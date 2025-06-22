package com.landing.tattoo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "content_blocks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String key;

    @Column(columnDefinition = "TEXT")
    private String contentUa;

    @Column(columnDefinition = "TEXT")
    private String contentEn;
}
