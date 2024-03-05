package com.example.demo.member.repository;


import com.example.demo.member.entity.Member;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByLoginId(String id);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByName(String name);

    Optional<Member> findByNameAndLoginIdAndBirth(String name, String id, LocalDate date);

    @Query("SELECT m FROM Member m JOIN Heart h ON m.id = h.member.id WHERE h.socialBoard.id = :socialBoardId")
    List<Member> findMembersLikedBoardByBoardId(@Param("socialBoardId") Long socialBoardId);




}
