//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.util;

import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.ImageHyperlink;

/**
 * Simple container class used as a return value for FormUI convenience methods
 * @author Jeff Hardy
 *
 */
public class RichTextImageLinkContainer {
	public IMethodRichText richText;
	public ImageHyperlink link;
	public Label label;
	
	public RichTextImageLinkContainer(IMethodRichText richText, ImageHyperlink link, Label label) {
		this.richText = richText;
		this.link = link;
		this.label = label;
	}
	
	public void setVisible(boolean visible) {
		if (richText != null) {
			richText.getControl().setVisible(visible);
		}
		if (link != null) {
			link.setVisible(visible);
		}
		if (label != null) {
			label.setVisible(visible);
		}
	}
	
	public void moveBelow(Control control) {
		if (link != null) {
			link.moveBelow(control);
		}
		if (label != null) {
			label.moveBelow(link);
		}
		if (richText != null) {
			richText.getControl().moveBelow(label);
		}
	}
	
	public void redraw() {
		if (link != null) {
			link.redraw();
		}
		if (label != null) {
			label.redraw();
		}
		if (richText != null) {
			richText.getControl().redraw();
		}
	}
}
