package com.likelion.rolling_paper.domain;

import com.likelion.rolling_paper.util.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "message")
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content; // 작성된 메시지 내용

    private Integer randomEmojiValue; // 랜덤 이모지 (0~6)

    // == 연관 관계 매핑 ==
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paper_id")
    private RollingPaper rollingPaper;

    // == 편의 메소드 ==
    public static Message toEntity(User user, String content, Integer randomEmojiValue, RollingPaper paper) {
        return Message.builder()
                .content(content)
                .randomEmojiValue(randomEmojiValue)
                .user(user)
                .rollingPaper(paper)
                .build();
    }
}
