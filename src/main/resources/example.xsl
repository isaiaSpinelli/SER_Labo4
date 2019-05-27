<?xml version = "1.0" encoding = "UTF-8" ?>

<xsl:stylesheet
version = "1.0"
xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">

	<xsl:output
	method = "html"
	encoding = "UTF-8"
	indent = "yes"
	/>

	<xsl:template match="/">
		<html>
			<head>
				<title>Cours HEIG-VD SER</title>
			</head>
			<body>
				<xsl:for-each select="countries/element[region='Europe']">
					<p>Pays1 : <xsl:value-of select="./altSpellings/element[2]" /></p>
					<p>flag : <xsl:value-of select="./flag" /></p>
					</xsl:for-each>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>



