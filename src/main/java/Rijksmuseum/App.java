package Rijksmuseum;

import Rijksmuseum.BLL.BeaconService;
import Rijksmuseum.CL.BeaconController;
import Rijksmuseum.DAO.BeaconRepository;
import Rijksmuseum.DTO.LongestVisitBeacons;
import Rijksmuseum.DTO.MostPersistentVisitors;
import Rijksmuseum.DTO.MostPopularBeacons;
import Rijksmuseum.DTO.PhonePath;
import Rijksmuseum.Model.Beacon;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void executeQuery(int method, int limit, BeaconController controller) {
        if (method == 1) {
            MostPopularBeacons mpb = controller.findMostPopularBeacons();
            System.out.println("The most popular beacons are: ");
            for (Beacon b : mpb.getBeacons()) {
                System.out.println("\tBeacon id: " + b.getBeaconId());
            }
            System.out.println("Number of visits: " + mpb.getVisits());
        } else if (method == 2) {
            LongestVisitBeacons lvb = controller.findLongestVisitBeacons();
            System.out.println("Beacons with the longest visit time are: ");
            for (Beacon b : lvb.getBeacons()) {
                System.out.println("\tBeacon id: " + b.getBeaconId());
            }
            System.out.println("Time spent near beacons: " + lvb.getTime() / 60 + " minutes");

        } else if (method == 3) {
            MostPersistentVisitors mpv = controller.findMostPersistentVisitors(limit);


            System.out.println("The visitors with the most beacon visits are: ");
            for (PhonePath pp : mpv.getPhonePaths()) {
                String tmp = pp.getPhone() + ": ";
                for (Beacon b : pp.getBeaconsPath()) {
                    tmp += b.getBeaconId() + " -> ";
                }
                tmp = tmp.substring(0, tmp.length() - 4);
                System.out.println("\t " + tmp);
            }

        } else if (method == 4) {
            Collection<Beacon> mlb = controller.findMostLeftBeacons(limit);

            System.out.println("The beacons mostly left before audio finish: ");
            for (Beacon b : mlb) {
                System.out.println("\tBeacon id: " + b.getBeaconId());
            }
        } else {
            System.out.println("Unknown command " + method + "!");
        }
    }
    public static void main( String[] args ) {
        if (args.length == 4) {

            String beaconFilePath = args[0];
            String eventFilePath = args[1];
            int method = Integer.parseInt(args[2]);
            int limit = Integer.parseInt(args[3]);
            BeaconRepository repository = new BeaconRepository(beaconFilePath, eventFilePath);
            BeaconService service = new BeaconService(repository);
            BeaconController controller = new BeaconController(service);

            executeQuery(method, limit, controller);
        }
    }
}
