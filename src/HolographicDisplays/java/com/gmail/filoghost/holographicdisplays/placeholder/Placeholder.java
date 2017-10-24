package com.gmail.filoghost.holographicdisplays.placeholder;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacerInfo;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class Placeholder {

    // The plugin that owns this placeholder.
    private final Plugin owner;

    // The placeholder itself, something like {onlinePlayers}. Case sensitive!
    private final String textPlaceholder;

    // How many tenths of second between each refresh.
    private int tenthsToRefresh;

    // This is the current replacement for this placeholder.
    private String currentReplacement;

    private PlaceholderReplacer replacer = null;

    private PlaceholderReplacerInfo infoReplacer = null;

    private Map<Hologram, String> infoReplacerCache = new HashMap<>();
    private Map<Hologram, Long> infoReplacerNextUpdate = new HashMap<>();

    public Placeholder(Plugin owner, String textPlaceholder, double refreshRate, PlaceholderReplacer replacer) {
        this.owner = owner;
        this.textPlaceholder = textPlaceholder;
        this.tenthsToRefresh = refreshRate <= 0.1 ? 1 : (int) (refreshRate * 10.0);
        this.replacer = replacer;
        this.currentReplacement = "";
    }


    public Placeholder(Plugin owner, String textPlaceholder, double refreshRate, PlaceholderReplacerInfo replacer) {
        this.owner = owner;
        this.textPlaceholder = textPlaceholder;
        this.tenthsToRefresh = refreshRate <= 0.1 ? 1 : (int) (refreshRate * 10.0);
        this.infoReplacer = replacer;
        this.currentReplacement = "";
    }

    public Plugin getOwner() {
        return owner;
    }

    public int getTenthsToRefresh() {
        return tenthsToRefresh;
    }

    public void setTenthsToRefresh(int tenthsToRefresh) {
        this.tenthsToRefresh = tenthsToRefresh;
    }

    public String getTextPlaceholder() {
        return textPlaceholder;
    }

    public String getCurrentReplacement() {
        return currentReplacement;
    }

    public void setCurrentReplacement(String replacement) {
        this.currentReplacement = replacement != null ? replacement : "null";
    }

    public PlaceholderReplacer getReplacer() {
        return replacer;
    }

    public void setReplacer(PlaceholderReplacer replacer) {
        this.replacer = replacer;
    }

    public PlaceholderReplacerInfo getInfoReplacer() {
        return this.infoReplacer;
    }

    public void setInfoReplacer(PlaceholderReplacerInfo replacer) {
        this.infoReplacer = replacer;
    }

    public void update() {
        if (this.replacer != null) {
            setCurrentReplacement(replacer.update());
        }
    }

    public String update(Hologram hologram) {
        if (this.infoReplacer == null) {
            return null;
        }

        if (hologram == null) {
            return null;
        }

        if (!this.infoReplacerNextUpdate.containsKey(hologram) || this.infoReplacerNextUpdate.get(hologram) > System.currentTimeMillis()) {
            this.infoReplacerCache.put(hologram, this.infoReplacer.replace(hologram));
            this.infoReplacerNextUpdate.put(hologram, System.currentTimeMillis() + 30000); // Update in 30seconds
        }

        return this.infoReplacerCache.get(hologram);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Placeholder) {
            return ((Placeholder) obj).textPlaceholder.equals(this.textPlaceholder);
        }

        return false;
    }


    @Override
    public int hashCode() {
        return textPlaceholder.hashCode();
    }


}
