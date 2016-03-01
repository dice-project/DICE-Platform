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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.richtext.actions.CopyAction;
import org.eclipse.epf.richtext.actions.CutAction;
import org.eclipse.epf.richtext.actions.FindReplaceAction;
import org.eclipse.epf.richtext.actions.PasteAction;
import org.eclipse.epf.richtext.actions.PastePlainTextAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.bindings.Binding;
import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.SWTKeySupport;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.ITextViewerExtension6;
import org.eclipse.jface.text.IUndoManager;
import org.eclipse.jface.text.IUndoManagerExtension;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.TextViewerUndoManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.HTMLTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.operations.OperationHistoryActionHandler;
import org.eclipse.ui.operations.RedoActionHandler;
import org.eclipse.ui.operations.UndoActionHandler;
import org.eclipse.ui.texteditor.IAbstractTextEditorHelpContextIds;
import org.eclipse.ui.texteditor.IReadOnlyDependent;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.IUpdate;
import org.eclipse.ui.texteditor.IWorkbenchActionDefinitionIds;

/**
 * The default rich text editor implementation.
 * <p>
 * The default rich text editor uses XHTML as the underlying markup language for
 * the rich text content. It is implemented using a <code>ViewForm</code>
 * control with a tool bar at the top, a tab folder that contains a
 * <code>RichText</code> control for entering the rich text content, and a tab
 * foler that contains a <code>StyleText</code> control for viewing and
 * modifying the XHTML representation of the rich text content.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public class RichTextEditor implements IRichTextEditor {

	// The HTML tab name.
	protected static final String HTML_TAB_NAME = RichTextResources.htmlTab_text;

	// If true, log debugging info.
	protected boolean debug;

	// The plug-in logger.
	protected Logger logger;

	// The base path used for resolving links (<href>, <img>, etc.).
	protected String basePath;

	// The editor form.
	protected ViewForm form;

	// The editor tool bar.
	protected IRichTextToolBar toolBar;

	// The editor content.
	protected Composite content;

	// The editor tab folder.
	protected CTabFolder tabFolder;

	// The rich text tab
	protected CTabItem richTextTab;

	// The HTML source tab
	protected CTabItem htmlTab;

	// The embedded rich text control.
	protected IRichText richText;

	// The underlying HTML text editor.
	protected TextViewer sourceViewer;
	
	protected IDocument currentDoc;
	
	// Drop support
	protected DropTarget sourceEditDropTarget;

	// HTML editor's context menu
	protected Menu contextMenu;

	// Has the HTML source been modified?
	protected boolean sourceModified = false;

	// The editor's editable flag.
	protected boolean editable = true;
	
	private OperationHistoryActionHandler undoAction;
	
	private OperationHistoryActionHandler redoAction;
	
	private IEditorSite editorSite;

	/** The actions registered with the editor. */
	private Map<String, IAction> fActions= new HashMap<String, IAction>(10);
	
	/** The verify key listener for activation code triggering. */
	private ActivationCodeTrigger fActivationCodeTrigger= new ActivationCodeTrigger();
	
	/** The editor's action activation codes. */
	private List fActivationCodes= new ArrayList(2);
	
	final IUndoManager undoManager= new TextViewerUndoManager(10);
	
	/**
	 * The key binding scopes of this editor.
	 * @since 2.1
	 */
	private String[] fKeyBindingScopes;

	
	protected IDocumentListener sourceEditDocumentListener= new IDocumentListener() {
		public void documentAboutToBeChanged(DocumentEvent event) {
		}
		public void documentChanged(DocumentEvent event) {
			sourceModified = true;
			if (richText != null && richText instanceof RichText) {
				richText.notifyModifyListeners();
			}
		}
	};


	// The deactivate listener for the sourceEdit control.
	protected Listener sourceEditDeactivateListener = new Listener() {
		public void handleEvent(Event event) {
			if (sourceModified) {
				updateRichText(sourceViewer.getTextWidget().getText());
				setModified(true);
				sourceModified = false;
			}
		}
	};

	// The key listener for the sourceEdit control.
	protected KeyListener sourceEditKeyListener = new KeyListener() {
		public void keyPressed(KeyEvent e) {
			Object adapter = PlatformUI.getWorkbench().getAdapter(
					IBindingService.class);
			if (adapter != null && adapter instanceof IBindingService) {
				int accel = SWTKeySupport
						.convertEventToUnmodifiedAccelerator(e);
				KeyStroke stroke = SWTKeySupport
						.convertAcceleratorToKeyStroke(accel);
				KeySequence seq = KeySequence.getInstance(stroke);
				Binding bind = ((IBindingService) adapter).getPerfectMatch(seq);
				if (bind != null) {
					ParameterizedCommand command = bind
							.getParameterizedCommand();
					if (command != null) {
						String cmdId = command.getId();
						if (cmdId != null
								&& cmdId
										.equals("org.eclipse.ui.edit.findReplace")) { //$NON-NLS-1$
							richText.getFindReplaceAction().execute(RichTextEditor.this);
						}
					}
				}
			}
		}

		public void keyReleased(KeyEvent e) {
		}
	};

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param style
	 *            the editor style
	 */
	public RichTextEditor(Composite parent, int style, IEditorSite editorSite) {
		this(parent, style, editorSite, null);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param style
	 *            the editor style
	 * @param basePath
	 *            the base path used for resolving links
	 */
	public RichTextEditor(Composite parent, int style, IEditorSite editorSite, String basePath) {
		this.basePath = basePath;
		this.editorSite = editorSite;
		debug = RichTextPlugin.getDefault().isDebugging();
		logger = RichTextPlugin.getDefault().getLogger();
		init(parent, style);
	}

	/**
	 * Initializes this editor.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param style
	 *            the editor style
	 */
	protected void init(Composite parent, int style) {
		try {
			form = new ViewForm(parent, style);
			form.marginHeight = 0;
			form.marginWidth = 0;

			toolBar = new RichTextToolBar(form, SWT.FLAT, this);

			content = new Composite(form, SWT.FLAT);
			GridLayout layout = new GridLayout();
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			content.setLayout(layout);

			tabFolder = createEditorTabFolder(content, style);

			form.setTopCenter(((RichTextToolBar)toolBar).getToolbarMgr().getControl());
			form.setTopLeft(((RichTextToolBar)toolBar).getToolbarMgrCombo().getControl());
			form.setContent(content);
		} catch (Exception e) {
			logger.logError(e);
		}
	}

	/**
	 * Returns the form control.
	 * 
	 * @return the form control
	 */
	public Control getControl() {
		return form;
	}

	/**
	 * Returns the rich text control embedded within this editor.
	 */
	public IRichText getRichTextControl() {
		return richText;
	}

	/**
	 * Sets the layout data.
	 * 
	 * @param layoutData
	 *            the layout data to set
	 */
	public void setLayoutData(Object layoutData) {
		if (form != null) {
			form.setLayoutData(layoutData);
		}
	}

	/**
	 * Returns the layout data.
	 * 
	 * @return the editor's layout data
	 */
	public Object getLayoutData() {
		if (form != null) {
			return form.getLayoutData();
		}
		return null;
	}

	/**
	 * Sets focus to this editor.
	 */
	public void setFocus() {
		if (richText != null) {
			richText.setFocus();
		}
		setSelection(0);
		if (toolBar != null && tabFolder != null) {
			toolBar.updateToolBar(editable);
		}

	}

	/**
	 * Tells the control it does not have focus.
	 */
	public void setBlur() {
		if (richText != null) {
			richText.setBlur();
		}
	}
	
	/**
	 * Checks whether this editor has focus.
	 * 
	 * @return <code>true</code> if this editor has the user-interface focus
	 */
	public boolean hasFocus() {
		if (richText != null) {
			return richText.hasFocus();
		}
		return false;
	}

	/**
	 * Selects the Rich Text or HTML tab.
	 * 
	 * @param index
	 *            <code>0</code> for the Rich Text tab, <code>1</code> for
	 *            the HTML tab.
	 */
	public void setSelection(int index) {
		if (tabFolder != null) {
			tabFolder.setSelection(index);
		}
	}

	/**
	 * Returns the base path used for resolving text and image links.
	 * 
	 * @return the base path used for resolving links specified with <href>,
	 *         <img>, etc.
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
		if (richText != null) {
			return richText.getCopyURL();
		}
		return null;
	}

	/**
	 * Sets the base URL of the rich text control whose content was last copied
	 * to the clipboard.
	 */
	public void setCopyURL() {
		if (richText != null) {
			richText.setCopyURL();
		}
	}

	/**
	 * Returns the editable state.
	 * 
	 * @return <code>true</code> if the content can be edited
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
		if (toolBar != null && tabFolder != null) {
			toolBar.updateToolBar(editable);
		}
		if (richText != null) {
			richText.setEditable(editable);
		}
		if (sourceViewer != null) {
			sourceViewer.setEditable(editable);
		}
	}

	/**
	 * Checks whether the content has been modified.
	 * 
	 * @return <code>true</code> if the content has been modified
	 */
	public boolean getModified() {
		if (richText != null) {
			return richText.getModified();
		}
		return false;
	}

	/**
	 * Sets the modified state.
	 * 
	 * @param modified
	 *            the modified state
	 */
	public void setModified(boolean modified) {
		if (richText != null) {
			richText.setModified(modified);
		}
	}

	/**
	 * Returns the rich text content.
	 * 
	 * @return the rich text content formatted in XHTML
	 */
	public String getText() {
		if (sourceModified) {
			setText(getSourceEdit().getText());
			setModified(true);
			sourceModified = false;
		}
		if (richText != null) {
			return richText.getText();
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * Sets the rich text content.
	 * 
	 * @param text
	 *            the rich text content in XHTML format
	 */
	public void setText(String text) {
		if (richText != null) {
			richText.setText(text);
		}
		sourceModified = false;
		if (tabFolder != null) {
			if (toolBar != null) {
				toolBar.updateToolBar(editable);
			}
			if (getSourceEdit() != null) {
				removeModifyListeners();
				currentDoc.set(text);
				addModifyListeners();
			}
		}
	}
	
	protected void addModifyListeners() {
		if (currentDoc != null) {
			currentDoc.addDocumentListener(sourceEditDocumentListener);
		}
	}

	protected void removeModifyListeners() {
		if (currentDoc != null) {
			currentDoc.removeDocumentListener(sourceEditDocumentListener);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.richtext.IRichText#checkModify()
	 */
	public void checkModify() {
		richText.checkModify();
		if (sourceModified) {
			notifyModifyListeners();
		}
		if (debug) {
			printDebugMessage("checkModify", "modified=" + sourceModified); //$NON-NLS-1$ //$NON-NLS-2$	
		}
	}

	/**
	 * Restores the rich text content back to the initial value.
	 */
	public void restoreText() {
		if (richText != null) {
			richText.restoreText();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.richtext.IRichText#getSelected()
	 */
	public RichTextSelection getSelected() {
		if (tabFolder.getSelection() == htmlTab) {
			String HTMLsource = getSourceEdit().getText();
			Point sel = sourceViewer.getSelectedRange();
			int selStartIndex = sel.x;
			int selEndIndex = sel.x + sel.y - 1;
			richText.getSelected().clear();
			richText.getSelected().setText(HTMLsource.substring(selStartIndex, selEndIndex + 1));
		}
		return richText.getSelected();
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
		if (richText != null) {
			return richText.getData(key);
		}
		return null;
	}

	/**
	 * Sets an application specific property name and value.
	 * 
	 * @param key
	 *            the name of the property
	 * @param value
	 *            the new value for the property
	 */
	public void setData(String key, Object value) {
		if (richText != null) {
			richText.setData(key, value);
		}
	}

	/**
	 * Executes the given rich text command. The supported command strings are
	 * defined in <code>RichTextCommand<code>.
	 * 
	 * @param	command		a rich text command string
	 * @return	a status code returned by the executed command
	 */
	public int executeCommand(String command) {
		if (richText != null) {
			return richText.executeCommand(command);
		}
		return 0;
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
		if (richText != null) {
			return richText.executeCommand(command, param);
		}
		return 0;
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
		if (richText != null) {
			return richText.executeCommand(command, params);
		}
		return 0;
	}

	/**
	 * Disposes the operating system resources allocated by this editor.
	 */
	public void dispose() {
		if (contextMenu != null && !contextMenu.isDisposed()) {
			contextMenu.dispose();
			contextMenu = null;
		}
		if (sourceEditDropTarget != null) {
			sourceEditDropTarget.dispose();
			sourceEditDropTarget = null;
		}
		if (fActivationCodeTrigger != null) {
			fActivationCodeTrigger.uninstall();
			fActivationCodeTrigger= null;
		}
		removeModifyListeners();
		if (getSourceEdit() != null) {
			getSourceEdit().removeListener(SWT.Deactivate,
					sourceEditDeactivateListener);
			getSourceEdit().removeKeyListener(sourceEditKeyListener);
			sourceEditDeactivateListener = null;
			sourceEditKeyListener = null;
		}
		
		if (sourceViewer != null) {
			sourceViewer= null;
		}

		if (fActions != null) {
			fActions.clear();
			fActions= null;
		}
		if (fActivationCodes != null) {
			fActivationCodes.clear();
			fActivationCodes= null;
		}

		if (richText != null) {
			richText.dispose();
			richText = null;
		}
	}

	/**
	 * Checks whether this control has been disposed.
	 * 
	 * @return <code>true</code> if this control is disposed successfully
	 */
	public boolean isDisposed() {
		if (richText != null) {
			return richText.isDisposed();
		}
		return true;
	}

	/**
	 * Returns the modify listeners attached to this editor.
	 * 
	 * @return an iterator for retrieving the modify listeners
	 */
	public Iterator<ModifyListener> getModifyListeners() {
		if (richText != null) {
			richText.getModifyListeners();
		}
		return null;
	}

	/**
	 * Adds a listener to the collection of listeners who will be notified when
	 * keys are pressed and released within this editor.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addKeyListener(KeyListener listener) {
		if (richText != null) {
			richText.addKeyListener(listener);
		}
	}

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when keys are pressed and released within this editor.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeKeyListener(KeyListener listener) {
		if (richText != null) {
			richText.removeKeyListener(listener);
		}
	}

	/**
	 * Adds a listener to the collection of listeners who will be notified when
	 * the content of this editor is modified.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addModifyListener(ModifyListener listener) {
		if (richText != null) {
			richText.addModifyListener(listener);
		}
	}

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when the content of this editor is modified.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeModifyListener(ModifyListener listener) {
		if (richText != null) {
			richText.removeModifyListener(listener);
		}
	}

	/**
	 * Adds the listener to the collection of listeners who will be notifed when
	 * this editor is disposed.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addDisposeListener(DisposeListener listener) {
		if (richText != null) {
			richText.addDisposeListener(listener);
		}
	}

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when this editor is disposed.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeDisposeListener(DisposeListener listener) {
		if (richText != null) {
			richText.removeDisposeListener(listener);
		}
	}

	/**
	 * Adds a listener to the collection of listeners who will be notified when
	 * help events are generated for this editor.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addHelpListener(HelpListener listener) {
		if (richText != null) {
			richText.addHelpListener(listener);
		}
	}

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when help events are generated for this editor.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeHelpListener(HelpListener listener) {
		if (richText != null) {
			richText.removeHelpListener(listener);
		}
	}

	/**
	 * Adds the listener to the collection of listeners who will be notifed when
	 * an event of the given type occurs within this editor.
	 * 
	 * @param eventType
	 *            the type of event to listen for
	 * @param listener
	 *            the listener which should be notified when the event occurs
	 */
	public void addListener(int eventType, Listener listener) {
		if (richText != null) {
			richText.addListener(eventType, listener);
		}
	}

	/**
	 * Removes the listener from the collection of listeners who will be notifed
	 * when an event of the given type occurs within this editor.
	 * 
	 * @param eventType
	 *            the type of event to listen for
	 * @param listener
	 *            the listener which should no longer be notified when the event
	 *            occurs
	 */
	public void removeListener(int eventType, Listener listener) {
		if (richText != null) {
			richText.removeListener(eventType, listener);
		}
	}

	/**
	 * Returns the event listeners attached to this editor.
	 * 
	 * @return an iterator for retrieving the event listeners attached to this
	 *         editor
	 */
	public Iterator<RichTextListener> getListeners() {
		if (richText != null) {
			return richText.getListeners();
		}
		return null;
	}

	/**
	 * Notifies the modify listeners that the rich text editor content has
	 * changed.
	 */
	public void notifyModifyListeners() {
		if (richText != null) {
			Event event = new Event();
			event.display = Display.getCurrent();
			event.widget = richText.getControl();

			for (Iterator<ModifyListener> i = getModifyListeners(); i != null && i.hasNext();) {
				ModifyListener listener = i.next();
				listener.modifyText(new ModifyEvent(event));
			}
		}
	}

	/**
	 * Fills the tool bar with action items.
	 * 
	 * @param toolBar
	 *            a tool bar contain rich text actions
	 */
	public void fillToolBar(IRichTextToolBar toolBar) {
	}

	/**
	 * Creates the underlying rich text control.
	 * 
	 * @param parent
	 *            the parent composite
	 * @param style
	 *            the style for the control
	 * @param basePath
	 *            the path used for resolving links
	 */
	protected IRichText createRichTextControl(Composite parent, int style,
			String basePath) {
		return new RichText(parent, style, basePath);
	}

	/**
	 * Creates the editor tab folder.
	 * 
	 * @param parent
	 *            the parent control
	 * @param style
	 *            the style for the control
	 * @return a new editor toolbar
	 */
	protected CTabFolder createEditorTabFolder(Composite parent, int style) {
		CTabFolder folder = new CTabFolder(parent, SWT.FLAT | SWT.BOTTOM);
		folder.setLayout(new GridLayout(1, true));
		folder.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite richTextComposite = new Composite(folder, SWT.FLAT);
		GridLayout richTextCompositeLayout = new GridLayout(1, false);
		richTextCompositeLayout.marginHeight = 0;
		richTextCompositeLayout.marginWidth = 0;
		richTextComposite.setLayout(richTextCompositeLayout);
		richTextComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		richText = createRichTextControl(richTextComposite, style, basePath);
		richText.setData(PROPERTY_NAME, this);
		richText.getFindReplaceAction().setRichText(this);

		richTextTab = new CTabItem(folder, SWT.FLAT);
		richTextTab.setText(RichTextResources.richTextTab_text);
		richTextTab.setToolTipText(RichTextResources.richTextTab_toolTipText);
		richTextTab.setControl(richTextComposite);

		Composite htmlComposite = new Composite(folder, SWT.FLAT);
		htmlComposite.setLayout(new FillLayout());

		sourceViewer = new TextViewer(htmlComposite, SWT.FLAT | SWT.MULTI
				| SWT.WRAP | SWT.V_SCROLL);
		sourceViewer.setUndoManager(undoManager);
		setDocument(null);
		addModifyListeners();
		getSourceEdit().addListener(SWT.Deactivate, sourceEditDeactivateListener);
		getSourceEdit().addKeyListener(sourceEditKeyListener);
		contextMenu = new Menu(parent.getShell(), SWT.POP_UP);
		getSourceEdit().setMenu(contextMenu);
		// FIXME! This opens up a can of worms, especially with DBCS characters.
		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=179432. 
		//addDropSupportToStyledText();
		fillContextMenu(contextMenu);
		
		
		htmlTab = new CTabItem(folder, SWT.NONE);
		htmlTab.setText(HTML_TAB_NAME);
		htmlTab.setToolTipText(RichTextResources.htmlTab_toolTipText); 
		htmlTab.setControl(htmlComposite);

		folder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				CTabItem item = (CTabItem) event.item;
				if (item.getText().equals(HTML_TAB_NAME)) {
					removeModifyListeners();
					currentDoc.set(getText());
					sourceModified = false;
					addModifyListeners();
					if (toolBar != null) {
						toolBar.updateToolBar(editable);
					}
				} else {
					updateRichText(getSourceEdit().getText());
					setModified(true);
					if (toolBar != null) {
						toolBar.updateToolBar(editable);
					}
				}
			}
		});
		fillToolBar(toolBar);
		
		initializeActivationCodeTrigger();
		createActions();
		
		folder.setSelection(0);

		return folder;
	}

	
	private void setDocument(IDocument doc) {
		if (doc == null) {
			doc = new Document();
		}
		// clean up old doc
		undoManager.disconnect();
		IDocument oldDoc = sourceViewer.getDocument();
		if (oldDoc != null) {
			oldDoc.removeDocumentListener(sourceEditDocumentListener);
		}

		// hook up new doc
		currentDoc = doc;
		sourceViewer.setDocument(currentDoc);
		currentDoc.addDocumentListener(sourceEditDocumentListener);
		undoManager.connect(sourceViewer);
		if (undoAction != null) {
			undoAction.setContext(getUndoContext());
		}
		if (redoAction != null) {
			redoAction.setContext(getUndoContext());
		}
	}
	
	/**
	 * Returns the HTML source edit control.
	 * 
	 * @return a <code>StyleText</code> object.
	 */
	public StyledText getSourceEdit() {
		if (sourceViewer != null) {
			return sourceViewer.getTextWidget();
		}
		return null;
	}

	/**
	 * Inserts text at the selection (overwriting the selection).
	 */
	public void addHTML(String text) {
		if (text == null || text.length() == 0) 
			return;
		if (tabFolder.getSelection() == richTextTab) {
			//To avoid encoding of javascript
			text = text.replaceAll("&", "&amp;");  //$NON-NLS-1$//$NON-NLS-2$
			executeCommand(RichTextCommand.ADD_HTML, text);
		} else if (tabFolder.getSelection() == htmlTab) {
			String oldHTML = getSourceEdit().getText();
			Point sel = sourceViewer.getSelectedRange();
			int selStartIndex = sel.x;
			int selEndIndex = sel.x + sel.y - 1;
			String newHTML = oldHTML.substring(0, selStartIndex) + text
					+ oldHTML.substring(selEndIndex + 1);
			removeModifyListeners();
			currentDoc.set(newHTML);
			addModifyListeners();
			updateRichText(newHTML);
		}
	}

	
	/**
	 * Inserts an image at the selection (overwriting the selection).
	 */
	public void addImage(String imageURL, String height, String width, String altTag) {
		if (tabFolder.getSelection() == richTextTab) {
			executeCommand(
					RichTextCommand.ADD_IMAGE,
					new String[] {
							imageURL,
							height, width, altTag });
		} else if (tabFolder.getSelection() == htmlTab) {
			StringBuffer imageLink = new StringBuffer();
			// order of these attributes is the same as JTidy'ed HTML
			imageLink.append("<img"); //$NON-NLS-1$
			if (height.length() > 0) {
				imageLink.append(" height=\"" + height + "\""); //$NON-NLS-1$ //$NON-NLS-2$
			}
			if (altTag.length() > 0) {
				imageLink.append(" alt=\"" + altTag + "\""); //$NON-NLS-1$ //$NON-NLS-2$
			}
			imageLink.append(" src=\"" + imageURL + "\""); //$NON-NLS-1$ //$NON-NLS-2$
			if (width.length() > 0) {
				imageLink.append(" width=\"" + width + "\""); //$NON-NLS-1$ //$NON-NLS-2$
			}
			imageLink.append(" />"); //$NON-NLS-1$
			String oldHTML = getSourceEdit().getText();
			Point sel = sourceViewer.getSelectedRange();
			int selStartIndex = sel.x;
			int selEndIndex = sel.x + sel.y - 1;
			String newHTML = oldHTML.substring(0, selStartIndex) + imageLink.toString()
					+ oldHTML.substring(selEndIndex + 1);
			removeModifyListeners();
			currentDoc.set(newHTML);
			addModifyListeners();
			updateRichText(newHTML);
		}
	}

	/**
	 * Checks whether the HTML tab is selected.
	 * 
	 * @return <code>true</code> if the HTML tab is selected.
	 */
	public boolean isHTMLTabSelected() {
		return (tabFolder.getSelection() == htmlTab);
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
				CutAction action = new CutAction(RichTextEditor.this);
				action.execute(RichTextEditor.this);
			}
		});
		final MenuItem copyItem = new MenuItem(contextMenu, SWT.PUSH);
		copyItem.setText(RichTextResources.copyAction_text); 
		copyItem.setImage(RichTextImages.IMG_COPY);
		copyItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				CopyAction action = new CopyAction(RichTextEditor.this);
				action.execute(RichTextEditor.this);
			}
		});
		final MenuItem pasteItem = new MenuItem(contextMenu, SWT.PUSH);
		pasteItem.setText(RichTextResources.pasteAction_text); 
		pasteItem.setImage(RichTextImages.IMG_PASTE);
		pasteItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				PasteAction action = new PasteAction(RichTextEditor.this);
				action.execute(RichTextEditor.this);
			}
		});
		
		final MenuItem pastePlainTextItem = new MenuItem(contextMenu, SWT.PUSH);
		pastePlainTextItem.setText(RichTextResources.pastePlainTextAction_text);
		pastePlainTextItem.setImage(RichTextImages.IMG_PASTE_PLAIN_TEXT);
		pastePlainTextItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				PastePlainTextAction action = new PastePlainTextAction(RichTextEditor.this);
				action.execute(RichTextEditor.this);
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
				String selectedText = getSelected().getText();
				boolean selection = selectedText.length() > 0;
				cutItem.setEnabled(editable && selection);
				copyItem.setEnabled(selection);
				pasteItem.setEnabled(editable && (html != null));
				pastePlainTextItem.setEnabled(editable && (text != null));
			}
		});
	}

	/**
	 * Updates the content of the rich text control without updating the HTML
	 * source editor.
	 * <p>
	 * This method should be called by the HTML source editor to sync up its
	 * content with the rich text control.
	 * 
	 * @param text
	 *            the rich text content in XHTML format
	 */
	private void updateRichText(String text) {
		if (richText != null) {
			richText.setText(text);
			richText.checkModify();
		}
		sourceModified = false;
		if (tabFolder != null) {
			if (toolBar != null) {
				toolBar.updateToolBar(editable);
			}
		}
	}
	
	private void addDropSupportToStyledText() {
		// this function based heavily on the example at:
		// http://www.eclipse.org/articles/Article-SWT-DND/DND-in-SWT.html
		
		 	// Allow data to be copied to the drop target
			int operations = DND.DROP_MOVE |  DND.DROP_COPY | DND.DROP_DEFAULT;
			sourceEditDropTarget = new DropTarget(getSourceEdit(), operations);
		 
			// Receive data in Text or HTML format
			final TextTransfer textTransfer = TextTransfer.getInstance();
			final HTMLTransfer htmlTransfer = HTMLTransfer.getInstance();
			Transfer[] types = new Transfer[] {htmlTransfer, textTransfer};
			sourceEditDropTarget.setTransfer(types);
			 
			sourceEditDropTarget.addDropListener(new DropTargetListener() {
			  public void dragEnter(DropTargetEvent event) {
			     if (event.detail == DND.DROP_DEFAULT) {
			         if ((event.operations & DND.DROP_COPY) != 0) {
			             event.detail = DND.DROP_COPY;
			         } else {
			             event.detail = DND.DROP_NONE;
			         }
			     }
			     if (!getEditable()) {
		             event.detail = DND.DROP_NONE;
			     }
			     // will accept text but prefer to have HTML dropped
			     for (int i = 0; i < event.dataTypes.length; i++) {
			         if (htmlTransfer.isSupportedType(event.dataTypes[i])){
			             event.currentDataType = event.dataTypes[i];
			             break;
			         }
			     }
			   }
			   public void dragOver(DropTargetEvent event) {
			        event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_INSERT_AFTER | DND.FEEDBACK_SCROLL;
			    }
			    public void dragOperationChanged(DropTargetEvent event) {
			        if (event.detail == DND.DROP_DEFAULT) {
			            if ((event.operations & DND.DROP_COPY) != 0) {
			                event.detail = DND.DROP_COPY;
			            } else {
			                event.detail = DND.DROP_NONE;
			            }
			        }
			    }
			    public void dragLeave(DropTargetEvent event) {
			    }
			    public void dropAccept(DropTargetEvent event) {
			    }
			    public void drop(DropTargetEvent event) {
			        if (textTransfer.isSupportedType(event.currentDataType) || 
			        		htmlTransfer.isSupportedType(event.currentDataType)) {
			            String text = (String)event.data;
			            addHTML(text);
			        }
			    }
			});
	}
	
	/**
	 * Displays the given debug message to the console.
	 */
	private void printDebugMessage(String method, String msg, String text) {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("RichTextEditor[").append(richText.getControl().handle).append(']') //$NON-NLS-1$
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
	private void printDebugMessage(String method, String msg) {
		printDebugMessage(method, msg, null);
	}
	
	public FindReplaceAction getFindReplaceAction() {
		return richText.getFindReplaceAction();
	}

	public void setFindReplaceAction(FindReplaceAction findReplaceAction) {
		if (richText != null) {
			richText.setFindReplaceAction(findReplaceAction);
			richText.getFindReplaceAction().setRichText(this);

		}
	}
	
	public void setInitialText(String text) {
		if (richText != null) {
			richText.setInitialText(text);
		}
		if (getSourceEdit() != null) {
			removeModifyListeners();
			setDocument(new Document(text));
			addModifyListeners();
		}

	}
	
	
	/**
	 * from org.eclipse.ui.texteditor.AbstractTextEditor#getUndoContext()
	 * Returns this editor's viewer's undo manager undo context.
	 *
	 * @return the undo context or <code>null</code> if not available
	 * @since 3.1
	 */
	private IUndoContext getUndoContext() {
		if (sourceViewer instanceof ITextViewerExtension6) {
			IUndoManager undoManager= ((ITextViewerExtension6)sourceViewer).getUndoManager();
			if (undoManager instanceof IUndoManagerExtension)
				return ((IUndoManagerExtension)undoManager).getUndoContext();
		}
		return null;
	}
	
	protected void createActions() {
		createUndoRedoActions();
		// select all
		Action selectAllAction = new Action() {
			@Override
			public void run() {
				getSourceEdit().selectAll();
			}
		};
		selectAllAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.SELECT_ALL);
		registerAction(ActionFactory.SELECT_ALL.getId(), selectAllAction);
	}

	/*
	 * from org.eclipse.ui.texteditor.AbstractTextEditor#createUndoRedoActions()
	 */
	protected void createUndoRedoActions() {
		IUndoContext undoContext= getUndoContext();
		if (undoContext != null) {
			// Use actions provided by global undo/redo
			
			// Create the undo action
			undoAction= new UndoActionHandler(getEditorSite(), undoContext);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(undoAction, IAbstractTextEditorHelpContextIds.UNDO_ACTION);
			undoAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.UNDO);
			registerAction(ITextEditorActionConstants.UNDO, undoAction);

			// Create the redo action.
			redoAction= new RedoActionHandler(getEditorSite(), undoContext);
			PlatformUI.getWorkbench().getHelpSystem().setHelp(redoAction, IAbstractTextEditorHelpContextIds.REDO_ACTION);
			redoAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.REDO);
			registerAction(ITextEditorActionConstants.REDO, redoAction);
		}
	}
	
	private IEditorSite getEditorSite() {
		return editorSite;
	}


	/**
	 * Registers the given undo/redo action under the given ID and
	 * ensures that previously installed actions get disposed. It
	 * also takes care of re-registering the new action with the
	 * global action handler.
	 * 
	 * @param actionId	the action id under which to register the action
	 * @param action	the action to register
	 * @since 3.1
	 */
	private void registerAction(String actionId, IAction action) {
		IAction oldAction= getAction(actionId);
		if (oldAction instanceof OperationHistoryActionHandler)
			((OperationHistoryActionHandler)oldAction).dispose();

		setAction(actionId, action);
		
		IActionBars actionBars= getEditorSite().getActionBars();
		if (actionBars != null)
			actionBars.setGlobalActionHandler(actionId, action);
	}

	
	/*
	 * @see ITextEditor#getAction(String)
	 */
	public IAction getAction(String actionID) {
		assert actionID != null;
		IAction action= (IAction) fActions.get(actionID);

//		if (action == null) {
//			action= findContributedAction(actionID);
//			if (action != null)
//				setAction(actionID, action);
//		}

		return action;
	}

	/*
	 * @see ITextEditor#setAction(String, IAction)
	 */
	public void setAction(String actionID, IAction action) {
		assert actionID != null;
		if (action == null) {
			action= (IAction) fActions.remove(actionID);
			if (action != null)
				fActivationCodeTrigger.unregisterActionFromKeyActivation(action);
		} else {
			fActions.put(actionID, action);
			fActivationCodeTrigger.registerActionForKeyActivation(action);
		}
	}
	
	/**
	 * Initializes the activation code trigger.
	 *
	 * @since 2.1
	 */
	private void initializeActivationCodeTrigger() {
		fActivationCodeTrigger.install();
		fActivationCodeTrigger.setScopes(fKeyBindingScopes);
	}

	/**
	 * Internal key verify listener for triggering action activation codes.
	 */
	class ActivationCodeTrigger implements VerifyKeyListener {

		/** Indicates whether this trigger has been installed. */
		private boolean fIsInstalled= false;
		/**
		 * The key binding service to use.
		 * @since 2.0
		 */
		private IKeyBindingService fKeyBindingService;
		
		/*
		 * @see VerifyKeyListener#verifyKey(org.eclipse.swt.events.VerifyEvent)
		 */
		public void verifyKey(VerifyEvent event) {

			ActionActivationCode code= null;
			int size= fActivationCodes.size();
			for (int i= 0; i < size; i++) {
				code= (ActionActivationCode) fActivationCodes.get(i);
				if (code.matches(event)) {
					IAction action= getAction(code.fActionId);
					if (action != null) {

						if (action instanceof IUpdate)
							((IUpdate) action).update();

						if (!action.isEnabled() && action instanceof IReadOnlyDependent) {
							IReadOnlyDependent dependent= (IReadOnlyDependent) action;
							boolean writable= dependent.isEnabled(true);
							if (writable) {
								event.doit= false;
								return;
							}
						} else if (action.isEnabled()) {
							event.doit= false;
							action.run();
							return;
						}
					}
				}
			}
		}

		/**
		 * Installs this trigger on the editor's text widget.
		 * @since 2.0
		 */
		public void install() {
			if (!fIsInstalled) {

				if (sourceViewer instanceof ITextViewerExtension) {
					ITextViewerExtension e= (ITextViewerExtension) sourceViewer;
					e.prependVerifyKeyListener(this);
				} else {
					StyledText text= sourceViewer.getTextWidget();
					text.addVerifyKeyListener(this);
				}

				fKeyBindingService= getEditorSite().getKeyBindingService();
				fIsInstalled= true;
			}
		}

		/**
		 * Uninstalls this trigger from the editor's text widget.
		 * @since 2.0
		 */
		public void uninstall() {
			if (fIsInstalled) {

				if (sourceViewer instanceof ITextViewerExtension) {
					ITextViewerExtension e= (ITextViewerExtension) sourceViewer;
					e.removeVerifyKeyListener(this);
				} else if (sourceViewer != null) {
					StyledText text= sourceViewer.getTextWidget();
					if (text != null && !text.isDisposed())
						text.removeVerifyKeyListener(fActivationCodeTrigger);
				}

				fIsInstalled= false;
				fKeyBindingService= null;
			}
		}

		/**
		 * Registers the given action for key activation.
		 * @param action the action to be registered
		 * @since 2.0
		 */
		public void registerActionForKeyActivation(IAction action) {
			if (action.getActionDefinitionId() != null)
				fKeyBindingService.registerAction(action);
		}

		/**
		 * The given action is no longer available for key activation
		 * @param action the action to be unregistered
		 * @since 2.0
		 */
		public void unregisterActionFromKeyActivation(IAction action) {
			if (action.getActionDefinitionId() != null)
				fKeyBindingService.unregisterAction(action);
		}

		/**
		 * Sets the key binding scopes for this editor.
		 * @param keyBindingScopes the key binding scopes
		 * @since 2.1
		 */
		public void setScopes(String[] keyBindingScopes) {
			if (keyBindingScopes != null && keyBindingScopes.length > 0)
				fKeyBindingService.setScopes(keyBindingScopes);
		}
	}
	
	/**
	 * Representation of action activation codes.
	 */
	static class ActionActivationCode {

		/** The action id. */
		public String fActionId;
		/** The character. */
		public char fCharacter;
		/** The key code. */
		public int fKeyCode= -1;
		/** The state mask. */
		public int fStateMask= SWT.DEFAULT;

		/**
		 * Creates a new action activation code for the given action id.
		 * @param actionId the action id
		 */
		public ActionActivationCode(String actionId) {
			fActionId= actionId;
		}

		/**
		 * Returns <code>true</code> if this activation code matches the given verify event.
		 * @param event the event to test for matching
		 * @return whether this activation code matches <code>event</code>
		 */
		public boolean matches(VerifyEvent event) {
			return (event.character == fCharacter &&
						(fKeyCode == -1 || event.keyCode == fKeyCode) &&
						(fStateMask == SWT.DEFAULT || event.stateMask == fStateMask));
		}
	}

}
