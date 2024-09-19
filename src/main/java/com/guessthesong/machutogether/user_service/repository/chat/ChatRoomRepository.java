package com.guessthesong.machutogether.user_service.repository.chat;

import com.guessthesong.machutogether.user_service.domain.chat.ChatRoom;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    /**
     * 주어진 ID에 해당하는 ChatRoom을 조회합니다.
     *
     * @param id 조회할 ChatRoom의 ID
     * @return 찾은 ChatRoom 객체, 없으면 null
     */
    Optional<ChatRoom> findById(Long id);

    /**
     * 주어진 이름을 포함하는 모든 ChatRoom을 조회합니다.
     *
     * @param name 검색할 ChatRoom 이름
     * @return 이름을 포함하는 ChatRoom 목록
     */
    List<ChatRoom> findByNameContaining(String name);

    /**
     * 모든 ChatRoom을 마지막 메시지 시간 기준 내림차순으로 조회합니다.
     *
     * @return 정렬된 ChatRoom 목록
     */
    List<ChatRoom> findAllOrderByLastMessageAtDesc();

    /**
     * 주어진 ID에 해당하는 ChatRoom을 삭제합니다.
     *
     * @param id 삭제할 ChatRoom의 ID
     */
    void deleteById(Long id);

    /**
     * 주어진 ChatRoom 객체를 삭제합니다.
     *
     * @param chatRoom 삭제할 ChatRoom 객체
     */
    void delete(ChatRoom chatRoom);

    /**
     * 모든 ChatRoom의 수를 반환합니다.
     *
     * @return ChatRoom의 총 개수
     */
    long count();

    /**
     * 주어진 ID에 해당하는 ChatRoom이 존재하는지 확인합니다.
     *
     * @param id 확인할 ChatRoom의 ID
     * @return 존재하면 true, 그렇지 않으면 false
     */
    boolean existsById(Long id);
}
