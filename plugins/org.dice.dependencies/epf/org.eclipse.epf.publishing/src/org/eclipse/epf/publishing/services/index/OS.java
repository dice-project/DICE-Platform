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
package org.eclipse.epf.publishing.services.index;


final class OS
{
    private static boolean isWindows98 = false;
    private static boolean isWindows95 = false;
    private static boolean isWindowsNT = false;
    private static boolean isMacintosh = false;

    static
    {
        String s;

        s = System.getProperty("os.name"); //$NON-NLS-1$

        if(s.equals("Windows NT")) //$NON-NLS-1$
        {
            isWindowsNT = true;
        }
        else if(s.equals("Windows 95")) //$NON-NLS-1$
        {
            isWindows95 = true;
        }
        else if(s.equals("Windows 98")) //$NON-NLS-1$
        {
            isWindows98 = true;
        }
        else if (s.equals("Macintosh") || //$NON-NLS-1$
				 s.equals("macos") ||		//Applet Viewer //$NON-NLS-1$
				 s.equals("Mac OS") ||		//Netscape //$NON-NLS-1$
				 s.equals("MacOS"))			//Internet Exploder //$NON-NLS-1$
        {
            isMacintosh = true;
        }
    }

    private OS()
    {
    }

    static boolean isWindows()
    {
        return (isWindows95() || isWindowsNT() || isWindows98);
    }

    static boolean isWindows95()
    {
        return (isWindows95);
    }

    static boolean isWindowsNT()
    {
        return (isWindowsNT);
    }

    static boolean isMacintosh()
    {
        return (isMacintosh);
    }
}