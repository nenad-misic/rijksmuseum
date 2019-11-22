package Rijksmuseum.Model;

public class RateEvent extends Event {
    private int rating;


    public RateEvent() {
    }

    public RateEvent(long eventId, String phone, long timeStamp, String type, Beacon beacon, int rating) {
        super(eventId, phone, timeStamp, type, beacon);
        this.rating = rating;
    }

    public RateEvent(Event that) {
        super(that);
        if(that.getClass().equals(this.getClass())){
            this.rating = ((RateEvent)that).rating;
        }

    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RateEvent{" +
                "rating=" + rating +
                "} " + super.toString();
    }
}
