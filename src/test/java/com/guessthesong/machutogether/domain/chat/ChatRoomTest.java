package com.guessthesong.machutogether.domain.chat;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class ChatRoomTest {

    @Test
    void testChatRoomCreation() {
        // Given
        String name = "Test Room";
        Instant createdAt = Instant.now();
        Instant lastMessageAt = createdAt.plusSeconds(3600);
        Integer maxParticipants = 10;
        String lastMessagePreview = "Hello, world!";

        // When
        ChatRoom chatRoom = ChatRoom.builder()
            .name(name)
            .createdAt(createdAt)
            .lastMessageAt(lastMessageAt)
            .maxParticipants(maxParticipants)
            .lastMessagePreview(lastMessagePreview)
            .build();

        // Then
        assertThat(chatRoom.getName()).isEqualTo(name);
        assertThat(chatRoom.getCreatedAt()).isEqualTo(createdAt);
        assertThat(chatRoom.getLastMessageAt()).isEqualTo(lastMessageAt);
        assertThat(chatRoom.getMaxParticipants()).isEqualTo(maxParticipants);
        assertThat(chatRoom.getLastMessagePreview()).isEqualTo(lastMessagePreview);
    }
}