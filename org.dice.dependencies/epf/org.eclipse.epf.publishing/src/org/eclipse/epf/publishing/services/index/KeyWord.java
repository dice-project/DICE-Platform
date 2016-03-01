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

import java.io.OutputStreamWriter;
import java.util.Vector;

class KeyWord
{
   private String keyWord=null;
   private Vector myKeyWords=null;
   private Vector myDocuments=null;
   private SpecKeyWord mySpecKeyWord=null;
   private KeyWord parentM=null;
   KeyWordDef def = null;
   
   KeyWord(KeyWordDef def)
   {
	   this.def = def;
	   keyWord = def.getKey();
   }

   private KeyWord(KeyWordDef def, String keyWord, KeyWord key)
   {
	   this.def = def;
	   this.keyWord = keyWord;
	   this.parentM = key;
	   
   }

   String getKeyWord()
   {
     return keyWord;
   }

   void addKeyWord(KeyWordDef def, String key, Document doc)
   {	   
     int index = MiscStatic.getIndex(key, KeyWordIndexHelper.defObj.levelSeparatorReplace, 0);
     //If the bottom of the keyword is reached
     if(index==-1)
     {
        addDocument(doc);
     }
     else
     {
        int index2 =  MiscStatic.getIndex(key, KeyWordIndexHelper.defObj.levelSeparatorReplace, index+1);
        String nextKeyWord=null;
        if(index2!=-1)
        {
           nextKeyWord = key.substring(index+1,index2);
        }
        else
        {
          nextKeyWord = key.substring(index+1);
        }

        KeyWord tmpKey = createKeyWord(def, nextKeyWord);
        if(tmpKey!=null)
        {
          tmpKey.addKeyWord(def, key.substring(index+1), doc);
        }
     }
   }

   void setSpecKeyWord(String relatedKey, boolean see)
   {
     IO.printDebug("setSpecKeyWord " + relatedKey); //$NON-NLS-1$
     mySpecKeyWord = new SpecKeyWord(relatedKey, see);
   }

   void insertSpecKeyWord(String specKeyWordString, String relatedKeyWord)
   {

      boolean found=false;
      int i=0;
      String firstKeyWord = specKeyWordString;
      int pos = specKeyWordString.indexOf(KeyWordIndexHelper.defObj.levelSeparatorReplace,0);
      if(pos!=-1)
      {
        firstKeyWord = specKeyWordString.substring(0,pos);
      }

      while(!found && myKeyWords!=null && i<myKeyWords.size())
      {
         KeyWord tmpK =  (KeyWord)myKeyWords.elementAt(i);
         if(firstKeyWord.equals(tmpK.getKeyWord()))
         {

         found=true;
         if(pos!=-1)
          {
            tmpK.insertSpecKeyWord(specKeyWordString.substring(pos+1), relatedKeyWord);
          }
          else
          {
            tmpK.setSpecKeyWord(relatedKeyWord, false);
          }
         }
          i++;
      }
      if(!found)
      {
         int index=-1;
         i=0;
         KeyWord newKeyWord2 = new KeyWord(null, firstKeyWord, this);
         IO.printDebug("new keyword " + firstKeyWord); //$NON-NLS-1$
         if(pos==-1)
         {
           newKeyWord2.setSpecKeyWord(relatedKeyWord, true);
         }
         else
         {
             newKeyWord2.insertSpecKeyWord(specKeyWordString.substring(pos+1), relatedKeyWord);
         }
         if(myKeyWords==null)
         {
            IO.printDebug("myKeyWords"); //$NON-NLS-1$
            myKeyWords = new Vector();
         }
         while(index==-1 && i<myKeyWords.size())
         {
            KeyWord tmpK =  (KeyWord)myKeyWords.elementAt(i);
            if(newKeyWord2.getKeyWord().toUpperCase().
                  compareTo(tmpK.getKeyWord().toUpperCase())<0)
            {
                index = i;
            }
            else if(newKeyWord2.getKeyWord().toUpperCase().
                  compareTo(tmpK.getKeyWord().toUpperCase())==0)
            {

                if(newKeyWord2.getKeyWord().compareTo(tmpK.getKeyWord())!=0)
                {
                   if(newKeyWord2.getKeyWord().compareTo(tmpK.getKeyWord())>0)
                   {
                     index = i;
                   }
                   else
                   {
                      index = i-1;
                   }
                }
            }
            i++;
         }

         if(index!=-1)
         {
              myKeyWords.insertElementAt(newKeyWord2, index);
         }
         else
         {
            myKeyWords.insertElementAt(newKeyWord2, myKeyWords.size());
         }
      }
   }

