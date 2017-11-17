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


public class Piece {

   
    public enum COLOR { 
        EMPTY('+'), 
        BLACK('B'), 
        WHITE('W');
        
        private final char note;
        
        COLOR(char note){
            this.note = note;
        }
        public char toChar(){
            return note;
        }
        
        @Override
        public String toString() {
            return note == '+' ? "Empty" : (note == 'B' ? "Black" : "White");
        }
    
    }
    
    static Integer count=1;
    COLOR color;
    Integer serial;
    
    Piece() {
        serial = count;
        if (serial % 2 == 0) {
            color = COLOR.WHITE;
        } else {
            color = COLOR.BLACK;
        }
        count++;
    }
    
    ///only for debug
    Piece(COLOR c) {
        serial = count; 
        color = c;
    }
    
    @Override
    public String toString() { 
        return serial.toString() + " " + color.toString();
    }
    
    COLOR getColor() {
        return color;
    }
    
    int getSerial() { 
        return serial;
    }
    
    static void resetCnt() {
        count = 1;
    }
    
}
