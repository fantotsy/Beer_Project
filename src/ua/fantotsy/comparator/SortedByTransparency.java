/* Project 3. Beer
 *
 * class SortedByTransparency implements Comparator<Item>
 *
 * fantotsy ©
 */

package ua.fantotsy.comparator;

import java.util.Comparator;

import ua.fantotsy.jaxb.Item;

public class SortedByTransparency implements Comparator<Item> {
	
	@Override
	public int compare(Item item1, Item item2) {
		String transparency1 = item1.getChars().getTransparency();
		String transparency2 = item2.getChars().getTransparency();
		return transparency1.compareTo(transparency2);
	}
}