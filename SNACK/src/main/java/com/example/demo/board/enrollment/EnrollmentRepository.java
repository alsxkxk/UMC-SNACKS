package com.example.demo.board.enrollment;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findEnrollmentsByMemberId(Long memberId);

    Optional<Enrollment> findEnrollmentByMemberIdAndBoardId(Long memberId, Long boardId);
}
