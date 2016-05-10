/* Project 3. Beer
 *
 * class SortedByType implements Comparator<Item>
 *
 * fantotsy ©
 */

package ua.fantotsy.comparator;

import java.util.Comparator;

import ua.fantotsy.jaxb.Item;

public class SortedByType implements Comparator<Item> {

	@Override
	public int compare(Item item1, Item item2) {
		String type1 = item1.getType().toString();
		String type2 = item2.getType().toString();
		return type1.compareTo(type2);
	}
}