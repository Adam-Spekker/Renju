/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.exception;



/**
 *
 * @author archghoul
 */

public class InvalidStepException extends BoardException {
    public String str;
    
    public InvalidStepException() {
        
    }
        
    
    public InvalidStepException(String str) {
        this.str=str;
    }
    
}

