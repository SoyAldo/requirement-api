package me.soyaldo.requirementapi.util;

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

}