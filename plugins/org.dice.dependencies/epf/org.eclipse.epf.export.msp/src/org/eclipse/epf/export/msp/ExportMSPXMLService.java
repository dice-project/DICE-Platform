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
package org.eclipse.epf.export.msp;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.ProcessConfigurator;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.RoleDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.process.TaskDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.ui.UIHelper;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.PredecessorList;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.library.util.LibraryUtil.ConfigAndPlugin;
import org.eclipse.epf.msproject.Assignment;
import org.eclipse.epf.msproject.DocumentRoot;
import org.eclipse.epf.msproject.MsprojectFactory;
import org.eclipse.epf.msproject.PredecessorLink;
import org.eclipse.epf.msproject.Project;
import org.eclipse.epf.msproject.Resource;
import org.eclipse.epf.msproject.Task;
import org.eclipse.epf.msproject.util.MsprojectResourceImpl;
import org.eclipse.epf.publishing.services.PublishHTMLOptions;
import org.eclipse.epf.publishing.services.PublishManager;
import org.eclipse.epf.publishing.services.PublishOptions;
import org.eclipse.epf.publishing.ui.wizards.PublishProgressMonitorDialog;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkOrderType;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.swt.widgets.Display;

/**
 * The default Export Microsoft Project Plan Service implementation.
 * 
 * @author Bingxue Xu
 * @author Kelvin Low
 * @since 1.0
 * 
 * Bugs fixed: https://bugs.eclipse.org/bugs/show_bug.cgi?id=155089
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=155086
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=155095
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=157265
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=155155
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=156959
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=157321
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=159230
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=162336
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=168801 fix for
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=176951
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=177428
 */
public class ExportMSPXMLService {

	// If true, generate debug traces.
	private static boolean debug = ExportMSPPlugin.getDefault().isDebugging();

	// All Tasks referenced by the exported Project Template.
	private List referencedTasks = new ArrayList();

	private HashMap rolesToUidMap = new HashMap();

	private HashMap taskUidToWbsWapperPathMap = new HashMap();

	private HashMap wbsWapperPathToLocalPredListMap = new HashMap();

	private HashMap wbsGuidToTaskUidMap = new HashMap();

	private HashMap wbsWrapperPathToPredListMap = new HashMap();

	// The name of the content folder. This will be named after the context.
	private String contentFolderName;

	private String abTargetDir;

	// If true, only export breakdown elements with 'isPlanned' attributes set
	// to true.
	protected boolean exportOnlyPlannedElements;

	private boolean publishContentSite = false;

	private int task_uid = 1;

	private int res_uid = 1;

	private int assign_uid = 1;

	protected MsprojectFactory projectFactory = MsprojectFactory.eINSTANCE;

	// The process to be exported.
	private Process process;
	private MethodConfiguration processConfig;

	private ConfigurableComposedAdapterFactory wbsAdapterFactory;

	// The process configurator for filtering breakdown elements in a
	// configuration.
	private ProcessConfigurator breakdownElementFilter;

	private IStructuredContentProvider wbsContentProvider;

	// Map processes to suppression objects.
	private Map suppressionMap = new HashMap();

	// The suppression object associated with the selected process to be
	// exported.
	private Suppression suppression;

	// The WBS element paths.
	private Stack elementPaths = new Stack();

	private Object currentElement;

	private String currentElementPath;

	private PredecessorList currentPredList;
	
	private HashMap<Task, WorkBreakdownElement> taskToWbeMap;

	/**
	 * Creates a new instance.
	 */
	public ExportMSPXMLService() {
		super();
	}

	ConfigAndPlugin tempConfigAndPlugin;
	/**
	 * Export a process to a Microsoft Project XML file.
	 * 
	 * @param process
	 *            a capability pattern or delivery process
	 * @param exportOptions
	 *            a collection of user specified export options
	 * @throws ExportMSPServiceException
	 *             if an error occurs while executing the operation
	 */
	public boolean export(Process process, ExportMSPOptions exportOptions)
			throws ExportMSPServiceException {
		
		tempConfigAndPlugin = null;
		boolean result = false;
		try {
			Scope scope = ProcessScopeUtil.getInstance().getScope(process);
			MethodConfiguration config = exportOptions.getMethodConfiguration();
			if (config == scope) {
				List<Process> list = new ArrayList<Process>();
				list.add(process);
				tempConfigAndPlugin = LibraryUtil.addTempConfigAndPluginToCurrentLibrary(list);
				if (tempConfigAndPlugin != null && tempConfigAndPlugin.config != null) {
					config = tempConfigAndPlugin.config;					
				}
			}
			IRealizationManager mgr = ConfigurationHelper.getDelegate().getRealizationManager(config);
			if (mgr != null) {
				mgr.updateProcessModel(process);
			}
			
			result =  export_(process, exportOptions, config);
		} finally {
			LibraryUtil.removeTempConfigAndPluginFromCurrentLibrary(tempConfigAndPlugin);
			tempConfigAndPlugin = null;
		}
		
		return result;
	}
	
