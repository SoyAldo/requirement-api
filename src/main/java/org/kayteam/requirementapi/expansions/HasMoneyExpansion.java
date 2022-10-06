package org.kayteam.requirementapi.expansions;

import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.RequirementExpansion;
import org.kayteam.requirementapi.requirements.HasMoneyRequirement;

import java.util.Map;

public class HasMoneyExpansion extends RequirementExpansion {

    public HasMoneyExpansion() {
        super( "has money" );
    }

    @Override
    public Requirement generateRequirement( String name , Map< String , Object > format ) {

        Requirement requirement = null;

        String type = ( String ) format.get( "type" );

        if ( format.containsKey( "amount" ) ) {

            String amount = ( String ) format.get( "amount" );

            requirement = new HasMoneyRequirement( name , amount , !type.startsWith("!") );

        }

        return requirement;

    }

}