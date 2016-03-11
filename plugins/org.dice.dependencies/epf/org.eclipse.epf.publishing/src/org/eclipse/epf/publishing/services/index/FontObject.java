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



class FontObject
{
  String name = null;
  String style = null;
  String size = null;
  String styleSheet = null;

  FontObject(String name, String style, String size)
  {
    this.name = name;
    this.style = style;
    this.size = size;
  }

  FontObject(String styleSheet)
  {
    this.styleSheet = styleSheet;
  }

  String getStyleSheet()
  {
    return styleSheet;
  }

  boolean isBold()
  {
    return style.equalsIgnoreCase(Def.Bold);
  }

  boolean isItalic()
  {
    return style.equalsIgnoreCase(Def.Italic);
  }

  String getSize()
  {
    return size;
  }

  String getName()
  {
    return name;
  }

  void print(StringBuffer outP)
  {
    if(styleSheet!=null)
    {
        outP.append(styleSheet);
    }
    else
    {
       outP.append(name + "\t" + style + "\t" + size); //$NON-NLS-1$ //$NON-NLS-2$
    }
    outP.append("\n"); //$NON-NLS-1$
  }
}