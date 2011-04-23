<?xml version='1.0' encoding="UTF-8"?>
<xsl:stylesheet 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:plm="http://www.agile.com/api/plm" 
	version="1.0">
	<xsl:output method="xml"
		omit-xml-declaration="no"
		encoding="UTF-16"/>		
	
	<xsl:template match="node() | @*">	
				<xsl:copy>
					<xsl:apply-templates select="@* | node()"/>	
				</xsl:copy>
	</xsl:template>
	
	<xsl:template match="plm:header">
	<plm:header>
			<xsl:for-each select="*">
				<xsl:element name = "plm:{local-name(.)}">
					<xsl:for-each select="@*">
						<xsl:attribute name="{local-name(.)}"><xsl:value-of select="."/></xsl:attribute>
					</xsl:for-each>
				</xsl:element>
			</xsl:for-each>
			
			<xsl:variable name="cond1"><xsl:value-of select="count(plm:param[@name='createdForProject' and @value='project1'])"/></xsl:variable>
			<xsl:variable name="cond2"><xsl:value-of select="count(plm:param[@name='createdByTool' and @value='zws'])"/></xsl:variable>
			<xsl:variable name="cond3"><xsl:value-of select="count(plm:param[@name='createdByToolVersion' and @value='1'])"/></xsl:variable>
			<xsl:if test="$cond1>0 and $cond2>0 and $cond3>0">
				<xsl:element name = "plm:param">
						<xsl:attribute name="name"><xsl:value-of select="'file-folder'"/></xsl:attribute>
						<xsl:attribute name="value"><xsl:value-of select="'PLMAPI_MAPPING'"/></xsl:attribute>
				</xsl:element>
				<xsl:element name = "plm:param">
					<xsl:attribute name="name"><xsl:value-of select="'file-name'"/></xsl:attribute>
					<xsl:attribute name="value"><xsl:value-of select="'java:zws1.xsl'"/></xsl:attribute>
				</xsl:element>
			</xsl:if>
	</plm:header>
	</xsl:template>

</xsl:stylesheet>