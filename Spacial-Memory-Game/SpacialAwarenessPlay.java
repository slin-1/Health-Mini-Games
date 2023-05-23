//Created by Steven Lin
//Spacial Awareness Game - code for play functionality

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class SpacialAwarenessPlay implements ActionListener {

	//JFrame for the initial GUI
	static JFrame welcomeFrame;
	//linked list of levels.
	static LinkedList<SpacialAwarenessLevel> levels;
	//short to keep track of how many incorrect guesses the user has had throughout the whole game
	static short incorrect;
	//byte to keep track of the player's current level. 
	static byte levelNum;
	
	//welcome method initializes and opens a GUI explaining the rules of the game
	//this is the first method that is run
	public static void welcome() {
		
		welcomeFrame = new JFrame();
		welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomeFrame.setSize(526,400);
		welcomeFrame.setLocationRelativeTo(null); //centers the GUI on the screen when starting
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		welcomeFrame.add(panel);
		
		JLabel welcome = new JLabel();
		welcome.setText("<html><div style='text-align: center;'>Welcome to Brain Game's spacial awareness test!<html>");
		welcome.setFont(new Font("Times new roman", Font.BOLD, 35));
		welcome.setForeground(Color.WHITE);

		welcome.setLocation(5,20);
		welcome.setSize(500,75);
		panel.add(welcome);
		
		JLabel instructions = new JLabel();
		instructions.setText("<html><div style='text-align: center;'>You will be shown a grid of white squares with 5 randomly coloured in black. Once the black squares dissapear, select the spaces where you remember seeing them. Find all of the squares to progress to the next level.<html>");
		instructions.setFont(new Font("Helvetica Ultra Light", Font.TRUETYPE_FONT, 18));
		instructions.setForeground(Color.WHITE);
		

		instructions.setLocation(5,110);
		instructions.setSize(500,140);
		panel.add(instructions);
				
		JButton button = new JButton("Start");
		button.setFont(new Font("Helvetica Ultra Light", Font.BOLD, 18));
		button.setBackground(Color.WHITE);

		button.setSize(150,60);
		button.setLocation(180, 275);
		panel.add(button);
		
		button.addActionListener(new SpacialAwarenessPlay());
		welcomeFrame.setVisible(true);	
	}
	
	//play method starts the game
	public static void play() {
		
		//initializes incrementing variables
		incorrect = 0;
		levelNum = 0;
		
		//initializes linked list
		levels = new LinkedList<SpacialAwarenessLevel>();
		
		//adds levels to the linked list in order from array dimensions of 3x3 to array dimensions of 8x8
		for(byte dimensions = 8; dimensions >= 3; dimensions--) {		
			SpacialAwarenessLevel level = new SpacialAwarenessLevel(dimensions);
			levels.push(level);		
		}
		
		//begins the first level
		levels.getFirst().startLevel();
	}
	
	//begins the next level of the game. 
	//java.util.LinkedList has no built in .next variable/method, meaning I have to keep track of the level using a byte variable
	public static void nextLevel() {
		
		//increments level number
		levelNum++;
		
		//checks if the next level exists
		if(levelNum < levels.size())
			levels.get(levelNum).startLevel();
		//if there is no next level, the game is over
		else {
			gameOver();
		}
	}
	
	//void method to run once the game has completed. Gives the player their score
	public static void gameOver() {
		
		if(incorrect == 1)
			JOptionPane.showMessageDialog(null, "You completed every level with " + incorrect + " mistake!");
		else
			JOptionPane.showMessageDialog(null, "You completed every level with " + incorrect + " mistakes!");
		
		System.exit(0);
	}
	
	
	//action listener linked exclusively to the button on the instructions GUI. Deletes the welcome GUI and runs play()
	public void actionPerformed(ActionEvent e) {
	
		welcomeFrame.dispose();
		play();
		
	}
	
	public static void main(String[] args) {
		welcome();
	}
}
