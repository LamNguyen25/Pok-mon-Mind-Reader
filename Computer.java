/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author nguyenlam
 * Computer class - make a prediction of what patterns that users are probably using to defeat them
 */
public class Computer {
    /*Create a HashMap */
    private HashMap<Pattern, Integer> patterns = new HashMap<>();
    /**
     * constructor
     */
    public Computer(){
        
    }
    /**
     * Make a prediction based on the patterns (built from the previous three choices)
     * Create three new patterns with the substring, then add 'w','f',or 'g' to each of them
     * Check to see if the new patterns are in the HashMap or not
     * @param p a pattern contains three letter inputs
     * @return a prediction (a letter)
     */
    public char makePrediction(String p){
        /*Create list of random choice*/
        final String alphabet = "FWG";
        /*Get the string random list 's length*/
        final int n = alphabet.length();
        Random r = new Random();
        //create new patterns by adding one letter for each pattern
        Pattern pt1 = new Pattern(p + "f");
        Pattern pt2 = new Pattern(p + "w");
        Pattern pt3 = new Pattern(p + "g");
        char prediction = ' ';
        if(patterns.isEmpty()){
            prediction = alphabet.charAt(r.nextInt(n));
        }
        else if(patterns.containsKey(pt1) && patterns.containsKey(pt2) && patterns.containsKey(pt3) ){
            if(patterns.get(pt1) > patterns.get(pt2) && patterns.get(pt1) > patterns.get(pt3)){
                prediction = 'W';
            }
            else if(patterns.get(pt2) > patterns.get(pt1) && patterns.get(pt2) > patterns.get(pt3)){
                prediction = 'G';
            }
            else if(patterns.get(pt3) > patterns.get(pt1) && patterns.get(pt3) > patterns.get(pt2)){
                prediction = 'F';
            }
            else if(patterns.get(pt1) == patterns.get(pt2) && patterns.get(pt1) > patterns.get(pt3) ){
                prediction = r.nextBoolean() ? 'W' : 'G';
            }
            else if(patterns.get(pt1) == patterns.get(pt3) && patterns.get(pt1) > patterns.get(pt2) ){
                prediction = r.nextBoolean() ? 'W' : 'F';
            }
            else if(patterns.get(pt2) == patterns.get(pt3) && patterns.get(pt2) > patterns.get(pt1) ){
                prediction = r.nextBoolean() ? 'G' : 'F';
            }
            else{
                prediction = alphabet.charAt(r.nextInt(n));
            }
            

        }
        else if(patterns.containsKey(pt1) && patterns.containsKey(pt2)){
            if(patterns.get(pt1) > patterns.get(pt2)){
                prediction = 'W';
            }
            else{
                prediction = 'G';
            }
        }
        else if(patterns.containsKey(pt1) && patterns.containsKey(pt3)){
            if(patterns.get(pt1) > patterns.get(pt3)){
                prediction = 'W';
            }
            else{
                prediction = 'F';
            }
        }
        else if(patterns.containsKey(pt2) && patterns.containsKey(pt3)){
            if(patterns.get(pt2) > patterns.get(pt3)){
                prediction = 'G';
            }
            else{
                prediction = 'F';
            }
        }
        else if(patterns.containsKey(pt1)){
            prediction = 'W';
        }
        else if(patterns.containsKey(pt2)){
            prediction = 'G';
        }
        else if(patterns.containsKey(pt3)){
            prediction = 'F';
        }
        else{
            prediction = alphabet.charAt(r.nextInt(n));
        }
        return prediction;
            
    }
    /**
     * Store a new string pattern to HashMap if it is not equal to any other objects in the bucket
     * Increase its value if it is already in the hashMap
     * @param p - a pattern with four letter inputs
     */
    public void storePattern(String p){
        Pattern pt = new Pattern(p);
        
        if(patterns.isEmpty() ){
            patterns.put(pt, 1);
        }
        else{
            if(patterns.containsKey(pt)){
                int counter = patterns.get(pt);
                patterns.put(pt, counter + 1);
            
            }
            else{
           
                patterns.put(pt, 1);
            }
               
        }
        
    }
    /**
     * Write HashMap contents to file
     * @param f the name of output file
     */
    public void saveMapToFile(File f){
        try{
            PrintWriter writer = new PrintWriter(f);
            Set<Map.Entry<Pattern, Integer>> patternsKey = patterns.entrySet();
            for(Map.Entry<Pattern, Integer> patKey : patternsKey){
                writer.println(patKey.getKey().getPattern() + "  " +patKey.getValue());
            }
            writer.close();
        }catch(FileNotFoundException fnf ) {
                System.out.println("File was not found");
            
        }
        
        
    }
    /**
     * Read the save file and return the most common pattern in the map
     * @param f - input file
     * @return the most common pattern in the map
     */
    public String readFile(File f){
        String returnPattern = "";
        try{
            
            Scanner reader = new Scanner(f);
            
            while (reader.hasNext()){
                String line = reader.nextLine();
                String[] pattern = line.split("  ");
                Pattern filePattern = new Pattern(pattern[0]);
                patterns.put(filePattern, Integer.parseInt(pattern[1]));
            }
            
            Set<Map.Entry<Pattern, Integer>> patternsKey = patterns.entrySet();
            for(Map.Entry<Pattern, Integer> patKey : patternsKey){
            int max = -1;
            
            if(max < patKey.getValue()){
                max = patKey.getValue();
                returnPattern = patKey.getKey().getPattern();
            }
            
        }
            
            reader.close();
        }catch( FileNotFoundException fnf ) {
            System.out.println("File was not found");
        }
        return returnPattern;
    }
}
