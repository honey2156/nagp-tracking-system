package com.nagarro.nagptrackingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.nagptrackingsystem.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
