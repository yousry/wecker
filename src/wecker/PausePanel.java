/**
* Small application For regular work breaks.
 * GPL (c)2007 Yousry Abdallah */
package wecker;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 * @author yousry
 * 
 */
public class PausePanel extends JPanel implements TimeListener {

	private static final long serialVersionUID = 1L;

	private JButton backFromPauseButton = null;

	private PauseFrame pauseFrame;

	/**
	 * This is the default constructor
	 */
	public PausePanel() {
		super();
		initialize();
	}

	public PausePanel(PauseFrame frame) {
		super();
		TimeRun.getTimeRun().addListener(this);
		initialize();
		this.pauseFrame = frame;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(1);
		this.setLayout(gridLayout);
		this.setSize(300, 200);
		this.add(getBackFromPauseButton(), null);
	}

	/**
	 * This method initializes backFromPauseButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBackFromPauseButton() {
		if (backFromPauseButton == null) {
			backFromPauseButton = new JButton();
			backFromPauseButton.setIcon(new ImageIcon(getClass().getResource("/teacup.jpg")));
			backFromPauseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pauseFrame != null)
						TimeRun.getTimeRun().startNewIntervall();
					pauseFrame.setVisible(false);
				}
			});
		}
		return backFromPauseButton;
	}

	public void timeisUp() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (pauseFrame != null)
					pauseFrame.setVisible(true);
			}
		});
	}

	public void timeStatus() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (pauseFrame != null && pauseFrame.isValid())
					pauseFrame.setVisible(false);
			}
		});
	}

}
