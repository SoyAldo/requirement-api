package me.soyaldo.requirementapi.requirements;

import me.soyaldo.requirementapi.Requirement;
import me.soyaldo.requirementapi.util.RequirementUtil;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class NumberEqualsRequirement extends Requirement {

    private final String input;
    private final String output;

    public NumberEqualsRequirement(String name, String input, String output, boolean positive) {
        super(name, "number equals", positive);
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
        // => Trying to parse the input and output
        try {
            // Parsing the input and output
            double parsedInput = Double.parseDouble(realInput);
            double parsedOutput = Double.parseDouble(realOutput);
            // Return the result
            return isPositive() == (parsedInput == parsedOutput);
        } catch (NumberFormatException ignore) {
            // If any is wrong return false
            return false;
        }
    }

    @Override
    public LinkedHashMap<String, Object> serialize() {
        LinkedHashMap<String, Object> result = super.serialize();
        result.put("input", input);
        result.put("output", output);
        return result;
    }

}