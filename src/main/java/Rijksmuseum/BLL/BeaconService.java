package Rijksmuseum.BLL;

import Rijksmuseum.DAO.BeaconRepository;
import Rijksmuseum.DTO.LongestVisitBeacons;
import Rijksmuseum.DTO.MostPersistentVisitors;
import Rijksmuseum.DTO.MostPopularBeacons;
import Rijksmuseum.Model.Beacon;

import java.util.Collection;
import java.util.List;

public class BeaconService {
    private BeaconRepository repository;

    public BeaconService(BeaconRepository repository) {
        this.repository = repository;
    }

    public MostPopularBeacons findMostPopularBeacons() {
        return this.repository.findMostPopularBeacons();
    }
    public LongestVisitBeacons findLongestVisitBeacons() {
        return this.repository.findLongestVisitBeacons();
    }
    public MostPersistentVisitors findMostPersistentVisitors(int limit) {
        return this.repository.findMostPersistentVisitors(limit);
    }
    public Collection<Beacon> findMostLeftBeacons(int limit) {
        return this.repository.findMostLeftBeacons(limit);
    }

    public List<Beacon> getAllBeacons() {return this.repository.getAllBeacons();}


}
