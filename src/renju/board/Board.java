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
public class Board {
       Field[][] board;
       COLOR currentPlayer;
       int currentTurn;
       Field lastField;
       boolean isFinished;
       ArrayList<RenjuInterface> interfaceList; 
       
       public Board() {
          board = new Field[15][15];
          isFinished = false;
          
          for (int i = 0; i < 15 ; i++)
              for (int j = 0; j < 15 ; j++)
                  board[i][j] = new Field(i,j);
          
          
          
          currentPlayer = COLOR.BLACK;
          Piece.resetCnt();
          currentTurn = 1;
          interfaceList = new ArrayList<>();
          
       } 
       
       public void setInterface(RenjuInterface i) {
               
           interfaceList.add(i);           
       }
       
       private void update() {
           for (RenjuInterface iter : interfaceList) {
               iter.update();
           }
        }
       
       public void reset() {
            isFinished = false;
            currentPlayer = COLOR.BLACK;
            Piece.resetCnt();
            currentTurn = 1;
           
           for (int i = 0; i < 15 ; i++)
              for (int j = 0; j < 15 ; j++)
                  board[i][j].deletePiece();
           
           lastField = null;
           this.update();
           
       }
       
       private boolean checkLine(int x, int y, int v, int h) {
           // vertical check
           int before = 0;
           int after = 0;
           for (int i = 1; i < 5 ; i++) {
               if(x-i*v < 0 || y-i*h < 0 || x-i*v > 14 || y-i*h > 14)
                   break;
               else 
                   if (board[x-i*v][y-i*h].getColor() == lastField.getColor()) {
                       before++;
                   } else break;    
           }
           for (int i = 1; i < 5 ; i++) {
               if(x+i*v < 0 || y+i*h < 0 || x+i*v > 14 || y+i*h > 14)
                   break;
               else 
                   if (board[x+i*v][y+i*h].getColor() == lastField.getColor()) {
                       after++;
                   } else break;
                   
           } 
           return before+after >= 4; //System.out.println("Nyertes:" + lastField.getColor());
           
           
       }
       
       public COLOR checkBoard() {
           
           int x = lastField.getX();
           int y = lastField.getY();
           
           if (checkLine(x,y,1,0) || checkLine(x,y,0,1) || checkLine(x,y,1,1) || checkLine(x,y,-1,1)) {   
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
       
       public boolean isValid(int x, int y) {
           return board[x][y].getPiece() == null; 
           
       }
       
      
       
       public void putPiece(int x, int y) throws InvalidStepException, GameFinishedException {
            if (!isFinished) {
                if (isValid(x,y)) {
                    board[x][y].addPiece(new Piece());
                    lastField=board[x][y];
                    this.update();
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
       
       
       
}
