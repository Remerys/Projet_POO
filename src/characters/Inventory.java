package characters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import items.*;

/**
 * The inventory is a kind of bag, which can contain a certain number of items
 * and interact with
 * 
 * @author Lilian
 */
public class Inventory {
	private List<Item> items = new ArrayList<>();
	private static final int MAX_WEIGHT = 100;
	private static final int EMPTY = 0;
	private int weight;
	private int nbItems;

	public Inventory() {
		this.weight = EMPTY;
		this.nbItems = 0;
	}

	/**
	 * Add an item in the inventory
	 * 
	 * @param item (Item)
	 * @throws Exception : Cannot add another item
	 */
	public void addItem(Item item) throws Exception {
		if (this.weight + item.getWeight() <= Inventory.MAX_WEIGHT) {
			items.add(item);
			this.weight += item.getWeight();
			this.nbItems++;
		} else {
			throw new Exception("Cannot add another item");
		}
	}

	/**
	 * Remove an item in the inventory
	 * 
	 * @param item (Item)
	 * @throws Exception : Cannot remove an item not present in the inventory
	 */
	public void removeItem(Item item) throws Exception {
		if (this.items.contains(item)) {
			items.remove(item);
			this.weight -= item.getWeight();
			this.nbItems--;
		} else {
			throw new Exception("Cannot remove an item not present in the inventory");
		}
	}

	/*------------------------------------------------------------------------------------
	 * 
	 * Getters
	 * 
	 ------------------------------------------------------------------------------------*/

	/**
	 * Get all items
	 * 
	 * @return all items
	 */
	public List<Item> getItems() {
		return this.items;
	}

	/**
	 * Get an item in an specific index,
	 * 
	 * @param index (int)
	 * @return item
	 */
	public Item getItem(int index) {
		return this.items.get(index);
	}

	/**
	 * Get an item with a string
	 * 
	 * @param itemType
	 * @return
	 */
	public Item getItem(String itemType) {
		for (Item item : this.items) {
			if (item.toString().toUpperCase().equals(itemType.toUpperCase())) {
				return item;
			}
		}
		return null;
	}

	/**
	 * Get the first item in the inventory
	 * 
	 * @return first item
	 */
	public Item getFirstItem() {
		return getItem(0);
	}

	/**
	 * Get the number of items in the inventory
	 * 
	 * @return the numbers of items
	 */
	public int getNbItems() {
		return this.nbItems;
	}

	/**
	 * Return a list with a specific type of item
	 * 
	 * @param itemType (Type) : the specific class
	 * @return a list of this type of item
	 */
	private <Type extends Item> List<Type> getItemsOfType(Class<Type> itemType) {
		List<Type> itemsType = new ArrayList<>();
		for (Item item : this.items) {
			if (itemType.isInstance(item)) {
				itemsType.add(itemType.cast(item)); // we cast with security
			}
		}
		return itemsType;
	}

	/**
	 * Return a list with all the potions in the inventory
	 * 
	 * @return all potions
	 */
	public List<Potion> getPotions() {
		return getItemsOfType(Potion.class);
	}

	/**
	 * Return the first potion
	 * 
	 * @return one potion
	 */
	public Potion getPotion() {
		return this.getPotions().get(0);
	}

	/**
	 * Return a list with all the weapons in the inventory
	 * 
	 * @return all weapons
	 */
	public List<Weapon> getWeapons() {
		return getItemsOfType(Weapon.class);
	}

	public int getWeight() {
		return this.weight;
	}

	public int getMaxWeight() {
		return Inventory.MAX_WEIGHT;
	}

	/*------------------------------------------------------------------------------------
	 * 
	 * Conditions
	 * 
	 ------------------------------------------------------------------------------------*/

