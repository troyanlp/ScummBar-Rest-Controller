package com.manuelmb.scummbar.dao;

import org.springframework.data.repository.CrudRepository;

import com.manuelmb.scummbar.domain.Board;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Long> {

    List<Board> findByName(String name);

}
