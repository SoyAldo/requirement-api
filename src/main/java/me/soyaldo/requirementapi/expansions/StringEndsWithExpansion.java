package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.Requirement;
import me.soyaldo.requirementapi.RequirementExpansion;
import me.soyaldo.requirementapi.requirements.StringEndsWithRequirement;
import me.soyaldo.requirementapi.util.RequirementUtil;

import java.util.Map;

public class StringEndsWithExpansion extends RequirementExpansion {

    public StringEndsWithExpansion() {
        super("string ends with");
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
        return new StringEndsWithRequirement(name, input, output, !type.startsWith("!"));
    }

}