package edu.groups.server.repository;

import edu.groups.server.entity.GroupEntity;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * Created by Dawid on 11.11.2017 at 19:56.
 */
@Repository
public interface GroupRepository extends CrudRepository<GroupEntity, Long> {
    List<GroupEntity> findAllByMembersUserNamesOrAdminsUserNamesAndVisibleTrue(String memberUserName,
                                                                               String adminUserName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    GroupEntity findByJoinCodeAndVisibleIsTrue(String joinCode);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    GroupEntity findByIdAndVisibleIsTrue(Long id);
}
