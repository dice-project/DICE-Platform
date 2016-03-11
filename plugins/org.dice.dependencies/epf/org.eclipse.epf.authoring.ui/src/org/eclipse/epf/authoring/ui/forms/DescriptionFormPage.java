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
package org.eclipse.epf.authoring.ui.forms;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIExtensionManager;
import org.eclipse.epf.authoring.ui.AuthoringUIHelpContexts;
import org.eclipse.epf.authoring.ui.AuthoringUIImages;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput;
import org.eclipse.epf.authoring.ui.filters.ContentFilter;
import org.eclipse.epf.authoring.ui.preferences.AuthoringUIPreferences;
import org.eclipse.epf.authoring.ui.providers.ColumnElement;
import org.eclipse.epf.authoring.ui.providers.DescriptionPageColumnProvider;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichTextEditor;
import org.eclipse.epf.authoring.ui.util.EditorsContextHelper;
import org.eclipse.epf.authoring.ui.util.UIHelper;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.DeleteMethodElementCommand;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.MethodPluginPropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.library.ui.LibraryUIUtil;
import org.eclipse.epf.library.ui.actions.MethodElementDeleteAction;
import org.eclipse.epf.library.ui.util.ConvertGuidanceType;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.richtext.RichTextListener;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import com.ibm.icu.text.DateFormat;


/**
 * The base class for all Description pages in the Method editors.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.0
 * fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=176382
 */
public abstract class DescriptionFormPage extends BaseFormPage implements IRefreshable {

	public static final int BUTTON_WIDTH = 150;
	
	protected static final String NOT_APPLICABLE_TEXT = AuthoringUIResources.notApplicable_text; 
	
	protected static final String CONTRIBUTES_TEXT = AuthoringUIResources.contributes_text; 
	
	private static final String LOCAL_CONTRIBUTES_TEXT = AuthoringUIResources.localContributes_text;  

	protected static final String EXTENDS_TEXT = AuthoringUIResources.extends_text; 

	protected static final String REPLACES_TEXT = AuthoringUIResources.replaces_text; 
	
	protected static final String LOCAL_REPLACES_TEXT = AuthoringUIResources.localReplaces_text; 
	
	protected static final String EXTENDS_REPLACES_TEXT = AuthoringUIResources.extendsReplaces_text;

