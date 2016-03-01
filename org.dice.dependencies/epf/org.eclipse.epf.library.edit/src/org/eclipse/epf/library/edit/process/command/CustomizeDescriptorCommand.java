package org.eclipse.epf.library.edit.process.command;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.ConstraintManager;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.UmaUtil;


public class CustomizeDescriptorCommand extends AbstractCommand implements
		IResourceAwareCommand {

	private Process proc;
	private BreakdownElementWrapperItemProvider wrapper;
	private Descriptor greenParent;
	private static boolean debug = false;

	public CustomizeDescriptorCommand(BreakdownElementWrapperItemProvider wrapper) {
		this.wrapper = wrapper;
		proc = (Process) wrapper.getTopItem();
		Object obj = TngUtil.unwrap(wrapper);
		if (obj instanceof Descriptor) {
			greenParent = (Descriptor) obj;
		}		
	}

	public BreakdownElementWrapperItemProvider getWrapper() {
		return wrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection getModifiedResources() {
		if (proc.eResource() != null) {
			return Collections.singletonList(proc.eResource());
		}
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#prepare()
	 */
	protected boolean prepare() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		if (greenParent == null || ! (greenParent instanceof TaskDescriptor)) {
			return;
		}		
		Object parentObj = wrapper.getParent(null);
		Activity parentAct = parentObj instanceof Activity ? (Activity) parentObj : null;
		if (parentAct == null) {
			return;
		}
		if (proc != ProcessUtil.getProcess(parentAct)) {
			return;
		}
		
		Descriptor des = UmaFactory.eINSTANCE.createTaskDescriptor();
		
		updateFromGreenParent(greenParent, des, true);		
		parentAct.getBreakdownElements().add(des);		
	}

	private static boolean isAttToCopy(EAttribute attribute, boolean newChild) {
		//For an old child, realization manager's process update takes care of synchronized attributes,
		//and any other attributes left as un-synchronized
		if (!newChild) {
			return false;
		}
		
		if (!attribute.isChangeable()) {
			return false;	
		}
		if (attribute.isDerived()) {
			return false;
		}
		if (attribute == UmaPackage.eINSTANCE.getMethodElement_Guid()) {
			return false;
		}
		if (attribute == UmaPackage.eINSTANCE.getNamedElement_Name()) {
			return false;
		}
		
		return true;
	}
	

	private static boolean isRefToCopy(EReference ref, boolean newChild) {
		//For now, no need to copy any references for an old child.
		//This may change if requirement changes in future
		if (!newChild) {
			return false;
		}
		
		if (!ref.isChangeable()) {
			return false;	
		}
		if (ref.isDerived()) {
			return false;
		}
		if (ref == UmaPackage.eINSTANCE.getDescribableElement_Presentation()) {
			return false;
		}
		if (ref == UmaPackage.eINSTANCE.getBreakdownElement_SuperActivities()) {
			return false;
		}
		if (ref == UmaPackage.eINSTANCE.getBreakdownElement_SuperActivities()) {
			return false;
		}
		if (ref == UmaPackage.eINSTANCE.getBreakdownElement_PlanningData()) {
			return false;
		}

		return true;
	}
	
	private static void copyAttributes(MethodElement source, MethodElement target, boolean newChild) {
		if (source == null || target == null || source.eClass() != target.eClass()) {
			return;
		}				
		if (debug) {
			System.out.println("\nLD> source: " + source); //$NON-NLS-1$
		}
		
		Collection<EAttribute> attributes = source.eClass().getEAllAttributes();		
		for (EAttribute attribute : attributes) {
			if (isAttToCopy(attribute, newChild)) {
				if (debug) {
					System.out.println("LD> attribute: " + attribute.getName() + ", type : " + attribute.getEType()); //$NON-NLS-1$
				}
				
				Object value = source.eGet(attribute);
				if (value != null) {
					target.eSet(attribute, value);
				}
			}
		}
	}
	
	private static void copyReferences(MethodElement source, MethodElement target, boolean newChild) {
		if (source == null || target == null || source.eClass() != target.eClass()) {
			return;
		}	
		Collection<EReference> references = source.eClass().getEAllReferences();
		
		if (debug) {
			System.out.println("\nLD> source: " + source); //$NON-NLS-1$
		}
		
		for (EReference reference : references) {
			if (debug) {				
				if (reference.isContainment()) {
					System.out.println("LD> reference: " + reference.getName() + ", type : " + reference.getEType()); //$NON-NLS-1$
				}
				if (reference == UmaPackage.eINSTANCE.getBreakdownElement_PlanningData()) {		
					Object value = source.eGet(reference);
					System.out.println("LD> planningData: " + value);
				}
			}			
			
			if ( ! isRefToCopy(reference, newChild)) {
				continue;
			}
			
			Object value = source.eGet(reference);
			if (value == null) {
				continue;
			}
			
			if (reference.isContainment()) {

			} else if (reference.isMany()) {
				List valueList = (List) value;
				if (! valueList.isEmpty()) {
					EList copyList = (EList) target.eGet(reference);
					copyList.clear();
					copyList.addAll(valueList);
				}
			} else {
				target.eSet(reference, value);
			}
		}
	}
	
	public static void updateFromGreenParent(Descriptor greenParent, Descriptor child, boolean newChild) {
		//for now do nothing for an old child
		if (!newChild) {	
			return;
		}
		
		if (newChild) { 
			child.setName(greenParent.getName());
		}
		copyAttributes(greenParent, child, newChild);
		copyAttributes(greenParent.getPresentation(), child.getPresentation(), newChild);
		
		DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
		if (debug) {
			child.setName(greenParent.getName() + "_n-modified");		//$NON-NLS-1$
			child.setPresentationName(greenParent.getPresentationName() + "_p-modified");//$NON-NLS-1$
			propUtil.setNameRepalce(child, true);
			propUtil.setPresentationNameRepalce(child, true);
		}
		
		copyReferences(greenParent, child, newChild);
		copyWpStates(greenParent, child, newChild);
		copyReferences(greenParent.getPresentation(), child.getPresentation(), newChild);	
		
		
		propUtil.setGreenParent(child, greenParent.getGuid());
		propUtil.addToCustomizingChildren(greenParent, child);
	}	
	
	
	private static void copyWpStates(MethodElement source, MethodElement target, boolean newChild) {
		if (! newChild) {
			return;
		}
		if (source == null || target == null || source.eClass() != target.eClass()) {
			return;
		}	
		
		if (source instanceof TaskDescriptor) {
			TaskDescriptor srcTd = (TaskDescriptor) source;
			TaskDescriptor tgtTd = (TaskDescriptor) target;
			for (Constraint c : ConstraintManager.getWpStates(srcTd)) {
				Constraint copiedState = (Constraint) EcoreUtil.copy(c);
				copiedState.setGuid(UmaUtil.generateGUID());
				tgtTd.getOwnedRules().add(copiedState);
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		
		execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
	 */
	public void undo() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#getResult()
	 */
	public Collection getResult() {
		return super.getResult();
	}
}
