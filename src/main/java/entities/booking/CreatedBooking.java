package entities.booking;

public class CreatedBooking {
    private int bookingid;
    private Booking booking;

    // Constructors
    public CreatedBooking() {}

    public CreatedBooking(int bookingid, Booking booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    // Getters and Setters
    public int getBookingid() { return bookingid; }
    public void setBookingid(int bookingid) { this.bookingid = bookingid; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }

    @Override
    public String toString() {
        return "CreatedBooking{" +
                "bookingid=" + bookingid +
                ", booking=" + booking.toString() +
                '}';
    }
}