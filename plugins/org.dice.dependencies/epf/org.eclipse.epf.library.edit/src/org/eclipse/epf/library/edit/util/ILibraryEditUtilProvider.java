package org.eclipse.epf.library.edit.util;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.validation.IValidationManager;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;

public interface ILibraryEditUtilProvider {

	boolean isSynFree();

	MethodElement getMethodElement(String guid, boolean skipContent);
	
	boolean isDynamicAndExclude(Object obj, Descriptor desc, EReference ref,
			MethodConfiguration config);

	boolean isDynamic(Object obj, Descriptor desc, EReference ref);
	
	boolean isGuidanceDynamic(Object obj, Descriptor desc,
			MethodConfiguration config);

	MethodLibrary getCurrentMethodLibrary();
	
	String getPresentationName(MethodElement element,
			MethodConfiguration config);
	
	IRealizationManager getRealizationManager(MethodConfiguration config);

	MethodElement getCalculatedElement(MethodElement element, MethodConfiguration config);

	boolean inConfig(MethodElement element, MethodConfiguration config);

	IValidationManager getValidationManager();
	
	List<MethodElement> calc0nFeatureValue(MethodElement element,
			EStructuralFeature feature, MethodConfiguration config);
	
	public List<MethodElement> calc0nFeatureValue(MethodElement element,
			OppositeFeature feature, MethodConfiguration config);
	
	public void createUserDefinedTypeContextMenuOnGuidanceNode(Collection<Object> newChildDescriptors);
		
	public Collection<UserDefinedTypeMeta> getUserDefinedTypes();
	
	public UserDefinedTypeMeta getUserDefineType(String id);
	
	public ModifiedTypeMeta getModifiedType(String id);
	
	public Collection<ModifiedTypeMeta> getModifiedTypes();
	
}
