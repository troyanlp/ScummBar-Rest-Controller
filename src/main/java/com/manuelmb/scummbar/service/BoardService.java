package com.manuelmb.scummbar.service;

import java.util.List;

import com.manuelmb.scummbar.domain.Board;
import com.manuelmb.scummbar.exception.DaoException;
import com.manuelmb.scummbar.exception.ServiceException;

public interface BoardService {

    Board saveOrUpdate(Board board) throws ServiceException;

    void delete(Long boardId) throws ServiceException;

    Board findByName(String name) throws ServiceException, DaoException;

    Board findById(Long id) throws ServiceException, DaoException;
    
    List<Board> findAll() throws ServiceException, DaoException;


}
