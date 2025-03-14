package org.example.socialmedia.controller;

import org.example.socialmedia.entities.User;
import org.example.socialmedia.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/follow")
    public ResponseEntity<?> followUser(@PathVariable Long id, @RequestHeader("username") String currentUsername) {
        User currentUser = userRepository.findByUsername(currentUsername).orElse(null);
        User userToFollow = userRepository.findById(id).orElse(null);
        if (currentUser == null || userToFollow == null) {
            return ResponseEntity.badRequest().body("Invalid user information");
        }
        currentUser.getFollowing().add(userToFollow);
        userToFollow.getFollowers().add(currentUser);
        userRepository.save(currentUser);
        userRepository.save(userToFollow);
        return ResponseEntity.ok("Successfully followed user");
    }

    @DeleteMapping("/{id}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable Long id, @RequestHeader("username") String currentUsername) {
        User currentUser = userRepository.findByUsername(currentUsername).orElse(null);
        User userToUnfollow = userRepository.findById(id).orElse(null);
        if (currentUser == null || userToUnfollow == null) {
            return ResponseEntity.badRequest().body("Invalid user information");
        }
        currentUser.getFollowing().remove(userToUnfollow);
        userToUnfollow.getFollowers().remove(currentUser);
        userRepository.save(currentUser);
        userRepository.save(userToUnfollow);
        return ResponseEntity.ok("Unfollowed successfully");
    }

}
