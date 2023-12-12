package com.cabonline.homework.api;

import com.cabonline.homework.service.UrlService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/api/shorten")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request) {
        var shortenedUrl = urlService.shortenUrl(request.getUrl());
        return ResponseEntity.ok(new ShortenUrlResponse()
                .setShortenedUrl(shortenedUrl));
    }

    @GetMapping("/{key}")
    public RedirectView getResource(@PathVariable("key") String key) {
        return urlService.returnResource(key);
    }

    @Data
    public static class ShortenUrlRequest {
        private String url;
    }

    @Data
    @Accessors(chain = true)
    public static class ShortenUrlResponse {
        private String shortenedUrl;
    }

}
