package ua.fantotsy.comparator;

import java.util.Comparator;

import ua.fantotsy.jaxb.Item;

public class SortedByFiltration implements Comparator<Item> {
	@Override
	public int compare(Item item1, Item item2) {
		Boolean isFiltered1 = item1.getChars().isFiltered();
		Boolean isFiltered2 = item2.getChars().isFiltered();
		return isFiltered1.compareTo(isFiltered2);
	}
}