package com.gmail.filoghost.holographicdisplays.commands.main.subs;

import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import com.gmail.filoghost.holographicdisplays.commands.Colors;
import com.gmail.filoghost.holographicdisplays.commands.CommandValidator;
import com.gmail.filoghost.holographicdisplays.commands.Strings;
import com.gmail.filoghost.holographicdisplays.commands.main.HologramSubCommand;
import com.gmail.filoghost.holographicdisplays.disk.HologramDatabase;
import com.gmail.filoghost.holographicdisplays.exception.CommandException;
import com.gmail.filoghost.holographicdisplays.object.NamedHologram;
import com.gmail.filoghost.holographicdisplays.object.NamedHologramManager;
import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * Created by TheLegion on 13.12.2017 (December) 21:26
 */
public class TouchHandlerCommand extends HologramSubCommand {

    public TouchHandlerCommand() {
        super("touchhandler");
        setPermission(Strings.BASE_PERM + "touchhandler");
    }

    /**
     * @return arguments <>hologramName</> - [string] the name of hologram to edit <>editType</> -
     * [string] the name of EditType (ADD, EDIT, CLEAR, CLEARALL) <>lineNumber</> - [integer]
     * the line to change touchhandler <>showText</> - [booleam] if true showing given text,
     * else dispatching given command by a event.getEntity() <>arguments</> - [String[]] - text
     * or command
     */
    @Override
    public String getPossibleArguments() {
        return "<hologramName> <editType (add, edit, remove, removeall)> <lineNumber> <showText> <arguments>";
    }

    @Override
    public int getMinimumArguments() {
        return 0; // TheLegion's command style, so we don't need check arguments by HoloSub
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) throws CommandException {
        if (args.length < 1)
            throw new CommandException(
                    "Enter a "
                            + Colors.PRIMARY
                            + "<holo>"
                            + Colors.ERROR
                            + " name to add/edit Touch Handler.");

        NamedHologram holo = NamedHologramManager.getHologram(args[0].toLowerCase());
        CommandValidator.notNull(holo, Strings.noSuchHologram(args[0].toLowerCase()));

        if (args.length < 2)
            throw new CommandException("Enter one of types: " + "\n" + getEditTypes());

        EditType editType;
        try {
            editType = EditType.valueOf(args[1].toUpperCase());

            if (editType == null)
                throw new CommandException(
                        "No such EditType "
                                + Colors.PRIMARY_SHADOW
                                + args[1]
                                + "\n"
                                + Colors.ERROR
                                + "Try one of those: "
                                + getEditTypes());
        } catch (Exception local) {
            throw new CommandException("No such EditType " + Colors.PRIMARY_SHADOW + args[1]);
        }

        if (!editType.equals(EditType.CLEARALL)) {
            if (args.length < 3)
                throw new CommandException(
                        "Enter a "
                                + Colors.PRIMARY
                                + "<line number>"
                                + Colors.ERROR
                                + " to edit Touch Handler on given line.");

            Integer givenNum;
            try {
                givenNum = Integer.parseInt(args[2]);
            } catch (NumberFormatException local) {
                throw new CommandException(
                        Colors.PRIMARY_SHADOW
                                + args[2]
                                + Colors.ERROR
                                + " isn't an Integer. Type sth like 2 or 3, or 4...");
            }

            if (givenNum > 0) givenNum--;
            CraftHologramLine line = holo.getLine(givenNum);
            if (line == null)
                throw new CommandException(
                        "No such line with number " + Colors.PRIMARY_SHADOW + args[1]);

            if (editType.equals(EditType.CLEAR)) {
                clearTouchHandler(line);
                line.SET_TOUCH_HANDLER("|||, ");

                HologramDatabase.saveHologram(holo);
                HologramDatabase.trySaveToDisk();

                throw new CommandException(
                        Colors.PRIMARY_SHADOW
                                + "Successes deleted a TouchHandler from line with number "
                                + givenNum);
            }

            if (args.length < 4)
                throw new CommandException(
                        "If you want to show any text to interactor type 'true', if you want to dispatch any command by an interactor type 'false'");

            String bool = args[3];
            bool = bool.replace("yes", "true");
            bool = bool.replace("no", "false");
            bool = bool.replace("екгу", "true");
            bool = bool.replace("нуы", "true");
            bool = bool.replace("афдыу", "false");
            bool = bool.replace("тщ", "false");
            bool = bool.toLowerCase();

            if (!bool.equalsIgnoreCase("true") && !bool.equalsIgnoreCase("false"))
                throw new CommandException(
                        Colors.PRIMARY_SHADOW
                                + " unknown type "
                                + bool
                                + Colors.ERROR
                                + ".\n"
                                + "If you want to show any text to interactor type 'true', if you want to dispatch any command by an interactor type 'false'");
            Boolean showText = Boolean.valueOf(bool);

            if (args.length < 5)
                throw new CommandException(
                        "Enter any argumentS, which you wanna "
                                + Colors.PRIMARY
                                + (showText
                                ? "show text to player, while he will interact."
                                + " For example: Heyyooo, this is a touchnahdler's text"
                                : "dispatch any server command by an interactor (wuthout any '/', if command has arguments enter it."
                                + " For example: tell TheLegion heyoo, my dear friend)"));

            StringBuilder arguments = new StringBuilder();
            for (int i = 4; i < args.length; i++)
                arguments.append(args[i]).append(i == args.length - 1 ? "" : " ");

            if (showText) setShowText(line, arguments.toString());
            else setDispatchCommand(line, arguments.toString());

            String th =
                    "|||"
                            + (showText ? "showText" : "dispatchCommand")
                            + "|/| "
                            + arguments.toString();
            line.SET_TOUCH_HANDLER(th);

            HologramDatabase.saveHologram(holo);
            HologramDatabase.trySaveToDisk();

            throw new CommandException(
                    Colors.PRIMARY_SHADOW
                            + "Successes "
                            + (editType.equals(EditType.EDIT) ? "edited" : "added")
                            + " a TouchHandler: "
                            + (showText
                            ? "showText, "
                            : "dispatchCommand, " + arguments.toString()));
        } else {
            for (CraftHologramLine line : holo.getLinesUnsafe()) {
                clearTouchHandler(line);
                line.SET_TOUCH_HANDLER("|||, ");
            }

            HologramDatabase.saveHologram(holo);
            HologramDatabase.trySaveToDisk();

            throw new CommandException(
                    Colors.PRIMARY_SHADOW
                            + "Successes deleted a TouchHandler from all lines of hologram.");
        }
    }

