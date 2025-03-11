package com.springboot.campusconnect2.entity;

import com.springboot.campusconnect2.dto.CommentDTO;
import com.springboot.campusconnect2.entity.BaseEntity;
import com.springboot.campusconnect2.entity.EventEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String commentWriter;

    @Column(nullable = false)
    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity eventEntity;

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, EventEntity eventEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setEventEntity(eventEntity);
        return commentEntity;
    }
}
