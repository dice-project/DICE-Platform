/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.ad.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityFinalNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeName2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityParameterNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionName2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ControlFlowNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.DecisionNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.InitialNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.MergeNodeNameEditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeName2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeName3EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.StructuredActivityNodeNameEditPart;

import org.eclipse.epf.diagram.ad.part.UMLVisualIDRegistry;

import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class UMLParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser activityPartitionActivityPartitionName_4006Parser;

	/**
	 * @generated
	 */
	private IParser getActivityPartitionActivityPartitionName_4006Parser() {
		if (activityPartitionActivityPartitionName_4006Parser == null) {
			activityPartitionActivityPartitionName_4006Parser = createActivityPartitionActivityPartitionName_4006Parser();
		}
		return activityPartitionActivityPartitionName_4006Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createActivityPartitionActivityPartitionName_4006Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser activityFinalNodeActivityFinalNodeName_4001Parser;

	/**
	 * @generated
	 */
	private IParser getActivityFinalNodeActivityFinalNodeName_4001Parser() {
		if (activityFinalNodeActivityFinalNodeName_4001Parser == null) {
			activityFinalNodeActivityFinalNodeName_4001Parser = createActivityFinalNodeActivityFinalNodeName_4001Parser();
		}
		return activityFinalNodeActivityFinalNodeName_4001Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createActivityFinalNodeActivityFinalNodeName_4001Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser mergeNodeMergeNodeName_4002Parser;

	/**
	 * @generated
	 */
	private IParser getMergeNodeMergeNodeName_4002Parser() {
		if (mergeNodeMergeNodeName_4002Parser == null) {
			mergeNodeMergeNodeName_4002Parser = createMergeNodeMergeNodeName_4002Parser();
		}
		return mergeNodeMergeNodeName_4002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createMergeNodeMergeNodeName_4002Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser initialNodeInitialNodeName_4003Parser;

	/**
	 * @generated
	 */
	private IParser getInitialNodeInitialNodeName_4003Parser() {
		if (initialNodeInitialNodeName_4003Parser == null) {
			initialNodeInitialNodeName_4003Parser = createInitialNodeInitialNodeName_4003Parser();
		}
		return initialNodeInitialNodeName_4003Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createInitialNodeInitialNodeName_4003Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser decisionNodeDecisionNodeName_4004Parser;

	/**
	 * @generated
	 */
	private IParser getDecisionNodeDecisionNodeName_4004Parser() {
		if (decisionNodeDecisionNodeName_4004Parser == null) {
			decisionNodeDecisionNodeName_4004Parser = createDecisionNodeDecisionNodeName_4004Parser();
		}
		return decisionNodeDecisionNodeName_4004Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createDecisionNodeDecisionNodeName_4004Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser structuredActivityNodeStructuredActivityNodeName_4005Parser;

	/**
	 * @generated
	 */
	private IParser getStructuredActivityNodeStructuredActivityNodeName_4005Parser() {
		if (structuredActivityNodeStructuredActivityNodeName_4005Parser == null) {
			structuredActivityNodeStructuredActivityNodeName_4005Parser = createStructuredActivityNodeStructuredActivityNodeName_4005Parser();
		}
		return structuredActivityNodeStructuredActivityNodeName_4005Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createStructuredActivityNodeStructuredActivityNodeName_4005Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser activityPartitionActivityPartitionName_4007Parser;

	/**
	 * @generated
	 */
	private IParser getActivityPartitionActivityPartitionName_4007Parser() {
		if (activityPartitionActivityPartitionName_4007Parser == null) {
			activityPartitionActivityPartitionName_4007Parser = createActivityPartitionActivityPartitionName_4007Parser();
		}
		return activityPartitionActivityPartitionName_4007Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createActivityPartitionActivityPartitionName_4007Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser activityParameterNodeActivityParameterNodeName_4008Parser;

	/**
	 * @generated
	 */
	private IParser getActivityParameterNodeActivityParameterNodeName_4008Parser() {
		if (activityParameterNodeActivityParameterNodeName_4008Parser == null) {
			activityParameterNodeActivityParameterNodeName_4008Parser = createActivityParameterNodeActivityParameterNodeName_4008Parser();
		}
		return activityParameterNodeActivityParameterNodeName_4008Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createActivityParameterNodeActivityParameterNodeName_4008Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser structuredActivityNodeStructuredActivityNodeName_4009Parser;

	/**
	 * @generated
	 */
	private IParser getStructuredActivityNodeStructuredActivityNodeName_4009Parser() {
		if (structuredActivityNodeStructuredActivityNodeName_4009Parser == null) {
			structuredActivityNodeStructuredActivityNodeName_4009Parser = createStructuredActivityNodeStructuredActivityNodeName_4009Parser();
		}
		return structuredActivityNodeStructuredActivityNodeName_4009Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createStructuredActivityNodeStructuredActivityNodeName_4009Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser structuredActivityNodeStructuredActivityNodeName_4010Parser;

	/**
	 * @generated
	 */
	private IParser getStructuredActivityNodeStructuredActivityNodeName_4010Parser() {
		if (structuredActivityNodeStructuredActivityNodeName_4010Parser == null) {
			structuredActivityNodeStructuredActivityNodeName_4010Parser = createStructuredActivityNodeStructuredActivityNodeName_4010Parser();
		}
		return structuredActivityNodeStructuredActivityNodeName_4010Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createStructuredActivityNodeStructuredActivityNodeName_4010Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser activityParameterNodeActivityParameterNodeName_4011Parser;

	/**
	 * @generated
	 */
	private IParser getActivityParameterNodeActivityParameterNodeName_4011Parser() {
		if (activityParameterNodeActivityParameterNodeName_4011Parser == null) {
			activityParameterNodeActivityParameterNodeName_4011Parser = createActivityParameterNodeActivityParameterNodeName_4011Parser();
		}
		return activityParameterNodeActivityParameterNodeName_4011Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createActivityParameterNodeActivityParameterNodeName_4011Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	private IParser controlFlowControlFlowName_4012Parser;

	/**
	 * @generated
	 */
	private IParser getControlFlowControlFlowName_4012Parser() {
		if (controlFlowControlFlowName_4012Parser == null) {
			controlFlowControlFlowName_4012Parser = createControlFlowControlFlowName_4012Parser();
		}
		return controlFlowControlFlowName_4012Parser;
	}

	/**
	 * @generated
	 */
	protected IParser createControlFlowControlFlowName_4012Parser() {
		UMLStructuralFeatureParser parser = new UMLStructuralFeatureParser(
				UMLPackage.eINSTANCE.getNamedElement_Name());
		parser.setViewPattern("{0}");
		parser.setEditPattern("{0}");
		return parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ActivityPartitionNameEditPart.VISUAL_ID:
			return getActivityPartitionActivityPartitionName_4006Parser();
		case ActivityFinalNodeNameEditPart.VISUAL_ID:
			return getActivityFinalNodeActivityFinalNodeName_4001Parser();
		case MergeNodeNameEditPart.VISUAL_ID:
			return getMergeNodeMergeNodeName_4002Parser();
		case InitialNodeNameEditPart.VISUAL_ID:
			return getInitialNodeInitialNodeName_4003Parser();
		case DecisionNodeNameEditPart.VISUAL_ID:
			return getDecisionNodeDecisionNodeName_4004Parser();
		case StructuredActivityNodeNameEditPart.VISUAL_ID:
			return getStructuredActivityNodeStructuredActivityNodeName_4005Parser();
		case ActivityPartitionName2EditPart.VISUAL_ID:
			return getActivityPartitionActivityPartitionName_4007Parser();
		case ActivityParameterNodeNameEditPart.VISUAL_ID:
			return getActivityParameterNodeActivityParameterNodeName_4008Parser();
		case StructuredActivityNodeName2EditPart.VISUAL_ID:
			return getStructuredActivityNodeStructuredActivityNodeName_4009Parser();
		case StructuredActivityNodeName3EditPart.VISUAL_ID:
			return getStructuredActivityNodeStructuredActivityNodeName_4010Parser();
		case ActivityParameterNodeName2EditPart.VISUAL_ID:
			return getActivityParameterNodeActivityParameterNodeName_4011Parser();
		case ControlFlowNameEditPart.VISUAL_ID:
			return getControlFlowControlFlowName_4012Parser();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(UMLVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(UMLVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (UMLElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}
}
