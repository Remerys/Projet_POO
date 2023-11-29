package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import characters.*;
//import characters.Character;
import game.Game;
import items.Bow;
import items.Flute;
//import items.*;
import items.HealthPotion;
import items.Item;
import items.Potion;
import items.Sword;
import locations.Location;

public class GameTest {
	Game game;

    /**
     * Initialise des choses importantes pour les tests et avant chaque test
	 * @throws Exception
     */
	@Before
	public void init() throws Exception {
		Hero.destroyHero();
		this.game = new Game();
		this.game.hero.getLocation().addCharacter(Diogene.getDiogene());
		this.game.hero.getLocation().addCharacter(new Crab());
		this.game.hero.getLocation().addCharacter(new Healer());
		this.game.hero.getLocation().addCharacter(new RabbitOfCaerbannog());
		this.game.hero.getLocation().addItem(new Bow());
		this.game.hero.addItem(new Sword());
		this.game.goTo("Island #1");
	}

	/**
	 * Soigne quand plus de potions
	 */
	@Test
    public void testHeal1() {
		this.game.hero.setHp(4);
        this.game.heal();
        assertTrue(this.game.hero.getHp() == 4);
    }

	/**
	 * Soigne
	 * @throws Exception
	 */
	@Test
    public void testHeal2() throws Exception {
		this.game.hero.setHp(4);
		int heroHPBefore = this.game.hero.getHp();

		Potion healthPotion = new HealthPotion();
		healthPotion.setHero(this.game.hero);
		this.game.hero.addItem(healthPotion);

        this.game.heal();
        assertTrue(this.game.hero.getHp() == heroHPBefore + HealthPotion.HEAL);
    }

	/**
	 * Soigne quand full hp
	 * @throws Exception
	 */
	@Test
    public void testHeal3() throws Exception {
		this.game.hero.fullyHeals();

		HealthPotion healthPotion = new HealthPotion();
		healthPotion.setHero(this.game.hero);
		this.game.hero.addItem(healthPotion);

        this.game.heal();
        assertTrue(this.game.hero.getHp() == this.game.hero.getMaxHp());
    }

	/**
	 * Change de map sur une qui n'existe pas
	 */
	@Test
    public void testGoTo1() {
		Location locationBefore = this.game.hero.getLocation();

        this.game.goTo("Island #42");

        assertTrue(this.game.hero.getLocation() == locationBefore);
    }

	/**
	 * Change de map sur une qui existe
	 */
	@Test
    public void testGoTo2() {
		Location locationBefore = this.game.hero.getLocation();

        this.game.goTo("Island #2");

		assertTrue(this.game.hero.getLocation() != locationBefore);
        assertTrue(this.game.hero.getLocation().getName() == "Island #2");
    }

	/**
	 * Change de map sur une qui existe mais où le joueur n'a pas d'accès
	 */
	@Test
    public void testGoTo3() {
		Location locationBefore = this.game.hero.getLocation();

        this.game.goTo("Quest Island");

        assertTrue(this.game.hero.getLocation() == locationBefore);
    }

	/**
	 * Attaque un ennemie qui n'existe pas
	 */
	@Test
    public void testAttack1() {
		int heroHPBefore = this.game.hero.getHp();
		int heroXPBefore = this.game.hero.getXp();

        this.game.attack("NonExistentEnemy");

        assertTrue(this.game.hero.getHp() == heroHPBefore);
        assertTrue(this.game.hero.getXp() == heroXPBefore);
    }

	/**
	 * Attaque un ennemie et meurt
	 */
	@Test
    public void testAttack2() {
		int heroXPBefore = this.game.hero.getXp();

        this.game.attack("Crab");

        assertTrue(this.game.hero.getHp() == 0);
        assertTrue(this.game.hero.getXp() == heroXPBefore);
    }

	/**
	 * Attaque un ennemie et le tue
	 */
	@Test
    public void testAttack3() {
		int heroXPBefore = this.game.hero.getXp();
		int heroLVBefore = this.game.hero.getLevel();

		this.game.hero.changeEquipedWeapon(new Sword());

        this.game.attack("Crab");

        assertTrue(this.game.hero.getHp() != 0);
        assertTrue(this.game.hero.getXp() != heroXPBefore || this.game.hero.getLevel() != heroLVBefore);
    }

	/**
	 * Parle à un PNJ qui n'existe pas
	 */
	@Test
    public void testTalk1() {
		// Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Appeler la méthode talk avec le personnage inexistant
        game.talk("nonExistingCharacter");

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Ajouter une assertion pour vérifier le comportement attendu
        assertEquals("This character doesn't exist", actualOutput);

        // Rétablir la sortie standard
        System.setOut(System.out);
    }

