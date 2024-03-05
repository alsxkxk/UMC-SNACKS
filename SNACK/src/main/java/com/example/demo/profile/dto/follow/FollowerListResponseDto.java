package com.example.demo.profile.dto.follow;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowerListResponseDto {
    private List<MyInfoFollowerResponseDto> myInfoFollowerResponseDtoList;

    @Builder
    public FollowerListResponseDto(List<MyInfoFollowerResponseDto> myInfoFollowerResponseDtoList){
        this.myInfoFollowerResponseDtoList = myInfoFollowerResponseDtoList;
    }

}
