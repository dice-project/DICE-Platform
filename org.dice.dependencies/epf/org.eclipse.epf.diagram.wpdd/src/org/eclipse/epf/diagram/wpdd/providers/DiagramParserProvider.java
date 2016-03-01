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
package org.eclipse.epf.diagram.wpdd.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.epf.diagram.model.ModelPackage;

import org.eclipse.epf.diagram.wpdd.edit.parts.LinkNameEditPart;
import org.eclipse.epf.diagram.wpdd.edit.parts.WorkProductNodeNameEditPart;

import org.eclipse.epf.diagram.wpdd.part.DiagramVisualIDRegistry;

/**
 * @generated
 */
public class DiagramParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser workProductNodeWorkProductNodeName_4001Parser;

	/**
	 * @generated
	 */
	private IParser getWorkProductNodeWorkProductNodeName_4001Parser() {
		if (workProductNodeWorkProductNodeName_4001Parser == null) {
			workProductNodeWorkProductNodeName_4001Parser = createWorkProductNodeWorkProductNodeName_4001Parser();
		}
		return workProductNodeWorkProductNodeName_4001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createWorkProductNodeWorkProductNodeName_4001Parser() {
		DiagramStructuralFeatureParser parser = new DiagramStructuralFeatureParser(
				ModelPackage.eINSTANCE.getNamedNode_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser linkLinkName_4002Parser;

	/**
	 * @generated
	 */
	private IParser getLinkLinkName_4002Parser() {
		if (linkLinkName_4002Parser == null) {
			linkLinkName_4002Parser = createLinkLinkName_4002Parser();
		}
		return linkLinkName_4002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createLinkLinkName_4002Parser() {
		DiagramStructuralFeatureParser parser = new DiagramStructuralFeatureParser(
				ModelPackage.eINSTANCE.getLink_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case WorkProductNodeNameEditPart.VISUAL_ID:
			return getWorkProductNodeWorkProductNodeName_4001Parser();
		case LinkNameEditPart.VISUAL_ID:
			return getLinkLinkName_4002Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(DiagramVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(DiagramVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (DiagramElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}
}
