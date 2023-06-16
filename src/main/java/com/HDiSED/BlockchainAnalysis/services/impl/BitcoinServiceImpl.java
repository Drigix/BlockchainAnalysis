package com.HDiSED.BlockchainAnalysis.services.impl;

import com.HDiSED.BlockchainAnalysis.models.*;
import com.HDiSED.BlockchainAnalysis.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import static com.HDiSED.BlockchainAnalysis.constans.UrlConstans.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BitcoinServiceImpl implements BitcoinService {

    private final UrlConnectionService urlConnectionService;

    private final BitcoinTransactionService bitcoinTransactionService;

    private final BitcoinTransactionInputService bitcoinTransactionInputService;

    private final BitcoinTransactionOutService bitcoinTransactionOutService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public BitcoinBlock findBlock() throws JsonProcessingException {
        String response = urlConnectionService.createConnection(bitcoinBlockConnectionURL);
        ObjectMapper objectMapper = new ObjectMapper();
        BitcoinBlock bitcoinBlock = objectMapper.readValue(response, BitcoinBlock.class);
        return bitcoinBlock;
    }

    @Override
    public BitcoinTransaction findOneTransaction(String url) throws JsonProcessingException {
        String response = urlConnectionService.createConnection(bitcoinTransactionConnectionURL+url);
        BitcoinTransaction bitcoinTransaction = objectMapper.readValue(response, BitcoinTransaction.class);
        saveTransaction(bitcoinTransaction);
        return bitcoinTransaction;
    }

    @Override
    public BitcoinAddressModel findOneAddress() throws JsonProcessingException {
        String response = urlConnectionService.createConnection(bitcoinAddressConnectionURL);
        ObjectMapper objectMapper = new ObjectMapper();
        BitcoinAddressModel bitcoinAddress = objectMapper.readValue(response, BitcoinAddressModel.class);
        List<BitcoinTransaction> firstTenTransactions = bitcoinAddress.getTxs().subList(0, Math.min(bitcoinAddress.getTxs().size(), 50));
        for(BitcoinTransaction bitcoinTransaction: bitcoinAddress.getTxs()) {
            saveTransaction(bitcoinTransaction);
        }
        //bitcoinTransactionRepository.save(bitcoinTransaction);
        return bitcoinAddress;
    }
    public void saveTransaction(BitcoinTransaction bitcoinTransaction) {
        bitcoinTransactionService.create(bitcoinTransaction);
        bitcoinTransactionOutService.create(bitcoinTransaction);
        bitcoinTransactionInputService.create(bitcoinTransaction);

    }
}
