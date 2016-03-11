/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
package org.eclipse.epf.richtext;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * Shared images used by the default rich text editor.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class RichTextImages {

	private static final String ETOOL16 = "full/etool16/"; //$NON-NLS-1$

	private static final String DTOOL16 = "full/dtool16/"; //$NON-NLS-1$	

	private static final RichTextPlugin richTextPlugin = RichTextPlugin
			.getDefault();

	public static final String IMG_PATH_ADD_IMAGE = ETOOL16 + "AddImage.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_ADD_LINE = ETOOL16 + "AddLine.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_ADD_LINK = ETOOL16 + "AddLink.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_ADD_ORDERED_LIST = ETOOL16
			+ "AddOrderedList.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_ADD_TABLE = ETOOL16 + "AddTable.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_ADD_UNORDERED_LIST = ETOOL16
			+ "AddUnorderedList.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_BOLD = ETOOL16 + "Bold.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_CLEAR_CONTENT = ETOOL16
			+ "ClearContent.gif"; //$NON-NLS-1$	

	public static final String IMG_PATH_COPY = ETOOL16 + "Copy.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_CUT = ETOOL16 + "Cut.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_FIND_REPLACE = ETOOL16
			+ "FindReplace.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_INDENT = ETOOL16 + "Indent.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_ITALIC = ETOOL16 + "Italic.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_JUSTIFY_CENTER = ETOOL16
			+ "JustifyCenter.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_JUSTIFY_FULL = ETOOL16
			+ "JustifyFull.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_JUSTIFY_LEFT = ETOOL16
			+ "JustifyLeft.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_JUSTIFY_RIGHT = ETOOL16
			+ "JustifyRight.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_OUTDENT = ETOOL16 + "Outdent.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_PASTE = ETOOL16 + "Paste.gif"; //$NON-NLS-1$
	
	public static final String IMG_PATH_PASTE_PLAIN_TEXT = ETOOL16 + "PastePlainText.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_STRIKE_THROUGH = ETOOL16
			+ "StrikeThrough.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_SUBSCRIPT = ETOOL16 + "Subscript.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_SUPERSCRIPT = ETOOL16
			+ "Superscript.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_UNDERLINE = ETOOL16 + "Underline.gif"; //$NON-NLS-1$

	public static final String IMG_PATH_TIDY = ETOOL16 + "TidyHTML.gif"; //$NON-NLS-1$
	
	public static final String IMG_PATH_ADD_CODE = ETOOL16 + "AddCode.gif"; //$NON-NLS-1$

	public static final Image IMG_ADD_IMAGE = richTextPlugin
			.getSharedImage(IMG_PATH_ADD_IMAGE);

	public static final Image IMG_ADD_LINE = richTextPlugin
			.getSharedImage(IMG_PATH_ADD_LINE);

	public static final Image IMG_ADD_LINK = richTextPlugin
			.getSharedImage(IMG_PATH_ADD_LINK);

	public static final Image IMG_ADD_ORDERED_LIST = richTextPlugin
			.getSharedImage(IMG_PATH_ADD_ORDERED_LIST);

	public static final Image IMG_ADD_TABLE = richTextPlugin
			.getSharedImage(IMG_PATH_ADD_TABLE);

	public static final Image IMG_ADD_UNORDERED_LIST = richTextPlugin
			.getSharedImage(IMG_PATH_ADD_UNORDERED_LIST);

	public static final Image IMG_BOLD = richTextPlugin
			.getSharedImage(IMG_PATH_BOLD);

	public static final Image IMG_CLEAR_CONTENT = richTextPlugin
			.getSharedImage(IMG_PATH_CLEAR_CONTENT);

	public static final Image IMG_COPY = richTextPlugin
			.getSharedImage(IMG_PATH_COPY);

	public static final Image IMG_CUT = richTextPlugin
			.getSharedImage(IMG_PATH_CUT);

	public static final Image IMG_FIND_REPLACE = richTextPlugin
			.getSharedImage(IMG_PATH_FIND_REPLACE);

	public static final Image IMG_INDENT = richTextPlugin
			.getSharedImage(IMG_PATH_INDENT);

	public static final Image IMG_ITALIC = richTextPlugin
			.getSharedImage(IMG_PATH_ITALIC);

	public static final Image IMG_JUSTIFY_CENTER = richTextPlugin
			.getSharedImage(IMG_PATH_JUSTIFY_CENTER);

	public static final Image IMG_JUSTIFY_FULL = richTextPlugin
			.getSharedImage(IMG_PATH_JUSTIFY_FULL);

	public static final Image IMG_JUSTIFY_LEFT = richTextPlugin
			.getSharedImage(IMG_PATH_JUSTIFY_LEFT);

	public static final Image IMG_JUSTIFY_RIGHT = richTextPlugin
			.getSharedImage(IMG_PATH_JUSTIFY_RIGHT);

	public static final Image IMG_OUTDENT = richTextPlugin
			.getSharedImage(IMG_PATH_OUTDENT);

	public static final Image IMG_PASTE = richTextPlugin
			.getSharedImage(IMG_PATH_PASTE);
	
	public static final Image IMG_PASTE_PLAIN_TEXT = richTextPlugin
			.getSharedImage(IMG_PATH_PASTE_PLAIN_TEXT);	

	public static final Image IMG_STRIKE_THROUGH = richTextPlugin
			.getSharedImage(IMG_PATH_STRIKE_THROUGH);

	public static final Image IMG_SUBSCRIPT = richTextPlugin
			.getSharedImage(IMG_PATH_SUBSCRIPT);

	public static final Image IMG_SUPERSCRIPT = richTextPlugin
			.getSharedImage(IMG_PATH_SUPERSCRIPT);

	public static final Image IMG_UNDERLINE = richTextPlugin
			.getSharedImage(IMG_PATH_UNDERLINE);
	public static final Image IMG_TIDY = richTextPlugin
			.getSharedImage(IMG_PATH_TIDY);
	
	public static final ImageDescriptor IMG_DESC_ADD_IMAGE = richTextPlugin
			.getImageDescriptor(IMG_PATH_ADD_IMAGE);

	public static final ImageDescriptor IMG_DESC_ADD_LINE = richTextPlugin
			.getImageDescriptor(IMG_PATH_ADD_LINE);

	public static final ImageDescriptor IMG_DESC_ADD_LINK = richTextPlugin
			.getImageDescriptor(IMG_PATH_ADD_LINK);

	public static final ImageDescriptor IMG_DESC_ADD_ORDERED_LIST = richTextPlugin
			.getImageDescriptor(IMG_PATH_ADD_ORDERED_LIST);

	public static final ImageDescriptor IMG_DESC_ADD_TABLE = richTextPlugin
			.getImageDescriptor(IMG_PATH_ADD_TABLE);

	public static final ImageDescriptor IMG_DESC_ADD_UNORDERED_LIST = richTextPlugin
			.getImageDescriptor(IMG_PATH_ADD_UNORDERED_LIST);

	public static final ImageDescriptor IMG_DESC_BOLD = richTextPlugin
			.getImageDescriptor(IMG_PATH_BOLD);

	public static final ImageDescriptor IMG_DESC_CLEAR_CONTENT = richTextPlugin
			.getImageDescriptor(IMG_PATH_CLEAR_CONTENT);

	public static final ImageDescriptor IMG_DESC_COPY = richTextPlugin
			.getImageDescriptor(IMG_PATH_COPY);

	public static final ImageDescriptor IMG_DESC_CUT = richTextPlugin
			.getImageDescriptor(IMG_PATH_CUT);

	public static final ImageDescriptor IMG_DESC_FIND_REPLACE = richTextPlugin
			.getImageDescriptor(IMG_PATH_FIND_REPLACE);

	public static final ImageDescriptor IMG_DESC_INDENT = richTextPlugin
			.getImageDescriptor(IMG_PATH_INDENT);

	public static final ImageDescriptor IMG_DESC_ITALIC = richTextPlugin
			.getImageDescriptor(IMG_PATH_ITALIC);

	public static final ImageDescriptor IMG_DESC_JUSTIFY_CENTER = richTextPlugin
			.getImageDescriptor(IMG_PATH_JUSTIFY_CENTER);

	public static final ImageDescriptor IMG_DESC_JUSTIFY_FULL = richTextPlugin
			.getImageDescriptor(IMG_PATH_JUSTIFY_FULL);

	public static final ImageDescriptor IMG_DESC_JUSTIFY_LEFT = richTextPlugin
			.getImageDescriptor(IMG_PATH_JUSTIFY_LEFT);

	public static final ImageDescriptor IMG_DESC_JUSTIFY_RIGHT = richTextPlugin
			.getImageDescriptor(IMG_PATH_JUSTIFY_RIGHT);

	public static final ImageDescriptor IMG_DESC_OUTDENT = richTextPlugin
			.getImageDescriptor(IMG_PATH_OUTDENT);

	public static final ImageDescriptor IMG_DESC_PASTE = richTextPlugin
			.getImageDescriptor(IMG_PATH_PASTE);

	public static final ImageDescriptor IMG_DESC_PASTE_PLAIN_TEXT = richTextPlugin
			.getImageDescriptor(IMG_PATH_PASTE_PLAIN_TEXT);

	public static final ImageDescriptor IMG_DESC_STRIKE_THROUGH = richTextPlugin
			.getImageDescriptor(IMG_PATH_STRIKE_THROUGH);

	public static final ImageDescriptor IMG_DESC_SUBSCRIPT = richTextPlugin
			.getImageDescriptor(IMG_PATH_SUBSCRIPT);

	public static final ImageDescriptor IMG_DESC_SUPERSCRIPT = richTextPlugin
			.getImageDescriptor(IMG_PATH_SUPERSCRIPT);

	public static final ImageDescriptor IMG_DESC_UNDERLINE = richTextPlugin
			.getImageDescriptor(IMG_PATH_UNDERLINE);

	public static final ImageDescriptor IMG_DESC_TIDY = richTextPlugin
			.getImageDescriptor(IMG_PATH_TIDY);
	
	public static final ImageDescriptor IMG_DESC_ADD_CODE = richTextPlugin
			.getImageDescriptor(IMG_PATH_ADD_CODE);
	

	public static final String DISABLED_IMG_PATH_ADD_IMAGE = DTOOL16
			+ "AddImage.gif"; //$NON-NLS-1$

//	public static final String DISABLED_IMG_PATH_ADD_LINE = DTOOL16
//			+ "AddLine.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_ADD_LINK = DTOOL16
			+ "AddLink.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_ADD_ORDERED_LIST = DTOOL16
			+ "AddOrderedList.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_ADD_TABLE = DTOOL16
			+ "AddTable.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_ADD_UNORDERED_LIST = DTOOL16
			+ "AddUnorderedList.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_BOLD = DTOOL16 + "Bold.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_CLEAR_CONTENT = DTOOL16
			+ "ClearContent.gif"; //$NON-NLS-1$	

	public static final String DISABLED_IMG_PATH_COPY = DTOOL16 + "Copy.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_CUT = DTOOL16 + "Cut.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_FIND_REPLACE = DTOOL16
			+ "FindReplace.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_INDENT = DTOOL16
			+ "Indent.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_ITALIC = DTOOL16
			+ "Italic.gif"; //$NON-NLS-1$

//	public static final String DISABLED_IMG_PATH_JUSTIFY_CENTER = DTOOL16
//			+ "JustifyCenter.gif"; //$NON-NLS-1$
//
//	public static final String DISABLED_IMG_PATH_JUSTIFY_FULL = DTOOL16
//			+ "JustifyFull.gif"; //$NON-NLS-1$
//
//	public static final String DISABLED_IMG_PATH_JUSTIFY_LEFT = DTOOL16
//			+ "JustifyLeft.gif"; //$NON-NLS-1$
//
//	public static final String DISABLED_IMG_PATH_JUSTIFY_RIGHT = DTOOL16
//			+ "JustifyRight.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_OUTDENT = DTOOL16
			+ "Outdent.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_PASTE = DTOOL16 + "Paste.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_PASTE_PLAIN_TEXT = DTOOL16 + "PastePlainText.gif"; //$NON-NLS-1$

//	public static final String DISABLED_IMG_PATH_STRIKE_THROUGH = DTOOL16
//			+ "StrikeThrough.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_SUBSCRIPT = DTOOL16
			+ "Subscript.gif"; //$NON-NLS-1$

	public static final String DISABLED_IMG_PATH_SUPERSCRIPT = DTOOL16
			+ "Superscript.gif"; //$NON-NLS-1$

	private static String DISABLED_IMG_PATH_TIDY = DTOOL16 + "TidyHTML.gif"; //$NON-NLS-1$
		
	public static final String DISABLED_IMG_PATH_UNDERLINE = DTOOL16
			+ "Underline.gif"; //$NON-NLS-1$
	
	public static final String DISABLED_IMG_PATH_ADD_CODE = DTOOL16
			+ "AddCode.gif"; //$NON-NLS-1$	

	public static final Image DISABLED_IMG_ADD_IMAGE = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_ADD_IMAGE);

