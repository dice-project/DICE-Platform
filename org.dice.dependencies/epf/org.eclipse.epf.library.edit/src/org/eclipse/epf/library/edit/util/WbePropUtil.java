package org.eclipse.epf.library.edit.util;

import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.WorkBreakdownElement;

public class WbePropUtil extends PropUtil {
	public static final String WBE_GlobalPresentedAfter = "wbe_GlobalPresentedAfter";	//$NON-NLS-1$

	private static WbePropUtil WbePropUtil = new WbePropUtil();
	public static WbePropUtil getWbePropUtil() {
		return WbePropUtil;
	}
	
	protected WbePropUtil() {		
	}
	
	protected WbePropUtil(IActionManager actionManager) {
		super(actionManager);
	}	
	
	public WorkBreakdownElement getGlobalPresentedAfter(WorkBreakdownElement wbe) {
		String guid = getStringValue(wbe, WBE_GlobalPresentedAfter);
		if (guid == null) {
			return null;
		}
		MethodElement element = LibraryEditUtil.getInstance().getMethodElement(
				guid);
		if (element instanceof WorkBreakdownElement) {
			return (WorkBreakdownElement) element;
		}
		return null;
	}

	public void setGlobalPresentedAfter(WorkBreakdownElement wbe,
			WorkBreakdownElement globalPresentedAfter) {
		setStringValue(wbe, WBE_GlobalPresentedAfter,
				globalPresentedAfter == null ? null : globalPresentedAfter
						.getGuid());
	}
	
}
