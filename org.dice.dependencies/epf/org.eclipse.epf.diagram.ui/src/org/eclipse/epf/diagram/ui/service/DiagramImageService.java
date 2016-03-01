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
package org.eclipse.epf.diagram.ui.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.diagram.ad.part.ActivityDiagramEditorPlugin;
import org.eclipse.epf.diagram.add.part.ActivityDetailDiagramEditorPlugin;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.services.DiagramHelper;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.diagram.model.util.IAdapterFactoryFilter;
import org.eclipse.epf.diagram.ui.DiagramUIPlugin;
import org.eclipse.epf.diagram.ui.viewer.AbstractDiagramGraphicalViewer;
import org.eclipse.epf.diagram.ui.viewer.NewActivityDetailDiagramViewer;
import org.eclipse.epf.diagram.ui.viewer.NewActivityDiagramViewer;
import org.eclipse.epf.diagram.ui.viewer.NewWPDependencyDiagramViewer;
import org.eclipse.epf.diagram.wpdd.part.WPDDiagramEditorPlugin;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.IMethodConfigurationProvider;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.VariabilityInfo;
import org.eclipse.epf.library.edit.process.BSActivityItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.ConfigurableComposedAdapterFactory;
import org.eclipse.epf.library.edit.util.DiagramOptions;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.ProcessAdapterFactoryFilter;
import org.eclipse.epf.library.layout.diagram.DiagramInfo;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


/**
 * Provides service methods for creating diagram images for activity elements
 *   
 * @author Shashidhar Kannoori
 * @author Jinhua Xi
 * @since 1.0
 */
public class DiagramImageService implements IDiagramImageService {

	static boolean debug  = DiagramUIPlugin.getDefault().isDebugging();
	
	private Composite parent = null;

	private Composite holder = null;

	private File pubDir;
	
	private boolean forXMLExport = false;

	private static Map<String, Integer> typeMap = new HashMap<String, Integer>();

	private DiagramInfo diagramInfo = null;

	private boolean publishUncreatedADD = true;
	
	private boolean publishADForActivityExtension = true;
	
	private boolean exist = true;
	
	private MethodConfiguration config = null;


	static {
		typeMap.put(ResourceHelper.DIAGRAM_TYPE_WORKFLOW, new Integer(
				IDiagramManager.ACTIVITY_DIAGRAM));
		typeMap.put(ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL, new Integer(
				IDiagramManager.ACTIVITY_DETAIL_DIAGRAM));
		typeMap.put(ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY, new Integer(
				IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM));
	}

	public static int getIntType(String diagramType) {
		Integer type = (Integer) typeMap.get(diagramType);
		if (type != null) {
			return type.intValue();
		}

		return -1;
	}
	public static PreferencesHint getPreferenceHint(String diagramType){
		int type = getIntType(diagramType);
		switch (type) {
		case IDiagramManager.ACTIVITY_DIAGRAM:
			return ActivityDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			return ActivityDetailDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			return WPDDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
		default:
			return null;
		}
		
	}
	public static String getStringType(int type) {
		switch(type) {
		case IDiagramManager.ACTIVITY_DIAGRAM:
			return ResourceHelper.DIAGRAM_TYPE_WORKFLOW;
		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			return ResourceHelper.DIAGRAM_TYPE_ACTIVITY_DETAIL;
		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			return ResourceHelper.DIAGRAM_TYPE_WP_DEPENDENCY;
		}
		return null;
	}

	Shell shell = null;

	private Set<DiagramManager> diagramManagers = new HashSet<DiagramManager>();

//	public DiagramImageService() {
//		this(null, new File(LibraryService.getInstance().getCurrentMethodLibraryPath()));
//	}

	public DiagramImageService(File pubDir) {
		this(null, pubDir);
	}

	public DiagramImageService(Composite parent, File pubDir) {
		this.parent = parent;
		this.pubDir = pubDir;
	}

