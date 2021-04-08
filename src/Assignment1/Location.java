package Assignment1;

import java.util.List;

public class Location {
    String name;
    double latitude;
    double longitude;
    double demandCoefficient;
    List<Flight> arriveFlights;
    List<Flight> departFlights;


    public Location(String name, double latitude, double longitude, double demandCoefficient) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.demandCoefficient = demandCoefficient;
    }

    public List<Flight> getArriveFlights() {
        return arriveFlights;
    }

    public void setArriveFlights(List<Flight> arriveFlights) {
        this.arriveFlights = arriveFlights;
    }

    public List<Flight> getDepartFlights() {
        return departFlights;
    }

    public void setDepartFlights(List<Flight> departFlights) {
        this.departFlights = departFlights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDemandCoefficient() {
        return demandCoefficient;
    }

    public void setDemandCoefficient(double demandCoefficient) {
        this.demandCoefficient = demandCoefficient;
    }

    @Override
    public String toString() {
        return name + ","
                + latitude + ","
                + longitude + ","
                + demandCoefficient + ","
                + "\n";
    }
}
