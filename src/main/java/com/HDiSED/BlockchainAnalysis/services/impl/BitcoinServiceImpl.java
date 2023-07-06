package com.HDiSED.BlockchainAnalysis.services.impl;

import com.HDiSED.BlockchainAnalysis.models.*;
import com.HDiSED.BlockchainAnalysis.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    private final  BitcoinSingleAddressService bitcoinSingleAddressService;

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
        BitcoinAddressModel bitcoinAddress = objectMapper.readValue(response, BitcoinAddressModel.class);
        List<BitcoinTransaction> firstTenTransactions = bitcoinAddress.getTxs().subList(0, Math.min(bitcoinAddress.getTxs().size(), 10));
        this.saveAddress(bitcoinAddress);
        for(BitcoinTransaction bitcoinTransaction: firstTenTransactions) {
            saveTransactionToAddress(bitcoinTransaction, bitcoinAddress.getAddress());
        }
        return bitcoinAddress;
    }

    @Override
    public BitcoinMultiAddress findManyAddresses() throws JsonProcessingException {
        String response = urlConnectionService.createConnection(bitcoinAddressesConectionURL);
        ObjectMapper objectMapper = new ObjectMapper();
        BitcoinMultiAddress bitcoinMultiAddress = objectMapper.readValue(response, BitcoinMultiAddress.class);

    //TODO NEED GOOD ADDRESSES TO SAVE TRANSACTION
        List<BitcoinSingleAddress> addresses = bitcoinMultiAddress.getAddresses();
        List<BitcoinTransaction> transactions = bitcoinMultiAddress.getTxs();
        List<String> addressesInDatabase = new ArrayList<>();

        for (BitcoinSingleAddress singleAddress : addresses) {
            addressesInDatabase.add(singleAddress.getAddress());
            saveBitcoinSingleAddress(singleAddress);

        }
        List<BitcoinTransaction> filteredTransactions = transactions.stream()
                .filter(transaction ->transaction
                        .getOut()
                        .stream()
                        .anyMatch(outAddress -> addressesInDatabase.contains(outAddress.getAddr())))
                .filter(transaction -> transaction
                        .getInputs()
                        .stream()
                        .anyMatch(inputAddress -> addressesInDatabase.contains(inputAddress.getPrev_out().getAddr()))
                ).collect(Collectors.toList());

        for(BitcoinTransaction transaction : filteredTransactions){
            saveTransactionToInputAndOutputAddresses(transaction, transaction.getInputs().get(0).getPrev_out().getAddr(),transaction.getOut().get(0).getAddr());
        }
        return bitcoinMultiAddress;
    }

    private void saveBitcoinSingleAddress(BitcoinSingleAddress singleAddress) {
        bitcoinSingleAddressService.create(singleAddress);
    }

    public void saveTransaction(BitcoinTransaction bitcoinTransaction) {
        bitcoinTransactionService.create(bitcoinTransaction);
       // bitcoinTransactionOutService.create(bitcoinTransaction);
        //bitcoinTransactionInputService.create(bitcoinTransaction);
    }

    public void saveTransactionToAddress(BitcoinTransaction bitcoinTransaction, String address) {
        BitcoinTransaction temp = bitcoinTransactionService.createToAddress(bitcoinTransaction, address);
       // bitcoinTransactionService.createToAddress(bitcoinTransaction, address);
//        bitcoinTransactionOutService.create(bitcoinTransaction);
//        bitcoinTransactionInputService.create(bitcoinTransaction);
    }

    public void saveTransactionToInputAndOutputAddresses(BitcoinTransaction bitcoinTransaction, String addressIn, String addressOut){
        bitcoinTransactionService.createToInputAndOutputAddresses(bitcoinTransaction, addressIn, addressOut);
    }

    public void saveAddress(BitcoinAddressModel bitcoinAddressModel) {
        bitcoinAddressService.create(bitcoinAddressModel);
    }
}
