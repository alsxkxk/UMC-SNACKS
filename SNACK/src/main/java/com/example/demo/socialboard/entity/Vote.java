package com.example.demo.socialboard.entity;

import jakarta.persistence.CascadeType;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "VOTE_ELEMENT")
    private String voteElement;

    @Column(name = "VOTE_NUM")
    private Integer voteNum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VOTEBOARD_ID")
    private VoteBoard voteBoard;

}
