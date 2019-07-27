package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Logic.OgreLevel;

public class TestOgreNotMovingLevel {
	private char[][] map = 	{ 
			{ 'X', 'X', 'X', 'X', 'X' }, 
			{ 'X', 'H', ' ', '0', 'X' }, 
			{ 'I', ' ', ' ', ' ', 'X' },
			{ 'I', 'k', ' ', ' ', 'X' }, 
			{ 'X', 'X', 'X', 'X', 'X' }};

	@Test
	public void testMoveHeroNextToOgre() {
		OgreLevel level2 = new OgreLevel(false, map);
		assertTrue(level2.getHero().positionIs(1, 1));
		level2.update('d');
		assertTrue(level2.getHero().positionIs(2, 1));
		assertTrue(level2.getHero().isNextTo(level2.getEnemies().get(0)));
		assertTrue(level2.getEnemies().get(0).isStunned());
	}

	@Test
	public void testMoveHeroIntoKey() {
		OgreLevel level2 = new OgreLevel(false, map);
		assertTrue(level2.getHero().positionIs(1, 1));
		level2.update('s');
		level2.update('s');
		assertTrue(level2.getHero().positionIs(1, 3));
		assertTrue(level2.getHero().samePosition(level2.getKey()));
		assertTrue(level2.getHero().hasKey());

	}

	@Test
	public void testMoveHeroIntoTheDoorClosed() {
		OgreLevel level2 = new OgreLevel(false, map);
		assertTrue(level2.getHero().positionIs(1, 1));
		level2.update('s');
		assertTrue(level2.getHero().positionIs(1, 2));
		level2.update('a');
		assertTrue(level2.getHero().positionIs(1, 2));

	}

	@Test
	public void testMoveHeroIntoTheDoorOpen() {
		OgreLevel level2 = new OgreLevel(false, map);
		assertTrue(level2.getHero().positionIs(1, 1));
		level2.update('s');
		level2.update('s');
		assertFalse(level2.getKey().getDoors().get(1).isOpen());
		level2.update('a');
		assertTrue(level2.getKey().getDoors().get(1).isOpen());

	}

	@Test
	public void testHeroBeatsLevel() {
		OgreLevel level2 = new OgreLevel(false, map);
		assertTrue(level2.getHero().positionIs(1, 1));
		level2.update('s');
		level2.update('s');
		level2.update('a');
		assertFalse(level2.isBeaten());
		level2.update('a');
		assertTrue(level2.isBeaten());

	}
	
	
	@Test
	public void testCompareTwoMaps() {
		OgreLevel level2 = new OgreLevel(false, map);
		assertTrue(level2.getHero().positionIs(1, 1));
		
		String map1s = level2.toString();
		
		level2.update('s');		
		String map2s = level2.toString();
		
		assertFalse(map1s.equals(map2s));
		
		level2.update('w');		
		String map3s = level2.toString();
		
		assertTrue(map1s.equals(map3s));
	}

}
