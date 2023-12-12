package com.cabonline.homework.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class ShorteningUtil {
    public static String generateShortUrlKey(Long index) {
        var id = String.valueOf(index);
        return Base64.encodeBase64URLSafeString(id.getBytes(StandardCharsets.UTF_8));
    }
}
