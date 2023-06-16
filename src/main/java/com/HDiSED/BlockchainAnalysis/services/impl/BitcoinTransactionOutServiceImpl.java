package com.HDiSED.BlockchainAnalysis.services.impl;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import com.HDiSED.BlockchainAnalysis.models.BitcoinTransactionOutModel;
import com.HDiSED.BlockchainAnalysis.repositories.BitcoinTransactionOutRepository;
import com.HDiSED.BlockchainAnalysis.services.BitcoinTransactionOutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BitcoinTransactionOutServiceImpl implements BitcoinTransactionOutService {

    private final BitcoinTransactionOutRepository bitcoinTransactionOutRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public  List<BitcoinTransactionOutModel> create(BitcoinTransaction bitcoinTransaction) {
        List<BitcoinTransactionOutModel> bitcoinTransactionOutList = new ArrayList<>();
        for (BitcoinTransactionOutModel bitcoinTransactionOutModel : bitcoinTransaction.getOut()) {
            Map<String, Object> outputMap = objectMapper.convertValue(bitcoinTransactionOutModel, Map.class);
            BitcoinTransactionOutModel saveOut = bitcoinTransactionOutRepository.createBitcoinTransactionOutWithRelationship(outputMap, bitcoinTransaction.getHash());
            bitcoinTransactionOutList.add(saveOut);
        }
        return bitcoinTransactionOutList;
    }
}
