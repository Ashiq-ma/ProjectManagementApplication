package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	Optional<Task> findByUserId(long userId);

	Optional<List<Task>> findByProjectId(long projectId);

}
