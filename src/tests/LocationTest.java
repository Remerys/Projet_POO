package tests;

import static org.junit.Assert.*;

import org.junit.*;

import locations.*;

import entities.Entity;
import entities.Fighter;
import entities.Talker;
import entities.Crab;
import entities.Healer;

public class LocationTest {
	
	@Test
	public void testTrue() {
		assertTrue(true);;
	}
	
	@Test
	public void exit() throws Exception {
		Location loc = new Location("1", "");
		Exit exit = new Exit(loc, "");
		assertEquals(loc, exit.exit());
	}
	
	@Test
	public void exit2() throws Exception {
		Location loc = new Location("1", "");
		Location notDest = new Location("2", "");
		Exit exit = new Exit(loc, "");
		boolean notEqual = notDest.equals(exit.exit());
		assertFalse(notEqual);
	}
	
	
	@Test
	public void exitCode() throws Exception {
		String code = "1234";
		Location dest = new Location("1", "");
		ExitWithCode exit = new ExitWithCode(dest, code, "");
		exit.enterCode(code);
		exit.unlock();
		assertEquals(dest, exit.exit());
	}
	
	@Test
	public void exitCode2() throws Exception {
		String code = "1234";
		Location dest = new Location("1", "");
		ExitWithCode exit = new ExitWithCode(dest, code, "");
		exit.enterCode("4321");
		
		try {
			exit.unlock();
			assertEquals(dest, exit.exit());
			assertFalse(true);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void exitCode3() throws Exception {
		String code = "1234";
		Location dest = new Location("1", "");
		ExitWithCode exit = new ExitWithCode(dest, code, "");
		
		try {
			exit.unlock();
			assertEquals(dest, exit.exit());
			assertFalse(true);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void exitCode4() {
		String code = "1234";
		Location dest = new Location("1", "");
		ExitWithCode exit = new ExitWithCode(dest, code, "");
		try {
			assertEquals(dest, exit.exit());
			assertFalse(true);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void exitCode5() {
		String code = "1234";
		Location dest = new Location("1", "");
		ExitWithCode exit = new ExitWithCode(dest, code, "");
		exit.enterCode(code);
		try {
			assertEquals(dest, exit.exit());
			assertFalse(true);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void exitCode6() {
		String code = "1234";
		Location dest = new Location("1", "");
		ExitWithCode exit = new ExitWithCode(dest, code, "");
		exit.enterCode("234");
		try {
			assertEquals(dest, exit.exit());
			assertFalse(true);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void loc_exit() throws Exception {
		Location loc = new Location("1", "");
		Location dest = new Location("2", "");
		loc.addExit(dest, "");
		assertEquals(dest, loc.exitTo(dest.getName()));
	}
	
	@Test
	public void loc_exit2() {
		Location loc = new Location("1", "");
		Location dest = new Location("2", "");
		Location notDest = new Location("3", "");
		loc.addExit(dest, "");
		try {
			loc.exitTo(notDest.getName());
			assertFalse(true);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void loc_exit3() {
		Location loc = new Location("1", "");
		Location notDest = new Location("2", "");
		try {
			loc.exitTo(notDest.getName());
			assertFalse(true);
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void loc_exit4() throws Exception {
		Location loc = new Location("1", "");
		Location loc2 = new Location("2", "");
		Location currentLoc = loc;
		loc.addExit(loc2, "");
		loc2.addExit(loc, "");
		currentLoc = loc.exitTo(loc2.getName());
		assertEquals(loc2, currentLoc);
		currentLoc = loc2.exitTo(loc.getName());
		assertEquals(loc, currentLoc);
	}
	
	@Test
	public void addCharacterStructTest1Fighter() {
		Location loc = new Location("", "");
		Fighter crab = new Crab();
		loc.addCharacter(crab);

		assertEquals(loc.getFighters().get(0), crab);
	}

	@Test
	public void addCharacterStructTest2Talker() {
		Location loc = new Location("", "");
		Talker healer = new Healer();
		loc.addCharacter((Entity)healer);

		assertEquals(loc.getTalkers().get(0), healer);
	}

	@Test
	public void addCharacterStructTest3Error() {
		Location loc = new Location("", "");

		// Defining an anonymous class that inherits entity, so that it doesn't implements talker nor can fight
		Entity anonEntity = new Entity("", "") {}; 
		
		loc.addCharacter(anonEntity);

		assertEquals(loc.getFighters().size(), 0);
		assertEquals(loc.getTalkers().size(), 0);		
	}

	@Test
	public void unlockExitFromLocStructTest1Exception() {
		Location loc = new Location("", "");
		try {
			loc.unlock("noLoc");
			assertTrue("The exit must not exist", false);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "enterExitCode : the location doesn't exist !");
		}
	}

	@Test
	public void unlockExitFromLocStructTest2NoLock() {
		Location loc = new Location("", "");
		Location end = new Location("c", "");
		loc.addExit(end, "");
		try {
			loc.unlock("c");
			assertTrue("The exit has been unlocked although it should not have a lock", false);
		} catch (Exception e) {
			if (e.getMessage().equals(Location.ERROR_EXIT_HAS_NO_LOCK)) {
				assertTrue(true);
			} else {
				assertTrue("The exit has not been found", false);
		}
		}
	}

	@Test
	public void unlockExitFromLocStructTest3Success() throws Exception{
		Location loc = new Location("", "");
		Location end = new Location("c", "");
		loc.addExitWithCode(end, "code", "");
		loc.enterExitCode("c", "code"); // We consider here that unlock works 
		try {
			loc.unlock("c");
			assertTrue(true);
		} catch (Exception e) {
			if (e.getMessage().equals(Location.ERROR_EXIT_HAS_NO_LOCK)) {
				assertTrue("The exit has no lock", false);
			} else {
				assertTrue("The exit has not been found", false);
			}
		}
	}
}

