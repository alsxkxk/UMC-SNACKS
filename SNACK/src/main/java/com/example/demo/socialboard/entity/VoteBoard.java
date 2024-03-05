package com.example.demo.socialboard.entity;

import com.example.demo.socialboard.dto.SocialBoardResponseDTO;
import com.example.demo.socialboard.dto.VoteBoardResponseDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@DiscriminatorValue("V")
@SuperBuilder
@NoArgsConstructor
public class VoteBoard extends SocialBoard {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "voteBoard", cascade = CascadeType.REMOVE)
    private List<Vote> votes = new ArrayList<>();

    @Override
    public SocialBoardResponseDTO toResponseEntity() {
        return new VoteBoardResponseDTO().toResponseEntity(this);
    }

    @Override
    public void update(SocialBoard updatedBoard) {
        VoteBoard voteBoard = (VoteBoard) updatedBoard;
        this.setContent(voteBoard.getContent());
        this.setVotes(voteBoard.getVotes());
    }
}