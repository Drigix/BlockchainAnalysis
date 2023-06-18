package com.HDiSED.BlockchainAnalysis.controllers;

import com.HDiSED.BlockchainAnalysis.models.BitcoinAddressModel;
import com.HDiSED.BlockchainAnalysis.models.BitcoinBlock;
import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import com.HDiSED.BlockchainAnalysis.services.BitcoinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/bitcoin")
public class BitcoinController {

    private final BitcoinService bitcoinService;


    @GetMapping("/blocks")
    public BitcoinBlock getBlock() throws JsonProcessingException {
    return bitcoinService.findBlock();
    }

    @GetMapping(value = "/transaction/{hash}")
    public BitcoinTransaction getSingleTransaction(@PathVariable String hash) throws JsonProcessingException {
        return bitcoinService.findOneTransaction(hash);
    }
    @GetMapping(value = "/address/{address}")
    public BitcoinAddressModel getSingleAddress(@PathVariable String address) throws JsonProcessingException {
        return bitcoinService.findOneAddress(address);
    }
}
