package com.springboot.campusconnect2.controller;

import com.springboot.campusconnect2.dto.CommentDTO;
import com.springboot.campusconnect2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sub-events/{eventId}/comments")
@RequiredArgsConstructor
public class EventController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@PathVariable Long eventId, @RequestBody CommentDTO commentDTO) {
        commentDTO.setEventId(eventId); // BoardId를 EventId로 재사용
        Long commentId = commentService.save(commentDTO);
        return ResponseEntity.ok(commentId);
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Long eventId) {
        List<CommentDTO> commentDTOList = commentService.findAll(eventId);
        return ResponseEntity.ok(commentDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        boolean isUpdated = commentService.update(id, commentDTO);
        if (isUpdated) {
            return ResponseEntity.ok("Comment updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to update comment");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        boolean isDeleted = commentService.delete(id);
        if (isDeleted) {
            return ResponseEntity.ok("Comment deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete comment");
        }
    }
}
