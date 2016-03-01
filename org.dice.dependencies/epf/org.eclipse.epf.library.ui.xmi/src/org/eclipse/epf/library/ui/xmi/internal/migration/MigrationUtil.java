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
package org.eclipse.epf.library.ui.xmi.internal.migration;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.common.CommonPlugin;
import org.eclipse.epf.common.IHTMLFormatter;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.library.xmi.XMILibraryResourceManager;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ecore.EProperty;

/**
 * Utility class to help migrate and format the html content.
 * 
 * @author JInhua Xi
 * @author Weiping Lu
 * @since 1.0
 */
public class MigrationUtil {
	
	public static Set<String> cdataFeatureNames = new HashSet<String>();
	private static boolean localDebug = false;
	static {
		cdataFeatureNames.add("alternatives"); //$NON-NLS-1$
		cdataFeatureNames.add("mainDescription"); //$NON-NLS-1$
		cdataFeatureNames.add("howToStaff"); //$NON-NLS-1$
		cdataFeatureNames.add("keyConsiderations"); //$NON-NLS-1$
		cdataFeatureNames.add("purpose"); //$NON-NLS-1$
		cdataFeatureNames.add("scope");  //$NON-NLS-1$
		cdataFeatureNames.add("usageNotes");  //$NON-NLS-1$
		
		cdataFeatureNames.add("skills");  //$NON-NLS-1$
		cdataFeatureNames.add("assignmentApproaches");  //$NON-NLS-1$
		cdataFeatureNames.add("synonyms");  //$NON-NLS-1$
		cdataFeatureNames.add("sectionDescription");  //$NON-NLS-1$
		cdataFeatureNames.add("impactOfNotHaving");  //$NON-NLS-1$
		cdataFeatureNames.add("reasonsForNotNeeding");  //$NON-NLS-1$
		cdataFeatureNames.add("briefOutline");  //$NON-NLS-1$
		cdataFeatureNames.add("representationOptions");  //$NON-NLS-1$
		cdataFeatureNames.add("usageGuidance");  //$NON-NLS-1$
		cdataFeatureNames.add("refinedDescription");  //$NON-NLS-1$
		cdataFeatureNames.add("activityEntryState");  //$NON-NLS-1$
		cdataFeatureNames.add("activityExitState");  //$NON-NLS-1$
		cdataFeatureNames.add("externalDescription");  //$NON-NLS-1$
		cdataFeatureNames.add("packagingGuidance");  //$NON-NLS-1$
		cdataFeatureNames.add("additionalInfo");  //$NON-NLS-1$
		cdataFeatureNames.add("problem");  //$NON-NLS-1$
		cdataFeatureNames.add("background");  //$NON-NLS-1$
		cdataFeatureNames.add("goals");  //$NON-NLS-1$
		cdataFeatureNames.add("application");  //$NON-NLS-1$
		cdataFeatureNames.add("levelsOfAdoption");  //$NON-NLS-1$
		cdataFeatureNames.add("scale");  //$NON-NLS-1$
		cdataFeatureNames.add("projectCharacteristics");  //$NON-NLS-1$
		cdataFeatureNames.add("riskLevel");  //$NON-NLS-1$
		cdataFeatureNames.add("estimatingTechnique");  //$NON-NLS-1$
		cdataFeatureNames.add("projectMemberExpertise");  //$NON-NLS-1$
		cdataFeatureNames.add("typeOfContract");  //$NON-NLS-1$
	
	}

	private static boolean isHtmlFeature(EStructuralFeature f) {
		return cdataFeatureNames.contains(f.getName());
	}

