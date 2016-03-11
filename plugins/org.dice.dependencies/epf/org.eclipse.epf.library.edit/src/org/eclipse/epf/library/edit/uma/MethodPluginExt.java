package org.eclipse.epf.library.edit.uma;

import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPlugin;

public class MethodPluginExt extends MethodElementExt {
	
	private boolean wpStatesLoaded = false;
	private MethodConfiguration embeddedConfig;
	private boolean embeddedConfigLoaded = false;

	public MethodPluginExt(MethodPlugin plugin) {
		super(plugin);
	}	
	
	public boolean isWpStatesLoaded() {
		return wpStatesLoaded;
	}
	
	public void setWpStatesLoaded(boolean wpStatesLoaded) {
		this.wpStatesLoaded = wpStatesLoaded;
	}
	
	public MethodConfiguration getEmbeddedConfig() {
		return embeddedConfig;
	}

	public void setEmbeddedConfig(MethodConfiguration embeddedConfig) {
		this.embeddedConfig = embeddedConfig;
	}
	
	public boolean isEmbeddedConfigLoaded() {
		return embeddedConfigLoaded;
	}

	public void setEmbeddedConfigLoaded(boolean embeddedConfigLoaded) {
		this.embeddedConfigLoaded = embeddedConfigLoaded;
	}
}