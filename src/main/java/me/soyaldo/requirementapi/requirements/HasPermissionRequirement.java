package me.soyaldo.requirementapi.requirements;

import me.soyaldo.requirementapi.models.Requirement;
import me.soyaldo.requirementapi.util.RequirementUtil;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class HasPermissionRequirement extends Requirement {

    private final String permission;

    public HasPermissionRequirement(String name, String permission, boolean positive) {
        super(name, "has permission", positive);
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public boolean onVerify(Player player, String[][] replacements) {
        String realPermission = RequirementUtil.processText(permission, player, replacements);
        return isPositive() == player.hasPermission(realPermission);

    }

    @Override
    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> result = super.serialize();
        result.put("permission", permission);
        return result;
    }

}