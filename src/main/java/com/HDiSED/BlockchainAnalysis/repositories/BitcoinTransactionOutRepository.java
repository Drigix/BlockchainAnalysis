package com.HDiSED.BlockchainAnalysis.repositories;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransactionOutModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Map;

public interface BitcoinTransactionOutRepository extends Neo4jRepository<BitcoinTransactionOutModel, Long> {
    @Query("MATCH (bt:BitcoinTransaction {hash: $btHash}) "
            + "CREATE (out:BitcoinTransactionOut $out) "
            + "CREATE (bt)-[:transaction_out]->(out) "
            + "RETURN out")
    BitcoinTransactionOutModel createBitcoinTransactionOutWithRelationship(Map<String, Object> out, String btHash);
}

