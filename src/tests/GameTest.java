package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import characters.*;
import characters.Character;
import game.Game;
import items.*;

public class GameTest {
	Game game;
	
	@Test
	public void testTrue() {
		assertTrue(true);
	}
	
	@Before
	public void init() {
		game = new Game();
		Character.setHero(game.hero);
	}
	
	/*@Test
	public void talkNoReply() {
		Diogene dio = Diogene.getDiogene();
		
		game.talk(dio.toString());
		assertTrue(dio.hasFinishedToTalk());
	}
	
	@Test
	public void talkReply() {
		Healer heal = new Healer();
		game.talk(heal.toString());
		System.out.println(heal.hasFinishedToTalk());
		assertTrue(heal.hasFinishedToTalk());
	}*/
	
	
	@Test
	public void attackNoSword() {
		System.out.println("ATTACK WITHOUT SWORD");
		Crab crab = new Crab();
		game.attack(crab.toString());
	}
	
	@Test
	public void attackSword() {
		System.out.println("ATTACK WITH SWORD");
		Crab crab = new Crab();
		crab.fullyHeals();
		game.hero.heal(game.hero.getMaxHp());
		game.hero.changeEquipedWeapon(new Sword());
		game.attack(crab.toString());
	}
}
