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

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/**
 * JarResources: JarResources maps all resources included in a
 * Zip or Jar file. Additionaly, it provides a method to extract one
 * as a blob.
 */
final class JarResources {

   // external debug flag
   boolean debugOn=false;

   // jar resource mapping tables
   private Hashtable htJarContents=new Hashtable();

   // a jar file
   private String jarFileName;

   /**
    * creates a JarResources. It extracts all resources from a Jar
    * into an internal hashtable, keyed by resource names.
    * @param jarFileName a jar or zip file
    */
   JarResources(String jarFileName) {
      this.jarFileName=jarFileName;
      init();
   }

   /**
    * Extracts a jar resource as a blob.
    * @param name a resource name.
    */
   byte[] getResource(String name) {
      return (byte[])htJarContents.get(name);
   }

   byte[] getResource()
   {
      Enumeration keys = htJarContents.keys();
      return (byte[])htJarContents.get(keys.nextElement());
   }

/**
  * initializes internal hash tables with Jar file resources.
  */
private void init() {
  try {
      // System.out.println("Processing Jar Archive " + jarFileName);
	  DataInputStream in = null;
      if(jarFileName.startsWith(Def.Http) || jarFileName.startsWith(Def.Https)|| jarFileName.startsWith(Def.File))
      {
        // System.out.println("Opening the Jar File as a URL stream");
		in = new DataInputStream( new URL(jarFileName).openStream() );
      }
      else
      {
		// System.out.println("Opening the Jar File as a local file stream");
        in = new DataInputStream( new FileInputStream(jarFileName));
      }
      // extract resources and put them into the hashtable.
      BufferedInputStream bis=new BufferedInputStream(in);
      ZipInputStream zis=new ZipInputStream(bis);
      ZipEntry ze=null;
      while ((ze=zis.getNextEntry())!=null)
      {
        if (ze.isDirectory()) {
           continue;
        }
        if (debugOn) {
           System.out.println(
              "ze.getName()="+ze.getName()+","+"getSize()="+ze.getSize() //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
           );
        }
        int size=(int)ze.getSize();
        // -1 means unknown size.
		if ( size == -1 ) {
			System.out.println("ERROR: Returned size of zip file is -1. Filename: " + ze.getName()); //$NON-NLS-1$
		}

        byte[] b=new byte[(int)size];
        int rb=0;
        int chunk=0;
        while (((int)size - rb) > 0) {
           chunk=zis.read(b,rb,(int)size - rb);
           if (chunk==-1) {
               break;
           }
           rb+=chunk;
        }
        // add to internal resource hashtable
        htJarContents.put(ze.getName(),b);
        if (debugOn) {
           System.out.println(
               ze.getName()+"  rb="+rb+ //$NON-NLS-1$
               ",size="+size+ //$NON-NLS-1$
               ",csize="+ze.getCompressedSize() //$NON-NLS-1$
               );
        }
     }
  } catch (NullPointerException e) {
     System.out.println("done."); //$NON-NLS-1$
  } catch (FileNotFoundException e) {
     e.printStackTrace();
  } catch (IOException e) {
     e.printStackTrace();
  }
}

   /**
    * Dumps a zip entry into a string.
    * @param ze a ZipEntry
    */
   private String dumpZipEntry(ZipEntry ze) {
       StringBuffer sb=new StringBuffer();
       if (ze.isDirectory()) {
          sb.append("d "); //$NON-NLS-1$
       } else {
          sb.append("f "); //$NON-NLS-1$
       }
       if (ze.getMethod()==ZipEntry.STORED) {
          sb.append("stored   "); //$NON-NLS-1$
       } else {
          sb.append("defalted "); //$NON-NLS-1$
       }
       sb.append(ze.getName());
       sb.append("\t"); //$NON-NLS-1$
       sb.append(""+ze.getSize()); //$NON-NLS-1$
       if (ze.getMethod()==ZipEntry.DEFLATED) {
          sb.append("/"+ze.getCompressedSize()); //$NON-NLS-1$
       }
       return (sb.toString());
   }

}	// End of JarResources class.
