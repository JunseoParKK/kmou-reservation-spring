package capston.kmouReserveApp.domain.reservation.repository;

import capston.kmouReserveApp.domain.reservation.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
