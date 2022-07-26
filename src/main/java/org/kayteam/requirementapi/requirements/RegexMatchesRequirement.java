package org.kayteam.requirementapi.requirements;

import org.bukkit.entity.Player;
import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.util.PlaceholderAPIUtil;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RegexMatchesRequirement extends Requirement {

    private final String input , regex;

    public RegexMatchesRequirement( String name , String input , String regex, boolean positive ) {
        super( name , "regex matches" , positive );
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
    public boolean onVerify(Player player ) {

        String realInput = input , realRegex = regex;

        realInput = PlaceholderAPIUtil.setPlaceholders( player , realInput);

        realRegex = PlaceholderAPIUtil.setPlaceholders( player , realRegex);

        return isPositive() == ( Pattern.matches( realRegex , realInput ) );

    }

    @Override
    public LinkedHashMap<String, Object> serialize() {

        LinkedHashMap<String, Object> result = super.serialize();

        result.put("input", input);

        result.put("regex", regex);

        return result;

    }

}