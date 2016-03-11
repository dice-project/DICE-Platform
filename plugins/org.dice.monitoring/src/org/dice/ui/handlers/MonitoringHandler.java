package org.dice.ui.handlers;

import java.net.MalformedURLException;
import java.net.URL;

import org.dice.ui.DiceUIActivator;
import org.dice.ui.MonitoringActivator;
import org.dice.ui.preferences.pages.DicePreferencesPage;
import org.dice.ui.preferences.pages.MonitoringPreferencesPage;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

public class MonitoringHandler extends AbstractHandler {

	private final String MONITORING_BROWSER_ID = "MONITORING_BROWSER_ID";

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
				browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(style, MONITORING_BROWSER_ID,
						name, tooltip);
			} else {
				browser = PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser();
			}

			String protocol = MonitoringActivator.getDefault().getPreferenceStore()
					.getString(MonitoringPreferencesPage.MONITORING_PROTOCOL);
			String server = MonitoringActivator.getDefault().getPreferenceStore()
					.getString(MonitoringPreferencesPage.MONITORING_SERVER);
			Integer port = MonitoringActivator.getDefault().getPreferenceStore()
					.getInt(MonitoringPreferencesPage.MONITORING_PORT);
			port = port != null ? port : 80;

			URL url = new URL(protocol, server, port, "");

			browser.openURL(url);
		} catch (PartInitException e) {
			MessageDialog.openError(PlatformUI.getWorkbench().getModalDialogShellProvider().getShell(),
					"Error while opening the Web Browser",
					"An error occurred while opening the Web Browser. Try restarting the IDE. More details:\n"
							+ e.getMessage());
		} catch (MalformedURLException e) {
			MessageDialog.openError(PlatformUI.getWorkbench().getModalDialogShellProvider().getShell(),
					"Error while opening the Web Browser",
					"An error occurred while opening the URL. More details:\n" + e.getMessage());
		}

		return null;
	}

}
