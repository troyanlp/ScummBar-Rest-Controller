package com.juanmlopez.webapp.domain;

import org.junit.Test;

import com.manuelmb.scummbar.domain.Board;

import static org.junit.Assert.assertNotNull;

public class BoardTest {

    @Test
    public void testNewBoard() {
        Board board = new Board("board");

        assertNotNull(board);
        assertNotNull(board.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testNewBoardFailsOnNullParam() {
        new Board(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewBoardFailsOnEmptyParam() {
        new Board("");
    }

}
