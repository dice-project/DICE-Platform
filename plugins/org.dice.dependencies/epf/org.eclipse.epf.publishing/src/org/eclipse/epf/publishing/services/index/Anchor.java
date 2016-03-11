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
package org.eclipse.epf.publishing.services.index;

public class Anchor
{
   private String text=null;
   private String anchor=null;

   Anchor(String text, String anchor)
   {
      this.text = text;
      this.anchor = anchor;
   }

   String getAnchor()
   {
      return anchor;
   }

   String getText()
   {
      return text;
   }
}