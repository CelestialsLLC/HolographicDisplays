package com.gmail.filoghost.holographicdisplays.nms.interfaces;

import com.gmail.filoghost.holographicdisplays.nms.interfaces.entity.*;
import com.gmail.filoghost.holographicdisplays.object.line.CraftHologramLine;
import com.gmail.filoghost.holographicdisplays.object.line.CraftItemLine;
import com.gmail.filoghost.holographicdisplays.object.line.CraftTouchSlimeLine;
import org.bukkit.inventory.ItemStack;

public interface NMSManager {

    // A method to register all the custom entities of the plugin, it may fail.
    void setup() throws Exception;

    NMSArmorStand spawnNMSArmorStand(
            org.bukkit.World world, double x, double y, double z, CraftHologramLine parentPiece);

    NMSHorse spawnNMSHorse(
            org.bukkit.World world, double x, double y, double z, CraftHologramLine parentPiece);

    NMSWitherSkull spawnNMSWitherSkull(
            org.bukkit.World bukkitWorld,
            double x,
            double y,
            double z,
            CraftHologramLine parentPiece);

    NMSItem spawnNMSItem(
            org.bukkit.World bukkitWorld,
            double x,
            double y,
            double z,
            CraftItemLine parentPiece,
            ItemStack stack);

    NMSSlime spawnNMSSlime(
            org.bukkit.World bukkitWorld,
            double x,
            double y,
            double z,
            CraftTouchSlimeLine parentPiece);

    boolean isNMSEntityBase(org.bukkit.entity.Entity bukkitEntity);

    NMSEntityBase getNMSEntityBase(org.bukkit.entity.Entity bukkitEntity);

    FancyMessage newFancyMessage(String text);

    boolean isUnloadUnsure(org.bukkit.Chunk bukkitChunk);
}
