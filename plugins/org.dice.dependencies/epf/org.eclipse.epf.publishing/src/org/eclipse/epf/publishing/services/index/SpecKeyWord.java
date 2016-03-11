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

import java.io.OutputStreamWriter;

//A keyword defined in a separate file
//This keyword reffers to a keyword defined in a html file
class SpecKeyWord
{
    private String relatedKeyWord=null;
    private boolean see=true;

    SpecKeyWord(String relatedKeyWord, boolean mode)
    {
        if(relatedKeyWord==null)
        {
           System.err.println("SpecKeyWord:SpecKeyWord\n" + HelpMessages.INPUT_PARAMETER_NULL); //$NON-NLS-1$
        }
       this.relatedKeyWord = relatedKeyWord;
       this.see = mode;
    }

    //Prints the keyword
    void print(OutputStreamWriter outP)
    {
      if(outP==null)
      {
         System.err.println("SpecKeyWord:print\n" + HelpMessages.BAD_OUTPUT_STREAM); //$NON-NLS-1$
         return;
      }
      if(relatedKeyWord!=null)
      {
       if(see)
       {
         MiscStatic.print(outP, KeyWordIndexHelper.defObj.getSee());
       }
       else
       {
         MiscStatic.print(outP, KeyWordIndexHelper.defObj.getSeeAlso());
       }
        MiscStatic.print(outP, "<A HREF=\"#" + //$NON-NLS-1$
          relatedKeyWord + "\">" + relatedKeyWord + "</A>\n"); //$NON-NLS-1$ //$NON-NLS-2$
      }
    }

    boolean isSee()
    {
       return see;
    }
}