package com.gmail.filoghost.holographicdisplays.api.placeholder;

@FunctionalInterface
public interface PlaceholderReplacer {

    /**
     * Called to update a placeholder's replacement.
     *
     * @return the replacement
     */
    String update();

}
