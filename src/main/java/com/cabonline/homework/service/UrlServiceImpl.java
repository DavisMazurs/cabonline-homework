package com.cabonline.homework.service;

import com.cabonline.homework.exception.RestErrorException;
import com.cabonline.homework.persistence.UrlEntity;
import com.cabonline.homework.util.ShorteningUtil;
import com.cabonline.homework.persistence.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    @Value("${app-settings.domain}")
    private String domain;

    @Override
    @Transactional
    public String shortenUrl(String url) {
        var shortenedUrlKey = ShorteningUtil.generateShortUrlKey(urlRepository.count());

        var shortenedUrlEntity = new UrlEntity()
                .setLongUrl(url)
                .setShortUrlKey(shortenedUrlKey);

        if (urlRepository.existsByShortUrlKey(shortenedUrlKey)) throw new RestErrorException("URL already exists", 409);

        urlRepository.save(shortenedUrlEntity);

        return String.format("%s/%s", domain, shortenedUrlKey);
    }

    @Override
    public RedirectView returnResource(String key) {
        var optionalUrl = urlRepository.findByShortUrlKey(key);
        if (optionalUrl.isEmpty()) throw new RestErrorException("URL not found", 404);
        return new RedirectView(optionalUrl.get().getLongUrl());
    }
}
