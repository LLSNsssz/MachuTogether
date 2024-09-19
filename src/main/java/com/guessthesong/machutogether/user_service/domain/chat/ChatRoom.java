package com.guessthesong.machutogether.user_service.domain.chat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

import lombok.AccessLevel;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "chat_room")
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant lastMessageAt;

    @Column(nullable = false)
    private Integer maxParticipants;

    @Column(length = 255)
    private String lastMessagePreview;

    @Builder
    public ChatRoom(String name, Instant createdAt, Instant lastMessageAt, Integer maxParticipants,
        String lastMessagePreview) {
        this.name = name;
        this.createdAt = createdAt;
        this.lastMessageAt = lastMessageAt;
        this.maxParticipants = maxParticipants;
        this.lastMessagePreview = lastMessagePreview;
    }
}