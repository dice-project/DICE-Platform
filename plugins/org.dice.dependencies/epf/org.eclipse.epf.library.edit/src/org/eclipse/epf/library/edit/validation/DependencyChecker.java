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
package org.eclipse.epf.library.edit.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;

/**
 * This class has static methods that check for cycles in the element dependency
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class DependencyChecker {
	private static final IStatus ERROR_STATUS = new Status(IStatus.ERROR,
			LibraryEditPlugin.getDefault().getId(), 0, "", null); //$NON-NLS-1$
	
	public static boolean newCheck 		= true;			//temp flag, will be removed!
	public static boolean newCheckAct 	= true;			//temp flag, will be removed!

	/**
	 * Checks for the cycles in the dependency graph of the given feature if the
	 * given value is added/assigned to it.
	 * 
	 * @param owner
	 *            the owner of the feature
	 * @param feature
	 *            the feature to check for circular dependency
	 * @param value
	 *            the value to be added/assigned to the feature
	 * @return check status, OK if no cycle, ERROR if there will be circular
	 *         dependency
	 */
	public static IStatus checkCircularDependency(EObject owner,
			final EStructuralFeature feature, Object value) {
		if (feature == UmaPackage.Literals.ACTIVITY__BREAKDOWN_ELEMENTS) {
			if (value instanceof Activity) {
				Activity activity = (Activity) owner;
				VariabilityType type = activity.getVariabilityType();
				return checkCircularDependency(activity, (Activity) value, type);
			}
		} else if (feature == UmaPackage.Literals.VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT) {
			if (isCircularDependency((VariabilityElement) owner,
					(VariabilityElement) value)) {
				return ERROR_STATUS;
			}
		} else if (feature == UmaPackage.Literals.DELIVERABLE__DELIVERED_WORK_PRODUCTS) {
			if (!checkCircularDeliverables((Deliverable)value, (Deliverable)owner)) {
				return ERROR_STATUS;
			}
		} else {
			if (feature.isMany()) {
				Iterator iter = new AbstractTreeIterator(value) {

					/**
					 * Comment for <code>serialVersionUID</code>
					 */
					private static final long serialVersionUID = 0L;

					protected Iterator getChildren(Object object) {
						if (feature.getContainerClass().isInstance(object)) {
							EObject eObject = (EObject) object;
							List list = (List) eObject.eGet(feature);
							if (!list.isEmpty()) {
								ArrayList children = new ArrayList();
								for (Iterator iterator = list.iterator(); iterator
										.hasNext();) {
									Object element = iterator.next();
									if (feature.getContainerClass().isInstance(
											element)) {
										children.add(element);
									}
								}
								return children.iterator();
							}
						}
						return Collections.EMPTY_LIST.iterator();
					}

				};

				while (iter.hasNext()) {
					if (iter.next() == owner) {
						return ERROR_STATUS;
					}
				}

			} else {
				if (feature.getContainerClass().isInstance(value)) {
					EObject v = (EObject) value;
					do {
						if (v == owner) {
							return ERROR_STATUS;
						}
						Object o = v.eGet(feature);
						if (feature.getContainerClass().isInstance(o)) {
							v = (EObject) o;
						} else {
							v = null;
						}
					} while (v != null);
				}
			}
		}

		return Status.OK_STATUS;
	}

	/**
	 * Checks for circular dependency if the given activity is applied to the
	 * given target activity via extend or copy.
	 * 
	 * @param activity activity to apply
	 * @param target target activity to add extension or copy of the given activity
	 * @return status of this check
	 */
	public static IStatus checkCircularDependency(Activity activity, Activity target) {
		if (isParent(activity, target)) {
			Object[] args = { target.getName() };
			String message = NLS
					.bind(
							LibraryEditResources.activity_variability_error_msg1,
							args);

			return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
					.getId(), 0, message, null);
		}
		Process process = TngUtil.getOwningProcess(target);
		if(!newCheckAct && hasCyclicDependency(activity, process) ||
				newCheckAct && sourceReachableByTarget(activity, target)) {
			Object[] args = { activity.getName(), process.getName() };
			String message = NLS.bind(
							LibraryEditResources.apply_pattern_error_msg,
							args);

			return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
					.getId(), 0, message, null);
		}
		else if(ProcessUtil.hasContributorOrReplacer(activity)) {
			Object[] args = { activity.getName(), process.getName() };
			String message = NLS
					.bind(
							LibraryEditResources.apply_pattern_error_msg1,
							args);

			return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
					.getId(), 0, message, null);

		}
		return Status.OK_STATUS;
	}
	
	private static boolean hasCyclicDependency(Activity activity, Process process) {
		Iterator iter = new AbstractTreeIterator(activity) {

			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 0L;

			protected Iterator getChildren(Object object) {
				if (object instanceof Activity) {
					ArrayList children = new ArrayList();
					for (Iterator iterator = ((Activity) object)
							.getBreakdownElements().iterator(); iterator
							.hasNext();) {
						Object element = iterator.next();
						if (element instanceof VariabilityElement) {
							children.add(element);
						}
					}
					return children.iterator();
				}
				return Collections.EMPTY_LIST.iterator();
			}

		};

		while (iter.hasNext()) {
			VariabilityElement ve = (VariabilityElement) iter.next();
			VariabilityElement base = ve.getVariabilityBasedOnElement();
			VariabilityType vType = ve.getVariabilityType();
			if (base != null && (vType == VariabilityType.EXTENDS)) {
				Process proc = TngUtil
						.getOwningProcess((BreakdownElement) base);
				if (proc == process) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if the given parent is a super activity of child.
	 * 
	 * @param parent
	 * @param child
	 * @return
	 */
	public static boolean isParent(Activity parent, BreakdownElement child) {
		for (Activity act = child.getSuperActivities(); act != null; act = act
				.getSuperActivities()) {
			if (act == parent) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks for cycles in dependency path of the given activity
	 * <code>act</code> if <code>base</code> is the base activity of
	 * <code>act</code> for given variability type
	 * 
	 * @param act
	 * @param base
	 * @param type
	 * @return
	 */
	public static IStatus checkCircularDependency(Activity act, Activity base,
			VariabilityType type) {
		return checkCircularDependency(act, base, type, false);
	}
	
	public static IStatus checkCircularDependencyAfterFilterSelection(Activity act, Activity base,
			VariabilityType type) {
		return checkCircularDependency(act, base, type, true);
	}
	
	private static IStatus checkCircularDependency(Activity act, Activity base,
			VariabilityType type, boolean filtering) {
		if((base instanceof Process)
				&& (type == VariabilityType.REPLACES) && !(act instanceof Process)) {
			String message = LibraryEditResources.activity_variability_error_msg3;
			return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
					.getId(), 0, message, null);
		}
		else if (isParent(base, act)) {
			Object[] args = { act.getName() };
			String message = NLS
					.bind(
							LibraryEditResources.activity_variability_error_msg1,
							args);

			return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
					.getId(), 0, message, null);
		} 
		else if (newCheckAct && filtering) {
			IFilter filter = getCircularDependencyCheckFilter(act);
			if (! filter.accept(base)) {
				Object[] args = { act.getName(), base.getName() };
				String message = NLS
						.bind(
								LibraryEditResources.activity_variability_error_msg2,
								args);

				return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
						.getId(), 0, message, null);
			}
			return Status.OK_STATUS;
		}
		else if (isCircularDependency(act, base)) {
			Object[] args = { act.getName(), base.getName() };
			String message = NLS
					.bind(
							LibraryEditResources.activity_variability_error_msg2,
							args);

			return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
					.getId(), 0, message, null);
		}
		// block for children
		else if (ProcessUtil.hasContributorOrReplacer(base)
				&& type == VariabilityType.EXTENDS) {
			Object[] args = { act.getName(), base.getName() };
			String message = NLS
					.bind(
							LibraryEditResources.activity_variability_error_msg,
							args);

			return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
					.getId(), 0, message, null);
		}
		Process process = TngUtil.getOwningProcess(act);
		if(process != null && hasCyclicDependency(base, process)) {
			Object[] args = { act.getName(), base.getName() };
			String message = NLS
					.bind(
							LibraryEditResources.activity_variability_error_msg2,
							args);

			return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
					.getId(), 0, message, null);
		}
		return Status.OK_STATUS;
	}
	

	/**
	 * Check for circular dependency if <code>base</code> is the base element
	 * of the given <code>element</code>.
	 * 
	 * @param base
	 *            base element
	 * @return status which indicates circular depenedency is detected or not
	 */
	private static boolean isCircularDependency(VariabilityElement element,
			VariabilityElement base) {
		// standard check
		//
		VariabilityType type = base.getVariabilityType();
		while (type != VariabilityType.NA) {
			VariabilityElement ve = base.getVariabilityBasedOnElement();
			if (ve != null && ve == element)
				return true;
			base = ve;
			type = ve.getVariabilityType();

		}

		return false;
	}

	/**
	 * Check whether given deliverables leads to circular dependency
	 * 
	 * @param toBePart
	 * @param deliverable
	 * @return <code>false</code> to indicate whether circular dependency is
	 *         detected, <code>true</code> if there is no circular dependency
	 */
	public static boolean checkCircularDeliverables(Deliverable toBePart,
			Deliverable deliverable) {
		if (newCheck) {
			return ! sourceReachableByTarget(toBePart, deliverable);
		}
		
		if (toBePart == deliverable) {
			return false;
		}
		java.util.List deliverables = toBePart.getDeliveredWorkProducts();
		if (deliverables != null && deliverables.size() > 0) {
			if (deliverables.contains(deliverable))
				return false;
			for (Iterator iterator = deliverables.iterator(); iterator
					.hasNext();) {
				Object obj = iterator.next();
				if (obj instanceof Deliverable) {
					return checkCircularDeliverables((Deliverable) obj,
							deliverable);
				}
			}
		}
		return true;
	}

	/**
	 * Checks if the given CustomCategory is an ancestor of the given
	 * DescibableElement
	 * 
	 * @param cc
	 * @param e
	 * @return
	 */
	public static boolean isAncessorOf(CustomCategory cc, DescribableElement e) {
		Iterator iter = new AbstractTreeIterator(cc, false) {
			private static final long serialVersionUID = 1L;

			protected Iterator getChildren(Object object) {
				if (object instanceof CustomCategory) {
					return ((CustomCategory) object).getCategorizedElements()
							.iterator();
				}
				return Collections.EMPTY_LIST.iterator();
			}

		};
		while (iter.hasNext()) {
			if (iter.next() == e) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks circular dependency when moving a collection of artifact objects
	 * in "sourceElements" under "destination"
	 * 
	 * @param destination
	 * @param sourceElements
	 * @return true if no circular dependency detected, otherwise false
	 */
	public static boolean checkCircularForArtifacts(Artifact destination,
			Collection sourceElements) {
		HashSet variantSet = new HashSet();
		collectVariantSet(destination, variantSet, VariabilityType.REPLACES);
		collectVariantSet(destination, variantSet, VariabilityType.EXTENDS);
		if (! checkCircularForArtifacts1(destination, sourceElements, variantSet)) {
			return false;
		}
		return checkCircularForArtifacts2(destination, sourceElements);
	}
	
	private static boolean checkCircularForArtifacts1(Artifact destination,
			Collection sourceElements, HashSet variantSet) {
		if (sourceElements == null) {
			return true;
		}
		for (Iterator iter = sourceElements.iterator(); iter.hasNext();) {
			Object obj = iter.next();
			if(obj instanceof Artifact){
				Artifact artifact = (Artifact) obj;
				if (variantSet.contains(artifact)) {
					return false;
				}
				if (! checkCircularForArtifacts1(destination, artifact.getContainedArtifacts(), variantSet)) {
					return false;
				}
			}			
		}
		return true;
	}
	
	private static boolean checkCircularForArtifacts2(Artifact destination,
			Collection sourceElements) {
		for (Iterator iter = sourceElements.iterator(); iter.hasNext();) {
			Object obj = iter.next();
			if (obj instanceof Artifact && sourceIsOrAboveMe((Artifact) obj, destination)) {
				return false;
			}			
		}
		return true;
	}
	
	private static boolean sourceIsOrAboveMe(Artifact source, Artifact me) {
		if (source == me) {
			return true;
		}
		Object obj = me.getContainerArtifact();
		if (obj instanceof Artifact && sourceIsOrAboveMe(source, (Artifact) obj)) {
			return true;
		}
		obj = me.getVariabilityBasedOnElement();
		if (obj instanceof Artifact && sourceIsOrAboveMe(source, (Artifact) obj)) {
			return true;
		}	
		return false;
	}
	
	//Collect all variants of "a" and "a"s ancestror Artifact objects
	private static void collectVariantSet(Artifact a, HashSet variantSet, VariabilityType type) {
		while (a != null) {
			for (Iterator iter = TngUtil.getGeneralizers(a, type); iter.hasNext();) {
				variantSet.add(iter.next());
			}
			a = a.getContainerArtifact();
		}
	}			
	
	private static CircularDependencyCheck getCircularDependencyCheck(VariabilityElement ve, 
					boolean filter, boolean move, boolean dnd) {
		MethodLibrary lib = UmaUtil.getMethodLibrary(ve);						
		DependencyInfoMgr depInfoMgr = new DependencyInfoMgr(lib);
		depInfoMgr.setDndBit(dnd);
		return new CircularDependencyCheck(depInfoMgr, ve, filter, move || dnd);
	}
	
	private static boolean sourceReachableByTarget(VariabilityElement source, VariabilityElement target) {
		return getCircularDependencyCheck(source, false, false, false).reachableBy(target);
	}
	
	/**
	 * return a circular dependency check filter
	 */
	public static IFilter getCircularDependencyCheckFilter(VariabilityElement ve) {
		MethodLibrary lib = UmaUtil.getMethodLibrary(ve);						
		DependencyInfoMgr depInfoMgr = new DependencyInfoMgr(lib);
		return new CircularDependencyCheckFilter(depInfoMgr, ve);
	}	
	
	//Return true if no circular detected
	public static boolean checkCircularForMovingVariabilityElement(VariabilityElement destination,
			Collection sourceElements) {
		return checkCircularForMovingVariabilityElement(destination, sourceElements, false);
	}
	
	public static boolean checkCircularForMovingVariabilityElement(VariabilityElement destination,
			Collection sourceElements, boolean dnd) {
		if (! DependencyInfoMgr.VeToCheck(destination)) {
			return true;
		}
		
		CircularDependencyCheck check = getCircularDependencyCheck(destination, false, true, dnd);
		for (Iterator it = sourceElements.iterator(); it.hasNext();) {
			if (! check.accept(it.next())) {
				return false;
			}
		}
		
		return true;
	}
	
	public static IStatus checkCircularForApplyingVariabilityElement(VariabilityElement base,
			VariabilityElement ve, boolean dnd) {
		boolean ok = checkCircularForMovingVariabilityElement(base, Collections.singletonList(ve), true);
		if (!ok) {
			Object[] args = { ve.getName(), base.getName() };
			String message = NLS
					.bind(
							LibraryEditResources.variability_error_msg,
							args);	
			return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
					.getId(), 0, message, null);
		}
		return Status.OK_STATUS;
	}
	
	public static IStatus checkCircularDependencyAfterFilterSelection(VariabilityElement ve, VariabilityElement base) {
			IFilter filter = getCircularDependencyCheckFilter(ve);
			if (! filter.accept(base)) {
				Object[] args = { ve.getName(), base.getName() };
				String message = NLS
						.bind(
								LibraryEditResources.variability_error_msg,
								args);		//Not right message, need to add a new resouce string later!
	
				return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault()
						.getId(), 0, message, null);
		}
		return Status.OK_STATUS;
	}
	
}
