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

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.provider.AttributeValueWrapperItemProvider;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.FeatureMapEntryWrapperItemProvider;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.FeatureValueWrapperItemProvider;
import org.eclipse.epf.library.edit.IDefaultNameSetter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.IGroupContainer;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.category.CustomCategoryItemProvider;
import org.eclipse.epf.library.edit.category.StandardCategoriesItemProvider;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.element.ContentPackageItemProvider;
import org.eclipse.epf.library.edit.internal.IListenerProvider;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.navigator.ContentItemProvider;
import org.eclipse.epf.library.edit.navigator.MethodPluginItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.command.MoveDownCommand;
import org.eclipse.epf.library.edit.process.command.MoveUpCommand;
import org.eclipse.epf.library.edit.util.model.OrderInfo;
import org.eclipse.epf.library.edit.util.model.OrderInfoCollection;
import org.eclipse.epf.library.edit.util.model.util.StringResource;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.library.edit.validation.NameChecker;
import org.eclipse.epf.library.edit.validation.UniqueNameHandler;
import org.eclipse.epf.services.IAccessController;
import org.eclipse.epf.services.IFileBasedLibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.ProcessPlanningTemplate;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.provider.MethodElementItemProvider;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.epf.uma.util.UmaResources;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.osgi.util.NLS;

import com.ibm.icu.util.StringTokenizer;


/**
 * Utility class with static methods for method authoring
 * 
 * @author Phong Nguyen Le
 * @author Jinhua Xi
 * @since 1.0
 */
public final class TngUtil {

	public static final UmaItemProviderAdapterFactory umaItemProviderAdapterFactory = new UmaItemProviderAdapterFactory();

	public static final boolean DEBUG = LibraryEditPlugin.getPlugin()
			.isDebugging();

	public static final String GUIDANCE_FILESTRING_SEPARATOR = "|"; //$NON-NLS-1$

	private static final String GUIDANCE_FILESTRING_SEPARATOR_SPLITTER = "\\|"; //$NON-NLS-1$

	public static final String COMMA_SEPARATOR = ","; //$NON-NLS-1$
	private static final String COMMA_SEPARATOR_SPLITTER = "\\,"; //$NON-NLS-1$
	
	public static final String PUBLISH_CATEGORY_PROPERTY = "PUBLISH_CATEGORY"; //$NON-NLS-1$
	
	/**
	 * Removes all adapters that are instance of the given class from the given
	 * EObject.
	 * 
	 * @param eObj
	 * @param cls
	 */
	public static void removeAdapters(EObject eObj, Class cls) {
		for (Iterator adapters = eObj.eAdapters().iterator(); adapters
				.hasNext();) {
			Adapter adapter = (Adapter) adapters.next();
			if (cls.isInstance(adapter)) {
				adapters.remove();
			}
		}
	}

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

	public static Adapter adapt(AdapterFactory factory, Notifier notifier,
			Object type, Class viewType) {
		for (Iterator adapters = notifier.eAdapters().iterator(); adapters
				.hasNext();) {
			Adapter adapter = (Adapter) adapters.next();
			if (adapter.isAdapterForType(type) && viewType.isInstance(adapter)) {
				return adapter;
			}
		}
		return factory.adaptNew(notifier, type);
	}

	public static boolean isValidFileName(String name) {
		if (StrUtil.isBlank(name))
			return false;
		if (name.indexOf('/') != -1 || name.indexOf('\\') != -1
				|| name.indexOf(':') != -1 || name.indexOf('*') != -1
				|| name.indexOf('?') != -1 || name.indexOf('"') != -1
				|| name.indexOf('<') != -1 || name.indexOf('>') != -1
				|| name.indexOf('|') != -1 || name.indexOf(';') != -1
				|| name.indexOf('[') != -1 || name.indexOf(']') != -1
				|| name.indexOf('#') != -1)
			return false;
		return true;
	}
	
	/**
	 * validate the string for a plugin name
	 * 
	 * @param name String
	 * @return String
	 */
	public static boolean isValidPluginName(String name) {
		if (isValidFileName(name) == false ) {
			return false;
		}
		
		// need to remove these characters since they caused problem with element url encoding, bookmark issue, 
		// and javascript string liternal escaping issue
		// see 156946 - plugin name should not allow %, #, and '
		if (name.indexOf('%') != -1 || name.indexOf('#') != -1
				|| name.indexOf('\'') != -1 )
			return false;
		return true;
	}

	/**
	 * Checks if the given name is valid for the given EObject.
	 * 
	 * @param eObj
	 * @param name
	 * @param eClasses
	 *            collection of EClass objects. The EObject instances of these
	 *            EClass objects will be saved in their own directory.
	 * @return null if successful, error message otherwise.
	 */
	public static String checkName(NamedElement e, String name,
			Collection eClasses) {
		return checkName(((EObject) e).eContainer(), e, name, TngUtil
				.getTypeText(e), eClasses);
	}

	public static String checkName(EObject parent, NamedElement e, String name,
			String elementTypeText, Collection eClasses) {
		String msg = checkElementName(name, elementTypeText);
		if (msg != null)
			return msg;
		if (parent == null)
			return null;
		List children = parent.eContents();
		for (int i = 0; i < children.size(); i++) {
			NamedElement child = (NamedElement) children.get(i);
			if (child != e && isEClassInstanceOf(eClasses, child)
					&& name.equalsIgnoreCase(child.getName())) {
				//		return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
				return NLS.bind(LibraryEditResources.duplicateElementNameError_msg, name); 
			}
		}
		return null;
	}

	public static String checkName(EObject parent, NamedElement e, String name,
			String elementTypeText, IFilter childFilter) {
		String msg = checkElementName(name, elementTypeText);
		if (msg != null)
			return msg;
		if (parent == null)
			return null;
		List children = parent.eContents();
		for (int i = 0; i < children.size(); i++) {
			NamedElement child = (NamedElement) children.get(i);
			if (child != e && childFilter.accept(child)
					&& name.equalsIgnoreCase(child.getName())) {
				//		return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
				return NLS.bind(LibraryEditResources.duplicateElementNameError_msg, name); 
			}
		}
		return null;
	}

	/**
	 * Checks if the given name can be used for the given element. The method
	 * assume that the given element will be saved under the given directory
	 * using the given name.
	 * 
	 * @param dir
	 *            Directory where the given element will be saved in a file or a
	 *            directory depending on the value of isDir
	 * @param siblings
	 *            exisiting siblings of the given element <code>e</code>
	 * @param e
	 *            the element its name to be checked.
	 * @param name
	 *            the suggested new name for the given element <code>e</code>
	 * @param typeLabel
	 *            the element type label.
	 * @param isDir
	 *            flag indicating whether the given element will be saved a
	 *            directory
	 * @return null if the given name can be used, an error message if the name
	 *         is already taken.
	 */
	public static String checkName(File dir, Collection siblings,
			NamedElement e, String name, String elementTypeText, boolean isDir) {
		String msg = checkElementName(name, elementTypeText);
		if (msg != null)
			return msg;
		String fileName = name;
		if (!isDir) {
			fileName = fileName + ((IFileBasedLibraryPersister)Services.getDefaultLibraryPersister()).getFileExtension(e);
		}
		File file = new File(dir, fileName);
		File currentFile = null;
		if (e != null && e.eResource() != null) {
			currentFile = new File(e.eResource().getURI().toFileString());
			if (isDir) {
				currentFile = currentFile.getParentFile();
			}
		}
		boolean b = file.exists();
		if (b && !file.equals(currentFile)) {
			msg = NLS.bind(LibraryEditResources.duplicateElementNameError_msg, name);
			/* TODO: is this needed? After calling NLS.bind, there will	not be any placeholders left for params.
			Object[] params = new Object[2];
			if (file.isFile()) {
				params[0] = LibraryEditResources.file_text; 
			} else {
				params[0] = LibraryEditResources.directory_text; 
			}
			params[1] = file;
			return MessageFormat.format(msg, params).toString();
			*/
			return msg;
		}
		for (Iterator iter = siblings.iterator(); iter.hasNext();) {
			NamedElement child = (NamedElement) iter.next();
			if (child != e && name.equalsIgnoreCase(child.getName())) {
				//		return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
				return NLS.bind(LibraryEditResources.duplicateElementNameError_msg, name); 
			}
		}
		// 
		return null;

	}

	public static String checkElementName(String name, String elementTypeText) {
		if (name == null || name.trim().length() == 0)
			//		return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
			return NLS.bind(LibraryEditResources.emptyElementNameError_msg, StrUtil.toLower(elementTypeText)); 
		if (name.startsWith(" ")) //$NON-NLS-1$
			//		return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
			return NLS.bind(LibraryEditResources.invalidElementNameError1_msg, name); 
		if (name.endsWith(".")) //$NON-NLS-1$
			//		return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
			return NLS.bind(LibraryEditResources.invalidElementNameError2_msg, name); 		
		if (!isValidFileName(name))
			//		return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
			return NLS.bind(LibraryEditResources.invalidElementNameError3_msg, name); 
		return null;
	}

	public static String checkPluginName(String name) {
	
		String error = checkElementName(name, TngUtil.getTypeText("MethodPlugin") ); //$NON-NLS-1$
		if ( error != null ) {
			return error;
		}
		
		// 156946 - plugin name should not allow %, #, and '
		if (!isValidPluginName(name) ) {
			//		return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
			return NLS.bind(LibraryEditResources.invalidPluginNameError_msg, name); 
		} else {
			return null;
		}
		
	}
	
	public static String checkActivityName(AdapterFactory adapterFactory,
			Object object, String name, Suppression suppression) {
		if (!(object instanceof Activity))
			return null;
		// if(!isValidFileName(name)) return "Invalid name: '" + name + "'. The
		// name cannot contain following characters: \\ / : * ? \" < > |";
		return NameChecker.checkName(adapterFactory, (BreakdownElement) object, Activity.class,
				UmaPackage.eINSTANCE.getNamedElement_Name(), name, suppression);
	}

//	public static String checkWBSActivityName(Object act, String name) {
//		return checkActivityName(TngAdapterFactory.INSTANCE
//				.getWBS_ComposedAdapterFactory(), act, name);
//	}

	public static String checkWBSActivityPresentationName(Object act,
			String name, Suppression suppression) {
		if (!(act instanceof Activity))
			return null;
		return NameChecker.checkName(TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory(), (BreakdownElement) act, Activity.class,
				UmaPackage.eINSTANCE.getMethodElement_PresentationName(),
				name, suppression);
	}

	public static String checkWorkBreakdownElementPresentationName(Object e,
			String name, Suppression suppression) {
		if (!(e instanceof WorkBreakdownElement))
			return null;
		return NameChecker.checkName(TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory(), (BreakdownElement) e,
				WorkBreakdownElement.class, UmaPackage.eINSTANCE
						.getMethodElement_PresentationName(), name, suppression);
	}

	public static String checkWorkProductDescriptorPresentationName(Object e,
			String name, Suppression suppression) {
		if (!(e instanceof WorkProductDescriptor))
			return null;
		return NameChecker.checkName(TngAdapterFactory.INSTANCE
				.getPBS_ComposedAdapterFactory(), (BreakdownElement) e,
				WorkProductDescriptor.class, UmaPackage.eINSTANCE
						.getMethodElement_PresentationName(), name, suppression);
	}

	public static String checkRoleDescriptorPresentationName(Object e,
			String name, Suppression suppression) {
		if (!(e instanceof RoleDescriptor))
			return null;
		return NameChecker.checkName(TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory(), (BreakdownElement) e, RoleDescriptor.class,
				UmaPackage.eINSTANCE.getMethodElement_PresentationName(),
				name, suppression);
	}

	public static boolean isEClassInstanceOf(Collection eClasses, Object obj) {
		for (Iterator iter = eClasses.iterator(); iter.hasNext();) {
			EClass eClass = (EClass) iter.next();
			if (eClass.isInstance(obj))
				return true;
		}
		return false;
	}

	public static boolean isInstanceOf(Collection classes, Object obj) {
		for (Iterator iter = classes.iterator(); iter.hasNext();) {
			Class clazz = (Class) iter.next();
			if (clazz.isInstance(obj))
				return true;
		}
		return false;
	}

	public static AdapterFactory getBestAdapterFactory(
			AdapterFactory adapterFactory) {
		if (adapterFactory instanceof ComposeableAdapterFactory) {
			return ((ComposeableAdapterFactory) adapterFactory)
					.getRootAdapterFactory();
		}
		return adapterFactory;
	}

