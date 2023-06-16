package com.HDiSED.BlockchainAnalysis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Node("BitcoinTransactionInput")
public class BitcoinTransactionInputModel {

    @Id
    @GeneratedValue
    private Long id;

    private Integer index;

    private Long sequence;

    private String witness;

    //private Object prev_out;

    private String script;

}
