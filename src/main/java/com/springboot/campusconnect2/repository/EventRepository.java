package com.springboot.campusconnect2.repository;

import com.springboot.campusconnect2.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
