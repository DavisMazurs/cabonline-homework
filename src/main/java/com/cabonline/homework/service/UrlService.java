package com.cabonline.homework.service;

import org.springframework.web.servlet.view.RedirectView;

public interface UrlService {
    String shortenUrl(String url);

    RedirectView returnResource(String key);
}
