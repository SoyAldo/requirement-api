package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.Requirement;
import me.soyaldo.requirementapi.RequirementExpansion;
import me.soyaldo.requirementapi.requirements.HasMoneyRequirement;
import me.soyaldo.requirementapi.util.RequirementUtil;

import java.util.Map;

public class HasMoneyExpansion extends RequirementExpansion {

    public HasMoneyExpansion() {
        super("has money");
    }

    @Override
    public Requirement generateRequirement(String name, Map<String, Object> format) {
        // Verify if the format no contain all fields
        if (!RequirementUtil.containFields(format, "type", "amount")) return null;
        // Getting the format data
        String type = (String) format.get("type");
        String amount = (String) format.get("amount");
        // Returning a new requirement
        return new HasMoneyRequirement(name, amount, !type.startsWith("!"));
    }

}