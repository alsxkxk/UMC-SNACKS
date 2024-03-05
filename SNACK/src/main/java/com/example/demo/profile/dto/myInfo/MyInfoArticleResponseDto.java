package com.example.demo.profile.dto.myInfo;

import com.example.demo.comment.dto.CommentResponseDTO;
import com.example.demo.socialboard.entity.SocialBoard;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyInfoArticleResponseDto {

    private Long articleId;
    //private String articleImageUrl;
    private String content;

    //좋아요, 답글
    private Long likes;
    private List<CommentResponseDTO> comments;



    @Builder
    public MyInfoArticleResponseDto(Long articleId, String content, Long likes, List<CommentResponseDTO> comments){
        this.articleId = articleId;
        this.content = content;
        this.likes = likes;
        this.comments = comments;
    }

    public static MyInfoArticleResponseDto of(SocialBoard entity){
        return new MyInfoArticleResponseDto(entity.getId(), entity.getContent(), entity.getLikes(), entity.toResponseEntity().getComments());//, entity.getImageUrl());

    }
}
