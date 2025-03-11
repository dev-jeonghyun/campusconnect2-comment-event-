package com.springboot.campusconnect2.entity;

import com.springboot.campusconnect2.entity.BaseEntity;
import com.springboot.campusconnect2.entity.CommentEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "event_table")
public class EventEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private String location;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(name = "is_private")
    private boolean privateEvent;

    @Column(nullable = false)
    private Long createdBy;

    @OneToMany(mappedBy = "eventEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> commentEntityList = new ArrayList<>();
}
