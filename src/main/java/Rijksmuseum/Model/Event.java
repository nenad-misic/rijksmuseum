package Rijksmuseum.Model;

import java.util.Objects;

public class Event {
    private long eventId;
    private String phone;
    private long timeStamp;
    private String type;
    private Beacon beacon;

    public Event() {
    }

    public Event(long eventId, String phone, long timeStamp, String type, Beacon beacon) {
        this.eventId = eventId;
        this.phone = phone;
        this.timeStamp = timeStamp;
        this.type = type;
        this.beacon = beacon;
    }


    public Event(Event that){
        this.eventId = that.eventId;
        this.phone = that.phone;
        this.timeStamp = that.timeStamp;
        this.type = that.type;
        this.beacon = that.beacon;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", phone='" + phone + '\'' +
                ", timeStamp=" + timeStamp +
                ", type='" + type + '\'' +
                ", beacon=" + beacon.getBeaconId() +
                '}';
    }
}
