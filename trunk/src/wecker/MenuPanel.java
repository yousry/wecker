/**
 * 
 */
package wecker;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Yousry Abdallah
 * 
 */
public class MenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7772054715657194896L;

	public MenuPanel() {
		super();
		initialize();
	}

	private void initialize() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(1);
		this.setLayout(gridLayout);
		this.setSize(300, 200);
		this.add(setupButton(), null);

	}

	private JButton setupButton() {
		JButton button = new JButton();
		button.setText("Setup");
		button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				Main.showSetup();
			}
		});

		return button;

	}

}
