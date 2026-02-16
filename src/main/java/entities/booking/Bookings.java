package entities.booking;

import java.util.List;

public class Bookings {
    private List<Booking> bookings;

    // Constructors
    public Bookings() {}

    public Bookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    // Getters and Setters
    public List<Booking> getBookings() { return bookings; }
    public void setBookings(List<Booking> bookings) { this.bookings = bookings; }
}