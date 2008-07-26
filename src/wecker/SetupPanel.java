/**
 * Small application For regular work breaks.
 * GPL (c)2007 Yousry Abdallah
 */
package wecker;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author yousry
 * 
 */
public class SetupPanel extends JPanel implements TimeListener {

	private static final long serialVersionUID = 1L;

	private JLabel timeProgressLabel = null;

	private JProgressBar intervallProgressBar = null;

	private JLabel intervallLabes = null;

	private JLabel intervallSizeLabel = null;

	private JSlider intervallSizeSlider = null;

	private JButton startButton = null;

	private JLabel intervallActualLabel = null;

	private SetupFrame setupFrame;

	/**
	 * This is the default constructor
	 */
	public SetupPanel() {
		super();
		TimeRun.getTimeRun().addListener(this);
		initialize();

	}

	public SetupPanel(SetupFrame frame) {
		super();
		TimeRun.getTimeRun().addListener(this);
		initialize();
		this.setupFrame = frame;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.gridx = 2;
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.insets = new Insets(0, 3, 0, 5);
		gridBagConstraints13.gridy = 1;
		intervallActualLabel = new JLabel();
		intervallActualLabel.setText("05/m");
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 2;
		gridBagConstraints12.insets = new Insets(5, 3, 5, 5);
		gridBagConstraints12.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints12.gridy = 2;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.gridy = 1;
		gridBagConstraints3.weightx = 1.0;
		gridBagConstraints3.gridx = 1;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.fill = GridBagConstraints.BOTH;
		gridBagConstraints2.insets = new Insets(0, 5, 0, 3);
		gridBagConstraints2.gridy = 1;
		intervallSizeLabel = new JLabel();
		intervallSizeLabel.setText("Intervall");
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 2;
		gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints11.insets = new Insets(0, 3, 0, 5);
		gridBagConstraints11.gridy = 0;
		intervallLabes = new JLabel();
		intervallLabes.setText("00:00");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 1;
		gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints1.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints1.gridy = 0;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.insets = new Insets(5, 5, 0, 3);
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		timeProgressLabel = new JLabel();
		timeProgressLabel.setText("Progress");
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		this.add(timeProgressLabel, gridBagConstraints);
		this.add(getIntervallProgressBar(), gridBagConstraints1);
		this.add(intervallLabes, gridBagConstraints11);
		this.add(intervallSizeLabel, gridBagConstraints2);
		this.add(getIntervallSizeSlider(), gridBagConstraints3);
		this.add(getStartButton(), gridBagConstraints12);
		this.add(intervallActualLabel, gridBagConstraints13);
	}

	/**
	 * This method initializes IntervallProgressBar
	 * 
	 * @return javax.swing.JProgressBar
	 */
	private JProgressBar getIntervallProgressBar() {
		if (intervallProgressBar == null) {
			intervallProgressBar = new JProgressBar();
		}
		return intervallProgressBar;
	}

	/**
	 * This method initializes IntervallSizeSlider
	 * 
	 * @return javax.swing.JSlider
	 */
	private JSlider getIntervallSizeSlider() {
		if (intervallSizeSlider == null) {
			intervallSizeSlider = new JSlider(1, 99, 5);
			intervallSizeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {

					NumberFormat formatter = new DecimalFormat("00");
					intervallActualLabel.setText(formatter.format(intervallSizeSlider.getValue()) + "/m");

				}
			});
		}
		return intervallSizeSlider;
	}

	/**
	 * This method initializes StartButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getStartButton() {
		if (startButton == null) {
			startButton = new JButton();
			startButton.setText("Start");
			startButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					TimeRun.getTimeRun().setIntervall(intervallSizeSlider.getValue() * (1000 * 60));
					TimeRun.getTimeRun().startNewIntervall();
					setupFrame.setVisible(false);

				}
			});
		}
		return startButton;
	}

	public void timeStatus() {

		if (TimeRun.getTimeRun() == null)
			return;

		long intervall = TimeRun.getTimeRun().getIntervall();
		long startDate = TimeRun.getTimeRun().getStartTime();
		long actualTime = Calendar.getInstance().getTimeInMillis();
		long status = (actualTime - startDate) * 100 / intervall;

		if (status > 100)
			status = 100;

		long remainingTime = intervall - (actualTime - startDate);
		if (remainingTime <= 0)
			remainingTime = 0;

		DateFormat df = new SimpleDateFormat("0:mm:ss");

		if (remainingTime >= 3600000)
			df = new SimpleDateFormat("1:mm:ss");

		final String statusText = df.format(remainingTime);
		final int statusInt = (int) status;

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				intervallLabes.setText(statusText);
				intervallProgressBar.setValue(statusInt);
			}
		});

	}

	public void timeisUp() {
	}

}
