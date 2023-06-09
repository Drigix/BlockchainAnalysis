package com.HDiSED.BlockchainAnalysis.services;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UrlConnectionService {

    public String createConnection(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String jsonResponse = response.getBody();
        return jsonResponse;
    }

}
