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
package org.eclipse.epf.persistence;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMISaveImpl;
import org.eclipse.epf.common.service.versioning.EPFVersions;
import org.eclipse.epf.common.service.versioning.VersionUtil;
import org.eclipse.epf.resourcemanager.ResourceManager;
import org.eclipse.epf.uma.MethodElement;

/**
 * XMLSave implementation for library XMI persistence
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MultiFileXMISaveImpl extends XMISaveImpl {

	public static final String SAVE_SEPARATELY_CLASS_SET = "SAVE_SEPARATELY_CLASS_SET"; //$NON-NLS-1$

	/**
	 * Save options to force saving all resources in the resource set, whether
	 * they have been modified or unchanged. Its value must be a string "true"
	 * or "false"
	 */
	public static final String SAVE_ALL = "SAVE_ALL"; //$NON-NLS-1$

	/**
	 * Save option to save objects of the save type together in the same file if
	 * MultiFileUtil.createFileURI() returns the same URI for them.
	 */
	public static final String SAVE_TOGETHER_CLASS_SET = "SAVE_TOGETHER_CLASS_SET"; //$NON-NLS-1$

	public static final String BACK_UP_BEFORE_SAVE = "BACK_UP_BEFORE_SAVE"; //$NON-NLS-1$

	public static final String DISCARD_UNRESOLVED_REFERENCES = "DISCARD_UNRESOLVED_REFERENCES"; //$NON-NLS-1$

	static final String MODIFIED_RESOURCE_SET = "MODIFIED_RESOURCE_SET"; //$NON-NLS-1$

	/**
	 * Save option to specify a TxRecord to log the transactional data for
	 * fail-safe persistence
	 * 
	 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister.FailSafeMethodLibraryPersister
	 * @see TxRecord
	 */
	static final String TX_RECORD = "TX_RECORD"; //$NON-NLS-1$

	public static final String MULTI_FILE = "MULTI_FILE"; //$NON-NLS-1$

	/**
	 * Save option to refresh the workspace when new resource is created and
	 * saved. Its value must be a string "true" or "false"
	 */
	public static final String REFRESH_NEW_RESOURCE = "REFRESH_NEW_RESOURCE"; //$NON-NLS-1$

	/**
	 * Save option to check the resource for modifiable before saving it. Its
	 * value must be a string "true" or "false"
	 */
	public static final String CHECK_MODIFY = "CHECK_MODIFY"; //$NON-NLS-1$

	// private Resource resource;
	private Set saveSeparatelyClassSet;

	private Map options;

	/**
	 * @param helper
	 */
	public MultiFileXMISaveImpl(XMLHelper helper) {
		super(helper);
	}

	XMLHelper getXMLHelper() {
		return helper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl#init(org.eclipse.emf.ecore.xmi.XMLResource,
	 *      java.util.Map)
	 */
	protected void init(XMLResource resource, Map opts) {
		super.init(resource, opts);

//		if (escape != null) {
//			// use MyEscape to not escape whitespaces
//			//
//			escape = new MyEscape();
//		}

		saveSeparatelyClassSet = (Set) opts.get(SAVE_SEPARATELY_CLASS_SET);
		options = opts;

		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet == null) {
			resourceSet = new MultiFileResourceSetImpl();
			resourceSet.getResources().add(resource);
		}
	}

	boolean canSaveSeparately(Object obj) {
		return MultiFileSaveUtil.hasOwnResource(obj, saveSeparatelyClassSet);
	}

	private String getUmaHREF(Resource resource, InternalEObject o) {
		if (o.eIsProxy()
				&& o.eProxyURI().scheme().equals(MultiFileURIConverter.SCHEME)) {
			return o.eProxyURI().toString();
		}
		String href = null;
		if (o instanceof MethodElement) {
			href = helper.getHREF(o);
		}
		if (href == null) {
			PersistencePlugin.getDefault().getLogger().logError("Could not find resource descriptor for resource " + resource + "\n  object: " + o); //$NON-NLS-1$ //$NON-NLS-2$
			return MultiFileSaveUtil.getHREF(resource, o);
		}
		return href;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl#saveHref(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature)
	 */
	protected void saveHref(EObject remote, EStructuralFeature f) {
		if (f instanceof EReference && ((EReference) f).isContainment()
				&& !toDOM) {
			// if this HREF is a contained element, save its ID attribute so the
			// proxy can be cached in the
			// GUIDToElementMap for temporary use before it can be resolved.
			// This helps improve loading time.
			//
			String href = helper.getHREF(remote);
			if (href != null) {
				if (escapeURI != null) {
					href = escapeURI.convert(href);
				}
				String name = helper.getQName(f);
				doc.startElement(name);
				EClass eClass = remote.eClass();
				EClass expectedType = (EClass) f.getEType();
				if (saveTypeInfo ? xmlTypeInfo.shouldSaveType(eClass,
						expectedType, f) : eClass != expectedType
						&& expectedType.isAbstract()) {
					saveTypeAttribute(eClass);
				}
				String id = helper.getID(remote);
				if (id != null) {
					doc.addAttribute(idAttributeName, id);
				}
				doc.addAttribute(XMLResource.HREF, href);
				if (eObjectToExtensionMap != null) {
					processAttributeExtensions(remote);
					if (processElementExtensions(remote)) {
						doc.endElement();
					} else {
						doc.endEmptyElement();
					}
				} else {
					doc.endEmptyElement();
				}
			}
			return;
		}

		super.saveHref(remote, f);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl#saveFeatures(org.eclipse.emf.ecore.EObject)
	 */
	protected boolean saveFeatures(EObject o) {
		try {
			MultiFileXMIResourceImpl resource = (MultiFileXMIResourceImpl) o
					.eResource();
			if (resource != null) {
				EObject container = o.eContainer();
				InternalEObject internalEObject = ((InternalEObject) o);
				if (o instanceof MethodElement && canSaveSeparately(o)
						&& container != null) {
					boolean containsNewFeature = (resource == container
							.eResource());
					MethodElement e = (MethodElement) o;
					URI uri = MultiFileSaveUtil.createURI(e, resource.getResourceSet());
					if (containsNewFeature) {
						if (!internalEObject.eIsProxy()) {
							resource = (MultiFileXMIResourceImpl) MultiFileSaveUtil
									.save(o, uri, options);
						}
					} else {
						// check if there is a ResourceDescriptor for this
						// element
						// create a new one if needed
						//
						ResourceManager resMgr = MultiFileSaveUtil
								.getResourceManager(container.eResource());
						if (resMgr != null
								&& resMgr.getResourceDescriptor(e.getGuid()) == null) {
							// make sure that no other ResourceManager has a
							// ResourceDescriptor for this element
							//
							MultiFileURIConverter uriConverter = (MultiFileURIConverter) resource
									.getResourceSet().getURIConverter();
							if (uriConverter
									.findResourceDescriptor(e.getGuid()) == null) {
								MultiFileSaveUtil.registerWithResourceManager(
										resMgr, e, resource.getFinalURI());
							}
						}
					}

					String href = getUmaHREF(resource, internalEObject);
					doc.addAttribute(XMLResource.HREF, href);
					endSaveFeatures(o, 0, null);
					return true;
				}
				if (o instanceof ResourceManager
						&& helper.getResource() != resource) {
					String href = getUmaHREF(resource, internalEObject);
					doc.addAttribute(XMLResource.HREF, href);
					endSaveFeatures(o, 0, null);
					return true;
				}
			}
			return super.saveFeatures(o);
		} catch (RuntimeException e) {
			CommonPlugin.INSTANCE.log(e);
			if (MultiFileSaveUtil.DEBUG) {
				e.printStackTrace();
				System.err.println("ERROR saving feature: " + o); //$NON-NLS-1$
				System.err.println("  Feature resource: " + o.eResource()); //$NON-NLS-1$
			}
			throw e;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.xmi.impl.XMISaveImpl#addNamespaceDeclarations()
	 */
	public void addNamespaceDeclarations() {
		super.addNamespaceDeclarations();
		
		if (!toDOM) {
			// save tool version info
			//
			for (Iterator iter = VersionUtil.getAllToolIDs().iterator();iter.hasNext();) {
				String toolID = (String)iter.next();
				EPFVersions epfVersions = VersionUtil.getVersions(toolID);
				String nsUri = epfVersions.getNsURI();
				doc.addAttribute(ExtendedMetaData.XMLNS_PREFIX + ":" + toolID, nsUri); //$NON-NLS-1$
				String toolVersion = epfVersions.getMinToolVersionForCurrentLibraryVersion().getToolVersion().toString();
				doc.addAttribute(toolID + ":version", toolVersion); //$NON-NLS-1$
			}
		}
	}
	
	public static class MyEscape extends Escape {
		// EMF 2.2 improved Escape a lot so the override this class to add our code is no longer needed
		// except to make it public
		
		public MyEscape() {
			setMappingLimit(0x10FFFF);
		}
	}

	// TODO: needs to revisit this class periodically since its base class might be updated a
	// lot in EMF.
//	public static class MyEscape extends Escape {
//		private static final int MAX_UTF_MAPPABLE_CODEPOINT = 0x10FFFF;
//
//		protected final char[] BLANK = {};
//		
//		private boolean allowControlCharacters;
//
//		public MyEscape() {
//			super();
//		}
//		
//		@Override
//		public void setAllowControlCharacters(boolean allowControlCharacters) {
//			this.allowControlCharacters = allowControlCharacters;
//		}
//		
//	    /*
//	     *  Convert attribute values:
//	     *  & to &amp;
//	     *  < to &lt;
//	     *  " to &quot;
//	     *  \t to &#x9;
//	     *  \n to &#xA;
//	     *  \r to &#xD;
//	     *  
//	     *  > to &gt; (this is used for XLIFF's)
//	     */
//	    public String convert(String input)
//	    {
//	      boolean changed = false;
//	      int inputLength = input.length();
//	      grow(inputLength);
//	      int outputPos = 0;
//	      int inputPos = 0;
//	      char ch = 0;
//	      while (inputLength-- > 0)
//	      {
//	        ch = input.charAt(inputPos++); // value[outputPos];
//	        switch (ch)
//	        {
//	          case 0x1:
//	          case 0x2:
//	          case 0x3:
//	          case 0x4:
//	          case 0x5:
//	          case 0x6:
//	          case 0x7:
//	          case 0x8:
//	          case 0xB:
//	          case 0xC:
//	          case 0xE:
//	          case 0xF:
//	          case 0x10:
//	          case 0x11:
//	          case 0x12:
//	          case 0x13:
//	          case 0x14:
//	          case 0x15:
//	          case 0x16:
//	          case 0x17:
//	          case 0x18:
//	          case 0x19:
//	          case 0x1A:
//	          case 0x1B:
//	          case 0x1C:
//	          case 0x1D:
//	          case 0x1E:
//	          case 0x1F:
//	          {
//	            if (allowControlCharacters)
//	            {
//	            outputPos = replaceChars(outputPos, CONTROL_CHARACTERS[ch], inputLength);
//	            changed = true;
//	            }
//	            else
//	            {
//	              throw new RuntimeException("An invalid XML character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
//	            }
//	            break;
//	          }
//	          case '&':
//	          {
//	            outputPos = replaceChars(outputPos, AMP, inputLength);
//	            changed = true;
//	            break;
//	          }
//	          case '<':
//	          {
//	            outputPos = replaceChars(outputPos, LESS, inputLength);
//	            changed = true;
//	            break;
//	          }
//	          // begin new code
//	          case '>':
//	          {
//		            outputPos = replaceChars(outputPos, GREATER, inputLength);
//		            changed = true;
//		            break;  
//	          }
//	          // end new code	          
//	          case '"':
//	          {
//	            outputPos = replaceChars(outputPos, QUOTE, inputLength);
//	            changed = true;
//	            break;
//	          }
//	          case '\n':
//	          {
//	            outputPos = replaceChars(outputPos, LF, inputLength);
//	            changed = true;
//	            break;
//	          }
//	          case '\r':
//	          {
//	            outputPos = replaceChars(outputPos, CR, inputLength);
//	            changed = true;
//	            break;
//	          }
//	          case '\t':
//	          {
//	            outputPos = replaceChars(outputPos, TAB, inputLength);
//	            changed = true;
//	            break;
//	          }
//	          default:
//	          {
//	            if (!XMLChar.isValid(ch))
//	            {
//	              if (XMLChar.isHighSurrogate(ch))
//	              {
//	                char high = ch;
//	                if (inputLength-- > 0)
//	                {
//	                  ch = input.charAt(inputPos++); 
//	                  if (XMLChar.isLowSurrogate(ch))
//	                  {
//	                    if (mappableLimit == MAX_UTF_MAPPABLE_CODEPOINT)
//	                    {
//	                      // Every codepoint is supported! 
//	                      value[outputPos++] = high;
//	                      value[outputPos++] = ch;
//	                    }
//	                    else
//	                    {
//	                      // Produce the supplemental character as an entity
//	                      outputPos = replaceChars(outputPos, ("&#x" + Integer.toHexString(XMLChar.supplemental(high, ch)) + ";").toCharArray(), inputLength);
//	                      changed = true;
//	                    }
//	                    break;
//	                  }
//	                  throw new RuntimeException("An invalid low surrogate character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
//	                }
//	                else
//	                {
//	                  throw new RuntimeException("An unpaired high surrogate character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
//	                }
//	              }
//	              else
//	              {
//	                throw new RuntimeException("An invalid XML character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
//	              }
//	            }
//	            else
//	            {
//	              // Normal (BMP) unicode codepoint. See if we know for a fact that the encoding supports it:
//	              if (ch <= mappableLimit)
//	              {
//	                value[outputPos++] = ch;
//	              }
//	              else
//	              {
//	                // We not sure the encoding supports this codepoint, so we write it as a character entity reference.
//	                outputPos = replaceChars(outputPos, ("&#x" + Integer.toHexString(ch) + ";").toCharArray(), inputLength);
//	                changed = true;
//	              }
//	            }
//	            break;
//	          }
//	        }
//	      }
//	      return changed ? new String(value, 0, outputPos) : input;
//	    }
//
//	    /*
//	     *  Convert element values:
//	     *  & to &amp;
//	     *  < to &lt;
//	     *  " to &quot;
//	     *  \n to line separator
//	     *  
//	     *  \r to blank;
//	     *  > to &gt; (this is used for XLIFF's)
//	     */
//	    public String convertText(String input)
//	    {
//	      boolean changed = false;
//	      int inputLength = input.length();
//	      grow(inputLength);
//	      int outputPos = 0;
//	      int inputPos = 0;
//	      char ch;
//	      while (inputLength-- > 0)
//	      {
//	        ch = input.charAt(inputPos++); // value[outputPos];
//	        switch (ch)
//	        {
//	          case 0x1:
//	          case 0x2:
//	          case 0x3:
//	          case 0x4:
//	          case 0x5:
//	          case 0x6:
//	          case 0x7:
//	          case 0x8:
//	          case 0xB:
//	          case 0xC:
//	          case 0xE:
//	          case 0xF:
//	          case 0x10:
//	          case 0x11:
//	          case 0x12:
//	          case 0x13:
//	          case 0x14:
//	          case 0x15:
//	          case 0x16:
//	          case 0x17:
//	          case 0x18:
//	          case 0x19:
//	          case 0x1A:
//	          case 0x1B:
//	          case 0x1C:
//	          case 0x1D:
//	          case 0x1E:
//	          case 0x1F:
//	          {
//	            if (allowControlCharacters)
//	            {
//	              outputPos = replaceChars(outputPos, CONTROL_CHARACTERS[ch], inputLength);
//	              changed = true;
//	            }
//	            else
//	            {
//	              throw new RuntimeException("An invalid XML character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
//	            }
//	            break;
//	          }
//	          case '&':
//	          {
//	            outputPos = replaceChars(outputPos, AMP, inputLength);
//	            changed = true;
//	            break;
//	          }
//	          case '<':
//	          {
//	            outputPos = replaceChars(outputPos, LESS, inputLength);
//	            changed = true;
//	            break;
//	          }
//	          case '"':
//	          {
//	            outputPos = replaceChars(outputPos, QUOTE, inputLength);
//	            changed = true;
//	            break;
//	          }
//	          case '\n':
//	          {
//	            outputPos = replaceChars(outputPos, LINE_FEED, inputLength);
//	            changed = true;
//	            break;
//	          }
//	          // begin modified
//	          case '\r':
//	          {
//	            outputPos = replaceChars(outputPos, BLANK, inputLength);
//	            changed = true;
//	            break;
//	          }
//	          case '>':
//	          {
////	            if (inputPos >= 3 && input.charAt(inputPos - 2) == ']' && input.charAt(inputPos - 3) == ']')
////	            {
////	              outputPos = replaceChars(outputPos, GREATER, inputLength);
////	              changed = true;
////	              break;
////	            }
////	            // continue with default processing
//	              outputPos = replaceChars(outputPos, GREATER, inputLength);
//	              changed = true;
//	              break;
//	          }
//		      // end modified
//	          default:
//	          {
//	            if (!XMLChar.isValid(ch))
//	            {
//	              if (XMLChar.isHighSurrogate(ch))
//	              {
//	                char high = ch;
//	                if (inputLength-- > 0)
//	                {
//	                  ch = input.charAt(inputPos++); 
//	                  if (XMLChar.isLowSurrogate(ch))
//	                  {
//	                    if (mappableLimit == MAX_UTF_MAPPABLE_CODEPOINT)
//	                    {
//	                      // Every codepoint is supported! 
//	                      value[outputPos++] = high;
//	                      value[outputPos++] = ch;
//	                    }
//	                    else
//	                    {
//	                      // Produce the supplemental character as an entity
//	                      outputPos = replaceChars(outputPos, ("&#x" + Integer.toHexString(XMLChar.supplemental(high, ch)) + ";").toCharArray(), inputLength);
//	                      changed = true;
//	                    }
//	                    break;
//	                  }
//	                  throw new RuntimeException("An invalid low surrogate character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
//	                }
//	                else
//	                {
//	                  throw new RuntimeException("An unpaired high surrogate character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
//	                }
//	              }
//	              else
//	              {
//	                throw new RuntimeException("An invalid XML character (Unicode: 0x" + Integer.toHexString(ch) + ") was found in the element content:" + input);
//	              }
//	            }
//	            else
//	            {
//	              // Normal (BMP) unicode codepoint. See if we know for a fact that the encoding supports it:
//	              if (ch <= mappableLimit)
//	              {
//	                value[outputPos++] = ch;
//	              }
//	              else
//	              {
//	                // We not sure the encoding supports this codepoint, so we write it as a character entity reference.
//	                outputPos = replaceChars(outputPos, ("&#x" + Integer.toHexString(ch) + ";").toCharArray(), inputLength);
//	                changed = true;
//	              }
//	            }
//	            break;
//	          }
//	        }
//	      }
//	      return changed ? new String(value, 0, outputPos) : input;
//	    }
//
//	}

	public static boolean checkModifyRequired(Map options) {
		Object opt = options.get(MultiFileXMISaveImpl.CHECK_MODIFY);
		return opt != null ? Boolean.valueOf(opt.toString()).booleanValue()
				: false;
	}
}