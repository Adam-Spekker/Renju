/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.board;


import java.util.ArrayList;
import renju.exception.*;
import renju.interf.RenjuInterface;
import renju.board.Piece.*;

/**
 *
 * @author archghoul
 */
public class Board implements java.io.Serializable{
       Field[][] board;
       COLOR currentPlayer;
       int currentTurn;
       Field lastField;
       boolean isFinished;
       static ArrayList<RenjuInterface> interfaceList;
      
       
       COLOR winner;
       
       public Board()  {
    
          board = new Field[15][15];
          isFinished = false;
          winner = COLOR.EMPTY;
          
          for (int i = 0; i < 15 ; i++)
              for (int j = 0; j < 15 ; j++)
                  board[i][j] = new Field(i,j);
          
          currentPlayer = COLOR.BLACK;
          Piece.resetCnt();
          currentTurn = 1;
          interfaceList = new ArrayList<>();     
          
       } 
     
       public void updateInterface(){
           for(RenjuInterface iter : interfaceList) {
               iter.setBoard(this);
           }
       }
       
       public void setInterface(RenjuInterface i) { 
           i.setBoard(this);
           interfaceList.add(i);
       }
       
       public void swapInterfaceColor(){
           for(RenjuInterface iter : interfaceList) {
               iter.setColor(iter.getColor() == COLOR.BLACK ? COLOR.WHITE : COLOR.BLACK);
           }           
       }
       
       
       
      public void reset() {
            isFinished = false;
            currentPlayer = COLOR.BLACK;
            Piece.resetCnt();
            currentTurn = 1;
            
            winner = COLOR.EMPTY;
           
           for (int i = 0; i < 15 ; i++)
              for (int j = 0; j < 15 ; j++)
                  board[i][j].deletePiece();
           
           lastField = null;
           
           
           
       }
       
       
       int countFork(int x, int y, int v, int h, int max) {
           int cnt = 0; 
           for (int i = 1; i < max+1 ; i++) {
               if(x-i*v < 0 || y-i*h < 0 || x-i*v > 14 || y-i*h > 14)
                   break;
               else 
                   if (board[x-i*v][y-i*h].getColor() == lastField.getColor()) {
                       cnt++;
                   } else break;    
           }
           return cnt;
       }
       
        private boolean checkLine(int x, int y, int v, int h, int max) {
           // vertical check
           int before;
           int after;
//           for (int i = 1; i < max+1 ; i++) {
//               if(x-i*v < 0 || y-i*h < 0 || x-i*v > 14 || y-i*h > 14)
//                   break;
//               else 
//                   if (board[x-i*v][y-i*h].getColor() == lastField.getColor()) {
//                       before++;
//                   } else break;    
//           }
//           for (int i = 1; i < max ; i++) {
//               if(x+i*v < 0 || y+i*h < 0 || x+i*v > 14 || y+i*h > 14)
//                   break;
//               else 
//                   if (board[x+i*v][y+i*h].getColor() == lastField.getColor()) {
//                       after++;
//                   } else break;
//                   
//           } 
            before = countFork(x,y,v,h,max);
            after = countFork(x,y,-v,-h,max);

           return before+after >= max; //System.out.println("Nyertes:" + lastField.getColor());
           
           
       }
       
       public COLOR checkBoard() {
           if (lastField == null)
               return COLOR.EMPTY;
           
           int x = lastField.getX();
           int y = lastField.getY();
           
           if (checkLine(x,y,1,0,4) || checkLine(x,y,0,1,4) || checkLine(x,y,1,1,4) || checkLine(x,y,-1,1,4)) {   
               isFinished = true;               
               return lastField.getColor();
           } else {
               return COLOR.EMPTY;
           }
           
           
       }
       
       public void printField(int x, int y) {
           if (x>14 || x<0 || y>14 || y<0){
               ////hiba out of bound
           }else{                
                System.out.println("FIELD[" + x + ", " + y + "]: " + board[x][y].toString()); 
           }
       
       }
       
       public int getFieldSerial(int x, int y){
          if(x < 0 || y < 0 || x > 14 || y > 14){
              return 0;
          }else {
                Piece p = board[x][y].getPiece();
                if(p == null) {
                    return 0;
                } else {
                    return p.getSerial();
                }
          } 
          
       }
       
       public Field getField(int x, int y){
          return board[x][y];
          
       }
       
       public boolean isValid(int x, int y) {
           return !(x < 0 || y < 0 || x > 14 || y > 14 || board[x][y].getPiece() != null);      
           
       }
       
      
       
       synchronized public void putPiece(int x, int y, COLOR c) throws InvalidStepException, GameFinishedException {
            if (!isFinished) {
                if (isValid(x,y)) {
                    board[x][y].addPiece(new Piece(c));
                    lastField = board[x][y];
                    currentTurn++; 
                    currentPlayer = currentPlayer == COLOR.BLACK ? COLOR.WHITE : COLOR.BLACK;
                      
                    winner = checkBoard();
                } else {
                  
                    throw new InvalidStepException();
                }
              
           } else {
             
               throw new GameFinishedException();
           }
           
       }
       
       
       
       public COLOR[][] getBoardColor() {
            COLOR[][] out = new COLOR[15][15];
            for(int i = 0; i <15; i++){
                for(int j = 0; j < 15; j++){
                        out[i][j] = board[i][j].getColor();
                    } 
                }
            
            return out;
       }
       
       public void printBoardColor() { 
           COLOR[][] temp = getBoardColor();
           System.out.println("Board in " + currentTurn + ". turn:" );
           for (int i = 0; i < 15; i++ ) {
               for (int j = 0; j < 15; j++) {
                   System.out.print(temp[i][j].toChar());
               }
               System.out.println();
           }
        }
       
       public COLOR getCurrentPlayer(){
           return currentPlayer;
       }
       
       public int getCurrentTurn() {
           return currentTurn;
       }
       
       public Field getLastField() {
           return lastField;
       }
       
       public COLOR getWinner() {
           return winner;
       }
       
       
       
       
}
