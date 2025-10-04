package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.models.Requirement;
import me.soyaldo.requirementapi.interfaces.RequirementExpansion;
import me.soyaldo.requirementapi.requirements.HasExp;
import me.soyaldo.requirementapi.util.RequirementUtil;

import java.util.Map;

public class HasExpExpansion implements RequirementExpansion {

    @Override
    public String getType() {
        return "has exp";
    }

    @Override
    public Requirement generateRequirement(String name, Map<String, Object> format) {
        // Verify if the format no contain all fields
        if (!RequirementUtil.containFields(format, "type", "amount", "level")) return null;
        // Getting the format data
        String type = (String) format.get("type");
        String amount = (String) format.get("amount");
        boolean level = (Boolean) format.get("level");
        // Returning a new requirement
        return new HasExp(name, amount, level, !type.startsWith("!"));
    }

}