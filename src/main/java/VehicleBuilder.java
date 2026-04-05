package main.java;

import java.util.Optional;

public class VehicleBuilder {
    String marke;
    String typ;
    String licensePlate;
    Optional<Integer> consumption;
    Optional<Integer> range;
    Optional<Integer> fuelTankCapacity;
    Optional<Integer> seatCapacity;
    Optional<Integer> speed;

    public VehicleBuilder(String marke, String typ, String licensePlate) {
        this.marke = marke;
        this.typ = typ;
        this.licensePlate = licensePlate;

        consumption = Optional.empty();
        range = Optional.empty();
        fuelTankCapacity = Optional.empty();
        seatCapacity = Optional.empty();
        speed = Optional.empty();
    }

    public VehicleBuilder setConsumption(int consumption) {
        this.consumption = Optional.of(consumption);
        return this;
    }

    public VehicleBuilder setRange(int range) {
        this.range = Optional.of(range);
        return this;
    }

    public VehicleBuilder setFuelTankCapacity(int fuelTankCapacity) {
        this.fuelTankCapacity = Optional.of(fuelTankCapacity);
        return this;
    }

    public VehicleBuilder setSeatCapacity(int seatCapacity) {
        this.seatCapacity = Optional.of(seatCapacity);
        return this;
    }

    public VehicleBuilder setSpeed(int speed) {
        this.speed = Optional.of(speed);
        return this;
    }


    public Vehicle build() {
        return new Vehicle(this);
    }

}