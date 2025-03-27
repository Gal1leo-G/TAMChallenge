package com.challenge.ms.animal.service;

import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

@Service
public class RESTService {
    public BufferedImage getPhoto(String baseUrl) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setMessageConverters(Arrays.asList(new BufferedImageHttpMessageConverter()));
        BufferedImage result = restTemplate.getForObject(baseUrl, BufferedImage.class);
        return result;
    }
}

