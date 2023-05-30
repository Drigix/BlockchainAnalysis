package com.HDiSED.BlockchainAnalysis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NodeEntity
public class BitcoinOutModel {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    private Integer n;

    private String hash;

    private String script;

    @Relationship(type = "SPENDS")
    private BitcoinModel bitcoinModel;
}
