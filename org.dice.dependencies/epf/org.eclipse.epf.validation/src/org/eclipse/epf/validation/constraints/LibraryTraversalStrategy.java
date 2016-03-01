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
package org.eclipse.epf.validation.constraints;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.EcoreUtil.ContentTreeIterator;
import org.eclipse.emf.validation.service.AbstractTraversalStrategy;
import org.eclipse.emf.validation.service.ITraversalStrategy;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DiagramElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * @author Phong Nguyen Le
 * @since  1.1
 */
public class LibraryTraversalStrategy extends AbstractTraversalStrategy {
	public static final String TRAVERSAL_TYPE = "TRAVERSAL_TYPE"; //$NON-NLS-1$
	/**
	 * Traversal strategy type constant for shadow traversal
	 */
	public static final int SHADOW = 0;
	
	/**
	 * Traversal strategy type constant for deep traversal
	 */
	public static final int DEEP = 1;
	
	public static final ITraversalStrategy deepTraversalStrategy = new LibraryTraversalStrategy(DEEP);
	
	public static class LibraryIterator extends ContentTreeIterator {
		private static final long serialVersionUID = -1175428027503440970L;
		private int traversalType = DEEP;
		
		protected LibraryIterator(Collection emfObjects) {
			super(emfObjects);
		}
		
		/**
		 * @param object
		 * @param isResolveProxies
		 */
		public LibraryIterator(Collection eObjects, boolean isResolveProxies, int traversalType, boolean disjoint) {
			super(disjoint ? makeTargetsDisjoint(eObjects) : eObjects, isResolveProxies);
			this.traversalType  = traversalType;
		}
		
		@Override
		protected Iterator<?> getEObjectChildren(EObject object) {
			if(traversalType == DEEP) {
				if(!(object instanceof MethodElement)
						|| (object instanceof Constraint || object instanceof DiagramElement)) 
				{
					return Collections.EMPTY_LIST.iterator();
				}
				
				List<MethodElement> list = new ArrayList<MethodElement>();
				for(Iterator<?> it = super.getEObjectChildren(object) ; it.hasNext();) {
					Object obj = it.next();
					if (obj instanceof MethodElement) {
						list.add((MethodElement) obj);
					}
				}
				
				return list.iterator();
			}
			
			if(object instanceof CustomCategory) {
				return getEObjectChildren((CustomCategory)object, UmaPackage.Literals.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS,
						UmaPackage.Literals.CUSTOM_CATEGORY);
			}
			else if (object instanceof ProcessComponent
					|| object instanceof MethodConfiguration
					|| object instanceof ContentElement
			) {
				return Collections.EMPTY_LIST.iterator();
			}
			else if (object instanceof MethodPackage) {
				MethodPackage pkg = (MethodPackage) object;
				if(TngUtil.isPredefined(pkg) && ModelStructure.CUSTOM_CATEGORY_PACKAGE_NAME.equals(pkg.getName())) {
					// this is the content package that contains all the custom categories
					// return root custom category as its only child
					//
					ContentPackage hiddenPkg = UmaUtil.findContentPackage(pkg
							.getChildPackages(), ModelStructure.HIDDEN_PACKAGE_NAME);
					if (hiddenPkg != null && !hiddenPkg.getContentElements().isEmpty()) {
						return hiddenPkg.getContentElements().iterator();
					}
					else {
						return Collections.EMPTY_LIST.iterator();
					}
				}
				else {
					return getEObjectChildren(pkg, UmaPackage.Literals.METHOD_PACKAGE__CHILD_PACKAGES, null);
				}
			}
			else if (object instanceof MethodLibrary) {
				return getEObjectChildren(
						(MethodLibrary) object,
						new EStructuralFeature[] {
								UmaPackage.Literals.METHOD_LIBRARY__METHOD_PLUGINS,
								UmaPackage.Literals.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS });
			}
			else if (object instanceof MethodPlugin) {
				return getEObjectChildren((EObject) object, UmaPackage.Literals.METHOD_PLUGIN__METHOD_PACKAGES, null);
			}
			else if (object == this.object) {
				return ((Collection)object).iterator();
			}
			
			return Collections.EMPTY_LIST.iterator();
		}
		
