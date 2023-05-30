package com.HDiSED.BlockchainAnalysis.repositories;

import com.HDiSED.BlockchainAnalysis.models.BitcoinModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitcoinRepository extends Neo4jRepository<BitcoinModel, Long> {
}
