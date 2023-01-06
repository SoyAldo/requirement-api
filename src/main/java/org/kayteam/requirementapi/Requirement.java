package org.kayteam.requirementapi;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.kayteam.actionapi.Actions;

import java.util.LinkedHashMap;

@Data
public abstract class Requirement {

    @Getter
    @Setter
    private RequirementManager requirementManager;
    @Getter
    private final String name;
    @Getter
    private final String type;
    @Getter
    private final boolean positive;
    @Getter
    @Setter
    private Actions denyActions;
    @Getter
    @Setter
    private Actions successActions;

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