package com.HDiSED.BlockchainAnalysis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;


@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NodeEntity
public class BitcoinModel {

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

    @Relationship(type="INPUT", direction = Relationship.INCOMING)
    private List<BitcoinInputsModel> inputs;

    @Relationship(type="OUTPUT")
    private List<BitcoinOutModel> out;
}
