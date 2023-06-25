package com.HDiSED.BlockchainAnalysis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Node("BitcoinMultiAddress")
public class BitcoinMultiAddress {

    @Id
    @GeneratedValue
    private Long id;

    private List<BitcoinSingleAddress> addresses;

    private List<BitcoinTransaction> txs;
}
