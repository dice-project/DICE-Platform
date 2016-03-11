/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.xml.uma;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iteration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A special Activity which prescribes pre-defined values for its instances for the attributes prefix ('Iteration') and isRepeatable ('True').  It has been included into the meta-model for convenience and to provide a special stereotype, because it represents a very commonly used Activity type.
 * Iteration groups a set of nested Activities that are repeated more than once.  It represents an important structuring element to organize work in repetitive cycles.  The concept of Iteration can be associated with different rules in different methods.  For example, the IBM Rational Unified Process method framework (RUP) defines a rule that Iterations are not allowed to span across Phases.  In contrast IBM Global Services Method (GSMethod) based method frameworks this rule does not apply and Iteration can be defined which nest Phases.  Rules like these, which play an important role for each individual method and are therefore not enforced by this meta-model.  Instead, process authors are expected to follow and check these rules manually.  (Note: Any Breakdown Element can be repeated; however, Iterations has been introduced as a special meta-model concept, because of its important role for many methods.)
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.epf.xml.uma.UmaPackage#getIteration()
 * @model extendedMetaData="name='Iteration' kind='elementOnly'"
 * @generated
 */
public interface Iteration extends Activity {
} // Iteration
