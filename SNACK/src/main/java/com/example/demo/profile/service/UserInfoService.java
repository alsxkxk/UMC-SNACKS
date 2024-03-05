package com.example.demo.profile.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.profile.dto.myInfo.MyInfoArticleResponseDto;
import com.example.demo.profile.dto.myInfo.MyInfoResponseDto;
import com.example.demo.socialboard.entity.SocialBoard;
import com.example.demo.socialboard.repository.SocialBoardRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserInfoService {

    private final MemberRepository memberRepository;
    private final SocialBoardRepository socialBoardRepository;

    public MyInfoResponseDto readUserInfo(Member tempMember) {

        List<SocialBoard> socialBoardsList = socialBoardRepository.findSocialBoardsByMemberId(tempMember.getId());
        List<MyInfoArticleResponseDto> myInfoArticleResponseDtoList = new ArrayList<>();
        for(SocialBoard article:socialBoardsList){
            myInfoArticleResponseDtoList.add(MyInfoArticleResponseDto.of(article));
        }

        return MyInfoResponseDto.builder()
                .myProfileImageUrl(tempMember.getProfileImageUrl())
                .nickname(tempMember.getNickname())
                .articleCount(Long.valueOf(socialBoardsList.size()))
                .followerCount(tempMember.getUserInfo().getFollowerCount())
                .followCount(tempMember.getUserInfo().getFollowCount())
                .introduction(tempMember.getUserInfo().getIntroduction())
                .myInfoArticleResponseDtoList(myInfoArticleResponseDtoList)
                .memberId(tempMember.getId())
                .build();
    }
}
