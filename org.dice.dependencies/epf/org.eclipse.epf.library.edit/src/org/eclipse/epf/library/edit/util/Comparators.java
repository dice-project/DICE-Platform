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

import java.util.Comparator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.navigator.PluginUIPackagesItemProvider;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.UmaPackage;

import com.ibm.icu.text.Collator;


/**
 * Collection of comparators
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class Comparators {

	public static final Comparator<Object> DEFAULT_COMPARATOR = new Comparator<Object>() {

		public int compare(Object o1, Object o2) {
			if (o1 == o2)
				return 0;
			Collator collator = Collator.getInstance();
			if (o1 instanceof MethodElement && o2 instanceof MethodElement) {
				return collator.compare(((MethodElement) o1).getName(), ((MethodElement) o2).getName());
			}
			Object o1unwrap = TngUtil.unwrap(o1);
			Object o2unwrap = TngUtil.unwrap(o2);
			if (o1unwrap instanceof MethodElement
					&& o2unwrap instanceof MethodElement) {
				return collator.compare(((MethodElement) o1unwrap).getName(), ((MethodElement) o2unwrap).getName());
			}
			return 0;
		}

	};
	
	public static final Comparator<Object> REVERSE_DEFAULT_COMPARATOR = new Comparator<Object>() {

		public int compare(Object o1, Object o2) {
			if (o1 == o2)
				return 0;
			Collator collator = Collator.getInstance();
			if (o1 instanceof MethodElement && o2 instanceof MethodElement) {
				return oppositeValue(collator.compare(((MethodElement) o1).getName(), ((MethodElement) o2).getName()));
			}
			Object o1unwrap = TngUtil.unwrap(o1);
			Object o2unwrap = TngUtil.unwrap(o2);
			if (o1unwrap instanceof MethodElement
					&& o2unwrap instanceof MethodElement) {
				return oppositeValue(collator.compare(((MethodElement) o1unwrap).getName(), ((MethodElement) o2unwrap).getName()));
			}
			return 0;
		}

	};
	
	private static int oppositeValue(int val) {
		if (val != 0) {
			val = 0 - val;
		}
		return val;
	}


	public static abstract class TypeComparator implements Comparator {
		protected Comparator<Object> getDefaultComparator() {
			return DEFAULT_COMPARATOR;
		}
		
		protected abstract int getOrderId(Object obj);

		public int compare(Object o1, Object o2) {
			o1 = TngUtil.unwrap(o1);
			o2 = TngUtil.unwrap(o2);
			if (o1 == o2)
				return 0;
			int ret = 0;
			ret = getOrderId((EObject) o1) - getOrderId((EObject) o2);
			if (ret == 0) {
				return getDefaultComparator().compare(o1, o2);
			}
			return ret;
		}

	}

	private static int comparePresentationName(MethodElement e1, MethodElement e2) {
		Collator collator = Collator.getInstance();
		String name1 = e1 instanceof BreakdownElement ? ProcessUtil.getPresentationName((BreakdownElement)e1)
				: e1.getPresentationName();
		if (name1.length() < 1) name1 = e1.getName();
		String name2 = e2 instanceof BreakdownElement ? ProcessUtil.getPresentationName((BreakdownElement)e2)
				: e2.getPresentationName();
		if (name2.length() < 1) name2 = e2.getName();
		return collator.compare(name1, name2);
	}
	
	private static MethodElement getMethodElement(Object obj) {
		Object object = TngUtil.unwrap(obj);
		if (object instanceof ProcessComponent)
			object = ((ProcessComponent) object).getProcess();
		object = TngUtil.unwrap(object);
		if(object instanceof MethodElement) {
			return (MethodElement)object;
		}
		return null;
		
	}

	public static final Comparator<Object> PRESENTATION_NAME_COMPARATOR = new Comparator<Object>() {
		
		public int compare(Object o1, Object o2) {
			if (o1 == o2)
				return 0;
			o1 = getMethodElement(o1);
			if (o1 == null) {
				return 0;
			}

			o2 = getMethodElement(o2);
			if (o2 == null) {
				return 0;
			}

			return comparePresentationName((MethodElement) o1, (MethodElement) o2);
		}

	};
	
	public static final Comparator<Object> NAME_GUID_COMPARATOR = new Comparator<Object>() {
		
		public int compare(Object o1, Object o2) {
			if (o1 == o2)
				return 0;
			o1 = getMethodElement(o1);
			if (o1 == null) {
				return 0;
			}

			o2 = getMethodElement(o2);
			if (o2 == null) {
				return 0;
			}
			MethodElement e1 = (MethodElement) o1;
			MethodElement e2 = (MethodElement) o2;

			Collator collator = Collator.getInstance();;
			return collator.compare(e1.getName() + e1.getGuid(), e2.getName() + e2.getGuid());
		}

	};
	
	public static final Comparator<Object> REVERSE_PRESENTATION_NAME_COMPARATOR = new Comparator<Object>() {
		
		public int compare(Object o1, Object o2) {
			if (o1 == o2)
				return 0;
			if(!(o1 instanceof DescribableElement)) {
				o1 = TngUtil.unwrap(o1);
				if(!(o1 instanceof DescribableElement)) {
					return 0;
				}
			}
			if(!(o2 instanceof DescribableElement)) {
				o2 = TngUtil.unwrap(o2);
				if(!(o2 instanceof DescribableElement)) {
					return 0;
				}
			}
			return oppositeValue(comparePresentationName((DescribableElement) o1, (DescribableElement) o2));
		}

	};

	public static final Comparator METHOD_TYPE_COMPARATOR = new TypeComparator() {
		/* (non-Javadoc)
		 * @see org.eclipse.epf.library.edit.util.Comparators.TypeComparator#getDefaultComparator()
		 */
		protected Comparator getDefaultComparator() {
			return PresentationContext.INSTANCE.getComparator();
		}

		protected int getOrderId(Object obj) {
			if (obj instanceof MethodElement) {
				int id = ((MethodElement) obj).eClass().getClassifierID();
				if (id == UmaPackage.TERM_DEFINITION) {
					id = Integer.MAX_VALUE;
				}
				return id;
			}
			return -1;
		}
	};
	
	/**
	 * Always sort by presentation name
	 */
	public static final Comparator METHOD_TYPE_PRES_NAME_COMPARATOR = new TypeComparator() {
		/* (non-Javadoc)
		 * @see org.eclipse.epf.library.edit.util.Comparators.TypeComparator#getDefaultComparator()
		 */
		protected Comparator getDefaultComparator() {
			return PresentationContext.INSTANCE.getPresNameComparator();
		}

		protected int getOrderId(Object obj) {
			if (obj instanceof MethodElement) {
				int id = ((MethodElement) obj).eClass().getClassifierID();
				if (id == UmaPackage.TERM_DEFINITION) {
					id = Integer.MAX_VALUE;
				}
				return id;
			}
			return -1;
		}
	};

	public static final Comparator<Object> PLUGINPACKAGE_COMPARATOR = new Comparator<Object>() {
	
		public int compare(Object o1, Object o2) {
			if (o1 == o2)
				return 0;
			o1 = TngUtil.unwrap(o1);
			o2 = TngUtil.unwrap(o2);
			
			
			if (o1 instanceof PluginUIPackagesItemProvider && o2 instanceof MethodPlugin) {
				return -1;
			}
			if (o1 instanceof MethodPlugin && o2 instanceof PluginUIPackagesItemProvider) {
				return 1;
			}
	
			boolean usePresName = PresentationContext.INSTANCE.isShowPresentationNames();
			String s1 = null;
			String s2 = null;
			if (usePresName && o1 instanceof DescribableElement) {
				s1 = ((DescribableElement) o1).getPresentationName();
			} else if (o1 instanceof MethodElement) {
				s1 = ((MethodElement) o1).getName();
			} else if (o1 instanceof PluginUIPackagesItemProvider) {
				s1 = ((PluginUIPackagesItemProvider) o1).getFullName();
			} else if (o1 instanceof String) {
				s1 = (String) o1;
			} 
			
			if (usePresName && o2 instanceof DescribableElement) {
				s2 = ((DescribableElement) o2).getPresentationName();
			} else if (o2 instanceof MethodElement) {
				s2 = ((MethodElement) o2).getName();
			} else if (o2 instanceof PluginUIPackagesItemProvider) {
				s2 = ((PluginUIPackagesItemProvider) o2).getFullName();
			} else if (o2 instanceof String) {
				s2 = (String) o2;
			}
			
			if (s1 == null || s2 == null) {
				return 0;
			}
			
			return s1.compareToIgnoreCase(s2);
		}
	};

	
}
