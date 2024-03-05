package com.example.demo.profile.dto.follow;

import com.example.demo.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MyInfoFolloweeResponseDto {
    private Long followerId;
//    private String nickname;

    @Builder
    public MyInfoFolloweeResponseDto(Long followerId){//, String nickname){
        this.followerId = followerId;
//        this.nickname = nickname;
    }

    public static MyInfoFolloweeResponseDto of(Member member){
        return new MyInfoFolloweeResponseDto(member.getId());//, member.getNickname());
    }
}
