package org.kayteam.requirementapitesting.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.kayteam.requirementapi.Requirement;
import org.kayteam.requirementapi.RequirementManager;
import org.kayteam.requirementapitesting.RequirementApiTesting;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RequirementApiTestingCommand implements CommandExecutor , TabCompleter {

    private final RequirementApiTesting requirementApiTesting;

    public RequirementApiTestingCommand( RequirementApiTesting requirementApiTesting ) {

        this.requirementApiTesting = requirementApiTesting;

    }

    @Override
    public boolean onCommand( CommandSender sender , Command command , String label , String[] args ) {

        FileConfiguration config = requirementApiTesting.getConfig();

        if ( ! ( sender instanceof Player) ) {

            sender.sendMessage( ChatColor.translateAlternateColorCodes( '&' , config.getString( "messages.onlyPlayers" ) ) );

            return true;

        }

        Player player = ( Player ) sender;

        if ( args.length < 1 ) {

            sender.sendMessage( ChatColor.translateAlternateColorCodes( '&' , config.getString( "messages.emptyArgs" ) ) );

            return true;

        }

        String subcommand = args[0];

        if ( subcommand.equalsIgnoreCase( "reload" ) ) {

            requirementApiTesting.onReload();

            sender.sendMessage( ChatColor.translateAlternateColorCodes( '&' , config.getString( "messages.reloaded" ) ) );

        } else if ( subcommand.equalsIgnoreCase( "test" ) ) {

            if ( args.length < 2 ) {

                sender.sendMessage( ChatColor.translateAlternateColorCodes( '&' , config.getString( "messages.emptyRequirement" ) ) );

                return true;

            }

            StringBuilder requirementTypeBuilder = new StringBuilder();
            String requirementType;

            for ( int i = 1 ; i < args.length ; i++ ) {

                requirementTypeBuilder.append(" ").append( args[i] );

            }

            requirementType = requirementTypeBuilder.toString().replaceFirst( " " , "" );

            RequirementManager requirementManager = requirementApiTesting.getRequirementManager();

            if ( ! config.contains( "types." + requirementType ) || ! requirementManager.existRequirementExpansion( requirementType ) ) {

                sender.sendMessage( ChatColor.translateAlternateColorCodes( '&' , config.getString( "messages.invalidRequirement" ) ) );

                return true;

            }

            Map< String , Object > requirementFormat = config.getConfigurationSection( "types." + requirementType ).getValues( true );

            Requirement requirement = requirementManager.loadRequirement( requirementType , requirementFormat );

            requirement.verify( player );

            sender.sendMessage( ChatColor.translateAlternateColorCodes( '&' , config.getString( "messages.verifyStarted" ) ) );

        }

        return true;

    }

    @Override
    public List<String> onTabComplete( CommandSender sender , Command command , String alias , String[] args ) {

        if ( args.length == 1 ) {

            return Arrays.asList( "reload" , "test" );

        }

        if ( args.length == 2 ) {

            return Arrays.asList( "has exp" );

        }

        return null;

    }

}