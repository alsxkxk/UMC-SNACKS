package com.example.demo.socialboard.repository;

import com.example.demo.socialboard.entity.SocialBoard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialBoardRepository extends JpaRepository<SocialBoard, Long>, SocialBoardRepositoryCustom {
    //public List<SocialBoard> findSocialBoardsByMemberId(Long id);

    @Query("SELECT s FROM SocialBoard s WHERE s.writer.id = :memberId")
    public List<SocialBoard> findSocialBoardsByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT s FROM SocialBoard s JOIN Heart h ON s.id = h.socialBoard.id WHERE h.member.id = :memberId")
    List<SocialBoard> findUserFavoriteBoardsByMemberId(@Param("memberId") Long memberId);



}
