package com.HDiSED.BlockchainAnalysis.repositories;

import com.HDiSED.BlockchainAnalysis.models.BitcoinTransaction;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Map;

@Repository
public interface BitcoinTransactionRepository extends Neo4jRepository<BitcoinTransaction, Long> {
    @Query("MATCH (tx:BitcoinTransaction) WHERE tx.hash = $txHash RETURN tx")
    BitcoinTransaction findByHash(String txHash);

    @Query("CREATE (bt:BitcoinTransaction $bt) RETURN bt")
    BitcoinTransaction createBitcionTransaction(Map<String, Object> bt);
}
