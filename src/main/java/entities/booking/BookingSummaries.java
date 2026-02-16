package entities.booking;

import java.util.List;

public class BookingSummaries {
    private List<BookingSummary> bookings;

    // Constructors
    public BookingSummaries() {}

    public BookingSummaries(List<BookingSummary> bookings) {
        this.bookings = bookings;
    }

    // Getters and Setters
    public List<BookingSummary> getBookings() { return bookings; }
    public void setBookings(List<BookingSummary> bookings) { this.bookings = bookings; }
}