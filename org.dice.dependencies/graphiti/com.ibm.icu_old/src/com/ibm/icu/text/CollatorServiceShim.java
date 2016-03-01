/**
*******************************************************************************
* Copyright (C) 2003-2011, International Business Machines Corporation and         *
* others. All Rights Reserved.                                                *
*******************************************************************************
*/

package com.ibm.icu.text;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;

import com.ibm.icu.impl.ICULocaleService;
import com.ibm.icu.impl.ICULocaleService.LocaleKeyFactory;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.ICUService;
import com.ibm.icu.impl.ICUService.Factory;
import com.ibm.icu.text.Collator.CollatorFactory;
import com.ibm.icu.util.ULocale;

final class CollatorServiceShim extends Collator.ServiceShim {

    Collator getInstance(ULocale locale) {
    // use service cache, it's faster than instantiation
//          if (service.isDefault()) {
//              return new RuleBasedCollator(locale);
//          }
        try {
            ULocale[] actualLoc = new ULocale[1];
            Collator coll = (Collator)service.get(locale, actualLoc);
            if (coll == null) {
                ///CLOVER:OFF
                //Can't really change coll after it's been initialized
                throw new MissingResourceException("Could not locate Collator data", "", "");
                ///CLOVER:ON
            }
            coll = (Collator) coll.clone();
            coll.setLocale(actualLoc[0], actualLoc[0]); // services make no distinction between actual & valid
            return coll;
        }
        catch (CloneNotSupportedException e) {
        ///CLOVER:OFF
            throw new IllegalStateException(e.getMessage());
        ///CLOVER:ON
        }
    }

    Object registerInstance(Collator collator, ULocale locale) {
        return service.registerObject(collator, locale);
    }

    Object registerFactory(CollatorFactory f) {
        class CFactory extends LocaleKeyFactory {
            CollatorFactory delegate;

            CFactory(CollatorFactory fctry) {
                super(fctry.visible());
                this.delegate = fctry;
            }

            public Object handleCreate(ULocale loc, int kind, ICUService srvc) {
                Object coll = delegate.createCollator(loc);
                return coll;
            }

            public String getDisplayName(String id, ULocale displayLocale) {
                ULocale objectLocale = new ULocale(id);
                return delegate.getDisplayName(objectLocale, displayLocale);
            }

            public Set<String> getSupportedIDs() {
                return delegate.getSupportedLocaleIDs();
            }
        }

        return service.registerFactory(new CFactory(f));
    }

    boolean unregister(Object registryKey) {
        return service.unregisterFactory((Factory)registryKey);
    }

    Locale[] getAvailableLocales() {
        // TODO rewrite this to just wrap getAvailableULocales later
        Locale[] result;
        if (service.isDefault()) {
            ClassLoader cl = getClass().getClassLoader();
            result = ICUResourceBundle.getAvailableLocales(ICUResourceBundle.ICU_COLLATION_BASE_NAME, cl);
        } else {
            result = service.getAvailableLocales();
        }
        return result;
    }

    ULocale[] getAvailableULocales() {
        ULocale[] result;
        if (service.isDefault()) {
            ClassLoader cl = getClass().getClassLoader();
            result = ICUResourceBundle.getAvailableULocales(ICUResourceBundle.ICU_COLLATION_BASE_NAME, cl);
        } else {
            result = service.getAvailableULocales();
        }
        return result;
    }

    String getDisplayName(ULocale objectLocale, ULocale displayLocale) {
        String id = objectLocale.getName();
        return service.getDisplayName(id, displayLocale);
    }

    private static class CService extends ICULocaleService {
        CService() {
            super("Collator");

            class CollatorFactory extends ICUResourceBundleFactory {
                CollatorFactory() {
                    super(ICUResourceBundle.ICU_COLLATION_BASE_NAME);
                }

                protected Object handleCreate(ULocale uloc, int kind, ICUService srvc) {
                    return new RuleBasedCollator(uloc);
                }
            }

            this.registerFactory(new CollatorFactory());
            markDefault();
        }
        ///CLOVER:OFF
        // The following method can not be reached by testing
        protected Object handleDefault(Key key, String[] actualIDReturn) {
            if (actualIDReturn != null) {
                actualIDReturn[0] = "root";
            }
            try {
                return new RuleBasedCollator(ULocale.ROOT);
            }
            catch (MissingResourceException e) {
                return null;
            }
        }
        ///CLOVER:ON
    }
    private static ICULocaleService service = new CService();
}
