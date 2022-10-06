package org.kayteam.requirementapi.requirements;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.util.PlaceholderAPIUtil;

import java.util.LinkedHashMap;

public class IsNearRequirement extends Requirement {

    private final String location , distance;

    public IsNearRequirement( String name , String location , String distance , boolean positive ) {
        super( name , "is near" , positive );
        this.location = location;
        this.distance = distance;
    }

    public String getLocation() {
        return location;
    }

    public String getDistance() {
        return distance;
    }

    @Override
    public boolean verify( Player player ) {

        String realLocation = location , realDistance = distance;

        realLocation = PlaceholderAPIUtil.setPlaceholders( player , realLocation );

        realDistance = PlaceholderAPIUtil.setPlaceholders( player , realDistance );

        try {

            int parsedDistance = Integer.parseInt( realDistance );

            if ( realLocation.contains( "," ) ) {

                String[] realLocationSplit = realLocation.split( "," );

                if ( realLocationSplit.length == 4 ) {

                    World world = getRequirementManager().getJavaPlugin().getServer().getWorld( realLocationSplit[ 0 ] );
                    int x = Integer.parseInt( realLocationSplit[ 1 ] );
                    int y = Integer.parseInt( realLocationSplit[ 2 ] );
                    int z = Integer.parseInt( realLocationSplit[ 3 ] );

                    if ( world != null ) {

                        Location parsedLocation = new Location( world , x , y , z );

                        return isPositive() == ( player.getLocation().distance( parsedLocation ) < parsedDistance );

                    }

                }

            }

        } catch ( NumberFormatException ignore ) {}

        return !isPositive();

    }

    @Override
    public LinkedHashMap<String, Object> serialize() {

        LinkedHashMap<String, Object> result = super.serialize();

        if ( ! isPositive() ) result.put( "type" , "!is near" );

        result.put( "location" , location );

        result.put( "distance" , distance );

        return result;

    }

}