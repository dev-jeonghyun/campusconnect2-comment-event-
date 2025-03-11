package com.springboot.campusconnect2.service;

import com.springboot.campusconnect2.dto.CommentDTO;
import com.springboot.campusconnect2.entity.CommentEntity;
import com.springboot.campusconnect2.entity.EventEntity;
import com.springboot.campusconnect2.repository.CommentRepository;
import com.springboot.campusconnect2.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final EventRepository eventRepository;

    // 댓글 저장
    public Long save(CommentDTO commentDTO) {
        Optional<EventEntity> optionalEventEntity = eventRepository.findById(commentDTO.getEventId());
        if (optionalEventEntity.isPresent()) {
            EventEntity eventEntity = optionalEventEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, eventEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            throw new IllegalArgumentException("해당 이벤트가 존재하지 않습니다.");
        }
    }

    // 특정 이벤트의 모든 댓글 조회
    public List<CommentDTO> findAll(Long eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트가 존재하지 않습니다."));
        List<CommentEntity> commentEntityList = commentRepository.findAllByEventEntityOrderByIdDesc(eventEntity);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, eventId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    // 댓글 수정
    public boolean update(Long id, CommentDTO commentDTO) {
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(id);
        if (optionalCommentEntity.isPresent()) {
            CommentEntity commentEntity = optionalCommentEntity.get();
            commentEntity.setCommentWriter(commentDTO.getCommentWriter());
            commentEntity.setCommentContents(commentDTO.getCommentContents());
            commentRepository.save(commentEntity);
            return true;
        }
        return false;
    }

    // 댓글 삭제
    public boolean delete(Long id) {
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("댓글 삭제 오류: " + e.getMessage());
            return false;
        }
    }
}
