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

/**
 * class to hold process specific layout data for each activity
 * the data will be write out to a javascript map 
 * so that activity layout in the published site can get the status dynamically.
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class ActivityLayoutData {

	private ProcessLayoutData proc_data;
	
	public String ad_img_path = null;
	public String add_img_path = null;
	public String wpd_img_path = null;
	
	// map of supressed item's relative path to the current activity
	private List supressedItems = new ArrayList();
	
	/**
	 * constuctor
	 * @param proc_data
	 * @param activity_path
	 */
	public ActivityLayoutData(ProcessLayoutData proc_data, String activity_path)
	{
		this.proc_data = proc_data;
	}

	/**
	 * get the ProcessLayoutData
	 * @return ProcessLayoutData
	 */
	public ProcessLayoutData getProcessLayoutData()
	{
		return proc_data;
	}
	
	/**
	 * set activity diagram path
	 * @param path String
	 */
	public void setActivityDiagramPath(String path)
	{
		ad_img_path = path;
	}
	
	/**
	 * set activity detail diagram path
	 * @param path String
	 */
	public void setActivityDetailDiagramPath(String path)
	{
		add_img_path = path;
	}
	
	/**
	 * 
	 * @param path String
	 */
	public void setWPDependencyDiagramPath(String path)
	{
		wpd_img_path = path;
	}
	
	/**
	 * set the element's relative path for the suppress element
	 * @param itemRelPath
	 */
	public void setSuppressed(String itemRelPath)
	{
		//System.out.println("Suppressed: " + activity_path + ": " + itemRelPath);
		
		if ( !supressedItems.contains(itemRelPath) )
		{
			supressedItems.add(itemRelPath);
		}
	}
	
	/**
	 * get the suppressed elements
	 * @return List a list of String for the relative path of the suppressed elements
	 */
	public List getSuppressedItems()
	{
		return supressedItems;
	}
	
	/**
	 * check if there is any suppressed item local to this process
	 * @return boolean
	 */
	public boolean hasLocalSuppressed()
	{
		return (supressedItems.size() > 0);
	}
}
