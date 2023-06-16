package com.HDiSED.BlockchainAnalysis.services.impl;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import com.HDiSED.BlockchainAnalysis.repositories.BitcoinTransactionRepository;
import com.HDiSED.BlockchainAnalysis.services.BitcoinTransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class BitcoinTransactionServiceImpl implements BitcoinTransactionService {

    private final BitcoinTransactionRepository bitcoinTransactionRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public BitcoinTransaction create(BitcoinTransaction bitcoinTransaction) {
        Map<String, Object> bitcoinTransactionMap = objectMapper.convertValue(bitcoinTransaction, Map.class);
        bitcoinTransactionMap.remove("inputs");
        bitcoinTransactionMap.remove("out");
        return bitcoinTransactionRepository.createBitcionTransaction(bitcoinTransactionMap);
    }
}
