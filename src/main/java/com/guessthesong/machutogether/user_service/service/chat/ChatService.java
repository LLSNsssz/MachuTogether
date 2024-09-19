package com.guessthesong.machutogether.user_service.service.chat;

import com.guessthesong.machutogether.user_service.domain.chat.ChatRoom;
import com.guessthesong.machutogether.user_service.domain.chat.ChatRoomMembership;
import com.guessthesong.machutogether.user_service.domain.chat.Message;
import com.guessthesong.machutogether.domain.chat.MessageDto;
import com.guessthesong.machutogether.user_service.domain.user.User;
import com.guessthesong.machutogether.user_service.repository.chat.ChatRoomRepository;
import com.guessthesong.machutogether.user_service.repository.chat.ChatRoomMembershipRepository;
import com.guessthesong.machutogether.user_service.repository.chat.MessageRepository;
import com.guessthesong.machutogether.user_service.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;
    private final ChatRoomMembershipRepository membershipRepository;
    private final UserRepository userRepository;

    public ChatService(ChatRoomRepository chatRoomRepository, MessageRepository messageRepository,
        ChatRoomMembershipRepository membershipRepository, UserRepository userRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.messageRepository = messageRepository;
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
    }

    public ChatRoom createChatRoom(String name, Long creatorUserId, Integer maxParticipants) {
        User creator = userRepository.findById(creatorUserId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ChatRoom chatRoom = ChatRoom.builder()
            .name(name)
            .createdAt(Instant.now())
            .lastMessageAt(Instant.now())
            .maxParticipants(maxParticipants)
            .build();
        chatRoom = chatRoomRepository.save(chatRoom);

        ChatRoomMembership membership = ChatRoomMembership.builder()
            .user(creator)
            .chatRoom(chatRoom)
            .joinedAt(Instant.now())
            .isAdmin(true)
            .build();
        membershipRepository.save(membership);

        return chatRoom;
    }

    public void joinChatRoom(Long userId, Long chatRoomId) {
        if (!membershipRepository.existsByUserIdAndChatRoomId(userId, chatRoomId)) {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
            ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId);
            ChatRoomMembership membership = ChatRoomMembership.builder()
                .user(user)
                .chatRoom(chatRoom)
                .joinedAt(Instant.now())
                .isAdmin(false)
                .build();
            membershipRepository.save(membership);
        }
    }

    public void leaveChatRoom(Long userId, Long chatRoomId) {
        membershipRepository.deleteByUserIdAndChatRoomId(userId, chatRoomId);

        if (membershipRepository.countByChatRoomId(chatRoomId) == 0) {
            chatRoomRepository.deleteById(chatRoomId);
        }
    }

    public MessageDto sendMessage(Long userId, Long chatRoomId, String content) {
        if (membershipRepository.existsByUserIdAndChatRoomId(userId, chatRoomId)) {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
            ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId);

            Message message = Message.builder()
                .content(content)
                .sentAt(Instant.now())
                .chatRoom(chatRoom)
                .user(user)
                .build();
            message = messageRepository.save(message);

            // Update ChatRoom's lastMessageAt and lastMessagePreview
            chatRoom.setLastMessageAt(message.getSentAt());
            chatRoom.setLastMessagePreview(content.length() > 255 ? content.substring(0, 255) : content);
            chatRoomRepository.save(chatRoom);

            return message;
        } else {
            throw new IllegalStateException("User is not a member of this chat room");
        }
    }
}
