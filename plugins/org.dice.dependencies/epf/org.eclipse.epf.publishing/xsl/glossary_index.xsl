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

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

<xsl:param name="title"/>
<xsl:param name="glossaryText"/>

<xsl:template match="Glossary">

<html>
			<xsl:attribute name="lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<xsl:attribute name="xml:lang"><xsl:value-of select="@lang"/></xsl:attribute>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="meta_tagged" content="true"/>
<title><xsl:value-of select="$glossaryText"/> - <xsl:value-of select="$title"/></title>
</head>
<frameset rows="25,*">
<frame name="glossary_nav" src="navig.htm" scrolling="auto" marginheight="2" marginwidth="2" title="Navigation Area"/>
<frame name="glossary_doc" src="../glossary.htm" marginheight="0" marginwidth="2" scrolling="auto" title="Content Area"/>
</frameset>
</html>

</xsl:template>

</xsl:stylesheet>
