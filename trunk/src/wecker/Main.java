/**
 * Small application For regular work breaks.
 * GPL (c)2007 Yousry Abdallah
 */
package wecker;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;

/**
 * @author Yousry Abdallah
 * 
 */
public class Main {

	static private SystemTray systemTray;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null,
					"Could not setup Default Look & Feel!", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}

		TimeRun timeRun = new TimeRun();
		Thread thread = new Thread(timeRun);
		timeRun.setIntervall(60000 * 5);
		timeRun.startNewIntervall();
		thread.start();

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (SystemTray.isSupported()) {

					try {
						systemTray = SystemTray.getSystemTray();
					} catch (Exception E) {

						MenuFrame menuFrame = new MenuFrame();
						menuFrame
								.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						menuFrame.setVisible(true);
					}

					Image image = java.awt.Toolkit.getDefaultToolkit()
							.getImage(
									this.getClass().getResource(
											"/teacupTray.gif"));

					PopupMenu popupMenu = new PopupMenu("Wecker");
					final TrayIcon trayIcon = new TrayIcon(image, "Wecker",
							popupMenu);

					MenuItem setupItem = new MenuItem("Setup");

					ActionListener setupListener = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							showSetup();
						}
					};

					setupItem.addActionListener(setupListener);
					popupMenu.add(setupItem);

					MenuItem exitItem = new MenuItem("Exit");

					ActionListener exitListener = new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							systemTray.remove(trayIcon);
							System.exit(0);
						}
					};

					exitItem.addActionListener(exitListener);
					popupMenu.add(exitItem);

					try {
						systemTray.add(trayIcon);
					} catch (AWTException e) {
						JOptionPane.showMessageDialog(null,
								"Could not add icon to systemtray.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"System not supported.", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PauseFrame pauseFrame = new PauseFrame();
				pauseFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			}
		});

	}

	protected static void showSetup() {
		SetupFrame setupFrame;
		if (SetupFrame.getSetupFrame() != null) {
			setupFrame = SetupFrame.getSetupFrame();
		} else {
			setupFrame = new SetupFrame();
			setupFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}
		setupFrame.setVisible(true);

	}
}
