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
package org.eclipse.epf.library.edit;

import java.util.Comparator;

import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.Comparators.TypeComparator;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;


/**
 * @author Phong Nguyen Le - Mar 7, 2006
 * @since  1.0
 */
public class PresentationContext {
	public static final PresentationContext INSTANCE = new PresentationContext(); 

	private boolean showPresentationNames = false;
	private Comparator comparator = Comparators.DEFAULT_COMPARATOR;
	private Comparator reverseComparator = Comparators.REVERSE_DEFAULT_COMPARATOR;
	private Comparator methodElementTypeComparator = Comparators.METHOD_TYPE_COMPARATOR;
	
	
	private Comparator guidanceTypeComparator = new TypeComparator() {
		/* (non-Javadoc)
		 * @see org.eclipse.epf.library.edit.util.Comparators.TypeComparator#getDefaultComparator()
		 */
		protected Comparator getDefaultComparator() {
			return comparator;
		}

		protected int getOrderId(Object obj) {
			if (obj instanceof Guidance) {
				int id = ((Guidance) obj).eClass().getClassifierID();
				if (id == UmaPackage.TERM_DEFINITION) {
					id = Integer.MAX_VALUE;
				}
				return id;
			}
			return -1;
		}
	};

	private Comparator processPackageComparator = new TypeComparator() {
		/* (non-Javadoc)
		 * @see org.eclipse.epf.library.edit.util.Comparators.TypeComparator#getDefaultComparator()
		 */
		protected Comparator getDefaultComparator() {
			return comparator;
		}
		
		protected int getOrderId(Object obj) {
			if (obj instanceof ProcessComponent) {
				return Integer.MAX_VALUE;
			} else if (obj instanceof ProcessPackage) {
				return 0;
			}
			return -1;
		}
	};


	
	private PresentationContext() {
		super();
	}

	public boolean isShowPresentationNames() {
		return showPresentationNames;
	}

	public void setShowPresentationNames(boolean showPresentationNames) {
		this.showPresentationNames = showPresentationNames;
		comparator = showPresentationNames ? Comparators.PRESENTATION_NAME_COMPARATOR : Comparators.DEFAULT_COMPARATOR;
		reverseComparator = showPresentationNames ? Comparators.REVERSE_PRESENTATION_NAME_COMPARATOR : Comparators.REVERSE_DEFAULT_COMPARATOR;
		methodElementTypeComparator = showPresentationNames ? Comparators.METHOD_TYPE_PRES_NAME_COMPARATOR : Comparators.METHOD_TYPE_COMPARATOR;
	}

	public Comparator getComparator() {
		return comparator;
	}

	public Comparator getReverseComparator() {
		return reverseComparator;
	}

	public Comparator getGuidanceTypeComparator() {
		return guidanceTypeComparator;
	}

	public Comparator getProcessPackageComparator() {
		return processPackageComparator;
	}
	
	public Comparator getMethodElementTypeComparator() {
		return methodElementTypeComparator;
	}
	
	public Comparator getPresNameComparator() {
		return Comparators.PRESENTATION_NAME_COMPARATOR;
	}

	public Comparator getPresNameReverseComparator() {
		return Comparators.REVERSE_PRESENTATION_NAME_COMPARATOR;
	}
	
	public Comparator getPresNameMethodElementTypeComparator() {
		return Comparators.METHOD_TYPE_PRES_NAME_COMPARATOR;
	}

}
