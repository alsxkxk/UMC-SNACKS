package com.example.demo.chat.Dto;

import com.example.demo.chat.Entity.ChatRoomMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomMemberChatMessageContentDTO {
    private ChatRoomMember chatRoomMember;
    private String content;
}
