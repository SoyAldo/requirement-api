package org.kayteam.requirementapi.requirement;

import org.bukkit.entity.Player;
import org.kayteam.actionapi.action.Action;

import java.util.ArrayList;
import java.util.List;

public class Requirement {

    private RequirementManager requirementManager;
    private List<Action> success_commands;
    private List<Action> deny_commands;
    private boolean optional;

    public Requirement() {
        success_commands = new ArrayList<>();
        deny_commands = new ArrayList<>();
        optional = false;
    }

    public RequirementManager getRequirementManager() {
        return requirementManager;
    }

    public void setRequirementManager(RequirementManager requirementManager) {
        this.requirementManager = requirementManager;
    }

    public List<Action> getSuccess_commands() {
        return success_commands;
    }

    public void setSuccess_commands(List<Action> success_commands) {
        this.success_commands = success_commands;
    }

    public List<Action> getDeny_commands() {
        return deny_commands;
    }

    public void setDeny_commands(List<Action> deny_commands) {
        this.deny_commands = deny_commands;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public boolean runVerify(Player player) { return true; }

}