/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.interf;

import renju.board.Board;
import renju.board.Piece.COLOR;



/**
 *
 * @author archghoul
 */
public interface RenjuInterface extends Runnable{
    
    public void setColor(COLOR c);
    
    public void setBoard(Board b);
    
    public void update();
    
    public Board getBoard();
       
    public COLOR getColor();
    
}
