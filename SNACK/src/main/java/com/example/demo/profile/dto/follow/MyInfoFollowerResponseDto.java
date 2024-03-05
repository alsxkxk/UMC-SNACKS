package com.example.demo.profile.dto.follow;

import com.example.demo.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyInfoFollowerResponseDto {
    private Long followerId;
//    private String nickname;

    @Builder
    public MyInfoFollowerResponseDto(Long followerId){//, String nickname){
        this.followerId = followerId;
//        this.nickname = nickname;
    }

    public static MyInfoFollowerResponseDto of(Member member){
        return new MyInfoFollowerResponseDto(member.getId());//, member.getNickname());
    }

}
