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
package org.eclipse.epf.authoring.gef.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RelativeLocator;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.swt.graphics.Image;

/**
 * TextFigure is customizable figure to allow word-wraping and with image. 
 * {@link Label} do not support word-wrap.  TextFigure is wrapper over {@link TextFlow}
 * and {@link FlowPage} to support word-wrap and image. 
 * @author James Thario
 * @since  1.0
 */
public class TextFigure extends Figure {

	private FlowPage textFlowPage;

	private TextFlow textFlow;

	private ImageFigure imageFigure;

	public TextFigure() {
		textFlowPage = new FlowPage();
		textFlowPage.setHorizontalAligment(PositionConstants.CENTER);
		setLayoutManager(new ToolbarLayout());
		imageFigure = new ImageFigure();
		add(imageFigure, new RelativeLocator(imageFigure,
				PositionConstants.CENTER));
		add(textFlowPage, new RelativeLocator(textFlowPage,
				PositionConstants.ALWAYS_LEFT));
	}

	public void add(Image image) {
		imageFigure.setImage(image);
	}

	public String getText() {
		return textFlow.getText();
	}

	public void setText(String newText) {
		textFlowPage.removeAll();
		textFlow = new TextFlow();
		textFlow.setFont(TemplateConstants.DEFAULT_FONT);
		textFlow.setLayoutManager(new ParagraphTextLayout(textFlow,
				ParagraphTextLayout.WORD_WRAP_HARD));
		textFlow.setText(newText);
		textFlowPage.add(textFlow);
	}

	public FlowPage getTextFlowPage() {
		return textFlowPage;
	}

	public TextFlow getTextFlow() {
		return textFlow;
	}
}
