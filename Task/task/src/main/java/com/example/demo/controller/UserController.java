package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	TaskService taskService;
	
	@GetMapping("/gettasks")
	public ResponseEntity<List<Task>> getAllTask(){
		return ResponseEntity.ok().body(taskService.getAllTasks());
	}
	
	@GetMapping("gettask/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable long taskId) {
		return ResponseEntity.ok().body(taskService.getTaskById(taskId));
	}
	
	@PutMapping("/status/{taskId}")
	public ResponseEntity<Task> updateStatus(@PathVariable int taskId,@RequestBody Task task) {
		return ResponseEntity.ok().body(taskService.updateStatus(taskId,task.getStatus()));
	}
	@GetMapping("gettaskByUserId/{userId}")
	public ResponseEntity<Task> getTaskByUserId(@PathVariable long userId) {
		return ResponseEntity.ok().body(taskService.getTaskByUserId(userId));
	}
	

}
