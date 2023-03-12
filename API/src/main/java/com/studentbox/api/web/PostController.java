package com.studentbox.api.web;

import com.studentbox.api.models.common.PaginationModel;
import com.studentbox.api.models.post.PostCreationModel;
import com.studentbox.api.models.post.PostModel;
import com.studentbox.api.models.post.PostModificationModel;
import com.studentbox.api.models.reply.PostReplyCreationModel;
import com.studentbox.api.models.reply.PostReplyModificationModel;
import com.studentbox.api.models.role.RoleModel;
import com.studentbox.api.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping
    @ApiOperation(value="Get a page of posts.", response = RoleModel.class)
    public ResponseEntity<List<PostModel>> getPageOfPosts(
            PaginationModel paginationModel
        ){
        return ResponseEntity.ok(postService.getPage(paginationModel));
    }

    @PostMapping
    @ApiOperation(value="Create a new post.", response = RoleModel.class)
    public ResponseEntity createPost(
            PostCreationModel postCreationModel
    ){
        postService.create(postCreationModel);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    @ApiOperation(value="Delete a post.", response = RoleModel.class)
    public ResponseEntity deletePost(
            @PathVariable String postId
    ){
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{postId}")
    @ApiOperation(value="Modify a post.", response = RoleModel.class)
    public ResponseEntity updatePost(
            @PathVariable String postId,
            PostModificationModel postModificationModel
    ){
        postService.update(postId, postModificationModel);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}/like")
    @ApiOperation(value="Like a post.", response = RoleModel.class)
    public ResponseEntity likePost(
            @PathVariable String postId
    ){
        postService.like(postId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Add a reply to a post.", response = RoleModel.class)
    @PostMapping("/{postId}/reply")
    public ResponseEntity createReplyToPost(
            @PathVariable String postId,
            PostReplyCreationModel postReplyCreationModel
    ){
        postService.createReply(postId, postReplyCreationModel);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Modify a reply.", response = RoleModel.class)
    @PatchMapping("/{postId}/reply/{replyId}")
    public ResponseEntity updateReplyToPost(
            @PathVariable String postId,
            @PathVariable String replyId,
            PostReplyModificationModel postReplyModificationModel
    ){
        postService.updateReply(postId, replyId, postReplyModificationModel);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Delete a reply.", response = RoleModel.class)
    @DeleteMapping("/{postId}/reply/{replyId}")
    public ResponseEntity deleteReplyToPost(
            @PathVariable String postId,
            @PathVariable String replyId
    ){
        postService.deleteReply(postId, replyId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value="Like reply.", response = RoleModel.class)
    @PatchMapping("/{postId}/reply/{replyId}/like")
    public ResponseEntity likeReplyToPost(
            @PathVariable String postId,
            @PathVariable String replyId
    ){
        postService.likeReply(postId, replyId);
        return ResponseEntity.ok().build();
    }


}
