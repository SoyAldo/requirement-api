package org.kayteam.requirementapi.requirement.requirements;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.requirementapi.requirement.Requirement;

import java.util.regex.Pattern;

public class RegexMatches extends Requirement {

    private final String input;
    private final String regex;
    private final boolean invert;

    public RegexMatches(String input, String regex, boolean invert) {
        this.input = input;
        this.regex = regex;
        this.invert = invert;
    }

    public String getInput() {
        return input;
    }

    public String getRegex() {
        return regex;
    }

    public boolean isInvert() {
        return invert;
    }

    @Override
    public boolean runVerify(Player player) {
        String inputParsed = "";
        if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            inputParsed = PlaceholderAPI.setPlaceholders(player, input);
        }
        if (this.invert) {
            return !Pattern.matches(regex, inputParsed);
        } else {
            return Pattern.matches(regex, inputParsed);
        }
    }

}