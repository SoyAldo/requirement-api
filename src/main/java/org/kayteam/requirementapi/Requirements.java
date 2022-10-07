package org.kayteam.requirementapi;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Requirements {


    private int minimumRequirements = 0;
    private final HashMap< String , Requirement > requirements = new HashMap<>();

    public HashMap< String , Requirement > getRequirements() {
        return requirements;
    }

    public int getMinimumRequirements() {
        return minimumRequirements;
    }

    public void setMinimumRequirements(int minimumRequirements) {
        this.minimumRequirements = minimumRequirements;
    }

    public boolean existRequirement(String name ) {
        return requirements.containsKey( name );
    }

    public void addRequirement( Requirement requirement ) {
        requirements.put( requirement.getName() , requirement );
    }

    public void removeRequirement( String name ) {
        requirements.remove( name );
    }

    public Requirement getRequirement( String name ) {
        return requirements.get( name );
    }

    public boolean verifyAll( Player player ) {

        int requirementsPassed = 0;

        for ( Requirement requirement : requirements.values() ) {

            boolean currentResult = requirement.verify( player );

            if ( currentResult ) requirementsPassed++;

            if ( minimumRequirements > 0 ) {

                if ( requirementsPassed >= minimumRequirements ) return true;

            } else {

                if ( ! currentResult ) return false;

            }

        }

        return ( minimumRequirements <= 0 );

    }

    public LinkedHashMap< String , Object > serialize() {

        LinkedHashMap< String , Object > result = new LinkedHashMap<>();

        for ( Requirement requirement : requirements.values() ) {

            result.put( requirement.getName() , requirement.serialize() );

        }

        return result;

    }

}