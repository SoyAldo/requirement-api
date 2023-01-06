package org.kayteam.requirementapi.requirements;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.util.PlaceholderAPIUtil;

import java.util.LinkedHashMap;

public class HasExp extends Requirement {

    @Getter private final String amount;
    @Getter private final boolean level;

    public HasExp(String name, String amount, boolean level, boolean positive) {
        super(name, "has exp", positive);
        this.amount = amount;
        this.level = level;
    }

    @Override
    public boolean onVerify(Player player) {
        String realAmount = amount;

        realAmount = PlaceholderAPIUtil.setPlaceholders(player, realAmount);

        try {
            int parsedAmount = Integer.parseInt(realAmount);
            if (level) {
                return isPositive() == (player.getLevel() >= parsedAmount);
            } else {
                return isPositive() == (player.getTotalExperience() >= parsedAmount);
            }
        } catch (NumberFormatException ignore) {
        }

        return !isPositive();
    }

    @Override
    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> result = super.serialize();

        result.put("amount", amount);
        result.put("level", level);

        return result;
    }

}