package com.gmail.filoghost.holograms.api;

import com.gmail.filoghost.holograms.api.replacements.FakeFloatingItem;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.object.BackendAPI;
import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
import com.gmail.filoghost.holographicdisplays.object.PluginHologram;
import com.gmail.filoghost.holographicdisplays.object.PluginHologramManager;
import com.gmail.filoghost.holographicdisplays.util.Utils;
import com.gmail.filoghost.holographicdisplays.util.Validator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class HolographicDisplaysAPI {
    @Deprecated
    public static Hologram createHologram(Plugin plugin, Location source, String... lines) {
        CraftHologram hologram = (CraftHologram) BackendAPI.createHologram(plugin, source);
        String[] arrayOfString;
        int j = (arrayOfString = lines).length;
        for (int i = 0; i < j; i++) {
            String line = arrayOfString[i];
            hologram.appendTextLine(line);
        }
        return hologram;
    }

    @Deprecated
    public static FloatingItem createFloatingItem(
            Plugin plugin, Location source, ItemStack itemstack) {
        Validator.notNull(itemstack, "itemstack");
        Validator.isTrue(itemstack.getType() != Material.AIR, "itemstack cannot be AIR");

        CraftHologram hologram = (CraftHologram) BackendAPI.createHologram(plugin, source);
        hologram.appendItemLine(itemstack);
        return new FakeFloatingItem(hologram, itemstack);
    }

    @Deprecated
    public static Hologram createIndividualHologram(
            Plugin plugin, Location source, Player whoCanSee, String... lines) {
        List<Player> whoCanSeeList = new ArrayList<>();
        whoCanSeeList.add(whoCanSee);
        return createIndividualHologram(plugin, source, whoCanSeeList, lines);
    }

    @Deprecated
    public static Hologram createIndividualHologram(
            Plugin plugin, Location source, List<Player> whoCanSee, String... lines) {
        Validator.notNull(plugin, "plugin");
        Validator.notNull(source, "source");
        Validator.notNull(source.getWorld(), "source's world");

        CraftHologram hologram = (CraftHologram) BackendAPI.createHologram(plugin, source);

        hologram.getVisibilityManager().setVisibleByDefault(false);
        if (whoCanSee != null) {
            for (Player player : whoCanSee) {
                hologram.getVisibilityManager().showTo(player);
            }
        }
        String[] arrayOfString;
        int j = (arrayOfString = lines).length;
        for (int i = 0; i < j; i++) {
            String line = arrayOfString[i];
            hologram.appendTextLine(line);
        }
        return hologram;
    }

    @Deprecated
    public static FloatingItem createIndividualFloatingItem(
            Plugin plugin, Location source, Player whoCanSee, ItemStack itemstack) {
        List<Player> whoCanSeeList = new ArrayList<>();
        whoCanSeeList.add(whoCanSee);
        return createIndividualFloatingItem(plugin, source, whoCanSeeList, itemstack);
    }

    @Deprecated
    public static FloatingItem createIndividualFloatingItem(
            Plugin plugin, Location source, List<Player> whoCanSee, ItemStack itemstack) {
        Validator.notNull(plugin, "plugin cannot be null");
        Validator.notNull(source, "source cannot be null");
        Validator.notNull(source.getWorld(), "source's world cannot be null");
        Validator.notNull(itemstack, "itemstack cannot be null");
        Validator.isTrue(itemstack.getType() != Material.AIR, "itemstack cannot be AIR");

        CraftHologram hologram = (CraftHologram) BackendAPI.createHologram(plugin, source);
        hologram.appendItemLine(itemstack);

        hologram.getVisibilityManager().setVisibleByDefault(false);
        if (whoCanSee != null) {
            for (Player player : whoCanSee) {
                hologram.getVisibilityManager().showTo(player);
            }
        }
        return new FakeFloatingItem(hologram, itemstack);
    }

    @Deprecated
    public static Hologram[] getHolograms(Plugin plugin) {
        Validator.notNull(plugin, "plugin cannot be null");

        List<Hologram> pluginHolograms = Utils.newList();
        for (PluginHologram pluginHologram : PluginHologramManager.getHolograms()) {
            if (pluginHologram.getOwner().equals(plugin)) {
                pluginHolograms.add(pluginHologram);
            }
        }
        return pluginHolograms.toArray(new Hologram[0]);
    }

    @Deprecated
    public static FloatingItem[] getFloatingItems(Plugin plugin) {
        Validator.notNull(plugin, "plugin cannot be null");
        return new FloatingItem[0];
    }

    @Deprecated
    public static boolean isHologramEntity(Entity bukkitEntity) {
        return HologramsAPI.isHologramEntity(bukkitEntity);
    }
}
