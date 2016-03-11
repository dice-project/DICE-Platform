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
package org.eclipse.graphiti.ui.internal.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFMultilineText extends Figure {

	private TextFlow textFlow;

	private GFFlowPage flowPage;

	public GFMultilineText() {
		setBorder(new MarginBorder(2));

		textFlow = new TextFlow();
		textFlow.setLayoutManager(new ParagraphTextLayout(textFlow, ParagraphTextLayout.WORD_WRAP_SOFT));

		flowPage = new GFFlowPage(textFlow);
		flowPage.add(textFlow);

		setLayoutManager(new StackLayout());
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
		if (newText != null && newText.indexOf('\t') != -1) {
			newText = newText.replace("\t", "        "); //$NON-NLS-1$//$NON-NLS-2$
		}

		textFlow.setText(newText);
	}

	/**
	 * Sets the horitontal aligment of the block. Valid values are:
	 * <UL>
	 * <LI>{@link PositionConstants#NONE NONE} - (default) Alignment is
	 * inherited from parent. If a parent is not found then LEFT is used.</LI>
	 * <LI>{@link PositionConstants#LEFT} - Alignment is with leading edge</LI>
	 * <LI>{@link PositionConstants#RIGHT} - Alignment is with trailing edge</LI>
	 * <LI>{@link PositionConstants#CENTER}</LI>
	 * <LI>{@link PositionConstants#ALWAYS_LEFT} - Left, irrespective of
	 * orientation</LI>
	 * <LI>{@link PositionConstants#ALWAYS_RIGHT} - Right, irrespective of
	 * orientation</LI>
	 * </UL>
	 * 
	 * @param value
	 *            the aligment
	 * @see #getHorizontalAligment()
	 */
	public void setHorizontalAligment(int value) {
		flowPage.setHorizontalAligment(value);
	}

	public int getHorizontalAligment() {
		return flowPage.getHorizontalAligment();
	}

	/**
	 * Sets the vertical aligment of the block. Valid values are:
	 * <UL>
	 * <LI>{@link PositionConstants#NONE NONE} - (default) Alignment is
	 * inherited from parent. If a parent is not found then TOP is used.</LI>
	 * <LI>{@link PositionConstants#TOP}</LI>
	 * <LI>{@link PositionConstants#BOTTOM}</LI>
	 * <LI>{@link PositionConstants#MIDDLE}</LI>
	 * </UL>
	 * 
	 * @param value
	 *            the aligment
	 * @see #getVerticalAligment()
	 */
	public void setVerticalAligment(int value) {
		flowPage.setVerticalAligment(value);
	}

	public int getVerticalAligment() {
		return flowPage.getVerticalAligment();
	}
}
