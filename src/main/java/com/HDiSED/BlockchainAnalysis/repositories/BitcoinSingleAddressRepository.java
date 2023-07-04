package com.HDiSED.BlockchainAnalysis.repositories;

import com.HDiSED.BlockchainAnalysis.models.BitcoinAddressModel;
import com.HDiSED.BlockchainAnalysis.models.BitcoinSingleAddress;
import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BitcoinSingleAddressRepository extends Neo4jRepository<BitcoinSingleAddress, Long> {

    @Query("CREATE (bsa:BitcoinSingleAddress $bsa) RETURN bsa")
    BitcoinSingleAddress createBitcoinSingleAddress(Map<String, Object> bsa);

    @Query("MATCH (ba:BitcoinAddress {address: $address}) "
            + "RETURN ba")
    BitcoinSingleAddress findByAddress(String address);

}
