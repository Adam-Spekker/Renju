/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.board;

import renju.exception.*;
import renju.board.Piece.COLOR;
/**
 *
 * @author archghoul
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;




public class RenjuBoardTest {
    
    Board board;
    
    @Before
    public void setUp() {
        board = new RenjuBoard();
    }
    
    
    
///Method: isValid()  
    @Test
    public void testIsValidStart() throws InvalidStepException, GameFinishedException {
        Assert.assertEquals(false, board.isValid(-1, 0));
        Assert.assertEquals(false, board.isValid(0, 15));
        
        Assert.assertEquals(false, board.isValid(0, 0));
        
        Assert.assertEquals(false, board.isValid(8, 8));
        
        Assert.assertEquals(true, board.isValid(7, 7));
        board.putPiece(7, 7, COLOR.BLACK);
        Assert.assertEquals(false, board.isValid(7, 7));     
        
        Assert.assertEquals(false, board.isValid(9, 9));
        Assert.assertEquals(true, board.isValid(8, 8));
        board.putPiece(8, 8, COLOR.WHITE);
        Assert.assertEquals(true, board.isValid(9, 9));
        board.putPiece(9, 9, COLOR.BLACK);
        Assert.assertEquals(true, board.isValid(0, 0));  
        
    }
    @Test
    public void testIsNotValid3x3() throws InvalidStepException, GameFinishedException {
        for (int i = 0; i<3; i++)
            board.putPiece(7+i, 7+i, Piece.COLOR.BLACK);    
        
        
        for (int i = 0; i < 2; i++) {
            board.putPiece(3, 4+i, Piece.COLOR.BLACK);            
        } 
        
        for (int i = 0; i < 2; i++) {
            board.putPiece(4+i, 3, Piece.COLOR.BLACK);            
        }
        
        board.currentPlayer = COLOR.BLACK;
        
       Assert.assertEquals(false, board.isValid(3, 3)); 
        
        
    }
    
    @Test 
    public void testIsValid3x3() throws InvalidStepException, GameFinishedException {
        for (int i = 0; i<3; i++)
            board.putPiece(7+i, 7+i, Piece.COLOR.BLACK);    
        
        
        for (int i = 0; i < 2; i++) {
            board.putPiece(3, 4+i, Piece.COLOR.BLACK);            
        } 
        board.putPiece(3, 6, Piece.COLOR.WHITE);
        
        for (int i = 0; i < 2; i++) {
            board.putPiece(4+i, 3, Piece.COLOR.BLACK);            
        }
        board.putPiece(6, 3, Piece.COLOR.WHITE);
        
        board.currentPlayer = COLOR.BLACK;
        
      
        Assert.assertEquals(true, board.isValid(3, 3));  
        
        
    }
    
    @Test 
    public void testIsValid4x4() throws InvalidStepException, GameFinishedException {
        for (int i = 0; i<3; i++)
            board.putPiece(7+i, 7+i, Piece.COLOR.BLACK);    
        
        for (int i = 0; i < 3; i++) {
            board.putPiece(3, 4+i, Piece.COLOR.BLACK);            
        } 
        
        for (int i = 0; i < 3; i++) {
            board.putPiece(4+i, 3, Piece.COLOR.BLACK);            
        }
        
        board.currentPlayer = COLOR.BLACK;
        
        Assert.assertEquals(false, board.isValid(3, 3));
        
        
    }


}