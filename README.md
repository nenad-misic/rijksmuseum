# Rijksmuseum - Challenge

One solution for challenge phase of "5 dana na javi" hackaton organized by Levi9.

## Environment

This project is developed on top of Apache Maven 3.6.2 and Java jdk 1.8.0_231.

## Build

In order to successfully build the project, given command should be executed in the root folder of project (where the pom.xml file is located):
```
mvn clean install
```

## Running the application

After successfully building the application, you can start it by executing given command in the same directory (where the pom.xml file is located):
```
mvn exec:java -D exec.mainClass=Rijksmuseum.App -D exec.args="BeaconFilePath EventFilePath Method Limit"
```

Example for parameters
```
mvn exec:java -D exec.mainClass=Rijksmuseum.App -D exec.args="./resources/beacons.csv ./resources/events.json 4 5"
```

## Technologies used in project

Maven - version 3.6.2
Java - jdk version 1.8.0_231
Version control - git

## Class diagram
```mermaid
classDiagram
class Beacon
Beacon : long beaconId
Beacon : String beaconName
Beacon : float beaconLocationX
Beacon : float beaconLocationY
Beacon : String beaconAudioFilepath

class Event
Event: long eventId
Event: String phone
Event: long timeStamp
Event: string type
RateEvent --|> Event
RateEvent: int rating


Event --* Beacon

class BeaconRepository
BeaconRepository *--> Beacon
BeaconRepository: MostPopularBeacons findMostPopularBeacons()
BeaconRepository: LongestVisitBeacons findLongestVisitBeacons()
BeaconRepository: MostPersistentVisitors findMostPersistentVisitors()
BeaconRepository: Collection<Beacon> findMostLeftBeacons()

class BeaconService
BeaconService --> BeaconRepository
BeaconService : MostPopularBeacons findMostPopularBeacons()
BeaconService : LongestVisitBeacons findLongestVisitBeacons()
BeaconService: MostPersistentVisitors findMostPersistentVisitors()
BeaconService : Collection<Beacon> findMostLeftBeacons()

class BeaconController
BeaconController --> BeaconService
BeaconController: MostPopularBeacons findMostPopularBeacons()
BeaconController: LongestVisitBeacons findLongestVisitBeacons()
BeaconController: MostPersistentVisitors findMostPersistentVisitors()
BeaconController: Collection<Beacon> findMostLeftBeacons()

class LongestVisitBeacons
LongestVisitBeacons *--> Beacon
LongestVisitBeacons: long time
class MostPopularBeacons
MostPopularBeacons *--> Beacon
MostPopularBeacons : int visits
class MostPersistentVisitors
MostPersistentVisitors *--> PhonePath
class PhonePath
PhonePath: string phone
PhonePath *--> Beacon
```