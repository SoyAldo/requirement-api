package me.soyaldo.requirementapi.requirements;

import me.soyaldo.requirementapi.models.Requirement;
import me.soyaldo.requirementapi.util.RequirementUtil;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RegexMatchesRequirement extends Requirement {

    private final String input;
    private final String regex;

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
        String realInput = RequirementUtil.processText(input, player, replacements);
        String realRegex = RequirementUtil.processText(regex, player, replacements);
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