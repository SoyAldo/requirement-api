package com.soyaldo.requirementapi.expansions;

import com.soyaldo.requirementapi.Requirement;
import com.soyaldo.requirementapi.RequirementExpansion;
import com.soyaldo.requirementapi.requirements.StringStartWithRequirement;

import java.util.Map;

public class StringStartWithExpansion extends RequirementExpansion {

    public StringStartWithExpansion() {
        super( "string starts with" );
    }

    @Override
    public Requirement generateRequirement( String name , Map<String, Object> format ) {

        Requirement requirement = null;

        String type = ( String ) format.get( "type" );

        if ( format.containsKey( "input" ) && format.containsKey( "output" ) ) {

            String input = ( String ) format.get( "input" ) , output = ( String ) format.get( "output" );

            requirement = new StringStartWithRequirement( name , input , output , !type.startsWith( "!" ) );

        }

        return requirement;

    }

}