<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Copyright (c) 2005, 2006 IBM Corporation and others.
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the Eclipse Public License v1.0
    which accompanies this distribution, and is available at
    http://www.eclipse.org/legal/epl-v10.html
    Contributors:
    IBM Corporation - initial implementation
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">

<xsl:output method="html" version="1.0" encoding="UTF-8" 
	doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" 
	doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" 
	indent="yes"/>
	
<xsl:template match="/PublishingOption">

<xsl:variable name="title"><xsl:value-of select="@title"/></xsl:variable>

<html>
			<xsl:attribute name="lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<xsl:attribute name="xml:lang"><xsl:value-of select="@lang"/></xsl:attribute>

<head>
<title><xsl:value-of select="$title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="meta_tagged" content="true"/>

<!-- include additional meta tags if any -->
<xsl:for-each select="metaTags/metaTag">
<meta>
<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
<xsl:attribute name="content"><xsl:value-of select="@content"/></xsl:attribute>
</meta>
</xsl:for-each>

<script language="JavaScript">
<![CDATA[
<!--
var tour = 0;

function getCookie (name) 
{
  var dcookie = document.cookie; 
  var cname = name + "=";
  var clen = dcookie.length;
  var cbegin = 0;
  while (cbegin < clen) 
  {
    var vbegin = cbegin + cname.length;
    if (dcookie.substring(cbegin, vbegin) == cname) 
    { 
      var vend = dcookie.indexOf (";", vbegin);
      if (vend == -1) 
      {
        vend = clen;
      }
      return unescape(dcookie.substring(vbegin, vend));
    }

    cbegin = dcookie.indexOf(" ", cbegin) + 1;
    if (cbegin == 0) 
    {
      break;
    }
  }
  return null;
 }

function fixUrl(url)
{
	if ( url == null )
	{
		return null;
	}

	var indx = url.indexOf("file:///");
	if ( indx >=0 )
	{
		url = url.substring(indx+8, url.length);
	}
	else
	{
		indx = url.indexOf("file://");
		if ( indx >=0 )
		{
			url = url.substring(indx+7, url.length);
		}
	}

	url = url.replace(/\\/g, "/");

   return unescape(url);
}

// -->
]]>
</script>
</head>
<script language="JavaScript">
var bannerheight = 21 + <xsl:value-of select="@bannerImageHeight"/>;
<xsl:choose>
	<xsl:when test="@useApplet='true'">
var toc_frame = "ory_toc";
var toc_url = "applet/myruptree.htm";
var default_page_url = "spscreen.htm";
	</xsl:when>
	<xsl:otherwise>
var toc_frame = "ory_toc_frame";
var toc_url = "noapplet/PublishedBookmarks.html";
var default_page_url = getCookie("rup_ory_doc");
if ( default_page_url == null )
{
	default_page_url = "about:blank";
}
	</xsl:otherwise>
</xsl:choose>
<![CDATA[
  if((navigator.appName == "Netscape" && parseInt(navigator.appVersion) >= 4 ) || 
      (navigator.appName == "Microsoft Internet Explorer" && parseInt(navigator.appVersion) >= 4 ))
  {
    document.write("  <frameset  frameborder=\"0\" framespacing=\"1\" border=\"1\" rows=\"" + bannerheight + ",*\">\n");
    document.write("    <frame src=\"topnav.htm\" noresize name=\"ory_button\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\">\n");
    document.writeln("<frameset cols=\"205,*\" frameborder=\"1\" framespacing=\"1\" border=\"1\" >\n");
    document.write("      <frame src=\"" + toc_url + "\" name=\"" + toc_frame + "\" frameborder=\"1\" scrolling=\"no\">\n");
    document.writeln("<frame name=\"ory_doc\" src=\"" + default_page_url + "\"  frameborder=\"0\" scrolling=\"yes\">\n");
    document.writeln("   </frameset>");
    document.writeln("   </frameset>");

  }
  else
  {
    parent.location = "applet/browser.htm";
  }
]]>

  </script>

<body>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td valign="top">
      <br/>
      <br/>
	</td>
    <td valign="top" width="24"></td>
    <td valign="top" width="1%">
      <p>&#160;</p>
    </td>
  </tr>
</table>


</body>

</html>


</xsl:template>
</xsl:stylesheet>
