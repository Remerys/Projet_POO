package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import characters.*;

public class CharactersTest {
	
	@Test
	public void getAttacked1() {
		Ork ork = new Ork();
		ork.getAttacked(10000);
		assertEquals(0, ork.getHp());
		assertTrue(ork.isDead());
	}
	
	@Test
	public void getAttacked2() {
		Ork ork = new Ork();
		int hp = ork.getHp();
		ork.getAttacked(1);
		assertEquals(hp-1, ork.getHp());
		assertFalse(ork.isDead());
	}
	
	@Test
	public void talk() {
		Diogene dio = Diogene.getDiogene();
		assertEquals("Get out of my sun!", dio.talk(null));
	}
	
	@Test
	public void singletonDiogene() {
		Diogene dio = Diogene.getDiogene();
		Diogene dio2 = Diogene.getDiogene();
		assertEquals(dio, dio2);
	}

}
