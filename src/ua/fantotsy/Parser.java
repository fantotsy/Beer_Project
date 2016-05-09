package ua.fantotsy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ua.fantotsy.jaxb.AlType;
import ua.fantotsy.jaxb.Beer;
import ua.fantotsy.jaxb.BeerType;
import ua.fantotsy.jaxb.CastingMethod;
import ua.fantotsy.jaxb.Chars;
import ua.fantotsy.jaxb.Ingredients;
import ua.fantotsy.jaxb.Item;
import ua.fantotsy.jaxb.MaterialType;

public class Parser {
	public static Beer SAXParsing(String filePath) {
		SAXParserFactory parserF = SAXParserFactory.newInstance();
		SAXHandler handler = new SAXHandler();
		try {
			SAXParser saxParser = parserF.newSAXParser();
			saxParser.parse(filePath, handler);
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return handler.getResult();
	}

	public static Beer DOMParsing(String filePath) {
		Beer beerItems = new Beer();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			Document doc = documentBuilder.parse(new File(filePath));

			NodeList items = doc.getElementsByTagName("el:item");

			for (int i = 0; i < items.getLength(); i++) {
				Item beerItem = new Item();
				Element item = (Element) items.item(i);

				beerItem.setId(item.getAttribute("id"));
				beerItem.setName(item.getElementsByTagName("el:name").item(0).getFirstChild().getNodeValue());
				beerItem.setType(BeerType
						.fromValue(item.getElementsByTagName("el:type").item(0).getFirstChild().getNodeValue()));

				Element beerAl = (Element) item.getElementsByTagName("el:al").item(0);
				if (beerAl != null) {
					AlType al = new AlType();

					al.setAlcoholContent(beerAl.getAttribute("alcoholContent"));
					beerItem.setAl(al);
				}
				beerItem.setManufacturer(
						item.getElementsByTagName("el:manufacturer").item(0).getFirstChild().getNodeValue());

				Ingredients beerIngredients = new Ingredients();
				NodeList ingredients = item.getElementsByTagName("el:ingredient");
				for (int j = 0; j < ingredients.getLength(); j++) {
					beerIngredients.getIngredient().add(ingredients.item(j).getFirstChild().getNodeValue());
				}
				beerItem.setIngredients(beerIngredients);

				Chars beerChars = new Chars();
				NodeList chars = item.getElementsByTagName("el:chars");
				Element ch = (Element) chars.item(0);
				beerChars.setTransparency(
						ch.getElementsByTagName("el:transparency").item(0).getFirstChild().getNodeValue());
				beerChars.setFiltered(Boolean
						.parseBoolean(ch.getElementsByTagName("el:filtered").item(0).getFirstChild().getNodeValue()));
				beerChars.setKcal(
						new BigInteger(ch.getElementsByTagName("el:kcal").item(0).getFirstChild().getNodeValue()));

				CastingMethod beerCastingMethod = new CastingMethod();
				NodeList castingMethod = item.getElementsByTagName("el:castingMethod");
				Element cMethod = (Element) castingMethod.item(0);
				beerCastingMethod
						.setVolume(cMethod.getElementsByTagName("el:volume").item(0).getFirstChild().getNodeValue());
				beerCastingMethod.setMaterial(MaterialType
						.fromValue(cMethod.getElementsByTagName("el:material").item(0).getFirstChild().getNodeValue()));

				beerChars.setCastingMethod(beerCastingMethod);
				beerItem.setChars(beerChars);

				beerItems.getItem().add(beerItem);

			}

		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return beerItems;
	}

	public static Beer StAXParsing(String filePath) {
		Beer beerItems = new Beer();
		try {
			Item beerItem = null;
			Ingredients beerIngredients = null;
			Chars beerChars = null;
			CastingMethod beerCastingMethod = null;
			String tagContent = null;
			FileInputStream input = new FileInputStream(filePath);
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader reader = factory.createXMLStreamReader(input);

			while (reader.hasNext()) {
				int event = reader.next();
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if ((reader.getLocalName()).equals("item")) {
						beerItem = new Item();
						beerItem.setId(reader.getAttributeValue(0));
					}
					if ((reader.getLocalName()).equals("al")) {
						AlType al = new AlType();
						al.setAlcoholContent(reader.getAttributeValue(0));
						beerItem.setAl(al);
					}
					if ((reader.getLocalName()).equals("ingredients")) {
						beerIngredients = new Ingredients();
					}
					if ((reader.getLocalName()).equals("chars")) {
						beerChars = new Chars();
					}
					if ((reader.getLocalName()).equals("castingMethod")) {
						beerCastingMethod = new CastingMethod();
					}
					break;

				case XMLStreamConstants.CHARACTERS:
					tagContent = reader.getText().trim();
					break;

				case XMLStreamConstants.END_ELEMENT:
					switch (reader.getLocalName()) {
					case "item":
						beerItems.getItem().add(beerItem);
						break;

					case "name":
						beerItem.setName(tagContent);
						break;

					case "type":
						beerItem.setType(BeerType.fromValue(tagContent));
						break;

					case "manufacturer":
						beerItem.setManufacturer(tagContent);
						break;

					case "ingredient":
						beerIngredients.getIngredient().add(tagContent);
						break;

					case "ingredients":
						beerItem.setIngredients(beerIngredients);
						break;

					case "transparency":
						beerChars.setTransparency(tagContent);
						break;

					case "filtered":
						beerChars.setFiltered(Boolean.parseBoolean(tagContent));
						break;

					case "kcal":
						beerChars.setKcal(new BigInteger(tagContent));
						break;

					case "volume":
						beerCastingMethod.setVolume(tagContent);
						break;

					case "material":
						beerCastingMethod.setMaterial(MaterialType.fromValue(tagContent));
						break;

					case "castingMethod":
						beerChars.setCastingMethod(beerCastingMethod);
						break;

					case "chars":
						beerItem.setChars(beerChars);
						break;
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beerItems;
	}

	public static void printData(Beer items, String parsingMethod) {
		System.out.println("================" + parsingMethod + "================");
		for (Item item : items.getItem()) {
			System.out.println("-------------" + item.getName() + "(id: " + item.getId() + ")-------------");
			System.out.println("Type: " + item.getType() + ";");
			if (item.getAl() != null) {
				System.out.println("Alcoholic (" + item.getAl().getAlcoholContent() + ");");
			} else {
				System.out.println("Non-alcoholic;");
			}
			System.out.println("Manufacturer: " + item.getManufacturer() + ";");
			System.out.print("Ingredients: ");
			for (String ingredient : item.getIngredients().getIngredient()) {
				if (ingredient != item.getIngredients().getIngredient()
						.get(item.getIngredients().getIngredient().size() - 1)) {
					System.out.print(ingredient + ", ");
				} else {
					System.out.println(ingredient + ";");
				}
			}
			System.out.println("Chars:");
			System.out.println("\tTransparency: " + item.getChars().getTransparency() + ";");
			if (item.getChars().isFiltered()) {
				System.out.println("\tFiltered;");
			} else {
				System.out.println("\tNon-filtered;");
			}
			System.out.println("\tKcal: " + item.getChars().getKcal() + ";");
			System.out.println("\tCastingMethod:");
			System.out.println("\t\tVolume: " + item.getChars().getCastingMethod().getVolume() + ";");
			System.out.println("\t\tMaterial: " + item.getChars().getCastingMethod().getMaterial() + ";");
			System.out.println("---------------------------------------------");
		}
	}
}