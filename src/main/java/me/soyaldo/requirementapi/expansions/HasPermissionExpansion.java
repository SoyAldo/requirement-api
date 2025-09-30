package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.Requirement;
import me.soyaldo.requirementapi.RequirementExpansion;
import me.soyaldo.requirementapi.requirements.HasPermissionRequirement;
import me.soyaldo.requirementapi.util.RequirementUtil;

import java.util.Map;

public class HasPermissionExpansion extends RequirementExpansion {

    public HasPermissionExpansion() {
        super("has permission");
    }

    @Override
    public Requirement generateRequirement(String name, Map<String, Object> format) {
        // Verify if the format no contain all fields
        if (!RequirementUtil.containFields(format, "type", "permission")) return null;
        // Getting the format data
        String type = (String) format.get("type");
        String permission = (String) format.get("permission");
        // Returning a new requirement
        return new HasPermissionRequirement(name, permission, !type.startsWith("!"));
    }

}