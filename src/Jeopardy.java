 // Copyright Wintriss Technical Schools 2013
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/* This recipe is to be used with the Jeopardy Handout: http://bit.ly/1bvnvd4 */

public class Jeopardy implements ActionListener {
	private JButton firstButton;
	private JButton secondButton;
	private JButton thirdButton, fourthButton;
	JButton[] questionButtons = new JButton[5];
	private Timer questionTimer = new Timer(1000, this);
	int secondsPassed = 0;
	//private Boolean secondButtonModifier = false;
	
	private JPanel randomPanel;
	private JPanel panel2;
	int score = 0;
	JLabel scoreBox = new JLabel("$0");
	int buttonCount = 0;

	public static void main(String[] args) {
		new Jeopardy().start();
	}

	private void start() {
		JFrame frame = new JFrame();
		
		frame.setLayout(new GridLayout(1, 2));
		panel2 = new JPanel();

		randomPanel = new JPanel();
		frame.setLayout(new BorderLayout());
		
		frame.setVisible(true);
		frame.setTitle("The Ultimate TRIALZ! (YOUR DOOM IS INEVITABLE!)");
		JPanel header = new JPanel();
		randomPanel.add(header);
		frame.add(randomPanel);
		
		playJeopardyTheme();
		
		questionButtons[1] = createButton("$1000000");
		randomPanel.add(questionButtons[1]);
		
		// 9. Use the secondButton variable to hold a button using the createButton method
		questionButtons[2] = createButton("$0");
		// 10. Add the secondButton to the quizPanel
		randomPanel.add(questionButtons[2]);
		
		questionButtons[3] = createButton("$200");
		randomPanel.add(questionButtons[3]);
		
		questionButtons[4] = createButton("$400");
		randomPanel.add(questionButtons[4]);
		
		// 11. Add an action listeners to the buttons (2 lines of code)
		for(JButton b: questionButtons)
		{
			if(!(b == null))
			{
				b.addActionListener(this);
			}
		}
		// 12. Fill in the actionPerformed() method below
				
		frame.pack();
		randomPanel.setLayout(new GridLayout(buttonCount+1, 3));
		frame.add(makeScorePanel(), BorderLayout.NORTH);
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().height, Toolkit.getDefaultToolkit().getScreenSize().width);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*
	 * 13. Use the method provided to play the Jeopardy theme music 
	 * 
	 * 14. Add buttons so that you have $200, $400, $600, $800 and $1000 questions
	 *
	 * [optional] Use the showImage or playSound methods when the user answers a question 
	 * [optional] Add new topics for the quiz
	 */
	
	private JButton createButton(String dollarAmount) {
		// Create a new JButton
		JButton button = new JButton();
		// Set the text of the button to the dollarAmount
		button.setText(dollarAmount);
		// Increment the buttonCount (this should make the layout vertical)
		buttonCount = buttonCount + 1;
		// Return your new button instead of the temporary button
		return button;
	}

	public void actionPerformed(ActionEvent arg0) {
		//playJeopardyTheme();

		
		if(arg0.getSource() instanceof Timer)
		{
			secondsPassed = secondsPassed + 1;
			System.out.println(secondsPassed);
		}
		else
		{
			JButton buttonPressed = (JButton) arg0.getSource();
			// If the buttonPressed was the firstButton
			
			if(!(buttonPressed.getText().equals("")))
			{
				if(buttonPressed == questionButtons[1])
				{
					buttonPressed.setText("");
					askQuestion("Enter the text that was shown on the button you just clicked", "$1000000", 1000000, 5);
					questionButtons[2].setText("2x");
					
				}
				// Or if the buttonPressed was the secondButton
	
				else if(buttonPressed == questionButtons[2])
				{
					if(buttonPressed.getText().equals("$0"))
					{
						askQuestion("Before you answer the question, remember this: Type your answer in the box below. When you are done, press Enter to finish. There is no time limit to this question (You have five seconds and the answer is 'answer'. If you take too long (you probably did by now), you will lose EVERYTHING)", "answer", -score, 5);
					}
					else if(buttonPressed.getText().equals("2x"))
					{
						askQuestion("PERFECTLY RECITE the question that was shown by the $1000000 button IN TEN SECONDS!", "Enter the text that was shown on the button you just clicked", score, 10);
					}
					buttonPressed.setText("");
	
				}
				
				else if(buttonPressed == questionButtons[3])
				{
					int blankButtons = 0;
					for(JButton b: questionButtons)
					{
						if(!(b == null))
						{
							if(b.getText().equals(""))
							{
								blankButtons = blankButtons + 1;
							}
						}
					}
					buttonPressed.setText("");
					askQuestion("How many blank buttons were there BEFORE you pressed this button?", "" + blankButtons, 200, secondsPassed + 1);
				}
				else if(buttonPressed == questionButtons[4])
				{
					buttonPressed.setText("");
					askQuestion("EXACTLY HOW MANY SECONDS HAVE PASSED SINCE YOU PRESSED THIS BUTTON?! You have as much time as you need. Therefore, you cannot reduce your inevitable losses.", "" + secondsPassed, 400, secondsPassed + 1);
				}
			}
		}
		// Clear the button text (set the button text to nothing)

	}

