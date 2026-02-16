package entities.booking;

public class BookingSummary {
    private BookingDates bookingDates;

    // Constructors
    public BookingSummary() {}

    public BookingSummary(BookingDates bookingDates) {
        this.bookingDates = bookingDates;
    }

    // Getters and Setters
    public BookingDates getBookingDates() { return bookingDates; }
    public void setBookingDates(BookingDates bookingDates) { this.bookingDates = bookingDates; }
}