package com.HDiSED.BlockchainAnalysis.repositories;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransactionInputModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Map;

public interface BitcoinTransactionInputRepository extends Neo4jRepository<BitcoinTransactionInputModel, Long> {


    @Query("MATCH (bt:BitcoinTransaction {hash: $btHash}) "
            + "CREATE (input:BitcoinTransactionInput $input) "
            + "CREATE (input)-[:transaction_input]->(bt) "
            + "RETURN input")
    BitcoinTransactionInputModel createBitcoinTransactionInputWithRelationship(Map<String, Object> input, String btHash);
}
