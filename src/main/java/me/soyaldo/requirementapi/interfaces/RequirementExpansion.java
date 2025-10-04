package me.soyaldo.requirementapi.interfaces;

import me.soyaldo.requirementapi.models.Requirement;

import java.util.Map;

public interface RequirementExpansion {

    /**
     * Get the requirement type
     *
     * @return The requirement type
     */
    String getType();

    Requirement generateRequirement(String name, Map<String, Object> format);
}