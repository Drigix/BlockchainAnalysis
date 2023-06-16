package com.HDiSED.BlockchainAnalysis.services.impl;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import com.HDiSED.BlockchainAnalysis.models.BitcoinTransactionInputModel;
import com.HDiSED.BlockchainAnalysis.repositories.BitcoinTransactionInputRepository;
import com.HDiSED.BlockchainAnalysis.services.BitcoinTransactionInputService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BitcoinTransactionInputServiceImpl implements BitcoinTransactionInputService {

    private final BitcoinTransactionInputRepository bitcoinTransactionInputRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<BitcoinTransactionInputModel> create(BitcoinTransaction bitcoinTransaction) {
        List<BitcoinTransactionInputModel> bitcoinTransactionInputList = new ArrayList<>();
        for (BitcoinTransactionInputModel bitcoinTransactionInputModel : bitcoinTransaction.getInputs()) {
            Map<String, Object> inputMap = objectMapper.convertValue(bitcoinTransactionInputModel, Map.class);
            BitcoinTransactionInputModel saveInput = bitcoinTransactionInputRepository.createBitcoinTransactionInputWithRelationship(inputMap, bitcoinTransaction.getHash());
            bitcoinTransactionInputList.add(saveInput);
        }
        return bitcoinTransactionInputList;
    }
}
