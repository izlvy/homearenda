package service;

import model.Room;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomService {
    private List<Room> rooms = new ArrayList<>();

    public List<Room> getAllRooms() {
        return rooms;
    }

    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut, double maxPrice) {
        return rooms.stream()
                .filter(Room::isAvailable)
                .filter(room -> room.getPricePerNight() <= maxPrice)
                .collect(Collectors.toList());
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void updateRoomAvailability(Room room, boolean isAvailable) {
        room.setAvailable(isAvailable);

    }
}