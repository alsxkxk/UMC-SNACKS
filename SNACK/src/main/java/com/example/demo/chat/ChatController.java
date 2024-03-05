package com.example.demo.chat;

import com.example.demo.chat.Dto.ChatRoomListDTO;
import com.example.demo.chat.Dto.MessageDTO;
import com.example.demo.exception.RestApiException;
import jakarta.validation.Valid;
import java.util.AbstractMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ChatController {
	private final ChatService chatService;
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final Mapper mapper;

	// <sessionId, <roomId, userId>>
	private static Map<String, Map.Entry<Long, Long>> sessions = new Hashtable<>();
	
	/*
	 * 채팅 페이지 진입 시 개인 채팅 및 팀 채팅 정보를 리턴해줌
	 * 
	 * @param 유저 정보, authentication
	 * @return 개인 채팅 및 팀 채팅 list
	 * @DTO 식별자, 이미지 URI, 이름, 읽지 않은 메시지 개수
	 * Exception
	 */ 
	@GetMapping("/chat")
	public ResponseEntity<Object> getChatRoomList(Authentication authentication) {
		log.info("[ChatController getChatRoomList] memberId : " + authentication.getName());
		ChatRoomListDTO chatRoomListDTO = chatService.getChatList(Long.parseLong(authentication.getName()));

		return new ResponseEntity<>(chatRoomListDTO, HttpStatus.OK);
	}
	
	/*
	 * (개인) 처음으로 대화하거나 방을 나갔다가 다시 대화 생성하는 경우
	 *
	 * @param 상대방 pk, participantId
	 * @return 채팅방 pk, roomId
	 * exception RestApiException
	 */
	@PostMapping("/chat/{memberId}")
	public ResponseEntity createChatRoom(Authentication authentication, @PathVariable("memberId") Long participantId) throws RestApiException {
		log.info("[ChatController createChatRoom] memberId : " + authentication.getName() + " participantId : " + participantId);
		Long roomId = chatService.createPrivateChatRoom(Long.parseLong(authentication.getName()), participantId);

		return new ResponseEntity<>(roomId, HttpStatus.OK);
	}
	
	/*
	 * 채팅방에 들어갔을 때 안 읽은 메시지들을 리턴해줌
	 * 
	 * @param principal 유저정보, roomId 방 식별자
	 * @return 안 읽은 메시지들
	 * exception
	 */
	@GetMapping("/chat/{roomId}")
	public ResponseEntity<List<MessageDTO.Response>> enterChatRoom(Authentication authentication, @PathVariable("roomId") Long roomId) {
		log.info("[ChatController enterChatRoom] memberId : " + authentication.getName() + " roomId : " + roomId);
		List<MessageDTO.Response> msgList = chatService.getChatMessage(Long.parseLong(authentication.getName()), roomId);

		return new ResponseEntity<>(msgList, HttpStatus.OK);
	}
	
	/*
	 * 사용자가 메시지를 전송하면 저장하고 같은 방에 있는 사용자에게 메시지를 반환함
	 * 
	 * @param
	 * @DTO senderName 이름, content 내용
	 */
	@MessageMapping("/chat/{roomId}") // '/pub/chat/roomId'
	public void messageSendAndSave(SimpMessageHeaderAccessor headerAccessor, @DestinationVariable Long roomId,
								   @Valid @Payload MessageDTO.Get messageDTO) {

		Long memberId = sessions.get(headerAccessor.getSessionId()).getValue();

		log.info("[ChatController messageSendAndSave] memberId : " + memberId +
				" senderName : " + messageDTO.getSenderName() +
				" Content: " + messageDTO.getContent());

		MessageDTO.Response response = mapper.MessageDtoGetToMessageDtoResponse(messageDTO);
		response.setMemberId(memberId);

		simpMessagingTemplate.convertAndSend("/sub/chat/" + roomId, response);

		MessageDTO.Request mdto = new MessageDTO.Request(memberId, roomId, messageDTO.getContent());
		chatService.saveMessage(mdto);
	}

	// 소켓 연결에 대한 사용자 정보 저장
	@EventListener(SessionConnectEvent.class)
	public void onConnect(SessionConnectEvent event){
		String sessionId = event.getMessage().getHeaders().get("simpSessionId").toString();
		String roomId = event.getMessage().getHeaders().get("nativeHeaders").toString().split("roomId=\\[")[1].split("]")[0];
		String memberId = event.getMessage().getHeaders().get("nativeHeaders").toString().split("memberId=\\[")[1].split("]")[0];
		log.info("[onConnect] roomId : " + roomId + " memberId : " + memberId);

		sessions.put(sessionId, new AbstractMap.SimpleEntry<>(Long.valueOf(roomId), Long.valueOf(memberId)));
	}

	@EventListener(SessionDisconnectEvent.class)
	public void onDisconnect(SessionDisconnectEvent event) {
		String roomId = sessions.get(event.getSessionId()).getKey().toString();
		String memberId = sessions.get(event.getSessionId()).getValue().toString();
		log.info("[onDisconnect] roomId : " + roomId + " memberId : " + memberId);

		chatService.setReadingTime(Long.valueOf(memberId), Long.valueOf(roomId));
		sessions.remove(event.getSessionId());
	}
	
	/*
	 * 사용자가 채팅방을 읽은 시각을 업데이트 함
	 */
//	@PostMapping("/chat/reading")
//	public void setReadingTime(Authentication authentication, @Valid @RequestBody ChatRoomDTO.Get dto) throws MethodArgumentTypeMismatchException, RestApiException {
//		log.info("[ChatController setReadingTime] memberId : " + authentication.getName() + " roomId : " + dto.getRoomId());
//		chatService.setReadingTime(Long.parseLong(authentication.getName()), dto.getRoomId());
//	}
	
	@DeleteMapping("/chat/{roomId}")
	public void deleteChatRoom(Authentication authentication, @PathVariable("roomId") Long roomId) {
		log.info("[ChatController deleteChatRoom] memberId : " + authentication.getName() + " roomId : " + roomId);
		chatService.deleteChatRoom(Long.parseLong(authentication.getName()), roomId);
	}
}
