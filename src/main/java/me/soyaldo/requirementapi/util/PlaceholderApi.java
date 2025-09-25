package me.soyaldo.requirementapi.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class PlaceholderApi {

    public static String setPlaceholders(Player player, String text) {
        Server server = Bukkit.getServer();
        PluginManager pluginManager = server.getPluginManager();
        Plugin placeholderapi = pluginManager.getPlugin("PlaceholderAPI");

        if (placeholderapi == null) return text;

        return PlaceholderAPI.setPlaceholders(player, text);
    }

    public static String setPlaceholders(OfflinePlayer player, String text) {
        Server server = Bukkit.getServer();
        PluginManager pluginManager = server.getPluginManager();
        Plugin placeholderapi = pluginManager.getPlugin("PlaceholderAPI");

        if (placeholderapi == null) return text;

        return PlaceholderAPI.setPlaceholders(player, text);
    }

}