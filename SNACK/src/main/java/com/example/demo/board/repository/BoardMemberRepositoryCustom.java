package com.example.demo.board.repository;

import com.example.demo.board.enrollment.Enrollment;
import com.example.demo.board.entity.Board;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMemberRepositoryCustom {
    List<Board> searchAttendingBoardsByMemberId(Long memberId);
    List<Enrollment> searchHostRequestByHostId(Long hostId);

}
