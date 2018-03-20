package org.dice.ui.handlers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.dice.ui.DiceUIActivator;
import org.dice.ui.preferences.pages.AbstractOpenBrowserPreferencesPage;
import org.dice.ui.preferences.pages.DicePreferencesPage;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

public abstract class AbstractOpenBrowserHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			IWebBrowser browser;
			boolean internalBrowser = DiceUIActivator.getDefault().getPreferenceStore()
					.getBoolean(DicePreferencesPage.DICE_BROWSER);
			if (internalBrowser) {
				int style = IWorkbenchBrowserSupport.AS_EDITOR | IWorkbenchBrowserSupport.LOCATION_BAR
						| IWorkbenchBrowserSupport.NAVIGATION_BAR;
				String name = null;
				String tooltip = "";
				browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(style, getBrowserId(), name,
						tooltip);
			} else {
				browser = PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser();
			}

			String protocol = getProtocol();
			String server = getServer();
			Integer port = getPort();
			String path = getPath();
			String parameters = getParameters();

			URL url = new URL(protocol, server, port, path + parameters);

			browser.openURL(url);
		} catch (PartInitException e) {
			MessageDialog.openError(getShell(), "Error while opening the Web Browser",
					"An error occurred while opening the Web Browser. Try restarting the IDE. More details:\n"
							+ e.getMessage());
		} catch (MalformedURLException e) {
			MessageDialog.openError(getShell(), "Error while opening the Web Browser",
					"An error occurred while opening the URL. More details:\n" + e.getMessage());
		}

		return null;
	}

	protected Shell getShell() {
		return PlatformUI.getWorkbench().getModalDialogShellProvider().getShell();
	}

	/**
	 * Get the preferences store
	 */
	protected IPreferenceStore getStore() {
		return getOpenBrowserPreferencesPage().getPluginPreferenceStore();
	}

	/**
	 * Get the protocol from the preferences page
	 */
	protected String getProtocol() {
		return getStore().getString(getOpenBrowserPreferencesPage().getProtocolIdProperty());
	}

	/**
	 * Get the server from the preferences page
	 */
	protected String getServer() {
		return getStore().getString(getOpenBrowserPreferencesPage().getServerIdProperty());
	}

	/**
	 * Get the port from the preferences page
	 */
	protected Integer getPort() {
		Integer port = getStore().getInt(getOpenBrowserPreferencesPage().getPortIdProperty());
		return port != null ? port : 80;
	}

	/**
	 * Get the path from the preferences page
	 */
	protected String getPath() {
		return getStore().getString(getOpenBrowserPreferencesPage().getPathIdProperty());
	}

	protected String getParameters() {
		Map<String, String> parameters = new HashMap<String, String>();
		fillRequestParameters(parameters);
		return parseMapParameters(parameters);
	}

	private String parseMapParameters(Map<String, String> parameters) {
		if (parameters == null || parameters.isEmpty()) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		builder.append("?");
		for (Iterator<Entry<String, String>> it = parameters.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> next = it.next();
			builder.append(next.getKey());
			builder.append("=");
			builder.append(next.getValue());

			if (it.hasNext()) {
				builder.append("&");
			}
		}

		return builder.toString();
	}

	/**
	 * Fill the parameters of the request. As parameter of this function, you'll
	 * receive the map you need to fulfill
	 */
	protected void fillRequestParameters(Map<String, String> parameters) {
	}

	/**
	 * The ID for this browser
	 */
	protected abstract String getBrowserId();

	/**
	 * The Preferences page of this preferences page contribution
	 */
	protected abstract AbstractOpenBrowserPreferencesPage getOpenBrowserPreferencesPage();

}
