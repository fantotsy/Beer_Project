package ua.fantotsy;

import java.math.BigInteger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.fantotsy.jaxb.AlType;
import ua.fantotsy.jaxb.Beer;
import ua.fantotsy.jaxb.BeerType;
import ua.fantotsy.jaxb.CastingMethod;
import ua.fantotsy.jaxb.Chars;
import ua.fantotsy.jaxb.Ingredients;
import ua.fantotsy.jaxb.Item;
import ua.fantotsy.jaxb.MaterialType;

public class SAXHandler extends DefaultHandler {
	private Beer beerItems = new Beer();
	private String currentElement = "";
	private Item beerItem;
	private Ingredients beerIngredients;
	private Chars beerChars;
	private CastingMethod beerCastingMethod;

	public Beer getResult() {
		return beerItems;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("start parsing...");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("end parsing.");
	}

	@Override
	public void startElement(String namespace, String localName, String qName, Attributes attr) {
		currentElement = qName;
		if (currentElement.equals("el:item")) {
			beerItem = new Item();
			beerItem.setId(attr.getValue(0));
		}
		if (currentElement.equals("el:al")) {
			AlType al = new AlType();
			al.setAlcoholContent(attr.getValue(0));
			beerItem.setAl(al);
		}
		if (currentElement.equals("el:ingredients")) {
			beerIngredients = new Ingredients();
		}
		if (currentElement.equals("el:chars")) {
			beerChars = new Chars();
		}
		if (currentElement.equals("el:castingMethod")) {
			beerCastingMethod = new CastingMethod();
		}
	}

	@Override
	public void endElement(String namespace, String localName, String qName) throws SAXException {
		currentElement = "";
		if (qName.equals("el:item")) {
			beerItems.getItem().add(beerItem);
		}
		if (qName.equals("el:ingredients")) {
			beerItem.setIngredients(beerIngredients);
		}
		if (qName.equals("el:chars")) {
			beerItem.setChars(beerChars);
		}
		if (qName.equals("el:castingMethod")) {
			beerChars.setCastingMethod(beerCastingMethod);
		}
	}

	@Override
	public void characters(char[] ch, int start, int end) {
		if (currentElement == "el:name") {
			beerItem.setName(new String(ch, start, end));
		}
		if (currentElement == "el:type") {
			beerItem.setType(BeerType.fromValue(new String(ch, start, end)));
		}
		if (currentElement == "el:manufacturer") {
			beerItem.setManufacturer(new String(ch, start, end));
		}
		if (currentElement == "el:ingredient") {
			beerIngredients.getIngredient().add(new String(ch, start, end));
		}
		if (currentElement == "el:transparency") {
			beerChars.setTransparency(new String(ch, start, end));
		}
		if (currentElement == "el:filtered") {
			beerChars.setFiltered(Boolean.parseBoolean(new String(ch, start, end)));
		}
		if (currentElement == "el:kcal") {
			beerChars.setKcal(new BigInteger(new String(ch, start, end)));
		}
		if (currentElement == "el:volume") {
			beerCastingMethod.setVolume(new String(ch, start, end));
		}
		if (currentElement == "el:material") {
			beerCastingMethod.setMaterial(MaterialType.fromValue(new String(ch, start, end)));
		}
	}

}