//	public static final Image DISABLED_IMG_ADD_LINE = richTextPlugin
//			.getSharedImage(DISABLED_IMG_PATH_ADD_LINE);

	public static final Image DISABLED_IMG_ADD_LINK = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_ADD_LINK);

	public static final Image DISABLED_IMG_ADD_ORDERED_LIST = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_ADD_ORDERED_LIST);

	public static final Image DISABLED_IMG_ADD_TABLE = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_ADD_TABLE);

	public static final Image DISABLED_IMG_ADD_UNORDERED_LIST = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_ADD_UNORDERED_LIST);

	public static final Image DISABLED_IMG_BOLD = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_BOLD);

	public static final Image DISABLED_IMG_CLEAR_CONTENT = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_CLEAR_CONTENT);

	public static final Image DISABLED_IMG_COPY = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_COPY);

	public static final Image DISABLED_IMG_CUT = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_CUT);

	public static final Image DISABLED_IMG_FIND_REPLACE = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_FIND_REPLACE);

	public static final Image DISABLED_IMG_INDENT = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_INDENT);

	public static final Image DISABLED_IMG_ITALIC = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_ITALIC);

//	public static final Image DISABLED_IMG_JUSTIFY_CENTER = richTextPlugin
//			.getSharedImage(DISABLED_IMG_PATH_JUSTIFY_CENTER);
//
//	public static final Image DISABLED_IMG_JUSTIFY_FULL = richTextPlugin
//			.getSharedImage(DISABLED_IMG_PATH_JUSTIFY_FULL);
//
//	public static final Image DISABLED_IMG_JUSTIFY_LEFT = richTextPlugin
//			.getSharedImage(DISABLED_IMG_PATH_JUSTIFY_LEFT);
//
//	public static final Image DISABLED_IMG_JUSTIFY_RIGHT = richTextPlugin
//			.getSharedImage(DISABLED_IMG_PATH_JUSTIFY_RIGHT);

	public static final Image DISABLED_IMG_OUTDENT = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_OUTDENT);

	public static final Image DISABLED_IMG_PASTE = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_PASTE);

