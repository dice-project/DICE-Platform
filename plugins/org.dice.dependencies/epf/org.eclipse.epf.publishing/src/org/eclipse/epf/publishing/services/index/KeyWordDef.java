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

/**
 * this class defines a key word
 *
 * @author Jinhua Xi
 * @since  1.0
 */
class KeyWordDef {

	private String anchor;
	private String key;
	private String text;

	private String keyWordString;

	KeyWordDef(String anchor)
	{
		this(anchor, null, null);
	}

	KeyWordDef(String anchor, String key, String text)
	{
		this.anchor = anchor;
		this.key = key;
		this.text = text;
		
		if ( this.text == null )
		{
			this.text = ""; //$NON-NLS-1$
		}

		this.keyWordString = KeyWordStatic.convertKeyWord(this.anchor, this.key, this.text);
		if ( this.key == null || this.key.equals("") ) //$NON-NLS-1$
		{
			int index = this.keyWordString.indexOf(KeyWordIndexHelper.defObj.levelSeparatorReplace);
			if ( index < 0 )
			{
				this.key = this.keyWordString;
			}
			else
			{
				this.key = this.keyWordString.substring(0, index);
			}
		}
	}
	
	String getAnchor()
	{
		return anchor;
	}
	
	String getKey()
	{
		return key;
	}
	
	public String toString()
	{
		return keyWordString;
	}



}

