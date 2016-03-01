package org.eclipse.epf.library.edit;

import org.eclipse.epf.uma.MethodConfiguration;

public class ContextIFilter implements IFilter {

	private Object context;

	public boolean accept(Object obj) {
		return false;
	}

	public Object getContext() {
		return context;
	}
	
	public void setContext(Object context) {
		this.context = context;
	}
	
	public MethodConfiguration getMethodConfiguration() {
		return context instanceof MethodConfiguration ? (MethodConfiguration) context : null;
	}
	
}
