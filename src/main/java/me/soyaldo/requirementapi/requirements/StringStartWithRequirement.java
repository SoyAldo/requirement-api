package me.soyaldo.requirementapi.requirements;

import me.soyaldo.requirementapi.models.Requirement;
import me.soyaldo.requirementapi.util.RequirementUtil;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class StringStartWithRequirement extends Requirement {

    private final String input;
    private final String output;

    public StringStartWithRequirement(String name, String input, String output, boolean positive) {
        super(name, "string start with", positive);
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
        return isPositive() == (realInput.startsWith(realOutput));
    }

    @Override
    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> result = super.serialize();
        result.put("input", input);
        result.put("output", output);
        return result;
    }

}