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
package org.eclipse.graphiti.mm.pictograms;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Anchor Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.graphiti.mm.pictograms.AnchorContainer#getAnchors <em>Anchors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getAnchorContainer()
 * @model abstract="true"
 * @generated
 */
public interface AnchorContainer extends PictogramElement {
	/**
	 * Returns the value of the '<em><b>Anchors</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.graphiti.mm.pictograms.Anchor}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.graphiti.mm.pictograms.Anchor#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anchors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anchors</em>' containment reference list.
	 * @see org.eclipse.graphiti.mm.pictograms.PictogramsPackage#getAnchorContainer_Anchors()
	 * @see org.eclipse.graphiti.mm.pictograms.Anchor#getParent
	 * @model opposite="parent" containment="true" resolveProxies="true" ordered="false"
	 * @generated
	 */
	EList<Anchor> getAnchors();

} // AnchorContainer
