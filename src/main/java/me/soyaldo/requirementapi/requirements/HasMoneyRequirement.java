package me.soyaldo.requirementapi.requirements;

import me.soyaldo.requirementapi.models.Requirement;
import me.soyaldo.requirementapi.util.RequirementUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

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
        if (getRequirementManager().getEconomy() == null) return false;
        String realAmount = RequirementUtil.processText(amount, player, replacements);
        try {
            double parsedAmount = Double.parseDouble(realAmount);
            Economy economy = getRequirementManager().getEconomy();
            double balance = economy.getBalance(player);
            return isPositive() == (balance >= parsedAmount);
        } catch (NumberFormatException ignore) {
            return false;
        }
    }

    @Override
    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> result = super.serialize();
        result.put("amount", amount);
        return result;
    }

}