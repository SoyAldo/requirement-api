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

    public RequirementManager( JavaPlugin javaPlugin ) {
        this.javaPlugin = javaPlugin;
    }

    /**
     * Register the requirement manager
     * This load all default expansions and Vault Economy
     */
    public void registerManager() {

        if ( VaultUtil.isEconomyEnabled() ) economy = VaultUtil.getEconomy();

        addRequirementExpansion( new HasExpExpansion() );
        addRequirementExpansion( new HasMoneyExpansion() );
        addRequirementExpansion( new HasPermissionExpansion() );
        addRequirementExpansion( new IsNearExpansion() );
        addRequirementExpansion( new NumberEqualsExpansion() );
        addRequirementExpansion( new NumberGreaterThanExpansion() );
        addRequirementExpansion( new NumberGreaterThanOrEqualsExpansion() );
        addRequirementExpansion( new NumberLessThanExpansion() );
        addRequirementExpansion( new NumberLessThanOrEqualsExpansion() );
        addRequirementExpansion( new RegexMatchesExpansion() );
        addRequirementExpansion( new StringContainsExpansion() );
        addRequirementExpansion( new StringEndsWithExpansion() );
        addRequirementExpansion( new StringEqualsExpansion() );
        addRequirementExpansion( new StringEqualsIgnoreCaseExpansion() );
        addRequirementExpansion( new StringStartWithExpansion() );

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

    /**
     * Verify if a specific type exist
     * @param type The requirement type
     * @return true if the requirement type exist or false if no exist
     */
    public boolean existRequirementExpansion( String type ) {
        return requirementExpansions.containsKey( type );
    }

    /**
     * Add new requirement expansion
     * @param requirementExpansion The requirement expansion
     */
    public void addRequirementExpansion(RequirementExpansion requirementExpansion ) {

        requirementExpansions.put( requirementExpansion.getType() , requirementExpansion );

    }

    /**
     * Remove a specific requirement expansion by type
     * @param type The requirement type
     */
    public void removeRequirementExpansion( String type ) {
        requirementExpansions.remove( type );
    }

    /**
     * Get a specific requirement expansion
     * @param type The requirement type
     * @return A requirement expansion if exist or null if not exist
     */
    public RequirementExpansion getRequirementExpansion( String type ) {
        return requirementExpansions.get( type );
    }

    public Requirements loadRequirements( ConfigurationSection configurationSection ) {

        Requirements requirements = new Requirements();

        if ( configurationSection.contains( "requirements") ) {

            if ( configurationSection.isConfigurationSection( "requirements" ) ) {

                for ( String requirementName : configurationSection.getConfigurationSection( "requirements" ).getKeys( false ) ) {

                    if ( ! configurationSection.isConfigurationSection( "requirements." + requirementName ) ) continue;

                    Map< String , Object > format = configurationSection.getConfigurationSection( "requirements." + requirementName ).getValues( true );

                    Requirement requirement = loadRequirement( requirementName , format );

                    if ( requirement != null ) requirements.addRequirement( requirement );

                }

            }

        }

        if ( configurationSection.contains( "minimum_requirements" ) ) {

            if ( configurationSection.isInt( "minimum_requirements" ) ) {

                requirements.setMinimumRequirements( configurationSection.getInt( "minimum_requirements" ) );

            }

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