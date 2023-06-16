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
@Node("BitcoinAddress")
public class BitcoinAddressModel {

    @Id
    @GeneratedValue
    private Long id;

    private String hash;

    private String address;

    private Long n_tx;

    private Long n_unredeemed;

    private Double total_received;

    private Double total_sent;

    private Double final_balance;

    private List<BitcoinTransaction> txs;
}
