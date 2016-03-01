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
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.swt.graphics.Image;

/**
 * @author Shashidhar Kannoori
 * @since 1.0
 * @deprecated
 */
public class WrapLabelnImage extends Figure {

	// The inner TextFlow
	private TextFlow textFlow;

	private Image image;

	public WrapLabelnImage(Image image) {
		setBorder(new MarginBorder(3));
		ImageFigure fig = new ImageFigure();
		this.image = image;
		fig.setImage(image);
		add(fig);
		FlowPage flowPage = new FlowPage();

		textFlow = new TextFlow();

		textFlow.setLayoutManager(new ParagraphTextLayout(textFlow,
				ParagraphTextLayout.WORD_WRAP_SOFT));
		
		flowPage.add(textFlow);
		flowPage.setSize(40,40);

		setLayoutManager(new ToolbarLayout());
		add(flowPage);

	}

	/**
	 * Returns the text inside the TextFlow.
	 * 
	 * @return the text flow inside the text.
	 */
	public String getText() {
		return textFlow.getText();
	}

	/**
	 * Sets the text of the TextFlow to the given value.
	 * 
	 * @param newText
	 *            the new text value.
	 */
	public void setText(String newText) {
		textFlow.setText(newText);
	}

	/**
	 * @return Returns the image.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image
	 *            The image to set.
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	public TextFlow getTextFlow(){
		return textFlow;
	}

}
