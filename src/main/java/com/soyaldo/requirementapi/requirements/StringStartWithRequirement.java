package com.soyaldo.requirementapi.requirements;

import com.soyaldo.requirementapi.Requirement;
import com.soyaldo.requirementapi.util.PlaceholderApi;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class StringStartWithRequirement extends Requirement {

    private final String input, output;

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
        String realInput = input;
        String realOutput = output;

        for (String[] replacement : replacements) {
            realInput = realInput.replace(replacement[0], replacement[1]);
            realOutput = realOutput.replace(replacement[0], replacement[1]);
        }

        realInput = PlaceholderApi.setPlaceholders(player, realInput);
        realOutput = PlaceholderApi.setPlaceholders(player, realOutput);

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