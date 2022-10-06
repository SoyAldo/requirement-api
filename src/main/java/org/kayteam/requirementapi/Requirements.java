package org.kayteam.requirementapi;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Requirements {

    private final HashMap< String , Requirement > requirements = new HashMap<>();

    public HashMap< String , Requirement > getRequirements() {
        return requirements;
    }

    public boolean existRequirement( String name ) {
        return requirements.containsKey( name );
    }

    public void addRequirement( Requirement requirement ) {
        requirements.put( requirement.getName() , requirement );
    }

    public Requirement getRequirement( String name ) {
        return requirements.get( name );
    }

    public void removeRequirement( String name ) {
        requirements.remove( name );
    }

    public boolean verifyAll( Player player ) {
        for ( Requirement requirement : requirements.values() ) {
            if ( ! requirement.verify( player ) ) return false;
        }
        return true;
    }

    public LinkedHashMap< String , Object > serialize() {

        LinkedHashMap< String , Object > result = new LinkedHashMap<>();

        for ( Requirement requirement : requirements.values() ) {

            result.put( requirement.getName() , requirement.serialize() );

        }

        return result;

    }

}