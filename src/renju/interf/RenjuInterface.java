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
public interface RenjuInterface {
    
    public void setBoard(Board b);
    
    public Board getBoard();
    
    public void update();
    
}
