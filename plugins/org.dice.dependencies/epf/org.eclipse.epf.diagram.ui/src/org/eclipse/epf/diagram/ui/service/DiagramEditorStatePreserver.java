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
//Copyright (c) 2005, 2007 IBM Corporation and others.
//All rights reserved. This program and the accompanying materials
//are made available under the terms of the Eclipse Public License v1.0
//which accompanies this distribution, and is available at
//http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.ui.service;

import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.DiagramEditorInputProxy;
import org.eclipse.epf.diagram.core.part.util.DiagramEditorUtil;
import org.eclipse.epf.diagram.core.providers.SharedResourceDiagramDocumentProvider;
import org.eclipse.epf.diagram.ui.DiagramUIPlugin;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;

/**
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class DiagramEditorStatePreserver {
	private IEditorReference diagramEditorRef;
	private boolean dirty;

	public DiagramEditorStatePreserver(Object activityOrWrapper, Suppression suppression, int diagramType) {
		IEditorInput input = new DiagramEditorInputProxy(
				new DiagramEditorInput(activityOrWrapper, suppression, diagramType), 
				DiagramEditorHelper.getDiagramPreferencesHint(diagramType));
		try {
			IEditorReference[] editorRefs = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.getEditorReferences();
			String editorId = DiagramEditorUtil.getEditorId(diagramType);
			for (int i = 0; i < editorRefs.length; i++) {
				IEditorReference editorRef = editorRefs[i];
				if(input.equals(editorRef.getEditorInput()) && editorRef.equals(editorId)) {
					IEditorPart editor = editorRef.getEditor(false);
					if(editor instanceof AbstractDiagramEditor) {
						diagramEditorRef = editorRef;		
						break;
					}
				}
			}
			if(diagramEditorRef != null) {
				dirty = diagramEditorRef.getEditor(false).isDirty();
			}
		} catch (Exception e) {
			//
		}
	}
	
	public void restore() {
		try {
			if (diagramEditorRef != null && !dirty) {
				AbstractDiagramEditor editor = (AbstractDiagramEditor) diagramEditorRef
						.getEditor(false);
				if (editor != null && editor.isDirty()) {
					IDocumentProvider docProvider = ((AbstractDiagramEditor) editor)
							.getDocumentProvider();

					// this will clear dirty flag of the editor
					//
					if (docProvider instanceof SharedResourceDiagramDocumentProvider) {
						((SharedResourceDiagramDocumentProvider) docProvider)
								.markDocumentAsSaved((IFileEditorInput) diagramEditorRef
										.getEditorInput());
					}

				}
			}
		} catch (Exception e) {
			DiagramUIPlugin.getDefault().getLogger().logError(e);
		}
	}
}
