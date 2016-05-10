<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:el="http://www.example.org/Elements">
	<xsl:template match="/">
		<html>
			<head>Beer Items</head>
			<body>
				<table border="2">
					<tr align="center">
						<th rowspan="3">Name</th>
						<th rowspan="3">ID</th>
						<th rowspan="3">Type</th>
						<th rowspan="3">Manufacturer</th>
						<th rowspan="3">Alcohol Content</th>
						<th rowspan="3">Ingredients</th>
						<th colspan="5">Characteristics</th>
					</tr>
					<tr>
						<th rowspan="2">Transparency</th>
						<th rowspan="2">Is Filtered</th>
						<th rowspan="2">kcal</th>
						<th colspan="2">Packing</th>
					</tr>
					<tr>
						<th>Volume</th>
						<th>Material</th>
					</tr>

					<xsl:for-each select="el:beer/el:item">

						<tr>
							<td>
								<xsl:value-of select="el:name" />
							</td>
							<td>
								<xsl:value-of select="@id" />
							</td>
							<td>
								<xsl:value-of select="el:type" />
							</td>
							<td>
								<xsl:value-of select="el:manufacturer" />
							</td>
							<td>
								<xsl:choose>
									<xsl:when test="el:al/@alcoholContent != ''">
										<xsl:value-of select="el:al/@alcoholContent" />
									</xsl:when>
									<xsl:otherwise>
										no alcohol
									</xsl:otherwise>
								</xsl:choose>
							</td>
							<td>
								<xsl:value-of select="el:ingredients" />
							</td>
							<td>
								<xsl:value-of select="el:chars/el:transparency" />
							</td>
							<td>
								<xsl:choose>
									<xsl:when test="el:chars/el:filtered = 'true'">
										yes
									</xsl:when>
									<xsl:otherwise>
										no
									</xsl:otherwise>
								</xsl:choose>
							</td>
							<td>
								<xsl:value-of select="el:chars/el:kcal" />
							</td>
							<td>
								<xsl:value-of select="el:chars/el:castingMethod/el:volume" />
							</td>
							<td>
								<xsl:value-of select="el:chars/el:castingMethod/el:material" />
							</td>
						</tr>

					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>