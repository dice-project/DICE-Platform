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

import org.eclipse.osgi.util.NLS;

public class Messages
        extends NLS {
    private static final String BUNDLE_NAME = "org.eclipse.epf.publishing.cmdline.Messages"; //$NON-NLS-1$

    public static String        badConfig;
    public static String        internalError;
    public static String        invalidAboutPath;
    public static String        invalidBannerPath;
    public static String        invalidLibraryPath;
    public static String        missingRequiredParameter;
    public static String        startingTask;
    public static String        unknownArg;
    public static Object        usage;
	public static String 		invalidImageHeight;

    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
}
