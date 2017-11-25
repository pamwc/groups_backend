package edu.groups.server.repository;

import edu.groups.server.entity.Comment;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

/**
 * Created by Dawid on 24.11.2017 at 19:20.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Comment findByIdAndVisibleIsTrue(Long id);
}
