package com.HDiSED.BlockchainAnalysis.services.impl;

import com.HDiSED.BlockchainAnalysis.models.BitcoinAddressModel;
import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import com.HDiSED.BlockchainAnalysis.repositories.BitcoinAddressRepository;
import com.HDiSED.BlockchainAnalysis.repositories.BitcoinTransactionRepository;
import com.HDiSED.BlockchainAnalysis.services.BitcoinAddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BitcoinAddressServiceImpl implements BitcoinAddressService {
    private final BitcoinAddressRepository bitcoinAddressRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    //TODO implementation
    @Override
    public BitcoinAddressModel create(BitcoinAddressModel bitcoinAddressModel) {
        Map<String, Object> bitcoinAddressModelMap = objectMapper.convertValue(bitcoinAddressModel, Map.class);
        return bitcoinAddressRepository.createBitcoinAddress(bitcoinAddressModelMap);
    }
}
