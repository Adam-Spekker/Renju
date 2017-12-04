/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.interf;

import java.util.Random;

import renju.board.Board;
import renju.board.Piece.COLOR;
import renju.exception.BoardException;

/**
 *
 * @author archghoul
 */
public class RenjuAI implements RenjuInterface{
    Board gameBoard;
    COLOR color;
    
    @Override
    public void setBoard(Board b) {
        gameBoard = b;
      //  gameBoard.setInterface(this);
    }
    
    @Override
    public void setColor(COLOR c) {
        color = c;
    }
    
    @Override
    public COLOR getColor() {
        return color;
    }
    
    @Override
    public Board getBoard() {
        return gameBoard;
    }
 
    @Override
    public boolean getIsNotified() {
        return true;
    }
    
    @Override
    public void update() {
        if (gameBoard.getCurrentPlayer() == color){
                    //System.out.println("white turn");
                    if(gameBoard.getWinner() == COLOR.EMPTY){
                    //this.moveSystematic(gameBoard.getLastField().getX(),gameBoard.getLastField().getY());
                       if(gameBoard.getLastField() != null){
                            this.moveRandom(gameBoard.getLastField().getX(),gameBoard.getLastField().getY());     
                       }else {
                           try {
                                gameBoard.putPiece(7, 7, color);                              
                            } catch (BoardException e){
                                
                            }
                       }
                    }// System.out.println("white moved");
                }
        
    }
    
    void moveSystematic(int x, int y) {      
        for (int i = 1; i < 6; i++) {
            for (int j = (-1)*i; j <= i; j++) {
                for (int k = (-1)*i; k <= i; k++) {
                    int xc = x+j;
                    int yc = y+k;
                    if ((xc<= 15) && (xc>=0) && (yc>=0) && (yc<=15)) {
                        try {
                            gameBoard.putPiece(xc, yc, color);                              
                        } catch (BoardException e){
                            continue;
                        }
                        return;
                    }
                }               
            }
        }
    }
    
    void moveRandom(int x, int y) {
        
        Random rand = new Random();
        int radius = 1;
        
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(RenjuAI.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        while (true) {       
            
            int xc = x+(rand.nextInt(1+2*radius)-radius);
            int yc = y+(rand.nextInt(1+2*radius)-radius);
            
            if (rand.nextInt(10) < 1)
                radius++;
            
            if ((xc<= 15) && (xc>=0) && (yc>=0) && (yc<=15)) {
                try {
                    gameBoard.putPiece(xc, yc, color);                              
                } catch (BoardException e){
                    continue;
                }
                return;
            }
        }
        
    }

    @Override
    public void run() {
        //int i = 1;
        while (true){
            try {
                    
                    Thread.sleep(1000);
                    update();
                   // System.out.println(""+i +" "+ gameBoard.getCurrentPlayer() );
                } catch (InterruptedException ex) {
                   System.out.println("interruptexception");
                }
 
              //  i++;
                //System.out.println(gameBoard.getCurrentPlayer());

                

            
        }
    }

   

    
    
}
