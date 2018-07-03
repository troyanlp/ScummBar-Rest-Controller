package com.manuelmb.scummbar.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.manuelmb.scummbar.controller.dto.BoardDTO;
import com.manuelmb.scummbar.controller.mapper.BoardMapper;
import com.manuelmb.scummbar.domain.Board;
import com.manuelmb.scummbar.exception.DaoException;
import com.manuelmb.scummbar.exception.ServiceException;
import com.manuelmb.scummbar.service.BoardService;

@RestController
@RequestMapping(value = "v1/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/{boardId}")
	public @ResponseBody BoardDTO getBoard(@Valid @PathVariable Long boardId) throws ServiceException, DaoException {
		return BoardMapper.makeBoardDTO(boardService.findById(boardId));
	}

	@GetMapping
	public BoardDTO getBoardByName(@RequestParam String name) throws ServiceException, DaoException {
		return BoardMapper.makeBoardDTO(boardService.findByName(name));
	}

	@GetMapping(value = "/all")
	public List<BoardDTO> getResource() throws ServiceException, DaoException {

		List<BoardDTO> custList = boardService.findAll().stream().map(b -> BoardMapper.makeBoardDTO(b))
				.collect(Collectors.toList());

		return custList;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody BoardDTO createBoard(@Valid @RequestBody BoardDTO boardDTO)
			throws ServiceException, DaoException {
		Board board = BoardMapper.makeBoard(boardDTO);
		return BoardMapper.makeBoardDTO(boardService.saveOrUpdate(board));
	}


	@PutMapping("/{boardId}")
	public @ResponseBody ResponseEntity<String> put(@Valid @PathVariable Long boardId, @RequestBody Map<String, String> data) {
		BoardDTO bDTO= new BoardDTO();
		
		bDTO.setId(boardId);
		bDTO.setName(data.get("name"));
		
		Board board = BoardMapper.makeBoard(bDTO);
		try {
			BoardMapper.makeBoardDTO(boardService.saveOrUpdate(board));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    return new ResponseEntity<String>("PUT Response", HttpStatus.CONFLICT);
 
		}
	    return new ResponseEntity<String>("PUT Response", HttpStatus.OK);
	}
	
	@DeleteMapping("/{boardId}")
	public void deleteBoard(@Valid @PathVariable Long boardId) throws ServiceException, DaoException {
		boardService.delete(boardId);
	}

}
