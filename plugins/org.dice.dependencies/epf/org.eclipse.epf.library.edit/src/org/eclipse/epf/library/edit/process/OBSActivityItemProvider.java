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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.command.OBSDragAndDropCommand;
import org.eclipse.epf.library.edit.process.command.OBSDropCommand;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * Item provider for Team Allocation activity
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class OBSActivityItemProvider extends BSActivityItemProvider {

	private Disposable rolledUpWrappers;

	/**
	 * @param adapterFactory
	 */
	public OBSActivityItemProvider(AdapterFactory adapterFactory) {
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
		// UmaFactory.eINSTANCE.createRoleDescriptor()));
		//    		
		// newChildDescriptors.add
		// (createChildParameter
		// (UmaPackage.eINSTANCE.getActivity_BreakdownElements(),
		// UmaFactory.eINSTANCE.createTeamProfile()));
		//
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
				.createRoleDescriptor()));

		// COMPOSITE ROLE FEATURE IS NOT AVAILABLE FOR FIRST RELEASE.
		// newChildDescriptors.add
		// (createChildParameter
		// (UmaPackage.eINSTANCE.getActivity_BreakdownElements(),
		// UmaFactory.eINSTANCE.createCompositeRole()));
		//				
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getActivity_BreakdownElements(), UmaFactory.eINSTANCE
				.createTeamProfile()));

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

		if (child instanceof Activity || child instanceof RoleDescriptor
				|| child instanceof TeamProfile || child instanceof Milestone) {
			return super.acceptAsChild(child);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#getObject(org.eclipse.epf.uma.Descriptor)
	 */
	protected Object getObject(Descriptor descriptor) {
		try {
			return ((RoleDescriptor) descriptor).getRole();
		}
		catch(ClassCastException e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#createDropCommand(java.lang.Object,
	 *      java.util.List)
	 */
	public IResourceAwareCommand createDropCommand(Object owner,
			List dropElements) {
		return new OBSDropCommand((Activity) owner, dropElements);
	}

	protected Command createDragAndDropCommand(EditingDomain domain,
			Object owner, float location, int operations, int operation,
			Collection collection) {
		// System.out
		// .println("ENTER:
		// com.ibm.library.edit.process.WBSActivityItemProvider#createDragAndDropCommand(domain,
		// owner, location, operations, operation, collection)");
		Command cmd = new OBSDragAndDropCommand(domain, owner, location,
				operations, operation, collection);

		// System.out.println(" can execute: " + cmd.canExecute());
		// System.out.println(" owner: " + owner);
		// System.out.println(" collection: " + collection);
		// System.out.println(" location: " + location);
		// System.out.println(" operations: " + operations);
		// System.out.println(" operation: " + operation);
		//        
		// System.out
		// .println("EXIT:
		// com.ibm.library.edit.process.WBSActivityItemProvider#createDragAndDropCommand(domain,
		// owner, location, operations, operation, collection)");
		return cmd;
	}

	// /* (non-Javadoc)
	// * @see
	// com.ibm.library.edit.process.BSActivityItemProvider#refreshChildren(org.eclipse.emf.common.notify.Notification,
	// java.util.List)
	// */
	// protected void refreshChildren(Notification notification, List
	// newOrOldChildren) {
	// if(!newOrOldChildren.isEmpty()) {
	// Process topAct = (Process) getTopItem();
	// AdapterFactory rootAdapterFactory = getRootAdapterFactory();
	//						
	// ProcessUtil.refreshViewer(rootAdapterFactory, topAct);
	//			
	// return;
	//
	// }
	// }
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#isNewDescriptor(java.util.List, java.lang.Object)
	 */
	protected boolean isNewDescriptor(List children, Object child) {
		Object obj = TngUtil.unwrap(child);
		// collect all role descriptors or their wrappers first for matching with team profiles
		// and eliminate the duplicates later in getChildren()
		//
		if(obj instanceof RoleDescriptor) {
			return true;
		}
		return super.isNewDescriptor(children, obj);
	}		

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		if (rolledUpWrappers != null) {
			rolledUpWrappers.dispose();
		}

		if (isRolledUp()) {
			List teamProfiles = new ArrayList();
			List roleDescriptors = new ArrayList();
			HashSet roleTeamPairs = new HashSet();
			HashSet roleDescriptorsWithoutLinkedElements = new HashSet();
			Collection children = super.getChildren(object);
			
			// collect all team profiles under this activity
			//
			HashSet validTeamProfiles = new HashSet();
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				Object o = iter.next();
				Object e = TngUtil.unwrap(o);
				if (e instanceof TeamProfile) {
					if(validTeamProfiles.add(e)) {
						teamProfiles.add(o);
					}
				}
			}
			
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				Object child = iter.next();
				Object e = TngUtil.unwrap(child);
				if (e instanceof RoleDescriptor) {
					RoleDescriptor desc = (RoleDescriptor) e;
					List list;
					List descriptors = null;
					ComposedBreakdownElementWrapperItemProvider composedWrapper = null;					
					if(child instanceof RoleDescriptorWrapperItemProvider) {
						Object value = ((RoleDescriptorWrapperItemProvider)child).getValue();
						if(value instanceof ComposedBreakdownElementWrapperItemProvider) {
							composedWrapper = (ComposedBreakdownElementWrapperItemProvider) value;
						}
					}
					if(composedWrapper != null) {
						list = new UniqueEList();
						descriptors = new ArrayList();
						for (Iterator iterator = composedWrapper.getValues().iterator(); iterator
								.hasNext();) {
							Object o = iterator.next();
							Object element = TngUtil.unwrap(o);
							if(element instanceof RoleDescriptor) {
								List teams = AssociationHelper.getTeamProfiles((RoleDescriptor) element);
								for (Iterator itor = teams.iterator(); itor
										.hasNext();) {
									Object team = itor.next();
									if(list.add(team)) {
										descriptors.add(o);
									}
									
								}
							}
						}
					}
					else {
						list = AssociationHelper.getTeamProfiles(desc);
					}
					int size = list.size(); 
					if(size > 1) {
						if (rolledUpWrappers == null) {
							rolledUpWrappers = new Disposable();
						}
						
						boolean readOnly = false;
						if (child instanceof BreakdownElementWrapperItemProvider) {
							readOnly = ((BreakdownElementWrapperItemProvider) child)
							.isReadOnly();
						}
						
						// show one descriptor per team
						//
						for (int i = 0; i < size; i++) {
							TeamRoleDescriptorItemProvider itemProvider = new TeamRoleDescriptorItemProvider(
									descriptors.get(i), object, adapterFactory);
							itemProvider.readOnly = readOnly;
							TeamProfile team = (TeamProfile) list.get(i);
							
//							// add team to the team list only if the team is under this activity
//							//
//							if(validTeamProfiles.contains(team)) {
//								addTeamProfiles(object, teamProfiles, team);
//							}
							
							itemProvider.team = team;
							rolledUpWrappers.add(itemProvider);
							roleDescriptors.add(itemProvider);
							roleTeamPairs.add(new RoleTeamPair((Role) getObject(desc), team));
						}						
					}
					else {
						Role role = (Role) getObject(desc);
						TeamProfile team = (TeamProfile) (size == 1 ? list.get(0) : null);
						if(role == null) {
							if(!roleDescriptorsWithoutLinkedElements.contains(e)) {
								roleDescriptors.add(child);
								
//								// add team to the team list only if the team is under this activity
//								//
//								if(team != null && validTeamProfiles.contains(team)) {
//									addTeamProfiles(object, teamProfiles, team);
//								}
								
								roleDescriptorsWithoutLinkedElements.add(e);
							}
						}
						else {
							RoleTeamPair roleTeamPair = new RoleTeamPair(role, team);
							if(!roleTeamPairs.contains(roleTeamPair)) {
								roleDescriptors.add(child);
								
//								// add team to the team list only if the team is under this activity
//								//
//								if(team != null && validTeamProfiles.contains(team)) {
//									addTeamProfiles(object, teamProfiles, team);
//								}
								
								roleTeamPairs.add(roleTeamPair);
							}
						}
					}
				}
			}
			roleTeamPairs.clear();
			roleTeamPairs = null;
			Collections.sort(teamProfiles, Comparators.PRESENTATION_NAME_COMPARATOR);
			Collections.sort(roleDescriptors, Comparators.PRESENTATION_NAME_COMPARATOR);
			children = new ArrayList(teamProfiles);
			children.addAll(roleDescriptors);
			
			updateCachedChildren(children);
			
			return children;
		}
		Collection children = super.getChildren(object);
		updateCachedChildren(children);
		return children;
	}
	
	public Collection getEClasses() {
		return ProcessUtil.getOBSEclasses();
	}
	
	private void addTeamProfiles(Object owner, Collection teamProfiles, TeamProfile teamProfile) {
		if (!TngUtil.checkExist(teamProfiles, teamProfile)) {
			TeamProfileWrapperItemProvider wrapper = new TeamProfileWrapperItemProvider(
					teamProfile, owner, adapterFactory) {
				/* (non-Javadoc)
				 * @see org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider#getChildren(java.lang.Object)
				 */
				public Collection getChildren(Object object) {
					Collection children = super.getChildren(object);
					
//					// don't show role descriptors
//					//
//					for (Iterator iter = children.iterator(); iter.hasNext();) {
//						Object child = TngUtil.unwrap(iter.next());
//						if(child instanceof RoleDescriptor) {
//							iter.remove();
//						}
//					}	
					
					return children;
				}
				
			};
			IBSItemProvider adapter = (IBSItemProvider) adapterFactory
					.adapt(teamProfile,
							ITreeItemContentProvider.class);
			wrapper.readOnly = (getTopItem() != adapter.getTopItem());
			teamProfiles.add(wrapper);
		}
	}

	private static class TeamRoleDescriptorItemProvider extends
			RoleDescriptorWrapperItemProvider {
		
		
		public TeamRoleDescriptorItemProvider(Object value, Object owner, AdapterFactory adapterFactory) {
			super(value, owner, adapterFactory);
		}

		/**
		 * @param value
		 * @param owner
		 * @param adapterFactory
		 */
		public TeamRoleDescriptorItemProvider(RoleDescriptor value,
				Object owner, AdapterFactory adapterFactory) {
			super(value, owner, adapterFactory);
			readOnly = false;
		}

		private TeamProfile team;

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ibm.library.edit.process.RoleDescriptorItemProvider#getAttribute(java.lang.Object,
		 *      java.lang.String)
		 */
		public String getAttribute(Object object, String property) {
			if (property == IBSItemProvider.COL_TEAMS && team != null) {
				return TngUtil.getPresentationName(team);
			}
			return super.getAttribute(object, property);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ibm.library.edit.process.BreakdownElementWrapperItemProvider#createWrapper(java.lang.Object,
		 *      java.lang.Object, org.eclipse.emf.common.notify.AdapterFactory)
		 */
		protected IWrapperItemProvider createWrapper(Object value,
				Object owner, AdapterFactory adapterFactory) {
			BreakdownElementWrapperItemProvider wrapper = (BreakdownElementWrapperItemProvider) super
					.createWrapper(value, owner, adapterFactory);
			wrapper.readOnly = readOnly;
			return wrapper;
		}
	}

	private static class RoleTeamPair {
		private Role role;
		private TeamProfile team;

		RoleTeamPair(Role role, TeamProfile team) {
			this.role = role;
			this.team = team;
		}
		
		public boolean equals(Object obj) {
			if(obj == this) return true;
			if(obj instanceof RoleTeamPair) {
				RoleTeamPair other = (RoleTeamPair) obj;
				return role == other.role && (team == other.team || (team == null && other.team == null));
			}
			else {
				return false;
			}
		}
		
		public int hashCode() {
			String str = role.getGuid();
			if(team != null) {
				str = str + team.getGuid();
			}
			return str.hashCode();			
		}
	}
	
	@Override
	protected boolean isWrappingRollupNeeded(Object object) {
		if(TngUtil.unwrap(object) instanceof RoleDescriptor) {
			return true;
		}
		return super.isWrappingRollupNeeded(object);
	}

	@Override
	protected Object createRollupWrapper(Object object, Object owner, AdapterFactory adapterFactory) {
		Object wrapper = super.createRollupWrapper(object, owner, adapterFactory);
		return new RoleDescriptorWrapperItemProvider(wrapper, owner, adapterFactory);
	}
	

}
