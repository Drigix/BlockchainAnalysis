package com.HDiSED.BlockchainAnalysis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Node("BitcoinMultiAddress")
public class BitcoinMultiAddress {
    private List<BitcoinSingleAddress> addresses;
    private List<BitcoinTransaction> txs;
}