	/**
	 * @param object
	 */
	public static String getLabel(Object object) {
		return getLabel(object, ""); //$NON-NLS-1$
	}

	public static String getLabel(Object object, String alternativeLabel) {
		if (object instanceof ContentDescription) {
			return getLabel(((ContentDescription) object).eContainer(), alternativeLabel);
		}
		
		if (object instanceof EObject) {
			EObject eObj = (EObject) object;
			if (eObj.eIsProxy()) {
				String path = null;
				try {
					org.eclipse.emf.common.util.URI uri = eObj.eResource().getResourceSet().getURIConverter()
							.normalize(((InternalEObject) eObj).eProxyURI());
					path = uri.isFile() ? uri.toFileString() : uri.toString();
				} catch (Exception e) {
					//
				}
				if (path != null) {
					path = MessageFormat.format(
							" ''{0}''", new Object[] { path }); //$NON-NLS-1$
				} else {
					path = ""; //$NON-NLS-1$
				}
				//		return I18nUtil.formatString(RESOURCE_BUNDLE, key, data);
				return NLS.bind(LibraryEditResources.unresolved_text, path); 
			}
		}
		String label = null;
		boolean showPresentationNames = PresentationContext.INSTANCE.isShowPresentationNames();
		if (showPresentationNames && object instanceof ProcessComponent)
			label = ((ProcessComponent) object).getProcess()
					.getPresentationName();
		else if (showPresentationNames && object instanceof MethodElement)
			label = ((MethodElement) object).getPresentationName();
		if (label == null || label.trim().length() == 0)
			label = ((NamedElement) object).getName();
		return label == null || label.trim().length() == 0 ? alternativeLabel : label;
	}

	public static String getLabel(VariabilityElement object,
			String alternativeLabel, boolean preferBase) {
		String label = TngUtil.getLabel(object, alternativeLabel);
		if (preferBase && object.getVariabilityBasedOnElement() != null) {
			VariabilityType type = object.getVariabilityType();
			String variabilityTxt = null;
			if (type == VariabilityType.CONTRIBUTES) {
				variabilityTxt = LibraryEditResources.contributesTo_text; 
			} else if (type == VariabilityType.LOCAL_CONTRIBUTION) {
					variabilityTxt = LibraryEditResources.localContributesTo_text; 
			} else if (type == VariabilityType.EXTENDS) {
				variabilityTxt = LibraryEditResources.extends_text; 
			} else if (type == VariabilityType.REPLACES) {
				variabilityTxt = LibraryEditResources.replaces_text; 
			} else if (type == VariabilityType.LOCAL_REPLACEMENT) {
				variabilityTxt = LibraryEditResources.localReplaces_text; 
			} else if (type == VariabilityType.EXTENDS_REPLACES){
				variabilityTxt = LibraryEditResources.extends_replaces_text;
			}
			if (variabilityTxt != null) {
				MethodPlugin basePlugin = UmaUtil.getMethodPlugin(object
						.getVariabilityBasedOnElement());
				if (basePlugin != null) {
					label = NLS.bind(LibraryEditResources.Util_labelpath_variabilitywithplugin_info, (new String[] {
					label,
					variabilityTxt,
					TngUtil
							.getLabel(
									object
											.getVariabilityBasedOnElement(),
									alternativeLabel),
					basePlugin.getName() }));
				} else {
					label = NLS.bind(LibraryEditResources.Util_labelpath_variability_info, (new String[] {
					label,
					variabilityTxt,
					TngUtil.getLabel(object
							.getVariabilityBasedOnElement(),
							alternativeLabel) }));
				}
			}
		}

		return label;
	}

	/**
	 * Initializes a newly created ProcessComponent
	 * 
	 * @param component
	 */
	public static void initializeProcessComponent(ProcessComponent component,
			EClass type) {
		// create new process for the process component
		//
		Process proc = component.getProcess();
		if (proc == null) {
			proc = (Process) UmaFactory.eINSTANCE.create(type);
			component.setProcess(proc);
		}

		// create top activity
		//
		// if(proc.getTopLevelBreakdownElement() == null) {
		// Activity act = UmaFactory.eINSTANCE.createActivity();
		// proc.setTopLevelBreakdownElement(act);
		// component.getBreakdownElements().add(act);
		// }

		// create WBS, OBS, and PBS
		//
		// if(component.getWBS() == null)
		// component.setWBS(UmaFactory.eINSTANCE.createWBS());
		// if(component.getTBS() == null)
		// component.setTBS(UmaFactory.eINSTANCE.createTBS());
		// if(component.getWPBS() == null)
		// component.setWPBS(UmaFactory.eINSTANCE.createWPBS());
	}

	public static BreakdownElement getParent(BreakdownElement be) {
		return null;
	}

	public static String checkNull(String str) {
		return (str == null ? "" : str); //$NON-NLS-1$
	}

	public static boolean descriptorExists(Role role, List roleDescriptorList) {
		for (int i = roleDescriptorList.size() - 1; i > -1; i--) {
			RoleDescriptor roleDesc = (RoleDescriptor) roleDescriptorList
					.get(i);
			if (roleDesc.getRole() == role)
				return true;
		}
		return false;
	}

	public static MethodElement copy(MethodElement element) {
		Copier copier = new Copier() {
			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 3257846576033904691L;

			/**
			 * Called to handle the copying of a cross reference; this adds
			 * values or sets a single value as appropriate for the multiplicity
			 * while omitting any bidirectional reference or single opposite
			 * feature's reference that isn't in the copy map.
			 * 
			 * @param eReference
			 *            the reference to copy.
			 * @param eObject
			 *            the object from which to copy.
			 * @param copyEObject
			 *            the object to copy to.
			 */
			protected void copyReference(EReference eReference,
					EObject eObject, EObject copyEObject) {
				if (eObject.eIsSet(eReference)) {
					OppositeFeature oppositeFeature = OppositeFeature.getOppositeFeature(eReference);
					
					// "many" opposite feature does not require copied target, it is
					// treated as a regular non-containment reference
					// TODO: bidirectional relationships are not automatically copied in
					// EMF implementation
					//
					boolean copiedTargetRequired = eReference.getEOpposite() != null
							|| (oppositeFeature != null && !oppositeFeature.isMany());
										
					if (eReference.isMany()) {
						List source = (List) eObject.eGet(eReference);
						InternalEList target = (InternalEList) copyEObject
								.eGet(getTarget(eReference));
						if (source.isEmpty()) {
							target.clear();
						} else {
							int index = 0;
							for (Iterator k = source.iterator(); k.hasNext();) {
								Object referencedEObject = k.next();
								Object copyReferencedEObject = get(referencedEObject);
								if (copyReferencedEObject == null) {
									if (!copiedTargetRequired) {
										target.addUnique(index,
												referencedEObject);
										++index;
									}
								} else {
									if (copiedTargetRequired) {
										int position = target
												.indexOf(copyReferencedEObject);
										if (position == -1) {
											target.addUnique(index,
													copyReferencedEObject);
										} else if (index != position) {
											target.move(index,
													copyReferencedEObject);
										}
									} else {
										target.addUnique(index,
												copyReferencedEObject);
									}
									++index;
								}
							}
						}
					} else {
						Object referencedEObject = eObject.eGet(eReference);
						if (referencedEObject == null) {
							copyEObject.eSet(getTarget(eReference), null);
						} else {
							Object copyReferencedEObject = get(referencedEObject);
							if (copyReferencedEObject == null) {
								if (!copiedTargetRequired) {
									copyEObject.eSet(getTarget(eReference),
											referencedEObject);
								}
							} else {
								copyEObject.eSet(getTarget(eReference),
										copyReferencedEObject);
							}
						}
					}
				}
			}
		};
		MethodElement copy = (MethodElement) copier.copy(element);
		copier.copyReferences();

		// unset the GUID of the copy and its offstring
		//
		copy.setGuid(null);
		for (Iterator iter = copy.eAllContents(); iter.hasNext();) {
			Object e = iter.next();
			if (e instanceof MethodElement) {
				((MethodElement) e).setGuid(null);
			}
		}
		return copy;
	}

	public static VariabilityElement getBase(VariabilityElement c) {
		for (; c.getVariabilityBasedOnElement() != null; c = (VariabilityElement) c
				.getVariabilityBasedOnElement())
			;
		return c;
	}

	public static ContentCategory findInherited(Collection inheritedList,
			ContentCategory category) {
		for (Iterator iter = inheritedList.iterator(); iter.hasNext();) {
			ContentCategory element = (ContentCategory) iter.next();
			if (element.getVariabilityBasedOnElement() != null
					&& (element.getVariabilityBasedOnElement() == category || element
							.getVariabilityBasedOnElement() == category
							.getVariabilityBasedOnElement()))
				return element;
		}
		return null;
	}

	public static ContentCategory removeInherited(List inheritedList,
			ContentCategory category) {
		for (Iterator iter = inheritedList.iterator(); iter.hasNext();) {
			ContentCategory element = (ContentCategory) iter.next();
			if (element.getVariabilityBasedOnElement() == category) {
				iter.remove();
				return element;
			}
		}
		return null;
	}
	
	public static ContentItemProvider getContentItemProvider(MethodPlugin plugin) {
		MethodPluginItemProvider methodPluginItemProvider = (MethodPluginItemProvider)getAdapter(
				plugin,	MethodPluginItemProvider.class);
		methodPluginItemProvider.getChildren(plugin);
		IGroupContainer groupContainer = (IGroupContainer) TngUtil.getAdapter(
				(EObject) plugin, IGroupContainer.class);
		if (groupContainer != null) {
			return (ContentItemProvider) groupContainer
					.getGroupItemProvider(LibraryEditPlugin.INSTANCE
							.getString("_UI_Content_group")); //$NON-NLS-1$
		}
		return null;
	}
	
	public static StandardCategoriesItemProvider getStandardCategoriesItemProvider(MethodPlugin plugin) {
		ContentItemProvider contentItemProvider = getContentItemProvider(plugin);
		contentItemProvider.getChildren(contentItemProvider);
		if (contentItemProvider != null) {
			return (StandardCategoriesItemProvider)contentItemProvider.getGroupItemProvider(LibraryEditPlugin.INSTANCE
					.getString("_UI_Standard_Categories_group")); //$NON-NLS-1$
		}
		return null;
	}


	public static Object getDisciplineCategoriesItemProvider(MethodPlugin model) {
		StandardCategoriesItemProvider standardCategoriesItemProvider = getStandardCategoriesItemProvider(model);
		standardCategoriesItemProvider.getChildren(standardCategoriesItemProvider);
		if (standardCategoriesItemProvider != null) {
			return standardCategoriesItemProvider
			.getGroupItemProvider(LibraryEditPlugin.INSTANCE
					.getString("_UI_Disciplines_group")); //$NON-NLS-1$
		}
		return null;
	}

	public static Object getDomainsItemProvider(MethodPlugin model) {
		StandardCategoriesItemProvider standardCategoriesItemProvider = getStandardCategoriesItemProvider(model);
		standardCategoriesItemProvider.getChildren(standardCategoriesItemProvider);
		if (standardCategoriesItemProvider != null) {
			return standardCategoriesItemProvider
			.getGroupItemProvider(LibraryEditPlugin.INSTANCE
					.getString("_UI_Domains_group")); //$NON-NLS-1$
		}
		return null;
	}

	public static Object getWorkProductTypesItemProvider(MethodPlugin model) {
		StandardCategoriesItemProvider standardCategoriesItemProvider = getStandardCategoriesItemProvider(model);
		standardCategoriesItemProvider.getChildren(standardCategoriesItemProvider);
		if (standardCategoriesItemProvider != null) {
			return standardCategoriesItemProvider
			.getGroupItemProvider(LibraryEditPlugin.INSTANCE
					.getString("_UI_WorkProductTypes_group")); //$NON-NLS-1$
		}
		return null;
	}

	public static Object getRoleSetsItemProvider(MethodPlugin model) {
		StandardCategoriesItemProvider standardCategoriesItemProvider = getStandardCategoriesItemProvider(model);
		standardCategoriesItemProvider.getChildren(standardCategoriesItemProvider);
		if (standardCategoriesItemProvider != null) {
			return standardCategoriesItemProvider
			.getGroupItemProvider(LibraryEditPlugin.INSTANCE
					.getString("_UI_Role_Sets_group")); //$NON-NLS-1$
		}
		return null;
	}

