package com.gmail.filoghost.holographicdisplays.commands.main;

import com.gmail.filoghost.holographicdisplays.exception.CommandException;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class HologramSubCommand {

    private String name;
    private String permission;
    private String[] aliases;

    public HologramSubCommand(String name) {
        this(name, new String[0]);
    }

    public HologramSubCommand(String name, String... aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public final boolean hasPermission(CommandSender sender) {
        if (permission == null) return true;
        return sender.hasPermission(permission);
    }

    public abstract String getPossibleArguments();

    public abstract int getMinimumArguments();

    public abstract void execute(CommandSender sender, String label, String[] args) throws CommandException;

    public abstract List<String> getTutorial();

    public abstract SubCommandType getType();

    public final boolean isValidTrigger(String name) {
        if (this.name.equalsIgnoreCase(name)) {
            return true;
        }

        if (aliases != null) {
            for (String alias : aliases) {
                if (alias.equalsIgnoreCase(name)) {
                    return true;
                }
            }
        }

        return false;
    }


    public enum SubCommandType {
        GENERIC, EDIT_LINES, HIDDEN
    }

}
