package com.likelion.rolling_paper.domain;

import com.likelion.rolling_paper.home.dto.CreateHomeReqDto;
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
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "rolling_paper_home")
public class RollingPaperHome extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "home_id")
    private Long id;

    private String name;

    @Column(name = "participants_count")
    private Integer participantsCount;

    // == 연관 관계 매핑 == //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    // == 편의 메소드 ==
    public static RollingPaperHome toEntity(User user, CreateHomeReqDto dto) {
        return RollingPaperHome.builder()
                .name(dto.name())
                .participantsCount(dto.participantCount())
                .owner(user)
                .build();
    }
}
