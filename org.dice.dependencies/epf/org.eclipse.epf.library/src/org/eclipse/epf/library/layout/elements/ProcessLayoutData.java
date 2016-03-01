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

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.epf.library.util.ResourceHelper;

/**
 * 
 * class to hold process specific layout data for each activity
 * the data will be write out to a javascript map 
 * so that activity layout in the published site can get the status dynamically.
 *
 * @author Jinhua Xi
 * @since 1.0
 */
public class ProcessLayoutData {

	Map activityMap = new HashMap();
	
	/**
	 * constructor
	 * @param proc_guid
	 */
	public ProcessLayoutData(String proc_guid)
	{
	}
	
	/**
	 * check if it contains the activity layout of the specified path
	 * @param activity_path String
	 * @return boolean
	 */
	public boolean hasActivityLayout(String activity_path)
	{
		return activityMap.containsKey(activity_path);
	}
	
	/**
	 * create an ActivityLayoutData for the activity path
	 * @param activity_path String
	 * @return ActivityLayoutData
	 */
	public ActivityLayoutData createActivityLauoutData(String activity_path)
	{
		ActivityLayoutData data = (ActivityLayoutData)activityMap.get(activity_path);
		if ( data == null )
		{
			data = new ActivityLayoutData(this, activity_path);
			activityMap.put(activity_path, data);
		}
		
		return data;
	}
	
	/**
	 * get a map of the activity layout data
	 * @return Map
	 */
	public Map getActivityLayoutDataMap()
	{
		return activityMap;
	}
	
	/**
	 * print the info to a javascript file in the published site. 
	 * These information will be used to determine the layout of process elements.
	 * @param ps PrintWriter
	 */
	public void print(PrintWriter ps)
	{
		for ( Iterator it = activityMap.entrySet().iterator(); it.hasNext(); )
		{
			Map.Entry entry = (Map.Entry)it.next();
			String activity_path = (String)entry.getKey();
			ActivityLayoutData data = (ActivityLayoutData)entry.getValue();
			if ( data.ad_img_path != null )
			{
				String key = activity_path + ResourceHelper.DIAGRAM_TYPE_WORKFLOW;
				printImgFile(ps, key, data.ad_img_path);
			}
			
			if ( data.add_img_path != null )
			{
				String key = activity_path + ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL;
				printImgFile(ps, key, data.add_img_path);
			}
			
			if ( data.wpd_img_path != null )
			{
				String key = activity_path + ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY;
				printImgFile(ps, key, data.wpd_img_path);
			}

			if ( data.getSuppressedItems().size() > 0 )
			{
				for ( Iterator it2 = data.getSuppressedItems().iterator(); it2.hasNext(); )
				{
					String relPath = (String)it2.next();
					String key = activity_path + relPath;
					printSuppressedItem(ps, key);
				}
			}			
		}
	}
	
	private void printImgFile(PrintWriter ps, String key, String fileName)
	{
		String line = "contentPage.processPage.imageFiles[\"" + key + "\"]=\"" + fileName + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		ps.println(line);
		//System.out.println(line);
		
	}
	
	private void printSuppressedItem(PrintWriter ps, String key)
	{
		String line = "contentPage.processPage.suppressedItems[\"" + key + "\"]=true"; //$NON-NLS-1$ //$NON-NLS-2$
		ps.println(line);	
		//System.out.println(line);
	}
	
	public void clear() {
		activityMap.clear();
	}
}
