package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.Requirement;
import me.soyaldo.requirementapi.RequirementExpansion;
import me.soyaldo.requirementapi.requirements.StringEqualsRequirement;
import me.soyaldo.requirementapi.util.RequirementUtil;

import java.util.Map;

public class StringEqualsExpansion extends RequirementExpansion {

    public StringEqualsExpansion() {
        super("string equals");
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
        return new StringEqualsRequirement(name, input, output, !type.startsWith("!"));
    }

}