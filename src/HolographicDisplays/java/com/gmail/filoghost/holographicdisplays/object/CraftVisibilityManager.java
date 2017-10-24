package com.gmail.filoghost.holographicdisplays.object;

import com.gmail.filoghost.holographicdisplays.HolographicDisplays;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;
import com.gmail.filoghost.holographicdisplays.util.Validator;
import com.gmail.filoghost.holographicdisplays.util.VersionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CraftVisibilityManager implements VisibilityManager {

    private static final int VISIBILITY_DISTANCE_SQUARED = 64 * 64;
    private final CraftHologram hologram;
    private Map<String, Boolean> playersVisibilityMap;
    private boolean visibleByDefault;

    public CraftVisibilityManager(CraftHologram hologram) {
        Validator.notNull(hologram, "hologram");
        this.hologram = hologram;
        this.visibleByDefault = true;
    }

    private static void sendCreatePacketIfNear(Player player, CraftHologram hologram) {
        if (HolographicDisplays.hasProtocolLibHook() && isNear(player, hologram)) {
            HolographicDisplays.getProtocolLibHook().sendCreateEntitiesPacket(player, hologram);
        }
    }

    private static void sendDestroyPacketIfNear(Player player, CraftHologram hologram) {
        if (HolographicDisplays.hasProtocolLibHook() && isNear(player, hologram)) {
            HolographicDisplays.getProtocolLibHook().sendDestroyEntitiesPacket(player, hologram);
        }
    }

    private static boolean isNear(Player player, CraftHologram hologram) {
        return player.isOnline() && player.getWorld().equals(hologram.getWorld()) && player.getLocation().distanceSquared(hologram.getLocation()) < VISIBILITY_DISTANCE_SQUARED;
    }

    @Override
    public boolean isVisibleByDefault() {
        return visibleByDefault;
    }

    @Override
    public void setVisibleByDefault(boolean visibleByDefault) {
        if (this.visibleByDefault != visibleByDefault) {

            boolean oldVisibleByDefault = this.visibleByDefault;
            this.visibleByDefault = visibleByDefault;

            for (Player player : VersionUtils.getOnlinePlayers()) {

                if (playersVisibilityMap != null && playersVisibilityMap.containsKey(player.getName().toLowerCase())) {
                    // Has a specific value set
                    continue;
                }

                if (oldVisibleByDefault) {
                    // If previously was visible, now is NOT visible by default, because the value has changed
                    sendDestroyPacketIfNear(player, hologram);
                } else {
                    // Opposite case
                    sendCreatePacketIfNear(player, hologram);
                }
            }
        }
    }

    @Override
    public void showTo(Player player) {
        Validator.notNull(player, "player");

        boolean wasVisible = isVisibleTo(player);

        if (playersVisibilityMap == null) {
            // Lazy initialization
            playersVisibilityMap = new ConcurrentHashMap<>();
        }

        playersVisibilityMap.put(player.getName().toLowerCase(), true);

        if (!wasVisible) {
            sendCreatePacketIfNear(player, hologram);
        }
    }

    @Override
    public void hideTo(Player player) {
        Validator.notNull(player, "player");

        boolean wasVisible = isVisibleTo(player);

        if (playersVisibilityMap == null) {
            // Lazy initialization
            playersVisibilityMap = new ConcurrentHashMap<>();
        }

        playersVisibilityMap.put(player.getName().toLowerCase(), false);

        if (wasVisible) {
            sendDestroyPacketIfNear(player, hologram);
        }
    }

    @Override
    public boolean isVisibleTo(Player player) {
        Validator.notNull(player, "player");

        if (playersVisibilityMap != null) {

            Boolean value = playersVisibilityMap.get(player.getName().toLowerCase());
            if (value != null) {
                return value;
            }
        }

        return visibleByDefault;
    }

    @Override
    public void resetVisibility(Player player) {
        Validator.notNull(player, "player");

        if (playersVisibilityMap == null) {
            return;
        }

        boolean wasVisible = isVisibleTo(player);

        playersVisibilityMap.remove(player.getName().toLowerCase());

        if (visibleByDefault && !wasVisible) {
            sendCreatePacketIfNear(player, hologram);

        } else if (!visibleByDefault && wasVisible) {
            sendDestroyPacketIfNear(player, hologram);
        }
    }

    @Override
    public void resetVisibilityAll() {
        if (playersVisibilityMap != null) {

            // We need to refresh all the players
            Set<String> playerNames = new HashSet<>(playersVisibilityMap.keySet());

            for (String playerName : playerNames) {
                Player onlinePlayer = Bukkit.getPlayerExact(playerName);
                if (onlinePlayer != null) {
                    resetVisibility(onlinePlayer);
                }
            }

            playersVisibilityMap.clear();
            playersVisibilityMap = null;
        }
    }

    @Override
    public String toString() {
        return "CraftVisibilityManager [playersMap=" + playersVisibilityMap + ", visibleByDefault=" + visibleByDefault + "]";
    }

}
