package com.sinse.apiapp.model.board;

import com.sinse.apiapp.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoardRepository extends JpaRepository<Board, Integer> {

}
