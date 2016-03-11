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
package org.eclipse.epf.authoring.ui.providers;

import java.util.ArrayList;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;

/**
 * Code taken from org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider
 * 
 */
public class MethodElementUILabelProvider implements ILabelProvider {
	
	protected ListenerList fListeners = new ListenerList();
	
	ILabelProvider fLabelProvider = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
			.getNavigatorView_ComposedAdapterFactory());
	
	private ArrayList<ILabelDecorator> fLabelDecorators;

	/**
	 * Creates a new label provider with default flags.
	 */
	public MethodElementUILabelProvider() {
		fLabelDecorators= null; 
	}
	
	/**
	 * Adds a decorator to the label provider
	 */
	public void addLabelDecorator(ILabelDecorator decorator) {
		if (fLabelDecorators == null) {
			fLabelDecorators= new ArrayList<ILabelDecorator>(2);
		}
		fLabelDecorators.add(decorator);
	}
	
	protected Image decorateImage(Image image, Object element) {
		if (fLabelDecorators != null && image != null) {
			for (int i= 0; i < fLabelDecorators.size(); i++) {
				ILabelDecorator decorator= (ILabelDecorator) fLabelDecorators.get(i);
				image= decorator.decorateImage(image, element);
			}
		}
		return image;
	}

	/* (non-Javadoc)
	 * @see ILabelProvider#getImage
	 */
	public Image getImage(Object element) {
		Image result= fLabelProvider.getImage(element);
		return decorateImage(result, element);
	}

	protected String decorateText(String text, Object element) {
		if (fLabelDecorators != null && text.length() > 0) {
			for (int i= 0; i < fLabelDecorators.size(); i++) {
				ILabelDecorator decorator= (ILabelDecorator) fLabelDecorators.get(i);
				text= decorator.decorateText(text, element);
			}
		}	
		return text;
	}


	/* (non-Javadoc)
	 * @see ILabelProvider#getText
	 */
	public String getText(Object element) {
		String result= fLabelProvider.getText(element);
		return decorateText(result, element);
	}

	/* (non-Javadoc)
	 * @see IBaseLabelProvider#dispose
	 */
	public void dispose() {
		if (fLabelDecorators != null) {
			for (int i= 0; i < fLabelDecorators.size(); i++) {
				ILabelDecorator decorator= (ILabelDecorator) fLabelDecorators.get(i);
				decorator.dispose();
			}
			fLabelDecorators= null;
		}
	}
	
	/* (non-Javadoc)
	 * @see IBaseLabelProvider#addListener(ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		if (fLabelDecorators != null) {
			for (int i= 0; i < fLabelDecorators.size(); i++) {
				ILabelDecorator decorator= (ILabelDecorator) fLabelDecorators.get(i);
				decorator.addListener(listener);
			}
		}
		fListeners.add(listener);	
	}

	/* (non-Javadoc)
	 * @see IBaseLabelProvider#isLabelProperty(Object, String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		return true;	
	}

	/* (non-Javadoc)
	 * @see IBaseLabelProvider#removeListener(ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
		if (fLabelDecorators != null) {
			for (int i= 0; i < fLabelDecorators.size(); i++) {
				ILabelDecorator decorator= (ILabelDecorator) fLabelDecorators.get(i);
				decorator.removeListener(listener);
			}
		}
		fListeners.remove(listener);	
	}
	
	public static ILabelDecorator[] getDecorators(boolean errortick, ILabelDecorator extra) {
		if (errortick) {
			if (extra == null) {
				return new ILabelDecorator[] {};
			} else {
				return new ILabelDecorator[] { extra };
			}
		}
		if (extra != null) {
			return new ILabelDecorator[] { extra };
		}
		return null;
	}
	
    /**
     * Fires a label provider changed event to all registered listeners
     * Only listeners registered at the time this method is called are notified.
     *
     * @param event a label provider changed event
     *
     * @see ILabelProviderListener#labelProviderChanged
     */
    protected void fireLabelProviderChanged(final LabelProviderChangedEvent event) {
        Object[] listeners = fListeners.getListeners();
        for (int i = 0; i < listeners.length; ++i) {
            final ILabelProviderListener l = (ILabelProviderListener) listeners[i];
            SafeRunner.run(new SafeRunnable() {
                public void run() {
                    l.labelProviderChanged(event);
                }
            });
        }
    }

}
