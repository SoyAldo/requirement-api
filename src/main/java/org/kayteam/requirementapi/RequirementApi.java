package org.kayteam.requirementapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.requirementapi.utils.BrandSender;

public final class RequirementApi extends JavaPlugin {

    @Override
    public void onEnable() {
        BrandSender.sendVersionStatus(this, Bukkit.getConsoleSender(), "&aEnabled");
    }

    @Override
    public void onDisable() {
        BrandSender.sendVersionStatus(this, Bukkit.getConsoleSender(), "&bDisabled");
    }

}