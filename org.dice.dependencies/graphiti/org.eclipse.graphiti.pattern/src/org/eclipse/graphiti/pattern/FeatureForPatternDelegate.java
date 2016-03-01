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
package org.eclipse.graphiti.pattern;

/**
 * The Class FeatureForPatternDelegate.
 */
public class FeatureForPatternDelegate implements IFeatureForPattern {

	private IPattern pattern;

	/**
	 * Creates a new {@link FeatureForPatternDelegate}.
	 * 
	 * @param pattern
	 *            the pattern
	 */
	public FeatureForPatternDelegate(IPattern pattern) {
		super();
		setPattern(pattern);
	}

	/**
	 * Gets the pattern.
	 * 
	 * @return Returns the pattern.
	 */
	public IPattern getPattern() {
		return pattern;
	}

	/**
	 * Sets the pattern.
	 * 
	 * @param pattern
	 *            the new pattern
	 */
	private void setPattern(IPattern pattern) {
		this.pattern = pattern;
	}

}
