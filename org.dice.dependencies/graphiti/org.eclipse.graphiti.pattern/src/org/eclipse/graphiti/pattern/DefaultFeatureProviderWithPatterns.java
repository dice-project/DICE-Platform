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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.pattern.internal.T;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

/**
 * The Class DefaultFeatureProviderWithPatterns.
 */
public class DefaultFeatureProviderWithPatterns extends DefaultFeatureProvider implements IFeatureProviderWithPatterns {

	private List<IPattern> patterns;

	private List<IConnectionPattern> connectionPatters;

	/**
	 * Creates a new {@link DefaultFeatureProviderWithPatterns}.
	 * 
	 * @param dtp
	 *            the diagram type provider
	 */
	public DefaultFeatureProviderWithPatterns(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	public void addPattern(IPattern pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException("Argument pattern must not be null."); //$NON-NLS-1$
		}
		
		pattern.setFeatureProvider(this);
		getPatterns().add(pattern);
	}

	/**
	 * Adds a connection pattern.
	 * 
	 * @param pattern
	 *            the connection pattern
	 */
	public void addConnectionPattern(IConnectionPattern pattern) {
		if (pattern == null) {
			throw new IllegalArgumentException("Argument pattern must not be null."); //$NON-NLS-1$
		}
		pattern.setFeatureProvider(this);
		getConnectionPatterns().add(pattern);
	}

	/**
	 * Gets the patterns.
	 * 
	 * @return the patterns
	 */
	protected List<IPattern> getPatterns() {
		if (this.patterns == null) {
			this.patterns = new ArrayList<IPattern>();
		}
		return this.patterns;
	}

	/**
	 * Gets the connection patterns.
	 * 
	 * @return the connection patterns
	 */
	protected List<IConnectionPattern> getConnectionPatterns() {
		if (this.connectionPatters == null) {
			this.connectionPatters = new ArrayList<IConnectionPattern>();
		}
		return this.connectionPatters;
	}
	
	/**
	 * Check feature and context.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	protected boolean checkFeatureAndContext(IFeature feature, IContext context) {
		boolean featureOkay = feature != null;
		boolean featureAvailable = feature.isAvailable(context);
		boolean ret = featureOkay && featureAvailable;
		return ret;
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		IAddFeature ret = null;
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, context.getNewObject())) {
				IPattern choosenPattern = null;
				IAddFeature f = new AddFeatureForPattern(this, pattern);
				boolean executable = checkFeatureAndContext(f, context);
				if (executable) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getAddFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}
		if (ret == null) {
			for (IConnectionPattern conPattern : getConnectionPatterns()) {
				if (conPattern.canAdd(context)) {
					return new AddFeatureForPattern(this, conPattern);
				}
			}
		}

		if (ret == null) {
			ret = getAddFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Gets the additional add feature.
	 * 
	 * @param context
	 *            the add context
	 * 
	 * @return the additional add feature
	 */
	protected IAddFeature getAddFeatureAdditional(IAddContext context) {
		return super.getAddFeature(context);
	}

	@Override
	public final ICreateFeature[] getCreateFeatures() {
		ICreateFeature[] ret = new ICreateFeature[0];
		List<ICreateFeature> retList = new ArrayList<ICreateFeature>();

		for (IPattern pattern : getPatterns()) {
			retList.add(new CreateFeatureForPattern(this, pattern));
		}

		ICreateFeature[] a = getCreateFeaturesAdditional();
		for (ICreateFeature element : a) {
			retList.add(element);
		}

		return retList.toArray(ret);
	}

