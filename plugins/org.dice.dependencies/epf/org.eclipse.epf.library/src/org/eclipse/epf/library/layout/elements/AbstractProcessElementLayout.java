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
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.MilestonePropUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TaskDescriptorPropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.IElementLayout;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.WorkProductDescriptor;

import com.ibm.icu.util.StringTokenizer;


/**
 * abstrct class for process element layout that is process specific
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public abstract class AbstractProcessElementLayout extends
		AbstractElementLayout {

	protected Process owningProcess = null;

	protected String elementProcessPath = null;
	protected String relProcessPath = null;
	protected String[] paths = null;
	protected String displayName = null;
	
	/**
	 * construct a layout for process element
	 * 
	 * @param layoutManager
	 * @param element
	 * @param owningProc
	 * @param path
	 */
	public void init(ElementLayoutManager layoutManager, MethodElement element,
			Process owningProc, String path) {

		init(layoutManager, element);

		this.owningProcess = owningProc;
		this.elementProcessPath = path;

		if (this.owningProcess == null) {
			this.owningProcess = TngUtil
					.getOwningProcess((BreakdownElement) super.element);
		}

		if (LibraryUtil.isProcess(element)) {
			owningProcess = (Process) element;
			elementProcessPath = AbstractProcessElementLayout.makePath(null,
					element);
			relProcessPath = elementProcessPath;
		}
		
		if ( elementProcessPath == null && this.element instanceof BreakdownElement)
		{
			elementProcessPath = AbstractProcessElementLayout.getPath((BreakdownElement)this.element);
		}
		
		// get the path array
		paths = getPathArray(elementProcessPath);
		
	}


	/**
	 * the process path of this item relative to the calling item
	 * @param path
	 */
	public void setRelativeProcessPath(String path)
	{
		this.relProcessPath = path;
	}
	
	/**
	 * get the relative process path
	 * @return String
	 */
	public String getRelativeProcessPath()
	{
		return this.relProcessPath;
	}
	
