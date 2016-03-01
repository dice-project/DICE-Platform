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
package org.eclipse.epf.richtext;

import java.io.File;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.common.IHTMLFormatter;
import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.common.xml.XSLTProcessor;
import org.eclipse.epf.richtext.actions.AddColumnAction;
import org.eclipse.epf.richtext.actions.AddRowAction;
import org.eclipse.epf.richtext.actions.CopyAction;
import org.eclipse.epf.richtext.actions.CutAction;
import org.eclipse.epf.richtext.actions.DeleteLastColumnAction;
import org.eclipse.epf.richtext.actions.DeleteLastRowAction;
import org.eclipse.epf.richtext.actions.FindReplaceAction;
import org.eclipse.epf.richtext.actions.PasteAction;
import org.eclipse.epf.richtext.actions.PastePlainTextAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.HTMLTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;

/**
 * The default rich text control implementation.
 * <p>
 * The default rich text editor uses XHTML as the underlying markup language for
 * the rich text content. It is implemented using a SWT <code>Browser</code>
 * control and DHTML (HTML, CSS and JavaScript).
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @author Shi Jin
 * @since 1.0
 */
public class RichText implements IRichText {

	// Encoded single quote. All single quotes need to be specially encoded to
	// avoid JavaScript error when calling Browse.executeCommand().
	private static final String ENCODED_SINGLE_QUOTE = "%sq%"; //$NON-NLS-1$

	private static final String ENCODED_NEWLINE = "%EOL%"; //$NON-NLS-1$

	protected static final String STATUS_PREFIX = "$$$"; //$NON-NLS-1$

	protected static final int STATUS_PREFIX_LENGTH = STATUS_PREFIX.length();

	protected static final int STATUS_NOP = 0;

	protected static final int STATUS_INITIALIZED = 1;

	protected static final int STATUS_MODIFIED = 2;

	protected static final int STATUS_GET_TEXT = 3;

	protected static final int STATUS_KEY_DOWN = 4;

	protected static final int STATUS_KEY_UP = 5;

	protected static final int STATUS_SELECT_TEXT = 6;

	protected static final int STATUS_SELECT_CONTROL = 7;

	protected static final int STATUS_SELECT_NONE = 8;

	protected static final int STATUS_EXEC_CMD = 9;

	protected static final int STATUS_REFORMAT_LINKS = 10;

	// The default base path used for resolving links (<href>, <img>, etc.)
	private static final String DEFAULT_BASE_PATH = System
			.getProperty("user.home") //$NON-NLS-1$
			+ System.getProperty("file.separator") + "rte"; //$NON-NLS-1$ //$NON-NLS-2$

	// If true, log debugging info.
	protected boolean debug;

	// The plug-in logger.
	protected Logger logger;

	// The underlying SWT Browser used for loading the JavaScript/DHTML editor.
	protected Browser editor;

	// The underlying OleControlSite for the SWT Browser (Win32 only).
	protected Control editorControl;

	// The base URL of the rich text control whose content was last
	// copied to the clipboard.
	protected static URL copyURL;

	// The context menu associated with this control.
	protected Menu contextMenu;

	// The folder that contains the supporting CSS and JavaScript files
	protected String rteFolder;

	// The URL that points to the supporting CSS and JavaScript files.
	protected String rteURL;

	// The base path used for resolving links (<href>, <img>, etc.)
	protected String basePath;

	// The DHTML initialization flag.
	protected boolean initialized;

	// The initial focus.
	protected boolean initializedWithFocus;

	// The control's initial text. This is used to cache the HTML source passed
	// in via setText()while the DHTML initialization is happening.
	protected String initialText;

	// The control's current text.
	protected String currentText = ""; //$NON-NLS-1$

	private String currentRawText = "";	//$NON-NLS-1$

	// The control's editable flag.
	protected boolean editable = true;

	// The control's modification flag.
	protected boolean modified;

	// The control's text and object selection flag.
	protected boolean hasSelection;

	// The control's text selection
	protected RichTextSelection richTextSelection = new RichTextSelection();

	// JavaScript command execution status code.
	protected int status = 0;

	// The HTML source formatter.
	protected IHTMLFormatter htmlFormatter;

	// The SWT event listeners.
	protected Map<Listener, RichTextListener> listeners;

	// The modify listeners.
	protected List<ModifyListener> modifyListeners;

	// The control's edit flag.
	protected boolean notifyingModifyListeners = false;

	// The controls's focus flag.
	protected boolean hasFocus = false;

	// The controls's processing JavaScript event flag.
	protected boolean processingJSEvent = false;

	// The controls's processing MODIFIED JavaScript event flag.
	protected boolean checkingModifyEvent = false;

	// The control's find/replace text action
	protected FindReplaceAction findReplaceAction;
	
	// The control's IE flag
	protected boolean isIE = false;
	
	// A event type indicate control has been initialized
	public static final int RICH_TEXT_INITIALIZED_WIN32 = 98979695;
	public static final int RICH_TEXT_INITIALIZED_LINUX = 98979694;
	
