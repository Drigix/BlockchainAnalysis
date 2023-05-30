package com.HDiSED.BlockchainAnalysis.services;

import com.HDiSED.BlockchainAnalysis.models.BitcoinModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static com.HDiSED.BlockchainAnalysis.constans.UrlConstans.*;

@Service
public class UrlConnectionService {

    private String data;

    private StringBuilder responseData;

    private JsonObject jsonObject;

    private URL url;

    public UrlConnectionService() {
        data = null;
        responseData = new StringBuilder();
        jsonObject = null;
        url = null;
    }

    public String bitcoinConnection() throws IOException {

        url = new URL(bitcoinBlockConnectionURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                responseData.append(line);
            }
            Gson gson = new GsonBuilder().setLenient().create();
            jsonObject = gson.fromJson(responseData.toString(), JsonObject.class);
            data = jsonObject.get("data").toString();
        }
        return data;
    }

    public BitcoinModel bitcoinTransactionConnection() throws JsonProcessingException {
        String url = bitcoinTransactionConnectionURL;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String jsonResponse = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();

        BitcoinModel bitcoinModel = objectMapper.readValue(jsonResponse, BitcoinModel.class);

        return bitcoinModel;
    }

}
