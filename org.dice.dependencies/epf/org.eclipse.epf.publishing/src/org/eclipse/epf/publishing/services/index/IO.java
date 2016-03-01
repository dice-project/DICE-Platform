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

import java.awt.Color;
import java.awt.Font;
import java.util.NoSuchElementException;

import com.ibm.icu.util.StringTokenizer;

/*********************************************************************
*  Class with only class methods that simplifies some communications
*  with a www-server and also output to the Java Console Window.
*
*  Due to security limitations, the only www-server reachable,
*  is the server from which the current applet is loaded from.
*
*  This class uses the ErrorWindow class for showing error messages.
*
* @see ruptools.io.ErrorWindow
*
* @version 1.0
* @author Rational Software
*********************************************************************/
class IO
{
   private static boolean debugOn=false;

   /**
   *  Class method that returns a set of string delimiters that can be
   *  used when tokenizing a text string.
   *
   */
   static String getDelim()
   {
      return new String("*"); //$NON-NLS-1$
   }

   /**
   *  Class method that controls if debug info shall be showed or not
   *  in the Java Console Window.
   *
   * @param isOn If <b>true</b> then debug info will be showed.
   *
   */
   static void debug(boolean isOn)
   {
        debugOn = isOn;
   }


   /**
   *  Print Error messages in the Java Console Window
   *
   * @param str String to print
   *
   */
   static void printError( String str )
   {
        System.out.println( "Error:" + str ); //$NON-NLS-1$
   }

   /**
   *  Print Debug messages in the Java Console Window if the debug flag
   *  is set (setDebug method)
   *
   * @param str String to print
   *
   */
   static void printDebug( String str )
   {
        if (debugOn)
             System.out.println( "Debug:" + str ); //$NON-NLS-1$
   }

   static Color getColor(String sPBG)
   {
         // Check if a pre-defined color is specified.
      sPBG = sPBG.trim();
      if (sPBG.equalsIgnoreCase("black")) //$NON-NLS-1$
        return(Color.black);
      if (sPBG.equalsIgnoreCase("blue")) //$NON-NLS-1$
        return(Color.blue);
      if (sPBG.equalsIgnoreCase("cyan")) //$NON-NLS-1$
        return(Color.cyan);
      if (sPBG.equalsIgnoreCase("darkGray")) //$NON-NLS-1$
        return(Color.darkGray);
      if (sPBG.equalsIgnoreCase("gray")) //$NON-NLS-1$
        return(Color.gray);
      if (sPBG.equalsIgnoreCase("green")) //$NON-NLS-1$
        return(Color.green);
      if (sPBG.equalsIgnoreCase("lightGray")) //$NON-NLS-1$
        return(Color.lightGray);
      if (sPBG.equalsIgnoreCase("magenta")) //$NON-NLS-1$
        return(Color.magenta);
      if (sPBG.equalsIgnoreCase("orange")) //$NON-NLS-1$
        return(Color.orange);
      if (sPBG.equalsIgnoreCase("pink")) //$NON-NLS-1$
        return(Color.pink);
      if (sPBG.equalsIgnoreCase("red")) //$NON-NLS-1$
        return(Color.red);
      if (sPBG.equalsIgnoreCase("white")) //$NON-NLS-1$
        return(Color.white);
      if (sPBG.equalsIgnoreCase("yellow")) //$NON-NLS-1$
        return(Color.yellow);
      else
      {
       // If the color is specified in HTML format, build it from the red, green and blue values
      int iRed = 255;
      int iBlue = 255;
      int iGreen = 255;
      if (sPBG.length() == 7 && sPBG.charAt(0) == '#')
      {
        iRed = Integer.parseInt(sPBG.substring(1,3),16);
        iGreen = Integer.parseInt(sPBG.substring(5,7),16);
        iBlue = Integer.parseInt(sPBG.substring(3,5),16);
        return(new Color(iRed, iGreen, iBlue));
      }

        StringTokenizer parser = new StringTokenizer(sPBG, ","); //$NON-NLS-1$
        try
        {
            String sRed = parser.nextToken();
            String sGreen = parser.nextToken();
            String sBlue = parser.nextToken();
            iRed = Integer.parseInt(sRed.trim());
            iGreen = Integer.parseInt(sGreen.trim());
            iBlue = Integer.parseInt(sBlue.trim());
            return new Color(iRed, iGreen, iBlue);

        }
        catch (NoSuchElementException e1)
        {
            System.out.println("IO.getColor\nException 1:" + e1.toString() + sPBG); //$NON-NLS-1$
        }
        catch (Exception e2)
        {
            System.out.println("IO.getColor\nException 2:" + e2.toString() + sPBG); //$NON-NLS-1$
        }
      }
       return Color.black;
    }

    static Font getFont(String sPBG)
     {
        String font = "Arial"; //$NON-NLS-1$
        String style = "PLAIN"; //$NON-NLS-1$
        int size = 12;
        int realStyle=Font.PLAIN;
        sPBG = sPBG.trim();
        StringTokenizer parser = new StringTokenizer(sPBG, ","); //$NON-NLS-1$
        try
        {
           font = parser.nextToken();
           font = font.trim();
           if (font.equalsIgnoreCase("Arial")) //$NON-NLS-1$
              font = "Arial"; //$NON-NLS-1$
           else if (font.equalsIgnoreCase("Courier")) //$NON-NLS-1$
              font = "Courier"; //$NON-NLS-1$
           else if (font.equalsIgnoreCase("Dialog")) //$NON-NLS-1$
             font = "Dialog"; //$NON-NLS-1$
           else if (font.equalsIgnoreCase("Helvetica")) //$NON-NLS-1$
             font = "Helvetica"; //$NON-NLS-1$
           else if (font.equalsIgnoreCase("Symbol")) //$NON-NLS-1$
             font = "Symbol"; //$NON-NLS-1$
           else if (font.equalsIgnoreCase("TimesRoman")) //$NON-NLS-1$
             font = "TimesRoman"; //$NON-NLS-1$

           style = parser.nextToken();
           style = style.trim();
           if(style.equalsIgnoreCase("ITALIC")) //$NON-NLS-1$
           {
             realStyle=Font.ITALIC;
           }
           else if(style.equalsIgnoreCase("BOLD")) //$NON-NLS-1$
           {
             realStyle=Font.BOLD;
           }
           else if(style.equalsIgnoreCase("BOLDITALIC")) //$NON-NLS-1$
           {
             realStyle=Font.BOLD + Font.BOLD;
           }
           size = new Integer(parser.nextToken().trim()).intValue();
        }
        catch (NoSuchElementException e1)
        {
            System.out.println("IO.getFont\nException 1:" + e1.toString()); //$NON-NLS-1$
        }
        catch (Exception e2)
        {
            System.out.println("IO.getFont\nException 2:" + e2.toString()); //$NON-NLS-1$
        }
       return new Font(font, realStyle, size);
    }
}
