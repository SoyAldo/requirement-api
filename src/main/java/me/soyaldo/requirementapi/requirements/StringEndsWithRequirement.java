package me.soyaldo.requirementapi.requirements;

import me.soyaldo.requirementapi.models.Requirement;
import me.soyaldo.requirementapi.util.RequirementUtil;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class StringEndsWithRequirement extends Requirement {

    private final String input;
    private final String output;

    public StringEndsWithRequirement(String name, String input, String output, boolean positive) {
        super(name, "string ends with", positive);
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    @Override
    public boolean onVerify(Player player, String[][] replacements) {
        String realInput = RequirementUtil.processText(input, player, replacements);
        String realOutput = RequirementUtil.processText(output, player, replacements);
        return isPositive() == (realInput.endsWith(realOutput));
    }

    @Override
    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> result = super.serialize();
        result.put("input", input);
        result.put("output", output);
        return result;
    }

}