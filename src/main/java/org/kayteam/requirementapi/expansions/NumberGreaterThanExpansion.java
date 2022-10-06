package org.kayteam.requirementapi.expansions;

import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.RequirementExpansion;
import org.kayteam.requirementapi.requirements.NumberGreaterThanRequirement;

import java.util.Map;

public class NumberGreaterThanExpansion extends RequirementExpansion {

    public NumberGreaterThanExpansion() {
        super( "number greater than" );
    }

    @Override
    public Requirement generateRequirement( String name , Map<String, Object> format ) {

        Requirement requirement = null;

        String type = ( String ) format.get( "type" );

        if ( format.containsKey( "input" ) && format.containsKey( "output" ) ) {

            String input = ( String ) format.get( "input" ) , output = ( String ) format.get( "output" );

            requirement = new NumberGreaterThanRequirement( name , input , output , !type.startsWith( "!" ) );

        }

        return requirement;

    }

}