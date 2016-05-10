/* Project 3. Beer
 *
 * class SortedByManufacturer implements Comparator<Item>
 *
 * fantotsy ©
 */

package ua.fantotsy.comparator;

import java.util.Comparator;

import ua.fantotsy.jaxb.Item;

public class SortedByManufacturer implements Comparator<Item> {

	@Override
	public int compare(Item item1, Item item2) {
		String manufacturer1 = item1.getManufacturer();
		String manufacturer2 = item2.getManufacturer();
		return manufacturer1.compareTo(manufacturer2);
	}
}