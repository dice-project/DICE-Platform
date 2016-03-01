//------------------------------------------------------------------------------
// Copyright (c) 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.util;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.uma.MethodElement;

/**
 * This interface, in conjunction with the extension point
 * elementPropertyProviders, provides a mechanism for exposure of custom
 * attributes of MethodElement to the XSLT templates during a publish of the
 * method library.
 * 
 * @author Pierre Padovani
 * 
 */
public interface IElementPropertyProvider extends IExecutableExtension {

  
  /**
   * Implementors must return the name of the child XmlElement they wish to have created
   * against the MethodElement being processed. This name will be used to create the child
   * XmlElement before a subsequent call to publishMethodElementProperties. If no properties
   * for the element exist or have been defined, return null.
   * 
   * @param element
   *          MethodElement
   * @return String
   */
  public String getChildElementName(MethodElement element);
  
  /**
   * Implementors of this method will be given a child XmlElement that can be filled with
   * any data desired. All data contained in this XmlElement will be exposed to the XSLT 
   * during a publish.
   * 
   * @param element
   *          MethodElement
   * @param data
   *          XmlElement
   */
  public void publishMethodElementProperties(MethodElement element, XmlElement data);
  
}
