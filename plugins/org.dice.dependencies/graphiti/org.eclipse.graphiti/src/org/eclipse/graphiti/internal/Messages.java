/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.internal;

import org.eclipse.osgi.util.NLS;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.graphiti.internal.messages"; //$NON-NLS-1$
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}

	public static String AbstractAddFeature_0_xfld;
	public static String AbstractCopyFeature_0_xfld;
	public static String AbstractCreateConnectionFeature_0_xfld;
	public static String AbstractDirectEditingFeature_0_xfld;
	public static String AbstractDrillDownFeature_0_xfld;
	public static String AbstractDrillDownFeature_1_xmsg;
	public static String AbstractDrillDownFeature_2_xmsg;
	public static String AbstractLayoutFeature_0_xfld;
	public static String AbstractMoveShapeFeature_0_xfld;
	public static String AbstractPasteFeature_0_xfld;
	public static String AbstractUpdateFeature_0_xfld;
	public static String AbstractUpdateFeature_1_xfld;
	public static String AbstractUpdateFeature_2_xfld;
	public static String CommandContainer_0_xfld;
	public static String ContextEntryHelper_0_xfld;
	public static String ContextEntryHelper_1_xfld;
	public static String ContextEntryHelper_10_xfld;
	public static String ContextEntryHelper_11_xfld;
	public static String ContextEntryHelper_12_xfld;
	public static String ContextEntryHelper_13_xfld;
	public static String ContextEntryHelper_2_xfld;
	public static String ContextEntryHelper_3_xfld;
	public static String ContextEntryHelper_4_xfld;
	public static String ContextEntryHelper_5_xfld;
	public static String ContextEntryHelper_6_xfld;
	public static String ContextEntryHelper_7_xfld;
	public static String ContextEntryHelper_8_xfld;
	public static String ContextEntryHelper_9_xfld;
	public static String DataBinder_0_xmsg;
	public static String DataBinder_1_xmsg;
	public static String DataMapping_0_xmsg;
	public static String DefaultAddBendpointFeature_0_xfld;
	public static String DefaultMoveAnchorFeature_0_xfld;
	public static String DefaultMoveBendpointFeature_0_xfld;
	public static String DefaultMoveConnectionDecoratorFeature_0_xfld;
	public static String DefaultPrintFeature_0_xfld;
	public static String DefaultReconnectionFeature_0_xfld;
	public static String DefaultRemoveBendpointFeature_0_xfld;
	public static String DefaultRemoveFeature_0_xfld;
	public static String DefaultResizeShapeFeature_0_xfld;
	public static String DefaultSaveImageFeature_0_xfld;
	public static String DefaultToolBehaviorProvider_0_xfld;
	public static String DefaultToolBehaviorProvider_1_xfld;
	public static String FeatureCommand_0_xmsg;
	public static String QuickFixFeature_0_xfld;
	public static String ReconnectionFeatureForPattern_0_xfld;
	public static String TemplatePattern_0_xmsg;
	public static String TemplatePattern_1_xmsg;
}