	/**
	 * Gets the additional create features.
	 * 
	 * @return the additional create features 
	 */
	protected ICreateFeature[] getCreateFeaturesAdditional() {
		ICreateFeature[] ret = new ICreateFeature[0];
		List<ICreateFeature> retList = new ArrayList<ICreateFeature>();
		ICreateFeature[] s = super.getCreateFeatures();
		for (ICreateFeature element : s) {
			retList.add(element);
		}
		return retList.toArray(ret);
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		ILayoutFeature ret = null;
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, getBusinessObjectForPictogramElement(context.getPictogramElement()))) {
				IPattern choosenPattern = null;
				ILayoutFeature f = new LayoutFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getLayoutFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getLayoutFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Gets the additional layout feature.
	 * 
	 * @param context
	 *            the layout context
	 * 
	 * @return the additional layout feature
	 */
	protected ILayoutFeature getLayoutFeatureAdditional(ILayoutContext context) {
		return super.getLayoutFeature(context);
	}

	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		IMoveShapeFeature ret = null;
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, getBusinessObjectForPictogramElement(context.getPictogramElement()))) {
				IPattern choosenPattern = null;
				IMoveShapeFeature f = new MoveShapeFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getMoveShapeFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getMoveShapeFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Gets the additional move shape feature.
	 * 
	 * @param context
	 *            the move shape context
	 * 
	 * @return the additional move shape feature
	 */
	protected IMoveShapeFeature getMoveShapeFeatureAdditional(IMoveShapeContext context) {
		return super.getMoveShapeFeature(context);
	}

	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		IResizeShapeFeature ret = null;
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, getBusinessObjectForPictogramElement(context.getPictogramElement()))) {
				IPattern choosenPattern = null;
				IResizeShapeFeature f = new ResizeShapeFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getResizeShapeFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getResizeShapeFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Check pattern.
	 * 
	 * @param pattern
	 *            the pattern
	 * @param object
	 *            the object
	 * 
	 * @return true, if successful
	 */
	protected boolean checkPattern(IPattern pattern, Object object) {
		return pattern.isMainBusinessObjectApplicable(object);
	}

	/**
	 * Gets the additional resize shape feature.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return the additional resize shape feature
	 */
	protected IResizeShapeFeature getResizeShapeFeatureAdditional(IResizeShapeContext context) {
		return super.getResizeShapeFeature(context);
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}
		IUpdateFeature ret = null;
		Object businessObject = getBusinessObjectForPictogramElement(context.getPictogramElement());
		for (IPattern pattern : getPatterns()) {
			if (checkPattern(pattern, businessObject) || pattern.canUpdate(context)) {
				IPattern choosenPattern = null;
				IUpdateFeature f = new UpdateFeatureForPattern(this, pattern);
				if (checkFeatureAndContext(f, context)) {
					if (ret == null) {
						ret = f;
						choosenPattern = pattern;
					} else {
						traceWarning("getUpdateFeature", pattern, choosenPattern); //$NON-NLS-1$
					}
				}
			}
		}

		if (ret == null) {
			ret = getUpdateFeatureAdditional(context);
		}

		return ret;
	}

	/**
	 * Gets additional the update feature.
	 * 
	 * @param context
	 *            the update context
	 * 
	 * @return the additional update feature
	 */
	protected IUpdateFeature getUpdateFeatureAdditional(IUpdateContext context) {
		return super.getUpdateFeature(context);
	}

	public IPattern getPatternForPictogramElement(PictogramElement pe) {
		if (pe == null) {
			throw new IllegalArgumentException("Argument pe must not be null."); //$NON-NLS-1$
		}
		for (IPattern pattern : getPatterns()) {
			if (pattern instanceof AbstractPattern) {
				AbstractPattern ap = (AbstractPattern) pattern;
				if (ap.isPatternRoot(pe)) {
					return pattern;
				}
			}
		}
		return null;
	}

	public void activateDirectEditingForPatterns(PictogramElement mainPictogramElement, Object bo) {
		IPattern pattern = getPatternForPictogramElement(mainPictogramElement);
		if (pattern != null) {
			IDirectEditingInfo dei = getDirectEditingInfo();
			dei.setMainPictogramElement(mainPictogramElement);
			pattern.completeInfo(dei, bo);
			dei.setActive(true);
		}
	}

	public void activateDirectEditingForPatterns(PictogramElement mainPictogramElement, Object bo, String keyProperty) {
		IPattern pattern = getPatternForPictogramElement(mainPictogramElement);
		if (pattern != null) {
			IDirectEditingInfo dei = getDirectEditingInfo();
			dei.setMainPictogramElement(mainPictogramElement);
			pattern.completeInfo(dei, bo, keyProperty);
			dei.setActive(true);
		}
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		ICreateConnectionFeature[] ret = new ICreateConnectionFeature[0];
		List<ICreateConnectionFeature> retList = new ArrayList<ICreateConnectionFeature>();

		for (IConnectionPattern conPattern : getConnectionPatterns()) {
			retList.add(new CreateConnectionFeatureForPattern(this, conPattern));
		}

		ICreateConnectionFeature[] a = getCreateConnectionFeaturesAdditional();
		for (ICreateConnectionFeature element : a) {
			retList.add(element);
		}

		return retList.toArray(ret);
	}

	/**
	 * Gets the additional create connection features.
	 * 
	 * @return the additional create connection features
	 */
	protected ICreateConnectionFeature[] getCreateConnectionFeaturesAdditional() {
		return super.getCreateConnectionFeatures();
	}
	
	/**
	 * Trace warning.
	 * 
	 * @param string
	 *            the string
	 * @param pattern
	 *            the pattern
	 * @param choosenPattern
	 *            the choosen pattern
	 */
	protected void traceWarning(String string, IPattern pattern, IPattern choosenPattern) {
		T.racer().warning(string + ": " + "Pattern " + pattern + " is executable additionally to pattern " + choosenPattern + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

}