	public static Object getToolsItemProvider(MethodPlugin model) {
		StandardCategoriesItemProvider standardCategoriesItemProvider = getStandardCategoriesItemProvider(model);
		standardCategoriesItemProvider.getChildren(standardCategoriesItemProvider);
		if (standardCategoriesItemProvider != null) {
			return standardCategoriesItemProvider
			.getGroupItemProvider(LibraryEditPlugin.INSTANCE
					.getString("_UI_Tools_group")); //$NON-NLS-1$
		}
		return null;
	}
	
	/**
	 * Gets the next available name that is not already taken by any of the specified elements.
	 * 
	 * @param elements
	 * @param e
	 * @param baseName
	 * @return baseName of baseName_X where is is the number starting from 2
	 */
	public static String getNextAvailableName(List<? extends MethodElement> elements, String baseName) {
		if(isNameTaken(elements, null, baseName)) {
			for (int i = 2; true; i++) {
				String name = baseName + '_' + i;
				if (!isNameTaken(elements, null, name)) {
					return name;
				}
			}
		}
		return baseName;
	}


	public static void setDefaultName(List<? extends MethodElement> siblings, MethodElement e,
			String baseName) {
		if (e.getName() != null && e.getName().trim().length() > 0)
			return;

		if (!isNameTaken(siblings, e, baseName)) {
			e.setName(baseName);
			setPresentationName(e, baseName);
			return;
		}
		for (int i = 2; true; i++) {
			String name = baseName + '_' + i;
			if (!isNameTaken(siblings, e, name)) {
				e.setName(name);
				setPresentationName(e, name);
				return;
			}
		}
	}
		
	public static void setDefaultName(CustomCategory parent, CustomCategory cc, String baseName) {
		MethodPlugin plugin = UmaUtil.getMethodPlugin(parent);
		ContentPackage pkg = plugin == null ? null : UmaUtil.findContentPackage(plugin,
				ModelStructure.DEFAULT.customCategoryPath);
		if (pkg == null) {
			TngUtil.setDefaultName(parent.getCategorizedElements(), cc, baseName);
			return;
		}
		
		UniqueNameHandler handler = new UniqueNameHandler();
		handler.registerNames(pkg.getContentElements());
		String name = handler.getUniqueName(baseName);
		cc.setName(name);
		cc.setPresentationName(name);
	}

	private static void setPresentationName(MethodElement e, String baseName) {
		if (e instanceof DescribableElement) {
			DescribableElement de = (DescribableElement) e;

			StringBuffer presNameBuf = new StringBuffer();
			StringTokenizer st = new StringTokenizer(baseName, "_"); //$NON-NLS-1$
			while (st.hasMoreTokens()) {
				String aWord = st.nextToken();
				presNameBuf.append(aWord.substring(0, 1).toUpperCase()
						+ aWord.substring(1) + " "); //$NON-NLS-1$
			}

			de.setPresentationName(presNameBuf.toString().trim());
		}
	}

	private static boolean isNameTaken(List<? extends MethodElement> siblings, MethodElement e,
			String name) {
		for (int i = siblings.size() - 1; i > -1; i--) {
			MethodElement sibling = (MethodElement) siblings.get(i);
			if (sibling != e && name.equalsIgnoreCase(sibling.getName())) {
				return true;
			}
		}
		return false;
	}

