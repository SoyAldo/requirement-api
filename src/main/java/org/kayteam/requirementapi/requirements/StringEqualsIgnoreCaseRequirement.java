package org.kayteam.requirementapi.requirements;

import org.bukkit.entity.Player;

import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.util.PlaceholderAPIUtil;

import java.util.LinkedHashMap;

public class StringEqualsIgnoreCaseRequirement extends Requirement {

    private final String input , output;

    public StringEqualsIgnoreCaseRequirement( String name , String input , String output , boolean positive ) {
        super( name , "string equals ignorecase" , positive );
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
    public boolean onVerify(Player player ) {

        String realInput = input , realOutput = output;

        realInput = PlaceholderAPIUtil.setPlaceholders( player , realInput );

        realOutput = PlaceholderAPIUtil.setPlaceholders( player , realOutput );

        return isPositive() == ( realInput.equalsIgnoreCase( realOutput ) );

    }

    @Override
    public LinkedHashMap<String, Object> serialize() {

        LinkedHashMap< String , Object > result = super.serialize();

        result.put( "input" , input);

        result.put( "output" , output);

        return result;

    }

}