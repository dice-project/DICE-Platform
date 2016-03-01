/*******************************************************************************
 * Copyright (c) 2008 IBM, TietoEnator, corp.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Brian Schlosser - initial implementation
 *  Roman Smirak  - update for EPFC 1.2 and 1.5
 *******************************************************************************/
package org.eclipse.epf.publishing.cmdline;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.diagram.ui.service.DiagramImageService;
import org.eclipse.epf.library.ILibraryService;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.xmi.XMILibraryManager;
import org.eclipse.epf.publishing.cmdline.dummyui.DummyWorkbenchAdvisor;
import org.eclipse.epf.publishing.services.AbstractViewBuilder;
import org.eclipse.epf.publishing.services.PublishHTMLOptions;
import org.eclipse.epf.publishing.services.PublishManager;
import org.eclipse.epf.publishing.services.PublishOptions;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

// TODO Replace deprecated IPlatformRunnable with IApplication
public class Publish implements IPlatformRunnable {

	private static final String ABOUT = "-about"; //$NON-NLS-1$
	private static final String BANNER = "-banner"; //$NON-NLS-1$
	private static final String BANNER_HEIGHT = "-bannerHeight"; //$NON-NLS-1$
	private static final String CHECK_LINKS = "-checkLinks"; //$NON-NLS-1$
	private static final String CONFIG = "-config"; //$NON-NLS-1$
	private static final String DYNAMIC = "-dynamic"; //$NON-NLS-1$
	private static final String FEEDBACK = "-feedback"; //$NON-NLS-1$
	private static final String GLOSSARY = "-glossary"; //$NON-NLS-1$
	private static final String INDEX = "-index"; //$NON-NLS-1$
	private static final String LIBRARY_PATH = "-libraryPath"; //$NON-NLS-1$
	private static final String PUBLISH_PATH = "-publishPath"; //$NON-NLS-1$
	private static final String PUBLISH_UNVERIFIED_DIAGRAMS = "-publishUnverifiedDiagrams"; //$NON-NLS-1$
	private static final String TITLE = "-title"; //$NON-NLS-1$

	private static final String PDELAUNCH = "-pdelaunch"; //$NON-NLS-1$

	private static final String LIBRARY_XMI = "/library.xmi"; //$NON-NLS-1$

	private String libraryPath;

	private String configuration;

	private Shell shell;

	public Object run(final Object args) throws Exception {
		try {
			// Parse parameters
			final PublishOptions publishDataModel = parseArgs((String[]) args);

			// FIXME GMF requires workbench
			Thread thread = new Thread(new Runnable() {
				public void run() {
					try {
						final Display display = new Display();
						shell = new Shell(display);
						shell.setLayoutData(new GridData(1, 1)); 
						// size can't be
						// 0,0
						// otherwise the diagram
						// will not be painted
						shell.setLayout(new GridLayout());
						shell.setVisible(false);

						PlatformUI.createAndRunWorkbench(display,
								new DummyWorkbenchAdvisor());

						shell.close();
						display.dispose();
					} catch (Exception e) {
						CmdlinePlugin.log(e);
						e.printStackTrace();
						System.err.println(Messages.internalError);
					}
				}
			});
			thread.start();

			// Open method library
			ILibraryService libService = LibraryService.getInstance();
			Map params = new HashMap();
			params.put(XMILibraryManager.ARG_LIBRARY_PATH, libraryPath);
			libService.openMethodLibrary(XMILibraryManager.LIBRARY_TYPE, params);

			// Start publishing
			return Publish.this.run(args, shell, publishDataModel);
		} catch (UsageException ex) {
			System.err.println(ex.getMessage());
			System.err.println(Messages.usage);
			return new Integer(1);
		} catch (Throwable ex) {
			CmdlinePlugin.log(ex);
			ex.printStackTrace();
			System.err.println(Messages.internalError);
			return new Integer(2);
		}
	}

