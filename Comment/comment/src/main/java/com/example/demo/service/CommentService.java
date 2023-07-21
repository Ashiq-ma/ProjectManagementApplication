package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	public Comment addComment(Comment comment) {
		return commentRepo.save(comment);
		
	}
	
	public Comment getCommentById(long commentId) {
		Comment c=commentRepo.findById(commentId).orElseThrow(()->new NoSuchElementException());
		return c;
	}
	
	public List<Comment> getAllComments(){
		return commentRepo.findAll();
	}
	
	public void deleteComment(long commentId) {
		commentRepo.deleteById(commentId);
	}
	
	public Comment getCommentByTaskId(long taskId) {
		Comment c=commentRepo.findByTaskId(taskId).orElseThrow(()->new NoSuchElementException());
		return c;
	}
	
	public void deleteCommentByTask(long taskId) {
		commentRepo.deleteById(taskId);
	}

}
