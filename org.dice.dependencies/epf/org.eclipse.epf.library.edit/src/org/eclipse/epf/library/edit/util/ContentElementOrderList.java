//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.epf.library.edit.util.model.ModelFactory;
import org.eclipse.epf.library.edit.util.model.OrderInfo;
import org.eclipse.epf.library.edit.util.model.OrderInfoCollection;
import org.eclipse.epf.library.edit.util.model.util.StringResource;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * 
 * This class encapsulates all the logics used to retrieve the list of all
 * contributed/inherited sections of the given element as well as allows
 * manipulate this list.
 * 
 * 1.5: Refactored to work on any VariabilityElement
 * 
 * @author Lokanath Jagga
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ContentElementOrderList extends BasicEList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3257572797487069233L;

	public static final String ORDER_INFO_NAME = "content elements"; //$NON-NLS-1$

	private static final Map DEFAULT_SAVE_OPTIONS = new HashMap();

	public static final int CONTENT_ELEMENTS__FOR_ELEMENT_ONLY = 1;

	public static final int CONTENT_ELEMENTS__FOR_ELEMENT_AND_PARENTS = 2;

	static {
		DEFAULT_SAVE_OPTIONS.put(XMLResource.OPTION_ENCODING, "ASCII"); //$NON-NLS-1$
	}

	private VariabilityElement editElement;

	private boolean mixed = true;

	private boolean changed = false;

	private EStructuralFeature feature;

	private static boolean isContributor(VariabilityElement e) {
		return TngUtil.isContributor(e);
	}

	public ContentElementOrderList(VariabilityElement e, int scope, EStructuralFeature feature) {
		this.feature = feature;
		editElement = e;
		if (scope == CONTENT_ELEMENTS__FOR_ELEMENT_ONLY) {
			mixed = false;
		} else if (scope == CONTENT_ELEMENTS__FOR_ELEMENT_AND_PARENTS) {
			calculateParentsOnly(e);
		} else {
			mixed = false;
		}
	}

	private void calculateParentsOnly(VariabilityElement e) {
		Iterator iter = null;
		if (isContributor(e) || isExtended(e)) {
			List supers = new ArrayList();
			UmaUtil.getAllSupersBoth(supers, e,
					VariabilityType.CONTRIBUTES,
					VariabilityType.EXTENDS);
			supers.add(e);
			iter = supers.iterator();
		} else {
			mixed = false;
		}

		if (mixed) {
			// create a map of GUID / contributor 
			
			OrderInfo latestInfo = null;
			Map guidMap = new HashMap();
			Set elements = new LinkedHashSet();
			while (iter.hasNext()) {
				VariabilityElement element = (VariabilityElement) iter.next();
				List contentElements = new ArrayList();
				Object eGet = ((VariabilityElement) element).eGet(feature);
				if (eGet instanceof List) {
					contentElements.addAll((List) eGet);
				}
				for (Iterator iterator = contentElements.iterator(); iterator
						.hasNext();) {
					DescribableElement categorizedElement = (DescribableElement) iterator
							.next();
					guidMap.put(categorizedElement.getGuid(),
							categorizedElement);
					elements.add(categorizedElement);
				}

				OrderInfo orderInfo = TngUtil.getOrderInfo(element,
						ORDER_INFO_NAME);
				if (orderInfo != null) {
					if (latestInfo == null
							|| orderInfo.getTimestamp() > latestInfo
									.getTimestamp()) {
						latestInfo = orderInfo;
					}
				}
			}

			if (latestInfo != null) {
				// reorder the sections based on the latest order info
				int size = latestInfo.getGUIDs().size();
				for (int i = 0; i < size; i++) {
					Object guid = latestInfo.getGUIDs().get(i);
					Object element = guidMap.get(guid);
					if (element != null) {
						super.add(element);
						elements.remove(element);
					}
				}
			}
			super.addAll(elements);
		}
	}

	// deprecate the following constructor
	public ContentElementOrderList(VariabilityElement e) {
		editElement = e;
		Iterator iter = null;
		if (isContributor(e) || TngUtil.hasContributor(e)) {
			VariabilityElement base = TngUtil.getBase(e);
			iter = new AbstractTreeIterator(base) {

				protected Iterator getChildren(Object object) {
					List children = new ArrayList();
					for (Iterator iterator = AssociationHelper
							.getImmediateVarieties((VariabilityElement) object)
							.iterator(); iterator.hasNext();) {
						VariabilityElement element = (VariabilityElement) iterator
								.next();
						if (element.getVariabilityType() == VariabilityType.CONTRIBUTES) {
							children.add(element);
						}
					}
					return children.iterator();
				}

			};
		} else if (isExtended(e)) {
			System.out
					.println("$$$ for " + e.getName() + " = extended is true"); //$NON-NLS-1$ //$NON-NLS-2$
			List supers = new ArrayList();
			UmaUtil.getAllSupers(supers, e, VariabilityType.EXTENDS);
			supers.add(e);
			iter = supers.iterator();
		} else {
			mixed = false;
		}

		if (mixed) {
			// create a map of GUID / contributor
			OrderInfo latestInfo = null;
			Map guidMap = new HashMap();
			List elements = new LinkedList();
			while (iter.hasNext()) {
				VariabilityElement element = (VariabilityElement) iter.next();
				guidMap.put(element.getGuid(), element);
				elements.add(element);

				OrderInfo orderInfo = TngUtil.getOrderInfo(element,
						ORDER_INFO_NAME);
				if (orderInfo != null) {
					if (latestInfo == null
							|| orderInfo.getTimestamp() > latestInfo
									.getTimestamp()) {
						latestInfo = orderInfo;
					}
				}
			}

			if (latestInfo != null) {
				// reorder the sections based on the latest order info
				//
				int size = latestInfo.getGUIDs().size();
				for (int i = 0; i < size; i++) {
					Object guid = latestInfo.getGUIDs().get(i);
					Object element = guidMap.get(guid);
					if (element != null) {
						super.add(element);
						elements.remove(element);
					}
				}
			}
			super.addAll(elements);
		}
		// else {
		// // addAll(editElement.getPresentation().getSections());
		// mixed = false;
		// }
	}

	private static boolean isExtended(VariabilityElement e) {
		return e.getVariabilityBasedOnElement() != null
				&& e.getVariabilityType() == VariabilityType.EXTENDS;
	}

	/**
	 * Applies recent changes in the list
	 * 
	 */
	public void apply() {
		if (!mixed || !changed)
			return;

		// save the order info to the orderingGuide of the editElement
		//
		String str = editElement.getOrderingGuide();
		OrderInfoCollection orderInfos = null;
		StringResource res = null;
		if (str == null || str.length() == 0) {
			orderInfos = ModelFactory.eINSTANCE.createOrderInfoCollection();
		} else {
			res = new StringResource(str);
			try {
				res.load(null);
				if (res.getContents().isEmpty()) {
					orderInfos = ModelFactory.eINSTANCE
							.createOrderInfoCollection();
				} else {
					orderInfos = (OrderInfoCollection) res.getContents().get(0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// find the order infor for sections
		//
		OrderInfo sectOrderInfo = null;
		for (Iterator iter = orderInfos.getOrderInfos().iterator(); iter
				.hasNext();) {
			OrderInfo orderInfo = (OrderInfo) iter.next();
			if (ORDER_INFO_NAME.equalsIgnoreCase(orderInfo.getName())) {
				sectOrderInfo = orderInfo;
				break;
			}
		}

		if (sectOrderInfo == null) {
			sectOrderInfo = ModelFactory.eINSTANCE.createOrderInfo();
			sectOrderInfo.setName(ORDER_INFO_NAME);
			orderInfos.getOrderInfos().add(sectOrderInfo);
		} else {
			sectOrderInfo.getGUIDs().clear();
		}

		int size = size();
		for (int i = 0; i < size; i++) {
			DescribableElement sect = (DescribableElement) get(i);
			sectOrderInfo.getGUIDs().add(sect.getGuid());
		}
		sectOrderInfo.setTimestamp(System.currentTimeMillis());
		if (res == null) {
			res = new StringResource(null);
			res.getContents().add(orderInfos);
		}
		try {
			res.save(DEFAULT_SAVE_OPTIONS);
			str = res.getString();
			// System.out.println("SectionList.apply(): new ordering guide");
			// System.out.println("------ orderingGuide start ------");
			// System.out.println(str);
			// System.out.println("------ orderingGuide end ------");
			editElement.setOrderingGuide(str);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public boolean canRemove(VariabilityElement contentElement) {
		if (eGet().contains(contentElement)) {
			return true;
		}
		return false;
	}

	public Object remove(int index) {
		if (mixed) {
			if (!canRemove((VariabilityElement) get(index)))
				return null;
			Object removed = super.remove(index);
			eGet().remove(removed);
			return removed;
		} else {
			return eGet().remove(index);
		}
	}

	public boolean remove(Object o) {
		if (!canRemove((VariabilityElement) o))
			return false;
		if (mixed) {
			if (super.remove(o)) {
				eGet().remove(o);
				return true;
			}
			return false;
		}
		return eGet().remove(o);
	}

	public boolean removeAll(Collection c) {
		if (mixed) {
			boolean modified = false;
			Iterator e = iterator();
			while (e.hasNext()) {
				Object o = e.next();
				if (c.contains(o) && canRemove((VariabilityElement) o)) {
					e.remove();
					eGet().remove(o);
					modified = true;
				}
			}
			return modified;
		} else {
			return eGet().removeAll(c);
		}
	}

	public void add(int index, Object element) {
		if (mixed) {
			super.add(index, element);
			eGet().add(element);
			changed = true;
		} else {
			eGet().add(index, element);
		}
	}

	public boolean add(Object o) {
		boolean b = eGet().add(o);
		if (mixed) {
			b = super.add(o);
			if (b)
				changed = true;
		}
		return b;
	}

	public boolean addAll(Collection c) {
		boolean b = eGet().addAll(c);
		;
		if (mixed) {
			b = super.addAll(c);
			if (b)
				changed = true;
		}
		return b;
	}

	public boolean addAll(int index, Collection c) {
		if (mixed) {
			eGet().addAll(c);
			;
			boolean b = super.addAll(index, c);
			if (b)
				changed = true;
		}
		return eGet().addAll(c);
	}

	public Object set(int index, Object element) {
		if (mixed)
			throw new UnsupportedOperationException();
		return eGet().set(index, element);
	}

	public void clear() {
		if (mixed)
			throw new UnsupportedOperationException();
		eGet().clear();
	}

	public void move(int index, Object object) {
		if (mixed) {
			super.move(index, object);
			changed = true;
		} else {
			((EList) eGet()).move(index, object);
		}
	}

	public Object move(int targetIndex, int sourceIndex) {
		if (mixed) {
			Object moved = super.move(targetIndex, sourceIndex);
			changed = true;
			return moved;
		} else {
			return eGet().move(targetIndex, sourceIndex);
		}
	}

	public Object get(int index) {
		if (mixed) {
			return super.get(index);
		} else {
			return eGet().get(index);
		}
	}

	public int size() {
		if (mixed) {
			return super.size();
		} else {
			return eGet().size();
		}
	}

	public Iterator iterator() {
		if (mixed) {
			return super.iterator();
		} else {
			return eGet().iterator();
		}
	}

	public boolean contains(Object object) {
		if (mixed) {
			return super.contains(object);
		}
		return eGet().contains(object);
	}

	public boolean containsAll(Collection collection) {
		if (mixed)
			return super.containsAll(collection);
		return eGet().containsAll(collection);
	}

	public Object[] toArray() {
		if (mixed) {
			return super.toArray();
		}
		return eGet().toArray();
	}

	public Object[] toArray(Object[] array) {
		if (mixed)
			return super.toArray(array);
		return eGet().toArray(array);
	}

	public int indexOf(Object object) {
		if (mixed)
			return super.indexOf(object);
		return eGet().indexOf(object);
	}

	public int lastIndexOf(Object object) {
		if (mixed)
			return super.lastIndexOf(object);
		return eGet().lastIndexOf(object);
	}

	public boolean isMixed() {
		return mixed;
	}

	private EList eGet() {
		EList list = null;
		Object object = editElement.eGet(feature);
		if (object instanceof EList) {
			list = (EList) object;
		}
		if (list == null) {
			list = new BasicEList();
		}
		return list;
	}
}
