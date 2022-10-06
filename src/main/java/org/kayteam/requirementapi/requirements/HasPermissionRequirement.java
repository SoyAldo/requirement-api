package org.kayteam.requirementapi.requirements;

import org.bukkit.entity.Player;
import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.util.PlaceholderUtil;

import java.util.LinkedHashMap;

public class HasPermissionRequirement extends Requirement {

    private final String permission;

    public HasPermissionRequirement( String name , String permission , boolean positive ) {
        super( name , "has permission" , positive );
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public boolean verify( Player player ) {

        String realPermission = permission;

        realPermission = PlaceholderUtil.setPlaceholders( player , realPermission );

        return isPositive() == player.hasPermission(realPermission);

    }

    @Override
    public LinkedHashMap<String, Object> serialize() {

        LinkedHashMap< String , Object > result = super.serialize();

        if ( ! isPositive() ) result.put( "type" , "!has permission" );

        result.put( "permission" , permission );

        return result;

    }

}