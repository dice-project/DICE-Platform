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

class Document
{
    //The title of the document
    private String title=null;

    //The url to the document
    private String url=null;
   // private Anchor anchor=null;

    //The parent KeyWord
    private KeyWord parentM=null;

    Document(String title, String url)
    {
       this.title = title;
       this.url =  url.replace('\\','/');
    }

    void setKeyWord(KeyWord key)
    {
       parentM = key;
    }
    String getTitle()
    {
       return title;
    }

    String getUrl()
    {
    	int first = url.indexOf("#"); //$NON-NLS-1$

        if(parentM!=null && first < 0)
        {
           return url +="#" + parentM.getAnchor(); //$NON-NLS-1$ 
        }

		if (first > 0 && url.indexOf("#", first) > 0) //$NON-NLS-1$
		{
			return url.substring(0, first + url.indexOf("#", first)); //$NON-NLS-1$
		}

       return url;
    }

    void print(OutputStreamWriter outP)
    {
        if(outP==null)
        {
          System.out.println("Document.print\n" + HelpMessages.BAD_OUTPUT_STREAM); //$NON-NLS-1$
          return;
        }
        String totalUrl = url;
        String tmpTitle=null;

           tmpTitle  = title;
           if(parentM!=null)
           {
             totalUrl+="#" + parentM.getAnchor(); //$NON-NLS-1$ 
           }


        if(tmpTitle==null || tmpTitle.equals("")) //$NON-NLS-1$
        {
          tmpTitle = "<FONT COLOR=\"#009933\">HelpMessages.TITLE_ANCHOR_MISSING</FONT>"; //$NON-NLS-1$
        }

        String target=null;
        if(!KeyWordIndexHelper.defObj.getTarget().equals(" ")) //$NON-NLS-1$
        {
           target = " TARGET=\"" + KeyWordIndexHelper.defObj.getTarget() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        }

        MiscStatic.print(outP, "<A HREF=\"" + totalUrl + "\" "); //$NON-NLS-1$ //$NON-NLS-2$
        if(target!=null)
        {
           MiscStatic.print(outP, target);
        }

        MiscStatic.print(outP, ">" + //$NON-NLS-1$
          tmpTitle + "</A>"); //$NON-NLS-1$
    }
}