/* Project 3. Beer
 *
 * class SortedById implements Comparator<Item>
 *
 * fantotsy ©
 */

package ua.fantotsy.comparator;

import java.util.Comparator;

import ua.fantotsy.jaxb.Item;

public class SortedById implements Comparator<Item> {

	@Override
	public int compare(Item item1, Item item2) {
		String id1 = item1.getId();
		String id2 = item2.getId();
		return id1.compareTo(id2);
	}
}