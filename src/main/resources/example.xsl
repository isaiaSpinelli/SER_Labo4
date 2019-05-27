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
				<!-- Bootstrap stylesheet and javascript -->
				<script src="js/jquery-3.4.1.min.js" />
				<script src="js/bootstrap.min.js" />
				<link rel="stylesheet" href="css/bootstrap.min.css" />
			</head>
			<body>
				<xsl:for-each select="countries/element[region='Europe']">
					
					
					
					<!-- Button trigger modal -->
					<button style="height:30px;width:250px" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
						<p><xsl:value-of select="./altSpellings/element[2]" /></p>
						<!-- <p>flag : <xsl:value-of select="./flag" /></p>-->
					</button>

					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"">
					  <div class="modal-dialog" role="document">
						<div class="modal-content">
						  <div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel"><xsl:value-of select="./altSpellings/element[2]" /></h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							x
							</button>
			
						  </div>
						
				
				
				
				

							<p>
							 
							  <img height="150" width="210" style="float: left;">
								<xsl:attribute name="src">
									<xsl:value-of select="./flag"/>
								</xsl:attribute>
							</img>

				
							
						
						  
							Capital : <br/>
							Population : habitants <br/>
							Superficie : km2 <br/>
							Continent :  <br/>
							Sous-Continent : <br/>
							<br/><br/>
							Langues parlées
							
							</p> 
							
						 
						  <div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
						  </div>
						</div>
					  </div>
					</div>
					
					</xsl:for-each>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>



