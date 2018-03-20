package org.dice.rcp;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class DiceActivator implements BundleActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.dice.rcp"; //$NON-NLS-1$

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		DiceActivator.context = bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		DiceActivator.context = null;
	}

}
