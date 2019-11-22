package Rijksmuseum.DTO;

import Rijksmuseum.Model.Beacon;

import java.util.ArrayList;
import java.util.List;

public class PhonePath {
    private String phone;
    private List<Beacon> beaconsPath;

    public PhonePath() {
        this.beaconsPath = new ArrayList<>();
    }

    public PhonePath(String phone, List<Beacon> beaconsPath) {

        this.phone = phone;
        this.beaconsPath = beaconsPath;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Beacon> getBeaconsPath() {
        return beaconsPath;
    }

    public void setBeaconsPath(List<Beacon> beaconsPath) {
        this.beaconsPath = beaconsPath;
    }
}
