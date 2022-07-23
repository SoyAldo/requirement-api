package org.kayteam.requirementapi.requirement;

import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RequirementModule {

    private int minimum_requirements;
    private boolean stop_at_success;
    private final LinkedHashMap<String, Requirement> requirements;
    private final List<Action> deny_commands;

    public RequirementModule() {
        minimum_requirements = 0;
        stop_at_success = false;
        requirements = new LinkedHashMap<>();
        deny_commands = new ArrayList<>();
    }

    public int getMinimum_requirements() {
        return minimum_requirements;
    }

    public void setMinimum_requirements(int minimum_requirements) {
        this.minimum_requirements = minimum_requirements;
    }

    public boolean isStop_at_success() {
        return stop_at_success;
    }

    public void setStop_at_success(boolean stop_at_success) {
        this.stop_at_success = stop_at_success;
    }

    public LinkedHashMap<String, Requirement> getRequirements() {
        return requirements;
    }

    public List<Action> getDeny_commands() {
        return deny_commands;
    }

    public boolean runVerify(Player player) {
        boolean complete = true;
        int count = 0;
        for (Requirement requirement:requirements.values()) {
            if (requirement.runVerify(player)) {
                if (minimum_requirements > 0) {
                    count++;
                    if (count >= minimum_requirements) {
                        if (stop_at_success) {
                            break;
                        }
                    }
                }
            } else {
                complete = false;
                if (!requirement.getDeny_commands().isEmpty()) {
                    for (Action action:requirement.getDeny_commands()) {
                        action.runAction(player);
                    }
                } else if (!deny_commands.isEmpty()) {
                    for (Action action:deny_commands) {
                        action.runAction(player);
                    }
                }
                if (!requirement.isOptional()) {
                    break;
                }
            }
        }
        return complete;
    }

}