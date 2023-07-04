package com.HDiSED.BlockchainAnalysis.repositories;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Map;

@Repository
public interface BitcoinTransactionRepository extends Neo4jRepository<BitcoinTransaction, Long> {

    @Query("CREATE (bt:BitcoinTransaction $bt) RETURN bt")
    BitcoinTransaction createBitcionTransaction(Map<String, Object> bt);

    @Query("MATCH (ba:BitcoinAddress {address: $address}) "
            + "CREATE (bt:BitcoinTransaction $bt) "
            + "CREATE (ba)-[:address_out]->(bt) "
            + "RETURN bt")
    BitcoinTransaction createBitcionTransactionToAddress(Map<String, Object> bt, String address);

    @Query("MATCH (ba:BitcoinSingleAddress {address: $address}) "
            + "CREATE (bt:BitcoinTransaction $bt) "
            + "CREATE (ba)-[:address_out]->(bt) "
            + "RETURN bt")
    BitcoinTransaction createBitcoinTransactionToSingleAddress(Map<String, Object> bt, String address);

    @Query("MATCH (baIn:BitcoinSingleAddress {address: $addressIn}) "
            + "MATCH (baOut:BitcoinSingleAddress {address: $addressOut}) "
            + "CREATE (bt:BitcoinTransaction $bt) "
            + "CREATE (baIn)-[:address_in]->(bt) "
            + "CREATE (baOut)-[:address_out]->(bt) "
            + "RETURN bt")
    BitcoinTransaction createBitcoinTransactionToInputAndOutputAddresses(Map<String, Object> bt,
                                                                         String addressIn,
                                                                         String addressOut);
}