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
public class SetupFrame extends JFrame {
	
	static private SetupFrame setupFrame;
	
	static SetupFrame getSetupFrame() {
		return setupFrame;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7788366063224510908L;
	
	public SetupFrame() {
		super();
		
		setupFrame = this;
		
		Image image = java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/teacupTray.gif"));
		setIconImage(image);

		setTitle("Wecker Setup");
		setSize(320, 200);
		JFrame.setDefaultLookAndFeelDecorated(true);
		setResizable(false);
		SetupPanel setupPanel = new SetupPanel(this);
		getContentPane().add(setupPanel);
		pack();
	}
	
}
