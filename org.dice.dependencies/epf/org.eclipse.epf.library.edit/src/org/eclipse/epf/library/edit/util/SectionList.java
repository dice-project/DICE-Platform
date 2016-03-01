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
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.epf.library.edit.util.model.ModelFactory;
import org.eclipse.epf.library.edit.util.model.OrderInfo;
import org.eclipse.epf.library.edit.util.model.OrderInfoCollection;
import org.eclipse.epf.library.edit.util.model.util.StringResource;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * 
 * This class encapsulates all the logics used to retrieve the list of all
 * contributed/inherited sections of the given element as well as allows
 * manipulate this list.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class SectionList extends BasicEList<Section> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3257572797487069233L;

	private static final String ORDER_INFO_NAME = "sections"; //$NON-NLS-1$

	private static final Map DEFAULT_SAVE_OPTIONS = new HashMap();

	public static final int STEPS_FOR_ELEMENT_ONLY = 1;

	public static final int STEPS_FOR_ELEMENT_AND_PARENTS = 2;

	static {
		DEFAULT_SAVE_OPTIONS.put(XMLResource.OPTION_ENCODING, "ASCII"); //$NON-NLS-1$
	}

	private ContentElement editElement;

	private boolean mixed = true;

	private boolean changed = false;

	private static boolean isContributor(VariabilityElement e) {
		return TngUtil.isContributor(e);
	}

	public SectionList(ContentElement e, int scope) {
		editElement = e;
		if (scope == STEPS_FOR_ELEMENT_ONLY) {
			mixed = false;
		} else if (scope == STEPS_FOR_ELEMENT_AND_PARENTS) {
			calculateParentsOnly(e);
		} else {
			mixed = false;
		}
	}

	private void calculateParentsOnly(ContentElement e) {
		Iterator iter = null;
		// if(isContributor(e)) {
		// // System.out.println("$$$ for " + e.getName() + " = contributing is
		// true");
		// List supers = new ArrayList();
		// UmaUtil.getAllSupers(supers, e, VariabilityType.CONTRIBUTES);
		// supers.add(e);
		// iter = supers.iterator();
		// } else if(isExtended(e)) {
		// // System.out.println("$$$ for " + e.getName() + " = extending is
		// true");
		// List supers = new ArrayList();
		// UmaUtil.getAllSupers(supers, e, VariabilityType.EXTENDS);
		// supers.add(e);
		// iter = supers.iterator();
		// }
		if (isContributor(e) || isExtended(e)) {
			// System.out.println("$$$ for " + e.getName() + " = contributing is
			// true");
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
			//
			OrderInfo latestInfo = null;
			Map<String, Section> guidSectionMap = new HashMap<String, Section>();
			Set sects = new LinkedHashSet();
			while (iter.hasNext()) {
				ContentElement element = (ContentElement) iter.next();
				if (ContentDescriptionFactory.hasPresentation(element)) {
					for (Iterator iterator = element.getPresentation()
							.getSections().iterator(); iterator.hasNext();) {
						Section sect = (Section) iterator.next();
						guidSectionMap.put(sect.getGuid(), sect);
						sects.add(sect);
					}
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
				//
				int size = latestInfo.getGUIDs().size();
				for (int i = 0; i < size; i++) {
					Object guid = latestInfo.getGUIDs().get(i);
					Section sect = guidSectionMap.get(guid);
					if (sect != null) {
						super.add(sect);
						sects.remove(sect);
					}
				}
			}
			super.addAll(sects);
		}
	}

	// deprecate the following constructor
	public SectionList(ContentElement e) {
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
			//
			OrderInfo latestInfo = null;
			Map<String, Section> guidSectionMap = new HashMap<String, Section>();
			List sects = new LinkedList();
			while (iter.hasNext()) {
				ContentElement element = (ContentElement) iter.next();
				if (ContentDescriptionFactory.hasPresentation(element)) {
					for (Iterator iterator = element.getPresentation()
							.getSections().iterator(); iterator.hasNext();) {
						Section sect = (Section) iterator.next();
						guidSectionMap.put(sect.getGuid(), sect);
						sects.add(sect);
					}
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
				//
				int size = latestInfo.getGUIDs().size();
				for (int i = 0; i < size; i++) {
					Object guid = latestInfo.getGUIDs().get(i);
					Section sect = guidSectionMap.get(guid);
					if (sect != null) {
						super.add(sect);
						sects.remove(sect);
					}
				}
			}
			super.addAll(sects);
		}
		// else {
		// // addAll(editElement.getPresentation().getSections());
		// mixed = false;
		// }
	}

	private static boolean isExtended(ContentElement e) {
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
			Section sect = (Section) get(i);
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

	public boolean canRemove(Section section) {
		if (ContentDescriptionFactory.hasPresentation(editElement)) {
			return editElement.getPresentation() == section.eContainer();
		}
		return false;
	}

	public Section remove(int index) {
		if (mixed) {
			if (!canRemove((Section) get(index)))
				return null;
			Section removed = super.remove(index);
			editElement.getPresentation().getSections().remove(removed);
			return removed;
		} else {
			return editElement.getPresentation().getSections().remove(index);
		}
	}

	public boolean remove(Object o) {
		if (!canRemove((Section) o))
			return false;
		if (mixed) {
			if (super.remove(o)) {
				editElement.getPresentation().getSections().remove(o);
				return true;
			}
			return false;
		}
		return editElement.getPresentation().getSections().remove(o);
	}

	public boolean removeAll(Collection c) {
		if (mixed) {
			boolean modified = false;
			Iterator e = iterator();
			while (e.hasNext()) {
				Object o = e.next();
				if (c.contains(o) && canRemove((Section) o)) {
					e.remove();
					editElement.getPresentation().getSections().remove(o);
					modified = true;
				}
			}
			return modified;
		} else {
			return editElement.getPresentation().getSections().removeAll(c);
		}
	}

	public void add(int index, Section element) {
		if (mixed) {
			super.add(index, element);
			editElement.getPresentation().getSections().add(element);
			changed = true;
		} else {
			editElement.getPresentation().getSections().add(index, element);
		}
	}

	public boolean add(Section o) {
		boolean b = editElement.getPresentation().getSections().add(o);
		if (mixed) {
			b = super.add(o);
			if (b)
				changed = true;
		}
		return b;
	}
	
	public boolean addAll(Collection<? extends Section> c) {
		boolean b = editElement.getPresentation().getSections().addAll(c);
		if (mixed) {
			b = super.addAll(c);
			if (b)
				changed = true;
		}
		return b;
	}

	public boolean addAll(int index, Collection<? extends Section> c) {
		if (mixed) {
			editElement.getPresentation().getSections().addAll(c);
			boolean b = super.addAll(index, c);
			if (b)
				changed = true;
		}
		return editElement.getPresentation().getSections().addAll(index, c);
	}

	public Section set(int index, Section element) {
		if (mixed)
			throw new UnsupportedOperationException();
		return editElement.getPresentation().getSections().set(index, element);
	}

	public void clear() {
		if (mixed)
			throw new UnsupportedOperationException();
		editElement.getPresentation().getSections().clear();
	}

	public void move(int index, Section object) {
		if (mixed) {
			super.move(index, object);
			changed = true;
		} else {
			((EList) editElement.getPresentation().getSections()).move(index,
					object);
		}
	}

	public Section move(int targetIndex, int sourceIndex) {
		if (mixed) {
			Section moved = super.move(targetIndex, sourceIndex);
			changed = true;
			return moved;
		} else {
			return ((EList<Section>) editElement.getPresentation().getSections()).move(
					targetIndex, sourceIndex);
		}
	}

	public Section get(int index) {
		if (mixed) {
			return super.get(index);
		} else {
			return editElement.getPresentation().getSections().get(index);
		}
	}

	public int size() {
		if (mixed) {
			return super.size();
		} else {
			return editElement.getPresentation().getSections().size();
		}
	}

	public Iterator iterator() {
		if (mixed) {
			return super.iterator();
		} else {
			return editElement.getPresentation().getSections().iterator();
		}
	}

	public boolean contains(Object object) {
		if (mixed) {
			return super.contains(object);
		}
		return editElement.getPresentation().getSections().contains(object);
	}

	public boolean containsAll(Collection<?> collection) {
		if (mixed)
			return super.containsAll(collection);
		return editElement.getPresentation().getSections().containsAll(
				collection);
	}

	public Object[] toArray() {
		if (mixed) {
			return super.toArray();
		}
		return editElement.getPresentation().getSections().toArray();
	}

	public Object[] toArray(Object[] array) {
		if (mixed)
			return super.toArray(array);
		return editElement.getPresentation().getSections().toArray(array);
	}

	public int indexOf(Object object) {
		if (mixed)
			return super.indexOf(object);
		return editElement.getPresentation().getSections().indexOf(object);
	}

	public int lastIndexOf(Object object) {
		if (mixed)
			return super.lastIndexOf(object);
		return editElement.getPresentation().getSections().lastIndexOf(object);
	}

	public boolean isMixed() {
		return mixed;
	}
}
