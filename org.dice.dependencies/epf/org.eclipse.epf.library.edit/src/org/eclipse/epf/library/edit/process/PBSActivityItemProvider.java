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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.epf.library.edit.ICachedChildrenItemProvider;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.command.PBSDragAndDropCommand;
import org.eclipse.epf.library.edit.process.command.PBSDropCommand;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class PBSActivityItemProvider extends BSActivityItemProvider 
implements ICachedChildrenItemProvider
{

	private Disposable rolledUpWrappers;

	/**
	 * @param adapterFactory
	 */
	public PBSActivityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		// Activity act = (Activity) object;
		// if(hasChildDescriptor(act)) {
		// newChildDescriptors.add
		// (createChildParameter
		// (UmaPackage.eINSTANCE.getActivity_BreakdownElements(),
		// UmaFactory.eINSTANCE.createWorkProductDescriptor()));
		// }
		// else {
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getActivity_BreakdownElements(), UmaFactory.eINSTANCE
				.createPhase()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getActivity_BreakdownElements(), UmaFactory.eINSTANCE
				.createIteration()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getActivity_BreakdownElements(), UmaFactory.eINSTANCE
				.createActivity()));

		// if(act.getBreakdownElements().isEmpty()) {
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getActivity_BreakdownElements(), UmaFactory.eINSTANCE
				.createWorkProductDescriptor()));
		// }

		// }

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getActivity_BreakdownElements(), UmaFactory.eINSTANCE
				.createMilestone()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.process.BSActivityItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object child) {
		child = TngUtil.unwrap(child);

		if (child instanceof Activity
				|| child instanceof WorkProductDescriptor
				|| child instanceof Milestone) {
			return super.acceptAsChild(child);
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		if (rolledUpWrappers != null) {
			rolledUpWrappers.dispose();
		}

		if (isRolledUp()) {
			Map workProductToWrappersMap = new HashMap();
			Collection children = super.getChildren(object);
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				Object child = iter.next();
				Object e = TngUtil.unwrap(child);
				if (e instanceof WorkProductDescriptor) {
					WorkProductDescriptor desc = (WorkProductDescriptor) e;
					List list = AssociationHelper.getDeliverableDescriptors(desc);
					Object wrapperKey = desc.getWorkProduct() == null ? (Object)desc : desc.getWorkProduct();
					List wrappers = (List) workProductToWrappersMap.get(wrapperKey);
					if (list.size() < 1) {
						// check if the descriptor already exists in the
						// rollup list
						//
						if(wrappers == null) {
							wrappers = new ArrayList();
							workProductToWrappersMap.put(wrapperKey, wrappers);
							wrappers.add(new ComposedWPDescriptorWrapperItemProvider(child, object, adapterFactory));
						}
						else {
							for (int i = wrappers.size() - 1; i > -1; i--) {
								((ComposedWPDescriptorWrapperItemProvider)wrappers.get(i)).addWorkProductDescriptor(child);
							}
						}
					} else {
							boolean checkExist = true;							
							if(wrappers == null) {
								wrappers = new ArrayList();
								workProductToWrappersMap.put(wrapperKey, wrappers);
								checkExist = false;
							}
							if(checkExist) {
								// remove any wrapper that is not of type DeliverableWorkProductDescriptorItemProvider from the wrapper list
								//
								for (Iterator iterator = wrappers.iterator(); iterator
										.hasNext();) {
									Object wrapper = (Object) iterator.next();
									if(!(wrapper instanceof DeliverableWorkProductDescriptorItemProvider)) {
										iter.remove();
									}
								}
								
								checkExist = wrappers.size() > 0;
							}
							
							// show one descriptor per deliverable
							//
							add_descriptors:
							for (Iterator iterator = list.iterator(); iterator.hasNext();) {
								Object deliverableDescriptor = iterator.next();
								if(checkExist) {
									for (int i = wrappers.size() - 1; i > -1; i--) {
										DeliverableWorkProductDescriptorItemProvider wrapper = (DeliverableWorkProductDescriptorItemProvider) wrappers.get(i);
										if(wrapper.deliverableDescriptor == deliverableDescriptor) {
											wrapper.addWorkProductDescriptor(child);
											continue add_descriptors;
										}
									}
								}

								DeliverableWorkProductDescriptorItemProvider itemProvider = new DeliverableWorkProductDescriptorItemProvider(
										desc, object, adapterFactory);
								itemProvider.deliverableDescriptor = deliverableDescriptor;
								wrappers.add(itemProvider);
							}

					}
				}
			}
			List rolledUpChildren = new ArrayList();
			for (Iterator iter = workProductToWrappersMap.values().iterator(); iter.hasNext();) {
				rolledUpChildren.addAll((Collection) iter.next());
				
			}
			List result = removeSubartifactsFromChildren(rolledUpChildren, true);
			if(rolledUpWrappers == null) {
				rolledUpWrappers = new Disposable();
			}
			rolledUpWrappers.addAll(result);
			
			// dispose wrappers of subartifact
			//
			for(int i = rolledUpChildren.size() - 1; i > -1; i--) {
				IWrapperItemProvider wrapper = (IWrapperItemProvider) rolledUpChildren.get(i);
				if(!rolledUpWrappers.contains(wrapper)) {
					wrapper.dispose();
				}
			}
			
			Collections.sort(result, Comparators.PRESENTATION_NAME_COMPARATOR);
			
			updateCachedChildren(result);
			
			return result;
		}

		Collection children = removeSubartifactsFromChildren(super.getChildren(object), false);
		
		updateCachedChildren(children);
		
		return children;
	}	
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BreakdownElementItemProvider#dispose()
	 */
	public void dispose() {
		if(rolledUpWrappers != null) {
			rolledUpWrappers.dispose();
		}
		
		super.dispose();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#isNewDescriptor(java.util.List, java.lang.Object)
	 */
	protected boolean isNewDescriptor(List children, Object child) {
		// return true to let overriden getChildren() to handle the uniqueness of the rollup list
		//
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#createOrMovePackageFor(java.util.List)
	 */
	protected void createOrMovePackageFor(List newChildren) {
		super.createOrMovePackageFor(newChildren);

		for (Iterator iter = newChildren.iterator(); iter.hasNext();) {
			Object e = iter.next();
			if (e instanceof WorkProductDescriptor) {
				// add deliverable parts to the activity's package
				WorkProductDescriptor wpd = (WorkProductDescriptor) e;
				for (Iterator iterator = wpd.getDeliverableParts().iterator(); iterator
						.hasNext();) {
					WorkProductDescriptor part = (WorkProductDescriptor) iterator
							.next();
					ProcessUtil.addToContainer(part, this, false);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#getObject(org.eclipse.epf.uma.Descriptor)
	 */
	protected Object getObject(Descriptor descriptor) {
		return ((WorkProductDescriptor) descriptor).getWorkProduct();
	}

	protected Command createDragAndDropCommand(EditingDomain domain,
			Object owner, float location, int operations, int operation,
			Collection collection) {
		return new PBSDragAndDropCommand(domain, owner, location, operations,
				operation, collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#createDropCommand(java.lang.Object,
	 *      java.util.List)
	 */
	public IResourceAwareCommand createDropCommand(Object owner,
			List dropElements) {
		return new PBSDropCommand((Activity) owner, dropElements);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#acceptAsRolledUpChild(java.lang.Object)
	 */
	protected boolean acceptAsRolledUpChild(Object child) {
		Object obj = TngUtil.unwrap(child);

		if (obj instanceof WorkProductDescriptor && ((WorkProductDescriptor)obj).getSuperActivities() != null) {
			return super.acceptAsRolledUpChild(obj);
		}
		return false;
	}

	public Collection getEClasses() {
		return ProcessUtil.getPBSEclasses();
	}

	private static class DeliverableWorkProductDescriptorItemProvider extends
			ComposedWPDescriptorWrapperItemProvider {

		private Object deliverableDescriptor;

		/**
		 * @param value
		 * @param owner
		 * @param adapterFactory
		 */
		public DeliverableWorkProductDescriptorItemProvider(
				WorkProductDescriptor value, Object owner,
				AdapterFactory adapterFactory) {
			super(value, owner, adapterFactory);
			readOnly = false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ibm.library.edit.process.BreakdownElementWrapperItemProvider#getAttribute(java.lang.Object,
		 *      java.lang.String)
		 */
		public String getAttribute(Object object, String property) {
			if (property == IBSItemProvider.COL_DELIVERABLE) {
				return TngUtil.getPresentationName(deliverableDescriptor);
			}
			return super.getAttribute(object, property);
		}

	}

	protected List removeSubartifactsFromChildren(Collection children, boolean unwrap) {
		MethodConfiguration config = getConfigurator() == null ? null : getConfigurator().getMethodConfiguration();
		List<?> list = ProcessUtil.removeSubartifactsFromChildren(config, children, unwrap);
		if(list.size() !=  children.size()) {
			// parent of descriptors of subartifacts has been set to this activity
			// iterate the subtree of the artifact descriptors in chidren list to correct this
			//
			for (Iterator<?> iterator = children.iterator(); iterator.hasNext();) {
				Object child = (Object) iterator.next();
				if(child instanceof WorkProductDescriptor && ((WorkProductDescriptor)child).getWorkProduct() instanceof Artifact) {
					for(AdapterFactoryTreeIterator<?> iter = new AdapterFactoryTreeIterator<Object>(adapterFactory, child);
						iter.hasNext(); iter.next());					
				}
			}
		}
		return list;
	}

	@Override
	protected boolean isWrappingRollupNeeded(Object object) {
		if(TngUtil.unwrap(object) instanceof WorkProductDescriptor) {
			return true;
		}
		return super.isWrappingRollupNeeded(object);
	}
	
}
