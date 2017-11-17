package edu.groups.server.controller;

import edu.groups.server.dto.AddEditPostDto;
import edu.groups.server.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dawid on 14.11.2017 at 23:01.
 */
@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/{groupId}/posts")
    @PreAuthorize("hasRole(T(edu.groups.server.configuration.security.UserRole).ADMIN.name)")
    public Long createNewPost(@PathVariable Long groupId, @RequestBody AddEditPostDto newPostDto) {
        return postService.createPost(groupId, newPostDto);
    }

    @PutMapping("/posts/{postId}")
    @PreAuthorize("hasRole(T(edu.groups.server.configuration.security.UserRole).ADMIN.name)")
    public void editPost(@PathVariable Long postId, @RequestBody AddEditPostDto editPostDto) {
        postService.editPost(postId, editPostDto);
    }

    @DeleteMapping("/posts/{postId}")
    @PreAuthorize("hasRole(T(edu.groups.server.configuration.security.UserRole).ADMIN.name)")
    public void removePost(@PathVariable Long postId) {
        postService.removePost(postId);
    }
}
