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
package org.eclipse.epf.library.edit.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.ICachedChildrenItemProvider;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.IWrapperItemProviderFactory;
import org.eclipse.epf.library.edit.IWrapperItemProviderFactoryProvider;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.VariabilityInfo;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.command.ActivityAddCommand;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.GraphicalData;
import org.eclipse.epf.library.edit.util.PredecessorList;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.util.WbePropUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.provider.ActivityItemProvider;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * Abstract base class for activity's item providers used by the Process Editor.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public abstract class BSActivityItemProvider extends ActivityItemProvider
		implements IProcessItemProvider, IBSItemProvider,
		ITableItemLabelProvider, IConfigurable, ICachedChildrenItemProvider {
	private static final Set<VariabilityType> localVariabilityTypes = new HashSet<VariabilityType>(Arrays.asList(new VariabilityType[] {
			VariabilityType.LOCAL_CONTRIBUTION,
			VariabilityType.LOCAL_REPLACEMENT
	}));

	private Object parent;

	private int id;

	protected Object topItem = null;

	private boolean rolledUp;

	private GraphicalData graphicalData;

	private PredecessorList predecessors;

	private Boolean expanded;

	protected Adapter baseListener;

	private IFilter childFilter = new IFilter() {

		public boolean accept(Object obj) {
			return acceptAsChild(obj);
		}

	};

	private boolean refreshAllIDsRequired;

	private IFilter filter;

	protected List cachedChildren;

	protected List cachedRollupChildren;

	private IConfigurator configurator;

	private Disposable contributedWrappers;

	private VariabilityInfo variabilityInfo;
	
	private boolean enableVariabilityInfo = true;

	private Disposable rolledUpWrappers;

	/**
	 * @param adapterFactory
	 */
	public BSActivityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		if(baseListener != null) {
			EObject currentBase = (EObject) baseListener.getTarget();
			if(currentBase != null) {
				currentBase.eAdapters().remove(baseListener);
			}
			if (getTarget() instanceof Activity) {
				Activity act = (Activity) getTarget();
				VariabilityElement base = act.getVariabilityBasedOnElement();
				if (base != null) {
					base.eAdapters().remove(baseListener);
				}
			}
		}

		if (predecessors != null) {
			predecessors.dispose();
		}

		if(cachedChildren != null) {
			cachedChildren.clear();
			cachedChildren = null;
		}
		
		if(cachedRollupChildren != null) {
			cachedRollupChildren.clear();
			cachedRollupChildren = null;
		}
		
		if(contributedWrappers != null) {
			contributedWrappers.dispose();
			contributedWrappers = null;
		}
		
		if(rolledUpWrappers != null) {
			rolledUpWrappers.dispose();
			rolledUpWrappers = null;
		}
		
		super.dispose();
	}

	/**
	 * Checks if the given object can be accepted as a child.
	 */
	protected boolean acceptAsChild(Object child) {
		if (filter != null) {
			return filter.accept(child);
		}
		return true;
	}
	
	protected boolean acceptAsChild(Object parent, Object child) {
		return acceptAsChild(child);
	}

	/**
	 * Checks if the given object can be accepted as a rolled-up child.
	 */
	protected boolean acceptAsRolledUpChild(Object child) {
		child = TngUtil.unwrap(child);
		if (filter != null && !(child instanceof Activity)
				&& !(child instanceof TeamProfile)) {
			return filter.accept(child);
		}
		return true;
	}

	/**
	 * Gets the object associated with the given descriptor (e.g: a
	 * TaskDescriptor is associated with a Task).
	 */
	protected abstract Object getObject(Descriptor descriptor);

	public Collection getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
		// disallow creating new child if this item provider is rolled up
		//
		if(isRolledUp()) {
			return Collections.EMPTY_LIST;
		}
		
		return super.getNewChildDescriptors(object, editingDomain, sibling);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		// if(isTopActivity(object)) {
		// newChildDescriptors.add
		// (createChildParameter
		// (UmaPackage.eINSTANCE.getActivity_BreakdownElements(),
		// UmaFactory.eINSTANCE.createPhase()));
		// newChildDescriptors.add
		// (createChildParameter
		// (UmaPackage.eINSTANCE.getActivity_BreakdownElements(),
		// UmaFactory.eINSTANCE.createIteration()));
		// }
		// else if(object instanceof Phase) {
		// newChildDescriptors.add
		// (createChildParameter
		// (UmaPackage.eINSTANCE.getActivity_BreakdownElements(),
		// UmaFactory.eINSTANCE.createIteration()));
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#isExpanded()
	 */
	public Boolean isExpanded() {
		return expanded;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setExpanded(boolean)
	 */
	public void setExpanded(Boolean b) {
		expanded = b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.IBSItemProvider#getId()
	 */
	public int getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.IBSItemProvider#getTopItem()
	 */
	public Object getTopItem() {
		if (topItem == null) {
			if(ProcessUtil.isTopProcess(target)) {
				return target;
			}
			Object parent = getParent(target);
			if(parent != null) {
				Object ip = getRootAdapterFactory().adapt(parent, ITreeItemContentProvider.class);
				if(ip instanceof IBSItemProvider) {
					IBSItemProvider adapter = (IBSItemProvider) ip;
					if (adapter != null) {
						Object top = adapter.getTopItem();
						if (top == null && parent instanceof Process
								&& ((Process) parent).getSuperActivities() == null) {
							top = parent;
							adapter.setTopItem(top);
						}
						return top;
					}
				}
			}
		}
		return topItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.IBSItemProvider#setId(int)
	 */
	public void setId(int id) {
		this.id = id;
	}

	private static boolean isTopActivity(Object object) {
		EObject eObj = (EObject) object;
		Object parent = eObj.eContainer();
		if (parent instanceof ProcessComponent) {
			ProcessComponent pc = (ProcessComponent) parent;
			return object == pc.getProcess();
		}
		return false;
	}

	/**
	 * @param object
	 * @return
	 */
	protected static boolean hasChildDescriptor(Activity act) {
		for (Iterator iter = act.getBreakdownElements().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (element instanceof Descriptor) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getActivity_BreakdownElements());
		}
		return childrenFeatures;
	}

	protected void setParentFor(Object child, Object parent) {
		ProcessUtil.setParent(child, parent, getRootAdapterFactory());
	}

//	private Collection getDirectChildren(Object object) {
//		List myChildren;
//		ChildrenStore store = getChildrenStore(object);
//		if (store != null) {
//			myChildren = store.getChildren();
//		} else {
//			// store = createChildrenStore(object);
//			List result = store != null ? null : new ArrayList();
//			EObject eObject = (EObject) object;
//
//			for (Iterator i = getChildrenFeatures(object).iterator(); i
//					.hasNext();) {
//				EStructuralFeature feature = (EStructuralFeature) i.next();
//				if (feature.isMany()) {					
//					List children = (List) eObject.eGet(feature);
//					
////					//Change for Activity Variability. To do realization for
////					//Browsing and Publishing.
////					List children = new ArrayList();
////					if(filter != null && filter instanceof IConfigurator){
////						children.addAll(((IConfigurator)filter).getChildren(object, feature));
////					}
////					else{
////						children = (List) eObject.eGet(feature);
////					}
//					
//					int index = 0;
//					for (Iterator ci = children.iterator(); ci.hasNext(); index++) {
//						Object child = ci.next();
//						if (acceptAsChild(child)) {
//							child = wrap(eObject, feature, child, index);
//							setParentFor(child, object);
//							// IBSItemProvider adapter = (IBSItemProvider)
//							// getBestAdapterFactory().adapt(child,
//							// ITreeItemContentProvider.class);
//							// adapter.setRolledUp(false);
//							if (store != null) {
//								store.getList(feature).add(child);
//							} else {
//								result.add(child);
//							}
//						}
//					}
//				} else {
//					Object child = eObject.eGet(feature);
//					if (acceptAsChild(child)) {
//						child = wrap(eObject, feature, child,
//								CommandParameter.NO_INDEX);
//						setParentFor(child, object);
//						// IBSItemProvider adapter = (IBSItemProvider)
//						// getBestAdapterFactory().adapt(child,
//						// ITreeItemContentProvider.class);
//						// adapter.setRolledUp(false);
//
//						if (store != null) {
//							store.setValue(feature, child);
//						} else {
//							result.add(child);
//						}
//					}
//				}
//			}
//			myChildren = store != null ? store.getChildren() : result;
//		}
//		Collection children = addInherited(object, myChildren);
//		return children;
//	}
	
	private Collection getImmediateChildren(Object object) {
		DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
		boolean isSynFree = ProcessUtil.isSynFree();
		
		Collection children = new ArrayList();
		for (Iterator iter = super.getChildren(object).iterator(); iter.hasNext();) {
			Object child = (Object) iter.next();
			if(acceptAsChild(object, child)) {

				if (isSynFree) {
					Object unwrapped = TngUtil.unwrap(child);
					if (unwrapped instanceof Descriptor) {
						Descriptor des = (Descriptor) unwrapped;
						Descriptor greenParent = propUtil.getGreenParentDescriptor(des);
						if (greenParent != null) {
							continue;
						}
					}
				}
				
				if(configurator != null) {
					child = configurator.resolve(child);
				}
				if(child != null && !children.contains(child)) {
					setParentFor(child, object);
					children.add(child);
				}
			}
		}
		children = addInherited(object, (List) children);
		return children;
	}
	
	private Collection getRolledUpChildren(Object object) {
		if (rolledUpWrappers != null) {
			rolledUpWrappers.dispose();
		}

		List children = new ArrayList();
		AbstractTreeIterator iter = new AbstractTreeIterator(object, false) {

			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = -3159537629619737368L;

			protected Iterator getChildren(Object object) {
				if (object instanceof Activity) {
					BSActivityItemProvider adapter = (BSActivityItemProvider) adapterFactory.adapt(object, ITreeItemContentProvider.class);
					Iterator iterator;
					boolean b = adapter.isRolledUp();
					try {
						adapter.basicSetRolledUp(false);
						iterator = adapter.getChildren(object).iterator();
					}
					finally {
						adapter.basicSetRolledUp(b);
					}
					return iterator;
				} else if (object instanceof BreakdownElementWrapperItemProvider
						&& !(object instanceof TeamProfileWrapperItemProvider)) {
					return ((BreakdownElementWrapperItemProvider) object)
							.getChildren(object).iterator();
				}
				return Collections.EMPTY_LIST.iterator();
			}

		};
		// make sure that only one descriptor for each content element in the roll-up list
		//
		List descriptors = new ArrayList();
		while (iter.hasNext()) {
			Object child = iter.next();
			if (acceptAsRolledUpChild(child)) {
				Object e = TngUtil.unwrap(child);
				if(e instanceof Descriptor) {
					Object desc = findDescriptor(descriptors, e);
					if(desc == null) {
						if(isWrappingRollupNeeded(child)) {
							child = createRollupWrapper(child, object, adapterFactory);
						}
						children.add(child);
						descriptors.add(child);
					}
					else {
						if(isWrappingRollupNeeded(child)) {
							ComposedBreakdownElementWrapperItemProvider composedWrapper = ProcessUtil.getComposedWrapper(desc);
							if(composedWrapper != null) {
								composedWrapper.addElement(child);
							}
						}
					}
				}
				else {
					children.add(child);
				}
			}
		}
		descriptors.clear();
		descriptors = null;
		return children;
	}
	
	/**
	 * Checks if wrapping of the specified rollup element is needed. If it's
	 * needed, the caller will use
	 * {@link #createRollupWrapper(Object, Object, AdapterFactory)} to create
	 * wrapper for the rollup element.
	 * 
	 * @param object
	 * @return
	 */
	protected boolean isWrappingRollupNeeded(Object object) {
		return false;
	}
	
	protected ComposedBreakdownElementWrapperItemProvider createComposedWrapper(Object object, Object owner, AdapterFactory adapterFactory) {
		return new ComposedBreakdownElementWrapperItemProvider(object, owner, adapterFactory);
	}
	
	protected Object createRollupWrapper(Object object, Object owner, AdapterFactory adapterFactory) {		
		ComposedBreakdownElementWrapperItemProvider wrapper = createComposedWrapper(object, owner, adapterFactory);
		wrapper.readOnly = true;
		wrapper.isRollupChild = true;
		
		if(rolledUpWrappers == null) {
			rolledUpWrappers = new Disposable();
		}
		rolledUpWrappers.add(wrapper);

		return wrapper; 		
	}		
	
	private Collection doGetChildren(Object object) {
		if(isRolledUp()) {
			return getRolledUpChildren(object);
		}
		else {
			return getImmediateChildren(object);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = getChildren_(object);
		if (!(children instanceof List)) {
			return children;
		}

		Activity activity = (Activity) object;
		VariabilityType extendType = activity.getVariabilityType();
		if (extendType != VariabilityType.LOCAL_CONTRIBUTION
				&& extendType != VariabilityType.EXTENDS) {
			return children;
		}
		
		List childList = handleGlobalOrdering(activity, (List) children);
//		System.out.println("");
		
		return childList;
	}

	private List handleGlobalOrdering(Activity activity, List childList) {

		WbePropUtil propUtil = WbePropUtil.getWbePropUtil();
//		Set localSet = new HashSet(activity.getBreakdownElements());
//		System.out.println("LD> orignail: ");
//		for (int i = 0; i < childList.size(); i++) {
//			Object item = TngUtil.unwrap(childList.get(i));
//			System.out.println("LD> " + i + ": " + item);
//			if (localSet.contains(item)) {
//				System.out.println("LD> globalPrev: " + propUtil.getGlobalPresentedAfter((WorkBreakdownElement) item));
//			}
//			System.out.println("");
//		}
		
		WorkBreakdownElement globalPresentedAfter = null;
		LinkedChildList linkedChildList = null;
		Map<WorkBreakdownElement, LinkedChildListNode> map = null;
		
		List<LinkedChildListNode> toMoveList = new ArrayList<LinkedChildListNode>();
		for (int i = 0; i < childList.size(); i++) {
			Object item = childList.get(i);
			if (item instanceof WorkBreakdownElement) {
				WorkBreakdownElement wbe = (WorkBreakdownElement) item;
				globalPresentedAfter = propUtil.getGlobalPresentedAfter(wbe);
				if (globalPresentedAfter != null) {
					if (linkedChildList == null) {
						map = new HashMap<WorkBreakdownElement, LinkedChildListNode>();
						linkedChildList = getLinkedChildList(activity, childList, i, map);
					}
				}
			}
			if (linkedChildList != null) {
				addItemToLinkedChildList(map, linkedChildList, item, globalPresentedAfter);
				if (globalPresentedAfter != null) {
					toMoveList.add(linkedChildList.last);
				}
			}
		}
		boolean orderModified = false;
		for (LinkedChildListNode node : toMoveList) {
			WorkBreakdownElement key = node.globalPresentedAfter;
			LinkedChildListNode newPrevNode = key == activity ? linkedChildList.head
					: map.get(key);
			if (newPrevNode == null || node.prevNode == newPrevNode) {
				continue;
			}
			node.prevNode.nextNode = node.nextNode;
			if (node.nextNode != null) {
				node.nextNode.prevNode = node.prevNode;
			}

			node.prevNode = newPrevNode;
			node.nextNode = newPrevNode.nextNode;

			if (newPrevNode.nextNode != null) {
				newPrevNode.nextNode.prevNode = node;
			}
			newPrevNode.nextNode = node;
			orderModified = true;
		}
		
		if (orderModified) {
			List oldChildList = childList;
			childList = new ArrayList();			
			LinkedChildListNode node = linkedChildList.head.nextNode;
			while (node != null) {
				childList.add(node.thisItem);
				node = node.nextNode;
			}
			if (oldChildList.size() != childList.size()) {		//Should never happen, but in case
				childList = oldChildList;
			}
		}
	
//		System.out.println("LD> result: ");
//		for (int i = 0; i < childList.size(); i++) {
//			Object item = TngUtil.unwrap(childList.get(i));
//			System.out.println("LD> " + i + ": " + item);
//			if (localSet.contains(item)) {
//				System.out.println("LD> globalPrev: " + propUtil.getGlobalPresentedAfter((WorkBreakdownElement) item));
//			}
//			System.out.println("");
//		}
		
		return childList;
	}

	private LinkedChildList getLinkedChildList(Activity activity,
			List childList, int index,
			Map<WorkBreakdownElement, LinkedChildListNode> map) {
		
		LinkedChildList linkedChildList;
		linkedChildList = new LinkedChildList(activity);

		for (int i = 0; i < index; i++) {
			Object item = childList.get(i);
			addItemToLinkedChildList(map, linkedChildList, item, null);
		}

		return linkedChildList;
	}

	private void addItemToLinkedChildList(Map<WorkBreakdownElement, LinkedChildListNode> map,
			LinkedChildList linkedChildList, Object item, WorkBreakdownElement globalPresentedAfter) {
		LinkedChildListNode node = new LinkedChildListNode(item, globalPresentedAfter);
		node.prevNode = linkedChildList.last;
		linkedChildList.last.nextNode = node;
		linkedChildList.last = node;
		if (! (item instanceof WorkBreakdownElement)) {
			item = TngUtil.unwrap(item);
		}
		if (item instanceof WorkBreakdownElement) {
			map.put((WorkBreakdownElement) item, node);
		}
	}
	
	static class LinkedChildList {
		LinkedChildListNode head;
		LinkedChildListNode last;
		public LinkedChildList(Activity act) {
			head = new LinkedChildListNode(act, null);
			last = head;
		}
	}
	
	static class LinkedChildListNode {
		LinkedChildListNode prevNode;
		LinkedChildListNode nextNode;
		Object thisItem;
		WorkBreakdownElement globalPresentedAfter;
		LinkedChildListNode(Object item, WorkBreakdownElement globalPresentedAfter) {
			thisItem = item;
			this.globalPresentedAfter = globalPresentedAfter;
		}
	}
	
	private Collection getChildren_(Object object) {
		Activity activity = (Activity) object;
		if(configurator != null) {
			
			//TODO: remove the enableVariabilityInfoFlag and  
			// create an interface or do differently
			if(enableVariabilityInfo)
				variabilityInfo = configurator.getVariabilityInfo(activity);
			else 
				variabilityInfo = null;
		}
		else {
			variabilityInfo = null;
		}
		if(variabilityInfo != null) {
			Object resolved = variabilityInfo.getInheritanceList().get(0);
			BSActivityItemProvider ip = (BSActivityItemProvider) adapterFactory.adapt(resolved, ITreeItemContentProvider.class);			
			Collection children;
			boolean b = ip.isRolledUp();
			try {
				ip.basicSetRolledUp(isRolledUp());
				children = ip.doGetChildren(resolved);
			}
			finally {
				ip.basicSetRolledUp(b);
			}
			if(!variabilityInfo.getContributors().isEmpty()) {
				ArrayList contributedChildren = new ArrayList();
				for (Iterator iter = variabilityInfo.getContributors().iterator(); iter.hasNext();) {
					Object e = iter.next();
					Object adapter = adapterFactory.adapt(e, ITreeItemContentProvider.class);
					if(adapter instanceof BSActivityItemProvider) {
						ip = ((BSActivityItemProvider)adapter);
						b = ip.isRolledUp();
						try {
							ip.basicSetRolledUp(isRolledUp());
							for (Iterator iterator = ip.getImmediateChildren(e).iterator(); iterator
							.hasNext();) {
								e = iterator.next();
								e = Providers.getConfigurationApplicator().resolve(e, configurator.getMethodConfiguration());
								if(e != null && !TngUtil.contains(children, e)) {
									contributedChildren.add(e);
								}
							}
						}
						finally {
							ip.basicSetRolledUp(b);
						}
					}
				}
				if(!contributedChildren.isEmpty()) {
					children.addAll(wrapContributed(activity, contributedChildren));
				}
			}
			return children;
		}
		return doGetChildren(object);
	}
	
	protected void updateCachedChildren(Collection children) {
//		if(isRolledUp()) {
//			if(cachedRollupChildren == null) {
//				cachedRollupChildren = new ArrayList(children);
//			}
//			else {
//				cachedRollupChildren.clear();
//				cachedRollupChildren.addAll(children);
//			}
//		}
//		else {
//			if(cachedChildren == null) {
//				cachedChildren = new ArrayList(children);
//			}
//			else {
//				cachedChildren.clear();
//				cachedChildren.addAll(children);
//			}
//		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.ICachedChildrenItemProvider#getChildrenFromCache()
	 */
	public Collection getChildrenFromCache() {
		if(cachedChildren == null) {
			getChildren(target);
		}
		return cachedChildren;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.ICachedChildrenItemProvider#getRollupChildrenFromCache()
	 */
	public Collection getRollupChildrenFromCache() {
		if(cachedRollupChildren == null) {
			boolean oldRolledUp = rolledUp;
			try {
				rolledUp = true;
				getChildren(target);
			}
			finally {
				rolledUp = oldRolledUp;
			}			
		}
		return cachedRollupChildren;
	}
	
	private BreakdownElementWrapperItemProvider findWrapper(Object owner, Object value) {
		// find if a wrapper already exist for the given value and owner
		//
		Object adapter = adapterFactory.adapt(value,
				ITreeItemContentProvider.class);
		BreakdownElementWrapperItemProvider wrapper = null;
		if (adapter instanceof IBSItemProvider) {
			List listeners = ((IBSItemProvider) adapter).getListeners();
			if (listeners != null) {
				find_wrapper_loop: for (Iterator iter = listeners.iterator(); iter
						.hasNext();) {
					Object element = (Object) iter.next();
					if (element instanceof BreakdownElementWrapperItemProvider) {
						wrapper = (BreakdownElementWrapperItemProvider) element;
						if (wrapper.getValue() == value
								&& wrapper.getParent(value) == owner) {
							break find_wrapper_loop;
						} else {
							wrapper = null;
						}
					}
				}
			}
		}
		return wrapper;
	}

	private List wrapInherited(Activity owner, Collection breakdownElements) {
		if (wrappers == null) {
			wrappers = new Disposable();
		}	
		return wrap(owner, breakdownElements, true, false, wrappers);
	}
	
	private List wrapContributed(Activity owner, Collection breakdownElements) {
		if(contributedWrappers == null) {
			contributedWrappers = new Disposable();
		}
		return wrap(owner, breakdownElements, false, true, contributedWrappers);
	}
	
	private IWrapperItemProviderFactory getWrapperItemProviderFactory() {
		IWrapperItemProviderFactory factory = null;
		if(adapterFactory instanceof IWrapperItemProviderFactoryProvider) {
			factory = ((IWrapperItemProviderFactoryProvider)adapterFactory).getWrapperItemProviderFactory();
		}
		if(factory == null) {
			factory = IBreakdownElementWrapperItemProviderFactory.INSTANCE;
		}
		return factory;
	}
	
	private List<?> wrap(Activity owner, Collection<?> breakdownElements, boolean inherited, boolean contributed, Disposable wrappers) {
		ArrayList<Object> wrapperList = new ArrayList<Object>();
		IWrapperItemProviderFactory wrapperFactory = getWrapperItemProviderFactory();
		for (Iterator<?> iter = breakdownElements.iterator(); iter.hasNext();) {
			Object object = iter.next();
			Object unWrapped = TngUtil.unwrap(object);
			if (unWrapped instanceof BreakdownElement) {
				BreakdownElement e = (BreakdownElement) unWrapped;
				if (!TngUtil.isBase(owner.getBreakdownElements(), e, localVariabilityTypes)) {
					Object child = getWrapper(owner, e, wrappers);
					if(child == null) {
						child = wrapperFactory.createWrapper(e, owner, adapterFactory);
						if(child instanceof BreakdownElementWrapperItemProvider) {
							BreakdownElementWrapperItemProvider beWrapper = (BreakdownElementWrapperItemProvider) child;
							if(inherited) {
								beWrapper.isInherited = true;
							}
							else if(contributed) {
								beWrapper.contributed = true;
								if(object instanceof BreakdownElementWrapperItemProvider
										&& ((BreakdownElementWrapperItemProvider)object).isInherited()) {
									beWrapper.isInherited = true;
								}
							}
						}
					}
					else {
						wrappers.remove(child);
					}
					wrapperList.add(child);
				}
			}
		}
		wrappers.dispose();
		wrappers.addAll(wrapperList);
		return wrapperList;
	}
	
	private static Object getWrapper(Activity owner, BreakdownElement e, Collection wrappers) {
		for (Iterator iter = wrappers.iterator(); iter.hasNext();) {
			IWrapperItemProvider wrapper = (IWrapperItemProvider) iter.next();
			if(wrapper.getOwner() == owner && wrapper.getValue() == e) {
				return wrapper;
			}
		}
		return null;
	}
	
	protected Adapter createBaseListener() {
		if (baseListener == null) {
			baseListener = new AdapterImpl() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.epf.uma.provider.ActivityItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
				 */
				public void notifyChanged(final Notification notification) {
					Activity act = (Activity) BSActivityItemProvider.this
							.getTarget();
					switch (notification.getFeatureID(Activity.class)) {
					case UmaPackage.ACTIVITY__PRESENTATION_NAME:
						if (ProcessUtil.isExtendingOrLocallyContributing(act)) {
							fireNotifyChanged(new ViewerNotification(
									notification, act, false, true));
						}
						break;

					case UmaPackage.ACTIVITY__NAME:
						if (act.getVariabilityBasedOnElement() != null) {
							fireNotifyChanged(new ViewerNotification(
									notification, act, false, true));
						}
						break;

					case UmaPackage.ACTIVITY__SUPPRESSED:
						fireNotifyChanged(new ViewerNotification(notification,
								act, true, false));
						break;
					case UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT:
						fireNotifyChanged(new ViewerNotification(notification,
								act, true, true));
						refreshAffectedViewers();
						break;

					case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS: {
						if(handleReplaceBreakdownElement(notification)) {
							break;
						}
						List list = getAffectedChildren(notification);
						if (!list.isEmpty()) {
							boolean updateLabel = refreshChildrenData(
									notification, list);

							// forward notification
							//
							fireNotifyChanged(new ViewerNotification(
									notification, act, true, updateLabel));

							// ProcessUtil.refreshViewer(getRootAdapterFactory(),
							// (Process) getTopItem());
							refreshAffectedViewers();
						}

						break;
					}
					}
				}
			};
		}

		return baseListener;
	}

	protected Activity listenToBaseActivity() {
		Activity act = (Activity) target;
		if (!ProcessUtil.isExtendingOrLocallyContributing(act)) {
			return null;
		}
		Activity baseAct;
		if(variabilityInfo != null) {
			if(variabilityInfo.getInheritanceList().size() > 1) {
				baseAct = (Activity) variabilityInfo.getInheritanceList().get(1);
			}			
			else {
				baseAct = (Activity) configurator.resolve(act.getVariabilityBasedOnElement());
			}
		}
		else {
			baseAct = (Activity) act.getVariabilityBasedOnElement();
		}
		if (baseAct == null) {
			return null;
		}
		
		createBaseListener();
		
		// remove baseListener from old base, if there is any
		//
		EObject oldBase = (EObject) baseListener.getTarget();
		if(oldBase != null) {
			oldBase.eAdapters().remove(baseListener);
		}
		
		if (!baseAct.eAdapters().contains(baseListener)) {
			baseAct.eAdapters().add(baseListener);
		}

		Object ip = adapterFactory.adapt(baseAct, ITreeItemContentProvider.class);
		if(ip instanceof BSActivityItemProvider) {
			BSActivityItemProvider adapter = (BSActivityItemProvider) ip;
			adapter.listenToBaseActivity();
		}
		else {
			System.out.println();
		}
		return baseAct;
	}

	protected Collection addInherited(Object object, List myChildren) {
		if (object instanceof Activity) {
			Activity act = (Activity) object;
			Activity baseAct = listenToBaseActivity();
			if (baseAct != null) {
				VariabilityType extendType = act.getVariabilityType();
				if (extendType == VariabilityType.LOCAL_REPLACEMENT) {
					return myChildren;
				} else if (extendType == VariabilityType.LOCAL_CONTRIBUTION
						|| extendType == VariabilityType.EXTENDS) {
					BSActivityItemProvider adapter = (BSActivityItemProvider) getRootAdapterFactory()
						.adapt(baseAct, ITreeItemContentProvider.class);
					List allChildren;					
					boolean oldRolledUp = adapter.isRolledUp();
					try {
						if(oldRolledUp) {
							adapter.basicSetRolledUp(false);
						}
						allChildren = wrapInherited(act, adapter.getChildren(baseAct));
						
						handleCustomizingGreenDescriptors(act, allChildren);
					}
					finally {
						if(oldRolledUp) {
							adapter.basicSetRolledUp(true);
						}
					}
//					// go thru own children list and insert it to the combined
//					// child list at the right place.
//					//
//					for (Iterator iter = myChildren.iterator(); iter.hasNext();) {
//						BreakdownElement element = (BreakdownElement) iter
//								.next();
//						TngUtil.addTo(allChildren, element, object, TngUtil
//								.getBestAdapterFactory(adapterFactory));
//					}
					if(allChildren.isEmpty()) {
						return myChildren;
					}
					else {
						TngUtil.addAllTo(allChildren, myChildren);
						return allChildren;
					}
				}
			}
		}
		return myChildren;
	}

	private void handleCustomizingGreenDescriptors(Activity act,
			List allChildren) {
		if (allChildren == null || allChildren.isEmpty()
				|| !ProcessUtil.isSynFree()) {
			return;
		}
		
		DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
		Map<String, Descriptor> map = new HashMap<String, Descriptor>();
		
		List<BreakdownElement> beList = act.getBreakdownElements();
		for (int i = 0; i < beList.size(); i++) {
			BreakdownElement be = beList.get(i);
			if (be instanceof Descriptor) {
				Descriptor des = (Descriptor) be;
				String greenParent = propUtil.getGreenParent(des);
				if (greenParent != null) {
					map.put(greenParent, des);
				}
			}
		}
		
		int sz = map.size();
		
		if (sz == 0) {
			return;
		}
		
		for (int i = 0; i < allChildren.size(); i++) {
			Object child = TngUtil.unwrap(allChildren.get(i));
			if (child instanceof Descriptor) {
				Descriptor greenDes = (Descriptor) child;
				Descriptor des = map.get(greenDes.getGuid());
				if (des != null) {
					allChildren.set(i, des);
					sz--;
					if (sz == 0) {
						return;
					}
				}
			}
		}
		
	}

	/**
	 * Checks if there is no descriptor in <code>children</code> referring to
	 * the same object as the specified descriptor <code>child</code> does.
	 * 
	 * @param children
	 * @param child
	 * @return true if no descriptor is found in the children list that refers
	 *         to the same object as the specified child does, false otherwise.
	 */
	protected boolean isNewDescriptor(List children, Object child) {
		return findDescriptor(children, child) == null;
	}
	
	protected Object findDescriptor(List children, Object child) {
		Object obj = getObject((Descriptor) TngUtil.unwrap(child));
		if (obj == null)
			return null;
		for (int i = children.size() - 1; i > -1; i--) {
			Object o = children.get(i);
			if (obj == getObject((Descriptor) TngUtil.unwrap(o)))
				return o;
		}
		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.command.CreateChildCommand.Helper#getCreateChildImage(java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.util.Collection)
	 */
	public Object getCreateChildImage(Object owner, Object feature,
			Object child, Collection selection) {
		// return
		// TngEditPlugin.INSTANCE.getImage("full/ctool16/CreateActivity_breakdownElements_Activity");
		return getImage(child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createRemoveCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, java.util.Collection)
	 */
	protected Command createRemoveCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection) {
		return super.createRemoveCommand(domain, owner, feature, collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.IBSItemProvider#getItemProvider(org.eclipse.emf.ecore.EObject)
	 */
	public IBSItemProvider getItemProvider(EObject eObj) {
		return (IBSItemProvider) TngUtil
				.getAdapter(eObj, IBSItemProvider.class);
	}

	public void setParent(Object object) {
		parent = object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		if(parent == null) {
			if(target instanceof Activity) {
				return ((Activity)target).getSuperActivities();
			}
		}
		return parent;
	}

	/**
	 * Gets the list of children that had been affected after a an ADD, REMOVE,
	 * or MOVE operation.
	 */
	protected List getAffectedChildren(Notification notification) {
		return ProcessUtil.getAffectedElements(notification, childFilter);
	}

	protected boolean refreshChildrenData(Notification notification,
			List affectedChildren) {
		return false;
	}

	protected void doRefreshChildren(Notification notification,
			List affectedChildren) {
		refreshChildrenData(notification, affectedChildren);

		Process topAct = (Process) getTopItem();
		AdapterFactory rootAdapterFactory = getRootAdapterFactory();
		ProcessUtil.refreshViewer(rootAdapterFactory, topAct);
	}

	protected void refreshChildren(final Notification notification,
			final List newOrOldChildren) {
		if (!newOrOldChildren.isEmpty()) {
			Runnable runnable = new Runnable() {

				public void run() {
					doRefreshChildren(notification, newOrOldChildren);
				}

			};
			UserInteractionHelper.getUIHelper().runSafely(runnable, false);
		}
	}

	protected void refreshAffectedViewers() {
		Runnable runnable = new Runnable() {

			public void run() {
				doRefreshAffectedViewers();
			}

		};
		UserInteractionHelper.getUIHelper().runSafely(runnable, false);
	}

	protected void doRefreshAffectedViewers() {

	}
	
	protected boolean handlePredecessorListChange(Notification notification) {
		return TngUtil.handlePredecessorListChange(this, notification);
	}
	
	protected boolean handleReplaceBreakdownElement(Notification notification) {
		if(notification.getEventType() == Notification.SET && notification.getNewValue() != notification.getOldValue()) {
			if(notification.getNewValue() instanceof WorkBreakdownElement) {
				WorkBreakdownElement e = (WorkBreakdownElement) notification.getNewValue();
				if(!AssociationHelper.getLinkToSuccessor(e).isEmpty()) {
					ProcessUtil.refreshPredeccessorLists(adapterFactory, (Process) getTopItem());
				}
			}
			fireNotifyChanged(new ViewerNotification(notification,
					getTarget(), true, false));
			refreshAffectedViewers();
			return true;
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		if (handlePredecessorListChange(notification))
			return;

		switch (notification.getFeatureID(Activity.class)) {
		case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
			if(handleReplaceBreakdownElement(notification)) {
				return;
			}
			List newOrOldChildren = getAffectedChildren(notification);
			if (!newOrOldChildren.isEmpty()) {
				switch (notification.getEventType()) {
				case Notification.ADD:
				case Notification.ADD_MANY:
					createOrMovePackageFor(newOrOldChildren);
					break;
				}

				fireNotifyChanged(new ViewerNotification(notification,
						notification.getNotifier(), true, false));

				// refreshChildren(notification, newOrOldChildren);

				refreshAffectedViewers();
			}
			return;
		case UmaPackage.ACTIVITY__SUPPRESSED:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, true));
			return;
		case UmaPackage.ACTIVITY__VARIABILITY_TYPE:
		case UmaPackage.ACTIVITY__VARIABILITY_BASED_ON_ELEMENT:

			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, true));

			// ProcessUtil.refreshViewer(getRootAdapterFactory(), (Process)
			// getTopItem());

			refreshAffectedViewers();

			return;
		}

		super.notifyChanged(notification);
	}

	/**
	 * @param newOrOldChildren
	 */
	protected void createOrMovePackageFor(List<?> newChildren) {
		ProcessPackage pkg = (ProcessPackage) ((EObject) target).eContainer();
		if(pkg == null) {
			return;
		}
		for (Object element : newChildren) {
			if (element instanceof Activity) {
				Activity act = (Activity) element;
				if (act.eContainer() == null) {
					// create new ProcessPackage for the activity
					//
					ProcessPackage newPkg = UmaFactory.eINSTANCE
							.createProcessPackage();
					newPkg.setName(act.getName());
					pkg.getChildPackages().add(newPkg);
					newPkg.getProcessElements().add(act);
				} else {
					// Check if the activity's ProcessPackage is at the right
					// place.
					// If not, move it to the right ProcessComponent.
					//
					ProcessPackage oldPkg = (ProcessPackage) act.eContainer();
					if (oldPkg.eContainer() != pkg) {
						pkg.getChildPackages().add(oldPkg);
					}
				}
			} else if(element instanceof ProcessElement) {
				pkg.getProcessElements().add((ProcessElement) element);
			}
		}
	}

	private static void getAllBreakdownElements(Set set, BreakdownElement e) {
		boolean ret = set.add(e);
		if (ret && (e instanceof Activity)) {
			for (Iterator iter = ((Activity) e).getBreakdownElements()
					.iterator(); iter.hasNext();) {
				BreakdownElement element = (BreakdownElement) iter.next();
				getAllBreakdownElements(set, element);
			}
		}
	}

	/**
	 * Refresh the comma-separated list of predecessor IDs in the viewer
	 * 
	 * @param affectedBreakdownElements
	 */
	private void refreshPredecessors(Notification msg,
			List affectedBreakdownElements) {

		Set refreshList = new HashSet();
		Set allBreakdownElements = new HashSet();
		for (Iterator iter = affectedBreakdownElements.iterator(); iter
				.hasNext();) {
			BreakdownElement e = (BreakdownElement) iter.next();
			if (msg.getEventType() == Notification.REMOVE
					|| msg.getEventType() == Notification.REMOVE_MANY) {
				setParentFor(e, null);
			}
			getAllBreakdownElements(allBreakdownElements, e);
		}
		// System.out.println("BSActivityItemProvider.refreshPredecessors():
		// allBreakdownElements="+allBreakdownElements);
		for (Iterator iterator = allBreakdownElements.iterator(); iterator
				.hasNext();) {
			BreakdownElement element = (BreakdownElement) iterator.next();
			for (Iterator iterator1 = AssociationHelper.getLinkToSuccessor(
					element).iterator(); iterator1.hasNext();) {
				WorkOrder workOrder = (WorkOrder) iterator1.next();
				BreakdownElement succ = AssociationHelper
						.getSuccessor(workOrder);
				if (!allBreakdownElements.contains(succ)) {
					refreshList.add(succ);
				}
			}
		}

		// System.out.println("BSActivityItemProvider.refreshPredecessors():
		// refreshList=" + refreshList);

		for (Iterator iter = refreshList.iterator(); iter.hasNext();) {
			Object child = iter.next();
			fireNotifyChanged(new ViewerNotification(msg, child, false, true));
		}
	}

	/**
	 * @param activity
	 */
	private void createPackageFor(Activity activity, EObject parentActivity) {
		ProcessPackage parent = (ProcessPackage) parentActivity.eContainer();
		ProcessPackage newPkg = UmaFactory.eINSTANCE.createProcessPackage();
		newPkg.setName(activity.getName());
		parent.getChildPackages().add(newPkg);
		ArrayList breakdownElements = new ArrayList();
		breakdownElements.add(activity);
		breakdownElements.addAll(activity.getBreakdownElements());
		newPkg.getProcessElements().addAll(breakdownElements);
	}

	protected String getColumnName(int columnIndex) {
		AdapterFactory rootAdapterFactory = getRootAdapterFactory();
		if (rootAdapterFactory instanceof IColumnAware) {
			Map columnIndexToNameMap = ((IColumnAware) rootAdapterFactory)
					.getColumnIndexToNameMap();
			if (columnIndexToNameMap != null) {
				return (String) columnIndexToNameMap.get(new Integer(
						columnIndex));
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ITableItemLabelProvider#getColumnImage(java.lang.Object,
	 *      int)
	 */
	public Object getColumnImage(Object object, int columnIndex) {
		String colName = getColumnName(columnIndex);
		return TngUtil.getColumnImage(object, colName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ITableItemLabelProvider#getColumnText(java.lang.Object,
	 *      int)
	 */
	public String getColumnText(Object object, int columnIndex) {
		String colName = getColumnName(columnIndex);
		return getAttribute(object, colName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.IBSItemProvider#setTopItem(java.lang.Object)
	 */
	public void setTopItem(Object top) {
		topItem = top;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#isRolledUp()
	 */
	public boolean isRolledUp() {
		return rolledUp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setRolledUp(boolean)
	 */
	public void setRolledUp(boolean b) {
		rolledUp = b;
		if (!rolledUp) {
			// user roll down this activity, set rolledUp flag of all its
			// offstring to false
			//
			Iterator iter = new AbstractTreeIterator(target, false) {

				/**
				 * Comment for <code>serialVersionUID</code>
				 */
				private static final long serialVersionUID = -7840687264753640250L;

				protected Iterator getChildren(Object object) {
					ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory
							.adapt(object, ITreeItemContentProvider.class);
					return adapter.getChildren(object).iterator();
				}

			};
			while (iter.hasNext()) {
				Object child = (Object) iter.next();
				Object adapter = adapterFactory.adapt(child,
						ITreeItemContentProvider.class);
				if (adapter instanceof IBSItemProvider) {
					((IBSItemProvider) adapter).setRolledUp(false);
				}
			}
		}
	}

	public GraphicalData getGraphicalData() {
		return graphicalData;
	}

	public void setGraphicalData(GraphicalData data) {
		graphicalData = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getAttribute(java.lang.Object,
	 *      java.lang.String)
	 */
	public String getAttribute(Object object, String property) {
		return ProcessUtil.getAttribute(object, property, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#setAttribute(java.lang.Object,
	 *      java.lang.String, java.lang.String)
	 */
	public void setAttribute(Object object, String prop, String txt) {
		Activity e = (Activity) object;
		ProcessUtil.setAttribute(e, prop, txt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createCreateChildCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object, int,
	 *      java.util.Collection)
	 */
	protected Command createCreateChildCommand(EditingDomain domain,
			EObject owner, EStructuralFeature feature, Object value, int index,
			Collection collection) {
		if (value instanceof Iteration) {
			((Iteration) value).setIsRepeatable(Boolean.TRUE);
		}
		return super.createCreateChildCommand(domain, owner, feature, value,
				index, collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getListeners()
	 */
	public List getListeners() {
		if (changeNotifier == null)
			return null;
		return Collections.unmodifiableList((List) changeNotifier);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getPredecessors()
	 */
	public PredecessorList getPredecessors() {
		if (predecessors == null) {
			predecessors = new PredecessorList(TngUtil
					.getBestAdapterFactory(adapterFactory), target);
		}
		return predecessors;
	}

	public boolean isFirstElement(Object obj) {
		return ProcessUtil.isFirstElement(getRootAdapterFactory(), this, obj);
	}

	public boolean isLastElement(Object obj) {
		return ProcessUtil.isLastElement(getRootAdapterFactory(), this, obj);
	}

	public void moveUp(Object obj, IActionManager actionMgr) {
		Object parent = this.getParent(obj);
		if ((parent != null) && (parent instanceof Activity)) {
			TngUtil.moveUp((Activity) parent, obj, getEClasses(), actionMgr);
		}
	}

	public void moveDown(Object obj, IActionManager actionMgr) {
		Object parent = this.getParent(obj);
		if ((parent != null) && (parent instanceof Activity)) {
			TngUtil.moveDown((Activity) parent, obj, getEClasses(), actionMgr);
		}
	}

	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		return new ActivityAddCommand((AddCommand) super.createAddCommand(
				domain, owner, feature, collection, index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.ActivityItemProvider#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		Object img = TngUtil.getImage(object);
		return img != null ? img : super.getImage(object);
	}

	public IFilter getChildFilter() {
		return childFilter;
	}

	public void setRefreshAllIDsRequired(boolean b) {
		refreshAllIDsRequired = b;
	}

	public boolean isRefreshAllIDsRequired() {
		return refreshAllIDsRequired;
	}

	public IFilter getFilter() {
		return this.filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setFilter(com.ibm.library.edit.IFilter)
	 */
	public void setFilter(IFilter filter) {
		this.filter = filter;
		if(filter instanceof IConfigurator) {
			configurator = (IConfigurator)filter;
		}
		else {
			configurator = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setLabel(java.lang.String)
	 */
	public void setLabel(String label) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#createDropCommand(java.lang.Object,
	 *      java.util.List)
	 */
	public IResourceAwareCommand createDropCommand(Object owner,
			List dropElements) {
		return null;
	}
	
	public void basicSetRolledUp(boolean b) {
		rolledUp = b;
	}
	
	/**
	 * @return the enableVariabilityInfo
	 */
	public boolean isEnableVariabilityInfo() {
		return enableVariabilityInfo;
	}

	/**
	 * @param enableVariabilityInfo the enableVariabilityInfo to set
	 */
	public void setEnableVariabilityInfo(boolean enableVariabilityInfo) {
		this.enableVariabilityInfo = enableVariabilityInfo;
	}
	
	private class MoveCommandEx extends MoveCommand implements IResourceAwareCommand {

		private Set<Resource> modifiedResources;

		public MoveCommandEx(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index) {
			super(domain, owner, feature, value, index);
		}				
		
		@Override
		public void doExecute() {
			// translate view index to index in breakdown elements list
			//
			List children = (List) getChildren(getTarget());
			Object child = children.get(index);
			index = ownerList.indexOf(child);
			if(index != -1) {
				super.doExecute();
			}
			
			//TODO: move in between green elements
		}

		public Collection getModifiedResources() {
			if(modifiedResources == null) {
				Resource resource = owner.eResource();
				if(resource != null) {
					modifiedResources = Collections.singleton(resource);
				}
				else {
					modifiedResources = Collections.EMPTY_SET;
				}
			}
			return modifiedResources;
		}
	}
	
	@Override
	protected Command createMoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index) {
		return new MoveCommandEx(domain, owner, feature, value, index);
	}
	
	protected IConfigurator getConfigurator() {
		return configurator;
	}
	
}