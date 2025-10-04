package me.soyaldo.requirementapi.requirements;

import me.soyaldo.requirementapi.models.Requirement;
import me.soyaldo.requirementapi.util.RequirementUtil;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class HasExp extends Requirement {

    private final String amount;
    private final boolean level;

    public HasExp(String name, String amount, boolean level, boolean positive) {
        super(name, "has exp", positive);
        this.amount = amount;
        this.level = level;
    }

    public String getAmount() {
        return amount;
    }

    public boolean isLevel() {
        return level;
    }

    @Override
    public boolean onVerify(Player player, String[][] replacements) {
        String realAmount = RequirementUtil.processText(amount, player, replacements);
        try {
            int parsedAmount = Integer.parseInt(realAmount);
            if (level) {
                return isPositive() == (player.getLevel() >= parsedAmount);
            } else {
                return isPositive() == (player.getTotalExperience() >= parsedAmount);
            }
        } catch (NumberFormatException ignore) {
            return !isPositive();
        }
    }

    @Override
    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> result = super.serialize();
        result.put("amount", amount);
        result.put("level", level);
        return result;
    }

}