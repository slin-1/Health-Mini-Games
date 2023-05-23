//Created by Steven Lin
//Spacial Awareness Game - code for level functionality

import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpacialAwarenessLevel implements ActionListener {

	//swing components
	JFrame frame;
	JPanel panel;
	JButton[][]buttons;
	
	//boolean array to record which elements of JButton[][]buttons are coloured in.
	boolean[][]coloured;
	//byte keeping track of how many correct guesses the user has had this level
	byte correct;
	
	final byte NUMBER_OF_COLOURED = 5;

		
	//constructor for SpacialAwarenessLevel object. Takes a byte as parameter representing the size of the JButton array
	public SpacialAwarenessLevel(byte dimensions) {
	
		//initializing swing components
		this.correct = 0;
		this.frame = new JFrame("Spacial Awareness Test");
		this.panel = new JPanel();
		this.buttons = new JButton[dimensions][dimensions];
		this.coloured = new boolean[dimensions][dimensions];
		this.frame.setLocationRelativeTo(null);
		
		selectColoured();	
		createGUI();
		
	}
	
	public void startLevel() {
		
		//reveals the JFrame containing the level
 		this.frame.setVisible(true);
		this.frame.setLocationRelativeTo(null);
		//gives the player 1000 milliseconds to see the coloured-in elements before timer task is run, hiding them
		new Timer().schedule(task, 1000);
	}
	
	//sets up swing components 
	public void createGUI() {
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		this.frame.setSize(516,539);

 		this.panel.setLayout(null);
 		this.frame.add(this.panel);
		
		for(byte i = 0; i < this.buttons.length; i++) {
			for(byte j = 0; j < this.buttons.length; j++) {
			
				this.buttons[i][j] = new JButton();
				//the location and size of each button is automatically determined by the size of the array
				this.buttons[i][j].setSize(500/this.buttons.length,500/this.buttons.length);
				this.buttons[i][j].setLocation(500/this.buttons.length*i,500/this.buttons.length*j);
			
				//Sets JButton to black if the index corresponds to a true in the boolean array 'coloured', as determined by the method selectColoured()
				if(this.coloured[i][j])
					this.buttons[i][j].setBackground(Color.BLACK);
				else
					this.buttons[i][j].setBackground(Color.WHITE);

				this.panel.add(this.buttons[i][j]);				
			}
		}			
	}
	
	public void selectColoured() {
		
		//Randomly selects 5 indexes from the JButton array to be coloured in. 
		for(int n = 1; n <= NUMBER_OF_COLOURED; n++) {
			
			byte i, j;		
			//do while loop to ensure there are no duplicates
			do{			
				i = (byte)(Math.random()*this.buttons.length);
				j = (byte)(Math.random()*this.buttons.length);
				
			}while(this.coloured[i][j]);
			
			//Records which JButtons are to be coloured as an array of booleans. 
			//This record will remain, even after the JButtons' colours have been hidden
			this.coloured[i][j] = true;
		}		
	}
	
	TimerTask task = new TimerTask() {
		public void run() {		
			hide();
		}	
	};
	
	public void hide() {
		
		//hides coloured-in elements of the array by iterating through each index and setting it to white
		//the buttons are also linked to ActionListener now so that the player could not click them before they've disappeared
		for(int i = 0; i < this.buttons.length; i++) {
			for(int j = 0; j < this.buttons.length; j++) {
						
				this.buttons[i][j].setBackground(Color.WHITE);
				this.buttons[i][j].addActionListener(this);
				//The buttons' action command is set to their index in the form "ij" so that their index is easily retrievable 
				buttons[i][j].setActionCommand(i + "" + j);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
	
		//gets the clicked element's index via its ActionCommand
		byte i = (byte)(e.getActionCommand().charAt(0)-48);
		byte j = (byte)(e.getActionCommand().charAt(1)-48);
		
		//disables the button once its been clicked, not allowing the user to click multiple times 
		this.buttons[i][j].setEnabled(false);

		//if the element was correct, the cell is set to green and byte correct increases by one
		if(this.coloured[i][j]) {
			this.correct++;
			this.buttons[i][j].setBackground(new Color(149,184,92));
		}
		//if the element was incorrect, the cell is set to red and short incorrect from the SpacialAwarenessPlay class increases by one
		else {
			SpacialAwarenessPlay.incorrect++;
			this.buttons[i][j].setBackground(new Color(255,105,97));	
		}		
		
		//if the number of correct guesses equals the number of correct cells, the level concludes by disposing of its frame and moving on to the next level
		//this win condition is why buttons must be disabled as soon as they are clicked to prevent the player from getting multiple correct guesses for the same button
		if(correct == NUMBER_OF_COLOURED) {
			this.frame.dispose();
			SpacialAwarenessPlay.nextLevel();
		}		
	}
}
