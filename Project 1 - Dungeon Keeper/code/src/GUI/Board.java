package GUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;


import javax.swing.JButton;

import java.awt.FlowLayout;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class Board extends JFrame {

	private JPanel mainPanel;

	JPanel panelSettings;
	JButton btnNewGame;
	JButton btnOptions;
	JButton btnExit;

	JPanel Controls;
	JButton btnDown;
	JButton btnUp;

	JButton btnLeft;
	JButton btnRight;

	JLabel lblGameStatus;

	String NumOfOgres = "1";
	String GuardPersonality = "Rookie";

	GamePanel gamePanel;

	/**
	 * Create the frame.
	 */
	public Board() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);

		panelSettings = new JPanel();
		mainPanel.add(panelSettings, BorderLayout.NORTH);
		panelSettings.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		initUI();
		pack();

	}

	public void initUI() {

		initNewGameButton();

		initOptions();

		initExitButton();

		initGameArea();

		initGameStatusLabel();

		initDirectionButtons();

	}

	@SuppressWarnings("unused")
	private void createOptionsFrame() {
		OptionsFrame optionsFrame = new OptionsFrame(this);

	}

	public void initOptions() {
		btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createOptionsFrame();

			}

		});
		panelSettings.add(btnOptions);

	}

	public void initExitButton() {

		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelSettings.add(btnExit);

	}

	public void initGameArea() {
		gamePanel = new GamePanel(this);
		gamePanel.setPreferredSize(new Dimension(500, 500));
		gamePanel.setBackground(Color.BLACK);
		mainPanel.add(gamePanel, BorderLayout.CENTER);

	}

	public void initGameStatusLabel() {

		lblGameStatus = new JLabel("You can start a new game.");
		mainPanel.add(lblGameStatus, BorderLayout.SOUTH);

	}

	public void initButtonUp() {
		btnUp = new JButton("   Up   ");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!gamePanel.heroMove('w'))
					gameOver();

			}
		});
		btnUp.setEnabled(false);
		Controls.add(btnUp, "cell 1 1,alignx center,aligny top");

	}

	public void initButtonDown() {
		btnDown = new JButton(" Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!gamePanel.heroMove('s'))
					gameOver();
			}
		});
		btnDown.setEnabled(false);
		Controls.add(btnDown, "cell 1 2,alignx center,aligny top");

	}

	public void initButtonLeft() {
		btnLeft = new JButton("  Left ");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!gamePanel.heroMove('a'))
					gameOver();
			}
		});
		btnLeft.setEnabled(false);
		Controls.add(btnLeft, "cell 0 2,alignx right,aligny top");

	}

	public void initButtonRight() {
		btnRight = new JButton("Right ");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!gamePanel.heroMove('d'))
					gameOver();
			}
		});
		btnRight.setEnabled(false);
		Controls.add(btnRight, "cell 2 2,alignx left,aligny top");

	}

	public void initDirectionButtons() {
		Controls = new JPanel();
		mainPanel.add(Controls, BorderLayout.EAST);
		Controls.setLayout(new MigLayout("", "[64.00px][64.00px][64px]", "[23px][][][23px][][][]"));
		
		initButtonRight();
		initButtonLeft();
		initButtonUp();
		initButtonDown();

	}

	public void initNewGameButton() {

		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!NumOfOgres.matches("[0-9]+") || NumOfOgres.length() != 1) {
					lblGameStatus.setText("Invalid number of Ogres.");
					return;
				}
				int numOgres = Integer.parseInt(NumOfOgres);
				if (numOgres < 1 || numOgres > 5) {
					lblGameStatus.setText("Invalid number of Ogres.");
					return;
				}

				gamePanel.startGame(GuardPersonality, numOgres);

				lblGameStatus.setText("You can play now.");
				enableButtons();

			}
		});
		panelSettings.add(btnNewGame);

	}

	public void gameOver() {
		disableButtons();
		int result = gamePanel.getGame().results();

		switch (result) {
		case 0:
			lblGameStatus.setText("You Won the Game!");
			break;
		case 1:
			lblGameStatus.setText("Game Over! The Guard has caught you!");
			break;
		case 2:
			lblGameStatus.setText("Game Over! A Ogre has killed you!");
			break;

		}

	}

	public void disableButtons() {
		btnRight.setEnabled(false);
		btnLeft.setEnabled(false);
		btnUp.setEnabled(false);
		btnDown.setEnabled(false);

	}

	public void enableButtons() {
		btnRight.setEnabled(true);
		btnLeft.setEnabled(true);
		btnUp.setEnabled(true);
		btnDown.setEnabled(true);
	}

	public String getNumOfOgres() {
		return NumOfOgres;
	}

	public void setNumOfOgres(String numOfOgres) {
		NumOfOgres = numOfOgres;
	}

	public String getGuardPersonality() {
		return GuardPersonality;
	}

	public void setGuardPersonality(String guardPersonality) {
		GuardPersonality = guardPersonality;
	}

}
