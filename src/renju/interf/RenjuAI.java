/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.interf;

import renju.board.Board;

/**
 *
 * @author archghoul
 */
public class RenjuAI implements RenjuInterface{
    Board renjuBoard = new Board();
    
    @Override
    public void setBoard(Board b) {
        renjuBoard = b;
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    @Override
    public void update() {
        
    }
    
}
