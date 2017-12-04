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
       
       RenjuUI ui = new RenjuUI();      
       board.setInterface(ui);
       ui.setColor(Piece.COLOR.BLACK);
       
       
//       RenjuInterface ui2= new RenjuUI();
//       board.setInterface(ui2);
//       ui2.setColor(Piece.COLOR.EMPTY);
//    
       RenjuAI ai = new RenjuAI();
       board.setInterface(ai);
       ai.setColor(Piece.COLOR.WHITE);
       
      // System.out.println(board.toString());
       new Thread(ui).start();
       //new Thread(ui2).start();
       new Thread(ai).start();
       
       board = null;
//       Thread a;
//        a = new Thread(ai);
//        a.start();
        
//        while (true){
//            System.out.println("threadalive :" + a.isAlive());
//            Thread.sleep(1000);
//        }
       
        
       // board.checkBoard();
       
    }
    
}
