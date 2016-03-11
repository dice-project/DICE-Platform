//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.editors;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.authoring.ui.forms.BaseFormPage;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.authoring.ui.richtext.MethodRichTextContext;
import org.eclipse.epf.authoring.ui.util.MethodRichTextMarkerHelper;
import org.eclipse.epf.common.IHTMLFormatter;
import org.eclipse.epf.library.layout.RichTextContentValidator;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.richtext.RichText;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * A Method Editor Rich Text control.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public class MethodRichText extends RichText implements IMethodRichText {

	// The method element associated with this rich text control.
	private MethodElement methodElement;
	
	// the MethodElement's ContentDescription
	private ContentDescription contentDescription;

	// The modal object associated with this rich text control.
	private EObject modalObject;

	// The modal object feature associated with this rich text control.
	private EStructuralFeature modalObjectFeature;

	// The resource being edited       
	protected Resource resource;
	
	// the decoratedField label
	protected Label label;
	
	protected ControlDecoration controlDecoration;
	
	protected final Image errorFieldDecorationImage = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage();
                                      
	// Field name being edited
	protected String fieldName = ""; //$NON-NLS-1$

	// Field name being edited with the trailing : removed     
	protected String fieldNameTrim;
	
	// marker helper
	protected MethodRichTextMarkerHelper markerHelper;
	
//	// marker attribute
//	private static String METHOD_FIELDNAME = "MethodFieldName";

//	// marker ID
//	protected static final String MARKER_ID = "org.eclipse.epf.authoring.ui.methodRichText"; //$NON-NLS-1$

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            The parent control.
	 * @param style
	 *            The initial style for the editor.
	 * @param basePath
	 *            The base path used for resolving hrefs.
	 */
	public MethodRichText(Composite parent, int style, String basePath) {
		super(parent, style, basePath);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param context
	 *            The context
	 */
	public MethodRichText(MethodRichTextContext context) {
		this(context.getParent(), context.getStyle(), context.getBasePath());
	}

	/**
	 * Updates the presentation names of all element links.
	 * 
	 * @param text
	 *            Rich text encoded in HTML format.
	 */
	@Override
	public String tidyText(String text) {
		/*
		 *  this will do the following to the HTML:
		 *  1.  update Element Links
		 *  2.  change <A ..> to <a ..>
		 *  3.  change </A> to </a>
		 *  4.  add double-quotes (") around all attribute values if they are missing
		 */
		
		return ResourceHelper.validateRichTextContent(methodElement, text,
				new RichTextContentValidator());
	}

	/**
	 * Sets the method element associated with this rich text control.
	 * 
	 * @param methodElement
	 */
	public void setMethodElement(MethodElement methodElement) {
		this.methodElement = methodElement;
		if (this.methodElement instanceof ProcessComponent) {
			this.methodElement = ((ProcessComponent) this.methodElement).getProcess();
		}
		if (this.methodElement instanceof DescribableElement) { 
			this.contentDescription = ((DescribableElement)this.methodElement).getPresentation();
		} else if (this.methodElement instanceof ContentDescription) {
			this.contentDescription = (ContentDescription)(this.methodElement);
		}
	}

	/**
	 * Returns the method element associated with this rich text control.
	 */
	public MethodElement getMethodElement() {
		return methodElement;
	}

	/**
	 * Returns the modal object associated with this rich text control.
	 */
	public EObject getModalObject() {
		return modalObject;
	}

	/**
	 * Sets the modal object associated with this rich text control.
	 */
	public void setModalObject(EObject modalObject) {
		this.modalObject = modalObject;
	}

	/**
	 * Returns modal object feature associated with this rich text control.
	 */
	public EStructuralFeature getModalObjectFeature() {
		return modalObjectFeature;
	}

	/**
	 * Sets the modal object feature associated with this rich text control.
	 */
	public void setModalObjectFeature(EStructuralFeature modalObjectFeature) {
		this.modalObjectFeature = modalObjectFeature;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setDecoratedFieldLabel(Label label) {
		this.label = label;
		if (label != null) {
			Object data = label.getData(BaseFormPage.LABEL_DECORATOR_KEY);
			if (data instanceof ControlDecoration) {
				controlDecoration = (ControlDecoration)data;
				controlDecoration.setImage(errorFieldDecorationImage);
				controlDecoration.hide();
			}
			updateFieldNameFromLabel(label);
		}
	}

	private boolean updateFieldNameFromLabel(Label label) {
		if (label != null) {
			String fieldName = label.getText();
			if (!this.fieldName.equals(fieldName)) {
				this.fieldName = fieldName;
				int colonIndex = fieldName.indexOf(':');
				if (colonIndex == -1)
					colonIndex = fieldName.length();
				this.fieldNameTrim = fieldName.substring(0, colonIndex).trim();
				return true;
			}
		}
		return false;
	}
	
	public void init(MethodElement element, Label label) {
		setMethodElement(element);
		setDecoratedFieldLabel(label);
		markerHelper = MethodRichTextMarkerHelper.INSTANCE;
	}
	
	@Override
	protected String formatHTML(String text) {
		String formattedText;
		try {
			// clear markers first
			clearMarkers();
			// Call JTidy to format the source to XHTML.
			formattedText = htmlFormatter.formatHTML(text);
			if (htmlFormatter.getLastErrorStr() != null) {
				String errorString = htmlFormatter.getLastErrorStr();
				// create markers
				try {
					createMarker(errorString);
				} catch (CoreException cex) {
					logger.logError(cex);
				}
			}
			return formattedText;
		} catch (UnsupportedEncodingException e) {
			logger.logError(e);
		}
		return text;
	}
	
	protected void clearMarkers() {
		markerHelper.deleteMarkers(contentDescription, fieldNameTrim);
		setErrorDescription(""); //$NON-NLS-1$
		hideErrorDecoration();
		refreshDecorators();
	}
	
	protected void setErrorDescription(String text) {
		if (controlDecoration != null) {
			controlDecoration.setDescriptionText(text);
		}
	}
	
	protected void refreshDecorators() {
		// refresh
//		PlatformUI.getWorkbench().getDecoratorManager().update(MethodElementLightweightLabelDecorator.DECORATOR_ID);
	}
	
	
	/*
	 * TODO:
	 * had to undo change in DescribableElementImpl to set presentation on any new obj
	 * so editor will need to assign presentation when field is edited so that the marker
	 * can be added to the presentation's resource
	 */
	
	
	protected void createMarker(String fullErrMsg) throws CoreException {
		if (contentDescription.eContainer() instanceof DescribableElement) {
			Matcher m = IHTMLFormatter.jTidyErrorParser.matcher(fullErrMsg);                                                                          
			if (m.find()) {                                                                                                                          
				String location = m.group(1);
				String errorMsg = m.group(4);
				BasicDiagnostic diagnostics = 
			          new BasicDiagnostic
			            (IHTMLFormatter.DIAGNOSTIC_SOURCE,
			             0, "", //$NON-NLS-1$
			                new Object[] { contentDescription, fieldNameTrim });
	
				diagnostics.add( 
					new BasicDiagnostic(Diagnostic.ERROR,
						location, 0, errorMsg,
						new Object[] { contentDescription, fieldNameTrim }));
	
		        markerHelper.createMarkers(diagnostics);
		        setErrorDescription(errorMsg);
		        showErrorDecoration();
				refreshDecorators();
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		clearMarkers();
		refreshDecorators();
	}
	
	public void showErrorDecoration() {
        if (controlDecoration != null) {
        	controlDecoration.show();
        }
	}

	public void hideErrorDecoration() {
        if (controlDecoration != null) {
        	controlDecoration.hide();
        }
	}
	
	@Override
	public void setText(String text) {
		// check if label text was changed (this happens when RTE is expanded)
		// this is called when RTE is toggled in editor - read new fieldName
		if (updateFieldNameFromLabel(label))
			hideErrorDecoration();
		super.setText(text);
	}
}
