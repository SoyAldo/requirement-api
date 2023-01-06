package org.kayteam.requirementapi.util;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;

public class VaultUtil {

    public static boolean isEconomyEnabled() {
        Server server = Bukkit.getServer();
        PluginManager pluginManager = server.getPluginManager();
        Plugin vault = pluginManager.getPlugin("Vault");

        if (vault == null) return false;

        ServicesManager servicesManager = server.getServicesManager();
        RegisteredServiceProvider<Economy> registeredServiceProvider = servicesManager.getRegistration(Economy.class);

        if (registeredServiceProvider == null) return false;

        return registeredServiceProvider.getProvider() != null;
    }

    public static Economy getEconomy() {
        Server server = Bukkit.getServer();
        PluginManager pluginManager = server.getPluginManager();
        Plugin vault = pluginManager.getPlugin("Vault");

        if (vault == null) return null;

        ServicesManager servicesManager = server.getServicesManager();
        RegisteredServiceProvider<Economy> registeredServiceProvider = servicesManager.getRegistration(Economy.class);

        if (registeredServiceProvider == null) return null;

        return registeredServiceProvider.getProvider();
    }

}