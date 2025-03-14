package org.example.socialmedia.repo;

import org.example.socialmedia.entities.Comment;
import org.example.socialmedia.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long post_id);
}
