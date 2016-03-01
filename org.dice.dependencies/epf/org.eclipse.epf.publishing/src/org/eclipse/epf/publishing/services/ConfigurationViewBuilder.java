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
package org.eclipse.epf.publishing.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.common.serviceability.DebugTrace;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.common.utils.Timer;
import org.eclipse.epf.library.configuration.ConfigurationFilter;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.configuration.PracticeSubgroupItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.Bookmark;
import org.eclipse.epf.library.layout.IElementLayout;
import org.eclipse.epf.library.layout.elements.ActivityLayout;
import org.eclipse.epf.library.layout.elements.ProcessElementItem;
import org.eclipse.epf.library.layout.elements.ProcessLayoutData;
import org.eclipse.epf.library.layout.elements.SummaryPageLayout;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.publishing.PublishingPlugin;
import org.eclipse.epf.publishing.PublishingResources;
import org.eclipse.epf.publishing.util.PublishingUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.util.AssociationHelper;

/**
 * Builds the views defined for a method configuration.
 * 
 * @author Shilpa Toraskar
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ConfigurationViewBuilder extends AbstractViewBuilder {

	private static final String PREFIX_Reference_Workflows = "Reference_Workflows"; //$NON-NLS-1$

	private static final String PREFIX_Tasks = "Tasks"; //$NON-NLS-1$

	private static final String PREFIX_ResponsibleFor_Tasks = "Primarily_Performs"; //$NON-NLS-1$

	private static final String PREFIX_ParticipatesIn_Tasks = "Additionally_Performs"; //$NON-NLS-1$

	private static final String PREFIX_Performing_Roles = "Performing_Roles"; //$NON-NLS-1$
	
	private static final String PREFIX_Input_Work_Products = "Input_Work_Products"; //$NON-NLS-1$

	private static final String PREFIX_Output_Work_Products = "Output_Work_Products"; //$NON-NLS-1$

	private static final String PREFIX_Work_Products_Created = "Responsible_For"; //$NON-NLS-1$

	private static final String PREFIX_Work_Products_Modified = "Modifies"; //$NON-NLS-1$

	private static final String PREFIX_Responsible_Role = "Responsible_Role"; //$NON-NLS-1$

	private static final String PREFIX_Containing_Work_Product = "Containing_Work_Product"; //$NON-NLS-1$

	private static final String PREFIX_Contained_Work_Products = "Contained_Work_Products"; //$NON-NLS-1$

	private static final String PREFIX_Guidances = "Guidance"; //$NON-NLS-1$

	private static final String PREFIX_InputTo_Task = "Input_to"; //$NON-NLS-1$

	private static final String PREFIX_OutputOf_Task = "Output_from"; //$NON-NLS-1$

	private static final String ICON_FOLDER = DefaultNodeIconResources
			.getIconName("folder"); //$NON-NLS-1$

	private static final String NODE_Reference_Workflows = PublishingResources.referenceWorkflowsNode_text; //$NON-NLS-1$

	private static final String NODE_Tasks = PublishingResources.taskNode_text; //$NON-NLS-1$

	private static final String NODE_ResponsibleFor_Tasks = PublishingResources.primarilyPerformsNode_text; //$NON-NLS-1$

	private static final String NODE_ParticipatesIn_Tasks = PublishingResources.additionallyPerformsNode_text; //$NON-NLS-1$

	private static final String NODE_Performing_Roles = PublishingResources.performingRolesNode_text; //$NON-NLS-1$

	private static final String NODE_Input_Work_Products = PublishingResources.inputWorkProductsNode_text; //$NON-NLS-1$

	private static final String NODE_Output_Work_Products = PublishingResources.outputWorkProductsNode_text; //$NON-NLS-1$

	private static final String NODE_Work_Products_Created = PublishingResources.responsibleForNode_text; //$NON-NLS-1$

	private static final String NODE_Work_Products_Modified = PublishingResources.modifiesNode_text; //$NON-NLS-1$

	private static final String NODE_Responsible_Role = PublishingResources.responsibleRoleNode_text; //$NON-NLS-1$

	private static final String NODE_Containing_Work_Product = PublishingResources.containingWorkProductNode_text; //$NON-NLS-1$

	private static final String NODE_Contained_Work_Products = PublishingResources.containedWorkProductsNode_text; //$NON-NLS-1$

	private static final String NODE_Guidances = PublishingResources.guidanceNode_text; //$NON-NLS-1$

	private static final String NODE_InputTo_Task = PublishingResources.inputToNode_text; //$NON-NLS-1$

	private static final String NODE_OutputOf_Task = PublishingResources.outputFromNode_text; //$NON-NLS-1$

	private static final String PROCESS_LAYOUT_DATA_FILE = "/scripts/processElementData.js"; //$NON-NLS-1$

	private static final Class ITreeItemContentProviderClass = ITreeItemContentProvider.class;

	protected static boolean debug = PublishingPlugin.getDefault()
			.isDebugging();

	protected List<Bookmark> bookmarks = new ArrayList<Bookmark>();

	protected AdapterFactory adapterFactory;

	protected IProgressMonitor monitor = null;

	protected EObjectComparator nameComparator = new EObjectComparator();

	/**
	 * Creates a new instance.
	 */
	public ConfigurationViewBuilder(ISiteGenerator siteGenerator) {
		super(siteGenerator);
	}

	/**
	 * Build the views defined in the configuration and publish the related
	 * contents.
	 * 
	 * @param monitor
	 *            IProgressMonitor
	 * @return List a list of Bookmarks for the views
	 */
	public List<Bookmark> buildViews(IProgressMonitor monitor) {
		this.monitor = monitor;

		// System.out.println("Building views..."); //$NON-NLS-1$

		long startTime = 0L;

		if (config != null) {
			
// already done in PublishManager
//			// first of all, we need to load the library,
//			// otherwise, some relationships and opposite features are not
//			// established
//			monitor.subTask(PublishingResources.loadLibraryTask_name);
//			if (profiling) {
//				startTime = System.currentTimeMillis();
//			}
//			LibraryUtil.loadAll((MethodLibrary) config.eContainer());
//			if (profiling) {
//				DebugTrace.print(this, "buildViews", "LibraryUtil.loadAll: " //$NON-NLS-1$  //$NON-NLS-2$
//								+ (System.currentTimeMillis() - startTime)
//								+ " ms"); //$NON-NLS-1$
//			}

			// create a filter that does not discard the contributors.
			// so we get the contributors in to show in the navigation tree
			IFilter configFilter = new ConfigurationFilter(config, false);
			adapterFactory = TngAdapterFactory.INSTANCE
					.getConfigurationView_AdapterFactory(configFilter);

			if (options != null && options.isPublishProcess()) {
				makeProcessClosure();
			}

			// publish all the views in the configuration
			if (profiling) {
				startTime = System.currentTimeMillis();
			}
			List views = LibraryUtil.getValidViews(config);
			for (Iterator it = views.iterator(); it.hasNext();) {
				if (monitor.isCanceled()) {
					return null;
				}

				ContentCategory v = (ContentCategory) it.next();
				if (!ConfigurationHelper.canShow(v, config)) {
					continue;
				}

				Object element = LibraryUtil.unwrap(v);
				Bookmark b = createBookmark(this.monitor, element);

				if (v == config.getDefaultView()) {
					super.defaultView = b;
				}

				// iterate thru configuration to build the view
				iterate(v, b);
				if (b.getChildCount() > 0) {
					bookmarks.add(b);
				}
			}
			if (profiling) {
				System.out.println("Time taken to publish bookmarks: " //$NON-NLS-1$
						+ (System.currentTimeMillis() - startTime) + " ms"); //$NON-NLS-1$
			}

			if (monitor.isCanceled()) {
				return null;
			}
		}

		if (profiling) {
			startTime = System.currentTimeMillis();
		}
		if (! ConfigurationHelper.serverMode) {
			publishReferenceElements();
		}		
		if (profiling) {
			DebugTrace.print(this, "publishReferenceElements", //$NON-NLS-1$
					(System.currentTimeMillis() - startTime) + " ms"); //$NON-NLS-1$
		}

		// copyIconsForNonApplet();

		if (monitor.isCanceled()) {
			return null;
		}

		// save published element urls
		if (profiling) {
			startTime = System.currentTimeMillis();
		}
		saveElementUrls();
		if (profiling) {
			DebugTrace.print(this, "saveElementUrls", //$NON-NLS-1$
					(System.currentTimeMillis() - startTime) + " ms"); //$NON-NLS-1$
		}

		return bookmarks;
	}

	/**
	 * The process element closure is generated as follows:
	 * <p>
	 * 1. publish the selected processses, which brings in all the related
	 * process elements
	 * <p>
	 * 2. publish all the referenced process elements from step 1, this brings
	 * in all the directly referenced content elements. make a first level
	 * closure to include the published elements and the referenced elements.
	 * Any direct references to any type of guidances are also in this closure.
	 * <p>
	 * 3. publish all the referened non-ContentCategory content elements from
	 * step 2. based on the first level closure. The purpose of the first level
	 * closure is to allow bring in guidances with valid element link and any
	 * other references such as a Task or Role, being linked with a missing
	 * element link. This again brings in all the direct references
	 * <p>
	 * 4.Make the final closure by including the following elements:
	 * <p>
	 * a. all published elements from step 1,2,3.
	 * <p>
	 * b. all referenced Guidances from step 3
	 * <p>
	 * c. all Guidances of type Practice, RoadMap, Suporting Material, and Term
	 * Definition, in the configuration
	 * <p>
	 * d. all Content Categories that contains at least one element of type a,
	 * b, or c. and their parent categories
	 * <p>
	 * The selected view is published based on the element closure defined
	 * above.
	 */
	private void makeProcessClosure() {
		monitor.subTask(PublishingResources.buildingProcessClosureTask_name);

		// publish the selected processes
		// need to build a closure of all the elements involved in the
		// processes
		List<Process> processes = options.getProcesses();
		if (processes != null && processes.size() > 0) {
			for (Iterator<Process> it = processes.iterator(); it.hasNext();) {
				makeProcessClosure(it.next());
				if (monitor.isCanceled()) {
					return;
				}
			}
		}

		// make the first level closure to include all the process elements and
		// it's referenced elements
		// any thing except Guidances and ContentCategories outside the closure
		// is filtered
		getValidator()
				.addClosureElements(getValidator().getPublishedElements());
		getValidator().addClosureElements(
				getValidator().getReferencedElements());

		// now publish all referenced elements, any direct references in the
		// process elements are
		// part of the closure
		// don't publish content categories for now since they might be empty
		// and discarded later
		List refs = new ArrayList(getValidator().getReferencedElements());
		for (Iterator it = refs.iterator(); it.hasNext();) {
			MethodElement e = (MethodElement) it.next();
			if (!(e instanceof ContentCategory)) {
				super.publish(monitor, e);

				// collect process specific layout info with suppression status
				// this will incldue the diagrams and the supression states of
				// each item under the current procee
				if (LibraryUtil.isProcess(e)) {
					publishProcessLayout((org.eclipse.epf.uma.Process) e);
				}

			}
		}

		// now, any referenced guidance should be in the closure,
		// so include them and make the final closure
		refs.clear();
		for (Iterator it = getValidator().getReferencedElements().iterator(); it
				.hasNext();) {
			MethodElement e = (MethodElement) it.next();
			if (e instanceof Guidance) {
				refs.add(e);
			}
		}

		if (refs.size() > 0) {
			getValidator().addClosureElements(refs);
		}

		// now all the published elements are the element closure, make the
		// final closure
		getValidator().makeElementClosure(config);
	}

	private void makeProcessClosure(org.eclipse.epf.uma.Process proc) {

		if (proc == null) {
			return;
		}

		if (ConfigurationHelper.canShow(proc, config)) {
			ActivityLayout l = new ActivityLayout();
			l.init(getLayoutMgr(), proc, proc, null);
			l.findAllLinkedElements();
		}

		if (monitor.isCanceled()) {
			return;
		}

		if (ConfigurationHelper.isExtender(proc)) {
			org.eclipse.epf.uma.Process baseProc = (org.eclipse.epf.uma.Process) proc
					.getVariabilityBasedOnElement();
			if (ConfigurationHelper.inConfig(baseProc, config)) {
				makeProcessClosure(baseProc);
			}
		}

	}

	private void publishReferenceElements() {
		// now process the referenced elements and publish the contents
		while (getValidator().getReferencedElements().size() > 0) {
			MethodElement e = (MethodElement) getValidator()
					.getReferencedElements().remove(0);

			try {
				if (monitor.isCanceled()) {
					return;
				}

				// references to method plugins and method packages can be
				// ignored
				if (e instanceof MethodPlugin || e instanceof MethodPackage) {
					continue;
				}

				long startTime = System.currentTimeMillis();

				super.publish(monitor, e);

				// collect process specific layout info with suppression status
				// this will incldue the diagrams and the supression states of
				// each item under the current procee
				if (LibraryUtil.isProcess(e)) {
					// long startTime1 = System.currentTimeMillis();
					publishProcessLayout((org.eclipse.epf.uma.Process) e);
					// long endTime1 = System.currentTimeMillis();
					// System.out.println("Published process " + e.getName() +
					// ": " + (endTime1 - startTime1) + " ms");
				}

				long endTime = System.currentTimeMillis();
				long timeTaken = endTime - startTime;
				if (timeTaken >= 5000) {
					System.out.println("Published " + e.getName() + ": " //$NON-NLS-1$ //$NON-NLS-2$
							+ timeTaken + " ms"); //$NON-NLS-1$
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				getValidator().logError(e, "Error publishing element", ex); //$NON-NLS-1$
			}
		}
	}

	private void publishProcessLayout(final org.eclipse.epf.uma.Process e) {

		Runnable runnable = new Runnable() {
			public void run() {
				try {
					ActivityLayout layout = new ActivityLayout();
					layout.init(getLayoutMgr(),
							(org.eclipse.epf.uma.Process) e, null, null);
					ProcessLayoutData pd = new ProcessLayoutData(e.getGuid());
					layout.loadLayoutData(pd, true, true, true);
					printLayoutScript(pd);
					pd.clear();
				} catch (Exception e1) {
					getValidator()
							.logError(
									e,
									"Error publishing process specific layout data", e1); //$NON-NLS-1$
				}

			}
		};

		Timer timer = new Timer();
		try {

			// run the publishing and check the time, if timeout, terminate it
			Thread t = new Thread(runnable);
			t.start();
			t.join(TIMEOUT_INTERVAL);
			if (t.isAlive()) {
				// wait for the thread to die and log an error
				timer.stop();
				getValidator()
						.logInfo(
								e,
								"publishing process specific layout data takes " + timer.getTime() + " mini seconds already and is still not done yet ..."); //$NON-NLS-1$ //$NON-NLS-2$
				timer.start();
				t.join();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {
			timer.stop();
			getValidator()
					.logInfo(
							e,
							timer.getTotalTime()
									+ " mini seconds publishing process specific layout data"); //$NON-NLS-1$
		}

	}

	private void printLayoutScript(ProcessLayoutData data) {
		File outputFile = new File(getLayoutMgr().getPublishDir(),
				PROCESS_LAYOUT_DATA_FILE); //$NON-NLS-1$
		PrintWriter pw = null;
		try {
			// create a stream with append enabled
			FileOutputStream os = new FileOutputStream(outputFile, true);

			// create a write with utf-8 encoding
			OutputStreamWriter writer = new OutputStreamWriter(os, "utf-8"); //$NON-NLS-1$

			// create a print writer with auto flush
			pw = new PrintWriter(writer, true);
			data.print(pw);
		} catch (Exception e) {
			getValidator().logError("unable to save process layout data", e); //$NON-NLS-1$

		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}

	}

	/**
	 * Iterate thru tuee
	 * 
	 * @param obj
	 * @param parent
	 */
	private void iterate(Object obj, Bookmark parent) {
		try {
			if (monitor.isCanceled()) {
				return;
			}

			// Get the adapter from the factory.
			ITreeItemContentProvider treeItemContentProvider = null;
			if (obj instanceof ITreeItemContentProvider) {
				treeItemContentProvider = (ITreeItemContentProvider) obj;
			} else {
				treeItemContentProvider = (ITreeItemContentProvider) adapterFactory
						.adapt(obj, ITreeItemContentProvider.class);
			}
		
			// Either delegate the call or return nothing.
			if (treeItemContentProvider != null) {
				Collection items = treeItemContentProvider.getChildren(obj);
				for (Iterator it = items.iterator(); it.hasNext();) {
					if (monitor.isCanceled()) {
						return;
					}

					// create bookmark
					Object itorObj = it.next();
					Object element = LibraryUtil.unwrap(itorObj);

					if ((element instanceof MethodElement)) {
						MethodElement me = (MethodElement) element;
						try {
							if (ConfigurationHelper.canShow(me, config)) {
								me = ConfigurationHelper
										.getCalculatedElement(me,
												getLayoutMgr()
														.getElementRealizer());
								if (me != null) {
									if (me instanceof Tool) {
										buildToolSubTree((Tool) me, parent);
									} else if (me instanceof Discipline) {
										buildDisciplineSubTree((Discipline) me,
												parent);
									}

									// else if (me instanceof DisciplineGrouping
									// || me instanceof Domain || me instanceof
									// WorkProductType
									// || me instanceof RoleSetGrouping || me
									// instanceof RoleSet )
									else if (me instanceof ContentCategory) {

										ContentCategory cc = (ContentCategory) me;

										// if the content category is empty,
										// don't add to the parent
										Bookmark b = createBookmark(
												this.monitor, cc);
										iterate(itorObj, b);
										if (options.isPublishEmptyCategories()
												|| b.getChildCount() > 0) {
											parent.addChild(b);
											discardEmptyCategory(cc, false);
										} else {
											discardEmptyCategory(cc, true);
										}
									} else {
										if (itorObj instanceof PracticeSubgroupItemProvider) {
											buildPracticeSubgroupTree(obj, parent,
													(PracticeSubgroupItemProvider) itorObj);
										} else {										
											Bookmark b = createBookmark(me, parent);
											if (!buildSubTree(itorObj, me, b)) {
												iterate(itorObj, b);
											}
										}
									}
								}
							}
						} catch (Exception e) {
							String message = "Error building navigation tree for " + LibraryUtil.getTypeName(me); //$NON-NLS-1$
							getHtmlBuilder().getValidator()
									.logError(message, e);
							e.printStackTrace();
						}
					} else {
						if (itorObj instanceof PracticeSubgroupItemProvider) {
							buildPracticeSubgroupTree(obj, parent, (PracticeSubgroupItemProvider) itorObj);
						} else {
							iterate(itorObj, parent);
						}
					}

				}
			}
		} catch (Exception e) {
			String message = "Error building navigation tree"; //$NON-NLS-1$
			getHtmlBuilder().getValidator().logError(message, e);

			e.printStackTrace();
		}
	}

	/**
	 * create a bookmark under the specified parent. If no parent is specified,
	 * 
	 * @param monitor
	 * @param element
	 * @param parent
	 * @return
	 */
	protected Bookmark createBookmark(Object element, Bookmark parent) {
		Bookmark b = createBookmark(this.monitor, element);
		if (parent == null) {
			bookmarks.add(b);
		} else {
			parent.addChild(b);
		}

		return b;
	}

	/**
	 * build the sub tree for the given element. return true if the element is
	 * handled, false otherwise
	 * 
	 * @param element
	 * @param bm
	 * @return boolean
	 */
	private boolean buildSubTree(Object obj, MethodElement element, Bookmark bm) {
		boolean isShowRelatedLinks = options.isShowRelatedLinks();
		if (element instanceof Task) {
			if(isShowRelatedLinks) {
				buildTaskSubTree((Task) element, bm);
			} else {
				return true;
			}
		} else if (element instanceof Role) {
			if(isShowRelatedLinks) {
				buildRoleSubTree((Role) element, bm);
			} else {
				return true;
			}
		} else if (element instanceof WorkProduct) {
			if(isShowRelatedLinks) {
				buildWorkProductSubTree((WorkProduct) element, bm);
			} else {
				return true;
			}
		} else if (LibraryUtil.isProcess(element)) {
			buildProcessSubTree(obj, (org.eclipse.epf.uma.Process) element, bm);
		} else {
			// System.out.println("Not handled: " + element);
			return false;
		}

		return true;
	}

	private void buildItems(List elements, Bookmark bm) {
		buildItems(elements, bm, false);
	}

	private void buildItems(List elements, Bookmark bm, boolean buildSubTree) {
		for (Iterator it = elements.iterator(); it.hasNext();) {
			if (monitor.isCanceled()) {
				return;
			}

			MethodElement element = (MethodElement) it.next();

			// filter away the containment child-element if any of the parent(s)
			// are in the list
			// 00384619 - Published site: Display of WPs under responsible role
			// if the container of the element is in the list, ignore it
			if (ConfigurationHelper.isContainmentElement(element)
					&& ConfigurationHelper.isContainerInList(element, elements,
							config)) {
				continue;
			}

			buildItem(element, bm, buildSubTree);
		}
	}

	private Bookmark buildItem(MethodElement element, Bookmark parent,
			boolean buildSubTree) {
		if (monitor.isCanceled()) {
			return null;
		}

		Bookmark b = null;

		// make sure the element is showable
		MethodElement e = ConfigurationHelper.getCalculatedElement(element,
				getLayoutMgr().getElementRealizer());
		if (isVisible(e)) {
			b = createBookmark(this.monitor, e);
			if (b == null) {
				return b;
			}

			parent.addChild(b);

			if (buildSubTree) {
				if (e instanceof Artifact) {
					buildContainedArtifactsSubTree((Artifact) e, b, true);
				}
			}
		}

		return b;
	}

//	/**
//	 * create the folder bookmark and it's children. generate the folder summary
//	 * page
//	 * 
//	 * @param element
//	 * @param bm
//	 * @param nodeName
//	 * @param items
//	 */
//
//	private Bookmark createFolderBookmark(MethodElement element, Bookmark bm,
//			String nodeName, List items, boolean createChildren) {
//		Bookmark b = null;
//		if (items.size() > 0) {
//			IElementLayout l = new SummaryPageLayout(getHtmlBuilder()
//					.getLayoutManager(), element, nodeName, items);
//			String url = l.getUrl();
//			getHtmlBuilder().generateHtml(l);
//			b = createBookmark(nodeName, EcoreUtil.generateUUID(), url,
//					ICON_FOLDER, ICON_FOLDER, null);
//			bm.addChild(b);
//			if (createChildren) {
//				buildItems(items, b);
//			}
//		}
//
//		return b;
//	}

	Set<String> summaryPagesGenerated = new HashSet<String>();
	
	private Bookmark createFolderBookmark(MethodElement element, Bookmark bm,
			String prefixName, String nodeName, List items,
			boolean createChildren) {
		Bookmark b = null;
		if (items.size() > 0) {
			IElementLayout l = new SummaryPageLayout(getHtmlBuilder()
					.getLayoutManager(), element, prefixName, nodeName, items);
			String url = l.getUrl();
			
			// don't regenerate the page
			// 200619 - Summary page html was generated multiple times when publishing
			if ( !summaryPagesGenerated.contains(url) ) {
				getHtmlBuilder().generateHtml(l);
				summaryPagesGenerated.add(url);
			}
			
			b = createBookmark(nodeName, EcoreUtil.generateUUID(), url,
					ICON_FOLDER, ICON_FOLDER, null);
			bm.addChild(b);
			if (createChildren) {
				buildItems(items, b);
			}
		}

		return b;
	}
	
	private void buildPracticeSubgroupTree(Object providerParent,
			Bookmark parent, PracticeSubgroupItemProvider provider) {
		Collection children = provider.getChildren(null);
		List items = children instanceof List ? (List) children
				: new ArrayList(children);

		if (items != null && items.size() > 0) {
			Practice practice = provider.getPractice();
			if (practice == null) {
				return;
			}

			IElementLayout l = new SummaryPageLayout(getHtmlBuilder()
					.getLayoutManager(), practice, provider.getPrefix(),
					provider.getText(null), items, provider.getText(null));
			
			String url = l.getUrl();

			String imageString = this.getNodeIconName(provider);
			Bookmark b = createBookmark(provider.getText(null), EcoreUtil
					.generateUUID(), url, imageString, imageString, null);
			parent.addChild(b);
			this.iterate(provider, b);
			
			if (!summaryPagesGenerated.contains(url)) {
				getHtmlBuilder().generateHtml(l);
				summaryPagesGenerated.add(url);
			}
		}

	}

	private Bookmark buildDisciplineSubTree(Discipline element, Bookmark parent) {
		String url = ""; //$NON-NLS-1$
		Bookmark b;

		// need to calculate the realized value of the feature
		List items_workflow = calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getDiscipline_ReferenceWorkflows());

		// Tasks in published site under Disciplines are in
		// random order
		// use the adaptor factory to get the childrens
		// List items_task = ConfigurationHelper.calc0nFeatureValue(element,
		// UmaPackage.eINSTANCE.getDiscipline_Tasks(), config);
		List items_task = new ArrayList();
		List item_subDisciplies = new ArrayList();
		ITreeItemContentProvider treeItemContentProvider = (ITreeItemContentProvider) adapterFactory
				.adapt(element, ITreeItemContentProvider.class);
		if (treeItemContentProvider != null) {
			Collection items = treeItemContentProvider.getChildren(element);
			for (Iterator it = items.iterator(); it.hasNext();) {
				if (monitor.isCanceled()) {
					return null;
				}

				// create bookmark
				Object itorObj = it.next();
				Object e = LibraryUtil.unwrap(itorObj);
				if ((e instanceof Task)) {
					MethodElement t = ConfigurationHelper.getCalculatedElement(
							(MethodElement) e, getLayoutMgr()
									.getElementRealizer());
					if (t != null) {
						items_task.add(t);
					}
				} else if (e instanceof Discipline) {
					MethodElement d = ConfigurationHelper.getCalculatedElement(
							(MethodElement) e, getLayoutMgr()
									.getElementRealizer());
					if (d != null) {
						item_subDisciplies.add(d);
					}

				}
			}
		}

		if (monitor.isCanceled()) {
			return null;
		}

		// all guidances
		List items_guidance = new ArrayList();
		items_guidance.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Assets()));
		items_guidance.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Checklists()));
		items_guidance.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_ConceptsAndPapers()));
		items_guidance.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Examples()));
		items_guidance.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Guidelines()));
		items_guidance.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_SupportingMaterials()));

		if (!options.isPublishEmptyCategories()
				&& items_workflow.size() + items_task.size()
						+ items_guidance.size() + item_subDisciplies.size() == 0) {
			// do nothing, don't show the folder
			discardEmptyCategory(element, true);
			return null;
		}

		if (monitor.isCanceled()) {
			return null;
		}

		// create the item bookmark
		// don't set to the parent yet. make sure it's not empty
		// need to check the sub-disciplines
		// 150984 - Publishing: Nested discipline is not display in the publish
		// page
		// Bookmark bm = createBookmark(element, parent);
		Bookmark bm = createBookmark(this.monitor, element);

		// sub-disciplines come first
		if (item_subDisciplies.size() > 0) {
			for (Iterator it = item_subDisciplies.iterator(); it.hasNext();) {
				Discipline d = (Discipline) it.next();
				buildDisciplineSubTree(d, bm);
			}
		}

		if (options.isPublishLightWeightTree()) {

			Collections.sort(items_workflow, nameComparator);
			//Collections.sort(items_task, nameComparator);
			Collections.sort(items_guidance, nameComparator);

			buildItems(items_workflow, bm);
			buildItems(items_task, bm);
			buildItems(items_guidance, bm);
		} else {
			if (items_workflow.size() > 0) {
				Bookmark wfFolder = createFolderBookmark(element, bm,
						PREFIX_Reference_Workflows, NODE_Reference_Workflows,
						items_workflow, false);

				// Capability Patterns in treebrowser under
				// disciplines-reference workflows cannot be expanded
				for (Iterator it = items_workflow.iterator(); it.hasNext();) {
					if (monitor.isCanceled()) {
						return null;
					}

					// 179609 - Exception when assign activity as discipline's
					// referenced workflow
					// this is a model change, referenced workflow can be
					// Activity,
					// old model references Process
					Activity act = (Activity) it.next();
					Bookmark bmWorkflow = buildItem(act, wfFolder, false);
					buildActivitySubTree(act, act, bmWorkflow);
				}
			}

			if (monitor.isCanceled()) {
				return null;
			}

			if (items_task.size() > 0) {
				createFolderBookmark(element, bm, PREFIX_Tasks, NODE_Tasks,
						items_task, true);
			}

			if (monitor.isCanceled()) {
				return null;
			}

			if (items_guidance.size() > 0) {
				createFolderBookmark(element, bm, PREFIX_Guidances,
						NODE_Guidances, items_guidance, true);
			}
		}

		if (options.isPublishEmptyCategories() || bm.getChildCount() > 0) {
			parent.addChild(bm);
			discardEmptyCategory(element, false);
		} else {
			discardEmptyCategory(element, true);
		}

		return bm;
	}

	private void buildActivitySubTree(Object obj, Activity element, Bookmark bm) {
		if (monitor.isCanceled()) {
			return;
		}

		List items = new ArrayList();

		org.eclipse.epf.uma.Process proc = TngUtil.getOwningProcess(element);
		ProcessElementItem procItem = new ProcessElementItem(obj, element,
				element.getGuid());

		ComposedAdapterFactory adapterFactory = super.getLayoutMgr()
				.getWBSAdapterFactory();
		Suppression sup = new Suppression(proc);
		iterateActivity(procItem, bm, adapterFactory, sup);
	}

	private void buildToolSubTree(Tool element, Bookmark parent) {
		if (monitor.isCanceled()) {
			return;
		}

		List items = calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getTool_ToolMentors());
		if (options.isPublishEmptyCategories() || items.size() > 0) {
			Bookmark b = createBookmark(element, parent);
			buildItems(items, b);
			discardEmptyCategory(element, false);
		} else {
			discardEmptyCategory(element, true);
		}
	}

	private void buildTaskSubTree(Task element, Bookmark bm) {

		String url;
		Bookmark b;

		List allItems = new ArrayList();

		// performing roles
		List items = new ArrayList();
		List rList = (List) calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getTask_PerformedBy());
		if (rList != null) {
			items.addAll(rList);
		}
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getTask_AdditionallyPerformedBy()));

		if (items.size() > 0) {
			if (options.isPublishLightWeightTree()) {
				Collections.sort(items, nameComparator);
				allItems.addAll(items);
			} else {
				createFolderBookmark(element, bm, PREFIX_Performing_Roles,
						NODE_Performing_Roles, items, true);
			}
		}

		if (monitor.isCanceled()) {
			return;
		}

		if (!options.isPublishLightWeightTree()) {
			// input work products, need a summary page,
			items = new ArrayList();
			items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
					.getTask_MandatoryInput()));
			items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
					.getTask_OptionalInput()));

			if (items.size() > 0) {
				createFolderBookmark(element, bm, PREFIX_Input_Work_Products,
						NODE_Input_Work_Products, items, true);
			}

			if (monitor.isCanceled()) {
				return;
			}
		}

		// output work products, need a summary page, TODO
		items = calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getTask_Output());

		if (items.size() > 0) {
			if (options.isPublishLightWeightTree()) {
				Collections.sort(items, nameComparator);
				allItems.addAll(items);
			} else {
				createFolderBookmark(element, bm, PREFIX_Output_Work_Products,
						NODE_Output_Work_Products, items, true);
			}
		}

		if (monitor.isCanceled()) {
			return;
		}

		// all guidances
		items.clear();
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Assets()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Checklists()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_ConceptsAndPapers()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Examples()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Guidelines()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_SupportingMaterials()));
		// Object e = calc01FeatureValue(element, UmaPackage.eINSTANCE
		// .getTask_Estimate());
		// if (e != null) {
		// items.add(e);
		// }
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getTask_ToolMentors()));

		if (items.size() > 0) {
			if (options.isPublishLightWeightTree()) {
				Collections.sort(items, nameComparator);
				allItems.addAll(items);
			} else {
				createFolderBookmark(element, bm, PREFIX_Guidances,
						NODE_Guidances, items, true);
			}
		}

		if (options.isPublishLightWeightTree()) {
			buildItems(allItems, bm);
		}

	}

	private void buildRoleSubTree(Role element, Bookmark bm) {
		String url;
		Bookmark b;

		if (monitor.isCanceled()) {
			return;
		}

		List allItems = new ArrayList();

		// tasks, // need a general overview page, TODO
		// List items = AssociationHelper.getPrimaryTasks(element);
		List items = calc0nFeatureValue(element,
				AssociationHelper.Role_Primary_Tasks);
		if (items.size() > 0) {
			Collections.sort(items, nameComparator);
			if (options.isPublishLightWeightTree()) {
				allItems.addAll(items);
			} else {
				createFolderBookmark(element, bm, PREFIX_ResponsibleFor_Tasks,
						NODE_ResponsibleFor_Tasks, items, true);
			}
		}

		if (monitor.isCanceled()) {
			return;
		}

		if (!options.isPublishLightWeightTree()) {
			// secondary tasks
			items = calc0nFeatureValue(element,
					AssociationHelper.Role_Secondary_Tasks);
			if (items.size() > 0) {
				Collections.sort(items, nameComparator);
				createFolderBookmark(element, bm, PREFIX_ParticipatesIn_Tasks,
						NODE_ParticipatesIn_Tasks, items, true);
			}
		}

		if (monitor.isCanceled()) {
			return;
		}

		// responsible for work products,
		items = calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getRole_ResponsibleFor());

		if (items.size() > 0) {
			Collections.sort(items, nameComparator);
			if (options.isPublishLightWeightTree()) {
				allItems.addAll(items);
			} else {
				b = createFolderBookmark(element, bm,
						PREFIX_Work_Products_Created,
						NODE_Work_Products_Created, items, false);
				buildItems(items, b, true);
			}
		}

		if (monitor.isCanceled()) {
			return;
		}

		if (!options.isPublishLightWeightTree()) {
			// modifies work products, need a summary page, TODO
			items = calc0nFeatureValue(element, UmaPackage.eINSTANCE
					.getRole_Modifies());
			if (items.size() > 0) {
				Collections.sort(items, nameComparator);
				b = createFolderBookmark(element, bm,
						PREFIX_Work_Products_Modified,
						NODE_Work_Products_Modified, items, false);
				buildItems(items, b, true);
			}

			if (monitor.isCanceled()) {
				return;
			}
		}

		// all guidances
		items.clear();
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Assets()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Checklists()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_ConceptsAndPapers()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Examples()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Guidelines()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_SupportingMaterials()));

		if (items.size() > 0) {
			Collections.sort(items, nameComparator);
			if (options.isPublishLightWeightTree()) {
				allItems.addAll(items);
			} else {
				createFolderBookmark(element, bm, PREFIX_Guidances, NODE_Guidances, items, true);
			}
		}

		if (options.isPublishLightWeightTree()) {
			// light weight tree, no sub folders
			buildItems(allItems, bm);
		}
	}

	private void buildWorkProductSubTree(WorkProduct element, Bookmark bm) {
		List items;
		String url = ""; //$NON-NLS-1$
		// Bookmark b;

		if (monitor.isCanceled()) {
			return;
		}

		List allItems = new ArrayList();

		// performing roles, 0.1 reference element will be realized in buildItem
		// multiplicity change for opposite features
		// Role r = AssociationHelper.getResponsibleRole(element);
		items = calc0nFeatureValue(element,
				AssociationHelper.WorkProduct_ResponsibleRoles);
		if (items.size() > 0) {
			if (options.isPublishLightWeightTree()) {
				Collections.sort(items, nameComparator);
				allItems.addAll(items);
			} else {
				createFolderBookmark(element, bm, PREFIX_Responsible_Role,
						NODE_Responsible_Role, items, true);
			}
		}

		if (monitor.isCanceled()) {
			return;
		}

		// containing work products, need a summary page, TODO
		if (element instanceof Artifact) {

			if (!options.isPublishLightWeightTree()) {
				WorkProduct wp = ((Artifact) element).getContainerArtifact();
				// createBookmark(NODE_Containing_Work_Product,
				// NODE_Containing_Work_Product, url, "", ""); //$NON-NLS-1$
				// //$NON-NLS-2$
				if (wp != null) {
					items = new ArrayList();
					items.add(wp);
					createFolderBookmark(element, bm,
							PREFIX_Containing_Work_Product,
							NODE_Containing_Work_Product, items, true);
				}

				if (monitor.isCanceled()) {
					return;
				}
			}

			// contained work products, need a summary page, TODO
			// items = ((Artifact)element).getContainedArtifacts();
			items = calc0nFeatureValue(element, UmaPackage.eINSTANCE
					.getArtifact_ContainedArtifacts());

			// make sure the contained elements does not contain the container,
			// this is possible due to realization, say, the containing element
			// contribute to the container
			items.remove(element);

			if (items.size() > 0) {
				if (options.isPublishLightWeightTree()) {
					Collections.sort(items, nameComparator);
					allItems.addAll(items);
				} else {
					Bookmark b = createFolderBookmark(element, bm,
							PREFIX_Contained_Work_Products,
							NODE_Contained_Work_Products, items, false);

					// for each contained work product, create the nested sub
					// tree
					buildItems(items, b, true);
				}
			}
		}

		if (monitor.isCanceled()) {
			return;
		}

		if (!options.isPublishLightWeightTree()) {
			// input to tasks
			items = new ArrayList();
			items.addAll(calc0nFeatureValue(element,
					AssociationHelper.WorkProduct_MandatoryInputTo_Tasks));
			items.addAll(calc0nFeatureValue(element,
					AssociationHelper.WorkProduct_OptionalInputTo_Tasks));
			if (items.size() > 0) {
				createFolderBookmark(element, bm, PREFIX_InputTo_Task,
						NODE_InputTo_Task, items, true);
			}

			if (monitor.isCanceled()) {
				return;
			}

			// output from tasks
			items = calc0nFeatureValue(element,
					AssociationHelper.WorkProduct_OutputFrom_Tasks);
			if (items.size() > 0) {
				createFolderBookmark(element, bm, PREFIX_OutputOf_Task,
						NODE_OutputOf_Task, items, true);
			}

			if (monitor.isCanceled()) {
				return;
			}
		}

		// all guidances
		items = new ArrayList();
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Assets()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Checklists()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_ConceptsAndPapers()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Examples()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Guidelines()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_SupportingMaterials()));
		// Object e = calc01FeatureValue(element, UmaPackage.eINSTANCE
		// .getWorkProduct_Estimate());
		// if (e != null) {
		// items.add(e);
		// }
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getWorkProduct_Reports()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getWorkProduct_Templates()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getWorkProduct_ToolMentors()));

		if (items.size() > 0) {
			if (options.isPublishLightWeightTree()) {
				Collections.sort(items, nameComparator);
				allItems.addAll(items);
			} else {
				createFolderBookmark(element, bm, PREFIX_Guidances,
						NODE_Guidances, items, true);
			}
		}

		if (options.isPublishLightWeightTree()) {
			buildItems(allItems, bm);
		}
	}

	private void buildContainedArtifactsSubTree(Artifact element, Bookmark bm,
			boolean showGuidances) {
		if (monitor.isCanceled()) {
			return;
		}

		List items;
		String url = ""; //$NON-NLS-1$

		// contained work products, need a summary page, TODO
		// items = ((Artifact)element).getContainedArtifacts();
		items = calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getArtifact_ContainedArtifacts());

		// make sure the contained elements does not contain the container,
		// this is possible due to realization, say, the containing element
		// contribute to the container
		items.remove(element);

		if (monitor.isCanceled()) {
			return;
		}

		if (items.size() > 0) {
			// Bookmark b = createFolderBookmark(element, bm,
			// NODE_Contained_Work_Products, items, false);

			// for each contained work product, create the nested sub tree
			for (Iterator it = items.iterator(); it.hasNext();) {
				if (monitor.isCanceled()) {
					return;
				}

				Artifact e = (Artifact) it.next();
				buildItem(e, bm, true);
			}
		}

		if (!showGuidances) {
			return;
		}

		if (monitor.isCanceled()) {
			return;
		}

		// all guidances
		items = new ArrayList();
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Assets()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Checklists()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_ConceptsAndPapers()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Examples()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_Guidelines()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getContentElement_SupportingMaterials()));
		// Object e = calc01FeatureValue(element, UmaPackage.eINSTANCE
		// .getWorkProduct_Estimate());
		// if (e != null) {
		// items.add(e);
		// }
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getWorkProduct_Reports()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getWorkProduct_Templates()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getWorkProduct_ToolMentors()));

		if (items.size() > 0) {
			// createFolderBookmark(element, bm, NODE_Guidances, items, true);
			buildItems(items, bm);
		}
	}

	/**
	 * iterate the break down structure and build the xml document
	 * 
	 * @param parentObj
	 *            The object to iterate. It can be a breakdown element, or it's
	 *            adaptor
	 * @param parentXml
	 * @param adapterFactory
	 */
	private void iterateActivity(ProcessElementItem parentItem,
			Bookmark parentBm, ComposedAdapterFactory adapterFactory,
			Suppression sup) {
		if (monitor.isCanceled()) {
			return;
		}

		ITreeItemContentProvider provider = null;
		Object parentObj = parentItem.rawItem;

		if (parentObj instanceof ITreeItemContentProvider) {
			provider = (ITreeItemContentProvider) parentObj;
		} else {
			provider = (ITreeItemContentProvider) adapterFactory.adapt(
					parentObj, ITreeItemContentProvider.class);
		}

		if (provider != null) {
			// String displayName = ProcessUtil.getAttribute(parentObj,
			// IBSItemProvider.COL_PRESENTATION_NAME, provider);
			// parentXml.setAttribute("DisplayName", displayName);

			Collection children = provider.getChildren(parentObj);
			for (Iterator it = children.iterator(); it.hasNext();) {
				if (monitor.isCanceled()) {
					return;
				}

				Object rawitem = it.next();
				if (sup.isSuppressed(rawitem)) {
					continue;
				}

				MethodElement item = (MethodElement) LibraryUtil
						.unwrap(rawitem);

				if (!options.showDescriptorsInNavigationTree
						&& (item instanceof Descriptor)) {
					continue;
				}

				ProcessElementItem elementItem = new ProcessElementItem(
						rawitem, item, parentItem);

				Bookmark child = buildItem(elementItem.element, parentBm, false);
				if (child != null) {
					// set the query string
					child.setQueryString(elementItem.getQueryString());

					IBSItemProvider adapter = null;
					if (rawitem instanceof IBSItemProvider) {
						adapter = (IBSItemProvider) rawitem;
					} else {
						adapter = (IBSItemProvider) adapterFactory.adapt(item,
								ITreeItemContentProvider.class);
						;
					}

					String displayName = ProcessUtil.getAttribute(item,
							IBSItemProvider.COL_PRESENTATION_NAME, adapter);
					child.setPresentationName(displayName);

					if (item instanceof Activity) {
						iterateActivity(elementItem, child, adapterFactory, sup);
					}
				}
			}
		}
	}

	private void buildProcessSubTree(Object obj,
			org.eclipse.epf.uma.Process element, Bookmark bm) {
		if (monitor.isCanceled()) {
			return;
		}

		List items = new ArrayList();

		ProcessElementItem procItem = new ProcessElementItem(obj, element,
				element.getGuid());

		ComposedAdapterFactory adapterFactory = super.getLayoutMgr()
				.getWBSAdapterFactory();
		Suppression sup = new Suppression(element);
		iterateActivity(procItem, bm, adapterFactory, sup);

		String url;
		Bookmark b;

		if (monitor.isCanceled()) {
			return;
		}

		// all guidances
		items = new ArrayList();
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getBreakdownElement_Checklists()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getBreakdownElement_Concepts()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getBreakdownElement_Examples()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getBreakdownElement_Guidelines()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getBreakdownElement_ReusableAssets()));
		items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getActivity_Roadmaps()));

		// need to include supporting materials as well
		// fix bug: supporting material served as user defined diagram should not 
		// show up in the navigation tree of a published process.	
		org.eclipse.epf.diagram.model.util.DiagramInfo userDiagramInfo = 
				new org.eclipse.epf.diagram.model.util.DiagramInfo(element);
		List v = calc0nFeatureValue(element, UmaPackage.eINSTANCE
				.getBreakdownElement_SupportingMaterials());
		for ( Iterator it = v.iterator(); it.hasNext(); ) {
			SupportingMaterial s = (SupportingMaterial)it.next();
			if ( !userDiagramInfo.isDiagram( (SupportingMaterial)s ) ) {
				items.add(s);
			}
		}

		if (element instanceof DeliveryProcess) {
			DeliveryProcess dp = (DeliveryProcess) element;
			items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
					.getDeliveryProcess_CommunicationsMaterials()));
			items.addAll(calc0nFeatureValue(element, UmaPackage.eINSTANCE
					.getDeliveryProcess_EducationMaterials()));
		}

		if (items.size() > 0) {
			createFolderBookmark(element, bm, PREFIX_Guidances, NODE_Guidances,
					items, true);
		}
	}

	/**
	 * dispose the object
	 */
	public void dispose() {
		super.dispose();

		adapterFactory = null;
		nameComparator = null;
		bookmarks.clear();
		elementUrls.clear();
		summaryPagesGenerated.clear();
	}

	protected Set elementUrls = new HashSet();

	protected void elementPublished(IElementLayout layout, String htmlfile) {
		// only for process element for now
		// may cover all method elements later
		if (layout.getElement() instanceof ProcessElement) {
			ElementUrl url = new ElementUrl(layout.getElement().getGuid(),
					layout.getUrl(), layout.getDisplayName());
			elementUrls.add(url);
		}
	}

	private void saveElementUrls() {
		// save published element urls
		// need to escape the url and text since it will be assigned to a
		// javascript variable as literal
		XmlElement xml = new XmlElement("ElementUrls"); //$NON-NLS-1$
		for (Iterator it = elementUrls.iterator(); it.hasNext();) {
			ElementUrl url = (ElementUrl) it.next();
			xml.newChild("elementUrl", url.guid) //$NON-NLS-1$
					.setAttribute("text", StrUtil.escape(url.text)) //$NON-NLS-1$
					.setAttribute("url", StrUtil.escape(url.url)); //$NON-NLS-1$
		}

		// StringBuffer buffer = new StringBuffer();
		// buffer.append(XmlHelper.XML_HEADER).append(xml.toXml());
		// File f = new File(getLayoutMgr().getPublishDir(), "ElementUrls.xml");
		// //$NON-NLS-1$
		// FileUtil.writeUTF8File(f.getAbsolutePath(), buffer.toString());

		String html = PublishingUtil.getHtml(xml, "xsl/elementUrls.xsl"); //$NON-NLS-1$
		File f = new File(getLayoutMgr().getPublishDir(),
				PROCESS_LAYOUT_DATA_FILE); //$NON-NLS-1$
		FileUtil.writeUTF8File(f.getAbsolutePath(), html, true);
	}

	/**
	 * data structure to define url of an element
	 * 
	 * @author Jinhua Xi
	 * 
	 */
	public class ElementUrl {

		String guid;
		String url;
		String text;

		/**
		 * constructor
		 * 
		 * @param guid
		 *            String the guid of the element
		 * @param url
		 *            String the url of the element
		 * @param text
		 *            String the text alone with the url
		 */
		public ElementUrl(String guid, String url, String text) {
			this.guid = guid;
			this.url = url;
			this.text = text;
		}
	}

	class EObjectComparator implements Comparator {
		private EStructuralFeature pName = UmaPackage.eINSTANCE
				.getMethodElement_PresentationName();
		private EStructuralFeature name = UmaPackage.eINSTANCE
				.getNamedElement_Name();

		public EObjectComparator() {

		}

		public int compare(Object e1, Object e2) {
			if (e1 instanceof EObject && e2 instanceof EObject) {
				Object v1 = getValue(e1);
				Object v2 = getValue(e2);

				if (v1 == null && v2 == null) {
					return e1.toString().compareTo(e2.toString());
				} else if (v1 == null) {
					return -1;
				} else if (v2 == null) {
					return 1;
				} else {
					return v1.toString().compareTo(v2.toString());
				}
			} else {
				return e1.toString().compareTo(e2.toString());
			}
		}

		private Object getValue(Object e) {
			Object v = null;
			try {
				v = ((EObject) e).eGet(pName);
			} catch (Exception ex) {

			}
			;

			if (v == null || v.toString().length() == 0) {
				try {
					v = ((EObject) e).eGet(name);
				} catch (Exception ex) {

				}
				;
			}

			return v;
		}
	}
}
