package org.kayteam.requirementapi.requirement.requirements;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.requirementapi.requirement.Requirement;

public class HasPermission extends Requirement {

    private final String permission;
    private final boolean invert;

    public HasPermission(String permission, boolean invert) {
        this.permission = permission;
        this.invert = invert;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isInvert() {
        return invert;
    }

    @Override
    public boolean runVerify(Player player) {
        if (!permission.equals("")) {
            String a = "";
            if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
                a = PlaceholderAPI.setPlaceholders(player, permission);
            }
            if (invert) {
                return !player.hasPermission(permission);
            } else {
                return player.hasPermission(permission);
            }
        }
        return false;
    }

}