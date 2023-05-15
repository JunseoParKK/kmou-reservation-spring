package capston.kmouReserveApp.domain.reservation.reservation;

import capston.kmouReserveApp.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
