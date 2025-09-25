package me.soyaldo.requirementapi.requirements;

import org.bukkit.entity.Player;

import me.soyaldo.requirementapi.Requirement;
import me.soyaldo.requirementapi.util.PlaceholderApi;

import net.milkbowl.vault.economy.Economy;

import java.util.LinkedHashMap;

public class HasMoneyRequirement extends Requirement {

    private final String amount;

    public HasMoneyRequirement(String name, String amount, boolean positive) {
        super(name, "has money", positive);
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public boolean onVerify(Player player, String[][] replacements) {
        if (getRequirementManager().getEconomy() == null) return !isPositive();

        String realAmount = amount;

        for (String[] replacement : replacements) {
            realAmount = realAmount.replace(replacement[0], replacement[1]);
        }

        realAmount = PlaceholderApi.setPlaceholders(player, realAmount);

        try {
            double parsedAmount = Double.parseDouble(realAmount);
            Economy economy = getRequirementManager().getEconomy();
            double balance = economy.getBalance(player);
            return isPositive() == (balance >= parsedAmount);
        } catch (NumberFormatException ignore) {
            return !isPositive();
        }
    }

    @Override
    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> result = super.serialize();

        result.put("amount", amount);

        return result;
    }

}