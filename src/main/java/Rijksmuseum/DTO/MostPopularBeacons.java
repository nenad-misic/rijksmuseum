package Rijksmuseum.DTO;

import Rijksmuseum.Model.Beacon;

import java.util.List;

public class MostPopularBeacons {
    private List<Beacon> beacons;
    private int visits;

    public MostPopularBeacons() {
    }

    public MostPopularBeacons(List<Beacon> beacons, int visits) {
        this.beacons = beacons;
        this.visits = visits;
    }

    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }
}
