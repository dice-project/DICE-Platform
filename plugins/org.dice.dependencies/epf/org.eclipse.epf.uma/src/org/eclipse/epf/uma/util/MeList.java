package org.eclipse.epf.uma.util;

import java.util.ArrayList;
import java.util.List;

public class MeList extends ArrayList {
	
	private boolean oFeatureHandled = false;

	private boolean hasUnresolved = false;

	public MeList() {
		super();
	}
	
	public MeList(List list) {
		super(list);
	}	
	
	public boolean isHasUnresolved() {
		return hasUnresolved;
	}

	public void setHasUnresolved(boolean hasUnresolved) {
		this.hasUnresolved = hasUnresolved;
	} 
	
	public boolean isOFeatureHandled() {
		return oFeatureHandled;
	}

	public void setOFeatureHandled(boolean oFeatureHandled) {
		this.oFeatureHandled = oFeatureHandled;
	}
	
}
