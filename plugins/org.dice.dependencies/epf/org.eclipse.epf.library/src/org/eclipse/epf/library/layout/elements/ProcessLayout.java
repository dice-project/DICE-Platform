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
package org.eclipse.epf.library.layout.elements;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.IElementLayout;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodElement;


/**
 * The element layout for a Role.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ProcessLayout extends AbstractElementLayout {

	public ProcessLayout() {
		super();
	}

	public void init(ElementLayoutManager layoutManager, MethodElement element) {
		super.__init(layoutManager, element);
	}

	/**
	 * @see org.eclipse.epf.library.layout.IElementLayout#getXmlElement(boolean)
	 */
	public XmlElement getXmlElement(boolean includeReferences) {
		XmlElement elementXml = super.getXmlElement();

		if (!includeReferences) {
			return elementXml;
		}

		// build the breakdown structure tree
		XmlElement bs = elementXml
				.newChild("breakdown").setAttribute("name", "Work Breakdown Structure"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		ComposedAdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory();
		iterate(super.element, bs, adapterFactory);

		bs = elementXml
				.newChild("breakdown").setAttribute("name", "Team Breakdown Structure"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		adapterFactory = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
		iterate(super.element, bs, adapterFactory);

		bs = elementXml
				.newChild("breakdown").setAttribute("name", "Work Product Breakdown Structure"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		adapterFactory = TngAdapterFactory.INSTANCE
				.getPBS_ComposedAdapterFactory();
		iterate(super.element, bs, adapterFactory);

		return elementXml;
	}

	/**
	 * iterate the break down structure and build the xml document
	 * 
	 * @param parentObj
	 * @param parentXml
	 * @param adapterFactory
	 */
	private void iterate(Object parentObj, XmlElement parentXml,
			ComposedAdapterFactory adapterFactory) {
		ITreeItemContentProvider provider = (ITreeItemContentProvider) adapterFactory
				.adapt(parentObj, ITreeItemContentProvider.class);
		// Either delegate the call or return nothing.
		if (provider != null) {
			Collection items = provider.getChildren(parentObj);
			for (Iterator it = items.iterator(); it.hasNext();) {
				MethodElement item = (MethodElement) LibraryUtil.unwrap(it
						.next());
				// super.processChild(parentXml, item, false);
				IElementLayout l = layoutManager.getLayout(item, true);
				if (l != null) {
					XmlElement child = l.getXmlElement(false);
					parentXml.addChild(child);

					// ineterate children
					if (item instanceof Activity) {
						iterate(item, child, adapterFactory);
					}

				}
			}
		}
	}

}
