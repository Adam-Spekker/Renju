/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju;

import renju.interf.*;
import renju.board.*;



/**
 *
 * @author archghoul
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new  Board();
        
      
        
//       for (int i = 0;  i<6; i++){
//            board.putPiece(p, 2, 3+i);
//            board.putPiece(p2, 3+i, 2);
//            board.printBoardColor();
//            board.checkBoard();
//       }
       
       RenjuInterface ui = new RenjuUI();      
       ui.setBoard(board);
       ui.setColor(Piece.COLOR.BLACK);
       
       
//       RenjuInterface ui2 = new RenjuUI();
//       ui2.setBoard(board);
//       ui2.setColor(Piece.COLOR.WHITE);
     
       RenjuInterface ai = new RenjuAI();
       ai.setBoard(board);
       ai.setColor(Piece.COLOR.WHITE);
        
       // board.checkBoard();
    }
    
}
