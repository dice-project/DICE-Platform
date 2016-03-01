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
package org.eclipse.epf.richtext;

import java.net.URL;
import java.util.Iterator;

import org.eclipse.epf.richtext.actions.FindReplaceAction;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;

/**
 * The interface for a rich text control.
 * <p>
 * A rich text control is an editable user interface object that displays rich
 * text and images.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public interface IRichText {

	public static final String PROPERTY_NAME = "richText"; //$NON-NLS-1$

	/**
	 * Returns this rich text control.
	 * 
	 * @return this rich text control
	 */
	public Control getControl();

	/**
	 * Sets the layout data.
	 * 
	 * @param layoutData
	 *            the layout data to set
	 */
	public void setLayoutData(Object layoutData);

	/**
	 * Returns the layout data.
	 * 
	 * @return this control's layout data
	 */
	public Object getLayoutData();

	/**
	 * Sets focus to this control.
	 */
	public void setFocus();

	/**
	 * Tells the control it does not have focus.
	 */
	public void setBlur();

	/**
	 * Checks whether this control has focus.
	 * 
	 * @return <code>true</code> if this control has the user-interface focus
	 */
	public boolean hasFocus();

	/**
	 * Returns the base path used for resolving text and image links.
	 * 
	 * @return the base path used for resolving links in this control
	 */
	public String getBasePath();

	/**
	 * Returns the editable state.
	 * 
	 * @return <code>true</code> if the content is ediatble
	 */
	public boolean getEditable();

	/**
	 * Sets the editable state.
	 * 
	 * @param editable
	 *            the editable state
	 */
	public void setEditable(boolean editable);

	/**
	 * Checks whether the content has been modified.
	 * 
	 * @return <code>true</code> if the content has been modified
	 */
	public boolean getModified();

	/**
	 * Sets the modified state.
	 * 
	 * @param modified
	 *            the modified state
	 */
	public void setModified(boolean modified);

	/**
	 * Returns the rich text content.
	 * 
	 * @return the rich text content formatted in a markup language
	 */
	public String getText();

	/**
	 * Sets the rich text content.
	 * 
	 * @param text
	 *            the rich text content formatted in a markup language
	 */
	public void setText(String text);

	/**
	 * Restores the rich text content back to the initial value.
	 */
	public void restoreText();

	/**
	 * Checks whether the editor content has been modified. If it has been
	 * modified, notify the modify listeners.
	 */
	public void checkModify();

	/**
	 * Returns an object that describes the current selection
	 * 
	 * @return a <code>RichTextSelection</code> object
	 */
	public RichTextSelection getSelected();

	/**
	 * Returns an application specific property value.
	 * 
	 * @param key
	 *            the name of the property
	 * @return the value of the property or <code>null</code> if it has not
	 *         been set
	 */
	public Object getData(String key);

	/**
	 * Sets an application specific property name and value.
	 * 
	 * @param key
	 *            the name of the property
	 * @param value
	 *            the property value
	 */
	public void setData(String key, Object value);

	/**
	 * Executes the given rich text command. The supported command strings are
	 * defined in <code>RichTextCommand<code>.
	 * 
	 * @param	command		a rich text command string
	 * @return	a status code returned by the executed command
	 */
	public int executeCommand(String command);

	/**
	 * Executes the given rich text command with a single parameter. The
	 * supported command strings are defined in <code>RichTextCommand<code>.
	 * 
	 * @param	command		a rich text command string
	 * @param	param		a parameter for the command or <code>null</code>
	 * @return	a status code returned by the executed command
	 */
	public int executeCommand(String command, String param);

	/**
	 * Executes the given rich text command with an array of parameters. The
	 * supported command strings are defined in <code>RichTextCommand<code>.
	 * 
	 * @param	command		a rich text command string
	 * @param	params		an array of parameters for the command or <code>null</code>
	 * @return	a status code returned by the executed command
	 */
	public int executeCommand(String command, String[] params);

	/**
	 * Returns the modify listeners attached to this control.
	 * 
	 * @return an iterator for retrieving the modify listeners
	 */
	public Iterator<ModifyListener> getModifyListeners();

	/**
	 * Adds a listener to the collection of listeners who will be notified when
	 * keys are pressed and released within this control.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addKeyListener(KeyListener listener);

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when keys are pressed and released within this control.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeKeyListener(KeyListener listener);

	/**
	 * Adds a listener to the collection of listeners who will be notified when
	 * the content of this control is modified.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addModifyListener(ModifyListener listener);

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when the content of this control is modified.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeModifyListener(ModifyListener listener);

	/**
	 * Adds the listener to the collection of listeners who will be notifed when
	 * this control is disposed.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addDisposeListener(DisposeListener listener);

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when this control is disposed.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeDisposeListener(DisposeListener listener);

	/**
	 * Adds a listener to the collection of listeners who will be notified when
	 * help events are generated for this control.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 */
	public void addHelpListener(HelpListener listener);

	/**
	 * Removes a listener from the collection of listeners who will be notified
	 * when help events are generated for this control.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 */
	public void removeHelpListener(HelpListener listener);

	/**
	 * Adds the listener to the collection of listeners who will be notifed when
	 * an event of the given type occurs within this control.
	 * 
	 * @param eventType
	 *            the type of event to listen for
	 * @param listener
	 *            the listener which should be notified when the event occurs
	 */
	public void addListener(int eventType, Listener listener);

	/**
	 * Removes the listener from the collection of listeners who will be notifed
	 * when an event of the given type occurs within this econtrol.
	 * 
	 * @param eventType
	 *            the type of event to listen to
	 * @param listener
	 *            the listener which should no longer be notified when the event
	 *            occurs
	 */
	public void removeListener(int eventType, Listener listener);
	
	/**
	 * Notifies the modify listeners
	 *
	 */
	public void notifyModifyListeners();

	/**
	 * Returns the event listeners attached to this control.
	 * 
	 * @return an iterator for retrieving the event listeners attached to this
	 *         control
	 */
	public Iterator<RichTextListener> getListeners();

	/**
	 * Returns the base URL of the rich text control whose content was last
	 * copied to the clipboard.
	 * 
	 * @return the base URL of a rich text control
	 */
	public URL getCopyURL();

	/**
	 * Sets the base URL of the rich text control whose content was last copied
	 * to the clipboard.
	 */
	public void setCopyURL();

	/**
	 * Disposes the operating system resources allocated by this control.
	 */
	public void dispose();

	/**
	 * Checks whether this control has been disposed.
	 * 
	 * @return <code>true</code> if this control is disposed successfully
	 */
	public boolean isDisposed();
	
	/**
	 * 
	 * @return the control's FindReplaceAction
	 */
	public FindReplaceAction getFindReplaceAction();
	
	/**
	 * Sets the FindReplaceAction to use
	 * @param findReplaceAction
	 */
	public void setFindReplaceAction(FindReplaceAction findReplaceAction);
	
	/**
	 * Sets the initialText variable, which stores what is saved on disk
	 * @param text
	 */
	public void setInitialText(String text);
}