	private void askQuestion(String question, String correctAnswer, int prizeMoney, int timeLimit) {
		secondsPassed = 0;

		JOptionPane.showMessageDialog(null, "Get ready for some ultimate questioning ACTION!!!");
		questionTimer.start();
		String answer = JOptionPane.showInputDialog(question);
		// If the answer is correct
		questionTimer.stop();
		if(answer.equals(correctAnswer) && secondsPassed < timeLimit)
		{
			// Increase the score by the prizeMoney
			score = score + prizeMoney;
			// Call the updateScore() method
			// Pop up a message to tell the user they were correct
			JOptionPane.showMessageDialog(null, "Cantragulations! You just earned " + prizeMoney + " nonexistent dollars (Not legal tender)!");
			updateScore();
		}	
		// Otherwise
		else if(answer.equals(correctAnswer) && secondsPassed > timeLimit)
		{
			
			// Decrement the score by the prizeMoney
			score = score - prizeMoney/2;
			// Pop up a message to tell the user the correct answer
			JOptionPane.showMessageDialog(null, "SHAME on YOU and your EVIL PLANS! You just wasted " + secondsPassed + " seconds TOO MANY losing " + prizeMoney + " nonexistent dollars from the fact that your ridiculous answer of " + answer + " did not match the correct answer of " + correctAnswer + "!");
			// Call the updateScore() method
			updateScore();
		}	
		else if(!answer.equals(correctAnswer) && secondsPassed < timeLimit)
		{
			score = score - prizeMoney;
			JOptionPane.showMessageDialog(null, "The Committee On Incorrect Answers And Unexpected Penalties believe that if your answer is correct, then it should not be " + answer + ". It was " + correctAnswer + ", though it was quite obvious that you would eventually succumb to Your Doom. However, it appreciates your disinterest for this question.");
			updateScore();
		}
		else if(!answer.equals(correctAnswer) && secondsPassed > timeLimit)
		{
			score = score - 2*prizeMoney;
			JOptionPane.showMessageDialog(null, "The Committee refuses to wait " + secondsPassed + " seconds only to receive an incorrect response. The answer was " + correctAnswer + ", but that does not really matter anymore. Report to the Office Of Your Doom immediately!!");
			updateScore();
		}
		
	}

	public void playJeopardyTheme() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new URL("https://github.com/jointheleague/league-sounds/blob/master/jeopardy.wav?raw=true"));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }
	}


	private void playSound(String fileName) {
		AudioClip scream = JApplet.newAudioClip(getClass().getResource(fileName));
		scream.play();
	}

	private Component makeScorePanel() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("score:"));
		panel.add(scoreBox);
		panel.setBackground(Color.CYAN);
		return panel;
	}

	private void updateScore() {
		scoreBox.setText("$" + score);
		if(score < 0)
		{
			playJeopardyTheme();
			JOptionPane.showMessageDialog(null, "YOUR SCORE IS NEGATIVE! YOUR DOOM IS NOW!");
			System.exit(1);
		}
	}

	private JPanel createHeader(String topicName) {
		JPanel panelj = new JPanel();
		panelj.setLayout(new BoxLayout(panelj, BoxLayout.PAGE_AXIS));
		JLabel l1 = new JLabel(topicName);
		l1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelj.add(l1);
		return panelj;
	}

	void showCorrectImage() {
		showImage("correct.jpg");
	}

	void showIncorrectImage() {
		showImage("incorrect.jpg");
	}

	private void showImage(String fileName) {
		JFrame frame = new JFrame();
		URL imageURL = getClass().getResource(fileName);
		Icon icon = new ImageIcon(imageURL);
		JLabel image = new JLabel(icon);
		frame.add(image);
		frame.setVisible(true);
		frame.pack();
	}
}


