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
package org.eclipse.epf.authoring.gef.viewer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.diagram.model.util.GraphicalDataManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.VariabilityInfo;
import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.ProcessAdapterFactoryFilter;
import org.eclipse.epf.library.layout.diagram.DiagramInfo;
import org.eclipse.epf.library.layout.diagram.IActivityDiagramService;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.VariabilityElement;
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
 * @author Jinhua Xi
 * @since 1.0
 */
public class ActivityDiagramService implements IActivityDiagramService {

	private Composite parent = null;

	private Composite holder = null;

	private File pubDir;

	private static Map typeMap = new HashMap();

	private DiagramInfo diagramInfo = null;

	private boolean publishUncreatedADD = true;
	
	private boolean publishADForActivityExtension = true;

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

	Shell shell = null;

	public ActivityDiagramService() {
		this(null, new File(LibraryService.getInstance().getCurrentMethodLibraryLocation()));
	}

	public ActivityDiagramService(File pubDir) {
		this(null, pubDir);
	}

	public ActivityDiagramService(Composite parent, File pubDir) {
		this.parent = parent;
		this.pubDir = pubDir;
	}

	private AbstractDiagramGraphicalViewer getDiagramViewer(int diagramType) {
		// if the shell window is distroyed, recreate it
		if ((this.shell != null) && this.shell.isDisposed()) {
			this.parent = null;
			this.shell = null;
		}

		getViewerHolder(parent);

		switch (diagramType) {
		case IDiagramManager.ACTIVITY_DIAGRAM:
			return new ActivityDiagramViewer(holder);

		case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
			return new ActivityDetailDiagramViewer(holder);

		case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
			return new WPDependencyDiagramViewer(holder);
			
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

	private boolean hasUserDefinedDiagram(Activity e, String imgPath, String diagramType) throws Exception
	{		
		// if there is a user defined diagram, use it
		org.eclipse.epf.diagram.model.util.DiagramInfo info = new org.eclipse.epf.diagram.model.util.DiagramInfo((Activity)e);
		switch (getIntType(diagramType))
		{
			case IDiagramManager.ACTIVITY_DIAGRAM:
				if ( info.canPublishADImage() )
				{
					return (info.getActivityDiagram() != null) && info.canPublishADImage();
				}
				break;
	
			case IDiagramManager.ACTIVITY_DETAIL_DIAGRAM:
				if ( info.canPublishADDImage() )
				{
					return (info.getActivityDetailDiagram() != null) && info.canPublishADDImage();
				}
				break;
							
			case IDiagramManager.WORK_PRODUCT_DEPENDENCY_DIAGRAM:
				if ( info.canPublishWPDImage() )
				{
					return (info.getWPDDiagram() != null ) && info.canPublishWPDImage();
				}
				break;

			default:
				break;
		}
		
		return false;
	}
	
	private void __internal_saveDiagram(Object wrapper, final String imgPath, String diagramType,
			IFilter filter, Suppression sup) {
		
		if ( sup.isSuppressed(wrapper) ) {
			return;
		}
		
		Object o = TngUtil.unwrap(wrapper);
		if (!(o instanceof Activity)) {
			return;
		}

		//MethodElement e = (MethodElement)o;
		Activity e = (Activity)o;
		// DiagramInfo diagramInfo = null;
		Image image = null;
		int type = getIntType(diagramType);
		if (type < 0) {
			return;
		}

		AbstractDiagramGraphicalViewer viewer = null;

		// keep the dirty flag and reset back to avoid make the library dirty
		boolean dirtyFlag = e.eResource().isModified();

		try {
			if ( hasUserDefinedDiagram((Activity)e, imgPath, diagramType) )
			{
				return;
			}


			// first check if we need to generate the diagram or not
			// if the diagram is supressed, don't generate diagram
			// don't create the diagram if it's not there,
			// id the diagram does not exist, it's supressed
			org.eclipse.epf.uma.Diagram d = GraphicalDataManager.getInstance()
					.getUMADiagram((Activity) e, type, false);

			// Allow the option to publish 'uncreated' diagrams
			// by default, uncreated activity detail diagrams will be published,
			// uncreated diagrams of other types will not be published
			boolean exist = (d != null);
			if (exist) {
				if (d.getSuppressed().booleanValue() == true)
					return;
				
				// If an extension has its own diagram. Base is replaced or contributed. 
				// extension diagram shows realized element in undefined location. 
				// In publishing don't display extension diagram even if it has its own
				// diagram if realized elements are coming in through variability.
				if(type == GraphicalDataHelper.ACTIVITY_DIAGRAM &&
						checkVariability(e, filter,type) != null){
					return;
				}
					
			}else{
				
				if((type == GraphicalDataHelper.WORK_PRODUCT_DEPENDENCY_DIAGRAM))
					return;

				// For Activity Diagram un opened extension publish.
				if(type == GraphicalDataHelper.ACTIVITY_DIAGRAM){
					// If option is not checked, don't generate a diagram
					if(!publishADForActivityExtension) return;
					
					//If extension is modified don't generate it.
					if(!e.getBreakdownElements().isEmpty())
						return;
					VariabilityElement calculatedBase = checkVariability(e, filter, type);
					if(calculatedBase == null) {
						return;
					}	
					
					wrapper = calculatedBase;
					e = (Activity)calculatedBase;
					exist = true;
				}
				
				if (publishUncreatedADD == false && type == GraphicalDataHelper.ACTIVITY_DETAIL_DIAGRAM){
					boolean contributorexist = false;
					// This is need, if contributor has a ADD diagra, base don't 
					// base should generate ADD in browsing.
					MethodConfiguration config = null;
					if (filter instanceof ProcessAdapterFactoryFilter) {
						config = ((ProcessAdapterFactoryFilter) filter)
								.getMethodConfiguration();
					}
					if (config == null)
						return;

					// Get immediate contributors first, and check immediate contributors
					// have anything extra breakdown elements.
					List list = ConfigurationHelper.getContributors(e, config);
					if(e instanceof Activity){
						Iterator iterator = list.iterator();
						if(iterator != null){
							while(iterator.hasNext()){
								Object act = iterator.next();
								if(act != null){
									org.eclipse.epf.uma.Diagram dx = GraphicalDataManager.getInstance()
									.getUMADiagram((Activity) act, type, false);
									if(dx != null){   
										contributorexist = true;
										break;
									}
								}
							}
						}
					}
					if(!contributorexist)
					return;
				}
			}
			
			try {
				viewer = getDiagramViewer(type);
				viewer.loadDiagram(wrapper, !exist, filter, sup);
				diagramInfo = viewer.getDiagramInfo();
				if (diagramInfo != null && !diagramInfo.isEmpty()) {
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

						diagramInfo.setImageFilePath(imgPath);
					} else {
						System.out.println("Unable to create diagram image"); //$NON-NLS-1$
					}
				}
			} catch (RuntimeException e1) {
				e1.printStackTrace();
			}

			// delete the newly created diagram from the library
			if (!exist) {
				d = GraphicalDataManager.getInstance().getUMADiagram(
						(Activity) e, type, false);
				if (d != null) {
					EcoreUtil.remove(d);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
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
			org.eclipse.epf.uma.Diagram replacerDiagram = GraphicalDataManager
					.getInstance().getUMADiagram(replacer, type, false);
			if (replacerDiagram != null) {
				//anyReplacer.add(replacer);
				return replacer;
			} else {
				return null;
			}
		} else {
			org.eclipse.epf.uma.Diagram baseDiagram = GraphicalDataManager
					.getInstance()
					.getUMADiagram((Activity) ve, type, false);
			if (baseDiagram != null) {
				
				// Check first if baseDiagram is suppressed.
				if (baseDiagram.getSuppressed().booleanValue() == true)
					return null;
				
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

	public Activity getRealizedForUnmodified(Object activity, IFilter filter, Suppression suppression) {
		// Does nothing, just interface implementation.
		return null;
	}
}