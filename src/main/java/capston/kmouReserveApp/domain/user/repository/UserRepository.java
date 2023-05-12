package capston.kmouReserveApp.domain.user.repository;

import capston.kmouReserveApp.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
