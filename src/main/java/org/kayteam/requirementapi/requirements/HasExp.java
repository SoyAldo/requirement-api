package org.kayteam.requirementapi.requirements;

import org.bukkit.entity.Player;
import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.util.PlaceholderUtil;

import java.util.LinkedHashMap;

public class HasExp extends Requirement {

    private final String amount;
    private final boolean level;

    public HasExp( String name , String amount, boolean level , boolean positive ) {
        super( name , "has exp" , positive );
        this.amount = amount;
        this.level = level;
    }

    public String getAmount() {
        return amount;
    }

    public boolean isLevel() {
        return level;
    }

    @Override
    public boolean verify( Player player ) {

        String realAmount = amount;

        realAmount = PlaceholderUtil.setPlaceholders( player , realAmount);

        try {

            int parsedAmount = Integer.parseInt( realAmount );

            if ( level ) {

                return isPositive() == ( player.getLevel() >= parsedAmount );

            } else {

                return isPositive() == ( player.getTotalExperience() >= parsedAmount );

            }

        } catch ( NumberFormatException ignore ) {}

        return !isPositive();

    }

    @Override
    public LinkedHashMap<String, Object> serialize() {

        LinkedHashMap<String, Object> result = super.serialize();

        if ( ! isPositive() ) result.put( "type" , "!has exp" );

        result.put( "amount" , amount );

        result.put( "level" , level );

        return result;

    }

}