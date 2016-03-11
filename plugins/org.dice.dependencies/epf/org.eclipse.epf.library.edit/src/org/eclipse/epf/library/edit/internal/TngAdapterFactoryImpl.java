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
package org.eclipse.epf.library.edit.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.common.preferences.IPropertyChangeEventWrapper;
import org.eclipse.epf.common.preferences.IPropertyChangeListenerWrapper;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.configuration.PracticeItemProvider;
import org.eclipse.epf.library.edit.configuration.PracticeSubgroupItemProvider;
import org.eclipse.epf.library.edit.process.OBSItemProviderAdapterFactory;
import org.eclipse.epf.library.edit.process.PBSItemProviderAdapterFactory;
import org.eclipse.epf.library.edit.process.WBSItemProviderAdapterFactory;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.ExposedAdapterFactory;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.osgi.framework.Bundle;

/**
 * The default method library adapter factory implementation.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class TngAdapterFactoryImpl implements TngAdapterFactory {
	public static final TngAdapterFactory createAdapterFactoryProvider() {
		Object provider = ExtensionHelper.create(TngAdapterFactory.class, null);
		return provider instanceof TngAdapterFactory ? (TngAdapterFactory) provider : new TngAdapterFactoryImpl();
//		return new TngAdapterFactoryImpl();
	}
	
	private static final String WBS_ADAPTER_FACTORY_ID = "wbs"; //$NON-NLS-1$
	private static final String LIBRARY_ADAPTER_FACTORY_ID = "library"; //$NON-NLS-1$
	private static final String CONFIGURATION_ADAPTER_FACTORY_ID = "configuration"; //$NON-NLS-1$

	private ExposedAdapterFactory wbsAdapterFactory = null;

	private ExposedAdapterFactory obsAdapterFactory = null;

	private ExposedAdapterFactory pbsAdapterFactory = null;

	private AdapterFactory[] wbsAdapterFactories = null;

	private AdapterFactory[] obsAdapterFactories = null;

	private AdapterFactory[] pbsAdapterFactories = null;

	protected AdapterFactory[] procAdapterFactories = null;

	private ExposedAdapterFactory navigatorAdapterFactory;

	protected ExposedAdapterFactory configurationAdapterFactory;

	private ExposedAdapterFactory configProcessViewAdapterFactory;

	private ExposedAdapterFactory itemsFilterAdapterFactory;

	private ExposedAdapterFactory pbsFilterAdapaterFactory;

	private ExposedAdapterFactory obsFilterAdapaterFactory;

	protected ExposedAdapterFactory procAdapterFactory;

	/**
	 * 
	 */
	public TngAdapterFactoryImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.TngAdapterFactory#getWBS_AdapterFactory()
	 */
	public ComposedAdapterFactory getWBS_ComposedAdapterFactory() {
		if (wbsAdapterFactory == null) {
			synchronized (this) {
				if (wbsAdapterFactory == null) {
					wbsAdapterFactory = new ExposedAdapterFactory(
							getWBS_AdapterFactories());
				}
			}
		}
		return wbsAdapterFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.TngAdapterFactory#getOBS_AdapterFactory()
	 */
	public ComposedAdapterFactory getOBS_ComposedAdapterFactory() {
		if (obsAdapterFactory == null) {
			synchronized (this) {
				if (obsAdapterFactory == null) {
					obsAdapterFactory = new ExposedAdapterFactory(
							getOBS_AdapterFactories());
				}
			}
		}
		return obsAdapterFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.TngAdapterFactory#getPBS_AdapterFactory()
	 */
	public ComposedAdapterFactory getPBS_ComposedAdapterFactory() {
		if (pbsAdapterFactory == null) {
			synchronized (this) {
				if (pbsAdapterFactory == null) {
					pbsAdapterFactory = new ExposedAdapterFactory(
							getPBS_AdapterFactories());
				}
			}
		}
		return pbsAdapterFactory;
	}

	private AdapterFactory[] getWBS_AdapterFactories() {
		if (wbsAdapterFactories == null) {
			AdapterFactory factory = createAdapterFactoryFromExtension(WBS_ADAPTER_FACTORY_ID);
			if(factory == null) {
				factory = new WBSItemProviderAdapterFactory();
			}
			wbsAdapterFactories = new AdapterFactory[] {
			// new ResourceItemProviderAdapterFactory(),
					factory,
					// new BSProcesstypesItemProviderAdapterFactory(factory),
					new ReflectiveItemProviderAdapterFactory() };
		}
		return wbsAdapterFactories;
	}

	private AdapterFactory[] getOBS_AdapterFactories() {
		if (obsAdapterFactories == null) {
			OBSItemProviderAdapterFactory factory = new OBSItemProviderAdapterFactory();
			obsAdapterFactories = new AdapterFactory[] {
			// new ResourceItemProviderAdapterFactory(),
					factory,
					// new BSProcesstypesItemProviderAdapterFactory(factory),
					new ReflectiveItemProviderAdapterFactory() };
		}
		return obsAdapterFactories;
	}

	private AdapterFactory[] getPBS_AdapterFactories() {
		if (pbsAdapterFactories == null) {
			PBSItemProviderAdapterFactory factory = new PBSItemProviderAdapterFactory();
			pbsAdapterFactories = new AdapterFactory[] {
			// new ResourceItemProviderAdapterFactory(),
					factory,
					// new BSProcesstypesItemProviderAdapterFactory(factory),
					new ReflectiveItemProviderAdapterFactory() };
		}
		return pbsAdapterFactories;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#getNavigatorView_ComposedAdapterFactory()
	 */
	public ComposedAdapterFactory getNavigatorView_ComposedAdapterFactory() {
		if (navigatorAdapterFactory == null) {
			synchronized (this) {
				if (navigatorAdapterFactory == null) {
					AdapterFactory factory = createAdapterFactoryFromExtension(LIBRARY_ADAPTER_FACTORY_ID);
					if (factory == null) {
						factory = new org.eclipse.epf.library.edit.navigator.ItemProviderAdapterFactory();
					}
					navigatorAdapterFactory = new ExposedAdapterFactory(
							new AdapterFactory[] {
									// new
									// ResourceItemProviderAdapterFactory(),
									factory,
									new ReflectiveItemProviderAdapterFactory() });
				}
			}
		}
		return navigatorAdapterFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#getNavigatorView_ComposedAdapterFactory()
	 */
	// public ComposedAdapterFactory getFilterView_ComposedAdapterFactory() {
	// if(filterAdapterFactory == null) {
	// synchronized(this) {
	// if(filterAdapterFactory == null) {
	// filterAdapterFactory = new ComposedAdapterFactory(
	// new AdapterFactory[] {
	// new ResourceItemProviderAdapterFactory(),
	// new com.ibm.library.edit.filter.ItemProviderAdapterFactory(),
	// new ReflectiveItemProviderAdapterFactory()
	// });
	// }
	// }
	// }
	// return filterAdapterFactory;
	// }
	//    
	public ComposedAdapterFactory getConfigurationView_ComposedAdapterFactory() {
		if (configurationAdapterFactory == null) {
			synchronized (this) {
				if (configurationAdapterFactory == null) {
					AdapterFactory factory = createAdapterFactoryFromExtension(CONFIGURATION_ADAPTER_FACTORY_ID);
					if(factory == null) {
						factory = new org.eclipse.epf.library.edit.configuration.ItemProviderAdapterFactory();
					}
					configurationAdapterFactory = new ExposedAdapterFactory(
							new AdapterFactory[] {
									// new ResourceItemProviderAdapterFactory(),
									factory,
									new ReflectiveItemProviderAdapterFactory() });
				}
			}
		}
		return configurationAdapterFactory;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#getNavigatorView_FilterAdapterFactory(com.ibm.library.edit.IFilter)
	 */
	public AdapterFactory getConfigurationView_AdapterFactory(IFilter filter) {
		return new FilterAdapterFactory(
				getConfigurationView_ComposedAdapterFactory(), filter);
	}

	protected static class FilterItemProvider implements Adapter,
			IStructuredItemContentProvider, ITreeItemContentProvider,
			IItemLabelProvider, IItemPropertySource {
		private static final HashSet<Class<?>> CLASSES_EXCLUDED_FROM_SORTING = new HashSet<Class<?>>();

		static {
			CLASSES_EXCLUDED_FROM_SORTING.add(Activity.class);
			CLASSES_EXCLUDED_FROM_SORTING.add(CustomCategory.class);
			CLASSES_EXCLUDED_FROM_SORTING.add(TaskDescriptor.class);
			CLASSES_EXCLUDED_FROM_SORTING.add(TeamProfile.class);
			CLASSES_EXCLUDED_FROM_SORTING.add(PracticeSubgroupItemProvider.class);
		}

		private ItemProviderAdapter adapter;

		private IFilter filter;

		private boolean isAdapterConfigurable;

		protected FilterItemProvider(ItemProviderAdapter adapter, IFilter filter) {
			this.adapter = adapter;
			this.filter = filter;
			if (adapter instanceof IConfigurable) {
				isAdapterConfigurable = true;
				((IConfigurable) adapter).setFilter(filter);
			}

		}
		
		public ItemProviderAdapter getAdapter() {
			return adapter;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification notification) {
			adapter.notifyChanged(notification);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
		 */
		public Notifier getTarget() {
			return adapter.getTarget();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
		 */
		public void setTarget(Notifier newTarget) {
			adapter.setTarget(newTarget);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
		 */
		public boolean isAdapterForType(Object type) {
			return adapter.isAdapterForType(type);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IEditingDomainItemProvider#getChildren(java.lang.Object)
		 */
		public Collection<?> getChildren(Object object) {
			Collection<?> children = getChildren_(object);
			if (filter instanceof IConfigurator) {
				IConfigurator configurator = (IConfigurator) filter;
				children = configurator.getModifiedChildren(object, children);
			}
			return children;
		}
		
		private Collection<?> getChildren_(Object object) {
			List<Object> children = null;
			fake_loop: do {
				
				// Order is not important, If adapter is ILibraryItemProvider
				// and replaceChildren is false and IConfigurable, it should  
				// go to isAdapterConfigurable, not to regular iteration.

				if (filter instanceof IConfigurator
						&& adapter instanceof ILibraryItemProvider) {
					IConfigurator configurator = (IConfigurator) filter;
					children = new ArrayList<Object>();
					boolean replaceChildren = false;
					for (Iterator<?> iter = ((ILibraryItemProvider) adapter)
							.getChildrenFeatures(object).iterator(); iter
							.hasNext();) {
						EStructuralFeature feature = (EStructuralFeature) iter
								.next();
						Collection<?> otherChildren = configurator.getChildren(
								object, feature);
						if (otherChildren != null) {
							replaceChildren = true;
							children.addAll(otherChildren);
						}
					}
					if (replaceChildren) {
						break fake_loop;
					}
				} 
				if (isAdapterConfigurable) {
					Collection<?> collection = adapter.getChildren(object);
					children = new ArrayList<Object>(collection);
					break fake_loop;
				}
				
				// regular adapter
				children = new ArrayList<Object>(adapter.getChildren(object));
				for (Iterator<?> iter = children.iterator(); iter.hasNext();) {
					Object element = (Object) iter.next();
					if (!filter.accept(element)) {
						iter.remove();
					}
				}
			} while (false);

			if (adapter instanceof PracticeItemProvider) {
				return ((PracticeItemProvider) adapter).getModifiedChildren(object, children);
			}
			
			// alphabetically sort the children
			//
			Object uObj = TngUtil.unwrap(object);
			if (!TngUtil.isInstanceOf(CLASSES_EXCLUDED_FROM_SORTING, uObj) &&
					!(uObj instanceof ContentCategory)) {
				Collections.sort(children,
						Comparators.PRESENTATION_NAME_COMPARATOR);
			}

			// System.out.println("FilterItemProvider.getChildren() returned " +
			// children);
			return children;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IEditingDomainItemProvider#getParent(java.lang.Object)
		 */
		public Object getParent(Object object) {
			return adapter.getParent(object);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IEditingDomainItemProvider#getNewChildDescriptors(java.lang.Object,
		 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Object)
		 */
		public Collection getNewChildDescriptors(Object object,
				EditingDomain editingDomain, Object sibling) {
			return adapter.getNewChildDescriptors(object, editingDomain,
					sibling);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IEditingDomainItemProvider#createCommand(java.lang.Object,
		 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Class,
		 *      org.eclipse.emf.edit.command.CommandParameter)
		 */
		public Command createCommand(Object object,
				EditingDomain editingDomain, Class commandClass,
				CommandParameter commandParameter) {
			return adapter.createCommand(object, editingDomain, commandClass,
					commandParameter);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IStructuredItemContentProvider#getElements(java.lang.Object)
		 */
		public Collection getElements(Object object) {
			return adapter.getElements(object);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.ITreeItemContentProvider#hasChildren(java.lang.Object)
		 */
		public boolean hasChildren(Object object) {
			if (object instanceof TaskDescriptor) {
				return false;
			}
			if (filter instanceof IConfigurator
					&& adapter instanceof ILibraryItemProvider) {
				// always return true to improve performance
				//
				return true;
			}
			if(object instanceof ContentElement){
				return adapter.hasChildren(object);
			}
			if(TngUtil.unwrap(object) instanceof Milestone) {
				return false;
			}
			return true;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IItemLabelProvider#getText(java.lang.Object)
		 */
		public String getText(Object object) {
			return adapter.getText(object);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IItemLabelProvider#getImage(java.lang.Object)
		 */
		public Object getImage(Object object) {
			return adapter.getImage(object);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IItemPropertySource#getPropertyDescriptors(java.lang.Object)
		 */
		public List getPropertyDescriptors(Object object) {
			return adapter.getPropertyDescriptors(object);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IItemPropertySource#getPropertyDescriptor(java.lang.Object,
		 *      java.lang.Object)
		 */
		public IItemPropertyDescriptor getPropertyDescriptor(Object object,
				Object propertyID) {
			return adapter.getPropertyDescriptor(object, propertyID);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IItemPropertySource#getEditableValue(java.lang.Object)
		 */
		public Object getEditableValue(Object object) {
			return adapter.getEditableValue(object);
		}

	}

	protected static class FilterAdapterFactory implements AdapterFactory,
			IChangeNotifier, IDisposable {

		private ComposedAdapterFactory adapterFactory;

		private IFilter filter;

		/**
		 * 
		 */
		public FilterAdapterFactory(ComposedAdapterFactory adapterFactory,
				IFilter filter) {
			this.adapterFactory = adapterFactory;
			if(adapterFactory instanceof ConfigurableComposedAdapterFactory) {
				((ConfigurableComposedAdapterFactory)adapterFactory).setFilter(filter);
			}
			this.filter = filter;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.AdapterFactory#isFactoryForType(java.lang.Object)
		 */
		public boolean isFactoryForType(Object type) {
			return adapterFactory.isFactoryForType(type);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.AdapterFactory#adapt(java.lang.Object,
		 *      java.lang.Object)
		 */
		public Object adapt(Object object, Object type) {
			Object adapter = adapterFactory.adapt(object, type);
			if (adapter == null || adapter instanceof FilterItemProvider
					|| !(adapter instanceof ItemProviderAdapter)) {
				return adapter;
			}
			return new FilterItemProvider((ItemProviderAdapter) adapter, filter);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.AdapterFactory#adapt(org.eclipse.emf.common.notify.Notifier,
		 *      java.lang.Object)
		 */
		public Adapter adapt(Notifier target, Object type) {
			Adapter adapter = adapterFactory.adapt(target, type);
			if (adapter == null || adapter instanceof FilterItemProvider
					|| !(adapter instanceof ItemProviderAdapter)) {
				return adapter;
			}
			return new FilterItemProvider((ItemProviderAdapter) adapter, filter);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.AdapterFactory#adaptNew(org.eclipse.emf.common.notify.Notifier,
		 *      java.lang.Object)
		 */
		public Adapter adaptNew(Notifier target, Object type) {
			return adapterFactory.adaptNew(target, type);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.common.notify.AdapterFactory#adaptAllNew(org.eclipse.emf.common.notify.Notifier)
		 */
		public void adaptAllNew(Notifier notifier) {
			adapterFactory.adaptAllNew(notifier);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IChangeNotifier#fireNotifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void fireNotifyChanged(Notification notification) {
			adapterFactory.fireNotifyChanged(notification);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IChangeNotifier#addListener(org.eclipse.emf.edit.provider.INotifyChangedListener)
		 */
		public void addListener(INotifyChangedListener notifyChangedListener) {
			adapterFactory.addListener(notifyChangedListener);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IChangeNotifier#removeListener(org.eclipse.emf.edit.provider.INotifyChangedListener)
		 */
		public void removeListener(INotifyChangedListener notifyChangedListener) {
			adapterFactory.removeListener(notifyChangedListener);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
		 */
		public void dispose() {
			filter = null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#createWBSComposedAdapterFactory()
	 */
	public ComposedAdapterFactory createWBSComposedAdapterFactory() {
		AdapterFactory[] adapterFactories = new AdapterFactory[] {
		// new ResourceItemProviderAdapterFactory(),
				new WBSItemProviderAdapterFactory(),
				new ReflectiveItemProviderAdapterFactory() };
		return new ExposedAdapterFactory(adapterFactories);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.TngAdapterFactory#createPublishingWBSAdapterFactory()
	 */
	public ComposedAdapterFactory createPublishingWBSAdapterFactory() {
		return new ExposedAdapterFactory(
				new AdapterFactory[] {
						new org.eclipse.epf.library.edit.process.publishing.WBSItemProviderAdapterFactory(),
						new ReflectiveItemProviderAdapterFactory()
				}
		);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#createTBSComposedAdapterFactory()
	 */
	public ComposedAdapterFactory createTBSComposedAdapterFactory() {
		AdapterFactory[] adapterFactories = new AdapterFactory[] {
		// new ResourceItemProviderAdapterFactory(),
				new OBSItemProviderAdapterFactory(),
				new ReflectiveItemProviderAdapterFactory() };
		return new ExposedAdapterFactory(adapterFactories);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.TngAdapterFactory#createPublishingTBSAdapterFactory()
	 */
	public ComposedAdapterFactory createPublishingTBSAdapterFactory() {
		return new ExposedAdapterFactory(
				new AdapterFactory[] {
						new org.eclipse.epf.library.edit.process.publishing.TBSItemProviderAdapterFactory(),
						new ReflectiveItemProviderAdapterFactory()
				}
		);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#createWPBSComposedAdapterFactory()
	 */
	public ComposedAdapterFactory createWPBSComposedAdapterFactory() {
		AdapterFactory[] adapterFactories = new AdapterFactory[] {
		// new ResourceItemProviderAdapterFactory(),
				new PBSItemProviderAdapterFactory(),
				new ReflectiveItemProviderAdapterFactory() };
		return new ExposedAdapterFactory(adapterFactories);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#createProcessComposedAdapterFactory()
	 */
	public ComposedAdapterFactory createProcessComposedAdapterFactory() {
		org.eclipse.epf.library.edit.process.consolidated.ItemProviderAdapterFactory adapterFactory = new org.eclipse.epf.library.edit.process.consolidated.ItemProviderAdapterFactory();
		IPreferenceStoreWrapper prefStore = Providers.getAuthoringPluginPreferenceStore();
		if (prefStore != null) {
			adapterFactory.setColumnIndexToNameMap(ProcessUtil
					.toColumnIndexToNameMap(prefStore
							.getString(LibraryEditConstants.PREF_WBS_COLUMNS)));
		}
		AdapterFactory[] adapterFactories = new AdapterFactory[] {
		// new ResourceItemProviderAdapterFactory(),
				adapterFactory, new ReflectiveItemProviderAdapterFactory() };

		return new ExposedAdapterFactory(adapterFactories);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#createLibraryComposedAdapterFactory()
	 */
	public ComposedAdapterFactory createLibraryComposedAdapterFactory() {
		AdapterFactory factory = createAdapterFactoryFromExtension(LIBRARY_ADAPTER_FACTORY_ID);
		if (factory == null) {
			factory = new org.eclipse.epf.library.edit.navigator.ItemProviderAdapterFactory();
		}
		return new ExposedAdapterFactory(
				new AdapterFactory[] {
						// new
						// ResourceItemProviderAdapterFactory(),
						factory,
						new ReflectiveItemProviderAdapterFactory() });

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#createLibraryComposedAdapterFactory()
	 */
	public ComposedAdapterFactory createConfigPage_LibraryComposedAdapterFactory() {
//		AdapterFactory factory = createAdapterFactoryFromExtension(LIBRARY_ADAPTER_FACTORY_ID);
//		if (factory == null) {
		AdapterFactory	factory = new org.eclipse.epf.library.edit.navigator.ConfigPageItemProviderAdapterFactory();
//		}
		return new ExposedAdapterFactory(
				new AdapterFactory[] {
						// new
						// ResourceItemProviderAdapterFactory(),
						factory,
						new ReflectiveItemProviderAdapterFactory() });

	}
	
	// Section for TNG Filter modification process.
	// filter modification code. Similar to getFilterView_AdapterFactory(IFilter
	// filter)
	public AdapterFactory getItemsFilter_AdapterFactory(IFilter filter) {
		return new FilterAdapterFactory(
				getItemsFilter_ComposedAdapterFactory(), filter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#getItemsFilter_ComposedAdapterFactory()
	 */
	public ComposedAdapterFactory getItemsFilter_ComposedAdapterFactory() {
		if (itemsFilterAdapterFactory == null) {
			synchronized (this) {
				if (itemsFilterAdapterFactory == null) {
					itemsFilterAdapterFactory = new ExposedAdapterFactory(
							new AdapterFactory[] {
									// new ResourceItemProviderAdapterFactory(),
									new org.eclipse.epf.library.edit.itemsfilter.ItemProviderAdapterFactory(),
									new ReflectiveItemProviderAdapterFactory() });
				}
			}
		}
		return itemsFilterAdapterFactory;
	}

	public AdapterFactory getPBSFilter_AdapterFactory(IFilter filter) {
		return new FilterAdapterFactory(getPBSFilter_ComposedAdapterFactory(),
				filter);
	}

	public ComposedAdapterFactory getPBSFilter_ComposedAdapterFactory() {
		if (pbsFilterAdapaterFactory == null) {
			synchronized (this) {
				if (pbsFilterAdapaterFactory == null) {
					pbsFilterAdapaterFactory = new ExposedAdapterFactory(
							new AdapterFactory[] {
									// new ResourceItemProviderAdapterFactory(),
									new org.eclipse.epf.library.edit.itemsfilter.PBSItemProviderAdapterFactory(),
									new ReflectiveItemProviderAdapterFactory() });
				}
			}
		}
		return pbsFilterAdapaterFactory;
	}

	public AdapterFactory getOBSFilter_AdapterFactory(IFilter filter) {
		return new FilterAdapterFactory(getOBSFilter_ComposedAdapterFactory(),
				filter);
	}

	public ComposedAdapterFactory getOBSFilter_ComposedAdapterFactory() {
		if (obsFilterAdapaterFactory == null) {
			synchronized (this) {
				if (obsFilterAdapaterFactory == null) {
					obsFilterAdapaterFactory = new ExposedAdapterFactory(
							new AdapterFactory[] {
									// new ResourceItemProviderAdapterFactory(),
									new org.eclipse.epf.library.edit.itemsfilter.OBSItemProviderAdapterFactory(),
									new ReflectiveItemProviderAdapterFactory() });
				}
			}
		}
		return obsFilterAdapaterFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#getProcessComposedAdapterFactory()
	 */
	public ComposedAdapterFactory getProcessComposedAdapterFactory() {
		if (procAdapterFactory == null) {
			synchronized (this) {
				if (procAdapterFactory == null) {
					org.eclipse.epf.library.edit.process.consolidated.ItemProviderAdapterFactory adapterFactory = new org.eclipse.epf.library.edit.process.consolidated.ItemProviderAdapterFactory();
					final IPreferenceStoreWrapper prefStore = Providers.getPreferenceStore();
					if (prefStore != null) {
						adapterFactory
								.setColumnIndexToNameMap(ProcessUtil
										.toColumnIndexToNameMap(prefStore
												.getString(LibraryEditConstants.PREF_WBS_COLUMNS)));
						prefStore.addPropertyChangeListener(adapterFactory);
						
						// Initialize "auto inherit suppression states" flag for calculating suppression
						// and add listener to its change
						//
						Suppression.setAutoInheritSuppressionStates(prefStore.getBoolean(LibraryEditConstants.PREF_INHERIT_SUPPRESSION_STATE));
						prefStore.addPropertyChangeListener(new IPropertyChangeListenerWrapper() {

							public void propertyChange(IPropertyChangeEventWrapper event) {
								if(LibraryEditConstants.PREF_INHERIT_SUPPRESSION_STATE.equals(event.getProperty())) {
									Suppression.setAutoInheritSuppressionStates(prefStore.getBoolean(LibraryEditConstants.PREF_INHERIT_SUPPRESSION_STATE));
								}
							}

						});
					}					
				
					procAdapterFactories = new AdapterFactory[] {
							adapterFactory,
							new ReflectiveItemProviderAdapterFactory() };

					procAdapterFactory = new ExposedAdapterFactory(
							procAdapterFactories);
				}
			}
		}

		return procAdapterFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TngAdapterFactory#reset()
	 */
	public void reset() {
		if (navigatorAdapterFactory != null) {
			navigatorAdapterFactory.dispose();
		}

		if (itemsFilterAdapterFactory != null) {
			itemsFilterAdapterFactory.dispose();
			itemsFilterAdapterFactory = null;
		}
		
		if (obsFilterAdapaterFactory != null) {
			obsFilterAdapaterFactory.dispose();
			obsFilterAdapaterFactory = null;
		}
		
		if (pbsFilterAdapaterFactory != null) {
			pbsFilterAdapaterFactory.dispose();
			pbsFilterAdapaterFactory = null;
		}
		
		
		if (configProcessViewAdapterFactory != null) {
			configProcessViewAdapterFactory.dispose();
		}

		if (configurationAdapterFactory != null) {
			configurationAdapterFactory.dispose();
		}

		if (wbsAdapterFactory != null) {
			wbsAdapterFactory.dispose();
			wbsAdapterFactory = null;
		}

		if (obsAdapterFactory != null) {
			obsAdapterFactory.dispose();
			obsAdapterFactory = null;
		}

		if (pbsAdapterFactory != null) {
			pbsAdapterFactory.dispose();
			pbsAdapterFactory = null;
		}

		if (procAdapterFactory != null) {
			// remove adapter factory as property change listener from
			// preference store
			//
			IPreferenceStoreWrapper prefStore = Providers.getAuthoringPluginPreferenceStore();
			if (prefStore != null) {
				for (int i = 0; i < procAdapterFactories.length; i++) {
					Object adapterFactory = procAdapterFactories[i];
					if (adapterFactory instanceof IPropertyChangeListenerWrapper) {
						prefStore
								.removePropertyChangeListener((IPropertyChangeListenerWrapper) adapterFactory);
					}
				}
			}
			procAdapterFactory.dispose();
			procAdapterFactory = null;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.TngAdapterFactory#createPublishingCBSAdapterFactory()
	 */
	public ComposedAdapterFactory createPublishingCBSAdapterFactory() {
		return new ExposedAdapterFactory(
				new AdapterFactory[] {
						new org.eclipse.epf.library.edit.process.publishing.CBSItemProviderAdapterFactory(),
						new ReflectiveItemProviderAdapterFactory()
				}
		);

	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.TngAdapterFactory#createPublishingWPBSAdapterFactory()
	 */
	public ComposedAdapterFactory createPublishingWPBSAdapterFactory() {
		return new ExposedAdapterFactory(
				new AdapterFactory[] {
						new org.eclipse.epf.library.edit.process.publishing.WPBSItemProviderAdapterFactory(),
						new ReflectiveItemProviderAdapterFactory()
				}
		);

	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.TngAdapterFactory#cleanUp()
	 */
	public void cleanUp() {
		Suppression.cleanUp();

		if (navigatorAdapterFactory != null) {
			navigatorAdapterFactory.cleanUp();
		}

		if (itemsFilterAdapterFactory != null) {
			itemsFilterAdapterFactory.cleanUp();
		}
		
		if (obsFilterAdapaterFactory != null) {
			obsFilterAdapaterFactory.cleanUp();
		}
		
		if (pbsFilterAdapaterFactory != null) {
			pbsFilterAdapaterFactory.cleanUp();
		}
		
		
		if (configProcessViewAdapterFactory != null) {
			configProcessViewAdapterFactory.cleanUp();
		}

		if (configurationAdapterFactory != null) {
			configurationAdapterFactory.cleanUp();
		}

		if (wbsAdapterFactory != null) {
			wbsAdapterFactory.cleanUp();
		}

		if (obsAdapterFactory != null) {
			obsAdapterFactory.cleanUp();
		}

		if (pbsAdapterFactory != null) {
			pbsAdapterFactory.cleanUp();
		}

		if (procAdapterFactory != null) {
			procAdapterFactory.cleanUp();
		}
	}

	private static AdapterFactory createAdapterFactoryFromExtension(String id) {
		// Process the contributors.
		//
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(LibraryEditPlugin.PLUGIN_ID, "adapterFactories"); //$NON-NLS-1$
		if (extensionPoint != null) {
			IExtension[] extensions = extensionPoint.getExtensions();
			for (int i = 0; i < extensions.length; i++) {
				IExtension extension = extensions[i];
				String pluginId = extension.getNamespaceIdentifier();
				Bundle bundle = Platform.getBundle(pluginId);
				IConfigurationElement[] configElements = extension
						.getConfigurationElements();
				for (int j = 0; j < configElements.length; j++) {
					IConfigurationElement configElement = configElements[j];
					try {
						String factoryId = configElement.getAttribute("id"); //$NON-NLS-1$
						if(id.equals(factoryId)) {
							String className = configElement.getAttribute("class"); //$NON-NLS-1$
							if(className != null) {
								Object factory = bundle.loadClass(className).newInstance();
								if(factory instanceof AdapterFactory) {
									return (AdapterFactory) factory;
								}
							}
						}
					} catch (Exception e) {
						LibraryEditPlugin.INSTANCE.log(e);
					}
				}
			}
		}
		return null;
	}

}
