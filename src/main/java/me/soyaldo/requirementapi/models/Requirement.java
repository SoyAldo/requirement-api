package me.soyaldo.requirementapi.models;

import me.soyaldo.actionapi.models.Actions;
import me.soyaldo.requirementapi.managers.RequirementManager;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public abstract class Requirement {

    private RequirementManager requirementManager = null;
    private final String name;
    private final String type;
    private final boolean positive;
    private Actions denyActions;
    private Actions successActions;

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
     * @param player       The player
     * @param replacements The replacements
     * @return true if the player pass or false if not
     */
    public abstract boolean onVerify(Player player, String[][] replacements);

    public boolean verify(Player player) {
        return verify(player, true);
    }

    public boolean verify(Player player, String[][] requirements) {
        return verify(player, requirements, true);
    }

    public boolean verify(Player player, boolean executeActions) {
        return verify(player, new String[][]{}, executeActions);
    }

    public boolean verify(Player player, String[][] replacements, boolean executeActions) {
        boolean result = onVerify(player, replacements);

        if (result) {
            if (executeActions && successActions != null) successActions.execute(player);
        } else {
            if (executeActions && denyActions != null) denyActions.execute(player);
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

        if (denyActions != null) result.put("denyActions", denyActions.getActions());
        if (successActions != null) result.put("successAction", successActions.getActions());

        return result;
    }

}