	private AbstractDiagramGraphicalViewer getDiagramViewer(int diagramType, Object wrapper) {
		// if the shell window is distroyed, recreate it
		if ((this.shell != null) && this.shell.isDisposed()) {
			this.parent = null;
			this.shell = null;
		}
		getViewerHolder(parent);

		switch (diagramType) {
		case IDiagramManager.ACTIVITY_DIAGRAM:
			return new NewActivityDiagramViewer(holder, wrapper);

		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			return new NewActivityDetailDiagramViewer(holder,wrapper);

		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			return new NewWPDependencyDiagramViewer(holder,wrapper);

		default:
			return null;
		}
	}

	private void getViewerHolder(Composite parent) {
		if (parent == null) {
			if (shell == null || shell.isDisposed()) {
				shell = createShell();
			}
			shell.open();
			parent = shell;
		}

		if (holder != null) {
			holder.dispose();
		}

		holder = new Composite(parent, SWT.NONE);
		holder.setLayoutData(new GridData(1, 1)); // size can't be 0,0
		// otherwise the diagram
		// will not be painted
		holder.setLayout(new GridLayout());
		holder.setVisible(false);
	}

	private Shell createShell() {
		Shell shell = null;
		Display d = Display.getDefault();
		shell = new Shell(d);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		shell.setLayout(layout);
		shell.setBounds(0, 0, 0, 0);
		shell.setVisible(false);
		return shell;
	}

	public void dispose() {
		if ((shell != null) && (!shell.isDisposed())) {
			shell.close();
			shell.dispose();
		}
		
		for (DiagramManager mgr : diagramManagers) {
			mgr.removeConsumer(this);
		}
	}

