package entities.booking;

import java.util.Optional;

public class Booking {
    private int bookingid;
    private int roomid;
    private String firstname;
    private String lastname;
    private boolean depositpaid;
    private BookingDates bookingDates;
    private Optional<String> email;
    private Optional<String> phone;

    // Constructors
    public Booking() {}

    public Booking(int bookingid, int roomid, String firstname, String lastname, 
                   boolean depositpaid, BookingDates bookingDates) {
        this.bookingid = bookingid;
        this.roomid = roomid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.depositpaid = depositpaid;
        this.bookingDates = bookingDates;
    }

    // Getters and Setters
    public int getBookingid() { return bookingid; }
    public void setBookingid(int bookingid) { this.bookingid = bookingid; }

    public int getRoomid() { return roomid; }
    public void setRoomid(int roomid) { this.roomid = roomid; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public boolean isDepositpaid() { return depositpaid; }
    public void setDepositpaid(boolean depositpaid) { this.depositpaid = depositpaid; }

    public BookingDates getBookingDates() { return bookingDates; }
    public void setBookingDates(BookingDates bookingDates) { this.bookingDates = bookingDates; }

    public Optional<String> getEmail() { return email; }
    public void setEmail(String email) { this.email = Optional.ofNullable(email); }

    public Optional<String> getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = Optional.ofNullable(phone); }

    @Override
    public String toString() {
        return "Booking{" +
                "roomid=" + roomid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", depositpaid=" + depositpaid +
                ", bookingDates=" + bookingDates +
                '}';
    }
}