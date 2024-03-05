package com.example.demo.chat.repository;

import static com.example.demo.chat.Entity.QChatMessage.chatMessage;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChatMessageCustomRepositoryImpl implements ChatMessageCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void deleteMessagePeriodicallyEvery3Day() {
        jpaQueryFactory.delete(chatMessage)
                .where(chatMessage.sentAt.before(LocalDateTime.now().minusDays(3)));
    }

}