	/**
	 * Verify if there is a specific type of item in the inventory
	 * 
	 * @param itemType (Type) : the specific type
	 * @return if there is a specific type of item
	 */
	public <Type extends Item> boolean hasTypeItem(Class<Type> itemType) {
		return this.countItemsOfType(itemType) > 0;
	}

	/**
	 * Verify if there is a potion in the inventory
	 * 
	 * @return if there is a potion
	 */
	public boolean hasPotion() {
		return hasTypeItem(Potion.class);
	}

	/**
	 * Verify if there is a weapon in the inventory
	 * 
	 * @return if there is a weapon
	 */
	public boolean hasWeapon() {
		return hasTypeItem(Weapon.class);
	}

	/**
	 * Verify if there is a item in the inventory
	 * 
	 * @return if there is a item
	 */
	public boolean hasItem(Item item) {
		return this.items.contains(item);
	}

	/*------------------------------------------------------------------------------------
	 * 
	 * Prints
	 * 
	 ------------------------------------------------------------------------------------*/

	/*
	 * private <Type extends Item> void printItems(List<Type> items) {
	 * Iterator<Type> iterator = items.iterator();
	 * while (iterator.hasNext()) {
	 * System.out.print(iterator.next());
	 * if (iterator.hasNext()) {
	 * System.out.print(", ");
	 * }
	 * }
	 * System.out.println();
	 * }
	 */

	/**
	 * Get all non-specific items
	 * 
	 * @return a list of items
	 */
	private List<Item> getOtherItems() {
		List<Item> otherItems = new ArrayList<>(this.items);
		otherItems.removeAll(getPotions());
		otherItems.removeAll(getWeapons());
		return otherItems;
	}

	/**
	 * Print an item with the numbers of this items in front of this item
	 * 
	 * @param item  (Item)
	 * @param count (int)
	 */
	private <Type extends Item> void printItemWithCount(Type item, int count) {
		if (count > 1) {
			System.out.print("(" + count + ")");
		}
		System.out.print(item);
	}

	/**
	 * Print all items with the numbers of this items in front of this item
	 * 
	 * @param items (List<Item>)
	 */
	private <Type extends Item> void printItemsWithCount(List<Type> items) {
		// we sort the items
		List<Type> sortedItems = new ArrayList<>(items);
		Collections.sort(sortedItems,
				(item1, item2) -> item1.getClass().getName().compareTo(item2.getClass().getName()));

		if (!items.isEmpty()) {
			Iterator<Type> iterator = sortedItems.iterator();
			Type currentItem = iterator.next();
			int count = 1;

			while (iterator.hasNext()) {
				Type nextItem = iterator.next();
				if (currentItem.getClass().equals(nextItem.getClass())) { // there is a same type of item
					count++;
				} else { // we print and pass to the next item
					printItemWithCount(currentItem, count);
					System.out.print(", ");
					currentItem = nextItem;
					count = 1;
				}
			}

			// print the last item
			printItemWithCount(currentItem, count);

		}
		System.out.println();
	}

	/**
	 * Print the inventory
	 */
	public void printInventory() {
		if (this.nbItems > 0) {
			System.out.println("INVENTORY : ");

			System.out.print("\tWeapons : ");
			printItemsWithCount(this.getWeapons());

			System.out.print("\tPotions : ");
			printItemsWithCount(this.getPotions());

			System.out.print("\tOthers : ");
			printItemsWithCount(this.getOtherItems());
		} else {
			System.out.println("Nothing in the inventory.");
		}

	}

	/*------------------------------------------------------------------------------------
	 * 
	 * Utils
	 * 
	 ------------------------------------------------------------------------------------*/

	/**
	 * Count the number of items of an specific item
	 * 
	 * @param itemType (Type) : the specific item
	 * @return count the number of items
	 */
	private <Type extends Item> int countItemsOfType(Class<Type> itemType) {
		int count = 0;
		for (Item item : this.items) {
			if (itemType.isInstance(item)) {
				count++;
			}
		}
		return count;
	}
}
