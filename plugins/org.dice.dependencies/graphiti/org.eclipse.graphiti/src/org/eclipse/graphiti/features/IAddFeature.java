/*******************************************************************************
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
 *
 *******************************************************************************/
/*
 * Created on 12.07.2005
 */
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.func.IAdd;

/**
 * The Interface IAddFeature. A pictogram element has to be added. This
 * pictogram element has to link to a domain model element.
 * 
 * @return instance of a domain model element
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link AbstractAddFeature} instead.
 */
public interface IAddFeature extends IAdd, IFeature {
}