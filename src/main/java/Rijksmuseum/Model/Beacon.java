package Rijksmuseum.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Beacon {
    private long beaconId;
    private String beaconName;
    private float beaconLocationX;
    private float beaconLocationY;
    private String beaconAudioFilepath;
    private List<Event> events;

    public Beacon() {
        this.events = new ArrayList<Event>();
    }

    public Beacon(long beaconId, String beaconName, float beaconLocationX, float beaconLocationY, String beaconAudioFilepath) {
        this.beaconId = beaconId;
        this.beaconName = beaconName;
        this.beaconLocationX = beaconLocationX;
        this.beaconLocationY = beaconLocationY;
        this.beaconAudioFilepath = beaconAudioFilepath;
        this.events = new ArrayList<>();
    }

    public Beacon(Beacon that){
        this.beaconId = that.beaconId;
        this.beaconName = that.beaconName;
        this.beaconLocationX = that.beaconLocationX;
        this.beaconLocationY = that.beaconLocationY;
        this.beaconAudioFilepath = that.beaconAudioFilepath;
        this.events = new ArrayList<>();
        for (Event e : that.events){
            this.events.add(new Event(e));
        }
    }

    public long getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(long beaconId) {
        this.beaconId = beaconId;
    }

    public String getBeaconName() {
        return beaconName;
    }

    public void setBeaconName(String beaconName) {
        this.beaconName = beaconName;
    }

    public float getBeaconLocationX() {
        return beaconLocationX;
    }

    public void setBeaconLocationX(float beaconLocationX) {
        this.beaconLocationX = beaconLocationX;
    }

    public float getBeaconLocationY() {
        return beaconLocationY;
    }

    public void setBeaconLocationY(float beaconLocationY) {
        this.beaconLocationY = beaconLocationY;
    }

    public String getBeaconAudioFilepath() {
        return beaconAudioFilepath;
    }

    public void setBeaconAudioFilepath(String beaconAudioFilepath) {
        this.beaconAudioFilepath = beaconAudioFilepath;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event e){
        this.events.add(e);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beacon beacon = (Beacon) o;
        return beaconId == beacon.beaconId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(beaconId);
    }

    @Override
    public String toString() {
        return "Beacon{" +
                "beaconId=" + beaconId +
                ", beaconName='" + beaconName + '\'' +
                ", beaconLocationX=" + beaconLocationX +
                ", beaconLocationY=" + beaconLocationY +
                ", beaconAudioFilepath='" + beaconAudioFilepath + '\'' +
                ", events=" + events +
                '}';
    }


}
