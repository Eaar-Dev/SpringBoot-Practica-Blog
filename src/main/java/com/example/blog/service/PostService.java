package com.example.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post save(Post post, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        post.setAuthor(author);
        return postRepository.save(post);
    }

    public void delete(Long id, String username) {
        Post post = postRepository.findById(id).orElseThrow();

        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("No autorizado");
        }

        postRepository.delete(post);
    }

}
