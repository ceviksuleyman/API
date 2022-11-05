package pojos.bookingPojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

// Bu annotation aynı levelde bilinmeyen verileri  görmezden gelerek diğer verilerin
// bu class tipinde Pojo class'a çevrilmesine yarıyor.
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponseBodyPojo {

    private Integer bookingid;
    private BookingPojo booking;


    public BookingResponseBodyPojo() {
    }

    public BookingResponseBodyPojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingPojoParent{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
