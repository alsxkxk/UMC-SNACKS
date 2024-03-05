package com.example.demo.board.enrollment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseDTO {
    private Long id;
    private Long boardId;
    private String memberNickname;
    private String boardTitle;

    public static EnrollmentResponseDTO toResponseEntity(Enrollment enrollment) {
        return EnrollmentResponseDTO.builder()
                .id(enrollment.getId())
                .boardId(enrollment.getBoard().getId())
                .memberNickname(enrollment.getMember().getNickname())
                .boardTitle(enrollment.getBoard().getTitle())
                .build();
    }
}
