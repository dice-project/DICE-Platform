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
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RelativeLocator;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
/**
 * Figure to support free text in the diagram, without an image attached to it.
 * FreeTextFigure wrapper over {@link FlowPage} and {@link TextFlow}. 
 * 
 * @author Shashidhar kannoori
 *
 */
public class FreeTextFigure extends Figure {

	private FlowPage textFlowPage;

	private TextFlow textFlow;

	public FreeTextFigure() {
		textFlowPage = new FlowPage();
		textFlowPage.setHorizontalAligment(PositionConstants.CENTER);
		setLayoutManager(new StackLayout());
		add(textFlowPage, new RelativeLocator(textFlowPage,
				PositionConstants.ALWAYS_LEFT));
	}
	public String getText() {
		return textFlow.getText();
	}

	public void setText(String newText) {
		textFlowPage.removeAll();
		textFlow = new TextFlow();
		textFlow.setFont(TemplateConstants.DEFAULT_FONT);
		textFlow.setLayoutManager(new ParagraphTextLayout(textFlow,
				ParagraphTextLayout.WORD_WRAP_TRUNCATE));
		textFlow.setText(newText);
		textFlowPage.add(textFlow);
	}

	public FlowPage getTextFlowPage() {
		return textFlowPage;
	}
	public void setFont(Font f) {
		// TODO Auto-generated method stub
		super.setFont(f);
		textFlow.setFont(f);
	}
	public void setForegroundColor(Color fg) {
		// TODO Auto-generated method stub
		super.setForegroundColor(fg);
		textFlow.setForegroundColor(fg);
	}

	public TextFlow getTextFlow() {
		return textFlow;
	}
}
