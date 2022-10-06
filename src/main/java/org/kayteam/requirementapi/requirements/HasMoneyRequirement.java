package org.kayteam.requirementapi.requirements;

import org.bukkit.entity.Player;

import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.util.PlaceholderUtil;

import net.milkbowl.vault.economy.Economy;

import java.util.LinkedHashMap;

public class HasMoneyRequirement extends Requirement {

    private final String amount;

    public HasMoneyRequirement( String name , String amount , boolean positive ) {
        super( name , "has money" , positive );
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public boolean verify( Player player ) {

        if ( getRequirementManager().getEconomy() != null ) {

            String realAmount = amount;

            realAmount = PlaceholderUtil.setPlaceholders( player , realAmount );

            try {

                double parsedAmount = Double.parseDouble( realAmount );

                Economy economy = getRequirementManager().getEconomy();

                double balance = economy.getBalance( player );

                return isPositive() == ( balance >= parsedAmount );

            } catch ( NumberFormatException ignore ) {}

        }

        return !isPositive();

    }

    @Override
    public LinkedHashMap<String, Object> serialize() {

        LinkedHashMap< String , Object > result = super.serialize();

        if ( ! isPositive() ) result.put( "type" , "!has money" );

        result.put( "amount" , amount );

        return result;

    }

}