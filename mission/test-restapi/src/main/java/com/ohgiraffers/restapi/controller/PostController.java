package com.ohgiraffers.restapi.controller;

import com.ohgiraffers.restapi.domain.entity.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/swagger")
public class PostController {

    private List<Post> posts;

    @PostMapping("/posts")
    public ResponseEntity<?> regist(@RequestBody Post newPost) {

        int lastPostId = posts.get(posts.size() - 1).getPostId();

        newPost.setPostId(lastPostId + 1);

        posts.add(newPost);

        return ResponseEntity
                .created(URI.create("/swagger/posts/" + posts.get(posts.size() -1).getPostId())).build();
    }

}
