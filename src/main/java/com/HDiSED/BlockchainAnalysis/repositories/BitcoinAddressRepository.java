package com.HDiSED.BlockchainAnalysis.repositories;

import com.HDiSED.BlockchainAnalysis.models.BitcoinAddressModel;
import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BitcoinAddressRepository extends Neo4jRepository<BitcoinAddressModel, Long> {
   @Query("CREATE (ba:BitcoinAddress $ba) RETURN ba")
   BitcoinAddressModel createBitcoinAddress(Map<String, Object> ba);

}
