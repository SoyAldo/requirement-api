package org.kayteam.requirementapi;

import lombok.Data;

import java.util.Map;

@Data
public abstract class RequirementExpansion {

    private final String type;

    public abstract Requirement generateRequirement( String name , Map< String , Object > format );

}