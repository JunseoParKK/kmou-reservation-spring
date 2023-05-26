package capston.kmouReserveApp.domain.reservation.repository;

import capston.kmouReserveApp.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("select r from Reservation r left join fetch r.room where r.room.id =:roomId "+
        "and r.startTime = :sTime and r.endTime = :eTime")
    List<Reservation> findByRoomIdAndTime(@Param("roomId") Long roomId,
                                          @Param("sTime") LocalDateTime sTime,
                                          @Param("eTime") LocalDateTime eTime);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select r from Reservation r where r.reservationToken = :rToken")
    Optional<Reservation> findByTokenLock(@Param("rToken") String rToken);

    @Query("select r from Reservation r left join fetch r.room where r.room.id = :roomId "+
        "and r.reservationToken = :reservationToken")
    Optional<Reservation> findByRoomAndToken(
            @Param("roomId") Long roomId,
            @Param("reservationToken") String reservationToken);

    @Query("select r from Reservation r left join fetch r.room where r.room.id = :roomId")
    List<Reservation> findByRoomId(
            @Param("roomId") Long roomId);

    @Query("select r from Reservation r left join fetch r.user where r.user.id = :userIds")
    List<Reservation> findByUserIds(
            @Param("userIds") Long userIds);

    @Query("select r from Reservation r where DATE(r.startTime) = :dateFormat and r.room.id = :roomId")
    List<Reservation> findByDate(
            @Param("roomId") Long roomId,
            @Param("dateFormat") Date dateFormat);
}
