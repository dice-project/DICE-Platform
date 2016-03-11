package org.eclipse.epf.library.edit.util;

import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.uma.Constraint;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.WorkProductDescriptor;

public class MilestonePropUtil extends PropUtil {

	private static MilestonePropUtil milestonePropUtil = new MilestonePropUtil();
	public static MilestonePropUtil getMilestonePropUtil() {
		return milestonePropUtil;
	}
	
	public static MilestonePropUtil getMilestonePropUtil(IActionManager actionManager) {
		return new MilestonePropUtil(actionManager);
	}
		
	protected MilestonePropUtil() {		
	}
	
	protected MilestonePropUtil(IActionManager actionManager) {
		super(actionManager);
	}
	
	public void addWpState(Milestone ms, WorkProductDescriptor wpd,
			Constraint state, EReference ref) {
		ConstraintManager.addWpState(ms, wpd, state, ref, getActionManager());
	}

	public void removeWpState(Milestone ms,
			WorkProductDescriptor wpd, Constraint state, EReference ref) {
		ConstraintManager.removeWpState(ms, wpd, state, ref, getActionManager());
	}

	public List<Constraint> getWpStates(Milestone ms,
			WorkProductDescriptor wpd, EReference ref) {
		return ConstraintManager.getWpStates(ms, wpd, ref);
	}
	
}
