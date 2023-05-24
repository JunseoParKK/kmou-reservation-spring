package capston.kmouReserveApp.domain.user.repository;

import capston.kmouReserveApp.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.id =: userId")
    List<Long> findUserByIds(@Param("userId") Long userId);

    @Query("select u from User u where u.uuid = :uid")
    Optional<User> findByUuid(@Param("uid") String uuid);

    @Modifying
    @Query("update User u set u.name = :name, u.email = :email, u.phoneNum = :phoneNum "+
    "where u.uuid = :uid")
    void update(@Param("uid") String uid,
                          @Param("name")String name,
                          @Param("email")String email,
                          @Param("phoneNum")String phoneNum);
}
