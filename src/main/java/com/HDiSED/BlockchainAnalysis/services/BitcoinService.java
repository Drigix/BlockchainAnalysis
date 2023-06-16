package com.HDiSED.BlockchainAnalysis.services;

import com.HDiSED.BlockchainAnalysis.models.BitcoinAddressModel;
import com.HDiSED.BlockchainAnalysis.models.BitcoinBlock;
import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface BitcoinService {

    BitcoinBlock findBlock() throws JsonProcessingException;
    BitcoinTransaction findOneTransaction(String url) throws JsonProcessingException;
    BitcoinAddressModel findOneAddress() throws JsonProcessingException;
}
