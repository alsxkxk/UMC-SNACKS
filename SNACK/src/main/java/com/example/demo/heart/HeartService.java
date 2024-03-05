package com.example.demo.heart;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.socialboard.entity.SocialBoard;
import com.example.demo.socialboard.repository.SocialBoardRepository;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class HeartService {
    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final SocialBoardRepository socialBoardRepository;

    @Autowired
    public HeartService(HeartRepository heartRepository, MemberRepository memberRepository, SocialBoardRepository socialBoardRepository) {
        this.heartRepository = heartRepository;
        this.memberRepository = memberRepository;
        this.socialBoardRepository = socialBoardRepository;
    }




    @Transactional
    public void insert(HeartRequestDTO heartRequestDTO, Authentication authentication) throws Exception {

        Long memberId = Long.parseLong(authentication.getName());
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Could not found member id : " + memberId));

        SocialBoard board = socialBoardRepository.findById(heartRequestDTO.getBoardId())
                .orElseThrow(() -> new NoSuchElementException("Could not found board id : " + heartRequestDTO.getBoardId()));


        // 이미 좋아요되어있으면 에러 반환
        if (heartRepository.findByMemberAndSocialBoard(member, board).isPresent()){
            delete(heartRequestDTO, authentication);
            return;
        }

        Heart heart = Heart.builder()
                .socialBoard(board)
                .member(member)
                .build();

        socialBoardRepository.updateCount(board, true);
        heartRepository.save(heart);
    }

    @Transactional
    public void delete(HeartRequestDTO heartRequestDTO, Authentication authentication) {

        Long memberId = Long.parseLong(authentication.getName());
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Could not found member id : " + memberId));

        SocialBoard board = socialBoardRepository.findById(heartRequestDTO.getBoardId())
                .orElseThrow(() -> new NoSuchElementException("Could not found board id : " + heartRequestDTO.getBoardId()));

        Heart heart = heartRepository.findByMemberAndSocialBoard(member, board)
                .orElseThrow(() -> new NoSuchElementException("Could not found heart id"));

        socialBoardRepository.updateCount(board, false);
        heartRepository.delete(heart);
    }


}
