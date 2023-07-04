package com.HDiSED.BlockchainAnalysis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoinTransactionInputPrevOut {

    private String addr;

    private Integer n;

    private String script;
}
