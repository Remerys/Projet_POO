package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import characters.*;
//import characters.Character;
import game.Game;
//import items.*;
import items.HealthPotion;
import items.Potion;
import items.Sword;
import locations.Location;
import quests.Quest;

public class GameTest {
	Game game;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

	@Before
	public void init() {
		this.game = new Game();
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












	@Test
	public void talkNoReply() {
		Diogene dio = Diogene.getDiogene();

		this.game.talk(dio.toString());
		assertTrue(dio.hasFinishedToTalk());
	}

	@Test
	public void talkReply() {
		Healer heal = new Healer();
		this.game.talk(heal.toString());
		// System.out.println(heal.hasFinishedTalking());
		assertTrue(heal.hasFinishedToTalk());
	}

	/*
	 * @Test
	 * public void attackNoSword() {
	 * System.out.println("ATTACK WITHOUT SWORD");
	 * Crab crab = new Crab();
	 * game.attack(crab.toString());
	 * }
	 * 
	 * @Test
	 * public void attackSword() {
	 * System.out.println("ATTACK WITH SWORD");
	 * Crab crab = new Crab();
	 * crab.fullyHeals();
	 * game.hero.heal(game.hero.getMaxHp());
	 * game.hero.changeEquipedWeapon(new Sword());
	 * game.attack(crab.toString());
	 * }
	 */
}
