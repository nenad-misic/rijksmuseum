package Rijksmuseum.CL;

import Rijksmuseum.BLL.BeaconService;
import Rijksmuseum.DTO.LongestVisitBeacons;
import Rijksmuseum.DTO.MostPersistentVisitors;
import Rijksmuseum.DTO.MostPopularBeacons;
import Rijksmuseum.Model.Beacon;

import java.util.Collection;
import java.util.List;

public class BeaconController {
    private BeaconService service;

    public BeaconController(BeaconService service) {
        this.service = service;
    }

    public MostPopularBeacons findMostPopularBeacons() {
        return this.service.findMostPopularBeacons();
    }
    public LongestVisitBeacons findLongestVisitBeacons() {
        return this.service.findLongestVisitBeacons();
    }
    public MostPersistentVisitors findMostPersistentVisitors(int limit) {
        return this.service.findMostPersistentVisitors(limit);
    }
    public Collection<Beacon> findMostLeftBeacons(int limit) {
        return this.service.findMostLeftBeacons(limit);
    }


    public List<Beacon> getAllBeacons() { return this.service.getAllBeacons();}
}
