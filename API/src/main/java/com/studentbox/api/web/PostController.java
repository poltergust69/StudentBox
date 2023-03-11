package com.studentbox.api.web;

import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.post.PostModel;
import com.studentbox.api.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping
    public ResponseEntity<List<PostModel>> getPageOfPosts(
            PaginationModel paginationModel
        ){
        return ResponseEntity.ok(postService.getPageOfPosts(paginationModel));
    }

    @PostMapping
    public ResponseEntity createPost(
            @RequestParam String title,
            @RequestParam String content,
            String principal
    ){
        postService.createNewPost(title, content, principal);
        return ResponseEntity.ok().build();
    }
}
