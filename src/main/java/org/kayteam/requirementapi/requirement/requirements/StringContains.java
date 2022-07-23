package org.kayteam.requirementapi.requirement.requirements;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.requirementapi.requirement.Requirement;

public class StringContains extends Requirement {

    private final String input;
    private final String output;
    private final boolean invert;

    public StringContains(String input, String output, boolean invert) {
        this.input = input;
        this.output = output;
        this.invert = invert;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public boolean isInvert() {
        return invert;
    }

    @Override
    public boolean runVerify(Player player) {
        String inputParser = "";
        if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            inputParser = PlaceholderAPI.setPlaceholders(player, input);
        }
        if (invert) {
            return !inputParser.contains(output);
        } else {
            return inputParser.contains(output);
        }
    }

}