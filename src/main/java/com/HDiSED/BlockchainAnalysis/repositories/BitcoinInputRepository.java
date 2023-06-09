package com.HDiSED.BlockchainAnalysis.repositories;

import com.HDiSED.BlockchainAnalysis.models.BitcoinInputsModel;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BitcoinInputRepository extends Neo4jRepository<BitcoinInputsModel, Long> {
}
