package com.example.demo.chat;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("/live-chat")
public class LiveChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    /*
     * 라이브 채팅방을 생성하는 메서드
     *
     * @param 사용자 정보, authentication
     */
    @PostMapping
    public void createLiveChat(Authentication authentication) {

    }

    /*
     * 게시글에 있는 라이브 채팅방에 입장했을 때 호출.
     *
     * 어떻게 동작할지 결정 후 작성예정
     */
    public void enterLiveChatRoom () {

    }
}