	private boolean export_(Process process, ExportMSPOptions exportOptions, MethodConfiguration config)
			throws ExportMSPServiceException {
		String msprojectName = exportOptions.getMSProjectName();
		File exportDir = exportOptions.getExportDir();
		publishContentSite = exportOptions.getPublishWebSite();
		PublishOptions publishingOptions = exportOptions.getPublishingOptions();
		processConfig = config;
		if (debug) {
			System.out.println("$$$ exporting to Microsoft Project!"); //$NON-NLS-1$
			System.out.println("$$$ process          = " + process); //$NON-NLS-1$
			System.out.println("$$$ configuration    = " + processConfig); //$NON-NLS-1$
			System.out.println("$$$ msprojectName    = " //$NON-NLS-1$
					+ msprojectName);
			System.out
					.println("$$$ targetDir                 = " + exportDir.getAbsolutePath()); //$NON-NLS-1$
			System.out.println("$$$ exportOnlyPlannedElements = " //$NON-NLS-1$
					+ exportOnlyPlannedElements);
			System.out.println("$$$ publishConfigOptions      = " //$NON-NLS-1$
					+ publishingOptions);
		}

		// construct the export target xml file path
		if (!exportDir.exists()) {
			exportDir.mkdirs();
		}
		abTargetDir = exportDir.getAbsolutePath();
		Path exportPath = new Path(abTargetDir);
		boolean endWithXmlExt = msprojectName.toLowerCase().endsWith(".xml"); //$NON-NLS-1$
		String exportPathStr = (exportPath.append(endWithXmlExt ? msprojectName
				: (msprojectName + ".xml"))).toOSString(); //$NON-NLS-1$
		if (debug)
			System.out.println("$$$ exportPathStr                 = " //$NON-NLS-1$
					+ exportPathStr);

		// construct the empty MS project xml template file path
		String emptyTemplateFile = ExportMSPPlugin.getDefault()
				.getInstallPath()
				+ "template" //$NON-NLS-1$
				+ File.separator + "msproject_2003_template.xml"; //$NON-NLS-1$
		if (debug)
			System.out.println("$$$ emptyTemplateFile             = " //$NON-NLS-1$
					+ emptyTemplateFile);

		// copy the empty template MS project xml file to the export target
		try {
			File src = new File(emptyTemplateFile);
			File dst = new File(exportPathStr);
			FileUtil.copyFile(src, dst);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Project project = null;
		URI fileURI = URI.createFileURI(exportPathStr);

		try {

			XMLResource res = new MsprojectResourceImpl(fileURI);
			res.getDefaultSaveOptions().put(
					XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
			res.getDefaultLoadOptions().put(
					XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
			res.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION,
					Boolean.TRUE);
			res.getDefaultSaveOptions().put(
					XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE,
					Boolean.TRUE);
			res.getDefaultLoadOptions().put(
					XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
			res.load(null);

			DocumentRoot docRoot = (DocumentRoot) res.getEObject("/"); //$NON-NLS-1$
			project = (Project) docRoot.getProject();

			taskToWbeMap = new HashMap<Task, WorkBreakdownElement>();
			if (! generateMSProject(process, project, exportOptions)) {
				return false;
			}

			res.save(null);

			if (debug) {
				printMSProject(project);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new ExportMSPException(e);
		}

		return true;
	}

	/**
	 * Exports a capability pattern or delivery process to a Microsoft Project
	 * XML file.
	 * 
	 * @param process
	 *            a capability pattern or delivery process
	 * @param project
	 *            a Microsoft Project object
	 * @param exportOptions
	 *            a collection of user specified export options
	 * @throws ExportMSPServiceException
	 *             if an error occurs while executing the operation
	 */
	public boolean generateMSProject(Process process, Project project,
			ExportMSPOptions exportOptions) throws Exception {
		if (process == null || project == null) {
			throw new IllegalArgumentException();
		}

		try {
			MethodConfiguration config = tempConfigAndPlugin == null ? exportOptions
					.getMethodConfiguration()
					: tempConfigAndPlugin.config;
			if (config == null) {
				// Get the default method configuration associated with the
				// process.
				if (process instanceof DeliveryProcess) {
					config = ((DeliveryProcess) process).getDefaultContext();
				} else if (process instanceof CapabilityPattern) {
					config = ((CapabilityPattern) process).getDefaultContext();
				} else {
					throw new IllegalArgumentException();
				}
			}

			// Create the sub folder to store the published HTML content files.
			contentFolderName = config.getName();

			PublishOptions publishingOptions = exportOptions
					.getPublishingOptions();
			if (publishContentSite && publishingOptions != null) {
				File contentDir = new File(exportOptions.getExportDir(),
						contentFolderName);
				if (!contentDir.exists()) {
					contentDir.mkdirs();
				}
				if (debug) {
					System.out.println("$$$ vieBuilder methodConfig = " //$NON-NLS-1$
							+ config);
					System.out.println("$$$ vieBuilder publishConfigOptions = " //$NON-NLS-1$
							+ publishingOptions);
				}

				// Publish the associated configuration.
				if (!publishConfiguration(contentDir.getAbsolutePath(), config,
						publishingOptions)) {
					return false;
				}
			}

			exportOnlyPlannedElements = exportOptions
					.getExportOnlyPlannedWBSElements();

			// Generate the Microsoft Project XML file.
			// populate the project's attributes
			project.setName(process.getName());
			project.setStartDate(new Date());
			project.setCreationDate(new Date());
			project.setLastSaved(new Date());
			project.setFinishDate(new Date());
			
			setProjectExtendedAttributes(project);

			generateOperatonRun(process, project, config);
			
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	private void generateOperatonRun(Process process, Project project,
			MethodConfiguration config) throws InvocationTargetException,
			InterruptedException {
		final Process fprocess = process;
		final Project fproject = project;
		final MethodConfiguration fconfig = config;
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					monitor.beginTask(ExportMSPResources.exportMSPWizard_title,
							IProgressMonitor.UNKNOWN);

					generate(fprocess, fproject, fconfig);
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		ProgressMonitorDialog pmDialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
		pmDialog.run(true, false, op);
	}

	private void generate(Process process, Project project,
			MethodConfiguration config) throws Exception {
		generateProjectResources(process, config, project);
		generateProjectTasks(process, config, project);
		generateLinks(process, project);
		generateWPDs(config);
	}

	/**
	 * Sets the export options.
	 * 
	 * @param exportOptions
	 *            a collection of user specified export options
	 */
	protected void setExportOptions(ExportOptions exportOptions) {
		Boolean exportOnlyPlannedElements = (Boolean) exportOptions
				.get(ExportOptions.EXPORT_ONLY_PLANNED_ELEMENTS);
		this.exportOnlyPlannedElements = exportOnlyPlannedElements
				.booleanValue();
	}

	/**
	 * Generates the project resources for all the task descriptors in the
	 * process.
	 * 
	 * @param process
	 *            a process
	 * @param config
	 *            a method configuration used to filter the work breakdown
	 *            elements in the process
	 * @param project
	 *            an object to store the generated Microsoft Project WBS
	 * @throws Exception
	 *             if an error occurs while generating the Microsoft Project WBS
	 */
	protected void generateProjectResources(Process process,
			MethodConfiguration config, Project project) throws Exception {
		ComposedAdapterFactory adapterFactory = null;
		try {
			adapterFactory = TngAdapterFactory.INSTANCE
					.createTBSComposedAdapterFactory();
			if (adapterFactory instanceof ConfigurableComposedAdapterFactory) {
				((ConfigurableComposedAdapterFactory) adapterFactory)
						.setFilter(new ProcessConfigurator(config));
			}
			IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
					adapterFactory);

			List elements = process.getBreakdownElements();
			if (elements.size() > 0) {
				generateProjectResource(contentProvider,
						(BreakdownElement) process, project);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (adapterFactory != null) {
				adapterFactory.dispose();
			}
		}
	}

	/**
	 * Generates the project resources for all the role descriptors in the
	 * process.
	 */
	protected void generateProjectResource(
			IStructuredContentProvider contentProvider,
			BreakdownElement breakdownElement, Project project)
			throws Exception {
		if (breakdownElement instanceof WorkProductDescriptor
				|| breakdownElement.getSuppressed().booleanValue()
				|| (exportOnlyPlannedElements && !breakdownElement
						.getIsPlanned().booleanValue())) {
			return;
		}

		if (breakdownElement instanceof RoleDescriptor) {
			addResource(breakdownElement, project);
			return;
		}

		if (contentProvider != null) {
			Object[] elements = contentProvider.getElements(breakdownElement);
			for (int i = 0; i < elements.length; i++) {
				Object element = elements[i];
				if (element instanceof RoleDescriptorWrapperItemProvider) {
					RoleDescriptorWrapperItemProvider provider = (RoleDescriptorWrapperItemProvider) element;
					Object value = provider.getValue();
					if (value instanceof RoleDescriptor) {
						addResource((BreakdownElement) value, project);
					}
				} else if (element instanceof RoleDescriptor) {
					addResource((BreakdownElement) element, project);
				} else if (element instanceof BreakdownElementWrapperItemProvider) {
					BreakdownElementWrapperItemProvider provider = (BreakdownElementWrapperItemProvider) element;
					Object value = provider.getValue();
					if (value instanceof WorkBreakdownElement) {
						generateProjectResource(contentProvider,
								(WorkBreakdownElement) value, project);
					}
				} else if (element instanceof WorkBreakdownElement) {
					generateProjectResource(contentProvider,
							(WorkBreakdownElement) element, project);
				}
			}
		}

	}

	// for each RoleDescriptor, create a MS Project resource
	// for the RoleDescriptor and its underlying role, remember the assigned
	// res_uid
	protected void addResource(BreakdownElement breakdownElement, Project proj)
			throws Exception {
		if (!(breakdownElement instanceof RoleDescriptor)) {
			return;
		}

		// check the existence of the underlying role of this roleDescriptor
		// and skip the creation if a resource for the role already exists
		RoleDescriptor roleDescriptor = (RoleDescriptor) breakdownElement;
		if (debug)
			System.out.println("$$$ handle RoleDescriptor = " + roleDescriptor); //$NON-NLS-1$
		Role ref_edRole = roleDescriptor.getRole();
		if (debug)
			System.out.println("$$$ handle Ref-ed Role = " + ref_edRole); //$NON-NLS-1$

		// check the need to add a new resource based on RoleDescriptor's disply
		// name
		boolean newRDResource = true;
		String rdResourceName = getDisplayName(roleDescriptor);
		if (rolesToUidMap.get(rdResourceName) != null) {
			newRDResource = false;
		}

		// check the need to add a new resource based on associated role's
		// disply name
		boolean newRoleReource = false;
		String roleResourceName = null;
		if (ref_edRole != null
				&& rolesToUidMap
						.get((roleResourceName = getDisplayName(ref_edRole))) == null
				&& !roleResourceName.equalsIgnoreCase(rdResourceName)) {
			newRoleReource = true;
		}

		// create a resource for the RoleDescriptor
		if (newRDResource) {
			Resource aRes = projectFactory.createResource();

			aRes.setUID(BigInteger.valueOf(res_uid));
			aRes.setID(BigInteger.valueOf(res_uid));
			aRes.setName(rdResourceName);
			proj.getResources().getResource().add(aRes);

			rolesToUidMap.put(rdResourceName, BigInteger.valueOf(res_uid));
			res_uid++;
		}

		// create a resource for the underlying associated role too
		if (newRoleReource) {
			Resource aRes = projectFactory.createResource();

			aRes.setUID(BigInteger.valueOf(res_uid));
			aRes.setID(BigInteger.valueOf(res_uid));
			aRes.setName(roleResourceName);
			proj.getResources().getResource().add(aRes);

			rolesToUidMap.put(roleResourceName, BigInteger.valueOf(res_uid));
			res_uid++;
		}
	}

	/**
	 * Generates the MS Project WBS for a capability pattern or delivery
	 * process.
	 * 
	 * @param process
	 *            a process
	 * @param config
	 *            a method configuration used to filter the work breakdown
	 *            elements in the process
	 * @param project
	 *            an object to store the generated Microsoft Project WBS
	 * @throws Exception
	 *             if an error occurs while generating the Microsoft Project WBS
	 */
	protected void generateProjectTasks(Process process,
			MethodConfiguration config, Project project) throws Exception {
		// Save the reference to the exported process.
		this.process = process;

		wbsAdapterFactory = null;
		try {
			// Add the suppression object associated with the process to be
			// exported
			// to the suppression map.
			suppression = new Suppression(process);
			suppressionMap.put(process, suppression);

			wbsAdapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
					.createWBSComposedAdapterFactory();
			breakdownElementFilter = new ProcessConfigurator(config);
			wbsAdapterFactory.setFilter(breakdownElementFilter);

			wbsContentProvider = new AdapterFactoryContentProvider(
					wbsAdapterFactory);

			// test
			// enumerateProcessPredecessorLists();

			currentElement = process;
			if (process instanceof CapabilityPattern) {
				generateProjectTask(wbsContentProvider,
						(BreakdownElement) process, 1, project);
			} else {
//				List breakdownElements = process.getBreakdownElements();
//				if (breakdownElements.size() > 0) {
					generateProjectTask(wbsContentProvider,
							(BreakdownElement) process, 1, project);
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (wbsAdapterFactory != null) {
				wbsAdapterFactory.dispose();
			}
			if (suppressionMap != null) {
				suppressionMap.clear();
				suppressionMap = null;
			}
			if (wbsContentProvider != null) {
				wbsContentProvider.dispose();
			}
		}
	}

	private void enumerateProcessPredecessorLists() {
		// WBS tree
		Iterator wbsTreeIterator = new AdapterFactoryTreeIterator(
				wbsAdapterFactory, process);
		Object obj;
		while (wbsTreeIterator.hasNext()) {
			obj = wbsTreeIterator.next();
			System.out.println("treeIterator: " + obj); //$NON-NLS-1$
			IBSItemProvider adapter = (IBSItemProvider) wbsAdapterFactory
					.adapt(obj, ITreeItemContentProvider.class);
			PredecessorList predList = adapter.getPredecessors();
			for (Iterator iter = predList.iterator(); iter.hasNext();) {
				IBSItemProvider e = (IBSItemProvider) iter.next();
				System.out.println("    predList: " + e); //$NON-NLS-1$
				if (e instanceof ItemProviderAdapter) {
					System.out.println("    unwrappedPredList: " //$NON-NLS-1$
							+ ((ItemProviderAdapter) e).getTarget());
				}
			}
		}
	}

	/**
	 * Generates the Microsft Project task for a breakdown element.
	 * 
	 * @param contentProvider
	 *            a content provider
	 * @param breakdownElement
	 *            a breakdown element
	 * @param strBuf
	 *            a <code>StringBuffer</code> to store the generated XML
	 */
	protected void generateProjectTask(
			IStructuredContentProvider contentProvider,
			Object elementOrWrapper, int taskOLevel, Project proj)
			throws Exception {

		WorkBreakdownElement breakdownElement = (WorkBreakdownElement) TngUtil
				.unwrap(elementOrWrapper);

		// not really neccessary here
		if (breakdownElement instanceof WorkProductDescriptor
				|| breakdownElement instanceof RoleDescriptor
				|| breakdownElement.getSuppressed().booleanValue()
				|| (exportOnlyPlannedElements && !breakdownElement
						.getIsPlanned().booleanValue())) {
			return;
		}

		boolean isSubTasksPlanned = false;
		if (exportOnlyPlannedElements && breakdownElement instanceof Activity
				&& contentProvider != null) {
			Object[] elements = contentProvider.getElements(elementOrWrapper);
			for (int i = 0; i < elements.length; i++) {
				Object element = elements[i];
				if (element instanceof Activity) {
					if (!exportOnlyPlannedElements
							|| ((Activity) element).getIsPlanned()
									.booleanValue()) {
						isSubTasksPlanned = true;
						break;
					}
				} else if (element instanceof ActivityWrapperItemProvider) {
					Object wrappedElement = TngUtil.unwrap(element);
					if (wrappedElement instanceof Activity) {
						if (!exportOnlyPlannedElements
								|| ((Activity) wrappedElement).getIsPlanned()
										.booleanValue()) {
							isSubTasksPlanned = true;
							break;
						}
					}
				} else if (element instanceof TaskDescriptor) {
					TaskDescriptor descriptor = (TaskDescriptor) element;
					if (!exportOnlyPlannedElements
							|| descriptor.getIsPlanned().booleanValue()) {
						isSubTasksPlanned = true;
						break;
					}
				} else if (element instanceof TaskDescriptorWrapperItemProvider) {
					Object wrappedElement = TngUtil.unwrap(element);
					if (wrappedElement instanceof TaskDescriptor) {
						TaskDescriptor descriptor = (TaskDescriptor) wrappedElement;
						if (!exportOnlyPlannedElements
								|| descriptor.getIsPlanned().booleanValue()) {
							isSubTasksPlanned = true;
							break;
						}
					}
				}
			}
		}

		// create a task for the WorkBreakdownElement
		// decided just to check the isPlanned flag on the WBS
		boolean isPlannedTask = breakdownElement.getIsPlanned().booleanValue();
		Task newTask = null;
		if (!exportOnlyPlannedElements || exportOnlyPlannedElements
				&& isPlannedTask) {
			boolean suppressed = isSuppressed(breakdownElement);
			if (!suppressed) {
				newTask = addTask(elementOrWrapper, taskOLevel, proj);
			}
		}

		// if export planned only wbs, then we need to do the role rollup
		// calculation
		if (exportOnlyPlannedElements && newTask != null
				&& breakdownElement instanceof Activity
				&& contentProvider != null && !isSubTasksPlanned) {
			ArrayList rollupRoles = new ArrayList();
			boolean rb = calculateRollupRoles(contentProvider,
					breakdownElement, rollupRoles);
			// if the whole subtree of the breakdownElemnt is not planned
			// then roll up all the roles
			if (!rb) {
				HashSet rolesSet = new HashSet(rollupRoles);
				for (Iterator iter = rolesSet.iterator(); iter.hasNext();) {
					String roleName = (String) iter.next();
					addAssignment(roleName, newTask.getUID().intValue(), proj);
				}
			}
		}

		// export the next level wbs
		if (contentProvider != null) {
			boolean suppressed = isSuppressed(breakdownElement);
			if (!suppressed) {
				elementPaths.push(breakdownElement.getGuid());
				Object[] elements = contentProvider
						.getElements(elementOrWrapper);
				for (int i = 0; i < elements.length; i++) {
					Object element = elements[i];

					// get the current elementOrWrapper and the hierarcal guid
					// path to it
					currentElement = element;
					String[] paths = new String[elementPaths.size()];
					elementPaths.toArray(paths);
					StringBuffer pathStr = new StringBuffer();
					for (int j = 0; j < paths.length; j++) {
						pathStr.append(paths[j] + "."); //$NON-NLS-1$
					}
					if (currentElement instanceof BreakdownElementWrapperItemProvider) {
						Object wrapped = TngUtil.unwrap(element);
						pathStr.append(((BreakdownElement) wrapped).getGuid());
					} else {
						pathStr.append(((BreakdownElement) currentElement)
								.getGuid());
					}
					currentElementPath = pathStr.toString();
					// if (debug) System.out.println(" path: " +
					// currentElementPath);

					generateProjectTask(contentProvider, element,
							taskOLevel + 1, proj);
				}
				generateLinks(process, proj);
				elementPaths.pop();
			}
		}

	}

	/**
	 * Checks whether the given object is a suppressed work breakdown element in
	 * its owning process.
	 * 
	 * @param object
	 *            an object
	 * @return <ocde>true</code> if the object is a suppressed work breakdown
	 *         element in its owning process.
	 */
	protected boolean isSuppressed(Object object) {
		if (object != null) {
			if (object instanceof Descriptor) {
				if (debug)
					System.out.println("Descriptor = " //$NON-NLS-1$
							+ ((Descriptor) object).getName());
			}
			Process owningProcess = TngUtil.getOwningProcess(object);
			Suppression owningProcessSuppression = (Suppression) suppressionMap
					.get(owningProcess);
			if (owningProcessSuppression == null) {
				owningProcessSuppression = new Suppression(owningProcess);
				suppressionMap.put(owningProcess, owningProcessSuppression);
			}
			if (owningProcessSuppression.isSuppressed(object)) {
				return true;
			}
			if (owningProcess != process
					&& object instanceof WorkBreakdownElement) {
				elementPaths.push(((WorkBreakdownElement) object).getGuid());
				String[] paths = new String[elementPaths.size()];
				elementPaths.toArray(paths);
				Object wrapper = suppression.getObjectByPath(paths,
						wbsAdapterFactory);
				elementPaths.pop();
				return suppression.isSuppressed(wrapper);
			}
		}
		return false;
	}

	/**
	 * Adds a task to a Microsoft Project.
	 * 
	 * @param element
	 *            a work breakdown element
	 * @param taskOLevel
	 *            the outline level
	 * @param project
	 *            a Microsoft Project object
	 * @return the newly added task
	 * @throws Exception
	 *             if an error occurrs while performing the operation
	 */
	protected Task addTask(Object elementOrWrapper, int taskOLevel,
			Project project) throws Exception {
		Task task = addTask_(elementOrWrapper, taskOLevel, project);
		if (task != null) {
			WorkBreakdownElement element = (WorkBreakdownElement) TngUtil
				.unwrap(elementOrWrapper);
			taskToWbeMap.put(task, element);
		}
		return task;
	}
	
	private Task addTask_(Object elementOrWrapper, int taskOLevel,
			Project project) throws Exception {

		if (elementOrWrapper == null) {
			return null;
		}

		WorkBreakdownElement element = (WorkBreakdownElement) TngUtil
				.unwrap(elementOrWrapper);
		
		Task task = projectFactory.createTask();
		task.setName(getDisplayName(element));
		task.setOutlineLevel(BigInteger.valueOf(taskOLevel));
		task.setUID(BigInteger.valueOf(task_uid));
		task.setID(BigInteger.valueOf(task_uid));
		task.setType(BigInteger.valueOf(0));

		GregorianCalendar gcDate = new GregorianCalendar();
		// gcDate.add(GregorianCalendar.DATE, 80);
		Date startDate = gcDate.getTime();
		task.setStart(startDate);
		// task.setDuration("PT20H0M0S");

		String notes = getBriefDescription(element);
		if (notes != null && notes.trim().length() > 0) {
			task.setNotes(notes);
		}

		if (element instanceof Milestone) {
			task.setMilestone(true);
			task.setDurationFormat(BigInteger.valueOf(7));
			task.setDuration("PT0H0M0S");
		} else {
			task.setMilestone(false);
		}

		// Assign the task to all the associated roles.
		List rolesList = null;
		if (element instanceof TaskDescriptor) {
			rolesList = getRolesForTaskD((TaskDescriptor) element);
		} else if (element instanceof org.eclipse.epf.uma.Task) {
			rolesList = getRolesForTask((org.eclipse.epf.uma.Task) element);
		}
		if (rolesList != null) {
			for (Iterator iter = rolesList.iterator(); iter.hasNext();) {
				String roleName = (String) iter.next();
				addAssignment(roleName, task_uid, project);
			}
		}

		// Add the generated URL link to the task.
		if (publishContentSite) {
			String linkURL = getElementURL(element);
			if (element instanceof TaskDescriptor) {
				TaskDescriptor taskDescriptor = (TaskDescriptor) element;
				org.eclipse.epf.uma.Task ref_edTask = taskDescriptor.getTask();
				referencedTasks.add(ref_edTask);
			}
			task.setHyperlinkAddress(linkURL);
		}

		// aTask.setType(new BigInteger("1"));
		// aTask.setStart(new Date());
		// aTask.setSummary(false);
		// aTask.setConstraintType(new BigInteger("2"));

		// Add the task to the Microsoft Project object.
		project.getTasks().getTask().add(task);

		if (debug) {
			System.out.println("$$$ theCurrent element: taskUid=" + task_uid //$NON-NLS-1$
					+ ", " + currentElement); //$NON-NLS-1$
			System.out.println("                  path: " + currentElementPath); //$NON-NLS-1$
		}

		// Store the task's local predecessors.
		storeTaskPredecessors(element);

		taskUidToWbsWapperPathMap.put(BigInteger.valueOf(task_uid),
				currentElementPath);
		wbsGuidToTaskUidMap
				.put(element.getGuid(), BigInteger.valueOf(task_uid));

		// get predecessors of a work breakdown element
		IBSItemProvider adapter = (IBSItemProvider) wbsAdapterFactory.adapt(
				elementOrWrapper, ITreeItemContentProvider.class);
		PredecessorList currentPredList = adapter.getPredecessors();
		List<MethodElement> guidPredList = new ArrayList<MethodElement>();
		for (Iterator iter = currentPredList.iterator(); iter.hasNext();) {
			Object e = (Object) iter.next();
			if (debug)
				System.out.println("    wrapperPredListMember: " + e); //$NON-NLS-1$
			Object unwrappedE = TngUtil.unwrap(e);
			if (unwrappedE instanceof WorkBreakdownElement) {
				//guidPredList.add(((WorkBreakdownElement) unwrappedE).getGuid());
				guidPredList.add((WorkBreakdownElement) unwrappedE);
				if (debug)
					System.out.println("    unwrappedPredListMember: " //$NON-NLS-1$
							+ unwrappedE);
			}
			if (e instanceof ItemProviderAdapter) {
				unwrappedE = ((ItemProviderAdapter) e).getTarget();
				//guidPredList.add(((BreakdownElement) unwrappedE).getGuid());
				guidPredList.add((BreakdownElement) unwrappedE);
				if (debug)
					System.out.println("    unwrappedPredListMember: " //$NON-NLS-1$
							+ unwrappedE);
			}
		}
		wbsWrapperPathToPredListMap.put(currentElementPath, guidPredList);
		if (debug)
			System.out.println("    __wrapperPredList: " + guidPredList); //$NON-NLS-1$

		task_uid++;

		return task;
	}

	private String getBriefDescription(WorkBreakdownElement element) {
		if (element == null)
			return null;

		String briefDesc = element.getBriefDescription();
		if ((briefDesc == null || briefDesc.trim().length() <= 0)) {
			if (element instanceof TaskDescriptor) {
				TaskDescriptor taskDescriptor = (TaskDescriptor) element;
				org.eclipse.epf.uma.Task ref_edTask = taskDescriptor.getTask();
				if (ref_edTask != null) {
					MethodElement realizedElement = ConfigurationHelper.getCalculatedElement(ref_edTask, processConfig);
					if (debug) System.out.println("$$$ realizedElement = " + realizedElement); //$NON-NLS-1$
					if (realizedElement != null) {
						EAttribute attribute = UmaPackage.eINSTANCE.getMethodElement_BriefDescription();
						Object briefDescObj = ConfigurationHelper.calcAttributeFeatureValue(realizedElement, attribute, processConfig);
						briefDesc = briefDescObj.toString();
						if (debug) System.out.println("$$$ realized brief desc = " + briefDesc);	 //$NON-NLS-1$
					}
				}
			}
		}

		return briefDesc;
	}

	private void addAssignment(String resName, int taskUid, Project proj) {
		Assignment assignment = projectFactory.createAssignment();

		assignment.setUID(BigInteger.valueOf(assign_uid));
		assignment.setTaskUID(BigInteger.valueOf(taskUid));
		BigInteger resID = (BigInteger) rolesToUidMap.get(resName);
		assignment.setResourceUID(resID);

		proj.getAssignments().getAssignment().add(assignment);

		assign_uid++;
	}

	private List getRolesForTaskD(TaskDescriptor taskDescriptor) {
		ArrayList rolesList = new ArrayList();

		for (RoleDescriptor rd : taskDescriptor.getPerformedPrimarilyBy()) {

			// RoleDescriptor roleDescrp =
			// taskDescriptor.getPerformedPrimarilyBy();
			RoleDescriptor roleDescrp = (RoleDescriptor) ConfigurationHelper
					.getCalculatedElement(rd, breakdownElementFilter
							.getMethodConfiguration());

			if (roleDescrp != null) {
				rolesList.add(getDisplayName(roleDescrp));
			}
		}

		// List roleDescrpList =
		// taskDescriptor.getAdditionallyPerformedBy();
		List roleDescrpList = ConfigurationHelper.getCalculatedElements(
				taskDescriptor.getAdditionallyPerformedBy(),
				breakdownElementFilter.getMethodConfiguration());

		for (Iterator iter = roleDescrpList.iterator(); iter.hasNext();) {
			RoleDescriptor roleDescrp = (RoleDescriptor) iter.next();
			rolesList.add(getDisplayName(roleDescrp));
		}

		return rolesList;
	}

	private List getRolesForTask(org.eclipse.epf.uma.Task umaTask) {
		ArrayList rolesList = new ArrayList();

		for (Role role: umaTask.getPerformedBy()) {
			if (role != null) {
				rolesList.add(getDisplayName(role));
			}
		}

		List list = umaTask.getAdditionallyPerformedBy();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Role role = (Role) iter.next();
			rolesList.add(getDisplayName(role));
		}

		return rolesList;
	}

	private boolean calculateRollupRoles(
			IStructuredContentProvider contentProvider,
			BreakdownElement breakdownElement, List rollupRoles) {

		boolean isAnySubTaskPlanned = false;

		Object[] elements = contentProvider.getElements(breakdownElement);
		for (int i = 0; i < elements.length; i++) {
			Object element = elements[i];
			if (element instanceof Activity) {
				if (!exportOnlyPlannedElements
						|| ((Activity) element).getIsPlanned().booleanValue()) {
					isAnySubTaskPlanned = true;
				} else {
					isAnySubTaskPlanned = calculateRollupRoles(contentProvider,
							(BreakdownElement) element, rollupRoles);
				}
			} else if (element instanceof ActivityWrapperItemProvider) {
				Object wrappedElement = TngUtil.unwrap(element);
				if (wrappedElement instanceof Activity) {
					if (!exportOnlyPlannedElements
							|| ((Activity) wrappedElement).getIsPlanned()
									.booleanValue()) {
						isAnySubTaskPlanned = true;
					} else {
						isAnySubTaskPlanned = calculateRollupRoles(
								contentProvider,
								(BreakdownElement) wrappedElement, rollupRoles);
					}
				}
			} else if (element instanceof TaskDescriptor) {
				TaskDescriptor descriptor = (TaskDescriptor) element;
				if (!exportOnlyPlannedElements
						|| descriptor.getIsPlanned().booleanValue()) {
					isAnySubTaskPlanned = true;
				} else {
					rollupRoles
							.addAll(getRolesForTaskD((TaskDescriptor) element));
				}
			} else if (element instanceof TaskDescriptorWrapperItemProvider) {
				Object wrappedElement = TngUtil.unwrap(element);
				if (wrappedElement instanceof TaskDescriptor) {
					TaskDescriptor descriptor = (TaskDescriptor) wrappedElement;
					if (!exportOnlyPlannedElements
							|| descriptor.getIsPlanned().booleanValue()) {
						isAnySubTaskPlanned = true;
					} else {
						rollupRoles
								.addAll(getRolesForTaskD((TaskDescriptor) wrappedElement));
					}
				}
			}
		}

		return isAnySubTaskPlanned;
	}

	/**
	 * Retrieves a work breakdown element's predecessors, stores them in an
	 * <ocde>ArrayList</code>, and put it into a map.
	 * 
	 * @param element
	 *            a work breakdown element
	 */
	private void storeTaskPredecessors(WorkBreakdownElement element) {
		if (element == null) {
			return;
		}

		List predecessors = element.getLinkToPredecessor();
		List predList = new ArrayList();
		if (predecessors != null && predecessors.size() > 0) {
			for (Iterator i = predecessors.iterator(); i.hasNext();) {
				WorkOrder workOrder = (WorkOrder) i.next();
				BreakdownElement predecessor = workOrder.getPred();
				if (debug) {
					System.out.println("    localPredListMember: " //$NON-NLS-1$
							+ predecessor);
				}
				if (predecessor != null) {
					String predGuid = predecessor.getGuid();
					if (predGuid != null)
						predList.add(predGuid);
				}
			}
		}
		if (debug) {
			System.out.println("   local predlist for " + element.getName() //$NON-NLS-1$
					+ " = " + predList.toString()); //$NON-NLS-1$
		}

		wbsWapperPathToLocalPredListMap.put(currentElementPath, predList);
	}

	protected void generateLinks(Process process, Project project)
			throws Exception {
		EList tasks = project.getTasks().getTask();
		for (Iterator iter = tasks.iterator(); iter.hasNext();) {
			Task task = (Task) iter.next();
			
			BigInteger taskUid = task.getUID();
			// skip the MS project hidden task with uid = 0
			if (taskUid.intValue() == 0)
				continue;

			String wbsPathStr = (String) taskUidToWbsWapperPathMap.get(taskUid);

			HashMap<String, WorkOrder> predGuidToWOMap = getPredGuidToWOMap(taskToWbeMap.get(task));
			
			// List predList = (ArrayList)
			// wbsWapperPathToLocalPredListMap.get(wbsPathStr);
			List predList = (ArrayList) wbsWrapperPathToPredListMap
					.get(wbsPathStr);
			if (predList != null) {
				List removeList = new ArrayList();
				for (Iterator iterator = predList.iterator(); iterator
						.hasNext();) {
					//String predGuid = (String) iterator.next();
					MethodElement predElem = (MethodElement) iterator.next();
					String predGuid = predElem.getGuid();
					
					BigInteger predTaskUid = (BigInteger) wbsGuidToTaskUidMap
							.get(predGuid);

					if (predTaskUid == null
							|| predTaskUid.intValue() == taskUid.intValue()) {
						continue;
					}

					if (debug) {
						System.out.println("$$$ taskUid = " + taskUid //$NON-NLS-1$
								+ ", wbsPathStr = " + wbsPathStr); //$NON-NLS-1$
						System.out.println("    localPredList: " + predList); //$NON-NLS-1$
						System.out.println("    found pred guid: " + predGuid); //$NON-NLS-1$
					}

					//removeList.add(predGuid);
					removeList.add(predElem);

					PredecessorLink preLink = projectFactory
							.createPredecessorLink();
					task.getPredecessorLink().add(preLink);
					preLink.setPredecessorUID(predTaskUid);
					
					//preLink.setType(new BigInteger("1")); //$NON-NLS-1$
					BigInteger bigInt = getLinkTypeInt(predGuidToWOMap.get(predGuid));
					if (bigInt != null) {
						preLink.setType(bigInt);
					}
					
					preLink.setCrossProject(false);
					preLink.setLinkLag(new BigInteger("0")); //$NON-NLS-1$
					preLink.setLagFormat(new BigInteger("7")); //$NON-NLS-1$
				}
				predList.removeAll(removeList);
			}
		}
	}

	private BigInteger getLinkTypeInt(WorkOrder wo) {
		if (wo == null) {
			if (debug) {
				System.out.println("Warning> getLinkTypeInt, wo == null.");//$NON-NLS-1$
			}
			return null;
		}
		WorkOrderType woType = wo.getLinkType();
		if (woType == WorkOrderType.FINISH_TO_START) {
			return new BigInteger("1"); //$NON-NLS-1$
		} 
		if (woType == WorkOrderType.START_TO_START) {
			return new BigInteger("3"); //$NON-NLS-1$
		} 
		if (woType == WorkOrderType.FINISH_TO_FINISH) {
			return new BigInteger("0"); //$NON-NLS-1$
		}
		if (woType == WorkOrderType.START_TO_FINISH) {
			return new BigInteger("2"); //$NON-NLS-1$
		}
		return null;
	}

	private HashMap<String, WorkOrder> getPredGuidToWOMap(WorkBreakdownElement wbe) {
		HashMap<String, WorkOrder> predGuidToWOMap = new HashMap<String, WorkOrder>();
		if (wbe != null) {
			List<WorkOrder> woList = wbe.getLinkToPredecessor();
			int sz = woList == null ? 0 : woList.size();
			for (int i = 0; i < sz; i++) {
				WorkOrder wo = woList.get(i);
				if (wo == null || wo.getPred() == null) {
					continue;
				}
				predGuidToWOMap.put(wo.getPred().getGuid(), wo);
			}
		}
		return predGuidToWOMap;
	}

	/**
	 * Returns the generated HTML content file URL for a method element.
	 * 
	 * @param element
	 *            a method element
	 * @return the URL of the generated HTML content file
	 */
	protected String getElementURL(MethodElement element) {
		if (element == null)
			return null;

		String elementPath = null;
		String elementFileName = null;
		try {
			elementPath = ResourceHelper.getElementPath(element);
			elementFileName = ResourceHelper.getFileName(element,
					ResourceHelper.FILE_EXT_HTML);
		} catch (Exception e) {
			e.printStackTrace();
			return ""; //$NON-NLS-1$
		}
		if (elementPath != null && elementFileName != null) {
			elementPath = elementPath.replace('\\', '/');
			elementFileName = elementFileName.replace('\\', '/');
			String url = contentFolderName
					+ "/" + elementPath + elementFileName; //$NON-NLS-1$
			return abTargetDir + File.separator + url;
		} else {
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Returns the display name of a method element.
	 * 
	 * @param element
	 *            a method element
	 * @return the display name of the given element
	 */
	protected String getDisplayName(MethodElement element) {
		String name = null;
		if (element instanceof BreakdownElement) {
			name = ProcessUtil.getPresentationName((BreakdownElement) element);
		} else if (element instanceof DescribableElement) {
			name = ((DescribableElement) element).getPresentationName();
		}
		if (name == null || name.length() == 0) {
			name = element.getName();
		}
		if (name.indexOf("\n") >= 0) {//$NON-NLS-1$
			name = name.replaceAll("\r\n", " ");//$NON-NLS-1$ //$NON-NLS-2$
		}
		return name;
		
		//This is not needed since EMF would take care of encoding
		//suring save
		//return XMLUtil.escapeAttr(name);
	}

	/**
	 * Publishes the selected configuration associated with the exported
	 * process.
	 * 
	 * @param dir
	 *            the output directory
	 * @param config
	 *            the configuration to publish
	 * @param publishConfigOptions
	 *            the publishing options
	 * @return <code>true</code> if the configuration was published
	 *         successfully, <code>false</code> otherwise
	 * @throws Exception
	 *             if an error occurs while publishing the configuration
	 */
	protected boolean publishConfiguration(String dir,
			MethodConfiguration config, PublishOptions publishConfigOptions)
			throws Exception {
		PublishManager publishMgr = null;
		try {
			publishMgr = new PublishManager();
			publishMgr.init(dir, config, new PublishHTMLOptions(
					publishConfigOptions));

			ExportMSPXMLOperation operation = new ExportMSPXMLOperation(
					publishMgr);
			PublishProgressMonitorDialog dlg = new PublishProgressMonitorDialog(
					Display.getCurrent().getActiveShell(), publishMgr
							.getViewBuilder());
			boolean success = UIHelper.runWithProgress(operation,
					dlg, true, ExportMSPResources.exportMSPWizard_title);
			if (operation.getException() != null) {
				throw operation.getException();
			}
			return success && !dlg.getProgressMonitor().isCanceled();
		} catch (Exception e) {
			throw e;
		} finally {
			if (publishMgr != null) {
				publishMgr.dispose();
				publishMgr = null;
			}
		}
	}

	/**
	 * Prints the trace information for the Microsoft Project.
	 */
	private static void printMSProject(Project project) {
		System.out.println("\n$$$ read-in project = " + project); //$NON-NLS-1$

		EList tasks = project.getTasks().getTask();
		for (Iterator iter = tasks.iterator(); iter.hasNext();) {
			Task element = (Task) iter.next();
			System.out.println("$$$ a task = " + element); //$NON-NLS-1$
		}

		EList resources = project.getResources().getResource();
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			org.eclipse.epf.msproject.Resource element = (org.eclipse.epf.msproject.Resource) iter
					.next();
			System.out.println("$$$ a resource = " + element); //$NON-NLS-1$
		}

		EList assignments = project.getAssignments().getAssignment();
		for (Iterator iter = assignments.iterator(); iter.hasNext();) {
			Assignment element = (Assignment) iter.next();
			System.out.println("$$$ an assignment = " + element); //$NON-NLS-1$
		}

		System.out.println("$$$===\n"); //$NON-NLS-1$
	}
	
	protected HashMap<Task, WorkBreakdownElement> getTaskToWbeMap() {
		return taskToWbeMap;
	}
	
	//To be overriden by subclasses
	protected void setProjectExtendedAttributes(Project project) {
	}

	//To be overriden by subclasses
	protected void generateWPDs(MethodConfiguration config) {
	}	

}
