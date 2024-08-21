package com.guessthesong.machutogether.domain.chat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class MessageTest {

    @Test
    void testMessageCreation() {
        // Given
        String content = "Hello, world!";
        Instant sentAt = Instant.now();
        ChatRoom chatRoom = ChatRoom.builder().name("Test Room").build();
        Long userId = 1L;

        // When
        Message message = Message.builder()
            .content(content)
            .sentAt(sentAt)
            .chatRoom(chatRoom)
            .userId(userId)
            .build();

        // Then
        assertThat(message.getContent()).isEqualTo(content);
        assertThat(message.getSentAt()).isEqualTo(sentAt);
        assertThat(message.getChatRoom()).isEqualTo(chatRoom);
        assertThat(message.getUserId()).isEqualTo(userId);
    }
}