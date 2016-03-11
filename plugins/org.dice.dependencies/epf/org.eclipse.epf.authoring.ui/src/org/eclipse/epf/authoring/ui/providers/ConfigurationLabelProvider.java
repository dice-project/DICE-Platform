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
package org.eclipse.epf.authoring.ui.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.epf.authoring.gef.figures.Colors;
import org.eclipse.epf.library.configuration.ConfigurationData;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.FeatureValueWrapperItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * The label provider for the Configuration view tree,
 * 
 * @author Jinhua Xi
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ConfigurationLabelProvider extends VariabilityElementLabelProvider implements IColorProvider {
	MethodConfiguration config = null;

	public ConfigurationLabelProvider(MethodConfiguration config,
			AdapterFactory adapterFactory) {
		super(adapterFactory);
		this.config = config;
	}

	/**
	 * This implements {@link ILabelProvider}.getImage by forwarding it to an
	 * object that implements
	 * {@link IItemLabelProvider#getImage IItemLabelProvider.getImage}
	 */
	public Image getImage(Object object) {
		// by default, return the default image
		return super.getImage(object);
	}
	
	/**
	 * This implements {@link ILabelProvider}.getText by forwarding it to an
	 * object that implements
	 * {@link IItemLabelProvider#getText IItemLabelProvider.getText}
	 */
	public String getText(Object object) {
		String text = getText_(object);
		if(object instanceof IWrapperItemProvider){
			object = TngUtil.unwrap(object);
		}
		if (object instanceof ContentElement) {
			ConfigurationData configData = ConfigurationHelper.getDelegate().getConfigurationData(config);
			if (configData.isSuppressed((ContentElement) object)) {
				return "<--" + text + "-->";	    		 //$NON-NLS-1$ //$NON-NLS-2$	
			}
		}
		return text;
	}
	
	private String getText_(Object object) {
		String name = null;
		Object element = null;
		if (object instanceof MethodElement) {
			element = ConfigurationHelper.getCalculatedElement(
					(MethodElement) object, config);
		}
		else if (object instanceof FeatureValueWrapperItemProvider) {
			element = ((FeatureValueWrapperItemProvider) object)
					.getValue();
		}
		if(object instanceof IWrapperItemProvider){
			element = TngUtil.unwrap(object);
		}
		
		if(element != null) {
			if (element instanceof BreakdownElement) {
				name = ProcessUtil
				.getPresentationName((BreakdownElement) element);
			} else {
				if (element instanceof MethodElement) {
					name = ConfigurationHelper.getPresentationName((MethodElement) element, config);
				} else {
					name = TngUtil.getPresentationName(element);
				}
			}

			// name = ConfigurationHelper.getName((MethodElement)object,
			// config);
		}
		
		IWorkbenchAdapter adapter = (IWorkbenchAdapter) (object instanceof IWorkbenchAdapter ?
				object : (object instanceof IAdaptable ? ((IAdaptable) object).getAdapter(IWorkbenchAdapter.class) : null));
		if(adapter != null) {
			return adapter.getLabel(object);
		}
		
		if (name == null) {
			name = super.getText(object);
		}

		return name;
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object object, int index) {
		return getText(object);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	public Image getColumnImage(Object object, int index) {
		// by default, return the default image
		return getImage(object);
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#dispose()
	 */
	public void dispose() {
		config = null;
		super.dispose();
	}

	/**
	 * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
	 */
	public Color getForeground(Object element) {
		if (isSuppressed(element)) {
			return Colors.SUPRESSED_ELEMENT_LABEL;
		}
		else if (isExternal(element)) {
			return Colors.INHERITED_ELEMENT_LABEL;
		}
		return null;
	}

	private boolean isSuppressed(Object element) {
		if (element instanceof BreakdownElement) {
			org.eclipse.epf.uma.Process proc = TngUtil.getOwningProcess((BreakdownElement) element);
			if(proc != null) {
				return Suppression.getSuppression(proc).isSuppressed(element);
			}
		} else if (element instanceof BreakdownElementWrapperItemProvider) {
			Object top = ((BreakdownElementWrapperItemProvider)element).getTopItem();
			if(top instanceof org.eclipse.epf.uma.Process) {
				return Suppression.getSuppression((Process) top).isSuppressed(element);
			}
		}
		return false;
	}

	/**
	 * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
	 */
	public Color getBackground(Object element) {
		// let OS take control over tree widget background
		// rather than sending one fixed background.
		return null;
	}


	/**
	 * @see org.eclipse.epf.library.edit.util.VariabilityElementLabelProvider#isExternal(java.lang.Object)
	 */
	public boolean isExternal(Object element) {
		return ProcessUtil.isInherited(element) || ProcessUtil.isContributed(element);
	}


	/**
	 * @see org.eclipse.epf.library.edit.util.VariabilityElementLabelProvider#getFont(java.lang.Object)
	 */
	public Font getFont(Object element) {
		if (ProcessUtil.isInherited(element)) {
			return italicFont;
		}
		else if(ProcessUtil.isContributed(element)) {
			return boldItalicFont;
		}
		return regularFont;
	}

}