   private KeyWord createKeyWord(KeyWordDef def, String key)
   {

     if(myKeyWords==null)
     {
        myKeyWords = new Vector();
        KeyWord newKeyWord = new KeyWord(def, key, this);
        myKeyWords.addElement(newKeyWord);
        return newKeyWord;
     }
     else
     {
        int index=-1;
         int i=0;
          KeyWord newKeyWord2 = new KeyWord(def, key, this);
         while(index==-1 && i<myKeyWords.size())
         {
            KeyWord tmpK =  (KeyWord)myKeyWords.elementAt(i);


            if(newKeyWord2.getKeyWord().toUpperCase().
                compareTo(tmpK.getKeyWord().toUpperCase())<0)
            {
                  index = i;
            }
            else if(newKeyWord2.getKeyWord().toUpperCase().
                compareTo(tmpK.getKeyWord().toUpperCase())==0)
            {

                if(newKeyWord2.getKeyWord().compareTo(tmpK.getKeyWord())!=0)
                {
                   if(newKeyWord2.getKeyWord().compareTo(tmpK.getKeyWord())<0)
                   {
                     index = i;
                   }
                   else
                   {
                      index = i+1;
                   }
                }
                else
                {
                  return tmpK;
                }
            }
            i++;
         }

         if(index!=-1)
         {
              myKeyWords.insertElementAt(newKeyWord2, index);
              return newKeyWord2;
         }
         else
         {
            myKeyWords.insertElementAt(newKeyWord2, myKeyWords.size());
            return newKeyWord2;
         }

     }
   }

   private void addDocument(Document doc)
   {
    doc.setKeyWord(this);
      if(myDocuments==null)
      {
         myDocuments = new Vector();
         myDocuments.addElement(doc);
      }
      else
      {
         int index=-1;
         int i=0;
         while(index==-1 && i<myDocuments.size())
         {
            Document tmpD =  (Document)myDocuments.elementAt(i);
            if(doc.getTitle().compareTo(tmpD.getTitle())<0)
            {
                index = i;
            }
            i++;
         }

         if(index!=-1)
         {
              myDocuments.insertElementAt(doc, index);
         }
         else
         {
            myDocuments.insertElementAt(doc, myDocuments.size());
         }
      }
   }

   void print(OutputStreamWriter outP, int level)
   {
     if(outP==null)
      {
         System.err.println("KeyWord:print\n" + HelpMessages.BAD_OUTPUT_STREAM); //$NON-NLS-1$
         return;
      }
      KeyWordIndexHelper.defObj.printStart(outP,level+1);
      if(level==0)
      {
        MiscStatic.print(outP, "<A NAME=\"" + keyWord+ "\"> </A>"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      MiscStatic.printSpace(outP, level*4);
      if(level!=0)
      {
        MiscStatic.printSpace(outP,2);
      }
      if(myDocuments!=null)
      {
        if(!KeyWordIndexHelper.defObj.getShowDocumentTitle() && !KeyWordIndexHelper.defObj.getMulitDocumentKeyword())
        {
          Document tmpD = (Document)  myDocuments.elementAt(0);
          tmpD.setKeyWord(this);
          MiscStatic.print(outP, "<A HREF=\"" + tmpD.getUrl() + "\" TARGET=\"" //$NON-NLS-1$ //$NON-NLS-2$
          + KeyWordIndexHelper.defObj.getTarget() +"\">" + keyWord + "</A>"); //$NON-NLS-1$ //$NON-NLS-2$

        }
        else if(!KeyWordIndexHelper.defObj.getMulitDocumentKeyword())
        {
           MiscStatic.print(outP, keyWord);
           Document tmpD = (Document)  myDocuments.elementAt(0);
           tmpD.setKeyWord(this);
           tmpD.print(outP);
        }
        else
        {
         MiscStatic.print(outP, keyWord);
         MiscStatic.printSpace(outP, 1);

         for(int i=0;i<myDocuments.size();i++)
         {
            Document tmpD = (Document)  myDocuments.elementAt(i);
             tmpD.setKeyWord(this);
            tmpD.print(outP);
            if(i!=myDocuments.size()-1)
            {
              MiscStatic.print(outP, ", "); //$NON-NLS-1$
            }
            MiscStatic.print(outP, "\n"); //$NON-NLS-1$
         }
        }
      }
      else
      {
         MiscStatic.print(outP, keyWord);
      }
      if(mySpecKeyWord!=null)
      {
        mySpecKeyWord.print(outP);
      }

      if(KeyWordIndexHelper.defObj.getStyleSheet(Def.DefaultStyle)==null)
      {
        MiscStatic.print(outP, "<BR>"); //$NON-NLS-1$
      }
      KeyWordIndexHelper.defObj.printEnd(outP,level+1);
      if(myKeyWords!=null)
      {

         int tmpL = level+1;
         for(int i=0;i<myKeyWords.size();i++)
         {
            KeyWord tmpK = (KeyWord)  myKeyWords.elementAt(i);
            tmpK.print(outP, tmpL);
         }
      }

   }

   String getAnchor()
   {
	   if ( this.def != null)
      {
         return this.def.getAnchor();
      }
	   
      if(parentM!=null)
      {
         return parentM.getAnchor();
      }
       
      return ""; //$NON-NLS-1$
   }
   

}