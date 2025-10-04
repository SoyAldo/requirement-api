package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.interfaces.RequirementExpansion;
import me.soyaldo.requirementapi.models.Requirement;
import me.soyaldo.requirementapi.requirements.IsNearRequirement;
import me.soyaldo.requirementapi.util.RequirementUtil;

import java.util.Map;

public class IsNearExpansion implements RequirementExpansion {

    @Override
    public String getType() {
        return "is near";
    }

    @Override
    public Requirement generateRequirement(String name, Map<String, Object> format) {
        // Verify if the format no contain all fields
        if (!RequirementUtil.containFields(format, "type", "location", "distance")) return null;
        // Getting the format data
        String type = (String) format.get("type");
        String location = (String) format.get("location");
        String distance = (String) format.get("distance");
        // Returning a new requirement
        return new IsNearRequirement(name, location, distance, !type.startsWith("!"));
    }

}