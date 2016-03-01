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
package org.eclipse.epf.diagram.core.commands;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Command for change view dimensions.
 * 
 * @author Shashidhar Kannoori
 * @since 1.2
 */
public class ChangeBoundsCommand extends Command implements
		IResourceAwareCommand {

	private static final String label = "change bounds command label";	//$NON-NLS-1$

	private TransactionalEditingDomain domain;

	private View view;

	private int width;

	private int height;

	private Point location;

	private Point oldLocation;

	private int oldWidth;

	private int oldHeight;

	/**
	 * 
	 */
	public ChangeBoundsCommand(TransactionalEditingDomain domain, View view,
			Point location, int width, int height) {
		super(label);
		this.domain = domain;
		this.view = view;
		this.width = width;
		this.height = height;
		this.location = location;
	}

	@Override
	public boolean canExecute() {
		return (this.width != 0 || this.height != 0);
	}

	@Override
	public void execute() {
		try {
			TxUtil.runInTransaction(domain, new Runnable() {

				public void run() {
					oldLocation = new Point();
					Object x =  ViewUtil.getStructuralFeatureValue(view,
							NotationPackage.eINSTANCE.getLocation_X());
					if(x != null){
						oldLocation.x = ((Integer)x).intValue();
					}
					Object y = ViewUtil.getStructuralFeatureValue(view,
							NotationPackage.eINSTANCE.getLocation_Y());
					if(y != null){
						oldLocation.y = ((Integer) y).intValue();
					}
					oldWidth = ((Integer) ViewUtil.getStructuralFeatureValue(view,
							NotationPackage.eINSTANCE.getSize_Width())).intValue();
					oldHeight = ((Integer) ViewUtil.getStructuralFeatureValue(view,
							NotationPackage.eINSTANCE.getSize_Height())).intValue();

					if (location != null) {
						ViewUtil.setStructuralFeatureValue(view,
								NotationPackage.eINSTANCE.getLocation_X(), new Integer(
										location.x));
						ViewUtil.setStructuralFeatureValue(view,
								NotationPackage.eINSTANCE.getLocation_Y(), new Integer(
										location.y));
					}
					if (width > 0) {
						ViewUtil.setStructuralFeatureValue(view,
								NotationPackage.eINSTANCE.getSize_Width(), new Integer(
										width));
					}
					if (height > 0) {
						ViewUtil.setStructuralFeatureValue(view,
								NotationPackage.eINSTANCE.getSize_Height(),
								new Integer(height));
					}
				}
				
			});
		} catch (Exception e) {
			DiagramCorePlugin.getDefault().getLogger().logError("Error in changebounds command :",  e); //$NON-NLS-1$ 
			undo();
		}
	}

	@Override
	public void undo() {
		try {
			TxUtil.runInTransaction(domain, new Runnable() {

				public void run() {
					if (oldLocation != null) {
						ViewUtil.setStructuralFeatureValue(view,
								NotationPackage.eINSTANCE.getLocation_X(), new Integer(
										location.x));
						ViewUtil.setStructuralFeatureValue(view,
								NotationPackage.eINSTANCE.getLocation_Y(), new Integer(
										location.y));
					}
					if (oldWidth != width) {
						ViewUtil.setStructuralFeatureValue(view,
								NotationPackage.eINSTANCE.getSize_Width(), new Integer(
										width));
					}
					if (oldHeight > height) {
						ViewUtil.setStructuralFeatureValue(view,
								NotationPackage.eINSTANCE.getSize_Height(),
								new Integer(height));
					}
				}
				
			});
		} catch (Exception e) {
			
		}
	}

	public Collection getModifiedResources() {
		Set<Resource> set = new HashSet<Resource>();
		set.add(view.eResource());
		if (!set.isEmpty())
			return set;
		return Collections.EMPTY_LIST;
	}

	public org.eclipse.emf.common.command.Command chain(
			org.eclipse.emf.common.command.Command command) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getAffectedObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection getResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