		private Iterator getEObjectChildren(EObject eObject, EStructuralFeature[] features) {
			EContentsEList list = new EContentsEList(eObject, features);
			return isResolveProxies() ? list.iterator() : list.basicIterator();
		}
		
		private Iterator getEObjectChildren(EObject eObject, EReference reference, EClass eClass) {
			Iterator iter;
			if (reference == UmaPackage.eINSTANCE.getMethodElement_MethodElementProperty()) {
				return Collections.EMPTY_LIST.iterator();
			}
			if(reference.isMany()) {
				boolean resolve = isResolveProxies();
				List list = (List) eObject.eGet(reference, resolve);
				iter = !resolve && (list instanceof InternalEList) ? ((InternalEList)list).basicIterator() : list.iterator();
			}
			else {
				Object value = eObject.eGet(reference, isResolveProxies());
				iter = value != null ? Collections.singletonList(value).iterator() : Collections.EMPTY_LIST.iterator();
			}
			if(eClass == null) {
				return iter;
			}
			else {
				ArrayList list = new ArrayList();
				while(iter.hasNext()) {
					Object o = iter.next();
					if(eClass.isInstance(o)) {
						list.add(o);
					}
				}
				if(list.isEmpty()) {
					return Collections.EMPTY_LIST.iterator();
				}
				else {
					return list.iterator();
				}
			}
		}
	}
	
	public static int countElements(Collection traversalRoots, int traversalType) {
		return countElements(traversalRoots, true, traversalType);
	}
	
	private static int countElements(Collection traversalRoots, boolean makeDisjoint, int traversalType) {
		int count = 0;
		for(Iterator iter = new LibraryIterator(traversalRoots, true, traversalType, makeDisjoint); iter.hasNext(); count++) {
			iter.next();
		}
		return count;
	}

	private Collection roots;
	private boolean contextChanged = true;

	private int type = DEEP;
	
	/**
	 * Initializes me.
	 */
	public LibraryTraversalStrategy() {
		super();
	}
	
	public LibraryTraversalStrategy(int traversalType) {
		this.type  = traversalType;
	}
	
	public void startTraversal(
			Collection traversalRoots,
			IProgressMonitor progressMonitor) {
		
		roots = makeTargetsDisjoint(traversalRoots);
		
		super.startTraversal(traversalRoots, progressMonitor);
	}
	
	private Collection getRoots() {
		return roots;
	}
	
	/* (non-Javadoc)
	 * Implements the inherited method.
	 */
	protected int countElements(Collection ignored) {
		return countElements(getRoots(), false, type);
	}
	
	/* (non-Javadoc)
	 * Implements the inherited method.
	 */
	protected Iterator createIterator(Collection ignored) {
		return new LibraryIterator(getRoots(), true, type, false) {
			private static final long serialVersionUID = -5653134989235663973L;

			public Iterator getChildren(Object obj) {
				if (obj == getRoots()) {
					return new Iterator() {
						private final Iterator delegate =
							getRoots().iterator();
						
						public boolean hasNext() {
							return delegate.hasNext();
						}

						public Object next() {
							// if I'm being asked for my next element, then
							//    we are stepping to another traversal root
							contextChanged = true;
							
							return delegate.next();
						}

						public void remove() {
							delegate.remove();
						}};
				} else {
					return super.getChildren(obj);
				}
			}
			
			public Object next() {
				// this will be set to true again the next time we test hasNext() at
				//    the traversal root level
				contextChanged = false;
				
				return super.next();
			}};
	}
	
	public boolean isClientContextChanged() {
		return contextChanged;
	}
	
	public static Set makeTargetsDisjoint(Collection objects) {
		Set result = new java.util.HashSet();
		
		// ensure that any contained (descendent) elements of other elements
		//    that we include are not included, because they will be
		//    traversed by recursion, anyway
		
		for (Iterator outer = objects.iterator(); outer.hasNext();) {
			EObject outerNext = (EObject)outer.next();
			
			for (Iterator inner = result.iterator(); inner.hasNext();) {
				EObject innerNext = (EObject)inner.next();
				
				if (EcoreUtil.isAncestor(innerNext, outerNext)) {
					outerNext = null;  // forget this one
					break;
				}
			}
			
			if (outerNext != null) {
				result.add(outerNext);
			}
		}
		
		return result;
	}	
}
