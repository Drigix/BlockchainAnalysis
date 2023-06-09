package com.HDiSED.BlockchainAnalysis.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitcoinBlock {
    
    private String hash;

    private Long ver;

    private String prev_block;

    private String mrkl_root;

    private Long time;

    private Long bits;

    private Long fee;

    private Long nonce;

    private List<BitcoinTransaction> tx;
}
