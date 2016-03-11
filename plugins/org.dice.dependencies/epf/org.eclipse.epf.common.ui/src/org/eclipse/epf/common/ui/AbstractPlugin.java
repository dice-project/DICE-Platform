//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.common.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.IActivator;
import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.I18nUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The abstract base class for all EPF plug-ins.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @since 1.0
 */
public abstract class AbstractPlugin extends AbstractUIPlugin implements IActivator {


	// The relative path to the icons.
	private static final String ICON_PATH = "icons/"; //$NON-NLS-1$;

	// The logger hash map.
	private static Map<String, Logger> loggers = new HashMap<String, Logger>();

	// The message dialog hash map.
	private static Map<String, MsgDialog> msgDialogs = new HashMap<String, MsgDialog>();

	// The shared image hash map.
	private static Map<String, Image> sharedImages = new HashMap<String, Image>();

	// The resource bundle for this plug-in.
	private ResourceBundle resourceBundle;

	// This plug-in ID.
	private String pluginId;

	// The plug-in install URL.
	private URL installURL;

	// The plug-in install path.
	private String installPath;

	// The plug-in icon URL.
	private URL iconURL;

	// The profiling flag.
	private boolean profiling;
	
	private Map<String, Boolean> debugMap = new HashMap<String, Boolean>();

	/**
	 * Default constructor.
	 */
	public AbstractPlugin() {
		super();
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);

		init(context);

		// set the 
		if (isDebugging()) {
			getLogger().logInfo("Started " + pluginId); //$NON-NLS-1$			
		}
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		// Free the shared images.
		for (Iterator images = sharedImages.values().iterator(); images
				.hasNext();) {
			Image image = (Image) images.next();
			if (image != null && !image.isDisposed()) {
				image.dispose();
			}
		}

		super.stop(context);

