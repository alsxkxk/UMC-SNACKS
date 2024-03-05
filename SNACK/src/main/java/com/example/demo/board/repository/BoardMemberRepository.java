package com.example.demo.board.repository;

import com.example.demo.board.entity.BoardMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember, Long>, BoardMemberRepositoryCustom {


//    public List<BoardMember> findBoardMembersByMemberId(Long memberId);

    public List<BoardMember> findBoardMembersByBoardId(Long boardId);


}
