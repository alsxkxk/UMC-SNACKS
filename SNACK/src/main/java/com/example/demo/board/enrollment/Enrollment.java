package com.example.demo.board.enrollment;

import com.example.demo.board.entity.Board;
import com.example.demo.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ENROLL_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "REQUEST_MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "REQUEST_BOARD_ID")
    private Board board;


}
