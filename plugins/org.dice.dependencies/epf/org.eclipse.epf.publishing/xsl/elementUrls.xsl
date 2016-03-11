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
<xsl:output omit-xml-declaration="yes"/>

<xsl:template match="/ElementUrls">
<xsl:for-each select="elementUrl">contentPage.processPage.elementUrls["<xsl:value-of select="@nodeId"/>"] = ["<xsl:value-of select="@text"/>","<xsl:value-of select="@url"/>"];
</xsl:for-each>		
</xsl:template>
</xsl:stylesheet>
