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
/**
 * 
 */
package org.eclipse.epf.diagram.core.util;


import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Translatable;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.core.bridge.ActivityDiagramAdapter;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.figures.WidenedWrapLabel;
import org.eclipse.epf.diagram.core.part.DiagramEditorInput;
import org.eclipse.epf.diagram.core.part.IDiagramEditorInputProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeTypes;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.uml2.uml.ActivityNode;

import com.ibm.icu.text.BreakIterator;


/**
 * Utility class to be used for EPF diagram
 * 
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @author Shashidhar Kannoori
 * 
 * @since 1.2
 */
public final class DiagramCoreUtil {
	

	/**
	 * Set label properties for the given label figure
	 * @param fig_0
	 */
	public static void setLabelProperties(WrapLabel labelFigure){
		labelFigure.setFont(DiagramConstants.DEFAULT_FONT);
		labelFigure.setIconAlignment(PositionConstants.TOP);
		labelFigure.setLabelAlignment(PositionConstants.TOP);
		labelFigure.setTextAlignment(PositionConstants.TOP);
		labelFigure.setTextPlacement(PositionConstants.SOUTH);
		labelFigure.setTextWrap(true);
		labelFigure.setTextWrapAlignment(PositionConstants.CENTER);
		
	}
	
	/**
	 * Set the default font for this preference store.
	 * @param store IPreferenceStore
	 */
	public static void setDefaultFontPreference(IPreferenceStore store){
		FontData fontDataArray[] =
            JFaceResources.getDefaultFont().getFontData();
        FontData fontData = fontDataArray[0];
        fontData.setHeight(DiagramConstants.DEFAULT_FONT_SIZE);
        fontData.setName(DiagramConstants.DEFAULT_FONT_NAME);
        PreferenceConverter.setDefault(
            store,
            IPreferenceConstants.PREF_DEFAULT_FONT,
            fontData);	
	}
	
	public static String validStringForBreakdownElements(EditPart part, String txt){
		Object obj = part.getModel();
		if (obj instanceof Node) {
			Node node = (Node) obj;
			EObject umlE = node.getElement();
			Object e = BridgeHelper.getMethodElement((EModelElement)umlE);
			EObject umlA = node.getDiagram().getElement();
			ActivityDiagramAdapter adapter = (ActivityDiagramAdapter)BridgeHelper.getNodeAdapter(umlA);
			if(adapter != null){
				if (e instanceof Activity) {
					return TngUtil.checkWBSActivityPresentationName(e, txt, adapter.getSuppression());
				}
				else {
					return TngUtil
						.checkWorkBreakdownElementPresentationName(e,
								txt, adapter.getSuppression());
				}
			}
		}
		return null;
	}
	
