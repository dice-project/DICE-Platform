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

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Hashtable;

import org.eclipse.epf.common.utils.FileUtil;

import com.ibm.icu.util.StringTokenizer;

class MiscStatic
{
  static Hashtable twoValueFile=null;
  static String topDir=null;
  static int topDirL=0;

  static boolean speccharfound=false;
  static boolean tmpspeccharfound=false;

  static long diff;

  //Reads a file where each row contains two values separated with \t sign
  // The syntax of each line can be eighter
  // value  value
  // or
  // "value"    "value"
  //
  // The syntax must be the same for each row in the file.
  static boolean readTwoValueFile(String file, String characterSet)
  {
       IO.printDebug("readTwoValueFile " ); //$NON-NLS-1$
     if(file==null)
     {
        System.err.println("MiscStatic:readTwoValueFile" + HelpMessages.INPUT_PARAMETER_NULL); //$NON-NLS-1$
        return false;
     }
       IO.printDebug("before loadfile "  + file); //$NON-NLS-1$
     String infile = loadFile(file,characterSet);
    IO.printDebug("after loadfile"); //$NON-NLS-1$
      if(!infile.equals(Def.None))
      {
       if(infile.charAt(0)=='"')
       {
        StringTokenizer parser = new StringTokenizer(
                       infile, "\n"); //$NON-NLS-1$

        twoValueFile = new Hashtable();
        while(parser.hasMoreTokens())
        {

           String line = parser.nextToken();
           int firstFnutt = getIndex(line,"\"",0); //$NON-NLS-1$
           int secondFnutt = getIndex(line,"\"", firstFnutt+1); //$NON-NLS-1$

           int thirdFnutt = getIndex(line,"\"", secondFnutt+1); //$NON-NLS-1$
           int forthFnutt = getIndex(line,"\"", thirdFnutt+1); //$NON-NLS-1$

           if(firstFnutt==-1 || secondFnutt==-1 ||
               thirdFnutt==-1 || forthFnutt==-1)
           {
              return false;
           }
           String def = line.substring(firstFnutt+1, secondFnutt);
           String text = line.substring(thirdFnutt+1, forthFnutt);
           twoValueFile.put(def,text);
        }
      }
      else
      {
         StringTokenizer parser = new StringTokenizer(
                       infile, "\t\n"); //$NON-NLS-1$

         twoValueFile = new Hashtable();
         while(parser.hasMoreTokens())
         {
            twoValueFile.put(parser.nextToken().trim(),parser.nextToken().trim());
         }
      }
        return true;
     }
     return false;
  }

    //---------------------------------------------------------------------------------------------------
  //Returns the text within the title tags in the input string
  static String getTitle(String realString)
  {
      if(realString==null)
      {
        System.err.println("MiscStatic:getTitle" + HelpMessages.INPUT_PARAMETER_NULL); //$NON-NLS-1$
        return null;
      }
     String upperString = realString.toUpperCase();
     int titleStart = getIndex(upperString, "TITLE",0); //$NON-NLS-1$
     if(titleStart!=-1)
     {
        int titleStartEndT = getIndex(upperString, ">",titleStart); //$NON-NLS-1$
        if(titleStartEndT!=-1)
        {
          int titleEnd = getIndex(upperString, "</TITLE",titleStartEndT+1); //$NON-NLS-1$
          if(titleEnd!=-1)
          {
            String title = realString.substring(titleStartEndT+1,titleEnd).trim();
            return title;
           }
         }
       }
     return null;
  }


  //--------------------------------------------------------------------------------------------------
  //Return the index within the input string where the first occurance of the sting match
  // starts. Searches from the index defined by the parameter start
  static int getIndex(String str, String match, int start)
  {
     if(str==null)
      {
        System.err.println("MiscStatic:getIndex\n" + HelpMessages.INPUT_PARAMETER_NULL); //$NON-NLS-1$
        return -1;
     }

      if(match==null)
      {
        System.err.println("MiscStatic:getIndex\n" + HelpMessages.INPUT_PARAMETER_NULL); //$NON-NLS-1$
        return -1;
     }
     int found = str.indexOf(match.toUpperCase(), start);
     if(found==-1)
     {
        found = str.indexOf(match.toLowerCase(), start);
     }

     if(found==-1)
     {
        found = str.indexOf(match, start);
     }
     return found;
  }
   //--------------------------------------------------------------------------------------------------
   // Prints the a string to a DataOutputStream
   static boolean print(OutputStreamWriter outP, String str)
   {
    if(outP==null)
    {
        System.err.println("MiscStatic:print\n" + HelpMessages.BAD_OUTPUT_STREAM); //$NON-NLS-1$
        return false;
     }
     if(str==null)
     {
        System.err.println("MiscStatic:print\n" + HelpMessages.INPUT_PARAMETER_NULL); //$NON-NLS-1$
        return false;
     }
       try
        {
           outP.write(str,0,str.length());
           return true;
        }
        catch(Exception e)
        {
         System.err.println("MiscStatic:print" + HelpMessages.WRITE_EXCEPTION + //$NON-NLS-1$
              e.toString());
        }

        return false;
   }

