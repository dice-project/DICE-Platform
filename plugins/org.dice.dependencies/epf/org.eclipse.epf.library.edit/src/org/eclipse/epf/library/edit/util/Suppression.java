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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.ComposedBreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.command.RemoveUnusedDescriptorsCommand;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;

import com.ibm.icu.util.StringTokenizer;

/**
 * This class calculates the suppression state, both direct and indirect, of any
 * breakdown element or its wrapper in the specified process.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class Suppression {

	public static final String WBS = "wbs"; //$NON-NLS-1$

	public static final String TBS = "tbs"; //$NON-NLS-1$

	public static final String WPBS = "wpbs"; //$NON-NLS-1$

	public static final String CBS = "cbs"; //$NON-NLS-1$

	private static final Map procToSuppressionMap = new HashMap();

	private static boolean autoInheritIntermediateSuppressionState = true;

	protected Process process;

	private Set suppressedExternalElementPaths;

	private boolean modified;

	private Set internalUnsuppressedElements = new HashSet();

	public static final void clearCachedSuppressions() {
		procToSuppressionMap.clear();
	}

	/**
	 * Removes suppressions of invalid processes
	 */
	public static final void cleanUp() {
		ArrayList listToRemove = new ArrayList();
		for (Iterator iter = new ArrayList(procToSuppressionMap.keySet())
				.iterator(); iter.hasNext();) {
			Process proc = (Process) iter.next();
			if (proc.eIsProxy()) {
				listToRemove.add(proc);
			}
		}
		int size = listToRemove.size();
		for (int i = 0; i < size; i++) {
			procToSuppressionMap.remove(listToRemove.get(i));
		}
	}

	public static final void setAutoInheritSuppressionStates(boolean b) {
		autoInheritIntermediateSuppressionState = b;
	}

	public Suppression(Process process) {
		this.process = process;
		suppressedExternalElementPaths = loadSuppressedElementPaths();
	}

	public Process getProcess() {
		return process;
	}

	private Set loadSuppressedElementPaths() {
		Constraint rule = ConstraintManager.getConstraint(process,
				ConstraintManager.PROCESS_SUPPRESSION, false); // (Constraint)
		// process.getOwnedRules().get(0);
		if (rule != null && rule.getBody().length() > 0) {
			Set paths = new HashSet();
			for (StringTokenizer tokens = new StringTokenizer(rule.getBody()); tokens
					.hasMoreTokens();) {
				paths.add(tokens.nextToken());
			}
			return paths;
		}
		return null;
	}

	public Set getSuppressedExternalElementPaths() {
		if (suppressedExternalElementPaths == null) {
			suppressedExternalElementPaths = loadSuppressedElementPaths();
			if (suppressedExternalElementPaths == null) {
				suppressedExternalElementPaths = new HashSet();
			}
		}
		return suppressedExternalElementPaths;
	}

	/**
	 * @param process
	 * @param selection
	 * @return
	 */
	public boolean hasSuppressed(Collection selection) {
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof BreakdownElementWrapperItemProvider) {
				if (isSuppressed((BreakdownElementWrapperItemProvider) element)) {
					return true;
				}
			} else if (element instanceof BreakdownElement) {
				if (isSuppressed((BreakdownElement) element)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if the given selection has any element that is not suppressed yet
	 * in this suppression's process
	 * 
	 * @param selection
	 * @return
	 */
	public boolean canSuppress(Collection selection) {
		return canDo(selection, true);
	}

	/**
	 * Checks if the given selection has any element that is suppressed in this
	 * suppression's process
	 * 
	 * @param selection
	 * @return
	 */
	public boolean canReveal(Collection selection) {
		return canDo(selection, false);
	}

	/**
	 * Extracts items from the given selection that can be suppressed/revealed
	 * (depending on the <code>suppressed</code> parameter)
	 * 
	 * @param selection
	 * @param suppressed
	 * @return
	 */
	private Collection getApplicableItems(Collection selection,
			boolean suppressed) {
		ArrayList applicableItems = new ArrayList();
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof BreakdownElementWrapperItemProvider) {
				BreakdownElementWrapperItemProvider wrapper = (BreakdownElementWrapperItemProvider) element;
				if (wrapper.isReadOnly()) {
					if (isInSuppressedList(wrapper) != suppressed) {
						applicableItems.add(element);
					}
				} else {
					Object e = TngUtil.unwrap(wrapper);
					if (e instanceof MethodElement
							&& ((MethodElement) e).getSuppressed()
									.booleanValue() != suppressed) {
						applicableItems.add(element);
					}
				}
			} else if (element instanceof MethodElement) {
				if (((MethodElement) element).getSuppressed().booleanValue() != suppressed) {
					applicableItems.add(element);
				}
			}
		}
		return applicableItems;
	}

	/**
	 * Checks if the given element is valid to suppress/reveal upon
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isValid(Object element) {
		// don't show suppress/reveal for roledescriptor under team, work
		// product descriptor under deliverable work product,
		// and rolled-up item
		//
		BreakdownElement obj = null;
		if (element instanceof BreakdownElementWrapperItemProvider) {
			BreakdownElementWrapperItemProvider wrapper = (BreakdownElementWrapperItemProvider) element;
			if (wrapper.isRollupChild()) {
				return false;
			}
			obj = (BreakdownElement) TngUtil.unwrap(wrapper);
		} else if (element instanceof BreakdownElement)
			obj = (BreakdownElement) element;
		if (obj instanceof RoleDescriptor
				|| obj instanceof WorkProductDescriptor) {
			if (obj.getSuperActivities() == null
					|| obj.getSuperActivities() == null) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks if the given selection has any item that can be
	 * suppressed/revealed (depending on the <code>suppressed</code>
	 * parameter)
	 * 
	 * @param selection
	 * @param suppressed
	 * @return
	 */
	private boolean canDo(Collection selection, boolean suppressed) {
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (isValid(element)) {
				if (element instanceof BreakdownElementWrapperItemProvider) {
					BreakdownElementWrapperItemProvider wrapper = (BreakdownElementWrapperItemProvider) element;
					if (wrapper.isReadOnly()) {
						if (isInSuppressedList(wrapper) != suppressed) {
							return true;
						}
					} else {
						Object e = TngUtil.unwrap(wrapper);
						if (e instanceof MethodElement
								&& ((MethodElement) e).getSuppressed()
										.booleanValue() != suppressed) {
							return true;
						}
					}
				} else if (element instanceof MethodElement) {
					if (((MethodElement) element).getSuppressed()
							.booleanValue() != suppressed) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * A breakdown element is suppressed if itself or any of its parent activity
	 * is suppressed
	 * 
	 * @param e
	 * @return
	 */
	private boolean __isSuppressed(BreakdownElement e) {
		return getSuppressed(e, true, null) != null;
	}

	private static boolean isDirectlySuppressed(MethodElement e) {
		Boolean b = e.getSuppressed();
		if (b != null && b.booleanValue()) {
			return true;
		}
		if (e instanceof VariabilityElement) {
			VariabilityElement ve = (VariabilityElement) e;
			VariabilityType variabilityType = ve.getVariabilityType();
			if (variabilityType == VariabilityType.EXTENDS
					|| variabilityType == VariabilityType.CONTRIBUTES) {
				if (ve.getVariabilityBasedOnElement() != null) {
					return isDirectlySuppressed(ve
							.getVariabilityBasedOnElement());
				}
			}
		}

		return false;
	}

	/**
	 * Gets the suppressed breakdown element that made the given breakdown
	 * element become suppressed. This method will check the suppression state
	 * of the parent upto the specified <code>top</code> only if
	 * <code>checkParent</code> is <code>true</code>. It will not check the
	 * suppression state of <code>top</code>
	 * 
	 * @param e
	 * @return
	 */
	private static BreakdownElement getSuppressed(BreakdownElement e,
			boolean checkParent, Object top) {
		if (e.getSuppressed().booleanValue()) {
			return e;
		}

		if (checkParent) {
			for (BreakdownElement be = e; be != top
					&& be.getSuperActivities() != null;) {
				be = be.getSuperActivities();
				if (be.getSuppressed().booleanValue()) {
					return be;
				}
			}

			// special handling for nested artifact descriptors and deliverable
			// parts
			//
			if (e instanceof WorkProductDescriptor) {
				WorkProductDescriptor wpDesc = ((WorkProductDescriptor) e);
				WorkProduct wp = wpDesc.getWorkProduct();
				if (wp instanceof Artifact) {
					// look for parent artifact descriptor in the same activity
					// and check their suppression state
					//
					Activity act = UmaUtil.getParentActivity(e);
					if (act != null) {
						for (Iterator iter = act.getBreakdownElements()
								.iterator(); iter.hasNext();) {
							Object element = iter.next();
							if (element != e
									&& element instanceof WorkProductDescriptor) {
								WorkProductDescriptor wpd = ((WorkProductDescriptor) element);
								WorkProduct otherWp = wpd.getWorkProduct();
								if (otherWp instanceof Artifact
										&& UmaUtil.isContainedBy(wp, otherWp)
										&& wpd.getSuppressed().booleanValue()) {
									return wpd;
								}
							}
						}
					}
				}
				if (wpDesc != top && wpDesc.getSuperActivities() == null) {
					List list = AssociationHelper
							.getDeliverableDescriptors(wpDesc);
					if (list.size() == 1) {
						// this work product descriptor is a deliverable part of
						// a deliverable work product descriptor
						// check the suppression state of the deliverable work
						// product descriptor
						//
						return getSuppressed((BreakdownElement) list.get(0),
								checkParent, top);
					}
				}
			}
			// special handling for team profile own role descriptor
			//
			else if (e instanceof RoleDescriptor) {
				if (e.getSuperActivities() == null) {
					List list = AssociationHelper
							.getTeamProfiles((RoleDescriptor) e);
					if (list.size() == 1) {
						TeamProfile team = (TeamProfile) list.get(0);
						if (team != top && team != null) {
							return getSuppressed(team, checkParent, top);
						}
					}
				}
			}
			// special handling for sub team profile
			//
			else if (e instanceof TeamProfile) {
				if (e.getSuperActivities() == null) {
					TeamProfile superTeam = ((TeamProfile) e).getSuperTeam();
					if (superTeam != top && superTeam != null) {
						return getSuppressed(superTeam, checkParent, top);
					}
				}
			}
		}

		return null;
	}

	public boolean isInSuppressedList(
			BreakdownElementWrapperItemProvider wrapper) {
		if (suppressedExternalElementPaths == null) {
			return false;
		}

		// check if wrapper's path is in suppressedExternalElementPaths set
		//
		String path = getPath(wrapper);
		return suppressedExternalElementPaths.contains(path);
	}

	/**
	 * Gets the suppressed breakdown element that made the given wrapper become
	 * suppressed
	 * 
	 * @param wrapper
	 * @return
	 */
	private BreakdownElement getSuppressed(
			BreakdownElementWrapperItemProvider wrapper) {
		return getSuppressed(wrapper, true);
	}

	/**
	 * Gets the suppressed breakdown element that made the given wrapper become
	 * suppressed
	 * 
	 * @param wrapper
	 * @return
	 */
	private BreakdownElement getSuppressed(
			BreakdownElementWrapperItemProvider wrapper, boolean checkBase) {
		return getSuppressed(wrapper, checkBase, true,
				autoInheritIntermediateSuppressionState);
	}

	/**
	 * Gets the suppressed breakdown element that made the given wrapper become
	 * suppressed
	 * 
	 * @param wrapper
	 * @return
	 */
	private BreakdownElement getSuppressed(
			BreakdownElementWrapperItemProvider wrapper, boolean checkBase,
			boolean checkLocal, boolean inheritSuppressionState) {
		return getSuppressed(wrapper, checkBase, checkLocal,
				inheritSuppressionState, true, null);
	}

	private BreakdownElement getSuppressed(
			BreakdownElementWrapperItemProvider wrapper, boolean checkBase,
			boolean checkLocal, boolean inheritSuppressionState,
			boolean checkParent, Object top) {
		BreakdownElement e = (BreakdownElement) TngUtil.unwrap(wrapper);
		if (!wrapper.isReadOnly()) {
			// this is a wrapper of local element
			//
			return getSuppressed(e, checkParent, top);
		}

		// check if the base breakdown element is suppressed, directly or
		// indirectly, in its own process
		//
		if (checkBase) {
			BreakdownElement suppressed = getSuppressed(e, checkParent, top);
			if (suppressed != null) {
				return suppressed;
			}
		}

		Object parent = null;
		if (checkLocal) {
			// check if the wrapper is suppressed in this process
			//
			if (isInSuppressedList(wrapper)) {
				return e;
			}

			// check if the any local parent is suppressed
			//
			if (checkParent) {
				parent = wrapper.getParent(wrapper);
				if (parent instanceof BreakdownElement && parent != top) {
					BreakdownElement suppressed = getSuppressed(
							(BreakdownElement) parent, checkParent, top);
					if (suppressed != null) {
						return suppressed;
					}
				}
			}
		}

		// check if the breakdown element is suppressed in one of the immediate
		// base process
		//
		if (inheritSuppressionState) {
			Process proc = TngUtil.getOwningProcess(e);
			Activity inheritor = ProcessUtil.getInheritor(wrapper);
			BreakdownElement base = (BreakdownElement) inheritor
					.getVariabilityBasedOnElement();
			if (base != null) {
				Process immediateBaseProc = TngUtil.getOwningProcess(base);
				if (proc != immediateBaseProc) {
					// find the object in the breakdown structure of the
					// immediate
					// base process that represents the same breakdown element
					//
					Object object = inheritor.getVariabilityBasedOnElement();
					ArrayList objects = new ArrayList(ProcessUtil
							.getParentList(inheritor, wrapper));
					objects.add(e);
					for (Iterator iter = objects.iterator(); iter.hasNext();) {
						Object element = iter.next();
						ITreeItemContentProvider adapter = (ITreeItemContentProvider) wrapper
								.getAdapterFactory().adapt(object,
										ITreeItemContentProvider.class);
						find_child: for (Iterator iterator = adapter
								.getChildren(object).iterator(); iterator
								.hasNext();) {
							Object child = iterator.next();
							if (element == TngUtil.unwrap(child)) {
								object = child;
								break find_child;
							}
						}
					}
					if (object instanceof BreakdownElementWrapperItemProvider) {
						Suppression suppression = getSuppression(immediateBaseProc);
						BreakdownElement element = suppression.getSuppressed(
								(BreakdownElementWrapperItemProvider) object,
								false, true, inheritSuppressionState,
								checkParent, TngUtil.unwrap(object));
						if (element != null) {
							return element;
						}
					}
				}
			}
		}

		// check if the any inherited parent is suppressed
		//
		if (checkParent) {
			if (parent == null) {
				parent = wrapper.getParent(wrapper);
			}
			if (parent instanceof BreakdownElementWrapperItemProvider
					&& TngUtil.unwrap(parent) != top) {
				return getSuppressed(
						(BreakdownElementWrapperItemProvider) parent,
						checkBase, true, inheritSuppressionState, checkParent,
						top);
			}
		}

		return null;
	}

	/**
	 * Gets the cached suppression for the given process if one exists or
	 * creates new one
	 * 
	 * @param immediateBaseProc
	 * @return
	 */
	public static Suppression getSuppression(Process proc) {
		Suppression suppression = (Suppression) procToSuppressionMap.get(proc);
		if (suppression == null) {
			synchronized (procToSuppressionMap) {
				suppression = (Suppression) procToSuppressionMap.get(proc);
				if (suppression == null) {
					suppression = new Suppression(proc);
					procToSuppressionMap.put(proc, suppression);
				}
			}
		}
		return suppression;
	}

	public static synchronized void setSuppression(Suppression sup) {
		procToSuppressionMap.put(sup.getProcess(), sup);
	}
	
	private boolean __isSuppressed(BreakdownElementWrapperItemProvider wrapper) {
		return getSuppressed(wrapper) != null;
	}

	public void saveToModel() {
		if (suppressedExternalElementPaths == null) {
			return;
		}

		StringBuffer paths = new StringBuffer();
		for (Iterator iter = suppressedExternalElementPaths.iterator(); iter
				.hasNext();) {
			String path = (String) iter.next();
			if (isValid(path)) {
				paths.append(path).append(' ');
			}
		}

		Constraint rule = ConstraintManager.getConstraint(process,
				ConstraintManager.PROCESS_SUPPRESSION, true);
		// if(process.getOwnedRules().isEmpty()) {
		// rule = UmaFactory.eINSTANCE.createConstraint();
		// process.getOwnedRules().add(rule);
		// }
		// else {
		// rule = (Constraint) process.getOwnedRules().get(0);
		// }
		//		
		rule.setBody(paths.toString());
	}

	/**
	 * Checks if the given path is valid in process
	 * 
	 * @param path
	 * @return
	 */
	private boolean isValid(String path) {
		URI uri = URI.createURI(path);
		String type = uri.scheme();
		ConfigurableComposedAdapterFactory adapterFactory = null;
		if (WBS.equals(type)) {
			adapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
					.getWBS_ComposedAdapterFactory();
		} else if (TBS.equals(type)) {
			adapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
					.getOBS_ComposedAdapterFactory();
		} else if (WPBS.equals(type)) {
			adapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
					.getPBS_ComposedAdapterFactory();
		} else if (CBS.equals(type)) {
			adapterFactory = (ConfigurableComposedAdapterFactory) TngAdapterFactory.INSTANCE
					.getProcessComposedAdapterFactory();
		} else {
			return false;
		}

		// use process's default configuration to validate
		//
		MethodConfiguration currentConfig = null;
		IConfigurator configurator = null;
		IFilter filter = adapterFactory.getFilter();
		if (filter instanceof IConfigurator) {
			configurator = (IConfigurator) adapterFactory.getFilter();
			currentConfig = configurator.getMethodConfiguration();
			configurator.setMethodConfiguration(process.getDefaultContext());
		}
		try {
			String guid = uri.authority();
			if (!process.getGuid().equals(guid)) {
				return false;
			}
			Object object = process;
			ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory
					.adapt(object, ITreeItemContentProvider.class);
			for (int i = 0; i < uri.segmentCount(); i++) {
				guid = uri.segment(i);
				Iterator iter = adapter.getChildren(object).iterator();
				adapter = null;
				find_adapter: while (iter.hasNext()) {
					Object child = iter.next();
					object = TngUtil.unwrap(child);
					if (object instanceof MethodElement) {
						if (guid.equals(((MethodElement) object).getGuid())) {
							if (child instanceof ITreeItemContentProvider) {
								adapter = (ITreeItemContentProvider) child;
							} else {
								adapter = (ITreeItemContentProvider) adapterFactory
										.adapt(child,
												ITreeItemContentProvider.class);
							}
							break find_adapter;
						}
					} else {
						// must be an ItemProviderAdapter
						//
						ItemProviderAdapter itemProvider = (ItemProviderAdapter) object;
						MethodElement e = (MethodElement) itemProvider
								.getTarget();
						if (guid.equals(e.getGuid())) {
							adapter = (ITreeItemContentProvider) itemProvider;
							break find_adapter;
						}
					}
				}
				if (adapter == null) {
					return false;
				}
			}
			return true;
		} finally {
			// restore configuration
			//
			if (configurator != null) {
				configurator.setMethodConfiguration(currentConfig);
			}
		}
	}

	// private boolean reveal(List selection, Collection revealedDescriptors) {
	// boolean readOnlyElementAffected = false;
	// for (Iterator iter = selection.iterator(); iter.hasNext();) {
	// Object element = (Object) iter.next();
	// if (element instanceof BreakdownElementWrapperItemProvider) {
	// BreakdownElementWrapperItemProvider wrapper =
	// (BreakdownElementWrapperItemProvider) element;
	// MethodElement e = (MethodElement) TngUtil.unwrap(wrapper);
	// if (!wrapper.isReadOnly()) { // wrapper of local element
	// e.setSuppressed(Boolean.FALSE);
	// if(e instanceof Descriptor) {
	// revealedDescriptors.add(e);
	// }
	// } else { // was suppressed by this process
	//
	// // remove the path of suppressed element from the list
	// //
	// String path = getPath(wrapper);
	// getSuppressedExternalElementPaths().remove(path);
	// readOnlyElementAffected = true;
	// }
	// } else if (element instanceof MethodElement) {
	// ((MethodElement) element).setSuppressed(Boolean.FALSE);
	// if(element instanceof Descriptor) {
	// revealedDescriptors.add(element);
	// }
	// }
	// }
	// modified = true;
	// return readOnlyElementAffected;
	// }

	// /**
	// * @param selection
	// * @return true if this call revealed any read-only element
	// */
	// public boolean reveal(List selection) {
	// ArrayList revealedDescriptors = new ArrayList();
	// boolean ret = reveal(selection, revealedDescriptors);
	// List descriptorsToReveal = getOwnRelatedElements(revealedDescriptors,
	// false);
	// if(descriptorsToReveal != null) {
	// boolean ret2 = reveal(descriptorsToReveal, revealedDescriptors);
	// return ret || ret2;
	// }
	// return ret;
	// }

	/**
	 * @deprecated need to use {@link SuppressionCommand} instead
	 */
	public void reveal(List selection) {
		doSetSuppressed(selection, false);
	}

	public static String getViewType(
			BreakdownElementWrapperItemProvider wrapper) {
		// no need to store separate path for CBS, reuse WBS/TBS/WPBS paths
		//		
		Object e = TngUtil.unwrap(wrapper);
		if (e instanceof WorkBreakdownElement) {
			return WBS;
		} else if (e instanceof TeamProfile || e instanceof RoleDescriptor) {
			return TBS;
		} else if (e instanceof WorkProductDescriptor) {
			return WPBS;
		} else {
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Gets the process GUID in the given GUID path
	 * 
	 * @param guidPath
	 * @return
	 * @see #getPath(BreakdownElementWrapperItemProvider)
	 */
	public static String getProcessGUID(String guidPath) {
		int id = guidPath.indexOf("://"); //$NON-NLS-1$
		if (id != -1) {
			int beginIndex = id + 3;
			if (beginIndex < guidPath.length()) {
				int endIndex = guidPath.indexOf('/', beginIndex);
				if (endIndex != -1) {
					return guidPath.substring(beginIndex, endIndex);
				}
			}
		}
		return null;
	}

	/**
	 * @param wrapper
	 * @return
	 */
	public static String getPath(BreakdownElementWrapperItemProvider wrapper) {
		StringBuffer path = getPathWithoutViewType(wrapper);
		String viewType = getViewType(wrapper);
		path.insert(0, ":/").insert(0, viewType); //$NON-NLS-1$
		return path.toString();
	}
	
	public static StringBuffer getPathWithoutViewType(BreakdownElementWrapperItemProvider wrapper) {
		StringBuffer path = new StringBuffer(); 
		List parentList = ProcessUtil.getParentList(null, wrapper);
		if (!parentList.isEmpty()) {
			for (Iterator iter = parentList.iterator(); iter.hasNext();) {
				MethodElement e = (MethodElement) iter.next();
				// exclude TaskDescriptor and RoleDescriptor from the parent
				// path b/c those descriptors can be
				// parent only in CBS view
				if (!(e instanceof TaskDescriptor || e instanceof RoleDescriptor)) {
					path.append('/').append(e.getGuid());
				}
			}
		}
		MethodElement e = (MethodElement) TngUtil.unwrap(wrapper);
		path.append('/').append(e.getGuid());
		return path;
	}
	
	private static List getOwnRelatedElements(Collection changedDescriptors,
			boolean suppressed) {
		if (!changedDescriptors.isEmpty()) {
			ArrayList descriptorsToChange = new ArrayList();
			for (Iterator iter = new ArrayList(changedDescriptors).iterator(); iter
					.hasNext();) {
				Descriptor desc = (Descriptor) iter.next();
				Object[] relationships = null;
				if (desc instanceof TaskDescriptor) {
					relationships = RemoveUnusedDescriptorsCommand.TASK_DESCRIPTOR__RELATIONSHIPS;
				} else if (desc instanceof RoleDescriptor) {
					relationships = RemoveUnusedDescriptorsCommand.ROLE_DESCRIPTOR__RELATIONSHIPS;
				} else if (desc instanceof WorkProductDescriptor) {
					relationships = RemoveUnusedDescriptorsCommand.WORK_PRODUCT_DESCRIPTOR__RELATIONSHIPS;
				}
				if (relationships != null) {
					for (int i = 0; i < relationships.length; i++) {
						Object feature = relationships[i];
						boolean isMany;
						Object value;
						if (feature instanceof OppositeFeature) {
							OppositeFeature f = (OppositeFeature) feature;
							isMany = f.isMany();
							value = ((MultiResourceEObject) desc)
									.getOppositeFeatureValue(f);
						} else {
							EStructuralFeature f = (EStructuralFeature) feature;
							isMany = f.isMany();
							value = desc.eGet(f);
						}
						if (isMany) {
							for (Iterator iterator = ((Collection) value)
									.iterator(); iterator.hasNext();) {
								Descriptor ref = (Descriptor) iterator.next();
								if (ref.getSuppressed().booleanValue() != suppressed
										&& !ProcessUtil
												.checkDescriptorReferences(
														changedDescriptors, ref)) {
									descriptorsToChange.add(ref);
									changedDescriptors.add(ref);
								}
							}
						} else {
							Descriptor ref = (Descriptor) value;
							if (ref != null) {
								if (ref.getSuppressed().booleanValue() != suppressed
										&& !ProcessUtil.checkDescriptorReferences(
												changedDescriptors, ref)) {
									descriptorsToChange.add(ref);
									changedDescriptors.add(ref);
								}
							}
						}
					}
				}
			}
			return descriptorsToChange;
		}
		return null;
	}

	/**
	 * Suppresses or reveals the given selection
	 * 
	 * @param selection
	 * @param suppressed
	 * @return {@link Result} object that contains the result of this call
	 */
	private Result setSuppressed(List selection, boolean suppressed) {
		Result result = doSetSuppressed(selection, suppressed);

		// if descriptors had been suppressed, related elements that are not
		// used anywhere else also need to be suppressed (just like delete)
		//	
		if (!result.descriptors.isEmpty()) {
			List descriptorsToSuppress = getOwnRelatedElements(
					result.descriptors, suppressed);
			if (descriptorsToSuppress != null) {
				Result result2 = doSetSuppressed(descriptorsToSuppress,
						suppressed);

				// merge results
				//
				result.elements.addAll(result2.elements);
				result.descriptors.addAll(result2.descriptors);
				result.paths.addAll(result2.paths);
			}
		}

		return result;
	}

	/**
	 * @deprecated need to use {@link SuppressionCommand} instead
	 */
	public void suppress(List selection) {
		doSetSuppressed(selection, true);
	}

	// public boolean suppress(List selection) {
	// ArrayList suppressedDescriptors = new ArrayList();
	// boolean ret = suppress(selection, suppressedDescriptors);
	//		
	// // if descriptors had been suppressed, related elements that are not used
	// anywhere else also need to be suppressed (just like delete)
	// //
	// List descriptorsToSuppress = getOwnRelatedElements(suppressedDescriptors,
	// true);
	// if(descriptorsToSuppress != null) {
	// ArrayList out = new ArrayList();
	// boolean ret2 = suppress(descriptorsToSuppress, out);
	// if(out.size() != descriptorsToSuppress.size()) {
	// LibraryEditPlugin.getDefault().getLogger().logError("Suppression.suppress(List)
	// is buggy."); //$NON-NLS-1$
	// }
	// return ret || ret2;
	// }
	// return ret;
	// }

	private static class Result {
		/** Elements that have been suppressed or revealed */
		Collection elements;

		/** Descriptors that have been suppressed or revealed */
		Collection descriptors;

		/**
		 * Paths that have been added to or removed from
		 * <code>suppressedExternalElementPaths</code>
		 */
		Collection paths;

		Result() {
			elements = new ArrayList();
			descriptors = new ArrayList();
			paths = new ArrayList();
		}

		boolean isEmpty() {
			return elements.isEmpty() && paths.isEmpty();
		}

		void clear() {
			elements.clear();
			descriptors.clear();
			paths.clear();
		}
	}

	/**
	 * Suppresses or reveals the given selection depending on the value of
	 * <code>suppressed</code>
	 * 
	 * @param selection
	 * @param suppressed
	 * @return
	 */
	private Result doSetSuppressed(List selection, boolean suppressed) {
		Result result = new Result();
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			if (element instanceof BreakdownElementWrapperItemProvider) {
				BreakdownElementWrapperItemProvider wrapper = (BreakdownElementWrapperItemProvider) element;
				// if (!isSuppressed(wrapper)) {
				BreakdownElement e = (BreakdownElement) TngUtil.unwrap(wrapper);
				if (!wrapper.isReadOnly()) {
					// wrapper of local element
					//
					if (e.getSuppressed().booleanValue() != suppressed) {
						e.setSuppressed(Boolean.valueOf(suppressed));
						result.elements.add(e);
						if (e instanceof Descriptor) {
							result.descriptors.add(e);
						}
					}
				} else {
					// add the paths of suppressed element to the map
					//
					String path = getPath(wrapper);
					boolean b;
					if (suppressed) {
						b = getSuppressedExternalElementPaths().add(path);
					} else {
						b = getSuppressedExternalElementPaths().remove(path);
					}
					if (b) {
						result.paths.add(path);
					}
					// }
				}
			} else if (element instanceof MethodElement) {
				MethodElement e = ((MethodElement) element);
				if (e.getSuppressed().booleanValue() != suppressed) {
					e.setSuppressed(Boolean.valueOf(suppressed));
					result.elements.add(e);
					if (e instanceof Descriptor) {
						result.descriptors.add(e);
					}
				}
			}
		}
		modified = !result.isEmpty();
		return result;
	}

	// private boolean suppress(List selection, Collection
	// suppressedDescriptors) {
	// boolean readOnlyElementSuppressed = false;
	// for (Iterator iter = selection.iterator(); iter.hasNext();) {
	// Object element = (Object) iter.next();
	// if (element instanceof BreakdownElementWrapperItemProvider) {
	// BreakdownElementWrapperItemProvider wrapper =
	// (BreakdownElementWrapperItemProvider) element;
	// if (!isSuppressed(wrapper)) {
	// BreakdownElement e = (BreakdownElement) TngUtil
	// .unwrap(wrapper);
	// if (!wrapper.isReadOnly()) {
	// // wrapper of local element
	// //
	// e.setSuppressed(Boolean.TRUE);
	// if(e instanceof Descriptor) {
	// suppressedDescriptors.add(e);
	// }
	// } else {
	// // add the paths of suppressed element to the map
	// //
	// String path = getPath(wrapper);
	// getSuppressedExternalElementPaths().add(path);
	// readOnlyElementSuppressed = true;
	// }
	// }
	// } else if (element instanceof MethodElement) {
	// ((MethodElement) element).setSuppressed(Boolean.TRUE);
	// if(element instanceof Descriptor) {
	// suppressedDescriptors.add(element);
	// }
	// }
	// }
	// modified = true;
	// return readOnlyElementSuppressed;
	// }

	/**
	 * @param selection
	 * @return
	 */
	public boolean hasUnsuppressed(List selection) {
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof BreakdownElementWrapperItemProvider) {
				if (!isSuppressed((BreakdownElementWrapperItemProvider) element)) {
					return true;
				}
			} else if (element instanceof BreakdownElement) {
				if (!isSuppressed((BreakdownElement) element)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return Returns the modified.
	 */
	public boolean isSaveNeeded() {
		return modified;
	}

	public void saveIsDone() {
		modified = false;
	}
	
	protected boolean __isSuppressed(ComposedBreakdownElementWrapperItemProvider composedWrapper) {
		for (Iterator iter = composedWrapper.getValues().iterator(); iter.hasNext();) {
			Object element = iter.next();
			if(!isSuppressed(element)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * check if the element, or the item provider or adaptor associated with the
	 * element is suppressed.
	 * 
	 * @param e
	 *            MethodElement or an associated wrapper, adaptor, or item
	 *            provider, etc.
	 * @return boolean
	 */
	public boolean isSuppressed(Object e) {
		// long start = System.currentTimeMillis();

		try {

			if (e == null) {
				return true;
			}
			
			ComposedBreakdownElementWrapperItemProvider composedWrapper = ProcessUtil.getComposedWrapper(e);
			if(composedWrapper != null) {
				return __isSuppressed(composedWrapper);
			}

			if (internalUnsuppressedElements.contains(e)) {
				return false;
			}

			if (e instanceof BreakdownElementWrapperItemProvider) {
				return __isSuppressed((BreakdownElementWrapperItemProvider) e);
			} else if (e instanceof BreakdownElement) {
				return __isSuppressed((BreakdownElement) e);
			} else {
				Object targetObj = null;
				if (e instanceof WrapperItemProvider) {
					targetObj = ((WrapperItemProvider) e).getValue();
				} else if (e instanceof ItemProviderAdapter) {
					targetObj = ((ItemProviderAdapter) e).getTarget();
				}

				if (targetObj != null && targetObj != e) {
					return isSuppressed(targetObj);
				}
			}

			return false;

		} finally {
			// long time = (System.currentTimeMillis() - start);
			// if(time > 1000) {
			// BreakdownElement be = (BreakdownElement)TngUtil.unwrap(e);
			// String msg = "Suppression.isSuppressed(): time taken (ms) " +
			// time +
			// "\n process: " + (e instanceof
			// BreakdownElementWrapperItemProvider ?
			// ((BreakdownElementWrapperItemProvider)e).getTopItem() :
			// TngUtil.getOwningProcess(be)) +
			// "\n element: " + ProcessUtil.getLabelWithPath(be);
			//				
			// System.out.println(msg);
			// LibraryEditPlugin.getDefault().getLogger().logInfo(msg);
			// }
		}
	}

	/**
	 * Updates the suppression state of the given <code>wrapper</code> from
	 * its base.
	 * 
	 * @param wrapper
	 * @return <code>true</code> if this call modified the suppression state
	 *         of the given wrapper, <code>false</code> otherwise
	 */
	public boolean updateSuppressionFromBase(
			BreakdownElementWrapperItemProvider wrapper) {
		String path = getPath(wrapper);
		boolean ret;
		BreakdownElement e = getSuppressed(wrapper, true, false, true, false,
				null);
		if (e != null) {
			ret = getSuppressedExternalElementPaths().add(path);
		} else {
			ret = getSuppressedExternalElementPaths().remove(path);
		}
		if (ret) {
			modified = true;
		}
		return ret;
	}

	/**
	 * Excludes the descriptors with the same linked element from the check
	 * 
	 * @param selectionToReveal
	 * @return
	 */
	public String checkDuplicateNameAfterReveal(
			Collection selectionToRevealOrSuppress,
			AdapterFactory adapterFactory) {
		Collection elementsToReveal = getApplicableItems(
				selectionToRevealOrSuppress, false);
		for (Iterator iter = elementsToReveal.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof BreakdownElement) {
				BreakdownElement be = (BreakdownElement) element;
				String msg = ProcessUtil.checkBreakdownElementName(
						adapterFactory, be, be.getName(), this);
				if (msg != null) {
					return LibraryEditResources.Suppression_nameDuplication; 
				}
				msg = ProcessUtil.checkBreakdownElementPresentationName(
						adapterFactory, be, be.getPresentationName(), this);
				if (msg != null) {
					return LibraryEditResources.Suppression_presentationNameDuplication; 
				}
			} else {
				Object unwrapped = TngUtil.unwrap(element);
				if (unwrapped instanceof BreakdownElement) {
					ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory
							.adapt(element, ITreeItemContentProvider.class);
					Object parent = itemProvider.getParent(element);
					if (parent instanceof BreakdownElement) {
						itemProvider = (ITreeItemContentProvider) adapterFactory
								.adapt(parent, ITreeItemContentProvider.class);
						Collection siblings = itemProvider.getChildren(parent);
						Object linkedElement = null;
						if (element instanceof Descriptor) {
							linkedElement = ProcessUtil
									.getAssociatedElement((Descriptor) element);
						}
						try {
							internalUnsuppressedElements.add(element);
							nameCheckLoop: for (Iterator iterator = siblings
									.iterator(); iterator.hasNext();) {
								Object sibling = iterator.next();
								if (sibling instanceof BreakdownElement) {
									BreakdownElement be = (BreakdownElement) sibling;
									// skip checking on suppressed element
									//
									if (be.getSuppressed().booleanValue()) {
										continue nameCheckLoop;
									}
									if (linkedElement != null
											&& sibling instanceof Descriptor) {
										// skip checking on descriptors with the
										// same linked element
										//
										Object otherLinkedElement = ProcessUtil
												.getAssociatedElement((Descriptor) element);
										if (otherLinkedElement == linkedElement) {
											continue nameCheckLoop;
										}
									}
									String msg = ProcessUtil
											.checkBreakdownElementName(
													adapterFactory, be, be
															.getName(), this);
									if (msg != null) {
										return LibraryEditResources.Suppression_nameDuplication; 
									}
									msg = ProcessUtil
											.checkBreakdownElementPresentationName(
													adapterFactory, be,
													be.getPresentationName(),
													this);
									if (msg != null) {
										return LibraryEditResources.Suppression_presentationNameDuplication; 
									}
								}
							}
						} finally {
							internalUnsuppressedElements.remove(element);
						}
					}
				}
			}
		}
		return null;
	}

	public Object getObjectByPath(String[] guidPath,
			AdapterFactory adapterFactory) {
		if (guidPath.length == 0 || adapterFactory == null
				|| !guidPath[0].equals(process.getGuid())) {
			return null;
		}

		Object object = process;
		int len = guidPath.length;
		for (int i = 1; i < len; i++) {
			ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory
					.adapt(object, ITreeItemContentProvider.class);
			boolean found = false;

			// if current object is an activity, make sure it is rolled down
			// before looking into its children
			//
			IBSItemProvider rolledUpAdapter = null;
			Iterator iter = null;
			try {
				if (TngUtil.unwrap(object) instanceof Activity) {
					if (adapter instanceof BSActivityItemProvider) {
						BSActivityItemProvider activityItemProvider = (BSActivityItemProvider) adapter;
						if (activityItemProvider.isRolledUp()) {
							activityItemProvider.basicSetRolledUp(false);
							rolledUpAdapter = activityItemProvider;
						}
					} else if (adapter instanceof IBSItemProvider) {
						IBSItemProvider itemProvider = (IBSItemProvider) adapter;
						if (itemProvider.isRolledUp()) {
							itemProvider.setRolledUp(false);
							rolledUpAdapter = itemProvider;
						}
					}
				}
				iter = adapter.getChildren(object).iterator();
			} finally {
				if (rolledUpAdapter != null) {
					rolledUpAdapter.setRolledUp(true);
				}
			}

			find_child: while (iter.hasNext()) {
				Object child = iter.next();
				Object e = TngUtil.unwrap(child);
				if (e instanceof MethodElement
						&& ((MethodElement) e).getGuid().equals(guidPath[i])) {
					found = true;
					object = child;
					break find_child;
				}
			}

			if (!found) {
				return null;
			}
		}
		return object;
	}

	/**
	 * Checks if the breakdown element identified by the given
	 * <code>guidPath</code> is suppressed in the process of this Suppression
	 * object
	 * 
	 * @param guidPath
	 * @return
	 * @exception IllegalArgumentException
	 *                if the object with the given path could not be found using
	 *                the given adapter factory
	 */
	public boolean isSuppressed(String[] guidPath, AdapterFactory adapterFactory)
			throws IllegalArgumentException {
		Object object = getObjectByPath(guidPath, adapterFactory);
		if (object == null) {
			throw new IllegalArgumentException(
					"Could not find object with path '" + guidPath + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return isSuppressed(object);
	}

	/**
	 * Gets the set of suppressed item of the given processes.
	 * 
	 * @param proc
	 * @param adapterFactories
	 *            the adapter factories for WBS, TBS, WPBS, and CBS views
	 * @return
	 */
	public static Set getSuppressedItems(Process proc,
			AdapterFactory[] adapterFactories) {
		Set suppressedItems = new HashSet();
		Suppression suppression = new Suppression(proc);
		for (int i = 0; i < adapterFactories.length; i++) {
			for (Iterator iter = new AdapterFactoryTreeIterator(
					adapterFactories[i], proc); iter.hasNext();) {
				Object item = iter.next();
				if (suppression.isSuppressed(item)) {
					suppressedItems.add(item);
				}
			}
		}
		return suppressedItems;
	}

	public static class SuppressionCommand extends AbstractCommand implements
			IResourceAwareCommand {
		protected Collection modifiedResources;

		protected List collection;

		protected Result result;

		protected boolean suppressed;

		protected Suppression suppression;

		public SuppressionCommand(Suppression suppression, List selection,
				boolean suppressed) {
			this.suppression = suppression;
			collection = selection;
			this.suppressed = suppressed;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.epf.library.edit.command.IResourceAwareCommand#getModifiedResources()
		 */
		public Collection getModifiedResources() {
			if (modifiedResources == null) {
				modifiedResources = Collections.singletonList(suppression
						.getProcess().eResource());
			}
			return modifiedResources;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.command.Command#execute()
		 */
		public void execute() {
			result = suppression.setSuppressed(collection, suppressed);
			didExecute();
		}

		/**
		 * 
		 */
		protected void didExecute() {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.command.Command#redo()
		 */
		public void redo() {
			execute();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
		 */
		public void undo() {
			if (result != null && !result.isEmpty()) {
				if (!result.elements.isEmpty()) {
					for (Iterator iter = result.elements.iterator(); iter
							.hasNext();) {
						MethodElement e = (MethodElement) iter.next();
						e.setSuppressed(Boolean.valueOf(!suppressed));
					}
				}
				if (!result.paths.isEmpty()) {
					for (Iterator iter = result.paths.iterator(); iter
							.hasNext();) {
						Object path = iter.next();
						if (suppressed) {
							suppression.getSuppressedExternalElementPaths()
									.remove(path);
						} else {
							suppression.getSuppressedExternalElementPaths()
									.add(path);
						}
					}
				}
				didUndo();
				result.clear();
			}
		}

		/**
		 * 
		 */
		protected void didUndo() {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
		 */
		protected boolean prepare() {
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.command.AbstractCommand#getResult()
		 */
		public Collection getResult() {
			if (!result.isEmpty()) {
				return collection;
			}
			return Collections.EMPTY_LIST;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
		 */
		public Collection getAffectedObjects() {
			return collection;
		}

		public boolean isReadOnlyElementAffected() {
			return !result.paths.isEmpty();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.command.AbstractCommand#dispose()
		 */
		public void dispose() {
			if (result != null) {
				result.clear();
			}

			super.dispose();
		}
	}

	/**
	 * @param obj
	 * @return physical state of the processElement
	 */
	public boolean isItselfSuppressed(Object obj){
		if(obj instanceof ProcessElement){
			return ((ProcessElement)obj).getSuppressed();
		}
		
		if(obj instanceof BreakdownElementWrapperItemProvider && ProcessUtil.isInherited(obj)){
			return isInSuppressedList((BreakdownElementWrapperItemProvider)obj);
		}
		
		if(obj instanceof BreakdownElementWrapperItemProvider && !ProcessUtil.isInherited(obj)){
			Object object = TngUtil.unwrap(obj);
			return ((MethodElement)object).getSuppressed();
		}
				
		return false;
	}
	
	public boolean isSuppressed(Object obj, EStructuralFeature feature) {
		return isSuppressed(obj);
	}

}
