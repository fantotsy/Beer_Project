package ua.fantotsy.comparator;

import java.util.Comparator;

import ua.fantotsy.jaxb.Item;

public class SortedByKcal implements Comparator<Item> {
	@Override
	public int compare(Item item1, Item item2) {
		int kcal1 = item1.getChars().getKcal().intValue();
		int kcal2 = item2.getChars().getKcal().intValue();
		if (kcal1 < kcal2) {
			return -1;
		} else if (kcal1 > kcal2) {
			return 1;
		} else {
			return 0;
		}
	}
}