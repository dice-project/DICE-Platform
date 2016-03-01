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
package org.eclipse.graphiti.internal.contextbuttons;

import java.awt.Rectangle;

import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * An implementation of {@link IContextButtonPadDeclaration} which is based on
 * the UX guidelines.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class StandardContextButtonPadDeclaration extends AbstractContextButtonPadDeclaration {

	private static final IColorConstant PAD_OUTER_LINE_COLOR = new ColorConstant(173, 191, 204);

	private static final IColorConstant PAD_MIDDLE_LINE_COLOR = new ColorConstant(255, 255, 255);

	private static final IColorConstant PAD_INNER_LINE_COLOR = new ColorConstant(245, 249, 251);

	private static final IColorConstant PAD_FILL_COLOR = new ColorConstant(235, 243, 247);

	private static final IColorConstant BUTTON_OUTER_LINE_COLOR = new ColorConstant(46, 101, 140);

	private static final IColorConstant BUTTON_MIDDLE_LINE_COLOR = new ColorConstant(255, 255, 255);

	private static final IColorConstant BUTTON_FILL_COLOR = PAD_FILL_COLOR;

	public StandardContextButtonPadDeclaration(IContextButtonPadData contextButtonPadData) {
		super(contextButtonPadData);
	}

	// ======================== overwritten size getter =======================

	@Override
	protected int getButtonSize() {
		return 20;
	}

	@Override
	protected int getButtonPadding() {
		return 1;
	}

	@Override
	protected int getCollapseButtonPadding() {
		return 10;
	}

	@Override
	protected int getPadPaddingOutside() {
		return 10;
	}

	@Override
	protected int getPadPaddingInside() {
		return 4;
	}

	@Override
	protected int getPadHorizontalOverlap() {
		return 4;
	}

	@Override
	protected int getPadVerticalOverlap() {
		return 4;
	}

	@Override
	public int getPadAppendageLength() {
		return 8;
	}

	// ===================== overwritten drawing getter =======================

	public int getPadLineWidth() {
		return 1;
	}

	public int getPadCornerRadius() {
		return 12;
	}

	public IColorConstant getPadOuterLineColor() {
		return PAD_OUTER_LINE_COLOR;
	}

	public IColorConstant getPadMiddleLineColor() {
		return PAD_MIDDLE_LINE_COLOR;
	}

	public IColorConstant getPadInnerLineColor() {
		return PAD_INNER_LINE_COLOR;
	}

	public IColorConstant getPadFillColor() {
		return PAD_FILL_COLOR;
	}

	public double getPadDefaultOpacity() {
		return 0.9;
	}

	// ===================== overwritten button creators ======================

	@Override
	public PositionedContextButton createButton(IContextButtonEntry entry, Rectangle position) {
		PositionedContextButton ret = new PositionedContextButton(entry, position);
		ret.setLine(1, 4);
		ret.setColors(BUTTON_OUTER_LINE_COLOR, BUTTON_MIDDLE_LINE_COLOR, BUTTON_FILL_COLOR);
		ret.setOpacity(0.0, 0.7, 1.0);
		return ret;
	}
}
