//Created by Steven Lin
//Mental Health Game - code for menu GUI

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MentalMathMenuGUI implements ActionListener{
//Instance Variables
	JFrame frame;
	JButton startButton, instructionsButton;
	JLabel introLabel, instructionsLabel;
	
//=====================================================================================================================================================================================================	
	
//Constructor for GUI
	public MentalMathMenuGUI() {
	//initialize the elements of the GUI
		this.frame = new JFrame();	
    	this.startButton = new JButton("Click to Start!");
    	this.instructionsButton = new JButton("Instructions");
    	this.introLabel = new JLabel("Welcome to Brain Game's Mental Math Game!");
    	this.instructionsLabel = new JLabel("<html>Objective of the game:<br/>Gain points and train multiplication skills by completing as many given equations as possible within the allotted time<html>"
    										+ "<html><br/><br/>Given Time: 60 seconds<br/>Range of numbers: 1 to 15<br/><br/>Correct Answer: 20 Points <br/>Wrong Answer: -5 Points<br/>Entering non-digits: -5 Points<html>");
    	
    //Frame - frame that holds all elements
    	this.frame.setLayout(null); //no predetermined layout
    	this.frame.setSize(500, 500); //width x height (size of frame)
    	this.frame.getContentPane().setBackground(new Color(30, 30, 30)); //RGB colors for the background (dark gray)
    	this.frame.setTitle("Mental Math Menu"); //give GUI a title
    	this.frame.setLocationRelativeTo(null); //centers the GUI on the screen when starting
    	this.frame.setVisible(true);
    	//ensures that the application closes
    	this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    //startButton - button to begin the game
    	this.startButton.setSize(200, 50);
    	this.startButton.setLocation(150, 200); //set the location
    	this.startButton.setBackground(new Color(225, 198, 153)); //RGB colors - make button beige
    	this.startButton.addActionListener(this);
    	this.startButton.setVisible(true);
    	this.frame.add(startButton); //add button to the frame
    	
    //instructionsButton - button to open the instructions
    	this.instructionsButton.setSize(200, 50);
    	this.instructionsButton.setLocation(150, 275);
    	this.instructionsButton.setBackground(new Color(225, 198, 153)); //RGB colors - make button orange
    	this.instructionsButton.addActionListener(this);
    	this.instructionsButton.setVisible(true);
    	this.frame.add(instructionsButton); //add button to the frame
    	
    //introLabel - label to welcome the user
    	this.introLabel.setSize(400, 50);
    	this.introLabel.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18)); //changes font, and size
    	this.introLabel.setForeground(new Color(255, 255, 255)); //make font white
    	this.introLabel.setLocation(75, 150);
    	this.introLabel.setVisible(true);
    	this.frame.add(introLabel); //add label to the frame
    
    //instructionsLabel - displays the instructions when prompted
    	this.instructionsLabel.setSize(400, 180);
    	this.instructionsLabel.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
    	this.instructionsLabel.setForeground(new Color(255, 255, 255)); //make font white
    	this.instructionsLabel.setLocation(50, 10);
    	this.instructionsLabel.setVisible(false);
    	this.frame.add(instructionsLabel); //add label to the frame
	}
	
	
	//Method to determine what happens after user input
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton){ //starts the game when user presses the start button
    		startButton.setVisible(false); //remove start button
    		instructionsButton.setVisible(false); //remove instruction button
    		introLabel.setVisible(false); //remove introduction label
    		instructionsLabel.setVisible(false); //remove instructions label
    		frame.dispose(); //close the menu GUI
    		MentalMathGameGUI StartGame = new MentalMathGameGUI(); //start the game by creating a new GUI w/ the game class
    	}
		
		if(e.getSource() == instructionsButton){ //brings up the instructions of the game when the instructions button is pressed
    		startButton.setVisible(true); //keep start button visible
    		instructionsButton.setVisible(false); //remove instruction button
    		introLabel.setVisible(false); //remove introduction label
    		instructionsLabel.setVisible(true); //make instructions visible
    	}
	}
	
//=====================================================================================================================================================================================================	
	
	public static void main(String[] args) {
		MentalMathMenuGUI MentalMathMenu = new MentalMathMenuGUI(); //create the game's menu using the class's constructor
	}
}
