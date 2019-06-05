<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output encoding="UTF-8" indent="yes" method="html">
    <xsl:template match="/">
      <html>
        <head>
          <title>Cours HEIG-VD SER</title>
          <script src="js/jquery-3.4.1.min.js"/>
          <script src="js/bootstrap.min.js"/>
        </head>
      </html>
      <body>
        <xsl:for-each select="countries/element[./languages/element/name = 'French']">
          <xsl:text disable-output-escaping="no">test &lt;!-- Button trigger modal --/&gt; et &lt; </xsl:text>
        </xsl:for-each>
      </body>
    </xsl:template>
  </xsl:output>
</xsl:stylesheet>
