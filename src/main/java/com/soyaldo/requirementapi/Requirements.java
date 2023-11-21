package com.soyaldo.requirementapi;

import com.soyaldo.actionapi.Actions;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Requirements {

    private int minimumRequirements = 0;
    private Actions denyActions = new Actions();
    private final HashMap<String, Requirement> requirements = new HashMap<>();

    public int getMinimumRequirements() {
        return minimumRequirements;
    }

    public void setMinimumRequirements(int minimumRequirements) {
        this.minimumRequirements = minimumRequirements;
    }

    public Actions getDenyActions() {
        return denyActions;
    }

    public void setDenyActions(Actions denyActions) {
        this.denyActions = denyActions;
    }

    public boolean existRequirement(String name) {
        return requirements.containsKey(name);
    }

    public void addRequirement(Requirement requirement) {
        requirements.put(requirement.getName(), requirement);
    }

    public void removeRequirement(String name) {
        requirements.remove(name);
    }

    public Requirement getRequirement(String name) {
        return requirements.get(name);
    }

    public boolean verifyAll(Player player) {

        int requirementsPassed = 0;

        for (Requirement requirement : requirements.values()) {

            boolean currentResult = requirement.onVerify(player);

            if (currentResult) {

                if (requirement.getSuccessActions() != null) requirement.getSuccessActions().executeAll(player);

                requirementsPassed++;

            }

            if (minimumRequirements > 0) {

                if (requirementsPassed >= minimumRequirements) return true;

            } else {

                if (!currentResult) {

                    if (requirement.getDenyActions() != null) {

                        requirement.getDenyActions().executeAll(player);

                    } else {

                        if (denyActions != null) denyActions.executeAll(player);

                    }

                    return false;

                }

            }

        }

        if ((minimumRequirements > 0)) {

            if (denyActions != null) denyActions.executeAll(player);

            return false;

        }

        return true;

    }

    public boolean verifyAll(Player player, boolean executeActions) {

        int requirementsPassed = 0;

        for (Requirement requirement : requirements.values()) {

            boolean currentResult = requirement.onVerify(player);

            if (currentResult) {

                if (executeActions && requirement.getSuccessActions() != null)
                    requirement.getSuccessActions().executeAll(player);

                requirementsPassed++;

            }

            if (minimumRequirements > 0) {

                if (requirementsPassed >= minimumRequirements) return true;

            } else {

                if (!currentResult) {

                    if (executeActions && requirement.getDenyActions() != null) {

                        requirement.getDenyActions().executeAll(player);

                    } else {

                        if (executeActions && denyActions != null) denyActions.executeAll(player);

                    }

                    return false;

                }

            }

        }

        if ((minimumRequirements > 0)) {

            if (executeActions && denyActions != null) denyActions.executeAll(player);

            return false;

        }

        return true;

    }

    public LinkedHashMap<String, Object> serialize() {

        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        if (minimumRequirements > 0) result.put("minimumRequirements", minimumRequirements);

        LinkedHashMap<String, Object> requirementsSerialized = new LinkedHashMap<>();

        for (Requirement requirement : requirements.values()) {

            requirementsSerialized.put(requirement.getName(), requirement.serialize());

        }

        result.put("requirements", requirementsSerialized);

        if (denyActions != null) result.put("denyActions", denyActions.serialize());

        return result;

    }

}