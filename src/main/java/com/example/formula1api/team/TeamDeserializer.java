package com.example.formula1api.team;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class TeamDeserializer extends JsonDeserializer<Team> {

    @Override
    public Team deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.getCurrentToken() == JsonToken.VALUE_STRING) {
            var teamName = p.getText();
            return new Team(teamName, teamName);
        }

        var node = (JsonNode) p.getCodec().readTree(p);
        var name = node.has("name") ? node.get("name").asText() : "";
        var fullName = node.has("fullName") ? node.get("fullName").asText() : name;

        return new Team(name, fullName);
    }
}
