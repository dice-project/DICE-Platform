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
package org.eclipse.epf.diagram.model.util;

import java.util.Iterator;

import org.eclipse.epf.library.edit.util.ConstraintManager;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.SupportingMaterial;

import com.ibm.icu.util.StringTokenizer;

/**
 * This class stores diagram publish options and references to user-specified
 * diagrams to replace the activity diagram, activity detail diagram, or work
 * product dependency diagram for an activity.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class DiagramInfo {
	private static final String AD_IMAGE_URI = "ad_image_uri"; //$NON-NLS-1$

	private static final String ADD_IMAGE_URI = "add_image_uri"; //$NON-NLS-1$

	private static final String WPD_IMAGE_URI = "wpd_image_uri"; //$NON-NLS-1$

	private static final String PUBLISH_AD_IMAGE = "publish_ad_image"; //$NON-NLS-1$

	private static final String PUBLISH_ADD_IMAGE = "publish_add_image"; //$NON-NLS-1$

	private static final String PUBLISH_WPD_IMAGE = "publish_wpd_image"; //$NON-NLS-1$

	private static final String DELIMITER = "|"; //$NON-NLS-1$

	private Activity activity;

	private String AD_ImageURI;

	private String ADD_ImageURI;

	private String WPD_ImageURI;

	private boolean publishADImage;

	private boolean publishADDImage;

	private boolean publishWPDImage;

	private Constraint constraint;

	private SupportingMaterial activityDiagram;

	private SupportingMaterial activityDetailDiagram;

	private SupportingMaterial wpdDiagram;

	/**
	 * Constructs and loads a DiagramInfo instance for a given activity
	 * 
	 * @param activity
	 */
	public DiagramInfo(Activity activity) {
		this.activity = activity;
		refresh();
	}

	private DiagramInfo() {
		AD_ImageURI = ADD_ImageURI = WPD_ImageURI = ""; //$NON-NLS-1$
	}

	private DiagramInfo load() {
		DiagramInfo info = new DiagramInfo();
		for (StringTokenizer tokens = new StringTokenizer(constraint.getBody(),
				DELIMITER); tokens.hasMoreTokens();) {
			String token = tokens.nextToken();
			int id = token.indexOf('=');
			int max = token.length() - 1;
			if (id != -1 && id < max) {
				String key = token.substring(0, id);
				if (key.equalsIgnoreCase(AD_IMAGE_URI)) {
					info.AD_ImageURI = token.substring(id + 1);
				} else if (key.equalsIgnoreCase(ADD_IMAGE_URI)) {
					info.ADD_ImageURI = token.substring(id + 1);
				} else if (key.equalsIgnoreCase(WPD_IMAGE_URI)) {
					info.WPD_ImageURI = token.substring(id + 1);
				} else if (key.equalsIgnoreCase(PUBLISH_AD_IMAGE)) {
					info.publishADImage = Boolean.valueOf(
							token.substring(id + 1)).booleanValue();
				} else if (key.equalsIgnoreCase(PUBLISH_ADD_IMAGE)) {
					info.publishADDImage = Boolean.valueOf(
							token.substring(id + 1)).booleanValue();
				} else if (key.equalsIgnoreCase(PUBLISH_WPD_IMAGE)) {
					info.publishWPDImage = Boolean.valueOf(
							token.substring(id + 1)).booleanValue();
				}
			}
		}
		return info;
	}

	private void save() {
		if (constraint == null) {
			constraint = ConstraintManager.getConstraint(activity,
					ConstraintManager.ACITIVY_DIAGRAM, true);
		}
		if (activityDiagram != null) {
			AD_ImageURI = activityDiagram.getGuid();
		}
		if (activityDetailDiagram != null) {
			ADD_ImageURI = activityDetailDiagram.getGuid();
		}
		if (wpdDiagram != null) {
			WPD_ImageURI = wpdDiagram.getGuid();
		}

		DiagramInfo info = load();

		if (AD_ImageURI != null) {
			info.AD_ImageURI = AD_ImageURI;
		}
		if (ADD_ImageURI != null) {
			info.ADD_ImageURI = ADD_ImageURI;
		}
		if (WPD_ImageURI != null) {
			info.WPD_ImageURI = WPD_ImageURI;
		}
		info.publishADImage = publishADImage;
		info.publishADDImage = publishADDImage;
		info.publishWPDImage = publishWPDImage;

		String str = new StringBuffer(AD_IMAGE_URI).append('=').append(
				info.AD_ImageURI).append(DELIMITER).append(PUBLISH_AD_IMAGE)
				.append('=').append(String.valueOf(info.publishADImage))
				.append(DELIMITER).append(ADD_IMAGE_URI).append('=').append(
						String.valueOf(info.ADD_ImageURI)).append(DELIMITER)
				.append(PUBLISH_ADD_IMAGE).append('=').append(
						String.valueOf(info.publishADDImage)).append(DELIMITER)
				.append(WPD_IMAGE_URI).append('=').append(
						String.valueOf(info.WPD_ImageURI)).append(DELIMITER)
				.append(PUBLISH_WPD_IMAGE).append('=').append(
						String.valueOf(info.publishWPDImage)).toString();

		constraint.setBody(str);
	}

	/**
	 * Refreshes this DiagramInfo object from the model
	 * 
	 */
	public void refresh() {
		constraint = ConstraintManager.getConstraint(activity,
				ConstraintManager.ACITIVY_DIAGRAM, false);
		if (constraint == null) {
			return;
		}

		DiagramInfo info = load();

		AD_ImageURI = info.AD_ImageURI;
		ADD_ImageURI = info.ADD_ImageURI;
		WPD_ImageURI = info.WPD_ImageURI;
		publishADImage = info.publishADImage;
		publishADDImage = info.publishADDImage;
		publishWPDImage = info.publishWPDImage;

		activityDiagram = getSupportingMaterial(AD_ImageURI);
		activityDetailDiagram = getSupportingMaterial(ADD_ImageURI);
		wpdDiagram = getSupportingMaterial(WPD_ImageURI);
	}

	public String getAD_ImageURI() {
		return AD_ImageURI;
	}

	public String getADD_ImageURI() {
		return ADD_ImageURI;
	}

	public String getWPD_ImageURI() {
		return WPD_ImageURI;
	}

	/**
	 * Gets the option to publish user-specified activity detail diagram
	 * instead.
	 * 
	 * @return
	 * @see #getActivityDetailDiagram()
	 */
	public boolean canPublishADDImage() {
		return publishADDImage;
	}

	/**
	 * Sets the option to publish user-specified activity detail diagram
	 * instead.
	 * 
	 * @param publishADDImage
	 */
	public void setPublishADDImage(boolean publishADDImage) {
		this.publishADDImage = publishADDImage;
		save();
	}

	/**
	 * Gets the option to publish user-specified activity diagram instead.
	 * 
	 * @return
	 * @see #getActivityDiagram()
	 */
	public boolean canPublishADImage() {
		return publishADImage;
	}

	/**
	 * Sets the option to publish user-specified image for activity diagram
	 * instead of the activity diagram
	 * 
	 * @param publishADImage
	 */
	public void setPublishADImage(boolean publishADImage) {
		this.publishADImage = publishADImage;
		save();
	}

	/**
	 * Gets the option to publish user-specified work product dependency diagram
	 * instead.
	 * 
	 * @return
	 * @see #getWPDDiagram()
	 */
	public boolean canPublishWPDImage() {
		return publishWPDImage;
	}

	/**
	 * Sets the option to publish user-specified image for work product
	 * dependency diagram instead of the work product dependency diagram
	 * 
	 * @param publishWPDImage
	 */
	public void setPublishWPDImage(boolean publishWPDImage) {
		this.publishWPDImage = publishWPDImage;
		save();
	}

	public SupportingMaterial getActivityDiagram() {
		return activityDiagram;
	}

	/**
	 * Gets user-specified activity detail diagram.
	 * 
	 * @return a {@link SupportingMaterial} with the digram in its main
	 *         description
	 */
	public SupportingMaterial getActivityDetailDiagram() {
		return activityDetailDiagram;
	}

	/**
	 * Gets user-specified work product dependency diagram.
	 * 
	 * @return a {@link SupportingMaterial} with the digram in its main
	 *         description
	 */
	public SupportingMaterial getWPDDiagram() {
		return wpdDiagram;
	}

	public void setWPDDiagram(SupportingMaterial wpdDiagram) {
		if (this.wpdDiagram != wpdDiagram) {
			SupportingMaterial old = this.wpdDiagram;
			this.wpdDiagram = wpdDiagram;
			update(old, wpdDiagram);
		}
	}

	public void setActivityDetailDiagram(
			SupportingMaterial activityDetailDiagram) {
		if (this.activityDetailDiagram != activityDetailDiagram) {
			SupportingMaterial old = this.activityDetailDiagram;
			this.activityDetailDiagram = activityDetailDiagram;
			update(old, activityDetailDiagram);
		}
	}

	public void setActivityDiagram(SupportingMaterial activityDiagram) {
		if (this.activityDiagram != activityDiagram) {
			SupportingMaterial old = this.activityDiagram;
			this.activityDiagram = activityDiagram;
			update(old, activityDiagram);
		}
	}

	/**
	 * Gets the SupportingMaterial object for the diagram with the given URI
	 * 
	 * @param diagramURI
	 * @return null of the SupportingMaterial object could not be found in the
	 *         library
	 */
	private SupportingMaterial getSupportingMaterial(String diagramURI) {
		// diagramURI is the GUID of the supporting material that must be in the
		// list
		// of supporting materials of the activity
		//
		for (Iterator iter = activity.getSupportingMaterials().iterator(); iter
				.hasNext();) {
			SupportingMaterial e = (SupportingMaterial) iter.next();
			if (e.getGuid().equals(diagramURI)) {
				return e;
			}
		}
		return null;
	}

	private void update(SupportingMaterial old, SupportingMaterial neu) {
		if (old != null) {
			activity.getSupportingMaterials().remove(old);
		}
		if (neu != null) {
			activity.getSupportingMaterials().add(neu);
		}
		save();
	}

	/**
	 * Checks if the given supporting material is a user-defined diagram of this
	 * DiagramInfo's activity
	 * 
	 * @param sm
	 * @return
	 */
	public boolean isDiagram(SupportingMaterial sm) {
		return sm != null
				&& (sm == activityDiagram || sm == activityDetailDiagram || sm == wpdDiagram);
	}
}
