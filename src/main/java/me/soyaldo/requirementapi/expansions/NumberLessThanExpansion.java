package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.interfaces.RequirementExpansion;
import me.soyaldo.requirementapi.models.Requirement;
import me.soyaldo.requirementapi.requirements.NumberLessThanRequirement;
import me.soyaldo.requirementapi.util.RequirementUtil;

import java.util.Map;

public class NumberLessThanExpansion implements RequirementExpansion {

    @Override
    public String getType() {
        return "number less than";
    }

    @Override
    public Requirement generateRequirement(String name, Map<String, Object> format) {
        // Verify if the format no contain all fields
        if (!RequirementUtil.containFields(format, "type", "input", "output")) return null;
        // Getting the format data
        String type = (String) format.get("type");
        String input = (String) format.get("input");
        String output = (String) format.get("output");
        // Returning a new requirement
        return new NumberLessThanRequirement(name, input, output, !type.startsWith("!"));
    }

}