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
package org.eclipse.epf.authoring.ui.internal;

import org.eclipse.core.resources.IResource;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput;
import org.eclipse.epf.authoring.ui.providers.MethodElementUILabelProvider;
import org.eclipse.epf.authoring.ui.providers.ProblemsLabelDecorator;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.jface.text.Assert;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;

/**
 * Code taken from org.eclipse.jdt.internal.ui.javaeditor.JavaEditorErrorTickUpdater
 *
 */
public class MethodElementEditorErrorTickUpdater implements IProblemChangedListener {

	private MethodElementEditor fMethodElementEditor;
	private MethodElementUILabelProvider fLabelProvider;

	public MethodElementEditorErrorTickUpdater(MethodElementEditor editor) {
		Assert.isNotNull(editor);
		fMethodElementEditor= editor;
		fLabelProvider= new MethodElementUILabelProvider();
		fLabelProvider.addLabelDecorator(new ProblemsLabelDecorator(null));
		AuthoringUIPlugin.getDefault().getProblemMarkerManager().addListener(this);
	}

	/* (non-Javadoc)
	 * @see IProblemChangedListener#problemsChanged(IResource[], boolean)
	 */
	public void problemsChanged(IResource[] changedResources, boolean isMarkerChange) {
		if (!isMarkerChange)
			return;

		IEditorInput input= fMethodElementEditor.getEditorInput();
		if (input instanceof MethodElementEditorInput) { // might run async, tests needed
			MethodElement melement = ((MethodElementEditorInput)input).getMethodElement();
			if (melement instanceof ProcessComponent) {
				melement = ((ProcessComponent) melement).getProcess();
			}
			if (melement != null) {
				IResource resource= PersistenceUtil.getWorkspaceResource(melement);
				for (int i = 0; i < changedResources.length; i++) {
					if (changedResources[i].equals(resource)) {
						updateEditorImage(melement);
					}
				}
			}
		}
	}

	public void updateEditorImage(MethodElement melement) {
		Image titleImage= fMethodElementEditor.getTitleImage();
		if (titleImage == null) {
			return;
		}
		Image newImage;
		newImage= fLabelProvider.getImage(melement);
		if (titleImage != newImage) {
			postImageChange(newImage);
		}
	}

	private void postImageChange(final Image newImage) {
		Shell shell= fMethodElementEditor.getEditorSite().getShell();
		if (shell != null && !shell.isDisposed()) {
			shell.getDisplay().syncExec(new Runnable() {
				public void run() {
					fMethodElementEditor.updatedTitleImage(newImage);
				}
			});
		}
	}

	public void dispose() {
		fLabelProvider.dispose();
		AuthoringUIPlugin.getDefault().getProblemMarkerManager().removeListener(this);
	}


}


