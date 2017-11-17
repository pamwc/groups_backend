package edu.groups.server.dto;

import edu.groups.server.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * Created by Dawid on 17.11.2017 at 14:21.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto extends MessageDto {
    private CommentDto child;

    public CommentDto(Long id, String authorUserName, Date creationTime, String content) {
        super(id, authorUserName, creationTime, content);
    }

    public static Optional<CommentDto> valueOf(Comment commentEntity) {
        Optional<Comment> comment = ofNullable(commentEntity);
        Optional<CommentDto> commentDto = comment.map(CommentDto::toCommentDto);
        commentDto.ifPresent(that -> valueOf(commentEntity.getChild()).ifPresent(that::setChild));
        return commentDto;
    }

    private static CommentDto toCommentDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getAuthorUserName(), comment.getCreationTime(),
                comment.getContent());
    }
}
