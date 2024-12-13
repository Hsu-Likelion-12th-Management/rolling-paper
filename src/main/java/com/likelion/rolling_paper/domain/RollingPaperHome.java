package com.likelion.rolling_paper.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "rolling_paper_home")
public class RollingPaperHome {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roling_paper_home_id")
    private Long id;
    private String name;
    @Column(name = "participants_count")
    private Integer participantsCount;
}
