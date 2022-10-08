package org.kayteam.requirementapitesting;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.requirementapi.RequirementManager;
import org.kayteam.requirementapitesting.commands.RequirementApiTestingCommand;

import java.io.File;

public final class RequirementApiTesting extends JavaPlugin {

    private FileConfiguration config;
    private final RequirementManager requirementManager = new RequirementManager( this );

    @Override
    public void onEnable() {

        registerFiles();

        registerManagers();

        registerCommands();

    }

    @Override
    public void onDisable() { }

    public void onReload() {

        registerFiles();

    }

    private void registerFiles() {

        File file = new File( getDataFolder() , "config.yml");

        if ( ! file.exists() ) {

            saveResource("config.yml", true );

        }

        config = YamlConfiguration.loadConfiguration( file );

    }

    private void registerManagers() {
        requirementManager.registerManager();
    }

    private void registerCommands() {

        getCommand( "RequirementApiTesting" ).setExecutor( new RequirementApiTestingCommand( this ) );

        getCommand( "RequirementApiTesting" ).setTabCompleter( new RequirementApiTestingCommand( this ) );

    }

    public RequirementManager getRequirementManager() {

        return requirementManager;

    }


    @Override
    public FileConfiguration getConfig() {
        return config;
    }

}