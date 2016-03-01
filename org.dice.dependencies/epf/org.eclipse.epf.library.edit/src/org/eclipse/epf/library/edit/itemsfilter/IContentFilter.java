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
package org.eclipse.epf.library.edit.itemsfilter;

/**
 * A filter that displays only content elements in the method element selection
 * dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public interface IContentFilter extends IFilter {

	String[] getContentPackagePath();

}
