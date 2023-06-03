package com.HDiSED.BlockchainAnalysis.repositories;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BitcoinTransactionRepository extends Neo4jRepository<BitcoinTransaction, Long> {
}
