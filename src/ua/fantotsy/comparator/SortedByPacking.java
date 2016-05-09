package ua.fantotsy.comparator;

import java.util.Comparator;

import ua.fantotsy.jaxb.Item;

public class SortedByPacking implements Comparator<Item> {
	@Override
	public int compare(Item item1, Item item2) {
		String packing1 = item1.getChars().getCastingMethod().getMaterial().toString();
		String packing2 = item2.getChars().getCastingMethod().getMaterial().toString();
		return packing1.compareTo(packing2);
	}
}