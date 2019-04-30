package anton.nagptrackingsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import anton.nagptrackingsystem.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
