package com.guessthesong.machutogether.repository.chat;

import com.guessthesong.machutogether.domain.chat.Message;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

    /**
     * 특정 채팅방의 메시지를 최신순으로 조회합니다. (스크롤 방식)
     *
     * @param chatRoomId    채팅방 ID
     * @param lastMessageId 마지막으로 로드된 메시지의 ID (null이면 가장 최신 메시지부터 조회)
     * @param limit         조회할 메시지 수
     * @return 메시지 리스트
     */
    List<Message> findMessagesForScroll(Long chatRoomId, Long lastMessageId, Pageable limit);

    /**
     * 특정 채팅방의 최근 메시지를 조회합니다.
     *
     * @param chatRoomId 채팅방 ID
     * @param pageable 페이징 정보
     * @return 최근 메시지 리스트
     */
    List<Message> findRecentMessages(Long chatRoomId, Pageable pageable);
}
