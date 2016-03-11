/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.authoring.ui.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.providers.ConfigPackageContentProvider;
import org.eclipse.epf.library.configuration.ConfigurationData;
import org.eclipse.epf.library.edit.navigator.ConfigContentPackageItemProvider.LeafElementsItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

/**
 * ContainerCheckedTreeViewer class that supports MethodElements.
 * Specifically, has changes to support FeatureValueWrapperItemProviders.
 * 
 * Also, expands the tree for checked items when they are set via
 * setCheckedElements(..) iff expandWhenChecking is true.
 * @author Jeff Hardy
 * @author Phong Nguyen Le
 */
public class MethodContainerCheckedTreeViewer extends
		ContainerCheckedTreeViewer {
	
	protected boolean expandWhenChecking = false;
	protected boolean initializingTreeCheckState = false;
	private ConfigurationData configData;
	private boolean disableHandleLeafCrayChecks = false;

	protected ListenerList childrenCheckStateListeners = new ListenerList();

    /**
     * Constructor for MethodContainerCheckedTreeViewer.
     * @see CheckboxTreeViewer#CheckboxTreeViewer(Composite)
     */
    public MethodContainerCheckedTreeViewer(Composite parent) {
        super(parent);
        initViewer();
    }

    /**
     * Constructor for MethodContainerCheckedTreeViewer.
     * @see CheckboxTreeViewer#CheckboxTreeViewer(Composite,int)
     */
    public MethodContainerCheckedTreeViewer(Composite parent, int style) {
        super(parent, style);
        initViewer();
    }

    /**
     * Constructor for MethodContainerCheckedTreeViewer.
     * @see CheckboxTreeViewer#CheckboxTreeViewer(Tree)
     */
    public MethodContainerCheckedTreeViewer(Tree tree) {
        super(tree);
        initViewer();
    }
    
    protected void initViewer() {
        setUseHashlookup(true);
        addCheckStateListener(new ICheckStateListener() {
            public void checkStateChanged(CheckStateChangedEvent event) {
				updateWrappers(event.getElement());
				doCheckStateChanged(event.getElement());
//	not ready to call the following line yet				
//				handleLeafCrayChecks(element);	
			}
        });
        addTreeListener(new ITreeViewerListener() {
            public void treeCollapsed(TreeExpansionEvent event) {
            }

            public void treeExpanded(TreeExpansionEvent event) {
                Widget item = findItem(event.getElement());
                if (item instanceof TreeItem) {
                    initializeItem((TreeItem) item);
                }
            }
        });
    }
    
    /**
     * The item has expanded. Updates the checked state of its children. 
     */
    protected void initializeItem(TreeItem item) {
        if (item.getChecked() && !item.getGrayed()) {
            updateChildrenItems(item);
        }
    }
    
    /**
     * Updates the check state of all created children
     */
    protected void updateChildrenItems(TreeItem parent) {
        Item[] children = getChildren(parent);
        boolean state = parent.getChecked();
        for (int i = 0; i < children.length; i++) {
            TreeItem curr = (TreeItem) children[i];
            if (curr.getData() != null
                    && ((curr.getChecked() != state) || curr.getGrayed())) {
                curr.setChecked(state);
                curr.setGrayed(false);
                notifyChildrenCheckStateListeners(new CheckStateChangedEvent(this, curr.getData(),
                		state));                
                updateChildrenItems(curr);
            }
        }
    }
    
    @Override
    protected void doCheckStateChanged(Object element) {
    	if (initializingTreeCheckState) {
    		// when initializing, don't update children:
    		// parent may have been gray-checked and we don't
    		// want to white-check all children
    		// children will be checked because setCheckedElements(..)
    		// typically receives list of all checked elements
			Widget item = findItem(element);
			if (item instanceof TreeItem) {
				TreeItem treeItem = (TreeItem) item;
				treeItem.setGrayed(false);
				updateParentItems(treeItem.getParentItem());
			}
		} else {
			super.doCheckStateChanged(element);		
		}
    }
    
    private void handleLeafCrayChecks(Object element) {
    	if (getConfigData() == null || disableHandleLeafCrayChecks) {
    		return;
    	}
        Widget item = findItem(element);
        TreeItem treeItem = item instanceof TreeItem ? (TreeItem) item : null;
        if (treeItem == null) {
        	return;
        }
        
        Set<LeafElementsItemProvider> uncheckedLeafs = new HashSet<LeafElementsItemProvider>();        	
        collectUncheckedLeafs(treeItem, uncheckedLeafs);
        handleLeafCrayChecks(uncheckedLeafs);
    }
    
    private void collectUncheckedLeafs(TreeItem treeItem, Set<LeafElementsItemProvider> uncheckedLeafs) {
        if (treeItem.getData() instanceof LeafElementsItemProvider) {
        	if (! treeItem.getChecked()) {
        		uncheckedLeafs.add((LeafElementsItemProvider) treeItem.getData());
        	}
        	return;
        }
        Item[] children = getChildren(treeItem);
        for (int i = 0; i < children.length; i++) {
            TreeItem curr = (TreeItem) children[i];
            collectUncheckedLeafs(curr, uncheckedLeafs);
        }
    }
    
	public void handleLeafCrayChecks(
			Collection<LeafElementsItemProvider> uncheckedLeafs) {
		if (uncheckedLeafs == null || uncheckedLeafs.isEmpty()) {
			return;
		}
		Set<TreeItem> grayCheckedSet = new HashSet<TreeItem>();

		for (LeafElementsItemProvider leaf : uncheckedLeafs) {
			MethodPackage leafParent = leaf.getParentPackage();
			if (!getConfigData().hasAddedElements(leafParent)) {
				continue;
			}

			Widget item = findItem(leaf);
			if (item instanceof TreeItem) {
				TreeItem treeItem = (TreeItem) item;
				while (treeItem != null) {
					if (!grayCheckedSet.contains(treeItem)) {
						treeItem.setChecked(true);
						treeItem.setGrayed(true);
						grayCheckedSet.add(treeItem);
					}
					treeItem = treeItem.getParentItem();
				}
			}
		}
	}   
        
    public void updateParents(Object element) {
        Widget item = findItem(element);
        if (item instanceof TreeItem) {
            TreeItem treeItem = (TreeItem) item;
            updateParentItems(treeItem.getParentItem());
        }
    }

    /**
     * Updates the check / gray state of all parent items
     */
    protected void updateParentItems(TreeItem item) {
        if (item != null) {
            Item[] children = getChildren(item);
            boolean containsChecked = false;
            boolean containsUnchecked = false;
            for (int i = 0; i < children.length; i++) {
                TreeItem curr = (TreeItem) children[i];
                containsChecked |= curr.getChecked();
                containsUnchecked |= (!curr.getChecked() || curr.getGrayed());
            }
            item.setChecked(containsChecked);
            item.setGrayed(containsChecked && containsUnchecked);
            if (expandWhenChecking && item.getChecked()) {
            	item.setExpanded(true);
            }
            updateParentItems(item.getParentItem());
        }
    }
    
    private void updateWrappers(Object object) {
    	boolean state = getChecked(object);
    	Object element = TngUtil.unwrap(object);
    	if(object != element) {
    		setChecked(element, state);
    	}
		AdapterFactory adapterFactory = null;
		if (getContentProvider() instanceof AdapterFactoryContentProvider) {
			adapterFactory = ((AdapterFactoryContentProvider) getContentProvider())
					.getAdapterFactory();
		}
		if (adapterFactory != null) {
			Collection<?> wrappers = TngUtil.getWrappers(adapterFactory,
					element);
			if (!wrappers.isEmpty()) {
				for (Object wrapper : wrappers) {
					setChecked(wrapper, state);
				}
			}
		}
	}
    
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.CheckboxTreeViewer#setCheckedElements(java.lang.Object[])
	 */
    public void setCheckedElements(Object[] elements) {
    	// fix elements list to use EPF's wrappers
    	List<Object> treeElements = new ArrayList<Object>();
    	AdapterFactory adapterFactory = null;
    	if (getContentProvider() instanceof AdapterFactoryContentProvider) {
    		adapterFactory = ((AdapterFactoryContentProvider)getContentProvider()).getAdapterFactory();
    	}
		for (int i = 0; i < elements.length; i++) {
			Object element = elements[i];
			if (adapterFactory != null) {
				treeElements.addAll(TngUtil.getWrappers(adapterFactory, element));
			}
			if(!(element instanceof CustomCategory)) {
				treeElements.add(element);
			}
		}
    	initializingTreeCheckState = true;
        super.setCheckedElements(treeElements.toArray());
    	initializingTreeCheckState = false;
    }
    
    /**
     * Returns a list of top-most checked elements in this viewer's tree, 
     * including currently hidden ones that are marked as
     * checked but are under a collapsed ancestor.
     *
     * Only returns white-checked items, not grayed items
     *
     * @return the array of checked elements
     *
     * @see #setCheckedElements
     */
    public Object[] getTopCheckedElements() {
        ArrayList v = new ArrayList();
        Control tree = getControl();
        internalCollectTopChecked(v, tree);
        return v.toArray();
    }
    
    
    /**
     * Gathers the top-most checked states of the given widget and its
     * descendents, following a pre-order traversal of the tree.
     *
     * @param result a writable list of elements (element type: <code>Object</code>)
     * @param widget the widget
     */
    private void internalCollectTopChecked(List result, Widget widget) {
        Item[] items = getChildren(widget);
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (item instanceof TreeItem && ((TreeItem) item).getChecked() && !((TreeItem) item).getGrayed()) {
                Object data = item.getData();
                if (data != null) {
					result.add(data);
				}
            }
            if (item instanceof TreeItem && (!((TreeItem) item).getChecked() || ((TreeItem) item).getGrayed())) {
            	internalCollectTopChecked(result, item);
            }
        }
    }


	public boolean isExpandWhenChecking() {
		return expandWhenChecking;
	}

	public void setExpandWhenChecking(boolean expandWhenChecking) {
		this.expandWhenChecking = expandWhenChecking;
	}
	
	/**
	 * notified when children checkstate is changed (by checking a parent)
	 * @param listener
	 */
	public void addChildrenCheckStateListener(ICheckStateListener listener) {
		childrenCheckStateListeners.add(listener);
	}
	
	public void removeChildrenCheckStateListener(ICheckStateListener listener) {
		childrenCheckStateListeners.remove(listener);
	}
	
	protected void notifyChildrenCheckStateListeners(final CheckStateChangedEvent event) {
		for (final Object listener : childrenCheckStateListeners.getListeners()) {
            SafeRunner.run(new SafeRunnable() {
                public void run() {
                	((ICheckStateListener)listener).checkStateChanged(event);
                }
            });
		}
	}
		
    public Set<MethodPackage> getElementsUnslectedPkgs(Collection<MethodPackage> pkgsInConfig, ConfigPackageContentProvider provider) {
    	Set<MethodPackage> elementsUnslectedPkgs = new HashSet<MethodPackage>();
    	for (MethodPackage pkg : pkgsInConfig) {
    		if (pkg instanceof ContentPackage) {
    			Object leaf = provider.getLeafElementsNode(pkg);
            Widget item = findItem(leaf);            
				if (item instanceof TreeItem) {
					TreeItem treeItem = (TreeItem) item;
					if (! treeItem.getChecked() || treeItem.getGrayed()) {
						elementsUnslectedPkgs.add((ContentPackage) pkg);
					}
				}
    		}
        }
        return elementsUnslectedPkgs;
    }
    
    public void debugDump(Collection items, String msg) {
    	System.out.println("LD> msg : " + msg);						//$NON-NLS-1$	
    	for (Object item : items) {
    		System.out.println("LD> item : " + item);				//$NON-NLS-1$	
    		System.out.println("LD> found: " + findItem(item));		//$NON-NLS-1$	
    	}
		System.out.println("");	 //$NON-NLS-1$
    }
    
	public ConfigurationData getConfigData() {
		return configData;
	}

	public void setConfigData(ConfigurationData configData) {
		this.configData = configData;
	}

	@Override
    public boolean setChecked(Object element, boolean state) {
		try {
			disableHandleLeafCrayChecks = true;
			return super.setChecked(element, state);
		} finally {
			disableHandleLeafCrayChecks = false;
		}
    }
	
	public void debugTree() {
		Object root = getRoot();
		Tree tree = (Tree) this.findItem(root);
		debugItem(tree.getItem(0), "LD>"); //$NON-NLS-1$
	}

	private void debugItem(Object objItem, String indent) {
		if (objItem instanceof TreeItem) {
			TreeItem item = (TreeItem) objItem;
			System.out.println(indent + "item : " + item + ", hash: " //$NON-NLS-1$ //$NON-NLS-2$
					+ item.hashCode());
			System.out.println(indent + "data : " + item.getData()); //$NON-NLS-1$
			System.out.println(""); //$NON-NLS-1$
			for (TreeItem cItem : item.getItems()) {
				debugItem(cItem, indent + "     "); //$NON-NLS-1$
			}
		} else {
			System.out.println(indent + "objItem : " + objItem); //$NON-NLS-1$
			System.out.println(""); //$NON-NLS-1$
		}

	} 
	
}
