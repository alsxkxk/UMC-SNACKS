package com.example.demo.socialboard.entity;

import com.example.demo.BaseTimeEntity;
import com.example.demo.comment.entity.Comment;
import com.example.demo.member.entity.Member;
import com.example.demo.socialboard.dto.SocialBoardResponseDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class SocialBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "SOCIAL_BOARD_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WRITER_ID")
    private Member writer;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "LIKES")
    private Long likes = 0L;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    public abstract SocialBoardResponseDTO toResponseEntity();

    public abstract void update(SocialBoard updatedBoard);
}
