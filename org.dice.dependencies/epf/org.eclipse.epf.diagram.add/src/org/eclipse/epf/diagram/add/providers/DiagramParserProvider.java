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
package org.eclipse.epf.diagram.add.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.epf.diagram.add.edit.parts.LinkNameEditPart;
import org.eclipse.epf.diagram.add.edit.parts.RoleNodeNameEditPart;
import org.eclipse.epf.diagram.add.edit.parts.TaskNodeNameEditPart;
import org.eclipse.epf.diagram.add.edit.parts.WorkProductDescriptorNodeNameEditPart;

import org.eclipse.epf.diagram.add.part.DiagramVisualIDRegistry;

import org.eclipse.epf.diagram.model.ModelPackage;

/**
 * @generated
 */
public class DiagramParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser roleNodeRoleNodeName_4001Parser;

	/**
	 * @generated
	 */
	private IParser getRoleNodeRoleNodeName_4001Parser() {
		if (roleNodeRoleNodeName_4001Parser == null) {
			roleNodeRoleNodeName_4001Parser = createRoleNodeRoleNodeName_4001Parser();
		}
		return roleNodeRoleNodeName_4001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createRoleNodeRoleNodeName_4001Parser() {
		DiagramStructuralFeatureParser parser = new DiagramStructuralFeatureParser(
				ModelPackage.eINSTANCE.getNamedNode_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser taskNodeTaskNodeName_4002Parser;

	/**
	 * @generated
	 */
	private IParser getTaskNodeTaskNodeName_4002Parser() {
		if (taskNodeTaskNodeName_4002Parser == null) {
			taskNodeTaskNodeName_4002Parser = createTaskNodeTaskNodeName_4002Parser();
		}
		return taskNodeTaskNodeName_4002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createTaskNodeTaskNodeName_4002Parser() {
		DiagramStructuralFeatureParser parser = new DiagramStructuralFeatureParser(
				ModelPackage.eINSTANCE.getNamedNode_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser workProductDescriptorNodeWorkProductDescriptorNodeName_4003Parser;

	/**
	 * @generated
	 */
	private IParser getWorkProductDescriptorNodeWorkProductDescriptorNodeName_4003Parser() {
		if (workProductDescriptorNodeWorkProductDescriptorNodeName_4003Parser == null) {
			workProductDescriptorNodeWorkProductDescriptorNodeName_4003Parser = createWorkProductDescriptorNodeWorkProductDescriptorNodeName_4003Parser();
		}
		return workProductDescriptorNodeWorkProductDescriptorNodeName_4003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createWorkProductDescriptorNodeWorkProductDescriptorNodeName_4003Parser() {
		DiagramStructuralFeatureParser parser = new DiagramStructuralFeatureParser(
				ModelPackage.eINSTANCE.getNamedNode_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser linkLinkName_4004Parser;

	/**
	 * @generated
	 */
	private IParser getLinkLinkName_4004Parser() {
		if (linkLinkName_4004Parser == null) {
			linkLinkName_4004Parser = createLinkLinkName_4004Parser();
		}
		return linkLinkName_4004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createLinkLinkName_4004Parser() {
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
		case RoleNodeNameEditPart.VISUAL_ID:
			return getRoleNodeRoleNodeName_4001Parser();
		case TaskNodeNameEditPart.VISUAL_ID:
			return getTaskNodeTaskNodeName_4002Parser();
		case WorkProductDescriptorNodeNameEditPart.VISUAL_ID:
			return getWorkProductDescriptorNodeWorkProductDescriptorNodeName_4003Parser();
		case LinkNameEditPart.VISUAL_ID:
			return getLinkLinkName_4004Parser();
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
