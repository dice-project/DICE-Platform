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
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.richtext.actions;

import java.io.File;
import java.net.URL;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.common.ui.util.ClipboardUtil;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichTextCommand;
import org.eclipse.epf.richtext.RichTextEditor;
import org.eclipse.epf.richtext.RichTextImages;
import org.eclipse.epf.richtext.RichTextPlugin;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.HTMLTransfer;
import org.eclipse.swt.widgets.Display;

/**
 * Pastes text from the clipboard onto a rich text control.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class PasteAction extends RichTextAction {

	private static final String SOURCE_URL = "SourceURL:"; //$NON-NLS-1$

	private static final String HTM_EXT = ".htm"; //$NON-NLS-1$

	private static final String HTML_EXT = ".html"; //$NON-NLS-1$

	private static final Pattern HREF_REFERENCES = Pattern
			.compile(
					"href\\s*=\\s*\"(.*?)\"", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	protected static final Pattern p_image_ref = Pattern
			.compile(
					"(<(img|iframe).*?src\\s*=\\s*\")(.*?)(\")", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	protected static String sourceURLStr = ""; //$NON-NLS-1$

	private Logger logger;
	
	private static String RESOURCES = "resources"; //$NON-NLS-1$

	/**
	 * Creates a new instance.
	 */
	public PasteAction(IRichText richText) {
		super(richText, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(RichTextImages.IMG_DESC_PASTE);
		setDisabledImageDescriptor(RichTextImages.DISABLED_IMG_DESC_PASTE);
		setToolTipText(RichTextResources.pasteAction_toolTipText);
		logger = RichTextPlugin.getDefault().getLogger();
	}

	/**
	 * Returns <code>true</code> if this action should be disabled when the
	 * rich text editor is in source edit mode.
	 */
	public boolean disableInSourceMode() {
		return false;
	}

	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            a rich text control
	 */
	public void execute(IRichText richText) {
		if (richText != null) {
			copyLinkResources(richText);
			if (richText instanceof RichTextEditor
					&& ((RichTextEditor) richText).isHTMLTabSelected()) {
				StyledText styledText = ((RichTextEditor) richText)
						.getSourceEdit();
				styledText.paste();
			} else {
				richText.executeCommand(RichTextCommand.PASTE, sourceURLStr);
			}
		}
	}

	/**
	 * Copies the link resources.
	 * 
	 * @param richText
	 *            a rich text control
	 */
	protected void copyLinkResources(IRichText richText) {
		try {
			sourceURLStr = ClipboardUtil.getHTMLSourceURL();
			Clipboard clipboard = new Clipboard(Display.getCurrent());
			String html = (String) clipboard.getContents(HTMLTransfer
					.getInstance());
			if (html != null && html.length() > 0) {
				String basePath = richText.getBasePath();
				URL sourceURL = null;
				if (sourceURLStr == null || sourceURLStr.length() == 0
						|| sourceURLStr.equals("about:blank")) { //$NON-NLS-1$
					sourceURL = richText.getCopyURL();
				} else {
					sourceURL = new URL(sourceURLStr);
				}

				Matcher matcher = HREF_REFERENCES.matcher(html);
				while (matcher.find()) {
					String href = NetUtil.decodeURL(matcher.group(1));
					try {
						URL hrefURL = new URL(sourceURL, href);
						String scheme = hrefURL.getProtocol();
						if (scheme != null
								&& scheme
										.equalsIgnoreCase(NetUtil.FILE_SCHEME)) {
							String url = hrefURL.getPath();
							File srcFile = new File(NetUtil.decodeURL(url));
							File tgtFile = null;
							File tgtDir = null;
							if (href.startsWith("#") || sourceURL.sameFile(hrefURL)) { //$NON-NLS-1$
								continue;
							} else if (href.startsWith(RESOURCES) || href.startsWith("./" + RESOURCES)) { //$NON-NLS-1$
								tgtFile = new File(basePath, href);
								tgtDir = tgtFile.getParentFile();
							} else {
								String resPath = getSubdirectoryOfResources(href);
								tgtFile = new File(basePath + RESOURCES, resPath);
								tgtDir = tgtFile;
							}
							tgtDir.mkdirs();
							FileUtil.copyFile(srcFile, tgtDir);
						}
					} catch (Exception e) {
						logger.logError(e);
					}
				}

				matcher = p_image_ref.matcher(html);
				while (matcher.find()) {
					String src = NetUtil.decodeURL(matcher.group(3));
					try {
						URL srcURL = new URL(sourceURL, src);
						String scheme = srcURL.getProtocol();
						if (scheme != null
								&& scheme.equalsIgnoreCase(NetUtil.FILE_SCHEME)) {
							File srcFile = null;
							String authority = srcURL.getAuthority();
							if (authority != null) {
								srcFile = new File(NetUtil.decodeURL(authority + srcURL.getPath()));
							} else {
								srcFile = new File(NetUtil.decodeURL(srcURL.getPath()));
							}
							File tgtFile = null;
							File tgtDir = null;
							if (src.startsWith(RESOURCES) || src.startsWith("./" + RESOURCES)) { //$NON-NLS-1$
								tgtFile = new File(basePath, src);
								tgtDir = tgtFile.getParentFile();
							} else {
								String resPath = getSubdirectoryOfResources(src);
								tgtFile = new File(basePath + RESOURCES, resPath);
								tgtDir = tgtFile;
							}
							tgtDir.mkdirs();
							FileUtil.copyFile(srcFile, tgtDir);
						}
					} catch (Exception e) {
						logger.logError(e);
					}
				}
			}
		} catch (Exception e) {
			logger.logError(e);
		}
	}

	/**
	 * Parses the given HTML content from the clipboard and returns the source
	 * URL.
	 * 
	 * @param htmlContent
	 *            the HTML content from the clipboard
	 * @return the source URL or <code>null</code>
	 */
	protected String getSourceURL(String htmlContent) {
		String sourceURL = null;
		int sourceURLIndex = htmlContent.indexOf(SOURCE_URL);
		if (sourceURLIndex > 0) {
			sourceURL = htmlContent.substring(sourceURLIndex
					+ SOURCE_URL.length());
			sourceURL = sourceURL.substring(0, sourceURL
					.indexOf(FileUtil.LINE_SEP));
			if (sourceURL.toLowerCase().endsWith(HTM_EXT)
					|| sourceURL.toLowerCase().endsWith(HTML_EXT)) {
				sourceURL = sourceURL.substring(0, sourceURL.indexOf(FileUtil
						.getFileName(sourceURL)) - 1);
				sourceURL = sourceURL.replace('\\', '/');
			}
			sourceURL = FileUtil.appendSeparator(sourceURL, "/"); //$NON-NLS-1$
		}
		return sourceURL;
	}

	protected String getSubdirectoryOfResources(String path) {
		String result = ""; //$NON-NLS-1$
		int res_idx = path.indexOf(RESOURCES);
		if (res_idx != -1) {
			Stack<String> stack = new Stack<String>();
			File relative = new File(path).getParentFile();
			while (!relative.getName().equals(RESOURCES)) {
				stack.push(relative.getName());
				relative = relative.getParentFile();
			}
			while (!stack.isEmpty()) {
				result = result + (String)stack.pop() + File.separator;
			}
		} 
		return result;
	}
	
}
