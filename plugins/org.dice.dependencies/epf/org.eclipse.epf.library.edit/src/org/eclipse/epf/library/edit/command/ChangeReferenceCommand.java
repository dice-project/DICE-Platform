package org.eclipse.epf.library.edit.command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.util.ExtendedReference;

public class ChangeReferenceCommand extends MethodElementSetPropertyCommand {
	
	private boolean remove = false;
	private List<MethodElement> items;
	private Set<MethodElement> affectedSet = new HashSet<MethodElement>();
	private ExtendedReference reference;
	
	public ChangeReferenceCommand(MethodElement element, List<MethodElement> items, ExtendedReference reference, boolean remove) {
		super(element, MethodElementPropUtil.Me_references);
		this.items = items;
		this.remove = remove;
		this.reference = reference;
	}
	
	@Override
	public void redo() {
		PropUtil propUtil = PropUtil.getPropUtil();		
		List<MethodElement> listValue = propUtil.getExtendedReferenceList(element, reference, true);
		if (listValue == null || items == null || items.isEmpty()) {
			return;
		}		

		for (MethodElement p : items) {
			if (remove) {
				if (listValue.remove(p)) {
					affectedSet.add(p);
					propUtil.removeOpposite(reference, element, p);
				}
			} else {
				if (! listValue.contains(p) && listValue.add(p)) {
					affectedSet.add(p);
					propUtil.addOpposite(reference, element, p);
				}
			}	
		}
		try {
			this.value = propUtil.getReferencesXml(this.element, false);
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}		
		super.redo();
	}
		
	@Override
	public void undo() {
		PropUtil propUtil = PropUtil.getPropUtil();		
		try {
			for (MethodElement p : affectedSet) {
				if (remove) {
					propUtil.addOpposite(reference, element, p);	
				} else {
					propUtil.removeOpposite(reference, element, p);	
				}	
			}
			this.value = propUtil.getReferencesXml(this.element, true);
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}	
		super.undo();
	}
	
}