	/**
	 * Parle à un PNJ qui existe (Diogene)
	 */
	@Test
    public void testTalk2() {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Appeler la méthode talk avec le personnage inexistant
        game.talk("Diogene");

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Ajouter une assertion pour vérifier le comportement attendu
        assertEquals("Player talks to Diogene\n" +
        		     "--------------------------------------------------------------------\n" +
					 "Get out of my sun!", actualOutput);

        // Rétablir la sortie standard
        System.setOut(System.out);
    }

	/**
	 * Parle à un PNJ qui existe en disant no
	 */
	// @Test
    // public void testTalk3() {
	// 	// Simule les entrées utilisateur
    //     ByteArrayInputStream input = new ByteArrayInputStream("no\n".getBytes());
    //     System.setIn(input);

    //     // Rediriger la sortie standard pour capturer la sortie de la méthode
    //     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outputStream));

    //     // Appeler la méthode talk avec le personnage existant
    //     game.talk("Healer");

    //     // Récupérer la sortie de la méthode
    //     String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

    //     // Ajouter une assertion pour vérifier le comportement attendu
    //     assertEquals("Oh ... ok", actualOutput);

    //     // Rétablir la sortie standard et les entrées utilisateur
    //     System.setOut(System.out);
    //     System.setIn(System.in);
    // }


	/**
	 * Parle à un PNJ qui existe en disant yes
	 */
	// @Test
    // public void testTalk4() {
    //     // Rediriger la sortie standard pour capturer la sortie de la méthode
    //     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outputStream));

	// 	// Simule les entrées utilisateur
    //     InputStream mockInput = new ByteArrayInputStream("yes\n".getBytes());
    //     System.setIn(mockInput);

    //     // Appeler la méthode talk avec le personnage existant
    //     game.talk("Healer");

    //     // Récupérer la sortie de la méthode
    //     String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

    //     // Ajouter une assertion pour vérifier le comportement attendu
    //     assertEquals("I'm healing you", actualOutput);

    //     // Rétablir la sortie standard et les entrées utilisateur
    //     System.setOut(System.out);
    //     System.setIn(System.in);
    // }

	/**
	 * Parle à un PNJ qui existe en disant n'importe quoi
	 */
	// @Test
    // public void testTalk5() {
    //     // Rediriger la sortie standard pour capturer la sortie de la méthode
    //     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outputStream));

	// 	// Simule les entrées utilisateur
    //     InputStream mockInput = new ByteArrayInputStream("azdzadazgrh\n".getBytes());
    //     System.setIn(mockInput);

    //     // Appeler la méthode talk avec le personnage existant
    //     game.talk("Healer");

    //     // Récupérer la sortie de la méthode
    //     String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

    //     // Ajouter une assertion pour vérifier le comportement attendu
    //     assertEquals("What are you talking about ?", actualOutput);

    //     // Rétablir la sortie standard et les entrées utilisateur
    //     System.setOut(System.out);
    //     System.setIn(System.in);
    // }

    /**
     * Affiche les statistiques du héros
     */
    @Test
    public void testStats() {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the stats method
        game.stats();

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Ajouter une assertion pour vérifier le comportement attendu
        assertEquals("STATISTICS :\n" +
        		     "--------------------------------------------------------------------\n" +
					 "Name  : Player\n" +
					 		"Level : 1\n" +
					 		"XP : 0 / 10\n" +
					 		"HP : 10 / 10\n" +
					 		"MP : 0 / 0\n" +
					 		"Inventory Capacity : 21 / 100\n" +
					 		"Location : Island #1\n" +
					 		"Equipped Weapon : None", actualOutput);

        // Reset System.out to prevent interference with subsequent tests
        System.setOut(System.out);
    }

    /**
     * Affiche les quêtes
     */
    @Test
    public void testQuests() {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the stats method
        game.quests();

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Ajouter une assertion pour vérifier le comportement attendu
        assertEquals("List of available quests :\n" +
        		     "--------------------------------------------------------------------\n" +
					 "Main Quest : Todo\n" +
					 		"Your goal is to ...\n" +
					 		"Steps : \n" +
					 		"\t- Kill a rabbit : Todo", actualOutput);

        // Reset System.out to prevent interference with subsequent tests
        System.setOut(System.out);
    }

	/**
	 * Ajoute de l'xp au héros
	 */
	@Test
    public void testAddXp() {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the addXp method
        int xpToAdd = 13;
        game.addXp(xpToAdd);

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Assert that the expected output is printed
        assertEquals("Gain d'xp : 13", actualOutput);

        // Assert that the hero's XP is updated correctly
        assertEquals(3, game.hero.getXp());

        // Reset System.out to prevent interference with subsequent tests
        System.setOut(System.out);
    }

