package com.soyaldo.requirementapi.requirements;

import com.soyaldo.requirementapi.Requirement;
import com.soyaldo.requirementapi.util.PlaceholderApi;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class NumberLessThanRequirement extends Requirement {

    private final String input, output;

    public NumberLessThanRequirement(String name, String input, String output, boolean positive) {
        super(name, "number less than", positive);
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

        try {
            int parsedInput = Integer.parseInt(realInput);
            int parsedOutput = Integer.parseInt(realOutput);

            return isPositive() == (parsedInput < parsedOutput);
        } catch (NumberFormatException ignore) {
        }
        return !isPositive();
    }

    @Override
    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> result = super.serialize();
        result.put("input", input);
        result.put("output", output);
        return result;
    }

}