package org.dice.rcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.ui.css.swt.CSSSWTConstants;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.branding.IProductConstants;
import org.eclipse.ui.splash.BasicSplashHandler;
import org.osgi.framework.Bundle;

public class DiceSplashHandler extends BasicSplashHandler {

	// CSS id can be used to style the label for the build ID
	private static final String CSS_ID_SPLASH_BUILD_ID = "org-eclipse-ui-buildid-text"; //$NON-NLS-1$
	private static final String ABOUT_MAPPINGS = "$nl$/about.mappings"; //$NON-NLS-1$

	private static HashMap<Bundle, String[]> mappingsMap = new HashMap<Bundle, String[]>();

	public void init(Shell splash) {
		super.init(splash);
		String progressRectString = null;
		String messageRectString = null;
		String foregroundColorString = null;
		IProduct product = Platform.getProduct();
		if (product != null) {
			progressRectString = product.getProperty(IProductConstants.STARTUP_PROGRESS_RECT);
			messageRectString = product.getProperty(IProductConstants.STARTUP_MESSAGE_RECT);
			foregroundColorString = product.getProperty(IProductConstants.STARTUP_FOREGROUND_COLOR);
		}
		Rectangle progressRect = StringConverter.asRectangle(progressRectString, new Rectangle(10, 10, 300, 15));
		setProgressRect(progressRect);

		Rectangle messageRect = StringConverter.asRectangle(messageRectString, new Rectangle(10, 35, 300, 15));
		setMessageRect(messageRect);

		int foregroundColorInteger;
		try {
			foregroundColorInteger = Integer.parseInt(foregroundColorString, 16);
		} catch (Exception ex) {
			foregroundColorInteger = 0xD2D7FF; // off white
		}

		setForeground(new RGB((foregroundColorInteger & 0xFF0000) >> 16, (foregroundColorInteger & 0xFF00) >> 8,
				foregroundColorInteger & 0xFF));
		// the following code will be removed for release time

		String buildIdShowString = product.getProperty("buildIdShow"); //$NON-NLS-1$
		boolean buildIdShow = false;
		if (buildIdShowString != null) {
			buildIdShow = Boolean.valueOf(buildIdShowString);
		}

		String[] mappings = loadMappings(product.getDefiningBundle());

		if (buildIdShow) {
			StringBuilder sb = new StringBuilder();
			if (mappings.length >= 3) {
				sb.append(mappings[0]);
				if (mappings[1] != null && !mappings[1].isEmpty()) {
					sb.append(" (");
					sb.append(mappings[1]);
					sb.append(") :: ");
				} else {
					sb.append(" :: ");
				}
				sb.append(mappings[2]);
			} else {
				sb.append("Unknown Build");
			}

			// find the specified location. Not currently API
			// hardcoded to be sensible with our current splash Graphic

			String buildIdRectangleString = product.getProperty("buildIdRectangle"); //$NON-NLS-1$
			Rectangle buildIdRectangle = StringConverter.asRectangle(buildIdRectangleString,
					new Rectangle(300, 105, 220, 40));

			Label idLabel = new Label(getContent(), SWT.RIGHT);
			idLabel.setForeground(getForeground());
			idLabel.setBounds(buildIdRectangle);
			idLabel.setText(sb.toString());
			idLabel.setData(CSSSWTConstants.CSS_ID_KEY, CSS_ID_SPLASH_BUILD_ID);
		} else {
			getContent(); // ensure creation of the progress
		}
	}

	private static String[] loadMappings(Bundle definingBundle) {
		URL location = FileLocator.find(definingBundle, new Path(ABOUT_MAPPINGS), null);
		PropertyResourceBundle bundle = null;
		InputStream is;
		if (location != null) {
			is = null;
			try {
				is = location.openStream();
				bundle = new PropertyResourceBundle(is);
			} catch (IOException e) {
				bundle = null;
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					// do nothing if we fail to close
				}
			}
		}

		List<String> mappingsList = new ArrayList<String>();
		if (bundle != null) {
			boolean found = true;
			int i = 0;
			while (found) {
				try {
					mappingsList.add(bundle.getString(Integer.toString(i)));
				} catch (MissingResourceException e) {
					found = false;
				}
				i++;
			}
		}
		String[] mappings = (String[]) mappingsList.toArray(new String[mappingsList.size()]);
		mappingsMap.put(definingBundle, mappings);
		return mappings;
	}

}
