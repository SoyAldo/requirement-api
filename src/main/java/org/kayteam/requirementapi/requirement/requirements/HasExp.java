package org.kayteam.requirementapi.requirement.requirements;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.requirementapi.requirement.Requirement;

public class HasExp extends Requirement {

    private final String amount;
    private final boolean isLevel;
    private final boolean invert;

    public HasExp(String amount, boolean isLevel, boolean invert) {
        this.amount = amount;
        this.isLevel = isLevel;
        this.invert = invert;
    }

    public String getAmount() {
        return amount;
    }

    public boolean isLevel() {
        return isLevel;
    }

    public boolean isInvert() {
        return invert;
    }

    @Override
    public boolean runVerify(Player player) {
        String a = "";
        if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            a = PlaceholderAPI.setPlaceholders(player, amount);
        }
        try {
            int amountParsed = Integer.parseInt(a);
            if (isLevel) {
                if (invert) {
                    return !(player.getLevel() >= amountParsed);
                } else {
                    return player.getLevel() >= amountParsed;
                }
            } else {
                if (invert) {
                    return !(player.getTotalExperience() >= amountParsed);
                } else {
                    return player.getTotalExperience() >= amountParsed;
                }
            }
        } catch (IllegalArgumentException ignored) {}
        return false;
    }

}