package org.kayteam.requirementapi;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import org.kayteam.requirementapi.expansions.*;
import org.kayteam.requirementapi.util.VaultUtil;

import java.util.HashMap;
import java.util.Map;

public class RequirementManager {

    private final JavaPlugin javaPlugin;
    private Economy economy = null;
    private final HashMap< String , Requirements > requirements = new HashMap<>();
    private final HashMap< String , RequirementExpansion > requirementExpansions = new HashMap<>();

    public RequirementManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public void registerManager() {

        if ( VaultUtil.isEconomyEnabled() ) economy = VaultUtil.getEconomy();

        registerExpansion( new HasExpExpansion() );
        registerExpansion( new HasMoneyExpansion() );
        registerExpansion( new HasPermissionExpansion() );
        registerExpansion( new IsNearExpansion() );
        registerExpansion( new NumberEqualsExpansion() );
        registerExpansion( new NumberGreaterThanExpansion() );
        registerExpansion( new NumberGreaterThanOrEqualsExpansion() );
        registerExpansion( new NumberLessThanExpansion() );
        registerExpansion( new NumberLessThanOrEqualsExpansion() );
        registerExpansion( new RegexMatchesExpansion() );
        registerExpansion( new StringContainsExpansion() );
        registerExpansion( new StringEndsWithExpansion() );
        registerExpansion( new StringEqualsExpansion() );
        registerExpansion( new StringEqualsIgnoreCaseExpansion() );
        registerExpansion( new StringStartWithExpansion() );

    }

    public void reloadManager() {

    }

    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    public Economy getEconomy() {
        return economy;
    }

    public HashMap<String, Requirements> getRequirements() {
        return requirements;
    }

    public HashMap<String, RequirementExpansion> getRequirementExpansions() {
        return requirementExpansions;
    }

    public void registerExpansion( RequirementExpansion requirementExpansion ) {

        requirementExpansions.put( requirementExpansion.getType() , requirementExpansion );

    }

    public Requirements loadRequirements( ConfigurationSection configurationSection ) {

        Requirements requirements = new Requirements();

        for ( String name : configurationSection.getKeys(false) ) {

            Map< String , Object > format = configurationSection.getConfigurationSection( name ).getValues( true );

            Requirement requirement = loadRequirement( name , format );

            if ( requirement == null) continue;

            requirements.addRequirement( requirement );

        }

        return requirements;

    }

    public Requirement loadRequirement( String name , Map< String , Object > format ) {

        Requirement requirement = null;

        if ( format.containsKey( "type" ) ) {

            String type = ( String ) format.get( "type" );

            if ( type.startsWith( "!" ) ) type = type.replaceFirst( "!" , "");

            RequirementExpansion requirementExpansion = requirementExpansions.get( type );

            if ( requirementExpansion != null ) requirement = requirementExpansion.generateRequirement( name , format );

        }

        if ( requirement != null ) requirement.setRequirementManager( this );

        return requirement;

    }

}