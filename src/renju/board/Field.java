/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.board;

import renju.board.Piece.*;


/**
 *
 * @author archghoul
 */
public class Field implements java.io.Serializable{
    int x,y;
    Piece p;
 
    
    public Field(int x, int y) { 
        this.x = x;
        this.y= y;
        p = null;
        
    }
    
    void deletePiece() {
        this.p = null;
    }
    
    void addPiece(Piece p){
        this.p = p;
    }
       
    COLOR getColor() {
        return (p==null) ? COLOR.EMPTY : p.getColor();
    }
    
    public int getX() {
        return x;
    }
    
    Piece getPiece() {
        return p;
    }
  
    public int getY() {
        return y;
    }
    
    @Override
    public String toString() { 
        return (p==null) ? "+": p.toString();
    }
}
