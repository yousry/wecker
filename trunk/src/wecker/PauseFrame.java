/**
 * 
 */
package wecker;

import java.awt.Image;

import javax.swing.JFrame;

/**
 * @author yousry
 * 
 */
public class PauseFrame extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3372330164368763929L;

	public PauseFrame() {
		super();
		Image image = java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/teacupTray.gif"));
		setIconImage(image);

		
		
		setTitle("Click on Image to Continue!");
		
		setSize(320, 200);
		JFrame.setDefaultLookAndFeelDecorated(true);
		setResizable(false);
		PausePanel pausePanel = new PausePanel(this);
		getContentPane().add(pausePanel);
		pack();
		

	}


	
}
