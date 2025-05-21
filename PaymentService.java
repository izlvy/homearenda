package service;


import model.Booking;

public class PaymentService {
    public boolean processPayment(Booking booking, String cardNumber, String expiryDate, String cvv) {
        boolean paymentSuccess = simulatePaymentProcess(booking.getTotalPrice());

        if (paymentSuccess) {
            booking.setStatus(Booking.BookingStatus.CONFIRMED);
        }

        return paymentSuccess;
    }

    private boolean simulatePaymentProcess(double amount) {
        return true;
    }
}