   // Prints the a string to a DataOutputStream in UTF format
   static boolean printUTF(DataOutputStream outP, String str)
   {
    if(outP==null)
    {
        System.err.println("MiscStatic:printUTF\n" + HelpMessages.BAD_OUTPUT_STREAM); //$NON-NLS-1$
        return false;
     }
     if(str==null)
     {
        System.err.println("MiscStatic:printUTF\n" + HelpMessages.INPUT_PARAMETER_NULL); //$NON-NLS-1$
        return false;
     }
       try
        {

         outP.writeUTF(str);
         return true;
        }
        catch(Exception e)
        {
         System.err.println("MiscStatic:printUTF" + HelpMessages.WRITE_EXCEPTION + //$NON-NLS-1$
              e.toString());
           return false;
        }
   }


  static void printSpace(OutputStreamWriter outP, int noOfSpace)
   {
      for(int i=0;i<noOfSpace;i++)
      {
        try
        {
         outP.write("&nbsp;",0,6); //$NON-NLS-1$
        }
        catch(Exception e)
        {
           System.err.println("MiscStatic:printSpace" + HelpMessages.WRITE_EXCEPTION + //$NON-NLS-1$
              e.toString());
        }
      }

   }

   //---------------------------------------------------------------------------------------------------
   // Read a file and return it as a concatenated string.
   static String loadFile(String fileName,String characterset)
   {	  
	   try
	   {
		   return FileUtil.readFile(new File(fileName), characterset).toString();   
	   }
	   catch (Exception ex)
	   {
		   ;
	   }
	   
	   return ""; //$NON-NLS-1$
	}

   // Converts all letters like \ufffd,\ufffd,\ufffd to special codes
   static void specChar(String in, String out, String characterset)
   {
     if(in==null)
     {
        System.err.println("MiscStatic:specChar\n" + HelpMessages.INPUT_PARAMETER_NULL); //$NON-NLS-1$
        return;
     }

     if(out==null)
     {
        System.err.println("MiscStatic:specChar\n" + HelpMessages.INPUT_PARAMETER_NULL); //$NON-NLS-1$
        return;
     }
        String tmpFile = "13d84.hwr"; //$NON-NLS-1$
        String infile = null;
        String tmpInfile = MiscStatic.loadFile(in,characterset);
        boolean writeResult = true;
        try
        {

           OutputStreamWriter outP;
           if(characterset!=null)
           {
             outP = new OutputStreamWriter(
                  new FileOutputStream(tmpFile),characterset);
           }
            else
            {
               outP = new OutputStreamWriter(
                  new FileOutputStream(tmpFile));
            }

           writeResult  = MiscStatic.print(outP,tmpInfile);
           if(!writeResult)
           {
              System.err.println("MiscStatic:specChar" + HelpMessages.WRITE_EXCEPTION + //$NON-NLS-1$
              in);
           }
           outP.close();
         }
         catch(Exception e)
         {
             System.err.println("MiscStatic:specChar" + HelpMessages.WRITE_EXCEPTION + //$NON-NLS-1$
              e);
         }

           if(writeResult)
           {
             infile = MiscStatic.loadFile(tmpFile,characterset);
           }
           else
           {
             infile=null;
           }




        if(infile!=null)
        {
           try
            {
             StringBuffer resultfile= new StringBuffer();

             for(int i=0;i<infile.length();i++)
             {
                tmpspeccharfound=false;
                char t = infile.charAt(i);
                if(t!='&')
                {
                    if((t>'a' && t<'z') || (t>'A' && t<'Z') || (t>'0' && t<'9') ||
                       t=='<' || t=='>' || t=='/' || t=='.' || t=='=' ||
                       t=='-' || t=='%' || t=='@')
                    {
                        resultfile.append(t);
                    }
                    else
                    {
                      resultfile.append(MiscStatic.toSpecChar(t));
                    }
                }
                else
                {
                   char t2 = infile.charAt(i+1);
                   if(t2==' ' || t2=='\n')
                   {
                     resultfile.append("&amp;"); //$NON-NLS-1$
                     tmpspeccharfound=true;
                   }
                   else
                   {
                      resultfile.append(MiscStatic.toSpecChar(t));
                   }
                }
                if(tmpspeccharfound==true)
                {
                   speccharfound=true;
                }
             }

             if(speccharfound)
             {
                System.out.println(HelpMessages.WRITE_FILE + out);
                OutputStreamWriter outP;
                if(characterset!=null)
                {
                  outP = new OutputStreamWriter(
                      new FileOutputStream(out),characterset);
                }
                else
                {
                   outP = new OutputStreamWriter(
                      new FileOutputStream(out));
                }
                MiscStatic.print(outP, resultfile.toString());
                outP.close();
             }

             speccharfound=false;

            }
            catch(Exception e)
            {
               System.err.println("MiscStatic:specChar" + HelpMessages.WRITE_EXCEPTION + //$NON-NLS-1$
                 e);
            }

              File tmp = new File(tmpFile);
              tmp.delete();

     }
   }

