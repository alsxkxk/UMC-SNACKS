package com.example.demo.socialboard.dto;

import com.example.demo.comment.dto.CommentResponseDTO;
import com.example.demo.socialboard.entity.SocialBoard;
import com.example.demo.socialboard.entity.VoteBoard;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class VoteBoardResponseDTO extends SocialBoardResponseDTO{
    private List<VoteResponseDTO> votes;

    @Override
    public SocialBoardResponseDTO toResponseEntity(SocialBoard socialBoard) {

        VoteBoard voteBoard = (VoteBoard) socialBoard;
        List<CommentResponseDTO> responseDTOList = CommentResponseDTO.toListEntity(voteBoard.getComments());
        List<VoteResponseDTO> voteResponseDTOS = VoteResponseDTO.toListEntity(voteBoard.getVotes());

        return VoteBoardResponseDTO.builder()
                .id(voteBoard.getId())
                .writer(voteBoard.getWriter().getNickname())
                .content(voteBoard.getContent())
                .likes(voteBoard.getLikes())
                .comments(responseDTOList)
                .votes(voteResponseDTOS)
                .build();

    }
}
