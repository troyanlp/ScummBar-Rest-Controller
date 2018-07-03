package com.manuelmb.scummbar.controller.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.manuelmb.scummbar.controller.dto.BoardDTO;
import com.manuelmb.scummbar.domain.Board;

public class BoardMapper {

    public static Board makeBoard(BoardDTO boardDTO) {
        return new Board(boardDTO.getName());
    }

    public static BoardDTO makeBoardDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setName(board.getName());
        return boardDTO;
    }

    public static List<BoardDTO> makeBoardDTOList(Collection<Board> boards) {
        return boards.stream()
                .map(BoardMapper::makeBoardDTO)
                .collect(Collectors.toList());
    }

    public static List<Board> makeBoardList(Collection<BoardDTO> boardsDTO) {
        if (boardsDTO == null) {
            return Collections.emptyList();
        }
        return boardsDTO.stream()
                .map(BoardMapper::makeBoard)
                .collect(Collectors.toList());
    }

}
