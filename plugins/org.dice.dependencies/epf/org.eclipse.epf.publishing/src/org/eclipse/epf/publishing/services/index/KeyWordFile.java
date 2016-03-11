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

import java.util.List;

//Represents a html file that contains keywords
class KeyWordFile
{
    private String title=null;
    private String url=null;

    private int nextKeyWord=0;
    private List keyWordVector=null;
	//-------------------------------------------------------------------------------------------------------
	// Constructor.
    KeyWordFile(String title, String url, List keyWords) // list of KeyWordDef object
    {
       this.title = title;
       this.url = url;
       this.keyWordVector =  keyWords;
    }
    	//------------------------------------------------------------------------------------------------------
    KeyWordDef getNextKeyWord()
    {
       if(nextKeyWord<keyWordVector.size())
       {
    	   KeyWordDef next = (KeyWordDef)keyWordVector.get(nextKeyWord);
        nextKeyWord++;
        if(next!=null)
        {
          return next;
        }
        else
        {
           return null;
        }
       }
       return null;
    }
    	//------------------------------------------------------------------------------------------------------
    Document getDocument(String keyWord, int noOfDoc)
    {

        if(noOfDoc>1)
        {
           return null;
        }

        return new Document(title, url);
    }
}