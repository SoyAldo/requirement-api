package com.soyaldo.requirementapi;

import java.util.Map;

public abstract class RequirementExpansion {

    private final String type;

    public RequirementExpansion(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract Requirement generateRequirement(String name , Map< String , Object > format );

}