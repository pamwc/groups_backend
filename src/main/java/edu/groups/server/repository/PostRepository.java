package edu.groups.server.repository;

import edu.groups.server.entity.Post;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

/**
 * Created by Dawid on 17.11.2017 at 00:10.
 */

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Post findByIdAndVisibleIsTrue(Long id);
}
