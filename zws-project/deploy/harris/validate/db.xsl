<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
   version="1.0">

  <xsl:template match="databases">
    <database>
    <xsl:apply-templates />
    </database>
  </xsl:template>

  <xsl:template match="oracle">
    <xsl:element name="{@name}" >
      <xsl:attribute name="url">
        <xsl:value-of select="@url" />
      </xsl:attribute>
      <xsl:attribute name="username">
        <xsl:value-of select="@username" />
      </xsl:attribute>
      <xsl:attribute name="password">
        <xsl:value-of select="@password" />
      </xsl:attribute> 
    </xsl:element>
  </xsl:template>
</xsl:stylesheet>