//	public static final Image DISABLED_IMG_STRIKE_THROUGH = richTextPlugin
//			.getSharedImage(DISABLED_IMG_PATH_STRIKE_THROUGH);

	public static final Image DISABLED_IMG_SUBSCRIPT = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_SUBSCRIPT);

	public static final Image DISABLED_IMG_SUPERSCRIPT = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_SUPERSCRIPT);

	public static final Image DISABLED_IMG_UNDERLINE = richTextPlugin
			.getSharedImage(DISABLED_IMG_PATH_UNDERLINE);

	public static final ImageDescriptor DISABLED_IMG_DESC_ADD_IMAGE = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_ADD_IMAGE);

//	public static final ImageDescriptor DISABLED_IMG_DESC_ADD_LINE = richTextPlugin
//			.getImageDescriptor(DISABLED_IMG_PATH_ADD_LINE);

	public static final ImageDescriptor DISABLED_IMG_DESC_ADD_LINK = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_ADD_LINK);

	public static final ImageDescriptor DISABLED_IMG_DESC_ADD_ORDERED_LIST = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_ADD_ORDERED_LIST);

	public static final ImageDescriptor DISABLED_IMG_DESC_ADD_TABLE = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_ADD_TABLE);

	public static final ImageDescriptor DISABLED_IMG_DESC_ADD_UNORDERED_LIST = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_ADD_UNORDERED_LIST);

	public static final ImageDescriptor DISABLED_IMG_DESC_BOLD = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_BOLD);

	public static final ImageDescriptor DISABLED_IMG_DESC_CLEAR_CONTENT = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_CLEAR_CONTENT);

	public static final ImageDescriptor DISABLED_IMG_DESC_COPY = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_COPY);

	public static final ImageDescriptor DISABLED_IMG_DESC_CUT = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_CUT);

	public static final ImageDescriptor DISABLED_IMG_DESC_FIND_REPLACE = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_FIND_REPLACE);

	public static final ImageDescriptor DISABLED_IMG_DESC_INDENT = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_INDENT);

	public static final ImageDescriptor DISABLED_IMG_DESC_ITALIC = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_ITALIC);

