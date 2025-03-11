package com.springboot.campusconnect2.repository;

import com.springboot.campusconnect2.entity.CommentEntity;
import com.springboot.campusconnect2.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    // 특정 이벤트의 댓글을 시간순으로 조회
    List<CommentEntity> findAllByEventEntityOrderByIdDesc(EventEntity eventEntity);
}
