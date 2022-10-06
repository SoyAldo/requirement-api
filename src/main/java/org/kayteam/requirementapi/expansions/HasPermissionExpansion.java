package org.kayteam.requirementapi.expansions;

import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.RequirementExpansion;
import org.kayteam.requirementapi.requirements.HasPermissionRequirement;

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