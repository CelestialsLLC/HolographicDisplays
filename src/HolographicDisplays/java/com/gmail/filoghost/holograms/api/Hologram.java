package com.gmail.filoghost.holograms.api;

import org.bukkit.Location;
import org.bukkit.World;

@Deprecated
public interface Hologram {
    @Deprecated
    boolean update();

    @Deprecated
    void hide();

    @Deprecated
    void addLine(String paramString);

    @Deprecated
    void removeLine(int paramInt);

    @Deprecated
    void setLine(int paramInt, String paramString);

    @Deprecated
    void insertLine(int paramInt, String paramString);

    @Deprecated
    String[] getLines();

    @Deprecated
    int getLinesLength();

    @Deprecated
    void clearLines();

    @Deprecated
    Location getLocation();

    @Deprecated
    void setLocation(Location paramLocation);

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
    TouchHandler getTouchHandler();

    @Deprecated
    void setTouchHandler(TouchHandler paramTouchHandler);

    @Deprecated
    boolean hasTouchHandler();

    @Deprecated
    long getCreationTimestamp();

    @Deprecated
    void delete();

    @Deprecated
    boolean isDeleted();
}
