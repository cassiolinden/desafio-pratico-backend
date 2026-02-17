package entities.booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class BookingDates {
    private LocalDate checkin;
    private LocalDate checkout;

    // Constructors
    public BookingDates(LocalDate checkin, LocalDate checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // Getters and Setters
    public LocalDate getCheckin() { return checkin; }
    public void setCheckin(LocalDate checkin) { this.checkin = checkin; }

    public LocalDate getCheckout() { return checkout; }
    public void setCheckout(LocalDate checkout) { this.checkout = checkout; }

    public HashMap<String, String> getBookingDates() {
        HashMap<String, String> dates = new HashMap<>();
        dates.put("checkin", dateBookingConverter(checkin));
        dates.put("checkout", dateBookingConverter(checkout));
        return dates;
    }

    @Override
    public String toString() {
        return "BookingDates{" +
                "checkin=" + checkin +
                ", checkout=" + checkout +
                '}';
    }

    private String dateBookingConverter(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}