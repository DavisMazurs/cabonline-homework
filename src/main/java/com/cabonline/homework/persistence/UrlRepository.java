package com.cabonline.homework.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    boolean existsByShortUrlKey(String shortUrlKey);
    Optional<UrlEntity> findByShortUrlKey(String shortUrlKey);
}
