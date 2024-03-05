package com.example.demo.heart;

import static jakarta.persistence.FetchType.LAZY;

import com.example.demo.member.entity.Member;
import com.example.demo.socialboard.entity.SocialBoard;
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
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HEART_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "SOCIAL_BOARD_ID")
    private SocialBoard socialBoard;
}
