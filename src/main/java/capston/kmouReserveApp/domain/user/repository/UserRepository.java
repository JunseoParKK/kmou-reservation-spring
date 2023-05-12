package capston.kmouReserveApp.domain.user.repository;

import capston.kmouReserveApp.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u from User u where u.id =: userId")
    List<UUID> findUserByIds(@Param("userId") UUID userId);
}
