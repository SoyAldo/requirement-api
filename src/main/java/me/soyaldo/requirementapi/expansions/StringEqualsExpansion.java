package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.Requirement;
import me.soyaldo.requirementapi.RequirementExpansion;
import me.soyaldo.requirementapi.requirements.StringEqualsRequirement;

import java.util.Map;

public class StringEqualsExpansion extends RequirementExpansion {

    public StringEqualsExpansion() {
        super( "string equals" );
    }

    @Override
    public Requirement generateRequirement( String name , Map< String , Object > format ) {

        Requirement requirement = null;

        String type = ( String ) format.get( "type" );

        if ( format.containsKey( "input" ) && format.containsKey( "output" ) ) {

            String input = ( String ) format.get( "input" ) , output = ( String ) format.get( "output" );

            requirement = new StringEqualsRequirement( name , input , output , !type.startsWith( "!" ) );

        }

        return requirement;

    }

}