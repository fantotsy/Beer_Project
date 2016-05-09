package ua.fantotsy.comparator;

import java.util.Comparator;

import ua.fantotsy.jaxb.Item;

public class SortedByVolume implements Comparator<Item> {
	@Override
	public int compare(Item item1, Item item2) {
		String volume1 = item1.getChars().getCastingMethod().getVolume();
		String volume2 = item2.getChars().getCastingMethod().getVolume();
		return volume1.compareTo(volume2);
	}
}