	/**
	 * Gets font color for the given edit part. The color is depending on the state (suppression, read-only...)
	 * of the semantic model element.
	 * 
	 * @param view
	 * @return
	 */
	public static Color getFontColor(AbstractEditPart editPart) {
		Object object = null;
		
		// By default publishing mode is false. If publish mode is set return black color.
		if(LibraryUtil.PUBLISH_MODE){
			return null;
		}
		
		EditPartViewer viewer = editPart.getViewer();
		if(viewer instanceof IDiagramEditorInputProvider) {
			DiagramEditorInput input = ((IDiagramEditorInputProvider)viewer).getDiagramEditorInput();
			// use the following commented out code only if suppressed elements should be shown in gray
			//
//			if(input != null && input.getWrapper() != null &&
//					input.getWrapper().isInherited()) {
//				Object model = editPart.getModel();
//				if(model instanceof View) {
//					Collection children = null;
//					Diagram diagram = getDiagramEditPart(editPart).getDiagramView();
//					String type = diagram.getType();
//					if(DiagramManager.AD_kind.equals(type)) {
//						DiagramAdapter da = ((DiagramAdapter)BridgeHelper.getNodeAdapter(diagram.getElement()));
//						if(da != null) {
//							ActivityDiagramAdapter adapter = new ActivityDiagramAdapter(input.getWrapper());
//							adapter.setFilter(da.getFilter());
//							adapter.setSuppression(da.getSuppression());
//							children = adapter.getChildren();
//						}
//					}
//					else if(DiagramManager.ADD_kind.equals(type) || DiagramManager.WPD_kind.equals(type)) {
//						DiagramImpl d;
//						if(DiagramManager.ADD_kind.equals(type)) {
//							d = (DiagramImpl) ModelFactory.eINSTANCE.createActivityDetailDiagram();
//						}
//						else {
//							d = (DiagramImpl) ModelFactory.eINSTANCE.createWorkProductDependencyDiagram();
//						}						
//						DiagramImpl diagramInUse = ((DiagramImpl)diagram.getElement());
//						Suppression suppression = diagramInUse.getSuppression();
//						if(suppression != null) {
//							d.setWrapper(input.getWrapper());
//							d.setFilter(diagramInUse.getFilter());
//							d.setSuppression(diagramInUse.getSuppression());
//							children = d.getChildren();
//						}
//					}
//					if(children != null) {
//						MethodElement e = BridgeHelper.getMethodElement((View) model);
//						find_wrapper:
//							for (Iterator iter = children.iterator(); iter.hasNext();) {
//								Object child = (Object) iter.next();
//								if(TngUtil.unwrap(child) == e) {
//									object = child;
//									break find_wrapper;
//								}
//							}
//						if(object != null) {
//							org.eclipse.epf.uma.Process proc = TngUtil.getOwningProcess(input.getWrapper());
//							if(Suppression.getSuppression(proc).isSuppressed(object)) {
//								return DiagramConstants.SUPPRESSED_FONT_COLOR;
//							}
//							else {
//								return DiagramConstants.READ_ONLY_FONT_COLOR;
//							}
//						}
//					}
//				}
//			}
			
			if(input != null && input.getWrapper() != null) {
				return DiagramConstants.READ_ONLY_FONT_COLOR;
			}
		}
		
		Object model = editPart.getModel();
		if(model instanceof View) {
			return DiagramCoreUtil.getFontColor((View) model);
		}
	
		return null;
	}
	
	/**
	 * Gets font color for the given view. The color is depending on the state (suppression, read-only...)
	 * of the semantic model element.
	 * 
	 * @param view
	 * @return
	 */
	private static Color getFontColor(View view) {
//		if (BridgeHelper.isSuppressed(view)) {
//			return DiagramConstants.SUPPRESSED_FONT_COLOR;
//		}
		if (BridgeHelper.isReadOnly(view)) {
			return DiagramConstants.READ_ONLY_FONT_COLOR;
		}
//		else {
//			return DiagramConstants.DEFAULT_FONT_COLOR;
//		}
		return null;
	}
	
	public static DiagramEditPart getDiagramEditPart(EditPart editPart) {
		for(;editPart != null && !(editPart instanceof DiagramEditPart); editPart = editPart.getParent());
		return (DiagramEditPart) editPart;
	}
	
	public static boolean isReadOnly(AbstractEditPart editPart){
		Object model = editPart.getModel();
		if(model instanceof View) {
			return BridgeHelper.isReadOnly((View)model);
		}
		return false;
	}
	
	public static boolean isSyncBar(EditPart part){
		if(part instanceof IGraphicalEditPart){
			View node = (View)part.getModel();
			Object obj = node.getElement();
			if(obj instanceof ActivityNode){
				return BridgeHelper.isSynchBar((ActivityNode)obj);
			}
		}
		return false;
	}
	
