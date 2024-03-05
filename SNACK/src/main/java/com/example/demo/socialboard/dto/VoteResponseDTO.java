package com.example.demo.socialboard.dto;


import com.example.demo.socialboard.entity.Vote;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteResponseDTO {
    private String voteElement;

    private Integer voteNum;

    public static List<VoteResponseDTO> toListEntity(List<Vote> votes) {
        ArrayList<VoteResponseDTO> responseDTOs = new ArrayList<>();

        votes.iterator().forEachRemaining(
                vote -> responseDTOs.add(toResponseEntity(vote))
        );

        return responseDTOs;
    }

    public static VoteResponseDTO toResponseEntity(Vote vote) {
        return VoteResponseDTO.builder()
                .voteElement(vote.getVoteElement())
                .voteNum(vote.getVoteNum())
                .build();
    }
}
