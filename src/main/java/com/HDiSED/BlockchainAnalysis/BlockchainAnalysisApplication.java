package com.HDiSED.BlockchainAnalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
@EntityScan
public class BlockchainAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockchainAnalysisApplication.class, args);
	}

}
