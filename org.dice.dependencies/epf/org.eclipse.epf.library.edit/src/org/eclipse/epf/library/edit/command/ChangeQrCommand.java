package org.eclipse.epf.library.edit.command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.uma.ExtendReferenceMap;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.uma.MethodElement;

public class ChangeQrCommand extends MethodElementSetPropertyCommand {
	
	private boolean remove = false;
	private List<MethodElement> items;
	private Set<MethodElement> affectedSet = new HashSet<MethodElement>();
	private String qualifierId;
	private String oppositeName;
	
	public ChangeQrCommand(MethodElement element, List<MethodElement> items, String qualifierId, boolean remove) {
		super(element, MethodElementPropUtil.Me_references);
		this.items = items;
		this.remove = remove;
		this.qualifierId = qualifierId;
		this.oppositeName = ExtendReferenceMap.getOppositeName(qualifierId);
	}
	
	@Override
	public void redo() {
		MethodElementPropUtil propUtil = MethodElementPropUtil.getMethodElementPropUtil();		
		List<MethodElement> listValue = propUtil.getQReferenceListById(element, qualifierId, true);
		if (listValue == null || items == null || items.isEmpty()) {
			return;
		}		

		for (MethodElement p : items) {
			if (remove) {
				if (listValue.remove(p)) {
					affectedSet.add(p);
					propUtil.removeOpposite(oppositeName, element, p);
				}
			} else {
				if (! listValue.contains(p) && listValue.add(p)) {
					affectedSet.add(p);
					propUtil.addOpposite(oppositeName, element, p);
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
			for (MethodElement p : affectedSet) {
				if (remove) {
					propUtil.addOpposite(oppositeName, element, p);	
				} else {
					propUtil.removeOpposite(oppositeName, element, p);	
				}	
			}
			this.value = propUtil.getReferencesXml(this.element, true);
		} catch (Exception e) {
			LibraryEditPlugin.getDefault().getLogger().logError(e);
		}	
		super.undo();
	}
	
}