	protected static final ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
			TngUtil.umaItemProviderAdapterFactory);
	
	// Composites and sections.
	protected Section formSection;

	protected Composite sectionComposite;
	
	private Composite mainComposite;

	protected Composite expandedComposite;

	protected Section generalSection;

	protected Section variabilitySection;

	protected Composite generalComposite;

	protected Composite variabilityComposite;

	protected Section versionSection;

	protected Composite versionComposite;

	protected Section iconSection;

	protected Composite iconComposite;

	private Canvas pane1;

	private Canvas pane3;

	protected Section detailSection;

	protected Composite detailComposite;

	protected Section notationSection;

	protected Composite notationComposite;

	protected Section tailoringSection;

	protected Composite tailoringComposite;

	// Edit controls.
	protected Text ctrl_name;

	protected Text ctrl_presentation_name;
	
	protected Text ctrl_long_presentation_name;

	protected Label ctrl_type_label;

	protected Button ctrl_type_button;

	protected Text ctrl_brief_desc;

	protected IMethodRichText ctrl_full_desc;

	protected IMethodRichText ctrl_key;

	protected IMethodRichTextEditor ctrl_expanded;

	protected ComboViewer viewer_variability;

	protected Combo ctrl_variability;

	protected Label label_base;

	protected TableViewer base_viewer;

	protected Table ctrl_base;

	protected Button ctrl_base_button;

	private IMethodRichText activeControl;

	private Text ctrl_authors;

	protected Text ctrl_change_date;

	private Text ctrl_change_desc;

	private Text ctrl_version;

	protected Label label_copyright;

	protected TableViewer copyright_viewer;

	protected Table ctrl_copyright;

	private Button copyright_button;

	private Button copyright_button_deselect;

	protected ImageHyperlink expandDetailLink;

	protected Label expandDetailLabel;

	protected ImageHyperlink expandNotationLink;

	protected Label expandNotationLabel;

	protected ImageHyperlink expandTailoringLink;

	protected Label expandTailoringLabel;

	protected IMethodRichText ctrl_purpose;

	protected Text ctrl_external_id;

	private Image shapeImage;

	private Image nodeImage;

	protected Button ctrl_select_shapeIcon_button;

	protected Button ctrl_clear_shapeIcon_button;

	protected Button ctrl_select_nodeIcon_button;

	protected Button ctrl_clear_nodeIcon_button;
	
	protected Button ctrl_publish_categories_button;

	protected ImageHyperlink expandVersionLink;

	protected Label expandVersionLabel;

	protected ImageHyperlink expandLink;

	protected Label expandLabel;
	
	protected IStructuredContentProvider contentProviderVariability;

	protected IStructuredContentProvider contentProviderBase;
	
	private IColumnProvider columnProvider;
	
	protected List<ISectionProvider> sectionProviders;
	
	private boolean autoGenName = false;
	
	private DescriptionFormSectionExtender extender;
	
	protected ILabelProvider labelProviderVariability = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public String getText(Object object) {
			VariabilityType varObject = (VariabilityType) object;
			if (varObject == VariabilityType.NA)
				return NOT_APPLICABLE_TEXT;
			if (varObject == VariabilityType.CONTRIBUTES)
				return CONTRIBUTES_TEXT;
			if (varObject == VariabilityType.LOCAL_CONTRIBUTION)
				return LOCAL_CONTRIBUTES_TEXT;
			if (varObject == VariabilityType.EXTENDS)
				return EXTENDS_TEXT;
			if (varObject == VariabilityType.REPLACES)
				return REPLACES_TEXT;
			if (varObject == VariabilityType.LOCAL_REPLACEMENT)
				return LOCAL_REPLACES_TEXT;
			if (varObject == VariabilityType.EXTENDS_REPLACES)
				return EXTENDS_REPLACES_TEXT;
			
			return null;
		}
	};

	protected ILabelProvider labelProviderBase = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public String getColumnText(Object object, int columnIndex) {
			return TngUtil.getLabelWithPath(object);
		}
	};

	protected IActionManager actionMgr;

	protected IStructuredContentProvider copyrightContentProvider;

	private ILabelProvider copyrightLabelProviderBase = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory());

	protected ModifyListener modelModifyListener;
	
	protected MethodElementEditor.ModifyListener nameModifyListener;

	protected ModifyListener contentModifyListener;
	
	
	protected FocusAdapter nameFocusListener = new FocusAdapter() {
		public void focusGained(FocusEvent e) {
			((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget,
					UmaPackage.eINSTANCE.getNamedElement_Name());
		}

		public void focusLost(FocusEvent e) {
			String oldContent = methodElement.getName() ;
			String name = ctrl_name.getText().trim();
			if (((MethodElementEditor) getEditor()).mustRestoreValue(
					e.widget, methodElement.getName())) {
				return;
			}
			if (name.equals(methodElement.getName()))
				return;
			
			// 178462
			String msg = null;
			if (oldContent.indexOf("&") < 0 && name.indexOf("&") > -1) { //$NON-NLS-1$ //$NON-NLS-2$
				msg = NLS.bind(
						LibraryEditResources.invalidElementNameError4_msg,
						name);
			} else {
				IValidator validator = getNameValidator();
				if(validator == null){
					validator = IValidatorFactory.INSTANCE
						.createNameValidator(
								methodElement,
								TngAdapterFactory.INSTANCE
										.getNavigatorView_ComposedAdapterFactory());
				}
				msg = validator.isValid(name);
			}
			if (msg == null && !name.equals(methodElement.getName())) {
				if(name.indexOf("&") > -1) { //$NON-NLS-1$
					msg = NLS.bind(
							LibraryEditResources.invalidElementNameError4_msg,
							name);
				}
			}
			if (msg == null) {
				name = StrUtil.makeValidFileName(ctrl_name.getText());
				ctrl_name.setText(name); //183084
				if (!name.equals(methodElement.getName())) {						
					if (!changeElementName(name)) {
						return;
					}
				}
			} else {
				//Fix missing "&" in error dialog
				if (msg.indexOf("&") >= 0) {//$NON-NLS-1$
					msg = msg.replace("&", "&&");//$NON-NLS-1$//$NON-NLS-2$
				}
				AuthoringUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayError(
								AuthoringUIResources.renameError_title, 
								msg);
				ctrl_name.setText(methodElement.getName());
				ctrl_name.getDisplay().asyncExec(new Runnable() {
					public void run() {
						ctrl_name.setFocus();
						ctrl_name.selectAll();
					}
				});
			}
		}
	};

	protected boolean changeElementName(String name) {
		boolean success = actionMgr.doAction(
				IActionManager.SET, methodElement,
				UmaPackage.eINSTANCE.getNamedElement_Name(),
				name, -1);
		if (!success) {
			return false;
		}
		if(methodElement instanceof MethodConfiguration) {
			Resource resource = methodElement.eResource();
			if(resource != null) {
				((MethodElementEditor) getEditor()).addResourceToAdjustLocation(resource);
			}
		}
		if (ContentDescriptionFactory
				.hasPresentation(methodElement)) {
			Resource contentResource = contentElement
					.getPresentation().eResource();
			if (contentResource != null) {
				((MethodElementEditor) getEditor())
						.addResourceToAdjustLocation(contentResource);
			}
		}
		setFormTextWithVariableInfo();
		ctrl_name.setText(name);
		
		return true;
	}
	
	// Editing and display flags.
	protected boolean descExpandFlag = false;

	protected boolean generalSectionExpandFlag = false;

	protected boolean detailSectionExpandFlag = false;

	protected boolean notationSectionExpandFlag = false;

	protected boolean tailoringSectionExpandFlag = false;

	protected boolean versionSectionExpandFlag = false;

	protected boolean elementSectionExpandFlag = false;

	protected boolean iconSectionExpandFlag = false;

	protected boolean fullDescOn = true;

	protected boolean keyConsiderationOn = true;

	protected boolean briefDescOn = true;

	protected boolean elementTypeOn = false;

	protected boolean contentFieldOn = false;

	protected boolean versionSectionOn = true;

	protected boolean anyAttributeModified = false;

	protected boolean generalSectionOn = true;

	protected boolean notationSectionOn = false;

	protected boolean detailSectionOn = true;

	protected boolean tailoringSectionOn = false;

	protected boolean variabilitySectionOn = true;
	
	protected boolean slotSectionOn= false;

	protected boolean purposeOn = false;

	protected boolean externalIdOn = false;

	protected boolean iconSectionOn = false;
	
	protected boolean publishCategoryOn = false;
	
	protected boolean publishPracticeOn = false;
	
	protected boolean publishPracticeOnForUDT = false;
	
//	protected boolean publishDeliveableOn = false;
	
	protected boolean longPresentationNameOn = false;

	// Internal IDs for the sections.
	protected static final int GENERAL_SECTION_ID = 1;

	protected static final int DETAIL_SECTION_ID = 2;

	protected static final int VERSION_SECTION_ID = 3;

	protected static final int NOTATION_SECTION_ID = 4;

	protected static final int TAILORING_SECTION_ID = 5;

	private int warningCount = 1;

	private int SECTIONS = 0;

	private static int SHAPEICON_WIDTH_MAX = 32;

	private static int SHAPEICON_HEIGHT_MAX = 32;

	private static int NODEICON_WIDTH_MAX = 16;

	private static int NODEICON_HEIGHT_MAX = 16;

	protected IFilter filter;

	private boolean disposed;

	// To handle the i118n properly and avoid string concatenation.
	public String generalSectionDescription;
	public String detailSectionDescription;
	public String tailoringSectionDescription;
	public String notationSectionDescription;
	public String variabilitySectionDescription;
	public String iconSectionDescription;
	public String versionSectionDescription;

	private Label nodeIconPath;

	private Label shapeIconPath;
	
	private String[] imageTypes = new String[] { "*.gif;*.jpg;*.png;*.ico;*.bmp" }; //$NON-NLS-1$
	private String[] imageNames = new String[] { "Image (gif, jpeg, png, ico, bmp)" }; //$NON-NLS-1$

	/**
	 * Returns the Content filter associated with this page.
	 */
	protected IFilter getFilter() {
		return filter;
	}

	/**
	 * Returns the Content element associated with this page.
	 */
	protected Object getContentElement() {
		return null;
	}

	protected void initContentProviderBase() {
		contentProviderBase = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				ArrayList base_selected_list = new ArrayList();
				if (contentElement.getVariabilityBasedOnElement() != null)
					base_selected_list.add(contentElement
							.getVariabilityBasedOnElement());
				return base_selected_list.toArray();
			}

			public void notifyChanged(Notification notification) {
				switch (notification.getFeatureID(VariabilityElement.class)) {
				case UmaPackage.VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
					super.notifyChanged(notification);
				}
			}
		};
		base_viewer.setContentProvider(contentProviderBase);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param editor
	 *            The parent form editor.
	 * @param id
	 *            The unique ID for the form page.
	 * @param title
	 *            The title for the form page.
	 */
	public DescriptionFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
		extender = AuthoringUIExtensionManager.getInstance().createDescriptionFormSectionExtender(this);
	}
	
	private class VariabilityTypeContentProvider extends AdapterFactoryContentProvider {

		private VariabilityType[] types;

		public VariabilityTypeContentProvider(AdapterFactory adapterFactory, VariabilityType[] types) {
			super(adapterFactory);
			this.types = types;
		}
		
		@Override
		public Object[] getElements(Object object) {
			return types;
		}
		
		@Override
		public void notifyChanged(Notification notification) {
			if (notification.getNotifier() == methodElement
					&& notification.getEventType() == Notification.SET
					&& notification.getFeature() == UmaPackage.Literals.VARIABILITY_ELEMENT__VARIABILITY_TYPE) {
				viewer.setSelection(new StructuredSelection(notification.getNewValue()));
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.BaseFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		init_();
	}

	protected void init_() {
		if (AuthoringUIPreferences.getEnableAutoNameGen()) {
			if (AuthoringUIPreferences.getEnableAutoNameGen()) {
				if (LibraryUtil.hasNameTrackPresentationNameMark(methodElement)) {
					setAutoGenName(true);
				}
			}
		}
				
		VariabilityType[] types;
//		if (methodElement instanceof ContentCategory) {
//			types = new VariabilityType[] {
//					VariabilityType.NA,
//					VariabilityType.CONTRIBUTES,
//					VariabilityType.REPLACES,
//					VariabilityType.EXTENDS_REPLACES
//			};
//		} else {
//			types = new VariabilityType[] {
//					VariabilityType.NA,
//					VariabilityType.CONTRIBUTES,
//					VariabilityType.EXTENDS,
//					VariabilityType.REPLACES,
//					VariabilityType.EXTENDS_REPLACES
//			};
//		}
		types = new VariabilityType[] {
				VariabilityType.NA,
				VariabilityType.CONTRIBUTES,
				VariabilityType.EXTENDS,
				VariabilityType.REPLACES,
				VariabilityType.EXTENDS_REPLACES
		};
		contentProviderVariability = new VariabilityTypeContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory(), types);
	}

	/**
	 * @see org.eclipse.ui.forms.editor.createFormContent(IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);
		createEditorContent(toolkit);
		setContextHelp();
		setFormTextWithVariableInfo();
		loadData();
		addListeners();
	}
	
	protected void setFormTextWithVariableInfo() {
		UIHelper.setFormText(form, methodElement);
	}

	/**
	 * @deprecated
	 * @see TngUtil#getLabel Code moved to TngUtil.getLabel(below more specific
	 *      method)returns label with variabilityInfo
	 *      <code>TngUtil.getLabel(VariabilityElement object, String alternativeLabel, boolean preferBase)</code>
	 * 
	 */
	public String getLabel(VariabilityElement object, String alternativeLabel,
			boolean preferBase) {
		String label = TngUtil.getLabel(object, alternativeLabel);
		if (preferBase && object.getVariabilityBasedOnElement() != null) {
			VariabilityType type = object.getVariabilityType();
			String variabilityTxt = null;
			if (type == VariabilityType.CONTRIBUTES) {
				variabilityTxt = AuthoringUIResources
						.contributes_to_text; 
			} else if (type == VariabilityType.LOCAL_CONTRIBUTION) {
				variabilityTxt = AuthoringUIResources
					.localContributes_text; 			
			} else if (type == VariabilityType.EXTENDS) {					
				variabilityTxt = AuthoringUIResources
						.extends_text;
			} else if (type == VariabilityType.REPLACES) {
				variabilityTxt = AuthoringUIResources
						.replaces_text; 
			} else if (type == VariabilityType.LOCAL_REPLACEMENT) {
				variabilityTxt = AuthoringUIResources
						.localReplaces_text; 
			} else if (type == VariabilityType.EXTENDS_REPLACES) {
				variabilityTxt = AuthoringUIResources
				.extendsReplaces_text; 
			}
			if (variabilityTxt != null) {
				StringBuffer strBuf = new StringBuffer(label)
						.append(" (").append(variabilityTxt).append(" '") //$NON-NLS-1$ //$NON-NLS-2$
						.append(
								TngUtil.getLabel(object
										.getVariabilityBasedOnElement(),
										alternativeLabel)).append("'"); //$NON-NLS-1$
				MethodPlugin basePlugin = UmaUtil.getMethodPlugin(object
						.getVariabilityBasedOnElement());
				if (basePlugin != null) {
					strBuf
							.append(" in '").append(basePlugin.getName()).append("')"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				label = strBuf.toString();
			}
		}

		return label;
	}

	/**
	 * Creates the editor tab content
	 * 
	 * @param toolkit
	 *            The form toolkit.
	 */
	protected void createEditorContent(FormToolkit toolkit) {
		createFormComposites(toolkit);
		loadSectionDescription();
		
		// check for extension section if any
		loadSectionProviders();
		
		// Create the General section.
		if (generalSectionOn) {
			createGeneralSection(toolkit);
			toolkit.paintBordersFor(generalComposite);
			SECTIONS++;
		}

		for (int i = 0; i < sectionProviders.size(); i++) {
			try {
				Object provider  = sectionProviders.get(i);
				if (provider instanceof ISectionProvider) {
					((ISectionProvider) provider).createSection(
							(MethodElementEditor) getEditor(), toolkit,
							sectionComposite);
					SECTIONS++;
				}
			} catch (Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
		}
		
		// Create the Slot Section
		if (slotSectionOn) {
			createSlotSection(toolkit);
			SECTIONS++;
		}
		
		// Create the Detail section.
		if (detailSectionOn) {
			createDetailSection(toolkit);
			SECTIONS++;
		}

		// Create the Notation section.
		if (notationSectionOn) {
			createNotationSection(toolkit);
			SECTIONS++;
		}

		// Create the Tailoring Section.
		if (tailoringSectionOn) {
			createTailoringSection(toolkit);
			SECTIONS++;
		}

		// Create the Version section.
		if (versionSectionOn) {
			createVersionSection(toolkit);
			createVersionSectionContent();
			toolkit.paintBordersFor(versionComposite);
			SECTIONS++;
		}		
		
		if (variabilitySectionOn) {
			createVariabilitySection(toolkit);
			toolkit.paintBordersFor(variabilityComposite);
			SECTIONS++;
		}
		
		// Icon Section
		if (iconSectionOn) {
			createIconSection(toolkit);
			createIconSectionContent();
			SECTIONS++;
		}

		if (expandedComposite != null)
			toolkit.paintBordersFor(expandedComposite);
	}

	
	/** 
	 * Get all section providers
	 * @return
	 */
	protected List<ISectionProvider> loadSectionProviders() {
		if (sectionProviders == null) {
			sectionProviders = ExtensionManager.getExtensions(AuthoringUIPlugin.getDefault().getId(), "descriptionPageSectionProvider", ISectionProvider.class); //$NON-NLS-1$	
		}
		return sectionProviders;
	}
	
	private void createFormComposites(FormToolkit toolkit) {
		// Create the main section (used for swapping display of
		// sectionComposite and expandedComposite).
		formSection = toolkit.createSection(form.getBody(), Section.NO_TITLE);
		{
			TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
			formSection.setLayoutData(td);
			formSection.setLayout(new TableWrapLayout());
		}

		// Create the composite for the sections.
		mainComposite = toolkit.createComposite(formSection, SWT.NONE);
		{
			TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
			mainComposite.setLayoutData(td);
			FormLayout layout = new FormLayout();
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			mainComposite.setLayout( layout);
			formSection.setClient(mainComposite);
		}
		
	
		// check for extenstion point and add the page if there
		List<?> columnProviders = DescriptionPageColumnProvider.getInstance()
				.getColumnProviders();

		if (columnProviders == null || columnProviders.size() == 0) {
			createSectionComposite(mainComposite, 0, 100);
		} else {
			try {
				for (int i = 0; i < columnProviders.size(); i++) {
					ColumnElement column = (ColumnElement) columnProviders.get(i);
					Object providerClass = column.getContributorClass();
					int width = column.getWidth();
					String alignment = column.getAlignment();
					
					if (providerClass instanceof IColumnProvider) {
						columnProvider = (IColumnProvider) providerClass;
						
						if (alignment.equals("left")) {	//$NON-NLS-1$	
							createSectionComposite(mainComposite, width,
									100);

							Composite columnComposite = columnProvider
									.setColumn((MethodElementEditor) getEditor(), toolkit, mainComposite);
							{							
								FormData data = new FormData();
								data.top = new FormAttachment(0, 0);
								data.left = new FormAttachment(0, 5);
								data.bottom = new FormAttachment(100, -5);
								data.right = new FormAttachment(
										sectionComposite, 0);
								columnComposite.setLayoutData(data);
								GridLayout layout = new GridLayout();
								layout.marginHeight = 0;
								columnComposite.setLayout(layout);
							}						
						}
						if (alignment.equals("right")) { //$NON-NLS-1$	
							createSectionComposite(mainComposite, 0,
									100 - width);

							Composite columnComposite = columnProvider
									.setColumn((MethodElementEditor) getEditor(), toolkit, mainComposite);
							{	
								FormData data = new FormData();
								data.top = new FormAttachment(0, 0);
								data.left = new FormAttachment(
										sectionComposite, 5);
								data.bottom = new FormAttachment(100, -5);
								data.right = new FormAttachment(100, 0);
								columnComposite.setLayoutData(data);
								GridLayout layout = new GridLayout();
								layout.marginHeight = 0;
								columnComposite.setLayout(layout);
							}
						}
					}
				}
			} catch (Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
			}
		}

		// Create the composite for the RichTextEditor.
		expandedComposite = toolkit.createComposite(formSection, SWT.NONE);
		{
			TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
			expandedComposite.setLayoutData(td);
			expandedComposite.setLayout(new GridLayout(2, false));
			expandedComposite.setVisible(false);
		}

		// Add the expand/collapse hyperlink image.
		expandLink = toolkit.createImageHyperlink(expandedComposite, SWT.NONE);
		expandLink.setImage(AuthoringUIImages.IMG_EXPANDED);
		expandLink.setToolTipText(AuthoringUIResources.closeRTE);
		expandLink.setUnderlined(false);
		expandLink.addHyperlinkListener(new HyperlinkAdapter() {
			public void linkActivated(HyperlinkEvent e) {
				toggle(e);
			}
		});

		// Add the expand/collapse hyperlink text.
		expandLabel = createDecoratedLabel(toolkit, expandedComposite, ""); //$NON-NLS-1$
	}

	/**
	 * Creates EPF Main section composite where all general, detail section resides
	 */
	private void createSectionComposite(Composite parent, int leftMargin,
			int rightMargin) {
		// Create the composite for the sections.
		sectionComposite = toolkit.createComposite(parent, SWT.NONE);
		{
			FormData data = new FormData();
			data.top = new FormAttachment(0, 0);
			data.left = new FormAttachment(leftMargin, 0);
			data.bottom = new FormAttachment(100, -5);
			data.right = new FormAttachment(rightMargin, -5);
			sectionComposite.setLayoutData(data);
			GridLayout layout = new GridLayout();
			layout.marginHeight = 0;
			sectionComposite.setLayout(layout);
		}
	}
	
	/**
	 * Creates the General section.
	 */
	protected void createGeneralSection(FormToolkit toolkit) {
		generalSection = createSection(toolkit, sectionComposite,
				AuthoringUIText.GENERAL_INFO_SECTION_NAME,
				getGeneralSectionDescription());
		generalComposite = createComposite(toolkit, generalSection);
		((GridLayout) generalComposite.getLayout()).numColumns = 4;
		createGeneralSectionContent();
		extender.modifyGeneralSectionContent(toolkit, ((MethodElementEditor) getEditor()).getActionManager());
	}

	/**
	 * Creates the General section content.
	 */
	protected void createGeneralSectionContent() {
		// Add the Name label and text control.
		ctrl_name = createTextEditWithLabel3(toolkit, generalComposite,
				AuthoringUIText.NAME_TEXT, SWT.DEFAULT, SWT.SINGLE);

		// Add the Presentation name label and text control.
		ctrl_presentation_name = createTextEditWithLabel3(toolkit,
				generalComposite, AuthoringUIText.PRESENTATION_NAME_TEXT,
				SWT.DEFAULT, SWT.SINGLE);
		
		// Add long  presentation name lable and text control
		
		if (longPresentationNameOn && AuthoringUIPreferences.getEnableUIFields()) {
			ctrl_long_presentation_name = createTextEditWithLabel3(toolkit,
					generalComposite, AuthoringUIText.LONG_PRESENTATION_NAME_TEXT,
					SWT.DEFAULT, SWT.SINGLE);
		}

		if (elementTypeOn) {
			createLabel(toolkit, generalComposite, AuthoringUIText.TYPE_TEXT, 2);
			ctrl_type_label = createLabel(toolkit, generalComposite, ""); //$NON-NLS-1$
			{
				GridData gridData = new GridData(GridData.BEGINNING);
				gridData.horizontalSpan = 1;
				ctrl_type_label.setBackground(Display.getCurrent()
						.getSystemColor(19));
				ctrl_type_label.setLayoutData(gridData);
			}
			if (methodElement instanceof Guidance) {
				ctrl_type_button = toolkit.createButton(generalComposite,
						AuthoringUIText.CHANGE_TYPE_BUTTON_TEXT, SWT.PUSH);
				{
					GridData gridData = new GridData(GridData.BEGINNING);
					gridData.horizontalSpan = 1;
					ctrl_type_button.setLayoutData(gridData);
				}
				if (ConvertGuidanceType
						.getValidNewGuidanceTypes((Guidance) methodElement) == null) {
					ctrl_type_button.setVisible(false);
				}
			}
		}

		if (externalIdOn && AuthoringUIPreferences.getEnableUIFields()) {
			ctrl_external_id = createTextEditWithLabel3(toolkit,
					generalComposite, AuthoringUIText.EXTERNAL_ID_TEXT,
					SWT.DEFAULT, SWT.SINGLE);
		}

		if (briefDescOn) {
			ctrl_brief_desc = createTextEditWithLabel3(toolkit,
					generalComposite, AuthoringUIText.BRIEF_DESCRIPTION_TEXT,
					40, SWT.MULTI | SWT.TRAVERSE_TAB_NEXT);
		}		

		String publishButtonText = AuthoringUIText.PUBLISH_CATEGORIES_TEXT;
		if (! publishCategoryOn) {
			publishCategoryOn = publishPracticeOn;
			if (publishPracticeOn) {
				publishButtonText = AuthoringUIText.PUBLISH_PRACTICES_TEXT;
				if (publishPracticeOnForUDT) {
					// TODO_translation: to avoid new translation, use the new string in English, otherwise use the old one
					// if (publishButtonText.startsWith("Publish back links to this practice from its contained elements"))	//$NON-NLS-1$
						publishButtonText = AuthoringUIText.PUBLISH_PRACTICES_FOR_UDT_TEXT;
				}
			}
		}
		
		if (publishCategoryOn) {
			ctrl_publish_categories_button = toolkit
			.createButton(
					generalComposite,
					publishButtonText, SWT.CHECK); 
			GridData data = new GridData();
			data.horizontalSpan = 3;
			ctrl_publish_categories_button.setLayoutData(data);
		}
	}

	/**
	 * Creates the Content Variability section.
	 */
	protected void createVariabilitySection(FormToolkit toolkit) {
		variabilitySection = createSection(toolkit, sectionComposite,
				AuthoringUIText.VARIABILITY_SECTION_NAME,
				getVariabilitySectionDescription());
		
		variabilityComposite = toolkit.createComposite(variabilitySection);
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.BEGINNING | GridData.GRAB_HORIZONTAL);
			gridData.horizontalSpan = 1;
			gridData.heightHint = 24;
			gridData.widthHint = 300;
			variabilityComposite.setLayoutData(gridData);
		}
		variabilityComposite.setLayout(new GridLayout(5, false));
		variabilitySection.setClient(variabilityComposite);

		ctrl_variability = createComboWithLabel3(toolkit, variabilityComposite,
				AuthoringUIText.VARIABILITY_TYPE_TEXT);

		viewer_variability = new ComboViewer(ctrl_variability);
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.BEGINNING | GridData.GRAB_HORIZONTAL);
			gridData.horizontalSpan = 2;
			gridData.heightHint = 24;
			gridData.widthHint = 300;
			viewer_variability.getCombo().setLayoutData(gridData);
		}
		viewer_variability.setContentProvider(contentProviderVariability);
		viewer_variability.setLabelProvider(labelProviderVariability);
		viewer_variability.setInput(methodElement);

		toolkit.createLabel(variabilityComposite, "", SWT.NONE); //$NON-NLS-1$

		//String baseElement = LibraryUIText.getUITextLower(methodElement);
		label_base = createLabel(toolkit, variabilityComposite,
				AuthoringUIText.BASE_ELEMENT_TEXT, 2);
		ctrl_base = createTable(toolkit, variabilityComposite, SWT.SINGLE
				| SWT.READ_ONLY, GridData.FILL_HORIZONTAL | GridData.BEGINNING, 5, 300, 1, 2);
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.BEGINNING);
			gridData.horizontalSpan = 2;
			gridData.heightHint = 24;
			gridData.widthHint = 300;
			ctrl_base.setLayoutData(gridData);
		}

		base_viewer = new TableViewer(ctrl_base);
		initContentProviderBase();
		base_viewer.setLabelProvider(labelProviderBase);
		base_viewer.setInput(methodElement);

		Composite baseButtonPane = createComposite(toolkit, variabilityComposite, 
				GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_BEGINNING, 1, 1, 1);
		{
			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			baseButtonPane.setLayoutData(gridData);
		}
		ctrl_base_button = toolkit.createButton(baseButtonPane,
				AuthoringUIText.SELECT_BUTTON_TEXT, SWT.SIMPLE);
		{
			GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.widthHint = BUTTON_WIDTH;
			ctrl_base_button.setLayoutData(gridData);
		}

		// Set focus on the Name text control.
		Display display = form.getBody().getDisplay();
		if (!(display == null || display.isDisposed())) {
			display.asyncExec(new Runnable() {
				public void run() {
					if(ctrl_name.isDisposed()) return;
					
					if (isAutoGenName()) {
						ctrl_presentation_name.setFocus();
						if (methodUnit != null && methodUnit.getChangeDate() == null)
							ctrl_presentation_name.setSelection(0, ctrl_presentation_name.getText().length());
					} else {
						ctrl_name.setFocus();
						if (methodUnit != null && methodUnit.getChangeDate() == null)
							ctrl_name.setSelection(0, ctrl_name.getText().length());
					}
					
				}
			});
		}
	}

	protected void refresh(boolean editable) {
		if (generalSectionOn) { 
			ctrl_name.setEditable(editable);
			ctrl_presentation_name.setEditable(editable);
		}
		
		if (longPresentationNameOn && AuthoringUIPreferences.getEnableUIFields()) {
			ctrl_long_presentation_name.setEditable(editable);
		}

		if (briefDescOn) {
			ctrl_brief_desc.setEditable(editable);
		}
		if (purposeOn) {
			ctrl_purpose.setEditable(editable);
		}
		if (fullDescOn) {
			ctrl_full_desc.setEditable(editable);
		}
		if (keyConsiderationOn) {
			ctrl_key.setEditable(editable);
		}
		if (externalIdOn && AuthoringUIPreferences.getEnableUIFields()) {
			ctrl_external_id.setEditable(editable);
		}
		if (elementTypeOn) {
			ctrl_type_button.setEnabled(editable);
		}
		if (variabilitySectionOn) {
			ctrl_variability.setEnabled(editable);
			ctrl_base_button.setEnabled(editable);

			MethodPlugin plugin = UmaUtil.getMethodPlugin(methodElement);
			if (MethodPluginPropUtil.getMethodPluginPropUtil().isCustomizePlugin(plugin)) {
				ctrl_variability.setEnabled(false);
				ctrl_base_button.setEnabled(false);
			}
			
			if (((IStructuredSelection) viewer_variability.getSelection())
					.getFirstElement() == VariabilityType.NA) {
				ctrl_base_button.setEnabled(false);
			}
			
			if (((IStructuredSelection) viewer_variability.getSelection())
					.getFirstElement() == VariabilityType.CONTRIBUTES) {
				ctrl_presentation_name.setEditable(false);
				if (externalIdOn && AuthoringUIPreferences.getEnableUIFields()) {
					ctrl_external_id.setEditable(false);
				}
			}
		}
		if (versionSectionOn) {
			ctrl_change_date.setEditable(false);
			ctrl_change_desc.setEditable(editable);
			ctrl_authors.setEditable(editable);
			ctrl_version.setEditable(editable);
			copyright_button.setEnabled(editable);
			setCopyrightButtonDeselect();
		}
		if (iconSectionOn) {
			ctrl_select_shapeIcon_button.setEnabled(editable);
			ctrl_clear_shapeIcon_button.setEnabled(editable
					&& contentElement.getShapeicon() != null);
			ctrl_select_nodeIcon_button.setEnabled(editable);
			ctrl_clear_nodeIcon_button.setEnabled(editable
					&& contentElement.getNodeicon() != null);
		}
		if (ctrl_expanded != null) {
			ctrl_expanded.setEditable(editable);
		}
		
		if (publishCategoryOn) {
			ctrl_publish_categories_button.setEnabled(editable);
		}
		if (editable) {
			refreshForContributor();
		}
		if (columnProvider != null)
			columnProvider.refresh(editable);
		
		for (int i = 0; i < sectionProviders.size(); i++) {
			Object provider = sectionProviders.get(i);
			if (provider != null && provider instanceof ISectionProvider) {
				((ISectionProvider) provider).refresh(editable);
			}
		}
	
		if (extender != null) {
			extender.refresh(editable);
		}
	}

	/**
	 * Create the Detail section.
	 */
	protected void createDetailSection(FormToolkit toolkit) {
		detailSection = createSection(toolkit, sectionComposite,
				AuthoringUIText.DETAIL_SECTION_NAME,
				getDetailSectionDescription());
		detailComposite = createComposite(toolkit, detailSection);
		createDetailSectionContent();
		toolkit.paintBordersFor(detailComposite);
	}

	/**
	 * Creates the Detail section content.
	 */
	protected void createDetailSectionContent() {
		if (purposeOn) {
			ctrl_purpose = createRichTextEditWithLinkForSection(toolkit,
					detailComposite, AuthoringUIText.PURPOSE_TEXT, 40, 400,
					DETAIL_SECTION_ID);
		}

		if (fullDescOn) {
			ctrl_full_desc = createRichTextEditWithLinkForSection(toolkit,
					detailComposite, AuthoringUIText.MAIN_DESCRIPTION_TEXT,
					100, 400, DETAIL_SECTION_ID);

		}

		if (keyConsiderationOn) {
			ctrl_key = createRichTextEditWithLinkForSection(toolkit,
					detailComposite, AuthoringUIText.KEY_CONSIDERATIONS_TEXT,
					40, 400, DETAIL_SECTION_ID);
		}
	}

	/**
	 * Creates the Notation section.
	 */
	protected void createNotationSection(FormToolkit toolkit) {
		notationSection = createSection(toolkit, sectionComposite,
				AuthoringUIText.NOTATION_SECTION_NAME,
				getNotationSectionDescription());
		notationComposite = createComposite(toolkit, notationSection);
		createNotationSectionContent();
		toolkit.paintBordersFor(notationComposite);
	}

	/**
	 * Creates the Notation section content.
	 */
	protected void createNotationSectionContent() {
	}

	/**
	 * Creates the Tailoring section.
	 */
	protected void createTailoringSection(FormToolkit toolkit) {
		tailoringSection = createSection(toolkit, sectionComposite,
				AuthoringUIText.TAILORING_SECTION_NAME,
				getTailoringSectionDescription());
		tailoringComposite = createComposite(toolkit, tailoringSection);
		createTailoringSectionContent();
		toolkit.paintBordersFor(tailoringComposite);

	}

	protected void createTailoringSectionContent() {
	}
	
	/**
	 * Creates the slot section.
	 */
	protected void createSlotSection(FormToolkit toolkit) {

	}

	/**
	 * Add listeners to monitor focus and modification changes in the edit
	 * controls.
	 */
	protected void addListeners() {
		final MethodElementEditor editor = (MethodElementEditor) getEditor();

		modelModifyListener = editor.createModifyListener(methodElement);
		nameModifyListener =  editor.createModifyListener(methodElement);
		nameModifyListener.setForNameOnly(true);
		
		contentModifyListener = editor.createModifyListener(methodUnit);
		actionMgr = editor.getActionManager();
		
		

		form.addControlListener(new ControlListener() {
			public void controlResized(ControlEvent e) {
				if (ctrl_expanded != null) {
					((GridData) ctrl_expanded.getLayoutData()).heightHint = getRichTextEditorHeight();
					((GridData) ctrl_expanded.getLayoutData()).widthHint = getRichTextEditorWidth();
				}
				formSection.layout(true, true);
			}

			public void controlMoved(ControlEvent e) {
			}
		});

		form.getParent().addListener(SWT.Activate, new Listener() {
			public void handleEvent(Event event) {
				if(disposed) {
					return;
				}
				
				refresh(!TngUtil.isLocked(methodElement));
				setFormTextWithVariableInfo();
				
				// do refresh variability and copyright viewer
				if(variabilitySectionOn)
					base_viewer.refresh();
				if(versionSectionOn)
					copyright_viewer.refresh();
			}
		});

		if (generalSectionOn) {
			addGeneralSectionListeners();
		}

		if (detailSectionOn) {
			addDetailSectionListeners();
		}

		if (versionSectionOn) {
			addVersionSectionListeners();
		}

		if (iconSectionOn) {
			addIconSectionListeners();
		}

		if (variabilitySectionOn) {
			viewer_variability
					.addSelectionChangedListener(new ISelectionChangedListener() {
						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) viewer_variability
									.getSelection();
	
							if (contentElement.getVariabilityBasedOnElement() != null) {
								boolean status = editor
										.getActionManager()
										.doAction(
												IActionManager.SET,
												methodElement,
												UmaPackage.eINSTANCE
														.getVariabilityElement_VariabilityType(),
												(VariabilityType) selection
														.getFirstElement(), -1);
								
								if (!status) {
									return;
								}
								else {
									if (selection.getFirstElement() == VariabilityType.CONTRIBUTES)
										updatePNameForContributor(true);	
									else
										updatePNameForContributor(false);
								}
							}
							if (selection.getFirstElement() == VariabilityType.NA) {
								if (contentElement.getVariabilityBasedOnElement() != null) {
									boolean status = editor
											.getActionManager()
											.doAction(
													IActionManager.SET,
													methodElement,
													UmaPackage.eINSTANCE
															.getVariabilityElement_VariabilityBasedOnElement(),
													null, -1);
									if (!status) {
										return;
									}
								}
								ctrl_base_button.setEnabled(false);
								base_viewer.refresh();
							} else {
								List selectionBaseList = new ArrayList();
								VariabilityElement base = contentElement
										.getVariabilityBasedOnElement();
								selectionBaseList.add(base);
								ctrl_base_button.setEnabled(true);
								base_viewer.refresh();
							}
							setFormTextWithVariableInfo();
							
							for (int i = 0; i < sectionProviders.size(); i++) {
								Object provider = sectionProviders.get(i);
								if (provider != null && provider instanceof ISectionProvider) {
									((ISectionProvider) provider).refresh(false);
								}
							}
						}
					});
	
			ctrl_base_button.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					filter = getFilter();
					if (filter != null) {
						ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), filter, getContentElement(),
								LibraryUIText.getUIText(methodElement, true));
						fd.setViewerSelectionSingle(true);
						fd.setBlockOnOpen(true);
						fd.setTitle(LibraryUIText.getUIText(methodElement, true));
						fd.open();
						fd.getSelectedItems();
						if (fd.getSelectedItems().size() > 0) {
							IStatus istatus = DependencyChecker.checkCircularDependencyAfterFilterSelection(
									contentElement, (VariabilityElement) fd.getSelectedItems().get(0));
							if(!istatus.isOK()) {
								String title = AuthoringUIResources.variability_error_title;
								AuthoringUIPlugin.getDefault().getMsgDialog().displayError(title, istatus.getMessage());						
								return;
							}
							boolean status = editor
									.getActionManager()
									.doAction(
											IActionManager.SET,
											methodElement,
											UmaPackage.eINSTANCE
													.getVariabilityElement_VariabilityBasedOnElement(),
											(VariabilityElement) fd
													.getSelectedItems().get(0), -1);
							if (!status) {
								return;
							}
							status = editor
									.getActionManager()
									.doAction(
											IActionManager.SET,
											methodElement,
											UmaPackage.eINSTANCE
													.getVariabilityElement_VariabilityType(),
											(VariabilityType) ((IStructuredSelection) viewer_variability
													.getSelection())
													.getFirstElement(), -1);
							if (status) {
								if ((VariabilityType) ((IStructuredSelection) viewer_variability
										.getSelection()).getFirstElement() == VariabilityType.CONTRIBUTES)
									updatePNameForContributor(true);	
								else
									updatePNameForContributor(false);
							}
						}
					}
					setFormTextWithVariableInfo();
					
					for (int i = 0; i < sectionProviders.size(); i++) {
						Object provider = sectionProviders.get(i);
						if (provider != null && provider instanceof ISectionProvider) {
							((ISectionProvider) provider).refresh(false);
						}
					}
					if ((ctrl_base != null) && (!(ctrl_base.isDisposed())))
						ctrl_base.redraw();
					if ((base_viewer != null)
							&& (!(base_viewer.getControl().isDisposed())))
						base_viewer.refresh();
				}
			});
		}
	}

	private void updatePNameForContributor(boolean update) {
		// final MethodElementEditor editor = (MethodElementEditor) getEditor();

		final MethodElementEditor editor = (MethodElementEditor) getEditor();

		actionMgr = editor.getActionManager();
		if (contentElement.getVariabilityType() == VariabilityType.CONTRIBUTES) {
			// make presentation name empty
			actionMgr.doAction(IActionManager.SET, methodElement,
					UmaPackage.eINSTANCE.getMethodElement_PresentationName(),
					"", -1); //$NON-NLS-1$
			ctrl_presentation_name.setText(""); //$NON-NLS-1$

			// make external Id empty
			actionMgr.doAction(IActionManager.SET, contentElement
					.getPresentation(), UmaPackage.eINSTANCE
					.getContentDescription_ExternalId(), "", -1); //$NON-NLS-1$
			if (externalIdOn && AuthoringUIPreferences.getEnableUIFields()) {
				ctrl_external_id.setText(""); //$NON-NLS-1$
			}
		}
	
		refreshForContributor();
	}
	
	
	private void refreshForContributor() {
		boolean editable = true;
		if (generalSectionOn) {
			ctrl_presentation_name.setEditable(editable);
		}
		
		if (externalIdOn && AuthoringUIPreferences.getEnableUIFields()) {
			ctrl_external_id.setEditable(editable);
		}
		
		if (contentElement != null
				&& (contentElement.getVariabilityType() == VariabilityType.CONTRIBUTES)) {	
			if (contentElement.getPresentationName().equals("")) { //$NON-NLS-1$
				ctrl_presentation_name.setEditable(false);
			}
			if (externalIdOn && AuthoringUIPreferences.getEnableUIFields()) {
				if (contentElement.getPresentation().getExternalId().equals("")) { //$NON-NLS-1$
					ctrl_external_id.setEditable(false);
				}
			}
		}
	}
	
	protected ModifyListener newNameTackingPNameListener() {
		ModifyListener ret = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (isAutoGenName()) {
					String name = generateName(ctrl_presentation_name.getText());
					if (name.length() > 0) {
						ctrl_name.setText(name);
					}
				}
			}
		};
		
		return ret;
	}
	
	protected void addGeneralSectionListeners() {
		ctrl_name.addModifyListener(nameModifyListener);
		ctrl_name.addFocusListener(nameFocusListener);

		ctrl_presentation_name.addModifyListener(modelModifyListener);
		ctrl_presentation_name.addModifyListener(newNameTackingPNameListener());
		
		ctrl_presentation_name.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String oldContent = methodElement.getPresentationName();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						e.widget, oldContent)) {
					return;
				}
				
				String newContent = StrUtil.getPlainText(ctrl_presentation_name
						.getText());				
				if (!newContent.equals(oldContent)) {
					boolean success = actionMgr.doAction(IActionManager.SET,
							methodElement, UmaPackage.eINSTANCE
									.getMethodElement_PresentationName(),
							newContent, -1);
					if (success) {
						ctrl_presentation_name.setText(newContent);
					}
				}
				// clear the selection when the focus of the component is lost 
				if(ctrl_presentation_name.getSelectionCount() > 0){
					ctrl_presentation_name.clearSelection();
				} 
				if (isAutoGenName()) {
					changeElementName();
				}
			}
			
			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
			 */
			public void focusGained(FocusEvent e) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget, UmaPackage.eINSTANCE.getMethodElement_PresentationName());
				// when user tab to this field, select all text
				ctrl_presentation_name.selectAll();
			}
		});
		
		if (longPresentationNameOn && AuthoringUIPreferences.getEnableUIFields()) {
			ctrl_long_presentation_name.addModifyListener(modelModifyListener);
			ctrl_long_presentation_name.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent e) {
					String oldContent = contentElement.getPresentation().getLongPresentationName();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							ctrl_long_presentation_name, oldContent)) {
						return;
					}
					
					String newContent = StrUtil.getPlainText(ctrl_long_presentation_name
							.getText());				
					if (!newContent.equals(oldContent)) {
						boolean success = actionMgr.doAction(IActionManager.SET,
								contentElement.getPresentation(), UmaPackage.eINSTANCE
										.getContentDescription_LongPresentationName(),
								newContent, -1);
						if (success) {
							ctrl_long_presentation_name.setText(newContent);
						}
					}
					// clear the selection when the focus of the component is lost 
					if(ctrl_long_presentation_name.getSelectionCount() > 0){
						ctrl_long_presentation_name.clearSelection();
					}    
				}
				
				/* (non-Javadoc)
				 * @see org.eclipse.swt.events.FocusAdapter#focusGained(org.eclipse.swt.events.FocusEvent)
				 */
				public void focusGained(FocusEvent e) {
					// when user tab to this field, select all text
					ctrl_long_presentation_name.selectAll();
					((MethodElementEditor) getEditor())
					.setCurrentFeatureEditor(e.widget,
							UmaPackage.eINSTANCE
									.getContentDescription_LongPresentationName());
				}
			});
		}

		if (briefDescOn) {
			ctrl_brief_desc.addModifyListener(modelModifyListener);
			ctrl_brief_desc.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget,
							UmaPackage.eINSTANCE.getMethodElement_BriefDescription());
				}

				/* (non-Javadoc)
				 * @see org.eclipse.swt.events.FocusAdapter#focusLost(org.eclipse.swt.events.FocusEvent)
				 */
				public void focusLost(FocusEvent e) {
					String oldContent = methodElement.getBriefDescription();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							e.widget, oldContent)) {
						return;
					}
					String newContent = ctrl_brief_desc.getText();
					if (!newContent.equals(oldContent)) {
						boolean success = actionMgr.doAction(
								IActionManager.SET, methodElement,
								UmaPackage.eINSTANCE
										.getMethodElement_BriefDescription(),
								newContent, -1);
						if (success) {
							ctrl_brief_desc.setText(newContent);
						}
					}
				}
			});
		}

		if (elementTypeOn) {
			if (methodElement instanceof Guidance) {
				ctrl_type_button.addListener(SWT.Selection, new Listener() {
					public void handleEvent(Event event) {
						final MethodElementEditor editor = (MethodElementEditor) getEditor();
						if (editor.isDirty()) {
							String title = AuthoringUIResources.descriptionTabGuidanceWarningDialog_title; 
							String message = AuthoringUIResources.descriptionTabGuidanceWarningDialog_message1; 
							AuthoringUIPlugin.getDefault().getMsgDialog()
									.displayWarning(title, message);
						} else if (methodElement instanceof Guidance) {
							MethodElementDeleteAction deleteAction = new MethodElementDeleteAction();
							deleteAction.setEditingDomain(LibraryView.getView()
									.getEditingDomain());
							deleteAction
									.selectionChanged(new StructuredSelection(
											methodElement));
							Command cmd = deleteAction
									.createCommand(Collections
											.singleton(methodElement));
							if (cmd instanceof DeleteMethodElementCommand) {
								Guidance newGuidance;
								try {
									newGuidance = ConvertGuidanceType
										.convertGuidance(
												(Guidance) methodElement,
												null,
												(DeleteMethodElementCommand) cmd);
								}
								finally {
									try { cmd.dispose(); } catch(Exception e) {}
								}
								if (newGuidance != null) {
									EditorChooser.getInstance().closeEditor(
											methodElement);
									EditorChooser.getInstance().openEditor(
											newGuidance);
								}
							}
						} else if (editor.isDirty()) {
							String title = AuthoringUIResources.descriptionTabGuidanceWarningDialog_title; 
							String message = AuthoringUIResources.descriptionTabGuidanceWarningDialog_message2; 
							AuthoringUIPlugin.getDefault().getMsgDialog()
									.displayWarning(title, message);
						}
					}
				});
			}
		}
		
		if (publishCategoryOn) {
			ctrl_publish_categories_button.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					String val = new Boolean(ctrl_publish_categories_button.getSelection()).toString();
					MethodElementProperty prop = TngUtil.getPublishCategoryProperty(methodElement);

					if (prop == null) {
						prop = UmaFactory.eINSTANCE.createMethodElementProperty();
						prop.setName(TngUtil.PUBLISH_CATEGORY_PROPERTY);
						prop.setValue(val);
						actionMgr.doAction(IActionManager.ADD,
										methodElement,
										UmaPackage.eINSTANCE.getMethodElement_MethodElementProperty(),
										prop, -1);
					} else {
						actionMgr.doAction(IActionManager.SET,
										prop,
										UmaPackage.eINSTANCE.getMethodElementProperty_Value(),
										val, -1);
					}
				}
			});
		}
		
		if (externalIdOn && AuthoringUIPreferences.getEnableUIFields()) {
			ctrl_external_id.addModifyListener(contentModifyListener);
			ctrl_external_id.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					((MethodElementEditor) getEditor())
							.setCurrentFeatureEditor(e.widget,
									UmaPackage.eINSTANCE
											.getContentDescription_ExternalId());
				}

				public void focusLost(FocusEvent e) {
					String oldContent =  contentElement.getPresentation().getExternalId();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							ctrl_external_id, oldContent)) {
						return;
					}
					String newContent = ctrl_external_id.getText();
					if (!newContent.equals(oldContent)) {
						boolean success = actionMgr.doAction(
								IActionManager.SET, contentElement
										.getPresentation(),
								UmaPackage.eINSTANCE
										.getContentDescription_ExternalId(),
								newContent, -1);
						if (success && isVersionSectionOn()) {
							updateChangeDate();
						}
					}
				}
			});
		}
	}

	protected IValidator getNameValidator() {
		return null;
	}

	protected void addDetailSectionListeners() {
		if (fullDescOn) {
			ctrl_full_desc.setModalObject(contentElement.getPresentation());
			ctrl_full_desc.setModalObjectFeature(UmaPackage.eINSTANCE
					.getContentDescription_MainDescription());
			ctrl_full_desc.addModifyListener(contentModifyListener);
			ctrl_full_desc.addListener(SWT.Deactivate, new Listener() {
				public void handleEvent(Event e) {
					IMethodRichText control = descExpandFlag ? ctrl_expanded
							: ctrl_full_desc;
					if (debug) {
						System.out
								.println("DescriptionFormPage.ctrl_full_desc.deactivateListener: control=" + control); //$NON-NLS-1$
					}
					if (!control.getModified()) {
						if (debug) {
							System.out
									.println("DescriptionFormPage.ctrl_full_desc.deactivateListener: content not modified, exit"); //$NON-NLS-1$
						}
						return;
					}
					String oldContent = contentElement.getPresentation()
							.getMainDescription();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							control, oldContent)) {
						if (debug) {
							System.out
									.println("DescriptionFormPage.ctrl_full_desc.deactivateListener: restored old content, exit"); //$NON-NLS-1$
						}
						return;
					}
					String newContent = control.getText();
					if (!newContent.equals(oldContent)) {
						if (debug) {
							System.out
									.println("DescriptionFormPage.ctrl_full_desc.deactivateListener: saving modified content"); //$NON-NLS-1$
						}
						boolean success = actionMgr
								.doAction(
										IActionManager.SET,
										contentElement.getPresentation(),
										UmaPackage.eINSTANCE
												.getContentDescription_MainDescription(),
										newContent, -1);
						if (debug) {
							System.out
									.println("DescriptionFormPage.ctrl_full_desc.deactivateListener: saved status=" + success); //$NON-NLS-1$
						}
						if (success && isVersionSectionOn()) {
							updateChangeDate();
						}
					}
					if (debug) {
						System.out
								.println("DescriptionFormPage.ctrl_full_desc.deactivateListener: exit"); //$NON-NLS-1$
					}
				}
			});
		}

		if (keyConsiderationOn) {
			ctrl_key.setModalObject(contentElement.getPresentation());
			ctrl_key.setModalObjectFeature(UmaPackage.eINSTANCE
					.getContentDescription_KeyConsiderations());
			ctrl_key.addModifyListener(contentModifyListener);
			ctrl_key.addListener(SWT.Deactivate, new Listener() {
				public void handleEvent(Event e) {
					IMethodRichText control = descExpandFlag ? ctrl_expanded
							: ctrl_key;
					if (debug) {
						System.out
								.println("DescriptionFormPage.ctrl_key.deactivateListener: control=" + control); //$NON-NLS-1$
					}
					if (!control.getModified()) {
						if (debug) {
							System.out
									.println("DescriptionFormPage.ctrl_key.deactivateListener: content not modified, exit"); //$NON-NLS-1$
						}
						return;
					}
					String oldContent = contentElement.getPresentation()
							.getKeyConsiderations();
					if (((MethodElementEditor) getEditor()).mustRestoreValue(
							control, oldContent)) {
						if (debug) {
							System.out
									.println("DescriptionFormPage.ctrl_key.deactivateListener: restored old content, exit"); //$NON-NLS-1$
						}
						return;
					}
					String newContent = control.getText();
					if (!newContent.equals(oldContent)) {
						boolean success = actionMgr
								.doAction(
										IActionManager.SET,
										contentElement.getPresentation(),
										UmaPackage.eINSTANCE
												.getContentDescription_KeyConsiderations(),
										newContent, -1);
						if (debug) {
							System.out
									.println("DescriptionFormPage.ctrl_key.deactivateListener: saved status=" + success); //$NON-NLS-1$
						}
						if (success && isVersionSectionOn()) {
							updateChangeDate();
						}
					}
					if (debug) {
						System.out
								.println("DescriptionFormPage.ctrl_key.deactivateListener: exit"); //$NON-NLS-1$
					}
				}
			});
		}
	}

	/**
	 * Initializes the controls with data from the model.
	 */
	protected void loadData() {
		if (generalSectionOn) {
			loadGeneralSectionData();
		}
		if (detailSectionOn) {
			loadDetailSectionData();
		}
		if (notationSectionOn) {
			loadNotationSectionData();
		}
		if (tailoringSectionOn) {
			loadTailoringSectionData();
		}
		if (versionSectionOn) {
			loadVersionSectionData();
		}
		if (iconSectionOn) {
			loadIconSectionData();
		}
		if (variabilitySectionOn) {
			variabilitySectionData();
		}
		form.getBody().layout(true, true);
	}

	protected void loadGeneralSectionData() {
		String name = methodElement.getName();
		String presentationName = (methodElement).getPresentationName();
		ctrl_name.setText(name == null ? "" : name); //$NON-NLS-1$
		ctrl_presentation_name
				.setText(presentationName == null ? "" : presentationName); //$NON-NLS-1$

		if (longPresentationNameOn && AuthoringUIPreferences.getEnableUIFields()) {
			String longPresentName = contentElement.getPresentation().getLongPresentationName();
			ctrl_long_presentation_name.setText(longPresentName == null ? "" : longPresentName); //$NON-NLS-1$
		}
		if (elementTypeOn) {
			// String type = methodElement.getType().getName();
			String type = LibraryUIText.getUIText(methodElement);
			ctrl_type_label.setText(type == null ? "" : type); //$NON-NLS-1$
		}
		if (briefDescOn) {
			String brief_desc = methodElement.getBriefDescription();
			ctrl_brief_desc.setText(brief_desc == null ? "" : brief_desc); //$NON-NLS-1$
		}
		
		if (externalIdOn && AuthoringUIPreferences.getEnableUIFields()) {
			if (contentElement != null) {
				String external_id = contentElement.getPresentation().getExternalId();
				ctrl_external_id.setText(external_id == null ? "" : external_id); //$NON-NLS-1$
			}
		}
		
		if (publishCategoryOn) {
			MethodElementProperty prop = TngUtil.getPublishCategoryProperty(methodElement);
			if (prop != null) {
				String val = prop.getValue();
				ctrl_publish_categories_button.setSelection((new Boolean(val)).booleanValue());
			}
		}
	}

	protected void loadDetailSectionData() {
		if (fullDescOn) {
			String full_desc = (contentElement.getPresentation())
					.getMainDescription();
			ctrl_full_desc.setText(full_desc == null ? "" : full_desc); //$NON-NLS-1$
		}
		if (keyConsiderationOn) {
			String keyConsiderations = (contentElement.getPresentation())
					.getKeyConsiderations();
			ctrl_key
					.setText(keyConsiderations == null ? "" : keyConsiderations); //$NON-NLS-1$
		}
	}
	
	

	private void loadTailoringSectionData() {
	}

	private void loadNotationSectionData() {
	}

	private void variabilitySectionData() {
		VariabilityType variabilityType = contentElement.getVariabilityType();
		List selectionVariabilityList = new ArrayList();
		selectionVariabilityList.add(variabilityType);
		IStructuredSelection newVariabilitySelection = new StructuredSelection(
				selectionVariabilityList);
		viewer_variability.setSelection(newVariabilitySelection, true);
	}

	/**
	 * Toggle Description control to expand and control state.
	 */
	protected void toggle(HyperlinkEvent e) {
		if (ctrl_expanded == null) {
			ctrl_expanded = createRichTextEditor(toolkit, expandedComposite,
					SWT.MULTI | SWT.WRAP | SWT.V_SCROLL, GridData.FILL_BOTH,
					getRichTextEditorHeight(), getRichTextEditorWidth(), 2,
					expandLabel);
			ctrl_expanded.addModifyListener(contentModifyListener);
		}

		if (descExpandFlag) {
			ctrl_expanded.collapse();
			
			expandedComposite.setVisible(false);
			mainComposite.setVisible(true);
			formSection.setClient(mainComposite);
			enableSections(true);
			IMethodRichText richText = getActiveRichTextControl();
			richText.setText(ctrl_expanded.getText());
			richText.setFindReplaceAction(ctrl_expanded.getFindReplaceAction());
			for (Iterator i = richText.getListeners(); i.hasNext();) {
				RichTextListener listener = (RichTextListener) i.next();
				ctrl_expanded.removeListener(listener.getEventType(), listener
						.getListener());
			}
			if (ctrl_expanded.getModified()) {
				((MethodElementEditor) getEditor())
						.saveModifiedRichText(ctrl_expanded);
			}
			richText.setFocus();
			IEditorInput input = getEditorInput();
			if (input instanceof MethodElementEditorInput) {
				((MethodElementEditorInput)input).setModalObject(null);
				((MethodElementEditorInput)input).setModalObjectFeature(null);
			}
		} else {
			mainComposite.setVisible(false);
			expandedComposite.setVisible(true);
			formSection.setClient(expandedComposite);
			enableSections(false);
			expandLabel.setText((String) ((ImageHyperlink) e.getSource())
					.getData("Title")); //$NON-NLS-1$
			IMethodRichText richText = (IMethodRichText) e.getHref();
			ctrl_expanded.setInitialText(richText.getText());
			ctrl_expanded.setModalObject(richText.getModalObject());
			ctrl_expanded.setModalObjectFeature(richText
					.getModalObjectFeature());
			ctrl_expanded.setFindReplaceAction(richText.getFindReplaceAction());
			for (Iterator i = richText.getListeners(); i.hasNext();) {
				RichTextListener listener = (RichTextListener) i.next();
				ctrl_expanded.addListener(listener.getEventType(), listener
						.getListener());
			}
			boolean editable = !TngUtil.isLocked(methodElement);
			ctrl_expanded.setEditable(editable);
			if (editable) {
				ctrl_expanded.setFocus();
			}
			setActiveRichTextControl(richText);
			IEditorInput input = getEditorInput();
			if (input instanceof MethodElementEditorInput) {
				((MethodElementEditorInput)input).setModalObject(richText.getModalObject());
				((MethodElementEditorInput)input).setModalObjectFeature(richText.getModalObjectFeature());
			}
		}
		form.getBody().layout(true, true);
		descExpandFlag = !descExpandFlag;
	}

	/**
	 * Set the active rich text control.
	 */
	private void setActiveRichTextControl(IMethodRichText ctrl) {
		activeControl = ctrl;
	}

	/**
	 * Returns the active rich text control.
	 */
	private IMethodRichText getActiveRichTextControl() {
		return activeControl;
	}

	public void setBriefDescOn(boolean briefDescOn) {
		this.briefDescOn = briefDescOn;
	}
	
	public void setExternalIDOn(boolean exteranlIDOn) {
		this.externalIdOn = exteranlIDOn;
	}
	
	public void setLongPresentationNameOn(boolean longPresentationNameOn) {
		this.longPresentationNameOn = longPresentationNameOn;
	}

	public void setFullDescOn(boolean fullDescOn) {
		this.fullDescOn = fullDescOn;
	}

	public void setKeyConsiderationOn(boolean keyConsiderationOn) {
		this.keyConsiderationOn = keyConsiderationOn;
	}

	/**
	 * @param elementTypeOn
	 *            The elementTypeOn to set.
	 */
	public void setElementTypeOn(boolean elementTypeOn) {
		this.elementTypeOn = elementTypeOn;
	}

	/**
	 * @return Returns the anyAttributeModified.
	 */
	public boolean isAnyAttributeModified() {
		return anyAttributeModified;
	}

	/**
	 * Set this if any attribute is modified. This is required for the change
	 * date field in version section.
	 * 
	 * @param anyAttributeModified
	 *            The anyAttributeModified to set.
	 */
	public void setAnyAttributeModified(boolean anyAttributeModified) {
		this.anyAttributeModified = anyAttributeModified;
	}

	/**
	 * @return Returns the versionSectionOn.
	 */
	public boolean isVersionSectionOn() {
		return versionSectionOn;
	}

	/**
	 * @param versionSectionOn
	 *            The versionSectionOn to set.
	 */
	public void setVersionSectionOn(boolean versionSectionOn) {
		this.versionSectionOn = versionSectionOn;
	}

	/**
	 * Creates the Version Information section.
	 */
	private void createVersionSection(FormToolkit toolkit) {
		versionSection = toolkit.createSection(sectionComposite,
				Section.DESCRIPTION | Section.TWISTIE | Section.EXPANDED
						| Section.TITLE_BAR);
		GridData td = new GridData(GridData.FILL_BOTH);
		versionSection.setLayoutData(td);
		versionSection.setText(AuthoringUIText.VERSION_INFO_SECTION_NAME);
		versionSection.setDescription(getVersionSectionDescription());
		versionSection.setLayout(new GridLayout());

		versionComposite = toolkit.createComposite(versionSection);
		versionComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		versionComposite.setLayout(new GridLayout(5, false));
		versionSection.setClient(versionComposite);
	}
	
	

	/**
	 * Create the Version section content.
	 */
	protected void createVersionSectionContent() {
		String fillText = (methodUnit.getVersion() == null ? "" : methodUnit.getVersion()); //$NON-NLS-1$
		ctrl_version = createTextEditWithLabel4(toolkit, versionComposite,
				AuthoringUIText.VERSION_TEXT, SWT.DEFAULT, SWT.SINGLE, fillText);

		fillText = methodUnit.getChangeDate() == null ? "" //$NON-NLS-1$
				: DateFormat.getDateInstance(DateFormat.FULL).format(
						methodUnit.getChangeDate());
		ctrl_change_date = createTextEditWithLabel4(toolkit, versionComposite,
				AuthoringUIText.CHANGE_DATE_TEXT, SWT.DEFAULT, SWT.SINGLE, fillText);
		ctrl_change_date.setEditable(false);

		ctrl_change_desc = createTextEditWithLabel5(toolkit, versionComposite,
				AuthoringUIText.CHANGE_DESCRIPTION_TEXT, 40, SWT.MULTI);

		ctrl_authors = createTextEditWithLabel5(toolkit, versionComposite,
				AuthoringUIText.AUTHORS_TEXT, 40, SWT.MULTI);

		label_copyright = createLabel(toolkit, versionComposite,
				AuthoringUIText.COPYRIGHT_TEXT, 2);
		ctrl_copyright = createTable(toolkit, versionComposite, SWT.SINGLE
				| SWT.READ_ONLY, GridData.FILL_HORIZONTAL | GridData.BEGINNING | GridData.FILL_VERTICAL,
				5, 400, 1, 2);

		copyright_viewer = new TableViewer(ctrl_copyright);
		initContentProviderCopyright();
		copyright_viewer.setLabelProvider(copyrightLabelProviderBase);
		copyright_viewer.setInput(methodElement);
		Composite buttonpane = createComposite(toolkit, versionComposite,
				GridData.HORIZONTAL_ALIGN_END, 1, 1, 1);
		{
			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
			buttonpane.setLayoutData(gridData);
		}
		copyright_button = toolkit.createButton(buttonpane,
				AuthoringUIText.SELECT_BUTTON_TEXT, SWT.SIMPLE);
		{
			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
			gridData.widthHint = BUTTON_WIDTH;
			copyright_button.setLayoutData(gridData);
		}
		copyright_button_deselect = toolkit.createButton(buttonpane,
				AuthoringUIText.DESELECT_BUTTON_TEXT, SWT.SIMPLE);
		{
			GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
			gridData.widthHint = BUTTON_WIDTH;
			copyright_button_deselect.setLayoutData(gridData);
		}

		setCopyrightButtonDeselect();

		// Create the expanded composite.
		toolkit.paintBordersFor(versionComposite);
		//toolkit.paintBordersFor(buttonpane);
	}

	private void initContentProviderCopyright() {
		copyrightContentProvider = new AdapterFactoryContentProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public Object[] getElements(Object object) {
				List list = new ArrayList();
				if (methodUnit.getCopyrightStatement() != null) {
					list.add(methodUnit
							.getCopyrightStatement());
				}
				return list.toArray();
			}

			public void notifyChanged(Notification notification) {
			}
		};
		copyright_viewer.setContentProvider(copyrightContentProvider);
	}

	protected void addVersionSectionListeners() {
		ctrl_version.addModifyListener(contentModifyListener);
		ctrl_version.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget,
						UmaPackage.eINSTANCE.getMethodUnit_Version());
			}

			public void focusLost(FocusEvent e) {
				String oldContent = methodUnit
						.getVersion();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						e.widget, oldContent)) {
					return;
				}
				String newContent = StrUtil
						.getPlainText(ctrl_version.getText());
				if (!newContent.equals(oldContent)) {
					boolean status = actionMgr.doAction(IActionManager.SET,
							methodUnit,
							UmaPackage.eINSTANCE.getMethodUnit_Version(),
							newContent, -1);
					if (status) {
						updateChangeDate();
					}
				}
			}
		});

		ctrl_authors.addModifyListener(contentModifyListener);
		ctrl_authors.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget,
						UmaPackage.eINSTANCE.getMethodUnit_Authors());
			}

			public void focusLost(FocusEvent e) {
				String oldContent = methodUnit
						.getAuthors();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						e.widget, oldContent)) {
					return;
				}
				String newContent = StrUtil
						.getPlainText(ctrl_authors.getText());
				if (!newContent.equals(oldContent)) {
					boolean status = actionMgr.doAction(IActionManager.SET,
							methodUnit,
							UmaPackage.eINSTANCE.getMethodUnit_Authors(),
							newContent, -1);
					if (status) {
						updateChangeDate();
					}
				}
			}
		});

		copyright_button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IFilter filter = new ContentFilter() {
					protected boolean childAccept(Object obj) {
						if (obj instanceof Guidance) {
							return (obj instanceof SupportingMaterial);
						}
						return false;
					}
				};
				List alreadyExsting = new ArrayList();
				if (ctrl_copyright.getItemCount() > 0) {
					TableItem item = ctrl_copyright.getItems()[0];
					if (item.getData() != null)
						alreadyExsting.add(item.getData());
				}
				ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
						.getWorkbench().getActiveWorkbenchWindow().getShell(),
						filter, methodElement,
						FilterConstants.SUPPORTING_MATERIALS, alreadyExsting);
				fd.setViewerSelectionSingle(true);
				fd.setBlockOnOpen(true);
				fd.setTitle(FilterConstants.SUPPORTING_MATERIALS);
				fd.open();
				fd.getSelectedItems();
				if (fd.getSelectedItems().size() > 0) {
					boolean status = actionMgr.doAction(IActionManager.SET,
							methodUnit,
							UmaPackage.eINSTANCE
									.getMethodUnit_CopyrightStatement(),
							(SupportingMaterial) fd.getSelectedItems().get(0),
							-1);
					if (!status) {
						return;
					}
				}
				copyright_viewer.refresh();
				setCopyrightButtonDeselect();
			}
		});

		ctrl_change_desc.addModifyListener(contentModifyListener);
		ctrl_change_desc.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				((MethodElementEditor) getEditor()).setCurrentFeatureEditor(e.widget,
						UmaPackage.eINSTANCE.getMethodUnit_ChangeDescription());
			}

			public void focusLost(FocusEvent e) {
				String oldContent = methodUnit
						.getChangeDescription();
				if (((MethodElementEditor) getEditor()).mustRestoreValue(
						e.widget, oldContent)) {
					return;
				}
				String newContent = ctrl_change_desc.getText();
				newContent = newContent.replace(StrUtil.LINE_FEED, AuthoringUIResources.ChangeHistoryDialog_delimiter);
				if (!newContent.equals(oldContent)) {
					boolean success = actionMgr.doAction(
							IActionManager.SET, methodUnit, UmaPackage.eINSTANCE
							.getMethodUnit_ChangeDescription(), newContent, -1);
					if (success) {
						updateChangeDate();
					}
				}
			}
		});

		copyright_button_deselect.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean status = actionMgr.doAction(IActionManager.SET,
						methodUnit, UmaPackage.eINSTANCE
								.getMethodUnit_CopyrightStatement(), null, -1);
				if (status) {
					copyright_viewer.refresh();
					setCopyrightButtonDeselect();
				}
			}
		});
	}

	/*
	 * update the change date if any attribute is modified.
	 */
	protected void updateChangeDate() {

		Date changeDate = methodUnit.getChangeDate();
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);

		String oldContent = ""; //$NON-NLS-1$
		if (changeDate != null) {
			oldContent = df.format(changeDate);
		}

		Date currentDate = new Date();
		String newContent = df.format(currentDate);
		if (!newContent.equals(oldContent)) {
			boolean status = actionMgr.doAction(IActionManager.SET,
					methodUnit, UmaPackage.eINSTANCE
							.getMethodUnit_ChangeDate(), currentDate, -1);
			if (!status) {
				return;
			} else {
				ctrl_change_date.setText(newContent);
			}
		}
	}

	protected void loadVersionSectionData() {

		ctrl_version
				.setText(methodUnit.getVersion() == null ? "" : methodUnit.getVersion()); //$NON-NLS-1$
		ctrl_authors
				.setText(methodUnit.getAuthors() == null ? "" : methodUnit.getAuthors()); //$NON-NLS-1$
		String changeDesc = methodUnit.getChangeDescription() == null ? "" : methodUnit.getChangeDescription(); //$NON-NLS-1$
		changeDesc = changeDesc.replace(AuthoringUIResources.ChangeHistoryDialog_delimiter, StrUtil.LINE_FEED);
		ctrl_change_desc.setText(changeDesc);
		ctrl_change_date
				.setText(methodUnit.getChangeDate() == null ? "" //$NON-NLS-1$
						: DateFormat.getDateInstance(DateFormat.FULL).format(
								methodUnit.getChangeDate()));
	}

	/**
	 * Toggle Description control to expand and control state
	 */
	protected void toggle(HyperlinkEvent e, int id) {
		// TODO- we should combine these methods into one. One way to do it,
		// dispoing
		// ctrl_expanded every time it collapses and creating it when we expand.
		// At present, there is no method to dispose
		toggle(e);
	}

	/**
	 * @return Returns the iconSectionOn.
	 */
	public boolean isIconSectionOn() {
		return iconSectionOn;
	}

	/**
	 * @param iconSectionOn
	 *            The iconSectionOn to set.
	 */
	public void setIconSectionOn(boolean iconSectionOn) {
		this.iconSectionOn = iconSectionOn;
	}

	/**
	 * Creates the Icon section.
	 */
	protected void createIconSection(FormToolkit toolkit) {
		iconSection = toolkit.createSection(sectionComposite,
				Section.DESCRIPTION | Section.TWISTIE | Section.EXPANDED
						| Section.TITLE_BAR);
		GridData td = new GridData(GridData.FILL_BOTH);
		iconSection.setLayoutData(td);
		iconSection.setText(AuthoringUIText.ICON_SECTION_NAME);
		iconSection.setDescription(getIconSectionDescription());
		iconSection.setLayout(new GridLayout());

		iconComposite = toolkit.createComposite(iconSection);
		iconComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		iconComposite.setLayout(new GridLayout(3, true));
		iconSection.setClient(iconComposite);
	}
	private void createIconSectionContent() {
		Composite shapeIconComposite = createComposite(toolkit, iconComposite,
				1);
		shapeIconComposite.setLayoutData(new GridData());
		shapeIconComposite.setLayout(new GridLayout(2, true));
//		createLabel(
//				toolkit,
//				shapeIconComposite,
//				AuthoringUIResources.DescriptionFormPage_ShapeIconPreview_Label, 2); //$NON-NLS-1$
		// No wrap needed
		createLabelWithNoWrap(
				toolkit,
				shapeIconComposite,
				AuthoringUIResources.DescriptionFormPage_ShapeIconPreview_Label,
				GridData.BEGINNING,
				2); 
		
		pane1 = new Canvas(shapeIconComposite, SWT.BORDER);
		{
			GridData gridData = new GridData(GridData.BEGINNING);

			gridData.heightHint = 60;
			gridData.widthHint = 60;
			gridData.verticalSpan = 1;
			gridData.horizontalSpan = 1;
			pane1.setLayoutData(gridData);
			pane1.setBackground(ColorConstants.lightGray);
		}
		// pane1.setSize(30,30);
		Composite pane2 = createCompositeForButtons(toolkit, shapeIconComposite);
		ctrl_select_shapeIcon_button = toolkit
				.createButton(
						pane2,
						AuthoringUIResources.DescriptionFormPage_ShapeIconPreview_SelectButtonLabel, SWT.NONE); 
		ctrl_clear_shapeIcon_button = toolkit
				.createButton(
						pane2,
						AuthoringUIResources.DescriptionFormPage_ShapeIconPreview_ClearButtonLabel, SWT.NONE); 
		if (contentElement.getShapeicon() == null)
			ctrl_clear_shapeIcon_button.setEnabled(false);
		else
			ctrl_clear_shapeIcon_button.setEnabled(true);

		Composite nodeIconComposite = createComposite(toolkit, iconComposite, 1);
		nodeIconComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		nodeIconComposite.setLayout(new GridLayout(2, false));
//		createLabel(
//				toolkit,
//				nodeIconComposite,
//				AuthoringUIResources.DescriptionFormPage_NodeIconPreview_Label, 2); //$NON-NLS-1$
		// No wrap need for 
		createLabelWithNoWrap(
				toolkit,
				nodeIconComposite,
				AuthoringUIResources.DescriptionFormPage_NodeIconPreview_Label,
				GridData.BEGINNING,
				2); 
		pane3 = new Canvas(nodeIconComposite, SWT.BORDER);
		{
			GridData gridData = new GridData(GridData.BEGINNING);

			gridData.heightHint = 40;
			gridData.widthHint = 40;
			gridData.verticalSpan = 1;
			gridData.horizontalSpan = 1;
			pane3.setLayoutData(gridData);
			pane3.setBackground(ColorConstants.lightGray);
		}
		Composite pane4 = createCompositeForButtons(toolkit, nodeIconComposite);
		ctrl_select_nodeIcon_button = toolkit
				.createButton(
						pane4,
						AuthoringUIResources.DescriptionFormPage_NodeIconPreview_SelectButtonLabel, SWT.NONE); 
		ctrl_clear_nodeIcon_button = toolkit
				.createButton(
						pane4,
						AuthoringUIResources.DescriptionFormPage_NodeIconPreview_ClearButtonLabel, SWT.NONE); 
		if (contentElement.getNodeicon() == null)
			ctrl_clear_nodeIcon_button.setEnabled(false);
		else
			ctrl_clear_nodeIcon_button.setEnabled(true);
		
		new Label(iconComposite, SWT.NONE);
		shapeIconPath = createLabelWithNoWrap(toolkit,
				iconComposite, "", GridData.BEGINNING, 1); //$NON-NLS-1$
		nodeIconPath = createLabelWithNoWrap(toolkit,
				iconComposite, "", GridData.BEGINNING, 2); //$NON-NLS-1$
//			new Label(nodeIconComposite, SWT.NONE);
//		GridData data = new GridData(GridData.BEGINNING);
//		data.horizontalSpan =2;
//		nodeIconPath.setLayoutData(data);

		toolkit.paintBordersFor(pane1);
		toolkit.paintBordersFor(pane2);
		toolkit.paintBordersFor(pane3);
		toolkit.paintBordersFor(pane4);
	}

	protected void addIconSectionListeners() {

		ctrl_select_shapeIcon_button
				.addSelectionListener(new SelectionAdapter() {
//					File libDirFile = new File(LibraryService.getInstance()
//							.getCurrentMethodLibraryPath());

					public void widgetSelected(SelectionEvent e) {
						java.net.URI shapeIconUri = null;
						FileDialog fileChooser = new FileDialog(Display
								.getCurrent().getActiveShell(), SWT.OPEN);
						fileChooser.setText(AuthoringUIResources.DescriptionFormPage_IconFileChooserDialog_title); 
						fileChooser.setFilterExtensions(imageTypes); 
						fileChooser.setFilterNames(imageNames); 
						String imageFile = null;
						String filename = null;
						while (true) {
							imageFile = fileChooser.open();
							filename = fileChooser.getFileName();
							if (imageFile != null) {
								if (new File(imageFile).exists()) {
//									ImageData imageData = new ImageData(
//											imageFile);
//									if (imageData.width > SHAPEICON_WIDTH_MAX
//											|| imageData.height > SHAPEICON_HEIGHT_MAX) {
//										// msg user - image too big
//										AuthoringUIPlugin
//												.getDefault()
//												.getMsgDialog()
//												.displayError(
//														AuthoringUIResources.forms_DescriptionFormPage_imageTooBigDialog_title, //$NON-NLS-1$ 
//														MessageFormat
//																.format(
//																		AuthoringUIResources.forms_DescriptionFormPage_imageTooBigDialog_template, //$NON-NLS-1$
//																		new Object[] {
//																				imageFile,
//																				new Integer(
//																						SHAPEICON_WIDTH_MAX),
//																				new Integer(
//																						SHAPEICON_HEIGHT_MAX) }));
//									} else {
//										break;
//									}
									break;
								} else {
									// msg user - file doesn't exist
									AuthoringUIPlugin
											.getDefault()
											.getMsgDialog()
											.displayError(
													AuthoringUIResources.forms_DescriptionFormPage_imageNotFoundDialog_title, 
													MessageFormat
															.format(
																	AuthoringUIResources.forms_DescriptionFormPage_imageNotFoundDialog_template, 
																	new Object[] { imageFile }));
								}
							} else {
								return;
							}
						}
						if (imageFile != null && imageFile.length() > 0) {
							try {
								String url = copyResourceToLib(ctrl_select_shapeIcon_button.getShell(), filename,
										fileChooser.getFilterPath(),
										methodElement);
								if (url != null) {
									File file = new File(url);
									shapeIconUri = new URI(NetUtil
											.encodeFileURL(FileUtil
													.getRelativePath(file,
															new File(getPluginPath(methodElement)))));
									if (shapeIconUri != null) {
										boolean status = actionMgr
												.doAction(
														IActionManager.SET,
														methodElement,
														UmaPackage.eINSTANCE
																.getDescribableElement_Shapeicon(),
														shapeIconUri, -1);
										if (!status)
											return;
									}
									shapeImage = new Image(
											Display.getCurrent(), file
													.getAbsolutePath());
									shapeIconPath.setText(NetUtil.decodedFileUrl(shapeIconUri.getPath()));
								}
							} catch (Exception ex) {
								shapeIconUri = null;
								shapeImage = null;
								AuthoringUIPlugin.getDefault().getLogger()
										.logError(ex);
								if (ex instanceof IOException) {
									AuthoringUIPlugin
											.getDefault()
											.getMsgDialog()
											.displayError(
													AuthoringUIResources.err_copying_file_title, 
													AuthoringUIResources.err_copying_file_error, 
													AuthoringUIResources.err_copying_file_reason, 
													ex.getMessage());
								} else {
									AuthoringUIPlugin
											.getDefault()
											.getMsgDialog()
											.displayError(
													AuthoringUIResources.err_processing_file_title, 
													AuthoringUIResources.err_processing_file_description, ex); 
								}
							}
						}
						if (shapeImage != null) {
							pane1.redraw();
							ctrl_clear_shapeIcon_button.setEnabled(true);
						} else {
							ctrl_clear_shapeIcon_button.setEnabled(false);
						}

					}
				});

		ctrl_clear_shapeIcon_button
				.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						boolean status = actionMgr.doAction(IActionManager.SET,
								methodElement, UmaPackage.eINSTANCE
										.getDescribableElement_Shapeicon(),
								null, -1);
						if (!status)
							return;
						pane1.redraw();
						ctrl_clear_shapeIcon_button.setEnabled(false);
						shapeIconPath.setText(""); //$NON-NLS-1$
					}
				});

		ctrl_select_nodeIcon_button
				.addSelectionListener(new SelectionAdapter() {
//					File libDirFile = new File(LibraryService.getInstance()
//							.getCurrentMethodLibraryPath());

					public void widgetSelected(SelectionEvent e) {
						java.net.URI nodeIconUri = null;
						FileDialog fileChooser = new FileDialog(Display
								.getCurrent().getActiveShell(), SWT.OPEN);
						fileChooser.setText(AuthoringUIResources.DescriptionFormPage_IconFileChooserDialog_title); 
						fileChooser.setFilterExtensions(imageTypes); 
						fileChooser.setFilterNames(imageNames); 
						String imageFile = null;
						String filename = null;
						while (true) {
							imageFile = fileChooser.open();
							filename = fileChooser.getFileName();
							if (imageFile != null) {
								if (new File(imageFile).exists()) {
									ImageData imageData = new ImageData(
											imageFile);
									if (imageData.width > NODEICON_WIDTH_MAX
											|| imageData.height > NODEICON_HEIGHT_MAX) {
										// msg user - image too big
										AuthoringUIPlugin
												.getDefault()
												.getMsgDialog()
												.displayError(
														AuthoringUIResources.forms_DescriptionFormPage_imageTooBigDialog_title, 
														MessageFormat
																.format(
																		AuthoringUIResources.forms_DescriptionFormPage_imageTooBigDialog_template, 
																		new Object[] {
																				imageFile,
																				new Integer(
																						NODEICON_WIDTH_MAX),
																				new Integer(
																						NODEICON_HEIGHT_MAX) }));
									} else {
										break;
									}
								} else {
									// msg user - file doesn't exist
									AuthoringUIPlugin
											.getDefault()
											.getMsgDialog()
											.displayError(
													AuthoringUIResources.forms_DescriptionFormPage_imageNotFoundDialog_title, 
													MessageFormat
															.format(
																	AuthoringUIResources.forms_DescriptionFormPage_imageNotFoundDialog_template, 
																	new Object[] { imageFile }));
								}
							} else {
								return;
							}
						}
						if (imageFile != null && imageFile.length() > 0) {
							try {
								String url = copyResourceToLib(ctrl_select_nodeIcon_button.getShell(), filename,
										fileChooser.getFilterPath(),
										methodElement);
								if (url != null) {
									File file = new File(url);
									nodeIconUri = new URI(NetUtil
											.encodeFileURL(FileUtil
													.getRelativePath(file,
															new File(getPluginPath(methodElement)))));
									if (nodeIconUri != null) {
										boolean status = actionMgr
												.doAction(
														IActionManager.SET,
														methodElement,
														UmaPackage.eINSTANCE
																.getDescribableElement_Nodeicon(),
														nodeIconUri, -1);
										if (!status)
											return;
									}
									nodeImage = new Image(Display.getCurrent(),
											file.getAbsolutePath());
									nodeIconPath.setText(NetUtil.decodedFileUrl(nodeIconUri.getPath()));
								}
							} catch (Exception ex) {
								nodeIconUri = null;
								nodeImage = null;
								AuthoringUIPlugin.getDefault().getLogger()
										.logError(ex);
								if (ex instanceof IOException) {
									AuthoringUIPlugin
											.getDefault()
											.getMsgDialog()
											.displayError(
													AuthoringUIResources.err_copying_file_title, 
													AuthoringUIResources.err_copying_file_error, 
													AuthoringUIResources.err_copying_file_reason, 
													ex.getMessage());

								} else {
									AuthoringUIPlugin
											.getDefault()
											.getMsgDialog()
											.displayError(
													AuthoringUIResources.err_processing_file_title, 
													AuthoringUIResources.err_processing_file_description, ex); 
								}
							}
						}
						if (nodeImage != null) {
							pane3.redraw();
							ctrl_clear_nodeIcon_button.setEnabled(true);
						} else {
							ctrl_clear_nodeIcon_button.setEnabled(false);
						}
					}
				});

		ctrl_clear_nodeIcon_button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean status = actionMgr.doAction(IActionManager.SET,
						methodElement, UmaPackage.eINSTANCE
								.getDescribableElement_Nodeicon(), null, -1);
				if (!status)
					return;
				pane3.redraw();
				ctrl_clear_nodeIcon_button.setEnabled(false);
				nodeIconPath.setText(""); //$NON-NLS-1$
			}
		});
	}

	/**
	 * Copies the selected shapeNodeIcon and nodeIcon to resources folder of
	 * content element. Prompts to overwrite if file already exists.
	 * @param sFileName
	 * @param sFilePath
	 * @param methodElement
	 * @return
	 * @throws IOException
	 */
	protected String copyResourceToLib(Shell shell, String sFileName, String sFilePath,
			MethodElement methodElement) throws IOException {
		File newFile = LibraryUIUtil.copyResourceToLib(shell, new File(sFilePath, sFileName), methodElement);
		if (newFile != null)
			return newFile.getAbsolutePath();
		else 
			return null;
	}

	protected void loadIconSectionData() {
		pane1.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				// paint the grey background
				Rectangle clientRect = pane1.getClientArea();
				e.gc.setClipping(clientRect);
				e.gc.setBackground(Display.getCurrent().getSystemColor(15));
				e.gc.fillRectangle(clientRect);

				java.net.URI uri = contentElement.getShapeicon();
				if (uri != null) {
					String path = NetUtil.decodedFileUrl(contentElement.getShapeicon().getPath());
					if (path != null) {
						try {
							File file = new File(path);
							if (file.exists()) {
							} else {
								if (!file.isAbsolute()) {
									// Check for the uri contains the relative to library.
									MethodPlugin plugin = UmaUtil.getMethodPlugin(methodElement);
									if(path.indexOf(plugin.getName()) > -1){
										path = LibraryService.getInstance()
										.getCurrentMethodLibraryLocation()
										+ File.separator + path;	
									}else{
										path = getPluginPath(methodElement)
										+ File.separator + path;
									}
									
								}
								file = new File(path);
							}
							if (file.exists()) {
								if (shapeImage == null) {
									shapeImage = new Image(
											Display.getCurrent(), path);
								}
								if(contentElement.getShapeicon() != null){
									shapeIconPath.setText(NetUtil.decodedFileUrl(contentElement.getShapeicon().getPath()));
								}
								clientRect = pane1.getClientArea();
								e.gc.setClipping(clientRect);
								e.gc.setBackground(Display.getCurrent()
										.getSystemColor(SWT.COLOR_WHITE));
								e.gc.fillRectangle(clientRect);
								e.gc.drawImage(shapeImage, shapeImage
										.getBounds().x,
										shapeImage.getBounds().y, shapeImage
												.getBounds().width, shapeImage
												.getBounds().height, pane1
												.getClientArea().x, pane1
												.getClientArea().y, pane1
												.getClientArea().width, pane1
												.getClientArea().height);
							} else {
								if (warningCount == 1) {
									warningCount++;
									String msg = AuthoringUIResources.bind(AuthoringUIResources.DescriptionFormPage_LoadShapeIconWarning, (new Object[] { methodElement.getName(), path })); 
									MsgBox.showWarning(msg);
								}

							}
						} catch (Exception ex) {
							AuthoringUIPlugin
									.getDefault()
									.getLogger()
									.logError(
											AuthoringUIResources.bind(AuthoringUIResources.DescriptionFormPage_LoadShapeIconError, (new Object[] { ex.getMessage() }))); 
						}
					}
				}
			}

		});
		pane1.redraw();
		if(contentElement.getShapeicon() != null){
			shapeIconPath.setText(NetUtil.decodedFileUrl(contentElement.getShapeicon().getPath()));
		}
		
		pane3.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				// paint the grey background
				Rectangle clientRect = pane3.getClientArea();
				e.gc.setClipping(clientRect);
				e.gc.setBackground(Display.getCurrent().getSystemColor(15));
				e.gc.fillRectangle(clientRect);

				java.net.URI uri = contentElement.getNodeicon();
				if (uri != null) {
					String path = NetUtil.decodedFileUrl(contentElement.getNodeicon().getPath());
					if (path != null) {
						try {
							File file = new File(path);
							if (file.exists()) {
							} else {
								if (!file.isAbsolute()) {
									MethodPlugin plugin = UmaUtil.getMethodPlugin(methodElement);
									if(path.indexOf(plugin.getName()) > -1){
										path = LibraryService.getInstance()
										.getCurrentMethodLibraryLocation()
										+ File.separator + path;	
									}else{
										path = getPluginPath(methodElement)
										+ File.separator + path;
									}
								}
								file = new File(path);
							}
							if (file.exists()) {
								if (nodeImage == null) {
									nodeImage = new Image(Display.getCurrent(),
											path);
								}
								if(contentElement.getNodeicon() != null){
									nodeIconPath.setText(NetUtil.decodedFileUrl(contentElement.getNodeicon().getPath()));
								}
								clientRect = pane3.getClientArea();
								e.gc.setClipping(clientRect);
								e.gc.setBackground(Display.getCurrent()
										.getSystemColor(SWT.COLOR_WHITE));
								e.gc.fillRectangle(clientRect);
								e.gc.drawImage(nodeImage,
										nodeImage.getBounds().x, nodeImage
												.getBounds().y, nodeImage
												.getBounds().width, nodeImage
												.getBounds().height, pane3
												.getClientArea().x, pane3
												.getClientArea().y, pane3
												.getClientArea().width, pane3
												.getClientArea().height);
							} else {
								if (warningCount == 1) {
									warningCount++;
									String msg = AuthoringUIResources.bind(AuthoringUIResources.DescriptionFormPage_LoadNodeIconWarning, (new Object[] { methodElement.getName(), path })); 
									MsgBox.showWarning(msg);
								}

							}
						} catch (Exception ex) {
							AuthoringUIPlugin
									.getDefault()
									.getLogger()
									.logError(
											AuthoringUIResources.bind(AuthoringUIResources.DescriptionFormPage_LoadNodeIconError, (new Object[] { ex.getMessage() }))); 
						}
					}
				}
			}

		});
		pane3.redraw();
		if(contentElement.getNodeicon() != null){
			nodeIconPath.setText(NetUtil.decodedFileUrl(contentElement.getNodeicon().getPath()));
		}
	}

	
	protected void enableSections(boolean enable) {
		if (generalSectionOn) {
			generalSection.setVisible(enable);
			if (enable) {
				generalSection.setExpanded(generalSectionExpandFlag);
			} else {
				generalSectionExpandFlag = generalSection.isExpanded();
				generalSection.setExpanded(enable);
			}
		}

		if (detailSectionOn) {
			detailSection.setVisible(enable);
			if (enable) {
				detailSection.setExpanded(detailSectionExpandFlag);
			} else {
				detailSectionExpandFlag = detailSection.isExpanded();
				detailSection.setExpanded(enable);
			}
		}
		if (notationSectionOn) {
			notationSection.setVisible(enable);
			if (enable) {
				notationSection.setExpanded(notationSectionExpandFlag);
			} else {
				notationSectionExpandFlag = notationSection.isExpanded();
				notationSection.setExpanded(enable);
			}
		}
		if (tailoringSectionOn) {
			tailoringSection.setVisible(enable);
			if (enable) {
				tailoringSection.setExpanded(tailoringSectionExpandFlag);
			} else {
				tailoringSectionExpandFlag = tailoringSection.isExpanded();
				tailoringSection.setExpanded(enable);
			}
		}
		if (versionSectionOn) {
			versionSection.setVisible(enable);
			if (enable) {
				versionSection.setExpanded(versionSectionExpandFlag);
			} else {
				versionSectionExpandFlag = versionSection.isExpanded();
				versionSection.setExpanded(enable);
			}
		}
		if (iconSectionOn) {
			iconSection.setVisible(enable);
			if (enable) {
				iconSection.setExpanded(iconSectionExpandFlag);
			} else {
				iconSectionExpandFlag = iconSection.isExpanded();
				iconSection.setExpanded(enable);
			}
		}
		if (variabilitySectionOn) {
			variabilitySection.setVisible(enable);
			if (enable) {
				variabilitySection.setExpanded(elementSectionExpandFlag);
			} else {
				elementSectionExpandFlag = variabilitySection.isExpanded();
				variabilitySection.setExpanded(enable);
			}
		}
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#dispose()
	 */
	public void dispose() {
		if (shapeImage != null) {
			shapeImage.dispose();
		}
		if (nodeImage != null) {
			nodeImage.dispose();
		}
		if (labelProviderVariability != null) {
			labelProviderVariability.dispose();
		}
		if (contentProviderVariability != null) {
			contentProviderVariability.dispose();
		}
		if (labelProviderBase != null) {
			labelProviderBase.dispose();
		}
		if (copyrightLabelProviderBase != null) {
			copyrightLabelProviderBase.dispose();
		}
		
		if (columnProvider != null) {
			columnProvider.dispose();
		}
		if (sectionProviders != null) {
			for (int i = 0; i < sectionProviders.size(); i++) {
				Object provider = sectionProviders.get(i);
				if (provider != null && provider instanceof ISectionProvider) {
					((ISectionProvider) provider).dispose();
				}
			}
		}
		super.dispose();
		
		disposed = true;
	}		

	private void setCopyrightButtonDeselect() {
		if (methodUnit.getCopyrightStatement() != null) {
			copyright_button_deselect
					.setEnabled((methodUnit
							.getCopyrightStatement().getName() == null) ? false
							: true);
		} else
			copyright_button_deselect.setEnabled(false);
		if (TngUtil.isLocked(methodElement))
			copyright_button_deselect.setEnabled(false);
	}

	protected void setContextHelp() {
		if (mainComposite != null) {
			EditorsContextHelper.setHelp(mainComposite.getParent(),
					getContentElement());
		}
		if (expandedComposite != null) {
			PlatformUI.getWorkbench().getHelpSystem().setHelp(
					expandedComposite,
					AuthoringUIHelpContexts.RICH_TEXT_EDITOR_CONTEXT_ID);
		}

	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.ui.forms.IRefreshable#refreshName(java.lang.String)
	 */
	public void refreshName(String newName) {
		if (newName != null) {
			if ((ctrl_name != null) && !(ctrl_name.isDisposed())) {
				if (nameModifyListener != null) {				
					ctrl_name.removeModifyListener(nameModifyListener);
					ctrl_name.setText(newName);
					ctrl_name.addModifyListener(nameModifyListener);
				} else {
					ctrl_name.setText(newName);
				}
				setFormTextWithVariableInfo();
			}
		}
	}
	
	public void loadSectionDescription(){
		
	}
	protected String getDetailSectionDescription(){
		if(detailSectionDescription != null){
			return detailSectionDescription;
		}
		return AuthoringUIText.DETAIL_SECTION_DESC;
	}
	protected String getGeneralSectionDescription(){
		if(generalSectionDescription!= null){
			return generalSectionDescription;
		}
		return AuthoringUIText.GENERAL_INFO_SECTION_DESC;
	}
	protected String getTailoringSectionDescription(){
		if(tailoringSectionDescription != null){
			return tailoringSectionDescription;
		}
		return AuthoringUIText.TAILORING_SECTION_DESC;
	}
	protected String getNotationSectionDescription(){
		if(notationSectionDescription != null){
			return notationSectionDescription;
		}
		return AuthoringUIText.NOTATION_SECTION_DESC;
	}
	protected String getVariabilitySectionDescription(){
		if(variabilitySectionDescription != null){
			return variabilitySectionDescription;
		}
		return AuthoringUIText.VARIABILITY_SECTION_DESC;
	}
	
	private String getIconSectionDescription() {
		// TODO Auto-generated method stubs
		if(iconSectionDescription != null){
			return iconSectionDescription;
		}
		return AuthoringUIText.ICON_SECTION_DESC;
	}
	private String getVersionSectionDescription() {
		// TODO Auto-generated method stub
		if(versionSectionDescription != null){
			return versionSectionDescription;
		}
		return AuthoringUIText.VERSION_INFO_SECTION_DESC;
	}

	/** 
	 * Following methods below exposed public in order to allow 
	 * test automation to access the form objects through test objects
	 * of any automation tools.
	 */
	
	/**
	 * Get brief description
	 * @return Returns the ctrl_brief_desc.
	 */
	public Text getCtrl_brief_desc() {
		return ctrl_brief_desc;
	}

	/**
	 * Set brief description
	 * @param ctrl_brief_desc The ctrl_brief_desc to set.
	 */
	public void setCtrl_brief_desc(Text ctrl_brief_desc) {
		this.ctrl_brief_desc = ctrl_brief_desc;
	}

	/**
	 * Get main description
	 * @return Returns the ctrl_full_desc.
	 */
	public IMethodRichText getCtrl_full_desc() {
		return ctrl_full_desc;
	}

	/**
	 * Set main description
	 * @param ctrl_full_desc The ctrl_full_desc to set.
	 */
	public void setCtrl_full_desc(IMethodRichText ctrl_full_desc) {
		this.ctrl_full_desc = ctrl_full_desc;
	}

	/**
	 * Get presentation name
	 * @return Returns the ctrl_presentation_name.
	 */
	public Text getCtrl_presentation_name() {
		return ctrl_presentation_name;
	}

	/**
	 * Set presentation name
	 * @param ctrl_presentation_name The ctrl_presentation_name to set.
	 */
	public void setCtrl_presentation_name(Text ctrl_presentation_name) {
		this.ctrl_presentation_name = ctrl_presentation_name;
	}

	/**
	 * Get expanded rich text control
	 * @return Returns the ctrl_expanded.
	 */
	public IMethodRichTextEditor getCtrl_expanded() {
		return ctrl_expanded;
	}

	/**
	 * Set expanded rich text control
	 * @param ctrl_expanded The ctrl_expanded to set.
	 */
	public void setCtrl_expanded(IMethodRichTextEditor ctrl_expanded) {
		this.ctrl_expanded = ctrl_expanded;
	}

	/**
	 * Get rich text control
	 * @return Returns the ctrl_key.
	 */
	public IMethodRichText getCtrl_key() {
		return ctrl_key;
	}

	/**
	 * Set rich text control
	 * @param ctrl_key The ctrl_key to set.
	 */
	public void setCtrl_key(IMethodRichText ctrl_key) {
		this.ctrl_key = ctrl_key;
	}
	
	public String getPluginPath(MethodElement e){
		MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
		if (plugin != null) {
			return ResourceHelper.getFolderAbsolutePath(plugin);
		}

		return null;
	}

	private String generateName(String presentationName) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < presentationName.length(); i++) {
			char c = presentationName.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				c -= 'A';
				c += 'a'; 
			} else if (c == ' ') {
				c = '_';
			}
			
			boolean toAdd = (c >= '0' && c <= '9') ||
							(c >= 'a' && c <= 'z') ||
							(c == '-' || c == '_' || c== '.');
			if (toAdd) {
				buf.append(c);
			}			
		}
		return buf.toString();
	}
	
	protected void changeElementName() {
		String name = ctrl_name.getText();
		
		if (! name.equals(methodElement.getName())) {
			
			IValidator validator = getNameValidator();
			if(validator == null){
				validator = IValidatorFactory.INSTANCE
					.createNameValidator(
							methodElement,
							TngAdapterFactory.INSTANCE
									.getNavigatorView_ComposedAdapterFactory());
			}
			
			String name0 = name;
			for  (int i = 1; i < 1000; i++) {
				String msg = validator.isValid(name);
				if (msg == null || msg.length() == 0) {
					ctrl_name.setText(name);
					break;
				}
				name = name0 + "_" + i;		//$NON-NLS-1$
			}
			
			if (! name.equals(methodElement.getName())) {
				changeElementName(ctrl_name.getText());	
			}
			
			LibraryUtil.removeNameTrackPresentationNameMark(methodElement);
		}
	
	}

	protected boolean isAutoGenName() {
		return autoGenName;
	}

	protected void setAutoGenName(boolean autoGenName) {
		this.autoGenName = autoGenName;
	}
	
	public static class DescriptionFormSectionExtender {

		protected DescriptionFormPage formPage;
		public DescriptionFormSectionExtender(DescriptionFormPage formPage) {
			this.formPage = formPage;
		}
		
		public void modifyGeneralSectionContent(FormToolkit toolkit, IActionManager actionMgr) {
		}
		
		protected Composite getGeneralComposite() {
			return formPage.generalComposite;
		}
		
		protected MethodElement getElement() {
			return formPage.methodElement;
		}
		
		protected void refresh(boolean editable) {
		}
		
	}
			
}
