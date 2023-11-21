package com.soyaldo.requirementapi.expansions;

import com.soyaldo.requirementapi.Requirement;
import com.soyaldo.requirementapi.RequirementExpansion;
import com.soyaldo.requirementapi.requirements.NumberLessThanRequirement;

import java.util.Map;

public class NumberLessThanExpansion extends RequirementExpansion {

    public NumberLessThanExpansion() {
        super( "number less than" );
    }

    @Override
    public Requirement generateRequirement( String name , Map<String, Object> format ) {

        Requirement requirement = null;

        String type = ( String ) format.get( "type" );

        if ( format.containsKey( "input" ) && format.containsKey( "output" ) ) {

            String input = ( String ) format.get( "input" ) , output = ( String ) format.get( "output" );

            requirement = new NumberLessThanRequirement( name , input , output , !type.startsWith( "!" ) );

        }

        return requirement;

    }

}