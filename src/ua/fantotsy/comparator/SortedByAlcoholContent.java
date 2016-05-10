/* Project 3. Beer
 *
 * class SortedByAlcoholContent implements Comparator<Item>
 *
 * fantotsy ©
 */

package ua.fantotsy.comparator;

import java.util.Comparator;

import ua.fantotsy.jaxb.Item;

public class SortedByAlcoholContent implements Comparator<Item> {

	@Override
	public int compare(Item item1, Item item2) {
		if (item1.getAl().getAlcoholContent() == null && item2.getAl().getAlcoholContent() == null) {
			return 0;
		}
		if (item1.getAl().getAlcoholContent() == null) {
			return -1;
		}
		if (item2.getAl().getAlcoholContent() == null) {
			return 1;
		}
		String alcoholContent1 = item1.getAl().getAlcoholContent();
		String alcoholContent2 = item2.getAl().getAlcoholContent();
		return alcoholContent1.compareTo(alcoholContent2);
	}
}