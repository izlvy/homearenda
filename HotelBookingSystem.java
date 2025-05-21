package service;

import model.Booking;
import model.Room;
import model.User;

import java.time.LocalDate;

public class HotelBookingSystem {
    public static void main(String[] args) {

        RoomService roomService = new RoomService();
        BookingService bookingService = new BookingService(roomService);
        PaymentService paymentService = new PaymentService();


        Room room1 = new Room(1L, "101", Room.RoomType.SINGLE, 100.0);
        Room room2 = new Room(2L, "102", Room.RoomType.DOUBLE, 150.0);
        roomService.addRoom(room1);
        roomService.addRoom(room2);


        User user = new User(1L, "john_doe", "password", "John Doe", "john@example.com", "+998901234567");

        try {

            LocalDate checkIn = LocalDate.now();
            LocalDate checkOut = checkIn.plusDays(2);

            Booking booking = bookingService.createBooking(room1, user, checkIn, checkOut);

            boolean paymentSuccess = paymentService.processPayment(
                    booking, "1234-5678-9012-3456", "12/25", "123"
            );

            if (paymentSuccess) {
                System.out.println("Bron qilish muvaffaqiyatli yakunlandi!");
                System.out.println("Bron raqami: " + booking.getId());
                System.out.println("Umumiy narx: $" + booking.getTotalPrice());
            }

        } catch (Exception e) {
            System.out.println("Xatolik yuz berdi: " + e.getMessage());
        }
    }
}
