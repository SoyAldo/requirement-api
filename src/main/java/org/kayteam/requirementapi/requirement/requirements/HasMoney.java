package org.kayteam.requirementapi.requirement.requirements;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.requirementapi.requirement.Requirement;

public class HasMoney extends Requirement {

    private final String amount;
    private final boolean invert;

    public HasMoney(String amount, boolean invert) {
        this.amount = amount;
        this.invert = invert;
    }

    public String getAmount() {
        return amount;
    }

    public boolean isInvert() {
        return invert;
    }

    @Override
    public boolean runVerify(Player player) {
        if (getRequirementManager().isEconomyEnabled()) {
            String a = "";
            if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
                a = PlaceholderAPI.setPlaceholders(player, amount);
            }
            try {
                double amountParsed = Double.parseDouble(a);
                double balance = getRequirementManager().getEconomy().getBalance(player);
                if (invert) {
                    return !(balance >= amountParsed);
                } else {
                    return balance >= amountParsed;
                }
            } catch (IllegalArgumentException ignored) {}
        }
        return false;
    }

}