		if (isDebugging()) {
			getLogger().logInfo("Stopped " + pluginId); //$NON-NLS-1$
		}
	}

	/**
	 * Initializes this plug-in.
	 * 
	 * @param context
	 *            The bundle context.
	 */
	protected void init(BundleContext context) throws Exception {
		// Get the bundle for this plug-in.
		Bundle bundle = getBundle();

		// Get the resouce bundle for this plug-in.
		resourceBundle = Platform.getResourceBundle(bundle);

		// Get the ID for this plug-in.
		pluginId = bundle.getSymbolicName();

		if (isDebugging()) {
			getLogger().logInfo("Initializing " + pluginId); //$NON-NLS-1$		
		}

		// Get the install path of this plug-in.
		installURL = bundle.getEntry("/"); //$NON-NLS-1$

		try {
			installPath = FileLocator.resolve(installURL).getPath();
		} catch (IOException e) {
			installPath = Platform.getInstallLocation().getURL().getPath();
		}

		try {
			iconURL = new URL(installURL, ICON_PATH);
		} catch (IOException e) {
		}

		String symbolicName = bundle.getSymbolicName();
		if (symbolicName != null) {
			String key = symbolicName + "/profiling"; //$NON-NLS-1$
			String value = Platform.getDebugOption(key);
			profiling = value == null ? false : value.equalsIgnoreCase("true"); //$NON-NLS-1$
		}

		if (isDebugging()) {
			getLogger().logInfo(
					"Initialized " + pluginId + ", installPath=" + installPath); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	public boolean debug(String debugType) {
		Boolean b = debugMap.get(debugType);
		if (b == null) {
			Bundle bundle = getBundle();
			String symbolicName = bundle.getSymbolicName();
			if (symbolicName != null) {
				String key = symbolicName + "/" + debugType; //$NON-NLS-1$
				String value = Platform.getDebugOption(key);
				boolean bValue = value == null ? false : value.equalsIgnoreCase("true"); //$NON-NLS-1$
				b = new Boolean(bValue);
				debugMap.put(debugType, b);
			}
		}		
		return b.booleanValue();
	}

	/**
	 * Returns the ID of this plug-in.
	 * 
	 * @return The ID of this plug-in.
	 */
	public String getId() {
		return pluginId;
	}

	/**
	 * Returns the install URL of this plug-in.
	 * 
	 * @param The
	 *            install URL of this plug-in.
	 */
	public URL getInstallURL() {
		return installURL;
	}

	/**
	 * Returns the install path of this plug-in.
	 * 
	 * @param The
	 *            install path of this plug-in.
	 */
	public String getInstallPath() {
		return installPath;
	}

	/**
	 * Loads and returns the localized properties of a Java properties file.
	 * <p>
	 * 
	 * @param path
	 *            The properties file path relative to the plug-in root.
	 * @return A <code>Properties</code> object.
	 */
	public Properties getProperties(String path) throws IOException {
		Properties props = new Properties();
		if (path == null) {
			return props;
		}

		String filePath = getLocalizedFile(path, true);
		if (filePath != null) {
			props.load(new FileInputStream(filePath));
		}

		return props;
	}

	/**
	 * get the locale specific absolute file path name of the given file in the
	 * plugin.
	 * 
	 * @param path
	 *            The properties file path relative to the plug-in root.
	 * @return String the locale specific absolute file path name of the given
	 *         file.
	 * @throws IOException
	 */
	public String getLocalizedFile(String path, boolean useDefault)
			throws IOException {
		String filePath = null;
		String fileName = FileUtil.getFileName(path);
		int index = path.lastIndexOf(fileName);
		String pathName = path.substring(0, index);

		Locale locale = Locale.getDefault();

		Bundle bundle = getBundle();
		Bundle[] bundles = Platform.getFragments(bundle);
		if (bundles != null) {
			for (int i = 0; i < bundles.length; i++) {
				URL entry = bundles[i].getEntry(pathName);
				if (entry != null) {
					URL url = FileLocator.resolve(entry);
					filePath = I18nUtil.getLocalizedFile(url.getPath()
							+ fileName, locale); 
					if (filePath != null) {
						break;
					}
				}
			}
		}

		if (filePath == null) {
			URL entry = bundle.getEntry(path);
			if (entry != null) {
				URL url = FileLocator.resolve(entry);
				filePath = I18nUtil.getLocalizedFile(url.getPath(), locale);
				if (filePath == null && useDefault) {
					filePath = url.getPath();
				}
			}
		}

		return filePath;
	}

	/**
	 * for the given path in the plugin, find the localized files form the nl
	 * fragemenets and copy the localized files to the destination folder
	 * 
	 * @param path
	 *            String a relative path to the plugin root. The files in this
	 *            folder will be iterated and their localized files will be
	 *            copied over
	 * @param toDir
	 *            FIle the destination folder
	 * @param recursive
	 *            boolean recurively looking for files int the specified folder
	 * @param useLocaleFileName
	 *            boolean if true the locale specific file names will be used in
	 *            the copied destination, otherwise, the locale specific file
	 *            name will be renamed to the default one in the destination
	 *            folder
	 * @throws IOException
	 */
	public void copyLocalizedFiles(String path, File toDir, boolean recursive,
			boolean useLocaleFileName) throws IOException {
		String pluginPath = getInstallPath();
		URI pluginUri = new File(pluginPath).toURI();
		URI pathUri = new File(pluginPath, path).toURI();

		List<File> files = new ArrayList<File>();
		File f = new File(pluginPath, path);
		FileUtil.getAllFiles(f, files, recursive);

		// for each file found in the specified folder, get the localized file
		for (Iterator it = files.iterator(); it.hasNext();) {
			URI srcUri = ((File) it.next()).toURI();

			// get the relative path of the file to the plugin root, then find
			// the localized file
			String relPath = pluginUri.relativize(srcUri).getPath();

			// only get the locale specific file, don't include the default one
			String localizedFile = getLocalizedFile(relPath, false);
			if (localizedFile == null) {
				continue;
			}

			// need to change the target file path to relative to the path
			// instead of the plugin root
			relPath = pathUri.relativize(srcUri).getPath();
			File srcFile = new File(localizedFile);
			File targetFile = new File(toDir, relPath);
			File targetParent = targetFile.getParentFile();

			// copy the file to the desitination
			// if useLocaleFileName is true, the destination file name should
			// also use the locale specific file name
			if (useLocaleFileName) {
				String fileName = srcFile.getName();
				targetFile = new File(targetParent, fileName);
			}

			if (isDebugging()) {
				System.out.println("Copying localized file: "); //$NON-NLS-1$
				System.out.println("Source: " + srcFile); //$NON-NLS-1$
				System.out.println("Target: " + targetFile); //$NON-NLS-1$
				System.out.println(""); //$NON-NLS-1$
			}

			try {
				if (!targetParent.exists()) {
					targetParent.mkdirs();
				}

				if (!targetFile.exists()) {
					targetFile.createNewFile();
				}

				FileUtil.copyFile(srcFile, targetFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Returns the localized resource.
	 * 
	 * @param key
	 *            The resource key.
	 * @return The localized resource.
	 */
	public String getString(String key) {
		if (resourceBundle != null) {
			try {
				return resourceBundle.getString(key);
			} catch (MissingResourceException e) {
			}
		}
		return '[' + key + ']';
	}

	/**
	 * Returns the formatted localized message given the resource key and the
	 * message argument.
	 * 
	 * @param key
	 *            The resource key.
	 * @param argument
	 *            The message argument.
	 * @return The formatted localized message.
	 */
	public String formatMessage(String key, Object argument) {
		if (resourceBundle != null) {
			try {
				String msg = resourceBundle.getString(key);
				Object[] arguments = { argument };
				return MessageFormat.format(msg, arguments);
			} catch (MissingResourceException e) {
			}
		}
		return '[' + key + ']';
	}

	/**
	 * Returns the image URL given the relative path.
	 * 
	 * @param relativePath
	 *            The image's path relative to the plug-in's root.
	 * @return The image URL.
	 */
	public URL getImageURL(String relativePath) {
		try {
			URL url = new URL(iconURL, relativePath);
			return FileLocator.resolve(url);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Returns the image descriptor given the relative path.
	 * 
	 * @param relativePath
	 *            The image's path relative to the plug-in's root.
	 * @return The image descriptor.
	 */
	public ImageDescriptor getImageDescriptor(String relativePath) {
		try {
			URL url = new URL(iconURL, relativePath);
			return ImageDescriptor.createFromURL(url);
		} catch (MalformedURLException e) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

	/**
	 * Returns the image given the relative path.
	 * <p>
	 * Note: The returned image need to be freed by the caller.
	 * 
	 * @param relativePath
	 *            The image's path relative to the plug-in's root.
	 * @return The image.
	 */
	public Image getImage(String relativePath) {
		Image image = null;

		ImageDescriptor imageDescriptor = getImageDescriptor(relativePath);
		if (imageDescriptor != null) {
			image = imageDescriptor.createImage(false);
		}

		return image;
	}

	/**
	 * Returns the shared image given the relative path.
	 * <p>
	 * Note: The returned image will be automatically freed when the plug-in
	 * shuts down.
	 * 
	 * @param relativePath
	 *            The image's path relative to the plug-in's root.
	 * @return The image.
	 */
	public Image getSharedImage(String relativePath) {
		Image image = (Image) sharedImages.get(relativePath);
		if (image != null) {
			return image;
		}

		ImageDescriptor imageDescriptor = getImageDescriptor(relativePath);
		if (imageDescriptor != null) {
			image = imageDescriptor.createImage(false);
			if (image != null) {
				sharedImages.put(relativePath, image);
			}
		}

		return image;
	}

	/**
	 * Returns the profiling flag.
	 * 
	 * @return <code>true</code> if profiling is enabled for this plu-in
	 */
	public boolean isProfiling() {
		return profiling;
	}

	/**
	 * Returns the logger given the plug-in ID.
	 * 
	 * @return The new or cached logger.
	 */
	public Logger getLogger() {
		Logger logger = (Logger) loggers.get(pluginId);
		if (logger == null) {
			logger = new Logger(this);
			loggers.put(pluginId, logger);
		}
		return logger;
	}
	
	/**
	 * Returns the message dialog given the plug-in ID.
	 * 
	 * @return The new or cached message dialog.
	 */
	public static MsgDialog getMsgDialog(IActivator plugin) {
		MsgDialog msgDialog = (MsgDialog) msgDialogs.get(plugin.getId());
		if (msgDialog == null) {
			msgDialog = new MsgDialog(plugin);
			msgDialogs.put(plugin.getId(), msgDialog);
		}
		return msgDialog;
	}

	
	public MsgDialog getMsgDialog() {		
		return getMsgDialog(this);
	}
	
	/**
	 * Returns the standard display to be used. The method first checks, if the
	 * thread calling this method has an associated disaply. If so, this display
	 * is returned. Otherwise the method returns the default display.
	 */
	public static Display getStandardDisplay() {
		Display display;
		display = Display.getCurrent();
		if (display == null)
			display = Display.getDefault();
		return display;
	}

}
