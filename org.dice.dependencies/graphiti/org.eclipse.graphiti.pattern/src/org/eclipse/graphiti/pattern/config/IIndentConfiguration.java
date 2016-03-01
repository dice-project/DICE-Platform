/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.pattern.config;

/**
 * The Interface IIndentConfiguration.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IIndentConfiguration extends IPatternConfiguration {

	/**
	 * Gets the outer indent bottom.
	 * 
	 * @return the outer indent bottom
	 */
	int getOuterIndentBottom();

	/**
	 * Gets the outer indent left.
	 * 
	 * @return the outer indent left
	 */
	int getOuterIndentLeft();

	/**
	 * Gets the outer indent right.
	 * 
	 * @return the outer indent right
	 */
	int getOuterIndentRight();

	/**
	 * Gets the outer indent top.
	 * 
	 * @return the outer indent top
	 */
	int getOuterIndentTop();

	/**
	 * Sets the outer indent bottom.
	 * 
	 * @param outerIndentBottom
	 *            the new outer indent bottom
	 */
	void setOuterIndentBottom(int outerIndentBottom);

	/**
	 * Sets the outer indent left.
	 * 
	 * @param outerIndentLeft
	 *            the new outer indent left
	 */
	void setOuterIndentLeft(int outerIndentLeft);

	/**
	 * Sets the outer indent right.
	 * 
	 * @param outerIndentRight
	 *            the new outer indent right
	 */
	void setOuterIndentRight(int outerIndentRight);

	/**
	 * Sets the outer indent top.
	 * 
	 * @param outerIndentTop
	 *            the new outer indent top
	 */
	void setOuterIndentTop(int outerIndentTop);

}
