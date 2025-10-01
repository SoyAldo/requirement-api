package me.soyaldo.requirementapi.util;

import me.soyaldo.actionapi.util.ColorUtil;
import me.soyaldo.actionapi.util.PapiUtil;
import me.soyaldo.actionapi.util.TextUtil;
import org.bukkit.entity.Player;

import java.util.Map;

public class RequirementUtil {

    /**
     * Verify if the format contain fields
     *
     * @param format The requirement format
     * @param fields The fields names array
     * @return true if the format contain all fields or false if not
     */
    public static boolean containFields(Map<String, Object> format, String... fields) {
        for (String field : fields) {
            if (!format.containsKey(field)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Apply replacements and PlaceholdersAPI on the text
     *
     * @param text         The text to process
     * @param player       The player to apply PlaceholdersAPI
     * @param replacements The replacements
     * @param color        If apply color on the text
     * @return The text modified
     */
    public static String processText(String text, Player player, String[][] replacements, boolean color) {
        String modifiedText = text;
        // => Apply the replacement
        modifiedText = TextUtil.replace(modifiedText, replacements);
        // => Apply PlaceholderAPI
        modifiedText = PapiUtil.setPlaceholders(player, modifiedText);
        // => Apply color
        if (color) {
            modifiedText = ColorUtil.colorizeLegacy(modifiedText);
        }
        // => Returning the modified text
        return modifiedText;
    }

    /**
     * Apply replacements and PlaceholdersAPI on the text without color
     *
     * @param text         The text to process
     * @param player       The player to apply PlaceholdersAPI
     * @param replacements The replacements
     * @return The text modified
     */
    public static String processText(String text, Player player, String[][] replacements) {
        return processText(text, player, replacements, false);
    }

}