//Created by Steven Lin
//Mental Health Game - code for logic

import java.util.concurrent.ThreadLocalRandom;

public class MentalMathLogic {
//Variables
	public static int randomNum1;
	public static int randomNum2;
	public static int product;
	public static String strNum1 = "";
	public static String strNum2 = "";
	public static int intScore = 0;
	
//=====================================================================================================================================================================================================		
	
//Method to generate random numbers for the game
	public static void Randomize() {
		//generate random numbers between (1-12)
		randomNum1 = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		randomNum2 = ThreadLocalRandom.current().nextInt(1, 12 + 1);
		
		//convert the random numbers to string form
		strNum1 = Integer.toString(randomNum1);
		strNum2 = Integer.toString(randomNum2);
		
		//hold the product of the two random numbers
		product = (randomNum1 * randomNum2);
	}
	
	
//Method to add to the score when the user gets the correct answer
	public static void AddScore() {
		intScore += 20;
	}
	
	
//Method to remove score when the user gets the wrong answer
	public static void RemoveScore() {
		intScore -= 5;
	}
	
//=====================================================================================================================================================================================================		
	
//Main method to test methods
	public static void main(String[] args) {
		//test randomizer
		Randomize();
		System.out.println(randomNum1);
		System.out.println(randomNum2);
		System.out.println("Product: " + product);
		
		//test score system
		AddScore();
		System.out.println("Add Score Test: " + intScore);
		RemoveScore();
		System.out.println("Remove Score Test: " + intScore);
	}
}
