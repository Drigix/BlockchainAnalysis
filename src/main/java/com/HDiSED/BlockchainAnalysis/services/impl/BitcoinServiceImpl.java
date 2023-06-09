package com.HDiSED.BlockchainAnalysis.services.impl;

import com.HDiSED.BlockchainAnalysis.models.*;
import com.HDiSED.BlockchainAnalysis.repositories.BitcoinInputRepository;
import com.HDiSED.BlockchainAnalysis.repositories.BitcoinTransactionRepository;
import com.HDiSED.BlockchainAnalysis.repositories.UserRepository;
import com.HDiSED.BlockchainAnalysis.services.BitcoinService;
import com.HDiSED.BlockchainAnalysis.services.UrlConnectionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.neo4j.driver.Record;

import java.util.*;

import static com.HDiSED.BlockchainAnalysis.constans.UrlConstans.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class BitcoinServiceImpl implements BitcoinService {

    private final UrlConnectionService urlConnectionService;

    private final BitcoinInputRepository bitcoinInputRepository;

    private final BitcoinTransactionRepository bitcoinTransactionRepository;

    private final Driver neo4jDriver;

    private final UserRepository userRepository;

    private final Neo4jTemplate neo4jTemplate;

    @Override
    public BitcoinBlock findBlock() throws JsonProcessingException {
        String response = urlConnectionService.createConnection(bitcoinBlockConnectionURL);
        ObjectMapper objectMapper = new ObjectMapper();
        BitcoinBlock bitcoinBlock = objectMapper.readValue(response, BitcoinBlock.class);
        return bitcoinBlock;
    }

    @Override
    public BitcoinTransaction findOneTransaction() throws JsonProcessingException {
        String response = urlConnectionService.createConnection(bitcoinTransactionConnectionURL);
        ObjectMapper objectMapper = new ObjectMapper();
        BitcoinTransaction bitcoinTransaction = objectMapper.readValue(response, BitcoinTransaction.class);
        saveTransaction(bitcoinTransaction);
        //bitcoinTransactionRepository.save(bitcoinTransaction);
        return bitcoinTransaction;
    }

    @Override
    public BitcoinAddress findOneAddress() throws JsonProcessingException {
        String response = urlConnectionService.createConnection(bitcoinAddressConnectionURL);
        ObjectMapper objectMapper = new ObjectMapper();
        BitcoinAddress bitcoinAddress = objectMapper.readValue(response, BitcoinAddress.class);
        //bitcoinTransactionRepository.save(bitcoinTransaction);
        return bitcoinAddress;
    }
    public void saveTransaction(BitcoinTransaction bitcoinTransaction) {
//        String txQuery = "CREATE (tx:BitcoinTransaction {id: $txId, hash: $txHash})";
//        try (var session = neo4jDriver.session()) {
//            session.run(txQuery, Values.parameters("txId", bitcoinTransaction.getId(), "txHash", bitcoinTransaction.getHash()));
//        }
        //BitcoinTransaction temp = bitcoinTransactionRepository.findByHash("b7051599a7ec4468a76c0415078f3a5ae66a1a3ade21a7f5d80fa5306f4b2634");
        String txQuery2 = "MATCH (s:BitcoinTransaction) WHERE s.hash = $txHash RETURN s";
        try (var session = neo4jDriver.session()) {
            Record record = session.run(txQuery2, Values.parameters("txHash", "b7051599a7ec4468a76c0415078f3a5ae66a1a3ade21a7f5d80fa5306f4b2634")).single();
            Value value = record.get("s");
            Long bitcoinTransactionId = value.get("id").asLong();
            log.debug(bitcoinTransactionId.toString());
//            Node txNode = (Node) record.get("s").asNode();
        }
//        for (BitcoinInputsModel bitcoinInputsModel : bitcoinTransaction.getInputs()) {
//            String inputQuery = "MATCH (tx:BitcoinTransaction {id: $txId}) " +
//                    "CREATE (input:BitcoinInputsModel $inputProps) " +
//                    "CREATE (tx)-[:transaction_input]->(input)";
//            try (var session = neo4jDriver.session()) {
//                ObjectMapper objectMapper = new ObjectMapper();
//                Map<String, Object> inputProps = objectMapper.convertValue(bitcoinInputsModel, Map.class);
//                session.run(inputQuery,
//                        Values.parameters(
//                                "txId", bitcoinTransaction.getId(),
//                                "inputProps", Values.value(inputProps)
//                        ));
//            }
//        }
//        for(BitcoinInputsModel bitcoinInputsModel: bitcoinTransaction.getInputs()) {
//            String query = "CREATE (tx:BitcoinTransaction {id: $txId}) CREATE (input:BitcoinInputsModel $inputProps) " +
//                    "CREATE (tx)-[:transaction_input]->(input)";
//            try (var session = neo4jDriver.session()) {
//                ObjectMapper objectMapper = new ObjectMapper();
//                Map<String,Object> inputProps = objectMapper.convertValue(bitcoinInputsModel, Map.class);
//            session.run(query,
//                    Values.parameters(
//                            "txId", bitcoinTransaction.getId(),
//                            "inputProps", Values.value(inputProps)
//                    ));
//            }
//        }
    }
}
