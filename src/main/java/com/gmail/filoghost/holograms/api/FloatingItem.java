package com.gmail.filoghost.holograms.api;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

@Deprecated
public abstract interface FloatingItem
{
  @Deprecated
  public abstract boolean update();
  
  @Deprecated
  public abstract void hide();
  
  @Deprecated
  public abstract void setItemStack(ItemStack paramItemStack);
  
  @Deprecated
  public abstract ItemStack getItemStack();
  
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
  public abstract void teleport(Location paramLocation);
  
  @Deprecated
  public abstract void setTouchHandler(ItemTouchHandler paramItemTouchHandler);
  
  @Deprecated
  public abstract ItemTouchHandler getTouchHandler();
  
  @Deprecated
  public abstract boolean hasTouchHandler();
  
  @Deprecated
  public abstract void setPickupHandler(PickupHandler paramPickupHandler);
  
  @Deprecated
  public abstract PickupHandler getPickupHandler();
  
  @Deprecated
  public abstract boolean hasPickupHandler();
  
  @Deprecated
  public abstract long getCreationTimestamp();
  
  @Deprecated
  public abstract void delete();
  
  @Deprecated
  public abstract boolean isDeleted();
}
