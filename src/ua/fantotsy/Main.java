package ua.fantotsy;

import ua.fantotsy.comparator.*;
import ua.fantotsy.jaxb.Beer;

public class Main {

	public static void main(String[] args) {
		XMLValidator validator = new XMLValidator();
		validator.validate("src/BeerItems.xml", "src/Elements.xsd");

		Beer beerItems = Parser.SAXParsing("src/BeerItems.xml");
		Parser.printData(beerItems, "SAX Parser");

		System.out.println("\n\n");

		beerItems = Parser.DOMParsing("src/BeerItems.xml");
		Parser.printData(beerItems, "DOM Parser");

		System.out.println("\n\n");

		beerItems = Parser.StAXParsing("src/BeerItems.xml");
		Parser.printData(beerItems, "StAX Parser");
		
		System.out.println("\n\n");
		
		HTMLBuilder builder = new HTMLBuilder();
		builder.buildHTML("src/BeerItems.xsl", "src/BeerItems.xml", "src/BeerItems.html");
	}

	public static Beer sortByParameter(Beer beerItems, String parameter) {
		switch (parameter) {
		case "AlcoholContent":
			beerItems.getItem().sort(new SortedByAlcoholContent());
			break;
		case "Filtration":
			beerItems.getItem().sort(new SortedByFiltration());
			break;
		case "Id":
			beerItems.getItem().sort(new SortedById());
			break;
		case "Kcal":
			beerItems.getItem().sort(new SortedByKcal());
			break;
		case "Manufacturer":
			beerItems.getItem().sort(new SortedByManufacturer());
			break;
		case "Name":
			beerItems.getItem().sort(new SortedByName());
			break;
		case "Packing":
			beerItems.getItem().sort(new SortedByPacking());
			break;
		case "Transparency":
			beerItems.getItem().sort(new SortedByTransparency());
			break;
		case "Type":
			beerItems.getItem().sort(new SortedByType());
			break;
		case "Volume":
			beerItems.getItem().sort(new SortedByVolume());
			break;
		}
		return beerItems;
	}
}