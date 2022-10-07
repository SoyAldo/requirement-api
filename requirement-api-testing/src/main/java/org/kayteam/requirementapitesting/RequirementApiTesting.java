package org.kayteam.requirementapitesting;

import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.requirementapi.RequirementManager;
import org.kayteam.requirementapitesting.commands.RequirementApiTestingCommand;

import java.io.File;

public final class RequirementApiTesting extends JavaPlugin {

    RequirementManager requirementManager = new RequirementManager( this );

    @Override
    public void onEnable() {

        registerFiles();

        registerManagers();

        registerCommands();

    }

    @Override
    public void onDisable() { }

    public void onReload() {

        try {

            getConfig().load( new File( getDataFolder() + "config.yml") );

        } catch ( Exception ignore ) { }

    }

    private void registerFiles() {

        File file = new File( getDataFolder() , "config.yml" );

        if ( ! file.exists() ) {

            saveResource( "config.yml" , true );

        }

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

}