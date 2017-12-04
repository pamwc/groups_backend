package edu.groups.server.service;

import edu.groups.server.annotation.AppService;
import edu.groups.server.dto.AddEditPostDto;
import edu.groups.server.entity.GroupEntity;
import edu.groups.server.entity.Post;
import edu.groups.server.utils.UserContext;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

/**
 * Created by Dawid on 17.11.2017 at 00:11.
 */

@RequiredArgsConstructor
@AppService
@Transactional
public class PostService extends MessageService {

    public Post createPost(Long groupId, AddEditPostDto newPost) {
        GroupEntity group = getGroupOrThrow(groupId);
        throwExceptionIfUserIsNotGroupAdmin(group);
        Post post = new Post(UserContext.getUsername(), newPost.getContent(), newPost.getTitle(),
                newPost.isCommentEnabled(), group);
        return postRepository.save(post);
    }

    public void editPost(Long postId, AddEditPostDto editPostDto) {
        Post post = getPostOrThrow(postId);
        throwExceptionIfUserIsNotAuthor(post);
        updatePost(editPostDto, post);
    }

    private void updatePost(AddEditPostDto editPostDto, Post post) {
        post.setTitle(editPostDto.getTitle());
        post.setCommentEnabled(editPostDto.isCommentEnabled());
        post.setContent(editPostDto.getContent());
        postRepository.save(post);
    }

    public void removePost(Long postId) {
        Post post = getPostOrThrow(postId);
        throwExceptionIfUserIsNotAuthor(post);
        post.setVisible(false);
        postRepository.save(post);
        notificationService.removeNotificationByPostId(postId);
    }
}
