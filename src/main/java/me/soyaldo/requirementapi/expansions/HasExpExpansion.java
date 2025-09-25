package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.Requirement;
import me.soyaldo.requirementapi.RequirementExpansion;
import me.soyaldo.requirementapi.requirements.HasExp;

import java.util.Map;

public class HasExpExpansion extends RequirementExpansion {

    public HasExpExpansion() {
        super("has exp");
    }

    @Override
    public Requirement generateRequirement(String name, Map<String, Object> format) {
        Requirement requirement = null;
        String type = (String) format.get("type");
        if (format.containsKey("amount") && format.containsKey("level")) {
            String amount = (String) format.get("amount");
            boolean level = (Boolean) format.get("level");
            requirement = new HasExp(name, amount, level, !type.startsWith("!"));
        }
        return requirement;
    }

}