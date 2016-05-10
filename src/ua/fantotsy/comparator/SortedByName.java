/* Project 3. Beer
 *
 * class SortedByName implements Comparator<Item>
 *
 * fantotsy ©
 */

package ua.fantotsy.comparator;

import java.util.Comparator;

import ua.fantotsy.jaxb.Item;

public class SortedByName implements Comparator<Item> {

	@Override
	public int compare(Item item1, Item item2) {
		String name1 = item1.getName();
		String name2 = item2.getName();
		return name1.compareTo(name2);
	}
}