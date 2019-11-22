package Rijksmuseum.DAO;

import Rijksmuseum.DTO.LongestVisitBeacons;
import Rijksmuseum.DTO.MostPersistentVisitors;
import Rijksmuseum.DTO.MostPopularBeacons;
import Rijksmuseum.DTO.PhonePath;
import Rijksmuseum.Model.Beacon;
import Rijksmuseum.Model.Event;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.util.*;

public class BeaconRepository {
    private List<Beacon> beacons;
    private List<Event> events;
    private String beaconFilePath;
    private String eventFilePath;

    public BeaconRepository(String beaconFilePath, String eventFilePath) {
        this.beaconFilePath = beaconFilePath;
        this.eventFilePath = eventFilePath;
        this.beacons = new ArrayList<>();
        this.events = new ArrayList<>();
        getBeacons();
        getEvents();
    }

    public BeaconRepository(List<Beacon> beacons, List<Event> events, String beaconFilePath, String eventFilePath) {
        this.beacons = beacons;
        this.events = events;
        this.beaconFilePath = beaconFilePath;
        this.eventFilePath = eventFilePath;
    }

    public MostPopularBeacons findMostPopularBeacons() {
        List<Beacon> maxBeacons = new ArrayList<>();
        Beacon maxBeacon = null;
        int maxCount = 0;
        for(Beacon b : beacons){
            int currentCount = 0;
            for(Event e : b.getEvents()){
                if(e.getType().equals("START")){
                    currentCount++;
                }
            }
            if(currentCount > maxCount){
                maxBeacons = new ArrayList<>();
                maxBeacon = b;
                maxCount = currentCount;
            }else if (currentCount == maxCount){
                maxBeacons.add(b);
            }
        }

        maxBeacons.add(maxBeacon);

        return new MostPopularBeacons(maxBeacons, maxCount);
    }
    public LongestVisitBeacons findLongestVisitBeacons() {
        int maxTime = 0;
        Beacon maxBeacon = null;
        List<Beacon> maxBeacons = new ArrayList<>();
        for(Beacon b: this.beacons){
            int totalTime = 0;
            for(int i = 0; i < b.getEvents().size(); i++){
                if(b.getEvents().get(i).getType().equals("START")){
                    for(int j = i; j < b.getEvents().size(); j++){
                        if(
                                (
                                        b.getEvents().get(j).getType().equals("LEAVE") ||
                                        b.getEvents().get(j).getType().equals("STOP")
                                ) &&
                                        b.getEvents().get(j).getPhone().equals(b.getEvents().get(i).getPhone())){
                            totalTime += (b.getEvents().get(j).getTimeStamp() - b.getEvents().get(i).getTimeStamp());
                        }
                    }
                }
            }

            if(totalTime > maxTime){
                maxBeacons = new ArrayList<>();
                maxBeacon = b;
                maxTime = totalTime;
            }else if(totalTime == maxTime){
                maxBeacons.add(b);
            }
        }

        maxBeacons.add(maxBeacon);

        return new LongestVisitBeacons(maxBeacons, maxTime);
    }

    public MostPersistentVisitors findMostPersistentVisitors(int limit) {
        HashMap<String, ArrayList<Beacon>> visitors = new HashMap<>();
        for (Beacon b : beacons) {
            for (Event e : b.getEvents()) {
                if (e.getType().equals("START")) {
                    if (!visitors.containsKey(e.getPhone())) {
                        visitors.put(e.getPhone(), new ArrayList<Beacon>());
                    }
                    visitors.get(e.getPhone()).add(b);
                }
            }
        }
        ArrayList<PhonePath> phonePaths = new ArrayList<>();
        for (String key : visitors.keySet()) {
            PhonePath pp = new PhonePath(key, visitors.get(key));
            phonePaths.add(pp);
        }

        phonePaths.sort(Comparator.comparingInt(o -> o.getBeaconsPath().size()));
        ArrayList<PhonePath> maxVisitors = new ArrayList<>();
        if (phonePaths.size() <= 3) {
            maxVisitors = phonePaths;
        } else {
            maxVisitors = new ArrayList<>(phonePaths.subList(phonePaths.size()-limit, phonePaths.size()));
        }
        Collections.reverse(maxVisitors);
        return new MostPersistentVisitors(maxVisitors);

    }
    public Collection<Beacon> findMostLeftBeacons(int limit) {
        ArrayList<Beacon> e = new ArrayList<Beacon>(beacons);
        e.sort((o1, o2) -> {
            int o1Stops = 0;
            int o2Stops = 0;
            for (Event e1 : o1.getEvents()){
                if (e1.getType().equals("STOP")){
                    o1Stops++;
                }
            }
            for (Event e2 : o2.getEvents()){
                if(e2.getType().equals("STOP")){
                    o2Stops++;
                }
            }

            return o2Stops - o1Stops;
        });

        return e.subList(0,limit);
    }

    private Beacon findBeaconOfId(long id){
        for(Beacon b: this.beacons){
            if(b.getBeaconId() == id){
                return b;
            }
        }
        return null;
    }
    private List<Beacon> getBeacons(){
        if(this.beacons.size() == 0){
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(beaconFilePath));
                String row = csvReader.readLine();
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    Beacon b  = new Beacon(
                            Long.parseLong(data[0]),
                            data[1],
                            Float.parseFloat(data[2]),
                            Float.parseFloat(data[3]),
                            data[4]
                    );

                    this.beacons.add(b);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return this.beacons;
    }

    private List<Event> getEvents() {
        if(this.events.size() == 0){
            byte[] data = new byte[0];
            try {
                File f = new File(eventFilePath);
                FileInputStream fis = new FileInputStream(f);
                data = new byte[(int) f.length()];
                fis.read(data);
                fis.close();
                String str = new String(data, "UTF-8");

                Object obj = JSONValue.parse(str);
                JSONArray array = (JSONArray)obj;
                for(int i = 0; i < array.size(); i++){
                    JSONObject current = (JSONObject) array.get(i);
                    Beacon b = null;
                    if(current.get("beaconId") != null){
                        b = findBeaconOfId((Long)current.get("beaconId"));
                    }
                    Event e = new Event(
                            (Long)current.get("id"),
                            (String) current.get("phone"),
                            Long.parseLong((String)current.get("time")),
                            (String) current.get("type"),
                            b
                    );
                    if(b != null){
                        b.addEvent(e);
                    }
                    this.events.add(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return this.events;
    }


    public List<Beacon> getAllBeacons(){
        return this.beacons;
    }
}