	public static void formatValue(MethodElement element) throws Exception {
		List properties = element.getInstanceProperties();
		if (properties != null) {
			// get all string type attributes

//			HTMLFormatter formater = new HTMLFormatter();
			IHTMLFormatter formater = (IHTMLFormatter) ExtensionHelper.createExtensionForJTidy(
					CommonPlugin.getDefault().getId(), "htmlFormatter");  //$NON-NLS-1$
			
			if (ResourceHelper.getDefaultResourceMgr() == null) {
				ResourceHelper.setDefaultResourceMgr(new XMILibraryResourceManager());
			}
			
			boolean seen = false;
			for (int i = 0; i < properties.size(); i++) {
				EProperty property = (EProperty) properties.get(i);
				if (property != null) {
					EStructuralFeature feature = property
							.getEStructuralFeature();
				
//					EStructuralFeature feature = (EStructuralFeature) properties
//							.get(i);
//	
					Object value = element.eGet(feature);
					Object newValue = value;
					if (isHtmlFeature(feature) && value instanceof String) {
						if (! seen && localDebug) {							
							System.out.println("LD> element: " + element.eClass().getName());//$NON-NLS-1$
							seen = true;
						}
						
						try {
							
							String str0 = validateGuidAttribute((String) value);							
							String str1 = ResourceHelper.validateContent(element, str0);
							
							StrUtil.during_migration = true;
							newValue = formater.formatHTML((String) str1);
							StrUtil.during_migration = false;

							if (localDebug) {
								System.out.println("LD> feature: " + feature.getName());//$NON-NLS-1$
								System.out.println("LD> str0: " + value);				//$NON-NLS-1$
								if (! str1.equals(str0)) {
									System.out.println("LD> str1: " + str1);			//$NON-NLS-1$
								}
								if (! newValue.equals(str1)) {
									System.out.println("LD> newValue: " + newValue);			//$NON-NLS-1$
								}
							}
							
//							newValue = str2;
							
						} catch (UnsupportedEncodingException e) {
							// Not change value if it cannot be formatted
							// Should also write to log for user attention
							continue;
						}
						element.eSet(feature, newValue);
					}
				}
			}
		}
	}

	private static String validateGuidAttribute(String source) {
		String str = validateGuidAttribute(source,  ResourceHelper.p_link_ref);
		str = validateGuidAttribute(str, ResourceHelper.p_area_ref);
		return str;
	}
	
	private static String validateGuidAttribute(String source, Pattern pattern) {
		StringBuffer sb = new StringBuffer();
		Matcher m = pattern.matcher(source);

		while (m.find()) {
			String text = m.group();
			String replacement = text;			
			
			Map attributeMap = ResourceHelper.getAttributesFromLink(pattern, text);
			
			if (localDebug) {
				System.out.println("LD> text:      " + text);					//$NON-NLS-1$
				System.out.println("LD> attributeMap: " + attributeMap);		//$NON-NLS-1$
			}
			
			String guid = attributeMap == null ? null : (String) attributeMap.get(ResourceHelper.TAG_ATTR_GUID);
			String href = attributeMap == null ? null : (String) attributeMap.get(ResourceHelper.TAG_ATTR_HREF);

			int ix0 = -1;
			int ix1 = -1;
			if (guid == null && href != null) {
				ix0 = href.lastIndexOf(",");			//$NON-NLS-1$
				ix1 = href.lastIndexOf(".html");	//$NON-NLS-1$
			}
			
			if (ix0 >= 0 && ix1 > ix0) {
				
				guid = href.substring(ix0 + 1, ix1);
				//To do: validate if guid is valid here, otherwise, no replacement
				
				String replaced = href + "\"";  //$NON-NLS-1$
				String replacing = href + "\" guid=\""+guid + "\"";	//$NON-NLS-1$ //$NON-NLS-2$
				//replacement = TextReferenceReplacerImpl.replaceAll(text, replaced, replacing);
				replacement = text.replace(replaced, replacing);

				if (localDebug) {
					System.out.println("LD>  replaced: " + replaced);	//$NON-NLS-1$
					System.out.println("LD> replacing: " + replacing);	//$NON-NLS-1$
					System.out.println("");								//$NON-NLS-1$
				}
			}			
			m.appendReplacement(sb, Matcher.quoteReplacement(replacement));				
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	
	
}
