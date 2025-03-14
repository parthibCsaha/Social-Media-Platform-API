package org.example.socialmedia.controller;

import org.example.socialmedia.entities.Like;
import org.example.socialmedia.entities.Post;
import org.example.socialmedia.entities.User;
import org.example.socialmedia.repo.LikeRepository;
import org.example.socialmedia.repo.PostRepository;
import org.example.socialmedia.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/posts/{postId}/likes")
public class LikeController {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Like> addLike(@PathVariable Long postId, Principal principal) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Like like = new Like();
        like.setPost(post);
        like.setUser(user);
        return ResponseEntity.ok(likeRepository.save(like));
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<?> removeLike(@PathVariable Long postId, @PathVariable Long likeId, Principal principal) {
        return likeRepository
                .findById(likeId)
                .map(like -> {
                    if (!like.getUser().getUsername().equals(principal.getName())) {
                        return ResponseEntity.status(403).build();
                    }
                    likeRepository.delete(like);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
