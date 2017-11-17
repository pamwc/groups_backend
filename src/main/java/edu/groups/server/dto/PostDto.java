package edu.groups.server.dto;

import edu.groups.server.entity.Post;
import edu.groups.server.utils.CollectionsUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * Created by Dawid on 17.11.2017 at 14:17.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto extends MessageDto {
    private String title;
    private List<CommentDto> comments = new ArrayList<>();
    private boolean commentEnabled = true;

    public static Optional<PostDto> valueOf(Post post) {
        return ofNullable(post).map(PostDto::toPostDto);
    }

    private static PostDto toPostDto(Post post) {
        PostDto postDto = PostDto.builder()
                .title(post.getTitle())
                .commentEnabled(post.isCommentEnabled()).build();
        postDto.setAuthorUserName(post.getAuthorUserName());
        postDto.setContent(post.getContent());
        postDto.setCreationTime(post.getCreationTime());
        postDto.setId(post.getId());
        CollectionsUtil.mapToVisibleValue(post.getComments(), CommentDto::valueOf).ifPresent(postDto::setComments);
        return postDto;
    }
}
