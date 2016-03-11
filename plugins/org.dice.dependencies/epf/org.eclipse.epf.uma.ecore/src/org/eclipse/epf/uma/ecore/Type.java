package org.eclipse.epf.uma.ecore;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;

public class Type {

	private EClassifier classifier;
	private static Map<EClassifier, Type> map = new HashMap<EClassifier, Type>();

	public synchronized static Type getInstance(EClassifier classifier) {
		Type type = map.get(classifier);
		if (type == null) {
			type = new Type(classifier);
			map.put(classifier, type);
		}

		return type;
	}

	private Type(EClassifier classifier) {
		this.classifier = classifier;
	}

	public String getName() {
		return classifier.getName();
	}

	public boolean isInstance(Object object) {
		return classifier.isInstance(object);
	}

}
