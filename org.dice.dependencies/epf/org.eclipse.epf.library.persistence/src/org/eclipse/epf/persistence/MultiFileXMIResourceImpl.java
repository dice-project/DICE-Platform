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
package org.eclipse.epf.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.persistence.ILibraryResource;
import org.eclipse.epf.library.persistence.internal.IFailSafeSavable;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.persistence.util.PersistenceResources;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.resourcemanager.ResourceDescriptor;
import org.eclipse.epf.resourcemanager.ResourceManager;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.DefaultValueManager;
import org.eclipse.epf.uma.ecore.util.OppositeFeatureNotification;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;

/**
 * Resource implementation for library XMI persistence
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MultiFileXMIResourceImpl extends XMIResourceImpl 
implements ILibraryResource, IFailSafeSavable
{

	private static String tempDir;

	private URI finalURI;

	private URI oldURI;

	private String backupFile;

	private String currentTxID;

	private long lastLoadTimeStamp;

	private ResourceDescriptor resourceDescriptor;

	private boolean isUnloading;

	/** Cached modification stamp of the resource file */
	private long modificationStamp;

	private IFileInfo fileInfo;

	public MultiFileXMIResourceImpl(URI uri) {
		super(uri);
		setIntrinsicIDToEObjectMap(new HashMap());		
	}

	protected XMLLoad createXMLLoad() {
		return new MultiFileXMILoadImpl(createXMLHelper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#createXMLSave()
	 */
	protected XMLSave createXMLSave() {
		return new MultiFileXMISaveImpl(createXMLHelper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl#createXMLHelper()
	 */
	protected XMLHelper createXMLHelper() {
		return new MultiFileXMIHelperImpl(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.XMLResource#getID(org.eclipse.emf.ecore.EObject)
	 */
	public String getID(EObject eObject) {
		String id = MultiFileSaveUtil.getGuid(eObject);
		if (id != null)
			return id;
		return super.getID(eObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#setID(org.eclipse.emf.ecore.EObject,
	 *      java.lang.String)
	 */
	public void setID(EObject eObject, String id) {
		Object oldID = id != null ? getEObjectToIDMap().put(eObject, id)
				: getEObjectToIDMap().remove(eObject);

		Map guidToMethodElementMap = ((MultiFileResourceSetImpl) getResourceSet())
				.getGuidToMethodElementMap();

		if (oldID != null) {
			getIDToEObjectMap().remove(oldID);
			
			if (eObject instanceof MethodElement) {
				// remove object from guidToMethodElementMap only if it is a proxy
				//
				if(eObject.eIsProxy()) {
					EObject obj = (EObject) guidToMethodElementMap.get(id);
					if(obj != null && obj.eIsProxy()) {
						guidToMethodElementMap.remove(id);
					}
				}
				else {
					guidToMethodElementMap.remove(id);
				}
			}
		}

		if (id != null) {
			getIDToEObjectMap().put(id, eObject);
			if (eObject instanceof MethodElement) {
				guidToMethodElementMap.put(id, eObject);

				MethodElement e = (MethodElement) eObject;
				if (!e.getGuid().equals(id)) {
					e.setGuid(id);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#useUUIDs()
	 */
	protected boolean useUUIDs() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.resource.Resource#getURIFragment(org.eclipse.emf.ecore.EObject)
	 */
	public String getURIFragment(EObject eObject) {
		try {
			String id = getID(eObject);

			if (id != null) {
				return id;
			} else {
				List uriFragmentPath = new ArrayList();
				for (EObject container = eObject.eContainer(); container != null
						&& container.eResource() == this; container = eObject
						.eContainer()) {
					uriFragmentPath.add(((InternalEObject) container)
							.eURIFragmentSegment(eObject.eContainingFeature(),
									eObject));
					eObject = container;
				}

				StringBuffer result = new StringBuffer("/"); //$NON-NLS-1$
				result.append(getURIFragmentRootSegment(eObject));

				for (ListIterator i = uriFragmentPath
						.listIterator(uriFragmentPath.size()); i.hasPrevious();) {
					result.append('/');
					result.append((String) i.previous());
				}
				return result.toString();
			}
		} catch (RuntimeException e) {
			System.err.println("Error getting URI fragment for " + eObject); //$NON-NLS-1$
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#attachedHelper(org.eclipse.emf.ecore.EObject)
	 */
	protected void attachedHelper(EObject eObject) {
		super.attachedHelper(eObject);

		if (useIDs()) {
			String id = getID(eObject);
			if (id != null) {
				// update the eObjectToIDMap
				//
				getEObjectToIDMap().put(eObject, id);
				
				if (eObject instanceof MethodElement) {
					((MultiFileResourceSetImpl) getResourceSet())
							.getGuidToMethodElementMap().put(id, eObject);
				}
			}
		}

		if (isTrackingModification() && eObject.eResource() != this) {
			// remove the modification tracking adapter from the eObject
			//
			eObject.eAdapters().remove(modificationTrackingAdapter);
		}
	}

	private void attachedAllWithIDs(EObject eObj) {
		// attachedHelper(eObj);
		// List list = eObj.eContents();
		// int size = list.size();
		// for (int i = 0; i < size; i++) {
		// EObject o = (EObject) list.get(i);
		// if (o.eResource() == this) {
		// attachedAllWithIDs(o);
		// }
		// }

		Iterator allContents = new ContentTreeIterator(eObj);

		while (allContents.hasNext()) {
			attachedHelper((EObject) allContents.next());
		}
	}

	private void basicAttachedAll(EObject eObj) {
		eObj.eAdapters().add(modificationTrackingAdapter);
		List list = eObj.eContents();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			EObject o = (EObject) list.get(i);
			if (o.eResource() == this) {
				basicAttachedAll(o);
			}
		}
	}

	/**
	 * Calls on an object and all of its offstring objects that are in the same
	 * resource as the object when the object is attached to this resource. This
	 * method is expensive, so use it with caution.
	 * 
	 * @param eObj
	 */
	public void attachedAll(EObject eObj) {
		if (useIDs()) {
			attachedAllWithIDs(eObj);
		} else {
			basicAttachedAll(eObj);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.resource.Resource.Internal#attached(org.eclipse.emf.ecore.EObject)
	 */
	public void attached(EObject eObject) {
		attachedAll(eObject);
	}

	private void detachedAllWithIDs(EObject eObj) {
		Iterator allContents = new ContentTreeIterator(eObj);

		while (allContents.hasNext()) {
			detachedHelper((EObject) allContents.next());
		}
	}

	private void basicDetachedAll(EObject eObj) {
		eObj.eAdapters().remove(modificationTrackingAdapter);
		List list = eObj.eContents();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			EObject o = (EObject) list.get(i);
			if (o.eResource() == this) {
				basicDetachedAll(o);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#getEObjectByID(java.lang.String)
	 */
	protected EObject getEObjectByID(String id) {
		for (Iterator iter = getContents().iterator(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			String guid = MultiFileSaveUtil.getGuid(element);
			if (guid != null && guid.equals(id))
				return element;
		}

		// return super.getEObjectByID(id);

		if (idToEObjectMap != null) {
			InternalEObject eObject = (InternalEObject) idToEObjectMap.get(id);
			if (eObject != null && !eObject.eIsProxy()) {
				return eObject;
			}
		}

		return null;
	}
	
	private IFileInfo getFileInfo() {
		if(fileInfo == null || !new File(getURI().toFileString()).equals(fileInfo.getFile())) {
			fileInfo = FileManager.getInstance().getFileInfo(this); 
		}
		return fileInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#load(java.util.Map)
	 */
	public void load(Map options) throws IOException {
		if (isUnloading) {
			return;
		}
		
		boolean old = DefaultValueManager.INSTANCE.isUseStatic();
		try {
			DefaultValueManager.INSTANCE.setUseStatic(true);
			super.load(options);		
			updateTimeStamps();
		}
		finally {
			DefaultValueManager.INSTANCE.setUseStatic(old);
		}
		
		if (MultiFileSaveUtil.DEBUG) {
			if (getURI().toString().equals(testResourceURIStr)) {
				testResourceLoaded = true;
			}
		}
	}

	private static boolean testResourceLoaded;

	private static final String testResourceURIStr = "file:/C:/temp/newlib/library.xmi"; //$NON-NLS-1$

	public long getModificationStamp() {
		return modificationStamp;
	}
	
	/**
	 * Reloads this resource. Old objects will become proxies and they will be
	 * added to the specified collection <code>proxies</code>
	 * 
	 * @param proxies
	 */
	synchronized boolean reload(Collection proxies) throws IOException {
		if (isLoaded) {
			Notification notification = setLoaded(false);
			doUnload(proxies, false);
			// doUnload() might have set this resource loaded again
			// set isLoaded to false and contents to null to make sure
			//
			isLoaded = false;
			contents = null;
			if (notification != null) {
				eNotify(notification);
			}
			load(getResourceSet().getLoadOptions());
			return true;
		}
		return false;
	}

	/*
	 * Javadoc copied from interface.
	 */
	final void unloadWithoutRemove() {
		if (isLoaded) {
			// save the ResourceDescriptor pf this resource for later use to
			// create URI for the proxies after unload
			//
			ResourceDescriptor resDesc = MultiFileSaveUtil
					.getResourceDescriptor(this);
			if (resDesc != null) {
				setResourceDescriptor(resDesc);
			}

			Notification notification = setLoaded(false);
			doUnload(null, false);
			if (notification != null) {
				eNotify(notification);
			}
		}
	}

	/**
	 * Does all the work of unloading the resource. It calls
	 * {@link #unloaded unloaded} for each object it the content
	 * {@link #getAllContents tree}, and clears the
	 * {@link #getContents contents}, {@link #getErrors errors}, and
	 * {@link #getWarnings warnings}.
	 */
	protected void doUnload() {
		doUnload(null, true);
	}

	/**
	 * @author Phong Nguyen Le - Jul 17, 2006
	 * @since 1.0
	 */
	private final class ContentTreeIterator extends AbstractTreeIterator {
		/**
		 * Comment for <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * @param object
		 */
		private ContentTreeIterator(Object object) {
			super(object);
		}

		protected Iterator getChildren(Object object) {
			if (object instanceof EObject) {
				ArrayList children = new ArrayList();
				EContentsEList contents = new EContentsEList((EObject) object) {
					/*
					 * (non-Javadoc)
					 * 
					 * @see org.eclipse.emf.ecore.util.EContentsEList#resolve()
					 */
					protected boolean resolve() {
						return false;
					}
				};
				for (Iterator iter = contents.basicIterator(); iter.hasNext();) {
					EObject o = (EObject) iter.next();
					if (o.eResource() == MultiFileXMIResourceImpl.this
							|| o.eIsProxy()) {
						children.add(o);
					}
				}
				return children.iterator();
			}
			return Collections.EMPTY_LIST.iterator();
		}
	}

	/**
	 * Unloads this resource. Old objects will be come proxies and will be added
	 * to the specified collection <code>proxies</code>
	 * 
	 * @param proxies
	 * @param remove
	 *            if <code>true</code> will remove all the unloaded objects in
	 *            the resource from their containers and all opposite feature
	 *            data of the unloaded objects will be cleared.
	 */
	protected void doUnload(Collection proxies, boolean remove) {
		isUnloading = true;

		try {
			// from ResourceImpl
			//

			Iterator allContents = getAllProperContents(new ArrayList(
					getContents()));

			// This guard is needed to ensure that clear doesn't make the
			// resource
			// become loaded.
			//
			if (!getContents().isEmpty()) {
				if (remove) {
					getContents().clear();
				} else {
					// don't call getContents().clear() to keep the references
					// to elements
					// that just became proxies after unload
					//
					contents = null;
				}

			}
			getErrors().clear();
			getWarnings().clear();

			ArrayList<EObject> unloadedObjects = new ArrayList<EObject>();
			
			ArrayList allContentList = new ArrayList();
			while (allContents.hasNext()) {
				allContentList.add(allContents.next());
			}
			Iterator it = allContentList.iterator();
			
			try {
				while (it.hasNext()) {
					try {
						InternalEObject o = (InternalEObject) it
								.next();
						unloaded(o, remove);
						unloadedObjects.add(o);
					} catch (Exception e) {
						CommonPlugin.INSTANCE.log(e);
					}
				}
			} catch (Exception e) {
				CommonPlugin.INSTANCE.log(e);
			}
			if (proxies != null && !unloadedObjects.isEmpty()) {
				proxies.addAll(unloadedObjects);
			}
			if (remove) {
				// remove the unloaded objects from its container to prevent the
				// whole library from staying
				// in memory if only one of its element is leaked
				// 
				for (Iterator iter = unloadedObjects.iterator(); iter.hasNext();) {
					EObject object = (EObject) iter.next();
					EcoreUtil.remove(object);
				}
			}

			// from XMLResourceIml
			//		

			if (idToEObjectMap != null) {
				for (Iterator iter = idToEObjectMap.keySet().iterator(); iter
						.hasNext();) {
					((MultiFileResourceSetImpl) getResourceSet())
							.getGuidToMethodElementMap().remove(iter.next());
				}
				idToEObjectMap.clear();
			}

			if (eObjectToIDMap != null) {
				eObjectToIDMap.clear();
			}

			if (eObjectToExtensionMap != null) {
				eObjectToExtensionMap.clear();
			}

			if (intrinsicIDToEObjectMap != null) {
				intrinsicIDToEObjectMap.clear();
			}
			
			modificationStamp = IResource.NULL_STAMP;
			
		} finally {
			isUnloading = false;
		}
	}

	protected void unloaded(InternalEObject internalEObject) {
		unloaded(internalEObject, true);
	}
	
	/**
	 * @param internalEObject
	 * @param clear
	 *            if <code>true</code> all opposite feature data of the
	 *            unloaded objects will be cleared.
	 */
	private void unloaded(InternalEObject internalEObject, boolean clear) {
		String guid = MultiFileSaveUtil.getGuid(internalEObject);
		if (guid != null) {
			URI uri = resourceDescriptor != null ? MultiFileURIConverter
					.createURI(resourceDescriptor.getId()) : getURI();
			internalEObject.eSetProxyURI(uri.appendFragment(guid));
		}
		internalEObject.eAdapters().clear();

		if (internalEObject instanceof MultiResourceEObject && clear) {
			MultiResourceEObject multiResourceEObject = ((MultiResourceEObject) internalEObject);

			// clear own opposite feature map
			//			
			Map oppositeFeatureMap = multiResourceEObject
					.basicGetOppositeFeatureMap();
			if (oppositeFeatureMap != null) {
				oppositeFeatureMap.clear();
			}

			// remove itself from all opposite features
			// 
			multiResourceEObject.removeFromAllOppositeFeatures();
		}
		
		if(internalEObject instanceof ContentDescription || internalEObject instanceof Section) {
			// set all string attributes to NULL to release memory
			//
			for (EAttribute attr : internalEObject.eClass().getEAllAttributes()) {
				if(attr != UmaPackage.eINSTANCE.getMethodElement_Guid() && 
						attr.getEAttributeType().getInstanceClass().isAssignableFrom(String.class)) {
					internalEObject.eSet(attr, attr.getDefaultValue());
				}
			}
			
			if(internalEObject.eDirectResource() != null && internalEObject instanceof MultiResourceEObject) {
				((MultiResourceEObject)internalEObject).eSetResource(null);
			}
		}
	}

	public void detachedAll(EObject eObj) {
		if (useIDs()) {
			detachedAllWithIDs(eObj);
		} else {
			basicDetachedAll(eObj);
		}
	}		

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.resource.Resource.Internal#detached(org.eclipse.emf.ecore.EObject)
	 */
	public void detached(EObject eObject) {
		detachedAll(eObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#createModificationTrackingAdapter()
	 */
	protected Adapter createModificationTrackingAdapter() {
		return new ModificationTrackingAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl.ModificationTrackingAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
			 */
			public void notifyChanged(Notification notification) {
				// don't handle OppositeFeatureNotification b/c it does not
				// really modify this resource
				//
				if (notification instanceof OppositeFeatureNotification) {
					return;
				}
				super.notifyChanged(notification);
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#setModified(boolean)
	 */
	public void setModified(boolean isModified) {
		// boolean wasModified = isModified();
		super.setModified(isModified);
		// if(MultiFileSaveUtil.getMethodElement(this) instanceof
		// ProcessComponent) {
		// System.out.println("MultiFileXMIResourceImpl.setModified(): " +
		// this);
		// }
		// this code is needed to set dirty flag to the item provider's label
		//
		// if(wasModified != isModified && !getContents().isEmpty()) {
		// // refresh the label of the resource's object
		// //
		// EObject obj = (EObject) getContents().get(0);
		// for (Iterator iter = obj.eAdapters().iterator(); iter.hasNext();) {
		// Object adapter = iter.next();
		// if(adapter instanceof ItemProviderAdapter) {
		// ((ItemProviderAdapter)adapter).fireNotifyChanged(new
		// ViewerNotification(new NotificationImpl(Notification.SET, obj, obj),
		// obj, false, true));
		// }
		// }
		// }

		if (MultiFileSaveUtil.DEBUG) {
			if (testResourceLoaded
					&& getURI().toString().equals(testResourceURIStr)) {
				System.out.println("isModified=" + isModified); //$NON-NLS-1$
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.impl.NotifierImpl#eDeliver()
	 */
	public boolean eDeliver() {
		ResourceSet resourceSet = getResourceSet();
		if (resourceSet != null && !resourceSet.eDeliver())
			return false;

		return super.eDeliver();
	}

	public void save(Map options) throws IOException {
		boolean old = DefaultValueManager.INSTANCE.isUseStatic();
		
		Process proc = null;
		Scope scope = null;
		
		try {
			DefaultValueManager.INSTANCE.setUseStatic(true);
			
			if (options == null) {
				options = MultiFileResourceSetImpl.DEFAULT_SAVE_OPTIONS;
			}

			MethodElement e = PersistenceUtil.getMethodElement(this);
			if (e instanceof ProcessComponent) {
				proc = ((ProcessComponent) e).getProcess();
				if (proc != null && proc.getDefaultContext() instanceof Scope) {
					boolean oldDeliver = proc.eDeliver();
					try {
						proc.eSetDeliver(false);
						scope = (Scope) proc.getDefaultContext();
						proc.setDefaultContext(null);
						proc.getValidContext().clear();
					} finally {
						proc.eSetDeliver(oldDeliver);
					}
				}
			}			
			
			handleSynFreeFlag(e);
			
			super.save(options);

			// Special handling for saving MethodLibrary to remove all references to
			// MethodConfigurations from library file.
			//
			//MethodElement e = PersistenceUtil.getMethodElement(this);
			
			if (e instanceof MethodLibrary) {
				MethodLibrary lib = (MethodLibrary) e;
				// remove ResourceDescriptors of configuration files
				//
				ResourceManager resMgr = MultiFileSaveUtil.getResourceManager(this);
				if (resMgr != null) {
					for (Iterator iter = lib.getPredefinedConfigurations()
							.iterator(); iter.hasNext();) {
						MethodConfiguration config = (MethodConfiguration) iter
						.next();
						ResourceDescriptor resDesc = resMgr
						.getResourceDescriptor(config.getGuid());
						if (resDesc != null) {
							EcoreUtil.remove(resDesc);
						}
					}
				}
				List configs = new ArrayList(lib.getPredefinedConfigurations());
				boolean oldDeliver = lib.eDeliver();
				try {
					lib.eSetDeliver(false);
					lib.getPredefinedConfigurations().clear();

					// Save library file again to remove all references to configurations.
					// The previous save is still needed to save new configuration in its own file
					//
					super.save(options);
				} finally {
					lib.getPredefinedConfigurations().addAll(configs);
					lib.eSetDeliver(oldDeliver);
				}
			}
		}
		finally {
			DefaultValueManager.INSTANCE.setUseStatic(old);
			if (proc != null && scope != null) {
				boolean oldDeliver = proc.eDeliver();
				try {
					proc.eSetDeliver(false);
					proc.setDefaultContext(scope);
					proc.getValidContext().add(scope);
				} finally {
					proc.eSetDeliver(oldDeliver);
				}
			}
		}
	}

	public URI getFinalURI() {
		if (finalURI != null) {
			return finalURI;
		}
		return getURI();
	}

	public void setFinalURI(URI uri) {
		finalURI = uri;
	}

	public void backUpURI() {
		oldURI = getURI();
	}

	public boolean hasTempURI() {
		if (currentTxID != null) {
			return createTempURI().equals(getURI());
		}
		return false;
	}

	private URI createTempURI() {
		MethodElement e = PersistenceUtil.getMethodElement(this);
		return URI.createFileURI(new StringBuffer(getTempDir()).append(
				File.separator).append(currentTxID)
				.append("new").append(e.getGuid()).toString()); //$NON-NLS-1$
	}

	static String getTempDir() {
		if (tempDir == null) {
			tempDir = new File(FileManager.getTempDir(), "EPF").getAbsolutePath(); //$NON-NLS-1$
		}
		return tempDir;
	}

	/**
	 * Sets the temporary URI to save this resource to and it will be renamed to
	 * the original URI when saving is done
	 */
	public void setTempURI(String txID) {
		if (finalURI == null) {
			finalURI = oldURI = getURI();
			currentTxID = txID;

			URI tempURI = createTempURI();
			setURI(tempURI);

			MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) getResourceSet();
			resourceSet.getURIToTempURIMap().put(oldURI, tempURI);
		}
	}

	/**
	 * Restores the resource URI to the original one. This method must be call
	 * after saving regarless of its success.
	 * 
	 */
	private void restoreURI() {
		if (oldURI != null) {
			setURI(oldURI);
		}
	}

	public boolean txStarted() {
		return moveStarted() || saveStarted();
	}

	protected void updateTimeStamps() {
		FileManager.getInstance().refresh(this);
		lastLoadTimeStamp = System.currentTimeMillis();
		IFileInfo info = getFileInfo();
		if(info != null) {
			modificationStamp = info.getModificationStamp();
		}
	}
	
	/**
	 * Checks if this resource is currently synchronized with its data store
	 * 
	 * @return <li> -1 don't know
	 *         <li> 0 no
	 *         <li> 1 yes
	 * @deprecated use {@link #isSynchronized()} instead
	 */
	public int checkSynchronized() {
//		long currentTime = new File(getURI().toFileString()).lastModified();
//		if(MultiFileSaveUtil.same(currentTime, fileLastModified)) {
//			// some team providers changed the timestamp of last file modification by removing second fraction
//			// even the file was not changed
//			//
//			return 1;
//		}
//		return -1;
		return isSynchronized() ? 1 : 0;
	}
	
	public boolean isSynchronized() {
		IFileInfo fileInfo = getFileInfo();
		if (fileInfo != null) {
			return modificationStamp == fileInfo.getModificationStamp();
		}
		else {
			return true;
		}
	}

	public void txFinished(boolean successful) {
		boolean wasMove = !oldURI.equals(finalURI);
		if (successful) {
			setURI(finalURI);
			if(!wasMove) {
				setModified(false);
			}
			updateTimeStamps();
			if(!wasMove) {
				MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) getResourceSet();
				if(resourceSet != null) {
					resourceSet.getUnresolvedProxyMarkerManager().validateMarkers(this);
				}
			}
		} else {
			restoreURI();
			if (wasMove) {
				// finalURI must be cleared before restoring URI of its child
				// resources so the URI of its child resources can be resolved
				// correctly. See ResourceDescriptorImpl.getResolvedURI()
				//
				finalURI = null;
				
				// restore uri of ResourceDescriptor of this resource
				//
				MultiFileSaveUtil.updateURIMappings(this, oldURI, null, true);
			}
		}
		if (oldURI != null) {
			MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) getResourceSet();
			if (resourceSet != null) {
				resourceSet.getURIToTempURIMap().remove(oldURI);
			}
			oldURI = null;
		}
		currentTxID = null;
		finalURI = null;
	}

	public void deleteBackup() {
		if (backupFile != null) {
			try {
				// FileManager.getInstance().delete(backupFile);
				new File(backupFile).delete();
				backupFile = null;
			} catch (Throwable e) {
				CommonPlugin.INSTANCE.log(e);
				if (MultiFileSaveUtil.DEBUG) {
					e.printStackTrace();
				}
			}
		}
	}

	public boolean restore() {
		File src = null, dest = null;
		boolean moved = false;
		if (backupFile != null) {
			src = new File(backupFile);
			dest = new File(getFinalURI().toFileString());
		} else {
			moved = oldURI != null && !oldURI.equals(finalURI);
			if (moved) {
				File file = new File(getFinalURI().toFileString());
				dest = new File(oldURI.toFileString());
				moved = file.exists() && !dest.exists();
				if (moved) {
					src = file;
				}
			}
		}
		if (src != null) {
			if (dest.exists()) {
				FileUtil.moveFile(dest,new File(getURI().toFileString()));
			}
			boolean success;
			if (moved) {
				success = MultiFileSaveUtil.move(this, src, dest);
				// if(success) {
				// MultiFileSaveUtil.updateURIMappings(this, null);
				// }
			} else {
				// don't restore file if the source and destination are the same
				//
				success = src.lastModified() == dest.lastModified() && FileUtil.getSize(src) == FileUtil.getSize(dest);
				if(!success) {
					success = FileUtil.moveFile(src, dest);
				}
			}
			if (!success) {
				throw new MultiFileIOException(NLS.bind(PersistenceResources.restoreResourceError_msg, 
						FileManager.toFileString(getFinalURI())));
			}
			return true;
		}
		return false;
	}

	URI getOldURI() {
		return oldURI;
	}

	boolean moveStarted() {
		return oldURI != null && !oldURI.equals(getFinalURI());
	}

	boolean saveStarted() {
		return currentTxID != null;
	}
	
	private void refreshURIOfChildResources() {
		// refresh resolve URIs in ResourceDescriptors of the child
		// resources of this resource
		//
		HashMap<URI, ResourceDescriptor> oldURIToResourceDescriptorMap = null;
		ResourceManager resMgr = MultiFileSaveUtil
				.getResourceManager(this);
		if (resMgr != null) {
			oldURIToResourceDescriptorMap = new HashMap<URI, ResourceDescriptor>();
			for (Iterator<?> iter = resMgr.eAllContents(); iter
					.hasNext();) {
				Object obj = iter.next();
				if (obj instanceof ResourceDescriptor) {
					ResourceDescriptor desc = ((ResourceDescriptor) obj);
					oldURIToResourceDescriptorMap.put(desc
							.getResolvedURI(), desc);
					desc.clearResolvedURI();
				}
			}
		}

		// refresh URI of loaded child resources of this resource
		//
		if (oldURIToResourceDescriptorMap != null) {
			// go thru the list of loaded resources in resource set
			// to update the URI
			//
			for (Resource res : getResourceSet().getResources()) {
				ResourceDescriptor desc = (ResourceDescriptor) oldURIToResourceDescriptorMap
						.get(res.getURI());
				if (desc != null) {
					res.setURI(desc.getResolvedURI());
				}
			}
		}
	}

	public void commit() {
		if (finalURI != null && !getContents().isEmpty()) {
			File finalFile = new File(finalURI.toFileString());
			boolean wasMove = !oldURI.equals(finalURI);
			if (wasMove) {
				Object e = PersistenceUtil.getMethodElement(this);
				if (e instanceof ContentDescription) {
					if (finalFile.exists()) {
						// name for finalFile is already taken
						// try to get the next available name
						//
						finalURI = URI.createFileURI(MethodLibraryPersister
								.getNextAvailableFileName(finalFile.getParent()
										+ File.separator,
										(ContentDescription) e));
						finalFile = new File(finalURI.toFileString());
					}
				}
			} else {
				// back up the file
				//
				String backup = getBackupFilePath();
				File bakFile = new File(backup);

				// trying to delete the old backup file if it exists
				//
				if (bakFile.exists()) {
					bakFile.delete();
				}

				if (finalFile.exists()) {
					// some CM provider like ClearCase renamed the versioned
					// file it its repository as soon as user renamed the file
					// in the workspace. To avoid this, use only regular rename
					// routine of java.io.File instead of IResource routine
					//
					if (FileUtil.moveFile(finalFile, bakFile)) {
						backupFile = backup;
					} else {
						String msg = NLS.bind(
								PersistenceResources.renameError_msg,
								finalFile, backup);
						throw new MultiFileIOException(msg);
					}
				}
			}

			// rename the resource file to the original name
			//
			File currentFile = new File(wasMove ? oldURI.toFileString()
					: getURI().toFileString());
			boolean success = false;
			Exception ex = null;
			if (wasMove) {
				success = MultiFileSaveUtil.move(this, currentFile, finalFile);
			} else {
				// some CM provider like ClearCase renamed the versioned file in
				// its repository as soon as user renamed the file
				// in the workspace. To avoid this, use only regular rename
				// routine of java.io.File instead of IResource routine
				//
				try {
					FileUtil.doMoveFile(currentFile, finalFile);
					success = true;
				}
				catch(Exception e) {
					ex = e;
				}
			}
			if (!success) {
				String msg = NLS.bind(PersistenceResources.renameError_msg,
						currentFile, finalFile);
				if(ex != null) {
					msg = msg + ": "  + (ex.getMessage() != null ? ex.getMessage() : ex.toString()); //$NON-NLS-1$
				}
				throw new MultiFileIOException(msg);
			} else {
				if (wasMove) {
					// refresh resolve URI of ResourceDescriptor of this
					// resource
					//
					ResourceDescriptor resDesc = MultiFileSaveUtil
							.getResourceDescriptor(this);
					if (resDesc != null) {
						resDesc.clearResolvedURI();
						resDesc.getResolvedURI();
					} 
					else {
						if (MultiFileSaveUtil.DEBUG) {
							MethodElement e = PersistenceUtil
									.getMethodElement(this);
							if (e != null
									&& !getLibraryPersister()
											.hasOwnResourceWithoutReferrer(e)) {
								String msg = "FATAL ERROR: no ResourceDescriptor found in parent resource for " + this; //$NON-NLS-1$
								CommonPlugin.INSTANCE.log(msg);
								System.err.println(msg);
							}
						}
					}

					refreshURIOfChildResources();
				}

				RefreshJob.getInstance().resourceSaved(this);
			}
		}
	}

	public String getBackupFilePath() {
		String backupFile = new StringBuffer(getTempDir())
				.append(File.separator)
				.append(currentTxID)
				.append("old").append(PersistenceUtil.getMethodElement(this).getGuid()).toString(); //$NON-NLS-1$
		return backupFile;
	}

	/**
	 * @return the lastLoadTimeStamp
	 */
	public long getLastLoadTimeStamp() {
		return lastLoadTimeStamp;
	}

	static void clearDetachedEObjectToIDMap() {
		DETACHED_EOBJECT_TO_ID_MAP.clear();
	}

	/**
	 * @param resDesc
	 */
	void setResourceDescriptor(ResourceDescriptor resDesc) {
		resourceDescriptor = resDesc;
	}
	
	public ResourceDescriptor getResourceDescriptor() {
		return resourceDescriptor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResource#getLoadStamp()
	 */
	public long getLoadStamp() {
		return getLastLoadTimeStamp();
	}

	public URI getProxyURI(EObject object) {
		String guid = MultiFileSaveUtil.getGuid(object);
		if (guid != null) {
			ResourceDescriptor resourceDescriptor = this.resourceDescriptor != null ? this.resourceDescriptor :
					MultiFileSaveUtil.getResourceDescriptor(this);
			URI uri = resourceDescriptor != null ? MultiFileURIConverter
					.createURI(resourceDescriptor.getId()) : getURI();
			return uri.appendFragment(guid);
		}
		return getURI().appendFragment(getID(object));
	}

	public void setTxID(String txID) {
		setTempURI(txID);
	}
	
	private ILibraryPersister getLibraryPersister() {
		return ((MultiFileResourceSetImpl)resourceSet).getPersister();
	}
	
	private void handleSynFreeFlag(MethodElement e) {
		if (e instanceof MethodPlugin || e instanceof ProcessComponent) {
			MethodLibrary lib = UmaUtil.getMethodLibrary(e);
			if (lib == null) {
				lib = ((MultiFileResourceSetImpl)resourceSet).getMethodLibrary();
			}
			if (lib != null) {
				boolean isSynFree = UmaUtil.isSynFreeLibrary(lib);
				if (isSynFree) {
					if (e instanceof MethodPlugin) {
						MethodPlugin p = (MethodPlugin) e;
						if (! UmaUtil.isSynFreePlugin(p)) {
							boolean oldD = p.eDeliver();
							try {
								p.eSetDeliver(false);
								UmaUtil.setSynFreePlugin(p, true);
							} finally {
								if (oldD) {
									p.eSetDeliver(oldD);
								}
							}
						}					
					}
				}
			}
		}
	}
}