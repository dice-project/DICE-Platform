//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.layout.elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.common.utils.Timer;
import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.ComposedWPDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.PredecessorList;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.IElementLayout;
import org.eclipse.epf.library.layout.LayoutInfo;
import org.eclipse.epf.library.layout.diagram.DiagramInfo;
import org.eclipse.epf.library.layout.diagram.IActivityDiagramService;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.prefs.BSColumn;
import org.eclipse.epf.library.prefs.PreferenceUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ModelInfoKeyMap;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.eclipse.osgi.util.NLS;


/**
 * The element layout for a Role.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ActivityLayout extends AbstractProcessElementLayout {
	
	public static final String TAB_NAME_ACTIVITY_DESC = "Description"; //$NON-NLS-1$
	public static final String TAB_NAME_ACTIVITY_WBS = "WBS"; //$NON-NLS-1$
	public static final String TAB_NAME_ACTIVITY_TBS = "TBS"; //$NON-NLS-1$
	public static final String TAB_NAME_ACTIVITY_WPBS = "WPBS"; //$NON-NLS-1$
	
	//public static final String BRACE_REPLACEMENT = "_BR_";	 //$NON-NLS-1$
	//public static final String OPENBRACE_STRING = "\\{";	 //$NON-NLS-1$
	
	org.eclipse.epf.diagram.model.util.DiagramInfo userDiagramInfo = null;
	boolean debug = LibraryPlugin.getDefault().isDebugging();
	
	private TBSItemDetail itemDetail = new TBSItemDetail();
	
	public ActivityLayout() {
		super();
	}

	/**
	 * initialize the layout
	 */
	public void init(ElementLayoutManager layoutManager, MethodElement element) {
		
		// make sure the process is loaded
		// otherwise the process or process elements may not have a valid container
		// and thus the element content path can't be determined
		// this is just a workaround. should we have a better way to make sure the elements are loaded correctly???????????
		MethodPlugin plugin = LibraryUtil.getMethodPlugin(element);
		if ( plugin == null || plugin.eContainer() == null ) {
			// this should not happen in publishing mode since the whole library is already loaded
			if ( debug && getLayoutMgr().isPublishingMode() ) {
				System.out.println("Error: Element without a valid container: " + LibraryUtil.getTypeName(element)); //$NON-NLS-1$
			}

			LibraryUtil.loadAllProcesses(LibraryService.getInstance().getCurrentMethodLibrary());
		}
		
		super.__init(layoutManager, element);

		userDiagramInfo = new org.eclipse.epf.diagram.model.util.DiagramInfo((Activity)element);
		
	
		// setup the multi layout output
		// String file_desc = super.getFileName(ResourceHelper.FILE_EXT_HTML);		
		
		String file;
		
//		if (super.element.getGuid().startsWith("{"))
//		{
//			String newGuid = super.element.getGuid().replaceFirst( OPENBRACE_STRING, BRACE_REPLACEMENT );
//			file = super.element.getName() + newGuid.substring(0, newGuid.length()-1);
//		}
//		else
			//file = StrUtil.removeSpecialCharacters(super.element.getName()) + super.element.getGuid();
		
		file = getFileName(ResourceHelper.FILE_EXT_HTML);
		if (file != null ) {
			// replace ' and " with space
			file = file.replace('\'', ' ').replace('\"', ' ');
			
			setLayoutInfo(new LayoutInfo(
				TAB_NAME_ACTIVITY_DESC, "activity_desc.xsl", file + TABURL_SUFFIX_DESC, true)); //$NON-NLS-1$ 
			setLayoutInfo(new LayoutInfo(
				TAB_NAME_ACTIVITY_WPBS, "activity_wpbs.xsl", file + TABURL_SUFFIX_WPBS, false)); //$NON-NLS-1$ 
			setLayoutInfo(new LayoutInfo(
				TAB_NAME_ACTIVITY_TBS, "activity_tbs.xsl", file + TABURL_SUFFIX_TBS, false)); //$NON-NLS-1$ 
			setLayoutInfo(new LayoutInfo(
				TAB_NAME_ACTIVITY_WBS, "activity_wbs.xsl", file + TABURL_SUFFIX_WBS, false)); //$NON-NLS-1$ 
		} else {
			System.out.println("Error in ActivityLayout.init: no file for element " + super.element.getName()); //$NON-NLS-1$
		}
	}
	
	/**
	 * @see org.eclipse.epf.library.layout.IElementLayout#getXslUrl()
	 */
	public String getXslUrl() {
		// set the default tab url, the default tab will be set in publishing.
		// for browsing, use the TAB_NAME_ACTIVITY_WBS tab
		String tabName = getLayoutMgr().getValidator().getDefaultActivityTab();
		if ( tabName == null ) {
			tabName = TAB_NAME_ACTIVITY_WBS;
		}
		LayoutInfo info = getLayoutInfo(tabName);
		if ( info == null ) {
			info = getLayoutInfo(TAB_NAME_ACTIVITY_WBS);
		}	
		
		return info.layout_xsl;
	}
	
	private static final String TABURL_SUFFIX_DESC = "_desc" + ResourceHelper.FILE_EXT_HTML; //$NON-NLS-1$
	private static final String TABURL_SUFFIX_WPBS = "_wpbs" + ResourceHelper.FILE_EXT_HTML; //$NON-NLS-1$
	private static final String TABURL_SUFFIX_TBS = "_tbs" + ResourceHelper.FILE_EXT_HTML; //$NON-NLS-1$
	private static final String TABURL_SUFFIX_WBS = "_wbs" + ResourceHelper.FILE_EXT_HTML; //$NON-NLS-1$
	
	public static boolean isActivityTabUrl(String url) {
		
		if ( url == null || url.length() == 0 ) {
			return false;
		}
		
		return (url.endsWith(TABURL_SUFFIX_DESC) 
				|| url.endsWith(TABURL_SUFFIX_WPBS) 
				|| url.endsWith(TABURL_SUFFIX_TBS) 
				|| url.endsWith(TABURL_SUFFIX_WBS) );
		
	}
	/**
	 * override this method to collect the SupportingMaterials that are for user defined diagrams
	 */
	protected boolean acceptFeatureValue(EStructuralFeature feature, Object value) {
		
		if ( value instanceof SupportingMaterial ) {			
			return !userDiagramInfo.isDiagram( (SupportingMaterial)value );
		}
		else if ( value instanceof List )
		{
			List items = (List)value;
			int i = 0;
			while ( i < items.size() )
			{
				Object o = items.get(i);
				if ( (o instanceof SupportingMaterial) && userDiagramInfo.isDiagram((SupportingMaterial)o) ) {			
					items.remove(i);
				}
				else
				{
					i++;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * @see org.eclipse.epf.library.layout.IElementLayout#getXmlElement(boolean)
	 */
	public XmlElement getXmlElement(boolean includeReferences) {
		
		String msg;
		Timer t = null;
		if ( debug ) {
			t = new Timer();	
			msg = "Generating layout xml for " + LibraryUtil.getTypeName(element)  //$NON-NLS-1$ 
				+ ", includeReferences = " + includeReferences; //$NON-NLS-1$
			System.out.println(msg);
		}
		

		XmlElement elementXml = super.getXmlElement();
		
		// load the attributes
		loadAttributes(elementXml);
		
//		// set the default tab url, the default tab will be set in publishing.
//		// for browsing, use the TAB_NAME_ACTIVITY_WBS tab
//		String tabName = getLayoutMgr().getValidator().getDefaultActivityTab();
//		if ( tabName == null ) {
//			tabName = TAB_NAME_ACTIVITY_WBS;
//		}
//		LayoutInfo linfo = getLayoutInfo(tabName);
//		if ( linfo == null ) {
//			linfo = getLayoutInfo(TAB_NAME_ACTIVITY_WBS);
//		}	
//		
//		elementXml.setAttribute("defaultTabUrl", linfo.fileName);
//		
		Process proc = getOwningProcess();
		
		// need to handle the supressed breakdown elements
		// use the Supress utility
		Suppression sup = getSuppression(proc);

//		// display name needs to be fixed, extended activity may need to get display name from the base
//		// DVT:  PNs of extended processes no picked up for publish (TCT 638)
//		ComposedAdapterFactory adapterFactory = layoutManager.getCBSAdapterFactory();
//		Object wrapper = sup.getObjectByPath(super.paths, adapterFactory);
//		if ( wrapper == null ) {
//			wrapper = super.element;
//		}
//
//		IBSItemProvider adapter = (IBSItemProvider) adapterFactory.adapt(
//				wrapper, ITreeItemContentProvider.class);
//
//		String displayName = ProcessUtil.getAttribute(element,
//				IBSItemProvider.COL_PRESENTATION_NAME, adapter);
//
//		elementXml.setAttribute("DisplayName", displayName); //$NON-NLS-1$
		
		if (!includeReferences) {
			return elementXml;
		}
		
		loadExtendedAttributes(elementXml);
		
		// Load the udt info
		loadUdtReferences(elementXml);
		loadExtendedReferences(elementXml);
		
		elementXml.setAttribute("ShowFullMethodContent", (layoutManager.getValidator().showExtraInfoForDescriptors()) ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		// get the tabs xml section
		XmlElement tabsXml = elementXml.newChild("tabs"); //$NON-NLS-1$
		for (Iterator it = layouts.iterator(); it.hasNext();) {
			LayoutInfo info = (LayoutInfo) it.next();
			tabsXml.newChild("tab") //$NON-NLS-1$
					.setAttribute("name", info.name) //$NON-NLS-1$
					.setAttribute("url", super.elementPath + info.fileName); //$NON-NLS-1$
		}

		// do it only when it's a process or it's not in publishing model
		// since in publishing, the iteration always start from process
		// for browsing, it can start in the middle 
		if ( !getLayoutMgr().isPublishingMode() || super.element instanceof org.eclipse.epf.uma.Process ) {
			getLayoutMgr().prepareAdaptorFactoriesForProcess(proc);
		}

		// load the copyright info
		loadCopyright(elementXml);

		// calculate other selected referecnes
		List features = LibraryUtil.getStructuralFeatures(element);
//		List properties = element.getInstanceProperties();
		if (features != null) {
			// get element references
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features
						.get(i);

				// skip the breakdown elements since we will build the breakdown
				// structure later
				if (feature == UmaPackage.eINSTANCE
						.getActivity_BreakdownElements()) {
					continue;
				}

				// Object value = element.get(i);
				EClassifier type = feature.getEType();
				if (!(type instanceof EClass)) {
					continue;
				}

				loadFeature(feature, elementXml, false);
			}
		}
		
		Collection oppositeProperties = new ArrayList(element.getOppositeFeatures());
		for (Iterator z= oppositeProperties.iterator(); z.hasNext(); )
		{
			OppositeFeature ofeature = (OppositeFeature) z.next();
			loadFeature(ofeature, elementXml, includeReferences);
		}
		
		if ( debug ) {
			t.stop();
			msg = t.getTime() + " mini seconds building Activity Descriotion xml for " + LibraryUtil.getTypeName(element)  ; //$NON-NLS-1$ 			
			System.out.println(msg);
			
			t.start();
		}

		buildWBS(elementXml, sup);
		if ( debug ) {
			t.stop();
			msg = t.getTime() + " mini seconds building WBS for " + LibraryUtil.getTypeName(element)  ; //$NON-NLS-1$ 			
			System.out.println(msg);
			
			t.start();
		}
		
		buildTBS(elementXml, sup);	
		
		if ( debug ) {
			t.stop();
			msg = t.getTime() + " mini seconds building TBS for " + LibraryUtil.getTypeName(element)  ; //$NON-NLS-1$ 			
			System.out.println(msg);
	
			t.start();
		}
		
		buildWPBS(elementXml, sup);
		
		if ( debug ) {
			t.stop();
			msg = t.getTime() + " mini seconds building WPBS for " + LibraryUtil.getTypeName(element)  ; //$NON-NLS-1$ 			
			System.out.println(msg);
			
			msg = t.getTotalTime() + " mini seconds building layout xml for " + LibraryUtil.getTypeName(element)  ; //$NON-NLS-1$ 			
			System.out.println(msg);
		}
		
		
//		// for test only
//		ProcessLayoutData data = new ProcessLayoutData(getOwningProcess().getGuid());
//		loadLayoutData(data, true, true, true);
		
		return elementXml;
	}

	private String getUserDiagramText(SupportingMaterial userDiagram)
	{
		// user defined diagram
		AbstractElementLayout l = new GeneralLayout();
		l.init(getLayoutMgr(), userDiagram.getPresentation());
		l.setContentTarget(element);
		EStructuralFeature feature = UmaPackage.eINSTANCE.getContentDescription_MainDescription();
		return (String)l.getAttributeFeatureValue(feature);		
	}
	
	private void setColumns(XmlElement parentXml, List cols) {
		XmlElement colsXml = parentXml.newChild("columns"); //$NON-NLS-1$
		for (Iterator it = cols.iterator(); it.hasNext(); ) {
			BSColumn col = (BSColumn)it.next();
			colsXml.newChild("column") //$NON-NLS-1$
				.setAttribute("id", col.id) //$NON-NLS-1$
				.setAttribute("label", col.label); //$NON-NLS-1$
		}
	}
	
	private void buildWBS(XmlElement elementXml, Suppression sup)
	{
		// build the breakdown structure tree
		XmlElement bs = elementXml
				.newChild("breakdown").setAttribute("name", "Work Breakdown Structure") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.setAttribute("DisplayName", getDisplayName()); //$NON-NLS-1$

		// set the columns
		setColumns(bs, PreferenceUtil.getWBSColumns());
		
		// publish the consolidated view
		ComposedAdapterFactory adapterFactory = layoutManager
				.getCBSAdapterFactory();
		CompositeRoles crs = null; //new CompositeRoles(adapterFactory, (Activity) super.element);
		// if ( super.element instanceof Process )
		{
			ProcessUtil.updateIDs(adapterFactory, sup.getProcess());
		}

		ActivityLayoutSetting setting = new ActivityLayoutSetting(
				adapterFactory, crs, sup);

		// escape the quotes in the string sicne we set the string into javascript variables in the layout
		setting.escapeString = true;
		
		// set to only show task descriptors for sub-activities
		setting.showTaskOnly = true;
		
		// need to get the raw item provider from the adapter factory
		Object wrapper = sup.getObjectByPath(super.paths, adapterFactory);
		if ( wrapper == null ) {
			wrapper = super.element;
		} 
		
		if ( sup.isSuppressed(wrapper) ) {
			return;
		}
		
		ProcessElementItem elementItem = new ProcessElementItem(wrapper, super.element, super.elementProcessPath);
		iterate(elementItem, bs, setting);
		

		XmlElement diagrams = elementXml.newChild("diagrams"); //$NON-NLS-1$
		
		String diagramType;
		String imgFile;
		diagramType = ResourceHelper.DIAGRAM_TYPE_WORKFLOW;
		SupportingMaterial userDiagram = userDiagramInfo.getActivityDiagram();
		if ( userDiagramInfo.canPublishADImage() && (userDiagram != null) )
		{				
			diagrams.newChild("userdiagram") //$NON-NLS-1$
				.setAttribute("name", diagramType) //$NON-NLS-1$
				.setValue(getUserDiagramText(userDiagram));
		}
		else
		{
			org.eclipse.epf.diagram.model.util.DiagramInfo uddInfo = getRealizedUDD(sup, diagramType);
			boolean generatedDiagram = true;
			if(uddInfo != null){
				userDiagram = uddInfo.getActivityDiagram();
				if(uddInfo.canPublishADImage() && userDiagram != null){
					diagrams.newChild("userdiagram") //$NON-NLS-1$
					.setAttribute("name", diagramType) //$NON-NLS-1$
					.setValue(getUserDiagramText(userDiagram));
					generatedDiagram = false;
				}
			}
			
			if(generatedDiagram){
				imgFile = ResourceHelper.getDiagramFilePathName(element, diagramType);
				DiagramInfo diagram_workflow = generateDiagram(sup, diagramType, imgFile);	
				if (diagram_workflow != null
					&& diagram_workflow.getImageFileName() != null) {
				diagrams.newChild("diagram") //$NON-NLS-1$
						.setAttribute("name", ResourceHelper.DIAGRAM_TYPE_WORKFLOW) //$NON-NLS-1$
						.setAttribute("alt", getDiagramDisplayName(ResourceHelper.DIAGRAM_TYPE_WORKFLOW)) //$NON-NLS-1$
						//.setValue(diagram_workflow.getHTML());
						.addChild(diagram_workflow.getXmlElement());
				}
			}
		}
		
		diagramType = ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL;
		userDiagram = userDiagramInfo.getActivityDetailDiagram();
		if ( userDiagramInfo.canPublishADDImage() && (userDiagram != null) )
		{				
			diagrams.newChild("userdiagram") //$NON-NLS-1$
				.setAttribute("name", diagramType) //$NON-NLS-1$
				.setValue(getUserDiagramText(userDiagram));
		}
		else
		{
			imgFile = ResourceHelper.getDiagramFilePathName(element, diagramType);
			DiagramInfo diagram_detail = generateDiagram(sup, diagramType, imgFile);	
			if (diagram_detail != null && diagram_detail.getImageFileName() != null) {
				diagrams
						.newChild("diagram") //$NON-NLS-1$
						.setAttribute("name", ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL) //$NON-NLS-1$
						.setAttribute(
								"alt", getDiagramDisplayName(ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL)) //$NON-NLS-1$
						//.setValue(diagram_detail.getHTML());
						.addChild(diagram_detail.getXmlElement());
			}
		}
		
		diagramType = ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY;
		userDiagram = userDiagramInfo.getWPDDiagram();
		if ( userDiagramInfo.canPublishWPDImage() && (userDiagram != null) )
		{				
			diagrams.newChild("userdiagram") //$NON-NLS-1$
				.setAttribute("name", diagramType) //$NON-NLS-1$
				.setValue(getUserDiagramText(userDiagram));
		}
		else
		{
			imgFile = ResourceHelper.getDiagramFilePathName(element, diagramType);
			DiagramInfo diagram_wp = generateDiagram(sup, diagramType, imgFile);	
			if (diagram_wp != null && diagram_wp.getImageFileName() != null) {
				diagrams.newChild("diagram") //$NON-NLS-1$
						.setAttribute("name", ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY) //$NON-NLS-1$
						.setAttribute(
								"alt", getDiagramDisplayName(ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY)) //$NON-NLS-1$
						//.setValue(diagram_wp.getHTML());
						.addChild(diagram_wp.getXmlElement());
			}
		}
	}
	
	private String getDiagramDisplayName(String type) {
	
		String name = getDisplayName();
		if ( ResourceHelper.DIAGRAM_TYPE_WORKFLOW.equals(type) ) {
			return NLS.bind(LibraryResources.activityDiagramName, name);		
		} else if ( ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL.equals(type) ) {
			return NLS.bind(LibraryResources.activityDetailDiagramName, name);		
		} else if ( ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY.equals(type) ) {
			return NLS.bind(LibraryResources.wpDependencyDiagramName, name);		
		} else {
			return ""; //$NON-NLS-1$
		}
	}
	
//	private DiagramInfo generateDiagram(Suppression sup, String diagramType, String imgFile) {
//		
////		if ( "_o3nZoSFrEdqrX8YVzvtlIg,_jin2oCGGEdqMcovRzkCQow".equals(super.elementProcessPath) ) {
////			System.out.println("xx");
////		}
//		
//		DiagramInfo info = null;
//		ElementLayoutManager mgr = getLayoutMgr();
//		if (mgr != null) {
//			info = mgr.getCachedDiagramInfo(sup, diagramType, imgFile);
//			if (info != null) {
//				System.err.println("*** diagram generated: " + info.path + ",  file name: " + imgFile);
//
//				return info;
//			}
//		}
//		info = generateDiagram_(sup, diagramType, imgFile);
//		if ( info != null ) {
//			info.path = super.elementProcessPath;
//		}
//		
//		if (mgr != null) {
//			mgr.addCachedDiagramInfo(info, sup, diagramType, imgFile);
//		}
//		return info;
//	}
	
	private DiagramInfo generateDiagram(Suppression sup, String diagramType, String imgFile)
	{
		Timer timer = null;
		if (debug ){
			System.out.println("START: generating diagram " + imgFile);//$NON-NLS-1$
			timer = new Timer();			
		}
		// generate diagram
		// show the activity diagrams
		IActivityDiagramService diagramService = layoutManager
				.getActivityDiagramService();
		DiagramInfo diagram = null;
		if (diagramService != null) {
			IFilter filter = layoutManager.getDiagramAdapterFactory().getFilter();
			
			// get the wrapper from the WBS adapter factory
			Object wrapper = sup.getObjectByPath(super.paths, layoutManager.getDiagramAdapterFactory());
			
			// Set the publishing mode
			boolean publishingmode = LibraryUtil.PUBLISH_MODE;
			LibraryUtil.PUBLISH_MODE = true;

			boolean oldValue = DescriptorPropUtil.useLinkedElementInDiagram;
			try {
				DescriptorPropUtil.useLinkedElementInDiagram = layoutManager.getValidator().showLinkedPageForDescriptor();				
				diagram = diagramService.saveDiagram(wrapper,
					imgFile,
					diagramType, filter, sup);
			} finally {
				if (DescriptorPropUtil.useLinkedElementInDiagram != oldValue) {
					DescriptorPropUtil.useLinkedElementInDiagram = oldValue;
				}
			}
			
			// set back to 
			LibraryUtil.PUBLISH_MODE = publishingmode;
		}

		if ( timer != null ){
			timer.stop();	
			String msg = timer.getTime() + " mini seconds generating " + diagramType + " diagram"  ; //$NON-NLS-1$ //$NON-NLS-2$ 			
			System.out.println(msg);
			System.out.println("END: generating diagram " + imgFile);//$NON-NLS-1$
		}
		
		return diagram;

	}
	
	private org.eclipse.epf.diagram.model.util.DiagramInfo getRealizedUDD(Suppression sup, String diagramType)
	{
		// generate diagram
		// show the activity diagrams
		IActivityDiagramService diagramService = layoutManager
				.getActivityDiagramService();
		if (diagramService != null) {
			IFilter filter = layoutManager.getDiagramAdapterFactory().getFilter();
			
			// get the wrapper from the WBS adapter factory
			Object wrapper = sup.getObjectByPath(super.paths, layoutManager.getDiagramAdapterFactory());

			Object realized = diagramService.getRealizedForUnmodified(wrapper, filter, sup);
			
			if(realized instanceof Activity){
				org.eclipse.epf.diagram.model.util.DiagramInfo uddInfo = new org.eclipse.epf.diagram.model.util.DiagramInfo(
						(Activity)realized);
				if(uddInfo.canPublishADImage()){
					return uddInfo; 
				}
			}
		}
		return null;
	}
	
	private void buildTBS(XmlElement elementXml, Suppression sup)
	{
		XmlElement bs = elementXml
		.newChild("breakdown").setAttribute("name", "Team Breakdown Structure") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		.setAttribute("DisplayName", getDisplayName()); //$NON-NLS-1$

		// set the columns
		setColumns(bs, PreferenceUtil.getTBSColumns());

		ComposedAdapterFactory adapterFactory = layoutManager.getTBSAdapterFactory();
		CompositeRoles crs = null; //new CompositeRoles(adapterFactory, (Activity) super.element);
		
		// roolup the layout
		IBSItemProvider provider = (IBSItemProvider) adapterFactory.adapt(
				super.element, ITreeItemContentProvider.class);
				
		// need to get the raw item provider from the adapter factory
		Object wrapper = sup.getObjectByPath(super.paths, adapterFactory);
		if ( wrapper == null ) {
			wrapper = super.element;
		}
		ProcessElementItem elementItem = new ProcessElementItem(wrapper, super.element, super.elementProcessPath);

		// collect all RoleDescriptors before rollup
		provider.setRolledUp(false);
		itemDetail.iterate(adapterFactory, wrapper);

		provider.setRolledUp(true);		
		ActivityLayoutSetting setting = new ActivityLayoutSetting(adapterFactory, crs, sup);
		setting.rollupRoles = true;
		setting.escapeString = true;

		iterate(elementItem, bs, setting);
	}
	
	private void buildWPBS(XmlElement elementXml, Suppression sup)
	{
		XmlElement bs = elementXml
		.newChild("breakdown").setAttribute("name", "Work Product Breakdown Structure") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		.setAttribute("DisplayName", getDisplayName()); //$NON-NLS-1$

		// set the columns
		setColumns(bs, PreferenceUtil.getWPBSColumns());

		ComposedAdapterFactory adapterFactory = layoutManager.getWPBSAdapterFactory();
		CompositeRoles crs = null; //new CompositeRoles(adapterFactory, (Activity) super.element);
		
		// roolup the layout
		IBSItemProvider provider = (IBSItemProvider) adapterFactory.adapt(super.element,
				ITreeItemContentProvider.class);
		provider.setRolledUp(true);
		
		ActivityLayoutSetting setting = new ActivityLayoutSetting(adapterFactory, crs, sup);
		setting.escapeString = true;

		// need to get the raw item provider from the adapter factory
		Object wrapper = sup.getObjectByPath(super.paths, adapterFactory);
		if ( wrapper == null ) {
			wrapper = super.element;
		}
		ProcessElementItem elementItem = new ProcessElementItem(wrapper, super.element, super.elementProcessPath);
		iterate(elementItem, bs, setting);

	}

	/**
	 * iterate the break down structure and build the xml document
	 * 
	 * @param parentItem
	 *            The object to iterate. It can be a breakdown element, or it's
	 *            adaptor
	 * @param parentXml
	 * @param adapterFactory
	 */
	private void iterate(ProcessElementItem parentItem, XmlElement parentXml,
			ActivityLayoutSetting setting) {
		iterate(parentItem, parentXml, setting, 0);
	}
	
	// level to expand all BWS activities to, 0 for the first level
	private static final int ACTIVITY_SHOW_LEVEL = 1;
	
	/**
	 * iterate the break down structure and build the xml document
	 * 
	 * @param parentItem
	 *            The object to iterate. It can be a breakdown element, or it's
	 *            adaptor
	 * @param parentXml
	 * @param adapterFactory
	 */
	private void iterate(ProcessElementItem parentItem, XmlElement parentXml,
			ActivityLayoutSetting setting, int actLevel) {
		ITreeItemContentProvider provider = null;
		Object parentObj = parentItem.rawItem;
			
		Timer timer = null;
		if (debug ){
			timer = new Timer();			
		}
		
		if (parentObj instanceof ITreeItemContentProvider) {
			provider = (ITreeItemContentProvider) parentObj;
		} else {
			provider = (ITreeItemContentProvider) setting.adapterFactory.adapt(
					parentObj, ITreeItemContentProvider.class);
		}

		// Either delegate the call or return nothing.
		if (provider != null) {
//			String displayName = ProcessUtil.getAttribute(parentObj,
//					IBSItemProvider.COL_PRESENTATION_NAME, provider);
//			parentXml.setAttribute("DisplayName", displayName); //$NON-NLS-1$

			//List uniqueList = new ArrayList();
			Collection items = provider.getChildren(parentObj);
			for (Iterator it = items.iterator(); it.hasNext();) {
				Object rawitem = it.next();

				MethodElement item = (MethodElement) LibraryUtil
						.unwrap(rawitem);
				
				//Filter out WPDs and RDs from WBS page 
				if (setting.adapterFactory == layoutManager.getCBSAdapterFactory()) {
					if (item instanceof WorkProductDescriptor || item instanceof RoleDescriptor) {
						continue;
					}
				} 
				
				if (item instanceof WorkProductDescriptor) {
					if (! ConfigurationHelper.inConfig(item, getLayoutMgr().getConfiguration())) {
						continue;
					}
				}

				boolean aSkipIterCond = false;
				if ( setting.showTaskOnly && (actLevel > ACTIVITY_SHOW_LEVEL)
						&& !(item instanceof Activity || item instanceof TaskDescriptor) ) {
					if (item instanceof Milestone) {
						aSkipIterCond = true;
					} else {
						continue;
					}
				}

				if ( item == null ) {
					continue;
				}
				
				// add the element to the reference list, 
				// this is for publishing only, for browsing, this call does nothing
				getLayoutMgr().getValidator().addReferencedElement(super.element, item);
				
				ProcessElementItem elementItem = new ProcessElementItem(rawitem, item, parentItem);

				// Process Publishing: Replace role descriptor
				// with composite role
				// if it contains the role descriptor during preview/publishing
				// so if this guy is a RoleDescrptor, find the composite role
				// descriptor within the scope
				// 
				if ((parentObj instanceof Activity && setting.crs != null)
						&& (item instanceof RoleDescriptor)) {
					CompositeRole cr = setting.crs.getCompositeRole(
							(Activity) parentObj, (RoleDescriptor) item);
					if (cr != null) {
						item = cr;
					}
				}

				XmlElement child = generateChildXml(elementItem, parentXml, setting);
					
				parentXml.addChild(child);
									
				// show only task task descriptors for sub activities in the tbs layout
				if ( setting.showTaskOnly && (actLevel > ACTIVITY_SHOW_LEVEL) && !(item instanceof Activity )) {
					// if act level > 0, only iterate the sub-activities
					continue;
				}
				
				if (aSkipIterCond) {
					continue;
				}
				
				// ineterate children, not just activity, any child in the
				// rawitem should be iterated,
				// such as the sub-artifacts
				iterate(elementItem, child, setting, 
						(item instanceof Activity) ? actLevel+1 : actLevel);				
			}
		}
		
		if ( timer != null ) {
			timer.stop();	
			if (timer.getTime() > 100) {
				String msg = timer.getTime() + " mini seconds iterating breakdown item " + LibraryUtil.getTypeName(parentItem.element)  ; //$NON-NLS-1$ 		
				System.out.println(msg);
			}
		}
	}

	private XmlElement generateChildXml(ProcessElementItem elementItem, XmlElement parentXml,
			ActivityLayoutSetting setting)
	{
		Timer timer = null;
		if (debug ){
			timer = new Timer();			
		}
		
		MethodElement item = elementItem.element;
		
		IElementLayout l = getLayout(elementItem);
	
		// lat the presentation to determine when to show
		// set generate the flag for browsing
		boolean isSupressed = setting.sup.isSuppressed(elementItem.rawItem);
		if ( isSupressed ) {
			itemDetail.addSuppressed(elementItem.element);
			
		} else if (elementItem.rawItem instanceof BreakdownElementWrapperItemProvider &&
				item instanceof BreakdownElement) {
			try {
				isSupressed = isSuppressedDueToContribution((BreakdownElementWrapperItemProvider) elementItem.rawItem, setting.sup.getProcess(), 
					(BreakdownElement) item);
			} catch (Exception e) {
				isSupressed = false;
			}
		}
		
		XmlElement child = l.getXmlElement(false);

		// we still generate the item even though it's suppressed, 
		// let the layout javascript to handle the show or hide
		// set the isSupressed flag for browsing model only,
		// for publishing, the flag will be geenrated and saved in a seperated js file
		// published html pages will get the isSupressed flag via javascript method.
		if ( !getLayoutMgr().isPublishingMode() )
		{
			child.setAttribute("isSupressed", (isSupressed ? "true" : "false") ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		
		IBSItemProvider adapter = null;
		if (elementItem.rawItem instanceof IBSItemProvider) {
			adapter = (IBSItemProvider) elementItem.rawItem;
		} else {
			adapter = (IBSItemProvider) setting.adapterFactory
					.adapt(elementItem.element, ITreeItemContentProvider.class);
			;
		}

		// get the index and predecessor indeces
		if (elementItem.element instanceof BreakdownElement) {
			String index = getIndex(adapter);
			
			String modelInfo = null;			
			if (adapter instanceof ComposedWPDescriptorWrapperItemProvider) {
				ComposedWPDescriptorWrapperItemProvider provider = (ComposedWPDescriptorWrapperItemProvider) adapter;
				modelInfo = provider.getAttribute(item, IBSItemProvider.COL_MODEL_INFO);
			} else {
				modelInfo = ProcessUtil.getAttribute(item,
						IBSItemProvider.COL_MODEL_INFO, adapter);
			}			
			String modelInfoKey = ModelInfoKeyMap.getInstance().getModelInfoKey(modelInfo);
			
			String team = ProcessUtil.getAttribute(item,
					IBSItemProvider.COL_TEAMS, adapter);
			String prefix = ProcessUtil.getAttribute(item,
					IBSItemProvider.COL_PREFIX, adapter);			
			String isEventDriven = ProcessUtil.getAttribute(item,
					IBSItemProvider.COL_IS_EVENT_DRIVEN, adapter);
			String isOngoing = ProcessUtil.getAttribute(item,
					IBSItemProvider.COL_IS_ONGOING, adapter);
			String isOptional = ProcessUtil.getAttribute(item,
					IBSItemProvider.COL_IS_OPTIONAL, adapter);
			String isPlanned = ProcessUtil.getAttribute(item,
					IBSItemProvider.COL_IS_PLANNED, adapter);
			String isRepeatable = ProcessUtil.getAttribute(item,
					IBSItemProvider.COL_IS_REPEATABLE, adapter);
			String hasMultipleOccurrences = ProcessUtil
					.getAttribute(
							item,
							IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES,
							adapter);
			
//			String displayName = ProcessUtil.getAttribute(item,
//					IBSItemProvider.COL_PRESENTATION_NAME, adapter);
			
			String displayName = ConfigurationHelper.getPresentationName(item, this.getLayoutMgr().getConfiguration());
			
			String mName = ProcessUtil.getAttribute(item,
					IBSItemProvider.COL_NAME, adapter);

			// for WBS layout we put the string into javascript variables
			// so need to escape the quotes " and '
			if ( setting.escapeString )
			{
				modelInfo = StrUtil.escape(modelInfo);
				displayName = XMLUtil.escape(displayName);
				mName = XMLUtil.escape(mName);
				
				// and the url
				// need an escaped url for javascript
				String jsEscapedUrl = StrUtil.escape(l.getUrl());
				child.setAttribute("Url", jsEscapedUrl); //$NON-NLS-1$

			}
			
			child.setAttribute("Index", index) //$NON-NLS-1$
					.setAttribute("ModelInfo", modelInfo) //$NON-NLS-1$
					.setAttribute("ModelInfoKey", modelInfoKey) //$NON-NLS-1$
					.setAttribute("Team", team); //$NON-NLS-1$
					
			child.newChild("attribute").setAttribute("name", "prefix").setValue(prefix); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			
			child
					.newChild("attribute").setAttribute("name", "isEventDriven").setValue(isEventDriven); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
			child
					.newChild("attribute").setAttribute("name", "isOngoing").setValue(isOngoing); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
			child
					.newChild("attribute").setAttribute("name", "isOptional").setValue(isOptional); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
			child
					.newChild("attribute").setAttribute("name", "isPlanned").setValue(isPlanned); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
			child
					.newChild("attribute").setAttribute("name", "isRepeatable").setValue(isRepeatable); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
			child
					.newChild("attribute").setAttribute("name", "hasMultipleOccurrences").setValue(hasMultipleOccurrences); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 

			child.setAttribute("DisplayName", displayName); //$NON-NLS-1$
			child.setAttribute("Name", mName); //$NON-NLS-1$
		}		

		if (item instanceof WorkProductDescriptor) {
			String entryState = adapter.getAttribute(item, IBSItemProvider.COL_ENTRY_STATE);
			String exitState = adapter.getAttribute(item, IBSItemProvider.COL_EXIT_STATE);
			String deliverable = adapter.getAttribute(item, IBSItemProvider.COL_DELIVERABLE);

			child.setAttribute("EntryState", entryState) //$NON-NLS-1$
					.setAttribute("ExitState", exitState) //$NON-NLS-1$
					.setAttribute("Deliverable", deliverable); //$NON-NLS-1$
		}

		if (item instanceof WorkBreakdownElement) {
			String predecessors = getPredecessors(adapter,
					setting.sup);
			child.setAttribute("Predecessors", predecessors); //$NON-NLS-1$
		}

		// if it's a task descriptor, get the steps
		if (item instanceof TaskDescriptor) {

			XmlElement stepsXml = child.newChild("Steps"); //$NON-NLS-1$
			TaskDescriptor td = (TaskDescriptor) item;
			List selSteps = td.getSelectedSteps();
			Task t = (Task) ConfigurationHelper
					.getCalculatedElement(td.getTask(),
							getLayoutMgr().getConfiguration());
			if (t != null) {
				List steps = ConfigurationHelper
						.calc0nFeatureValue(t, UmaPackage.eINSTANCE
								.getTask_Steps(), getLayoutMgr()
								.getElementRealizer());
				for (int i = 0; i < steps.size(); i++) {
					Object step = steps.get(i);
					boolean selected = selSteps.contains(step);
					stepsXml
							.newChild("Step") //$NON-NLS-1$
							.setAttribute(
									"index", Integer.toString(i)) //$NON-NLS-1$
							.setAttribute(
									"selected", selected ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			}

		}

		// build the team profile tree.
		// This is a temporary replacement for the team breakdown
		// structure diagram
		if (item instanceof TeamProfile) {
			TeamProfile superTeam = ((TeamProfile)item).getSuperTeam();
			child.setAttribute("hasSuperTeam", (superTeam != null) ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

		// if role rollup is set, build the role sub tree
		if (setting.rollupRoles && item instanceof RoleDescriptor) {
			buildRoleRollup((RoleDescriptor) item, child, setting);
		}
		
		if ( timer != null ) {
			timer.stop();	
			if (timer.getTime() > 100) {
				String msg = timer.getTime() + " mini seconds generating xml for breakdown item " + LibraryUtil.getTypeName(elementItem.element)  ; //$NON-NLS-1$ 		
				System.out.println(msg);
			}
		}
		
		return child;
	}

	private boolean isSuppressedDueToContribution(BreakdownElementWrapperItemProvider provider,
			Process process, BreakdownElement breakdownElement) {
		
		Process ownerProcess = ProcessUtil.getProcessForBreakdownElement(breakdownElement);
		if (ownerProcess == null || ownerProcess == process) {
			return false;
		}
		
		boolean isSupressed = false;
		String path = Suppression.getPathWithoutViewType(provider).toString();
		String viewType = Suppression.getViewType(provider);

		String[] guids = path.substring(1).split("/"); 	//$NON-NLS-1$ /
		int sz = guids.length;
		ILibraryManager libMgr = LibraryService.getInstance()
				.getCurrentLibraryManager();

		for (int i = 1; i < sz - 1; i++) {
			MethodElement iThElement = libMgr.getMethodElement(guids[i]);
			if (!(iThElement instanceof Activity)) {
				break;
			}
			Activity act = (Activity) iThElement;
			Process proc = ProcessUtil.getProcess(act);
			if (proc == null) {
				break;
			}

			VariabilityElement base = proc.getVariabilityBasedOnElement();
			if (base != null
					&& proc.getVariabilityType() == VariabilityType.CONTRIBUTES) {
				String path1 = viewType + "://" + proc.getGuid();	//$NON-NLS-1$ /
				for (int j = i; j < sz; j++) {
					path1 += "/" + guids[j];  						//$NON-NLS-1$ /
				}
				if (getSuppression(proc).getSuppressedExternalElementPaths()
						.contains(path1)) {
					isSupressed = true;
					break;
				}
				if (proc == ownerProcess) {
					break;
				}				
			}
		}

		return isSupressed;
	}
	
	
	private void buildRoleRollup(RoleDescriptor roleItem, XmlElement parentXml,
			ActivityLayoutSetting setting) {
		Role r = roleItem.getRole();
		List descriptors = itemDetail.getDescriptors(r);
		if ( descriptors == null ) {
			descriptors = new ArrayList();
		}
		if ( !descriptors.contains(roleItem) ) {
			descriptors.add(roleItem);
		}
		
		List responsibleFor = new ArrayList();
		List modifies = new ArrayList();
		List primaryTasks = new ArrayList();
		List additionalTasks = new ArrayList();	
		List assistTasks = new ArrayList();
		List items;
		
		for ( Iterator it = descriptors.iterator(); it.hasNext(); ) {
			
			roleItem = (RoleDescriptor) it.next();
			
			items = ConfigurationHelper.calc0nFeatureValue(roleItem,
					UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor(), 
					getLayoutMgr().getElementRealizer());
			responsibleFor.addAll(items);
				
			items = ConfigurationHelper.calc0nFeatureValue(roleItem,
					UmaPackage.eINSTANCE.getRoleDescriptor_Modifies(), getLayoutMgr().getElementRealizer());
			modifies.addAll(items);
			
			items = ConfigurationHelper.calc0nFeatureValue(roleItem,
					AssociationHelper.RoleDescriptor_PrimaryTaskDescriptors,
					getLayoutMgr().getElementRealizer());
			primaryTasks.addAll(items);
			
			items = ConfigurationHelper.calc0nFeatureValue(roleItem,
					AssociationHelper.RoleDescriptor_AdditionalTaskDescriptors, 
					getLayoutMgr().getElementRealizer());
			additionalTasks.addAll(items);
			
			items = ConfigurationHelper.calc0nFeatureValue(roleItem,
					AssociationHelper.RoleDescriptor_AssistsIn_TaskDescriptors,
					getLayoutMgr().getElementRealizer());
			assistTasks.addAll(items);
		}
		
		Collections.sort(responsibleFor, Comparators.PRESENTATION_NAME_COMPARATOR);
		createRoleRollupNodes(parentXml, responsibleFor, TngUtil
				.getFeatureText(UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor()), setting);

		Collections.sort(modifies, Comparators.PRESENTATION_NAME_COMPARATOR);
		createRoleRollupNodes(parentXml, modifies, TngUtil
				.getFeatureText(UmaPackage.eINSTANCE.getRoleDescriptor_Modifies()), setting);

		Collections.sort(primaryTasks, Comparators.PRESENTATION_NAME_COMPARATOR);
		createRoleRollupNodes(parentXml, primaryTasks, 
				LibraryResources.ActivityLayout_primaryTasks_text, setting); 

		Collections.sort(additionalTasks, Comparators.PRESENTATION_NAME_COMPARATOR);
		createRoleRollupNodes(parentXml, additionalTasks, 
				LibraryResources.ActivityLayout_additionalTasks_text, setting); 
		
		Collections.sort(assistTasks, Comparators.PRESENTATION_NAME_COMPARATOR);
		createRoleRollupNodes(parentXml, assistTasks, 
				LibraryResources.ActivityLayout_assistTasks_text, setting);

		ModifiedTypeMeta meta = TypeDefUtil.getMdtMeta(UmaPackage.eINSTANCE.getRole());
		if (meta != null) {
			for ( Iterator it = descriptors.iterator(); it.hasNext(); ) {			
				roleItem = (RoleDescriptor) it.next();				
				for (ExtendedReference eRef : meta.getReferences()) {
					if (ExtendedReference.WorkProducts.equals(eRef.getContributeTo())) {
						items = PropUtil.getPropUtil().getExtendedReferenceList(roleItem, eRef, false);
						createRoleRollupNodes(parentXml, items, 
								eRef.getName(), setting);
					}
				}
				
			}
		}
		
		meta = TypeDefUtil.getMdtMeta(UmaPackage.eINSTANCE.getTask());
		if (meta != null) {
			for ( Iterator it = descriptors.iterator(); it.hasNext(); ) {			
				roleItem = (RoleDescriptor) it.next();				
				for (ExtendedReference eRef : meta.getReferences()) {
					if (ExtendedReference.Roles.equals(eRef.getContributeTo())) {
						items = PropUtil.getPropUtil().getReferencingList(roleItem, eRef);
						String info = LibraryResources.bind(LibraryResources.ActivityLayout_performAs_text, (new String[] {eRef.getName()}));
						createRoleRollupNodes(parentXml, items, 
								info, setting);
					}
				}
				
			}
		}
	}
	
	private void createRoleRollupNodes(XmlElement parentXml, List items,
			String info, ActivityLayoutSetting setting) {
		
		// 160188 - Published Team Allocation tab shows redundant information
		// only show one descriptor if more than one are linked to the same task or wp.
		// so keep the processed task and wp instead of the descriptors
		List processed = new ArrayList();
		MethodElement linked = null;
		for (Iterator it = items.iterator(); it.hasNext();) {
			MethodElement e = (MethodElement) it.next();
			linked = null;
			if ( e instanceof TaskDescriptor ) {
				linked = ((TaskDescriptor)e).getTask();
			} else if ( e instanceof WorkProductDescriptor ) {
				linked = ((WorkProductDescriptor)e).getWorkProduct();
			}
			if ( linked == null ) {
				linked = e;
			}
			
			if ( processed.contains(linked) ) {
				continue;
			}
			
			processed.add(linked);
			
			IElementLayout l = layoutManager.getLayout(e, true);
			XmlElement child = l.getXmlElement(false);
			child.setAttribute("ModelInfo", info); //$NON-NLS-1$
			String modelInfoKey = ModelInfoKeyMap.getInstance().getModelInfoKey(info);
			child.setAttribute("ModelInfoKey", modelInfoKey); //$NON-NLS-1$
			parentXml.addChild(child);
			
			if ( setting.escapeString )
			{
				String displayName = ConfigurationHelper.getPresentationName(e, this.getLayoutMgr().getConfiguration());
				displayName = XMLUtil.escape(displayName);
				child.setAttribute("DisplayName", displayName); //$NON-NLS-1$
			}
			
//			lookup the list to see if the item is a suppressed one - for RATLC00264374
//			if ( !getLayoutMgr().isPublishingMode() )
//			{
				for ( Object objItem : itemDetail.suppressedItems) {
					MethodElement item = (MethodElement)objItem;
					if ( item.getGuid().equals(l.getId())) {
						child.setAttribute("isSupressed", "true"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						break;
					}
				}
			}
//		}
	}


	private String getIndex(IBSItemProvider adapter) {
		// IBSItemProvider adapter = (IBSItemProvider) factory.adapt(e,
		// ITreeItemContentProvider.class);;
		if (adapter != null) {
			int index = adapter.getId();
			if (index == 0) {
				return ""; //$NON-NLS-1$
			}
			return Integer.toString(index);
		}

		return ""; //$NON-NLS-1$
	}

	private String getPredecessors(IBSItemProvider adapter, Suppression sup) {
		// IBSItemProvider adapter = (IBSItemProvider) factory.adapt(e,
		// ITreeItemContentProvider.class);;
		if (adapter != null) {
			PredecessorList list = adapter.getPredecessors();
			if (list != null) {
				return list.toUnSuppressedString(sup, true);
			}
		}

		return ""; //$NON-NLS-1$
	}

	public class CompositeRoles {
		// a map of activity to CompositeRoleInfo
		Map itemMap = new HashMap();

		public CompositeRoles(ComposedAdapterFactory adapterFactory,
				Activity act) {
			scan(adapterFactory, null, act);
		}

		private CompositeRoleInfo getCompositeRoleInfo(Activity parent,
				Activity act) {
			CompositeRoleInfo info = (CompositeRoleInfo) itemMap.get(act);
			if (info == null) {
				info = new CompositeRoleInfo(parent, act);
				itemMap.put(act, info);
			}

			return info;
		}

		private void scan(ComposedAdapterFactory adapterFactory,
				Activity parent, Activity act) {
			ITreeItemContentProvider provider = (ITreeItemContentProvider) adapterFactory
					.adapt(act, ITreeItemContentProvider.class);
			if (provider != null) {
				Collection items = provider.getChildren(act);
				for (Iterator it = items.iterator(); it.hasNext();) {
					MethodElement item = (MethodElement) LibraryUtil.unwrap(it
							.next());
					if (item instanceof CompositeRole) {
						getCompositeRoleInfo(parent, act).addCompositeRole(
								(CompositeRole) item);
					} else if (item instanceof Activity) {
						scan(adapterFactory, act, (Activity) item);
					}
				}
			}
		}

		/**
		 * find the composite role for the role descriptor from the activity and
		 * it's parents, return null if not found
		 * 
		 * @param activity
		 * @param item
		 * @return RoleDescriptor
		 */
		private CompositeRole getCompositeRole(Activity activity,
				RoleDescriptor item) {
			// iterate the breakdown elements,
			// if there is a composite role that contains the role, return the
			// composite,
			// if not, find in the parent activity

			if (activity == null) {
				return null;
			}

			CompositeRole desc = null;
			CompositeRoleInfo info = (CompositeRoleInfo) itemMap.get(activity);
			if (info != null) {
				desc = info.getCompositeRole(item);
				if (desc != null) {
					return desc;
				}

				return getCompositeRole(info.getParentActivity(), item);
			}

			return null;
		}
	}

	public class CompositeRoleInfo {
		Activity parent;

		Activity owner;

		List items = new ArrayList();

		public CompositeRoleInfo(Activity parent, Activity owner) {
			this.parent = parent;
			this.owner = owner;
		}

		private void addCompositeRole(CompositeRole e) {
			if (!items.contains(e)) {
				items.add(e);
			}
		}

		private Activity getParentActivity() {
			return parent;
		}

		private CompositeRole getCompositeRole(RoleDescriptor item) {
			if (items.size() == 0) {
				return null;
			}

			Role r = item.getRole();
			for (Iterator it = items.iterator(); it.hasNext();) {
				CompositeRole cr = (CompositeRole) it.next();
				if (cr.getAggregatedRoles().contains(r)) {
					return cr;
				}
			}

			return null;
		}
	}

	public class ActivityLayoutSetting {
		public ComposedAdapterFactory adapterFactory;

		public CompositeRoles crs;

		public Suppression sup;

		boolean rollupRoles = false;

		public boolean escapeString = false;
		
		// set to show task only for sub activities
		// this only apply to CBS layout
		public boolean showTaskOnly = false;
		
		public ActivityLayoutSetting(ComposedAdapterFactory adapterFactory,
				CompositeRoles crs, Suppression sup) {
			this.adapterFactory = adapterFactory;
			this.crs = crs;
			this.sup = sup;

		}
	}

	public class TBSItemDetail {
		
		// record the suppressed items
		List suppressedItems = new ArrayList();
		
		// map of role to role descriptors
		Map roleDescriptorMap = new HashMap();
		
		public TBSItemDetail() {
			
		}
		
		public void addSuppressed(Object item) {
			if ( !suppressedItems.contains(item) ) {
				suppressedItems.add(item);
			}
		}
		
		public void iterate(ComposedAdapterFactory adapterFactory, Object parentObj) {
		
			ITreeItemContentProvider provider = null;
			if (parentObj instanceof ITreeItemContentProvider) {
				provider = (ITreeItemContentProvider) parentObj;
			} else {
				provider = (ITreeItemContentProvider) adapterFactory.adapt(
						parentObj, ITreeItemContentProvider.class);
			}

			// Either delegate the call or return nothing.
			if (provider == null) {
				return;
			}
				
			Collection items = provider.getChildren(parentObj);
			for (Iterator it = items.iterator(); it.hasNext();) {
				Object rawitem = it.next();

				MethodElement item = (MethodElement) LibraryUtil.unwrap(rawitem);
				if ( item instanceof RoleDescriptor ) {
					addRoleDescriptor( (RoleDescriptor)item );
				} else {
					iterate(adapterFactory, rawitem);
				}
			}
		}
		
		
		private void addRoleDescriptor(RoleDescriptor rdesc) {
			Role r = rdesc.getRole();
			if (r != null ) {
				List items = (List)roleDescriptorMap.get(r);
				if ( items == null ) {
					items = new ArrayList();
					roleDescriptorMap.put(r, items);
					if (debug ) {
						System.out.println("Added RoleDescriptor " + rdesc.getPresentationName()); //$NON-NLS-1$
					}
				}
				
				if ( !items.contains(rdesc) ) {
					items.add(rdesc);
				}
			}
		}
		
		public List getDescriptors(Role r) {
			return (List)roleDescriptorMap.get(r);
		}
	}
	
	/**
	 * load the process specific layout data for publishing.
	 * browsing does not need this since it's generated on the fly
	 * @param proc_data
	 */
	public void loadLayoutData(ProcessLayoutData proc_data, boolean loadWbs, boolean loadTbs, boolean loadWpbs)
	{
		Timer t = new Timer();		

		ActivityLayoutData act_data = proc_data.createActivityLauoutData(this.elementProcessPath);
		
		// iterate the breakdown structure and collect the info.
		Process proc = getOwningProcess();
		if ( proc == this.element ) {
			supCount = 0;
		}
		
		// need to handle the supressed breakdown elements
		// use the Supress utility
		Suppression sup = getSuppression(proc);

		// need to interate the 3 layout structure sicne their path is different
		if ( loadWbs ) {
			loadWBSLayoutData(act_data, sup);
		}
		
		if ( loadTbs ) {
			loadTBSLayoutData(act_data, sup);
		}
		
		if ( loadWpbs ) {
			loadWPBSLayoutData(act_data, sup);
		}
		
		if ( debug ) {
			t.stop();
			String msg = t.getTime()
					+ " mini-second(s) loading layout data for process " + LibraryUtil.getTypeName(proc) //$NON-NLS-1$ /
					+ ", activity: " + LibraryUtil.getTypeName(super.element); //$NON-NLS-1$ /			
			System.out.println(msg);
		}
	}
	
	private void loadWBSLayoutData(ActivityLayoutData act_data, Suppression sup)
	{
		// publish the consolidated view
		ComposedAdapterFactory adapterFactory = layoutManager
				.getCBSAdapterFactory();
		CompositeRoles crs = null; //new CompositeRoles(adapterFactory, (Activity) super.element);

		ActivityLayoutSetting setting = new ActivityLayoutSetting(
				adapterFactory, crs, sup);

		// need to get the raw item provider from the adapter factory
		Object wrapper = sup.getObjectByPath(super.paths, adapterFactory);
		if ( wrapper == null ) {
			wrapper = super.element;
		}
		ProcessElementItem elementItem = new ProcessElementItem(wrapper, super.element, super.elementProcessPath);
		iterateProcessItem(elementItem, act_data, setting, true, false, false);

//		// if there is no process-local supressed item, don't generate diagrams
		// no, this will not work, since the text color ofd the diagram is green, if use base, it's black.
		// so need to generate the diagram no matter what.
//		if ( !act_data.hasLocalSuppressed() )
//		{
//			return;
//		}
		
		String diagramType, imgFile;
		
		// make a short prefix for the file name
		String prefix = Integer.toHexString(super.elementProcessPath.hashCode());
		
		diagramType = ResourceHelper.DIAGRAM_TYPE_WORKFLOW;
		imgFile = ResourceHelper.getDiagramFilePathName(element, prefix + "_" + diagramType); //$NON-NLS-1$
		DiagramInfo diagram_workflow = generateDiagram(sup, diagramType, imgFile);	
		if (diagram_workflow != null
				&& diagram_workflow.getImageFileName() != null) {
			act_data.setActivityDiagramPath(diagram_workflow.getImageFileName());
		}
		
		diagramType = ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL;
		imgFile = ResourceHelper.getDiagramFilePathName(element, prefix + "_" + diagramType); //$NON-NLS-1$
		DiagramInfo diagram_detail = generateDiagram(sup, diagramType, imgFile);	
		if (diagram_detail != null && diagram_detail.getImageFileName() != null) {
			act_data.setActivityDetailDiagramPath(diagram_detail.getImageFileName());
		}

		diagramType = ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY;
		imgFile = ResourceHelper.getDiagramFilePathName(element, prefix + "_" + diagramType); //$NON-NLS-1$
		DiagramInfo diagram_wp = generateDiagram(sup, diagramType, imgFile);	
		if (diagram_wp != null && diagram_wp.getImageFileName() != null) {
			act_data.setWPDependencyDiagramPath(diagram_wp.getImageFileName());
		}

	}
	
	private void loadTBSLayoutData(ActivityLayoutData act_data, Suppression sup)
	{
		// publish the consolidated view
		ComposedAdapterFactory adapterFactory = layoutManager
				.getTBSAdapterFactory();
		CompositeRoles crs = null; //new CompositeRoles(adapterFactory, (Activity) super.element);

		// roolup the layout
		IBSItemProvider provider = (IBSItemProvider) adapterFactory.adapt(
				super.element, ITreeItemContentProvider.class);
		provider.setRolledUp(true);

		
		ActivityLayoutSetting setting = new ActivityLayoutSetting(
				adapterFactory, crs, sup);
		setting.rollupRoles = true;

		
		// need to get the raw item provider from the adapter factory
		Object wrapper = sup.getObjectByPath(super.paths, adapterFactory);
		if ( wrapper == null ) {
			wrapper = super.element;
		}
		ProcessElementItem elementItem = new ProcessElementItem(wrapper, super.element, super.elementProcessPath);
		iterateProcessItem(elementItem, act_data, setting, false, true, false);

	}
	
	private void loadWPBSLayoutData(ActivityLayoutData act_data, Suppression sup)
	{
		// publish the consolidated view
		ComposedAdapterFactory adapterFactory = layoutManager
				.getWPBSAdapterFactory();
		CompositeRoles crs = null; //new CompositeRoles(adapterFactory, (Activity) super.element);

		// roolup the layout
		IBSItemProvider provider = (IBSItemProvider) adapterFactory.adapt(
				super.element, ITreeItemContentProvider.class);
		provider.setRolledUp(true);
	
		ActivityLayoutSetting setting = new ActivityLayoutSetting(
				adapterFactory, crs, sup);

		
		// need to get the raw item provider from the adapter factory
		Object wrapper = sup.getObjectByPath(super.paths, adapterFactory);
		if ( wrapper == null ) {
			wrapper = super.element;
		}
		ProcessElementItem elementItem = new ProcessElementItem(wrapper, super.element, super.elementProcessPath);
		iterateProcessItem(elementItem, act_data, setting, false, false, true);

	}
	
	private static int supCount = 0;
	
	private void iterateProcessItem(ProcessElementItem parentItem, ActivityLayoutData act_data, 
			ActivityLayoutSetting setting, boolean loadWbs, boolean loadTbs, boolean loadWpbs) {
		
		//String msg = " --- iterate process item " + LibraryUtil.getTypeName(parentItem.element) + "[" + parentItem.path + "]" ; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 			
		//System.out.println(msg);

		ITreeItemContentProvider provider = null;
		Object parentObj = parentItem.rawItem;
		
		if (parentObj instanceof ITreeItemContentProvider) {
			provider = (ITreeItemContentProvider) parentObj;
		} else {
			provider = (ITreeItemContentProvider) setting.adapterFactory.adapt(
					parentObj, ITreeItemContentProvider.class);
		}

		// Either delegate the call or return nothing.
		if (provider != null) {
			Collection items = provider.getChildren(parentObj);
			for (Iterator it = items.iterator(); it.hasNext();) {
				Object rawitem = it.next();			
				
//				// lat the presentation to determine when to show
//				// set generate the flag for browsing
				boolean isSupressed = setting.sup.isSuppressed(rawitem);
				MethodElement item = (MethodElement) LibraryUtil
						.unwrap(rawitem);
								
				ProcessElementItem elementItem = new ProcessElementItem(rawitem, item, parentItem);

				if (debug && isSupressed ) {
					System.out.println("suppressed: " + ++supCount + " : "  //$NON-NLS-1$ //$NON-NLS-2$
							+ LibraryUtil.getTypeName(item) 
							+ ": " + elementItem.path); //$NON-NLS-1$
					
				}

				IElementLayout l = getLayout(elementItem);
				if ( l instanceof AbstractProcessElementLayout )
				{		
					if ( isSupressed )
					{
						String relPath = ((AbstractProcessElementLayout)l).getRelativeProcessPath();
						act_data.setSuppressed(relPath);
					} 
					else 
					{				
						// only iterate un-suppressed item
						// if the layout is an activity layout, collect the layout data for it
						if ( l instanceof ActivityLayout )
						{
							((ActivityLayout)l).loadLayoutData(act_data.getProcessLayoutData(), 
								 loadWbs, loadTbs, loadWpbs);
						}
						else
						{
							// ineterate children, not just activity, any child in the
							// rawitem should be iterated,
							// such as the sub-artifacts
							iterateProcessItem(elementItem, act_data, setting, loadWbs, loadTbs, loadWpbs);						
						}
					}
				}
			}
		}
	}	
	
	/**
	 * get all the linked tasks, roles, and workproducts for this activity and all it's breakdown elements, recursively.
	 * the element found are notified via IContentValidator.addReferencedElement()
	 * 
	 */
	public void findAllLinkedElements() {
		// iterate the breakdown structure and collect the info.
		Process proc = getOwningProcess();
		
		// need to handle the supressed breakdown elements
		// use the Supress utility
		Suppression sup = getSuppression(proc);

		// publish the consolidated view
		ComposedAdapterFactory adapterFactory = layoutManager
				.getCBSAdapterFactory();

		// need to get the raw item provider from the adapter factory
		Object wrapper = sup.getObjectByPath(super.paths, adapterFactory);
		if ( wrapper == null ) {
			wrapper = super.element;
		}
		
		getLayoutMgr().getValidator().addReferencedElement(null, super.element);

		iterateItemForLinkedElements(wrapper, adapterFactory, sup);
	}

	private void iterateItemForLinkedElements(Object parentObj, ComposedAdapterFactory adapterFactory, Suppression sup) {
		
		ITreeItemContentProvider provider = null;
		
		if (parentObj instanceof ITreeItemContentProvider) {
			provider = (ITreeItemContentProvider) parentObj;
		} else {
			provider = (ITreeItemContentProvider) adapterFactory.adapt(
					parentObj, ITreeItemContentProvider.class);
		}

		// Either delegate the call or return nothing.
		if (provider == null) {
			return;
		}
		
		Collection items = provider.getChildren(parentObj);
		for (Iterator it = items.iterator(); it.hasNext();) {
			Object rawitem = it.next();			
			
			if ( sup.isSuppressed(rawitem) ) {
				continue;
			}
			
			MethodElement item = (MethodElement) LibraryUtil
					.unwrap(rawitem);
			MethodElement e = null;
			if ( item instanceof TaskDescriptor ) {
				e = ((TaskDescriptor)item).getTask();
			} else if ( item instanceof RoleDescriptor ) {
				e = ((RoleDescriptor)item).getRole();
			} else if ( item instanceof WorkProductDescriptor ) {
				e = ((WorkProductDescriptor)item).getWorkProduct();
			} 
			MethodElement er = ConfigurationHelper.getCalculatedElement(e,  getLayoutMgr().getElementRealizer());
			if (er != null) {
				e = er;
			}
			
			if (e != null) {
				getLayoutMgr().getValidator().addReferencedElement(item, e);
				if (e instanceof Artifact) {
					for (TreeIterator<EObject> ti = e.eAllContents(); ti.hasNext();) {
						EObject obj = ti.next();
						if (obj instanceof Artifact) {
							getLayoutMgr().getValidator().addReferencedElement(
									item, (Artifact) obj);
						}
					}
				}
			} 
				
			MethodElement parent = (MethodElement) LibraryUtil.unwrap(rawitem);
			getLayoutMgr().getValidator().addReferencedElement(parent, item);

			iterateItemForLinkedElements(rawitem, adapterFactory, sup);
		}
	}	
}