	protected static final int STATUS_SELECT_TABLE = 51;
	// The table selection flag
	protected boolean tableSelection;
	
	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param style
	 *            the style for this control
	 * @param basePath
	 *            the path used for resolving links
	 */
	public RichText(Composite parent, int style, String basePath) {
		debug = RichTextPlugin.getDefault().isDebugging();
		logger = RichTextPlugin.getDefault().getLogger();
		findReplaceAction = new FindReplaceAction(this);
		rteFolder = RichTextPlugin.getDefault().getInstallPath() + "rte/"; //$NON-NLS-1$		
		rteURL = XMLUtil.escape("file://" + rteFolder); //$NON-NLS-1$
		setBasePath(basePath);

		try {
			boolean enableMozilla = false;
			String enableMozillaProperty = System.getProperty("rte.enable.mozilla");
			if (enableMozillaProperty != null) {
				enableMozilla = Boolean.valueOf(enableMozillaProperty);
			}
			if (enableMozilla) {
				editor = new Browser(parent, SWT.MOZILLA);
			} else {
				editor = new Browser(parent, SWT.NONE);
			}
			if (debug) {
				printDebugMessage("RichText", "basePath=" + basePath); //$NON-NLS-1$ //$NON-NLS-2$
			}
			editor.setLayoutData(new GridData(GridData.FILL_BOTH));
			editor.setData(PROPERTY_NAME, this);
			init(parent, style);
		} catch (Exception e) {
			editor = null;
			String msg = "Failed to create RichText with basePath=" + basePath; //$NON-NLS-1$
			logger.logError(msg, e);
			if (debug) {
				System.out.println(msg);
				e.printStackTrace();
			}
		}
		tableSelection = false;
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param style
	 *            the style for this control
	 */
	public RichText(Composite parent, int style) {
		this(parent, style, null);
	}

	/**
	 * Sets the base path for resolving links.
	 */
	protected void setBasePath(String path) {
		if (path != null && path.length() > 0) {
			if (path.startsWith(FileUtil.UNC_PATH_PREFIX)) {
				basePath = FileUtil.UNC_PATH_PREFIX
						+ FileUtil.appendSeparator(path.substring(
								FileUtil.UNC_PATH_PREFIX_LENGTH).replace('\\',
								'/'), "/"); //$NON-NLS-1$
			} else {
				basePath = FileUtil.appendSeparator(path).replace('\\', '/');
			}
		} else {
			basePath = FileUtil.appendSeparator(DEFAULT_BASE_PATH).replace(
					'\\', '/');
		}
	}

	/**
	 * Initializes this control.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param style
	 *            the style for this control
	 * @throws Exception
	 *             when an error has occurred while initialzing this control
	 */
	protected void init(Composite parent, int style) throws Exception {
		try {
			addStatusTextListener();
			if (debug) {
				printDebugMessage("init", "added status text listener"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			String editorHTML = generateEditorHTML();
			if (debug) {
				printDebugMessage("init", "generated editor HTML"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			editor.setText(editorHTML);
			if (debug) {
				printDebugMessage("init", "loaded editor HTML"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			contextMenu = new Menu(parent.getShell(), SWT.POP_UP);
			editor.setMenu(contextMenu);
			fillContextMenu(contextMenu);
			if (debug) {
				printDebugMessage("init", "added context menu"); //$NON-NLS-1$ //$NON-NLS-2$
			}

			addListeners();
			if (debug) {
				printDebugMessage("init", "added listeners"); //$NON-NLS-1$ //$NON-NLS-2$
			}

//			htmlFormatter = new HTMLFormatter();
			htmlFormatter = (IHTMLFormatter) ExtensionHelper.createExtensionForJTidy(
					CommonPlugin.getDefault().getId(), "htmlFormatter"); //$NON-NLS-1$
			if (debug) {
				printDebugMessage("init", "instantiated HTMLFormatter"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} catch (Exception e) {
			editor = null;
			dispose();
			throw e;
		}
	}

	/**
	 * Returns this rich text control.
	 * 
	 * @return this rich text control
	 */
	public Control getControl() {
		return editor;
	}

	/**
	 * Sets the layout data.
	 * 
	 * @param layoutData
	 *            the layout data to set
	 */
	public void setLayoutData(Object layoutData) {
		if (editor != null) {
			editor.setLayoutData(layoutData);
		}
	}

	/**
	 * Returns the layout data.
	 * 
	 * @return this control's layout data
	 */
	public Object getLayoutData() {
		if (editor != null) {
			return editor.getLayoutData();
		}
		return null;
	}

	/**
	 * Sets focus to this control.
	 */
	public void setFocus() {
		if (debug) {
			printDebugMessage("setFocus, editable=" + editable); //$NON-NLS-1$
		}
		if (editor != null) {
			if (initialized) {
				if (!editor.isFocusControl()) {
					if (!Platform.getOS().equals("win32")) { //$NON-NLS-1$
						// Workaround for Mozilla and Firefox rich text editor focus
						// issue.
						editor.setFocus();
					}
					executeCommand(RichTextCommand.SET_FOCUS);
				}
				hasFocus = true;
			} else {
				initializedWithFocus = true;
			}
		}
	}

	/**
	 * Tells the control it does not have focus.
	 */
	public void setBlur() {
		if (debug) {
			printDebugMessage("setBlur, editable=" + editable); //$NON-NLS-1$
		}
		if (editor != null) {
			if (initialized) {
				hasFocus = false;
			} else {
				initializedWithFocus = false;
			}
		}
	}

	/**
	 * Checks whether this control has focus.
	 * 
	 * @return <code>true</code> if this control has the user-interface focus
	 */
	public boolean hasFocus() {
		if (editor != null) {
			return hasFocus;
		}
		return false;
	}

	/**
	 * Returns the base path used for resolving text and image links.
	 * 
	 * @return the base path used for resolving links in this control
	 */
	public String getBasePath() {
		return basePath;
	}

	/**
	 * Returns the base URL of the rich text control whose content was last
	 * copied to the clipboard.
	 * 
	 * @return the base URL of a rich text control
	 */
	public URL getCopyURL() {
		return copyURL;
	}

	/**
	 * Sets the base URL of the rich text control whose content was last copied
	 * to the clipboard.
	 */
	public void setCopyURL() {
		try {
			copyURL = new File(basePath).toURL();
		} catch (Exception e) {
			copyURL = null;
		}
	}

	/**
	 * Returns the editable state.
	 * 
	 * @return <code>true</code> if the content is editable
	 */
	public boolean getEditable() {
		return editable;
	}

	/**
	 * Sets the editable state.
	 * 
	 * @param editable
	 *            the editable state
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
		if (initialized) {
			executeCommand(RichTextCommand.SET_EDITABLE, "" + editable); //$NON-NLS-1$
		}
	}

	/**
	 * Checks whether the content has been modified.
	 * 
	 * @return <code>true</code> if the content has been modified
	 */
	public boolean getModified() {
		return modified;
	}

	/**
	 * Sets the modified state.
	 * 
	 * @param modified
	 *            the modified state
	 */
	public void setModified(boolean modified) {
		this.modified = modified;
	}

	/**
	 * Returns the rich text content.
	 * 
	 * @return the rich text content formatted in a markup language
	 */
	public String getText() {
		if (editor != null && initialized) {
			try {
				executeCommand(RichTextCommand.GET_TEXT);
				if (currentText != null && currentText.length() > 0) {
					currentText = currentText.replaceAll(
							"<P>&nbsp;</P>", "<br/>"); //$NON-NLS-1$ //$NON-NLS-2$			
					currentText = tidyText(currentText);
					currentText = formatHTML(currentText);
				} else {
					currentText = ""; //$NON-NLS-1$
				}
				if (debug) {
					printDebugMessage("getText", "text=", currentText); //$NON-NLS-1$ //$NON-NLS-2$
				}
				return currentText;
			} catch (Exception e) {
				logger.logError(e);
			}
		}
		return ""; //$NON-NLS-1$
	}

	protected String formatHTML(String text) {
		String formattedText;
		try {
			// Call JTidy to format the source to XHTML.
			formattedText = htmlFormatter.formatHTML(text);
			if (htmlFormatter.getLastErrorStr() != null) {
				logger.logError(htmlFormatter.getLastErrorStr());
			}
			return formattedText;
		} catch (UnsupportedEncodingException e) {
			logger.logError(e);
		}
		return text;
	}

	/**
	 * Sets the rich text content.
	 * 
	 * @param text
	 *            the rich text content formatted in a markup language
	 */
	public void setText(String text) {
		if (editor != null) {
			if (debug) {
				printDebugMessage("setText", "text=", text); //$NON-NLS-1$ //$NON-NLS-2$
			}

			setCurrentRawText(text);
			
			String newText = text;
			if (newText != null) {
				newText = tidyText(newText);
				// Call JTidy to format the source to XHTML.
				newText = formatHTML(newText);
			} else {
				newText = ""; //$NON-NLS-1$
			}

			if (initialized) {
				modified = !newText.equals(currentText);
			}
			initialText = newText;
			if (initialText.equals("") && !isIE) { //$NON-NLS-1$
				initialText = "<br />"; //$NON-NLS-1$
			}

			if (debug) {
				printDebugMessage(
						"setText", "modified=" + modified + ", newText=", newText); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}

			if (initialized) {
				try {
					executeCommand(RichTextCommand.SET_TEXT, workaroundForObjectParamNode(newText));
					executeCommand(RichTextCommand.SET_EDITABLE, "" + editable); //$NON-NLS-1$				
				} catch (Exception e) {
					logger.logError(e);
				}
			}

			currentText = newText;
		}
	}

	/**
	 * Restores the rich text content back to the initial value.
	 */
	public void restoreText() {
		setText(initialText);
		modified = false;
	}

	/**
	 * Returns the currently selected text.
	 * 
	 * @return the selected text or <code>""</code> if there is no
	 *         hasSelection
	 */
	public String getSelectedText() {
		// executeCommand(RichTextCommand.GET_SELECTED_TEXT);
		return richTextSelection.getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.richtext.IRichText#getSelected()
	 */
	public RichTextSelection getSelected() {
		return richTextSelection;
	}

	/**
	 * Returns an application specific property value.
	 * 
	 * @param key
	 *            the name of the property
	 * @return the value of the property or <code>null</code> if it has not
	 *         been set
	 */
	public Object getData(String key) {
		if (editor != null) {
			return editor.getData(key);
		}
		return null;
	}

	/**
	 * Sets an application specific property name and value.
	 * 
	 * @param key
	 *            the name of the property
	 * @param value
	 *            the property value
	 */
	public void setData(String key, Object value) {
		if (editor != null) {
			editor.setData(key, value);
		}
	}

	/**
	 * Executes the given JavaScript.
	 * 
	 * @param script
	 *            the JavaScript to execute
	 * @return a status code returned by the executed script
	 */
	protected int execute(final String script) {
		status = 0;
		if (editor != null && script != null && script.length() > 0) {
			try {
				if (!isIE && processingJSEvent) {
					Display.getCurrent().asyncExec(new Runnable() {
						public void run() {
							if (!isDisposed()) {
								editor.execute(script);
								if (!Platform.getOS().equals(Platform.OS_WIN32)) {
									if (script.startsWith(RichTextCommand.SET_TEXT)) {
										notifyListeners(RichText.RICH_TEXT_INITIALIZED_LINUX, new Event());
									}
								}
							}
						}
					});
				} else {
					editor.execute(script);
				}
				if (debug) {
					printDebugMessage("execute", script); //$NON-NLS-1$				
				}
			} catch (Exception e) {
				String msg = "Failed to execute " + script; //$NON-NLS-1$
				logger.logError(msg, e);
				if (debug) {
					printDebugMessage("execute", msg); //$NON-NLS-1$
					e.printStackTrace();
				}
			}
		}
		return status;
	}

	/**
	 * Executes the given rich text command. The supported command strings are
	 * defined in <code>RichTextCommand<code>.
	 * 
	 * @param	command		a rich text command string.
	 * @return	a status code returned by the executed command
	 */
	public int executeCommand(String command) {
		status = 0;
		if (command != null && command.equals(RichTextCommand.CLEAR_CONTENT)) {
			String oldInitialText = initialText;
			setText(""); //$NON-NLS-1$
			initialText = oldInitialText;
			status = 1;
			modified = true;
			notifyModifyListeners();
		} else {
			status = execute(command + "();"); //$NON-NLS-1$
		}
		return status;
	}

	/**
	 * Executes the given rich text command with a single parameter. The
	 * supported command strings are defined in <code>RichTextCommand<code>.
	 * 
	 * @param	command		a rich text command string
	 * @param	param		a parameter for the command or <code>null</code>
	 * @return	a status code returned by the executed command
	 */
	public int executeCommand(String command, String param) {
		if (param == null) {
			return executeCommand(command);
		}
		return execute(command + "('" + formatText(param) + "');"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Executes the given rich text command with an array of parameters. The
	 * supported command strings are defined in <code>RichTextCommand<code>.
	 * 
	 * @param	command		a rich text command string
	 * @param	params		an array of parameters for the command or <code>null</code>
	 * @return	a status code returned by the executed command
	 */
	public int executeCommand(String command, String[] params) {
		if (params == null || params.length == 0) {
			return executeCommand(command);
		}
		StringBuffer sb = new StringBuffer();
		int paramsLength = params.length;
		for (int i = 0; i < paramsLength; i++) {
			sb.append('\'').append(formatText(params[i])).append('\'');
			if (i < paramsLength - 1) {
				sb.append(',');
			}
		}
		String param = sb.toString();
		return execute(command + "(" + param + ");"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Disposes the operating system resources allocated by the control.
	 */
	public void dispose() {
		if (contextMenu != null && !contextMenu.isDisposed()) {
			contextMenu.dispose();
			contextMenu = null;
		}
		if (listeners != null) {
			listeners.clear();
			listeners = null;
		}
		if (modifyListeners != null) {
			modifyListeners.clear();
			modifyListeners = null;
		}
		if (htmlFormatter != null) {
			htmlFormatter = null;
		}
		if (this.findReplaceAction != null) {
			this.findReplaceAction.dispose();
			this.findReplaceAction = null;
		}
	}

	/**
	 * Checks whether this control has been disposed.
	 * 
	 * @return <code>true</code> if this control is disposed successfully
	 */
	public boolean isDisposed() {
		return editor.isDisposed();
	}

	/**
	 * Returns the modify listeners attached to this control.
	 * 
	 * @return an iterator for retrieving the modify listeners
	 */
	public Iterator<ModifyListener> getModifyListeners() {
		return modifyListeners.iterator();
	}

	/**
	 * Adds a listener to the collection of listeners who will be notified when
	 * keys are pressed and released within this control.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addKeyListener(KeyListener listener) {
		if (editor != null) {
			editor.addKeyListener(listener);
		}
	}

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when keys are pressed and released within this control.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeKeyListener(KeyListener listener) {
		if (editor != null) {
			editor.removeKeyListener(listener);
		}
	}

	/**
	 * Adds a listener to the collection of listeners who will be notified when
	 * the content of this control is modified.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addModifyListener(ModifyListener listener) {
		if (editor != null && listener != null
				&& !modifyListeners.contains(listener)) {
			modifyListeners.add(listener);
		}
	}

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when the content of this control is modified.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeModifyListener(ModifyListener listener) {
		if (editor != null && listener != null
				&& modifyListeners.contains(listener)) {
			modifyListeners.remove(listener);
		}
	}

	/**
	 * Adds the listener to the collection of listeners who will be notifed when
	 * this control is disposed.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addDisposeListener(DisposeListener listener) {
		if (editor != null) {
			editor.addDisposeListener(listener);
		}
	}

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when this control is disposed.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeDisposeListener(DisposeListener listener) {
		if (editor != null) {
			editor.removeDisposeListener(listener);
		}
	}

	/**
	 * Adds a listener to the collection of listeners who will be notified when
	 * help events are generated for this control.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addHelpListener(HelpListener listener) {
		if (editor != null) {
			editor.addHelpListener(listener);
		}
	}

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when help events are generated for this control.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeHelpListener(HelpListener listener) {
		if (editor != null) {
			editor.removeHelpListener(listener);
		}
	}

	/**
	 * Adds the listener to the collection of listeners who will be notifed when
	 * an event of the given type occurs within this control.
	 * 
	 * @param eventType
	 *            the type of event to listen for
	 * @param listener
	 *            the listener which should be notified when the event occurs
	 */
	public void addListener(int eventType, Listener listener) {
		if (editor != null && !listeners.containsKey(listener)) {
			if (eventType != SWT.SELECTED) {
				if (editorControl == null
						|| (eventType != SWT.Activate
								&& eventType != SWT.Deactivate
								&& eventType != SWT.FocusIn && eventType != SWT.FocusOut)) {
					editor.addListener(eventType, listener);
				}
			}
			listeners.put(listener, new RichTextListener(eventType, listener));
		}
	}

	/**
	 * Removes the listener from the collection of listeners who will be notifed
	 * when an event of the given type occurs within this control.
	 * 
	 * @param eventType
	 *            the type of event to listen for
	 * @param listener
	 *            the listener which should no longer be notified when the event
	 *            occurs
	 */
	public void removeListener(int eventType, Listener listener) {
		if (editor != null && listeners.containsKey(listener)) {
			if (editorControl == null
					|| (eventType != SWT.Activate
							&& eventType != SWT.Deactivate
							&& eventType != SWT.FocusIn && eventType != SWT.FocusOut)) {
				editor.removeListener(eventType, listener);
			}
			listeners.remove(listener);
		}
	}

	/**
	 * Returns the event listeners attached to this control.
	 * 
	 * @return an iterator for retrieving the event listeners attached to this
	 *         control
	 */
	public Iterator<RichTextListener> getListeners() {
		return listeners.values().iterator();
	}

	/**
	 * Adds the listener to monitor events and status sent by the underlying
	 * DHTML editor.
	 */
	protected void addStatusTextListener() {
		editor.addStatusTextListener(new StatusTextListener() {
			public void changed(StatusTextEvent event) {
				String eventText = event.text;
				int eventTextLength = eventText.length();
				if (eventText.startsWith(STATUS_PREFIX)
						&& eventTextLength > STATUS_PREFIX_LENGTH) {
					try {
						processingJSEvent = true;
						int endStatusIndex = STATUS_PREFIX_LENGTH + 1;
						if (eventText.length() > STATUS_PREFIX_LENGTH + 1
								&& Character.isDigit(eventText
										.charAt(endStatusIndex))) {
							endStatusIndex++;
						}
						int statusType = Integer.parseInt(eventText.substring(
								STATUS_PREFIX_LENGTH, endStatusIndex));
						switch (statusType) {
						case STATUS_NOP:
							break;
						case STATUS_INITIALIZED:
							if (!initialized) {
								initialized = true;
								if (debug) {
									printDebugMessage(
											"statusTextListener", "STATUS_INITIALIZED"); //$NON-NLS-1$ //$NON-NLS-2$
								}
								if (!Platform.getOS().equals("win32")) { //$NON-NLS-1$
									// Workaround Mozilla'a IFRAME
									// height issue.
									executeCommand(RichTextCommand.SET_HEIGHT,
											"" + editor.getBounds().height); //$NON-NLS-1$
								}
								executeCommand(RichTextCommand.SET_TEXT,
										workaroundForObjectParamNode(currentText));
								if (initializedWithFocus) {
									setFocus();
								}
								if (!editable) {
									executeCommand(
											RichTextCommand.SET_EDITABLE,
											"" + editable); //$NON-NLS-1$
								}								
								
								if (Platform.getOS().equals(Platform.OS_WIN32)) {
									notifyListeners(RichText.RICH_TEXT_INITIALIZED_WIN32, new Event());
								}
							}
							break;
						case STATUS_MODIFIED:
							if (debug) {
								printDebugMessage(
										"statusTextListener", "STATUS_MODIFIED"); //$NON-NLS-1$ //$NON-NLS-2$
							}
							checkModify();
							break;
						case STATUS_GET_TEXT:
							if (eventTextLength >= STATUS_PREFIX_LENGTH + 2) {
								currentText = eventText
										.substring(STATUS_PREFIX_LENGTH + 2);
								
								currentText = unWorkaroundForObjectParamNode(currentText); 
							} else {
								currentText = ""; //$NON-NLS-1$
							}
							if (debug) {
								printDebugMessage(
										"statusTextListener", //$NON-NLS-1$
										"STATUS_GET_TEXT, currentText=", currentText); //$NON-NLS-1$
							}
							break;
						case STATUS_KEY_DOWN:
							if (eventTextLength >= STATUS_PREFIX_LENGTH + 2) {
								String cmd = eventText
										.substring(STATUS_PREFIX_LENGTH + 2);
								if (debug) {
									printDebugMessage("statusTextListener", //$NON-NLS-1$
											"STATUS_KEY_DOWN, cmd=" + cmd); //$NON-NLS-1$
								}
								if (cmd.equals(RichTextCommand.COPY)) {
									setCopyURL();
								} else if (cmd.equals(RichTextCommand.CUT)) {
									setCopyURL();
									CutAction action = new CutAction(
											RichText.this);
									action.execute(RichText.this);
								} else if (cmd
										.equals(RichTextCommand.FIND_TEXT)) {
									getFindReplaceAction().execute(
											RichText.this);
								} else if (cmd.equals(RichTextCommand.PASTE)) {
									PasteAction action = new PasteAction(
											RichText.this);
									action.execute(RichText.this);
								} else if (cmd.equals(RichTextCommand.SAVE)) {
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getActivePage().getActiveEditor()
											.doSave(null);
								} else if (cmd.equals(RichTextCommand.SAVE_ALL)) {
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getActivePage().saveAllEditors(
													false);
								}
							}
							break;
						case STATUS_KEY_UP:
							if (debug) {
								printDebugMessage("statusTextListener", //$NON-NLS-1$
										"STATUS_KEY_UP, modified=" + modified); //$NON-NLS-1$
							}
							checkModify();
							break;
						case STATUS_SELECT_TABLE:
							tableSelection = true;
							
							if (hasFocus())
								notifyListeners(SWT.SELECTED, new Event());
							break;
						case STATUS_SELECT_TEXT:
							if (eventTextLength >= STATUS_PREFIX_LENGTH + 2) {
								String[] strings = eventText.substring(
										STATUS_PREFIX_LENGTH + 2).split(
										"\\$", 5); //$NON-NLS-1$
								try {
									richTextSelection.setFontName(strings[0]);
									richTextSelection.setFontSize(strings[1]);
									richTextSelection.setBlockStyle(strings[2]);
									richTextSelection.setFlags(Integer
											.parseInt(strings[3]));
									richTextSelection.setText(strings[4]);
								} catch (NumberFormatException e) {
									logger.logError(e);
								}
								if (debug) {
									printDebugMessage(
											"selectionStatusListener", //$NON-NLS-1$
											"current selection is=" + richTextSelection); //$NON-NLS-1$
								}
								if(strings[4].length() == 0) {
									hasSelection = false;
								} else {
									hasSelection = true;
								}
								
								if (hasFocus())
									notifyListeners(SWT.SELECTED, new Event());
							} else {
								richTextSelection.setText(""); //$NON-NLS-1$
								hasSelection = false;
							}
							if (debug) {
								printDebugMessage(
										"statusTextListener", //$NON-NLS-1$
										"STATUS_SELECT_TEXT, selectedText=", richTextSelection.getText()); //$NON-NLS-1$
							}
							tableSelection = false;
							break;
						case STATUS_SELECT_CONTROL:
							if (debug) {
								printDebugMessage("statusTextListener", //$NON-NLS-1$
										"STATUS_SELECT_CONTROL, control selected"); //$NON-NLS-1$
							}
							hasSelection = true;
							break;
						case STATUS_SELECT_NONE:
							if (debug) {
								printDebugMessage("statusTextListener", //$NON-NLS-1$
										"STATUS_SELECT_NONE, no selection"); //$NON-NLS-1$
							}
							hasSelection = false;
							tableSelection = false;
							break;
						case STATUS_EXEC_CMD:
							if (eventTextLength >= STATUS_PREFIX_LENGTH + 3) {
								try {
									status = Integer.parseInt(eventText
											.substring(
													STATUS_PREFIX_LENGTH + 2,
													STATUS_PREFIX_LENGTH + 3));
								} catch (Exception e) {
									status = -1;
								}
							}
							if (debug && status != 1) {
								printDebugMessage("statusTextListener", //$NON-NLS-1$
										"STATUS_EXEC_CMD, status=" + status); //$NON-NLS-1$
							}
							break;
						case STATUS_REFORMAT_LINKS:
							if (debug) {
								printDebugMessage(
										"statusTextListener", "STATUS_REFORMAT_LINKS"); //$NON-NLS-1$ //$NON-NLS-2$
							}
							if (Platform.getOS().equals("win32")) { //$NON-NLS-1$ 
								// Workaround the drag and drop issue with DBCS
								// characters.
//								if (modified) {
//									setText(getText());
//									modified = true;
//								}
							}
							checkModify();
							break;
						}
					} catch (Exception e) {
					} finally {
						processingJSEvent = false;
					}
				}
			}
		});
	}

	/**
	 * Generates the HTML source for the editor.
	 * 
	 * @return the HTML source for the editor
	 */
	protected String generateEditorHTML() throws Exception {
		String escapedBasePath = basePath;
		if (escapedBasePath.startsWith(FileUtil.UNC_PATH_PREFIX))
			escapedBasePath = escapedBasePath.replaceFirst(
					"^\\\\\\\\", "\\\\\\\\\\\\\\\\"); //$NON-NLS-1$ //$NON-NLS-2$
		escapedBasePath = XMLUtil
				.escape("file://" + escapedBasePath.replaceAll("'", "\\\\'")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		String escapedRteUTL = rteURL.replaceAll("&apos;", "%27"); //$NON-NLS-1$ //$NON-NLS-2$

		StringBuffer rteXML = new StringBuffer();
		rteXML.append("<rte id=\"").append("rte") //$NON-NLS-1$ //$NON-NLS-2$
				.append("\" css=\"").append(escapedRteUTL + "rte.css") //$NON-NLS-1$ //$NON-NLS-2$
				.append("\" js=\"").append(escapedRteUTL + "rte.js") //$NON-NLS-1$ //$NON-NLS-2$
				.append("\" baseURL=\"").append(escapedBasePath) //$NON-NLS-1$
				.append("\"/>"); //$NON-NLS-1$
		StringWriter result = new StringWriter();
		XSLTProcessor.transform(
				rteFolder + "rte.xsl", rteXML.toString(), result); //$NON-NLS-1$
		return result.toString();
	}

	/**
	 * Fills the context menu with menu items.
	 * 
	 * @param contextMenu
	 *            a context menu containing rich text actions
	 */
	protected void fillContextMenu(Menu contextMenu) {
		final MenuItem cutItem = new MenuItem(contextMenu, SWT.PUSH);
		cutItem.setText(RichTextResources.cutAction_text);
		cutItem.setImage(RichTextImages.IMG_CUT);
		cutItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				CutAction action = new CutAction(RichText.this);
				action.execute(RichText.this);
			}
		});
		final MenuItem copyItem = new MenuItem(contextMenu, SWT.PUSH);
		copyItem.setText(RichTextResources.copyAction_text);
		copyItem.setImage(RichTextImages.IMG_COPY);
		copyItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				CopyAction action = new CopyAction(RichText.this);
				action.execute(RichText.this);
			}
		});
		final MenuItem pasteItem = new MenuItem(contextMenu, SWT.PUSH);
		pasteItem.setText(RichTextResources.pasteAction_text);
		pasteItem.setImage(RichTextImages.IMG_PASTE);
		pasteItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				PasteAction action = new PasteAction(RichText.this);
				action.execute(RichText.this);
			}
		});

		final MenuItem pastePlainTextItem = new MenuItem(contextMenu, SWT.PUSH);
		pastePlainTextItem.setText(RichTextResources.pastePlainTextAction_text);
		pastePlainTextItem.setImage(RichTextImages.IMG_PASTE_PLAIN_TEXT);
		pastePlainTextItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				PastePlainTextAction action = new PastePlainTextAction(
						RichText.this);
				action.execute(RichText.this);
			}
		});

		final MenuItem sperate1 = new MenuItem(contextMenu, SWT.SEPARATOR);
		
		final MenuItem addRowItem = new MenuItem(contextMenu, SWT.PUSH);
		addRowItem.setText(RichTextResources.addRowAction_text);
		addRowItem.setImage(RichTextImages.IMG_ADD_ROW);
		addRowItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				AddRowAction action = new AddRowAction(RichText.this);
				action.execute(RichText.this);
			}
		});
		
		final MenuItem addColumnItem = new MenuItem(contextMenu, SWT.PUSH);
		addColumnItem.setText(RichTextResources.addColumnAction_text);
		addColumnItem.setImage(RichTextImages.IMG_ADD_COLUMN);
		addColumnItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				AddColumnAction action = new AddColumnAction(RichText.this);
				action.execute(RichText.this);
			}
		});
		
		final MenuItem deleteLastRowItem = new MenuItem(contextMenu, SWT.PUSH);
		deleteLastRowItem.setText(RichTextResources.deleteLastRowAction_text);
		deleteLastRowItem.setImage(RichTextImages.IMG_DELETE_ROW);
		deleteLastRowItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				DeleteLastRowAction action = new DeleteLastRowAction(RichText.this);
				action.execute(RichText.this);
			}
		});
		
		final MenuItem deleteLastColumnItem = new MenuItem(contextMenu, SWT.PUSH);
		deleteLastColumnItem.setText(RichTextResources.deleteLastColumnAction_text);
		deleteLastColumnItem.setImage(RichTextImages.IMG_DELETE_COLUMN);
		deleteLastColumnItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				DeleteLastColumnAction action = new DeleteLastColumnAction(RichText.this);
				action.execute(RichText.this);
			}
		});
		
		contextMenu.addMenuListener(new MenuListener() {
			public void menuHidden(MenuEvent e) {
			}

			public void menuShown(MenuEvent e) {
				Clipboard clipboard = new Clipboard(Display.getCurrent());
				String html = (String) clipboard.getContents(HTMLTransfer
						.getInstance());
				String text = (String) clipboard.getContents(TextTransfer
						.getInstance());
				cutItem.setEnabled(editable && hasSelection);
				copyItem.setEnabled(hasSelection);
				pasteItem.setEnabled(editable && (html != null));
				pastePlainTextItem.setEnabled(editable && (text != null));
				addRowItem.setEnabled(tableSelection);
				addColumnItem.setEnabled(tableSelection);
				deleteLastRowItem.setEnabled(tableSelection);
				deleteLastColumnItem.setEnabled(tableSelection);
			}
		});
	}

	/**
	 * Adds listeners to manage the activation and focus events.
	 */
	protected void addListeners() {
		editorControl = getControlSite(editor);
		if (editorControl != null) {
			if (debug) {
				printDebugMessage(
						"init", "editorControl=" + editorControl.getClass().getName()); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			// only IE (win32) has the editorControl != null
			isIE = true;
			
			editorControl.addListener(SWT.Activate, new Listener() {
				public void handleEvent(Event event) {
					if (debug) {
						printDebugMessage("activateListener"); //$NON-NLS-1$
					}
					setFocus();
					notifyListeners(SWT.Activate, event);
				}
			});

			editorControl.addListener(SWT.Deactivate, new Listener() {
				public void handleEvent(Event event) {
					if (debug) {
						printDebugMessage("deactivateListener"); //$NON-NLS-1$
					}
					setBlur();
					notifyListeners(SWT.Deactivate, event);
				}
			});

			editorControl.addListener(SWT.FocusIn, new Listener() {
				public void handleEvent(Event event) {
					if (debug) {
						printDebugMessage("focusInListener"); //$NON-NLS-1$
					}
					executeCommand("updateSelection"); //$NON-NLS-1$					
					notifyListeners(SWT.FocusIn, event);
				}
			});

	        editorControl.addKeyListener(new KeyAdapter() {
	        	   public void keyReleased(KeyEvent event) {
						int keyCode = event.keyCode;
						int stateMask = event.stateMask;
						if (debug) {
							printDebugMessage(
									"keyUpListener", "keyCode=" + keyCode //$NON-NLS-1$ //$NON-NLS-2$
											+ ", stateMask=" + stateMask + ", editable=" + editable); //$NON-NLS-1$ //$NON-NLS-2$
						}
						
						if ( stateMask == SWT.CTRL && event.keyCode == 0x11 ) { //0x11 is for all Control key, such as ctrl-b, ctrl-I, ctrl-c, etc.. 
							executeCommand("updateSelection");
						} 
						
						if ((stateMask & SWT.CTRL) > 0
								|| (stateMask & SWT.ALT) > 0
								|| ((stateMask & SWT.SHIFT) > 0 && keyCode == stateMask)) {
							return;
						}
						if (editable) {
							switch (event.keyCode) {
							case SWT.ARROW_DOWN:
							case SWT.ARROW_LEFT:
							case SWT.ARROW_RIGHT:
							case SWT.ARROW_UP:
							case SWT.END:
							case SWT.HOME:
							case SWT.PAGE_DOWN:
							case SWT.PAGE_UP:
							case SWT.TAB:
								return;
							default:
								checkModify();
								break;
							}
						}
					}
				});
			
//			editorControl.addListener(SWT.KeyUp, new Listener() {
//				public void handleEvent(Event event) {
//					int keyCode = event.keyCode;
//					int stateMask = event.stateMask;
//					if (debug) {
//						printDebugMessage(
//								"keyUpListener", "keyCode=" + keyCode //$NON-NLS-1$ //$NON-NLS-2$
//										+ ", stateMask=" + stateMask + ", editable=" + editable); //$NON-NLS-1$ //$NON-NLS-2$
//					}
//					if ((stateMask & SWT.CTRL) > 0
//							|| (stateMask & SWT.ALT) > 0
//							|| ((stateMask & SWT.SHIFT) > 0 && keyCode == stateMask)) {
//						return;
//					}
//					if (editable) {
//						switch (event.keyCode) {
//						case SWT.ARROW_DOWN:
//						case SWT.ARROW_LEFT:
//						case SWT.ARROW_RIGHT:
//						case SWT.ARROW_UP:
//						case SWT.END:
//						case SWT.HOME:
//						case SWT.PAGE_DOWN:
//						case SWT.PAGE_UP:
//						case SWT.TAB:
//							return;
//						default:
//							checkModify();
//							break;
//						}
//					}
//				}
//			});

			editor.addLocationListener(new LocationAdapter() {
				public void changing(LocationEvent event) {
					// Deactivate the links in the content page in readonly
					// mode.
					event.doit = editable;
				}
			});
		} else {
			editor.addListener(SWT.Activate, new Listener() {
				public void handleEvent(Event event) {
					if (debug) {
						printDebugMessage("activateListener"); //$NON-NLS-1$
					}
					setFocus();
				}
			});

			editor.addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {
					if (e.keyCode == SWT.TAB) {
						if ((e.stateMask & SWT.SHIFT) != 0) {
							editor.traverse(SWT.TRAVERSE_TAB_PREVIOUS);
						} else if ((e.stateMask & SWT.CTRL) == 0 ){
							editor.traverse(SWT.TRAVERSE_TAB_NEXT);
						}
						return;
					}
					if (!editable) {
						e.doit = false;
					}
				}

				public void keyReleased(KeyEvent e) {
					if ((e.stateMask & SWT.CTRL) > 0
							|| (e.stateMask & SWT.ALT) > 0)
						return;
					if (editable) {
						switch (e.keyCode) {
						case SWT.ARROW_DOWN:
						case SWT.ARROW_LEFT:
						case SWT.ARROW_RIGHT:
						case SWT.ARROW_UP:
						case SWT.END:
						case SWT.HOME:
						case SWT.PAGE_DOWN:
						case SWT.PAGE_UP:
						case SWT.SHIFT:
						case SWT.TAB:
							break;
						default:
							checkModify();
							break;
						}
					}
				}
			});
		}

		editor.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				if (debug) {
					printDebugMessage("disposeListener"); //$NON-NLS-1$						
				}
				dispose();
			}
		});

		listeners = new Hashtable<Listener, RichTextListener>();
		modifyListeners = new ArrayList<ModifyListener>();
	}

	/**
	 * Notifies the rich text event listeners.
	 * 
	 * @param eventType
	 *            the event type
	 * @param event
	 *            the SWT event
	 */
	protected void notifyListeners(int eventType, Event event) {
		if (notifyingModifyListeners) {
			return;
		}

		if (listeners != null) {
			event.display = Display.getCurrent();
			event.widget = editor;

			for (Iterator<RichTextListener> i = listeners.values().iterator(); i
					.hasNext();) {
				RichTextListener listener = i.next();
				if (listener.getEventType() == eventType) {
					if (debug) {
						printDebugMessage(
								"notifyListeners", "notifying listener, " + listener + ", eventType=" + eventType); //$NON-NLS-1$ //$NON-NLS-2$	//$NON-NLS-3$	
					}
					listener.getListener().handleEvent(event);
					if (debug) {
						printDebugMessage(
								"notifyListeners", "notified listener, " + listener + ", eventType=" + eventType); //$NON-NLS-1$ //$NON-NLS-2$	//$NON-NLS-3$	
					}
				}
			}
		}
	}

	/**
	 * Notifies the modify listeners that the rich text editor content has
	 * changed.
	 */
	public void notifyModifyListeners() {
		notifyingModifyListeners = true;

		Event event = new Event();
		event.display = Display.getCurrent();
		event.widget = editor;

		for (Iterator<ModifyListener> i = modifyListeners.iterator(); i
				.hasNext();) {
			ModifyListener listener = i.next();
			if (debug) {
				printDebugMessage(
						"notifyModifyListeners", "notifying listener, " + listener); //$NON-NLS-1$ //$NON-NLS-2$	
			}
			listener.modifyText(new ModifyEvent(event));
			if (debug) {
				printDebugMessage(
						"notifyModifyListeners", "notified listener, " + listener); //$NON-NLS-1$ //$NON-NLS-2$	
			}
		}

		notifyingModifyListeners = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.richtext.IRichText#checkModify()
	 */
	public void checkModify() {
		try {
			if (!checkingModifyEvent) {
				checkingModifyEvent = true;
				if (modified) {
					notifyModifyListeners();
				} else {
					if (!isIE && processingJSEvent) {
						Display.getCurrent().asyncExec(new Runnable() {
							public void run() {
								if (!getText().equals(initialText)) {
									modified = true;
									notifyModifyListeners();
								}
							}
						});
					} else {
						if (!getText().equals(initialText)) {
							modified = true;
							notifyModifyListeners();
						}
					}
				}
				if (debug) {
					printDebugMessage("checkModify", "modified=" + modified); //$NON-NLS-1$ //$NON-NLS-2$	
				}
			}
		} finally {
			checkingModifyEvent = false;
		}
	}

	/**
	 * This provides an opportunity for a sub class to tidy up the rich text.
	 * 
	 * @param text
	 *            rich text encoded in HTML format
	 */
	public String tidyText(String text) {
		return text;
	}

	/**
	 * Formats the text for consumption by the JavaScript/DHTML editor.
	 * 
	 * @param text
	 *            rich text encoded in HTML format
	 */
	public String formatText(String text) {
		if (text == null || text.length() == 0) {
			return text;
		}
		StringBuffer result = new StringBuffer();
		int textSize = text.length();
		for (int i = 0; i < textSize; i++) {
			char ch = text.charAt(i);
			switch (ch) {
			case '\r':
				break;
			case '\t':
				result.append(' ');
				break;
			case '\n':
				result.append(ENCODED_NEWLINE);
				break;
			case '\'':
				result.append(ENCODED_SINGLE_QUOTE);
				break;
			case '\\':
				result.append("\\\\"); //$NON-NLS-1$
				break;
			default:
				result.append(ch);
			}
		}
		return result.toString();
	}

	/**
	 * Returns the child <code>OleControlSite</code> contained within the
	 * given <code>Composite</code>.
	 * 
	 * @param composite
	 *            a <code>Composite</code> object, presumably a
	 *            <code>Browser</code>
	 * @return an <code>OleControlSite</code> object
	 */
	protected Control getControlSite(Composite composite) {
		if (Platform.getOS().equals("win32")) { //$NON-NLS-1$
			Control[] controls = composite.getChildren();
			for (int i = 0; i < controls.length; i++) {
				String controlClass = controls[i].getClass().getName();
				if (controlClass.equals("org.eclipse.swt.browser.WebSite")) { //$NON-NLS-1$
					return controls[i];
				} else if (controls[i] instanceof Composite) {
					return getControlSite((Composite) controls[i]);
				}
			}
		}
		return null;
	}

	/**
	 * Displays the given debug message to the console.
	 */
	protected void printDebugMessage(String method, String msg, String text) {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("RichText[").append(editor.handle).append(']') //$NON-NLS-1$
				.append('.').append(method);
		if (msg != null && msg.length() > 0) {
			strBuf.append(": ").append(msg); //$NON-NLS-1$
		}
		if (text != null && text.length() > 0) {
			strBuf.append('\n').append(text);
		}
		System.out.println(strBuf);
	}

	/**
	 * Displays the given debug message to the console.
	 */
	protected void printDebugMessage(String method, String msg) {
		printDebugMessage(method, msg, null);
	}

	/**
	 * Displays the given debug message to the console.
	 */
	protected void printDebugMessage(String method) {
		printDebugMessage(method, null);
	}

	public FindReplaceAction getFindReplaceAction() {
		return findReplaceAction;
	}

	public void setFindReplaceAction(FindReplaceAction findReplaceAction) {
		if (findReplaceAction != null) {
			if (this.findReplaceAction != null
					&& this.findReplaceAction != findReplaceAction) {
				this.findReplaceAction.dispose();
			}
			this.findReplaceAction = findReplaceAction;
			this.findReplaceAction.setRichText(this);
		}
	}

	public void setInitialText(String text) {
		setText(text);
		initialText = text == null ? "" : text; //$NON-NLS-1$
		modified = false;
	}
	
	public boolean hasError() {
		return htmlFormatter.getLastErrorStr() != null;
	}
	
	public static String workaroundForObjectParamNode(String html) {
		String result = html.replaceAll("<param", "<paramTemp"); //$NON-NLS-1$ //$NON-NLS-2$
		
		return result;
	}
	
	private String unWorkaroundForObjectParamNode(String html) {
		String result = html.replaceAll("<paramTemp", "<param"); //$NON-NLS-1$ //$NON-NLS-2$		
				
		return result;
	}
	
	public String getCurrentRawText() {
		return currentRawText;
	}

	private void setCurrentRawText(String currentRawText) {
		this.currentRawText = currentRawText == null ? "" : currentRawText;	//$NON-NLS-1		
	}

}
