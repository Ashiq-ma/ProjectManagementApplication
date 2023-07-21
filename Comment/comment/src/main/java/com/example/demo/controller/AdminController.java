package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Comment;
import com.example.demo.service.CommentService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	CommentService commentService;
	
	
	
	@GetMapping("/getComments")
	public ResponseEntity<List<Comment>> getAllComments(){
		return ResponseEntity.ok().body(commentService.getAllComments());
	}
	
	@GetMapping("/getComment/{commentId}")
	public ResponseEntity<Comment> getAllComments(@PathVariable long commentId){
		return ResponseEntity.ok().body(commentService.getCommentById(commentId));
	}
	
	@PostMapping("/addComment")
	public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
		return ResponseEntity.ok().body(commentService.addComment(comment));
	}
	
	@DeleteMapping("/deleteComment/{commentId}")
	public ResponseEntity<Comment> deleteCommentById(@PathVariable long commentId){
		commentService.deleteComment(commentId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/getCommentByTaskId/{taskId}")
	public ResponseEntity<Comment> getCommentByTaskId(@PathVariable long taskId){
		return ResponseEntity.ok().body(commentService.getCommentByTaskId(taskId));
	}
	
	@DeleteMapping("/deleteCommentByTask/{taskId}")
	public ResponseEntity<Comment> deleteCommentByTask(@PathVariable long taskId){
		commentService.deleteComment(taskId);
		return ResponseEntity.ok().build();
	}

}
