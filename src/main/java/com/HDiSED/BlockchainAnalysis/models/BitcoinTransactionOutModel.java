package com.HDiSED.BlockchainAnalysis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Node("BitcoinTransactionOut")
public class BitcoinTransactionOutModel {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    private Integer n;

    private String hash;

    private String script;

}
