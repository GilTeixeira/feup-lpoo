package GUI;

import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JFrame {

	public BufferedImage backGroundMainMenu;

	public Image backGroundGame;

	MainMenu mainMenu;
	GamePanel gamePanel;
	public OptionsMenu optionsMenu;

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public Board() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 806, 629);
		setResizable(false);

		backGroundMainMenu = ImageIO.read(Board.class.getResource("/Images/Background.png"));

		mainMenu = new MainMenu(this);
		optionsMenu = new OptionsMenu(this);
		gamePanel = new GamePanel(this);

		setpanel(mainMenu);
		setVisible(true);
	}

	public void setpanel(JPanel panel) {
		JPanel contentPane = (JPanel) getContentPane();

		contentPane.removeAll();
		contentPane.add(panel);
		contentPane.revalidate();
		contentPane.repaint();
	}

}
