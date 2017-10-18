package com.gmail.filoghost.holograms.api.replacements;

import com.gmail.filoghost.holograms.api.FloatingItem;
import com.gmail.filoghost.holograms.api.ItemTouchHandler;
import com.gmail.filoghost.holograms.api.PickupHandler;
import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
import com.gmail.filoghost.holographicdisplays.object.line.CraftItemLine;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class FakeFloatingItem
  implements FloatingItem
{
  public CraftHologram hologram;
  private CraftItemLine mainLine;
  
  public FakeFloatingItem(CraftHologram hologram, ItemStack item)
  {
    this.hologram = hologram;
    this.mainLine = hologram.appendItemLine(item);
  }
  
  public boolean update()
  {
    return true;
  }
  
  public void hide() {}
  
  public void setItemStack(ItemStack itemstack)
  {
    this.mainLine.setItemStack(itemstack);
  }
  
  public ItemStack getItemStack()
  {
    return this.mainLine.getItemStack();
  }
  
  public Location getLocation()
  {
    return this.hologram.getLocation();
  }
  
  public double getX()
  {
    return this.hologram.getX();
  }
  
  public double getY()
  {
    return this.hologram.getY();
  }
  
  public double getZ()
  {
    return this.hologram.getZ();
  }
  
  public World getWorld()
  {
    return this.hologram.getWorld();
  }
  
  public void teleport(Location location)
  {
    this.hologram.teleport(location);
  }
  
  public void setTouchHandler(ItemTouchHandler handler)
  {
    if (handler != null) {
      this.mainLine.setTouchHandler(new OldItemTouchHandlerWrapper(this, handler));
    } else {
      this.mainLine.setTouchHandler(null);
    }
  }
  
  public ItemTouchHandler getTouchHandler()
  {
    return ((OldItemTouchHandlerWrapper)this.mainLine.getTouchHandler()).oldHandler;
  }
  
  public boolean hasTouchHandler()
  {
    return this.mainLine.getTouchHandler() != null;
  }
  
  public void setPickupHandler(PickupHandler handler)
  {
    if (handler != null) {
      this.mainLine.setPickupHandler(new OldPickupHandlerWrapper(this, handler));
    } else {
      this.mainLine.setPickupHandler(null);
    }
  }
  
  public PickupHandler getPickupHandler()
  {
    return ((OldPickupHandlerWrapper)this.mainLine.getPickupHandler()).oldHandler;
  }
  
  public boolean hasPickupHandler()
  {
    return this.mainLine.getPickupHandler() != null;
  }
  
  public long getCreationTimestamp()
  {
    return this.hologram.getCreationTimestamp();
  }
  
  public void delete()
  {
    this.hologram.delete();
  }
  
  public boolean isDeleted()
  {
    return this.hologram.isDeleted();
  }
}
