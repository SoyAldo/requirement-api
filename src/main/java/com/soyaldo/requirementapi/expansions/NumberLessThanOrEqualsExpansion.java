package com.soyaldo.requirementapi.expansions;

import com.soyaldo.requirementapi.Requirement;
import com.soyaldo.requirementapi.RequirementExpansion;
import com.soyaldo.requirementapi.requirements.NumberLessThanOrEqualsRequirement;

import java.util.Map;

public class NumberLessThanOrEqualsExpansion extends RequirementExpansion {

    public NumberLessThanOrEqualsExpansion() {
        super( "number less than or equals" );
    }

    @Override
    public Requirement generateRequirement( String name , Map<String, Object> format ) {

        Requirement requirement = null;

        String type = ( String ) format.get( "type" );

        if ( format.containsKey( "input" ) && format.containsKey( "output" ) ) {

            String input = ( String ) format.get( "input" ) , output = ( String ) format.get( "output" );

            requirement = new NumberLessThanOrEqualsRequirement( name , input , output , !type.startsWith( "!" ) );

        }

        return requirement;

    }

}