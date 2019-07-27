package GUI;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	Board frame;
	JButton btnSingle;
	JButton btnMulti;
	JButton btnOptions;

	/**
	 * Create the panel.
	 */
	public MainMenu(Board frame) {
		setLayout(null);
		
		this.frame = frame;

		setSingleButton();
		setMultiButton();
		setOptionsButton();

		
	}

	void setSingleButton() {
		btnSingle = new JButton("SinglePlayer");
		btnSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setpanel(frame.gamePanel);
				frame.gamePanel.remove(frame.gamePanel.gamePanelStatus);
				frame.gamePanel.repaint();
				try {
					frame.gamePanel.startGameSingle();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		btnSingle.setBounds(329, 265, 142, 30);
		add(btnSingle);

	}

	void setMultiButton() {
		btnMulti = new JButton("MultiPlayer");
		btnMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setpanel(frame.gamePanel);
				frame.gamePanel.remove(frame.gamePanel.gamePanelStatus);
				frame.gamePanel.repaint();
				try {
					frame.gamePanel.startGameMulti();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnMulti.setBounds(329, 317, 142, 30);
		add(btnMulti);

	}

	void setOptionsButton() {
		btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setpanel(frame.optionsMenu);
			}
		});
		btnOptions.setBounds(329, 367, 142, 30);
		add(btnOptions);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(frame.backGroundMainMenu, 0, 0, getWidth(), getHeight(), null);

		requestFocusInWindow();

	}

}
