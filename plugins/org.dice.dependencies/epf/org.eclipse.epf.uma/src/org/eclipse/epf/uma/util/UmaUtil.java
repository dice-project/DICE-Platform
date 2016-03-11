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
package org.eclipse.epf.uma.util;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

/**
 * Utility class for accessing and updating the UMA model objects.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class UmaUtil {

	public static EReference createReference(String name) {
		EReference ref =  EcoreFactory.eINSTANCE.createEReference();
		ref.eSetDeliver(false);
		ref.setName(name);
		ref.setContainment(false);
		ref.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
		ref.setEType(UmaPackage.eINSTANCE.getMethodElement());
		return ref;
	}
	
	public static EAttribute createAttribute(String name) {
		EAttribute att =  EcoreFactory.eINSTANCE.createEAttribute();
		att.eSetDeliver(false);
		att.setName(name);
		att.setEType(UmaPackage.eINSTANCE.getContentDescription_MainDescription().getEType());
		return att;
	}
	
	public static EReference MethodElement_UdtList = createReference("udtList");	//$NON-NLS-1$
	public static OppositeFeature UdtListOpposite = new OppositeFeature(MethodElement.class, "udtListOpposite", MethodElement_UdtList, true);	//$NON-NLS-1$
	
	public static final String Unresolved = new String("unresolved");		//$NON-NLS-1$  Don' use Unresolved = "unresolved";
	
	private static IUmaUtilProvider provider;
	public static Set<String> unresolvedGuidSet = new HashSet<String>();
	
	private static IUmaUtilProvider getProvider() {
		return provider;
	}

	public static void setProvider(IUmaUtilProvider provider) {
		UmaUtil.provider = provider;
	}

	public static void setUnresolved(MethodElement element) {
		element.setName(Unresolved);
	}
	
	public static boolean isUnresolved(MethodElement element) {
		return element.getName() == Unresolved;		//Don't use "equals" !
	}
	
	public static List<MethodElement> filterOutUnresolved(List<MethodElement> list) {
		List<MethodElement> filteredList = new ArrayList<MethodElement>();
		if (list != null && !list.isEmpty()) {
			for (MethodElement element : list) {
				if (! isUnresolved(element) && isInLibrary(element)) {
					filteredList.add(element);
				}
			}
		}
		return filteredList;
	}
	
	/**
	 * Replaces the feature values of an old method element with the
	 * corresponding feature values of a new method element.
	 * <p>
	 * Note: All features are updated except for the GUID feature.
	 * 
	 * @param oldElement
	 *            the old method element
	 * @param newElement
	 *            the new method element
	 */
	public static void replace(MethodElement oldElement,
			MethodElement newElement) {
		List features = oldElement.eClass().getEAllStructuralFeatures();
		if (features != null) {
			int size = features.size();
			for (int i = 0; i < size; i++) {
				EStructuralFeature feature = (EStructuralFeature) features
						.get(i);
				if (feature != UmaPackage.eINSTANCE.getMethodElement_Guid()) {
					// don't replace GUID
					Object newValue = newElement.eGet(feature);
					oldElement.eSet(feature, newValue);
				}
			}
		}

	}

	/**
	 * Checks whether a model object has a direct resource.
	 * 
	 * @param e
	 *            a model object
	 * @return <code>true</code> if the specified model object is contained by
	 *         a resource
	 */
	public static boolean hasDirectResource(EObject e) {
		Resource resource = e.eResource();
		return (resource != null && resource.getContents().contains(e));
	}
	
	/**
	 * Gets all resources owned by the specified object.
	 * 
	 * @param e
	 * @return
	 */
	public static Collection<Resource> getResources(EObject e) {
		HashSet<Resource> resources = new HashSet<Resource>();
		getResources(e, resources);
		return resources;
	}
	
	public static void getResources(EObject e, Collection<Resource> resources) {
		if (UmaUtil.hasDirectResource(e)) {
			resources.add(e.eResource());
		} else {
			for (Iterator iter = e.eContents().iterator(); iter.hasNext();) {
				getResources((EObject) iter.next(), resources);
			}
		}
	}

	/**
	 * Gets a specific type of adapter associated with a notifier.
	 * 
	 * @param eObj
	 *            a notifier
	 * @param cls
	 *            the adapter class
	 * @return an <code>Adapter<code> object or <code>null</code>
	 */
	public static Object getAdapter(EObject eObj, Class cls) {
		for (Iterator adapters = eObj.eAdapters().iterator(); adapters
				.hasNext();) {
			Adapter adapter = (Adapter) adapters.next();
			if (cls.isInstance(adapter)) {
				return adapter;
			}
		}
		return null;
	}
	
	/**
	 * Removes all adapters of the specified object and all of its children.
	 * 
	 * @param eObject
	 */
	public static void removeAllAdapters(EObject eObject) {
		for (TreeIterator<EObject> iter = eObject.eAllContents(); iter.hasNext();) {
			EObject o = iter.next();
			o.eAdapters().clear();
		}
	}

	/**
	 * Gets the method package with a specific name.
	 * 
	 * @param methodPackages
	 *            a list of method packages
	 * @param name
	 *            a method package name
	 * @return a method package with the matching name or <code>null</code>
	 */
	public static MethodPackage findMethodPackage(List methodPackages,
			String name) {
		for (int i = methodPackages.size() - 1; i > -1; i--) {
			Object obj = methodPackages.get(i);
			if (obj instanceof MethodPackage) {
				MethodPackage pkg = (MethodPackage) obj;
				if (name.equals(pkg.getName())) {
					return pkg;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the method package with a specific path.
	 * 
	 * @param methodPlugin
	 *            a method plug-in
	 * @param path
	 *            an array of method element path fragments
	 * @return a method package with the matching path or <code>null</code>
	 */
	public static MethodPackage findMethodPackage(MethodPlugin methodPlugin,
			String[] path) {
		MethodPackage pkg = null;
		List list = methodPlugin.getMethodPackages();
		for (int i = 0; i < path.length; i++) {
			pkg = findMethodPackage(list, path[i]);
			if (pkg == null) {
				return null;
			}
			list = pkg.getChildPackages();
		}
		return pkg;
	}

	/**
	 * Gets the parent activity of a breakdown element.
	 * 
	 * @param e
	 *            a breakdown element
	 * @return the parent activity or <code>null</code>
	 */
	public static Activity getParentActivity(BreakdownElement e) {
		return e.getSuperActivities();
	}

	/**
	 * Gets the parent activity of a work order.
	 * 
	 * @param workOrder
	 *            a work order
	 * @return the parent activity or <code>null</code>
	 */
	public static Activity getOwningActivity(WorkOrder workOrder) {
		ProcessPackage pkg = (ProcessPackage) workOrder.eContainer();
		for (Iterator iter = pkg.getProcessElements().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (element instanceof Activity) {
				return (Activity) element;
			}
		}
		return null;
	}

	/**
	 * Gets the content package with a specific name.
	 * 
	 * @param methodPackages
	 *            a list of method packages
	 * @param name
	 *            a content package name
	 * @return a content package with the mathcing name or <code>null</code>
	 */
	public static ContentPackage findContentPackage(List methodPackages,
			String name) {
		for (int i = methodPackages.size() - 1; i > -1; i--) {
			Object obj = methodPackages.get(i);
			if (obj instanceof ContentPackage) {
				ContentPackage pkg = (ContentPackage) obj;
				if (name.equals(pkg.getName())) {
					return pkg;
				}
			}
		}
		return null;
	}

	/**
	 * Returns the content package with a specific path.
	 * 
	 * @param methodPlugin
	 *            a method plug-in
	 * @param path
	 *            an array of method element path fragments
	 * @return a content package with the matching path or <code>null</code>
	 */
	public static ContentPackage findContentPackage(MethodPlugin methodPlugin,
			String[] path) {
		ContentPackage pkg = null;
		List list = methodPlugin.getMethodPackages();
		for (int i = 0; i < path.length; i++) {
			pkg = findContentPackage(list, path[i]);
			if (pkg == null) {
				return null;
			}
			list = pkg.getChildPackages();
		}
		return pkg;
	}

	/**
	 * Gets the parent method plug-in of a method element.
	 * 
	 * @param element
	 *            a Method element
	 * @return the parent method plug-in or <code>null</code>
	 */
	public static MethodPlugin getMethodPlugin(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof MethodPlugin) {
				return (MethodPlugin) obj;
			}
		}
		return null;
	}

	/**
	 * Gets the parent method library of a method element.
	 * 
	 * @param element
	 *            a method element
	 * @return the parent method library or <code>null</code>
	 */
	public static MethodLibrary getMethodLibrary(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof MethodLibrary) {
				return (MethodLibrary) obj;
			}
		}
		return null;
	}
	
	public static EObject getTopContainer(EObject element) {
		EObject container = null;
		for (EObject obj = element.eContainer(); obj != null; obj = obj.eContainer()) {
			container = obj;
		}
		return container;		
	}

	/**
	 * Gets the parent content package of a content element.
	 * 
	 * @param element
	 *            a content element
	 * @return the parent content package or <code>null</code>
	 */
	public static ContentPackage getContentPackage(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof ContentPackage) {
				return (ContentPackage) obj;
			}
		}
		return null;
	}

	/**
	 * Gets the parent process package of a process element.
	 * 
	 * @param element
	 *            a Process element
	 * @return the parent process package or <code>null</code>
	 */
	public static ProcessPackage getProcessPackage(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof ProcessPackage) {
				return (ProcessPackage) obj;
			}
		}
		return null;
	}

	/**
	 * Gets the parent diagram of a diagram element.
	 * 
	 * @param element
	 *            a diagram element
	 * @return the parent diagram or <code>null</code>
	 */
	public static Diagram getDiagram(EObject element) {
		for (EObject obj = element; obj != null; obj = obj.eContainer()) {
			if (obj instanceof Diagram) {
				return (Diagram) obj;
			}
		}
		return null;
	}

	/**
	 * Checks whether a method element is contained by a specific content
	 * package.
	 * 
	 * @param element
	 *            a method element
	 * @param contentPackage
	 *            a content package
	 * @return <code>true</code> if the method element is contained by the
	 *         content package
	 */
	public static boolean isContainedByContentPackage(EObject element,
			ContentPackage contentPackage) {
		return isContainedBy(element, contentPackage);
	}

	/**
	 * Checks whether a model object is contained by a specific container.
	 * 
	 * @param eObj
	 *            a model object
	 * @param container
	 *            a container
	 * @return <code>true</code> if the model object is contained by the
	 *         container
	 */
	public static boolean isContainedBy(EObject eObj, Object container) {
		if (eObj == null) {
			return false;
		}
		for (EObject obj = eObj.eContainer(); obj != null; obj = obj
				.eContainer()) {
			if (obj == container) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Generates and returns a unique ID.
	 * 
	 * @return a unique ID
	 */
	public static String generateGUID() {
		return EcoreUtil.generateUUID();
	}

	/**
	 * Creates and returns the content description name of a given method
	 * element.
	 * 
	 * @param element
	 *            a method element
	 * @return a suitable content description name
	 */
	public static String createContentDescriptionName(MethodElement e) {
		return e.getName() + ',' + e.getGuid();
	}

	public static void getAllSupers(List supers, VariabilityElement e,
			VariabilityType type) {
		VariabilityElement base = e.getVariabilityBasedOnElement();
		if (base != null && e.getVariabilityType() == type
				&& !supers.contains(base)) {
			supers.add(base);
			getAllSupers(supers, base, type);
		}
	}

	public static void getAllSupersBoth(List supers, VariabilityElement e,
			VariabilityType type1, VariabilityType type2) {
		VariabilityElement base = e.getVariabilityBasedOnElement();
		if (base != null
				&& (e.getVariabilityType() == type1 || e.getVariabilityType() == type2)
				&& !supers.contains(base)) {
			supers.add(base);
			getAllSupersBoth(supers, base, type1, type2);
		}
	}

	/**
	 * Gets the class of a content element.
	 * 
	 * @param contentElement
	 *            a content element
	 * @return the content element class
	 */
	public static Class getClassOfContentElement(ContentElement contentElement) {
		if (contentElement instanceof Role)
			return Role.class;
		if (contentElement instanceof Task)
			return Task.class;
		if (contentElement instanceof WorkProduct)
			return WorkProduct.class;
		if (contentElement instanceof Guidance)
			return Guidance.class;
		if (contentElement instanceof Domain)
			return Domain.class;
		if (contentElement instanceof Discipline)
			return Discipline.class;
		if (contentElement instanceof DisciplineGrouping)
			return DisciplineGrouping.class;
		if (contentElement instanceof RoleSet)
			return RoleSet.class;
		if (contentElement instanceof RoleSetGrouping)
			return RoleSetGrouping.class;
		if (contentElement instanceof WorkProductType)
			return WorkProductType.class;
		return Object.class;
	}

	/**
	 * Creates a default work order for two work breakdown elements.
	 * 
	 * @param e
	 *            a work breakdown element
	 * @param predecessor
	 *            the predecessor work breakdown element
	 * @return the newly created work order
	 */
	public static WorkOrder createDefaultWorkOrder(WorkBreakdownElement succ,
			WorkBreakdownElement pred) {
		return createDefaultWorkOrder(succ, pred, true);
	}
	
	public static WorkOrder createDefaultWorkOrder(WorkBreakdownElement succ,
			WorkBreakdownElement pred, boolean link) {
		WorkOrder wo = UmaFactory.eINSTANCE.createWorkOrder();
		wo.setPred(pred);
		if(link) {
			succ.getLinkToPredecessor().add(wo);
		}
		return wo;
	}	

	/**
	 * Locates the work order associated with two work breakdown elements.
	 * 
	 * @param e
	 *            a work breakdown element
	 * @param predecessor
	 *            the predecessor work breakdown element
	 * @return a work order or <code>null</code>
	 */
	public static WorkOrder findWorkOrder(WorkBreakdownElement e,
			Object predecessor) {
		for (Iterator iter = e.getLinkToPredecessor().iterator(); iter
				.hasNext();) {
			WorkOrder workOrder = (WorkOrder) iter.next();
			if (workOrder.getPred() == predecessor)
				return workOrder;
		}

		return null;
	}
	
	/**
	 * Locates the work order associated with two work breakdown elements. If
	 * includeBase is true, will collect WorkOrder for base element of the
	 * predecessor as well.
	 * 
	 * @param e
	 * @param predecessor
	 * @param includeBase
	 * @return
	 */
	public static Collection<WorkOrder> findWorkOrder(WorkBreakdownElement e, WorkBreakdownElement predecessor, boolean includeBase) {
		if(!includeBase) {
			WorkOrder wo = findWorkOrder(e, predecessor);
			return (Collection<WorkOrder>) (wo == null ? Collections.emptyList() : Collections.singletonList(wo));
		}
		ArrayList<WorkOrder> workOrders = new ArrayList<WorkOrder>();
		collectWorkOrders(workOrders, e, predecessor);
		return workOrders;
	}
	
	/**
	 * Collects the WorkOrder objects associated between the specified work
	 * breakdown element <code>e</code> and the predecessor or any of its base elements.
	 * 
	 * @param workOrders
	 * @param e
	 * @param predecessor
	 */
	public static void collectWorkOrders(Collection<WorkOrder> workOrders,
			WorkBreakdownElement e, Object predecessor) {
		for (Iterator iter = e.getLinkToPredecessor().iterator(); iter
				.hasNext();) {
			WorkOrder workOrder = (WorkOrder) iter.next();
			if (workOrder.getPred() == predecessor) {
				workOrders.add(workOrder);
			}
		}
		if(predecessor instanceof VariabilityElement) {
			VariabilityElement ve = ((VariabilityElement)predecessor).getVariabilityBasedOnElement();
			if(ve != null) {
				collectWorkOrders(workOrders, e, ve);
			}
		}
	}

	/**
	 * Removes the work order associated with two work breakdown elements.
	 * 
	 * @param e
	 *            a work breakdown element
	 * @param predecessor
	 *            the predecessor work breakdown element
	 * @return the removed work order or <code>null</code>
	 */
	public static WorkOrder removeWorkOrder(WorkBreakdownElement e,
			Object predecessor) {
		for (Iterator iterator = e.getLinkToPredecessor().iterator(); iterator
				.hasNext();) {
			WorkOrder order = (WorkOrder) iterator.next();
			if (order.getPred() == predecessor) {
				iterator.remove();
				return order;
			}
		}
		return null;
	}

	/**
	 * Gets the parent process component of a method element.
	 * 
	 * @param e
	 *            a method element, typically a process element
	 * @return the parent process component or <code>null</code>
	 */
	public static ProcessComponent getProcessComponent(MethodElement e) {
		EObject container;
		for (container = e; container != null
				&& !(container instanceof ProcessComponent); container = container
				.eContainer())
			;
		if (container != null) {
			return ((ProcessComponent) container);
		}
		return null;
	}

	/**
	 * Gets the parent method unit of a method element.
	 * 
	 * @param e
	 *            a method element
	 * @return the parent method unit or <code>null</code>
	 */
	public static MethodUnit getMethodUnit(MethodElement e) {
		EObject container;
		for (container = e; container != null
				&& !(container instanceof MethodUnit); container = container
				.eContainer())
			;
		if (container != null) {
			return ((MethodUnit) container);
		}
		return null;

	}

	/**
	 * Checks whether a method plug-in has method elements that reference
	 * elements in a base plug-in.
	 * <p>
	 * Note: This is a expensive call to make for a large method plug-in.
	 * 
	 * @param plugin
	 *            a method plug-in
	 * @param base
	 *            a base method plug-in
	 * @return <code>true</code> if the specified method plug-in contain
	 *         method elements that reference elements in a base plug-in.
	 */
	public static boolean hasReference(MethodPlugin plugin, MethodPlugin base) {
		for (EContentsEList.FeatureIterator featureIterator = (EContentsEList.FeatureIterator) plugin
				.eCrossReferences().iterator(); featureIterator.hasNext();) {
			EObject ref = (EObject) featureIterator.next();
			EStructuralFeature f = featureIterator.feature();

			if (f != UmaPackage.eINSTANCE.getMethodPlugin_Bases()
					&& UmaUtil.getMethodPlugin(ref) == base) {
				return true;
			}
		}

		for (Iterator iter = plugin.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();

			// ignore ProcessElement b/c it can references anything
			//
			if (element instanceof ProcessElement) {
				continue;
			}

			for (Iterator iterator = element.eCrossReferences().iterator(); iterator
					.hasNext();) {
				EObject ref = (EObject) iterator.next();
				if (getMethodPlugin(ref) == base) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Removes all element references in a method plug-in that point to elements
	 * in a base plug-in.
	 * <p>
	 * Note: This is a expensive call to make for a large method plug-in.
	 * 
	 * @param plugin
	 *            a method plug-in
	 * @param base
	 *            the base method plug-in
	 * @return <code>true</code> if the operation is successful
	 */
	public static boolean removeReferences(MethodPlugin plugin,
			MethodPlugin base) {
		for (Iterator iter = plugin.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();

			// ignore ProcessElement b/c it can references anything
			//
			if (element instanceof ProcessElement) {
				continue;
			}

			for (EContentsEList.FeatureIterator featureIterator = (EContentsEList.FeatureIterator) element
					.eCrossReferences().iterator(); featureIterator.hasNext();) {
				EObject ref = (EObject) featureIterator.next();

				if (getMethodPlugin(ref) == base) {
					EStructuralFeature f = featureIterator.feature();
					if (f.isMany()) {
						((Collection) element.eGet(f)).remove(ref);
					} else {
						element.eSet(f, null);
					}
				}
			}
		}

		return false;
	}

	public static String getMessage(IStatus status) {
		String msg = status.getMessage();
		if (status.isMultiStatus()) {
			StringBuffer strBuf = new StringBuffer(msg);
			IStatus statuses[] = status.getChildren();
			for (int i = 0; i < statuses.length; i++) {
				strBuf.append('\n').append(statuses[i].getMessage());
			}
			msg = strBuf.toString();
		}
		if (msg != null && msg.trim().length() == 0) {
			msg = null;
		}
		return msg;
	}

	/**
	 * Generates a GUID using a base GUID.
	 * 
	 * @param baseGUID
	 *            a base GUID
	 * @return a unique ID
	 */
	public static final String generateGUID(String baseGUID) {
		return GUID.generate(baseGUID);
	}

	private static class GUID {
		private static MessageDigest md5 = null;

		private static MessageDigest getMD5() {
			if (md5 == null) {
				synchronized (GUID.class) {
					if (md5 == null) {
						try {
							md5 = MessageDigest.getInstance("MD5"); //$NON-NLS-1$
						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return md5;
		}

		public static final String generate(String baseGUID) {
			MessageDigest md5 = getMD5();
			md5.update(baseGUID.getBytes());
			byte[] hash = md5.digest();
			char[] buffer = new char[23];
			buffer[0] = '-';

			// Do a base 64 conversion by turning every 3 bytes into 4 base 64
			// characters
			//
			for (int i = 0; i < 5; ++i) {
				buffer[4 * i + 1] = BASE64_DIGITS[(hash[i * 3] >> 2) & 0x3F];
				buffer[4 * i + 2] = BASE64_DIGITS[((hash[i * 3] << 4) & 0x30)
						| ((hash[i * 3 + 1] >> 4) & 0xF)];
				buffer[4 * i + 3] = BASE64_DIGITS[((hash[i * 3 + 1] << 2) & 0x3C)
						| ((hash[i * 3 + 2] >> 6) & 0x3)];
				buffer[4 * i + 4] = BASE64_DIGITS[hash[i * 3 + 2] & 0x3F];
			}

			// Handle the last byte at the end.
			//
			buffer[21] = BASE64_DIGITS[(hash[15] >> 2) & 0x3F];
			buffer[22] = BASE64_DIGITS[(hash[15] << 4) & 0x30];

			return new String(buffer);
		}

		private static final char[] BASE64_DIGITS = { 'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c',
				'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0',
				'1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_' };
	}
	
	public static void main(String[] args) {
		System.out.println(generateGUID());
	}
	
	public static boolean isSynFree() {
		IUmaUtilProvider p = getProvider();
		return p == null ? true : p.isSynFree();
	}
	
	public static boolean isSynFreeLibrary(MethodLibrary lib) {
		IUmaUtilProvider p = getProvider();
		return p == null ? true : p.isSynFreeLibrary(lib);
	}
	
	public static boolean isSynFreePlugin(MethodPlugin plugin) {
		IUmaUtilProvider p = getProvider();
		return p == null ? true : p.isSynFreePlugin(plugin);
	}
	
	public static boolean isSynFreeProcess(Process proc) {
		IUmaUtilProvider p = getProvider();
		return p == null ? true : p.isSynFreeProcess(proc);	
	}
	
	public static void setSynFreeLibrary(MethodLibrary lib, boolean value) {
		IUmaUtilProvider p = getProvider();
		if (p == null) {
			return;
		}
		p.setSynFreeLibrary(lib, value);
	}
	
	public static void setSynFreePlugin(MethodPlugin plugin, boolean value) {
		IUmaUtilProvider p = getProvider();
		if (p == null) {
			return;
		}
		p.setSynFreePlugin(plugin, value);
	}
	
	public static void setSynFreeProcess(Process proc, boolean value) {
		IUmaUtilProvider p = getProvider();
		if (p == null) {
			return;
		}
		p.setSynFreeProcess(proc, value);
	}
	
	public static boolean isInLibrary(MethodElement element) {
		if (element == null) {
			return false;
		}
		EObject cont = element.eContainer();
		while (cont instanceof MethodElement) {
			if (cont instanceof MethodLibrary) {
				return true;
			}
			cont = cont.eContainer();
		}
		
		return false;
	}
	
	public static Set<MethodPackage> getDecendentPackages(MethodPackage pkg) {		
		Set<MethodPackage> set = new HashSet<MethodPackage>();
		for (MethodPackage childPkg : pkg.getChildPackages()) {
			set.add(childPkg);
			set.addAll(getDecendentPackages(childPkg));
		}
		return set;
	}
	
	public static Set<MethodPackage> getAllMethodPackages(MethodPlugin plugin) {
		Set<MethodPackage> set = new HashSet<MethodPackage>();
		for (MethodPackage pkg : plugin.getMethodPackages()) {
			set.add(pkg);
			set.addAll(getDecendentPackages(pkg));
		}
		return set;
	}
	
	public static Process getProcess(MethodElement e) {
		ProcessComponent procComp = UmaUtil.getProcessComponent(e);
		if (procComp != null) {
			org.eclipse.epf.uma.Process proc = procComp.getProcess();
			return proc;
		}
		return null;
	}
	
	public static Scope getScope(Process proc) {
		if (proc == null || !(proc.getDefaultContext() instanceof Scope)) {
			return null;
		}
		
		Scope scope = (Scope) proc.getDefaultContext();		
		return scope;
	}
	
	public static boolean isConfigFree(Process process) {
		if (getScope(process) != null) {
			return true;
		}
	
		if (process.getDefaultContext() == null && process
						.getValidContext().isEmpty()) {
			return true;
		}
		if (! process.getValidContext().isEmpty()) {
			return process.getValidContext().get(0) instanceof Scope;
		}
				
		return false;
	}

	public static void dumpUmaTypes() {
		Set<Class> set = new HashSet<Class>();
		List<String> types = new ArrayList<String>();
		for (Method m : UmaFactory.eINSTANCE.getClass().getDeclaredMethods()) {
			Class cls = m.getReturnType();
			if (cls != null && set.add(cls)) {
				types.add(cls.getName());
			}
		}
		Collections.sort(types);
		for (String type : types) {
			System.out.println(type);
		}
	}
	
	public static Set<Resource> getElementResources(Set<? extends MethodElement> elements) {
		if (elements == null || elements.isEmpty()) {
			return Collections.EMPTY_SET;
		}
		Set<Resource> resources = new HashSet<Resource>();
		for (MethodElement element : elements) {
			Resource res = element.eResource();
			if (res != null) {
				resources.add(res);
			}
		}		
		return resources;
	}
	
	public static List<String> getResourceFilePaths(Set<Resource> resources) {
		if (resources == null || resources.isEmpty()) {
			return Collections.EMPTY_LIST;
		}
		List<String> ret = new ArrayList();
		for (Resource res : resources) {
			ret.add(res.getURI().toFileString());
		}
		return ret;
	}	
	
}