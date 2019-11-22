package Rijksmuseum.DTO;

import Rijksmuseum.Model.Beacon;

import java.util.ArrayList;
import java.util.List;

public class LongestVisitBeacons {
    private List<Beacon> beacons;
    private long time;

    public LongestVisitBeacons() {
        this.beacons = new ArrayList<>();
    }

    public LongestVisitBeacons(List<Beacon> beacons, long time) {
        this.beacons = beacons;
        this.time = time;
    }

    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
