package com.manuelmb.scummbar.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manuelmb.scummbar.dao.BoardRepository;
import com.manuelmb.scummbar.domain.Board;
import com.manuelmb.scummbar.exception.DaoException;
import com.manuelmb.scummbar.exception.ServiceException;
import com.manuelmb.scummbar.service.BoardService;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    @Override
    public Board saveOrUpdate(Board board) throws ServiceException {
        if (board == null) {
            throw new ServiceException();
        }
        return boardRepository.save(board);
    }

    @Transactional
    @Override
    public void delete(Long boardId) throws ServiceException {
        if (boardId == null) {
            throw new ServiceException();
        }
        boardRepository.delete(boardId);
    }

    @Override
    public Board findByName(String name) throws ServiceException, DaoException {
        if (name == null || name.isEmpty()) {
            throw new ServiceException();
        }
        List<Board> board = boardRepository.findByName(name);
        if (board.isEmpty()) {
            throw new DaoException();
        }
        return board.stream().findFirst().get();
    }

    @Override
    public Board findById(Long id) throws ServiceException, DaoException {
        if (id == null) {
            throw new ServiceException();
        }
        Board board = boardRepository.findOne(id);
        if (board == null) {
            throw new DaoException();
        }
        return board;
    }
    
    @Override
    public List<Board> findAll() throws ServiceException, DaoException {
        return (List<Board>) boardRepository.findAll();
        
    }

}
