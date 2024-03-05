package com.example.demo.board.repository;

import com.example.demo.board.entity.Board;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    public List<Board> findBoardsByWriterId(Long writerId);



}
