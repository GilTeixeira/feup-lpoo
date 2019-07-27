package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Logic.OgreLevel;

public class TestOgreMovingLevel {


	@Test
	public void testMoveHeroNextToOgre() {
		char[][] map = 	{ 
				{ 'X', 'X', 'X', 'X', 'X' }, 
				{ 'X', 'H', ' ', '0', 'X' }, 
				{ 'I', ' ', ' ', 'k', 'X' },
				{ 'I', ' ', ' ', ' ', 'X' }, 
				{ 'X', 'X', 'X', 'X', 'X' }};
		OgreLevel level2 = new OgreLevel(true, map);
		assertTrue(level2.getHero().positionIs(1, 1));
		level2.update('d');
		assertTrue(level2.getHero().positionIs(2, 1));
		assertTrue(level2.getHero().isNextTo(level2.getEnemies().get(0)));
		assertTrue(level2.getEnemies().get(0).isStunned());
	}
	
	@Test//(timeout=1000)
	public void testMoveOgreNextToHero() {
		char[][] map = 	{ 
				{ 'X', 'X', 'X', 'X', 'X' }, 
				{ 'X', 'H', ' ', ' ', 'X' },
				{ 'X', ' ', ' ', 'k', 'X' }, 
				{ 'I', ' ', ' ', ' ', 'X' }, 
				{ 'I', ' ', ' ', '0', 'X' },
				{ 'X', 'X', 'X', 'X', 'X' } };
		OgreLevel level2 = new OgreLevel(true, map);
		assertTrue(level2.getHero().positionIs(1, 1));
		
		do{
			if(level2.getHero().positionIs(1, 1))
				level2.update('d');
			else level2.update('a');			
		}
		while(!level2.getHero().isNextTo(level2.getEnemies().get(0)));
		
		assertTrue(level2.getHero().isNextTo(level2.getEnemies().get(0)));
	}

	@Test(timeout=1000)
	public void testHeroGetsHitByClub() {
		char[][] map = 	{ 
				{ 'X', 'X', 'X', 'X', 'X' }, 
				{ 'X', 'H', ' ', '0', 'X' }, 
				{ 'I', ' ', ' ', 'k', 'X' },
				{ 'I', '0', ' ', '0', 'X' }, 
				{ 'X', 'X', 'X', 'X', 'X' }};
		OgreLevel level2 = new OgreLevel(true, map);
		assertTrue(level2.getHero().positionIs(1, 1));
		
		assertTrue(level2.getHero().isAlive());
		
		do{
			if(level2.getHero().positionIs(1, 1))
				level2.update('d');
			else level2.update('a');			
		}
		while(level2.getHero().isAlive());
		
		assertFalse(level2.getHero().isAlive());
}

	@Test(timeout=1000)
	public void testKeyGetsHit() {
		char[][] map = { 
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, 
				{ 'X', 'H', 'X', ' ', ' ', '0', 'X' },
				{ 'X', ' ', 'X', ' ', ' ', ' ', 'X' }, 
				{ 'X', 'X', 'X', 'k', ' ', ' ', 'X' },
				{ 'I', ' ', ' ', ' ', ' ', ' ', 'X' }, 
				{ 'I', '0', ' ', ' ', ' ', '0', 'X' },
				{ 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };
		OgreLevel level2 = new OgreLevel(true, map);
		assertTrue(level2.getHero().positionIs(1, 1));
		assertTrue(level2.getHero().isAlive());
		assertFalse(level2.getKey().isHit());

		do {
			if (level2.getHero().positionIs(1, 1))
				level2.update('s');
			else
				level2.update('w');
		} while (!level2.getKey().isHit());
		assertTrue(level2.getKey().isHit());

	}
	
	@Test(timeout=1000)
	public void testOriginalOgreLevel() {
		
		OgreLevel level2 = new OgreLevel(1);
		assertTrue(level2.getHero().positionIs(1, 7));
		assertTrue(level2.getHero().isAlive());
		assertTrue(level2.getKey().positionIs(7, 1));
		assertTrue(level2.getEnemies().size()==1);

	}

	
}
