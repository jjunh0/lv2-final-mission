package finalmission.reservation;

import finalmission.meetingroom.MeetingRoom;
import finalmission.meetingroom.MeetingRoomRepository;
import finalmission.member.Member;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationTimeRepository reservationTimeRepository;
    private final ReservationRepository reservationRepository;
    private final MeetingRoomRepository meetingRoomRepository;

    public List<MeetingRoomAvailableTimeResponse> getAvailableTimes(Long meetingRoomId,
        LocalDate date) {
        List<ReservationTime> reservationTimes = reservationTimeRepository.findAll();
        List<Reservation> reservations = reservationRepository.findByMeetingRoomIdAndDate(
            meetingRoomId, date);
        return reservationTimes.stream()
            .map(reservationTime ->
                new MeetingRoomAvailableTimeResponse(
                    reservationTime.getId(),
                    reservationTime.getStartAt(),
                    hasReservationInTime(reservationTime, reservations)
                )).toList();
    }

    private static boolean hasReservationInTime(ReservationTime reservationTime,
        List<Reservation> reservations) {
        return reservations.stream()
            .anyMatch(reservation ->
                reservation.isReservationTime(reservationTime));
    }

    public void create(Member member, ReservationRequest request) {
        validateHasReservation(request.meetingRoomId(), request.date(), request.timeId());

        MeetingRoom meetingRoom = meetingRoomRepository.findById(request.meetingRoomId())
            .orElseThrow(() -> new IllegalArgumentException("해당하는 회의실이 없습니다."));
        ReservationTime reservationTime = reservationTimeRepository.findById(request.timeId())
            .orElseThrow(() -> new IllegalArgumentException("해당하는 시간이 없습니다."));

        Reservation reservation = new Reservation(
            member, meetingRoom, reservationTime, request.date()
        );

        reservationRepository.save(reservation);
    }

    private void validateHasReservation(Long meetingRoomId, LocalDate date, Long timeId) {
        if(reservationRepository.existsByMeetingRoomIdAndDateAndTimeId(meetingRoomId, date, timeId)) {
            throw new IllegalArgumentException("해당 시간에 예약이 존재합니다");
        }
    }

    public List<ReservationResponse> getByMember(Member member) {
        return reservationRepository.findAllByMemberId(member.getId())
            .stream().map(reservation -> new ReservationResponse(
                reservation.getId(),
                reservation.getMeetingRoom().getName(),
                reservation.getTime().getStartAt(),
                reservation.getDate()
            )).toList();
    }
}
