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

        // Zapisujemy BitcoinMultiAddress do bazy danych chyba usless
        //saveBitcoinMultiAddress(bitcoinMultiAddress);
    //TODO THIS SAVE
        List<BitcoinSingleAddress> addresses = bitcoinMultiAddress.getAddresses();
        List<BitcoinTransaction> transactions = bitcoinMultiAddress.getTxs();


        for (BitcoinSingleAddress singleAddress : addresses) {
            // Zapisujemy pojedynczy adres do bazy danych
            saveBitcoinSingleAddress(singleAddress);
//            for (BitcoinTransaction transaction : transactions) {
//                saveTransactionToAddress(transaction, singleAddress.getAddress());
////                for (BitcoinTransactionOutModel output : transaction.getOut()) {
////                    if (true) {
////                        // Zapisujemy transakcję powiązaną z adresem do bazy danych
////                        saveTransactionToAddress(transaction, singleAddress.getAddress());
////                        // Przerywamy pętlę wewnętrzną, bo adres pojedynczy już został znaleziony w transakcji
////                        break;
////                    }
////                }
//            }
        }

        //magia
        for (BitcoinTransaction transaction : transactions) {

            boolean hasInputAddress = false;

            boolean hasOutputAddress = false;

            List<BitcoinTransactionInputModel> inputs = transaction.getInputs();
            //its plural number
            List<BitcoinTransactionOutModel> outs = transaction.getOut();
            String inputAddress = "";
            String outAddress = "";
            for (BitcoinTransactionInputModel input : inputs) {
                 inputAddress = input.getPrev_out().getAddr();
                if (true) {
                    hasInputAddress = true;
                    break; // Możemy przerwać pętlę, bo już znaleźliśmy adres IN na liście
                }
            }

            // Sprawdzamy, czy adres OUT transakcji znajduje się na liście adresów
            for (BitcoinTransactionOutModel out : outs) {
                outAddress = out.getAddr();
                if (true) {
                    hasOutputAddress = true;
                    break; // Możemy przerwać pętlę, bo już znaleźliśmy adres OUT na liście
                }
            }

            // Jeśli zarówno adres IN, jak i adres OUT są na liście, zapisujemy transakcję
            if (hasInputAddress && hasOutputAddress) {
                saveTransactionToInputAndOutputAddresses(transaction, inputAddress, outAddress);
            }
        }
        // Iterujemy przez listę transakcji
        return bitcoinMultiAddress;
    }

    private void saveBitcoinSingleAddress(BitcoinSingleAddress singleAddress) {
        bitcoinSingleAddressService.create(singleAddress);
    }

    public void saveTransaction(BitcoinTransaction bitcoinTransaction) {
        bitcoinTransactionService.create(bitcoinTransaction);
        bitcoinTransactionOutService.create(bitcoinTransaction);
        bitcoinTransactionInputService.create(bitcoinTransaction);
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
