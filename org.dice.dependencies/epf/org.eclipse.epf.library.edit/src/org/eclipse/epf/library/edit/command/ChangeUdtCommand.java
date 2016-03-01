package org.eclipse.epf.library.edit.command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.uma.ExtendReferenceMap;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;

public class ChangeUdtCommand extends MethodElementSetPropertyCommand {
	
	private boolean remove = false;
	private List<Practice> items;
	private Set<Practice> affectedSet = new HashSet<Practice>();
	
	public ChangeUdtCommand(MethodElement element, List<Practice> items, boolean remove) {
		super(element, MethodElementPropUtil.Me_references);
		this.items = items;
		this.remove = remove;
	}

	@Override
	public void redo() {
		MethodElementPropUtil propUtil = MethodElementPropUtil.getMethodElementPropUtil();		
		List<Practice> listValue = propUtil.getUdtList(element, true);
		if (listValue == null || items == null || items.isEmpty()) {
			return;
		}		

		for (Practice p : items) {
			if (remove) {
				if (listValue.remove(p)) {
					affectedSet.add(p);
					propUtil.removeOpposite(ExtendReferenceMap.UdtList, element, p);
				}
			} else {
				if (! listValue.contains(p) && listValue.add(p)) {
					affectedSet.add(p);
					propUtil.addOpposite(ExtendReferenceMap.UdtList, element, p);
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
		MethodElementPropUtil propUtil = MethodElementPropUtil.getMethodElementPropUtil();	
		try {
			for (Practice p : affectedSet) {
				if (remove) {
					propUtil.addOpposite(ExtendReferenceMap.UdtList, element, p);	
				} else {
					propUtil.removeOpposite(ExtendReferenceMap.UdtList, element, p);	
				}	
			}
			this.value = propUtil.getReferencesXml(this.element, true);
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}	
		super.undo();
	}
	
}
