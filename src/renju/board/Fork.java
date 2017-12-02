/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.board;

/**
 *
 * @author archghoul
 */
 public class Fork {
        int len;
        boolean isOpen;
        int firstDiffColor;
        
        int getLen() {return len;}        
        boolean getIsOpen() {return isOpen;}
        int getFirstDiff() {return firstDiffColor;}
        
        Fork (int a, int b, boolean c) {
            len = a;
            firstDiffColor = b;
            isOpen =c;
        }
        
        @Override
        public String toString() {
            return "["+ len + firstDiffColor + (isOpen ? "o":"c") +"]";
        }
        
    }