	public static List extract(Collection collection, Class cls) {
		ArrayList list = new ArrayList();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (cls.isInstance(element)) {
				list.add(element);
			}
		}
		return list;
	}
	
	public static <T> List<T> extractType(Collection<? extends Object> collection, Class<T> cls) {
		ArrayList<T> list = new ArrayList<T>();
		for (Iterator<? extends Object> iter = collection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (cls.isInstance(element)) {
				list.add((T)element);
			}
		}
		return list;
	}


	public static void setDefaultName(IDefaultNameSetter defaultNameSetter,
			Notification msg) {
		if (defaultNameSetter.getInterestedFeatureOwnerClass() != null) {
			int featureId = msg.getFeatureID(defaultNameSetter
					.getInterestedFeatureOwnerClass());
			if (featureId == defaultNameSetter.getInterestedFeatureID()) {
				switch (msg.getEventType()) {
				case Notification.ADD:
					defaultNameSetter.setDefaultName(msg.getNewValue());
					break;
				case Notification.ADD_MANY:
					for (Iterator iter = ((Collection) msg.getNewValue())
							.iterator(); iter.hasNext();) {
						defaultNameSetter.setDefaultName(iter.next());
					}
					break;
				}
			}
		}
	}

	/**
	 * @param baseCategory
	 * @param methodConf
	 * @param children
	 */
	public static void addExtendedChildren(ContentCategory baseCategory,
			MethodConfiguration methodConf, Collection children,
			String[] categoryPkgPath) {
		Map<String, Boolean> map = new HashMap<String, Boolean>(); 
		MethodPlugin basePlugin = UmaUtil.getMethodPlugin(baseCategory);
		for (Iterator iter = methodConf.getMethodPluginSelection().iterator(); iter
				.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) iter.next();
			if (Misc.isBaseOf(basePlugin, plugin, map)) {
				ContentPackage categoryPkg = UmaUtil.findContentPackage(plugin,
						categoryPkgPath);
				if (categoryPkg != null
						&& methodConf.getMethodPackageSelection().contains(
								categoryPkg)) {
					ContentCategory category = findInherited(categoryPkg
							.getContentElements(), baseCategory);
					if (category != null) {
						ItemProviderAdapter adapter = (ItemProviderAdapter) TngAdapterFactory.INSTANCE
								.getNavigatorView_ComposedAdapterFactory()
								.adapt(category, ITreeItemContentProvider.class);
						if (adapter == null) {
							System.err
									.println("DisciplineGroupingItemProvider.addExtendedChildren(): could not find adapter for " + category); //$NON-NLS-1$
						}
						for (Iterator iterator = adapter.getChildren(category)
								.iterator(); iterator.hasNext();) {
							Object child = (Object) iterator.next();
							ContentElement element = null;
							if (child instanceof ContentElement) {
								element = (ContentElement) child;
							}
							if (element != null
									&& element.getVariabilityBasedOnElement() == null) {
								// this is an own child of an extended category
								// it must be added to the child list of the
								// base category in the configuration.
								//
								children.add(child);
							}
						}

						// collect the own children of the extended categories
						// of this category down the inheritance tree
						//
						addExtendedChildren(category, methodConf, children,
								categoryPkgPath);
					}
				}
			}
		}
	}
	
	public static void addAllTo(List allChildren, List newChildren) {
		int id;
		BreakdownElement child, next, last;
		ArrayList list = new ArrayList();
		while(!newChildren.isEmpty()) {
			child = (BreakdownElement) newChildren.remove(0);
			list.clear();
			list.add(child);
			last = null;
			
			// find the last element of the chain in new children list
			//
			find_last:
			for(next = child.getPresentedAfter(); next != null; next = next.getPresentedAfter()) {
				last = next;
				id = indexOfNextElement(newChildren, next);
				if(id == -1) {
					break find_last;
				}
				else {
					list.add(newChildren.remove(id));
				}
			}
			if(last != null) {
				id = indexOfNextElement(allChildren, last);
				if(id == -1) {
					allChildren.addAll(list);
				}
				else {
					allChildren.addAll(id, list);
				}
			}
			else {
				allChildren.add(child);
			}
		}
	}

	/**
	 * Adds the given child to the allChildren list based on the presentedBefore
	 * of child.
	 * 
	 * @param allChildren
	 * @param child
	 */
	public static void addTo(List allChildren, BreakdownElement child,
			Object owner, AdapterFactory adapterFactory) {
		BreakdownElement next = child.getPresentedAfter();
		int id;
		if (next == null) {
			id = -1;
		} else {
			id = indexOfNextElement(allChildren, next);
			// if(id == -1) {
			// // System.out.println("TngUtil.addTo(): invalid presentedBefore
			// of " + child);
			// BreakdownElement prev = child.getPresentedBefore();
			// if(prev == null) {
			// id = 0;
			// }
			// else {
			// id = indexOf(allChildren, prev);
			// if(id > -1) {
			// if(id < allChildren.size() - 1) {
			// id++;
			// }
			// else {
			// id = -1;
			// }
			// }
			// }
			// }
		}
		if (id == -1) {
			allChildren.add(child);
		} else {
			allChildren.add(id, child);
		}
	}

	/**
	 * Gets the index of next element of the breakdown element whose presented-after element is the given 
	 * break down element
	 * @param mixedList
	 *            mixed list of BreakdownElementWrapperItemProvider and
	 *            BreakdownElement objects
	 * @param presentedAfter
	 * @return
	 */
	private static int indexOfNextElement(List mixedList, BreakdownElement presentedAfter) {
		int size = mixedList.size();
		for (int i = 0; i < size; i++) {
			Object o = unwrap(mixedList.get(i));
			if (o == presentedAfter
					|| (o instanceof VariabilityElement && ((VariabilityElement)o).getVariabilityBasedOnElement() == presentedAfter)) {
				return i;
			}
		}
		return -1;
	}
	
	public static List getAvailableBaseProcesses(MethodPlugin plugin,
			List processClasses) {
		List processes = new ArrayList();
		List allBasePlugins = Misc.getAllBase(plugin);
		allBasePlugins.add(0, plugin);
		for (Iterator iter = allBasePlugins.iterator(); iter.hasNext();) {
			MethodPlugin basePlugin = (MethodPlugin) iter.next();
			Collection packages = getRootProcessPackages(basePlugin,
					processClasses);
			for (Iterator iterator = packages.iterator(); iterator.hasNext();) {
				ProcessPackage pkg = (ProcessPackage) iterator.next();
				for (Iterator iterator1 = pkg.getChildPackages().iterator(); iterator1
						.hasNext();) {
					Object childPkg = (Object) iterator1.next();
					if (childPkg instanceof ProcessComponent) {
						Process proc = ((ProcessComponent) childPkg)
								.getProcess();
						if (isInstanceOf(processClasses, proc)) {
							processes.add(proc);
						}
					}
				}
			}
		}
		return processes;
	}

	private static String[] getRootProcessPackagePath(Class procClass) {
		if (procClass == DeliveryProcess.class) {
			return ModelStructure.DEFAULT.deliveryProcessPath;
		} else if (procClass == CapabilityPattern.class) {
			return ModelStructure.DEFAULT.capabilityPatternPath;
		} else if (procClass == ProcessPlanningTemplate.class) {
			return ModelStructure.DEFAULT.processPlanningTemplatePath;
		}

		return null;
	}

	private static Collection getRootProcessPackages(MethodPlugin plugin,
			Collection procClasses) {
		List packages = new ArrayList();
		for (Iterator iter = procClasses.iterator(); iter.hasNext();) {
			Class clazz = (Class) iter.next();
			String[] path = getRootProcessPackagePath(clazz);
			if (path != null) {
				MethodPackage pkg = UmaUtil.findMethodPackage(plugin, path);
				if (pkg instanceof ProcessPackage) {
					packages.add(pkg);
				}
			}
		}

		return packages;
	}
	
	/**
	 * 
	 * @param plugin
	 * @return true iff the plugin has any ContentCategory objects
	 */
	public static boolean hasCategories(MethodPlugin plugin) {
		List<ContentPackage> conPacks = getContentCategoryPackages(plugin);
		for (Iterator<ContentPackage> conPacksIter = conPacks.iterator();conPacksIter.hasNext();) {
			ContentPackage conPack = conPacksIter.next();
			for (Iterator conPackIter = conPack.getContentElements().iterator();conPackIter.hasNext();) {
				Object o = conPackIter.next();
				if (o instanceof ContentCategory) {
					return true;
				}
			}
		}
		return false;
	}		

	public static List<Process> getAllProcesses(MethodPlugin plugin) {
		List<Process> processes = new ArrayList<Process>();
		for (MethodPackage pkg : plugin.getMethodPackages()) {
			_iteratePackageForProcesses(pkg, processes);
		}

		return processes;
	}

	public static List<Process> getAllProcesses(MethodPackage pkg) {
		List<Process>  processes = new ArrayList<Process> ();
		_iteratePackageForProcesses(pkg, processes);

		return processes;
	}
	
	private static void _iteratePackageForProcesses(MethodPackage pkg,
			List processes) {
		if (pkg instanceof ProcessComponent) {
			Process p = ((ProcessComponent) pkg).getProcess();
			if (p != null && !processes.contains(p)) {
				processes.add(p);
			}
		}

		for (Iterator it = pkg.getChildPackages().iterator(); it.hasNext();) {
			_iteratePackageForProcesses((MethodPackage) it.next(), processes);
		}
	}

	/**
	 * Checks if e is a subelement of parent
	 * 
	 * @param parent
	 * @param e
	 * @param adapterFactory
	 * @return
	 */
	public static boolean isSubelementOf(Object parent, Object e,
			AdapterFactory adapterFactory) {
		if (parent == e)
			return false;
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory
				.adapt(parent, ITreeItemContentProvider.class);
		for (Iterator<?> iter = adapter.getChildren(parent).iterator(); iter
				.hasNext();) {
			Object element = (Object) iter.next();
			if (element == e)
				return true;
			if (isSubelementOf(element, e, adapterFactory)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @param child
	 * @param e
	 * @param adapterFactory
	 * @return
	 */
	public static boolean isSuperElementOf(Object child, Object e,
			AdapterFactory adapterFactory) {
		if (child == e)
			return false;
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory
				.adapt(e, ITreeItemContentProvider.class);
		for (Iterator<?> iter = adapter.getChildren(e).iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			if (element == child)
				return true;
			if (isSuperElementOf(child, element, adapterFactory)) {
				return true;
			}
		}

		return false;
	}

	public static Object unwrap(Object obj) {
		if (obj instanceof IWrapperItemProvider) {
			do {
				obj = ((IWrapperItemProvider) obj).getValue();
			} while (obj instanceof IWrapperItemProvider);
		} else if (obj instanceof CommandWrapper) {
			do {
				obj = ((CommandWrapper) obj).getCommand();
			} while (obj instanceof CommandWrapper);
		}

		return obj;
	}
	
	public static MethodElement getWrappedElement(Object obj) {
		if (obj instanceof MethodElement) {
			return (MethodElement) obj;
		}
		obj = unwrap(obj);
		if (obj instanceof MethodElement) {
			return (MethodElement) obj;
		}
		
		return null;
	}
	
	/**
	 * Returns boolean value based on whether object is wrapper object or not
	 * @param obj
	 * @return
	 */
	public static boolean isWrapped(Object obj) {
		if (obj instanceof IWrapperItemProvider || obj instanceof CommandWrapper) {
			return true;
		}
		return false;
	}

	public static Command unwrap(Command cmd) {
		while (cmd instanceof CommandWrapper) {
			cmd = ((CommandWrapper) cmd).getCommand();
		}

		return cmd;
	}

	/**
	 * Checks if the given <code>base</code> is the base element of any
	 * element in the given elements
	 * 
	 * @param elements
	 * @param base
	 * @param variabilityTypes variability types to check against, ignored if <code>null</code>
	 * @return
	 */
	public static boolean isBase(List<?> elements, Object base, Set<VariabilityType> variabilityTypes) {
		for (Iterator<?> iter = elements.iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			if (element instanceof VariabilityElement) {
				VariabilityElement ve = ((VariabilityElement) element);
				if (base == ve.getVariabilityBasedOnElement()
						&& (variabilityTypes == null || variabilityTypes
								.contains(ve.getVariabilityType()))) {
					return true;
				}
			}
		}
		return false;
	}
	
	

	public static String getPresentationName(Object e) {
		return getPresentationName(e, null);
	}
	
	public static String getPresentationName(Object e, PresentationNameHelper helper) {
		if (e instanceof ContentDescription) {
			e = ((ContentDescription) e).eContainer();
		}

		if (e == null) {
			return ""; //$NON-NLS-1$
		}

		String name = null;
		
		if (e instanceof MethodElement) {
			name = ((MethodElement) e).getPresentationName();
			if (StrUtil.isBlank(name)) 	{
				if (helper != null) {
					name = helper.getPresentationName((MethodElement) e);
				} 
				if (helper == null || StrUtil.isBlank(name)) {
					name = ((MethodElement) e).getName();
				}
			}
		}

		return name;
	}

	public static Process getOwningProcess(BreakdownElement e) {
		EObject container;
		for (container = e.eContainer(); container != null
				&& !(container instanceof ProcessComponent); container = container
				.eContainer())
			;
		if (container != null) {
			return ((ProcessComponent) container).getProcess();
		}
		return null;
	}
	
	public static Process getOwningProcess(BreakdownElementWrapperItemProvider wrapper) {
		Object top = wrapper.getTopItem();
		if(top instanceof Process) {
			Process proc = (Process) top;
			if(proc.eContainer() instanceof ProcessComponent) {
				return proc;
			}
		}
		return null;
	}
	
	public static Process getOwningProcess(Object object) {
		if(object instanceof BreakdownElement) {
			return getOwningProcess((BreakdownElement)object);
		}
		else if(object instanceof BreakdownElementWrapperItemProvider) {
			return getOwningProcess((BreakdownElementWrapperItemProvider)object);
		}
		return null;
	}

	public static boolean canReference(MethodElement source,
			MethodElement target) {
		MethodPlugin targetPlugin = UmaUtil.getMethodPlugin(target);
		MethodPlugin sourcePlugin = UmaUtil.getMethodPlugin(source);
		if (sourcePlugin == targetPlugin)
			return true;
		return Misc.isBaseOf(targetPlugin, sourcePlugin, new HashMap<String, Boolean>());
	}

	public static OrderInfo getOrderInfo(MethodElement e, String orderInfoName) {
		String str = e.getOrderingGuide();
		if (str == null || str.length() == 0)
			return null;
		Resource res = new StringResource(str);
		try {
			res.load(null);
			if (res.getContents().isEmpty())
				return null;
			OrderInfoCollection orderInfos = (OrderInfoCollection) res
					.getContents().get(0);
			for (Iterator iter = orderInfos.getOrderInfos().iterator(); iter
					.hasNext();) {
				OrderInfo orderInfo = (OrderInfo) iter.next();
				if (orderInfoName.equalsIgnoreCase(orderInfo.getName())) {
					return orderInfo;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * @param provider
	 * @param notification
	 */
	public static void refreshContributors(IChangeNotifier notifier,
			Notification notification, boolean contentRefresh,
			boolean labelUpdate) {
		for (Iterator iter = getContributors((VariabilityElement) notification
				.getNotifier()); iter.hasNext();) {
			Object element = iter.next();
			notifier.fireNotifyChanged(new ViewerNotification(notification,
					element, contentRefresh, labelUpdate));
		}
	}
	
	/**
	 * Used to refresh plugins that extend another
	 * @param notifier
	 * @param notification
	 * @param contentRefresh
	 * @param labelUpdate
	 */
	public static void refreshPluginExtenders(IChangeNotifier notifier,
			Notification notification, boolean contentRefresh,
			boolean labelUpdate) {
		Object obj = notification.getNotifier();
		if (obj instanceof MethodPlugin) {
			for (Iterator iter = AssociationHelper.getPluginDirectExtensions(
					((MethodPlugin) obj)).iterator(); iter.hasNext();) {
				Object element = iter.next();
				notifier.fireNotifyChanged(new ViewerNotification(notification,
						element, contentRefresh, labelUpdate));
			}
		}
	}

	public static boolean hasContributor(VariabilityElement e) {
		return hasGeneralizer(e, Collections
				.singleton(VariabilityType.CONTRIBUTES));
	}

	public static boolean hasGeneralizer(VariabilityElement e,
			Collection variabilityTypes) {
		for (Iterator iterator = AssociationHelper.getImmediateVarieties(e)
				.iterator(); iterator.hasNext();) {
			VariabilityElement element = (VariabilityElement) iterator.next();
			if (variabilityTypes.contains(element.getVariabilityType())) {
				return true;
			}
		}
		return false;
	}

	public static Iterator getImmediateVarieties(VariabilityElement elem, VariabilityType type) {
		List selectedVes = new ArrayList();
		List allVes = AssociationHelper.getImmediateVarieties(elem);
		for (int i=0; i<allVes.size(); i++) {
			VariabilityElement ve = (VariabilityElement) allVes.get(i);
			if (type == ve.getVariabilityType()) {
				selectedVes.add(ve);
			}			
		}
		return selectedVes.iterator();
	}
		
	public static Iterator getGeneralizers(VariabilityElement e,
			final VariabilityType type) {
		return new AbstractTreeIterator(e, false) {

			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 3978145439449298483L;

			protected Iterator getChildren(Object object) {
				List children = new ArrayList();
				for (Iterator iterator = AssociationHelper
						.getImmediateVarieties((VariabilityElement) object)
						.iterator(); iterator.hasNext();) {
					VariabilityElement element = (VariabilityElement) iterator
							.next();
					if ((type == null) || element.getVariabilityType() == type) {
						children.add(element);
					}
				}
				return children.iterator();
			}

		};
	}

	/**
	 * get geenralizers of all types, recursively
	 * 
	 * @param e
	 * @return
	 */
	public static Iterator getGeneralizers(VariabilityElement e) {
		return getGeneralizers(e, null);
	}

	public static Iterator getContributors(VariabilityElement e) {
		return getGeneralizers(e, VariabilityType.CONTRIBUTES);
	}

	public static ItemProviderAdapter getAdapter(MethodPlugin plugin,
			String[] path) {
		IGroupContainer groups = (IGroupContainer) TngAdapterFactory.INSTANCE
				.getNavigatorView_ComposedAdapterFactory().adapt(plugin,
						ITreeItemContentProvider.class);
		int i;
		for (i = 0; i < path.length - 1; i++) {
			groups = (IGroupContainer) groups.getGroupItemProvider(path[i]);
			if (groups == null)
				return null;
		}
		return (ItemProviderAdapter) groups.getGroupItemProvider(path[i]);
	}

	private static void refreshAdapter(MethodPlugin plugin, String[] path,
			Notification msg) {
		if (plugin == null) {
			return;
		}
		ItemProviderAdapter adapter = getAdapter(plugin, path);
		if (adapter != null) {
			adapter.fireNotifyChanged(new ViewerNotification(msg, adapter,
					true, false));
		}
	}

	public static void refreshAdapter(String[] path, Notification notification) {
		EObject eObj = null;
		switch (notification.getEventType()) {
		case Notification.ADD:
			eObj = (EObject) notification.getNewValue();
			break;
		case Notification.ADD_MANY:
			eObj = (EObject) new ArrayList((Collection) notification
					.getNewValue()).get(0);
			break;
		case Notification.REMOVE:
			eObj = (EObject) notification.getOldValue();
			break;
		case Notification.REMOVE_MANY:
			eObj = (EObject) new ArrayList((Collection) notification
					.getOldValue()).get(0);
			break;
		}
		if (eObj != null) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin(eObj);
			TngUtil.refreshAdapter(plugin, path, notification);
		}
	}

	public static void refreshUncategorizedTasksItemProvider(
			MethodPlugin plugin, Notification msg) {
		// refresh the uncategorized tasks group
		//

		refreshAdapter(plugin, LibraryEditConstants.UNCATEGORIZED_TASKS_PATH,
				msg);
	}

	public static void refreshDomainUncategorizedWorkProductsItemProvider(
			MethodPlugin plugin, Notification msg) {
		refreshAdapter(plugin,
				LibraryEditConstants.UNCATEGORIZED_DOMAIN_WORKPRODUCTS_PATH,
				msg);
	}

	public static void refreshWorkProductTypeUncategorized(MethodPlugin plugin,
			Notification msg) {
		refreshAdapter(
				plugin,
				LibraryEditConstants.UNCATEGORIZED_WORKPRODUCTTYPE_WORKPRODUCTS_PATH,
				msg);
	}

	public static void refreshUncategorizedWorkProductsItemProviders(
			MethodPlugin plugin, Notification msg) {
		refreshDomainUncategorizedWorkProductsItemProvider(plugin, msg);
		refreshWorkProductTypeUncategorized(plugin, msg);
	}

	public static void refreshUncategorizedRolesItemProvider(
			MethodPlugin plugin, Notification msg) {
		refreshAdapter(plugin, LibraryEditConstants.UNCATEGORIZED_ROLES_PATH,
				msg);
	}

	public static void refreshUncategorizedToolMentorsItemProvider(
			MethodPlugin plugin, Notification msg) {
		refreshAdapter(plugin,
				LibraryEditConstants.UNCATEGORIZED_TOOLMENTORS_PATH, msg);

	}

	public static Object createWrapper(AdapterFactory adapterFactory,
			EObject object, EStructuralFeature feature, Object value, int index) {
		if (FeatureMapUtil.isFeatureMap(feature)) {
			value = new FeatureMapEntryWrapperItemProvider(
					(FeatureMap.Entry) value, object, (EAttribute) feature,
					index, adapterFactory, null);
		} else if (feature instanceof EAttribute) {
			value = new AttributeValueWrapperItemProvider(value, object,
					(EAttribute) feature, index, adapterFactory, null);
		} else if (!((EReference) feature).isContainment()) {
			value = new FeatureValueWrapperItemProvider(feature, value, object,
					adapterFactory);
		}

		return value;
	}

	public static Object getNavigatorParentItemProvider(Guidance guidance) {
		EObject container = guidance.eContainer();
		if (!(container instanceof ContentPackage))
			return null;
		ContentPackageItemProvider itemProvider = (ContentPackageItemProvider) TngUtil
				.getAdapter(container, ContentPackageItemProvider.class);
		return itemProvider != null ? itemProvider.getGuidances() : null;
	}

	public static Object getNavigatorParentItemProvider(WorkProduct object) {
		EObject contentPkg = object.eContainer();
		if (contentPkg == null)
			return null;
		ContentPackageItemProvider itemProvider = (ContentPackageItemProvider) TngUtil
				.getAdapter(contentPkg, ContentPackageItemProvider.class);
		return itemProvider != null ? itemProvider.getWorkProducts() : null;
	}
	
	public static Object getImage(Object object) {
		Object image = TngUtil.getCustomNodeIcon(object);
		if(image != null) {
			return image;
		}
		
		//For user defined type
//		if ((object instanceof Practice) && (PracticePropUtil.getPracticePropUtil().isUdtType((Practice)object))) {
//			ImageDescriptor desc = getImageForUdt((Practice)object);
//			if (desc != null) {
//				return desc;
//			}
//		}
		
		if ((object instanceof Practice) && (PracticePropUtil.getPracticePropUtil().isUdtType((Practice)object))) {
			URL url = getImageForUdt2((Practice)object);
			if (url != null) {
				return url;
			}
			return UmaEditPlugin.INSTANCE.getImage("full/obj16/UdtNode");
		}	
		
		Object adapter = null;
		try {
			adapter = umaItemProviderAdapterFactory.adapt(object,
					ITreeItemContentProvider.class);
			if (adapter instanceof IItemLabelProvider) {
				return ((IItemLabelProvider) adapter).getImage(object);
			}
		}
		finally {
			if(adapter != object && adapter instanceof IDisposable) {
				((IDisposable)adapter).dispose();
			}
		}
		return null;
	}
	
	public static ImageDescriptor getImageForUdt(Practice prac) {
		try {
			UserDefinedTypeMeta udtMeta = PracticePropUtil.getPracticePropUtil().getUtdData(prac);
			String imageUrl = udtMeta.getRteNameMap().get(UserDefinedTypeMeta._icon);
			if (imageUrl == null) {
				return null;
			}
			return ImageDescriptor.createFromURL(new URL(imageUrl));
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
		
		return null;
	}
	
	public static URL getImageForUdt2(Practice prac) {
		try {
			UserDefinedTypeMeta udtMeta = PracticePropUtil.getPracticePropUtil().getUtdData(prac);
			String imageUrl = udtMeta.getRteNameMap().get(UserDefinedTypeMeta._icon);
			if (imageUrl == null) {
				return null;
			}
			return new URL(imageUrl);
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
		
		return null;
	}
	
	private static String getTypeNameForUdt(Practice prac) {
		try {
			UserDefinedTypeMeta udtMeta = PracticePropUtil.getPracticePropUtil().getUtdData(prac);
			return udtMeta.getRteNameMap().get(UserDefinedTypeMeta._typeName);
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}
		
		return null;
	}

	public static Object getColumnImage(Object object, String colName) {
		// if(colName == IBSItemProvider.COL_TYPE)
		// if(colName == IBSItemProvider.COL_NAME)
		if (colName == IBSItemProvider.COL_PRESENTATION_NAME) {
			return getImage(object);
		}
		return null;
	}

	/**
	 * Move element up withing the parent
	 * 
	 * @param activity
	 * @param elementObj
	 * @param eClasses
	 * @param actionMgr
	 */
	public static void moveUp(Activity activity, Object elementObj,
			Collection eClasses, IActionManager actionMgr) {
		MoveUpCommand cmd = new MoveUpCommand(activity, elementObj, eClasses);
		actionMgr.execute(cmd);
	}

	/**
	 * Move element down within the parent
	 * 
	 * @param activity
	 * @param elementObj
	 * @param eClasses
	 * @param actionMgr
	 */
	public static void moveDown(Activity activity, Object elementObj,
			Collection eClasses, IActionManager actionMgr) {
		MoveDownCommand cmd = new MoveDownCommand(activity, elementObj,
				eClasses);
		actionMgr.execute(cmd);
	}

	public static CustomCategory createRootCustomCategory(
			ContentPackage customCategoryPkg) {
		// create root custom category
		//
		CustomCategory rootCustomCategory = UmaFactory.eINSTANCE
				.createCustomCategory();
		rootCustomCategory.setName(LibraryEditPlugin.INSTANCE
				.getString("_UI_Custom_Categories_group")); //$NON-NLS-1$
		ContentPackage hiddenPkg = UmaUtil.findContentPackage(customCategoryPkg
				.getChildPackages(), ModelStructure.HIDDEN_PACKAGE_NAME);
		if (hiddenPkg == null) {
			// create hidden package to store the root custom category
			//
			hiddenPkg = UmaFactory.eINSTANCE.createContentPackage();
			hiddenPkg.setName(ModelStructure.HIDDEN_PACKAGE_NAME);
			customCategoryPkg.getChildPackages().add(hiddenPkg);
		}
		hiddenPkg.getContentElements().add(rootCustomCategory);

		return rootCustomCategory;
	}

	public static Set<CustomCategory> getAllCustomCategories(MethodPlugin plugin) {
		Set<CustomCategory> set = new HashSet<CustomCategory>();
		ContentPackage customCategoryPkg = UmaUtil.findContentPackage(plugin,
				ModelStructure.DEFAULT.customCategoryPath);
		if (customCategoryPkg != null) {
			for (ContentElement element : customCategoryPkg
					.getContentElements()) {
				if (element instanceof CustomCategory) {
					set.add((CustomCategory) element);
				}
			}
		}
		return set;
	}
	
	public static CustomCategory getRootCustomCategory(MethodPlugin plugin) {
		ContentPackage customCategoryPkg = UmaUtil.findContentPackage(plugin,
				ModelStructure.DEFAULT.customCategoryPath);
		if (customCategoryPkg == null)
			return null;
		ContentPackage hiddenPkg = UmaUtil.findContentPackage(customCategoryPkg
				.getChildPackages(), ModelStructure.HIDDEN_PACKAGE_NAME);
		if (hiddenPkg != null && !hiddenPkg.getContentElements().isEmpty()) {
			return (CustomCategory) hiddenPkg.getContentElements().get(0);
		}
		CustomCategory root = createRootCustomCategory(customCategoryPkg);
		// code for backward compatibility: add all the custom categories under
		// customCategoryPkg
		// to the content element reference list of the root custom category so
		// they can be visible
		// in the new UI
		//
		for (Iterator iter = customCategoryPkg.getContentElements().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (element instanceof CustomCategory) {
				root.getCategorizedElements().add((DescribableElement) element);
			}
		}
		return root;
	}

	public static boolean isRootCustomCategory(CustomCategory category) {
		EObject pkg = category.eContainer();
		if (pkg instanceof ContentPackage
				&& ModelStructure.HIDDEN_PACKAGE_NAME
						.equals(((ContentPackage) pkg).getName())) {
			return true;
		}
		return false;
	}

	public static boolean isRootCutomCategoryPackage(MethodPackage pkg) {
		if ( !(pkg instanceof ContentPackage) ||
				!ModelStructure.HIDDEN_PACKAGE_NAME
						.equals(pkg.getName()) ) {
			return false;
		}
		
		// check the plugin and make sure it's the object, TODO
		
		return true;
	}
	
	/**
	 * Saves resource of the given element's container if this has been modified
	 * 
	 * @param element
	 */
	public static void saveContainerResource(EObject element,
			IFileBasedLibraryPersister persister) {
		// save container's resource
		if (element.eContainer() != null) {
			Resource resource = element.eContainer().eResource();
			if (resource != null && resource.isModified()) {
				try {
					persister.save(resource);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static ContentCategory findContributor(ContentPackage categoryPkg,
			ContentCategory baseCategory) {
		for (Iterator iter = categoryPkg.getContentElements().iterator(); iter
				.hasNext();) {
			ContentCategory cat = (ContentCategory) iter.next();
			if (cat.getVariabilityBasedOnElement() == baseCategory) {
				return cat;
			}
		}
		return null;
	}

	public static boolean isEmpty(ContentCategory category) {
		if (!category.getConceptsAndPapers().isEmpty()
				|| !category.getSupportingMaterials().isEmpty())
			return false;

		if (category instanceof Discipline) {
			return ((Discipline) category).getTasks().isEmpty();
		} else if (category instanceof Domain) {
			return ((Domain) category).getWorkProducts().isEmpty();
		} else if (category instanceof RoleSet) {
			return ((RoleSet) category).getRoles().isEmpty();
		} else if (category instanceof Tool) {
			return ((Tool) category).getToolMentors().isEmpty();
		} else if (category instanceof WorkProductType) {
			return ((WorkProductType) category).getWorkProducts().isEmpty();
		} else if (category instanceof CustomCategory) {
			CustomCategory userDefinedCategory = ((CustomCategory) category);
			return userDefinedCategory.getCategorizedElements().isEmpty()
			// && userDefinedCategory.getSubCategories().isEmpty()
			;
		}

		throw new IllegalArgumentException("Unknown category: " + category); //$NON-NLS-1$
	}

	/**
	 * @param order
	 */
	private static void addToPackage(WorkOrder order, BreakdownElement e) {
		EObject container = e.eContainer();
		if(container != null) {
			((ProcessPackage) container).getProcessElements().add(order);
		}
	}

	/**
	 * @param order
	 */
	private static void removeFromPackage(WorkOrder order, BreakdownElement e) {
		EObject container = e.eContainer();
		if(container instanceof ProcessPackage) {
			((ProcessPackage) container).getProcessElements().remove(order);
		}
	}

	public static boolean handlePredecessorListChange(
			ItemProviderAdapter adapter, Notification notification) {
		if (notification.getNotifier() instanceof WorkBreakdownElement) {
			switch (notification.getFeatureID(WorkBreakdownElement.class)) {
			case UmaPackage.ACTIVITY__LINK_TO_PREDECESSOR:
				switch (notification.getEventType()) {
				case Notification.ADD:
					addToPackage((WorkOrder) notification.getNewValue(),
							(BreakdownElement) notification.getNotifier());
					break;
				case Notification.ADD_MANY:
					for (Iterator iter = ((Collection) notification
							.getNewValue()).iterator(); iter.hasNext();) {
						addToPackage((WorkOrder) iter.next(),
								(BreakdownElement) notification.getNotifier());
					}
					break;
				case Notification.REMOVE:
					removeFromPackage((WorkOrder) notification.getOldValue(),
							(BreakdownElement) notification.getNotifier());
					break;
				case Notification.REMOVE_MANY:
					for (Iterator iter = ((Collection) notification
							.getOldValue()).iterator(); iter.hasNext();) {
						removeFromPackage((WorkOrder) iter.next(),
								(BreakdownElement) notification.getNotifier());
					}
					break;

				}

				adapter.fireNotifyChanged(new ViewerNotification(notification,
						notification.getNotifier(), false, true));
				return true;
			}
		}
		return false;
	}

	/**
	 * return all the global packages for the method plugin
	 * 
	 * @param plugin
	 *            MethodPlugin
	 * @return List a list of global packages
	 */
	public static List<ContentPackage> getGlobalPackages(MethodPlugin plugin) {
		ArrayList<ContentPackage> packages = new ArrayList<ContentPackage>();
		String[][] categoryPaths = ModelStructure.DEFAULT.getCategoryPaths();
		for (int i = 0; i < categoryPaths.length; i++) {
			String[] path = categoryPaths[i];
			ContentPackage categoryPkg = UmaUtil.findContentPackage(plugin,
					path);
			if (categoryPkg != null) {
				packages.add(categoryPkg);
			}
		}

		return packages;
	}

	
	/**
	 * return all the content category packages for the method plugin. 
	 * 
	 * @param plugin
	 *            MethodPlugin
	 * @return List a list of global packages
	 */
	public static List<ContentPackage> getContentCategoryPackages(MethodPlugin plugin) {
		return getGlobalPackages(plugin);
	}
	
	
	/**
	 * return all the system defined packages for the method plugin
	 * 
	 * @param plugin
	 *            MethodPlugin
	 * @return List a list of all system packages
	 */
	public static List getAllSystemPackages(MethodPlugin plugin) {
		ArrayList packages = new ArrayList();
		String[][] paths = ModelStructure.DEFAULT.getAllSystemPaths();
		for (int i = 0; i < paths.length; i++) {
			String[] path = paths[i];
			MethodPackage pkg = UmaUtil.findMethodPackage(plugin, path);
			if (pkg != null && !packages.contains(pkg)) {
				packages.add(pkg);
				EObject o = pkg.eContainer();
				while ((o instanceof MethodPackage) && !packages.contains(o)) {
					packages.add(o);
					o = o.eContainer();
				}
			}
		}

		// also add the hidden costom category package
		ContentPackage customCategoryPkg = UmaUtil.findContentPackage(plugin,
				ModelStructure.DEFAULT.customCategoryPath);
		if (customCategoryPkg != null) {
			ContentPackage hiddenPkg = UmaUtil.findContentPackage(
					customCategoryPkg.getChildPackages(),
					ModelStructure.HIDDEN_PACKAGE_NAME);
			if (hiddenPkg != null) {
				packages.add(hiddenPkg);
			}
		}

		return packages;
	}

	public static boolean isPredefined(MethodElement e) {
		if (e instanceof CustomCategory
				&& isRootCustomCategory((CustomCategory) e))
			return true;
		String path = ModelStructure
				.toFilePath(Misc.getPathRelativeToPlugin(e));
		for (Iterator iter = ModelStructure.ALL_PREDEFINED_PATHS.iterator(); iter
				.hasNext();) {
			String predefinePath = (String) iter.next();
			if (predefinePath.startsWith(path)) {
				return true;
			}
		}
		return false;
	}


	public static MethodPackage getMethodPackage(EObject e) {
		for (; !(e instanceof MethodPackage) && e != null; e = e.eContainer())
			;
		return (MethodPackage) e;
	}
	
	public static MethodPackage getParentMethodPackage(EObject e) {
		EObject eobj = e;
		while(eobj != null) {
			eobj = eobj.eContainer();
			if (eobj == null ) {
				return null;
			} else if (eobj instanceof MethodPackage) {
				return (MethodPackage) eobj;
			}
		}
		return  null;
	}

	/**
	 * validate the configuration by forcing to select the global packages of
	 * the selected method plugins, this is needed for configuration exporting.
	 * If global packages are missing, the exported configuration is not valid
	 * 
	 * @param actionMgr if not null, will use the given IActionManager to change the configuration, otherwise configuration will be changed directly
	 * @param plugin
	 */
	public static void validateMethodConfiguration(IActionManager actionMgr, MethodConfiguration config) {
		List plugins = new ArrayList(config.getMethodPluginSelection());
		List pkgSels = new ArrayList(config.getMethodPackageSelection());
		List<MethodPlugin> pluginsToAdd = new ArrayList<MethodPlugin>();
		List<MethodPackage> packagesToAdd = new ArrayList<MethodPackage>();

		// make sure all required packages are selected.
		// if a package is selected, all the parent packages must be selected.
		for ( Iterator it = new ArrayList(pkgSels).iterator(); it.hasNext(); ) {
			MethodPackage pkg = (MethodPackage)it.next();
			EObject c = pkg.eContainer();
			while ( ((c instanceof MethodPackage) || (c instanceof MethodPlugin)) 
					&& !pkgSels.contains(c) && !plugins.contains(c) ) {
				if (c instanceof MethodPlugin) {
					plugins.add(c);
					pluginsToAdd.add((MethodPlugin)c);
				} else {
					pkgSels.add(c);
					packagesToAdd.add((MethodPackage)c);
				}
				
				c = c.eContainer();
			}	
			
			// if the package is a ProcessComponent, select all the child packages
			if ( pkg instanceof ProcessComponent ) {
				List oldPkgSels = new ArrayList(pkgSels);
				getAllChildPackages(pkg, pkgSels);
				pkgSels.removeAll(oldPkgSels);
				packagesToAdd.addAll(pkgSels);
			}
		}
		ConfigurationUtil.addCollToMethodPluginList(actionMgr, config, pluginsToAdd);
		ConfigurationUtil.addCollToMethodPackageList(actionMgr, config, packagesToAdd);
		pkgSels = new ArrayList(config.getMethodPackageSelection());

		// make sure all system packages are selected
		List<MethodPackage> sysPackagesToAdd = new ArrayList<MethodPackage>();
		for (Iterator itp = plugins.iterator(); itp.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) itp.next();
			List pkgs = TngUtil.getAllSystemPackages(plugin);
			for (Iterator it = pkgs.iterator(); it.hasNext();) {
				Object pkg = it.next();
				if (!pkgSels.contains(pkg) && pkg instanceof ContentPackage) {
					pkgSels.add(pkg);
					sysPackagesToAdd.add((MethodPackage)pkg);
				}
			}
		}
		ConfigurationUtil.addCollToMethodPackageList(actionMgr, config, sysPackagesToAdd);
	}
	
	/**
	 * get all child packages of the given MethodPackage and add to the list
	 * @param pkg MethodPackage
	 * @param result List the packages found
	 */
	private static void getAllChildPackages(MethodPackage pkg, List result) {
		List pkgs = pkg.getChildPackages();
		
		// only add unique items
		for ( Iterator it = pkgs.iterator(); it.hasNext(); ) {
			Object p = it.next();
			if ( !result.contains(p) ) {
				result.add(p);
			}
		}

		for (Iterator it = pkgs.iterator(); it.hasNext();) {
			getAllChildPackages((MethodPackage) it.next(), result);
		}
	}
	
	/**
	 * Adds the given object's method package and plugin to the given
	 * configuration if they are not in the configuration yet.
	 * 
	 * @param config
	 * @param object
	 */
	public static boolean addTo(MethodConfiguration config, EObject object,
			Set addedObjects) {
		MethodPackage pkg = getMethodPackage(object);
		if (pkg != null) {
			if (!config.getMethodPackageSelection().contains(pkg)) {
				config.getMethodPackageSelection().add(pkg);
				addedObjects.add(pkg);
				MethodPlugin plugin = UmaUtil.getMethodPlugin(pkg);
				
				// 150552 - Processes: If configuration is updated by itself due to actions like CP extendiing or deep copy, 
				// there is a refresh issue.
				// missing the "!" here!
				if (!config.getMethodPluginSelection().contains(plugin)) {
					config.getMethodPluginSelection().add(plugin);
					addedObjects.add(plugin);
				
					// need to validate the method configuration to make sure all related system packages are selected.
					// otherwise the configuration explorer will not work
					// 150552 - Processes: If configuration is updated by itself due to actions like CP extendiing or deep copy, there is a refresh issue.		
					validateMethodConfiguration(null, config);
				}
				return true;
			}
		}
		return false;
	}

	public static boolean addToConfiguration(MethodConfiguration config,
			EObject object, Set addedObjects) {
		int size = addedObjects.size();
		addTo(config, object, addedObjects);

		if (object instanceof VariabilityElement) {
			// add all the base elements if there is any
			for (VariabilityElement c = ((VariabilityElement) object)
					.getVariabilityBasedOnElement(); c != null; c = (VariabilityElement) c
					.getVariabilityBasedOnElement()) {
				addTo(config, c, addedObjects);
			}
		}

		return addedObjects.size() > size;
	}

	/**
	 * 
	 * @param eObj
	 * @param context
	 *            the <code>org.eclipse.swt.widgets.Shell</code> that is to be
	 *            used to parent any dialogs with the user, or <code>null</code>
	 *            if there is no UI context (declared as an <code>Object</code>
	 *            to avoid any direct references on the SWT component)
	 * @return
	 */
	public static IStatus checkEdit(EObject eObj, Object context) {
		if (((InternalEObject) eObj).eProxyURI() != null) {
			// this is a unresolved proxy, disallow editing it
			//
			EObject proxy = eObj;
			String uri = ((InternalEObject) proxy).eProxyURI().toString();
			org.eclipse.emf.common.util.URI containerURI = proxy.eContainer() != null ? proxy
					.eContainer().eResource().getURI()
					: null;
			String location;
			if (containerURI != null) {
				location = containerURI != null && containerURI.isFile() ? containerURI.toFileString()
						: ""; //$NON-NLS-1$
				if (location.length() != 0) {
					location = MessageFormat.format(
							" in ''{0}''", new Object[] { location }); //$NON-NLS-1$
				}
			} else {
				location = ""; //$NON-NLS-1$
			}
			String msg = MessageFormat.format(LibraryEditResources.unresolvedObjectError_reason
					, new Object[] { proxy.eClass().getName(), uri, location });
			return new Status(IStatus.ERROR, LibraryEditPlugin.INSTANCE
					.getSymbolicName(), 0, msg, null);
		}				
		
		IAccessController ac = Services.getAccessController();
		if(ac == null) {
			return Status.OK_STATUS;
		}
		return ac.checkModify(Collections.singletonList(eObj), context);
	}
	
	/**
	 * Check the given element for modifiable
	 * 
	 * @param e
	 * @throws MessageException if the element cannot be modified
	 */
	public static void checkModify(EObject e) {
		IAccessController ac = Services.getAccessController();
		if(ac != null) {
			IStatus status = ac.checkModify(Collections.singletonList(e), null);
			if (!status.isOK()) {
				String msg = UmaUtil.getMessage(status);
				if (msg == null) {
					Object obj;
					if(e.eResource().getURI().isFile()) {
						obj = e.eResource().getURI().toFileString();
					}
					else {
						obj = e; 
					}
					msg = MessageFormat.format(UmaResources.err_cannotModify0,
							new Object[] { obj });
				}
				throw new MessageException(msg);
			}
		}
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
		if (msg == null || msg.length() == 0) {
			msg = LibraryEditResources.util_tngutil_cannot_edit_resource; 
		}
		return msg;
	}

	public static boolean isContributor(VariabilityElement e) {
		return e.getVariabilityBasedOnElement() != null
		&& (e.getVariabilityType() == VariabilityType.CONTRIBUTES);
	}
	
	public static boolean isReplacer(VariabilityElement e) {
		return e.getVariabilityBasedOnElement() != null
		&& (e.getVariabilityType() == VariabilityType.REPLACES);
	}
	
	public static boolean isContributorOrReplacer(VariabilityElement e) {
		VariabilityElement base = e.getVariabilityBasedOnElement();
		VariabilityType type = e.getVariabilityType();
		return base != null && (type == VariabilityType.CONTRIBUTES || type == VariabilityType.REPLACES);
	}

	public static boolean isGeneralizer(Object obj, Collection types) {
		if (!(obj instanceof VariabilityElement))
			return false;
		VariabilityElement ve = (VariabilityElement) obj;
		if (ve.getVariabilityBasedOnElement() == null)
			return false;
		if (types == null)
			return true;
		return types.contains(ve.getVariabilityType());
	}

	public static String getTypeText(String typeKey) {
		try {
			return UmaEditPlugin.INSTANCE.getString("_UI_" + typeKey + "_type"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (MissingResourceException e) {
		}
		return UmaEditPlugin.INSTANCE.getString("_UI_Unknown_type"); //$NON-NLS-1$		
	}

	/**
	 * Looks up the user friendly type name for a Method element.
	 */
	public static String getTypeText(EObject element) {
		//For user defined type
		if ((element instanceof Practice) && (PracticePropUtil.getPracticePropUtil().isUdtType((Practice)element))) {
			String typeName = getTypeNameForUdt((Practice)element);
			if (typeName != null) {
				return typeName;
			}
		}		
		
		return getTypeText(element.eClass());
	}
	
	/**
	 * Looks up the user-friendly type name for a given type.
	 * 
	 * @param type
	 * @return
	 */
	public static String getTypeText(EClass type) {
		return getTypeText(type.getName());
	}

	/**
	 * This looks up the name of the specified feature.
	 */
	public static String getFeatureText(Object feature) {
		if (feature instanceof EStructuralFeature) {
			return getFeatureText((EStructuralFeature) feature);
		} else {
			return "Unknown"; //$NON-NLS-1$
		}
	}

	/**
	 * Checks if the given object is locked. An object is considered locked if
	 * its MethodPlugin has the attribute userChangeable set to false. The
	 * 'locked' status of an object is independent from 'read-only' status of
	 * its resource file.
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isLocked(EObject object) {
		MethodPlugin plugin = UmaUtil.getMethodPlugin(object);
		if (plugin == null)
			return false;
		return !plugin.getUserChangeable().booleanValue();
	}

	public static boolean checkExist(Collection wrappers, Object e) {
		for (Iterator iterator = wrappers.iterator(); iterator.hasNext();) {
			Object element = unwrap(iterator.next());
			if (element == e) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the index of element or its wrapper in the given list
	 * @param list a mixed list of element and wrapper
	 * @param e
	 * @return -1 if the element or its wrapper could not be found in the list
	 */
	public static int getIndexOf(List list, Object e) {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Object element = unwrap(list.get(i));
			if(element == e) {
				return i;
			}
		}
		return -1;
	}

	public static EStructuralFeature getContainingFeature(Object object,
			AdapterFactory adapterFactory) {
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory
				.adapt(object, ITreeItemContentProvider.class);
		if (adapter == null)
			return null;
		Object parent = adapter.getParent(object);
		if (!(parent instanceof EObject))
			return null;
		adapter = (ITreeItemContentProvider) adapterFactory.adapt(parent,
				ITreeItemContentProvider.class);
		if (adapter instanceof MethodElementItemProvider) {
			EObject parentEObj = (EObject) parent;
			for (Iterator iter = ((MethodElementItemProvider) adapter)
					.getChildrenFeatures(parent).iterator(); iter.hasNext();) {
				EStructuralFeature f = (EStructuralFeature) iter.next();
				if (f.isMany()) {
					if (((Collection) parentEObj.eGet(f)).contains(object)) {
						return f;
					}
				} else {
					if (parentEObj.eGet(f) == object) {
						return f;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * Looks up the user-friendly name for a given feature.
	 * 
	 * @param feature
	 * @return
	 */
	public static String getFeatureText(EStructuralFeature feature) {
		switch (feature.getFeatureID()) {
		// case UmaPackage.TASK_DESCRIPTOR__PERFORMED_PRIMARILY_BY:
		// return "Primary Performer";
		// case UmaPackage.TASK_DESCRIPTOR__ADDITIONALLY_PERFORMED_BY:
		// return "Secondary Performer";
		case UmaPackage.TASK_DESCRIPTOR__ASSISTED_BY:
			return LibraryEditResources.assists_text; 
		}
		ExtendedReference eRef = TypeDefUtil.getInstance().getAssociatedExtendedReference(feature);
		if (eRef != null) {
			return eRef.getName();
		}

		String featureKey;
		if (feature instanceof EStructuralFeature) {
			EStructuralFeature eFeature = (EStructuralFeature) feature;
			featureKey = eFeature.getEContainingClass().getName()
					+ "_" + eFeature.getName(); //$NON-NLS-1$
		} else {
			featureKey = LibraryEditResources.unknown_text; 
		}
		return UmaEditPlugin.INSTANCE
				.getString("_UI_" + featureKey + "_feature"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * get the library root path for the given element, assuming the element and
	 * it's parents are loaded
	 * 
	 * @param element
	 * @return File the root path of the library
	 */
	public static File getLibraryRootPath(EObject element) {
		EObject parent = element;
		while ((parent != null) && !(parent instanceof MethodLibrary)) {
			parent = parent.eContainer();
		}

		if (parent != null && parent.eResource() != null) {
			org.eclipse.emf.common.util.URI uri = parent.eResource().getURI();
			String path = uri.toFileString();
			File f = new File(path);
			return f.getParentFile();
		}
		return null;
	}

	/**
	 * get the full path URI by appending the relative path URI to the object's
	 * library path
	 * 
	 * @param o
	 *            EObject the object to find the MethodLibrary, can be any
	 *            object in the library
	 * @param uri
	 *            The uri to be fixed
	 * @return URI the full path uri
	 */
	public static URI getFullPathURI(EObject o, URI uri) {
		if (uri == null) {
			return uri;
		}

		// try if this is a valid URL or not
		try {
			URL url = uri.toURL();
			if (url != null) {
				return uri;
			}
		} catch (Exception ex) {
			; // not a valid url, maybe a relative path
		}

		// need to get the element's resource path
		File f = getLibraryRootPath(o);
		if (f != null) {
			return new File(f, NetUtil.decodedFileUrl(uri.toString())).toURI();
		}

		return uri;
	}
	
	/**
	 * 
	 * @param object
	 * @return custom node icon or null if the given object does not have one
	 */
	public static Object getCustomNodeIcon(Object object) {
		if (object instanceof DescribableElement) {
			if (((DescribableElement) object).getNodeicon() != null) {
				URI imgUri = TngUtil.getFullPathofNodeorShapeIconURI(
						(DescribableElement) object,
						((DescribableElement) object).getNodeicon());
				Object image = LibraryEditPlugin.INSTANCE
						.getSharedImage(imgUri);
				if (image != null)
					return image;
			}
		}
		return null;
	}

	
	/**
	 * To get the node icon/shape icon full path URI by appending the relative path URI to the object's
	 * library path
	 * 
	 * @param o
	 *            EObject the object to find the MethodLibrary, can be any
	 *            object in the library
	 * @param uri
	 *            The uri to be fixed
	 * @return URI the full path uri
	 */
	//FIXME: make storage independent
	public static URI getFullPathofNodeorShapeIconURI(EObject o, URI uri) {
		if (uri == null) {
			return uri;
		}

		// try if this is a valid URL or not
		try {
			URL url = uri.toURL();
			if (url != null) {
				return uri;
			}
		} catch (Exception ex) {
			; // not a valid url, maybe a relative path
		}

		// need to get the element's resource path
		MethodPlugin plugin = UmaUtil.getMethodPlugin(o);
		if (plugin != null && UmaUtil.hasDirectResource(plugin)) {
			File f = getLibraryRootPath(o);
			String path = uri.getPath();
			if (f != null && path.indexOf(plugin.getName() + "/") == 0) { //$NON-NLS-1$
				return new File(f, NetUtil.decodedFileUrl(uri.toString()))
						.toURI();
			}
			else {
				IFileBasedLibraryPersister persister = (IFileBasedLibraryPersister) Services
						.getLibraryPersister(Services.XMI_PERSISTENCE_TYPE);
				File pluginDir = persister.getFile(plugin.eResource())
						.getParentFile();

				return new File(pluginDir, NetUtil.decodedFileUrl(uri
						.toString())).toURI();

			}
		}
		return null;
	}
	/**
	 * @param e
	 * @return
	 */
	public static String toStackTraceString(Throwable e) {
		StringWriter strWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(strWriter));
		return strWriter.toString();
	}
	
	public static String getLabelWithPath(Object object) {
		if (object instanceof BreakdownElement) {
			return ProcessUtil.getLabelWithPath((BreakdownElement) object);
		}

		if (object instanceof ContentDescription) {
			return getLabelWithPath(((ContentDescription) object).eContainer());
		}

		if(object instanceof MethodElement) {
			MethodElement me = (MethodElement) object;
			Collection<NamedElement> itemPath = Misc.getObjectTreeRelativeToPlugin(me);
			StringBuffer path = new StringBuffer(getLabel(object, me.getGuid()));
			if (MethodElementUtil.getMethodModel(object) != null) {
				path
				.append(", ").append(MethodElementUtil.getMethodModel(object).getName()); //$NON-NLS-1$
				for (Iterator<NamedElement> iter = itemPath.iterator();iter.hasNext();) {
					NamedElement e = iter.next();
					String s = String.valueOf(e.getName());

					if (ModelStructure.CORE_CONTENT_PACAKGE_NAME.equals(s) || ModelStructure.CONTENT_PACKAGE_NAME.equalsIgnoreCase(s)
							|| (((NamedElement) object).equals(e))) {
					} else {
						path.append('/').append(s);
					}
				}
			}
			return path.toString();
		}
		return ""; //$NON-NLS-1$
	}

	public static EObject getOwner(Command command) {
		command = TngUtil.unwrap(command);
		EObject owner = null;
		if (command instanceof AddCommand) {
			owner = ((AddCommand) command).getOwner();
		} else if (command instanceof RemoveCommand) {
			owner = ((RemoveCommand) command).getOwner();
		} else if (command instanceof SetCommand) {
			owner = ((SetCommand) command).getOwner();
		} else if (command instanceof MoveCommand) {
			owner = ((MoveCommand) command).getOwner();
		} else if (command instanceof PasteFromClipboardCommand) {
			Object o = ((PasteFromClipboardCommand) command).getOwner();
			if (o instanceof EObject) {
				owner = (EObject) o;
			}
		}
		return owner;
	}
	
	public static Set<Resource> getModifiedResources(Command cmd) {
		Set<Resource> modifiedResources = new HashSet<Resource>();
		collectModifiedResources(cmd, modifiedResources);
		return modifiedResources;
	}
	
	public static void collectModifiedResources(Command command, Set<Resource> modifiedResources) {
		if (command instanceof IResourceAwareCommand) {
			modifiedResources.addAll(((IResourceAwareCommand) command)
					.getModifiedResources());
		} 
		else if(command instanceof CompoundCommand){
			List commandList = ((CompoundCommand)command).getCommandList();
			for (Iterator iter = commandList.iterator(); iter.hasNext();) {
				collectModifiedResources((Command) iter.next(), modifiedResources);				
			}
		}
		else {
			EObject modified = TngUtil.getOwner(command);
			if(modified != null) {
				Resource resource = modified.eResource();
				if(resource != null) {
					modifiedResources.add(resource);
				}
			}
		}
	}

	public static List convertGuidanceAttachmentsToList(String attachmentString) {
		ArrayList attachments = new ArrayList();
		if (attachmentString == null)
			attachmentString = ""; //$NON-NLS-1$
		String files[] = attachmentString
				.split(GUIDANCE_FILESTRING_SEPARATOR_SPLITTER);
		for (int i = 0; i < files.length; i++) {
			if (files[i].trim().length() > 0)
				attachments.add(files[i]);
		}
		return attachments;
	}

	public static String convertGuidanceAttachmentsToString(List attachmentList) {
		String attachmentString = ""; //$NON-NLS-1$
		int i = 0;
		for (Iterator iter = attachmentList.iterator(); iter.hasNext();) {
			String attachment = (String) iter.next();
			if (i++ > 0)
				attachmentString = attachmentString
						.concat(GUIDANCE_FILESTRING_SEPARATOR);
			attachmentString = attachmentString.concat(attachment);
		}
		return attachmentString;
	}
	
	public static List<String> convertStringsToList(String string) {
		ArrayList<String> strList = new ArrayList<String>();
		if (string == null)
			string = ""; //$NON-NLS-1$
		String strings[] = string
				.split(COMMA_SEPARATOR_SPLITTER);
		for (int i = 0; i < strings.length; i++) {
			if (strings[i].trim().length() > 0)
				strList.add(strings[i].trim());
		}
		return strList;
	}
	
	public static List<String> convertStringsToList(String string, String seperator) {
		ArrayList<String> strList = new ArrayList<String>();
		if (string == null)
			string = ""; //$NON-NLS-1$
		String strings[] = string
				.split(seperator);
		for (int i = 0; i < strings.length; i++) {
			if (strings[i].trim().length() > 0)
				strList.add(strings[i].trim());
		}
		return strList;
	}

	public static String convertListToString(List<String> strList) {
		String string = ""; //$NON-NLS-1$
		int i = 0;
		if (strList != null) {
			for (String aString: strList) {
				if (i++ > 0)
					string = string
							.concat(COMMA_SEPARATOR);
				string = string.concat(aString);
			}
		}
		return string;
	}

	
	/**
	 * 
	 * @param e
	 * @return true iff the MethodElement is allowed an attachment.  Currently, only
	 * Example, ResuableAsset, Template,  Whitepaper are allowed attachments
	 */
	public static boolean isAllowedAttachments(MethodElement e) {
		if (e == null) {
			return false;
		}
		if (e instanceof Example)
			return true;
		if (e instanceof ReusableAsset)
			return true;
		if (e instanceof Template)
			return true;
		if (e instanceof Whitepaper)
			return true;

		return false;
	}

	/**
	 * Checks if the given <code>elements</code> contains <code>e</code> or has a wrapper of <code>e</code>
	 * 
	 * @param elements
	 * @param e
	 * @return
	 */
	public static boolean contains(Collection elements, Object e) {
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if(element == e || e == unwrap(element)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the given CustomCategory is an ancestor of the given DescibableElement
	 * 
	 * @param cc
	 * @param e
	 * @return
	 * @deprecated moved to {@link DependencyChecker#isAncessorOf(CustomCategory, DescribableElement)}
	 */
	public static boolean isAncessorOf(CustomCategory cc, DescribableElement e) {
		return DependencyChecker.isAncessorOf(cc, e);
	}
	
	public static void refreshParentIfNameChanged(Notification notification, ItemProviderAdapter adapter) {
		if(notification.getNotifier() instanceof ContentElement) {
			switch (notification.getFeatureID(DescribableElement.class)) {
			case UmaPackage.DESCRIBABLE_ELEMENT__NAME:
				if(PresentationContext.INSTANCE.isShowPresentationNames()) {
					return;
				}
				break;
			case UmaPackage.DESCRIBABLE_ELEMENT__PRESENTATION_NAME:
				if(!PresentationContext.INSTANCE.isShowPresentationNames()) {
					return;
				}
				break;
			default:
				return;
			}
		}
		else if ( notification.getFeatureID(NamedElement.class) != UmaPackage.NAMED_ELEMENT__NAME ){
			return;
		}
		// name change might change the element's order in the sorted list
		// refresh the parent content to resort the element list
		//
		adapter.fireNotifyChanged(new ViewerNotification(notification, adapter.getParent(notification.getNotifier()), true, false));
	}	
	
	/**
	 * Checks if the given element <code>e</code> is a contributor of the given <code>base</code>
	 * @param base
	 * @param e
	 * @return
	 */
	public static boolean isContributorOf(Object base, VariabilityElement e) {
		for(VariabilityElement ve = (VariabilityElement) e;
		ve != null && ve.getVariabilityType() == VariabilityType.CONTRIBUTES && ve.getVariabilityBasedOnElement() != null;
		ve = ve.getVariabilityBasedOnElement()) {
			if(ve.getVariabilityBasedOnElement() == base) {
				return true;
			}
		}
		return false;
	}

//	/**
//	 * Checks if the given parent contains the given child, either via containment or contribution
//	 * @param parent
//	 * @param child
//	 * @return
//	 */
//	public static boolean isContainedBy(Artifact parent, Artifact child) {
//		EObject container = child.eContainer();
//		if(container instanceof Artifact) {
//			Artifact currentParent = (Artifact)container;
//			if(container == parent || isContributorOf(parent, currentParent)) {
//				return true;
//			}
//			Iterator iter = new AbstractTreeIterator(parent, false) {
//				
//				private static final long serialVersionUID = 1L;
//				
//				protected Iterator getChildren(Object object) {
//					return ((Artifact)object).getContainedArtifacts().iterator();
//				}
//				
//			};
//			while(iter.hasNext()) {
//				Object o = iter.next();
//				if(o == currentParent || isContributorOf(o, currentParent)) {
//					return true;
//				}
//			}
//			return false;
//		}
//		else {
//			return UmaUtil.isContainedBy(child, parent);
//		}
//	}
	
	public static void getConfigurationsToUpdate(MethodPackage parent, MethodPackage pkg, Collection configsToUpdate) {
		Object configs = ((MultiResourceEObject) parent)
		.getOppositeFeatureValue(AssociationHelper.MethodPackage_MethodConfigurations);
		if (configs instanceof List) {
			for (Iterator itconfig = ((List) configs).iterator(); itconfig
			.hasNext();) {
				MethodConfiguration config = (MethodConfiguration) itconfig
				.next();
				List pkgs = config.getMethodPackageSelection();
				if (!pkgs.contains(pkg)) {
					configsToUpdate.add(config);
				}
			}
		}
	}
	
	/**
	 * Checks if the given object can be estimated
	 * 
	 * @param o
	 * @return
	 */
	public static boolean canEstimate(Object o) {
		return o instanceof Task || o instanceof WorkBreakdownElement;
	}
	
	
	public static void debugShowAll() {
		ILibraryInspector inspector = getLibraryInspector();
		if (inspector != null) {
			inspector.showAllContents();
		}
	}
	
	public static void debugShowAll(MethodLibrary lib) {
		ILibraryInspector inspector = getLibraryInspector();
		if (inspector != null) {
			inspector.showAllContents(lib);
		}
	}
	
	//Used for debug only
	public static ILibraryInspector getLibraryInspector() {
		return (ILibraryInspector) ExtensionManager.getExtension("org.eclipse.epf.library.edit", "libraryInspector"); 			//$NON-NLS-1$ //$NON-NLS-2$
	}
	
	public static boolean isInPluginWithLessThanOneSuperCustomCategory(CustomCategory cc, MethodPlugin plugin) {
		if(UmaUtil.getMethodPlugin(cc) != plugin) {
			return false;
		}
		List list = AssociationHelper.getCustomCategories(cc);
		int count = 0;
		for (Iterator iter = list.iterator(); iter.hasNext() && count < 2;) {
			CustomCategory cat = (CustomCategory) iter.next();
			if(UmaUtil.getMethodPlugin(cat) == plugin) {
				count++;
			}						
		}
		return count < 2;
	}

	/**
	 * Check if the given wrapper is under a custom category tree
	 * 
	 * @param item
	 * @return
	 */
	public static boolean isUnderCustomCategoryTree(IWrapperItemProvider wrapper) {
		do {
			Object owner = wrapper.getOwner();
			if(unwrap(owner) instanceof CustomCategory) {
				return true;
			}
			wrapper = owner instanceof IWrapperItemProvider ? (IWrapperItemProvider) owner : null;
		} while(wrapper != null);
		
		return false;
	}
	
	public static Collection getExclusiveSubCustomCategories(CustomCategory cc) {
		ArrayList collection = new ArrayList();
		collectExclusiveSubCustomCategories(collection, cc, UmaUtil.getMethodPlugin(cc));
		return collection;
	}
	
	private static void collectExclusiveSubCustomCategories(Collection collection, CustomCategory cc, MethodPlugin plugin) {
		for (Iterator iter = cc.getCategorizedElements().iterator(); iter.hasNext();) {
			Object e = (Object) iter.next();
			if(e instanceof CustomCategory) {
				CustomCategory sub = (CustomCategory) e;
				if(isInPluginWithLessThanOneSuperCustomCategory(sub, plugin)) {
					collection.add(e);
					collectExclusiveSubCustomCategories(collection, sub, plugin);
				}
			}
		}
	}
	
	public static Object getAdapterByType(Notifier notifier, Object type) {
		for (Iterator iter = notifier.eAdapters().iterator(); iter.hasNext();) {
			Adapter adapter = (Adapter) iter.next();
			if(adapter.isAdapterForType(type)) {
				return adapter;
			}
		}
		return null;
	}

	/**
	 * Removes all references to a method element.
	 * 
	 * @param element
	 *            a method element
	 * @return a map containing the removed references
	 */
	public static Map removeReferences(MethodElement element) {
		return TngUtil.removeReferences(element, false);
	}

	/**
	 * Removes all references to a method element.
	 * 
	 * @param element
	 *            a method element
	 * @return a map of method elements whose references to the given element
	 *         are just removed, and their features that hold the references to
	 *         the given element
	 */
	public static Map removeReferences(MethodElement element,
			boolean checkModify) {
		// List referencers = new ArrayList();
		Map objectFeaturesMap = new HashMap();
		MultiResourceEObject eObj = (MultiResourceEObject) element;
	
		if (eObj.basicGetOppositeFeatureMap() == null)
			return objectFeaturesMap;
	
		for (Iterator iter = eObj.getOppositeFeatureMap().entrySet().iterator(); iter
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			OppositeFeature oppositeFeature = ((OppositeFeature) entry.getKey());
			EStructuralFeature feature = oppositeFeature.getTargetFeature();
			if (feature.isMany()) {
				if (oppositeFeature.isMany()) {
					for (Iterator iterator = new ArrayList((Collection) eObj
							.getOppositeFeatureValue(oppositeFeature))
							.iterator(); iterator.hasNext();) {
						EObject obj = (EObject) iterator.next();
						if (checkModify) {
							checkModify(obj);
						}
						((List) obj.eGet(feature)).remove(element);
						List features = (List) objectFeaturesMap.get(obj);
						if (features == null) {
							features = new ArrayList();
							objectFeaturesMap.put(obj, features);
						}
						features.add(feature);
					}
				} else {
					EObject obj = (EObject) eObj
							.getOppositeFeatureValue(oppositeFeature);
					if (obj != null) {
						if (checkModify) {
							checkModify(obj);
						}
						((List) obj.eGet(feature)).remove(element);
						List features = (List) objectFeaturesMap.get(obj);
						if (features == null) {
							features = new ArrayList();
							objectFeaturesMap.put(obj, features);
						}
						features.add(feature);
					}
				}
			} else {
				if (oppositeFeature.isMany()) {
					for (Iterator iterator = new ArrayList((Collection) eObj
							.getOppositeFeatureValue(oppositeFeature))
							.iterator(); iterator.hasNext();) {
						EObject obj = (EObject) iterator.next();
						if (checkModify) {
							checkModify(obj);
						}
						obj.eSet(feature, null);
						List features = (List) objectFeaturesMap.get(obj);
						if (features == null) {
							features = new ArrayList();
							objectFeaturesMap.put(obj, features);
						}
						features.add(feature);
					}
				} else {
					EObject obj = (EObject) eObj
							.getOppositeFeatureValue(oppositeFeature);
					if (obj != null) {
						if (checkModify) {
							checkModify(obj);
						}
						obj.eSet(feature, null);
						List features = (List) objectFeaturesMap.get(obj);
						if (features == null) {
							features = new ArrayList();
							objectFeaturesMap.put(obj, features);
						}
						features.add(feature);
					}
				}
			}
		}
		// for (Iterator iter = objectFeaturesMap.entrySet().iterator();
		// iter.hasNext();) {
		// Map.Entry entry = (Map.Entry) iter.next();
		// referencers.add(new Referencer((EObject)entry.getKey(),
		// (List)entry.getValue()));
		// }
		// return referencers;
		return objectFeaturesMap;
	}
	
	/**
	 * Return publishing category property for method element
	 * The meaning of this property is used for:
	 * 
	 * CustomCategory: Publish this category with the categorized method elements
	 * Practice: Publish this practice with the referenced method elements
	 * 
	 * @param element
	 * @return
	 * 		 property if found, else null
	 */
	public static MethodElementProperty getPublishCategoryProperty(MethodElement element) {
		List props = element.getMethodElementProperty();
		if (props == null || props.size() == 0) {
			return null;
		}

		for (Iterator it = props.iterator(); it.hasNext();) {
			MethodElementProperty prop = (MethodElementProperty) it.next();
			if (PUBLISH_CATEGORY_PROPERTY.equals(prop.getName())) {
				return prop;
			}
		}

		return null;
	}

	public static List<INotifyChangedListener> getNotifyChangedListeners(AdapterFactory adapterFactory, Object obj) {
		Object adapter = adapterFactory.adapt(obj, ITreeItemContentProvider.class);
		if(adapter instanceof IListenerProvider) {
			return ((IListenerProvider) adapter).getNotifyChangedListeners();
		}
		return Collections.emptyList();
	}
	
	public static Collection<?> getWrappers(AdapterFactory adapterFactory, Object obj) {
		HashSet<Object> wrappers = new HashSet<Object>();
		for (INotifyChangedListener listener : getNotifyChangedListeners(adapterFactory, obj)) {
			if(listener instanceof IWrapperItemProvider && unwrap(listener) == obj) {
				wrappers.add(listener);
			}
		}
		return wrappers;
	}

	/**
	 * Gets the wrapper for the given value in the given wrappers.
	 * 
	 * @param wrappers
	 * @param value
	 * @return
	 */
	public static FeatureValueWrapperItemProvider getWrapper(Collection wrappers, Object value) {
		if (wrappers == null)
			return null;
		for (Iterator iter = wrappers.iterator(); iter.hasNext();) {
			Object listener = (Object) iter.next();
			if(listener instanceof FeatureValueWrapperItemProvider
					&& unwrap(listener) == value) {
				return (FeatureValueWrapperItemProvider) listener;
			}
		}
		return null;
	}

	public static Object getFeatureValueWrapperItemProviderForCC(AdapterFactory adapterFactory, CustomCategory cc) {
		// get ITreeContentProvider adapater
		CustomCategoryItemProvider adapter = (CustomCategoryItemProvider) adapterFactory.adapt(cc, ITreeItemContentProvider.class);
		for (Iterator iter = adapter.getNotifyChangedListeners().iterator(); iter.hasNext();) {
			Object listener = iter.next();
			if (listener instanceof FeatureValueWrapperItemProvider
					&& unwrap(listener) == cc) {
				return (FeatureValueWrapperItemProvider)listener;
			}
		}
		return cc;
	}
	
	public static class PresentationNameHelper {
		//to be overriden by subclass
		public String getPresentationName(MethodElement element) {
			return element.getPresentationName();
		}
	}
	
}