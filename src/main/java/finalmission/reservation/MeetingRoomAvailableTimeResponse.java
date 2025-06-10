package finalmission.reservation;

import java.time.LocalTime;

public record MeetingRoomAvailableTimeResponse(
    Long timeId,
    LocalTime time,
    boolean isReserved
) {

}
