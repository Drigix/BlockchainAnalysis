package com.HDiSED.BlockchainAnalysis.repositories;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitcoinTransactionRepository extends Neo4jRepository<BitcoinTransaction, Long> {
    BitcoinTransaction findByHash(String hash);
}
