package com.example.demo.chat.repository;

import com.example.demo.chat.Dto.ChatRoomMemberJoinMessageDTO;
import com.example.demo.chat.Entity.ChatRoomMember;
import java.util.List;

public interface ChatRoomMemberCustomRepository {
    void deleteMemberToChatRoom(Long memberId, Long crmId);

//    ChatRoom findExistedChatRoom(Long creatorId, Long participantId);

    public List<ChatRoomMemberJoinMessageDTO> getChatRoomMemberJoinChatMessage(Long memberId);

    public List<ChatRoomMember> findMembersWithSameRoom(Long memberId);
}
