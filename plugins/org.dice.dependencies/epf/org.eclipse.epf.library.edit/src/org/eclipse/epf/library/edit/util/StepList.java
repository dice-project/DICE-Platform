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
package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Section;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.util.AssociationHelper;


/**
 * Reorder steps
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class StepList {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3257567325849858871L;

	private static DescribableElement getDescribableElement(Section step) {
		return (DescribableElement) step.eContainer().eContainer();
	}

	private Task currentTask;

	private LinkedList stepLists;

	public StepList(Task currentTask, List stepLists) {
		this.currentTask = currentTask;
		this.stepLists = new LinkedList(stepLists);
		reorder();
	}

	/**
	 * 
	 */
	private void reorder() {
		Map ownerSectionsMap = new HashMap();
		int size = stepLists.size();
		for (int i = 0; i < size; i++) {
			EcoreEList eList = (EcoreEList) stepLists.get(i);
			ownerSectionsMap.put(eList.getNotifier(), eList);
		}
		for (Iterator iter = new ArrayList(stepLists).iterator(); iter
				.hasNext();) {
			List stepList = (List) iter.next();
			if (!stepList.isEmpty()) {
				Section step = (Section) stepList.get(0);
				Section pred = step.getPredecessor();
				if (pred == null) {
					List oldFirstList = (List) stepLists.get(0);

					// put this step list at beginning
					//
					stepLists.remove(stepList);
					stepLists.add(0, stepList);

					// set this step list to be the predecessor of the previous
					// first list
					//
					if (!oldFirstList.isEmpty()) {
						Section oldFirstStep = (Section) oldFirstList.get(0);
						if (TngUtil.canReference(
								getDescribableElement(oldFirstStep),
								getDescribableElement(step))) {
							oldFirstStep.setPredecessor(step);
						}
					}
				} else {
					List predList = (List) ownerSectionsMap.get(pred
							.eContainer());
					stepLists.remove(predList);
					int id = stepLists.indexOf(stepList);
					stepLists.add(id, predList);
				}
			}
		}
	}

	// private void reassignPredecessors() {
	//
	// }

	public List getSteps() {
		List allSteps = new ArrayList();
		int size = stepLists.size();
		for (int i = 0; i < size; i++) {
			List steps = (List) stepLists.get(i);
			for (Iterator iter = steps.iterator(); iter.hasNext();) {
				allSteps.add(iter.next());
			}
		}
		return allSteps;
	}

	public boolean moveUp(Section step) {
		List steps = currentTask.getPresentation().getSections();
		int id = steps.indexOf(step);
		if (id == -1)
			return false;
		if (id > 0) {
			if (id == 1) {

			}
			// move the step within its list
			//
			Collections.swap(steps, id - 1, id);
			return true;
		}
		// move the whole step list up
		//
		id = stepLists.indexOf(steps);
		if (id == -1)
			return false;
		List predList = null;
		int predListId = id - 1;
		// look for non-empty predecessor list
		//
		find_predList: for (; predListId > -1; predListId--) {
			List list = (List) stepLists.get(predListId);
			if (!list.isEmpty()) {
				predList = list;
				break find_predList;
			}
		}
		Section oldPred = ((Section) steps.get(0)).getPredecessor();
		List oldSuccessors = AssociationHelper.getSuccessors((Section) steps
				.get(steps.size() - 1));
		if (predList != null) {
			stepLists.remove(steps);
			stepLists.add(predListId, steps);
			Section last = (Section) predList.get(predList.size() - 1);
			List oldPredSuccessors = AssociationHelper.getSuccessors(last);
			if (TngUtil.canReference(currentTask, getDescribableElement(last))) {
				step.setPredecessor(last);
				for (Iterator iter = oldPredSuccessors.iterator(); iter
						.hasNext();) {
					Section sect = (Section) iter.next();
					sect.setPredecessor(step);
				}
			}
		} else {
			stepLists.add(0, steps);
		}
		DescribableElement oldPredTask = getDescribableElement(oldPred);
		for (Iterator iter = oldSuccessors.iterator(); iter.hasNext();) {
			Section sect = (Section) iter.next();
			if (TngUtil.canReference(getDescribableElement(sect), oldPredTask)) {
				sect.setPredecessor(oldPred);
			}
		}

		return true;
	}

	public boolean moveDown(Section step) {
		List steps = currentTask.getPresentation().getSections();
		int id = steps.indexOf(step);
		if (id == -1)
			return false;
		if (id < steps.size() - 1) {
			// move the step within its list
			//
			Collections.swap(steps, id + 1, id);
			return true;
		}
		// move the whole step list up
		//
		id = stepLists.indexOf(steps);
		if (id == -1)
			return false;
		List succList = null;
		int succListId = id + 1;
		int size = stepLists.size();

		// look for non-empty successor list
		//
		find_List: for (; succListId < size; succListId++) {
			List list = (List) stepLists.get(succListId);
			if (!list.isEmpty()) {
				succList = list;
				break find_List;
			}
		}
		if (succList != null) {
			stepLists.remove(succList);
			stepLists.add(id, succList);
		} else {
			stepLists.add(steps);
		}

		return true;

	}

}
