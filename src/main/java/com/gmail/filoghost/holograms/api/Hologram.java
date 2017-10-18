package com.gmail.filoghost.holograms.api;

import org.bukkit.Location;
import org.bukkit.World;

@Deprecated
public abstract interface Hologram
{
  @Deprecated
  public abstract boolean update();
  
  @Deprecated
  public abstract void hide();
  
  @Deprecated
  public abstract void addLine(String paramString);
  
  @Deprecated
  public abstract void removeLine(int paramInt);
  
  @Deprecated
  public abstract void setLine(int paramInt, String paramString);
  
  @Deprecated
  public abstract void insertLine(int paramInt, String paramString);
  
  @Deprecated
  public abstract String[] getLines();
  
  @Deprecated
  public abstract int getLinesLength();
  
  @Deprecated
  public abstract void clearLines();
  
  @Deprecated
  public abstract Location getLocation();
  
  @Deprecated
  public abstract double getX();
  
  @Deprecated
  public abstract double getY();
  
  @Deprecated
  public abstract double getZ();
  
  @Deprecated
  public abstract World getWorld();
  
  @Deprecated
  public abstract void setLocation(Location paramLocation);
  
  @Deprecated
  public abstract void teleport(Location paramLocation);
  
  @Deprecated
  public abstract void setTouchHandler(TouchHandler paramTouchHandler);
  
  @Deprecated
  public abstract TouchHandler getTouchHandler();
  
  @Deprecated
  public abstract boolean hasTouchHandler();
  
  @Deprecated
  public abstract long getCreationTimestamp();
  
  @Deprecated
  public abstract void delete();
  
  @Deprecated
  public abstract boolean isDeleted();
}
