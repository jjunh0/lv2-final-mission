package finalmission.meetingroom;

import finalmission.client.NameClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;
    private final NameClient nameClient;

    public MeetingRoomResponse create() {
        String meetingRoomName = nameClient.getRandomName();
        MeetingRoom meetingRoom = new MeetingRoom(meetingRoomName);
        meetingRoomRepository.save(meetingRoom);
        return new MeetingRoomResponse(meetingRoom.getName());
    }
}
