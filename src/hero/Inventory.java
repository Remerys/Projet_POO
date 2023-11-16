package hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import items.*;

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
	
	public void addItem(Item item) throws Exception{
		if (this.weight + item.getWeight() <= Inventory.MAX_WEIGHT ) {
			items.add(item);
			this.weight += item.getWeight();
			this.nbItems++;
		} else {
			throw new Exception("Cannot add another item");
		}
	}
	
	public void removeItem(Item item) throws Exception{
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
	
	public List<Item> getItems() {
		return this.items;
	}
	
	public Item getItem(int index) {
		return this.items.get(index);
	}
	
	public Item getFirstItem() {
		return getItem(0);
	}
	
	public int nbItems() {
		return this.nbItems;
	}
	
	private <Type extends Item> List<Type> getItemsOfType(Class<Type> itemType) {
        List<Type> itemsType = new ArrayList<>();
        for (Item item : this.items) {
            if (itemType.isInstance(item)) {
            	itemsType.add(itemType.cast(item)); //we cast with security
            }
        }
        return itemsType;
    }

	public List<Potion> getPotions() {
		return getItemsOfType(Potion.class);
	}
	
	public List<Weapon> getWeapons() {
		return getItemsOfType(Weapon.class);
	}
	
	/*------------------------------------------------------------------------------------
	 * 
	 * Conditions
	 * 
	 ------------------------------------------------------------------------------------*/
	
	public <Type extends Item> boolean hasTypeItem(Class<Type> itemType) {
		return this.countItemsOfType(itemType) > 0;
	}
	
	public boolean hasPotion() {
		return hasTypeItem(Potion.class);
	}
	
	public boolean hasWeapon() {
		return hasTypeItem(Weapon.class);
	}
	
	public boolean hasItem(Item item) {
		return this.items.contains(item);
	}
	
	/*------------------------------------------------------------------------------------
	 * 
	 * Prints
	 * 
	 ------------------------------------------------------------------------------------*/
	
	/*private <Type extends Item> void printItems(List<Type> items) {
        Iterator<Type> iterator = items.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }*/
	
	private List<Item> getOtherItems() {
        List<Item> otherItems = new ArrayList<>(this.items);
        otherItems.removeAll(getPotions());
        otherItems.removeAll(getWeapons());
        return otherItems;
    }
	
	private <Type extends Item> void printItemWithCount(Type item, int count) {
        if (count > 1) {
            System.out.print("(" + count + ")");
        }
        System.out.print(item);
    }
	
	private <Type extends Item> void printItemsWithCount(List<Type> items) {
		//we sort the items
		List<Type> sortedItems = new ArrayList<>(items);
        Collections.sort(sortedItems, (item1, item2) -> item1.getClass().getName().compareTo(item2.getClass().getName()));
        
        if (!items.isEmpty()) {
            Iterator<Type> iterator = sortedItems.iterator();
            Type currentItem = iterator.next();
            int count = 1;

            while (iterator.hasNext()) {
                Type nextItem = iterator.next();
                if (currentItem.getClass().equals(nextItem.getClass())) { //there is a same type of item
                    count++;
                } else { //we print and pass to the next item
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
	
	public void printInventory() {
		if (this.nbItems > 0) {
			System.out.println("Inventory : ");
	        
	        System.out.print("\tWeapons : ");
	        printItemsWithCount(this.getWeapons());
	        
	        System.out.print("\tPotions : ");
	        printItemsWithCount(this.getPotions());
	        
	        System.out.print("\tOthers : ");
	        printItemsWithCount(this.getOtherItems());
	        
	        System.out.println();
		} else {
			System.out.println("Nothing in the inventory.");
			System.out.println();
		}
        
    }
	
	/*------------------------------------------------------------------------------------
	 * 
	 * Utils
	 * 
	 ------------------------------------------------------------------------------------*/
	
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
