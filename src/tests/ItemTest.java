package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import entities.Hero;
import items.*;

public class ItemTest {
    private Hero testHero;

    @Before
    public void setUp() {
        // Initialisation du héros pour les tests
        testHero = Hero.createHero("Test", null);
    }

    @Test
    public void testBow() {
        Bow bow = new Bow();
        assertEquals(5, bow.getWeight());
        assertEquals(5, bow.getDamage());
        assertEquals(10, bow.getRange());
    }

    @Test
    public void testHealPotion() {
        HealthPotion healPotion = new HealthPotion();
        assertEquals(1, healPotion.getWeight()); // Potion a le poids correct
        testHero.setHp(2);
        healPotion.setHero(testHero);
        healPotion.use();
        assertEquals(7, testHero.getHp()); // Vérifiez si l'utilisation de la potion guérit le héros correctement
    }

    @Test
    public void testMagicWand() {
        MagicWand magicWand = new MagicWand();
        assertEquals(5, magicWand.getWeight());
        assertEquals(10, magicWand.getDamage());
        assertEquals(10, magicWand.getRange());
    }

    @Test
    public void testSword() {
        Sword sword = new Sword();
        assertEquals(10, sword.getWeight());
        assertEquals(10, sword.getDamage());
        assertEquals(10, sword.getRange());
    }

    @Test
    public void testPotionWeight() {
        Potion potion = new HealthPotion();
        assertEquals(1, potion.getWeight());
    }

    @Test
    public void testWeaponWeight() {
        Weapon weapon = new Bow();
        assertEquals(5, weapon.getWeight());
    }

    @Test
    public void testWeaponDamage() {
        Weapon weapon = new Sword();
        assertEquals(10, weapon.getDamage());
    }

    @Test
    public void testWeaponRange() {
        Weapon weapon = new MagicWand();
        assertEquals(10, weapon.getRange());
    }
}

