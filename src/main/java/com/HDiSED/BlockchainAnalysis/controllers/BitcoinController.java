package com.HDiSED.BlockchainAnalysis.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bitcoin")
public class BitcoinController {

    @GetMapping
    public String getBitcoinData() throws IOException {
        String data = null;
        StringBuilder responseData = new StringBuilder();
        JsonObject jsonObject = null;
        URL url = null;

        // REST API call that got used along with the search string
        url = new URL("https://api.coincap.io/v2/assets/bitcoin");
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
            jsonObject = new Gson().fromJson(responseData.toString(), JsonObject.class);
            data = jsonObject.get("data").toString();
        }
        System.out.println(data);
        return data;
    }

}
