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
    
    
    public RenjuBoard() {
        super();     
        forks  = new Fork[9];
        ///[0][1][2]
        ///[3][4][5] -> [4] wont be used
        ///[6][7][8]
        
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
           ///collect forks
           int k=0;
           for (int i = -1; i<=1; i++){
               for (int j = -1; j<=1; j++){
                   forks[k] = checkDirection(x, y, i, j);
                   
                   k++;
               }               
           }
           
           
           for (int i = 0; i<3; i++) {
               System.out.println(""+forks[i*3].toString()+ 
                                    forks[i*3+1].toString()+
                                    forks[i*3+2].toString());
           }
           System.out.println(""); 
           
           
            
            
           ///Check possible double 3x3 forks
           int txt = 0;
           for(int i=0; i<4; i++){
               if ((forks[i].len + forks[8-i].len >= 2) && forks[i].isOpen && forks[8-i].isOpen) {
                   txt++;
                }
            }
           if (txt >= 2) {
               return false;
           }
               
               
               
               
           ///Check possible double 4x4 forks
           int fxf = 0;
           for(int i=0; i<4; i++){
               if ((forks[i].len + forks[8-i].len >= 3) ) {
                   fxf++;
                }
            }
           if (fxf >= 2) {
               return false;
           }
           
           int ol = 0;
           for(int i=0; i<4; i++){
               if ((forks[i].len + forks[8-i].len >= 5) ) {
                   return false;
                }
            }
          
           ///Check possible overlines
           ///check vertical
           ///check horizontal
           ///check diagonal (x)
           ///check diagonal (-x)
           
           
           
           
            
        }
        
        
        return true;
    }
    
    Fork checkDirection(int x, int y, int v, int h) {
           int cnt = 0;    
           int end = 0;
           boolean open = true;
           
           
           for (int i = 1; i < 5; i++) {
               if(x+i*v < 0 || y+i*h < 0 || x+i*v > 14 || y+i*h > 14) {
                    end=i;
                    if(end == 1) {open=false;}
                    if(board[x+(i-1)*v][y+(i-1)*h].getColor() != COLOR.EMPTY) {
                        open=false; 
                    } 
                   break;
               } else {
                   if (board[x+i*v][y+i*h].getColor() == currentPlayer) {
                       cnt++;
                   } else {
                        if(board[x+i*v][y+i*h].getColor() == COLOR.EMPTY) {
                            if(board[x+(i-1)*v][y+(i-1)*h].getColor() == COLOR.EMPTY) {
                                break;
                            }                                         
                        } else {
                                end = i; 
                                if(end == 1) {open=false;}
                                if(board[x+(i-1)*v][y+(i-1)*h].getColor() != COLOR.EMPTY) {
                                    open=false; 
                                }
                                break;
                            }   
//                      }      
                   }
                }
           }
           
           
           
           
           
           return new Fork(cnt,end,open);
    }
    
    
    
}
