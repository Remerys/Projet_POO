package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hero.Hero;
import items.*;
import locations.Location;

public class HeroTest {
	
	private static Location loc = new Location();
	
	@Test
	public void testtoString() {
		Hero hero = Hero.createHero("Fabio", loc);
		assertEquals("Fabio :\n hp 10\n lvl 1 \n", hero.toString());
		Hero.destroyHero();
	}
	
	@Test 
	public void levelUp1() {
		Hero hero = Hero.createHero("Fabio", loc);
		hero.addXp(10);
		assertEquals(2, hero.getLevel());
		Hero.destroyHero();
	}
	
	@Test 
	public void levelUp2() {
		Hero hero = Hero.createHero("Fabio", loc);
		hero.addXp(150);
		assertEquals(5, hero.getLevel());
		Hero.destroyHero();
	}
	
	@Test
	public void printInventory1() {
		Hero hero = Hero.createHero("Fabio", loc);
		hero.printInventory();
		Hero.destroyHero();
	}
	
	@Test
	public void printInventory2() throws Exception {
		Hero hero = Hero.createHero("Fabio", loc);
		Bow bow = new Bow();
		Bow bow2 = new Bow();
		Sword s = new Sword();
		HealPotion p = new HealPotion();
		hero.addItem(bow);
		hero.addItem(s);
		hero.addItem(bow2);
		hero.addItem(p);
		hero.printInventory();
		Hero.destroyHero();
	}
}
