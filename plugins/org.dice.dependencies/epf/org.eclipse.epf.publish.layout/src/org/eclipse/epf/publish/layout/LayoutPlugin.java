package org.eclipse.epf.publish.layout;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.epf.common.AbstractActivator;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class LayoutPlugin extends AbstractActivator {

	private static final String LAYOUT_PATH = "layout/"; //$NON-NLS-1$;

	public static final String LAYOUT_XSL_PATH = "layout/xsl/"; //$NON-NLS-1$;
	private static final String LAYOUT_CSS_PATH = "layout/css/"; //$NON-NLS-1$;

	public static final String LAYOUT_SCRIPTS_FOLDER = "scripts"; //$NON-NLS-1$;

	public static final String LAYOUT_SCRIPTS_PATH = "layout/scripts/"; //$NON-NLS-1$;
	
	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.epf.publish.layout";

	// The shared instance
	private static LayoutPlugin plugin;
	
	private String layoutPath, layoutXslPath, layoutCssPath;
	
	/**
	 * The constructor
	 */
	public LayoutPlugin() {
		super();
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		try {
			URL url = new URL(super.getInstallURL(), LAYOUT_PATH);
			layoutPath = FileLocator.resolve(url).getPath();

			url = new URL(super.getInstallURL(), LAYOUT_XSL_PATH);
			layoutXslPath = FileLocator.resolve(url).getPath();

			url = new URL(super.getInstallURL(), LAYOUT_CSS_PATH);
			layoutCssPath = FileLocator.resolve(url).getPath();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static LayoutPlugin getDefault() {
		return plugin;
	}
	
	public String getLayoutPath() {
		return layoutPath;
	}

	public String getLayoutXslPath() {
		return layoutXslPath;
	}

	public String getLayoutCssPath() {
		return layoutCssPath;
	}

}
