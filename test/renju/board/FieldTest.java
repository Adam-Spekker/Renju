/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renju.board;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import renju.board.Piece.COLOR;

/**
 *
 * @author archghoul
 */
public class FieldTest {
    
    Field field;
    
    @Before
    public void setUp() {
        field = new Field(0,0);
    }
    
    @Test 
    public void testGetFieldColor() {
        Assert.assertEquals(COLOR.EMPTY, field.getColor());
        field.addPiece(new Piece(COLOR.BLACK));
        Assert.assertEquals(COLOR.BLACK, field.getColor());
        
        
        
    }
    
}
