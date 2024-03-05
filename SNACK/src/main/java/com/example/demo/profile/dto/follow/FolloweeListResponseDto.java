package com.example.demo.profile.dto.follow;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FolloweeListResponseDto {
    private List<MyInfoFolloweeResponseDto> myInfoFolloweeResponseDtoList;

    @Builder
    public FolloweeListResponseDto(List<MyInfoFolloweeResponseDto> myInfoFolloweeResponseDtoList){
        this.myInfoFolloweeResponseDtoList = myInfoFolloweeResponseDtoList;
    }
}
