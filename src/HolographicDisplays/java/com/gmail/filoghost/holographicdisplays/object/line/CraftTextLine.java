package com.gmail.filoghost.holographicdisplays.object.line;

import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSCanMount;
import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSEntityBase;
import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.NMSNameable;
import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
import com.gmail.filoghost.holographicdisplays.placeholder.PlaceholdersManager;
import com.gmail.filoghost.holographicdisplays.util.MinecraftVersion;
import com.gmail.filoghost.holographicdisplays.util.Offsets;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class CraftTextLine extends CraftTouchableLine implements TextLine {

    private String text;

    private NMSNameable nmsNameble;

    // Legacy code for < 1.7, not used in 1.8 and greater
    private NMSEntityBase nmsSkullVehicle;

    public CraftTextLine(CraftHologram parent, String text) {
        super(0.23, parent);
        setText(text);
    }

    public CraftTextLine(CraftHologram parent, String string, boolean b) {
        super(0.23, parent);

        String[] parseConfig = string.split("|||, ");
        String parsedText = "";
        String parsedTouchHandler = parseConfig[1];

        char[] chars = string.toCharArray();
        for (int i = 0; i < string.length(); i++) {
            char c = chars[i];
            if (c == '|' && chars[i + 1] == '|' && chars[i + 1 + 1] == '|') break;

            parsedText += c;
        }

        setText(parsedText);

        if (!StringUtils.isBlank(parsedTouchHandler)) {
            String[] parseTouchHandler = parsedTouchHandler.split("|/| ");

            boolean showText = (parseTouchHandler[0].contains("showText") ? true : false);
            try {
                String arguments = parseTouchHandler[1];

                if (showText) setShowText(arguments);
                else setDispatchCommand(arguments);
            } catch (ArrayIndexOutOfBoundsException createdNow) {
                //                setShowText("Hello.");
            }
        }
    }

    public void setShowText(String arguments) {
        this.setTouchHandler(
                (Player whoClicked) ->
                        whoClicked.sendMessage(
                                ChatColor.translateAlternateColorCodes('&', arguments)));
    }

    public void setDispatchCommand(String arguments) {
        arguments = arguments.replace("/", "");
        arguments = arguments.replace("\"", "");

        String finalArguments = arguments;
        this.setTouchHandler((Player whoClicked) -> dispatch(whoClicked, finalArguments));
    }

    public void dispatch(Player whoClicked, String arguments) {
        Bukkit.dispatchCommand(whoClicked, arguments);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;

        if (nmsNameble != null) {
            if (text != null && !text.isEmpty()) {
                nmsNameble.setCustomNameNMS(text);
                if (getParent().isAllowPlaceholders()) {
                    PlaceholdersManager.trackIfNecessary(this, this.getParent());
                }
            } else {
                nmsNameble.setCustomNameNMS(""); // It will not appear
                if (getParent().isAllowPlaceholders()) {
                    PlaceholdersManager.untrack(this);
                }
            }
        }
    }

    public void setTouchHandler(TouchHandler touchHandler) {

        if (nmsNameble != null) {

            Location loc = nmsNameble.getBukkitEntityNMS().getLocation();
            super.setTouchHandler(
                    touchHandler,
                    loc.getWorld(),
                    loc.getX(),
                    loc.getY() - getTextOffset(),
                    loc.getZ());

        } else {
            super.setTouchHandler(touchHandler, null, 0, 0, 0);
        }
    }

    @Override
    public void spawn(World world, double x, double y, double z) {
        super.spawn(world, x, y, z);

        if (MinecraftVersion.isGreaterEqualThan(MinecraftVersion.v1_8)) {
            nmsNameble =
                    HolographicDisplays.getNMSManager()
                            .spawnNMSArmorStand(world, x, y + getTextOffset(), z, this);
        } else {
            nmsNameble =
                    HolographicDisplays.getNMSManager()
                            .spawnNMSHorse(world, x, y + Offsets.WITHER_SKULL_WITH_HORSE, z, this);
            nmsSkullVehicle =
                    HolographicDisplays.getNMSManager()
                            .spawnNMSWitherSkull(
                                    world, x, y + Offsets.WITHER_SKULL_WITH_HORSE, z, this);

            // In 1.7 it must be an instanceof NMSCanMount
            ((NMSCanMount) nmsNameble).setPassengerOfNMS(nmsSkullVehicle);
            nmsSkullVehicle.setLockTick(true);
        }

        if (text != null && !text.isEmpty()) {
            nmsNameble.setCustomNameNMS(text);
        }

        nmsNameble.setLockTick(true);
    }

    @Override
    public void despawn() {
        super.despawn();

        if (nmsSkullVehicle != null) {
            nmsSkullVehicle.killEntityNMS();
            nmsSkullVehicle = null;
        }

        if (nmsNameble != null) {
            nmsNameble.killEntityNMS();
            nmsNameble = null;
        }
    }

    @Override
    public void teleport(double x, double y, double z) {
        super.teleport(x, y, z);

        if (nmsSkullVehicle != null) {
            nmsSkullVehicle.setLocationNMS(x, y + Offsets.WITHER_SKULL_WITH_HORSE, z);
        }

        if (nmsNameble != null) {
            nmsNameble.setLocationNMS(x, y + getTextOffset(), z);
        }
    }

    @Override
    public int[] getEntitiesIDs() {
        if (isSpawned()) {
            if (nmsSkullVehicle != null) {
                if (touchSlime != null) {
                    return ArrayUtils.addAll(
                            new int[]{nmsNameble.getIdNMS(), nmsSkullVehicle.getIdNMS()},
                            touchSlime.getEntitiesIDs());
                } else {
                    return new int[]{nmsNameble.getIdNMS(), nmsSkullVehicle.getIdNMS()};
                }
            } else {
                if (touchSlime != null) {
                    return ArrayUtils.add(touchSlime.getEntitiesIDs(), nmsNameble.getIdNMS());
                } else {
                    return new int[]{nmsNameble.getIdNMS()};
                }
            }
        } else {
            return new int[0];
        }
    }

    public NMSNameable getNmsNameble() {
        return nmsNameble;
    }

    public NMSEntityBase getNmsSkullVehicle() {
        return nmsSkullVehicle;
    }

    private double getTextOffset() {
        if (MinecraftVersion.isGreaterEqualThan(MinecraftVersion.v1_9)) {
            return Offsets.ARMOR_STAND_ALONE_1_9;
        } else if (MinecraftVersion.isGreaterEqualThan(MinecraftVersion.v1_8)) {
            return Offsets.ARMOR_STAND_ALONE;
        } else {
            return Offsets.WITHER_SKULL_WITH_HORSE;
        }
    }

    @Override
    public String toString() {
        return "CraftTextLine [text=" + text + "]";
    }
}
