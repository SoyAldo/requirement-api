package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.models.Requirement;
import me.soyaldo.requirementapi.interfaces.RequirementExpansion;
import me.soyaldo.requirementapi.requirements.RegexMatchesRequirement;
import me.soyaldo.requirementapi.util.RequirementUtil;

import java.util.Map;

public class RegexMatchesExpansion implements RequirementExpansion {

    @Override
    public String getType() {
        return "regex matches";
    }

    @Override
    public Requirement generateRequirement(String name, Map<String, Object> format) {
        // Verify if the format no contain all fields
        if (!RequirementUtil.containFields(format, "type", "input", "regex")) return null;
        // Getting the format data
        String type = (String) format.get("type");
        String input = (String) format.get("input");
        String regex = (String) format.get("regex");
        // Returning a new requirement
        return new RegexMatchesRequirement(name, input, regex, !type.startsWith("!"));
    }

}