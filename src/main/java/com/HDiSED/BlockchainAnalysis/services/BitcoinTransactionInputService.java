package com.HDiSED.BlockchainAnalysis.services;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import com.HDiSED.BlockchainAnalysis.models.BitcoinTransactionInputModel;

import java.util.List;

public interface BitcoinTransactionInputService {

    List<BitcoinTransactionInputModel> create(BitcoinTransaction bitcoinTransaction);
}
