package tests;

import static org.junit.Assert.*;

import org.junit.*;

import locations.*;

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
	
	/* Faire les tests des codes */
}
