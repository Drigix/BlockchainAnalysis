package com.HDiSED.BlockchainAnalysis.services.impl;

import com.HDiSED.BlockchainAnalysis.models.BitcoinSingleAddress;
import com.HDiSED.BlockchainAnalysis.repositories.BitcoinSingleAddressRepository;
import com.HDiSED.BlockchainAnalysis.services.BitcoinSingleAddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BitcoinSingleAddressServiceImpl implements BitcoinSingleAddressService {
    private final BitcoinSingleAddressRepository bitcoinSingleAddressRepository;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public BitcoinSingleAddress create(BitcoinSingleAddress bitcoinSingleAddress) {
        Map<String, Object> bitcoinSingleAddressMap = objectMapper.convertValue(bitcoinSingleAddress, Map.class);
        return bitcoinSingleAddressRepository.createBitcoinSingleAddress(bitcoinSingleAddressMap);
    }
}
