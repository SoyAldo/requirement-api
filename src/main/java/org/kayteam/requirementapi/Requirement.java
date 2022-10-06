package org.kayteam.requirementapi;

import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public abstract class Requirement {

    private RequirementManager requirementManager;
    private final String name;
    private final String type;
    private final boolean positive;

    public Requirement( String name , String type , boolean positive) {
        this.name = name;
        this.type = type;
        this.positive = positive;
    }

    /**
     * Sets the requirement manager for this requirement
     * @param manager The RequirementManager
     */
    public void setRequirementManager(RequirementManager manager) {
        requirementManager = manager;
    }

    public RequirementManager getRequirementManager() {
        return requirementManager;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isPositive() {
        return positive;
    }

    public abstract boolean verify( Player player );

    public LinkedHashMap<String, Object> serialize() {

        LinkedHashMap< String , Object > result = new LinkedHashMap<>();

        result.put( "type" , type );

        return result;

    }

}