package org.kayteam.requirementapi.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlaceholderUtil {

    public static String setPlaceholders(Player player , String text ) {

        if ( Bukkit.getServer().getPluginManager().getPlugin( "PlaceholderAPI" ) != null ) {

            text = PlaceholderAPI.setPlaceholders( player , text );

        }

        return text;

    }

    public static String setPlaceholders(OfflinePlayer player , String text ) {

        if ( Bukkit.getServer().getPluginManager().getPlugin( "PlaceholderAPI" ) != null ) {

            text = PlaceholderAPI.setPlaceholders( player , text );

        }

        return text;

    }

}