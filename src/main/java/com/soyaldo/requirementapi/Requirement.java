package com.soyaldo.requirementapi;

import com.soyaldo.actionapi.Actions;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public abstract class Requirement {

    private RequirementManager requirementManager = null;
    private final String name;
    private final String type;
    private final boolean positive;
    private Actions denyActions = null;
    private Actions successActions = null;

    public Requirement(String name, String type, boolean positive) {
        this.name = name;
        this.type = type;
        this.positive = positive;
    }

    public RequirementManager getRequirementManager() {
        return requirementManager;
    }

    public void setRequirementManager(RequirementManager requirementManager) {
        this.requirementManager = requirementManager;
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

    public Actions getDenyActions() {
        return denyActions;
    }

    public void setDenyActions(Actions denyActions) {
        this.denyActions = denyActions;
    }

    public Actions getSuccessActions() {
        return successActions;
    }

    public void setSuccessActions(Actions successActions) {
        this.successActions = successActions;
    }

    /**
     * Verify if the player pass this requirement
     *
     * @param player The player
     * @return true if the player pass or false if not
     */
    public abstract boolean onVerify(Player player);

    public boolean verify(Player player, boolean executeActions) {
        boolean result = onVerify(player);

        if (result) {
            if (executeActions && successActions != null) successActions.executeAll(player);
        } else {
            if (executeActions && denyActions != null) denyActions.executeAll(player);
        }

        return result;
    }

    public boolean verify(Player player) {
        boolean result = onVerify(player);

        if (result) {
            if (successActions != null) successActions.executeAll(player);
        } else {
            if (denyActions != null) denyActions.executeAll(player);
        }

        return result;
    }

    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        if (positive) {
            result.put("type", type);
        } else {
            result.put("type", "!" + type);
        }

        if (denyActions != null) result.put("denyActions", denyActions.serialize());
        if (successActions != null) result.put("successAction", successActions.serialize());

        return result;
    }

}