	/**
	 * save the element diagram image and returns the image file url.
	 * 
	 * @param wrapper
	 * @param imgPath
	 * @param diagramType
	 * @param filter
	 *            IFilter
	 * @param sup
	 *            Suppression
	 * @return String the image path relative to the publishing dir
	 * 
	 * @see org.eclipse.epf.library.layout.diagram.IActivityDiagramService#saveDiagram(java.lang.Object, java.lang.String, java.lang.String, org.eclipse.epf.library.edit.IFilter, org.eclipse.epf.library.edit.util.Suppression)
	 */
	public DiagramInfo saveDiagram(final Object wrapper, final String imgPath, 
			final String diagramType, final IFilter filter,
			final Suppression sup) {
		// initialize the diagramInfo
		diagramInfo = null;

		// grab the UI thread to avoid thread access error
		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				__internal_saveDiagram(wrapper, imgPath, diagramType, filter, sup);
			}
		});

		return diagramInfo;
	}

	private boolean hasUserDefinedDiagram(Activity e, String imgPath, int type)
			throws Exception {
		// if there is a user defined diagram, use it
		org.eclipse.epf.diagram.model.util.DiagramInfo info = new org.eclipse.epf.diagram.model.util.DiagramInfo(
				(Activity) e);
		switch (type) {
		case IDiagramManager.ACTIVITY_DIAGRAM:
			if (info.canPublishADImage()) {
				return (info.getActivityDiagram() != null)
						&& info.canPublishADImage();
			}
			break;

		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			if (info.canPublishADDImage()) {
				return (info.getActivityDetailDiagram() != null)
						&& info.canPublishADDImage();
			}
			break;

		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			if (info.canPublishWPDImage()) {
				return (info.getWPDDiagram() != null)
						&& info.canPublishWPDImage();
			}
			break;

		default:
			break;
		}

		return false;
	}
	
	private void __internal_saveDiagram(Object wrapper, final String imgPath,
			String diagramType, IFilter filter, Suppression sup) {
		if (sup.isSuppressed(wrapper)) {
			return;
		}
		// DiagramInfo diagramInfo = null;

		int type = getIntType(diagramType);
		if (type < 0) {
			return;
		}
		_internal_generateImage(wrapper, imgPath, type, filter, sup, true, true);
	}
	
	/**
	 * Creates a diagram JPEG for given Activity and  
	 * 
	 * @param wrapper
	 * @param imgPath
	 * @param diagramType
	 * @param filter
	 * @param sup
	 * @return
	 */
	public String generateDiagramImage(final Object wrapper, final String imgPath, 
			final int diagramType, final IFilter filter,
			final Suppression sup, final boolean realizedDiagram){
		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				_internal_generateImage(wrapper, imgPath, diagramType, filter, sup, false, realizedDiagram);
			}
		});
		return imgPath;
	}
	
	/**
	 * Method to create a diagram viewer and generate image and saves the image.
	 * 
	 * 
	 * @param wrapper
	 * @param imgPath
	 * @param diagramType
	 * @param filter
	 * @param sup
	 * @param calculateDiagramInfo
	 */
	private void _internal_generateImage(Object wrapper, final String imgPath,
			int type, IFilter filter, Suppression sup, boolean calculateDiagramInfo,
			boolean realizedDiagram){
		
		exist = true;
		Image image = null;
		
		Object o = TngUtil.unwrap(wrapper);
		if (!(o instanceof Activity)) {
			return;
		}

		Activity e = (Activity) o;
		
		AbstractDiagramGraphicalViewer viewer = null;

		// keep the dirty flag and reset back to avoid make the library dirty
		boolean dirtyFlag = e.eResource().isModified();

		try {
			
			Object object = canCreateImage(wrapper, imgPath, type, filter, sup);
			if(realizedDiagram && object == null){
				return;
			}else{
				wrapper = object;
			}
			try {
				viewer = getDiagramViewer(type, wrapper);
				if(config != null){
					viewer.setMethodConfiguration(config);
				}
				EditPart diagramEditPart = viewer.loadDiagram(wrapper, !exist, filter, sup);
				if(diagramEditPart == null){
					if(debug){
						DiagramUIPlugin.getDefault().getLogger().
						logError("Publishing: DiagramImageService viewer EditPart is null: "+wrapper);	//$NON-NLS-1$
					}
					return;
				}else{
					if(diagramEditPart.getChildren().isEmpty())
						return;
				}
				if(calculateDiagramInfo){
					diagramInfo = viewer.getDiagramInfo();
				}
				image = viewer.createDiagramImage();
				if (image != null) {
					// save the image
					File f = new File(pubDir, imgPath);

					// make sure the file is created otherwise exception
					File parent = f.getParentFile();
					if (!parent.exists()) {
						parent.mkdirs();
					}

					if (!f.exists()) {
						f.createNewFile();
					}
					OutputStream os = new FileOutputStream(f);
					ImageLoader loader = new ImageLoader();
					loader.data = new ImageData[] { image.getImageData() };
					loader.save(os, SWT.IMAGE_JPEG);					
				} else {
					DiagramUIPlugin.getDefault().getLogger().logError(
							"Failed to create diagram image for" + e); //$NON-NLS-1$
				}
				if (calculateDiagramInfo && diagramInfo != null && !diagramInfo.isEmpty()) {
					diagramInfo.setImageFilePath(imgPath);
				}
			} catch (RuntimeException e1) {
				e1.printStackTrace();
			}

			// delete the newly created diagram from the library
			if (!exist) {
				Diagram d = getDiagram(e, type);
				if (d != null) {
					DiagramHelper.deleteDiagram(d, false);
				}
			}
		} catch (Exception ex) {
			DiagramUIPlugin.getDefault().getLogger().logError("Exception"+e); //$NON-NLS-1$
		} finally {
			try {
				// restore the dirty flag
				e.eResource().setModified(dirtyFlag);

				if (viewer != null) {
					viewer.dispose();
				}
				if (image != null) {
					image.dispose();
				}
			} catch (RuntimeException e1) {
				e1.printStackTrace();
			}
		}
	}
	

	private Object canCreateImage(Object wrapper, final String imgPath,
			int type, IFilter filter, Suppression suppression){

		Object o = TngUtil.unwrap(wrapper);
		if (!(o instanceof Activity)) {
			return null;
		}
		try{
			//MethodElement e = (MethodElement)o;
			Activity e = (Activity) o;
			if (hasUserDefinedDiagram((Activity) e, imgPath, type)) {
				return null;
			}
			// Check diagram exists, don't generate for suppressed.
			Diagram d = getDiagram(e, type);

			// Check publishing options.
			exist = (d != null);
			
			if (isForXMLExport()) {
				if (exist && canPublishDiagram(d,e)) {
					return e;
				}				
				return null;
			}
			
			if (exist) {
				
				if(!canPublishDiagram(d,e)) return null;
				
				if (type == IDiagramManager.ACTIVITY_DIAGRAM ) {
					
					// If an extension has its own diagram. Base is replaced or
					// contributed.extension diagram shows realized element in undefined
					// location. In publishing don't display extension diagram even if it has
					// its own diagram if realized elements are coming in through
					// variability.
					if ( isBreakdownElementsReplaced(e, filter) ) {
						return null;
					}
				
					// if the object is a wrapper, the diagram is from it's base,
					// don't show the AD id the preference is not set
					if ( (e != wrapper) && !publishADForActivityExtension ) {
						return null;
					}

					// Check variability and base's variability.
//					VariabilityElement calculatedBase = checkVariability(e,
//							filter, type);
//					if (calculatedBase != null) {
//						wrapper = calculatedBase;
//					}
					
				} else if ( type == IDiagramManager.ACTIVITY_DETAIL_DIAGRAM) {
					if ( (e != wrapper) && !publishUncreatedADD ) {
						return null;
					}
				}
				
			} else {

				if ((type == IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM))
					return null;

				// For Activity Diagram un opened extension publish.
				if (type == IDiagramManager.ACTIVITY_DIAGRAM) {
					// If option is not checked, don't generate a diagram
					if (!publishADForActivityExtension)
						return null;

					// If extension is modified don't generate it.
					if (!e.getBreakdownElements().isEmpty())
						return null;
					
					// Check any child is suppressed. 
					// if any child is suppresed don't generate diagram
					if(anyChildrenSuppressed(wrapper, filter, suppression)) 
						return null;
					
					// Check variability and base's variability.
					VariabilityElement calculatedBase = checkVariability(e,
							filter, type);
					if (calculatedBase == null) {
						return null;
					}

					wrapper = calculatedBase;
					e = (Activity) calculatedBase;
					exist = true;
				}

				if (publishUncreatedADD == false
						&& type == IDiagramManager.ACTIVITY_DETAIL_DIAGRAM) {
					boolean contributorexist = false;
					// This is need, if contributor has a ADD diagram, base don't
					// base should generate ADD in browsing.
					MethodConfiguration config = null;
					if (filter instanceof ProcessAdapterFactoryFilter) {
						config = ((ProcessAdapterFactoryFilter) filter)
								.getMethodConfiguration();
					}
					if (config == null)
						return null;

					// Get immediate contributors first, and check immediate
					// contributors
					// have anything extra breakdown elements.
					List list = ConfigurationHelper.getContributors(e, config);
					if (e instanceof Activity) {
						Iterator iterator = list.iterator();
						if (iterator != null) {
							while (iterator.hasNext()) {
								Object act = iterator.next();
								if (act != null) {
									Diagram dx = getDiagram((Activity)act, type);
									if (dx != null) {
										contributorexist = true;
										break;
									}
								}
							}
						}
					}
					if (!contributorexist)
						return null;
				}
			}
		}catch(Exception e){
			return null;
		}
		return wrapper;
	}

	private boolean canPublishDiagram(Diagram diagram, Activity activity) {
		switch (DiagramHelper.getDiagramType(diagram)) {
		case IDiagramManager.ACTIVITY_DIAGRAM:
			if (DiagramOptions.isPublishAD(activity)) {
				return true;
			}
			break;
		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			if (DiagramOptions.isPublishADD(activity)) {
				return true;
			}
			break;
		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			if (DiagramOptions.isPublishWPDD(activity)) {
				return true;
			}
			break;
		}
		return false;
	}
	
	private boolean anyChildrenSuppressed(Object wrapper, IFilter filter, Suppression suppression) {
		ITreeItemContentProvider adapter = getAdapter(wrapper, filter);
		boolean oldRolledUp = false;
		if(adapter instanceof BSActivityItemProvider) {
			BSActivityItemProvider itemProvider = (BSActivityItemProvider)adapter;
			oldRolledUp = itemProvider.isRolledUp();
			itemProvider.basicSetRolledUp(false);
		}
		else if(adapter instanceof IBSItemProvider){
			IBSItemProvider itemProvider = (IBSItemProvider)adapter;
			oldRolledUp = itemProvider.isRolledUp();
			itemProvider.setRolledUp(false);
		}
		
		try {
			// filter out the suppressed elements
			//
			Object element = TngUtil.unwrap(wrapper);
			for (Iterator iter = adapter.getChildren(element).iterator(); iter.hasNext();) {
				Object child = iter.next();
				if(suppression.isSuppressed(child)) {
					return true;
				}
			}
		}
		finally {
			// restore the rolled-up flag
			//
			if(adapter instanceof IBSItemProvider) {
				((IBSItemProvider)adapter).setRolledUp(oldRolledUp);
			}
		}
		
		return false;
	}
	
	/**
	 * Moved code from getChildren. getAdapter() will return Adapter,
	 * which will allow us to find itemprovider for the children. 
	 * 
	 * @return
	 */
	private ITreeItemContentProvider getAdapter(Object wrapper, IFilter filter){
		ITreeItemContentProvider adapter = null;
		if (wrapper instanceof BreakdownElementWrapperItemProvider) {
			adapter = (BreakdownElementWrapperItemProvider)wrapper;
		} else {
			adapter = (ITreeItemContentProvider) getAdapterFactory(filter).adapt(
					wrapper, ITreeItemContentProvider.class);
		}
		return adapter;
	}
	
	AdapterFactory getAdapterFactory(IFilter filter){
		AdapterFactory adapterFactory = null;
		if (filter == null) {
			adapterFactory = TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory();
		} else if (filter instanceof IAdapterFactoryFilter) {
			adapterFactory = (ConfigurableComposedAdapterFactory) ((IAdapterFactoryFilter) filter)
					.getWBSAdapterFactory();
		}
		return adapterFactory;
	}
	
	private VariabilityElement checkVariability(VariabilityElement e, IFilter filter,
			int type) {

		MethodConfiguration config = null;
		if (filter instanceof ProcessAdapterFactoryFilter) {
			config = ((ProcessAdapterFactoryFilter) filter)
					.getMethodConfiguration();
		}
		if (config == null)
			return null;

		// Get immediate contributors first, and check immediate contributors
		// have anything extra breakdown elements.
		List list = ConfigurationHelper.getContributors(e, config);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object next = iterator.next();
			if (next instanceof Activity) {
				if (!((Activity) next).getBreakdownElements().isEmpty())
					return null;
			}
		}

		// Get all Contributors from parent chain and contributor chain for the
		// element e.
		VariabilityInfo eInfo = ((ProcessAdapterFactoryFilter) filter)
				.getVariabilityInfo((VariabilityElement) e);
		List contributors = eInfo.getContributors();

		VariabilityElement ve = e.getVariabilityBasedOnElement();
		if (ve == null) {
			return null;
		}
		
		Activity replacer = (Activity) ConfigurationHelper.getReplacer(ve,
				config);
		if (replacer != null) {
			ve = replacer;
			Diagram replacerDiagram = getDiagram(replacer, type);
			if (replacerDiagram != null) {
				return replacer;
			} else {
				return null;
			}
		} else {
			Diagram baseDiagram = getDiagram((Activity) ve, type);
			if (baseDiagram != null) {
				
				// Check first if baseDiagram is suppressed.
				if(BridgeHelper.isSuppressed(baseDiagram)) return null;
				
				// Find the contributors of Base
				VariabilityInfo veInfo = ((ProcessAdapterFactoryFilter) filter)
						.getVariabilityInfo((VariabilityElement) ve);
				List veContributors = veInfo.getContributors();
				if (contributors.size() != veContributors.size()) {
					for (Iterator iterator = contributors.iterator(); iterator
							.hasNext();) {
						Object next = iterator.next();
						if (!veContributors.contains(next)) {
							if (!((Activity) next).getBreakdownElements()
									.isEmpty()) {
								return null;
							}
						}
					}
				}
				
				return ve;
				
			}else{
				// If no base diagram, check base of base had any diagram.
				return checkVariability(ve, filter, type);
			}
		}
		
	}
	
	public VariabilityElement checkVariability(VariabilityElement e, IFilter filter,
			int type, boolean checkUDD) {

		MethodConfiguration config = null;
		org.eclipse.epf.diagram.model.util.DiagramInfo uddInfo = null;
		
		if (filter instanceof ProcessAdapterFactoryFilter) {
			config = ((ProcessAdapterFactoryFilter) filter)
					.getMethodConfiguration();
		}
		if (config == null)
			return null;

		// Get immediate contributors first, and check immediate contributors
		// have anything extra breakdown elements.
		List list = ConfigurationHelper.getContributors(e, config);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object next = iterator.next();
			if (next instanceof Activity) {
				if (!((Activity) next).getBreakdownElements().isEmpty())
					return null;
			}
		}

		// Get all Contributors from parent chain and contributor chain for the
		// element e.
		VariabilityInfo eInfo = ((ProcessAdapterFactoryFilter) filter)
				.getVariabilityInfo((VariabilityElement) e);
		List contributors = eInfo.getContributors();

		VariabilityElement ve = e.getVariabilityBasedOnElement();
		if (ve == null) {
			return null;
		}
		
		Activity replacer = (Activity) ConfigurationHelper.getReplacer(ve,
				config);
		if (replacer != null) {
			ve = replacer;
			Diagram replacerDiagram = getDiagram(replacer, type);
			if(checkUDD){
				uddInfo = new org.eclipse.epf.diagram.model.util.DiagramInfo(replacer);
				if(uddInfo.canPublishADImage() && uddInfo.getActivityDiagram() != null){
					return replacer;
				}
			}else{
				if (replacerDiagram != null) {
					return replacer;
				}
			}
			return null;
		} else {
			
			Diagram baseDiagram = null;
			if(checkUDD){
				uddInfo = new org.eclipse.epf.diagram.model.util.DiagramInfo((Activity)ve);
			}else{
				baseDiagram = getDiagram((Activity) ve, type);
			}
			
			if (baseDiagram != null || (uddInfo != null && uddInfo.canPublishADImage() && uddInfo.getActivityDiagram() != null)) {
				
				// Check first if baseDiagram is suppressed.
//				if(BridgeHelper.isSuppressed(baseDiagram)) return null;
				
				// Find the contributors of Base
				VariabilityInfo veInfo = ((ProcessAdapterFactoryFilter) filter)
						.getVariabilityInfo((VariabilityElement) ve);
				List veContributors = veInfo.getContributors();
				if (contributors.size() != veContributors.size()) {
					for (Iterator iterator = contributors.iterator(); iterator
							.hasNext();) {
						Object next = iterator.next();
						if (!veContributors.contains(next)) {
							if (!((Activity) next).getBreakdownElements()
									.isEmpty()) {
								return null;
							}
						}
					}
				}
				
				return ve;
				
			}else{
				// If no base diagram, check base of base had any diagram.
				return checkVariability(ve, filter, type);
			}
		}
		
	}
	

	/**
	 * Set the window's preference attribute for Activity Detail Diagram.
	 * 
	 */
	public void setPublishedUnCreatedADD(boolean flag) {
		this.publishUncreatedADD = flag;
	}
	/**
	 * Set the window's preference attribute for Acitivyt Diagram
	 * 
	 */
	
	public void setPublishADForActivityExtension(boolean flag){
		this.publishADForActivityExtension = flag;
	}

