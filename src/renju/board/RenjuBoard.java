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
    
    Fork[] forks;
    
    public class Fork {
        int len;
        boolean isOpen;
        
        int getLen() {return len;}
        
        boolean getIsOpen() {return isOpen;}
        
        Fork (int a, boolean b) {
            len = a;
            isOpen = b;
        }
    }
    
    
    public RenjuBoard() {
        super();     
        forks  = new Fork[4];
    }
    
    @Override
    public boolean isValid(int x, int y) {
        
        ///Empty?
        if(x < 0 || y < 0 || x > 14 || y > 14 || board[x][y].getPiece() != null)
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
           
//           for (int i = -1; i<2; i++) {
//               for (int j = -1; j<2; j++) {
//                   if(i==0 && j==0)
//                       continue;
//                    if (countFork(x,y,i,j,2) >= 2)
//                        cnt++;
//               }
//           }
           
           
            
        }
        
        
        return true;
    }
    
    int checkForkType(int x, int y, int v, int h) {
           int cnt = 0;    
           int end = 0;
           
           for (int i = 1; i < 6; i++) {
               if(x-i*v < 0 || y-i*h < 0 || x-i*v > 14 || y-i*h > 14)
                   break;
               else 
                   if (board[x-i*v][y-i*h].getColor() == lastField.getColor()) {
                       cnt++;
                   } else if(board[x-i*v][y-i*h].getColor() == null) {
                   } else {
                       end = i;
                       break;
                   }   
               if (cnt > 2){ }break;
           }
           
           
           
           
           
           return cnt;
    }
    
    
    
}
