package Test;

import static org.junit.Assert.*;

import org.junit.Test;


import Logic.*;
import Logic.BotBehaviours.*;

public class TestGame {

	@Test
	public void testMoveUserIntoFreeCell() {
		
		 Game game=new Game(1, 3, new Random());
		 
		 assertEquals(game.getPlayers().get(0).getX(),1);
		 assertEquals(game.getPlayers().get(0).getY(),1);
		 
		 assertEquals(game.getPlayers().get(0).getDirection(),Player.RIGHT);
		 game.update();
		 assertTrue(game.getPlayers().get(0).getX()==2);
		 assertTrue(game.getPlayers().get(0).getY()==1);
	}
	

	
	@Test
	public void testMoveUserIntoAWall() {
		Game game=new Game(1, 3, new Random());
		 
		 assertEquals(game.getPlayers().get(0).getX(),1);
		 assertEquals(game.getPlayers().get(0).getY(),1);
		 
		 game.getPlayers().get(0).setDirection(Player.UP);
		 
		 assertFalse(game.checkGameOver());
		 
		 game.update();
		 
		 assertTrue(game.checkGameOver());
	}
	
	@Test
	public void testMoveHeroIntoItsTail() {
		Game game=new Game(1, 1, new EnemyAvoidance());
		 
		 assertEquals(game.getPlayers().get(0).getX(),1);
		 assertEquals(game.getPlayers().get(0).getY(),1);
		 
		 game.update();
		 game.update();
		 game.update();
		 
		 game.getPlayers().get(0).setDirection(Player.DOWN);
		 game.update();
		 game.update();
		 game.getPlayers().get(0).setDirection(Player.LEFT);
		 game.update();
		 game.getPlayers().get(0).setDirection(Player.UP);
		 game.update();
		 assertFalse(game.checkGameOver());
		 game.update();
		 
		 assertTrue(game.checkGameOver());

		

	}
	
	@Test
	public void testPlayerWonGame() {
		Game game=new Game(1, 1, new Random());
		
		 assertFalse(game.checkGameOver());
		while(game.getPlayers().get(0).getX()!=Game.ARENA_WIDTH-1)
			if(game.update())
				break;
		if(!game.checkGameOver()){
			 game.getPlayers().get(0).setDirection(Player.DOWN);
			 game.update();
			 game.getPlayers().get(0).setDirection(Player.LEFT);
			
			while(game.getPlayers().get(0).getX()!=1)
				if(game.update())
					break;
			
		}
		
		assertTrue(game.checkGameOver());
		assertEquals(game.getPlayerwhoWon(),1);
			 

	}
}