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
public class BitcoinInputsModel {

    @Id
    @GeneratedValue
    private Long id;

    private Integer index;

    private Integer sequence;

    private String witness;

    private Object prev_out;

    private String script;

    @Relationship(type = "REFERENCES")
    private BitcoinModel bitcoinModel;
}
