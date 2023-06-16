package com.HDiSED.BlockchainAnalysis.services;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import com.HDiSED.BlockchainAnalysis.models.BitcoinTransactionOutModel;

import java.util.List;

public interface BitcoinTransactionOutService {

    List<BitcoinTransactionOutModel> create(BitcoinTransaction bitcoinTransaction);
}
