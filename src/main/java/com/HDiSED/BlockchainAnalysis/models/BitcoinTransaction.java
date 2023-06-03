package com.HDiSED.BlockchainAnalysis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Node("bitcoinTransaction")
public class BitcoinTransaction {

    @Id
    @GeneratedValue
    private Long id;

    private String hash;

    private Integer ver;

    private Integer vin_sz;

    private Integer vout_sz;

    private String lock_time;

    private Double size;

    private Double weight;

    private Double fee;

    private String relayed_by;

    private Double block_height;

    private Double block_index;

    private Double time;

    private Boolean double_spend;

    private String tx_index;

    @Relationship(type="INPUT", direction = Relationship.Direction.INCOMING)
    private List<BitcoinInputsModel> inputs;
//
//    @Relationship(type="OUTPUT", direction = Relationship.Direction. )
//    private List<BitcoinOutModel> out;
}