//	public String getUrl() {
//		String url = super.getUrl();
//
//		return url + getQueryString();
//	}

	/**
	 * get query string for the process element
	 * @return String
	 */
	public String getQueryString() {
//		if (owningProcess == element) {
//			return "";
//		}
		// return "?" + ResourceHelper.URL_PARAMETER_PROCESS + "=" +
		// owningProcess.getGuid() + "&" + ResourceHelper.URL_PARAMETER_PATH +
		// "=" + elementProcessPath;

		return ElementLayoutManager.getQueryString(owningProcess.getGuid(),
				elementProcessPath);
	}

	/**
	 * @see org.eclipse.epf.library.layout.IElementLayout#getDisplayName()
	 */
	public String getDisplayName() {
		if ( this.displayName == null ) {
			Process proc = getOwningProcess();
			
			// need to handle the supressed breakdown elements
			// use the Supress utility
			Suppression sup = getSuppression(proc);
	
			// display name needs to be fixed, extended activity may need to get display name from the base
			// DVT:  PNs of extended processes no picked up for publish (TCT 638)
			ComposedAdapterFactory adapterFactory = layoutManager.getCBSAdapterFactory();
			Object wrapper = sup.getObjectByPath(this.paths, adapterFactory);
			if ( wrapper == null ) {
				wrapper = super.element;
			}
	
			IBSItemProvider adapter = (IBSItemProvider) adapterFactory.adapt(
					wrapper, ITreeItemContentProvider.class);
	
//			this.displayName = ProcessUtil.getAttribute(element,
//					IBSItemProvider.COL_PRESENTATION_NAME, adapter);
			
			this.displayName = ConfigurationHelper.getPresentationName(element, this.getLayoutMgr().getConfiguration());
			
		}
		
		return displayName;
	}
	
	protected Suppression getSuppression(Process proc)
	{
		return layoutManager.getSuppression(proc);
	}
	
	protected XmlElement getXmlElement() {
		XmlElement elementXml = super.getXmlElement();
		elementXml.setAttribute("queryString", getQueryString()) //$NON-NLS-1$
			//.setAttribute("owningProcessGuid", owningProcess.getGuid()) // //$NON-NLS-1$
			//.setAttribute("elementProcessPath", elementProcessPath) //$NON-NLS-1$
			.setAttribute("relProcessPath", relProcessPath); //$NON-NLS-1$

		return elementXml;
	}
	
	protected Process getOwningProcess() {
		return owningProcess;
	}

	protected IElementLayout getLayout(ProcessElementItem item) {
		IElementLayout layout = layoutManager.createLayout(item.element, owningProcess, item.path);
		
		if ( layout instanceof AbstractProcessElementLayout )
		{		
			// set the relative path to this layout since this is the caller
			String relPath = AbstractProcessElementLayout.getRelativePath(item.path, this.elementProcessPath);
			((AbstractProcessElementLayout)layout).setRelativeProcessPath(relPath);
		}
		return layout;
	}

	protected String getSuperActivityPath() {
		if ( element instanceof BreakdownElement ) {
			Activity act = ((BreakdownElement)element).getSuperActivities();
			String id = act.getGuid();
			int i = elementProcessPath.indexOf(id);
			if ( i >=0 ) {
				return elementProcessPath.substring(0, i + id.length());
			}
		}
		
		return null;
	}
	
	/**
	 * static utility methods for make a path for the process elenment
	 */
	public static String makePath(String parentPath, MethodElement element) {
		if (parentPath == null || parentPath.length() == 0) {
			return element.getGuid();
		}

		return parentPath + "," + element.getGuid(); //$NON-NLS-1$
	}

	/**
	 * static method to get the path of a break down element
	 * @param element
	 * @return String
	 */
	public static String getPath(BreakdownElement element) {
		
		String path = element.getGuid();
		BreakdownElement act = element;
		Process proc = TngUtil.getOwningProcess(element);

		// Check superactivity is process and should owning process (this is useful in deepcopy
		while ( act != proc )
		{
			Activity superActs = act.getSuperActivities();
			if ( superActs != null )
			{
				act = superActs;
				path = act.getGuid() + "," + path; //$NON-NLS-1$
			}
			else
			{
				break;
			}
		}
		
		return path;		
	}
	
	/**
	 * get the path for a given breakdown element item provider
	 * @param wrapper BreakdownElementWrapperItemProvider
	 * @return String the path
	 */
	public static String getPath(BreakdownElementWrapperItemProvider wrapper)
	{
		Object e = LibraryUtil.unwrap(wrapper);
		if ( !(e instanceof MethodElement) ) {
			return null;
		}
		Object topItem = wrapper.getTopItem();
		StringBuffer path = new StringBuffer();
		path.append(((MethodElement)e).getGuid());
		
		Object parent = wrapper;
		while (parent != null && parent != topItem) {
			if ( parent instanceof BreakdownElement ) {
				parent = ((BreakdownElement)parent).getSuperActivities();
				e = parent;
			} else if ( parent instanceof BreakdownElementWrapperItemProvider ){
				parent = ((BreakdownElementWrapperItemProvider)parent).getParent(parent);
				e = LibraryUtil.unwrap(parent);
			} else {
				break;
			}
			
			if ( e instanceof MethodElement) {
				path.insert(0, ((MethodElement)e).getGuid() + ","); //$NON-NLS-1$
			}
		}
		
		return path.toString();
	}
	
	/**
	 * utility method to get the relative path to the parent
	 * @param path String
	 * @param parentPath String
	 * @return String
	 */
	public static String getRelativePath(String path, String parentPath) {
		
		if ( path == null )
		{
			return path;
		}
		
		if ( path.startsWith(parentPath) )
		{
			return path.substring(parentPath.length());
		}
		
		return path;
	}

	/**
	 * get the path arrray
	 * @param path String
	 * @return String[]
	 */
	public static String[] getPathArray(String path)
	{
		if ( path == null )
		{
			return new String[]{};			
		}
		
		List items = new ArrayList();
		StringTokenizer st = new StringTokenizer(path, ","); //$NON-NLS-1$
		while ( st.hasMoreTokens() )
		{
			String s = st.nextToken().trim();
			if ( s.length() > 0 )
			{
				items.add(s);
			}
		}
		
		String[] arr = new String[items.size()];
		items.toArray(arr);
		
		return arr;
	}
	
	/**
	 * get the owning process from path, the first part of the path is the guid of the process
	 * @param path
	 * @return String
	 */
	public static String getOwningProcessGuidFromPath(String path)
	{
		if ( path == null )
		{
			return null;
		}
		
		int index = path.indexOf(","); //$NON-NLS-1$
		String guid;
		if ( index < 0 )
		{
			guid = path;
		}
		else
		{
			guid = path.substring(0, index);
		}
		
		return guid;
	}

	/**
	 * get the parent path
	 * @param path
	 * @return String
	 */
	public static String getParentPath(String path) {
		if ( path == null ) {
			return null;
		}
		
		int index = path.lastIndexOf(","); //$NON-NLS-1$
		if ( index > 0 ) {
			return path.substring(0, index);
		}
		
		return null;
	}
	
	@Override
	protected void modifyChildDisplayName(Object feature,
			XmlElement childXmlElement, MethodElement childElement) {
		if (! (element instanceof TaskDescriptor) && !(element instanceof Milestone)) {
			return;
		}
		
		if (! (childElement instanceof WorkProductDescriptor)) {
			return;
		}
		if (! (feature instanceof EReference)) {
			return;
		}
		EReference ref = (EReference) feature;
		if (! ref.isMany()) {
			return;
		}
		WorkProductDescriptor wpd = (WorkProductDescriptor) childElement;
		String displayName = childXmlElement.getAttribute("DisplayName"); //$NON-NLS-1$)
		if (displayName == null) {
			return;
		}
		
		List<Constraint> states = null;
		if (element instanceof TaskDescriptor) {
			states = TaskDescriptorPropUtil.getTaskDescriptorPropUtil().getWpStates(
				(TaskDescriptor) element, wpd, ref);
		} else if (element instanceof Milestone) {
			states = MilestonePropUtil.getMilestonePropUtil().getWpStates(
					(Milestone) element, wpd, ref);
		}
		if (states != null && states.size() > 0) {
			String stateName = states.get(0).getBody();
			String newDisplayName = displayName + " [" + stateName + "]"; //$NON-NLS-1$ //$NON-NLS-2$
			childXmlElement.setAttribute("DisplayName", newDisplayName); //$NON-NLS-1$
		}		
	}
	
}