	/**
	 * Utilise un objet non utilisable
	 * @throws Exception
	 */
	@Test
    public void testUse1() throws Exception {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Add an item to the hero's inventory
        Sword sword = new Sword();
        game.hero.addItem(sword);

        // Call the use method
        game.use("Sword");

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Assert that the expected output is printed
        assertEquals("Use of : Sword\n" +
					 "This item isn't usable.", actualOutput);

        // Reset System.out to prevent interference with subsequent tests
        System.setOut(System.out);
    }

	/**
	 * Utilise un objet utilisable
	 * @throws Exception
	 */
	@Test
    public void testUse2() throws Exception {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Add an item to the hero's inventory
        Flute flute = new Flute();
        game.hero.addItem(flute);

        // Call the use method
        game.use("Flute");

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Assert that the expected output is printed
        assertEquals("Use of : Flute\n" +
					 "tututu.. tutu.. tu.. tu.. tu.. tutututututututututututututu..", actualOutput);

        // Reset System.out to prevent interference with subsequent tests
        System.setOut(System.out);
    }

	/**
	 * Affiche les informations de la map actuelle sans items, ni mobs, ni npcs
	 */
	@Test
    public void testMap1() {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a Location instance or mock it (you may need to adapt this based on your Location class structure)
        Location location = new Location("Test Location", "This is a test location.");
		this.game.hero.getLocation().addExit(location, "Wow, a new island");

        // Set the hero's location (assuming you have a method to set the hero's location)
        game.goTo("Test Location");

        // Call the map method
        game.map();

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Assert that the expected output is printed
        assertEquals("Player go to Test Location\n" +
					 "Map : Test Location\n" +
        			 "A short description : This is a test location.\n\n" +
        			 "List of items in this map :\n" +
        				"\tNone\n" +
        			 "List of mobs in this map :\n" +
        				"\tNone\n" +
        			 "List of NPCs you can talk to in this map :\n" +
        				"\tNone", actualOutput);

        // Reset System.out to prevent interference with subsequent tests
        System.setOut(System.out);
    }

	/**
	 * Affiche les informations de la map actuelle avec des items, des mobs, des npcs
	 * @throws Exception
	 */
	@Test
    public void testMap2() throws Exception {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the map method
        game.map();

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Assert that the expected output is printed
        assertEquals("Map : Island #1\n" +
        			 "A short description : An unknown island located at one end of the archipelago. You can observe another island in the distance.\n\n" +
        			 "List of items in this map :\n" +
        				"\tBow\n" +
        			 "List of mobs in this map :\n" +
        				"\tCrab\n" +
        				"\tRabbitOfCaerbannog\n" +
        			 "List of NPCs you can talk to in this map :\n" +
        				"\tDiogene\n" +
						"\tHealer", actualOutput);

        // Reset System.out to prevent interference with subsequent tests
        System.setOut(System.out);
    }



	/**
	 * Prends un item de la map qui existe
	 * @throws Exception
	 */
	@Test
    public void testTake1() throws Exception {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

		Item bow = this.game.hero.getLocation().getItem("Bow");

        // Call the take method
        game.take("Bow");

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Assert that the expected output is printed
        assertEquals("Take : Bow\nItem taken successfully", actualOutput);

        // Assert that the item is added to the hero's inventory
        assertTrue(this.game.hero.hasItem(bow));

        // Assert that the item is removed from the location's items
        assertFalse(this.game.hero.getLocation().getItems().contains(bow));

        // Reset System.out to prevent interference with subsequent tests
        System.setOut(System.out);
    }

	/**
	 * Prends un item de la map qui n'existe pas
	 * @throws Exception
	 */
	@Test
    public void testTake2() throws Exception {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the take method
        game.take("nonExistingItem");

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Assert that the expected output is printed
        assertEquals("Take : nonExistingItem\nThis item doesn't exist", actualOutput);

        // Reset System.out to prevent interference with subsequent tests
        System.setOut(System.out);
    }

	/**
	 * Equipe une arme qui existe
	 */
	@Test
    public void testEquip1() {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

		Item sword = this.game.hero.getItem("sword");

        // Call the equip method
        game.equip("sword");

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Assert that the expected output is printed
        assertEquals("Weapon changed successfully", actualOutput);

        // Assert that the weapon is equipped by the hero
        assertEquals(sword, this.game.hero.getWeaponEquiped());

        // Reset System.out to prevent interference with subsequent tests
        System.setOut(System.out);
    }

	/**
	 * Equipe une arme qui n'existe pas
	 */
	@Test
    public void testEquip2() {
        // Rediriger la sortie standard pour capturer la sortie de la méthode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Call the equip method
        game.equip("nonExistingWeapon");

        // Récupérer la sortie de la méthode
        String actualOutput = outputStream.toString().trim().replaceAll("\r", "");

        // Assert that the expected output is printed
        assertEquals("You don't have this weapon", actualOutput);

        // Reset System.out to prevent interference with subsequent tests
        System.setOut(System.out);
    }
}
