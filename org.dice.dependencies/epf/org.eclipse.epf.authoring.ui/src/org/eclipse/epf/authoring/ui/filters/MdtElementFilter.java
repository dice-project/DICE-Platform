package org.eclipse.epf.authoring.ui.filters;

import org.eclipse.epf.library.configuration.ConfigurationFilter;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;

public class MdtElementFilter extends ConfigurationFilter {

	private ModifiedTypeMeta meta;
	
	public MdtElementFilter(MethodConfiguration methodConfig, ModifiedTypeMeta meta) {
		super(methodConfig);
		this.meta = meta;
	}

	@Override
	public boolean accept(Object obj) {
		boolean b = accept_(obj);
//		if (b) {
//			System.out.println("LD> obj: " + obj);
//		}
		return b;
	}
	
	private boolean accept_(Object obj) {
		if (obj instanceof ContentElement) {
			ContentElement element = (ContentElement) obj;	
			if (TypeDefUtil.getMdtMeta(element) == meta) {
				return super.accept(obj);
			}
			return false;
		}
		if (obj instanceof MethodElement) {
			if (obj instanceof ProcessPackage) {
				return false;
			}
			if (obj instanceof MethodLibrary ||
					obj instanceof MethodPlugin ||
					obj instanceof MethodPackage) {
				return super.accept(obj);
			}
			return false;
		}	
		
		return false;
	}	
	
}
