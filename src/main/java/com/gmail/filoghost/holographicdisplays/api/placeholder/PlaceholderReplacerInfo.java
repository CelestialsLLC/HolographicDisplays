package com.gmail.filoghost.holographicdisplays.api.placeholder;

import com.gmail.filoghost.holographicdisplays.api.Hologram;

@FunctionalInterface
public interface PlaceholderReplacerInfo {

	/**
	 * Called to update a placeholder's replacement.
	 * @param hologram The hologram being updated.
	 * @return the replacement
	 */
	public String replace(Hologram hologram);
	
}
