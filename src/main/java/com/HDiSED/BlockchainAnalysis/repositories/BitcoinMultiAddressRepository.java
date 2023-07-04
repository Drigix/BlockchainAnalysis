package com.HDiSED.BlockchainAnalysis.repositories;

import com.HDiSED.BlockchainAnalysis.models.BitcoinMultiAddress;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BitcoinMultiAddressRepository {

    @Query("CREATE (bma:BitcoinMultiAddress $bma) RETURN bma")
    BitcoinMultiAddress createBitcoinMultiAddress(Map<String, Object> bma);

}
