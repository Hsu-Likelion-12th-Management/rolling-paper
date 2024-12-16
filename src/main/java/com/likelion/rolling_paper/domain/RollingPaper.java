package com.likelion.rolling_paper.domain;

import com.likelion.rolling_paper.util.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "rolling_paper")
public class RollingPaper extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolling_paper_id")
    private Long id;

    private String name; // 스노우볼 이름

    @Enumerated(EnumType.STRING)
    private State state;

    // == 연관 관계 매핑 == //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id")
    private RollingPaperHome home;

    // === 편의 메소드 ===
    public static RollingPaper toEntity(String name, RollingPaperHome paperHome) {
        return RollingPaper.builder()
                .state(State.PROGRESS)
                .name(name)
                .home(paperHome)
                .build();
    }

}
