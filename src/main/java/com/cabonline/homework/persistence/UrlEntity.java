package com.cabonline.homework.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(
        name = "short_url",
        uniqueConstraints = @UniqueConstraint(columnNames = {"short_url_Key"})
)
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortUrlKey;
    private String longUrl;
}
