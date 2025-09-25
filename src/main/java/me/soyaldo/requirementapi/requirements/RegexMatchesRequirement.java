package me.soyaldo.requirementapi.requirements;

import me.soyaldo.requirementapi.Requirement;
import me.soyaldo.requirementapi.util.PlaceholderApi;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RegexMatchesRequirement extends Requirement {

    private final String input, regex;

    public RegexMatchesRequirement(String name, String input, String regex, boolean positive) {
        super(name, "regex matches", positive);
        this.input = input;
        this.regex = regex;
    }

    public String getInput() {
        return input;
    }

    public String getRegex() {
        return regex;
    }

    @Override
    public boolean onVerify(Player player, String[][] replacements) {
        String realInput = input;
        String realRegex = regex;

        for (String[] replacement : replacements) {
            realInput = realInput.replace(replacement[0], replacement[1]);
            realRegex = realRegex.replace(replacement[0], replacement[1]);
        }

        realInput = PlaceholderApi.setPlaceholders(player, realInput);
        realRegex = PlaceholderApi.setPlaceholders(player, realRegex);

        return isPositive() == (Pattern.matches(realRegex, realInput));
    }

    @Override
    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> result = super.serialize();
        result.put("input", input);
        result.put("regex", regex);
        return result;
    }

}