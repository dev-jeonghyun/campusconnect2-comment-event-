package com.springboot.campusconnect2.service;

import com.springboot.campusconnect2.dto.EventDTO;
import com.springboot.campusconnect2.entity.EventEntity;
import com.springboot.campusconnect2.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    // 이벤트 생성
    public EventDTO createEvent(EventDTO eventDTO) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setTitle(eventDTO.getTitle());
        eventEntity.setDescription(eventDTO.getDescription());
        eventEntity.setLocation(eventDTO.getLocation());
        eventEntity.setStartDate(eventDTO.getStartDate());
        eventEntity.setEndDate(eventDTO.getEndDate());
        eventDTO.setPrivateEvent(eventEntity.isPrivateEvent()); // 수정된 부분
        eventEntity.setCreatedBy(eventDTO.getCreatedBy());

        EventEntity savedEvent = eventRepository.save(eventEntity);

        eventDTO.setId(savedEvent.getId());
        return eventDTO;
    }

    public List<EventDTO> findAllEvents() {
        List<EventEntity> eventEntities = eventRepository.findAll();

        return eventEntities.stream().map(eventEntity -> {
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(eventEntity.getId());
            eventDTO.setTitle(eventEntity.getTitle());
            eventDTO.setDescription(eventEntity.getDescription());
            eventDTO.setLocation(eventEntity.getLocation());
            eventDTO.setStartDate(eventEntity.getStartDate());
            eventDTO.setEndDate(eventEntity.getEndDate());
            eventDTO.setPrivateEvent(eventEntity.isPrivateEvent()); // boolean 값 설정
            eventDTO.setCreatedBy(eventEntity.getCreatedBy());
            return eventDTO;
        }).collect(Collectors.toList());
    }

    // 특정 이벤트 조회
    public EventDTO getEventById(Long id) {
        EventEntity eventEntity = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트를 찾을 수 없습니다."));

        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventEntity.getId());
        eventDTO.setTitle(eventEntity.getTitle());
        eventDTO.setDescription(eventEntity.getDescription());
        eventDTO.setLocation(eventEntity.getLocation());
        eventDTO.setStartDate(eventEntity.getStartDate());
        eventDTO.setEndDate(eventEntity.getEndDate());
        eventDTO.setPrivateEvent(eventEntity.isPrivateEvent()); // 수정된 부분
        eventDTO.setCreatedBy(eventEntity.getCreatedBy());

        return eventDTO;
    }

    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        EventEntity eventEntity = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트를 찾을 수 없습니다."));

        eventEntity.setTitle(eventDTO.getTitle());
        eventEntity.setDescription(eventDTO.getDescription());
        eventEntity.setLocation(eventDTO.getLocation());
        eventEntity.setStartDate(eventDTO.getStartDate());
        eventEntity.setEndDate(eventDTO.getEndDate());
        eventDTO.setPrivateEvent(eventEntity.isPrivateEvent()); // 수정된 부분
        eventEntity.setCreatedBy(eventDTO.getCreatedBy());

        EventEntity updatedEvent = eventRepository.save(eventEntity);
        return toEventDTO(updatedEvent);
    }

    // 이벤트 삭제
    public void deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("삭제할 이벤트가 존재하지 않습니다.");
        }
    }

    // DTO -> Entity 변환 메서드
    private EventEntity toEventEntity(EventDTO eventDTO) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setTitle(eventDTO.getTitle());
        eventEntity.setDescription(eventDTO.getDescription());
        eventEntity.setLocation(eventDTO.getLocation());
        eventEntity.setStartDate(eventDTO.getStartDate());
        eventEntity.setEndDate(eventDTO.getEndDate());
        eventDTO.setPrivateEvent(eventEntity.isPrivateEvent()); // 수정된 부분
        eventEntity.setCreatedBy(eventDTO.getCreatedBy());
        return eventEntity;
    }

    // Entity -> DTO 변환 메서드
    private EventDTO toEventDTO(EventEntity eventEntity) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(eventEntity.getId());
        eventDTO.setTitle(eventEntity.getTitle());
        eventDTO.setDescription(eventEntity.getDescription());
        eventDTO.setLocation(eventEntity.getLocation());
        eventDTO.setStartDate(eventEntity.getStartDate());
        eventDTO.setEndDate(eventEntity.getEndDate());
        eventDTO.setPrivateEvent(eventEntity.isPrivateEvent()); // 수정된 부분
        eventDTO.setCreatedBy(eventEntity.getCreatedBy());
        return eventDTO;
    }
}
