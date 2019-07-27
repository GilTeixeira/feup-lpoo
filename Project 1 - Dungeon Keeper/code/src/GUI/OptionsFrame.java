package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OptionsFrame {
	JFrame options = new JFrame();
	JPanel contentPane = new JPanel();
	JTextField tfNumOgres = new JTextField();
	JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
	JLabel lblGuardPersonality = new JLabel("Guard Personality");
	JComboBox<String> fldGuardPersona = new JComboBox<String>();
	Board board;

	public OptionsFrame(Board board) {
		this.board = board;

		// options.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		options.setBounds(100, 100, 450, 300);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		options.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initOgreSelector();
		initGuardSelector();

		WindowListener exitListener = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				board.setGuardPersonality(fldGuardPersona.getSelectedItem().toString());
				board.setNumOfOgres(tfNumOgres.getText());

			}
		};
		options.addWindowListener(exitListener);
		options.setVisible(true);

	}

	public void initOgreSelector() {
		
		tfNumOgres.setText(board.getNumOfOgres());
		tfNumOgres.setBounds(144, 70, 86, 20);
		contentPane.add(tfNumOgres);
		tfNumOgres.setColumns(10);

	
		lblNumberOfOgres.setBounds(28, 73, 106, 14);
		contentPane.add(lblNumberOfOgres);

	}

	public void initGuardSelector() {
		
		lblGuardPersonality.setBounds(28, 112, 106, 14);
		contentPane.add(lblGuardPersonality);

		
		fldGuardPersona.setBounds(144, 109, 86, 20);
		contentPane.add(fldGuardPersona);
		fldGuardPersona.addItem("Rookie");
		fldGuardPersona.addItem("Druken");
		fldGuardPersona.addItem("Suspicious");
		if (board.getGuardPersonality() == "Rookie")
			fldGuardPersona.setSelectedIndex(0);
		else if (board.getGuardPersonality() == "Druken")
			fldGuardPersona.setSelectedIndex(1);
		else if (board.getGuardPersonality() == "Suspicious")
			fldGuardPersona.setSelectedIndex(2);
		

	}

}
