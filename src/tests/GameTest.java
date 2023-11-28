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

public class GameTest {
	Game game;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

	@Before
	public void init() {
		game = new Game();
	}

	@Test
    public void testDisplayAvailableCommands() {
        Game.displayAvailableCommands();
        assertTrue(outContent.toString().contains("List of available commands :"));
    }

	@Test
	public void talkNoReply() {
		Diogene dio = Diogene.getDiogene();

		game.talk(dio.toString());
		assertTrue(dio.hasFinishedToTalk());
	}

	@Test
	public void talkReply() {
		Healer heal = new Healer();
		game.talk(heal.toString());
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
