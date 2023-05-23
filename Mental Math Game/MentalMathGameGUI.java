//Created by Steven Lin
//Mental Math game GUI for Brain Game for Hacking-Health hackathon

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MentalMathGameGUI implements ActionListener{
//Instance Variables
	JFrame gameFrame;
	JButton returnButton;
	JLabel questionLabel, resultLabel, scoreLabel, timerLabel;
	JTextField userText;
	Timer timer;

//=====================================================================================================================================================================================================	
	
//Constructor for the game
	public MentalMathGameGUI() {
		MentalMathLogic.Randomize(); //initiate the number randomizer
	
	//initialize the elements of the GUI
		this.gameFrame = new JFrame();	
		this.returnButton = new JButton("Return");
		this.questionLabel = new JLabel(MentalMathLogic.strNum1 + " x " + MentalMathLogic.strNum2 + " = ?");
		this.resultLabel = new JLabel();
		this.scoreLabel = new JLabel("Score: 0");
		this.timerLabel = new JLabel("time");
		this.userText = new JTextField();
		
	//timer - setup for the timer	
		int intCount = 60; //the amount of time
		timerLabel.setText("Time Left: " + intCount + "s"); //sets the timer label to the starting time
		TimeClass Time = new TimeClass(intCount); //initiates the timer class to begin counting down
		timer = new Timer(1000, Time); //introduces the delay of the timer. In this case the timer uses seconds.
		timer.start(); //starts the timer right away		
		
	//gameFrame - frame that holds all elements
		this.gameFrame.setLayout(null); //no predetermined layout
    	this.gameFrame.setSize(500, 500); //width x height (size of frame)
    	this.gameFrame.getContentPane().setBackground(new Color(30, 30, 30)); //RGB colors for the background (dark gray)
    	this.gameFrame.setTitle("Mental Math Game"); //give GUI a title
    	this.gameFrame.setLocationRelativeTo(null); //centers the GUI on the screen when starting
    	this.gameFrame.setVisible(true);
    	//ensures that the application closes
    	this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
    //returnButton - button to allow the user to return to the game menu
    	this.returnButton.setSize(100, 30); //sets button size
    	this.returnButton.setLocation(373, 420); //set the location
    	this.returnButton.setBackground(new Color(225, 198, 153)); //make button orange
    	this.returnButton.setToolTipText("Press to return to the game's menu"); //tool-tip for user functionality
    	this.returnButton.addActionListener(this); //takes user input
    	this.returnButton.setVisible(true);
    	this.gameFrame.add(returnButton); //add button to the frame
    	
    //questionLabel - displays the equation to the user
    	this.questionLabel.setSize(400, 50);
    	this.questionLabel.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 30)); //changes font, and size
    	this.questionLabel.setForeground(new Color(255, 255, 255)); //make font white
    	this.questionLabel.setLocation(185, 140);
    	this.questionLabel.setVisible(true);
    	this.gameFrame.add(questionLabel);	
    	
    //resultLabel - displays whether the user got the correct answer or not
    	this.resultLabel.setSize(400, 50);
    	this.resultLabel.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 25));
    	this.resultLabel.setForeground(new Color(255, 255, 255)); //make font white
    	this.resultLabel.setLocation(210, 260);
    	this.resultLabel.setVisible(false);
    	this.gameFrame.add(resultLabel);
    	
    //scoreLabel - displays the user's score
    	this.scoreLabel.setSize(400, 50);
    	this.scoreLabel.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
    	this.scoreLabel.setForeground(new Color(255, 255, 255)); //make font white
    	this.scoreLabel.setLocation(20, 410);
    	this.scoreLabel.setVisible(true);
    	this.gameFrame.add(scoreLabel);
    
    //timerLabel - displays the remaining time
    	this.timerLabel.setSize(400, 50);
    	this.timerLabel.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
    	this.timerLabel.setForeground(new Color(255, 255, 255)); //make font white
    	this.timerLabel.setLocation(20, 385);
    	this.timerLabel.setVisible(true);
    	this.gameFrame.add(timerLabel);
    	
    //userText - allows the user to enter their answer
    	this.userText.setSize(200, 50);
    	this.userText.setLocation(150, 200);
    	this.userText.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 16));
    	this.userText.setToolTipText("Enter the answer to the equation"); //tool-tip for user functionality
    	this.userText.requestFocus(); //sets the focus onto the text box so the user can play right away
    	this.userText.addActionListener(this); //utilize the user input
    	this.userText.setVisible(true);
    	this.gameFrame.add(userText);
	}
	
	
	//Catch number format exception (When user enters characters other than digits)
	public static Integer tryParse(String text) {
		  try {
		    return Integer.parseInt(text);
		  } 
		  catch (NumberFormatException e) {
		    return 0; //returns 0 as an answer when non-digits are entered
		  }
		}
	
	
	//Method to determine what happens after user input
	public void actionPerformed(ActionEvent e) {
		//Code for the game functionality
		int intAnswer = tryParse(userText.getText());
	
		if (intAnswer == MentalMathLogic.product) {	//if the user input is correct
			resultLabel.setVisible(true);
			resultLabel.setForeground(new Color(0, 255, 0)); //set font to green
			resultLabel.setText("Correct!");
			MentalMathLogic.AddScore(); //adds 20 points
			scoreLabel.setText("Score: " + MentalMathLogic.intScore); //update the user's score
		}	
		else { //if the user input is anything but correct
			resultLabel.setVisible(true);
			resultLabel.setForeground(new Color(255, 0, 0)); //set font to red
			resultLabel.setText("Wrong!");
			MentalMathLogic.RemoveScore(); //removes 5 points
			scoreLabel.setText("Score: " + MentalMathLogic.intScore); //update the user's score
		}

		//Code for when the user presses the return button
		if(e.getSource() == returnButton){
			timer.restart(); //restarts the timer to prevent error
			timer.stop(); //stops the timer
			MentalMathLogic.intScore = 0; //resets the score to prevent cheating
			gameFrame.dispose(); //close the game GUI
			MentalMathMenuGUI ReturnMenu = new MentalMathMenuGUI(); //creates the game menu again
		}
		
		//reset the text box to nothing after the user enters an answer
		userText.setText(""); 
		
		//Randomize for new numbers
		MentalMathLogic.Randomize();
		
		//Set the question label as the new equation
		questionLabel.setText(MentalMathLogic.strNum1 + " x " + MentalMathLogic.strNum2 + " = ?"); 
	}

//=====================================================================================================================================================================================================		
	
	// public static void main(String[] args) {
	// 	MentalMathGameGUI MentalMathGame = new MentalMathGameGUI(); //used to test the game
	// }

//=====================================================================================================================================================================================================	
	
	//Timer class used to count down for the game
	public class TimeClass implements ActionListener {
		int intCounter;
	
		public TimeClass(int intCounter) {
			this.intCounter = intCounter;
		}
		
		public void actionPerformed(ActionEvent e) {
			intCounter--; //subtracts 1 from the time every second
			
			if(intCounter >= 1) { //updates the timer label
				timerLabel.setText("Time Left: " + intCounter + "s");
			}
			else { //stops the timer once the time runs out
				timer.stop(); //stops the timer
				timerLabel.setText("Time Left: Finished!"); //updates the timer label
				JOptionPane.showMessageDialog(null, "Congratulations, your final score is: " + MentalMathLogic.intScore); //displays a message with the user's final score
				userText.setEnabled(false); //stops the user from typing answers
				userText.setText("    Please return to play again!"); //updates the text box - centered text
			}
		}
	}
}
