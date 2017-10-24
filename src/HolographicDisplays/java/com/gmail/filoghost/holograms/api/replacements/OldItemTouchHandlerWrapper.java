package com.gmail.filoghost.holograms.api.replacements;

import com.gmail.filoghost.holograms.api.FloatingItem;
import com.gmail.filoghost.holograms.api.ItemTouchHandler;
import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class OldItemTouchHandlerWrapper
        implements TouchHandler {
    public ItemTouchHandler oldHandler;
    private FloatingItem item;

    public OldItemTouchHandlerWrapper(FloatingItem item, ItemTouchHandler oldHandler) {
        this.item = item;
        this.oldHandler = oldHandler;
    }

    public void onTouch(Player player) {
        this.oldHandler.onTouch(this.item, player);
    }
}
