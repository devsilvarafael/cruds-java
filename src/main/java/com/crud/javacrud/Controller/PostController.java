package com.crud.javacrud.Controller;

import com.crud.javacrud.Model.Post;
import com.crud.javacrud.Model.User;
import com.crud.javacrud.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    public PostRepository postRepository;

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        List<Post> users = postRepository.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        Optional<Post> postExists = postRepository.findById(id);

        if(postExists.isPresent()) {
            return ResponseEntity.ok(postExists);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        Post newPost = postRepository.save(post);

        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, Post post) {
        Optional<Post> postExists = postRepository.findById(id);

        if(postExists.isPresent()) {
            Post updatedPost = postExists.get();

            updatedPost.setContent(post.getContent());
            updatedPost.setCategory(post.getCategory());

            Post savedPost = postRepository.save(updatedPost);

            return ResponseEntity.ok(savedPost);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            postRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
