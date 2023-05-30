package com.HDiSED.BlockchainAnalysis.controllers;

import com.HDiSED.BlockchainAnalysis.models.BitcoinModel;
import com.HDiSED.BlockchainAnalysis.models.BitcoinOutModel;
import com.HDiSED.BlockchainAnalysis.services.UrlConnectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bitcoin")
public class BitcoinController {

    private final UrlConnectionService urlConnectionService;

    @GetMapping
    public String getBitcoinData() throws IOException {
        String data = null;
        StringBuilder responseData = new StringBuilder();
        JsonObject jsonObject = null;
        URL url = null;

        // REST API call that got used along with the search string
        //url = new URL("https://blockchain.info/rawblock/000000000000000000046c26f11387bc410d7c76264fccf87aa9feb0b5c3cbe3?fbclid=IwAR3mrUpTcEJRWvV6XpHD6fqnSuzrLBJ7be3f4I9FEb_3YBuj-V6Rgrh0K8c");
        url = new URL("https://blockchain.info/rawtx/b7051599a7ec4468a76c0415078f3a5ae66a1a3ade21a7f5d80fa5306f4b2634");
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
            ObjectMapper objectMapper = new ObjectMapper();
            data = "";
        }
        System.out.println(data);
        return data;
        //return urlConnectionService.bitcoinConnection();
    }

    @GetMapping(value = "/transaction")
    public BitcoinModel getSingleTransaction() throws JsonProcessingException {

        return urlConnectionService.bitcoinTransactionConnection();
    }


}
