package model;

import java.time.LocalDate;

public class Booking {
    private Long id;
    private Room room;
    private User user;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus status;
    private double totalPrice;

    public enum BookingStatus {
        PENDING, CONFIRMED, CANCELLED
    }

    public Booking(Long id, Room room, User user, LocalDate checkInDate, LocalDate checkOutDate) {
        this.id = id;
        this.room = room;
        this.user = user;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = BookingStatus.PENDING;
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        long days = java.time.temporal.ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        this.totalPrice = days * room.getPricePerNight();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;

    }
}