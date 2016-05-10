package ua.fantotsy;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class HTMLBuilder {

	public void buildHTML(String xslFile, String xmlFile, String outputFile) {
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xslFile));
			transformer.transform(new StreamSource(xmlFile), new StreamResult(outputFile));
		} catch (TransformerException e) {
			System.out.println("Error!" + e.getMessage());
		}
		System.out.println("Ok!");
	}
}