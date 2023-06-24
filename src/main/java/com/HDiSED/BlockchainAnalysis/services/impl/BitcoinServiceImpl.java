package com.HDiSED.BlockchainAnalysis.services.impl;

import com.HDiSED.BlockchainAnalysis.models.*;
import com.HDiSED.BlockchainAnalysis.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    private final BitcoinAddressService bitcoinAddressService;

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
        String response = urlConnectionService.createConnection(bitcoinTransactionConnectionURL + url);
        BitcoinTransaction bitcoinTransaction = objectMapper.readValue(response, BitcoinTransaction.class);
        saveTransaction(bitcoinTransaction);
        return bitcoinTransaction;
    }

    @Override
    public BitcoinAddressModel findOneAddress(String url) throws JsonProcessingException {
        String response = urlConnectionService.createConnection(bitcoinAddressConnectionURL + url);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("dupa");
        BitcoinAddressModel bitcoinAddress = objectMapper.readValue(response, BitcoinAddressModel.class);
        List<BitcoinTransaction> firstTenTransactions = bitcoinAddress.getTxs().subList(0, Math.min(bitcoinAddress.getTxs().size(), 10));
        this.saveAddress(bitcoinAddress);

        for(BitcoinTransaction bitcoinTransaction: firstTenTransactions) {
            saveTransactionToAddress(bitcoinTransaction, bitcoinAddress.getAddress());
        }
        return bitcoinAddress;
    }

    @Override
    public List<BitcoinAddressModel> findManyAddresses() throws JsonProcessingException {
        String response = urlConnectionService.createConnection(bitcoinAddressesConectionURL);
        ObjectMapper objectMapper = new ObjectMapper();
        List<BitcoinAddressModel> bitcoinAddressModelList = objectMapper.readValue(response, new TypeReference<List<BitcoinAddressModel>>() {});
        return bitcoinAddressModelList;
    }
    public void saveTransaction(BitcoinTransaction bitcoinTransaction) {
        bitcoinTransactionService.create(bitcoinTransaction);
        bitcoinTransactionOutService.create(bitcoinTransaction);
        bitcoinTransactionInputService.create(bitcoinTransaction);
    }

    public void saveTransactionToAddress(BitcoinTransaction bitcoinTransaction, String address) {
        bitcoinTransactionService.createToAddress(bitcoinTransaction, address);
        bitcoinTransactionOutService.create(bitcoinTransaction);
        bitcoinTransactionInputService.create(bitcoinTransaction);
    }

    public void saveAddress(BitcoinAddressModel bitcoinAddressModel) {
        bitcoinAddressService.create(bitcoinAddressModel);
    }
}
