package pojos;

public class BookingDatesPojo {

    // 1. tum keyler icin private variables olusturduk
    private String checkin;
    private String checkout;


    // 2. Tum parametrelerle ve paramatresiz cons. olusturduk
    public BookingDatesPojo() {

    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // 3. Public Getter ve Setter methodlarini olustur

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    // 4. toString() method'unu olustur


    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
