package com.HDiSED.BlockchainAnalysis.services;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;

public interface BitcoinTransactionService {

    BitcoinTransaction create(BitcoinTransaction bitcoinTransaction);
}
