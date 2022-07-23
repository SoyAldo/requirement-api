package org.kayteam.requirementapi.requirement.requirements;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.kayteam.requirementapi.requirement.Requirement;

public class IsNear extends Requirement {

    private final Location location;
    private final String distance;
    private final boolean invert;

    public IsNear(Location location, String distance, boolean invert) {
        this.location = location;
        this.distance = distance;
        this.invert = invert;
    }


    public Location getLocation() {
        return location;
    }

    public String getDistance() {
        return distance;
    }

    public boolean isInvert() {
        return invert;
    }

    @Override
    public boolean runVerify(Player player) {
        if (location != null) {
            if (player.getWorld().getName().equals(location.getWorld().getName())) {
                String distanceParsed = "";
                if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
                    distanceParsed = PlaceholderAPI.setPlaceholders(player, distance);
                }
                try {
                    double distanceNumber = Double.parseDouble(distanceParsed);
                    if (invert) {
                        return !(player.getLocation().distance(this.location) < distanceNumber);
                    } else {
                        return player.getLocation().distance(this.location) < distanceNumber;
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
        return false;
    }

}