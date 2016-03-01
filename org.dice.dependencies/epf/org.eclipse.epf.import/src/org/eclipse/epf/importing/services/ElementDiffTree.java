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
package org.eclipse.epf.importing.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;


/**
 * Displays the element differences in a tree.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ElementDiffTree {

	public static final int DIFF_STATE_SAME = 0;

	public static final int DIFF_STATE_NEW = 1;

	public static final int DIFF_STATE_CHANGED = 4;

	public static final int DIFF_STATE_OLD_ONLY = 5;

	private MethodElement e1;

	private MethodElement e2;

	// The parent in the base library if e2 is a new element.
	private MethodElement e1_parent;

	private ElementDiffTree parent = null;

	// Define the diff state of the second element compared to the first one.
	private int diffState = DIFF_STATE_SAME;

	private List children = new ArrayList();

	private boolean hasDirtyChild = false;

	/**
	 * Creates a new instance.
	 */
	public ElementDiffTree(MethodElement e1, MethodElement e2) {
		this(e1, e2, null);
	}

	/**
	 * Creates a new instance.
	 */
	public ElementDiffTree(MethodElement e1, MethodElement e2,
			MethodElement e1_parent) {
		this.e1_parent = e1_parent;
		this.e1 = e1;
		this.e2 = e2;

		resolveDiffState();
	}
	
	/**
	 * Returns the base method element.
	 */
	public MethodElement getBaseElement() {
		return e1;
	}

	/**
	 * Returns the base method element's parent.
	 */
	public MethodElement getBaseParentElement() {
		return e1_parent;
	}

	/**
	 * Returns the import method element.
	 */
	public MethodElement getImportElement() {
		return e2;
	}

	/**
	 * Checks whether the element is new to the library.
	 */
	public boolean isNew() {
		return (e1 == null) && (e2 != null);
	}

	/**
	 * Checks whether the element exists only in the library and not in the
	 * configuration to be imported.
	 */
	public boolean isOldOnly() {
		return (e1 != null) && (e2 == null);
	}

	/**
	 * Returns true if both base and import elements are not null, 
	 * otherwise returns false.
	 */
	public boolean isBoth() {
		return (e1 != null) && (e2 != null);
	}

	/**
	 * Adds a child node.
	 */
	public void addChild(ElementDiffTree child) {
		children.add(child);
		child.setParent(this);
	}

	/**
	 * hasDirtyChild attribute.
	 */
	public boolean hasDirtyChild() {
		return this.hasDirtyChild;
	}

	/**
	 * Sets hasDirtyChild attribute.
	 */
	public void setDirtyChild(boolean flag) {
		hasDirtyChild = hasDirtyChild || flag;
		if (hasDirtyChild && parent != null) {
			parent.setDirtyChild(hasDirtyChild);
		}
	}

	private void setParent(ElementDiffTree parent) {
		this.parent = parent;
		if (diffState != DIFF_STATE_SAME && parent != null) {
			parent.setDirtyChild(true);
		}
	}

	/**
	 * Returns the parent.
	 */
	public ElementDiffTree getParent() {
		return this.parent;
	}

	/**
	 * hasChildren attribute.
	 */
	public boolean hasChildren() {
		return (children != null) && (children.size() > 0);
	}

	/**
	 * Returns the child list.
	 */
	public List getChildren() {
		return children;
	}

	/**
	 * Returns the diff state of the second element comparing to the first one.
	 */
	public int getDiffState() {
		return diffState;
	}

	/**
	 * Returns a message string.
	 */
	public String getDiffMessage() {
		return "[" + getVersion(e1) + "] : [" + getVersion(e2) + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	private void resolveDiffState() {
		// how to fidd two elements?
		// 1. attributes
		// 2. version number
		// 3. children
		// 4. references
		// 5. ...
		if (e1 == null) {
			diffState = DIFF_STATE_NEW;
		} else if (e2 == null) {
			diffState = DIFF_STATE_OLD_ONLY;
		} else {
			if (e1 instanceof MethodLibrary) {
				diffState = DIFF_STATE_SAME;
			} else {
				diffState = DIFF_STATE_CHANGED;
			}
		}
	}

	private String getVersion(MethodElement element) {
		if (element == null) {
			return ""; //$NON-NLS-1$
		}

		return getPackage(element).getVersion();
	}

	private org.eclipse.epf.uma.MethodUnit getPackage(MethodElement element) {
		if (element instanceof org.eclipse.epf.uma.MethodUnit) {
			return (org.eclipse.epf.uma.MethodUnit) element;
		}

		return getPackage((MethodElement) element.eContainer());
	}

	// Utility method to get the element information.
	/**
	 * Returns the name.
	 */
	public String getName() {
		if (e1 != null) {
			return e1.getName();
		}
		if (e2 != null) {
			return e2.getName();
		} else {
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Returns the content type.
	 */
	public String getContentType() {
		MethodElement e = e1;
		if (e == null) {
			e = e2;
		}

		if (e != null) {
			return e.getType().getName();
		}

		return ""; //$NON-NLS-1$
	}
	
	public void debugDump() {
		debugDump(0, ""); //$NON-NLS-1$
	}
	
	private void debugDump(int level, String indent) {
		String line = null;
		if (e1 == null) {
			line = "null"; //$NON-NLS-1$
		} else {
			line = e1.getGuid() + ", " + e1.getName() + ", " + e1.eClass().getName(); //$NON-NLS-1$ //$NON-NLS-2$
		}
		outLn(level, indent, line);
		
		if (e2 == null) {
			line = "null"; //$NON-NLS-1$
		} else {
			line = e2.getGuid() + ", " + e2.getName() + ", " + e2.eClass().getName(); //$NON-NLS-1$ //$NON-NLS-2$
		}
		outLn(level, indent, line);
		System.out.println(""); //$NON-NLS-1$
		
		for (int i=0; i < children.size(); i++) {
			ElementDiffTree child = (ElementDiffTree) children.get(i);
			child.debugDump(level + 1, indent + "   "); //$NON-NLS-1$
		}		
	}
	
	private void outLn(int level, String indent, String line) {
		String str = indent + level + ": " + line; //$NON-NLS-1$
		System.out.println(str);
	}

}
