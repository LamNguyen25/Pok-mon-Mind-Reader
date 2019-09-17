/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nguyenlam
 * Project #2
 * Date: 10/09/2018
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        boolean playAgain = true;
        /**The score of the user and the score of the computer */
        int userScore = 0, compScore = 0;
        /**Initialize the percentage of the user and the computer*/
        float userPercentage = 0 , compPercentage = 0;
        String userInput ="";
        Computer comp = new Computer();
        /**The choice of the computer*/
        char computerInput;
        File inputFile = new File("HashMapOutput.txt");
        // Check to see if there is a save file
        if(inputFile.exists()){
            
            System.out.println("Beginner or Veteran \n" + "Enter 'b' for Beginner and 'v' for Veteran.");
            String userChoice = CheckInput.getString();
            switch(userChoice){
                case "V":
                case "v":
                    //Check to see if the file is empty or not
                    try{
                            Scanner reader = new Scanner(inputFile);
                            if(reader.hasNext()){
                                comp.storePattern(comp.readFile(inputFile));
                            }
                            else{
                                System.out.println("File was empty");
                            }
                            reader.close();

                            }catch( FileNotFoundException fnf ) {
                            System.out.println("File was not found");
                        }
                    
                    break;
                case "B":
                case "b":
                    break;
                default :
                    System.out.println("Invalid choice");
                    break;
                   
            }
            
        }
        while(playAgain){
           
            //Get user's input 
            System.out.println("F => Fire\n"  + 
                               "W => Water\n" +
                               "G => Grass\n" +
                               "E => Exit");
            System.out.println("Please enter your letter choice: ");
            String Userchoice = CheckInput.getString();
            
            //Get computer's input (make a prediction)
            computerInput = comp.makePrediction(userInput);
           if(Userchoice.equals("F") || Userchoice.equals("f") || Userchoice.equals("W") || Userchoice.equals("w") || Userchoice.equals("G") || Userchoice.equals("g")){
               
               userInput = userInput + Userchoice.toLowerCase();
            }

           if(userInput.length() == 4){
                
                comp.storePattern(userInput);
                userInput = userInput.substring(1);
                
            }
            
            
            switch(Userchoice){
                case "F":
                case "f":
                    if(computerInput == 'F'){
                        System.out.println("YOU HAVE FIRE, I HAVE FIRE, WE TIE!");
                        System.out.println("Your score: " + userScore + "  My score: " + compScore);
                        if(userScore == 0 && compScore == 0){
                            
                            userPercentage = 0;
                            compPercentage = 0;
                        }
                        else{
                            //Calculate percentage
                            userPercentage = (float) ((userScore * 100)/(userScore + compScore));
                            compPercentage = (float) ((compScore * 100)/(userScore + compScore));
                        }
                        System.out.println("Your percentage: " + userPercentage + "%  My percentage: " + compPercentage + "%");
                    }
                    else if(computerInput == 'W'){
                        compScore++;
                        System.out.println("YOU HAVE FIRE, I HAVE WATER, YOU LOSE!");
                        System.out.println("Your score: " + userScore + "  My score: " + compScore);
                        
                        if(userScore == 0 && compScore == 0){
                            userPercentage = 0;
                            compPercentage = 0;
                        }
                        else{
                            userPercentage = (float) ((userScore * 100)/(userScore + compScore));
                            compPercentage = (float) ((compScore * 100)/(userScore + compScore));
                        }
                        System.out.println("Your percentage: " + userPercentage + "%  My percentage: " + compPercentage + "%");
                    }
                    else if(computerInput == 'G'){
                        userScore++;
                        System.out.println("YOU HAVE FIRE, I HAVE GRASS, YOU WIN!");
                        System.out.println("Your score: " + userScore + "  My score: " + compScore);
                        if(userScore == 0 && compScore == 0){
                            userPercentage = 0;
                            compPercentage = 0;
                        }
                        else{
                            userPercentage = (float) ((userScore * 100)/(userScore + compScore));
                            compPercentage = (float) ((compScore * 100)/(userScore + compScore));
                        }
                        System.out.println("Your percentage: " + userPercentage + "%  My percentage: " + compPercentage + "%");
                    }
                    break;
                case "W":
                case "w":
                    if(computerInput == 'F'){
                        userScore++;
                        System.out.println("YOU HAVE WATER, I HAVE FIRE, YOU WIN!");
                        System.out.println("Your score: " + userScore + "  My score: " + compScore);
                        if(userScore == 0 && compScore == 0){
                            userPercentage = 0;
                            compPercentage = 0;
                        }
                        else{
                            userPercentage = (float) ((userScore * 100)/(userScore + compScore));
                            compPercentage = (float) ((compScore * 100)/(userScore + compScore));
                        }
                        System.out.println("Your percentage: " + userPercentage + "%  My percentage: " + compPercentage + "%");
                    }
                    else if(computerInput == 'W'){
                        System.out.println("YOU HAVE WATER, I HAVE WATER, WE TIE!");
                        System.out.println("Your score: " + userScore + "  My score: " + compScore);
                        if(userScore == 0 && compScore == 0){
                            userPercentage = 0;
                            compPercentage = 0;
                        }
                        else{
                            userPercentage = (float) ((userScore * 100)/(userScore + compScore));
                            compPercentage = (float) ((compScore * 100)/(userScore + compScore));
                        }
                        System.out.println("Your percentage: " + userPercentage + "%  My percentage: " + compPercentage + "%");
                    }
                    else if(computerInput == 'G'){
                        compScore++;
                        System.out.println("YOU HAVE WATER, I HAVE GRASS, YOU LOSE!");
                        System.out.println("Your score: " + userScore + "  My score: " + compScore);
                        if(userScore == 0 && compScore == 0){
                            userPercentage = 0;
                            compPercentage = 0;
                        }
                        else{
                            userPercentage = (float) ((userScore * 100)/(userScore + compScore));
                            compPercentage = (float) ((compScore * 100)/(userScore + compScore));
                        }
                        System.out.println("Your percentage: " + userPercentage + "%  My percentage: " + compPercentage + "%");
                    }
                    break;
                case "G":
                case "g":
                    if(computerInput == 'F'){
                        compScore++;
                        System.out.println("YOU HAVE GRASS, I HAVE FIRE, YOU LOSE!");
                        System.out.println("Your score: " + userScore + "  My score: " + compScore);
                        if(userScore == 0 && compScore == 0){
                            userPercentage = 0;
                            compPercentage = 0;
                        }
                        else{
                            userPercentage = (float) ((userScore * 100)/(userScore + compScore));
                            compPercentage = (float) ((compScore * 100)/(userScore + compScore));
                        }
                        System.out.println("Your percentage: " + userPercentage + "%  My percentage: " + compPercentage + "%");
                    }
                    else if(computerInput == 'W'){
                        userScore++;
                        System.out.println("YOU HAVE GRASS, I HAVE WATER, YOU WIN!");
                        System.out.println("Your score: " + userScore + "  My score: " + compScore);
                        if(userScore == 0 && compScore == 0){
                            userPercentage = 0;
                            compPercentage = 0;
                        }
                        else{
                            userPercentage = (float) ((userScore * 100)/(userScore + compScore));
                            compPercentage = (float) ((compScore * 100)/(userScore + compScore));
                        }
                        System.out.println("Your percentage: " + userPercentage + "%  My percentage: " + compPercentage + "%");
                    }
                    else if(computerInput == 'G'){
                        System.out.println("YOU HAVE GRASS, I HAVE GRASS, WE TIE!");
                        System.out.println("Your score: " + userScore + "  My score: " + compScore);
                        if(userScore == 0 && compScore == 0){
                            userPercentage = 0;
                            compPercentage = 0;
                        }
                        else{
                            userPercentage = (float) ((userScore * 100)/(userScore + compScore));
                            compPercentage = (float) ((compScore * 100)/(userScore + compScore));
                        }
                        System.out.println("Your percentage: " + userPercentage + "%  My percentage: " + compPercentage + "%");
                    }
                    break;
                case "E":
                case "e":
                    System.out.println("Game over");
                    System.out.println("Your score: " + userScore + "  My score: " + compScore);
                    System.out.println("Your percentage: " + userPercentage + "%  My percentage: " + compPercentage + "%");
                    System.out.println("Do you want to write the contents of the HashMap to a text file? \n" + "Enter 'y' for yes and 'n' for No.");
                    String userChoice = CheckInput.getString();
                    switch(userChoice){
                        case "Y":
                        case "y":
                            //System.out.println("Enter the path for the output file: ");
                            
                            File outFile = new File("HashMapOutput.txt");
                            comp.saveMapToFile(outFile);
                            playAgain = false;
                            System.exit(0);
                            break;
                        case "N":
                        case "n":
                            playAgain = false;
                            System.exit(0);
                    }
                    playAgain = false;
                    System.exit(0);
                default:
                    System.out.println("You entered an invalid number");
                    continue;
            }//End of Switch statement
            
        }// end of while loop
    
    }
    
}
