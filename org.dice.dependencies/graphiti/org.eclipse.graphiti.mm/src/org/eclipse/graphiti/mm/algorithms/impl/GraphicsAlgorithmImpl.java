/**
 * <copyright>
 * 
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 * 
 * </copyright>
 */
package org.eclipse.graphiti.mm.algorithms.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;

import org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;

import org.eclipse.graphiti.mm.impl.GraphicsAlgorithmContainerImpl;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Graphics Algorithm</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getBackground <em>Background</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getForeground <em>Foreground</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getLineWidth <em>Line Width</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getFilled <em>Filled</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getLineVisible <em>Line Visible</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getRenderingStyle <em>Rendering Style</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getTransparency <em>Transparency</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getGraphicsAlgorithmChildren <em>Graphics Algorithm Children</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getParentGraphicsAlgorithm <em>Parent Graphics Algorithm</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getPictogramElement <em>Pictogram Element</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getX <em>X</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getY <em>Y</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.impl.GraphicsAlgorithmImpl#getStyle <em>Style</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GraphicsAlgorithmImpl extends GraphicsAlgorithmContainerImpl implements GraphicsAlgorithm {
	/**
	 * The cached value of the '{@link #getBackground() <em>Background</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackground()
	 * @generated
	 * @ordered
	 */
	protected Color background;

	/**
	 * The cached value of the '{@link #getForeground() <em>Foreground</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForeground()
	 * @generated
	 * @ordered
	 */
	protected Color foreground;

	/**
	 * The default value of the '{@link #getLineWidth() <em>Line Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineWidth()
	 * @generated
	 * @ordered
	 */
	protected static final Integer LINE_WIDTH_EDEFAULT = new Integer(1);

	/**
	 * The cached value of the '{@link #getLineWidth() <em>Line Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineWidth()
	 * @generated
	 * @ordered
	 */
	protected Integer lineWidth = LINE_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getLineStyle() <em>Line Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineStyle()
	 * @generated
	 * @ordered
	 */
	protected static final LineStyle LINE_STYLE_EDEFAULT = LineStyle.SOLID;

	/**
	 * The cached value of the '{@link #getLineStyle() <em>Line Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineStyle()
	 * @generated
	 * @ordered
	 */
	protected LineStyle lineStyle = LINE_STYLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getFilled() <em>Filled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilled()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FILLED_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getFilled() <em>Filled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilled()
	 * @generated
	 * @ordered
	 */
	protected Boolean filled = FILLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getLineVisible() <em>Line Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineVisible()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean LINE_VISIBLE_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getLineVisible() <em>Line Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineVisible()
	 * @generated
	 * @ordered
	 */
	protected Boolean lineVisible = LINE_VISIBLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRenderingStyle() <em>Rendering Style</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRenderingStyle()
	 * @generated
	 * @ordered
	 */
	protected RenderingStyle renderingStyle;

	/**
	 * The default value of the '{@link #getTransparency() <em>Transparency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransparency()
	 * @generated
	 * @ordered
	 */
	protected static final Double TRANSPARENCY_EDEFAULT = new Double(0.0);

	/**
	 * The cached value of the '{@link #getTransparency() <em>Transparency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransparency()
	 * @generated
	 * @ordered
	 */
	protected Double transparency = TRANSPARENCY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGraphicsAlgorithmChildren() <em>Graphics Algorithm Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGraphicsAlgorithmChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<GraphicsAlgorithm> graphicsAlgorithmChildren;

	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int WIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected int width = WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int HEIGHT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected int height = HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected static final int X_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected int x = X_EDEFAULT;

	/**
	 * The default value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected static final int Y_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected int y = Y_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStyle() <em>Style</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected Style style;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphicsAlgorithmImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AlgorithmsPackage.Literals.GRAPHICS_ALGORITHM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getBackground() {
		if (background != null && background.eIsProxy()) {
			InternalEObject oldBackground = (InternalEObject)background;
			background = (Color)eResolveProxy(oldBackground);
			if (background != oldBackground) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AlgorithmsPackage.GRAPHICS_ALGORITHM__BACKGROUND, oldBackground, background));
			}
		}
		return background;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color basicGetBackground() {
		return background;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBackground(Color newBackground) {
		Color oldBackground = background;
		background = newBackground;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__BACKGROUND, oldBackground, background));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color getForeground() {
		if (foreground != null && foreground.eIsProxy()) {
			InternalEObject oldForeground = (InternalEObject)foreground;
			foreground = (Color)eResolveProxy(oldForeground);
			if (foreground != oldForeground) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AlgorithmsPackage.GRAPHICS_ALGORITHM__FOREGROUND, oldForeground, foreground));
			}
		}
		return foreground;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color basicGetForeground() {
		return foreground;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForeground(Color newForeground) {
		Color oldForeground = foreground;
		foreground = newForeground;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__FOREGROUND, oldForeground, foreground));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getLineWidth() {
		return lineWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineWidth(Integer newLineWidth) {
		Integer oldLineWidth = lineWidth;
		lineWidth = newLineWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_WIDTH, oldLineWidth, lineWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineStyle getLineStyle() {
		return lineStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineStyle(LineStyle newLineStyle) {
		LineStyle oldLineStyle = lineStyle;
		lineStyle = newLineStyle == null ? LINE_STYLE_EDEFAULT : newLineStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_STYLE, oldLineStyle, lineStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFilled() {
		return filled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilled(Boolean newFilled) {
		Boolean oldFilled = filled;
		filled = newFilled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__FILLED, oldFilled, filled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getLineVisible() {
		return lineVisible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineVisible(Boolean newLineVisible) {
		Boolean oldLineVisible = lineVisible;
		lineVisible = newLineVisible;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_VISIBLE, oldLineVisible, lineVisible));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderingStyle getRenderingStyle() {
		if (renderingStyle != null && renderingStyle.eIsProxy()) {
			InternalEObject oldRenderingStyle = (InternalEObject)renderingStyle;
			renderingStyle = (RenderingStyle)eResolveProxy(oldRenderingStyle);
			if (renderingStyle != oldRenderingStyle) {
				InternalEObject newRenderingStyle = (InternalEObject)renderingStyle;
				NotificationChain msgs = oldRenderingStyle.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE, null, null);
				if (newRenderingStyle.eInternalContainer() == null) {
					msgs = newRenderingStyle.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE, oldRenderingStyle, renderingStyle));
			}
		}
		return renderingStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RenderingStyle basicGetRenderingStyle() {
		return renderingStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRenderingStyle(RenderingStyle newRenderingStyle, NotificationChain msgs) {
		RenderingStyle oldRenderingStyle = renderingStyle;
		renderingStyle = newRenderingStyle;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE, oldRenderingStyle, newRenderingStyle);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRenderingStyle(RenderingStyle newRenderingStyle) {
		if (newRenderingStyle != renderingStyle) {
			NotificationChain msgs = null;
			if (renderingStyle != null)
				msgs = ((InternalEObject)renderingStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE, null, msgs);
			if (newRenderingStyle != null)
				msgs = ((InternalEObject)newRenderingStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE, null, msgs);
			msgs = basicSetRenderingStyle(newRenderingStyle, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE, newRenderingStyle, newRenderingStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getTransparency() {
		return transparency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransparency(Double newTransparency) {
		Double oldTransparency = transparency;
		transparency = newTransparency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__TRANSPARENCY, oldTransparency, transparency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GraphicsAlgorithm> getGraphicsAlgorithmChildren() {
		if (graphicsAlgorithmChildren == null) {
			graphicsAlgorithmChildren = new EObjectContainmentWithInverseEList.Resolving<GraphicsAlgorithm>(GraphicsAlgorithm.class, this, AlgorithmsPackage.GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN, AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM);
		}
		return graphicsAlgorithmChildren;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphicsAlgorithm getParentGraphicsAlgorithm() {
		if (eContainerFeatureID() != AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM) return null;
		return (GraphicsAlgorithm)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GraphicsAlgorithm basicGetParentGraphicsAlgorithm() {
		if (eContainerFeatureID() != AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM) return null;
		return (GraphicsAlgorithm)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentGraphicsAlgorithm(GraphicsAlgorithm newParentGraphicsAlgorithm, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParentGraphicsAlgorithm, AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentGraphicsAlgorithm(GraphicsAlgorithm newParentGraphicsAlgorithm) {
		if (newParentGraphicsAlgorithm != eInternalContainer() || (eContainerFeatureID() != AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM && newParentGraphicsAlgorithm != null)) {
			if (EcoreUtil.isAncestor(this, newParentGraphicsAlgorithm))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParentGraphicsAlgorithm != null)
				msgs = ((InternalEObject)newParentGraphicsAlgorithm).eInverseAdd(this, AlgorithmsPackage.GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN, GraphicsAlgorithm.class, msgs);
			msgs = basicSetParentGraphicsAlgorithm(newParentGraphicsAlgorithm, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM, newParentGraphicsAlgorithm, newParentGraphicsAlgorithm));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramElement getPictogramElement() {
		if (eContainerFeatureID() != AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT) return null;
		return (PictogramElement)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PictogramElement basicGetPictogramElement() {
		if (eContainerFeatureID() != AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT) return null;
		return (PictogramElement)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPictogramElement(PictogramElement newPictogramElement, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPictogramElement, AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPictogramElement(PictogramElement newPictogramElement) {
		if (newPictogramElement != eInternalContainer() || (eContainerFeatureID() != AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT && newPictogramElement != null)) {
			if (EcoreUtil.isAncestor(this, newPictogramElement))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPictogramElement != null)
				msgs = ((InternalEObject)newPictogramElement).eInverseAdd(this, PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM, PictogramElement.class, msgs);
			msgs = basicSetPictogramElement(newPictogramElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT, newPictogramElement, newPictogramElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidth(int newWidth) {
		int oldWidth = width;
		width = newWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__WIDTH, oldWidth, width));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeight(int newHeight) {
		int oldHeight = height;
		height = newHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__HEIGHT, oldHeight, height));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getX() {
		return x;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setX(int newX) {
		int oldX = x;
		x = newX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__X, oldX, x));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getY() {
		return y;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setY(int newY) {
		int oldY = y;
		y = newY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__Y, oldY, y));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Style getStyle() {
		if (style != null && style.eIsProxy()) {
			InternalEObject oldStyle = (InternalEObject)style;
			style = (Style)eResolveProxy(oldStyle);
			if (style != oldStyle) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AlgorithmsPackage.GRAPHICS_ALGORITHM__STYLE, oldStyle, style));
			}
		}
		return style;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Style basicGetStyle() {
		return style;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyle(Style newStyle) {
		Style oldStyle = style;
		style = newStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AlgorithmsPackage.GRAPHICS_ALGORITHM__STYLE, oldStyle, style));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGraphicsAlgorithmChildren()).basicAdd(otherEnd, msgs);
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParentGraphicsAlgorithm((GraphicsAlgorithm)otherEnd, msgs);
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPictogramElement((PictogramElement)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE:
				return basicSetRenderingStyle(null, msgs);
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN:
				return ((InternalEList<?>)getGraphicsAlgorithmChildren()).basicRemove(otherEnd, msgs);
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM:
				return basicSetParentGraphicsAlgorithm(null, msgs);
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT:
				return basicSetPictogramElement(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM:
				return eInternalContainer().eInverseRemove(this, AlgorithmsPackage.GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN, GraphicsAlgorithm.class, msgs);
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT:
				return eInternalContainer().eInverseRemove(this, PictogramsPackage.PICTOGRAM_ELEMENT__GRAPHICS_ALGORITHM, PictogramElement.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__BACKGROUND:
				if (resolve) return getBackground();
				return basicGetBackground();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__FOREGROUND:
				if (resolve) return getForeground();
				return basicGetForeground();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_WIDTH:
				return getLineWidth();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_STYLE:
				return getLineStyle();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__FILLED:
				return getFilled();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_VISIBLE:
				return getLineVisible();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE:
				if (resolve) return getRenderingStyle();
				return basicGetRenderingStyle();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__TRANSPARENCY:
				return getTransparency();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN:
				return getGraphicsAlgorithmChildren();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM:
				if (resolve) return getParentGraphicsAlgorithm();
				return basicGetParentGraphicsAlgorithm();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT:
				if (resolve) return getPictogramElement();
				return basicGetPictogramElement();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__WIDTH:
				return getWidth();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__HEIGHT:
				return getHeight();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__X:
				return getX();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__Y:
				return getY();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__STYLE:
				if (resolve) return getStyle();
				return basicGetStyle();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__BACKGROUND:
				setBackground((Color)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__FOREGROUND:
				setForeground((Color)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_WIDTH:
				setLineWidth((Integer)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_STYLE:
				setLineStyle((LineStyle)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__FILLED:
				setFilled((Boolean)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_VISIBLE:
				setLineVisible((Boolean)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE:
				setRenderingStyle((RenderingStyle)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__TRANSPARENCY:
				setTransparency((Double)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN:
				getGraphicsAlgorithmChildren().clear();
				getGraphicsAlgorithmChildren().addAll((Collection<? extends GraphicsAlgorithm>)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM:
				setParentGraphicsAlgorithm((GraphicsAlgorithm)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT:
				setPictogramElement((PictogramElement)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__WIDTH:
				setWidth((Integer)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__HEIGHT:
				setHeight((Integer)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__X:
				setX((Integer)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__Y:
				setY((Integer)newValue);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__STYLE:
				setStyle((Style)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__BACKGROUND:
				setBackground((Color)null);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__FOREGROUND:
				setForeground((Color)null);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_WIDTH:
				setLineWidth(LINE_WIDTH_EDEFAULT);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_STYLE:
				setLineStyle(LINE_STYLE_EDEFAULT);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__FILLED:
				setFilled(FILLED_EDEFAULT);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_VISIBLE:
				setLineVisible(LINE_VISIBLE_EDEFAULT);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE:
				setRenderingStyle((RenderingStyle)null);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__TRANSPARENCY:
				setTransparency(TRANSPARENCY_EDEFAULT);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN:
				getGraphicsAlgorithmChildren().clear();
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM:
				setParentGraphicsAlgorithm((GraphicsAlgorithm)null);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT:
				setPictogramElement((PictogramElement)null);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__X:
				setX(X_EDEFAULT);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__Y:
				setY(Y_EDEFAULT);
				return;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__STYLE:
				setStyle((Style)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__BACKGROUND:
				return background != null;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__FOREGROUND:
				return foreground != null;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_WIDTH:
				return LINE_WIDTH_EDEFAULT == null ? lineWidth != null : !LINE_WIDTH_EDEFAULT.equals(lineWidth);
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_STYLE:
				return lineStyle != LINE_STYLE_EDEFAULT;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__FILLED:
				return FILLED_EDEFAULT == null ? filled != null : !FILLED_EDEFAULT.equals(filled);
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_VISIBLE:
				return LINE_VISIBLE_EDEFAULT == null ? lineVisible != null : !LINE_VISIBLE_EDEFAULT.equals(lineVisible);
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE:
				return renderingStyle != null;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__TRANSPARENCY:
				return TRANSPARENCY_EDEFAULT == null ? transparency != null : !TRANSPARENCY_EDEFAULT.equals(transparency);
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__GRAPHICS_ALGORITHM_CHILDREN:
				return graphicsAlgorithmChildren != null && !graphicsAlgorithmChildren.isEmpty();
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PARENT_GRAPHICS_ALGORITHM:
				return basicGetParentGraphicsAlgorithm() != null;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__PICTOGRAM_ELEMENT:
				return basicGetPictogramElement() != null;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__WIDTH:
				return width != WIDTH_EDEFAULT;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__HEIGHT:
				return height != HEIGHT_EDEFAULT;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__X:
				return x != X_EDEFAULT;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__Y:
				return y != Y_EDEFAULT;
			case AlgorithmsPackage.GRAPHICS_ALGORITHM__STYLE:
				return style != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractStyle.class) {
			switch (derivedFeatureID) {
				case AlgorithmsPackage.GRAPHICS_ALGORITHM__BACKGROUND: return StylesPackage.ABSTRACT_STYLE__BACKGROUND;
				case AlgorithmsPackage.GRAPHICS_ALGORITHM__FOREGROUND: return StylesPackage.ABSTRACT_STYLE__FOREGROUND;
				case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_WIDTH: return StylesPackage.ABSTRACT_STYLE__LINE_WIDTH;
				case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_STYLE: return StylesPackage.ABSTRACT_STYLE__LINE_STYLE;
				case AlgorithmsPackage.GRAPHICS_ALGORITHM__FILLED: return StylesPackage.ABSTRACT_STYLE__FILLED;
				case AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_VISIBLE: return StylesPackage.ABSTRACT_STYLE__LINE_VISIBLE;
				case AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE: return StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE;
				case AlgorithmsPackage.GRAPHICS_ALGORITHM__TRANSPARENCY: return StylesPackage.ABSTRACT_STYLE__TRANSPARENCY;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AbstractStyle.class) {
			switch (baseFeatureID) {
				case StylesPackage.ABSTRACT_STYLE__BACKGROUND: return AlgorithmsPackage.GRAPHICS_ALGORITHM__BACKGROUND;
				case StylesPackage.ABSTRACT_STYLE__FOREGROUND: return AlgorithmsPackage.GRAPHICS_ALGORITHM__FOREGROUND;
				case StylesPackage.ABSTRACT_STYLE__LINE_WIDTH: return AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_WIDTH;
				case StylesPackage.ABSTRACT_STYLE__LINE_STYLE: return AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_STYLE;
				case StylesPackage.ABSTRACT_STYLE__FILLED: return AlgorithmsPackage.GRAPHICS_ALGORITHM__FILLED;
				case StylesPackage.ABSTRACT_STYLE__LINE_VISIBLE: return AlgorithmsPackage.GRAPHICS_ALGORITHM__LINE_VISIBLE;
				case StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE: return AlgorithmsPackage.GRAPHICS_ALGORITHM__RENDERING_STYLE;
				case StylesPackage.ABSTRACT_STYLE__TRANSPARENCY: return AlgorithmsPackage.GRAPHICS_ALGORITHM__TRANSPARENCY;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (lineWidth: ");
		result.append(lineWidth);
		result.append(", lineStyle: ");
		result.append(lineStyle);
		result.append(", filled: ");
		result.append(filled);
		result.append(", lineVisible: ");
		result.append(lineVisible);
		result.append(", transparency: ");
		result.append(transparency);
		result.append(", width: ");
		result.append(width);
		result.append(", height: ");
		result.append(height);
		result.append(", x: ");
		result.append(x);
		result.append(", y: ");
		result.append(y);
		result.append(')');
		return result.toString();
	}

} //GraphicsAlgorithmImpl