	public static String wrap(String s, int wrapWidth) {
		String wrapped = ""; //$NON-NLS-1$
		BreakIterator bi = BreakIterator.getLineInstance();
		String remaining = s;
		while (true) {
			if (remaining.length() <= wrapWidth) {
				wrapped += remaining;
				break;
			} else {
				bi.setText(remaining);
				int pos = bi.following(wrapWidth);
				if (pos == BreakIterator.DONE) {
					wrapped += remaining;
					break;
				} else {
					if (pos >= remaining.length()) {
						wrapped += remaining;
						break;
					} else {
						wrapped += remaining.substring(0, pos) + "\n"; //$NON-NLS-1$
						remaining = remaining.substring(pos);
					}
				}
			}
		}
		return wrapped;
	}
	
	/**
	 * Set the default font for this preference store.
	 * @param store IPreferenceStore
	 */
	public static void setDefaultLineStyle(IPreferenceStore store){
        PreferenceConverter.setDefault(
            store,
            IPreferenceConstants.PREF_LINE_COLOR,
            PreferenceConverter.COLOR_DEFAULT_DEFAULT);	
	}
	
	/**
	 * Hide default connection handles in AD, ADD, WPDD.
	 * 
	 * @param store
	 *            IPreferenceStore
	 */
	public static void hideConnectionHandles(IPreferenceStore store) {
		store.setDefault(IPreferenceConstants.PREF_SHOW_CONNECTION_HANDLES,
				false);
	}
	
	/**
	 * Return text height based on text, font and width of boundries text is in
	 * 
	 * @param text
	 * @param fontStyle
	 * @param width
	 * @return
	 */
	public static int getTextHeight(String text, FontStyle fontStyle, int width) {
		Font font = null;
		String fontName = null;
		int fontHeight = 0;
		
		if (fontStyle != null) {
			fontName = fontStyle.getFontName();
			fontHeight = fontStyle.getFontHeight();
			if (fontName == null)
				fontName = DiagramConstants.getFontName();
			if (fontHeight == 0)
				fontHeight = DiagramConstants.DEFAULT_FONT_SIZE;
			
			font = new Font(null, fontName, fontHeight, java.awt.Font.PLAIN);
		}
		else 
			font = DiagramConstants.DEFAULT_FONT;

		Dimension d;
		try {
			d = FigureUtilities.getTextExtents(text, font);
		}
		finally {
			if(font != null && font != DiagramConstants.DEFAULT_FONT) {
				font.dispose();
			}
		}

		int noOfLines = 0;
		if (d != null && d.width > 0) {
			int div = d.width / width;
			int mod = d.width % width;
			if (mod != 0)
				div++;
			noOfLines = div;
			return noOfLines * fontHeight;
		}
		return 0;
	}

	public static Dimension getTextSizeInWrapLabel(String text, Font f, int wHint, int hHint, IMapMode mapMode) {
		class MyWrapLabel extends WidenedWrapLabel implements IMapMode {
			private IMapMode mapMode;

			public MyWrapLabel(IMapMode mapMode) {
				this.mapMode = mapMode != null ? mapMode : MapModeTypes.IDENTITY_MM;
			}

			public int DPtoLP(int deviceUnit) {
				return mapMode.DPtoLP(deviceUnit);
			}

			public Translatable DPtoLP(Translatable t) {
				return mapMode.DPtoLP(t);
			}

			public int LPtoDP(int logicalUnit) {
				return mapMode.LPtoDP(logicalUnit);
			}

			public Translatable LPtoDP(Translatable t) {
				return mapMode.LPtoDP(t);
			}
			
		};
		MyWrapLabel label = new MyWrapLabel(mapMode);
		DiagramCoreUtil.setLabelProperties(label);		
		label.setText(text);
		if(f != null) {
			label.setFont(f);
		}
		return label.getTextSize(wHint, hHint);
	}
	
	public static Color getLinkOverrideColor(Edge edge) {
		return !LibraryUtil.PUBLISH_MODE && DiagramCoreValidation.isReadOnly(edge)? 
				DiagramConstants.READ_ONLY_FONT_COLOR : null;
	}
}