   //converts a character to a special code if needed
   static String toSpecChar(char token)
   {
        tmpspeccharfound=true;
        if(token=='\ufffd')
		{
		    return "&aring;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
	      return "&Aring;"; //$NON-NLS-1$
	    }

		if(token=='\ufffd')
		{
			return "&Auml;"; //$NON-NLS-1$
		}
	    if(token=='\ufffd')
	    {
		 return "&auml;"; //$NON-NLS-1$
		}
	    if(token=='\ufffd')
		{
		  return "&Ouml;"; //$NON-NLS-1$
		}
	    if(token=='\ufffd')
		{
		  return "&ouml;"; //$NON-NLS-1$
		}
	    if(token=='\ufffd')
		{
			return "&Eacute;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&Egrave;"; //$NON-NLS-1$
		}

		if(token=='\ufffd')
		{
			   return "&Uuml;"; //$NON-NLS-1$
		}

		if(token=='\ufffd')
		{
			 return "&aacute;"; //$NON-NLS-1$
		}

		if(token=='\ufffd')
		{
			 return "&Aacute;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&agrave;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&eacute;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&egrave;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&iacute;"; //$NON-NLS-1$

		}
		if(token=='\ufffd')
		{
			return "&igrave;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&euml;"; //$NON-NLS-1$
		}
	    if(token=='\ufffd')
		{
			return "&oacute;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			 return "&ograve;"; //$NON-NLS-1$
		}

		if(token=='\ufffd')
		{
			return "&uacute;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&ugrave;"; //$NON-NLS-1$

		}
	    if(token=='\ufffd')
		 {
			return "&uuml;"; //$NON-NLS-1$

		}

		 if(token=='\ufffd')
		 {
			return "&yuml;"; //$NON-NLS-1$
		 }
		if(token=='\ufffd')
		{
			return "&acirc;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			 return "&Acirc;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&ecirc;"; //$NON-NLS-1$
		}
	    if(token=='\ufffd')
		{
		return "&Ecirc;"; //$NON-NLS-1$

		}
		if(token=='\ufffd')
		{
		   return "&ucirc;"; //$NON-NLS-1$

		}
		if(token=='\ufffd')
		{
			return "&Ucirc;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&ocirc;"; //$NON-NLS-1$
		}
		 if(token=='\ufffd')
		{
			return "&Ocirc;"; //$NON-NLS-1$

		}
		if(token=='\ufffd')
		{
			return "&icirc;"; //$NON-NLS-1$

		}

		if(token=='\ufffd')
		{
			 return "&AElig;"; //$NON-NLS-1$
		}

	    if(token=='\ufffd')
		{
			 return "&aelig;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			 return "&Icirc;"; //$NON-NLS-1$

		}
		if(token=='\ufffd')
		{
			return "&Agrave;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
        {
	       return "&Atilde;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			 return "&atilde;"; //$NON-NLS-1$
		}

		if(token=='\ufffd')
		{
			 return "&ETH;"; //$NON-NLS-1$
		}

		if(token=='\ufffd')
		{
			 return "&eth;"; //$NON-NLS-1$
		}

		if(token=='\ufffd')
		{
			 return "&Igrave;"; //$NON-NLS-1$
		}
	    if(token=='\ufffd')
		{
		    return "&Ntilde;"; //$NON-NLS-1$
		}

	    if(token=='\ufffd')
		{
			return "&ntilde;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&Otilde;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&otilde;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&szlig;"; //$NON-NLS-1$
		}

	    if(token=='\ufffd')
		{
			return "&THORN;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
			return "&thorn;"; //$NON-NLS-1$
		}

        if(token=='\ufffd')
		{
		  return "&Oslash;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
		  return "&oslash;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
		  return "&Ograve;"; //$NON-NLS-1$
		}
	    if(token=='\ufffd')
		{
			  return "&Ugrave;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
		   return "&iuml;"; //$NON-NLS-1$
		}

	    if(token=='\ufffd')
		{
			  return "&Yacute;"; //$NON-NLS-1$
		}
		if(token=='\ufffd')
		{
		   return "&yacute;"; //$NON-NLS-1$
		}


		if(token=='\ufffd')
		{
			return "&Ccedil;"; //$NON-NLS-1$
		}

		if(token=='\ufffd')
		{
			return "&ccedil;"; //$NON-NLS-1$
		}

		/*if(token=='\ufffd')
		{
			return "&frac12;";
		}*/

        tmpspeccharfound = false;
		return new Character(token).toString();

     }

      /**
  * Trims double quotes from an argument.
  * @param str	the string to trim.
  * @return the trimmed string.
 **/

 static String trimQuotes(String str) {

  int len = str.length();
  if(len <= 0) return str;

  if(str.charAt(len - 1) == '"') str = str.substring(0, len - 1);
  if(str.charAt(0) == '"') str = str.substring(1);

  return str;
  }
}