//	public static final ImageDescriptor DISABLED_IMG_DESC_JUSTIFY_CENTER = richTextPlugin
//			.getImageDescriptor(DISABLED_IMG_PATH_JUSTIFY_CENTER);
//
//	public static final ImageDescriptor DISABLED_IMG_DESC_JUSTIFY_FULL = richTextPlugin
//			.getImageDescriptor(DISABLED_IMG_PATH_JUSTIFY_FULL);
//
//	public static final ImageDescriptor DISABLED_IMG_DESC_JUSTIFY_LEFT = richTextPlugin
//			.getImageDescriptor(DISABLED_IMG_PATH_JUSTIFY_LEFT);
//
//	public static final ImageDescriptor DISABLED_IMG_DESC_JUSTIFY_RIGHT = richTextPlugin
//			.getImageDescriptor(DISABLED_IMG_PATH_JUSTIFY_RIGHT);

	public static final ImageDescriptor DISABLED_IMG_DESC_OUTDENT = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_OUTDENT);

	public static final ImageDescriptor DISABLED_IMG_DESC_PASTE = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_PASTE);

	public static final ImageDescriptor DISABLED_IMG_DESC_PASTE_PLAIN_TEXT = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_PASTE_PLAIN_TEXT);

//	public static final ImageDescriptor DISABLED_IMG_DESC_STRIKE_THROUGH = richTextPlugin
//			.getImageDescriptor(DISABLED_IMG_PATH_STRIKE_THROUGH);

	public static final ImageDescriptor DISABLED_IMG_DESC_SUBSCRIPT = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_SUBSCRIPT);

	public static final ImageDescriptor DISABLED_IMG_DESC_SUPERSCRIPT = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_SUPERSCRIPT);

	public static final ImageDescriptor DISABLED_IMG_DESC_UNDERLINE = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_UNDERLINE);

	public static final ImageDescriptor DISABLED_IMG_DESC_TIDY = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_TIDY);
	
	public static final ImageDescriptor DISABLED_IMG_DESC_ADD_CODE = richTextPlugin
			.getImageDescriptor(DISABLED_IMG_PATH_ADD_CODE);
	

	public static final Image IMG_ADD_ROW = richTextPlugin.getSharedImage(ETOOL16 + "addRow.gif"); //$NON-NLS-1$
	public static final Image DISABLE_IMG_ADD_ROW = richTextPlugin.getSharedImage(DTOOL16 + "addRow.gif"); //$NON-NLS-1$
	public static final Image IMG_ADD_COLUMN = richTextPlugin.getSharedImage(ETOOL16 + "addColumn.gif"); //$NON-NLS-1$
	public static final Image DISABLE_IMG_ADD_COLUMN = richTextPlugin.getSharedImage(DTOOL16 + "addColumn.gif"); //$NON-NLS-1$
	public static final Image IMG_DELETE_ROW = richTextPlugin.getSharedImage(ETOOL16 + "deleteRow.gif"); //$NON-NLS-1$
	public static final Image DISABLE_IMG_DELETE_ROW = richTextPlugin.getSharedImage(DTOOL16 + "deleteRow.gif"); //$NON-NLS-1$
	public static final Image IMG_DELETE_COLUMN = richTextPlugin.getSharedImage(ETOOL16 + "deleteColumn.gif"); //$NON-NLS-1$
	public static final Image DISABLE_IMG_DELETE_COLUMN = richTextPlugin.getSharedImage(DTOOL16 + "deleteColumn.gif"); //$NON-NLS-1$

}
