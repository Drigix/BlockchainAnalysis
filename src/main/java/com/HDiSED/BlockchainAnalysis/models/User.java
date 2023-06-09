package com.HDiSED.BlockchainAnalysis.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node("User")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "FRIENDS_WITH", direction = Relationship.Direction.OUTGOING)
    private List<User> friends;

    // Konstruktory, gettery i settery

    public void addFriend(User friend) {
        if (friends == null) {
            friends = new ArrayList<>();
        }
        friends.add(friend);
    }
}