    public void clearTouchHandler(CraftHologramLine line) {
        if (line instanceof TextLine) {
            TextLine text = (TextLine) line;
            text.setTouchHandler((Player whoClicked) -> whoClicked.closeInventory());
        } else if (line instanceof ItemLine) {
            ItemLine item = (ItemLine) line;
            item.setTouchHandler((Player whoClicked) -> whoClicked.closeInventory());
        }
    }

    public void setShowText(CraftHologramLine line, String arguments) {
        if (line instanceof TextLine) {
            TextLine text = (TextLine) line;
            text.setTouchHandler((Player whoClicked) -> whoClicked.sendMessage(ChatColor.translateAlternateColorCodes('&', arguments)));
        } else if (line instanceof ItemLine) {
            ItemLine item = (ItemLine) line;
            item.setTouchHandler((Player whoClicked) -> whoClicked.sendMessage(ChatColor.translateAlternateColorCodes('&', arguments)));
        }
    }

    public void setDispatchCommand(CraftHologramLine line, String arguments) {
        arguments = arguments.replace("/", "");
        arguments = arguments.replace("\"", "");

        if (line instanceof TextLine) {
            TextLine text = (TextLine) line;
            String finalArguments = arguments;

            text.setTouchHandler((Player whoClicked) -> dispatch(whoClicked, finalArguments));
        } else if (line instanceof ItemLine) {
            ItemLine item = (ItemLine) line;
            String finalArguments1 = arguments;

            item.setTouchHandler((Player whoClicked) -> dispatch(whoClicked, finalArguments1));
        }
    }

    public void dispatch(Player whoClicked, String arguments) {
        Bukkit.dispatchCommand(whoClicked, arguments);
    }

    @Override
    public List<String> getTutorial() {
        return Collections.singletonList(
                "Adds/Edits a Touch Handler to line to an existing hologram.");
    }

    @Override
    public HologramSubCommand.SubCommandType getType() {
        return SubCommandType.GENERIC;
    }

    public String getEditTypes() {
        String types = "";

        for (EditType type : EditType.values())
            types +=
                    Colors.SECONDARY_SHADOW
                            + type.getName()
                            + " | "
                            + Colors.SECONDARY
                            + type.getDescription();
        return types;
    }

    public enum EditType {
        ADD(
                "add",
                "Adds A Touch Handler to a given line. If TouchHandler exists TH will be replaces with new."),
        EDIT("edit", "Edits a Touch Handler to a given line."),
        CLEAR("clear", "Clears a Touch Handler to a given line."),
        CLEARALL("clearall", "Clears all TouchHandlers (every line)");

        private final String name;
        private final String description;

        EditType(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return this.name;
        }

        public String getDescription() {
            return this.description;
        }
    }
}
