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
package org.eclipse.epf.diagram.core.providers;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramModificationListener;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * 
 * @since 1.2
 */
public class AccessibleDiagramModificationListener extends
		DiagramModificationListener {

	public AccessibleDiagramModificationListener(
			AbstractDocumentProvider documentProvider, DiagramDocument document) {
		super(documentProvider, document);
	}

	public Diagram getDiagram() {
		DiagramDocument doc = getDiagramDocument();
		return doc != null ? doc.getDiagram() : null;
	}		

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getEventType() != Notification.REMOVING_ADAPTER) {
			Object feature = notification.getFeature();
			if(feature instanceof EStructuralFeature && ((EStructuralFeature)feature).isTransient()) {
				return;
			}
			Object notifier = notification.getNotifier();
			if (notifier instanceof EObject
					&& (UmaUtil.isContainedBy((EObject) notifier, getDiagram()) || UmaUtil
							.isContainedBy((EObject) notifier, getDiagram()
									.getElement()))) {
				getDiagramDocument().setContent(
						getDiagramDocument().getContent());
			}
		}
	}
}
