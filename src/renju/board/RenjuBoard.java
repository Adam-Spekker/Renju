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
public class RenjuBoard extends Board{
    
    public RenjuBoard() {
        super();        
    }
    
    @Override
    public boolean isValid(int x, int y) {
        return board[x][y].getPiece() == null;
    }
    
}
