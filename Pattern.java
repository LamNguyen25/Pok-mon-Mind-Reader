/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.util.HashMap;

/**
 *
 * @author nguyenlam
 */


public class Pattern {
    /**The pattern type String stored in the map */
    private String pattern;
    /**
     * Constructor - Set the value to the pattern
     * @param p the value the pattern will be set to
     */
    public Pattern(String p){
        pattern = p;
    }
    /**
     * Retrieve the pattern
     * @return the pattern 
     */
    public String getPattern(){
        return pattern;
    }
    /**
     * Generate a random number to store pattern object
     * @return the same hash value for the same object data
     */
    @Override
    public int hashCode(){
        final int prime = 31;
        int l = 1;
        l = prime * l + pattern.hashCode();
        return l;
    }
    /**
     * Compare two objects to see of they are the same.
     * @param o object in the bucket
     * @return true if the two objects are equal, false otherwise
     * 
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Pattern){
            Pattern p = (Pattern) o;
            return pattern.equals(p.pattern);
        }
        return false;
    }
}
