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
package org.eclipse.graphiti.mm.algorithms.styles.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.RenderingStyle;
import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl#getBackground <em>Background</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl#getForeground <em>Foreground</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl#getLineWidth <em>Line Width</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl#getLineStyle <em>Line Style</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl#getFilled <em>Filled</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl#getLineVisible <em>Line Visible</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl#getRenderingStyle <em>Rendering Style</em>}</li>
 *   <li>{@link org.eclipse.graphiti.mm.algorithms.styles.impl.AbstractStyleImpl#getTransparency <em>Transparency</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractStyleImpl extends EObjectImpl implements AbstractStyle {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractStyleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StylesPackage.Literals.ABSTRACT_STYLE;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylesPackage.ABSTRACT_STYLE__BACKGROUND, oldBackground, background));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.ABSTRACT_STYLE__BACKGROUND, oldBackground, background));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylesPackage.ABSTRACT_STYLE__FOREGROUND, oldForeground, foreground));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.ABSTRACT_STYLE__FOREGROUND, oldForeground, foreground));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.ABSTRACT_STYLE__LINE_WIDTH, oldLineWidth, lineWidth));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.ABSTRACT_STYLE__LINE_STYLE, oldLineStyle, lineStyle));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.ABSTRACT_STYLE__FILLED, oldFilled, filled));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.ABSTRACT_STYLE__LINE_VISIBLE, oldLineVisible, lineVisible));
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
				NotificationChain msgs = oldRenderingStyle.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE, null, null);
				if (newRenderingStyle.eInternalContainer() == null) {
					msgs = newRenderingStyle.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE, oldRenderingStyle, renderingStyle));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE, oldRenderingStyle, newRenderingStyle);
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
				msgs = ((InternalEObject)renderingStyle).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE, null, msgs);
			if (newRenderingStyle != null)
				msgs = ((InternalEObject)newRenderingStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE, null, msgs);
			msgs = basicSetRenderingStyle(newRenderingStyle, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE, newRenderingStyle, newRenderingStyle));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StylesPackage.ABSTRACT_STYLE__TRANSPARENCY, oldTransparency, transparency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE:
				return basicSetRenderingStyle(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StylesPackage.ABSTRACT_STYLE__BACKGROUND:
				if (resolve) return getBackground();
				return basicGetBackground();
			case StylesPackage.ABSTRACT_STYLE__FOREGROUND:
				if (resolve) return getForeground();
				return basicGetForeground();
			case StylesPackage.ABSTRACT_STYLE__LINE_WIDTH:
				return getLineWidth();
			case StylesPackage.ABSTRACT_STYLE__LINE_STYLE:
				return getLineStyle();
			case StylesPackage.ABSTRACT_STYLE__FILLED:
				return getFilled();
			case StylesPackage.ABSTRACT_STYLE__LINE_VISIBLE:
				return getLineVisible();
			case StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE:
				if (resolve) return getRenderingStyle();
				return basicGetRenderingStyle();
			case StylesPackage.ABSTRACT_STYLE__TRANSPARENCY:
				return getTransparency();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case StylesPackage.ABSTRACT_STYLE__BACKGROUND:
				setBackground((Color)newValue);
				return;
			case StylesPackage.ABSTRACT_STYLE__FOREGROUND:
				setForeground((Color)newValue);
				return;
			case StylesPackage.ABSTRACT_STYLE__LINE_WIDTH:
				setLineWidth((Integer)newValue);
				return;
			case StylesPackage.ABSTRACT_STYLE__LINE_STYLE:
				setLineStyle((LineStyle)newValue);
				return;
			case StylesPackage.ABSTRACT_STYLE__FILLED:
				setFilled((Boolean)newValue);
				return;
			case StylesPackage.ABSTRACT_STYLE__LINE_VISIBLE:
				setLineVisible((Boolean)newValue);
				return;
			case StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE:
				setRenderingStyle((RenderingStyle)newValue);
				return;
			case StylesPackage.ABSTRACT_STYLE__TRANSPARENCY:
				setTransparency((Double)newValue);
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
			case StylesPackage.ABSTRACT_STYLE__BACKGROUND:
				setBackground((Color)null);
				return;
			case StylesPackage.ABSTRACT_STYLE__FOREGROUND:
				setForeground((Color)null);
				return;
			case StylesPackage.ABSTRACT_STYLE__LINE_WIDTH:
				setLineWidth(LINE_WIDTH_EDEFAULT);
				return;
			case StylesPackage.ABSTRACT_STYLE__LINE_STYLE:
				setLineStyle(LINE_STYLE_EDEFAULT);
				return;
			case StylesPackage.ABSTRACT_STYLE__FILLED:
				setFilled(FILLED_EDEFAULT);
				return;
			case StylesPackage.ABSTRACT_STYLE__LINE_VISIBLE:
				setLineVisible(LINE_VISIBLE_EDEFAULT);
				return;
			case StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE:
				setRenderingStyle((RenderingStyle)null);
				return;
			case StylesPackage.ABSTRACT_STYLE__TRANSPARENCY:
				setTransparency(TRANSPARENCY_EDEFAULT);
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
			case StylesPackage.ABSTRACT_STYLE__BACKGROUND:
				return background != null;
			case StylesPackage.ABSTRACT_STYLE__FOREGROUND:
				return foreground != null;
			case StylesPackage.ABSTRACT_STYLE__LINE_WIDTH:
				return LINE_WIDTH_EDEFAULT == null ? lineWidth != null : !LINE_WIDTH_EDEFAULT.equals(lineWidth);
			case StylesPackage.ABSTRACT_STYLE__LINE_STYLE:
				return lineStyle != LINE_STYLE_EDEFAULT;
			case StylesPackage.ABSTRACT_STYLE__FILLED:
				return FILLED_EDEFAULT == null ? filled != null : !FILLED_EDEFAULT.equals(filled);
			case StylesPackage.ABSTRACT_STYLE__LINE_VISIBLE:
				return LINE_VISIBLE_EDEFAULT == null ? lineVisible != null : !LINE_VISIBLE_EDEFAULT.equals(lineVisible);
			case StylesPackage.ABSTRACT_STYLE__RENDERING_STYLE:
				return renderingStyle != null;
			case StylesPackage.ABSTRACT_STYLE__TRANSPARENCY:
				return TRANSPARENCY_EDEFAULT == null ? transparency != null : !TRANSPARENCY_EDEFAULT.equals(transparency);
		}
		return super.eIsSet(featureID);
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
		result.append(')');
		return result.toString();
	}

} //AbstractStyleImpl
