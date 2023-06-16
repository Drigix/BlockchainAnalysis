package com.HDiSED.BlockchainAnalysis.services.impl;

import com.HDiSED.BlockchainAnalysis.models.BitcoinAddressModel;
import com.HDiSED.BlockchainAnalysis.services.BitcoinAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BitcoinAddressServiceImpl implements BitcoinAddressService {

    @Override
    public BitcoinAddressModel create(BitcoinAddressModel bitcoinAddressModel) {
        return bitcoinAddressModel;
    }
}
