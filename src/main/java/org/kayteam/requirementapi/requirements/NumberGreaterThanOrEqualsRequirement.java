package org.kayteam.requirementapi.requirements;

import org.bukkit.entity.Player;
import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.util.PlaceholderUtil;

import java.util.LinkedHashMap;

public class NumberGreaterThanOrEqualsRequirement extends Requirement {

    private final String input , output;

    public NumberGreaterThanOrEqualsRequirement(String name , String input , String output , boolean positive ) {
        super( name , "number greater than or equals" , positive );
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
    public boolean verify( Player player ) {

        String realInput = input , realOutput = output;

        realInput = PlaceholderUtil.setPlaceholders( player , realInput);

        realOutput = PlaceholderUtil.setPlaceholders( player , realOutput);

        try {

            int parsedInput = Integer.parseInt( realInput );

            int parsedOutput = Integer.parseInt( realOutput );

            return isPositive() == ( parsedInput >= parsedOutput );

        } catch ( NumberFormatException ignore ) {}

        return !isPositive();

    }

    @Override
    public LinkedHashMap<String, Object> serialize() {

        LinkedHashMap<String, Object> result = super.serialize();

        if ( ! isPositive() ) result.put( "type" , "!number greater than or equals" );

        result.put( "input" , input );

        result.put( "output" , output );

        return result;

    }

}