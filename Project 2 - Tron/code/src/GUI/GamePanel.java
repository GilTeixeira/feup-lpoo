package GUI;

import javax.swing.JPanel;
import javax.swing.Timer;

import Logic.Game;
import Logic.Map;
import Logic.Player;
import Logic.BotBehaviours.*;

import java.awt.Graphics;


import javax.imageio.ImageIO;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener {

	public static final int SINGLEPLAYER = 0;
	public static final int MULTIPLAYER = 1;

	private final int DELAY = 25 * 2;
	private Timer timer;

	Board board;

	BufferedImage[] PlayersH;
	BufferedImage[] PlayersV;

	private BufferedImage tail1;
	private BufferedImage tail2;
	private BufferedImage tail3;
	private BufferedImage tail4;

	private BufferedImage wall;

	public int gameType;

	private BotBehaviour[] botBehaviours = new BotBehaviour[4];

	public BufferedImage backGroundGame;

	private boolean drawGame = false;
	boolean gameOver = true;
	boolean showPanel = false;

	private int playerWhoWon;

	GamePanelStatus gamePanelStatus;

	Game game;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public GamePanel(Board board) throws IOException {
		setLayout(null);
		this.board = board;
		addKeyListener(new TAdapter());

		gamePanelStatus = new GamePanelStatus(this);

		botBehaviours[0] = new Random();
		botBehaviours[1] = new WallHugging();
		botBehaviours[2] = new EnemyAvoidance();
		botBehaviours[3] = new MostOpenDestination();

		loadImages();

		timer = new Timer(DELAY, this);
		timer.start();

	}

	public void startGameSingle() throws InterruptedException {
		game = new Game(1, board.optionsMenu.getNumBotsSelected(),
				botBehaviours[board.optionsMenu.getBotBehaviourSelected()]);
		
		drawGame = true;
		gameOver = false;
		showPanel = false;
		gameType = SINGLEPLAYER;

	}

	public void startGameMulti() throws InterruptedException {
		game = new Game(2, 0, botBehaviours[board.optionsMenu.getBotBehaviourSelected()]);
		drawGame = true;
		gameOver = false;
		showPanel = false;
		gameType = MULTIPLAYER;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backGroundGame, 0, 0, getWidth(), getHeight(), null);

		if (drawGame)
			displayGame(g);

		requestFocusInWindow();

	}

	private void loadImages() throws IOException {
		
		BufferedImage Player1H=ImageIO.read(Board.class.getResource("/Images/p1H.png"));
		BufferedImage Player2H=ImageIO.read(Board.class.getResource("/Images/p2H.png"));
		BufferedImage Player3H=ImageIO.read(Board.class.getResource("/Images/p3H.png"));
		BufferedImage Player4H=ImageIO.read(Board.class.getResource("/Images/p4H.png"));
		
		PlayersH = new BufferedImage[] { Player1H, Player2H, Player3H, Player4H };
		
		BufferedImage Player1V=ImageIO.read(Board.class.getResource("/Images/p1V.png"));
		BufferedImage Player2V=ImageIO.read(Board.class.getResource("/Images/p2V.png"));
		BufferedImage Player3V=ImageIO.read(Board.class.getResource("/Images/p3V.png"));
		BufferedImage Player4V=ImageIO.read(Board.class.getResource("/Images/p4V.png"));
		
		PlayersV = new BufferedImage[] { Player1V, Player2V, Player3V, Player4V };
		

		
		
		tail1 = ImageIO.read(Board.class.getResource("/Images/tail1.png"));		
		tail2 = ImageIO.read(Board.class.getResource("/Images/tail2.png"));		
		tail3 = ImageIO.read(Board.class.getResource("/Images/tail3.png"));		
		tail4 = ImageIO.read(Board.class.getResource("/Images/tail4.png"));		

		wall = ImageIO.read(Board.class.getResource("/Images/Wall.png"));
		backGroundGame = ImageIO.read(Board.class.getResource("/Images/backGame.png"));


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (!gameOver) {
			gameOver = game.update();
			showPanel = gameOver;
			setPlayerWhoWon(game.getPlayerwhoWon());
		}

		if (showPanel && gameOver) {
			if (game.getPlayerwhoWon() == -1)
				gamePanelStatus.lblStatus.setText("You Lose!");
			else if (game.getPlayerwhoWon() == 1)
				gamePanelStatus.lblStatus.setText("Player 1 Won!");
			else
				gamePanelStatus.lblStatus.setText("Player 2 Won!");

			add(gamePanelStatus);

		}

		repaint();
	}

	void displayGame(Graphics g) {

		displayTails(g);
		displayBots(g);
		displayPlayers(g);
	}

	
	void displayTails(Graphics g) {
		Map map = game.getMap();

		int deltay = getHeight() / map.getMaxY();
		int deltax = getWidth() / map.getMaxXsize();

		for (int y = 0; y < game.getMap().getMaxY(); y++)
			for (int x = 0; x < map.getMaxXsize(); x++)
				switch (map.getPosMap(x, y)) {
				case -1:
					g.drawImage(wall, deltax * x, deltay * y, deltax, deltay, null);
					break;
				case 1:
					g.drawImage(tail1, deltax * x, deltay * y, deltax, deltay, null);
					break;
				case 2:
					g.drawImage(tail2, deltax * x, deltay * y, deltax, deltay, null);
					break;
				case 3:
					g.drawImage(tail3, deltax * x, deltay * y, deltax, deltay, null);
					break;
				case 4:
					g.drawImage(tail4, deltax * x, deltay * y, deltax, deltay, null);
					break;

				}
	}

	void displayBots(Graphics g) {

		Map map = game.getMap();

		int deltay = getHeight() / map.getMaxY();
		int deltax = getWidth() / map.getMaxXsize();




		for (int i = 0; i < game.getBots().size(); i++) {
			if (game.getBots().get(i).isAlive())
				switch (game.getBots().get(i).getDirection()) {
				case Player.UP:
					g.drawImage(PlayersV[game.getBots().get(i).getId() - 1], deltax * game.getBots().get(i).getX() - 3,
							deltay * game.getBots().get(i).getY(), deltax * 2, deltay * 4, null);
					break;
				case Player.RIGHT:
					g.drawImage(PlayersH[game.getBots().get(i).getId() - 1],
							deltax * game.getBots().get(i).getX() - deltax * 3,
							deltay * game.getBots().get(i).getY() - 3, deltax * 4, deltay * 2, null);
					break;
				case Player.DOWN:
					g.drawImage(PlayersV[game.getBots().get(i).getId() - 1], deltax * game.getBots().get(i).getX() - 3,
							deltay * game.getBots().get(i).getY() - deltax * 3, deltax * 2, deltay * 4, null);
					break;
				case Player.LEFT:
					g.drawImage(PlayersH[game.getBots().get(i).getId() - 1], deltax * game.getBots().get(i).getX(),
							deltay * game.getBots().get(i).getY() - 3, deltax * 4, deltay * 2, null);
					break;

				}
		}

	}

	void displayPlayers(Graphics g) {
		Map map = game.getMap();

		int deltay = getHeight() / map.getMaxY();
		int deltax = getWidth() / map.getMaxXsize();



		for (int i = 0; i < game.getPlayers().size(); i++) {
			if (game.getPlayers().get(i).isAlive())
				switch (game.getPlayers().get(i).getDirection()) {
				case Player.UP:
					g.drawImage(PlayersV[game.getPlayers().get(i).getId() - 1],
							deltax * game.getPlayers().get(i).getX() - 3, deltay * game.getPlayers().get(i).getY(),
							deltax * 2, deltay * 4, null);
					break;
				case Player.RIGHT:
					g.drawImage(PlayersH[game.getPlayers().get(i).getId() - 1],
							deltax * game.getPlayers().get(i).getX() - deltax * 3,
							deltay * game.getPlayers().get(i).getY() - 3, deltax * 4, deltay * 2, null);
					break;
				case Player.DOWN:
					g.drawImage(PlayersV[game.getPlayers().get(i).getId() - 1],
							deltax * game.getPlayers().get(i).getX() - 3,
							deltay * game.getPlayers().get(i).getY() - deltax * 3, deltax * 2, deltay * 4, null);
					break;
				case Player.LEFT:
					g.drawImage(PlayersH[game.getPlayers().get(i).getId() - 1],
							deltax * game.getPlayers().get(i).getX(), deltay * game.getPlayers().get(i).getY() - 3,
							deltax * 4, deltay * 2, null);
					break;

				}
		}
	}
	
	
	public int getPlayerWhoWon() {
		return playerWhoWon;
	}

	public void setPlayerWhoWon(int playerWhoWon) {
		this.playerWhoWon = playerWhoWon;
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if ((key == KeyEvent.VK_LEFT) && !game.isDirPlayer(0, Player.RIGHT)) {
				game.setDirPlayer(0, Player.LEFT);
			}

			if ((key == KeyEvent.VK_RIGHT) && !game.isDirPlayer(0, Player.LEFT)) {
				game.setDirPlayer(0, Player.RIGHT);
			}

			if ((key == KeyEvent.VK_UP) && !game.isDirPlayer(0, Player.DOWN)) {
				game.setDirPlayer(0, Player.UP);
			}

			if ((key == KeyEvent.VK_DOWN) && !game.isDirPlayer(0, Player.UP)) {
				game.setDirPlayer(0, Player.DOWN);
			}
			if (game.getPlayers().size() == 2) {
				if ((key == KeyEvent.VK_A) && !game.isDirPlayer(1, Player.RIGHT)) {
					game.setDirPlayer(1, Player.LEFT);
				}

				if ((key == KeyEvent.VK_D) && !game.isDirPlayer(1, Player.LEFT)) {
					game.setDirPlayer(1, Player.RIGHT);
				}

				if ((key == KeyEvent.VK_W) && !game.isDirPlayer(1, Player.DOWN)) {
					game.setDirPlayer(1, Player.UP);
				}

				if ((key == KeyEvent.VK_S) && !game.isDirPlayer(1, Player.UP)) {
					game.setDirPlayer(1, Player.DOWN);
				}
			}
		}
	}

}
