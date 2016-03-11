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
package org.eclipse.epf.library.ui;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.epf.common.service.utils.CommandLineRunUtil;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.I18nUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceException;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.DebugUtil;
import org.eclipse.epf.library.edit.util.IRunnableWithProgress;
import org.eclipse.epf.library.layout.LayoutResources;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.preferences.LibraryPreferences;
import org.eclipse.epf.library.prefs.PreferenceUtil;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.ui.actions.ConfigurationContributionItem;
import org.eclipse.epf.library.ui.dialogs.CopyLibraryDialog;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.library.ui.wizards.LibraryBackupUtil;
import org.eclipse.epf.library.xmi.XMILibraryManager;
import org.eclipse.epf.library.xmi.XMILibraryUtil;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.persistence.migration.MappingUtil;
import org.eclipse.epf.persistence.migration.UpgradeCallerInfo;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.progress.WorkbenchJob;

/**
 * The default Library UI Manager implementation.
 * <p>
 * A Library Manager provides the user interface for creating and opening a
 * method library.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class LibraryUIManager {

	public static boolean DEBUG = LibraryUIPlugin.getDefault().isDebugging();

	public static final String TOOLBAR_CONFIG_CONTRIBUTION_ID = "toolbar.config.contribution"; //$NON-NLS-1$

	private static final String CONFIG_VIEW_ID = "org.eclipse.epf.authoring.ui.views.ConfigurationView"; //$NON-NLS-1$

	private static final String PROCESS_EDITOR_ID = "org.eclipse.epf.authoring.ui.editors.ProcessEditor"; //$NON-NLS-1$
	
	private static final String REPORT_PERS_ID = "org.eclipse.birt.report.designer.ui.ReportPerspective"; //$NON-NLS-1$

	private static LibraryUIManager instance = null;
	
	private static boolean skipInstallPathCheck = false;
	
	// The URI of the method library that will be opened regardless of the
	// saved library preferences.
	protected static URI libraryURI;

	// The URI of the method library that will be opened only if there are
	// no saved library preferences.
	protected static URI defaultLibraryURI;
	protected static URI defaultLibraryURI_NL;
	private boolean libraryInitialized = false;
	
	protected ConfigurationContributionItem configCombo = null;

	/**
	 * Returns the singleton instance.
	 */
	public static LibraryUIManager getInstance() {
		if (instance == null) {
			synchronized (LibraryUIManager.class) {
				if (instance == null) {
					instance = new LibraryUIManager();
				}
			}
		}
		return instance;
	}

	/**
	 * Private default constructor to prevent this class from being
	 * instantiated.
	 */
	private LibraryUIManager() {
		// Monitor perspective changes to display/hide the configuration combo.
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (DebugUtil.uiDebug) {
			DebugUtil.print("LibraryUIManager() called, window: " + window);//$NON-NLS-1$
			DebugUtil.print();
		}
		if (window != null) {
			window.addPerspectiveListener(new IPerspectiveListener() {
				public void perspectiveActivated(IWorkbenchPage page,
						IPerspectiveDescriptor desc) {
					checkConfigurationContribution();
				}

				public void perspectiveChanged(IWorkbenchPage page,
						IPerspectiveDescriptor desc, String id) {
					checkConfigurationContribution();
				}
			});
		}
	}
	
	/**
	 * Creates and opens a new method library.
	 * 
	 * @param path
	 *            the method library path
	 * @return <code>true</code> if the method library is created and opened
	 *         successfully
	 */
	public boolean createLibrary(String path) {
		try {
			File libraryPath = new File(path);
			if (!libraryPath.exists()) {
				libraryPath.mkdirs();
			}
			XMILibraryUtil.createMethodLibrary(libraryPath.getName(), path);
			LibraryUIPreferences.setSavedLibraryPath(path);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Opens the last opened method library in a non-UI job. If successful,
	 * successRunnable will be run, otherwise nextRunnable will be run.
	 * 
	 * @param path
	 *            URI path to a method library
	 * @return <code>true</code> if the method library is opened successfully
	 */
	private void openLastOpenedLibrary(final Runnable successRunnable, final Runnable nextRunnable) {
		try {
			
			// first check for saved lib in preference
			// so that we don't show progress monitor if there's no lib
			String savedMethodLibraryURI = LibraryPreferences
				.getSavedMethodLibraryURI();
			URI uri = new URI(savedMethodLibraryURI);
			if (uri.getPath().length() == 0) {
				nextRunnable.run();
				return;
			}
			
			final WorkspaceJob openLibraryJob = new WorkspaceJob("Open last opened library") {

				@Override
				public IStatus runInWorkspace(IProgressMonitor monitor)
						throws CoreException {
					try {
						monitor.beginTask(StrUtil.EMPTY_STRING, IProgressMonitor.UNKNOWN);
						LibraryService.getInstance().closeCurrentMethodLibrary();
						LibraryService.getInstance().openLastOpenedMethodLibrary();
					} catch (Exception e) {
						LibraryUIPlugin.getDefault().getLogger().logError(e);
					}
					return Status.OK_STATUS;
				}
				
			};
			BusyIndicatorHelper.setUserInterfaceActive(false);
			final Integer openLibraryJobBusyId = BusyIndicatorHelper.showWhile(PlatformUI.getWorkbench().getDisplay());
			PlatformUI.getWorkbench().getProgressService().showInDialog(null, openLibraryJob);
			openLibraryJob.setSystem(true);
			openLibraryJob.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {
					// UI activation job
					WorkbenchJob reactivateUIJob = new WorkbenchJob(StrUtil.EMPTY_STRING) {

						@Override
						public IStatus runInUIThread(IProgressMonitor monitor) {
							BusyIndicatorHelper.setUserInterfaceActive(true);
							BusyIndicatorHelper.hideWhile(PlatformUI.getWorkbench().getDisplay(), openLibraryJobBusyId);
							// Show Problems View if necessary.
							ILibraryManager libMgr = LibraryService
									.getInstance().getCurrentLibraryManager();
							if(libMgr != null) {
								ILibraryResourceSet resourceSet = (ILibraryResourceSet) libMgr.getEditingDomain().getResourceSet();
								if (resourceSet.hasUnresolvedProxy()) {
									try {
										PlatformUI
										.getWorkbench()
										.getActiveWorkbenchWindow()
										.getActivePage()
										.showView(
												"org.eclipse.ui.views.ProblemView", null, IWorkbenchPage.VIEW_VISIBLE); //$NON-NLS-1$
									} catch (Exception e) {
										LibraryUIPlugin.getDefault().getLogger()
										.logError(e);
									}
								}
							}

							return Status.OK_STATUS;
						}
						
					};
					reactivateUIJob.setSystem(true);
					reactivateUIJob.schedule();
					
					if(LibraryService.getInstance().getCurrentMethodLibrary() != null) {
						successRunnable.run();
					} else {
						nextRunnable.run();
					}
				}

			});
			openLibraryJob.schedule();
		} catch (Exception e) {
			// don't do anything
			LibraryUIPlugin.getDefault().getLogger().logError(e);
		}		
	}
	
	/**
	 * Opens a method library with the specified library path URI in a non-UI
	 * job. If successful, successRunnable will be run, otherwise nextRunnable
	 * will be run.
	 * 
	 * @param path
	 *            URI path to a method library
	 * @return <code>true</code> if the method library is opened successfully
	 */
	private void openLibrary(final URI uri, final Runnable successRunnable, final Runnable nextRunnable) {
		if(uri == null) {
			if (nextRunnable != null) {
				nextRunnable.run();
			}
			return;
		}
		
		final Map<String, Object> args = new HashMap<String, Object>();
		args.put(XMILibraryManager.ARG_LIBRARY_PATH, new File(uri).getAbsolutePath());
		final List<Exception> errors = new ArrayList<Exception>();

		WorkspaceJob openLibraryJob = new WorkspaceJob(LibraryUIResources.openingLibraryTask_name) {
		
			@Override
			public IStatus runInWorkspace(IProgressMonitor monitor)
					throws CoreException {
				try {
					monitor.beginTask(StrUtil.EMPTY_STRING, IProgressMonitor.UNKNOWN);
					LibraryService.getInstance().closeCurrentMethodLibrary();
					String newPath = handleLibraryOnReadOnlyInstallPath(uri);
					if (newPath != null) {
						args.put(XMILibraryManager.ARG_LIBRARY_PATH, newPath);
					}
					MethodLibrary library = LibraryService.getInstance()
							.openMethodLibrary(XMILibraryManager.LIBRARY_TYPE, args);
					LibraryService.getInstance().setCurrentMethodLibrary(
							library);
					String path = (String) args.get(XMILibraryManager.ARG_LIBRARY_PATH);
					LibraryUIPreferences.setSavedLibraryPath(path);
				} catch (LibraryServiceException e) {
					errors.add(e);
				} finally {
					monitor.done();
				}
				return Status.OK_STATUS;
			}
		
		};
		openLibraryJob.setSystem(true);
		final Integer openLibraryJobBusyId = BusyIndicatorHelper.showWhile(PlatformUI.getWorkbench().getDisplay());
		BusyIndicatorHelper.setUserInterfaceActive(false);
		PlatformUI.getWorkbench().getProgressService().showInDialog(null, openLibraryJob);
		openLibraryJob.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				// UI activation job
				WorkbenchJob reactivateUIJob = new WorkbenchJob(StrUtil.EMPTY_STRING) {

					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						BusyIndicatorHelper.setUserInterfaceActive(true);
						BusyIndicatorHelper.hideWhile(PlatformUI.getWorkbench().getDisplay(), openLibraryJobBusyId);
						// Show Problems View if necessary.
						ILibraryManager libMgr = LibraryService
							.getInstance().getCurrentLibraryManager();
						if(libMgr != null) {
							ILibraryResourceSet resourceSet = (ILibraryResourceSet) libMgr.getEditingDomain().getResourceSet();
							if (resourceSet.hasUnresolvedProxy()) {
								try {
									PlatformUI
									.getWorkbench()
									.getActiveWorkbenchWindow()
									.getActivePage()
									.showView(
											"org.eclipse.ui.views.ProblemView", null, IWorkbenchPage.VIEW_VISIBLE); //$NON-NLS-1$
								} catch (Exception e) {
									LibraryUIPlugin.getDefault().getLogger()
									.logError(e);
								}
							}
						}
						postOpenLibrary(errors, (String) args.get(XMILibraryManager.ARG_LIBRARY_PATH));
						if(errors.isEmpty()) {
							successRunnable.run();
						} else if (nextRunnable != null) {
							nextRunnable.run();
						}
						return Status.OK_STATUS;
					}
					
				};
				reactivateUIJob.setSystem(true);
				reactivateUIJob.schedule();
			}

		});
		openLibraryJob.schedule();
	}

	/**
	 * Opens a method library given the library path.
	 * 
	 * @param path
	 *            path to a method library
	 * @return <code>true</code> if the method library is opened successfully
	 */
	public boolean openLibrary(final String path) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put(XMILibraryManager.ARG_LIBRARY_PATH, path);
		return openLibrary(XMILibraryManager.LIBRARY_TYPE, args);
	}

	/**
	 * Opens a method library.
	 * 
	 * @param type
	 *            the method library type
	 * @param params
	 *            method library specific arguments
	 * @return <code>true</code> if the method library is opened successfully
	 */
	public boolean openLibrary(final String type, final Map<String, Object> args) {
		final String path = (String) args
				.get(XMILibraryManager.ARG_LIBRARY_PATH);

		final List<Exception> errors = new ArrayList<Exception>();
		
		UserInteractionHelper.getUIHelper().runWithProgress(new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				try {
					LibraryService.getInstance().closeCurrentMethodLibrary();
					URI libURI = Path.fromOSString(path).toFile().toURI();
					String newPath = handleLibraryOnReadOnlyInstallPath(libURI);
					if (newPath != null) {
						args.put(XMILibraryManager.ARG_LIBRARY_PATH, newPath);
					}
					MethodLibrary library = LibraryService.getInstance()
							.openMethodLibrary(type, args);
					LibraryService.getInstance().setCurrentMethodLibrary(
							library);
					LibraryUIPreferences.setSavedLibraryPath(path);

					// Show Problems View if necessary.
					ILibraryResourceSet resourceSet = ((ILibraryResourceSet) LibraryService
							.getInstance().getCurrentLibraryManager()
							.getEditingDomain().getResourceSet());
					if (resourceSet.hasUnresolvedProxy()) {
						SafeUpdateController.asyncExec(new Runnable() {
							public void run() {
								try {
									PlatformUI
											.getWorkbench()
											.getActiveWorkbenchWindow()
											.getActivePage()
											.showView(
													"org.eclipse.ui.views.ProblemView", null, IWorkbenchPage.VIEW_VISIBLE); //$NON-NLS-1$
								} catch (Exception e) {
									LibraryUIPlugin.getDefault().getLogger()
											.logError(e);
								}
							}
						});
					}
				} catch (Exception e) {
					if (!(e instanceof IOException && e.getMessage()
							.startsWith("###"))) { //$NON-NLS-1$
						LibraryUIPlugin.getDefault().getLogger().logError(e);
					}
					errors.add(e);
				}
			}
			
		}, false, LibraryUIResources.openingLibraryTask_name);
		
		return postOpenLibrary(errors, (String) args.get(XMILibraryManager.ARG_LIBRARY_PATH));
	}

	private boolean postOpenLibrary(List<Exception> errors, String libPath) {
		try {
			if (errors.isEmpty()) {
				IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow();
				if (workbenchWindow != null) {
					IWorkbenchPage activePage = workbenchWindow.getActivePage();
					if (activePage != null) {
						activePage.closeAllEditors(false);
					}
				}
				return true;
			} else {
				Iterator<Exception> iter = errors.iterator();
				while (iter.hasNext()) {
					Exception e = iter.next();
					if (e instanceof IOException) {
						String message = e.getMessage();
						if (message.startsWith("###")) { //$NON-NLS-1$
							String projectFileName = message.substring(3);

							String prompt = LibraryUIResources
									.bind(
											LibraryUIResources.readOnlyProjectFile_text,
											projectFileName);
							String[] buttonLabels = {
									LibraryUIResources.retryButton_text,
									LibraryUIResources.cancelButton_text };
							MessageDialog msgBox = new MessageDialog(Display
									.getCurrent().getActiveShell(),
									LibraryUIResources.openLibraryWizard_title,
									null, prompt, MessageDialog.WARNING,
									buttonLabels, 0);
							if (msgBox.open() == 0) {
								return openLibrary(libPath);
							} else {
								return true;
							}
						}
					} else {
						Throwable ex = e;
						for (ex = e; ex != null
								&& !(ex instanceof MessageException); ex = ex
								.getCause())
							;
						String msg = ex != null && ex.getMessage() != null ? ex
								.getMessage() : e.getMessage() != null ? e
								.getMessage() : e.toString();
						LibraryUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayError(
										LibraryUIResources.openLibraryWizard_title,
										msg, e);
					}
				}
			}
		} catch (Exception e) {
			LibraryUIPlugin.getDefault().getLogger().logError(e);
		}

		return false;
	}

	/**
	 * Upgrades a method library to a new meta-model.
	 * 
	 * @param libPath
	 *            path to a method library folder
	 * @return <code>true</code> if the given method library is sucessfully
	 *         upgraded
	 */
	public static boolean upgradeLibrary(final String libPath,
			final UpgradeCallerInfo callerInfo) {
		Shell shell = Display.getCurrent().getActiveShell();
		if (!CommandLineRunUtil.getInstance().isNeedToRun() && UpgradeCallerInfo.isUpgradeLibrary(callerInfo)) {
			LibraryBackupUtil.promptBackupLibrary(shell, new File(libPath));
		}

		String libXmi = MultiFileSaveUtil.DEFAULT_LIBRARY_MODEL_FILENAME;
		if (callerInfo != null && callerInfo.getIsExportedPluginLib()) {
			libXmi = XMILibraryManager.exportFile;
		}
		return upgradeLibrary(new File(libPath, libXmi), callerInfo);
	}

	public static boolean upgradeLibrary(final File libFile,
			final UpgradeCallerInfo callerInfo) {
		final StringBuffer errMsg = new StringBuffer();
		final boolean[] cancelFlagHolder = { false };

		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			public void execute(IProgressMonitor monitor) {
				monitor.beginTask(LibraryUIResources.upgradingLibraryTask_name,
						10);
				monitor.worked(1);
				try {
					MappingUtil.migrate(libFile.getAbsolutePath(), monitor,
							callerInfo);
				} catch (OperationCanceledException e) {
					cancelFlagHolder[0] = true;
				} catch (Exception e) {
					LibraryUIPlugin.getDefault().getLogger().logError(e);
					if (DEBUG) {
						e.printStackTrace();
					}
					String msg = e.getMessage();
					if (msg == null) {
						msg = LibraryUIResources.upgradeLibraryError_msg;
					}
					errMsg.append(msg);
				} finally {
					monitor.done();
				}
			}
		};

		try {
			// Run the operation and display the progress.
			ProgressMonitorDialog pmDialog = new ProgressMonitorDialog(Display
					.getCurrent().getActiveShell()) {
				protected Point getInitialSize() {
					Point calculatedSize = super.getInitialSize();
					if (calculatedSize.x < 675) {
						calculatedSize.x = 675;
					}
					return calculatedSize;
				}
			};
			pmDialog.run(true, false, operation);
			if (cancelFlagHolder[0]) {
				return false;
			} else if (errMsg.length() > 0) {
				IStatus status = new Status(IStatus.ERROR,
						LibraryUIPlugin.PLUGIN_ID, 0, "", null) {//$NON-NLS-1$
					public IStatus[] getChildren() {
						IStatus[] ret = new Status[1];
						IStatus cs = new Status(IStatus.ERROR,
								LibraryUIPlugin.PLUGIN_ID, 0,
								errMsg.toString(), null);
						ret[0] = cs;
						return ret;
					}

					public boolean isMultiStatus() {
						return true;
					}
				};

				LibraryUIPlugin.getDefault().getMsgDialog().displayError(
						LibraryUIResources.upgradeLibraryDialog_title,
						LibraryUIResources.upgradeLibraryError_msg, status);

				return false;
			}
			return true;
		} catch (Exception e) {
			LibraryUIPlugin.getDefault().getLogger().logError(e);
			if (DEBUG) {
				e.printStackTrace();
			}
			LibraryUIPlugin.getDefault().getMsgDialog().displayError(
					LibraryUIResources.upgradeLibraryDialog_title,
					LibraryUIResources.upgradeLibraryError_msg);
			return false;
		}

	}
	
	/**
	 * Sets the path of the method library that will be opened only if there are
	 * no saved library preferences.
	 * 
	 * @param libraryPath
	 *            path to a method library
	 */
	public static void setCommandLineDefaultLibrary(String libraryPath) {
		if (libraryPath != null && libraryPath.length() > 0) {
			IPath path = Path.fromOSString(libraryPath);
			try {
				defaultLibraryURI = path.toFile().toURI();
			} catch (Exception e) {
				defaultLibraryURI = null;
				LibraryPlugin.getDefault().getLogger().logError(e);
			}
		}
	}

	/**
	 * Sets the path of the method library that will be opened regardless of the
	 * saved library preferences.
	 * 
	 * @param libraryPath
	 *            path to a method library
	 */
	public static void setCommandLineLibrary(String libraryPath) {
		if (libraryPath != null && libraryPath.length() > 0) {
			IPath path = Path.fromOSString(libraryPath);
			try {
				libraryURI = path.toFile().toURI();
			} catch (Exception e) {
				libraryURI = null;
				LibraryPlugin.getDefault().getLogger().logError(e);
			}
		}
	}
	
	/**
	 * Checks whether the method configuration combobox should be added to the
	 * system toolbar.
	 * <p>
	 * The method configuration combobox lists all the method configurations in
	 * the current method library.
	 */
	public void checkConfigurationContribution() {
		IWorkbench workbench = LibraryUIPlugin.getDefault().getWorkbench();
		if (workbench != null) {
			IWorkbenchWindow window = (IWorkbenchWindow) workbench
					.getActiveWorkbenchWindow();
			if (window != null && window instanceof ApplicationWindow) {
				ICoolBarManager coolBarMgr = ((ApplicationWindow) window)
						.getCoolBarManager();
				try {
					IWorkbenchPage activePage = window.getActivePage();
					if (activePage != null) {
						if (foundConfigView(activePage)
								|| foundProcessEditor(activePage)
								|| foundPerspective(activePage, REPORT_PERS_ID)
								|| ConfigurationHelper.getDelegate().additionShowConfigSelectMenu(activePage)) {
							showConfigurationContribution(coolBarMgr);
						} else {
							hideConfigurationContribution(coolBarMgr);
						}
					}
				} catch (Exception e) {
					LibraryUIPlugin.getDefault().getLogger().logError(e);
					if (DEBUG) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Checks for the presence of the Configuration view in the active workbench
	 * page.
	 */
	private boolean foundConfigView(IWorkbenchPage activePage) {
		if (activePage == null) return false;
		return activePage.findView(CONFIG_VIEW_ID) != null;
	}

	/**
	 * Checks for the presence of a Process editor in the active workbench.
	 * page.
	 */
	private boolean foundProcessEditor(IWorkbenchPage activePage) {
		if (activePage == null) return false;
		IEditorReference[] editorRefs = activePage.findEditors(null,
				PROCESS_EDITOR_ID, IWorkbenchPage.MATCH_ID);
		return editorRefs != null && editorRefs.length > 0;
	}
	
	/**
	 * Checks for the presence of Report Perspective in the active workbench
	 * page. 
	 */
	private boolean foundPerspective(IWorkbenchPage activePage, String persId) {
		if (activePage.getPerspective().getId().equals(persId)) {		
			return true;
		}
		
		return false;
	}

	/**
	 * Displays the method configuration combobox in the system toolbar.
	 */
	private void showConfigurationContribution(ICoolBarManager coolBarMgr)
			throws Exception {
		// Check for the method configuration combobox toolbar.
		IContributionItem configToolBar = coolBarMgr
				.find(TOOLBAR_CONFIG_CONTRIBUTION_ID);

		if (configToolBar != null) {
			// Make sure the toolbar has the method configuration combobox
			// contribution.
			if (configToolBar instanceof ToolBarContributionItem) {
				IToolBarManager toolBarMgr = ((ToolBarContributionItem) configToolBar)
						.getToolBarManager();
				if (toolBarMgr != null) {
					IContributionItem[] toolBarItems = toolBarMgr.getItems();
					if (toolBarItems != null && toolBarItems.length > 0) {
						for (int i = 0; i < toolBarItems.length; i++) {
							toolBarItems[i].setVisible(true);
						}
						configToolBar.setVisible(true);
						updateSystemToolBar(coolBarMgr);
						return;
					}
				}

				// The method configuration combobox toolbar has been restored
				// via a saved perspective, add the method configuration
				// combobox contribution.
				configCombo = new ConfigurationContributionItem(
						null);
				if (DebugUtil.uiDebug) {
					DebugUtil.print("showConfigurationContribution, configCombo 1: " + configCombo);//$NON-NLS-1$
					DebugUtil.print();
				}
				configCombo.setId(ConfigurationContributionItem.class.getName());
				toolBarMgr.add(configCombo);
				configToolBar.setVisible(true);
				updateSystemToolBar(coolBarMgr);
				return;
			}
		}

		IToolBarManager toolBarMgr = new ToolBarManager(SWT.FLAT | SWT.LEFT);
		configCombo = new ConfigurationContributionItem(
				null);
		if (DebugUtil.uiDebug) {
			DebugUtil.print("showConfigurationContribution, configCombo 2: " + configCombo);//$NON-NLS-1$
			DebugUtil.print();
		}
		configCombo.setId(ConfigurationContributionItem.class.getName());
		toolBarMgr.add(configCombo);
		ToolBarContributionItem configComboToolBar = new ToolBarContributionItem(
				toolBarMgr, TOOLBAR_CONFIG_CONTRIBUTION_ID);
		coolBarMgr.add(configComboToolBar);
	}

	/**
	 * Hides the method configuration combobox from the system toolbar.
	 */
	private void hideConfigurationContribution(ICoolBarManager coolBarMgr)
			throws Exception {
		// Check for the method configuration combobox toolbar.
		IContributionItem configToolBar = coolBarMgr
				.find(TOOLBAR_CONFIG_CONTRIBUTION_ID);

		if (configToolBar == null) {
			return;
		}

		// Hide the method configuration combobox contribution from the toolbar.
		if (configToolBar instanceof ToolBarContributionItem) {
			IToolBarManager toolBarMgr = ((ToolBarContributionItem) configToolBar)
					.getToolBarManager();
			IContributionItem[] toolBarItems = toolBarMgr.getItems();
			for (int i = 0; i < toolBarItems.length; i++) {
				toolBarItems[i].setVisible(false);
			}
		}

		// Hide the method configuration combobox toolbar contribution.
		configToolBar.setVisible(false);

		// Update the the system tool bar.
		updateSystemToolBar(coolBarMgr);
	}

	/**
	 * Updates the system tool bar.
	 */
	private void updateSystemToolBar(ICoolBarManager coolBarMgr) {
		if (coolBarMgr instanceof CoolBarManager) {
			((CoolBarManager) coolBarMgr).update(true);
		}
	}

	/**
	 * Adds a part listener so the library will open upon "startup"
	 * "startup" means when the view with the specified id is first
	 * visible
	 */
	public void addMethodViewPartListener(final String id) {
		IWorkbench workbench = LibraryUIPlugin.getDefault().getWorkbench();
		if (workbench != null) {
			IWorkbenchWindow window = (IWorkbenchWindow) workbench
					.getActiveWorkbenchWindow();
			if (window != null && window instanceof ApplicationWindow) {
				try {
					final IWorkbenchPage activePage = window.getActivePage();
					activePage.addPartListener(new IPartListener2() {

						public void partActivated(
								IWorkbenchPartReference partRef) {
						}

						public void partBroughtToTop(
								IWorkbenchPartReference partRef) {
						}

						public void partClosed(IWorkbenchPartReference partRef) {
						}

						public void partDeactivated(
								IWorkbenchPartReference partRef) {
						}

						public void partHidden(IWorkbenchPartReference partRef) {
						}

						public void partInputChanged(
								IWorkbenchPartReference partRef) {
						}

						public void partOpened(IWorkbenchPartReference partRef) {
						}

						public void partVisible(IWorkbenchPartReference partRef) {
							if (partRef.getId().equals(id)) {
								activePage.removePartListener(this);
								startupOpenLibrary();
							}
						}
						
					});
				} catch (Exception e) {
					LibraryUIPlugin.getDefault().getLogger().logError(e);
					if (DEBUG) {
						e.printStackTrace();
					}
				}
			}
		}

	}
	
	
	/**
	 * Opens the library upon startup
	 * 1.  First tries to open the library specified by -library
	 * 2.  Then tries to open the last opened library
	 * 3.  Finally, tries to open the library specified by -defaultlibrary
	 */
	public void startupOpenLibrary() {
		if (libraryInitialized == true) {
			return;
		}
		libraryInitialized = true;
		try {
			String savedMethodLibraryURI = LibraryPreferences.getSavedMethodLibraryURI();
			final boolean hasSavedUri = savedMethodLibraryURI != null && savedMethodLibraryURI.length() > 0;
			
			final String lastSavedConfigName = PreferenceUtil.getSavedLastConfig();
			
			final Runnable successRunnable = new Runnable() {

				public void run() {
					if (LibraryService.getInstance().getCurrentMethodLibrary() != null) {
						MethodConfiguration savedConfig = LibraryServiceUtil
								.getMethodConfiguration(LibraryService.getInstance()
								.getCurrentMethodLibrary(), lastSavedConfigName);
						if (savedConfig != null) {
							LibraryService.getInstance().setCurrentMethodConfiguration(savedConfig);
						}
					}
				}
				
			};
			
			final Runnable openDefaultLibraryRunnable = new Runnable() {

				public void run() {
					if (defaultLibraryURI != null && !hasSavedUri) {
						// Try loading the NL library first.
						Locale locale = Locale.getDefault();
						String defaultLibraryStr = new File(defaultLibraryURI)
								.getAbsolutePath();
						String localizedLibPath = I18nUtil.getLocalizedFile(
								FileUtil.removeAllSeparator(defaultLibraryStr),
								locale);
						if (localizedLibPath != null) {
							defaultLibraryURI_NL = StrUtil.toURI(localizedLibPath);
						}
						
						final URI defautlLibUrl = defaultLibraryURI_NL != null ? defaultLibraryURI_NL : defaultLibraryURI;
						if (defautlLibUrl != null) {
//							openLibrary(defautlLibUrl, successRunnable, new Runnable() {
//								public void run() {
//									openLibrary(defautlLibUrl, successRunnable, null);
//								}
//							});
							openLibrary(defautlLibUrl, successRunnable, null);
						}
						
					}
				}
				
			};
			
			openLibrary(libraryURI, successRunnable, new Runnable() {

				public void run() {
					openLastOpenedLibrary(successRunnable, openDefaultLibraryRunnable);
				}

			});
			
//			if (libraryURI != null && openLibrary(libraryURI)) {
//			} else if (openLastOpenedLibrary()) {
//			} else if (defaultLibraryURI != null && !hasSavedUri) {
//				// Try loading the NL library first.
//				Locale locale = Locale.getDefault();
//				String defaultLibraryStr = new File(defaultLibraryURI)
//						.getAbsolutePath();
//				String localizedLibPath = I18nUtil.getLocalizedFile(
//						FileUtil.removeAllSeparator(defaultLibraryStr),
//						locale);
//				if (localizedLibPath != null) {
//					defaultLibraryURI_NL = StrUtil.toURI(localizedLibPath);
//				}
//				if (defaultLibraryURI_NL != null && openDefaultLibrary(defaultLibraryURI_NL)) {
//				} else {
//					openDefaultLibrary(defaultLibraryURI);
//				}
//			}
			
			
		} catch (Exception e) {
			LibraryUIPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	/**
	 * 
	 * @param path
	 * @return null if path is not on install path or not read-only or if user 
	 * declines to copy the library to a writable location. Returns
	 * a new path if the user wishes to open the library in a different location
	 * 
	 */
	private String handleLibraryOnReadOnlyInstallPath(URI libURI) {
		if (isSkipInstallPathCheck()) {
			return null;
		}
		try {
			boolean readOnly = false;
			
			// determine if path is in Installation directory
			File libPathFile = new File(libURI);
			if (!libPathFile.exists()) {
				return null;
			}
			Location installLocation = Platform.getInstallLocation();
			URL installLocationURL = installLocation.getURL();
			URI installLocationURI = new URI(NetUtil.encodeFileURL(installLocationURL.toExternalForm()));
			File installLocFile = new File(installLocationURI);
			String canonicalLibPath = NetUtil.decodedFileUrl(libPathFile.getCanonicalPath());
			String canonicalInstallPath = NetUtil.decodedFileUrl(installLocFile.getCanonicalPath());;

			if (!canonicalLibPath.startsWith(canonicalInstallPath)) {
				return null;
			}
			// show dialog, allow copy to new path

			final String defaultCopyPath = new File(new File(LibraryUIPreferences.getDefaultLibraryPath()).getParent(), libPathFile.getName()).getAbsolutePath();

			final StringBuffer newPath = new StringBuffer();
			
			UserInteractionHelper.getUIHelper().runSafely(new Runnable() {
				public void run() {
					String title = LibraryUIResources.copyLibraryDialog_title;
					String message = LibraryUIResources.copyLibraryDialog_text_readOnlyLib;
					CopyLibraryDialog dlg = new CopyLibraryDialog(Display.getCurrent().getActiveShell(), title,
							message, defaultCopyPath);

					if (dlg.open() == Dialog.OK) {
						newPath.append(dlg.getPath());
					}
				}
			}, true);
			if (newPath.length() > 0) {
				copyLibrary(libPathFile, new File(newPath.toString()));
				return newPath.toString();
			} else {
				return null;
			}

		} catch (URISyntaxException uriEx) {
			LibraryUIPlugin.getDefault().getLogger().logError(uriEx);
			return null;
		} catch (IOException ioEx) {
			LibraryUIPlugin.getDefault().getLogger().logError(ioEx);
			return null;
		}
	}
	
	/**
	 * Back up library 
	 * 
	 * @param source
	 * @param dest
	 */
	private static void copyLibrary(final File source, final File dest) {
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					// excude the non-library files that might be locked by rmc.
					// these files may cause backup to fail due to file lock.
					String excludes = ".lock"; //$NON-NLS-1$
					LayoutResources.copyDir(source, dest, "**", excludes); //$NON-NLS-1$
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		};

		UserInteractionHelper.runWithProgress(runnable,
				LibraryUIResources.copyLibraryTask_name);
	}

	/**
	 * 
	 * @return the current MethodConfiguration dropdown box
	 */
	public ConfigurationContributionItem getConfigCombo() {
		return configCombo;
	}

	public static boolean isSkipInstallPathCheck() {
		return skipInstallPathCheck;
	}

	public static void setSkipInstallPathCheck(boolean skipInstallPathCheck) {
		LibraryUIManager.skipInstallPathCheck = skipInstallPathCheck;
	}
		
}