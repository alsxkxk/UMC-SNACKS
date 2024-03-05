package com.example.demo.chat.Dto;

import com.example.demo.Games;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class ChatRoomDTO {
	
	@Getter
	@Setter
	@RequiredArgsConstructor
	public static class Get {
		private String type;
		@Positive
		private Long roomId;
		private Long memberId;
		private Games games;
		private String name;	
		private int numberOfUnreadMessage;
		private String imageUri;
		private String content;
		private LocalDateTime sentAt;
		private LocalDateTime appointment;	// 팀의 경우 약속 시간
	}
}
