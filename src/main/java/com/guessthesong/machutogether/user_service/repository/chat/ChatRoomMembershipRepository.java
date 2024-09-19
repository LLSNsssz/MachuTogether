package com.guessthesong.machutogether.user_service.repository.chat;

import com.guessthesong.machutogether.user_service.domain.chat.ChatRoomMembership;
import com.guessthesong.machutogether.user_service.domain.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomMembershipRepository extends JpaRepository<ChatRoomMembership, Long> {

    /**
     * 특정 채팅방의 모든 멤버십을 조회합니다.
     *
     * @param chatRoomId 채팅방 ID
     * @return 채팅방의 모든 멤버십 리스트
     */
    List<ChatRoomMembership> findByChatRoomId(Long chatRoomId);

    /**
     * 특정 채팅방의 모든 멤버(User)를 조회합니다.
     *
     * @param chatRoomId 채팅방 ID
     * @return 채팅방의 모든 멤버 리스트
     */
    List<User> findUsersByChatRoomId(Long chatRoomId);

    /**
     * 특정 사용자의 특정 채팅방 멤버십을 삭제합니다.
     *
     * @param userId     사용자 ID
     * @param chatRoomId 채팅방 ID
     */
    void deleteByUserIdAndChatRoomId(Long userId, Long chatRoomId);

    /**
     * 특정 채팅방의 멤버 수를 계산합니다.
     *
     * @param chatRoomId 채팅방 ID
     * @return 멤버 수
     */
    Long countByChatRoomId(Long chatRoomId);

    /**
     * 특정 사용자가 특정 채팅방의 멤버인지 확인합니다.
     *
     * @param userId     사용자 ID
     * @param chatRoomId 채팅방 ID
     * @return 멤버십 존재 여부
     */
    boolean existsByUserIdAndChatRoomId(Long userId, Long chatRoomId);
}