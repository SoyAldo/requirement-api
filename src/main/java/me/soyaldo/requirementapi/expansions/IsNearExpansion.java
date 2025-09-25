package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.Requirement;
import me.soyaldo.requirementapi.RequirementExpansion;
import me.soyaldo.requirementapi.requirements.IsNearRequirement;

import java.util.Map;

public class IsNearExpansion extends RequirementExpansion {

    public IsNearExpansion() {
        super( "is near" );
    }

    @Override
    public Requirement generateRequirement( String name , Map< String , Object > format ) {

        Requirement requirement = null;

        String type = ( String ) format.get( "type" );

        if ( format.containsKey( "location" ) && format.containsKey( "distance" ) ) {

            String location = ( String ) format.get( "location" );

            String distance = ( String ) format.get( "distance" );

            requirement = new IsNearRequirement( name , location , distance , !type.startsWith( "!" ) );

        }

        return requirement;

    }

}