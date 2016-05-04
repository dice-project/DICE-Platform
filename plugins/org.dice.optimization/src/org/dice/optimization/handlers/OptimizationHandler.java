package org.dice.optimization.handlers;

import java.net.MalformedURLException;
import java.net.URL;

import org.dice.optimization.OptimizationActivator;
import org.dice.optimization.preferences.pages.OptimizationPreferencesPage;
import org.dice.ui.DiceUIActivator;
import org.dice.ui.preferences.pages.DicePreferencesPage;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

public class OptimizationHandler extends AbstractHandler {

	private final String OPTIMIZATION_BROWSER_ID = "OPTIMIZATION_BROWSER_ID";

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
				browser = PlatformUI.getWorkbench().getBrowserSupport().createBrowser(style, OPTIMIZATION_BROWSER_ID,
						name, tooltip);
			} else {
				browser = PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser();
			}

			String protocol = OptimizationActivator.getDefault().getPreferenceStore()
					.getString(OptimizationPreferencesPage.OPTIMIZATION_PROTOCOL);
			String server = OptimizationActivator.getDefault().getPreferenceStore()
					.getString(OptimizationPreferencesPage.OPTIMIZATION_SERVER);
			Integer port = OptimizationActivator.getDefault().getPreferenceStore()
					.getInt(OptimizationPreferencesPage.OPTIMIZATION_PORT);
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
