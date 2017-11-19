/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.board;

import renju.board.Piece.COLOR;



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
        
        ///Empty?
        if (board[x][y].getPiece() != null)
            return false;
        
        
        ///First moves check
        if (currentTurn < 4)
            switch (currentTurn) {
                case 1: return (x==7 && y==7);                      
                case 2: return (x<=8 && x>=6 && y<=8 && y>=6);
                case 3: return (x<=9 && x>=5 && y<=9 && y>=5);               
            }
        
        ///Black moves restrictions
        if (currentPlayer == COLOR.BLACK) {
           ///Check possible double 3x3 forks
           int cnt =0;
           
           for (int i = -1; i<2; i++) {
               for (int j = -1; j<2; j++) {
                   if(i==0 && j==0)
                       continue;
                    if (countFork(x,y,i,j,2) >= 2)
                        cnt++;
               }
           }
           
           
            
        }
        
        
        return true;
    }
    
    
    
}
