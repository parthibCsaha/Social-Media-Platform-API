package org.example.socialmedia.controller;

import org.example.socialmedia.entities.Comment;
import org.example.socialmedia.entities.Post;
import org.example.socialmedia.entities.User;
import org.example.socialmedia.repo.CommentRepository;
import org.example.socialmedia.repo.PostRepository;
import org.example.socialmedia.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return ResponseEntity.ok().body(comments);
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@PathVariable Long postId, @RequestBody Comment comment, Principal principal) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        User author = userRepository.findByUsername(principal.getName()).orElseThrow();
        comment.setPost(post);
        comment.setAuthor(author);
        comment.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(commentRepository.save(comment));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, Principal principal) {
        return commentRepository
                .findById(commentId)
                .map(new Function<Comment, ResponseEntity<Comment>>() {

                    @Override
                    public ResponseEntity<Comment> apply(Comment comment) {
                        if (!comment.getAuthor().getUsername().equals(principal.getName())) {
                            return ResponseEntity.status(403).build();
                        }
                        commentRepository.delete(comment);
                        return ResponseEntity.ok().build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }


}











