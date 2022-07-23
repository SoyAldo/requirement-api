package org.kayteam.requirementapi.requirement;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.actionapi.action.ActionManager;
import org.kayteam.actionapi.action.ActionModule;
import org.kayteam.requirementapi.requirement.requirements.*;
import org.kayteam.storageapi.storage.Yaml;

public class RequirementManager {

    private JavaPlugin javaPlugin;
    private Economy economy;

    public void register(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> registeredServiceProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
            if (registeredServiceProvider != null) {
                economy = registeredServiceProvider.getProvider();
            }
        }
    }

    public Economy getEconomy() {
        return economy;
    }

    public boolean isEconomyEnabled() {
        return economy !=null;
    }

    public RequirementModule loadRequirementModule(String path, Yaml yaml) {
        RequirementModule requirementModule = new RequirementModule();
        // minimum_requirements
        if (yaml.contains(path + ".minimum_requirements") && yaml.isInt(path + ".minimum_requirements")) {
            requirementModule.setMinimum_requirements(yaml.getInt(path + ".minimum_requirements"));
        }
        // stop_at_success
        if (yaml.contains(path + ".stop_at_success") && yaml.isBoolean(path + ".stop_at_success")) {
            requirementModule.setStop_at_success(yaml.getBoolean(path + ".stop_at_success"));
        }
        // requirements
        if (yaml.contains(path + ".requirements") && yaml.isConfigurationSection(path + ".requirements")) {
            for (String name:yaml.getConfigurationSection(path + ".requirements").getKeys(false)) {
                String type = yaml.getString(path + ".requirements." + name + ".type");
                Requirement requirement = null;
                switch (type) {
                    case "has permission": {
                        if (yaml.contains(path + ".requirements." + name + ".permission") && yaml.isString(path + ".requirements." + name + ".permission")) {
                            String permission = yaml.getString(path + ".requirements." + name + ".permission");
                            requirement = new HasPermission(permission, false);
                        }
                        break;
                    }
                    case "!has permission": {
                        if (yaml.contains(path + ".requirements." + name + ".permission") && yaml.isString(path + ".requirements." + name + ".permission")) {
                            String permission = yaml.getString(path + ".requirements." + name + ".permission");
                            requirement = new HasPermission(permission, true);
                        }
                        break;
                    }
                    case "has money": {
                        if (yaml.contains(path + ".requirements." + name + ".amount") && yaml.isString(path + ".requirements." + name + ".amount")) {
                            String amount = yaml.getString(path + ".requirements." + name + ".amount");
                            requirement = new HasMoney(amount, false);
                        }
                        break;
                    }
                    case "!has money": {
                        if (yaml.contains(path + ".requirements." + name + ".amount") && yaml.isString(path + ".requirements." + name + ".amount")) {
                            String amount = yaml.getString(path + ".requirements." + name + ".amount");
                            requirement = new HasMoney(amount, true);
                        }
                        break;
                    }
                    case "has exp": {
                        if (yaml.contains(path + ".requirements." + name + ".amount") && yaml.isString(path + ".requirements." + name + ".amount")) {
                            if (yaml.contains(path + ".requirements." + name + ".level") && yaml.isBoolean(path + ".requirements." + name + ".level")) {
                                String amount = yaml.getString(path + ".requirements." + name + ".amount");
                                boolean level = yaml.getBoolean(path + ".requirements." + name + ".level");
                                requirement = new HasExp(amount, level, false);
                            }
                        }
                        break;
                    }
                    case "!has exp": {
                        if (yaml.contains(path + ".requirements." + name + ".amount") && yaml.isString(path + ".requirements." + name + ".amount")) {
                            if (yaml.contains(path + ".requirements." + name + ".level") && yaml.isBoolean(path + ".requirements." + name + ".level")) {
                                String amount = yaml.getString(path + ".requirements." + name + ".amount");
                                boolean level = yaml.getBoolean(path + ".requirements." + name + ".level");
                                requirement = new HasExp(amount, level, true);
                            }
                        }
                        break;
                    }
                    case "is near": {
                        if (yaml.contains(path + ".requirements." + name + ".location") && yaml.isString(path + ".requirements." + name + ".location")) {
                            if (yaml.contains(path + ".requirements." + name + ".distance") && yaml.isBoolean(path + ".requirements." + name + ".distance")) {
                                String locationString = yaml.getString(path + ".requirements." + name + ".location");
                                try {
                                    World world = Bukkit.getWorld(locationString.split(",")[0]);
                                    int x = Integer.parseInt(locationString.split(",")[1]);
                                    int y = Integer.parseInt(locationString.split(",")[2]);
                                    int z = Integer.parseInt(locationString.split(",")[3]);
                                    if (world != null) {
                                        Location location = new Location(world, x, y, z);
                                        String distance = yaml.getString(path + ".requirements." + name + ".distance");
                                        requirement = new IsNear(location, distance, false);
                                    } else {
                                        requirement = new IsNear(null, "0", false);
                                    }
                                } catch (NumberFormatException ignored) {
                                    requirement = new IsNear(null, "0", false);
                                }
                            }
                        }
                        break;
                    }
                    case "!is near": {
                        if (yaml.contains(path + ".requirements." + name + ".location") && yaml.isString(path + ".requirements." + name + ".location")) {
                            if (yaml.contains(path + ".requirements." + name + ".distance") && yaml.isBoolean(path + ".requirements." + name + ".distance")) {
                                String locationString = yaml.getString(path + ".requirements." + name + ".location");
                                try {
                                    World world = Bukkit.getWorld(locationString.split(",")[0]);
                                    int x = Integer.parseInt(locationString.split(",")[1]);
                                    int y = Integer.parseInt(locationString.split(",")[2]);
                                    int z = Integer.parseInt(locationString.split(",")[3]);
                                    if (world != null) {
                                        Location location = new Location(world, x, y, z);
                                        String distance = yaml.getString(path + ".requirements." + name + ".distance");
                                        requirement = new IsNear(location, distance, true);
                                    } else {
                                        requirement = new IsNear(null, "0", true);
                                    }
                                } catch (NumberFormatException ignored) {
                                    requirement = new IsNear(null, "0", true);
                                }
                            }
                        }
                        break;
                    }
                    case "string equals": {
                        if (yaml.contains(path + ".requirements." + name + ".input") && yaml.isString(path + ".requirements." + name + ".input")) {
                            if (yaml.contains(path + ".requirements." + name + ".output") && yaml.isString(path + ".requirements." + name + ".output")) {
                                String input = yaml.getString(path + ".requirements." + name + ".input");
                                String output = yaml.getString(path + ".requirements." + name + ".output");
                                requirement = new StringEquals(input, output, false);
                            }
                        }
                        break;
                    }
                    case "!string equals": {
                        if (yaml.contains(path + ".requirements." + name + ".input") && yaml.isString(path + ".requirements." + name + ".input")) {
                            if (yaml.contains(path + ".requirements." + name + ".output") && yaml.isString(path + ".requirements." + name + ".output")) {
                                String input = yaml.getString(path + ".requirements." + name + ".input");
                                String output = yaml.getString(path + ".requirements." + name + ".output");
                                requirement = new StringEquals(input, output, true);
                            }
                        }
                        break;
                    }
                    case "string equals ignorecase": {
                        if (yaml.contains(path + ".requirements." + name + ".input") && yaml.isString(path + ".requirements." + name + ".input")) {
                            if (yaml.contains(path + ".requirements." + name + ".output") && yaml.isString(path + ".requirements." + name + ".output")) {
                                String input = yaml.getString(path + ".requirements." + name + ".input");
                                String output = yaml.getString(path + ".requirements." + name + ".output");
                                requirement = new StringEqualsIgnorecase(input, output, false);
                            }
                        }
                        break;
                    }
                    case "!string equals ignorecase": {
                        if (yaml.contains(path + ".requirements." + name + ".input") && yaml.isString(path + ".requirements." + name + ".input")) {
                            if (yaml.contains(path + ".requirements." + name + ".output") && yaml.isString(path + ".requirements." + name + ".output")) {
                                String input = yaml.getString(path + ".requirements." + name + ".input");
                                String output = yaml.getString(path + ".requirements." + name + ".output");
                                requirement = new StringEqualsIgnorecase(input, output, true);
                            }
                        }
                        break;
                    }
                    case "string contains": {
                        if (yaml.contains(path + ".requirements." + name + ".input") && yaml.isString(path + ".requirements." + name + ".input")) {
                            if (yaml.contains(path + ".requirements." + name + ".output") && yaml.isString(path + ".requirements." + name + ".output")) {
                                String input = yaml.getString(path + ".requirements." + name + ".input");
                                String output = yaml.getString(path + ".requirements." + name + ".output");
                                requirement = new StringContains(input, output, false);
                            }
                        }
                        break;
                    }
                    case "!string contains": {
                        if (yaml.contains(path + ".requirements." + name + ".input") && yaml.isString(path + ".requirements." + name + ".input")) {
                            if (yaml.contains(path + ".requirements." + name + ".output") && yaml.isString(path + ".requirements." + name + ".output")) {
                                String input = yaml.getString(path + ".requirements." + name + ".input");
                                String output = yaml.getString(path + ".requirements." + name + ".output");
                                requirement = new StringContains(input, output, true);
                            }
                        }
                        break;
                    }
                    case "regex matches": {
                        if (yaml.contains(path + ".requirements." + name + ".input") && yaml.isString(path + ".requirements." + name + ".input")) {
                            if (yaml.contains(path + ".requirements." + name + ".regex") && yaml.isString(path + ".requirements." + name + ".regex")) {
                                String input = yaml.getString(path + ".requirements." + name + ".input");
                                String regex = yaml.getString(path + ".requirements." + name + ".regex");
                                requirement = new RegexMatches(input, regex, false);
                            }
                        }
                        break;
                    }
                    case "!regex matches": {
                        if (yaml.contains(path + ".requirements." + name + ".input") && yaml.isString(path + ".requirements." + name + ".input")) {
                            if (yaml.contains(path + ".requirements." + name + ".regex") && yaml.isString(path + ".requirements." + name + ".regex")) {
                                String input = yaml.getString(path + ".requirements." + name + ".input");
                                String regex = yaml.getString(path + ".requirements." + name + ".regex");
                                requirement = new RegexMatches(input, regex, true);
                            }
                        }
                        break;
                    }
                    case ">":
                    case ">=":
                    case "==":
                    case "<":
                    case "<=": {
                        if (yaml.contains(path + ".requirements." + name + ".input") && yaml.isString(path + ".requirements." + name + ".input")) {
                            if (yaml.contains(path + ".requirements." + name + ".output") && yaml.isString(path + ".requirements." + name + ".output")) {
                                String input = yaml.getString(path + ".requirements." + name + ".input");
                                String output = yaml.getString(path + ".requirements." + name + ".output");
                                requirement = new Comparators(type, input, output, false);
                            }
                        }
                        break;
                    }
                    case "!>":
                    case "!>=":
                    case "!==":
                    case "!<":
                    case "!<=": {
                        if (yaml.contains(path + ".requirements." + name + ".input") && yaml.isString(path + ".requirements." + name + ".input")) {
                            if (yaml.contains(path + ".requirements." + name + ".output") && yaml.isString(path + ".requirements." + name + ".output")) {
                                String input = yaml.getString(path + ".requirements." + name + ".input");
                                String output = yaml.getString(path + ".requirements." + name + ".output");
                                requirement = new Comparators(type, input, output, true);
                            }
                        }
                        break;
                    }
                }
                if (requirement != null) {
                    // success_commands
                    if (yaml.contains(path + ".requirements." + name + ".success_commands") && yaml.isList(path + ".requirements." + name + ".success_commands")) {
                        ActionManager actionManager = new ActionManager();
                        if (javaPlugin != null) {
                            actionManager.register(javaPlugin);
                        }
                        ActionModule actionModule = actionManager.loadActionList(yaml.getStringList(path + ".requirements." + name + ".success_commands"));
                        requirement.getSuccess_commands().addAll(actionModule.getActions());
                    }
                    // deny_commands
                    if (yaml.contains(path + ".requirements." + name + ".deny_commands") && yaml.isList(path + ".requirements." + name + ".deny_commands")) {
                        ActionManager actionManager = new ActionManager();
                        if (javaPlugin != null) {
                            actionManager.register(javaPlugin);
                        }
                        ActionModule actionModule = actionManager.loadActionList(yaml.getStringList(path + ".requirements." + name + ".deny_commands"));
                        requirement.getDeny_commands().addAll(actionModule.getActions());
                    }
                    // optional
                    if (yaml.contains(path + ".requirements." + name + ".optional") && yaml.isBoolean(path + ".requirements." + name + ".optional")) {
                        requirement.setOptional(yaml.getBoolean(path + ".requirement." + name + ".optional"));
                    }
                    // add requirement
                    requirement.setRequirementManager(this);
                    requirementModule.getRequirements().put(name, requirement);
                }
            }
        }
        // deny_commands
        if (yaml.contains(path + ".deny_commands") && yaml.isList(path + ".deny_commands")) {
            ActionManager actionManager = new ActionManager();
            if (javaPlugin != null) {
                actionManager.register(javaPlugin);
            }
            ActionModule actionModule = actionManager.loadActionList(yaml.getStringList(path + ".deny_commands"));
            requirementModule.getDeny_commands().addAll(actionModule.getActions());
        }
        return requirementModule;
    }



}