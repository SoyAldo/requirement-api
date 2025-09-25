package me.soyaldo.requirementapi.expansions;

import me.soyaldo.requirementapi.Requirement;
import me.soyaldo.requirementapi.RequirementExpansion;
import me.soyaldo.requirementapi.requirements.HasPermissionRequirement;

import java.util.Map;

public class HasPermissionExpansion extends RequirementExpansion {

    public HasPermissionExpansion() {
        super( "has permission" );
    }

    @Override
    public Requirement generateRequirement( String name , Map< String , Object > format ) {

        Requirement requirement = null;

        String type = ( String ) format.get( "type" );

        if ( format.containsKey( "permission" ) ) {

            String permission = ( String ) format.get( "permission" );

            requirement = new HasPermissionRequirement( name , permission , !type.startsWith( "!" ) );

        }

        return requirement;

    }

}