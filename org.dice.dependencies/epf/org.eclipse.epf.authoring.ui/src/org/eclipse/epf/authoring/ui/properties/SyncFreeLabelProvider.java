/*******************************************************************************
* Licensed Materials - Property of IBM
* (c) Copyright IBM Corporation 2007,2009. All Rights Reserved.
*
* Note to U.S. Government Users Restricted Rights:
* Use, duplication or disclosure restricted by GSA ADP Schedule
* Contract with IBM Corp. 
*******************************************************************************/

package org.eclipse.epf.authoring.ui.properties;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/**
 * 
 * @author achen
 *
 */
public class SyncFreeLabelProvider extends AdapterFactoryLabelProvider implements ITableFontProvider {
	private DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();	
	private Font systemFont = Display.getCurrent().getSystemFont();
	private	Font boldFont = null;
	private Font boldAndItalicFont = null;
	
	private Descriptor desc;
	private EReference ref;
	private MethodConfiguration config;

	public SyncFreeLabelProvider(AdapterFactory adapterFactory, Descriptor desc, EReference ref, MethodConfiguration config) {
		super(adapterFactory);
		this.desc = desc;
		this.ref = ref;		
		this.config = config;
		boldFont = createFont(SWT.BOLD);
		boldAndItalicFont = createFont(SWT.BOLD | SWT.ITALIC);
	}
    
    public Font getFont(Object obj, int columnIndex) {
		if (propUtil.isFromGreenParentLocalList(obj, desc, ref)) {
			return boldAndItalicFont;
		}
    	
		if (!propUtil.isDynamic(obj, desc, ref)) {
			return boldFont;
		}
		
    	return systemFont;
    }	    	
    
    public String getColumnText(Object obj, int columnIndex) {
    	String original = super.getColumnText(obj, columnIndex);
    	
    	if (propUtil.isDynamicAndExclude(obj, desc, ref, config)) {
    		return "--<" + original + ">";	    		 //$NON-NLS-1$ //$NON-NLS-2$
    	}
    	
    	return original;	    	
    }
    
    public void dispose() {
    	super.dispose();
    	
    	if (boldFont != null) {
    		boldFont.dispose();
    	}
    	
    	if (boldAndItalicFont != null) {
    		boldAndItalicFont.dispose();
    	}
    }
    
    private Font createFont(int style) {
		FontData[] fontdata = systemFont.getFontData();
		for (FontData data : fontdata) {
			data.setStyle(style);
		}
		
		return new Font(Display.getCurrent(), fontdata);    	
    }
    
}
