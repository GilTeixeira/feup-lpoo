package GUI;

import javax.swing.JPanel;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class OptionsMenu extends JPanel {

	Board frame;

	public JComboBox<String> comboBoxAI;
	public JComboBox<String> comboBoxNumBots;
	JButton btnBack;

	/**
	 * Create the panel.
	 */
	public OptionsMenu(Board frame) {

		setLayout(null);
		setComboBoxAI();
		setComboBoxNumBots();
		setBackButton();

		this.frame = frame;

	}

	void setComboBoxAI() {
		comboBoxAI = new JComboBox<String>();
		comboBoxAI.setBounds(300, 221, 219, 20);
		add(comboBoxAI);
		initAISelector();

	}

	void setComboBoxNumBots() {
		comboBoxNumBots = new JComboBox<String>();
		comboBoxNumBots.setBounds(300, 288, 219, 20);
		add(comboBoxNumBots);
		initNumBotsSelector();

	}

	void setBackButton() {
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setpanel(frame.mainMenu);
			}
		});
		btnBack.setBounds(677, 527, 79, 23);
		add(btnBack);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(frame.backGroundMainMenu, 0, 0, getWidth(), getHeight(), null);

	}

	public void initAISelector() {

		comboBoxAI.addItem("Random");
		comboBoxAI.addItem("Wall-hugging");
		comboBoxAI.addItem("Enemy Avoidance");
		comboBoxAI.addItem("Most Open Destination");

	}

	public void initNumBotsSelector() {

		comboBoxNumBots.addItem("1");
		comboBoxNumBots.addItem("2");
		comboBoxNumBots.addItem("3");

	}

	public int getNumBotsSelected() {
		return Integer.parseInt(comboBoxNumBots.getSelectedItem().toString());

	}

	public int getBotBehaviourSelected() {
		return comboBoxAI.getSelectedIndex();

	}

}
