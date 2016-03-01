/*******************************************************************************
 * Copyright (c) 2000, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.gef.dnd;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

/**
 * Holds on to the object being transferred in a field so that
 * DropTargetListeners can know what's being dragged before the drop occurs. The
 * object isn't converted to bytes, so this Transfer will only work when
 * dragging within the same instance of Eclipse. Subclasses should maintain a
 * single instance of their Transfer and provide a static method to obtain that
 * instance.
 */
public abstract class SimpleObjectTransfer extends ByteArrayTransfer {

	private Object object;
	private long startTime;

	/**
	 * Returns the Object.
	 * 
	 * @return The Object
	 */
	public Object getObject() {
		return object;
	}

	/**
	 * The data object is not converted to bytes. It is held onto in a field.
	 * Instead, a checksum is written out to prevent unwanted drags across
	 * mulitple running copies of Eclipse.
	 * 
	 * @see org.eclipse.swt.dnd.Transfer#javaToNative(Object, TransferData)
	 */
	public void javaToNative(Object object, TransferData transferData) {
		setObject(object);
		startTime = System.currentTimeMillis();
		if (transferData != null)
			super.javaToNative(String.valueOf(startTime).getBytes(),
					transferData);
	}

	/**
	 * The data object is not converted to bytes. It is held onto in a field.
	 * Instead, a checksum is written out to prevent unwanted drags across
	 * mulitple running. copies of Eclipse.
	 * 
	 * @see org.eclipse.swt.dnd.Transfer#nativeToJava(TransferData)
	 */
	public Object nativeToJava(TransferData transferData) {
		byte bytes[] = (byte[]) super.nativeToJava(transferData);
		if (bytes == null) {
			return null;
		}
		long startTime = Long.parseLong(new String(bytes));
		return (this.startTime == startTime) ? getObject() : null;
	}

	/**
	 * Sets the Object.
	 * 
	 * @param obj
	 *            The Object
	 */
	public void setObject(Object obj) {
		object = obj;
	}

}
