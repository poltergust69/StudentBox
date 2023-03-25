package com.studentbox.api.web;

import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.post.PostCreationModel;
import com.studentbox.api.models.post.PostModel;
import com.studentbox.api.models.post.PostModificationModel;
import com.studentbox.api.models.reply.PostReplyCreationModel;
import com.studentbox.api.models.reply.PostReplyModificationModel;
import com.studentbox.api.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    @ApiOperation(value="Get a page of posts.", response = PostModel[].class)
    public ResponseEntity<List<PostModel>> getPageOfPosts(
            PaginationModel paginationModel
        ){
        return ResponseEntity.ok(postService.getPage(paginationModel));
    }

    @GetMapping("/{postId}")
    @ApiOperation(value="Get the post with the ID requested.", response = PostModel.class)
    public ResponseEntity<PostModel> getPost(
            @PathVariable String postId
    ){
        return ResponseEntity.ok(postService.findBasicById(postId));
    }


    @PostMapping
    @ApiOperation(value="Create a new post.")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity createPost(
            PostCreationModel postCreationModel
    ){
        try{
            postService.create(postCreationModel);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    @ApiOperation(value="Delete a post.")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deletePost(
            @PathVariable String postId
    ){
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{postId}")
    @ApiOperation(value="Modify a post.")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updatePost(
            @PathVariable String postId,
            PostModificationModel postModificationModel
    ){
        postService.update(postId, postModificationModel);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}/like")
    @ApiOperation(value="Like a post.")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity likePost(
            @PathVariable String postId
    ){
        postService.like(postId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Add a reply to a post.")
    @PostMapping("/{postId}/reply")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity createReplyToPost(
            @PathVariable String postId,
            PostReplyCreationModel postReplyCreationModel
    ){
        postService.createReply(postId, postReplyCreationModel);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Modify a reply.")
    @PatchMapping("/{postId}/reply/{replyId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updateReplyToPost(
            @PathVariable String postId,
            @PathVariable String replyId,
            PostReplyModificationModel postReplyModificationModel
    ){
        postService.updateReply(postId, replyId, postReplyModificationModel);
        return ResponseEntity.ok().build();
    }
    @ApiOperation(value="Delete a reply.")
    @DeleteMapping("/{postId}/reply/{replyId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity deleteReplyToPost(
            @PathVariable String postId,
            @PathVariable String replyId
    ){
        postService.deleteReply(postId, replyId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Like reply.")
    @PatchMapping("/{postId}/reply/{replyId}/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity likeReplyToPost(
            @PathVariable String postId,
            @PathVariable String replyId
    ){
        postService.likeReply(postId, replyId);
        return ResponseEntity.ok().build();
    }


}
