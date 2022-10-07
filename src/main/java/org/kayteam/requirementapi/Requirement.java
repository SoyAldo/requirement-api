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

    /**
     * Get the requirement manager
     * @return The requirement manager
     */
    public RequirementManager getRequirementManager() {
        return requirementManager;
    }

    /**
     * Get the requirement name
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the requirement type
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * Get if the requirement is positive or negative
     * @return true if the requirement verify is positive to pass or false if the requirement verify is negative to pass
     */
    public boolean isPositive() {
        return positive;
    }

    /**
     * Verify if the player pass this requirement
     * @param player The player
     * @return true if the player pass or false if not
     */
    public abstract boolean verify( Player player );

    public LinkedHashMap<String, Object> serialize() {

        LinkedHashMap< String , Object > result = new LinkedHashMap<>();

        result.put( "type" , type );

        return result;

    }

}