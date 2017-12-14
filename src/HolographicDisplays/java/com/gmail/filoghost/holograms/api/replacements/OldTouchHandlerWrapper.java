package com.gmail.filoghost.holograms.api.replacements;

import com.gmail.filoghost.holographicdisplays.object.CraftHologram;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class OldTouchHandlerWrapper
        implements com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler {
    public com.gmail.filoghost.holograms.api.TouchHandler oldHandler;
    private CraftHologram hologram;

    public OldTouchHandlerWrapper(
            CraftHologram hologram, com.gmail.filoghost.holograms.api.TouchHandler oldHandler) {
        this.hologram = hologram;
        this.oldHandler = oldHandler;
    }

    public void onTouch(Player player) {
        this.oldHandler.onTouch(this.hologram, player);
    }
}
