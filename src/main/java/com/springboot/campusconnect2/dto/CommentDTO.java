package com.springboot.campusconnect2.dto;

import com.springboot.campusconnect2.entity.CommentEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;

    @NotEmpty(message = "댓글 작성자는 필수 값입니다.")
    private String commentWriter;

    @NotEmpty(message = "댓글 내용은 필수 값입니다.")
    private String commentContents;

    // 기존 Board ID를 Event ID로 확장하여 재사용
    private Long eventId;

    private LocalDateTime commentCreatedTime;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long eventId) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDTO.setEventId(eventId);
        return commentDTO;
    }
}
