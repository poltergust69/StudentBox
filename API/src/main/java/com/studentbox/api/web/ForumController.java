package com.studentbox.api.web;

import com.studentbox.api.common.PermissionEvaluator;
import com.studentbox.api.models.common.PageModel;
import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.post.PostCreationModel;
import com.studentbox.api.models.post.PostModel;
import com.studentbox.api.models.post.reply.PostReplyCreationModel;
import com.studentbox.api.service.forum.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/forum/posts")
@AllArgsConstructor
@CrossOrigin("*")
public class ForumController {
    private final PostService postService;

    @GetMapping
    @ApiOperation(value="Get a page of posts.", response = PostModel[].class)
    public ResponseEntity<PageModel<PostModel>> getPageOfPosts(
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
        postService.create(postCreationModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{postId}")
    @ApiOperation(value="Delete a post.")
    @PreAuthorize("isAuthenticated() && @permissionEvaluator.hasPermissionToAlterPost(principal, #postId)")
    public ResponseEntity deletePost(
            @PathVariable String postId,
            PermissionEvaluator permissionEvaluator
    ){
        postService.delete(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{postId}")
    @ApiOperation(value="Modify a post.")
    @PreAuthorize("isAuthenticated() && @permissionEvaluator.hasPermissionToAlterPost(principal, #postId)")
    public ResponseEntity updatePost(
            @PathVariable String postId,
            PostCreationModel postModel,
            PermissionEvaluator permissionEvaluator
    ){
        postService.update(postId, postModel);
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
    @PostMapping("/{postId}/replies")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity createReplyToPost(
            @PathVariable String postId,
            PostReplyCreationModel postReplyCreationModel
    ){
        postService.createReply(postId, postReplyCreationModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value="Modify a reply.")
    @PatchMapping("/{postId}/replies/{replyId}")
    @PreAuthorize("isAuthenticated() && @permissionEvaluator.hasPermissionToAlterPostReply(principal, #replyId)")
    public ResponseEntity updateReplyToPost(
            @PathVariable String postId,
            @PathVariable String replyId,
            PostReplyCreationModel postReplyModel
    ){
        postService.updateReply(postId, replyId, postReplyModel);
        return ResponseEntity.ok().build();
    }
    @ApiOperation(value="Delete a reply.")
    @DeleteMapping("/{postId}/replies/{replyId}")
    @PreAuthorize("isAuthenticated() && @permissionEvaluator.hasPermissionToAlterPostReply(principal, #replyId)")
    public ResponseEntity deleteReplyToPost(
            @PathVariable String postId,
            @PathVariable String replyId
    ){
        postService.deleteReply(postId, replyId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ApiOperation(value="Like reply.")
    @PutMapping("/{postId}/replies/{replyId}/like")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity likeReplyToPost(
            @PathVariable String postId,
            @PathVariable String replyId
    ){
        postService.likeReply(postId, replyId);
        return ResponseEntity.ok().build();
    }


}
