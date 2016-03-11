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
package org.eclipse.epf.authoring.ui.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public abstract class VariabilityElementLabelProvider extends
		AdapterFactoryLabelProvider implements IFontProvider {

	protected static Font regularFont;

	protected static Font boldFont;

	protected static Font italicFont;
	
	protected static Font boldItalicFont;

	protected static Font strikeoutFont;	

	static {
		Font defaultFont = JFaceResources.getDefaultFont();
		regularFont = defaultFont;
		FontData[] fds = JFaceResources.getDefaultFont().getFontData();
		if (fds.length > 0) {
			FontData fd = fds[0];
			int h = fd.getHeight();
			Display display = Display.getDefault();
			boldFont = new Font(display, fd.getName(), h, fd.getStyle()
					| SWT.BOLD);
			italicFont = new Font(display, fd.getName(), h, fd.getStyle()
					| SWT.ITALIC);
			boldItalicFont = new Font(display, fd.getName(), h, fd.getStyle()
					| SWT.BOLD | SWT.ITALIC);
		}
	}

	public static final Font getItalicFont() {
		return italicFont;
	}

//	public static final Font getDefaultFont() {
//		return regularFont;
//	}

	/**
	 * @param adapterFactory
	 */
	public VariabilityElementLabelProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);

	}

	public void dispose() {
		super.dispose();
	}

	public Font getFont(Object element) {
		if (isExternal(element)) {
			return italicFont;
		}
		return boldFont;
	}

	public abstract boolean isExternal(Object element);

}
