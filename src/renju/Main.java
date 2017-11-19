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
        Board board = new  RenjuBoard();
        
      
        
//       for (int i = 0;  i<6; i++){
//            board.putPiece(p, 2, 3+i);
//            board.putPiece(p2, 3+i, 2);
//            board.printBoardColor();
//            board.checkBoard();
//       }
       
       RenjuInterface ui = new RenjuUI();
      // RenjuInterface ui2 = new RenjuUI();
       //RenjuInterface ai = new RenjuAI();
       ui.setBoard(board);
      // ui2.setBoard(board);
      // ai.setBoard(board);
       
       
       
        
       // board.checkBoard();
    }
    
}
