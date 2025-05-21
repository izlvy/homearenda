package service;

import model.Booking;
import model.Room;
import model.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingService {
    private List<Booking> bookings = new ArrayList<>();
    private RoomService roomService;

    public BookingService(RoomService roomService) {
        this.roomService = roomService;
    }

    public Booking createBooking(Room room, User user, LocalDate checkIn, LocalDate checkOut) {
        if (!isRoomAvailable(room, checkIn, checkOut)) {
            throw new IllegalStateException("Xona ushbu sanalar oralig'ida band");
        }

        Booking booking = new Booking(generateBookingId(), room, user, checkIn, checkOut);
        bookings.add(booking);
        roomService.updateRoomAvailability(room, false);
        return booking;
    }

    public void cancelBooking(Booking booking) {
        booking.setStatus(Booking.BookingStatus.CANCELLED);
        roomService.updateRoomAvailability(booking.getRoom(), true);
    }

    private boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {
        return bookings.stream()
                .filter(b -> b.getRoom().equals(room))
                .filter(b -> b.getStatus() == Booking.BookingStatus.CONFIRMED)
                .noneMatch(b -> (checkIn.isBefore(b.getCheckOutDate()) &&
                        checkOut.isAfter(b.getCheckInDate())));
    }

    private Long generateBookingId() {
        return System.currentTimeMillis();
    }
}