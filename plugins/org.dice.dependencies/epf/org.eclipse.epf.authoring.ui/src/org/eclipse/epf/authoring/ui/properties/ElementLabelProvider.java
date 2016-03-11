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
package org.eclipse.epf.authoring.ui.properties;

import java.util.Iterator;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.jface.util.Assert;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;


/**
 * Label provider for the title bar for the tabbed property sheet page.
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ElementLabelProvider extends LabelProvider {

	private ITypeMapper typeMapper;

	private ILabelProvider labelProvider = null;

	/**
	 * Create an instance
	 */
	public ElementLabelProvider() {
		super();
		typeMapper = new ElementTypeMapper();
	}

	
	/**
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object objects) {
		if (objects == null || objects.equals(StructuredSelection.EMPTY)) {
			return null;
		}
		Image image = LibraryUIImages.IMG_PROCESS;
		final boolean multiple[] = { false };
		final Object object = getObject(objects, multiple);
		if (object == null || ((IStructuredSelection) objects).size() > 1) {
			return image;
		} else {
			Object obj = LibraryUtil.unwrap(object);
			if (labelProvider == null) {
				labelProvider = new AdapterFactoryLabelProvider(TngUtil.umaItemProviderAdapterFactory);
			}
			Image titleImage = labelProvider.getImage(obj);

			return titleImage;
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.LabelProvider#dispose()
	 */
	public void dispose() {
		labelProvider.dispose();
	}


	/**
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object objects) {
		if (objects == null || objects.equals(StructuredSelection.EMPTY)) {
			return PropertiesResources.Process_NoItems; 
		}
		final boolean multiple[] = { false };
		final Object object = getObject(objects, multiple);
		if (object == null || ((IStructuredSelection) objects).size() > 1) {
			Object size = new Integer(((IStructuredSelection) objects).size());
			String msg = PropertiesResources.bind(PropertiesResources.Process_ItemsSelected, (new Object[] { size })); 
			return msg;
		} else {

			Object obj = LibraryUtil.unwrap(object);
			return getName(obj);
		}
	}

	private String getName(Object obj) {
		String name = null;

		String prefix = PropertiesUtil.getType(obj);

		if (obj instanceof BreakdownElement) {
			name = prefix + " : " + ((BreakdownElement) obj).getName(); //$NON-NLS-1$
		} else {
			name = PropertiesResources.Process_NoPropertiesAvailable; 
		}

		return name;
	}

	/**
	 * Determine if a multiple object selection has been passed to the label
	 * provider. If the objects is a IStructuredSelection, see if all the
	 * objects in the selection are the same and if so, we want to provide
	 * labels for the common selected element.
	 * 
	 * @param objects
	 *            a single object or a IStructuredSelection.
	 * @param multiple
	 *            first element in the array is true if there is multiple
	 *            unequal selected elements in a IStructuredSelection.
	 * @return the object to get labels for.
	 */
	private Object getObject(Object objects, boolean multiple[]) {
		Assert.isNotNull(objects);
		Object object = null;
		if (objects instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) objects;
			object = selection.getFirstElement();
			if (selection.size() == 1) {
				// one element selected
				multiple[0] = false;
				return object;
			}
			// multiple elements selected
			multiple[0] = true;
//			Class firstClass = typeMapper.mapType(object, object.getClass());
			Class firstClass = typeMapper.mapType(object);
			// determine if all the objects in the selection are the same type
			if (selection.size() > 1) {
				for (Iterator i = selection.iterator(); i.hasNext();) {
					Object next = i.next();
//					Class nextClass = typeMapper.mapType(next, next
//							.getClass());
					Class nextClass = typeMapper.mapType(next);
					if (!nextClass.equals(firstClass)) {
						// two elements not equal == multiple selected unequal
						multiple[0] = false;
						object = null;
						break;
					}
				}
			}
		} else {
			multiple[0] = false;
			object = objects;
		}
		return object;
	}

}