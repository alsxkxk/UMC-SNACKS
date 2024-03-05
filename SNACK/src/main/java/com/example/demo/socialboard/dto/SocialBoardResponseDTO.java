package com.example.demo.socialboard.dto;

import com.example.demo.comment.dto.CommentResponseDTO;
import com.example.demo.socialboard.entity.SocialBoard;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class SocialBoardResponseDTO {
    private Long id;

    private String writer;

    private String content;

    private Long likes = 0L;

    private List<CommentResponseDTO> comments;

    public abstract SocialBoardResponseDTO toResponseEntity(SocialBoard socialBoard);
}
