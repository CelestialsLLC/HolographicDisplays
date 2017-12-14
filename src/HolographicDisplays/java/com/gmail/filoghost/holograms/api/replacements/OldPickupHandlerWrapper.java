package com.gmail.filoghost.holograms.api.replacements;

import com.gmail.filoghost.holograms.api.FloatingItem;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class OldPickupHandlerWrapper
        implements com.gmail.filoghost.holographicdisplays.api.handler.PickupHandler {
    public com.gmail.filoghost.holograms.api.PickupHandler oldHandler;
    private FloatingItem item;

    public OldPickupHandlerWrapper(
            FloatingItem item, com.gmail.filoghost.holograms.api.PickupHandler oldPickupHandler) {
        this.item = item;
        this.oldHandler = oldPickupHandler;
    }

    public void onPickup(Player player) {
        this.oldHandler.onPickup(this.item, player);
    }
}
