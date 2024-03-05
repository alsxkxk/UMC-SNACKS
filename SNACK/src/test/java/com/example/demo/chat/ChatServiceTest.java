package com.example.demo.chat;

import com.example.demo.chat.Dto.ChatRoomDTO;
import com.example.demo.chat.Dto.ChatRoomListDTO;
import com.example.demo.chat.Entity.ChatMessage;
import com.example.demo.chat.repository.ChatMessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ChatServiceTest {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatService chatService;

    @Test
    public void test() {
        List<ChatMessage> u = chatMessageRepository.getNumberOfUnreadMessages(4L, 3L);

        for(ChatMessage cm : u) {
            System.out.println("roomId  senderId  content   sentAt");
            System.out.print(cm.getChatRoom().getRoomId() + " " + cm.getSender().getId() + " " + cm.getContent() + " " + cm.getSentAt() + " ");
        }
    }

    @Test
    public void getChatRoomListTest() {
        ChatRoomListDTO dto = chatService.getChatList(1L);

        System.out.println(dto.getChatRoomDTOGetList().get(0).getGames());

    }
}
