package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GamePanelStatus extends JPanel {

	public JLabel lblStatus;
	JButton btnRestart;
	JButton btnBack;

	/**
	 * Create the panel.
	 */
	public GamePanelStatus(GamePanel panel) {

		setBounds(286, 217, 252, 166);
		setLayout(null);

		setBackground(Color.CYAN);
		
		setStatusLabel();
		setRestartButton(panel);
		setBackButton(panel);
	}
	
	void setStatusLabel() {
		lblStatus = new JLabel("Game Status");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(85, 30, 92, 14);
		add(lblStatus);
	
	}
	
	void setRestartButton(GamePanel panel) {
		btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.remove(panel.gamePanelStatus);
				panel.repaint();
				try {
					if (panel.gameType == 0)
						panel.startGameSingle();
					else
						panel.startGameMulti();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		btnRestart.setBounds(30, 89, 79, 23);
		add(btnRestart);
		
	}
	
	void setBackButton(GamePanel panel) {
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.board.setpanel(panel.board.mainMenu);
			}
		});
		btnBack.setBounds(145, 89, 79, 23);
		add(btnBack);
		
	}

}
