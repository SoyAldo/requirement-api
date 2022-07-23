package org.kayteam.requirementapi.requirement.requirements;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.requirementapi.requirement.Requirement;

public class Comparators extends Requirement {

    private final String type;
    private final String input;
    private final String output;
    private final boolean invert;

    public Comparators(String type, String input, String output, boolean invert) {
        this.type = type;
        this.input = input;
        this.output = output;
        this.invert = invert;
    }

    public String getType() {
        return type;
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
        String inputParsed = "";
        String outputParsed = "";
        if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            inputParsed = PlaceholderAPI.setPlaceholders(player, input);
            outputParsed = PlaceholderAPI.setPlaceholders(player, output);
        }
        try {
            int inputNumber = Integer.parseInt(inputParsed);
            int outputNumber = Integer.parseInt(outputParsed);
            if (invert) {
                switch (type) {
                    case ">":
                        return !(inputNumber > outputNumber);
                    case ">=":
                        return !(inputNumber >= outputNumber);
                    case "==":
                        return !(inputNumber == outputNumber);
                    case "<":
                        return !(inputNumber < outputNumber);
                    case "<=":
                        return !(inputNumber <= outputNumber);
                    default:
                        return false;
                }
            } else {
                switch (type) {
                    case ">":
                        return inputNumber > outputNumber;
                    case ">=":
                        return inputNumber >= outputNumber;
                    case "==":
                        return inputNumber == outputNumber;
                    case "<":
                        return inputNumber < outputNumber;
                    case "<=":
                        return inputNumber <= outputNumber;
                    default:
                        return false;
                }
            }
        } catch (NumberFormatException ignored) {}
        return false;
    }

}