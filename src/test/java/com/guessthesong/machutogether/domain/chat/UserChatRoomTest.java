package com.guessthesong.machutogether.domain.chat;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class UserChatRoomTest {

    @Test
    void testUserChatRoomCreation() {
        // Given
        Long userId = 1L;
        ChatRoom chatRoom = ChatRoom.builder().name("Test Room").build();
        Instant joinedAt = Instant.now();
        boolean isAdmin = true;
        String nickname = "TestUser";
        boolean notificationEnabled = true;

        // When
        UserChatRoom userChatRoom = UserChatRoom.builder()
            .userId(userId)
            .chatRoom(chatRoom)
            .joinedAt(joinedAt)
            .isAdmin(isAdmin)
            .nickname(nickname)
            .notificationEnabled(notificationEnabled)
            .build();

        // Then
        assertThat(userChatRoom.getUserId()).isEqualTo(userId);
        assertThat(userChatRoom.getChatRoom()).isEqualTo(chatRoom);
        assertThat(userChatRoom.getJoinedAt()).isEqualTo(joinedAt);
        assertThat(userChatRoom.isAdmin()).isEqualTo(isAdmin);
        assertThat(userChatRoom.getNickname()).isEqualTo(nickname);
        assertThat(userChatRoom.isNotificationEnabled()).isEqualTo(notificationEnabled);
    }

}