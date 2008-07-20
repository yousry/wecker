/**
 * 
 */
package wecker;

import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JFrame;

/**
 * @author Yousry Abdallah
 *
 */
public class MenuFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3831472731629513475L;

	/**
	 * @throws HeadlessException
	 */
	public MenuFrame() throws HeadlessException {
		Image image = java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/teacupTray.gif"));
		setIconImage(image);
		setTitle("Wecker");
		
		setSize(320, 200);
		JFrame.setDefaultLookAndFeelDecorated(true);
		setResizable(false);
		MenuPanel pausePanel = new MenuPanel();
		getContentPane().add(pausePanel);
		pack();

		
		
		
	}
	

	
}
