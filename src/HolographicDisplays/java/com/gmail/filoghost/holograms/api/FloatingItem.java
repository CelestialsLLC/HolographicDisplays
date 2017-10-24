package com.gmail.filoghost.holograms.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

@Deprecated
public interface FloatingItem {
    @Deprecated
    boolean update();

    @Deprecated
    void hide();

    @Deprecated
    ItemStack getItemStack();

    @Deprecated
    void setItemStack(ItemStack paramItemStack);

    @Deprecated
    Location getLocation();

    @Deprecated
    double getX();

    @Deprecated
    double getY();

    @Deprecated
    double getZ();

    @Deprecated
    World getWorld();

    @Deprecated
    void teleport(Location paramLocation);

    @Deprecated
    ItemTouchHandler getTouchHandler();

    @Deprecated
    void setTouchHandler(ItemTouchHandler paramItemTouchHandler);

    @Deprecated
    boolean hasTouchHandler();

    @Deprecated
    PickupHandler getPickupHandler();

    @Deprecated
    void setPickupHandler(PickupHandler paramPickupHandler);

    @Deprecated
    boolean hasPickupHandler();

    @Deprecated
    long getCreationTimestamp();

    @Deprecated
    void delete();

    @Deprecated
    boolean isDeleted();
}