	public Object run(Object args, Shell shell, PublishOptions publishDataModel)
			throws Exception {
		// String selectedConfigName = publishDataModel.getSelectedConfig();
		// String publishDestFolder = publishDataModel.getPublicationPath();
		ILibraryService lp = LibraryService.getInstance();
		MethodLibrary library = lp.getCurrentMethodLibrary();
		try {
			MethodConfiguration config = null;

			List configurations = library.getPredefinedConfigurations();
			for (Iterator iter = configurations.iterator(); iter.hasNext();) {
				MethodConfiguration configuration = (MethodConfiguration) iter
						.next();
				if (configuration.getName().equals(this.configuration)) {
					config = configuration;
				}

			}

			if (config == null) {
				throw new UsageException(NLS.bind(Messages.badConfig,
						this.configuration, libraryPath));
			}

			// Create and initialise Publish manager
			PublishManager mgr = new PublishManager();
			mgr
					.init(publishDataModel.getPublishDir(), config,
							publishDataModel);

			// Activate Activity diagram service
			AbstractViewBuilder viewBuilder = mgr.getViewBuilder();
			ElementLayoutManager layoutMgr = viewBuilder.getLayoutMgr();
			DiagramImageService diagramService = new DiagramImageService(shell,
					new File(layoutMgr.getPublishDir()));
			diagramService.setConfig(config);
			diagramService.setPublishedUnCreatedADD(publishDataModel
					.isPublishUnopenADD());
			diagramService.setPublishADForActivityExtension(publishDataModel
					.isPublishBaseAD());
			layoutMgr.setActivityDiagramService(diagramService);

			IProgressMonitor monitor = new TextProgressMonitor();
			mgr.publish(monitor);
		} finally {
			lp.closeCurrentMethodLibrary();
			shell.getDisplay().wake();
		}

		return EXIT_OK;
	}

	private PublishOptions parseArgs(String[] args) throws UsageException {
		PublishHTMLOptions options = new PublishHTMLOptions();

		// TODO Handle as a parameter
		options.setPublishConfiguration(true);
		options.setPublishProcess(true);
		// options.setPublishJavaScriptTree(false);
		// options.setPublishLightWeightTree(true);

		// TODO Proper handling of all parameters (e.g. convert broken links)
		for (int i = 0; i < args.length; i++) {
			if (args[i].charAt(0) == '-') {
				if (args[i].equals(LIBRARY_PATH)) {
					File path = new File(args[++i]);
					if (!path.isAbsolute()) {
						path = path.getAbsoluteFile();
					}
					if (!new File(path, LIBRARY_XMI).exists()) {
						throw new UsageException(NLS.bind(
								Messages.invalidLibraryPath, args[i]));
					}
					libraryPath = path.getAbsolutePath();
					System.out.println(libraryPath);
				} else if (args[i].equals(PUBLISH_PATH)) {
					options.setPublishDir(args[++i]);
				} else if (args[i].equals(CONFIG)) {
					// options.setSelectedConfig(args[++i]);
					this.configuration = args[++i];
				} else if (args[i].equals(BANNER)) {
					options.setBannerImage(args[++i]);
					if (!new File(args[i]).exists()) {
						throw new UsageException(NLS.bind(
								Messages.invalidBannerPath, args[i]));
					}
				} else if (args[i].equals(BANNER_HEIGHT)) {
					try {
						options.setBannerImageHeight(Integer
								.parseInt(args[++i]));
					} catch (NumberFormatException e) {
						throw new UsageException(NLS.bind(
								Messages.invalidImageHeight, args[i]));
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new UsageException(NLS.bind(
								Messages.invalidImageHeight, "<Missing>"));
					}
				} else if (args[i].equals(FEEDBACK)) {
					options.setFeedbackURL(args[++i]);
				} else if (args[i].equals(TITLE)) {
					options.setTitle(args[++i]);
				} else if (args[i].equals(ABOUT)) {
					options.setAboutHTML(args[++i]);
					if (!new File(args[i]).exists()) {
						throw new UsageException(NLS.bind(
								Messages.invalidAboutPath, args[i]));
					}
				} else if (args[i].equals(GLOSSARY)) {
					options.setPublishGlossary(true);
				} else if (args[i].equals(INDEX)) {
					options.setPublishIndex(true);
				} else if (args[i].equals(DYNAMIC)) {
					options.setPublishDynamicWebApp(true);
					options.setDynamicWebAppName(args[++i]);
				} else if (args[i].equals(CHECK_LINKS)) {
					options.setCheckExternalLinks(true);
					options.setConvertBrokenLinks(true);
				} else if (args[i].equals(PUBLISH_UNVERIFIED_DIAGRAMS)) {
					options.setPublishUnopenADD(true);
					options.setPublishBaseAD(true);
				} else if (args[i].equals(PDELAUNCH)) {
					// ignore
				} else {
					throw new UsageException(NLS.bind(Messages.unknownArg,
							args[i]));
				}
			} else {
				throw new UsageException(NLS.bind(Messages.unknownArg, args[i]));
			}
		}

		if (libraryPath == null) {
			throw new UsageException(NLS.bind(
					Messages.missingRequiredParameter, LIBRARY_PATH));
		}
		if (options.getPublishDir() == null) {
			throw new UsageException(NLS.bind(
					Messages.missingRequiredParameter, PUBLISH_PATH));
		}
		if (this.configuration == null) {
			throw new UsageException(NLS.bind(
					Messages.missingRequiredParameter, CONFIG));
		}

		return options;
	}

}
