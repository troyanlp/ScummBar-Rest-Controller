package com.juanmlopez.webapp.dao.hibernate;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.manuelmb.scummbar.WebApp;
import com.manuelmb.scummbar.dao.BoardRepository;
import com.manuelmb.scummbar.domain.Board;
import com.manuelmb.scummbar.exception.DaoException;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApp.class)
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @After
    public void tearDown() throws DaoException {
        boardRepository.deleteAll();
    }

    @Test
    public void testGetBoardNotFound() throws DaoException {
        //assertTrue(boardRepository.findByName("not found").isEmpty());
    }

    @Test
    public void testAddBoard() throws DaoException {
        String name = "name";
        Board board = new Board(name);
        boardRepository.save(board);

        List<Board> found = boardRepository.findByName(name);
        //assertFalse(found.isEmpty());
        //assertEquals(name, found.stream().findFirst().get().getName());
    }

}

