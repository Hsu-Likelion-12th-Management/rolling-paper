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
import jakarta.persistence.OneToOne;
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
@Table(name = "rolling_paper")
public class RollingPaper extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolling_paper_id")
    private Long id;

    private String name; // 스노우볼 이름

    private Boolean isFinish; // 작성 종료 여부

    // == 연관 관계 매핑 == //
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    // === 편의 메소드 ===
    public static RollingPaper toEntity(User user) {
        return RollingPaper.builder()
                .isFinish(false)
                .name(user.getNickname())
                .owner(user)
                .build();
    }

    public void changeRollingPaperStatusToFinish() {
        this.isFinish = true;
    }
}