//	private DiagramGenerator createDiagramImage(DiagramEditPart diagramEP, ImageFileFormat fileFormat)
//	throws CoreException {
//		CopyToImageUtil util = new CopyToImageUtil();
//		DiagramGenerator dGen = util.copyToImage(diagramEP, new Path(pubDir.getPath()), fileFormat, new NullProgressMonitor());
//		dGen.createAWTImageForDiagram();
//		return dGen;
//	}
	
	public Diagram getDiagram(Activity e, int type){
		DiagramManager dMgr = getDiagramManager(e);
		Diagram diagram = null;
		if (dMgr != null) {
			try {
				List<Diagram> list = dMgr.getDiagrams(e, type);
				if (!list.isEmpty()) {
					diagram = (Diagram) list.get(0);
				}
			} catch (Exception ex) {
				DiagramUIPlugin.getDefault().getLogger().logError(
						"Error in getDiagram()" + ex.getMessage()); //$NON-NLS-1$
			}
		}
		return diagram;
	}
	
	private DiagramManager getDiagramManager(Activity e) {
		DiagramManager mgr = DiagramManager.getInstance(TngUtil.getOwningProcess(e), this);
		diagramManagers .add(mgr);
		return mgr;
	}
	
	/**
	 * gets the method configuration  
	 * @return
	 */
	public MethodConfiguration getConfig() {
		return config;
	}
	/**
	 * sets the method configuration.
	 * @param config
	 */
	public void setConfig(MethodConfiguration config) {
		this.config = config;
	}
	

	/**
	 * Checks any breakdownelements of an Activity has a replacer.
	 * 
	 * @param element
	 *              VariabilityElement the element
	 * @param config 
	 * 				MethodConfiguration
	 * @return VariabilityElement the replacer if there is one and ONLY one,
	 *         null otherwise
	 */
	private boolean isBreakdownElementsReplaced(Activity activity,
			IFilter filter) {
		
		MethodConfiguration config = null;
		if (filter instanceof IMethodConfigurationProvider) {
			config = ((IMethodConfigurationProvider) filter)
					.getMethodConfiguration();
		}
		if (config == null)
			return true;
		
		for (Iterator iter = activity.getBreakdownElements().iterator(); iter
				.hasNext();) {
			BreakdownElement element = (BreakdownElement) iter.next();
			if (element instanceof Activity) {
				if(ConfigurationHelper.getReplacer((Activity)element, config) != null)
					return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Method to get a DiagramViewer for given activity.  
	 * Without doing realization and doesn't check for diagram exists or not. 
	 */
	
	public AbstractDiagramGraphicalViewer getDiagramViewer(final Object wrapper,
			final int type, final IFilter filter,
			final Suppression sup){
		Object o = TngUtil.unwrap(wrapper);
		if (!(o instanceof Activity)) {
			return null;
		}

		Activity e = (Activity) o;
		
		AbstractDiagramGraphicalViewer viewer = null;
		if (type < 0) {
			return null;
		}
		viewer = getDiagramViewer(type, wrapper);
		return viewer;
	}
	
	public Activity getRealizedForUnmodified(Object wrapper, IFilter filter, 
			Suppression suppression){
		Activity activity = (Activity)TngUtil.unwrap(wrapper);
		if ( activity == null ) {
			return null;
		}
		
		if (!publishADForActivityExtension)
			return null;
		// If extension is modified don't generate it.
		if (!activity.getBreakdownElements().isEmpty())
			return null;
		
		// Check any child is suppressed. 
		// if any child is suppresed don't generate diagram
		if(anyChildrenSuppressed(activity, filter, suppression)) 
			return null;
		
		// Check variability and base's variability.
		VariabilityElement calculatedBase = checkVariability(activity,
				filter, IDiagramManager.ACTIVITY_DIAGRAM, true);
		if (calculatedBase == null) {
			return null;
		}

		wrapper = calculatedBase;
		return (Activity)wrapper;
	}
	
	/**
	 * @param pubDir
	 */
	public void setPubDir(File pubDir) {
		this.pubDir = pubDir;
	}
	
	private boolean isForXMLExport() {
		return forXMLExport;
	}
	
	/**
	 * @param forXMLExport
	 */
	public void setForXMLExport(boolean forXMLExport) {
		this.forXMLExport = forXMLExport;
	}
	
}