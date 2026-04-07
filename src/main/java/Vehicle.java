package main.java;

import java.util.Optional;

public class Vehicle {

    private String marke;
    private String typ;
    private String licensePlate;
    private Optional<Double> consumption;
    private Optional<Integer> range;
    private Optional<Integer> fuelTankCapacity;
    private Optional<Integer> seatCapacity;
    private Optional<Integer> speed;


    Vehicle(VehicleBuilder builder) {
        marke = builder.marke;
        typ = builder.typ;
        licensePlate = builder.licensePlate;
        consumption = builder.consumption;
        range = builder.range;
        fuelTankCapacity = builder.fuelTankCapacity;
        seatCapacity = builder.seatCapacity;
        speed = builder.speed;
    }

    String getMarke() { return marke; }
    String getTyp() { return typ; }
    String getLicensePlate() { return licensePlate; }
    Optional<Double> getConsumption() { return consumption; }
    Optional<Integer> getRange() { return range; }
    Optional<Integer> getFuelTankCapacity() { return fuelTankCapacity; }
    Optional<Integer> getSeatCapacity() { return seatCapacity; }
    Optional<Integer> getSpeed() { return speed; }

    public String toString() {
        String result = String.format("Marke: %s, Typ: %s, Kennzeichen: %s", marke, typ, licensePlate);

        if (consumption.isPresent()) {
            result += String.format(", Verbrauch liegt bei: %.1f l/km ", consumption.get());
        }

        if (range.isPresent()) {
            result += String.format(", Reichweite: %d km ", range.get());
        }

        if (fuelTankCapacity.isPresent()) {
            result += String.format(", Tankkapazität: %d Liter ", fuelTankCapacity.get());
        }

        if (seatCapacity.isPresent()) {
            result += String.format(", Sitzkapazität: %d Plätze ", seatCapacity.get());
        }


        if (speed.isPresent()) {
            result += String.format(", Geschwindigkeit: %d km/h ", speed.get());
        }

        return result;
    }
    
}
