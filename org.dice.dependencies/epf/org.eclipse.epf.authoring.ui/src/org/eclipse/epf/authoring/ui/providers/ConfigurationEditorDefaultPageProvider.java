/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.authoring.ui.providers;

import java.util.Map;

import org.eclipse.epf.authoring.ui.forms.ConfigViewPage;
import org.eclipse.epf.authoring.ui.forms.ConfigurationDescription;
import org.eclipse.epf.authoring.ui.forms.ConfigurationPage;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.ui.forms.editor.FormEditor;

public class ConfigurationEditorDefaultPageProvider implements
		IMethodElementEditorPageProviderExtension {

	public Map<Object, String> getPages(Map<Object, String> pageMap,
			FormEditor editor, Object input) {
		if (input instanceof MethodConfiguration) {
			pageMap.put(new ConfigurationDescription(editor), null);
			pageMap.put(new ConfigurationPage(editor), null);
			pageMap.put(new ConfigViewPage(editor), null);
		}
		return pageMap;
	}

}
