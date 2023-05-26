package capston.kmouReserveApp.domain.reservation.service;

import capston.kmouReserveApp.domain.reservation.dto.ReservationCheck;
import capston.kmouReserveApp.domain.reservation.dto.ReservationDetails;
import capston.kmouReserveApp.domain.reservation.dto.ReservationRequest;
import capston.kmouReserveApp.domain.reservation.dto.ValidateFindByIdDto;
import capston.kmouReserveApp.domain.reservation.entity.Reservation;
import capston.kmouReserveApp.domain.reservation.entity.Room;
import capston.kmouReserveApp.domain.reservation.repository.ReservationRepository;
import capston.kmouReserveApp.domain.reservation.repository.RoomRepository;
import capston.kmouReserveApp.domain.user.entity.User;
import capston.kmouReserveApp.domain.user.repository.UserRepository;
import capston.kmouReserveApp.exception.ApiException;
import capston.kmouReserveApp.exception.ErrorCode;
import capston.kmouReserveApp.utils.TimeParsingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Transactional
    @Override
    public ReservationDetails register(Long roomId, ReservationRequest.RegisterReservation registerReservation) {
       Reservation reservation = validateCreateRequest(roomId,registerReservation);
       Reservation saveReservation = reservationRepository.save(reservation);
       ReservationDetails reservationDetails = ReservationDetails.mapToDto(saveReservation);
       log.info("register reservationDetails: {}",reservationDetails);

       return reservationDetails;
    }

    private Reservation validateCreateRequest(
            Long roomId,
            ReservationRequest.RegisterReservation registerReservation
    ){
        // 시간 간력 차이 최소 1시간
        validateDiffTime(registerReservation.getStartTime(),registerReservation.getEndTime());

        ValidateFindByIdDto validateFindByIdDto = validateFindById(registerReservation.getUuid(),roomId);

        LocalDateTime startTime = TimeParsingUtils.formatterLocalDateTime(registerReservation.getStartTime());
        LocalDateTime endTime = TimeParsingUtils.formatterLocalDateTime(registerReservation.getEndTime());

        // 예약 인원 체크
        Room findRoom = roomRepository.findById(roomId)
                .orElseThrow(()->{
                    log.error("room 대상이 없습니다. roomId: {}", roomId);
                    throw new ApiException(ErrorCode.NOT_FOUND_ENTITY);
                });
        if(registerReservation.getMemberNum() > findRoom.getMaxNum() || registerReservation.getMemberNum() <= 0){
            throw new ApiException(ErrorCode.INVALID_REQUEST,"올바르지 않은 인원수입니다. 다시 예약해주세요.");
        }

        // 예약 중복 체크
        List<Reservation> findReservations = reservationRepository.findByRoomIdAndTime(roomId,startTime,endTime);

        if(findReservations.size() != 0){
            throw new ApiException(ErrorCode.DUPLICATED_ENTITY,"이미 예약한 사람이 있습니다. 다시 예약해주세요.");
        }

        Reservation reservation = registerReservation.mapToEntity(
                validateFindByIdDto.getUser(),
                validateFindByIdDto.getRoom()
        );

        log.info("registerReservation: {}",reservation);

        // 중복 reservation 체크
        reservationRepository.findByTokenLock(reservation.getReservationToken()).ifPresent( r-> {
            log.error("Same reservationToken is existing, can't make the reservation");
            throw new ApiException(ErrorCode.DUPLICATED_ENTITY,"reservationToken", reservation.getReservationToken());
        });

        return reservation;
    }

    private void validateDiffTime(String stateTime,String endTime){
        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        Date stateDate = null;
        Date endDate = null;

        try{
            stateDate = sft.parse(stateTime);
            endDate = sft.parse(endTime);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }
        long diffTime = (endDate.getTime() - stateDate.getTime())/3600000;

        //if(diffTime < 1 || diffTime > 2)
        if(diffTime != 1){
            throw new ApiException(ErrorCode.INVALID_REQUEST,"예약 시간 1시간 단위만 가능합니다.");
        }

        // 현재 시간보다 이전의 예약이면 예약 불가
        Date now=new Date();
        if(!stateDate.after(now)){
            throw new ApiException(ErrorCode.INVALID_REQUEST,"현재 날짜와 시간 이후로 예약 가능합니다.");
        }
    }

    private ValidateFindByIdDto validateFindById(String uuid,Long roomId){
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(()->{
                    log.error("user 대상이 없습니다. uuid: {}",uuid);
                    throw new ApiException(ErrorCode.NOT_FOUND_USER);
                });

        Room room = roomRepository.findById(roomId)
                .orElseThrow(()->{
                    log.error("room 대상이 없습니다. roomId: {}",roomId);
                    throw new ApiException(ErrorCode.NOT_FOUND_ENTITY);
                });

        return ValidateFindByIdDto.builder()
                .user(user)
                .room(room)
                .build();
    }

    @Transactional
    @Override
    public ReservationDetails getByRoomAndReservationToken(Long roomId, String reservationToken) {
        Reservation reservation = reservationRepository.findByRoomAndToken(roomId,reservationToken)
                .orElseThrow(()->{
                    log.error("reservation 대상이 없습니다. roomId: {}, reservationToken: {}",roomId,reservationToken);
                    throw new ApiException(ErrorCode.NOT_FOUND_ENTITY);
                });
        return ReservationDetails.mapToDto(reservation);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReservationDetails> getByRoomId(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()->{
                    log.error("room 대상이 없습니다. roomId: {}",roomId);
                    throw new ApiException(ErrorCode.NOT_FOUND_ENTITY);
                });
        List<ReservationDetails> collect = reservationRepository.findByRoomId(room.getId()).stream()
                .map(ReservationDetails::mapToDto)
                .collect(Collectors.toList());
        log.info("collect: {}",collect);
        return collect;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReservationDetails> getAllByUserUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(()->{
                    log.error("user 대상이 없습니다. uuid: {}",uuid);
                    throw new ApiException(ErrorCode.NOT_FOUND_USER);
                });
        return reservationRepository.findByUserIds(user.getId()).stream()
                .map(ReservationDetails::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public String updateByToken(Long roomId, String reservationToken, ReservationRequest.UpdateReservation updateReservation) {
        Reservation findReservation = reservationRepository.findByTokenLock(reservationToken)
                .orElseThrow(()->{
                    log.info("reservation 대상이 없습니다. reservationToken: {}",reservationToken);
                    throw new ApiException(ErrorCode.NOT_FOUND_ENTITY);
                });
        validateUpdateRequest(roomId,updateReservation);
        findReservation.update(updateReservation);

        String updatedReservationToken = findReservation.getReservationToken();
        return updatedReservationToken;
    }

    private void validateUpdateRequest(Long roomId,ReservationRequest.UpdateReservation updateReservation){
        validateDiffTime(updateReservation.getStartTime(),updateReservation.getEndTime());
        validateFindById(updateReservation.getUuid(),roomId);
    }

    @Transactional
    @Override
    public void deleteByToken(String uuid, String reservationToken) {
        userRepository.findByUuid(uuid)
                .orElseThrow(()->{
                    log.error("user 대상이 없습니다. uuid: {}",uuid);
                    throw new ApiException(ErrorCode.NOT_FOUND_USER);
                });

        // 해당 등록자인지 체크
        reservationRepository.findByTokenLock(reservationToken)
                .ifPresent(reservation -> {
                    log.info("해당 reservation register: {}",reservation.getUser().getUuid());
                    reservationRepository.deleteById(reservation.getId());
                });
    }

    @Override
    public List<ReservationCheck.ReservationCheckResponse> getByRoomAndDate(Long roomId, String date) {
        return null;
    }
}
