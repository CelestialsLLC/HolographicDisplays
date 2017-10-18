package com.gmail.filoghost.holographicdisplays.nms.v1_12_R1;

import java.util.Collection;

import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftSlime;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

public class CraftNMSSlime extends CraftSlime {

	public CraftNMSSlime(CraftServer server, EntityNMSSlime entity) {
		super(server, entity);
	}
	
	// Disallow all the bukkit methods.
	
	@Override
	public void remove() {
		// Cannot be removed, this is the most important to override.
	}
	
	// Methods from LivingEntity class
	@Override public boolean addPotionEffect(PotionEffect effect) { return false; }
	@Override public boolean addPotionEffect(PotionEffect effect, boolean param) { return false; }
	@Override public boolean addPotionEffects(Collection<PotionEffect> effects) { return false; }
	@Override public void setRemoveWhenFarAway(boolean remove) { }
	@Override public void setAI(boolean ai) { }
	@Override public void setCanPickupItems(boolean pickup) { }
	@Override public void setCollidable(boolean collidable) { }
	@Override public void setGliding(boolean gliding) {	}
	@Override public boolean setLeashHolder(Entity holder) { return false; }

	// Methods from Entity
	@Override public void setVelocity(Vector vel) { }
	@Override public boolean teleport(Location loc) { return false; }
	@Override public boolean teleport(Entity entity) { return false; }
	@Override public boolean teleport(Location loc, TeleportCause cause) { return false; }
	@Override public boolean teleport(Entity entity, TeleportCause cause) { return false; }
	@Override public void setFireTicks(int ticks) { }
	@Override public boolean setPassenger(Entity entity) { return false; }
	@Override public boolean eject() { return false; }
	@Override public boolean leaveVehicle() { return false; }
	@Override public void playEffect(EntityEffect effect) { }
	@Override public void setCustomName(String name) { }
	@Override public void setCustomNameVisible(boolean flag) { }
	@Override public void setGlowing(boolean flag) { }
	@Override public void setGravity(boolean gravity) { }
	@Override public void setInvulnerable(boolean flag) { }
	@Override public void setMomentum(Vector value) { }
	@Override public void setSilent(boolean flag) { }
	@Override public void setTicksLived(int value) { }

	// Methods from Slime
	@Override public void setSize(int size) { }
	
}
