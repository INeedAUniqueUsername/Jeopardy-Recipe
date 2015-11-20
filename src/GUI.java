import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {

	private static JFrame window;
	private static JPanel windowPanel;
	
	public static void main(String[] args) {
		start();
	}
	public static void start()
	{
		window = new JFrame();
		windowPanel = new JPanel();

		JButton button1 = new JButton();
		button1.setText("YOUR DOOM!");
		//button1.addActionListener(this);
		
		JButton button2 = new JButton();
		button2.setText("NOT YOUR DOOM!");
		//button2.addActionListener(this);
		
		windowPanel.add(button1);
		windowPanel.add(button2);
		
		window.add(windowPanel);
		window.setVisible(true);
	}
}
