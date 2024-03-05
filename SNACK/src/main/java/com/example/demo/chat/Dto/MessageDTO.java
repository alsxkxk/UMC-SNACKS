package com.example.demo.chat.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class MessageDTO {
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Get {
		@NotBlank
		private String senderName;
		@NotNull
		private String content;
	}
	
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Request {
		@Positive
		private Long senderId;
		@Positive
		private Long roomId;
		@NotNull
		private String content;
	}
	
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Response {
		private Long memberId;
		private String senderName;
		private String content;
		private LocalDateTime time;

		public void setMemberId(Long memberId) {
			this.memberId = memberId;
		}
	}
	
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Preview {
		private String content;
		private LocalDateTime time;
	}
}
