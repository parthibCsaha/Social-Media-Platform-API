package org.example.socialmedia.controller;

import org.example.socialmedia.entities.Post;
import org.example.socialmedia.entities.User;
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
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post, Principal principal) {
        String username = principal.getName();
        User author = userRepository.findByUsername(username).orElseThrow();
        post.setAuthor(author);
        post.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(postRepository.save(post));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post, Principal principal) {
        return postRepository
                .findById(id)
                .map(new Function<Post, ResponseEntity<Post>>() {
                    @Override
                    public ResponseEntity<Post> apply(Post post) {
                        if (!post.getAuthor().getUsername().equals(principal.getName())) {
                            return ResponseEntity.status(403).build();
                        }
                        post.setContent(post.getContent());
                        return ResponseEntity.ok(postRepository.save(post));
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id, Principal principal) {
        return postRepository
                .findById(id)
                .map(new Function<Post, ResponseEntity<Post>>() {
                    @Override
                    public ResponseEntity<Post> apply(Post post) {
                        if (!post.getAuthor().getUsername().equals(principal.getName())) {
                            return ResponseEntity.status(403).build();
                        }
                        postRepository.delete(post);
                        return ResponseEntity.ok().build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

}


























